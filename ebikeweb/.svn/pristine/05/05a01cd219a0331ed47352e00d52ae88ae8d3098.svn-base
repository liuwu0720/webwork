package com.clt.systemmanger.service.imp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IStaticDao;
import com.clt.systemmanger.model.TStatic;
import com.clt.systemmanger.service.IStaticService;

@Service
public class StaticServiceImp implements IStaticService
{
	@Autowired
	private IStaticDao dao;
	
	/**
	 * 得到字符串类型的值
	 * 
	 * @param parameName
	 * @return
	 */
	@Cacheable( value = "staticCache" , key = "#parameName" )
	public String getStringByParame( String parameName )
	{
		List< TStatic > list = dao.findByProperty( "vcParame" , parameName );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			String value = list.get( 0 ).getVcValue();
			return value;
		}
		return null;
	}
	
	/**
	 * 得到整数类型的值
	 * 
	 * @param parameName
	 * @return
	 */
	@Cacheable( value = "staticCache" , key = "#parameName" )
	public int getIntByParame( String parameName )
	{
		
		List< TStatic > list = dao.findByProperty( "vcParame" , parameName );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			String value = list.get( 0 ).getVcValue();
			return Integer.parseInt( value );
		}
		return 0;
	}
}
