package com.clt.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;

/**
 * @Package com.clt.systemmanger.controller
 * @Description:
 * @author hjx
 * @date 2014年7月18日 下午1:15:52
 * @version V1.0
 */

public class GlobalController
{
	
	public void initBinder( WebDataBinder binder )
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		dateFormat.setLenient( false );
		SimpleDateFormat datetimeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		datetimeFormat.setLenient( false );
		// 自动转换日期类型的字段格式
		binder.registerCustomEditor( Date.class ,
		        new CustomDateEditor( dateFormat , true ) );
		binder.registerCustomEditor( java.sql.Timestamp.class ,
		        new CustomTimestampEditor( datetimeFormat , true ) );
		
	}
	
}
