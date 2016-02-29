package com.clt.systemmanger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clt.util.push.PushMessageUtil;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-3-12 下午3:26:22
 * @version V1.0
 */
@Controller
@RequestMapping( "/phone" )
@ApiIgnore
public class ShareCircleAction
{
	@RequestMapping( value = "/push" , method = RequestMethod.POST )
	public void pushMessage( HttpServletRequest request , HttpServletResponse response )
	{
		PushMessageUtil demo = new PushMessageUtil();
		try
		{
			// demo.sendAndroidUnicast();//单独
			demo.sendAndroidListcast();// 分组
			// demo.sendAndroidBroadcast();//所有人
			// demo.sendAndroidGroupcast();
			// demo.sendAndroidCustomizedcast();
			// demo.sendAndroidFilecast();
			/* TODO these methods are all available, just fill in some fields and do the test
			 * demo.sendAndroidBroadcast();
			 * demo.sendAndroidGroupcast();
			 * demo.sendAndroidCustomizedcast();
			 * demo.sendAndroidFilecast();
			 * 
			 * demo.sendIOSBroadcast();
			 * demo.sendIOSUnicast();
			 * demo.sendIOSGroupcast();
			 * demo.sendIOSCustomizedcast();
			 * demo.sendIOSFilecast();
			 */
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}
	}
}
