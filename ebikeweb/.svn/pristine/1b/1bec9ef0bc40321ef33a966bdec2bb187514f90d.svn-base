package com.clt.systemmanger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.service.IAssessWeightService;
import com.clt.systemmanger.model.TAssessWeight;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.SystemConstants;

@Controller
@RequestMapping( "/assessWeightAction" )
public class AssessWeightAction
{
	@Autowired
	private IAssessWeightService weightService;
	
	@RequestMapping( "/intoAssessWeight" )
	public String intoAssessWeight()
	{
		return "back/system/assessWeightList";
	}
	
	/**
	 * @Description 查询所有的评价权重
	 * @return
	 * @author chengwzh
	 * @Date 2015/7/21 10:34:00
	 */
	@RequestMapping( "/findAll" )
	@ResponseBody
	public Map< String , Object > findAll()
	{
		Map< String , Object > result = weightService.findAll();
		JSONArray arr = JSONArray.fromObject( result );
		System.out.println( "jsonArr:" + arr );
		return result;
	}
	
	@RequestMapping( "/intoSave" )
	public String intoSave( HttpServletRequest request )
	{
		String idstr = request.getParameter( "id" );
		if ( StringUtils.isNotBlank( idstr ) )
		{
			int id = Integer.parseInt( idstr );
			TAssessWeight entity = weightService.get( id );
			request.setAttribute( "weight" , entity );
			// 获取当前表的所有字段
			String vcTableName = entity.getVcTableName();
			List< Map< String , Object >> fields = weightService
			        .getAllFields( vcTableName );
			request.setAttribute( "fields" , fields );
			// 获取当前表的注释
			Map< String , Object > comment = weightService.getCommentByName( vcTableName );
			request.setAttribute( "comment" , comment );
		}
		List< Map< String , Object >> tables = weightService.getAlltables();
		request.setAttribute( "tables" , tables );
		return "back/system/assessWeightSave";
	}
	
	/**
	 * @Description 增加或修改
	 * @param entity
	 * @return
	 * @author chengwzh
	 * @date 2015/7/21 13:40:00
	 */
	@ResponseBody
	@RequestMapping( "/save" )
	public void add( TAssessWeight entity , HttpSession session ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		Map< String , Object > result = new HashMap< String , Object >();
		try
		{
			if ( entity.getId() == null )
			{
				int userId = user.getId();
				entity.setiUser( userId );
				entity.setVcUsername( user.getVcUsername() );
				weightService.save( entity );
			}
			else
			{
				weightService.update( entity );
			}
			weightService.save( entity );
			AjaxUtil.rendJson( response , true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 根据id删除
	 * @param id
	 * @param response
	 * @author chengwh
	 * @date 2015/7/21 16:10:00
	 */
	@RequestMapping( "/del" )
	public void del( @RequestParam( value = "id" ) int id , HttpServletResponse response )
	{
		try
		{
			TAssessWeight entity = weightService.get( id );
			entity.setNEnable( SystemConstants.SYS_DISABLE );
			weightService.update( entity );
			AjaxUtil.rendJson( response , true , "删除成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "删除失败：" + e.getMessage() );
		}
	}
	
	@ResponseBody
	@RequestMapping( "/getAllFields" )
	public List< Map< String , Object >> getAllFields(
	        @RequestParam( value = "tableName" ) String tableName )
	{
		List< Map< String , Object >> result = weightService.getAllFields( tableName );
		return result;
	}
}
