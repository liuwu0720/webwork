package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TOrder;
import com.clt.sub.model.TReturnPic;
import com.clt.sub.model.TShiphead;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.sub.service
 * @Description: 订单Service
 * @author chenbin
 * @date 2014-8-1 上午10:56:41
 * @version V1.0
 */
public interface IOrderService
{
	public TOrder get( Integer id );
	
	public void update( TOrder entity );
	
	public void save( TOrder entity );
	
	public List< TOrder > find( String hql );
	
	public void saveOrUpdate( TOrder entity , TUser user , String paramType ,
	        int integalID );
	
	public void delete( TOrder entity );
	
	public void deleteByKey( Integer id );
	
	public List< TOrder > loadAll();
	
	public abstract List< TOrder > findAll( Page page );
	
	/**
	 * 
	 * @Description: 获取订单表最大的订单号并加1 N+yyMMdd+4位流水号
	 * @return String 订单号
	 * @author chenbin
	 * @create_date 2014-7-16 下午3:36:42
	 */
	public String getMaxOrderNo();
	
	/**
	 * @Description: 获取该分供方的所有订单 根据分供方编号
	 * @param subno
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午4:06:13
	 */
	public List< TOrder > getAllOrderBysubno( String subno , Page page );
	
	public List< TOrder > findByPropertys( String[] propertyNames , Object[] values );
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	public int getCountSQL( String sql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @param page
	 * @create_date 2015-4-29 上午10:14:51
	 */
	public Map< String , Object > findBySpringSql( String sql , Page page );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param order
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-5 下午2:19:50
	 */
	public void saveOrUpdateOrder( TOrder order );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tReturnPic
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-8 上午10:56:30
	 */
	public void saveTreturnPic( TReturnPic tReturnPic );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-8 下午2:17:05
	 */
	public Map< String , Object > getSpringSql( String sql , Page page );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return TShiphead 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-12 下午2:46:55
	 */
	public TShiphead getByOrderId( Integer id );
}
