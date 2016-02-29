package com.clt.sub.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TArkilometer;
import com.clt.sub.model.TCity;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TProvince;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.service.IArkilometerService;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping( "/arkilometerAction" )
@ApiIgnore
public class ArkilometerAction
{
	
	@Autowired
	IArkilometerService arkilometerService;
	@Autowired
	ICityService cityService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	ICustomerSerivce customerSerivce;
	
	/**
	 * @Description: 获取该分供方所有的 应收公里数信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/getAllArkilometerBySubNo" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllArkilometerBySubNo(
	        HttpServletRequest request, 
	        @ApiParam( value = "开始城市，搜索条件，可空" , required = false ) @RequestParam( value = "startCity" , required = false ) String startCity ,
	        @ApiParam( value = "目的城市，搜索条件，可空" , required = false ) @RequestParam( value = "endCity" , required = false ) String endCity  )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TArkilometer.class );
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
		
		
		Map< String , Object > resuMap = arkilometerService
		        .findAllByHqlHelp( hql );
		
		if ( resuMap.get( "rows" ) != null )
		{
			List relist = ( List ) resuMap.get( "rows" );
			System.out.println( "resuMap size " + relist.size() );
		}
		else
		{
			System.out.println( "无数据  " );
		}
		
		return resuMap;
		
	}
	
	/**
	 * 
	 * @Description: 跳转页面 因JSP位于web-info 下面只能转发
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-11 下午1:20:51
	 */
	@RequestMapping( "/intogetAllArkilometer" )
	@ApiIgnore
	public String intogetAllArkilometer( HttpServletRequest request )
	{
		return "sub/subBasicData/arkilometerInfoList";
		
	}
	
	/**
	 * 
	 * @Description: TODO(应收公里审核列表)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-21 下午1:45:10
	 */
	@RequestMapping( "/audit" )
	@ApiIgnore
	public String auditAllArkilometer( HttpServletRequest request )
	{
		return "sub/subBasicData/auditArkilometerList";
		
	}
	
	/**
	 * 
	 * @Description: TODO(应收公里审核)
	 * @param request
	 * @param startCity
	 * @param endCity
	 * @param vcCustomer
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-21 下午1:53:40
	 */
	@RequestMapping( "/getAuditArkilometers" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAuditArkilometers(
	        HttpServletRequest request ,
	        @ApiParam( value = "开始城市，搜索条件，可空" , required = false ) @RequestParam( value = "startCity" , required = false ) String startCity ,
	        @ApiParam( value = "目的城市，搜索条件，可空" , required = false ) @RequestParam( value = "endCity" , required = false ) String endCity ,
	        @ApiParam( value = "客户，搜索条件，可空" , required = false ) @RequestParam( value = "vcCustomer" , required = false ) String vcCustomer ,
	        @ApiParam( value = "是否审核，搜索条件，可空" , required = false ) @RequestParam( value = "check" , required = false ) String check )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TArkilometer.class );
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
		if ( StringUtils.isNotBlank( vcCustomer ) )
		{
			hql.addLike( "vcCustomer" , vcCustomer );
		}
		if ( StringUtils.isNotBlank( endCity ) )
		{
			hql.addEqual( "nCheck" , check );
		}
		Map< String , Object > resuMap;
		try
		{
			resuMap = arkilometerService.findAllByHqlHelp( hql );
			
			return resuMap;
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
		
	}

	/**
	 * @Description: 保存修改应收公里数 之前的操作 获取所需数据
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveBefore" )
	public String saveBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getiArchive();
		
		TSubsuppliers sub = subService.get( archid );
		
		String paramType = "add";
		String arkid = request.getParameter( "arkID" );
		if ( StringUtils.isNotBlank( arkid ) )
		{
			
			paramType = "update";
			
			TArkilometer ark = arkilometerService
			        .get( Integer.parseInt( arkid ) );
			JSONObject jsonObject = new JSONObject();

			int stacityID = ark.getIStartId();
			int endcityID = ark.getIEndId();
			
			TCity stacity = cityService.getCityByID( stacityID );
			TProvince stapro = cityService.getProvinceByID( stacity
			        .getProvinceid() );
			
			TCity endcity = cityService.getCityByID( endcityID );
			TProvince endpro = cityService.getProvinceByID( endcity
			        .getProvinceid() );
			
			request.setAttribute( "stapro" , stapro );
			request.setAttribute( "stacity" , stacity );
			
			request.setAttribute( "endpro" , endpro );
			request.setAttribute( "endcity" , endcity );
			
			request.setAttribute( "ark" , ark );
		}
		
		List< TProvince > prolist = cityService.getAllProvince();
		List< TCustomer > tCustomerList = customerSerivce.findAllBySubNo( sub
		        .getVcSubno() );
		request.setAttribute( "tCustomerList" , tCustomerList );
		request.setAttribute( "prolist" , prolist );
		request.setAttribute( "paramType" , paramType );
		return "sub/subBasicData/saveArkilomter";
		
	}
	
	/**
	 * 
	 * @Description: 保存分供方 应收公里数
	 * @param request
	 * @param ark
	 *            要保存或者修改的公里数对象
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-11 下午1:22:34
	 */
	@RequestMapping( value = "/saveArkilomter" , method = RequestMethod.POST )
	public void saveArkilomter( HttpServletRequest request , TArkilometer ark ,
	        HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		TSubsuppliers sub = subService.get( user.getiArchive() );
		
		// int stacityID = Integer.parseInt( request.getParameter( "IStartId" )
		// );
		// int endcityID = Integer.parseInt( request.getParameter( "IEndId" ) );
		TCity stacity = cityService.getCityByID( ark.getIStartId() );
		TCity endcity = cityService.getCityByID( ark.getIEndId() );
		String paramType = request.getParameter( "paramType" );
		TCustomer tCustomer = customerSerivce.get( ark.getiCustomerId() );
		// ark.setDtStart( DateUtil.parseDate( beginStr ) );
		// ark.setDtEnd( DateUtil.parseDate( endStr ) );
		ark.setIStartId( stacity.getId() );
		ark.setIEndId( endcity.getId() );
		ark.setVcStartCity( stacity.getCityname() );
		ark.setVcEndCity( endcity.getCityname() );
		ark.setVcCustomer( tCustomer.getVcCustomerName() );
		ark.setVcUpdateUser( user.getVcAccount() );
		String message = "success";
		try
		{
			if ( "add".equals( paramType ) )
			{
				ark.setVcSubno( sub.getVcSubno() );
				List< TArkilometer > tArkilometers = arkilometerService
				        .checkIfExiste( ark );
				if ( tArkilometers.size() > 0 )
				{
					message = "【" + ark.getVcStartCity() + "】至【"
					        + ark.getVcEndCity() + "】客户【" + ark.getVcCustomer()
					        + "】的应收公里已维护，不能有重复数据！";
				}
			}
			// 验证编辑保存逻辑，如果起始地、客户、目的地都相同则不经重复验证
			if ( "update".equals( paramType ) )
			{
				TArkilometer newArkilometer = new TArkilometer();
				newArkilometer = arkilometerService.get( ark.getId() );
				if ( newArkilometer.getIStartId().equals( ark.getIStartId() )
				        && newArkilometer.getIEndId().equals( ark.getIEndId() )
				        && newArkilometer.getiCustomerId().equals(
				                ark.getiCustomerId() ) )
				{
					message = "success";
				}
				else
				{
					List< TArkilometer > tArkilometers = arkilometerService
					        .checkIfExiste( ark );
					if ( tArkilometers.size() > 0 )
					{
						message = "【" + ark.getVcStartCity() + "】至【"
						        + ark.getVcEndCity() + "】客户【"
						        + ark.getVcCustomer() + "】的应收公里已维护，不能有重复数据！";
					}
				}
				
			}
			if ( message.equalsIgnoreCase( "success" ) )
			{
				arkilometerService.saveOrUpdateCleanBefore( ark );
				AjaxUtil.rendJson( resp , true , "保存成功." );
			}
			else
			{
				AjaxUtil.rendJson( resp , false , message );

			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( resp , false , "保存失败." + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 结算应收 保存公里数
	 * @param request
	 * @param ark
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-14 下午5:20:43
	 */
	@RequestMapping( "/arbysaveArk" )
	public void arbysaveArk( HttpServletRequest request , TArkilometer ark ,
	        HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		TSubsuppliers sub = subService.get( user.getiArchive() );
		
		TCity stacity = cityService.getCityByID( ark.getIStartId() );
		TCity endcity = cityService.getCityByID( ark.getIEndId() );
		
		ark.setVcStartCity( stacity.getCityname() );
		ark.setVcEndCity( endcity.getCityname() );
		ark.setVcSubno( sub.getVcSubno() );
		
		System.out.println( "ark.getId >>" + ark.getId() );
		try
		{
			arkilometerService.save( ark );
			AjaxUtil.rendJson( resp , true , "保存成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( resp , false , "保存失败." + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 删除应收公里数
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/del" )
	@ApiIgnore
	public void del( HttpServletRequest request , HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getIArchiveType();
		// 该用户类型等于分供方
		if ( archid == SystemConstants.SYS_TARCHIVE_SUB )
		{
			int kilomerID = Integer.parseInt( request.getParameter( "arkID" ) );
			TArkilometer kil = arkilometerService.get( kilomerID );
			
			kil.setNEnable( SystemConstants.SYS_DISABLE );
			kil.setDtUpdate( new Date() );
			kil.setIUpdateUser( user.getId() );
			
			try
			{
				arkilometerService.update( kil );
				AjaxUtil.rendJson( resp , true , "删除成功." );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				AjaxUtil.rendJson( resp , false , "删除失败." + e.getMessage() );
			}
			
		}
		
	}
	
	/**
	 * @Description: 获取应收公里数对象
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/getArkilometerByID" )
	@ApiIgnore
	public String getArkilometerByID( HttpServletRequest request )
	{
		
		int kilomerID = Integer.parseInt( request.getParameter( "kilomerID" ) );
		TArkilometer kil = arkilometerService.get( kilomerID );
		
		request.setAttribute( "Arkilometer" , kil );
		
		return "index";
		
	}
	
	/**
	 * @Description: 结算应收 维护公里数 页面显示前加载
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-14 下午5:03:12
	 */
	@RequestMapping( "/ArordersaveBefore" )
	@ApiIgnore
	public String ArordersaveBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		TSubsuppliers sub = subService.get( user.getiArchive() );
		
		int stacityID = Integer.parseInt( request.getParameter( "stacityID" ) );
		int endcityID = Integer.parseInt( request.getParameter( "endcityID" ) );
		
		// 起运城市
		TCity stacity = cityService.getCityByID( stacityID );
		TProvince stapro = cityService
		        .getProvinceByID( stacity.getProvinceid() );
		
		// 目的城市
		TCity endcity = cityService.getCityByID( endcityID );
		TProvince endpro = cityService
		        .getProvinceByID( endcity.getProvinceid() );
		
		request.setAttribute( "stapro" , stapro );
		request.setAttribute( "stacity" , stacity );
		
		request.setAttribute( "endpro" , endpro );
		request.setAttribute( "endcity" , endcity );
		
		return "sub/subBasicData/arbysaveArk";
		
	}
	
	/**
	 * @Description: 结算应收 跳转到 维护公里数 页面
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-14 下午4:56:35
	 */
	public String intoArkiomerByArorder( HttpServletRequest request )
	{
		int stacityID = Integer.parseInt( request.getParameter( "stacityID" ) );
		int endcityID = Integer.parseInt( request.getParameter( "endcityID" ) );
		
		return "";
	}
	
	/**
	 * @Description: 验证该起始地 目的地 的公里数是否有效
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-14 下午4:56:35
	 */
	@RequestMapping( "/checkArkiomerByCity" )
	@ApiIgnore
	public void checkArkiomerByCity( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		TSubsuppliers sub = subService.get( user.getiArchive() );
		
		int stacityID = Integer.parseInt( request.getParameter( "stacityID" ) );
		int endcityID = Integer.parseInt( request.getParameter( "endcityID" ) );
		TCity stacity = cityService.getCityByID( stacityID );
		TCity endcity = cityService.getCityByID( endcityID );
		
		String sql = " select getArkilomer('" + sub.getVcSubno() + "','"
		        + stacity.getCityname() + "','" + endcity.getCityname() + "','"
		        + DateUtil.getDate( "yyyy/MM/dd" ) + "') nar from dual ";
		
		List datelist = arkilometerService.getDateBySQL( sql , null );
		
		if ( datelist.get( 0 ) != null )
		{
			AjaxUtil.rendJson( resp , false , stacity.getCityname() + " 到 "
			        + endcity.getCityname() + " 已维护,公里数:" + datelist.get( 0 ) );
		}
		else
		{
			AjaxUtil.rendJson( resp , true , "ok" );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(审核通过)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-21 下午2:24:51
	 */
	@RequestMapping( value = "checkyes" )
	public void checkyes( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		String[] ids = request.getParameterValues( "array[]" );
		List< TArkilometer > tArkilometers = new ArrayList< TArkilometer >();
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int id = Integer.parseInt( ids[i] );
			TArkilometer tArkilometer = arkilometerService.get( id );
			tArkilometer.setnCheck( 1 );
			tArkilometer.setDtUpdate( new Date() );
			tArkilometer.setVcUpdateUser( user.getVcAccount() );
			tArkilometers.add( tArkilometer );
		}
		try
		{
			arkilometerService.saveOrUpdateList( tArkilometers );
			AjaxUtil.rendJson( response , true , "审核通过" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "审核失败!" );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(审核不通过)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-21 下午2:32:43
	 */
	@RequestMapping( value = "checkno" )
	public void checkno( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		List< TArkilometer > tArkilometers = new ArrayList< TArkilometer >();
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int id = Integer.parseInt( ids[i] );
			TArkilometer tArkilometer = arkilometerService.get( id );
			tArkilometer.setnCheck( 0 );
			tArkilometer.setDtUpdate( new Date() );
			tArkilometer.setVcUpdateUser( user.getVcAccount() );
			tArkilometers.add( tArkilometer );
		}
		try
		{
			arkilometerService.saveOrUpdateList( tArkilometers );
			AjaxUtil.rendJson( response , true , "审核通过" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "审核失败!" );
		}
	}
}
