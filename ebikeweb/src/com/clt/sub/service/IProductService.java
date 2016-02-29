package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TProduct;
import com.clt.sub.model.TProductCarStyle;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * 抢单主表服务类
 * 
 * @author hjx86
 * 
 */
public interface IProductService
{
	public TProduct get( Integer id );
	
	public void update( TProduct entity );
	
	public void save( TProduct entity );
	
	public void saveOrUpdate( TProduct entity );
	
	public void delete( TProduct entity );
	
	public void deleteByKey( Integer id );
	
	public List< TProduct > loadAll();
	
	public abstract List< TProduct > findAll( Page page );
	
	/**
	 * @Description: 保存产品信息以及产品车型信息
	 * @param entity
	 *            保存产品信息
	 * @param productCars
	 *            产品车型信息 集合 void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:10:55
	 */
	public void saveOrUpdate( TProduct entity , List< TProductCarStyle > productCars );
	
	/**
	 * @Description: 获得今天正在秒杀的产品信息
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:52:45
	 */
	public List< TProduct > findTodayProduct();
	
	/**
	 * @Description: 获得即将开始秒杀的产品信息 ，并按秒杀开始时间升序
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:52:45
	 */
	public List< TProduct > findComeKillProduct();
	
	/**
	 * @Description: 获得秒杀的历史信息（已经过了秒杀的）
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:58:51
	 */
	public List< TProduct > findOutTimeKillProduct();
	
	/**
	 * @Description: 获得自己发布的秒杀信息
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午11:01:30
	 */
	public List< TProduct > findSelfReleaseProduct( TUser userId );
	
	/**
	 * @Description: 根据helper 获得产品列表数据
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月12日 下午5:05:26
	 */
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	/**
	 * @Description: 根据sql和page获得列表信息
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月26日 上午9:59:28
	 */
	public Map< String , Object > findBySqlAndPage( String sql , Page page );
	
	/**
	 * @Description 中标后生成临时合同（订单）
	 * @param productId
	 */
	public boolean saveTempAgreement( int productId );
	
	public Map< String , Object > findByHelper( HqlHelper helper , String subNo );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param properties
	 * @param values
	 * @return List<TProduct> 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-17 下午3:55:34
	 */
	public List< TProduct > findByProperties( String[] properties , Object[] values );
	
	/**
	 * @Description: 获得秒杀的历史信息（已经过了秒杀的）,并且没有人参与秒杀的,并且是初始状态的抢单
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:58:51
	 */
	public List< TProduct > findOutTimeAndNotKillProduct();
	
	// 获取pc端历史订单
	public Map< String , Object > getEndProduct( int userId , String vcKillno ,
	        String vcStart , String vcEnd , String isArrive , Page page );
	
	// 通过抢单id获取订单列表
	public Map< String , Object > getOrdersByCarId( int productCarStyleId );
	
	// 根据orderId获取订单状态
	public Map< String , Object > getStatusByOid( int orderId );
	
	/**
	 * @Description: 根据抢单编号，获得抢单信息，修改对应抢单状态为 发布者评价。
	 * 适用于 发布者评价后，修改状态 
	 * @param killNo 
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月21日 下午3:50:48
	 */
	public void saveBidByKillNo( String killNo );
}
