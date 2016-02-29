package com.clt.systemmanger.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.systemmanger.model.TIntegalCut;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IIntegalCutService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-10-9 下午3:21:36
 * @version V1.0
 */
@Controller
@RequestMapping( "/integalCutAction" )
@ApiIgnore
public class IntegalCutAction
{
	
	@Autowired
	private IIntegalCutService integalCutService;
	
	/**
	 * 
	 * @Description: 跳转页面 因JSP位于web-info 下面只能转发
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-11 下午1:20:51
	 */
	@RequestMapping( "/intogetAllIntegalCuts" )
	public String intogetAllIntegalCuts( HttpServletRequest request )
	{
		return "integal/integalCutinfoList";
		
	}
	
	/**
	 * 
	 * @Description: 跳转到保存前的处理
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-11 下午1:20:51
	 */
	@RequestMapping( "/saveBefore" )
	public String saveBefore( HttpServletRequest request )
	{
		String inteCutID = request.getParameter( "inteCutID" );
		if ( StringUtils.isNotBlank( inteCutID ) )
		{
			TIntegalCut integalCut = integalCutService
			        .get( Integer.parseInt( inteCutID ) );
			request.setAttribute( "integalCut" , integalCut );
			
		}
		return "integal/saveIntegalCut";
		
	}
	
	/**
	 * 
	 * @Description: 删除扣分类型 逻辑删除 不可见
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-11 下午1:20:51
	 */
	@RequestMapping( "/delIntegalCuts" )
	public void delIntegalCuts( HttpServletRequest request , HttpServletResponse resp )
	{
		int intgegalCutID = Integer.parseInt( request.getParameter( "inteCutID" ) );
		TIntegalCut integalCut = integalCutService.get( intgegalCutID );
		integalCut.setNEnable( SystemConstants.SYS_DISABLE );
		try
		{
			integalCutService.update( integalCut );
			AjaxUtil.rendJson( resp , true , "保存成功!" );
		}
		catch ( Exception e )
		{
			// TODO: handle exception
			AjaxUtil.rendJson( resp , false , "保存失败,原因 :" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: 新增 / 修改扣分类型
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-11 下午1:20:51
	 */
	@RequestMapping( "/save" )
	public void save( HttpServletRequest request , HttpServletResponse resp ,
	        TIntegalCut integalCut )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		integalCut.setNEnable( SystemConstants.SYS_ENABLE );
		integalCut.setDtOpUser( new Date() );
		integalCut.setIOpUserId( user.getId() );
		
		try
		{
			integalCutService.saveOrUpdate( integalCut );
			AjaxUtil.rendJson( resp , true , "保存成功!" );
		}
		catch ( Exception e )
		{
			// TODO: handle exception
			AjaxUtil.rendJson( resp , false , "保存失败,原因 :" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: 获取所有的可用的 积分扣除类型
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-11 下午1:20:51
	 */
	@RequestMapping( "/getAllIntegalCuts" )
	@ResponseBody
	public Map< String , Object > getAllIntegalCuts( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		
		HqlHelper hql = new HqlHelper( TIntegalCut.class );
		hql.addOrderBy( "id" , "desc" );
		hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		hql.setQueryPage( page );
		
		String vcCode = request.getParameter( "vcCode" );
		if ( StringUtils.isNotBlank( vcCode ) )
		{
			System.out.println( "vcCode=" + vcCode );
			hql.addLike( "vcCode" , vcCode );
			
		}
		
		String vcCause = request.getParameter( "vcCause" );
		if ( StringUtils.isNotBlank( vcCause ) )
		{
			System.out.println( "vcCause=" + vcCause );
			hql.addLike( "vcCause" , vcCause );
			
		}
		
		Map< String , Object > resuMap = integalCutService.findAllByHqlHelp( hql );
		return resuMap;
	}
}
