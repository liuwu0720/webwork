package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TDriver;
import com.clt.sub.model.TDriverSalaryCoefficient;
import com.clt.sub.model.TDriverSubsideLinks;
import com.clt.sub.model.TDriverSubsides;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

public interface IDriverService
{
	public TDriver get( Integer id );
	
	public void save( TDriver entity );
	
	/**
	 * 更新的时候，判断图片路径是否需要更换
	 */
	public void update( TDriver entity );
	
	public void delete( TDriver entity );
	
	public List< TDriver > findByProperty( String propertyName , Object value );
	
	public Map< String , Object > findByHelper( HqlHelper hql );
	
	public Map< String , Object > findById( int id );
	
	public Map< String , Object > findCarNoById( int id );
	
	public void deleteLinkById( int id );
	
	/**
	 * 获取所有的司机类型
	 * 
	 * @return
	 */
	public Map< String , Object > findDriverClass();
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames
	 * @param values
	 * @return List<TDriver> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-25 上午11:52:45
	 */
	public List< TDriver > findByPropertys( String[] propertyNames , Object[] values );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-25 下午2:07:38
	 */
	public Map< String , Object > findDriverSalaryCoffe( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TDriverSalaryCoefficient 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-25 下午2:17:32
	 */
	public TDriverSalaryCoefficient findDriverSalaryCoffById( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSalaryCoefficient
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-25 下午3:40:19
	 */
	public void saveOrUpdateSalaryCoefficient( TDriverSalaryCoefficient tSalaryCoefficient );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSalaryCoefficient
	 * @return String 返回值描述
	 * @author liuwu
	 * @param tDriver
	 * @param subno
	 * @create_date 2015-5-26 下午2:24:09
	 */
	public String checkSalaryCoefficient( TDriverSalaryCoefficient tSalaryCoefficient ,
	        String subno , TDriver tDriver );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSalaryCoefficient
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-26 下午2:46:51
	 */
	public void updateTSalaryCOfficient( TDriverSalaryCoefficient tSalaryCoefficient );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param driver
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-27 下午2:24:23
	 */
	public void updateCleanBefore( TDriver driver );
	
	/**
	 * @Description: TODO(查找司机补贴)
	 * @param parseInt
	 * @return TDriverSubsides 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-27 下午5:47:11
	 */
	public TDriverSubsides findDriverSalarySubsideById( int parseInt );
	
	/**
	 * @Description: TODO(保存司机补贴)
	 * @param tDriverSubsides
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-28 上午9:32:23
	 */
	public void saveDriverSubsides( TDriverSubsides tDriverSubsides );
	
	/**
	 * @Description: TODO(修改司机补贴)
	 * @param tDriverSubsides
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-28 上午9:34:49
	 */
	public void updateDriverSubsides( TDriverSubsides tDriverSubsides );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-29 上午11:08:22
	 */
	public Map< String , Object > findDriverSalarySubside( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-29 上午11:55:20
	 */
	public Map< String , Object > getSpringSql( String sql , Page page );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSubsideLinks
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-2 上午9:57:37
	 */
	public void saveUpdateDriverSubsideLink( TDriverSubsideLinks tSubsideLinks );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return List<TDriverSubsideLinks> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-2 下午2:06:05
	 */
	public List< TDriverSubsideLinks > findTDriverSubsideLinksByDriverId( int parseInt );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tLinks
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-2 下午2:09:09
	 */
	public void deleteDriverLinks( List< TDriverSubsideLinks > tLinks );
	
	public Map< String , Object > parseUrl( Map< String , Object > map );
	
}
