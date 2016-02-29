/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-11 下午2:55:20
 * @version V1.0
 */
package com.clt.systemmanger.service;

import java.util.Map;

import com.clt.sub.model.TAssess;
import com.clt.systemmanger.model.TStores;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.systemmanger.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-11 下午2:55:20
 * @version V1.0
 */
public interface IStoresService
{
	public TStores get( int id );
	
	public void save( TStores entity );
	
	public void update( TStores entity );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-11 下午4:26:16
	 */
	Map< String , Object > getSpringSQL( String sql , Page page );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-12 上午9:52:10
	 */
	Map< String , Object > findByHql( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tAssess
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-12 下午3:38:07
	 */
	void saveAssess( TAssess tAssess );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param stroeId
	 * @return TStores 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-15 下午3:42:59
	 */
	TStores getById( int stroeId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tStores
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-15 下午4:04:40
	 */
	void parseUrl( TStores tStores );
	
}
