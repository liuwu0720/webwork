/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-8 上午10:54:11 
 * @version V1.0 
 */
package com.clt.sub.controller;

import java.util.Date;
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

import com.clt.sub.model.TArorderDriver;
import com.clt.sub.service.IAorderDriverService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.sub.controller
 * @Description: TODO(司机支出审核)
 * @author liuwu
 * @date 2015-6-8 上午10:54:11
 * @version V1.0
 */
@Controller
@RequestMapping( "/arorderDriverAction" )
@ApiIgnore
public class ArorderDriverAction
{	
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	IAorderDriverService iAorderDriverService;

	/**
	 * 
	 * @Description: TODO(司机支出审核)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-8 上午10:57:39
	 */
	@RequestMapping( "/intogetArorderDriverBySubno" )
	@ApiIgnore
	public String intogetAllReturnInfoBySubno( HttpServletRequest request )
	{
		return "sub/arorder/arOrderDriverList";
	}
	
	/**
	 * 
	 * @Description: TODO(获取司机支出审核)
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-8 下午1:32:44
	 */
	@RequestMapping( "/getArOrderDriverlist" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getArOrderDriverlist(
	        HttpServletRequest request , String vcDriverName )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		Map< String , Object > resuMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
			Page page = ServiceUtil.getcurrPage( request );
			HqlHelper hql = new HqlHelper( TArorderDriver.class );
			hql.setQueryPage( page );
			hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
			hql.addEqual( "vcSubno" , subbo );
			hql.addEqual( "nCreateBill" , 1 );// 是否生成对帐单(0:未生成，1已生成)
			if ( StringUtils.isNotBlank( vcDriverName ) )
			{
				hql.addEqual( "vcDriver" , vcDriverName );
			}
			hql.addOrderBy( "id" , "desc" );
			resuMap = iAorderDriverService.findAllByHqlHelp( hql );
			return AjaxUtil.getMapByResult( visit , resuMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(司机支出审核)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-8 下午3:25:58
	 */
	@RequestMapping( value = "/audit" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public void audit( HttpServletRequest request ,
	        HttpServletResponse response ,
	        @RequestParam( value = "array[]" , required = true ) String array[] ,
	        String vcAuditNote ,
	        String vcResult )
	{	
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			for ( int i = 0 ; i < array.length ; i++ )
			{
				int id = Integer.parseInt( array[i] );
				TArorderDriver tArorderDriver = iAorderDriverService
				        .getById( id );
				// tArorderDriver.setnAudit( Integer.parseInt( vcResult ) );
				tArorderDriver.setVcAuditNote( vcAuditNote );
				tArorderDriver.setVcBillUser( user.getVcAccount() );
				tArorderDriver.setDtBill( new Date() );
				tArorderDriver.setnBill( Integer.parseInt( vcResult ) );
				iAorderDriverService.saveOrUpdate( tArorderDriver );
			}
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			// TODO: handle exception
			AjaxUtil.rendJson( response , false , "失败！原因：" + e.getMessage() );
		}
	}

}
