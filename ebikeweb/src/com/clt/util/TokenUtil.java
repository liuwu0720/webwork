package com.clt.util;

import java.util.UUID;

/**
 * @Package com.clt.util
 * @Description: token工具
 * @author hjx
 * @date 2015年4月16日 下午5:06:19
 * @version V1.0
 */
public class TokenUtil
{
	/**
	 * @Description: 使用UUID生产一个字符串，作为token
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2015年4月16日 下午5:07:07
	 */
	public static String getToken()
	{
		return UUID.randomUUID().toString();
	}
	
}
