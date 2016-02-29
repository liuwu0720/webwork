package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IShiplineDao;
import com.clt.sub.model.TShipline;
import com.clt.sub.service.IShiplineService;
import com.clt.util.Page;

@Service
public class ShiplineServiceImp implements IShiplineService
{
	
	@Autowired
	IShiplineDao shiplineDao;
	
	public TShipline get( Integer id )
	{
		// TODO Auto-generated method stub
		return shiplineDao.get( id );
	}
	
	public void update( TShipline entity )
	{
		// TODO Auto-generated method stub
		shiplineDao.update( entity );
	}
	
	public void updateCleanBefore( TShipline entity )
	{
		shiplineDao.updateCleanBefore( entity );
	}
	
	public void save( TShipline entity )
	{
		// TODO Auto-generated method stub
		shiplineDao.save( entity );
	}
	
	public void saveOrUpdate( TShipline entity )
	{
		// TODO Auto-generated method stub
		shiplineDao.saveOrUpdate( entity );
	}
	
	public void delete( TShipline entity )
	{
		// TODO Auto-generated method stub
		shiplineDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		shiplineDao.deleteByKey( id );
	}
	
	public List< TShipline > loadAll()
	{
		// TODO Auto-generated method stub
		return shiplineDao.loadAll();
	}
	
	public List< TShipline > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return shiplineDao.findAll( page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-6 下午2:57:03
	 */
	public List< TShipline > findByHeadId( int shipId )
	{
		// TODO Auto-generated method stub
		return shiplineDao.findByProperty( "IShiphead" , shipId );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderId
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-7 下午4:43:01
	 */
	public List< TShipline > findByOrderId( int orderId )
	{
		return shiplineDao.findByProperty( "IOrderId" , orderId );
	}
	
	public List< TShipline > findByProperty( String propertyName , Object value )
	{
		List< TShipline > lines = shiplineDao.findByProperty( propertyName , value );
		return lines;
	}
	
	public List< TShipline > findByPropertys( String[] propertyNames , Object[] values )
	{
		List< TShipline > lines = shiplineDao.findByPropertys( propertyNames , values );
		return lines;
	}
}
