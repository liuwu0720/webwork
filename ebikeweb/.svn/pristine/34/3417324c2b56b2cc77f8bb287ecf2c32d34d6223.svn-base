package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IDriverSalaryDao;
import com.clt.sub.model.TDriverSalary;
import com.clt.sub.service.IDriverSalaryService;
import com.clt.util.HqlHelper;
import com.clt.util.SystemConstants;

@Service
public class DriverSalaryServiceImp implements IDriverSalaryService
{
	@Autowired
	private IDriverSalaryDao salaryDao;
	
	public TDriverSalary get( int id )
	{
		return salaryDao.get( id );
	}
	
	public void save( TDriverSalary entity )
	{
		salaryDao.save( entity );
	}
	
	public void update( TDriverSalary entity )
	{
		salaryDao.update( entity );
	}
	
	public void saveOrUpdate( TDriverSalary entity )
	{
		salaryDao.saveOrUpdate( entity );
	}
	
	public void saveOrUpdateAll( List< TDriverSalary > entities )
	{
		salaryDao.saveOrUpdateAll( entities );
	}
	
	public void delete( int id )
	{
		TDriverSalary salary = salaryDao.get( id );
		salary.setnEnable( SystemConstants.SYS_DISABLE );
		salaryDao.update( salary );
	}
	
	public Map< String , Object > findByHelper( HqlHelper hql )
	{
		return salaryDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description 根据司机id查询月份
	 */
	public List< Integer > getMonthsExist( int driverId )
	{
		String[] propertyNames = { "iDriverId" , "nEnable" };
		Object[] values = { driverId , SystemConstants.SYS_ENABLE };
		List< TDriverSalary > salarys = salaryDao
		        .findByPropertys( propertyNames , values );
		List< Integer > nMonths = new ArrayList< Integer >();
		if ( CollectionUtils.isEmpty( salarys ) )
		{
			return null;
		}
		for ( TDriverSalary salary : salarys )
		{
			int nMonth = salary.getnMonth();
			nMonths.add( nMonth );
		}
		System.out.println( "nMonths:" + nMonths );
		return nMonths;
	}
}
