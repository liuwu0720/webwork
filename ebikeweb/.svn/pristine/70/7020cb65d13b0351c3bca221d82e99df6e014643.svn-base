package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TShipline;
import com.clt.util.Page;

public interface IShiplineService
{
	public TShipline get( Integer id );
	
	public void update( TShipline entity );
	
	public void updateCleanBefore( TShipline entity );
	
	public void save( TShipline entity );
	
	public void saveOrUpdate( TShipline entity );
	
	public void delete( TShipline entity );
	
	public void deleteByKey( Integer id );
	
	public List< TShipline > loadAll();
	
	public abstract List< TShipline > findAll( Page page );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param shipId
	 * @return List<TShipline> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-6 下午2:56:54
	 */
	public List< TShipline > findByHeadId( int shipId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderId
	 * @return List<TShipline> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-7 下午4:42:55
	 */
	public List< TShipline > findByOrderId( int orderId );
	
	public List< TShipline > findByProperty( String propertyName , Object value );
	
	public List< TShipline > findByPropertys( String[] propertyNames , Object[] values );
	
}
