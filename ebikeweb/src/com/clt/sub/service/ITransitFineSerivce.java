/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-4 下午1:51:48 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.TFineApplay;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;

/** 
 * @Package com.clt.finance.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-4 下午1:51:48 
 * @version V1.0 
 */
public interface ITransitFineSerivce
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-4 下午1:52:17
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	/**
	 * @Description: TODO(通过审核)
	 * @param array
	 *            void 返回值描述
	 * @author liuwu
	 * @param user
	 * @param subbo
	 * @param vcApplyNote
	 * @param vcResult
	 * @return
	 * @create_date 2015-1-5 上午11:28:23
	 */
	public String updateSureList( String array , TUser user ,
	        String subbo ,
	        String vcResult , String vcApplyNote );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFineApplay
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:00:54
	 */
	public void save( TFineApplay tFineApplay );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFineApplay
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:04:34
	 */
	public void update( TFineApplay tFineApplay );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TFineApplay 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:16:16
	 */
	public TFineApplay findById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFineApplay
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-15 下午3:57:42
	 */
	public void parseUrl( TFineApplay tFineApplay );
	
	

}
