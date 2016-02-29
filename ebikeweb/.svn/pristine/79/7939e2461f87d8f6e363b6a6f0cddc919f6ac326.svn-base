package com.clt.systemmanger.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.systemmanger.model.TMessage;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IMessageService;
import com.clt.util.AjaxUtil;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: 消息处理控制器
 * @author hjx
 * @date 2014年9月15日 下午2:32:22
 * @version V1.0
 */
@Controller
@RequestMapping( "/messageAction" )
@ApiIgnore
public class MessageAction
{
	@Autowired
	private IMessageService msgService;
	
	/**
	 * @Description: 打开个人的消息页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年9月15日 下午2:33:37
	 */
	@RequestMapping( "/openMsgList" )
	public String openMsgList()
	{
		return "back/msg/myMsgList";
	}
	
	/**
	 * @Description: 获得当前用户最新的消息列表数据
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年9月15日 下午2:36:04
	 */
	@RequestMapping( "/getNewMsg" )
	@ResponseBody
	public Map< String , Object > getNewMsg( HttpServletRequest request )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TMessage.class );
		helper.setQueryPage( page );
		helper.addEqual( "IAcceptUser" , user.getId() );
		// 查询参数
		String vcTitle = request.getParameter( "vcTitle" );
		
		if ( StringUtils.isNotBlank( vcTitle ) )
		{
			helper.addLike( "vcTitle" , vcTitle );
		}
		
		String vcSendUserName = request.getParameter( "vcSendUserName" );
		if ( StringUtils.isNotBlank( vcSendUserName ) )
		{
			helper.addLike( "vcSendUserName" , vcSendUserName );
		}
		
		String dtSend1 = request.getParameter( "dtSend1" );
		if ( StringUtils.isNotBlank( dtSend1 ) )
		{
			try
			{
				Date begin = DateUtil.parseDate( dtSend1 );
				helper.addGreatEqualThan( "dtSend" , begin );
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}
		}
		
		String dtSend2 = request.getParameter( "dtSend2" );
		if ( StringUtils.isNotBlank( dtSend2 ) )
		{
			try
			{
				Date dt2 = DateUtil.parseDate( dtSend2 );
				helper.addLessEqualThan( "dtSend" , dt2 );
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}
		}
		
		String typeStr = request.getParameter( "type" );
		if ( StringUtils.isNotBlank( typeStr ) && "ignore".equals( typeStr ) )
		{
			helper.addEqual( "NIgnore" , SystemConstants.SYS_ENABLE ); // 忽略
		}
		else
		{
			helper.addEqual( "NIgnore" , SystemConstants.SYS_DISABLE );// 没忽略类型的消息
		}
		helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		
		result = msgService.findByHelper( helper );
		return result;
	}
	
	/**
	 * @Description: 忽略消息
	 * @param msgId
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年9月15日 下午4:39:24
	 */
	@RequestMapping( "/ignoreMsg" )
	public void ignoreMsg( @RequestParam( "id" ) Integer msgId ,
	        @RequestParam( "ig" ) String ig , HttpServletResponse response )
	{
		TMessage msg = msgService.get( msgId );
		String message = "";
		if ( "y".equals( ig ) )
		{
			msg.setNIgnore( SystemConstants.SYS_ENABLE );
			message = "忽略消息成功！";
		}
		else
		{
			msg.setNIgnore( SystemConstants.SYS_DISABLE );
			message = "恢复未忽略消息成功！";
		}
		try
		{
			msgService.update( msg );
			AjaxUtil.rendJson( response , true , message );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , true , "操作失败，失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description: 删除消息
	 * @param msgId
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年9月15日 下午4:41:14
	 */
	@RequestMapping( "/delMsg" )
	public void delMsg( @RequestParam( "id" ) Integer msgId , HttpServletResponse response )
	{
		try
		{
			msgService.deleteByKey( msgId );
			AjaxUtil.rendJson( response , true , "删除成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , true , "删除失败，失败原因：" + e.getMessage() );
		}
	}
}
