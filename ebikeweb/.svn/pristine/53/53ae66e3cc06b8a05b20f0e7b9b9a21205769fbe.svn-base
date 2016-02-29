package com.clt.common;

/**
 * @Package com.clt.common
 * @Description: 自定义业务异常处理类 友好提示
 * @author hjx
 * @date 2015年2月3日 下午4:40:01
 * @version V1.0
 */
public class BusinessException extends RuntimeException
{
	private static final long serialVersionUID = 3152616724785436891L;
	
	public BusinessException( String frdMessage )
	{
		super( createFriendlyErrMsg( frdMessage ) );
	}
	
	public BusinessException( Throwable throwable )
	{
		super( throwable );
	}
	
	public BusinessException( Throwable throwable , String frdMessage )
	{
		super( throwable );
	}
	
	private static String createFriendlyErrMsg( String msgBody )
	{
		String prefixStr = "抱歉，";
		String suffixStr = " 请稍后再试或与管理员联系！";
		
		StringBuffer friendlyErrMsg = new StringBuffer( "" );
		
		friendlyErrMsg.append( prefixStr );
		
		friendlyErrMsg.append( msgBody );
		
		friendlyErrMsg.append( suffixStr );
		
		return friendlyErrMsg.toString();
	}
}