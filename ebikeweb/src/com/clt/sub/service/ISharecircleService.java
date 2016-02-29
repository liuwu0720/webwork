package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TSharecircle;
import com.clt.systemmanger.model.TShareTag;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

public interface ISharecircleService
{
	public TSharecircle get( Integer id );
	
	public void save( TSharecircle entity );
	
	public void update( TSharecircle entity );
	
	public void saveOrUpdate( TSharecircle entity );
	
	public void delete( TSharecircle entity );
	
	public void updateCleanBefore( TSharecircle entity );
	
	public void deleteByKey( Integer id );
	
	public List< TSharecircle > loadAll();
	
	public List< TSharecircle > findAll( Page page );
	
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	public List< TSharecircle > findByPropertys( String[] propertyNames , Object[] values );
	
	/**
	 * 解析结果集中 每个对象里的url，修改url，使其可以在客户端进行加载 hjx
	 * 
	 * @param map
	 * @return
	 */
	public Map< String , Object > parseUrl( Map< String , Object > map );
	
	public List< TShareTag > getAllTags();
}
