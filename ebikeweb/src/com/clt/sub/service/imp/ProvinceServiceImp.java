package com.clt.sub.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IProvinceDao;
import com.clt.sub.model.TProvince;
import com.clt.sub.service.IProvinceService;

@Service
public class ProvinceServiceImp implements IProvinceService
{
	@Autowired
	private IProvinceDao provinceDao;
	
	public TProvince get( int id )
	{
		return provinceDao.get( id );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void save( TProvince entity )
	{
		provinceDao.save( entity );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void update( TProvince entity )
	{
		provinceDao.update( entity );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void saveOrUpdate( TProvince entity )
	{
		provinceDao.saveOrUpdate( entity );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void delete( TProvince entity )
	{
		provinceDao.delete( entity );
	}
	
	@CacheEvict( value = "cityCache" , beforeInvocation = true )
	public void deleteById( int id )
	{
		provinceDao.deleteByKey( id );
	}
	
	/**
	 * 通过省份名称获取省份Id
	 */
	public int getIdByName( String provinceName )
	{
		String sql = "select id from t_province where PROVINCENAME like '" + provinceName
		        + "%'";
		Object idObj = provinceDao.getDateBySQL( sql );
		int id = Integer.parseInt( idObj.toString() );
		return id;
	}
}
