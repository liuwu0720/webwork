package com.clt.sub.service.imp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ICityDao;
import com.clt.sub.dao.IProvinceDao;
import com.clt.sub.model.TCity;
import com.clt.sub.model.TProvince;
import com.clt.sub.service.ICityService;

@Service
public class CityServiceImp implements ICityService
{
	@Autowired
	ICityDao cityDao;
	@Autowired
	IProvinceDao provinceDao;
	
	@Cacheable( value = "cityCache" )
	public List< TProvince > getAllProvince()
	{
		return provinceDao.findAll();
	}
	
	@Cacheable( value = "cityCache" , key = "#id" )
	public List< TCity > getCitysByProID( Integer id )
	{
		
		TProvince pro = provinceDao.get( id );
		if ( pro != null )
		{
			List< TCity > allcity = cityDao.findByProperty( "provinceid" , pro.getId() );
			
			return allcity;
		}
		return null;
		
	}
	
	public TCity getCityByID( Integer id )
	{
		
		return cityDao.get( id );
	}
	
	public TProvince getProvinceByID( Integer id )
	{
		
		return provinceDao.get( id );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void update( TCity entity )
	{
		cityDao.update( entity );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void save( TCity entity )
	{
		cityDao.save( entity );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void saveOrUpdate( TCity entity )
	{
		cityDao.saveOrUpdate( entity );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void delete( TCity entity )
	{
		cityDao.delete( entity );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void deleteByKey( Integer id )
	{
		cityDao.deleteByKey( id );
	}
	
	/**
	 * @Description: 获得所有城市
	 * @return
	 * @author hjx
	 * @create_date 2015年4月17日 上午10:33:00
	 */
	public List< TCity > getAllCitys()
	{
		return cityDao.findAll();
	}
	
	/**
	 * 通过名字获取id
	 */
	public int getIdByName( String cityName )
	{
		List< TCity > list = cityDao.findByProperty( "cityname" , cityName );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return list.get( 0 ).getId();
		}
		return 0;
	}
}
