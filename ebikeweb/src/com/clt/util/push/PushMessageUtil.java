package com.clt.util.push;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.clt.util.push.android.AndroidBroadcast;
import com.clt.util.push.android.AndroidCustomizedcast;
import com.clt.util.push.android.AndroidFilecast;
import com.clt.util.push.android.AndroidGroupcast;
import com.clt.util.push.android.AndroidListcast;
import com.clt.util.push.android.AndroidUnicast;
import com.clt.util.push.ios.IOSBroadcast;
import com.clt.util.push.ios.IOSCustomizedcast;
import com.clt.util.push.ios.IOSFilecast;
import com.clt.util.push.ios.IOSGroupcast;
import com.clt.util.push.ios.IOSUnicast;

public class PushMessageUtil
{
	private String appkey = null;
	private String appMasterSecret = null;
	private String timestamp = null;
	
	public PushMessageUtil()
	{
		String requrl = getUrl();
		File file = new File( requrl + "/appconfig/app.properties" );
		
		Properties pro = new Properties();
		try
		{
			FileInputStream inStr = new FileInputStream( file );
			pro.load( inStr );
			appkey = pro.getProperty( "appkey" );
			appMasterSecret = pro.getProperty( "appMasterSecret" );
			timestamp = Integer
			        .toString( ( int ) ( System.currentTimeMillis() / 1000 ) );
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public void sendAndroidBroadcast() throws Exception
	{
		AndroidBroadcast broadcast = new AndroidBroadcast();
		broadcast.setAppMasterSecret( appMasterSecret );
		broadcast.setPredefinedKeyValue( "appkey" , this.appkey );
		broadcast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		broadcast.setPredefinedKeyValue( "ticker" , "Android broadcast ticker" );
		broadcast.setPredefinedKeyValue( "title" , "中文的title" );
		broadcast.setPredefinedKeyValue( "text" , "Android broadcast text" );
		broadcast.setPredefinedKeyValue( "after_open" , "go_custom" );
		broadcast.setPredefinedKeyValue( "display_type" , "notification" );
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		broadcast.setPredefinedKeyValue( "production_mode" , "true" );
		// Set customized fields
		broadcast.setExtraField( "test" , "helloworld" );
		broadcast.send();
	}
	
	public void sendAndroidUnicast() throws Exception
	{
		AndroidUnicast unicast = new AndroidUnicast();
		unicast.setAppMasterSecret( appMasterSecret );
		unicast.setPredefinedKeyValue( "appkey" , this.appkey );
		unicast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		// TODO Set your device token
		unicast.setPredefinedKeyValue(
		        "device_tokens" ,
		        "An4ngMp2HfGcOnfYIquy2iJB4MsRhMO02CU-Kfwfv2P6,AjV7Qs70Q7MsftekjQwgM7I-vuqjUJkGOmMjR65G1eHt" );
		unicast.setPredefinedKeyValue( "ticker" , "Android unicast ticker" );
		unicast.setPredefinedKeyValue( "title" , "中文的title" );
		unicast.setPredefinedKeyValue( "text" , "Android unicast text" );
		unicast.setPredefinedKeyValue( "after_open" , "go_app" );
		unicast.setPredefinedKeyValue( "display_type" , "notification" );
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		unicast.setPredefinedKeyValue( "production_mode" , "true" );
		// Set customized fields
		unicast.setExtraField( "test" , "helloworld" );
		unicast.send();
	}
	
	public void sendAndroidGroupcast() throws Exception
	{
		AndroidGroupcast groupcast = new AndroidGroupcast();
		groupcast.setAppMasterSecret( appMasterSecret );
		groupcast.setPredefinedKeyValue( "appkey" , this.appkey );
		groupcast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
		 *		"and": 
		 *		[
		 *			{"tag":"test"},
		 *			{"tag":"Test"}
		 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		JSONObject TestTag = new JSONObject();
		testTag.put( "tag" , "test" );
		TestTag.put( "tag" , "Test" );
		tagArray.put( testTag );
		tagArray.put( TestTag );
		whereJson.put( "and" , tagArray );
		filterJson.put( "where" , whereJson );
		System.out.println( filterJson.toString() );
		
		groupcast.setPredefinedKeyValue( "filter" , filterJson );
		groupcast.setPredefinedKeyValue( "ticker" , "Android groupcast ticker" );
		groupcast.setPredefinedKeyValue( "title" , "中文的title" );
		groupcast.setPredefinedKeyValue( "text" , "Android groupcast text" );
		groupcast.setPredefinedKeyValue( "after_open" , "go_app" );
		groupcast.setPredefinedKeyValue( "display_type" , "notification" );
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		groupcast.setPredefinedKeyValue( "production_mode" , "true" );
		groupcast.send();
	}
	
	public void sendAndroidCustomizedcast() throws Exception
	{
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast();
		customizedcast.setAppMasterSecret( appMasterSecret );
		customizedcast.setPredefinedKeyValue( "appkey" , this.appkey );
		customizedcast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		// TODO Set your alias here, and use comma to split them if there are
		// multiple alias.
		// And if you have many alias, you can also upload a file containing
		// these alias, then
		// use file_id to send customized notification.
		customizedcast.setPredefinedKeyValue( "alias" , "xx" );
		// TODO Set your alias_type here
		customizedcast.setPredefinedKeyValue( "alias_type" , "xx" );
		customizedcast.setPredefinedKeyValue( "ticker" ,
		        "Android customizedcast ticker" );
		customizedcast.setPredefinedKeyValue( "title" , "中文的title" );
		customizedcast.setPredefinedKeyValue( "text" ,
		        "Android customizedcast text" );
		customizedcast.setPredefinedKeyValue( "after_open" , "go_app" );
		customizedcast.setPredefinedKeyValue( "display_type" , "notification" );
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		customizedcast.setPredefinedKeyValue( "production_mode" , "true" );
		customizedcast.send();
	}
	
	public void sendAndroidFilecast() throws Exception
	{
		AndroidFilecast filecast = new AndroidFilecast();
		filecast.setAppMasterSecret( appMasterSecret );
		filecast.setPredefinedKeyValue( "appkey" , this.appkey );
		filecast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		// TODO upload your device tokens, and use '\n' to split them if there
		// are multiple tokens
		filecast.uploadContents( "aa" + "\n" + "bb" );
		filecast.setPredefinedKeyValue( "ticker" , "Android filecast ticker" );
		filecast.setPredefinedKeyValue( "title" , "中文的title" );
		filecast.setPredefinedKeyValue( "text" , "Android filecast text" );
		filecast.setPredefinedKeyValue( "after_open" , "go_app" );
		filecast.setPredefinedKeyValue( "display_type" , "notification" );
		filecast.send();
	}
	
	public void sendIOSBroadcast() throws Exception
	{
		IOSBroadcast broadcast = new IOSBroadcast();
		broadcast.setAppMasterSecret( appMasterSecret );
		broadcast.setPredefinedKeyValue( "appkey" , this.appkey );
		broadcast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		
		broadcast.setPredefinedKeyValue( "alert" , "IOS 广播测试" );
		broadcast.setPredefinedKeyValue( "badge" , 0 );
		broadcast.setPredefinedKeyValue( "sound" , "chime" );
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		broadcast.setPredefinedKeyValue( "production_mode" , "false" );
		// Set customized fields
		broadcast.setCustomizedField( "test" , "helloworld" );
		broadcast.send();
	}
	
	public void sendIOSUnicast() throws Exception
	{
		IOSUnicast unicast = new IOSUnicast();
		unicast.setAppMasterSecret( appMasterSecret );
		unicast.setPredefinedKeyValue( "appkey" , this.appkey );
		unicast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		// TODO Set your device token
		unicast.setPredefinedKeyValue( "device_tokens" , "xx" );
		unicast.setPredefinedKeyValue( "alert" , "IOS 单播测试" );
		unicast.setPredefinedKeyValue( "badge" , 0 );
		unicast.setPredefinedKeyValue( "sound" , "chime" );
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		unicast.setPredefinedKeyValue( "production_mode" , "false" );
		// Set customized fields
		unicast.setCustomizedField( "test" , "helloworld" );
		unicast.send();
	}
	
	public void sendIOSGroupcast() throws Exception
	{
		IOSGroupcast groupcast = new IOSGroupcast();
		groupcast.setAppMasterSecret( appMasterSecret );
		groupcast.setPredefinedKeyValue( "appkey" , this.appkey );
		groupcast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
		 *		"and": 
		 *		[
		 *			{"tag":"iostest"}
		 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		testTag.put( "tag" , "iostest" );
		tagArray.put( testTag );
		whereJson.put( "and" , tagArray );
		filterJson.put( "where" , whereJson );
		System.out.println( filterJson.toString() );
		
		// Set filter condition into rootJson
		groupcast.setPredefinedKeyValue( "filter" , filterJson );
		groupcast.setPredefinedKeyValue( "alert" , "IOS 组播测试" );
		groupcast.setPredefinedKeyValue( "badge" , 0 );
		groupcast.setPredefinedKeyValue( "sound" , "chime" );
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		groupcast.setPredefinedKeyValue( "production_mode" , "false" );
		groupcast.send();
	}
	
	public void sendIOSCustomizedcast() throws Exception
	{
		IOSCustomizedcast customizedcast = new IOSCustomizedcast();
		customizedcast.setAppMasterSecret( appMasterSecret );
		customizedcast.setPredefinedKeyValue( "appkey" , this.appkey );
		customizedcast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		// TODO Set your alias here, and use comma to split them if there are
		// multiple alias.
		// And if you have many alias, you can also upload a file containing
		// these alias, then
		// use file_id to send customized notification.
		customizedcast.setPredefinedKeyValue( "alias" , "xx" );
		// TODO Set your alias_type here
		customizedcast.setPredefinedKeyValue( "alias_type" , "xx" );
		customizedcast.setPredefinedKeyValue( "alert" , "IOS 个性化测试" );
		customizedcast.setPredefinedKeyValue( "badge" , 0 );
		customizedcast.setPredefinedKeyValue( "sound" , "chime" );
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		customizedcast.setPredefinedKeyValue( "production_mode" , "false" );
		customizedcast.send();
	}
	
	public void sendIOSFilecast() throws Exception
	{
		IOSFilecast filecast = new IOSFilecast();
		filecast.setAppMasterSecret( appMasterSecret );
		filecast.setPredefinedKeyValue( "appkey" , this.appkey );
		filecast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		// TODO upload your device tokens, and use '\n' to split them if there
		// are multiple tokens
		filecast.uploadContents( "aa" + "\n" + "bb" );
		filecast.setPredefinedKeyValue( "alert" , "IOS 文件播测试" );
		filecast.setPredefinedKeyValue( "badge" , 0 );
		filecast.setPredefinedKeyValue( "sound" , "chime" );
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		filecast.setPredefinedKeyValue( "production_mode" , "false" );
		filecast.send();
	}
	
	public static void main( String[] args )
	{
		// TODO set your appkey and master secret here
		PushMessageUtil demo = new PushMessageUtil();
		try
		{
			demo.sendAndroidBroadcast();
			// demo.sendAndroidUnicast();
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
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用) void 返回值描述
	 * @author liuwu
	 * @throws Exception
	 *             AszVixCbLM87UIcJQZWZtlD3DcYR1aC_tNetSrU4qbX6,
	 *             AhxNU7fEwklpPqrcmuhJxR_KtPmLUKDZxsUl8M-IJmn6,
	 * @create_date 2015-3-12 上午11:09:32
	 */
	public void sendAndroidListcast() throws Exception
	{
		AndroidListcast unicast = new AndroidListcast();
		unicast.setAppMasterSecret( appMasterSecret );
		unicast.setPredefinedKeyValue( "appkey" , this.appkey );
		unicast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		// TODO Set your device token
		unicast.setPredefinedKeyValue( "device_tokens" ,
		        "AszVixCbLM87UIcJQZWZtlD3DcYR1aC_tNetSrU4qbX6" );
		unicast.setPredefinedKeyValue( "ticker" , "ticker " );
		unicast.setPredefinedKeyValue( "title" , "来自刘武的路途分享" );
		unicast.setPredefinedKeyValue( "text" , "测试文字描述，测试文字描述，测试文字描述，测试文字描述" );
		unicast.setPredefinedKeyValue( "after_open" , "go_app" );
		unicast.setPredefinedKeyValue( "display_type" , "notification" );
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		unicast.setPredefinedKeyValue( "production_mode" , "true" );
		// Set customized fields
		unicast.setExtraField( "test" , "helloworld" );
		unicast.send();
		
	}
	
	public static String getUrl()
	{
		java.net.URL u = PushMessageUtil.class.getResource( "" );
		String str = u.toString();
		// 截去一些前面6个无用的字符
		str = str.substring( 6 , str.length() );
		// 将%20换成空格（如果文件夹的名称带有空格的话，会在取得的字符串上变成%20）
		
		str = str.replaceAll( "%20" , " " );
		// 查找“WEB-INF”在该字符串的位置
		
		int num = str.indexOf( "WEB-INF" );
		// 截取即可
		str = str.substring( 0 , num + "WEB-INF".length() );
		return str;
	}
	
	/**
	 * @Description: TODO(手机数量小于500的情况)
	 * @param shareTitle
	 * @param shareMessage
	 *            void 返回值描述
	 * @author liuwu
	 * @throws Exception
	 * @create_date 2015-3-26 下午2:45:32
	 */
	public void sendAndroidBroadcastType1( String shareTitle ,
	        String shareMessage ) throws Exception
	{
		AndroidBroadcast broadcast = new AndroidBroadcast();
		broadcast.setAppMasterSecret( appMasterSecret );
		broadcast.setPredefinedKeyValue( "appkey" , this.appkey );
		broadcast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		broadcast.setPredefinedKeyValue( "ticker" , "Android broadcast ticker" );
		broadcast.setPredefinedKeyValue( "title" , shareTitle );
		broadcast.setPredefinedKeyValue( "text" , shareMessage );
		broadcast.setPredefinedKeyValue( "after_open" , "go_custom" );
		broadcast.setPredefinedKeyValue( "display_type" , "notification" );
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		broadcast.setPredefinedKeyValue( "production_mode" , "true" );
		// Set customized fields
		broadcast.setExtraField( "test" , "helloworld" );
		broadcast.send();
		
	}
	

	/**
	 * 
	 * @Description: TODO(根据deviceTokens分批推送)
	 * @param shareTitle
	 *            标题
	 * @param shareMessage
	 *            内容
	 * @param deviceTokens
	 *            手机设备编号
	 * @param vcPacageName
	 *            包名
	 * @param map
	 *            需要传的参数
	 * @throws Exception
	 *             void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-18 上午10:55:34
	 */
	public void sendAndroidBroadcastType2( String shareTitle ,
	        String shareMessage , String deviceTokens , String vcPacageName ,
	        Map< String , String > map )
	        throws Exception
	{
		AndroidListcast unicast = new AndroidListcast();
		unicast.setAppMasterSecret( appMasterSecret );
		unicast.setPredefinedKeyValue( "appkey" , this.appkey );
		unicast.setPredefinedKeyValue( "timestamp" , this.timestamp );
		// TODO Set your device token
		unicast.setPredefinedKeyValue( "device_tokens" , deviceTokens );
		unicast.setPredefinedKeyValue( "ticker" , "ticker " );
		unicast.setPredefinedKeyValue( "title" , shareTitle );
		unicast.setPredefinedKeyValue( "text" , shareMessage );
		unicast.setPredefinedKeyValue( "after_open" , "go_activity" );
		unicast.setPredefinedKeyValue( "display_type" , "notification" );
		if ( StringUtils.isNotBlank( vcPacageName ) )
		{
			unicast.setPredefinedKeyValue( "activity" , vcPacageName );
		}
		unicast.setPredefinedKeyValue( "production_mode" , "true" );
		// Set customized fields
		if ( map != null )
		{
			Iterator it = map.entrySet().iterator();
			while ( it.hasNext() )
			{
				Entry entry = ( Entry ) it.next();
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				unicast.setExtraField( key , value );
			}
		}

		unicast.send();
		
	}
	
}
