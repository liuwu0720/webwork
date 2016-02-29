package com.clt.systemmanger.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IMsgRecordService;
import com.clt.util.AjaxUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping( "/msgRecordAction" )
@Controller
@Api( value = "MsgRecordAction-api" , description = "消息推送记录表相关操作" , position = 5 )
public class MsgRecordAction
{
	@Autowired
	private IMsgRecordService msgService;
	
	/**
	 * @Decription 查询所有消息记录
	 * @param vcTitle
	 * @param request
	 * @return
	 * @author chengwzh
	 * @date 2015/6/17 10:00
	 */
	@RequestMapping( value = "/getAllMsgs" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "查询所有消息记录" , notes = "String vcTitle：消息标题" , position = 5 )
	public Map< String , Object > getAllMsgs(
	        @ApiParam( value = "消息标题" ) @RequestParam( value = "vcTitle" , required = false ) String vcTitle ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = msgService.findAllMsgs( page , vcTitle ,
			        userId );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 增加/修改消息记录
	 * @param entity
	 * @param session
	 * @param response
	 * @author chengwzh
	 * @date 2015/6/17 15:05
	 */
	@RequestMapping( value = "/save" , method = RequestMethod.POST )
	@ApiOperation( value = "增加/修改消息记录" , notes = "增加/修改消息记录" , position = 5 , response = TMsgRecord.class )
	public void save( @ModelAttribute TMsgRecord entity , HttpSession session ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		String username = user.getVcUsername();
		try
		{
			if ( entity.getId() == null )
			{
				entity.setIUser( userId );
				entity.setVcAdduser( username );
				msgService.save( entity );
			}
			else
			{
				msgService.update( entity );
			}
			AjaxUtil.rendJson( response , true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败，原因：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 删除消息记录
	 * @param id
	 * @param response
	 * @author chengwzh
	 * @date 2015/6/17 15:24
	 */
	@RequestMapping( value = "/del" , method = RequestMethod.POST )
	@ApiOperation( value = "删除消息记录" , notes = "删除消息记录" , position = 5 , response = TMsgRecord.class )
	public void del( @ApiParam( value = "消息id" ) @RequestParam( value = "id" ) int id ,
	        HttpServletResponse response )
	{
		try
		{
			TMsgRecord entity = msgService.get( id );
			entity.setNEnable( SystemConstants.SYS_DISABLE );
			msgService.update( entity );
			AjaxUtil.rendJson( response , true , "删除成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "删除失败，原因：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 根据id获取消息记录
	 * @param id
	 * @return
	 * @author chengwzh
	 * @date 2015/6/17 15:32
	 */
	@RequestMapping( value = "/getById" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "根据id获取消息记录" , notes = "根据id获取消息记录" , position = 5 , response = TMsgRecord.class )
	public Map< String , Object > getById(
	        @ApiParam( value = "消息id" ) @RequestParam( value = "id" ) int id )
	{
		try
		{
			TMsgRecord entity = msgService.get( id );
			return AjaxUtil.getMapByNotException( true , entity );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
}
