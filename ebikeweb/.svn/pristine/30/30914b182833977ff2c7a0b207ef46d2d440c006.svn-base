package com.clt.sub.controller;

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

import com.clt.sub.model.TSubCarStyle;
import com.clt.sub.service.ISubCarStyleService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

@Controller
@RequestMapping( "/subCarStyleAction" )
@ApiIgnore
public class SubCarStyleAction
{
	
	@Autowired
	private ISubCarStyleService subCarStyleService;
	@Autowired
	private ISubsuppliersService subService;
	
	@RequestMapping( "/intoAllSubCarStyle" )
	@ApiIgnore
	public String intoShipHeadInfoList( HttpServletRequest request )
	{
		
		return "sub/subBasicData/subCarStyleList";
		
	}
	
	/**
	 * @Description: 获取该分供方所有的车型信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( value = "/getAllSubCarStyle" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllSubCarStyle( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getIArchiveType();
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TSubCarStyle.class );
		hql.addEqual( "vcSubno" , subno );
		// hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		hql.setQueryPage( page );
		Map< String , Object > resultMap = null;
		// 该用户类型等于分供方
		if ( archid == SystemConstants.SYS_TARCHIVE_SUB )
		{
			try
			{
				resultMap = subCarStyleService.findAllByHql( hql );
				return resultMap;
			}
			catch ( Exception e )
			{
				return AjaxUtil.getMapByException( e );
			}
		}
		return resultMap;
	}
	
	@RequestMapping( "/findAllBySubno" )
	@ResponseBody
	@ApiIgnore
	public List< TSubCarStyle > findAllBySubno(
	        @RequestParam( value = "vcSubno" ) String vcSubno )
	{
		try
		{
			String[] properties = { "NEnable" , "vcSubno" };
			Object[] values = { SystemConstants.SYS_ENABLE , vcSubno };
			List< TSubCarStyle > styles = subCarStyleService.findByProperties(
			        properties , values );
			return styles;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param arkID
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-6 下午1:44:29
	 */
	@RequestMapping( "/saveBefore" )
	public String saveBefore( HttpServletRequest request , String arkID ,
	        HttpServletResponse response )
	{
		if ( StringUtils.isNotBlank( arkID ) )// 编辑
		{
			int id = Integer.parseInt( arkID );
			TSubCarStyle tSubCarStyle = subCarStyleService.get( id );
			request.setAttribute( "tSubCarStyle" , tSubCarStyle );
			
		}
		return "sub/subBasicData/subCarStyleSave";
	}
	
	/**
	 * @Description: 保存分供方车型信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( value = "/save" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > save( HttpServletRequest request ,
	        TSubCarStyle tSubCarStyle )
	{
		try
		{
			/**
			 * 验证重复性
			 */
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			String subno = subService.get( user.getiArchive() ).getVcSubno();
			String[] properties = { "vcCarStyle" , "NEnable" , "vcSubno" };
			Object[] values = { tSubCarStyle.getVcCarStyle() , 0 , subno };
			List< TSubCarStyle > tSubCarStyles = subCarStyleService.findByProperties(
			        properties , values );
			if ( tSubCarStyles != null && tSubCarStyles.size() > 0 )
			{
				return AjaxUtil.getMap( false , "商品车【" + tSubCarStyle.getVcCarStyle()
				        + "】重复！" );
			}
			
			if ( tSubCarStyle.getId() == null )
			{
				
				tSubCarStyle.setVcSubno( subno );
				subCarStyleService.save( tSubCarStyle );
				return AjaxUtil.getMap( true , "成功!" );
			}
			else
			{
				subCarStyleService.update( tSubCarStyle );
				return AjaxUtil.getMap( true , "成功!" );
			}
			
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 删除分供方车型信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/del" )
	@ResponseBody
	public Map< String , Object > del( HttpServletRequest request ,
	        HttpServletResponse response , String arkID )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getIArchiveType();
		// 该用户类型等于分供方
		if ( archid == SystemConstants.SYS_TARCHIVE_SUB )
		{
			try
			{
				int styID = Integer.parseInt( arkID );
				TSubCarStyle carsty = subCarStyleService.get( styID );
				carsty.setNEnable( 1 );
				subCarStyleService.update( carsty );
				return AjaxUtil.getMap( true , "成功！" );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				return AjaxUtil.getMapByException( e );
			}
		}
		return AjaxUtil.getMap( false , "当前用户不是承运方！" );
	}
}
