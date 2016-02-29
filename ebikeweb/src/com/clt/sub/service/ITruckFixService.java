/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-6 上午10:18:11 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.TApplyPic;
import com.clt.sub.model.TFixTruck;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;

/** 
 * @Package com.clt.sub.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-6 上午10:18:11 
 * @version V1.0 
 */
public interface ITruckFixService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-6 上午10:18:36
	 */
	Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	

	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFixTruck
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:32:39
	 */
	void save( TFixTruck tFixTruck );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFixTruck
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:34:34
	 */
	void update( TFixTruck tFixTruck );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TFixTruck 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:38:55
	 */
	TFixTruck findById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param array
	 * @param user
	 * @param subbo
	 * @param vcResult
	 * @param vcNote
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-30 下午2:38:08
	 */
	String updateSureList( String array , TUser user , String subbo ,
	        String vcResult , String vcNote );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFixTruck
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-4 下午1:36:44
	 */
	void parseUrl( TFixTruck tFixTruck );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tApplyPic
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-9 下午5:25:16
	 */
	void parseUrl( TApplyPic tApplyPic );
	
	
}
