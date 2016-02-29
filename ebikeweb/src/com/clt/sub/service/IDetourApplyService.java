/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-6 下午3:02:50 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.TDetour;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;

/** 
 * @Package com.clt.sub.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-6 下午3:02:50 
 * @version V1.0 
 */
public interface IDetourApplyService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-6 下午4:21:27
	 */
	Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param user
	 * @param subbo
	 * @param array
	 * @param vcResult
	 * @param vcLength
	 * @param vcNote
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-23 下午6:02:28
	 */
	String updateSureList( TUser user , String subbo , String array ,
	        String vcResult , String vcLength , String vcNote );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDetour
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午10:25:15
	 */
	void save( TDetour tDetour );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDetour
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午10:27:37
	 */
	void update( TDetour tDetour );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TDetour 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午10:32:48
	 */
	TDetour findDetourById( int parseInt );
	
}
