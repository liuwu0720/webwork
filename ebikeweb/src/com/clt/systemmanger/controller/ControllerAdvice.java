package com.clt.systemmanger.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

import com.clt.common.UserSession;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.SystemConstants;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice
{
	protected static Logger logger = Logger.getLogger( ControllerAdvice.class );
	
	@ExceptionHandler( Exception.class )
	@ResponseStatus( HttpStatus.BAD_REQUEST )
	@ResponseBody
	public Map< String , Object > processUnauthenticatedException(
	        NativeWebRequest request , Exception e )
	{
		e.printStackTrace();
		logger.error( e.getMessage() );
		HttpServletRequest req = request.getNativeRequest( HttpServletRequest.class );
		
		String urlPath = req.getScheme() + "://" + req.getServerName() + ":"
		        + req.getServerPort() + req.getRequestURI();
		String logFileName = new SimpleDateFormat( "yyyy-MM-dd" ).format( new Date() )
		        + ".log";
		try
		{
			File file = new File( SystemConstants.SYS_ERROR_LOG_DEF );
			if ( ! file.exists() )
			{
				file.mkdirs();
			}
			File f = new File( file , logFileName );
			if ( f.createNewFile() )
			{
			}
			PrintWriter writer = null;
			FileWriter fileWrite = new FileWriter( f , true );
			writer = new PrintWriter( fileWrite );
			
			TUser user = ( TUser ) UserSession.get( "user" );
			writer.append( ">>>>错误 <<<<" );
			writer.append( "\r\n" );
			writer.append( "时间："
			        + new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( new Date() )
			        + "__用户名：" + user.getVcAccount() );
			writer.append( "\r\n" );
			writer.append( "访问路径： " + urlPath );
			writer.append( "\r\n" );
			e.printStackTrace( writer );
			writer.append( "\r\n" );
			writer.flush();
			writer.close();
		}
		catch ( Exception e1 )
		{
			e1.printStackTrace();
		}
		return AjaxUtil.getMapByException( e );
	}
}
