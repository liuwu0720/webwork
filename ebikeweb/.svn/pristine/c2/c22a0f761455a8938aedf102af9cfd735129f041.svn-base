package com.clt.sub.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TCity;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TDriverCost;
import com.clt.sub.model.TProvince;
import com.clt.sub.model.TSubCarStyle;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.IDriverCostService;
import com.clt.sub.service.ISubCarStyleService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
 * @Package com.clt.sub.controller
 * @Description: TODO(应收单价维护)
 * @author liuwu
 * @date 2014-12-24 上午11:06:01
 * @version V1.0
 */
@Controller
@RequestMapping( "/driverCostAction" )
@Api( value = "DriverCostAction-api" , description = "有关于应收单价维护的CURD操作" , position = 5 )
public class DriverCostAction
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
	
	// 应收单价 列表编辑 和指令选择订单发运数量一样 传 司机车辆ＩＤ和单价到后台　
	
	@RequestMapping( "/intoGetAllDriverCost" )
	@ApiIgnore
	public String intoOrderInfoBysubno( HttpServletRequest request )
	{
		
		return "sub/subBasicData/driverCostInfoList";
		
	}
	
	/**
	 * @Description: 获取该分供方所有的 应收单价信息
	 * @return
	 * @throws
	 */
	@ApiOperation( value = "获取该分供方所有有效的应收单价信息" , notes = "从会话中获得该用户的信息，从而获取该分供方所有有效的 应收单价信息" , position = 5 )
	@RequestMapping( value = "/getAllDriverCostBySubNo" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllDriverCostBySubNo(
	        @ApiParam( value = "开始城市，搜索条件，可空" , required = false ) @RequestParam( value = "startCity" , required = false ) String startCity ,
	        @ApiParam( value = "目的城市，搜索条件，可空" , required = false ) @RequestParam( value = "endCity" , required = false ) String endCity ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TDriverCost.class );
		hql.setQueryPage( page );
		
		hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqual( "vcSubno" , subbo );
		if ( StringUtils.isNotBlank( startCity ) )
		{
			hql.addEqual( "vcStartCity" , startCity );
		}
		if ( StringUtils.isNotBlank( endCity ) )
		{
			hql.addEqual( "vcEndCity" , endCity );
		}
		
		Map< String , Object > resuMap = driverCostService.findByHql( hql );
		

		return resuMap;

	}
	
	/**
	 * 
	 * @Description: TODO(点击修改)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-22 下午4:46:32
	 */
	@RequestMapping( "/saveBefore" )
	@ApiIgnore
	public String saveBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
		
		String paramType = "add";
		String costID = request.getParameter( "costID" );// 表主键ID
		TDriverCost tDriverCost = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tDriverCost = driverCostService.get( Integer.parseInt( costID ) );// 应收单价表
			if ( tDriverCost.getIStartId() != null )
			{
				TCity stacity = cityService.getCityByID( tDriverCost.getIStartId() );
				TProvince stapro = cityService.getProvinceByID( stacity.getProvinceid() );
				request.setAttribute( "stapro" , stapro );
				request.setAttribute( "stacity" , stacity );
			}
			if ( tDriverCost.getIEndId() != null )
			{
				TCity endcity = cityService.getCityByID( tDriverCost.getIEndId() );
				TProvince endpro = cityService.getProvinceByID( endcity.getProvinceid() );
				
				request.setAttribute( "endpro" , endpro );
				request.setAttribute( "endcity" , endcity );
			}

		}

		List< TCustomer > tCustomerList = customerSerivce.findAllBySubNo( subbo );
		request.setAttribute( "tCustomerList" , tCustomerList );
		
		List< TSubCarStyle > subCarStyles = iSubCarStyleService.findAllBySubNo( subbo );
		request.setAttribute( "subCarStyles" , subCarStyles );
		
		request.setAttribute( "tDriverCost" , tDriverCost );
		
		List< TProvince > prolist = cityService.getAllProvince();
		request.setAttribute( "prolist" , prolist );
		request.setAttribute( "paramType" , paramType );
		return "sub/subBasicData/driverCostSave";
		
	}
	
	/**
	 * @Description: 根据id，获得对应应收单价信息，该方法只提供给app
	 * @param costID
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月16日 上午11:36:39
	 */
	@ApiOperation( value = "获得应收单价信息" , notes = "根据id，获得对应应收单价信息，该方法只提供与app" , position = 5 )
	@RequestMapping( value = "getDriverCostById" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDriverCostByIdToApp(
	        @ApiParam( value = "应收单价信息id" , required = true ) @RequestParam( "costID" ) Integer costID )
	{
		
		try
		{
			TDriverCost tDriverCost = driverCostService.get( costID );
			if ( null != tDriverCost )
			{
				return AjaxUtil.getMapByNotException( true , tDriverCost );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , tDriverCost );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 保存应收单价信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiIgnore
	@RequestMapping( value = "/saveDriverCost" , method = RequestMethod.POST )
	public void save( HttpServletRequest request , HttpServletResponse resp ,
	        TDriverCost tDriverCost )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		TSubCarStyle tSubCarStyle = iSubCarStyleService.get( tDriverCost
		        .getISubCarid() );
		TCustomer tCustomer = customerSerivce
		        .get( tDriverCost.getICustomerid() );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
		tDriverCost.setVcUpdateUser( user.getVcAccount() );
		tDriverCost.setVcSubno( subbo );
		TCity startCity = cityService.getCityByID( tDriverCost.getIStartId() );
		TCity endCity = cityService.getCityByID( tDriverCost.getIEndId() );
		tDriverCost.setVcStartCity( startCity.getCityname() );
		tDriverCost.setVcEndCity( endCity.getCityname() );
		tDriverCost.setNCheck( SystemConstants.SYS_CHECK_NO );// 未审核
		tDriverCost.setNEnable( SystemConstants.SYS_ENABLE );
		tDriverCost.setDtAdd( new Date() );
		tDriverCost.setVcCarName( tSubCarStyle.getVcCarStyle() );
		tDriverCost.setVcCustomer( tCustomer.getVcCustomerName() );
		tDriverCost.setVcUpdateUser( user.getVcAccount() );
		if ( tDriverCost.getId() != null )// 编辑
		{
			try
			{
				
				driverCostService.saveOrUpdate( tDriverCost );
				AjaxUtil.rendJson( resp , true , "编辑保存成功." );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				AjaxUtil.rendJson( resp , false , "编辑保存失败！失败原因：" + e.getMessage() );
				
			}
		}
		else
		// 新增
		{
			
			try
			{
				/**
				 * 新增的时候验证重复性
				 */
				String message = checkIfExistSame( tDriverCost );
				if ( message.equalsIgnoreCase( "success" ) )
				{
					driverCostService.save( tDriverCost );
					AjaxUtil.rendJson( resp , true , "新增保存成功！" );
				}
				else
				{
					AjaxUtil.rendJson( resp , false , "操作失败,原因：" + message );
				}

			}
			catch ( Exception e )
			{
				AjaxUtil.rendJson( resp , false , "新增保存失败！" );
			}
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDriverCost
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-28 上午11:57:02
	 */
	private String checkIfExistSame( TDriverCost tDriverCost )
	{
		String message = "success";
		try
		{
			List< TDriverCost > tDriverCosts = driverCostService
			        .checkIfExist( tDriverCost );
			if ( tDriverCosts.size() > 0 )
			{
				message = "【" + tDriverCost.getVcStartCity() + "】至【"
				        + tDriverCost.getVcEndCity() + "】客户【"
				        + tDriverCost.getVcCustomer() + "】车型【"
				        + tDriverCost.getVcCarName() + "】的应收单价已 维护，不能有重复数据！";
			}
		}
		catch ( Exception e )
		{
			message = e.getMessage();
		}
		return message;
	}

	/**
	 * @Description: 结算应收 维护单价
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-14 下午6:13:40
	 */
	@RequestMapping( "/arsaveByCost" )
	@ApiIgnore
	public void arsaveByCost( HttpServletRequest request , TDriverCost cost ,
	        HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		cost.setVcSubno( subbo );
		try
		{
			driverCostService.save( cost );
			AjaxUtil.rendJson( resp , true , "保存成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( resp , false , "保存失败." + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 结算应收 维护单价 页面进入之前加载数据
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-14 下午5:56:40
	 */
	@RequestMapping( "/ArordersaveBefore" )
	@ApiIgnore
	public String ArordersaveBefore( HttpServletRequest request )
	{
		
		int driverID = Integer.parseInt( request.getParameter( "driverID" ) );
		TTruckDriver driver = truckDriverService.get( driverID );
		
		request.setAttribute( "driver" , driver );
		return "sub/subBasicData/arbysaveCost";
	}
	
	/**
	 * @Description: 删除应收单价信息
	 * @return
	 * @throws
	 */
	@RequestMapping( "/del" )
	@ApiIgnore
	public void del( HttpServletRequest request , HttpServletResponse response )
	{
		int costID = Integer.parseInt( request.getParameter( "costID" ) );
		try
		{
			TDriverCost drivercost = driverCostService.get( costID );
			drivercost.setNEnable( SystemConstants.SYS_DISABLE );
			driverCostService.update( drivercost );
			AjaxUtil.rendJson( response , true , "删除成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "操作失败!" );
		}
		
	}
	
}
