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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TCity;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TProvince;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-16 上午11:23:16
 * @version V1.0
 */
@Controller
@RequestMapping( "/customerAction" )
@Api( value = "customerAction-api" , description = "获取当前分供方的客户列表api" , position = 5 )
public class CustomerAction
{
	
	@Autowired
	ISubsuppliersService subService;
	
	@Autowired
	ICustomerSerivce customerService;
	
	@Autowired
	ICityService cityService;
	
	/**
	 * 
	 * @Description: TODO(获取该分供方所有的客户信息)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-16 下午2:36:01
	 */
	@RequestMapping( value = "/getAllCustomerByNo " , method = RequestMethod.POST )
	@ApiOperation( value = "获取当前分供方的客户列表" , notes = "获取当前分供方的客户列表" )
	@ResponseBody
	public Map< String , Object > getAllCustomerByNo( HttpServletRequest request )
	{
		
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		Map< String , Object > resuMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
			Page page = ServiceUtil.getcurrPage( request );
			HqlHelper hql = new HqlHelper( TCustomer.class );
			hql.setQueryPage( page );
			hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
			hql.addEqual( "vcSubno" , subbo );
			String shortName = request.getParameter( "shortName" );
			
			if ( StringUtils.isNotBlank( shortName ) )
			{
				hql.addEqual( "vcShortName" , shortName );
			}
			resuMap = customerService.findAllByHqlHelp( hql );
			return AjaxUtil.getMapByResult( visit , resuMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(跳转页面 因JSP位于web-info 下面只能转发)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-16 下午2:36:08
	 */
	@RequestMapping( "/customerList " )
	@ApiIgnore
	public String getCustomerList( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "sub/customer/customerList";
		
	}
	
	/**
	 * @Description: 点击修改时跳转
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveBefore" )
	@ApiIgnore
	public String saveBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String cuID = request.getParameter( "cuID" );// 客户ID
		TCustomer customer = new TCustomer();
		/*String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
		customer.setVcSubno( subbo );
		customer.setIUser( user.getId() );*/
		if ( StringUtils.isNotBlank( cuID ) ) // 编辑
		{
			
			paramType = "update";
			
			customer = customerService.get( Integer.parseInt( cuID ) );
			int cityID = customer.getICity();
			int provinceID = customer.getIProvince();
			
			TCity city = cityService.getCityByID( cityID );
			TProvince province = cityService.getProvinceByID( provinceID );
			request.setAttribute( "city" , city );
			
			request.setAttribute( "province" , province );
			
		}// 保存
		else
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
			String vcCustomerNo = customerService.getVcCustomerNo( subbo );
			customer.setVcCustomerNo( vcCustomerNo );
		}
		
		List< TProvince > prolist = cityService.getAllProvince();
		request.setAttribute( "customer" , customer );
		request.setAttribute( "prolist" , prolist );
		request.setAttribute( "paramType" , paramType );
		return "sub/customer/customerSave";
		
	}
	
	/**
	 * @Description: 根据省份id 获取对用的所有的城市 返回json
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/getAllCityByProID" )
	@ResponseBody
	@ApiIgnore
	public List< TCity > getAllCityByProID( HttpServletRequest request )
	{
		
		int proID = Integer.parseInt( request.getParameter( "proID" ) );
		System.out.println( "pro " + proID );
		if ( proID != 0 )
		{
			List< TCity > citylist = cityService.getCitysByProID( proID );
			return citylist;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description: TODO(保存操作)
	 * @param request
	 * @param response
	 * @param customer
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-17 上午11:49:12
	 */
	@RequestMapping( "/saveCustomer" )
	@ApiIgnore
	public void saveCustomer( HttpServletRequest request , HttpServletResponse response ,
	        TCustomer customer )
	{
		int provinceId = Integer.parseInt( request.getParameter( "provinceId" ) );
		int cityId = Integer.parseInt( request.getParameter( "cityId" ) );
		TProvince province = cityService.getProvinceByID( provinceId );
		TCity city = cityService.getCityByID( cityId );
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		customer.setIUser( user.getId() );// 维护人
		customer.setICity( cityId );
		customer.setIProvince( provinceId );
		customer.setVcProvince( province.getVcProvinceName() );
		customer.setVcCity( city.getCityname() );
		customer.setDtAdd( new Date() );
		if ( customer.getId() != null )
		{
			try
			{
				
				customerService.saveOrUpdate( customer );
				AjaxUtil.rendJson( response , true , "编辑保存成功." );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				AjaxUtil.rendJson( response , false , "编辑保存失败." + e.getMessage() );
			}
		}
		else
		{
			try
			{
				String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
				customer.setVcSubno( subbo );
				customerService.save( customer );
				AjaxUtil.rendJson( response , true , "新增保存成功." );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				AjaxUtil.rendJson( response , false , "新增保存失败." + e.getMessage() );
			}
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-17 下午3:54:48
	 */
	@RequestMapping( "/del" )
	@ApiIgnore
	public void deleteCustomer( HttpServletRequest request , HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		List< TCustomer > tCustomers = new ArrayList< TCustomer >();
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int id = Integer.parseInt( ids[i] );
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			int archid = user.getIArchiveType();
			// 该用户类型等于分供方
			if ( archid == SystemConstants.SYS_TARCHIVE_SUB )
			{
				int customerID = Integer.parseInt( ids[i] );
				TCustomer customer = customerService.get( customerID );
				
				customer.setNEnable( SystemConstants.SYS_DISABLE );
				customer.setDtAdd( new Date() );
				customer.setIUser( user.getId() );
				tCustomers.add( customer );
			}
			
		}
		
		try
		{
			customerService.updateList( tCustomers );
			AjaxUtil.rendJson( response , true , "删除成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "删除失败." + e.getMessage() );
		}
		
	}
	
}
