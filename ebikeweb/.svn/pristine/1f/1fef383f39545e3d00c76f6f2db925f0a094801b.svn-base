/**
 * @Description: TODO(应收单价审核)
 * @author liuwu
 * @date 2014-12-24 下午1:26:04
 * @version V1.0
 */
package com.clt.sub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TDriverCost;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.IDriverCostService;
import com.clt.sub.service.ISubCarStyleService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.sub.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-24 下午1:26:04
 * @version V1.0
 */
@Controller
@RequestMapping( "/driverCostCheckAction" )
@ApiIgnore
public class DriverCostCheckAction
{
	
	@Autowired
	ITruckDriverService truckDriverService;
	@Autowired
	IDriverCostService driverCostService;
	@Autowired
	ICustomerSerivce customerSerivce;
	@Autowired
	ISubCarStyleService iSubCarStyleService;
	@Autowired
	ICityService cityService;
	@Autowired
	ISubsuppliersService subService;
	
	@RequestMapping( "/check" )
	@ApiIgnore
	public String intoOrderInfoBysubno( HttpServletRequest request )
	{
		
		return "sub/subBasicData/driverCostInfoListCheck";
		
	}
	
	/**
	 * 
	 * @Description: TODO(查询)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-24 下午1:42:09
	 */
	@RequestMapping( "/getAllDriverCostBySubNo" )
	@ResponseBody
	public Map< String , Object > getAllDriverCostBySubNo( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		Page page = ServiceUtil.getcurrPage( request );
		
		String sql = "select td.id as ID, to_char(td.dt_start,'yyyy-mm-dd ') as DT_START,to_char(td.dt_end,'yyyy-mm-dd ') as DT_END, td.n_cost, td.dt_add, td.n_enable, td.vc_subno, td.VC_UPDATE_USER, td.vc_start_city, td.vc_end_city, td.i_customerid, td.i_start_id, td.i_end_id, td.i_sub_carid,td.N_CHECK,tc.id as customerid,tc.vc_short_name , ts.id as carid,ts.vc_car_style as VC_CAR_STYLE"
		        + " from T_DRIVER_COST td left join T_CUSTOMER tc on td.i_customerid = tc.id"
		        + " left join t_sub_car_style ts on td.i_sub_carid = ts.id where td.vc_subno='"
		        + subbo + "' and td.N_ENABLE=0";
		
		String startCity = request.getParameter( "startCity" );
		String endCity = request.getParameter( "endCity" );
		
		if ( StringUtils.isNotBlank( startCity ) )
		{
			sql += "and td.VC_START_CITY like '%" + startCity + "%'";
		}
		if ( StringUtils.isNotBlank( endCity ) )
		{
			sql += "and td.VC_END_CITY like '%" + endCity + "%'";
		}
		if ( StringUtils.isNotBlank( request.getParameter( "check" ) ) )
		{
			int checkFlag = Integer.parseInt( request.getParameter( "check" ) );
			sql += "and td.N_CHECK = " + checkFlag;
		}
		System.out.println( "sql =" + sql );
		Map< String , Object > orderMap = driverCostService.getSpringSQL( sql , page );
		
		return orderMap;
		
	}
	
	/**
	 * 
	 * @Description: TODO(审核通过)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-24 下午2:59:42
	 */
	@RequestMapping( "/checkyes" )
	public void checkyes( HttpServletRequest request , HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		List< TDriverCost > tDriverCosts = new ArrayList< TDriverCost >();
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int id = Integer.parseInt( ids[i] );
			TDriverCost driverCost = driverCostService.get( id );
			driverCost.setNCheck( SystemConstants.SYS_CHECK_YES );
			driverCost.setDtAdd( new Date() );
			driverCost.setVcUpdateUser( user.getVcAccount() );
			tDriverCosts.add( driverCost );
			
		}
		try
		{
			driverCostService.saveOrUpdateList( tDriverCosts );
			AjaxUtil.rendJson( response , true , "审核通过" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "审核失败!" );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(取消审核)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-24 下午3:07:58
	 */
	@RequestMapping( "/checkno" )
	public void checkno( HttpServletRequest request , HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		List< TDriverCost > tDriverCosts = new ArrayList< TDriverCost >();
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int id = Integer.parseInt( ids[i] );
			TDriverCost driverCost = driverCostService.get( id );
			driverCost.setNCheck( SystemConstants.SYS_CHECK_NO );
			driverCost.setDtAdd( new Date() );
			driverCost.setVcUpdateUser( user.getVcAccount() );
			tDriverCosts.add( driverCost );
			
		}
		try
		{
			driverCostService.saveOrUpdateList( tDriverCosts );
			AjaxUtil.rendJson( response , true , "已取消审核" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "操作失败!" );
		}
	}
}
