package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TDriverSalary;
import com.clt.util.HqlHelper;

public interface IDriverSalaryService
{
	public TDriverSalary get( int id );
	
	public void save( TDriverSalary entity );
	
	public void update( TDriverSalary entity );
	
	public void saveOrUpdateAll( List< TDriverSalary > entities );
	
	public void saveOrUpdate( TDriverSalary entity );
	
	public void delete( int id );
	
	public Map< String , Object > findByHelper( HqlHelper hql );
	
	public List< Integer > getMonthsExist( int driverId );
}
