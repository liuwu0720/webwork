package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ITruckDriverLinkDao;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.service.ITruckDriverLinkService;

@Service
public class TruckDriverLinkServiceImp implements ITruckDriverLinkService
{
	@Autowired
	private ITruckDriverLinkDao linkDao;
	
	public void get( Integer id )
	{
		linkDao.get( id );
	}
	
	public List< TTruckDriverLlink > getByProperty( String propertyName , Object value )
	{
		return linkDao.findByProperty( propertyName , value );
	}
	
	public void save( TTruckDriverLlink entity )
	{
		linkDao.save( entity );
	}
	
	public void update( TTruckDriverLlink entity )
	{
		linkDao.update( entity );
	}
	
	public void delete( TTruckDriverLlink entity )
	{
		linkDao.delete( entity );
	}
	
	public void deleteById( Integer id )
	{
		linkDao.deleteByKey( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames2
	 * @param values2
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-27 下午4:28:57
	 */
	public List< TTruckDriverLlink > findByPropertys( String[] propertyNames2 ,
	        Object[] values2 )
	{
		// TODO Auto-generated method stub
		return linkDao.findByPropertys( propertyNames2 , values2 );
	}
}
