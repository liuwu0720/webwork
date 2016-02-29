/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-8 下午1:47:58 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.TArorderDriver;
import com.clt.util.HqlHelper;

/** 
 * @Package com.clt.sub.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-8 下午1:47:58 
 * @version V1.0 
 */
public interface IAorderDriverService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-8 下午1:53:03
	 */
	Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return TArorderDriver 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-8 下午3:54:04
	 */
	TArorderDriver getById( int id );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tArorderDriver
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-8 下午3:56:13
	 */
	void saveOrUpdate( TArorderDriver tArorderDriver );
	
}
