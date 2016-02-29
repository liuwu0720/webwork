package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TDriver;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.model.TTruckType;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * 
 * @Description: 车辆司机Service
 * @author chenbin
 * @date 2014-8-11 下午1:26:32
 * @version V1.0
 */
public interface ITruckDriverService
{
	public TTruckDriver get( Integer id );
	
	public void update( TTruckDriver entity );
	
	public void updateCleanBefore( TTruckDriver entity );
	
	public void save( TTruckDriver entity );
	
	public void saveDriver( TDriver entity );
	
	public void saveOrUpdateTDriver( TDriver entity );
	
	public TTruckType getTTruckType( Integer id );
	
	public TDriver getDriver( Integer id );
	
	public void saveTruckDriverLlink( TTruckDriverLlink entity );
	
	public List< TDriver > getDriversByTruckID( int truckID );
	
	public void saveOrUpdate( TTruckDriver entity );
	
	public void delete( TTruckDriver entity );
	
	public void deleteByKey( Integer id );
	
	public List< TTruckDriver > loadAll();
	
	public abstract List< TTruckDriver > findAll( Page page );
	
	public List< TTruckType > getAllTruckType();
	
	public void saveAndsaveUsers( String[] carnos , String[] drivernames ,
	        String[] drivertels , TUser us , int params );
	
	public void saveAndsaveUser( int driverID );
	
	/**
	 * 
	 * @Description: 根据对象的属性和值 查询 并分页
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return List<TArkilometer> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午7:00:05
	 */
	public List< TTruckDriver > findByPropertys( String[] propertyNames ,
	        Object[] values , Page page );
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	public List< TTruckDriver > findByPropertys( String[] propertyNames , Object[] values );
	
	public int getCountSQL( String sql );
	
	public List< String[] > getDateBySQL( String sql , Page page );
	
	public Object getDateBySQL( String sql );
	
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
	/**
	 * 
	 * @Description: 根据车辆信息 获取主驾信息
	 * @param truckID
	 * @return TDriver 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-30 下午5:33:40
	 */
	public TDriver getMainDriver( Integer truckID );
	
	/**
	 * 
	 * @Description: 根据车辆ID 获取副驾信息
	 * @param truckID
	 * @return TDriver 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-30 下午5:33:54
	 */
	public TDriver getcopilotDriver( Integer truckID );
	
	/**
	 * @Description: 修改车辆的主驾 副驾信息
	 * @param truckID
	 *            车辆ID
	 * @param mainDriverID
	 *            主驾司机ID
	 * @param copilotDriverID
	 *            副驾司机ID void 返回值描述
	 * @author chenbin
	 * @create_date 2014-10-8 下午2:04:42
	 */
	public void updateMainDeiver( Integer truckID , Integer mainDriverID ,
	        Integer copilotDriverID );
	
	/**
	 * 
	 * @Description: 取消车辆和司机的关联
	 * @param truckID
	 *            void 返回值描述
	 * @author chenbin
	 * @param integer
	 * @create_date 2014-10-8 下午3:56:51
	 */
	public void delTruckDriverLink( Integer truckID , Integer integer );
	
	/**
	 * 
	 * @Description: 删除车辆信息
	 * @param truckID
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-10-8 下午3:56:51
	 */
	public void delTruckDriver( Integer truckID );
	
	/**
	 * 根据分供方编号，获得该分供方下面的所有车辆信息
	 * 
	 * @param subno
	 * @return
	 */
	public List< Map< String , Object > > getTruckDriverBySubno( String subno );
	
	/**
	 * 根据司机档案id，获得和他有关的车辆
	 * 
	 * @param driverId
	 * @return
	 */
	public List< Map< String , Object > > getTruckDriverByDriverId( int driverId );
	
	/**
	 * @Description: TODO(检查当前插入的司机信息是否存在相同的)
	 * @param sql
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-22 下午5:23:18
	 */
	public String findIfExistSame( String sql );
	
	/**
	 * 通过分供方编号，获得该分供方下所有有效的司机
	 * 
	 * @param subno
	 * @return
	 */
	public List< TDriver > getDriverBysubno( String subno );
	
	public void delTruck( Integer truckID , Integer userId );
	
	public Map< String , Object > getAllTrucksByApp( Page page , String vcSubno );
	
	public Map< String , Object > getTruckById( int truckId );
	
	public void findByProperty( String propertyName , Object value );
	
	/**
	 * @Description: TODO(检查司机手机号码是否重复)
	 * @param driver
	 * @return String 返回值描述
	 * @author liuwu
	 * @param subno
	 * @create_date 2015-5-27 下午2:09:42
	 */
	public String checkIfExiste( TDriver driver , String subno );
	
	/**
	 * @Description 删除司机与车辆的关联
	 * @param position
	 *            (职位（1、主驾，2、副驾、机动）)
	 */
	public void deleteByProperties( int truckId , int position );
	
	public List< Map< String , Object >> getOrderlistByHeadId( int headId );
	
	// 通过发运主表id获取检查装车详情
	public List< Map< String , Object >> getCheckLoadingById( int headId );
	
	// 通过发运主表id获取订单客户id
	public List< Map< String , Object >> getCustomerIdByHeadId( int headId );
	
	// 激活车辆，设置为可用
	public void saveTruckEnabledStatus( int id );
}
