package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TDriverCost;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * 
 * @Description: 分供方 司机应收单价
 * @author chenbin
 * @date 2014-8-11 下午2:25:45
 * @version V1.0
 */
public interface IDriverCostService
{
	
	public TDriverCost get( Integer id );
	
	public void update( TDriverCost entity );
	
	public void save( TDriverCost entity );
	
	public void saveOrUpdate( TDriverCost entity );
	
	public void delete( TDriverCost entity );
	
	public void deleteByKey( Integer id );
	
	public List< TDriverCost > loadAll();
	
	public abstract List< TDriverCost > findAll( Page page );
	
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
	/**
	 * @Description: 批量保存应付单价
	 * @param msgstr
	 *            字符串 数组 格式 >> 车辆ID,单价,开始时间, 结束时间
	 * @author chenbin
	 * @throws Exception
	 * @create_date 2014-8-11 下午5:34:45
	 */
	public void saveTDriverCosts( String[] msgstr , TUser us ) throws Exception;
	
	/**
	 * @Description: TODO(批量修改)
	 * @param tDriverCosts
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-24 下午3:19:15
	 */
	public void saveOrUpdateList( List< TDriverCost > tDriverCosts );
	
	/**
	 * @Description: TODO(根据条件查出应收单价)
	 * @param iCarStyleId
	 * @param stacity
	 * @param endcity
	 * @param iCustomerId
	 * @return double 返回值描述
	 * @author liuwu
	 * @param string
	 * @create_date 2014-12-25 下午3:25:32
	 */
	public float getCostPrice( int iCarStyleId , int stacity , int endcity ,
	        int iCustomerId , String subno );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDriverCost
	 * @return List<TDriverCost> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-19 上午11:58:53
	 */
	public List< TDriverCost > checkIfExist( TDriverCost tDriverCost );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-19 下午6:24:48
	 */
	public Map< String , Object > findByHql( HqlHelper hql );
}
