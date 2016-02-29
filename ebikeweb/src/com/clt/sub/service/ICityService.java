package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TCity;
import com.clt.sub.model.TProvince;

/**
 * @Package com.clt.sub.service
 * @Description: 省市Service
 * @author chenbin
 * @date 2014-8-1 上午10:57:12
 * @version V1.0
 */
public interface ICityService
{
	public List< TProvince > getAllProvince();
	
	public List< TCity > getCitysByProID( Integer id );
	
	public List< TCity > getAllCitys();
	
	public TCity getCityByID( Integer id );
	
	public TProvince getProvinceByID( Integer id );
	
	public void update( TCity entity );
	
	public void save( TCity entity );
	
	public void saveOrUpdate( TCity entity );
	
	public void delete( TCity entity );
	
	public void deleteByKey( Integer id );
	
	// 通过名字获取id
	public int getIdByName( String cityName );
}
