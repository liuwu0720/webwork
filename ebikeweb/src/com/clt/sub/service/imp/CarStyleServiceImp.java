package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ICarStyleDao;
import com.clt.sub.model.TCarStyle;
import com.clt.sub.service.ICarStyleService;
import com.clt.util.Page;

@Service
public class CarStyleServiceImp implements ICarStyleService
{
	
	@Autowired
	ICarStyleDao carStyleDao;
	
	public TCarStyle get( Integer id )
	{
		// TODO Auto-generated method stub
		return carStyleDao.get( id );
	}
	
	public void update( TCarStyle entity )
	{
		// TODO Auto-generated method stub
		carStyleDao.update( entity );
	}
	
	public void save( TCarStyle entity )
	{
		// TODO Auto-generated method stub
		carStyleDao.save( entity );
	}
	
	public void saveOrUpdate( TCarStyle entity )
	{
		// TODO Auto-generated method stub
		carStyleDao.saveOrUpdate( entity );
	}
	
	public void delete( TCarStyle entity )
	{
		// TODO Auto-generated method stub
		carStyleDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		carStyleDao.deleteByKey( id );
	}
	
	public List< TCarStyle > loadAll()
	{
		// TODO Auto-generated method stub
		return carStyleDao.loadAll();
	}
	
	public List< TCarStyle > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return carStyleDao.findAll( page );
	}
	
}
