package com.clt.sub.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IHolidayTypeDao;
import com.clt.sub.model.THolidayType;
import com.clt.sub.service.IHolidayTypeService;

@Service
public class HolidayTypeServiceImp implements IHolidayTypeService
{
	@Autowired
	private IHolidayTypeDao typeDao;
	
	public void delete( THolidayType entity )
	{
		typeDao.delete( entity );
	}
	
	public THolidayType get( int id )
	{
		return typeDao.get( id );
	}
	
	public void save( THolidayType entity )
	{
		typeDao.save( entity );
	}
	
	public void update( THolidayType entity )
	{
		typeDao.update( entity );
	}
	
}
