package com.clt.systemmanger.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.systemmanger.model.TUserLog;
import com.clt.systemmanger.service.IUserLogService;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: 用户日志控制
 * @author hjx
 * @date 2014年12月16日 下午1:50:40
 * @version V1.0
 */
@Controller
@RequestMapping( "/userlogAction" )
@ApiIgnore
public class UserLogAction
{
	@Autowired
	private IUserLogService userLogService;
	
	/**
	 * @Description: 打开用户日志页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年12月16日 下午1:57:19
	 */
	@RequestMapping( "/openLogPage" )
	public String openLogPage()
	{
		
		return "back/system/userLogList";
	}
	
	/**
	 * @Description: 获取用户日志数据
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年12月16日 下午1:57:33
	 */
	@RequestMapping( "/getUserLog" )
	@ResponseBody
	public Map< String , Object > getUserLog( HttpServletRequest request )
	{
		Page p = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TUserLog.class );
		helper.setQueryPage( p );
		// 查询条件
		String userName = request.getParameter( "userName" );
		if ( StringUtils.isNotBlank( userName ) )
		{
			helper.addLike( "vcUserName" , userName );
		}
		
		String startDate = request.getParameter( "startDate" );
		String endDate = request.getParameter( "endDate" );
		if ( StringUtils.isNotBlank( startDate ) )
		{
			try
			{
				helper.addGreatEqualThan( "dtAdd" , DateUtil.parseTime( startDate ) );
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}
		}
		if ( StringUtils.isNotBlank( endDate ) )
		{
			try
			{
				helper.addLessEqualThan( "dtAdd" , DateUtil.parseTime( endDate ) );
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}
		}
		
		return userLogService.findByHelper( helper );
	}
}
