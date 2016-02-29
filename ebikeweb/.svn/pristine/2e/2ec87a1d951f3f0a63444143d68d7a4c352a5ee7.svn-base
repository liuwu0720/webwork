package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TSubCarStyle;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * 
 * @Package com.clt.sub.service
 * @Description: 分供方车型 Service
 * @author chenbin
 * @date 2014-8-1 上午10:56:00
 * @version V1.0
 */
public interface ISubCarStyleService
{
	public TSubCarStyle get( Integer id );
	
	public void update( TSubCarStyle entity );
	
	public void save( TSubCarStyle entity );
	
	public void saveOrUpdate( TSubCarStyle entity );
	
	public void delete( TSubCarStyle entity );
	
	public void deleteByKey( Integer id );
	
	public List< TSubCarStyle > loadAll();
	
	public abstract List< TSubCarStyle > findAll( String subno );
	
	public List< TSubCarStyle > findAllByPage( String subno , Page page );
	
	/**
	 * @Description: TODO(根据分供方名称查出所有商品车)
	 * @return List<TSubCarStyle> 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-23 下午3:33:55
	 */
	public List< TSubCarStyle > findAllBySubNo( String subbo );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-6 上午11:17:14
	 */
	public Map< String , Object > findAllByHql( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param properties
	 * @param values
	 * @return List<TSubCarStyle> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-1 下午1:59:42
	 */
	public List< TSubCarStyle > findByProperties( String[] properties ,
	        Object[] values );
}
