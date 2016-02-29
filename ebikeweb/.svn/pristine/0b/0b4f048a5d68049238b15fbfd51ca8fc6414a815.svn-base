package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TArkilometer;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * 
 * @Package com.clt.sub.service
 * @Description: 分供方应付公里数Service
 * @author chenbin
 * @date 2014-8-11 下午1:19:58
 * @version V1.0
 */
public interface IArkilometerService
{
	public TArkilometer get( Integer id );
	
	public void update( TArkilometer entity );
	
	public void save( TArkilometer entity );
	
	public void saveOrUpdate( TArkilometer entity );
	
	public void delete( TArkilometer entity );
	
	public void deleteByKey( Integer id );
	
	public List< TArkilometer > loadAll();
	
	public abstract List< TArkilometer > findAll( Page page );
	
	/**
	 * 
	 * @Description: 根据对象的属性和值 查询 并分页
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return List<TArkilometer> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午7:00:05
	 */
	public List< TArkilometer > findByPropertys( String[] propertyNames ,
	        Object[] values , Page page );
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	public List< String[] > getDateBySQL( String sql , Page page );
	
	/**
	 * @Description: TODO(根据起始地、目的地查找应收公里)
	 * @param startID
	 * @param endID
	 * @return double 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-30 上午11:25:21
	 */
	public float getKilomter( int startID , int endID );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @param page
	 * @create_date 2015-5-19 上午9:24:08
	 */
	public Map< String , Object > findBySpringSql( String sql , Page page );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tArkilometer
	 * @return List<TArkilometer> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-19 上午11:37:55
	 */
	public List< TArkilometer > checkIfExiste( TArkilometer tArkilometer );
	
	/**
	 * @Description: TODO(防止同一session两个对象时Hibernate报错))
	 * @param ark
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-19 下午5:57:33
	 */
	public void saveOrUpdateCleanBefore( TArkilometer ark );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tArkilometers
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-21 下午2:29:23
	 */
	public void saveOrUpdateList( List< TArkilometer > tArkilometers );
	
}
