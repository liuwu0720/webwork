/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-9 下午2:27:04
 * @version V1.0
 */
package com.clt.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.clt.systemmanger.model.TUser;
import com.clt.util.push.PushMessageUtil;

/**
 * @Package com.clt.util
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-9 下午2:27:04
 * @version V1.0
 */
public class PushUtils implements Runnable
{
	private final static int indexsize = 40;
	
	private String shareTitle;// 消息标题
	private String shareMessage;// 消息内容
	private List< TUser > users;// 接收推送消息的用户
	private String vcPacageName;// 包名
	Map< String , String > map;// 需要传过去的参数
	
	/**
	 * 
	 * @param shareTitle
	 *            消息标题
	 * @param shareMessage
	 *            消息内容
	 * @param users
	 *            接收推送消息的用户
	 * @param vcPacageName
	 *            包名
	 * @param map
	 *            需要传过去的参数
	 */
	public PushUtils( String shareTitle , String shareMessage ,
	        List< TUser > users , String vcPacageName ,
	        Map< String , String > map )
	{
		super();
		this.shareTitle = shareTitle;
		this.shareMessage = shareMessage;
		this.users = users;
		this.vcPacageName = vcPacageName;
		this.map = map;
	}

	public void pushMessage( List< TUser > users , String shareTitle ,
	        String shareMessage )
	{
		if ( users.size() > indexsize )
		{
			int size = users.size() % indexsize == 0 ? users.size() / indexsize
			        : users.size() / indexsize + 1;
			int index = 0;
			for ( int i = 1 ; i <= size ; i++ )
			{
				index++ ;
				StringBuffer stringBuffer = new StringBuffer();
				int maxIndex = 0;
				if ( indexsize * index < users.size() )
				{
					maxIndex = index * indexsize;
				}
				else
				{
					maxIndex = users.size();
				}
				for ( int i2 = indexsize * ( index - 1 ) ; i2 < maxIndex ; i2++ )
				{
					stringBuffer.append( users.get( i2 ).getVcDeviceTokens()
					        + "," );
				}
				int lastIndex = stringBuffer.lastIndexOf( "," );
				
				String deviceTokens = stringBuffer.deleteCharAt( lastIndex )
				        .toString();
				PushMessageUtil pUtil = new PushMessageUtil();
				try
				{
					pUtil.sendAndroidBroadcastType2( shareTitle , shareMessage ,
					        deviceTokens , vcPacageName , map );// 所有人
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author liuwu
	 * @create_date 2015-6-17 下午4:29:38
	 */
	public void run()
	{
		if ( users.size() > indexsize )
		{
			int size = users.size() % indexsize == 0 ? users.size() / indexsize
			        : users.size() / indexsize + 1;
			int index = 0;
			for ( int i = 1 ; i <= size ; i++ )
			{
				index++ ;
				StringBuffer stringBuffer = new StringBuffer();
				int maxIndex = 0;
				if ( indexsize * index < users.size() )
				{
					maxIndex = index * indexsize;
				}
				else
				{
					maxIndex = users.size();
				}
				for ( int i2 = indexsize * ( index - 1 ) ; i2 < maxIndex ; i2++ )
				{
					if ( users.get( i2 ).getVcDeviceTokens() != null )
					{
						stringBuffer.append( users.get( i2 )
						        .getVcDeviceTokens() + "," );
					}

				}
				int lastIndex = stringBuffer.lastIndexOf( "," );
				String deviceTokens = "";
				if ( lastIndex > 0 )
				{
					deviceTokens = stringBuffer.deleteCharAt( lastIndex )
					        .toString();
				}

				// deviceTokens =
				// "AjV7Qs70Q7MsftekjQwgM7I-vuqjUJkGOmMjR65G1eHt";
				PushMessageUtil pUtil = new PushMessageUtil();
				try
				{
					synchronized ( this )
					{
						if ( StringUtils.isNotBlank( deviceTokens ) )
						{
							pUtil.sendAndroidBroadcastType2( shareTitle ,
							        shareMessage , deviceTokens , vcPacageName ,
							        map );// 所有人
						}

						System.out.println( "开始**************" );
					}
					Thread.sleep( 1000 );
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
			}

			if ( index == size )
			{
				Thread.interrupted();
				System.out.println( "*****************结束" );
			}

		}
		else
		// 小于indexsize的情况
		{
			StringBuffer stringBuffer = new StringBuffer();
			for ( TUser tUser : users )
			{
				if ( tUser.getVcDeviceTokens() != null )
				{
					stringBuffer.append( tUser.getVcDeviceTokens() + "," );
				}

			}
			int lastIndex = stringBuffer.lastIndexOf( "," );
			String deviceTokens = null;
			if ( lastIndex > 0 )
			{
				deviceTokens = stringBuffer.deleteCharAt( lastIndex )
				        .toString();
			}

			PushMessageUtil pUtil = new PushMessageUtil();
			try
			{
				if ( StringUtils.isNotBlank( deviceTokens ) )
				{
					pUtil.sendAndroidBroadcastType2( shareTitle , shareMessage ,
					        deviceTokens , vcPacageName , map );// 所有人
				}
				
				
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		
	}
}
