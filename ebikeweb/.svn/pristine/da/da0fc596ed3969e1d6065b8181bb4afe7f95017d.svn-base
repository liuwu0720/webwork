/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-11 下午3:38:56 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TShipStatus;
import com.clt.util.HqlHelper;

/** 
 * @Package com.clt.sub.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-11 下午3:38:56 
 * @version V1.0 
 */
public interface IShipStatusService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-11 下午3:43:44
	 */
	Map< String , Object > findOrderStatusByOrderId( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tShipStatus
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-11 下午5:04:11
	 */
	void saveOrUpdate( TShipStatus tShipStatus );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-14 下午1:59:09
	 */
	Map< String , Object > findByHQL( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tShipStatus
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-10 上午10:36:26
	 */
	void save( TShipStatus tShipStatus );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return List<TShipStatus> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-14 下午2:52:06
	 */
	List< TShipStatus > findByLine( Integer id );
	
}
