/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-3-11 下午3:10:01
 * @version V1.0
 */
package com.clt.util.push;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package push
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-3-11 下午3:10:01
 * @version V1.0
 */
@Controller
@RequestMapping( "/phone" )
@ApiIgnore
public class PushMessage
{
	@RequestMapping( value = "/push1" , method = RequestMethod.POST )
	public void pushMessage( HttpServletRequest request , HttpServletResponse response )
	        throws UnsupportedEncodingException
	{
		System.out.println( "url= " + request.getRequestURL() );
		System.out.println( "request.getHeader=" + request.getHeader( "user-agent" ) );
		String name = request.getParameter( "name" );
		System.out.println( "name= " + name );
		name = new String( name.getBytes( "iso8859-1" ) , "UTF-8" );
		System.out.println( "name22= " + name );
	}
}
