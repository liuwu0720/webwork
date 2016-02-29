package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IDriverDao;
import com.clt.sub.dao.IShiplineDao;
import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.sub.dao.ITruckDriverDao;
import com.clt.sub.dao.ITruckDriverLinkDao;
import com.clt.sub.dao.ITruckTypeDao;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.model.TTruckType;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.dao.IArchiveTypeDao;
import com.clt.systemmanger.dao.IMsgRecordDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.PushUtils;
import com.clt.util.SystemConstants;

@Service
public class TruckDriverServiceImp implements ITruckDriverService
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	ITruckDriverDao truckDao;
	@Autowired
	IArchiveTypeDao archiveTypeDao;
	@Autowired
	IUserDao userDao;
	@Autowired
	ISubsuppliersDao subDao;
	@Autowired
	IDriverDao driverDao;
	@Autowired
	ITruckDriverLinkDao truckDriverLinkDao;
	@Autowired
	ITruckTypeDao truckTypeDao;
	@Autowired
	IShiplineDao lineDao;
	@Autowired
	IMsgRecordDao iMsgRecordDao;
	
	public TTruckDriver get( Integer id )
	{
		// TODO Auto-generated method stub
		return truckDao.get( id );
	}
	
	public void update( TTruckDriver entity )
	{
		truckDao.update( entity );
	}
	
	public void updateCleanBefore( TTruckDriver entity )
	{
		truckDao.updateCleanBefore( entity );
	}
	
	public void save( TTruckDriver entity )
	{
		// TODO Auto-generated method stub
		truckDao.save( entity );
	}
	
	public void saveOrUpdate( TTruckDriver entity )
	{
		// TODO Auto-generated method stub
		truckDao.saveOrUpdate( entity );
	}
	
	public void delete( TTruckDriver entity )
	{
		// TODO Auto-generated method stub
		truckDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		truckDao.deleteByKey( id );
	}
	
	public List< TTruckDriver > loadAll()
	{
		// TODO Auto-generated method stub
		return truckDao.loadAll();
	}
	
	public List< TTruckDriver > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return truckDao.findAll( page );
	}
	
	/**
	 * @Description: 批量增加分供方的司机车辆信息
	 * @return String 跳转的页面
	 * @throws
	 */
	public void saveAndsaveUsers( String[] carnos , String[] drivernames ,
	        String[] drivertels , TUser us , int params )
	{
		int archid = us.getIArchiveType();
		
		// 档案类型等于分供方
		if ( archid == SystemConstants.SYS_TARCHIVE_SUB )
		{
			
			TSubsuppliers sub = subDao.get( us.getiArchive() );
			
			for ( int i = 0 ; i < carnos.length ; i++ )
			{
				TTruckDriver driver = new TTruckDriver();
				driver.setNEnable( 0 );
				driver.setVcCarNo( carnos[i] );
				// driver.setVcDriverName( drivernames[i] );
				// driver.setVcDriverTel( drivertels[i] );
				driver.setVcSubno( sub.getVcSubno() );
				truckDao.save( driver );
				
				if ( params == 1 )
				{
					TUser tuser = new TUser();
					tuser.setNEnable( 0 );
					tuser.setDtAddtime( new Date() );
					tuser.setIArchiveType( SystemConstants.SYS_TARCHIVE_DRIVER );
					tuser.setiArchive( driver.getId() );
					// 司机的手机号为登陆用户名 密码为123456 MD5加密
					// tuser.setVcAccount( driver.getVcDriverTel() );
					tuser.setVcPassword( SystemConstants.SYS_TARCHIVE_DRIVER_PASSWORD );
					userDao.save( tuser );
				}
				
			}
			
		}
		else
		{
			
		}
		
	}
	
	/**
	 * @Description: 单独开通分供方的司机账号
	 * @return
	 * 
	 * @throws
	 */
	public void saveAndsaveUser( int driverID )
	{
		TTruckDriver drive = truckDao.get( driverID );
		
		TUser tuser = new TUser();
		tuser.setNEnable( 0 );
		tuser.setDtAddtime( new Date() );
		tuser.setIArchiveType( SystemConstants.SYS_TARCHIVE_DRIVER );
		tuser.setiArchive( drive.getId() );
		// 司机的手机号为登陆用户名 密码为123456 MD5加密
		// tuser.setVcAccount( drive.getVcDriverTel() );
		tuser.setVcPassword( SystemConstants.SYS_TARCHIVE_DRIVER_PASSWORD );
		userDao.save( tuser );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-16 下午7:37:44
	 */
	public List< TTruckDriver > findByPropertys( String[] propertyNames ,
	        Object[] values , Page page )
	{
		return truckDao.findByPropertys( propertyNames , values , page );
	}
	
	/**
	 * 根据多个参数查询
	 * 
	 * @param propertyNames
	 * @param values
	 * @return
	 */
	public List< TTruckDriver > findByPropertys( String[] propertyNames , Object[] values )
	{
		return truckDao.findByPropertys( propertyNames , values );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-5 下午1:51:57
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return truckDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-5 下午1:51:57
	 */
	public int getCountSQL( String sql )
	{
		// TODO Auto-generated method stub
		return truckDao.getCountSQL( sql );
	}
	
	public List< TTruckType > getAllTruckType()
	{
		// TODO Auto-generated method stub
		return truckTypeDao.findAll();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-9-28 下午3:03:24
	 */
	public TDriver getDriver( Integer id )
	{
		// TODO Auto-generated method stub
		return driverDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-9-28 下午3:03:24
	 */
	public void saveDriver( TDriver entity )
	{
		// TODO Auto-generated method stub
		driverDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-9-28 下午3:07:08
	 */
	public void saveTruckDriverLlink( TTruckDriverLlink entity )
	{
		// TODO Auto-generated method stub
		truckDriverLinkDao.save( entity );
	}
	
	/**
	 * @Description: 获取司机信息 根据车辆ID
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-9-28 下午3:07:08
	 */
	public List< TDriver > getDriversByTruckID( int truckID )
	{
		
		String hql = " from TTruckDriverLlink driverlink where driverlink.NEnable="
		        + SystemConstants.SYS_ENABLE + " and driverlink.ITruckID= " + truckID;
		
		List< TTruckDriverLlink > drilinks = truckDriverLinkDao.find( hql );
		List< TDriver > driverlist = new ArrayList< TDriver >();
		for ( TTruckDriverLlink trlink : drilinks )
		{
			driverlist.add( driverDao.get( trlink.getIDriverID() ) );
		}
		return driverlist;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-30 上午9:40:35
	 */
	public List< String[] > getDateBySQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return truckDao.getDateBySQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-30 上午9:49:06
	 */
	public Object getDateBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return truckDao.getDateBySQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-30 下午3:10:02
	 */
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return truckDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-30 下午5:05:46
	 */
	public TTruckType getTTruckType( Integer id )
	{
		// TODO Auto-generated method stub
		return truckTypeDao.get( id );
	}
	
	// 车辆主驾司机信息
	public TDriver getMainDriver( Integer truckID )
	{
		String sql = "  select driver.id from t_driver driver,t_truck_driver truck,t_truck_driver_link drilink where drilink.i_truck = truck.id and drilink.i_driver= driver.id and drilink.n_position_type=1 and truck.id="
		        + truckID;
		Object mainid = getDateBySQL( sql );
		if ( mainid != null )
		{
			System.out.println( " 主驾司机ID " + mainid );
			TDriver mainDriver = getDriver( Integer.parseInt( mainid + "" ) );
			System.out.println( " 主驾司机姓名 " + mainDriver.getVcDriverName() );
			
			return mainDriver;
		}
		else
		{
			return null;
		}
	}
	
	// 车辆副驾司机信息
	public TDriver getcopilotDriver( Integer truckID )
	{
		String sql = "  select driver.id from t_driver driver,t_truck_driver truck,t_truck_driver_link drilink where drilink.i_truck = truck.id and drilink.i_driver= driver.id and drilink.n_position_type=2 and truck.id="
		        + truckID;
		Object copilotID = getDateBySQL( sql );
		if ( copilotID != null )
		{
			System.out.println( " 副驾司机ID " + copilotID );
			TDriver copilotDriver = getDriver( Integer.parseInt( copilotID + "" ) );
			System.out.println( " 副驾司机姓名 " + copilotDriver.getVcDriverName() );
			return copilotDriver;
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param truckID
	 * @param mainDriverID
	 * @param copilotDriverID
	 * @author chenbin
	 * @create_date 2014-10-8 下午2:05:10
	 */
	public void updateMainDeiver( Integer truckID , Integer mainDriverID ,
	        Integer copilotDriverID )
	{
		String hql = " from TTruckDriverLlink truckLink where  truckLink.ITruckID="
		        + truckID;
		// 主驾司机ID
		if ( mainDriverID != null )
		{
			hql += " and truckLink.NPositionType =1 ";
			
			List< TTruckDriverLlink > linklist = truckDriverLinkDao.find( hql );
			TTruckDriverLlink drilink = null;
			if ( linklist.size() > 0 )
			{
				drilink = linklist.get( 0 );
				drilink.setIDriverID( mainDriverID );
				truckDriverLinkDao.update( drilink );
			}
			else
			{
				drilink = new TTruckDriverLlink();
				drilink.setIDriverID( mainDriverID );
				drilink.setITruckID( truckID );
				drilink.setNPositionType( 1 );
				truckDriverLinkDao.save( drilink );
			}
			
		}
		// 副驾司机ID
		if ( copilotDriverID != null )
		{
			hql = " from TTruckDriverLlink truckLink where  truckLink.ITruckID= "
			        + truckID + " and truckLink.NPositionType =2 ";
			
			List< TTruckDriverLlink > linklist = truckDriverLinkDao.find( hql );
			TTruckDriverLlink drilink = null;
			if ( linklist.size() > 0 )
			{
				drilink = linklist.get( 0 );
				drilink.setIDriverID( copilotDriverID );
				truckDriverLinkDao.update( drilink );
			}
			else
			{
				drilink = new TTruckDriverLlink();
				drilink.setIDriverID( copilotDriverID );
				drilink.setITruckID( truckID );
				drilink.setNPositionType( 2 );
				truckDriverLinkDao.save( drilink );
			}
		}
	}
	
	/**
	 * @Description: 取消车辆和司机的关联
	 * @param truckID
	 *            车辆ID
	 * @author chenbin
	 * @param userId
	 * @create_date 2014-10-8 下午3:57:17
	 */
	public void delTruckDriverLink( Integer truckID , Integer userId )
	{
		// TODO Auto-generated method stub
		String hql = " from TTruckDriverLlink truckLink where  truckLink.ITruckID="
		        + truckID;
		TTruckDriver truckDriver = truckDao.get( truckID );// 拖车信息
		List< TTruckDriverLlink > linklist = truckDriverLinkDao.find( hql );
		TUser user = userDao.get( userId );
		if ( CollectionUtils.isNotEmpty( linklist ) )
		{
			for ( TTruckDriverLlink tTruckDriverLlink : linklist )
			{
				// tTruckDriverLlink.setNEnable( SystemConstants.SYS_DISABLE );
				truckDriverLinkDao.delete( tTruckDriverLlink );
				
				/**
				 * 消息推送
				 */
				
				String[] properties = { "IArchiveType" , "iArchive" , "NEnable" };
				// 主驾
				if ( tTruckDriverLlink.getNPositionType() == 1 )
				{
					Object[] mainValues = { SystemConstants.SYS_TARCHIVE_DRIVER ,
					        tTruckDriverLlink.getIDriverID() , SystemConstants.SYS_ENABLE };// 主驾司机的参数
					List< TUser > mainUsers = userDao.findByPropertys( properties ,
					        mainValues );// 主驾司机
					Map< String , String > mainMap = new HashMap< String , String >();
					
					// 主驾司机消息推送
					mainMap.put( "driverId" , tTruckDriverLlink.getIDriverID() + "" );
					mainMap.put( "msgType" , "9" );
					if ( mainUsers.size() > 0 )
					{
						PushUtils pUtils1 = new PushUtils( "拖车主驾取消!" , mainUsers.get( 0 )
						        .getVcUsername()
						        + "你被取消了" + truckDriver.getVcCarNo() + "主驾！" , mainUsers ,
						        "com.unlcn.driver.mine.MyInfoDetailActivity" , mainMap );
						pUtils1.run();
						// 保存消息记录
						TMsgRecord tMsgRecord = new TMsgRecord();
						tMsgRecord.setIUser( userId );// 添加人ID
						tMsgRecord.setVcAdduser( user.getVcAccount() );
						tMsgRecord.setIUserAccept( mainUsers.get( 0 ).getId() );
						tMsgRecord.setNMsgType( 1 );// 单发
						tMsgRecord.setVcContent( "你补取消了" + truckDriver.getVcCarNo()
						        + "主驾！" );
						tMsgRecord.setVcTitle( "拖车主驾取消!" );
						iMsgRecordDao.save( tMsgRecord );
					}
				}
				else if ( tTruckDriverLlink.getNPositionType() == 2 )
				{
					Object[] copilotValues = { SystemConstants.SYS_TARCHIVE_DRIVER ,
					        tTruckDriverLlink.getIDriverID() , SystemConstants.SYS_ENABLE };// 副驾司机的参数
					
					List< TUser > copilotUsers = userDao.findByPropertys( properties ,
					        copilotValues );// 副驾司机
					Map< String , String > copilotMap = new HashMap< String , String >();
					
					if ( copilotUsers.size() > 0 )
					{
						// 副驾司机消息推送
						copilotMap.put( "driverId" , tTruckDriverLlink.getIDriverID()
						        + "" );
						copilotMap.put( "msgType" , "9" );
						PushUtils pUtils2 = new PushUtils( "拖车副驾取消！" , copilotUsers.get(
						        0 ).getVcUsername()
						        + "你被取消了" + truckDriver.getVcCarNo() + "副驾！" ,
						        copilotUsers ,
						        "com.unlcn.driver.mine.MyInfoDetailActivity" , copilotMap );
						pUtils2.run();
						// 保存消息记录
						TMsgRecord tMsgRecord = new TMsgRecord();
						tMsgRecord.setIUser( userId );// 添加人ID
						tMsgRecord.setVcAdduser( user.getVcAccount() );
						tMsgRecord.setIUserAccept( copilotUsers.get( 0 ).getId() );
						tMsgRecord.setNMsgType( 1 );// 单发
						tMsgRecord.setVcContent( "你现在被取消了" + truckDriver.getVcCarNo()
						        + "副驾！" );
						tMsgRecord.setVcTitle( "拖车副驾取消！" );
						iMsgRecordDao.save( tMsgRecord );
					}
				}
				
			}
		}
		
	}
	
	/**
	 * @Description:删除司机与车辆的关联
	 * @param: driverId
	 * @author :chengwzh
	 * @date:2015/5/7 13:34
	 */
	public void delDriverTruckLink( Integer driverId )
	{
		String hql = " from TTruckDriverLlink truckLink where  truckLink.IDriverID="
		        + driverId;
		
		List< TTruckDriverLlink > linklist = truckDriverLinkDao.find( hql );
		truckDriverLinkDao.deleteAll( linklist );
	}
	
	/**
	 * @Description: 删除车辆信息
	 * @param truckID
	 * @author chenbin
	 * @create_date 2014-10-8 下午5:18:13
	 */
	public void delTruck( Integer truckID , Integer userId )
	{
		// 删除该车辆信息和司机的关联
		delTruckDriverLink( truckID , userId );
		
		TTruckDriver truck = truckDao.get( truckID );
		System.out.println( "truck:" + truck.getVcCarNo() );
		truck.setNEnable( SystemConstants.SYS_DISABLE );
		truckDao.update( truck );
	}
	
	/**
	 * Description :删除司机信息
	 * 
	 * @param truckID
	 * @author chengwzh
	 * @create date:2015/5/7
	 */
	public void delTruckDriver( Integer driverID )
	{
		// 删除该司机与车辆的关联
		delDriverTruckLink( driverID );
		
		TDriver driver = ( TDriver ) driverDao.get( driverID );
		System.out.println( "DriverName:" + driver.getVcDriverName() );
		driver.setNEnable( SystemConstants.SYS_DISABLE );
		driverDao.update( driver );
		
	}
	
	/**
	 * @Description 删除司机与车辆的关联
	 * @param position
	 *            (职位（1、主驾，2、副驾、机动）)
	 */
	public void deleteByProperties( int truckId , int position )
	{
		String sql = "delete from t_truck_driver_link where I_TRUCK=" + truckId
		        + " and N_POSITION_TYPE=" + position;
		jdbcTemplate.update( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-9 上午9:59:21
	 */
	public void saveOrUpdateTDriver( TDriver entity )
	{
		// TODO Auto-generated method stub
		driverDao.saveOrUpdate( entity );
	}
	
	/**
	 * 根据分供方编号，获得该分供方下面的所有车辆信息
	 * 
	 * @param subno
	 * @return
	 */
	public List< Map< String , Object > > getTruckDriverBySubno( String subno )
	{
		List< TTruckDriver > trucks = truckDao.findByProperty( "vcSubno" , subno );
		if ( CollectionUtils.isNotEmpty( trucks ) )
		{
			List< Map< String , Object > > list = new ArrayList< Map< String , Object > >();
			for ( TTruckDriver truck : trucks )
			{
				Map< String , Object > map = new HashMap< String , Object >();
				map.put( "id" , truck.getId() );
				map.put( "carNo" , truck.getVcCarNo() );
				list.add( map );
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 根据司机档案id，获得和他有关的车辆
	 * 
	 * @param driverId
	 * @return
	 */
	public List< Map< String , Object > > getTruckDriverByDriverId( int driverId )
	{
		// return truckDao.findByProperty( "vcSubno" , driverId );
		List< TTruckDriverLlink > truckDriverLinks = truckDriverLinkDao.findByProperty(
		        "IDriverID" , driverId );
		if ( CollectionUtils.isNotEmpty( truckDriverLinks ) )
		{
			List< Map< String , Object > > list = new ArrayList< Map< String , Object > >();
			for ( TTruckDriverLlink link : truckDriverLinks )
			{
				TTruckDriver tTruckDriver = truckDao.get( link.getITruckID() );
				Map< String , Object > map = new HashMap< String , Object >();
				map.put( "id" , tTruckDriver.getId() );
				map.put( "carNo" , tTruckDriver.getVcCarNo() );
				list.add( map );
			}
			return list;
		}
		return null;
	}
	
	/**
	 * @Description: TODO(检查当前插入的司机信息是否存在相同的))
	 * @param sql
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-22 下午5:23:53
	 */
	public String findIfExistSame( String sql )
	{
		String message = driverDao.findIfExistSame( sql );
		return message;
	}
	
	/**
	 * 通过分供方编号，获得该分供方下所有有效的司机
	 * 
	 * @param subno
	 * @return
	 */
	public List< TDriver > getDriverBysubno( String subno )
	{
		List< TDriver > drivers = driverDao.findByPropertys( new String[] { "vcSubno" ,
		        "NEnable" } , new Object[] { subno , SystemConstants.SYS_ENABLE } );
		if ( CollectionUtils.isNotEmpty( drivers ) )
		{
			return drivers;
		}
		return null;
	}
	
	/**
	 * @Description 通过分供方编号获取所有车辆信息（手机端）
	 * @author chengwzh
	 * @date 2015/5/11 16:34
	 */
	public Map< String , Object > getAllTrucksByApp( Page page , String vcSubno )
	{
		String hql = "select d.id, d.VC_CAR_NO, t.VC_TYPE_NAME, d.N_LENGTH, d.N_WIDTH, d.N_HEIGHT"
		        + " from t_truck_driver d, t_truck_type t"
		        + " where d.I_TRUCK_TYPE = t.id and d.N_ENABLE="
		        + SystemConstants.SYS_ENABLE
		        + " and d.VC_SUBNO='"
		        + vcSubno
		        + "' order by d.id desc";
		Map< String , Object > result = truckDao.getSpringSQL( hql , page );
		List< Map< String , Object >> rows = ( List< Map< String , Object >> ) result
		        .get( "rows" );
		for ( Map< String , Object > row : rows )
		{
			Integer id = Integer.parseInt( ( row.get( "ID" ).toString() ) );
			// System.out.println( "id:" + id );
			TDriver mainDriver = getMainDriver( id );// 根据truckId查询主驾
			String mainDriverName = null;
			if ( mainDriver != null )
			{
				mainDriverName = mainDriver.getVcDriverName();
			}
			else
			{
				mainDriverName = "暂无";
			}
			row.put( "mainDriverName" , mainDriverName );
			TDriver copilotDriver = getcopilotDriver( id );// 根据truckId查询副驾
			String copilotDriverName = null;
			if ( copilotDriver != null )
			{
				copilotDriverName = copilotDriver.getVcDriverName();
			}
			else
			{
				copilotDriverName = "暂无";
			}
			row.put( "copilotDriverName" , copilotDriverName );
			System.out.println( mainDriverName + ":" + copilotDriverName );
		}
		result.put( "rows" , rows );
		return result;
	}
	
	public void findByProperty( String propertyName , Object value )
	{
		truckDao.findByProperty( propertyName , value );
	}
	
	/**
	 * @Description 通过truckId获取车辆详细信息(手机端)
	 * @param vcCarNo
	 * @author chengwzh
	 * @date 2015/5/12
	 */
	public Map< String , Object > getTruckById( int truckId )
	{
		String sql = "select d.id, d.VC_CAR_NO,d.n_enable,d.vc_subno,d.i_truck_type,d.n_length,"
		        + "d.n_width,d.n_height,d.n_self_weight,d.n_loan_weight,d.n_status,"
		        + "d.vc_shipno,d.vc_driverno, t.VC_TYPE_NAME"
		        + " from t_truck_driver d, t_truck_type t"
		        + " where d.I_TRUCK_TYPE = t.id and d.N_ENABLE ="
		        + SystemConstants.SYS_ENABLE + " and d.id=" + truckId;
		// 根据车牌号获取的只有一条数据
		List< Map< String , Object >> trucks = jdbcTemplate.queryForList( sql );
		if ( CollectionUtils.isEmpty( trucks ) )
		{
			return null;
		}
		Map< String , Object > truck = trucks.get( 0 );
		TDriver mainDriver = getMainDriver( truckId );
		String mainDriverName = null;
		if ( mainDriver == null )
		{
			mainDriverName = "暂无";
		}
		else
		{
			mainDriverName = mainDriver.getVcDriverName();// 获取主驾名字
			int driverId1 = mainDriver.getId();
			truck.put( "driverId1" , driverId1 );
		}
		truck.put( "mainDriverName" , mainDriverName );
		TDriver copilotDriver = getcopilotDriver( truckId );
		String copilotDriverName = null;
		if ( copilotDriver == null )
		{
			copilotDriverName = "暂无";
		}
		else
		{
			copilotDriverName = copilotDriver.getVcDriverName();// 获取副驾名字
			int driverId2 = copilotDriver.getId();
			truck.put( "driverId2" , driverId2 );
		}
		truck.put( "copilotDriverName" , copilotDriverName );
		return truck;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param driver
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-27 下午2:10:26
	 */
	public String checkIfExiste( TDriver driver , String subno )
	{
		String[] propertyNames = { "vcDriverTel" , "NEnable" , "vcSubno" };
		Object[] values = { driver.getVcDriverTel() , SystemConstants.SYS_ENABLE , subno };
		List< TDriver > tDrivers = driverDao.findByPropertys( propertyNames , values );
		if ( tDrivers.size() > 0 )
		{
			return "司机【" + driver.getVcDriverName() + "】手机号【" + driver.getVcDriverTel()
			        + "】已注册 ！";
		}
		else
		{
			return "success";
		}
		
	}
	
	// 通过发运主表id获取订单列表
	public List< Map< String , Object >> getOrderlistByHeadId( int headId )
	{
		String sql = "select o.id,o.vc_car_name,o.n_total_car,o.vc_start_city,o.vc_dest_city,o.vc_receive_address"
		        + " from t_order o,t_shipline l where o.id=l.i_order_id"
		        + " and l.n_enable="
		        + SystemConstants.SYS_ENABLE
		        + " and l.i_shiphead="
		        + headId;
		List< Map< String , Object >> result = jdbcTemplate.queryForList( sql );
		return result;
	}
	
	// 通过发运主表id获取订单客户id
	public List< Map< String , Object >> getCustomerIdByHeadId( int headId )
	{
		String sql = "select distinct o.I_CUSTOMER_ID"
		        + " from t_order o,t_shipline l where o.id=l.i_order_id"
		        + " and l.n_enable=" + SystemConstants.SYS_ENABLE + " and l.i_shiphead="
		        + headId;
		List< Map< String , Object >> result = jdbcTemplate.queryForList( sql );
		return result;
	}
	
	// 通过发运主表id获取检查装车详情
	public List< Map< String , Object >> getCheckLoadingById( int headId )
	{
		String sql = "select o.id,o.vc_car_name,o.n_total_car"
		        + " from t_order o,t_shipline l where  o.id=l.i_order_id"
		        + " and l.n_enable=" + SystemConstants.SYS_ENABLE + " and l.i_shiphead="
		        + headId;
		List< Map< String , Object >> result = jdbcTemplate.queryForList( sql );
		return result;
	}
	
	// 激活车辆，设置为可用
	public void saveTruckEnabledStatus( int id )
	{
		// 车辆设置为可用
		TTruckDriver truck = get( id );
		if ( truck.getNStatus() != 0 )
		{
			truck.setNStatus( SystemConstants.SYS_ENABLE );
			update( truck );
		}
	}
}
