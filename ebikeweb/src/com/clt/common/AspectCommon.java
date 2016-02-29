package com.clt.common;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Package com.clt.common
 * @Description:切面组件
 * @author hjx
 * @date 2014年12月10日 上午11:24:20
 * @version V1.0
 */
@Aspect
@Component
public class AspectCommon
{
	Logger logger = Logger.getLogger( AspectCommon.class );
	String logStr = null;
	
	/**
	 * 
	 */
	public AspectCommon()
	{
		super();
		System.out.println( "AspectCommon初始化。" );
		System.out.println( "AspectCommon实现对积分扣除的处理。" );
		System.out.println( "AspectCommon实现对。" );
		System.out.println( "AspectCommon初始化。" );
		System.out.println( "AspectCommon初始化。" );
	}
	
	// @Before( "execution(* com.clt.sub.controller.*.*(..))" )
	public void beforeRun()
	{
		System.out.println( "模拟进行权限检查。" );
		System.out.println( "模拟进行权限检查。" );
		System.out.println( "模拟进行权限检查。" );
		System.out.println( "模拟进行权限检查。" );
		System.out.println( "模拟进行权限检查。" );
		System.out.println( "模拟进行权限检查。" );
		System.out.println( "模拟进行权限检查。" );
		System.out.println( "模拟进行权限检查。" );
		System.out.println( "模拟进行权限检查。" );
	}
	
	/**
	 * 环绕通知：包围一个连接点的通知，可以在方法的调用前后完成自定义的行为，也可以选择不执行
	 */
	@Around( "execution(* com.clt.*.controller.*.*(..))" )
	public Object doAround( ProceedingJoinPoint pjp ) throws Throwable
	{
		
		Object result = null;
		try
		{
			result = pjp.proceed();
		}
		catch ( Exception e )
		{
			logStr = "方法：" + pjp.getTarget().getClass() + "."
			        + pjp.getSignature().getName() + "()  ";
			logStr = logStr + "错误信息如下：[" + e + "]";
			System.out.println( logStr );
			logger.info( logStr );
		}
		
		return result;
	}
}
