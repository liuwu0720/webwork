package com.clt.sub.service.imp;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clt.common.UserSession;
import com.clt.sub.dao.IArkilometerDao;
import com.clt.sub.dao.ICustomerDao;
import com.clt.sub.dao.IDriverDao;
import com.clt.sub.dao.IOrderDao;
import com.clt.sub.dao.IShipStatusDao;
import com.clt.sub.dao.IShipheadDao;
import com.clt.sub.dao.IShiplineDao;
import com.clt.sub.dao.ISubCarStyleDao;
import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.sub.dao.ITruckDriverDao;
import com.clt.sub.dao.ITruckDriverLinkDao;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TShipline;
import com.clt.sub.model.TSubCarStyle;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.service.IShipheadService;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IMsgRecordService;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.PushUtils;
import com.clt.util.SystemConstants;

@Service
public class ShipheadServiceImp implements IShipheadService
{
	
	@Autowired
	IShipheadDao shipheadDao;
	@Autowired
	ISubsuppliersDao subDao;
	@Autowired
	IOrderDao orderDao;
	@Autowired
	IArkilometerDao arkilomerDao;
	@Autowired
	IShiplineDao shiplineDao;
	@Autowired
	ITruckDriverDao driverDao;
	@Autowired
	IDriverDao iDriverDao;
	@Autowired
	private JdbcTemplate jdbcDao;
	@Autowired
	IUserDao userDao;
	
	@Autowired
	ITruckDriverLinkDao iTruckDriverLinkDao;
	
	@Autowired
	IShipStatusDao iShipStatusDao;
	@Autowired
	ICustomerDao iCustomerDao;
	@Autowired
	ISubCarStyleDao iSubCarStyleDao;
	
	@Autowired
	IMsgRecordService msgService;
	
	public TShiphead get( Integer id )
	{
		// TODO Auto-generated method stub
		return shipheadDao.get( id );
	}
	
	public void update( TShiphead entity )
	{
		// TODO Auto-generated method stub
		shipheadDao.update( entity );
	}
	
	public void updateCleanBefore( TShiphead entity )
	{
		shipheadDao.updateCleanBefore( entity );
	}
	
	public void save( TShiphead entity )
	{
		// TODO Auto-generated method stub
		shipheadDao.save( entity );
	}
	
	public void saveOrUpdate( TShiphead entity )
	{
		// TODO Auto-generated method stub
		shipheadDao.saveOrUpdate( entity );
	}
	
	public void delete( TShiphead entity )
	{
		// TODO Auto-generated method stub
		shipheadDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		shipheadDao.deleteByKey( id );
	}
	
	public List< TShiphead > loadAll()
	{
		// TODO Auto-generated method stub
		return shipheadDao.loadAll();
	}
	
	public List< TShiphead > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return shipheadDao.findAll( page );
	}
	
	public Map< String , Object > findByHelper( HqlHelper hql )
	{
		return shipheadDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * 
	 * @Description: TODO(订单配载)
	 * @param msgstrs
	 * @param driverID
	 * @return
	 * @author liuwu
	 * @create_date 2015-7-8 下午1:44:22
	 */
	@SuppressWarnings( "unchecked" )
	public String saveshipHead( String[] msgstrs , int driverID )
	{
		JSONObject msgobj = new JSONObject();
		msgobj.put( "resultCode" , "ok" );
		msgobj.put( "errorContent" , "保存成功" );
		
		TUser user = ( TUser ) UserSession.get( "user" );
		String subno = subDao.get( user.getiArchive() ).getVcSubno();
		// 拖车
		TTruckDriver truckDriver = driverDao.get( driverID );
		TShiphead head = new TShiphead();
		head.setNShipType( 0 );
		head.setNEnable( 0 );
		head.setDtCreate( new Date() );
		head.setVcSubno( subno );
		head.setVcShipno( getMaxShipNo() );
		head.setITruckId( driverID );
		head.setVcTruckName( truckDriver.getVcCarNo() );
		head.setVcDriverId( getDriverId( truckDriver ) );
		
		// 根据拖车ID找出对应的主驾司机 消息推送需要用到
		TDriver tDriver = findDriverLinkByTruckId( truckDriver.getId() );
		String[] properties = { "IArchiveType" , "iArchive" , "NEnable" };
		Object[] mainValues = { SystemConstants.SYS_TARCHIVE_DRIVER , tDriver.getId() ,
		        SystemConstants.SYS_ENABLE };
		List< TUser > users = userDao.findByPropertys( properties , mainValues );
		shipheadDao.save( head );
		String caiStr = "";// 拆分订单时的提示信息
		for ( int i = 0 ; i < msgstrs.length ; i++ )
		{
			String[] ords = msgstrs[i].split( "," );
			TOrder order = orderDao.getOrderByOrderNo( ords[0] );
			
			if ( order == null )
			{
				msgobj.put( "resultCode" , "no" );
				msgobj.put( "errorContent" , "获取订单出错，" + ords[0] + "  订单号有重复" );
				
				return msgobj.toString();
			}
			if ( order.getnLoad() == 1 )
			{
				msgobj.put( "resultCode" , "no" );
				msgobj.put( "errorContent" , "订单" + ords[0] + "已配载！ " );
				
				return msgobj.toString();
			}
			
			int count = Integer.parseInt( ords[1] );
			if ( count > order.getNTotalCar() )
			{
				System.out.println( "发运数量大于订单需求数量" );
				
				msgobj.put( "resultCode" , "no" );
				msgobj.put( "errorContent" , "发运数量大于订单需求数量" );
				
				return msgobj.toString();
				
			}
			String datestr = DateUtil.getDate( "yyyy/MM/dd" );
			// and to_char(ark.dtStart,'yyyy-MM-dd') between ? and ?
			String sql = " select getArkilomer('" + subno + "','"
			        + order.getVcStartCity() + "','" + order.getVcDestCity() + "','"
			        + datestr + "') nar from dual ";
			System.out.println( "sql" + sql );
			
			/*float nKilometer = 0;
			try
			{
				
				List artlist = arkilomerDao.getDateBySQL( sql , null );
				if ( CollectionUtils.isNotEmpty( artlist )
				        && artlist.get( 0 ) != null )
				{
					nKilometer = Float.parseFloat( artlist.get( 0 ) + "" );
				}
				else
				{
					msgobj.put( "resultCode" , "ok" );
					msgobj.put( "errorContent" , order.getVcStartCity() + " 到 "
					        + order.getVcDestCity() + " 公里数未维护" );
					
				}
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				// 保存发运指令 获取 订单起运地目的地 公里数 出错
				msgobj.put( "resultCode" , "no" );
				msgobj.put( "errorContent" , " 原因：" + e.getMessage() );
				return msgobj.toString();
			}*/
			if ( count < order.getNTotalCar() )// 拆单(如果订单的发运数量小于总数时原先的订单拆成2张子订单)
			{
				order.setNEnable( SystemConstants.SYS_DISABLE );// 原订单设为无效
				orderDao.saveOrUpdate( order );
				// shipheadDao.save( head );
				for ( int i1 = 0 ; i1 < 2 ; i1++ )
				{
					TOrder copyOrder = new TOrder();
					try
					{
						BeanUtils.copyProperties( copyOrder , order );
						copyOrder.setVcOrderno( copyOrder.getVcOrderno() + "_"
						        + ( i1 + 1 ) );
						copyOrder.setNShipedQty( count );
						copyOrder.setNEnable( SystemConstants.SYS_ENABLE );
						System.out.println( count + "--" + order.getNTotalCar() );
						
						float ratio1 = Float.parseFloat( count + "" )
						        / Float.parseFloat( order.getNTotalCar() + "" );
						System.out.println( "ratio1 = " + ratio1 );
						if ( order.getNTotalPrice() != null )
						{
							copyOrder.setNTotalPrice( ratio1 * order.getNTotalPrice() );
						}
						copyOrder.setnLoad( 1 );// 已配载
						if ( i1 == 1 )
						{
							
							copyOrder.setNShipedQty( order.getNTotalCar() - count );// 第二张子订单为余数
							
							if ( order.getNTotalPrice() != null )
							{
								copyOrder.setNTotalPrice( ( 1 - ratio1 )
								        * order.getNTotalPrice() );
								
							}
							copyOrder.setnLoad( 0 );// 未配载
						}
						copyOrder.setNTotalCar( copyOrder.getNShipedQty() );
						
						orderDao.save( copyOrder );
						if ( i1 == 0 )
						{
							TShipline line = new TShipline();
							line.setIOrderId( copyOrder.getId() );
							line.setIShiphead( head.getId() );
							line.setVcStartCity( copyOrder.getVcStartCity() );
							line.setVcDestCity( copyOrder.getVcDestCity() );
							line.setDtAdd( new Date() );
							// line.setNQty( count );
							line.setnShipQty( copyOrder.getNShipedQty() );
							// line.setNApkilometer( nKilometer );
							shiplineDao.save( line );
							TShipStatus tShipStatus = new TShipStatus();
							tShipStatus.setnOrderId( copyOrder.getId() );
							tShipStatus.setnLineId( line.getId() );
							tShipStatus.setVcAddUser( user.getVcAccount() );
							tShipStatus.setVcStatusNote( SystemConstants.VC_LOADING_TRUE );
							tShipStatus
							        .setVcStatusTag( SystemConstants.VC_LOADING_TRUE_TAG );
							tShipStatus.setnHeadId( head.getId() );
							iShipStatusDao.saveOrUpdate( tShipStatus );
							
							// 消息推送
							HashMap< String , String > map = new HashMap< String , String >();
							map.put( "vcLineId" , line.getId() + "" );
							map.put( "msgType" , "6" );
							PushUtils pushUtils = new PushUtils(
							        "你有新配载任务，请点击查看" ,
							        "你有来自" + user.getVcAccount() + "的订单配载信息" ,
							        users ,
							        "com.unlcn.driver.ordermanagement.DriverOrderArriveDialogActivity" ,
							        map );
							pushUtils.run();
							for ( TUser tUser : users )
							{
								// 保存消息记录
								TMsgRecord tMsgRecord = new TMsgRecord();
								tMsgRecord.setIUser( user.getId() );// 添加人ID
								tMsgRecord.setVcAdduser( user.getVcAccount() );// 添加人姓名
								tMsgRecord.setIUserAccept( tUser.getId() );// 接收人用户id
								tMsgRecord.setNMsgType( 1 );// 单发
								tMsgRecord.setVcContent( "来自" + user.getVcAccount()
								        + "的订单配载信息" );
								tMsgRecord.setVcTitle( "有新的订单指配，请点击查看" );
								msgService.save( tMsgRecord );
							}
						}
					}
					catch ( IllegalAccessException e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch ( InvocationTargetException e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch ( Exception e )
					{
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				caiStr += "原订单" + order.getVcOrderno() + "已经进行了拆分！ ";
			}
			else
			{
				// shipheadDao.save( head );
				// order.setNShipedQty( order.getNShipedQty() + count );
				order.setnLoad( 1 );// 改变状态为已配载
				order.setNShipedQty( count );
				orderDao.saveOrUpdate( order );
				TShipline line = new TShipline();
				line.setIOrderId( order.getId() );
				line.setIShiphead( head.getId() );
				line.setVcStartCity( order.getVcStartCity() );
				line.setVcDestCity( order.getVcDestCity() );
				line.setDtAdd( new Date() );
				// line.setNQty( count );
				line.setnShipQty( count );
				// line.setNApkilometer( nKilometer );
				shiplineDao.save( line );
				
				TShipStatus tShipStatus = new TShipStatus();
				tShipStatus.setnOrderId( order.getId() );
				tShipStatus.setnLineId( line.getId() );
				tShipStatus.setVcAddUser( user.getVcAccount() );
				tShipStatus.setVcStatusNote( SystemConstants.VC_LOADING_TRUE );
				tShipStatus.setVcStatusTag( SystemConstants.VC_LOADING_TRUE_TAG );
				tShipStatus.setnHeadId( head.getId() );
				iShipStatusDao.save( tShipStatus );
				
				// 消息推送
				HashMap< String , String > map = new HashMap< String , String >();
				map.put( "vcLineId" , line.getId() + "" );
				map.put( "msgType" , "6" );
				PushUtils pushUtils = new PushUtils(
				        "你有新配载任务，请点击查看" ,
				        "你有来自" + user.getVcAccount() + "的订单配载信息" ,
				        users ,
				        "com.unlcn.driver.ordermanagement.DriverOrderArriveDialogActivity" ,
				        map );
				pushUtils.run();
				for ( TUser tUser : users )
				{
					// 保存消息记录
					TMsgRecord tMsgRecord = new TMsgRecord();
					tMsgRecord.setIUser( user.getId() );// 添加人ID
					tMsgRecord.setVcAdduser( user.getVcAccount() );// 添加人姓名
					tMsgRecord.setIUserAccept( tUser.getId() );// 接收人用户id
					tMsgRecord.setNMsgType( 1 );// 单发
					tMsgRecord.setVcContent( "你有来自" + user.getVcAccount() + "的订单配载信息" );
					tMsgRecord.setVcTitle( "有新的订单指配，请点击查看" );
					msgService.save( tMsgRecord );
				}
			}
			truckDriver.setNStatus( 1 );// 状态改变，回单后改为0
			// truckDriver.setVcShipNo( head.getVcShipno() );
			driverDao.saveOrUpdate( truckDriver );
		}
		if ( caiStr != "" )
		{
			msgobj.put( "resultCode" , "ok" );
			msgobj.put( "errorContent" , caiStr );
		}
		
		return msgobj.toString();
	}
	
	/**
	 * @Description: TODO(根据拖车ID找出对应的主驾司机)
	 * @param id
	 * @return TDriver 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-25 下午4:35:49
	 */
	private TDriver findDriverLinkByTruckId( Integer id )
	{
		String[] propertyNames = { "NEnable" , "ITruckID" , "NPositionType" };
		Object[] values = { SystemConstants.SYS_ENABLE , id , 1 };
		List< TTruckDriverLlink > truckDriverLlinks = iTruckDriverLinkDao
		        .findByPropertys( propertyNames , values );
		TDriver tDriver = iDriverDao.get( truckDriverLlinks.get( 0 ).getIDriverID() );
		return tDriver;
	}
	
	/**
	 * @Description: TODO(通过拖车ID找出对应的主驾和副驾司机ID)
	 * @param truckDriver
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-3 下午5:03:27
	 */
	private String getDriverId( TTruckDriver truckDriver )
	{
		String[] propertyNames = { "NEnable" , "ITruckID" };
		Object[] values = { SystemConstants.SYS_ENABLE , truckDriver.getId() };
		List< TTruckDriverLlink > truckDriverLlinks = iTruckDriverLinkDao
		        .findByPropertys( propertyNames , values );
		List< TDriver > tDrivers = new ArrayList< TDriver >();
		for ( TTruckDriverLlink tLlink : truckDriverLlinks )
		{
			TDriver tDriver = iDriverDao.get( tLlink.getIDriverID() );
			tDrivers.add( tDriver );
		}
		String strIds = "";
		for ( TDriver tDriver : tDrivers )
		{
			strIds += tDriver.getId() + "" + ",";
		}
		System.out.println( "strIds = " + strIds );
		return strIds;
	}
	
	/**
	 * @Description: 生成发运指令 Z+yyMMdd+4位流水号
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午3:26:33
	 */
	public synchronized String getMaxShipNo()
	{
		
		List< TShiphead > orderlist = shipheadDao
		        .findAllAndOrderByProperty( "id" , false );
		String shipNo = "Z";
		String datestr = DateUtil.getDate( "yyMMdd" );
		DecimalFormat df = new DecimalFormat( "0000" );
		String str2 = "";
		if ( orderlist.size() == 0 )
		{
			str2 = df.format( Integer.parseInt( "1" ) );
			
		}
		else
		{
			TShiphead shead = orderlist.get( 0 );
			String str = shead.getVcShipno().substring( 7 , shead.getVcShipno().length() );
			str2 = df.format( Integer.parseInt( str ) + 1 );
		}
		
		shipNo += datestr + str2;
		System.out.println( "Max ShipNo " + shipNo );
		return shipNo;
	}
	
	/**
	 * 
	 * @Description: 入场确定 根据发运明细
	 * @param lineid
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:09:32
	 */
	public void EntranceByShipLineID( int[] lineids )
	{
		TUser user = ( TUser ) UserSession.get( "user" );
		for ( int lineid : lineids )
		{
			TShipline line = shiplineDao.get( lineid );
			/*line.setNEntrance( 0 );
			line.setDtEntrance( new Date() );
			line.setIEntranceUser( user.getId() );*/
			shiplineDao.update( line );
		}
		
	}
	
	/**
	 * @Description: 装车确定 根据发运明细
	 * @param lineids
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:14:11
	 */
	public void LoadByShipLineID( int[] lineids )
	{
		TUser user = ( TUser ) UserSession.get( "user" );
		for ( int lineid : lineids )
		{
			TShipline line = shiplineDao.get( lineid );
			/*line.setNLoad( 0 );
			line.setDtLoad( new Date() );
			line.setILoadUser( user.getId() );*/
			shiplineDao.update( line );
		}
		
	}
	
	/**
	 * @Description: 发车确定 根据发运明细
	 * @param lineids
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:14:11
	 */
	public void SendByShipLineID( int[] lineids )
	{
		TUser user = ( TUser ) UserSession.get( "user" );
		for ( int lineid : lineids )
		{
			TShipline line = shiplineDao.get( lineid );
			/*line.setNShip( 0 );
			line.setDtShip( new Date() );
			line.setIShipUser( user.getId() );*/
			shiplineDao.update( line );
		}
		TShipline line = shiplineDao.get( lineids[0] );
		TShiphead head = shipheadDao.get( line.getIShiphead() );
		
		String sql = " select count(*)  coun from TShipline line where line.IShiphead="
		        + head.getId() + " and line.NShip=0 ";
		int count1 = shiplineDao.getCountSQL( sql );
		
		sql = " select count(*)  coun from TShipline line where line.IShiphead="
		        + head.getId() + "  ";
		int count2 = shiplineDao.getCountSQL( sql );
		// 如果该发运指令已经全部发运确定则 发运指令所对应的车辆设置为不可用
		if ( count1 == count2 )
		{
			TTruckDriver driver = driverDao.get( head.getITruckId() );
			driver.setNStatus( 1 );
			driverDao.update( driver );
			
		}
		
	}
	
	// 保存发运明细
	public void saveDespatchInfo( String partype , String shipnos , int usid )
	{
		String[] strs = shipnos.split( "," );
		TUser tUser = userDao.get( usid );
		// 入场确定
		if ( partype.equals( "parEntrance" ) )
		{
			for ( String str : strs )
			{
				TShiphead head = shipheadDao.getShipHeadByshipNo( str );
				List< TShipline > shiplist = shiplineDao.findByProperty( "IShiphead" ,
				        head.getId() );
				for ( TShipline ship : shiplist )
				{
					/*ship.setDtEntrance( new Date() );
					ship.setIEntranceUser( usid );
					ship.setNEntrance( 0 );*/

					ship.setNCurrentStatus( SystemConstants.SYS_SUB_PARENTRANCE );
					TShipStatus tShipStatus = new TShipStatus();
					tShipStatus.setDtStatus( new Date() );
					tShipStatus.setnHeadId( ship.getIShiphead() );
					tShipStatus.setnLineId( ship.getId() );
					tShipStatus.setnOrderId( ship.getIOrderId() );
					tShipStatus.setVcAddUser( tUser.getVcAccount() );
					tShipStatus.setVcStatusNote( SystemConstants.VC_ENTRANCE_TRUE );// 入场
					tShipStatus.setVcStatusTag( SystemConstants.VC_ENTRANCE_TRUE_TAG );
					iShipStatusDao.saveOrUpdate( tShipStatus );
				}
				shiplineDao.saveOrUpdateAll( shiplist );
			}
		}
		// 装车确定
		else if ( partype.equals( "parload" ) )
		{
			for ( String str : strs )
			{
				TShiphead head = shipheadDao.getShipHeadByshipNo( str );
				List< TShipline > shiplist = shiplineDao.findByProperty( "IShiphead" ,
				        head.getId() );
				for ( TShipline ship : shiplist )
				{
					/*ship.setNLoad( 0 );
					ship.setDtLoad( new Date() );
					ship.setILoadUser( usid );*/
					ship.setNCurrentStatus( SystemConstants.SYS_SUB_PARLOAD );
					TShipStatus tShipStatus = new TShipStatus();
					tShipStatus.setDtStatus( new Date() );
					tShipStatus.setnHeadId( ship.getIShiphead() );
					tShipStatus.setnLineId( ship.getId() );
					tShipStatus.setnOrderId( ship.getIOrderId() );
					tShipStatus.setVcAddUser( tUser.getVcAccount() );
					tShipStatus.setVcStatusNote( SystemConstants.VC_LOAD_TRUE );// 装车
					tShipStatus.setVcStatusTag( SystemConstants.VC_LOAD_TRUE_TAG );
					iShipStatusDao.saveOrUpdate( tShipStatus );
				}
				shiplineDao.saveOrUpdateAll( shiplist );
			}
			
		}
		// 发运确定
		else if ( partype.equals( "parship" ) )
		{
			for ( String str : strs )
			{
				TShiphead head = shipheadDao.getShipHeadByshipNo( str );
				List< TShipline > shiplist = shiplineDao.findByProperty( "IShiphead" ,
				        head.getId() );
				for ( TShipline ship : shiplist )
				{
					/*ship.setNShip( 0 );
					ship.setDtShip( new Date() );
					ship.setIShipUser( usid );*/
					ship.setNCurrentStatus( SystemConstants.SYS_SUB_PARSHIP );
					TShipStatus tShipStatus = new TShipStatus();
					tShipStatus.setDtStatus( new Date() );
					tShipStatus.setnHeadId( ship.getIShiphead() );
					tShipStatus.setnLineId( ship.getId() );
					tShipStatus.setnOrderId( ship.getIOrderId() );
					tShipStatus.setVcAddUser( tUser.getVcAccount() );
					tShipStatus.setVcStatusNote( SystemConstants.VC_SHIP_TRUE );// 发运确定
					tShipStatus.setVcStatusTag( SystemConstants.VC_SHIP_TRUE_TAG );
					iShipStatusDao.saveOrUpdate( tShipStatus );
					
				}
				shiplineDao.saveOrUpdateAll( shiplist );
				// 车辆设置为不可用
				TTruckDriver driver = driverDao.get( head.getITruckId() );
				driver.setNStatus( 1 );
				driverDao.update( driver );
			}
		}
		// 运抵确定
		else if ( partype.equals( "parArrived" ) )
		{
			for ( String str : strs )
			{
				TShiphead head = shipheadDao.getShipHeadByshipNo( str );
				List< TShipline > shiplist = shiplineDao.findByProperty( "IShiphead" ,
				        head.getId() );
				for ( TShipline ship : shiplist )
				{
					/*	ship.setNArrived( 0 );
						ship.setDtArrived( new Date() );
						ship.setIArrivedUser( usid );*/
					ship.setNCurrentStatus( SystemConstants.SYS_SUB_PARARRIVED );
					TShipStatus tShipStatus = new TShipStatus();
					tShipStatus.setDtStatus( new Date() );
					tShipStatus.setnHeadId( ship.getIShiphead() );
					tShipStatus.setnLineId( ship.getId() );
					tShipStatus.setnOrderId( ship.getIOrderId() );
					tShipStatus.setVcAddUser( tUser.getVcAccount() );
					tShipStatus.setVcStatusNote( SystemConstants.VC_ARRIVED_TRUE );// 运抵确定
					tShipStatus.setVcStatusTag( SystemConstants.VC_ARRIVED_TRUE_TAG );
					iShipStatusDao.saveOrUpdate( tShipStatus );
				}
				shiplineDao.saveOrUpdateAll( shiplist );
			}
			
		}
		// 回单确认
		else if ( partype.equals( "parReturn" ) )
		{
			strs = shipnos.split( ";" );
			
			for ( String str : strs )
			{
				String[] shipstr = str.split( "," );
				TShipline ship = shiplineDao.get( Integer.parseInt( shipstr[0] ) );
				
				/*ship.setNReturn( 0 );
				ship.setDtReturn( new Date() );
				ship.setIReturnUser( usid );*/
				ship.setNQty( Integer.parseInt( shipstr[1] ) );
				ship.setNCurrentStatus( SystemConstants.SYS_SUB_PARRETURN );
				
				shiplineDao.update( ship );
				TShiphead head = shipheadDao.get( ship.getIShiphead() );
				// 车辆设置为可用
				TTruckDriver driver = driverDao.get( head.getITruckId() );
				if ( driver.getNStatus() != 0 )
				{
					driver.setNStatus( 0 );
					driverDao.update( driver );
				}
				TShipStatus tShipStatus = new TShipStatus();
				tShipStatus.setDtStatus( new Date() );
				tShipStatus.setnHeadId( ship.getIShiphead() );
				tShipStatus.setnLineId( ship.getId() );
				tShipStatus.setnOrderId( ship.getIOrderId() );
				tShipStatus.setVcAddUser( tUser.getVcAccount() );
				tShipStatus.setVcStatusNote( SystemConstants.VC_RETURN_TRUE );// 回单确认
				tShipStatus.setVcStatusTag( SystemConstants.VC_RETURN_TRUE_TAG );
				iShipStatusDao.saveOrUpdate( tShipStatus );
				// 回单数量若有改变，则订单表的订单总价应该要改掉
				TOrder order = orderDao.get( ship.getIOrderId() );
				if ( order.getNTotalCar() > 0 )
				{
					float ratio1 = Float.parseFloat( ship.getNQty() + "" )
					        / Float.parseFloat( order.getNTotalCar() + "" );
					if ( order.getNPayType().equals( 0 ) )// 现金支付
					{
						order.setNTotalPrice( ratio1 * order.getNTotalPrice() );
						orderDao.update( order );
					}
				}
				
			}
			
		}
	}
	
	/**
	 * @Description: 回单确定 根据发运
	 * @param lineids
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:14:11
	 */
	public void ReturnByShipLineID( String shipNo )
	{
		TUser user = ( TUser ) UserSession.get( "user" );
		
		List< TShiphead > headlist = shipheadDao.findByProperty( "vcShipno" , shipNo );
		if ( headlist.size() > 1 )
		{
			System.out.println( "发运指令 大于1   有错误" );
		}
		TShiphead head = headlist.get( 0 );
		List< TShipline > linelist = shiplineDao.findByProperty( "IShiphead" , head
		        .getId() );
		
		for ( TShipline ship : linelist )
		{
			/*	line.setNReturn( 0 );
				line.setDtReturn( new Date() );
				line.setIReturnUser( user.getId() );*/
			shiplineDao.update( ship );
			TShipStatus tShipStatus = new TShipStatus();
			tShipStatus.setDtStatus( new Date() );
			tShipStatus.setnHeadId( ship.getIShiphead() );
			tShipStatus.setnLineId( ship.getId() );
			tShipStatus.setnOrderId( ship.getIOrderId() );
			tShipStatus.setVcAddUser( user.getVcUsername() );
			tShipStatus.setVcStatusNote( SystemConstants.VC_RETURN_TRUE );// 回单确认
			tShipStatus.setVcStatusTag( SystemConstants.VC_RETURN_TRUE_TAG );
			iShipStatusDao.saveOrUpdate( tShipStatus );
		}
		// 回单确认后 修改该车辆的状态为可用
		TTruckDriver driver = driverDao.get( head.getITruckId() );
		driver.setNStatus( 0 );
		driverDao.update( driver );
	}
	
	/**
	 * 
	 * @Description: 获取该分供方所有的发运指令 未入场确定的指令
	 * @param subno
	 * @return List 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-19 下午6:57:22
	 */
	public List getAllShipdetailBySubno( String subno )
	{
		int status = 0;
		String partype = "";
		
		String sql = "  select head.id,head.vc_shipno,ord.vc_orderno,head.dt_create,d.vc_car_no,d.vc_driver_name,line.vc_start_city,line.vc_dest_city,line.n_ship_qty,sty.vc_car_style "
		        + " from t_shiphead head,t_shipline line,t_truck_driver d,t_order ord,t_sub_car_style sty "
		        + " where head.id=line.i_shiphead and head.i_truck_id = d.id and line.i_order_id=ord.id and ord.i_car_style=sty.id and line."
		        + partype + "=1 order by head.vc_shipno";
		List< String[] > dateslist = shipheadDao.getDateBySQL( sql , null );
		return dateslist;
	}
	
	/**
	 * @Description: 获取该分供方所有已回单的发运数据
	 * @param shipno
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-21 下午2:08:15
	 */
	public List getAllReturnShip( String subno )
	{
		String sql = " select head.vcShipno,head.dtCreate,dri.vcCarNo,dri.vcDriverName,dri.vcDriverTel,line.id,line.vcStartCity,line.vcDestCity,line.NShipQty,line.NApkilometer "
		        + "		from TShiphead  head ,TShipline line,TTruckDriver dri where head.id=line.IShiphead  and head.ITruckId=dri.id and line.NReturn=1 and head.vcSubno="
		        + subno;
		List< String[] > dateslist = shipheadDao.getDateBySQL( sql , null );
		
		return dateslist;
	}
	
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		
		return shipheadDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: 调度指令取消
	 * @param headids
	 *            shipheadID , 分隔
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-15 下午2:18:41
	 */
	public String saveShipInfoCancel( String headids )
	{
		String msgstr = "ok";
		TShiphead head = shipheadDao.get( Integer.parseInt( headids ) );
		
		String sql = "select head.id from t_shiphead head, t_shipline line where head.id=line.i_shiphead and head.id= "
		        + headids
		        + " group by line.n_current_status,head.id having  line.n_current_status < "
		        + SystemConstants.SYS_SUB_PARSHIP;
		
		int count1 = shiplineDao.getCountSQL( sql );
		if ( count1 > 0 )
		{
			List< TShipline > linelist = shiplineDao.findByProperty( "IShiphead" , head
			        .getId() );
			for ( TShipline line : linelist )
			{
				TOrder ord = orderDao.get( line.getIOrderId() );
				ord.setNShipedQty( 0 );
				ord.setnLoad( 0 );// 还原状态为未配载
				orderDao.update( ord );
			}
			
			head.setNEnable( SystemConstants.SYS_DISABLE );
			shipheadDao.update( head );
			// 释放车辆
			int truckId = head.getITruckId();
			TTruckDriver truck = driverDao.get( truckId );
			truck.setNStatus( 0 );
			driverDao.updateCleanBefore( truck );
		}
		else
		{
			// 该指令 状态已在发运之后了
			msgstr = "shiperror";
			return msgstr;
		}
		return msgstr;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-22 下午2:14:24
	 */
	public List< String[] > getDateBySQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return shipheadDao.getDateBySQL( sql , page );
	}
	
	/**
	 * 
	 * @Description: TODO(增加配载)
	 * @param strs
	 * @param driveID
	 * @param iHeadId
	 * @return
	 * @author liuwu
	 * @create_date 2015-7-8 下午1:56:20
	 */
	public String saveUpdateShipHead( String[] strs , int driveID , int iHeadId )
	{
		JSONObject msgobj = new JSONObject();
		msgobj.put( "resultCode" , "ok" );
		msgobj.put( "errorContent" , "保存成功" );
		
		TUser user = ( TUser ) UserSession.get( "user" );
		String subno = subDao.get( user.getiArchive() ).getVcSubno();
		// 拖车
		TTruckDriver truckDriver = driverDao.get( driveID );
		TShiphead head = shipheadDao.get( iHeadId );
		/*head.setNShipType( 0 );
		head.setNEnable( 0 );
		head.setDtCreate( new Date() );
		head.setVcSubno( subno );
		head.setVcShipno( getMaxShipNo() );
		head.setITruckId( driveID );
		head.setVcTruckName( truckDriver.getVcCarNo() );
		head.setVcDriverId( getDriverId( truckDriver ) );*/

		// 根据拖车ID找出对应的主驾司机 消息推送需要用到
		TDriver tDriver = findDriverLinkByTruckId( truckDriver.getId() );
		String[] properties = { "IArchiveType" , "iArchive" , "NEnable" };
		Object[] mainValues = { SystemConstants.SYS_TARCHIVE_DRIVER , tDriver.getId() ,
		        SystemConstants.SYS_ENABLE };
		List< TUser > users = userDao.findByPropertys( properties , mainValues );
		
		String caiStr = "";// 拆分订单时的提示信息
		for ( int i = 0 ; i < strs.length ; i++ )
		{
			String[] ords = strs[i].split( "," );
			TOrder order = orderDao.getOrderByOrderNo( ords[0] );
			if ( order == null )
			{
				msgobj.put( "resultCode" , "no" );
				msgobj.put( "errorContent" , "获取订单出错，" + ords[0] + "  订单号有重复" );
				
				return msgobj.toString();
			}
			int count = Integer.parseInt( ords[1] );
			if ( count > order.getNTotalCar() )
			{
				System.out.println( "发运数量大于订单需求数量" );
				
				msgobj.put( "resultCode" , "no" );
				msgobj.put( "errorContent" , "发运数量大于订单需求数量" );
				
				return msgobj.toString();
				
			}
			String datestr = DateUtil.getDate( "yyyy/MM/dd" );
			// and to_char(ark.dtStart,'yyyy-MM-dd') between ? and ?
			String sql = " select getArkilomer('" + subno + "','"
			        + order.getVcStartCity() + "','" + order.getVcDestCity() + "','"
			        + datestr + "') nar from dual ";
			System.out.println( "sql" + sql );
			
			float nKilometer = 0;
			try
			{
				
				List artlist = arkilomerDao.getDateBySQL( sql , null );
				nKilometer = Float.parseFloat( artlist.get( 0 ) + "" );
			}
			catch ( Exception e )
			{
				// TODO: handle exception
				System.out.println( "保存发运指令  获取 订单起运地目的地  公里数 出错 ..." );
				msgobj.put( "resultCode" , "ok" );
				msgobj.put( "errorContent" , order.getVcStartCity() + " 到 "
				        + order.getVcDestCity() + " 公里数未维护" );
				
			}
			if ( count < order.getNTotalCar() )// 拆单(如果订单的发运数量小于总数时原先的订单拆成2张子订单)
			{
				order.setNEnable( SystemConstants.SYS_DISABLE );// 原订单设为无效
				orderDao.saveOrUpdate( order );
				
				for ( int i1 = 0 ; i1 < 2 ; i1++ )
				{
					TOrder copyOrder = new TOrder();
					try
					{
						BeanUtils.copyProperties( copyOrder , order );
						copyOrder.setVcOrderno( copyOrder.getVcOrderno() + "_"
						        + ( i1 + 1 ) );
						copyOrder.setNShipedQty( count );
						copyOrder.setNEnable( SystemConstants.SYS_ENABLE );
						System.out.println( count + "--" + order.getNTotalCar() );
						
						float ratio1 = Float.parseFloat( count + "" )
						        / Float.parseFloat( order.getNTotalCar() + "" );
						System.out.println( "ratio1 = " + ratio1 );
						if ( order.getNTotalPrice() != null )
						{
							copyOrder.setNTotalPrice( ratio1 * order.getNTotalPrice() );
						}
						
						if ( i1 == 1 )
						{
							
							copyOrder.setNShipedQty( order.getNTotalCar() - count );// 第二张子订单为余数
							
							if ( order.getNTotalPrice() != null )
							{
								copyOrder.setNTotalPrice( ( 1 - ratio1 )
								        * order.getNTotalPrice() );
								
							}
						}
						copyOrder.setNTotalCar( copyOrder.getNShipedQty() );
						copyOrder.setnLoad( 1 );// 已配载
						orderDao.save( copyOrder );
						shipheadDao.save( head );
						TShipline line = new TShipline();
						line.setIOrderId( copyOrder.getId() );
						line.setIShiphead( head.getId() );
						line.setVcStartCity( copyOrder.getVcStartCity() );
						line.setVcDestCity( copyOrder.getVcDestCity() );
						line.setDtAdd( new Date() );
						// line.setNQty( count );
						line.setnShipQty( copyOrder.getNShipedQty() );
						line.setNApkilometer( nKilometer );
						shiplineDao.save( line );
						TShipStatus tShipStatus = new TShipStatus();
						tShipStatus.setnOrderId( copyOrder.getId() );
						tShipStatus.setnLineId( line.getId() );
						tShipStatus.setVcAddUser( user.getVcUsername() );
						tShipStatus.setVcStatusNote( SystemConstants.VC_LOADING_TRUE );
						tShipStatus.setVcStatusTag( SystemConstants.VC_LOADING_TRUE_TAG );
						tShipStatus.setnHeadId( head.getId() );
						iShipStatusDao.saveOrUpdate( tShipStatus );
						
						// 消息推送
						HashMap< String , String > map = new HashMap< String , String >();
						map.put( "vcLineId" , line.getId() + "" );
						PushUtils pushUtils = new PushUtils(
						        "你有新任务，请点击查看" ,
						        "你有新任务，请点击查看" ,
						        users ,
						        "com.unlcn.driver.ordermanagement.DriverOrderArriveDialogActivity" ,
						        map );
						pushUtils.run();
						
					}
					catch ( IllegalAccessException e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch ( InvocationTargetException e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch ( Exception e )
					{
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				caiStr += "原订单" + order.getVcOrderno() + "已经进行了拆分！ ";
			}
			else
			{
				// shipheadDao.save( head );
				// order.setNShipedQty( order.getNShipedQty() + count );
				order.setnLoad( 1 );// 改变状态为已配载
				order.setNShipedQty( count );
				orderDao.saveOrUpdate( order );
				TShipline line = new TShipline();
				line.setIOrderId( order.getId() );
				line.setIShiphead( head.getId() );
				line.setVcStartCity( order.getVcStartCity() );
				line.setVcDestCity( order.getVcDestCity() );
				line.setDtAdd( new Date() );
				// line.setNQty( count );
				line.setnShipQty( count );
				line.setNApkilometer( nKilometer );
				shiplineDao.save( line );
				
				TShipStatus tShipStatus = new TShipStatus();
				tShipStatus.setnOrderId( order.getId() );
				tShipStatus.setnLineId( line.getId() );
				tShipStatus.setVcAddUser( user.getVcUsername() );
				tShipStatus.setVcStatusNote( SystemConstants.VC_LOADING_TRUE );
				tShipStatus.setVcStatusTag( SystemConstants.VC_LOADING_TRUE_TAG );
				tShipStatus.setnHeadId( head.getId() );
				iShipStatusDao.save( tShipStatus );
				
				// 消息推送
				HashMap< String , String > map = new HashMap< String , String >();
				map.put( "vcLineId" , line.getId() + "" );
				PushUtils pushUtils = new PushUtils(
				        "你有新任务，请点击查看" ,
				        "你有新任务，请点击查看" ,
				        users ,
				        "com.unlcn.driver.ordermanagement.DriverOrderArriveDialogActivity" ,
				        map );
				pushUtils.run();
			}
			// truckDriver.setNStatus( 1 );// 状态改变，回单后改为0
			// truckDriver.setVcShipNo( head.getVcShipno() );
			driverDao.saveOrUpdate( truckDriver );
		}
		if ( caiStr != "" )
		{
			msgobj.put( "resultCode" , "ok" );
			msgobj.put( "errorContent" , caiStr );
		}
		
		return msgobj.toString();
	}
	
	public List< TShiphead > findByProperty( String propertyName , Object value )
	{
		return shipheadDao.findByProperty( propertyName , value );
	}
	
	/**
	 * @Description: TODO(根据参数查询并按指定字段排序)
	 * @param properties
	 * @param values
	 * @param orderByParam
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-8 下午4:46:34
	 */
	public List< TShiphead > findByPropertysOrderBy( String[] properties ,
	        Object[] values , String orderByParam )
	{
		// TODO Auto-generated method stub
		return shipheadDao.findByPropertysOrderBy( properties , values , orderByParam );
	}
	
	public List< TShiphead > findByPropertys( String[] propertyNames , Object[] values )
	{
		return shipheadDao.findByPropertys( propertyNames , values );
	}
	
	// 通过车辆id获取当前配载的订单
	public List< Map< String , Object >> getOrdersByTruckId( int truckId )
	{
		Map< String , Object > map = getLatestShiphead( truckId );
		if ( map == null )
		{
			return null;
		}
		int headId = Integer.parseInt( map.get( "ID" ).toString() );
		String sql = "select o.ID,o.VC_ORDERNO,o.VC_CAR_NAME,o.N_TOTAL_CAR,o.VC_START_CITY "
		        + " from t_shipline l,t_order o where "
		        + " l.I_ORDER_ID=o.id and o.N_LOADING=1 " + " and l.I_SHIPHEAD=" + headId;
		List< Map< String , Object >> result = jdbcDao.queryForList( sql );
		return result;
	}
	
	// 获取已配载车辆
	public Map< String , Object > getLoadedTrucks( int userId , String vcCarNo )
	{
		String sql = "select t.ID,t.VC_CAR_NO,d.VC_DRIVER_NAME,d.VC_DRIVER_TEL from t_truck_driver t,t_driver d,"
		        + "t_truck_driver_link l where l.I_DRIVER=d.id and l.I_TRUCK=t.id and "
		        + " l.N_POSITION_TYPE=1 and t.N_STATUS=1 and d.I_USERID=" + userId;
		if ( StringUtils.isNotBlank( vcCarNo ) )
		{
			sql += " and t.VC_CAR_NO like '%" + vcCarNo + "%'";
		}
		Map< String , Object > result = shipheadDao.getSpringSQL( sql , null );
		return result;
	}
	
	// 通过headId获取配载的订单
	public Map< String , Object > getOrdersByHid( int headId )
	{
		
		String sql = "select o.ID,o.VC_ORDERNO,o.VC_START_CITY,o.VC_DEST_CITY,o.VC_RECEIVE_CONTACT,"
		        + "o.VC_RECEIVE_ADDRESS,o.Vc_Receive_Tel,o.VC_CAR_NAME,o.N_SHIPEDQTY,o.N_TOTAL_CAR"
		        + " from t_shipline l,t_order o where l.I_ORDER_ID=o.id and o.N_ENABLE="
		        + SystemConstants.SYS_ENABLE
		        + "  and o.N_LOADING=1 and l.i_shiphead="
		        + headId;
		Map< String , Object > result = shipheadDao.getSpringSQL( sql , null );
		return result;
	}
	
	// 通过truckId获取最新的发运指令
	public Map< String , Object > getLatestShiphead( int truckId )
	{
		String sql = "select ID,VC_SHIPNO,DT_CREATE,VC_SUBNO from t_shiphead h where N_ENABLE="
		        + SystemConstants.SYS_ENABLE
		        + " and N_CURRENT_STATUS<15 and I_TRUCK_ID="
		        + truckId + " order by DT_CREATE desc";
		List< Map< String , Object >> result = jdbcDao.queryForList( sql );
		if ( CollectionUtils.isNotEmpty( result ) )
		{
			return result.get( 0 );
		}
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iheadId
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-21 下午3:14:42
	 */
	public String saveShipHeadFromErp( int iheadId )
	{
		String message = "success";
		
		String sql = "SELECT * FROM v_shiphead_erp@link_erp.unlcn.com head where head.ILINEID = "
		        + iheadId;
		List< Map< String , Object > > arList = shipheadDao.excuteSql( sql );
		try
		{
			
			if ( arList != null && arList.size() > 0 )
			{
				
				String vcShipNo = ( String ) arList.get( 0 ).get( "VCSHIPNO" );// 指令号
				System.out.println( "vcShipNo = " + vcShipNo );
				/**
				 * 查出这指令是否已配载
				 */
				String[] propertyNames1 = { "vcShipno" , "NEnable" };
				Object[] values1 = { vcShipNo , SystemConstants.SYS_ENABLE };
				List< TShiphead > tShipheads = shipheadDao.findByPropertys(
				        propertyNames1 , values1 );
				if ( tShipheads != null && tShipheads.size() > 0 )
				{
					message = "指令【" + vcShipNo + "】已配载过！";
					return message;
				}
				else
				{
					int mainDriverId = Integer.parseInt( arList.get( 0 )
					        .get( "IDRIVERID" ).toString() );// 主驾司机序号
					int viceDriverId = Integer.parseInt( arList.get( 0 )
					        .get( "IDRIVERID2" ).toString() );// 副驾司机序号
					int truckId = Integer.parseInt( arList.get( 0 )
					        .get( "IVEHICLEID" ).toString() );// 运输车序号
					String vcLicence = arList.get( 0 ).get( "VCLICENSE" )
					        .toString();// 车牌号
					/**
					 * 指令主表
					 */
					TShiphead tShiphead = new TShiphead();
					tShiphead.setVcShipno( vcShipNo );// 发运指令号
					tShiphead.setDtCreate( new Date() );// 生成指令时间
					tShiphead.setVcTruckName( vcLicence );// 运输车名称
					/**
					 * 检查是否存在相同的拖车 没有创建
					 */
					TTruckDriver truckDriver = createTruck( vcLicence );
					truckDriver.setVcShipNo( vcShipNo );
					driverDao.update( truckDriver );
					tShiphead.setITruckId( truckDriver.getId() );
					tShiphead.setVcTruckName( truckDriver.getVcCarNo() );
					/**
					 * 查出 主驾司机
					 */
					TSubsuppliers tSubsuppliers = getZhongLian();// 中联
					String[] properties = { "IArchiveType" , "iArchive" };
					Object[] values = { SystemConstants.SYS_TARCHIVE_SUB ,
					        tSubsuppliers.getId() };
					TUser tUser = userDao.findByPropertys( properties , values )
					        .get( 0 );// 中联的用户表对应ID
					
					String sql2 = "SELECT * FROM  v_driver_erp@link_erp.unlcn.com where ilineid = "
					        + mainDriverId;
					List< Map< String , Object > > arList2 = shipheadDao
					        .excuteSql( sql2 );
					String mainDriverName = "";
					String mainDriverTel = "";
					if ( arList2.get( 0 ).get( "VCNAME" ) != null )
					{
						mainDriverName = arList2.get( 0 ).get( "VCNAME" )
						        .toString();// 主驾司机姓名
					}
					
					if ( arList2.get( 0 ).get( "VCMOBILE" ) != null )
					{
						mainDriverTel = arList2.get( 0 ).get( "VCMOBILE" )
						        .toString();// 手机
					}

					TDriver tDriver = checkTheSameDriver( mainDriverTel );
					TDriver mainDriver = new TDriver();
					List< TUser > driverUsers = new ArrayList< TUser >();// 消息推送用到
					if ( tDriver == null )
					{
						mainDriver.setVcDriverName( mainDriverName );
						mainDriver.setVcDriverTel( mainDriverTel );
						mainDriver.setVcSubno( tSubsuppliers.getVcSubno() );
						mainDriver.setiUserId( tUser.getId() );
						iDriverDao.save( mainDriver );
						// 对应用户表
						TUser driverUser = new TUser();
						driverUser
						        .setIArchiveType( SystemConstants.SYS_TARCHIVE_DRIVER );
						driverUser.setVcUsername( mainDriverName );
						driverUser.setVcAccount( mainDriverTel );
						org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
						String tt = t.encodePassword( "123456" ,
						        driverUser.getVcAccount() );
						driverUser.setiArchive( mainDriver.getId() );
						driverUser.setVcPassword( tt );
						userDao.save( driverUser );
						driverUsers.add( driverUser );
					}
					
					/**
					 * 副驾司机
					 */
					int driverId = 0;
					if ( tDriver == null )
					{
						tShiphead.setVcDriverId( mainDriver.getId() + "," + 0 );
						driverId = mainDriver.getId();
					}
					else
					{
						tShiphead.setVcDriverId( tDriver.getId() + "," + 0 );
						driverId = tDriver.getId();
					}
					
					tShiphead.setVcSubno( tSubsuppliers.getVcSubno() );// 所属分供方编号
					tShiphead.setnCurrentStatus( 0 );// 当前状态0：配载
					shipheadDao.save( tShiphead );
					/**
					 * 创建司机与拖车的关联
					 */
					createTruckDriverLink( driverId , truckDriver.getId() );
					
					/**
					 * 查找指令明细
					 */
					
					String sql4 = "SELECT * FROM   v_shipline_erp@link_erp.unlcn.com where ISHIPID = "
					        + iheadId;
					List< Map< String , Object > > arList4 = shipheadDao
					        .excuteSql( sql4 );
					for ( int i = 0 ; i < arList4.size() ; i++ )
					
					{
						TShipline tShipline = new TShipline();
						int erpOrderId = Integer.parseInt( arList4.get( i )
						        .get( "IORDERID" ).toString() );// 订单表ID
						/**
						 * 根据原ERP订单信息生成订单
						 */
						TOrder tOrder = createOrder( erpOrderId , tShiphead );
						tShipline.setIOrderId( tOrder.getId() );// 订单ID
						tShipline.setIShiphead( tShiphead.getId() );// 指令主表ID
						if ( arList4.get( i ).get( "DCQTY" ) != null )
						{
							tShipline.setNQty( Integer.parseInt( arList4
							        .get( i ).get( "DCQTY" ).toString() ) );// 应收结算数量
						}
						if ( arList4.get( i ).get( "DCSHIPQTY" ) != null )
						{
							tShipline.setnShipQty( Integer.parseInt( arList4
							        .get( i ).get( "DCSHIPQTY" ).toString() ) );// 装运数量
						}
						if ( arList4.get( i ).get( "VCSTARTCITYNAME" ) != null )
						{
							tShipline.setVcStartCity( arList4.get( i )
							        .get( "VCSTARTCITYNAME" ).toString() );// 开始城市
						}
						if ( arList4.get( i ).get( "VCENDCITYNAME" ) != null )
						{
							tShipline.setVcDestCity( arList4.get( i )
							        .get( "VCENDCITYNAME" ).toString() );// 目的地城市名称
						}
						tShipline.setDtAdd( new Date() );
						if ( arList4.get( i ).get( "DCARKILOMETER" ) != null )// 应收公里
						{
							tShipline
							        .setNApkilometer( Float.parseFloat( arList4
							                .get( i ).get( "DCARKILOMETER" )
							                .toString() ) );
						}
						tShipline.setNCurrentStatus( 0 );
						tShipline.setNarorder( 1 );
						shiplineDao.save( tShipline );
						/**
						 * 订单状态表
						 */
						TShipStatus tShipStatus = new TShipStatus();
						tShipStatus.setnOrderId( tOrder.getId() );
						tShipStatus.setnLineId( tShipline.getId() );
						tShipStatus.setVcAddUser( tUser.getVcAccount() );
						tShipStatus
						        .setVcStatusNote( SystemConstants.VC_LOADING_TRUE );
						tShipStatus
						        .setVcStatusTag( SystemConstants.VC_LOADING_TRUE_TAG );
						tShipStatus.setnHeadId( tShiphead.getId() );
						iShipStatusDao.save( tShipStatus );

						/**
						 * 消息推送
						 */
						
						HashMap< String , String > map = new HashMap< String , String >();
						map.put( "vcLineId" , tShipline.getId() + "" );
						map.put( "msgType" , "6" );
						PushUtils pushUtils = new PushUtils(
						        "你有新配载任务，请点击查看" ,
						        "你有来自" + tUser.getVcAccount() + "的订单配载信息" ,
						        driverUsers ,
						        "com.unlcn.driver.ordermanagement.DriverOrderArriveDialogActivity" ,
						        map );
						pushUtils.run();
						for ( TUser tuser : driverUsers )
						{
							// 保存消息记录
							TMsgRecord tMsgRecord = new TMsgRecord();
							tMsgRecord.setIUser( tUser.getId() );// 添加人ID
							tMsgRecord.setVcAdduser( tUser.getVcAccount() );// 添加人姓名
							tMsgRecord.setIUserAccept( tuser.getId() );// 接收人用户id
							tMsgRecord.setNMsgType( 1 );// 单发
							tMsgRecord.setVcContent( "来自"
							        + tUser.getVcAccount() + "的订单配载信息" );
							tMsgRecord.setVcTitle( "有新的订单指配，请点击查看" );
							msgService.save( tMsgRecord );
						}
					}
					
				}
				
			}
			else
			{
				return "查询不到该指令号";
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return e.getMessage();
		}
		
		return message;
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param driverId
	 * @param truckId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-23 下午6:34:38
	 */
	private void createTruckDriverLink( int driverId , int truckId )
	{
		String[] propertyNames = { "IDriverID" , "NPositionType" };
		Object[] values = { driverId , 1 };
		List< TTruckDriverLlink > links = iTruckDriverLinkDao.findByPropertys(
		        propertyNames , values );
		if ( links != null && links.size() > 0 )
		{
			TTruckDriverLlink truckDriverLlink = links.get( 0 );
			truckDriverLlink.setITruckID( truckId );
			iTruckDriverLinkDao.update( truckDriverLlink );
		}
		else
		{
			TTruckDriverLlink newDriverLlink = new TTruckDriverLlink();
			newDriverLlink.setIDriverID( driverId );
			newDriverLlink.setITruckID( truckId );
			newDriverLlink.setNEnable( SystemConstants.SYS_ENABLE );
			newDriverLlink.setNPositionType( 1 );
			iTruckDriverLinkDao.save( newDriverLlink );
		}
		
	}

	/**
	 * @Description: TODO(根据原ERP订单信息生成订单)
	 * @param erpOrderId
	 * @return TOrder 返回值描述
	 * @author liuwu
	 * @param tShiphead
	 * @create_date 2015-9-22 下午2:50:10
	 */
	private TOrder createOrder( int erpOrderId , TShiphead tShiphead )
	{

		String sql4 = "SELECT * FROM   v_order_erp@link_erp.unlcn.com where ilineid = "
		        + erpOrderId;
		List< Map< String , Object > > torderList = shipheadDao
		        .excuteSql( sql4 );
		TOrder tOrder = new TOrder();
		if ( torderList != null && torderList.size() > 0 )
		{

			int customerId = Integer.parseInt( torderList.get( 0 )
			        .get( "ICUSTOMERID" ).toString() );// erp客户表
			TCustomer tCustomer = createCustomer( customerId );// 创建客户
			int carId = Integer.parseInt( torderList.get( 0 ).get( "ISTYLEID" )
			        .toString() );
			TSubCarStyle tSubCarStyle = createCarStyle( carId );// 创建商品车
			TSubsuppliers tSubsuppliers = getZhongLian();// 中联
			tOrder.setICarStyle( carId );
			tOrder.setVcCarName( tSubCarStyle.getVcCarStyle() );
			tOrder.setVcSubno( tSubsuppliers.getVcSubno() );
			if ( torderList.get( 0 ).get( "VCADDRESS" ) != null )
			{
				tOrder.setVcLoadAddress( torderList.get( 0 ).get( "VCADDRESS" )
				        .toString() );// 装货地址
			}
			if ( torderList.get( 0 ).get( "VCCONTACT2" ) != null )
			{
				tOrder.setVcLoadContact( torderList.get( 0 ).get( "VCCONTACT2" )
				        .toString() );// 联系人
			}
			if ( torderList.get( 0 ).get( "VCTEL" ) != null )
			{
				tOrder.setVcLoadTel( torderList.get( 0 ).get( "VCTEL" )
				        .toString() );// 联系电话
			}
			if ( torderList.get( 0 ).get( "DTSHIPDATE" ) != null )
			{
				tOrder.setDtShip( ( Date ) torderList.get( 0 ).get(
				        "DTSHIPDATE" ) );// 要求发运日
			}
			if ( torderList.get( 0 ).get( "DTCOMEDATE" ) != null )
			{
				tOrder.setDtArrive( ( Date ) torderList.get( 0 ).get(
				        "DTCOMEDATE" ) );// 要求到货日
			}
			if ( torderList.get( 0 ).get( "VCSTARTCITY" ) != null )
			{
				tOrder.setVcStartCity( torderList.get( 0 ).get( "VCSTARTCITY" )
				        .toString() );// 起运城市
			}
			if ( torderList.get( 0 ).get( "VCCITYNAME" ) != null )
			{
				tOrder.setVcDestCity( torderList.get( 0 ).get( "VCCITYNAME" )
				        .toString() );// 目的城市
			}
			if ( torderList.get( 0 ).get( "VCADDRESS2" ) != null )
			{
				tOrder.setVcReceiveAddress( torderList.get( 0 )
				        .get( "VCADDRESS2" ).toString() );// 收货地址
			}
			if ( torderList.get( 0 ).get( "VCCONTACT2" ) != null )
			{
				tOrder.setVcReceiveContact( torderList.get( 0 )
				        .get( "VCCONTACT2" ).toString() );// 收货联系人
			}
			if ( torderList.get( 0 ).get( "VCTEL2" ) != null )
			{
				tOrder.setVcReceiveTel( torderList.get( 0 ).get( "VCTEL2" )
				        .toString() );// 收货人电话
			}
			if ( torderList.get( 0 ).get( "DCQTY" ) != null )
			{
				tOrder.setNTotalCar( Integer.parseInt( torderList.get( 0 )
				        .get( "DCQTY" ).toString() ) );// 数量
			}
			if ( torderList.get( 0 ).get( "DCSHIPEDQTY" ) != null )
			{
				tOrder.setNShipedQty( Integer.parseInt( torderList.get( 0 )
				        .get( "DCSHIPEDQTY" ).toString() ) );// 发运数量
			}
			if ( torderList.get( 0 ).get( "VCORDERNO" ) != null )
			{
				if ( tCustomer.getNSecondHandCar() == 0 )
				{
					
					tOrder.setVcOrderno( torderList.get( 0 ).get( "VCORDERNO" )
					        .toString()
					        + "*" );// 订单号
				}
				else
				{
					tOrder.setVcOrderno( torderList.get( 0 ).get( "VCORDERNO" )
					        .toString() );// 订单号
				}
			}
			if ( torderList.get( 0 ).get( "IPAYID" ) != null )
			{
				tOrder.setNPayType( Integer.parseInt( torderList.get( 0 )
				        .get( "IPAYID" ).toString() ) );// 支付方式(0 现金 1客户)
			}
			if ( torderList.get( 0 ).get( "DCPAY" ) != null )
			{
				tOrder.setNTotalPrice( Float.parseFloat( torderList.get( 0 )
				        .get( "DCPAY" ).toString() ) );// 订单总价
			}
			if ( tCustomer != null )
			{
				tOrder.setICustomerId( tCustomer.getId() );// 客户表ID
			}
			if ( torderList.get( 0 ).get( "VCCUSTORDERNO" ) != null )
			{
				tOrder.setVcCustOrderNo( torderList.get( 0 )
				        .get( "VCCUSTORDERNO" ).toString() );// 客户订单号(客户自己录入)
			}
			if ( torderList.get( 0 ).get( "DTDATE" ) != null )
			{
				tOrder.setDtCreateDate( ( Date ) torderList.get( 0 ).get(
				        "DTDATE" ) );// 创建时间
			}
			tOrder.setnLoad( 1 );// 是否配载（0：未配载[默认]；1已配载）
			tOrder.setiTruckId( tShiphead.getITruckId() );// 拖车ID
			orderDao.save( tOrder );
		}
		
		return tOrder;
	}
	
	/**
	 * @Description: TODO( 创建商品车)
	 * @param carId
	 * @return TCarStyle 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-22 下午4:05:59
	 */
	private TSubCarStyle createCarStyle( int carId )
	{
		String sql = "SELECT * FROM  v_carstyle_erp@link_erp.unlcn.com where ilineid = "
		        + carId;
		List< Map< String , Object > > carStyles = shipheadDao.excuteSql( sql );
		String vcCarStyle = carStyles.get( 0 ).get( "VCSTYLENAME" ).toString();
		TSubCarStyle tSubCarStyle = new TSubCarStyle();
		TSubsuppliers tSubsuppliers = getZhongLian();
		String[] properties = { "vcCarStyle" , "NEnable" , "vcSubno" };
		Object[] values = { vcCarStyle , SystemConstants.SYS_ENABLE ,
		        tSubsuppliers.getVcSubno() };
		List< TSubCarStyle > tSubCarStyles = iSubCarStyleDao.findByPropertys(
		        properties , values );
		
		if ( tSubCarStyles != null && tSubCarStyles.size() > 0 )
		{
			return tSubCarStyles.get( 0 );
			
		}
		else
		{
			tSubCarStyle.setVcCarStyle( vcCarStyle );
			tSubCarStyle.setVcSubno( tSubsuppliers.getVcSubno() );
			iSubCarStyleDao.saveOrUpdate( tSubCarStyle );
			return tSubCarStyle;
		}
		
	}
	
	/**
	 * @Description: TODO(创建客户)
	 * @param customerId
	 * @return TCustomer 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-22 下午3:16:14
	 */
	private TCustomer createCustomer( int customerId )
	{
		String sql = "SELECT * FROM  v_customer_erp@link_erp.unlcn.com where ilineid = "
		        + customerId;
		List< Map< String , Object > > tCustomers = shipheadDao.excuteSql( sql );
		String vcCustomer = tCustomers.get( 0 ).get( "VCCUSTOMERNAME" )
		        .toString();
		TCustomer tCustomer = new TCustomer();
		List< TCustomer > tCustomerList = iCustomerDao.findByProperty(
		        "vcShortName" , vcCustomer );
		TSubsuppliers tSubsuppliers = getZhongLian();
		
		if ( tCustomerList != null && tCustomerList.size() > 0 )
		{
			return tCustomerList.get( 0 );
		}
		else
		{
			tCustomer.setVcCustomerNo( tCustomers.get( 0 ).get( "VCCUSTOMERNO" )
			        .toString() );// 客户编号
			tCustomer.setVcShortName( tCustomers.get( 0 )
			        .get( "VCCUSTOMERNAME" ).toString() );// 客户简称
			if ( tCustomers.get( 0 ).get( "VCCONTACT" ) != null )
			{
				tCustomer.setVcLinkman( tCustomers.get( 0 ).get( "VCCONTACT" )
				        .toString() );// 客户联系人
			}
			if ( tCustomers.get( 0 ).get( "VCTEL" ) != null )
			{
				
				tCustomer.setVcPhone( tCustomers.get( 0 ).get( "VCTEL" )
				        .toString() );// 联系电话
			}
			if ( tCustomers.get( 0 ).get( "VCADDRESS" ) != null )
			{
				tCustomer.setVcRegAddress( tCustomers.get( 0 )
				        .get( "VCADDRESS" ).toString() );// 公司注册地址
			}
			if ( tCustomers.get( 0 ).get( "VCPROVINCE" ).toString() != null )
			{
				tCustomer.setVcProvince( tCustomers.get( 0 ).get( "VCPROVINCE" )
				        .toString() );
			}
			if ( tCustomers.get( 0 ).get( "VCCITYNAME" ).toString() != null )
			{
				tCustomer.setVcCity( tCustomers.get( 0 ).get( "VCCITYNAME" )
				        .toString() );
			}
			tCustomer.setVcSubno( tSubsuppliers.getVcSubno() );// 所属分供方编号
			if ( tCustomers.get( 0 ).get( "ITYPE" ).toString() != null )
			{
				if ( tCustomers.get( 0 ).get( "ITYPE" ).toString().equals( "4" ) )
				{
					tCustomer.setNSecondHandCar( 0 );// 是否是从erp导过来二手车客户（0为是，1为不是）
				}
				else
				{
					tCustomer.setNSecondHandCar( 1 );// 是否是从erp导过来二手车客户（0为是，1为不是）
				}

			}
			// tCustomer.setNSecondHandCar( 0 );// 是否是从erp导过来二手车客户（0为是，1为不是）
			iCustomerDao.saveOrUpdate( tCustomer );
			return tCustomer;
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return TSubsuppliers 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-22 下午4:22:43
	 */
	private TSubsuppliers getZhongLian()
	{
		TSubsuppliers tSubsuppliers = subDao.get( SystemConstants.ZHONGLIANID );
		return tSubsuppliers;
	}
	
	/**
	 * @Description: TODO(检查是否存在相同的司机)
	 * @param mainDriverTel
	 * @return TDriver 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-22 下午2:10:34
	 */
	private TDriver checkTheSameDriver( String mainDriverTel )
	{
		List< TDriver > tDrivers = iDriverDao.findByProperty( "vcDriverTel" ,
		        mainDriverTel );
		if ( tDrivers != null && tDrivers.size() > 0 )
		{
			return tDrivers.get( 0 );
		}
		return null;
	}
	
	/**
	 * @Description: TODO(检查是否存在相同的拖车)
	 * @param vcLicence
	 * @return boolean 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-22 下午1:44:38
	 */
	private TTruckDriver createTruck( String vcLicence )
	{
		List< TTruckDriver > truckDrivers = driverDao.findByProperty(
		        "vcCarNo" , vcLicence );
		if ( truckDrivers != null && truckDrivers.size() > 0 )
		{
			return truckDrivers.get( 0 );
		}
		else
		{
			TTruckDriver truckDriver = new TTruckDriver();
			truckDriver.setVcCarNo( vcLicence );
			truckDriver.setNStatus( 1 );
			driverDao.save( truckDriver );
			return truckDriver;
		}

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-24 上午10:17:59
	 */
	public List< Map< String , Object >> excuteSql( String sql )
	{
		List< Map< String , Object > > arlist = shipheadDao.excuteSql( sql );
		return arlist;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iheadId
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-24 下午2:22:40
	 */
	public String updateCancelShipHead( int iheadId )
	{
		String message = "success";
		
		String sql = "SELECT * FROM v_shiphead_erp@link_erp.unlcn.com head where head.ILINEID = "
		        + iheadId;
		List< Map< String , Object > > arList = shipheadDao.excuteSql( sql );
		
		try
		{
			if ( arList != null && arList.size() > 0 )
			{
				String vcShipNo = ( String ) arList.get( 0 ).get( "VCSHIPNO" );// 指令号
				String[] propertyNames = { "vcShipno" , "NEnable" };
				Object[] values = { vcShipNo , SystemConstants.SYS_ENABLE };
				List< TShiphead > tShipheads = shipheadDao.findByPropertys(
				        propertyNames , values );
				if ( tShipheads != null && tShipheads.size() > 0 )
				{
					TShiphead tShiphead = tShipheads.get( 0 );
					tShiphead.setNEnable( SystemConstants.SYS_DISABLE );
					shipheadDao.update( tShiphead );
					cancelShipLine( tShiphead.getId() );
				}

			}
			else
			{
				message = "查询不到指令！";
			}
		}
		catch ( Exception e )
		{
			message = "失败！原因：" + e.getMessage();
		}
		return message;
	}
	
	/**
	 * @Description: TODO(取消指令明细)
	 * @param id
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-24 下午2:38:48
	 */
	private void cancelShipLine( Integer id )
	{
		
		String[] propertyNames = { "IShiphead" , "NEnable" };
		Object[] values = { id , SystemConstants.SYS_ENABLE };
		List< TShipline > tShiplines = shiplineDao.findByPropertys(
		        propertyNames , values );
		for ( TShipline tShipline : tShiplines )
		{
			tShipline.setNEnable( SystemConstants.SYS_DISABLE );
			shiplineDao.update( tShipline );
			TOrder tOrder = orderDao.get( tShipline.getIOrderId() );
			tOrder.setNEnable( SystemConstants.SYS_DISABLE );
			orderDao.update( tOrder );
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iheadId
	 * @param itruckId
	 * @param idriverId
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-24 下午3:05:19
	 */
	public String updateShipHeadTruckAndDriver( int iheadId , int itruckId ,
	        int idriverId )
	{
		String message = "success";
		
		String sql = "SELECT * FROM v_shiphead_erp@link_erp.unlcn.com head where head.ILINEID = "
		        + iheadId;
		List< Map< String , Object > > arList = shipheadDao.excuteSql( sql );
		
		try
		{
			if ( arList != null && arList.size() > 0 )
			{
				String vcShipNo = ( String ) arList.get( 0 ).get( "VCSHIPNO" );// 指令号
				String[] propertyNames = { "vcShipno" , "NEnable" };
				Object[] values = { vcShipNo , SystemConstants.SYS_ENABLE };
				List< TShiphead > tShipheads = shipheadDao.findByPropertys(
				        propertyNames , values );
				if ( tShipheads != null && tShipheads.size() > 0 )
				{
					TShiphead tShiphead = tShipheads.get( 0 );

					String sql2 = "select * from V_VEHICLE_ERP@link_erp.unlcn.com t where t.ILINEID ="
					        + itruckId;
					List< Map< String , Object > > arList2 = shipheadDao
					        .excuteSql( sql2 );
					if ( arList2 != null && arList2.size() > 0 )
					{
						String vcLicence = arList.get( 0 ).get( "VCLICENSE" )
						        .toString();// 车牌号
						
						/**
						 * 检查是否存在相同的拖车 没有创建
						 */
						TTruckDriver truckDriver = createTruck( vcLicence );
						tShiphead.setVcTruckName( truckDriver.getVcCarNo() );
						tShiphead.setITruckId( truckDriver.getId() );
						/**
						 * 检查是否存在该司机
						 */
						
						TDriver driver = createDriver( idriverId );
						tShiphead.setVcDriverId( driver.getId() + "," + 0 );
						shipheadDao.update( tShiphead );
					}
					else
					{
						message = "查询不到该拖车";
					}
				}
				else
				{
					message = "新平台系统查询不到该指令！";
				}
				
			}
			else
			{
				message = "erp查询不到该指令！";
			}
		}
		catch ( Exception e )
		{
			message = "失败！原因：" + e.getMessage();
		}
		return message;
	}
	
	/**
	 * @Description: TODO(司机：有返回没有创建)
	 * @return TDriver 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-24 下午3:36:51
	 */
	private TDriver createDriver( int idriverId )
	{
		String sql3 = "SELECT * FROM  v_driver_erp@link_erp.unlcn.com where ilineid = "
		        + idriverId;
		List< Map< String , Object > > arList = shipheadDao.excuteSql( sql3 );
		String mainDriverTel = "";
		String mainDriverName = "";
		TSubsuppliers tSubsuppliers = getZhongLian();
		String[] properties = { "IArchiveType" , "iArchive" };
		Object[] value = { SystemConstants.SYS_TARCHIVE_SUB ,
		        tSubsuppliers.getId() };
		TUser tUser = userDao.findByPropertys( properties , value ).get( 0 );// 中联的用户表对应ID
		if ( arList.get( 0 ).get( "VCMOBILE" ) != null )
		{
			mainDriverTel = arList.get( 0 ).get( "VCMOBILE" ).toString();// 手机
		}
		
		if ( arList.get( 0 ).get( "VCNAME" ) != null )
		{
			mainDriverName = arList.get( 0 ).get( "VCNAME" ).toString();// 姓名
		}

		String[] propertyNames = { "vcDriverTel" , "NEnable" , "vcSubno" };
		Object[] values = { mainDriverTel , SystemConstants.SYS_ENABLE ,
		        tSubsuppliers.getVcSubno() };
		List< TDriver > tDrivers = iDriverDao.findByPropertys( propertyNames ,
		        values );

		if ( tDrivers != null && tDrivers.size() > 0 )
		{
			return tDrivers.get( 0 );
		}
		else
		{
			TDriver mainDriver = new TDriver();
			mainDriver.setVcDriverName( mainDriverName );
			mainDriver.setVcDriverTel( mainDriverTel );
			mainDriver.setVcSubno( tSubsuppliers.getVcSubno() );
			mainDriver.setiUserId( tUser.getId() );
			iDriverDao.save( mainDriver );
			// 对应用户表
			TUser driverUser = new TUser();
			driverUser.setIArchiveType( SystemConstants.SYS_TARCHIVE_DRIVER );
			driverUser.setVcUsername( mainDriverName );
			driverUser.setVcAccount( mainDriverTel );
			org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
			String tt = t.encodePassword( "123456" , driverUser.getVcAccount() );
			driverUser.setiArchive( mainDriver.getId() );
			driverUser.setVcPassword( tt );
			userDao.save( driverUser );
			return mainDriver;
		}

	}
	
	/**
	 * 
	 * @Description: TODO(增加或删除订单接口)
	 * @param iheadId
	 * @param type
	 * @param orderIds
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-25 上午10:57:43
	 */
	public String updateChangeOrderQtysFromErp( int iheadId , String type ,
	        String orderIds )
	{
		String message = "success";
		/**
		 * 增加订单
		 */
		if ( type.equalsIgnoreCase( "add" ) )
		{
			String sql = "SELECT * FROM v_shiphead_erp@link_erp.unlcn.com head where head.ILINEID = "
			        + iheadId;
			List< Map< String , Object > > arList = shipheadDao.excuteSql( sql );
			
			if ( arList != null && arList.size() > 0 )
			{
				String vcShipNo = ( String ) arList.get( 0 ).get( "VCSHIPNO" );// 指令号
				System.out.println( "vcShipNo = " + vcShipNo );
				/**
				 * 查出这指令是否已配载
				 */
				String[] propertyNames1 = { "vcShipno" , "NEnable" };
				Object[] values1 = { vcShipNo , SystemConstants.SYS_ENABLE };
				List< TShiphead > tShipheads = shipheadDao.findByPropertys(
				        propertyNames1 , values1 );
				if ( tShipheads != null && tShipheads.size() > 0 )
				{
					TShiphead tShiphead = tShipheads.get( 0 );
					String[] ids = orderIds.split( "," );
					for ( String id : ids )
					{
						int erpOrderId = Integer.parseInt( id );
						TOrder tOrder = createOrder( erpOrderId , tShiphead );
						
					}
				}
				else
				{
					message = "新平台系统未查询到该指令，请先同步到新平台系统";
				}


			}
			else
			{
				message = "erp查询不到该指令!";
			}
			

		}
		else if ( type.equalsIgnoreCase( "delete" ) )
		{
			String[] ids = orderIds.split( "," );
			for ( String id : ids )
			{
				int erpOrderId = Integer.parseInt( id );
				message = disableOrder( erpOrderId );
				if(message.equalsIgnoreCase( "success" )){
					continue;
				}else {
					break;
				}
			}
		}

		return message;
	}

	/**
	 * @Description: TODO(删除用户)
	 * @param erpOrderId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-25 下午1:56:15
	 */ 
    private String disableOrder( int erpOrderId )
    {
		String message = "success";
    	String sql4 = "SELECT * FROM   v_order_erp@link_erp.unlcn.com where ilineid = "
		        + erpOrderId;
		List< Map< String , Object > > torderList = shipheadDao
		        .excuteSql( sql4 );
		if ( torderList != null && torderList.size() > 0 )
		{
			int customerId = Integer.parseInt( torderList.get( 0 )
			        .get( "ICUSTOMERID" ).toString() );// erp客户表
			TCustomer tCustomer = createCustomer( customerId );// 创建客户
			if ( torderList.get( 0 ).get( "VCORDERNO" ) != null )
			{
				if ( tCustomer.getNSecondHandCar() == 0 )
				{
					
					String vcOrderNo = torderList.get( 0 ).get( "VCORDERNO" )
					        .toString()
					        + "*";// 订单号
					String[] propertyNames = { "NEnable" , "vcOrderno" };
					Object[] values = { SystemConstants.SYS_ENABLE , vcOrderNo };
					List< TOrder > tOrders = orderDao.findByPropertys(
					        propertyNames , values );
					if ( tOrders != null && tOrders.size() > 0 )
					{
						for ( TOrder tOrder : tOrders )
						{
							tOrder.setNEnable( SystemConstants.SYS_DISABLE );
							orderDao.update( tOrder );
						}
					}
					else
					{
						message = "新平台系统订单编号【" + vcOrderNo + "】查询不到！";
					}
				}
				else
				{
					String vcOrderNo = torderList.get( 0 ).get( "VCORDERNO" )
					        .toString();// 订单号
					String[] propertyNames = { "NEnable" , "vcOrderno" };
					Object[] values = { SystemConstants.SYS_ENABLE , vcOrderNo };
					List< TOrder > tOrders = orderDao.findByPropertys(
					        propertyNames , values );
					if ( tOrders != null && tOrders.size() > 0 )
					{
						for ( TOrder tOrder : tOrders )
						{
							tOrder.setNEnable( SystemConstants.SYS_DISABLE );
							orderDao.update( tOrder );
						}
					}
					else
					{
						message = "新平台系统订单编号【" + vcOrderNo + "】查询不到！";
					}

				}
			}
			else
			{
				message = "新平台系统无此订单信息！请先同步！";
			}
		}
		else
		{
			message = "ERP查询不到订单信息";
		}

		return message;
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iheadId
	 * @param iorderId
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-25 下午2:55:02
	 */
	public String updateOrderDetialsFromErp( int iorderId )
	{
		String message = "";
		String sql4 = "SELECT * FROM   v_order_erp@link_erp.unlcn.com where ilineid = "
		        + iorderId;
		List< Map< String , Object > > torderList = shipheadDao
		        .excuteSql( sql4 );
		if ( torderList != null && torderList.size() > 0 )
		{
			int customerId = Integer.parseInt( torderList.get( 0 )
			        .get( "ICUSTOMERID" ).toString() );// erp客户表
			TCustomer tCustomer = createCustomer( customerId );// 创建客户
			if ( torderList.get( 0 ).get( "VCORDERNO" ) != null )
			{
				String vcOrderNo = "";
				if ( tCustomer.getNSecondHandCar() == 0 )
				{
					vcOrderNo = torderList.get( 0 ).get( "VCORDERNO" )
					        .toString()
					        + "*";// 订单号
				}
				else
				{
					vcOrderNo = torderList.get( 0 ).get( "VCORDERNO" )
					        .toString();// 订单号
				}
				String[] propertyNames = { "NEnable" , "vcOrderno" };
				Object[] values = { SystemConstants.SYS_ENABLE , vcOrderNo };
				List< TOrder > tOrders = orderDao.findByPropertys(
				        propertyNames , values );
				if ( tOrders != null && tOrders.size() > 0 )
				{
					TOrder tOrder = tOrders.get( 0 );
					
					int carId = Integer.parseInt( torderList.get( 0 )
					        .get( "ISTYLEID" ).toString() );
					TSubCarStyle tSubCarStyle = createCarStyle( carId );// 创建商品车
					TSubsuppliers tSubsuppliers = getZhongLian();// 中联
					tOrder.setICarStyle( carId );
					tOrder.setVcCarName( tSubCarStyle.getVcCarStyle() );
					tOrder.setVcSubno( tSubsuppliers.getVcSubno() );
					if ( torderList.get( 0 ).get( "VCADDRESS" ) != null )
					{
						tOrder.setVcLoadAddress( torderList.get( 0 )
						        .get( "VCADDRESS" ).toString() );// 装货地址
					}
					if ( torderList.get( 0 ).get( "VCCONTACT2" ) != null )
					{
						tOrder.setVcLoadContact( torderList.get( 0 )
						        .get( "VCCONTACT2" ).toString() );// 联系人
					}
					if ( torderList.get( 0 ).get( "VCTEL" ) != null )
					{
						tOrder.setVcLoadTel( torderList.get( 0 ).get( "VCTEL" )
						        .toString() );// 联系电话
					}
					if ( torderList.get( 0 ).get( "DTSHIPDATE" ) != null )
					{
						tOrder.setDtShip( ( Date ) torderList.get( 0 ).get(
						        "DTSHIPDATE" ) );// 要求发运日
					}
					if ( torderList.get( 0 ).get( "DTCOMEDATE" ) != null )
					{
						tOrder.setDtArrive( ( Date ) torderList.get( 0 ).get(
						        "DTCOMEDATE" ) );// 要求到货日
					}
					if ( torderList.get( 0 ).get( "VCSTARTCITY" ) != null )
					{
						tOrder.setVcStartCity( torderList.get( 0 )
						        .get( "VCSTARTCITY" ).toString() );// 起运城市
					}
					if ( torderList.get( 0 ).get( "VCCITYNAME" ) != null )
					{
						tOrder.setVcDestCity( torderList.get( 0 )
						        .get( "VCCITYNAME" ).toString() );// 目的城市
					}
					if ( torderList.get( 0 ).get( "VCADDRESS2" ) != null )
					{
						tOrder.setVcReceiveAddress( torderList.get( 0 )
						        .get( "VCADDRESS2" ).toString() );// 收货地址
					}
					if ( torderList.get( 0 ).get( "VCCONTACT2" ) != null )
					{
						tOrder.setVcReceiveContact( torderList.get( 0 )
						        .get( "VCCONTACT2" ).toString() );// 收货联系人
					}
					if ( torderList.get( 0 ).get( "VCTEL2" ) != null )
					{
						tOrder.setVcReceiveTel( torderList.get( 0 )
						        .get( "VCTEL2" ).toString() );// 收货人电话
					}
					if ( torderList.get( 0 ).get( "DCQTY" ) != null )
					{
						tOrder.setNTotalCar( Integer.parseInt( torderList
						        .get( 0 ).get( "DCQTY" ).toString() ) );// 数量
					}
					if ( torderList.get( 0 ).get( "DCSHIPEDQTY" ) != null )
					{
						tOrder.setNShipedQty( Integer.parseInt( torderList
						        .get( 0 ).get( "DCSHIPEDQTY" ).toString() ) );// 发运数量
					}
					if ( torderList.get( 0 ).get( "VCORDERNO" ) != null )
					{
						if ( tCustomer.getNSecondHandCar() == 0 )
						{
							
							tOrder.setVcOrderno( torderList.get( 0 )
							        .get( "VCORDERNO" ).toString()
							        + "*" );// 订单号
						}
						else
						{
							tOrder.setVcOrderno( torderList.get( 0 )
							        .get( "VCORDERNO" ).toString() );// 订单号
						}
					}
					if ( torderList.get( 0 ).get( "IPAYID" ) != null )
					{
						tOrder.setNPayType( Integer.parseInt( torderList
						        .get( 0 ).get( "IPAYID" ).toString() ) );// 支付方式(0
																		 // 现金
																		 // 1客户)
					}
					if ( torderList.get( 0 ).get( "DCPAY" ) != null )
					{
						tOrder.setNTotalPrice( Float.parseFloat( torderList
						        .get( 0 ).get( "DCPAY" ).toString() ) );// 订单总价
					}
					if ( tCustomer != null )
					{
						tOrder.setICustomerId( tCustomer.getId() );// 客户表ID
					}
					if ( torderList.get( 0 ).get( "VCCUSTORDERNO" ) != null )
					{
						tOrder.setVcCustOrderNo( torderList.get( 0 )
						        .get( "VCCUSTORDERNO" ).toString() );// 客户订单号(客户自己录入)
					}
					if ( torderList.get( 0 ).get( "DTDATE" ) != null )
					{
						tOrder.setDtCreateDate( ( Date ) torderList.get( 0 )
						        .get( "DTDATE" ) );// 创建时间
					}
					orderDao.update( tOrder );
				}
				else
				{
					message = "新平台系统无此订单信息！请先同步！";
				}

			}
			else
			{
				message = "订单编号缺失！";
			}
		}
		else
		{
			return "ERP查询不到该订单信息";
		}
		return message;

    }

	/** 
	 * @Description:这里用一句话描述这个方法的作用 
	 * @param strs
	 * @param headId
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException 
	 * @author hjx
	 * @create_date 2015年9月28日 下午4:02:24
	 */ 
    public Map< String , Object > saveAppendLoading( String[] strs , int headId )
            throws IllegalAccessException , InvocationTargetException
    {
	    // TODO Auto-generated method stub
	    return null;
    }
	
}
