/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-7 下午3:15:51 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.TDamage;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;

/**
 * @Package com.clt.sub.service
 * @Description: TODO(质损申请)
 * @author liuwu
 * @date 2015-1-7 下午3:15:51
 * @version V1.0
 */
public interface IDamageService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-8 上午11:37:34
	 */
	Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	/**
	 * @Description: TODO(审核通过)
	 * @param damageId
	 * @param note
	 * @param subbo
	 * @param user
	 *            void 返回值描述
	 * @author liuwu
	 * @param vcResult
	 * @return
	 * @create_date 2015-1-8 下午3:34:31
	 */
	String updateDamageList( String damageId , String note , String subbo ,
	        TUser user , String vcResult );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDamage
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-24 下午5:08:52
	 */
	void saveDamage( TDamage tDamage );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDamage
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午9:55:12
	 */
	void updateDamage( TDamage tDamage );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TDamage 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午10:01:23
	 */
	TDamage findDamageById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDamage
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-4 上午11:51:56
	 */
	void parseUrl( TDamage tDamage );
	
	

}
