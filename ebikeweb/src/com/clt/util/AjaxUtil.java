package com.clt.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.clt.common.UserSession;
import com.clt.systemmanger.model.TUser;

/**
 * @Package com.clt.util
 * @Description: 后台返回json
 * @author hjx
 * @date 2014年7月18日 上午11:33:58
 * @version V1.0
 */
public class AjaxUtil
{
	private static void rendText( HttpServletResponse response , String content )
	        throws IOException
	
	{
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter().write( content );
	}
	
	/**
	 * @Description: 封装成json,不建议使用，该方法替换为 getMap( boolean success , String
	 *               message ); 请注意： getMap这个方法会返回一个map对象。
	 * @param response
	 * @param success
	 *            是否成功
	 * @param message
	 *            返回的消息
	 * @throws IOException
	 *             void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月18日 上午11:45:48
	 * 
	 */
	@Deprecated
	public static void rendJson( HttpServletResponse response , boolean success ,
	        String message )
	{
		JSONObject json = new JSONObject();
		json.put( "isSuccess" , success );
		json.put( "message" , message );
		
		try
		{
			rendText( response , json.toString() );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回一个是否成功，并带提示消息的map
	 * 
	 * @param success
	 * @param message
	 * @return
	 */
	public static Map< String , Object > getMap( boolean success , String message )
	{
		Map< String , Object > map = new HashMap< String , Object >();
		map.put( "isSuccess" , success );
		map.put( "message" , message );
		return map;
	}
	
	/**
	 * @Description: 分装为map 方便返回数据给app，这是没异常的分装（分成功和未成功）
	 * @param isSuccess
	 *            是否获得数据，默认提示消息 成功！ 和 没有获取到相关数据！
	 * @param obj
	 *            这是对应数据
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月15日 下午2:17:23
	 */
	public static Map< String , Object > getMapByNotException( boolean isSuccess ,
	        Object obj )
	{
		Map< String , Object > appMap = new HashMap< String , Object >();
		appMap.put( "isSuccess" , isSuccess );
		if ( ! isSuccess )
		{
			appMap.put( "message" , "没有获取到相关数据！" );
		}
		else
		{
			appMap.put( "message" , "获取成功！" );
			appMap.put( "data" , obj );
		}
		return appMap;
	}
	
	/**
	 * @Description: 当程序异常，分装map 给手机端
	 * @param e
	 *            异常信息对象
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月15日 下午2:20:41
	 */
	public static Map< String , Object > getMapByException( Exception e )
	{
		Map< String , Object > appMap = new HashMap< String , Object >();
		appMap.put( "isSuccess" , false );
		// appMap.put( "message" , "获取数据失败，失败原因：" + e.getMessage() );
		int i = ( int ) ( ( Math.random() * 9 + 1 ) * 1000 );
		String messageCode = String.valueOf( i );
		appMap.put( "message" , "获取数据失败，请联系管理员!错误码：" + messageCode );
		
		String logFileName = new SimpleDateFormat( "yyyy-MM-dd" ).format( new Date() )
		        + ".log";
		try
		{
			File file = new File( SystemConstants.SYS_ERROR_LOG );
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
			writer.append( ">>>>错误码：" + messageCode + "<<<<" );
			writer.append( "\r\n" );
			writer.append( "时间："
			        + new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ).format( new Date() )
			        + "__用户名：" + user.getVcAccount() );
			
			writer.append( "      *************************" + e.toString()
			        + "*************************" );
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
		
		return appMap;
	}
	
	/**
	 * @Description: 封装返回数据格式，判断请求时app发送，如果是按app的格式给予，如果不是返回原来的格式给pc端
	 * @param visit
	 *            请求格式，如果是手机端请求，其url 会有 一个参数 visit=phone
	 * @param result
	 *            列表数据Map类型
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月15日 下午6:01:39
	 */
	public static Map< String , Object > getMapByResult( String visit ,
	        Map< String , Object > result )
	{
		if ( StringUtils.isNotBlank( visit ) && SystemConstants.APP_VISIT.equals( visit ) )
		{
			if ( CollectionUtils.isNotEmpty( ( List ) result.get( "rows" ) ) )
			{
				return AjaxUtil.getMapByNotException( true , result );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , null );
			}
		}
		else
		{
			return result;
		}
	}
	
}
