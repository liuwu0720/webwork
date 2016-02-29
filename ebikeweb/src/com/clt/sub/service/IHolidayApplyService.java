/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-4-24 下午3:24:57 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.THolidayApply;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;

/**
 * @Package com.clt.sub.service
 * @Description: TODO(请假审批)
 * @author liuwu
 * @date 2015-4-24 下午3:24:57
 * @version V1.0
 */
public interface IHolidayApplyService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-24 下午3:26:30
	 */
	Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param array
	 * @param vcResult
	 * @param vcApproveNote
	 * @param user
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-24 下午4:22:09
	 */
	String updateHolidayApply( String array , String vcResult ,
	        String vcApproveNote , TUser user );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tHolidayApply
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:52:14
	 */
	void save( THolidayApply tHolidayApply );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tHolidayApply
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:52:40
	 */
	void update( THolidayApply tHolidayApply );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return THolidayApply 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:56:40
	 */
	THolidayApply findById( int parseInt );
	
}
