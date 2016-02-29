package com.clt.sub.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.clt.sub.model.TShiphead;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * 
 * @Description: 发运指令Service
 * @author chenbin
 * @date 2014-8-11 下午1:25:50
 * @version V1.0
 */
public interface IShipheadService
{
	public TShiphead get( Integer id );
	
	public void update( TShiphead entity );
	
	public void updateCleanBefore( TShiphead entity );
	
	public void save( TShiphead entity );
	
	public void saveOrUpdate( TShiphead entity );
	
	public void delete( TShiphead entity );
	
	public void deleteByKey( Integer id );
	
	public List< TShiphead > loadAll();
	
	// 通过truckId获取最新的发运指令
	public Map< String , Object > getLatestShiphead( int truckId );
	
	public abstract List< TShiphead > findAll( Page page );
	
	/**
	 * 
	 * @Description: 生成发运指令 Z+yyMMdd+4位流水号
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午3:26:33
	 */
	public String getMaxShipNo();
	
	/**
	 * 
	 * @Description: TODO(订单配载)
	 * @param msgstr
	 * @param driverID
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-8 下午1:44:32
	 */
	public String saveshipHead( String[] msgstr , int driverID );
	
	/**
	 * 
	 * @Description: 获取该分供方所有的发运指令 未入场确定的指令
	 * @param subno
	 * @return List 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:57:22
	 */
	public List getAllShipdetailBySubno( String subno );
	
	/**
	 * @Description: 回单确定 根据发运指令号
	 * @param lineids
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:14:11
	 */
	public void ReturnByShipLineID( String shipNo );
	
	/**
	 * @Description: 发车确定 根据发运明细
	 * @param lineids
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:14:11
	 */
	public void SendByShipLineID( int[] lineids );
	
	/**
	 * @Description: 装车确定 根据发运明细
	 * @param lineids
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:14:11
	 */
	public void LoadByShipLineID( int[] lineids );
	
	/**
	 * 
	 * @Description: 入场确定 根据发运明细
	 * @param lineid
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:09:32
	 */
	public void EntranceByShipLineID( int[] lineids );
	
	/**
	 * 
	 * @Description: 获取该分供方所有已回单的数据
	 * @param shipno
	 * @return List 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-21 下午2:07:45
	 */
	public List getAllReturnShip( String subno );
	
	public Map< String , Object > findByHelper( HqlHelper hql );
	
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
	public void saveDespatchInfo( String partype , String shipnos , int usid );
	
	public String saveShipInfoCancel( String headids );
	
	public List< String[] > getDateBySQL( String sql , Page page );
	
	/**
	 * @Description: TODO(增加配载)
	 * @param strs
	 * @param driveID
	 * @param iHeadId
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-8 下午1:55:48
	 */
	public String saveUpdateShipHead( String[] strs , int driveID , int iHeadId );
	
	/**
	 * @Description 通过属性查找
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List< TShiphead > findByProperty( String propertyName , Object value );
	
	/**
	 * @Description: TODO(根据参数查询并按指定字段排序)
	 * @param properties
	 * @param values
	 * @param orderByParam
	 * @return List<TShiphead> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-8 下午4:46:00
	 */
	public List< TShiphead > findByPropertysOrderBy( String[] properties ,
	        Object[] values , String orderByParam );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iheadId
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-21 下午3:14:36
	 */
	public String saveShipHeadFromErp( int iheadId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return List<Map<String,Object>> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-24 上午10:17:45
	 */
	public List< Map< String , Object >> excuteSql( String sql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iheadId
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-24 下午2:22:25
	 */
	public String updateCancelShipHead( int iheadId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iheadId
	 * @param itruckId
	 * @param idriverId
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-24 下午3:05:11
	 */
	public String updateShipHeadTruckAndDriver( int iheadId , int itruckId ,
	        int idriverId );
	
	/**
	 * @Description: TODO(增加或删除订单接口)
	 * @param iheadId
	 * @param type
	 * @param orderIds
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-25 上午10:57:13
	 */
	public String updateChangeOrderQtysFromErp( int iheadId , String type ,
	        String orderIds );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iheadId
	 * @param iorderId
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-25 下午2:54:49
	 */
	public String updateOrderDetialsFromErp( int iorderId );
	
	public List< TShiphead > findByPropertys( String[] propertyNames , Object[] values );
	
	// 通过车辆id获取订单
	public List< Map< String , Object >> getOrdersByTruckId( int truckId );
	
	// 获取已配载车辆
	public Map< String , Object > getLoadedTrucks( int userId , String vcCarNo );
	
	// 通过headId获取配载的订单
	public Map< String , Object > getOrdersByHid( int headId );
	
	// 保存追加配载
	public Map< String , Object > saveAppendLoading( String[] strs , int headId )
	        throws IllegalAccessException , InvocationTargetException;
}
