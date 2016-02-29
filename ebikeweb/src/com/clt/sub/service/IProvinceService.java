package com.clt.sub.service;

import com.clt.sub.model.TProvince;

public interface IProvinceService
{
	public TProvince get( int id );
	
	public void update( TProvince entity );
	
	public void save( TProvince entity );
	
	public void saveOrUpdate( TProvince entity );
	
	public void delete( TProvince entity );
	
	public void deleteById( int id );
	
	public int getIdByName( String provinceName );
}
