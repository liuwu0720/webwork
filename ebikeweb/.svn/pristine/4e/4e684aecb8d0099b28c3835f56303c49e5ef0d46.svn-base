package com.clt.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

/**
 * @Package com.clt.common
 * @Description: Spring 统一日志处理实现类
 * @author hjx
 * @date 2015年2月3日 下午4:41:33
 * @version V1.0
 */
public class LogInterceptor implements MethodInterceptor
{
	
	public Object invoke( MethodInvocation invocation ) throws Throwable
	{
		Logger loger = Logger.getLogger( invocation.getClass() );
		loger.info( "--Log By hjx -----------------------------------------------------------------------------" );
		loger.info( invocation.getMethod() + ":BEGIN!--(LOG)" );// 方法前的操作
		Object obj = invocation.proceed();// 执行需要Log的方法
		loger.info( invocation.getMethod() + ":END!--(LOG)" );// 方法后的操作
		loger.info( "-------------------------------------------------------------------------------------------------" );
		
		return obj;
	}
	
}
