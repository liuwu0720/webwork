package com.clt.sub.service.imp;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IArkilometerDao;
import com.clt.sub.dao.IArorderDao;
import com.clt.sub.dao.IArorderDriverDao;
import com.clt.sub.dao.IDriverCostDao;
import com.clt.sub.dao.IDriverDao;
import com.clt.sub.dao.IDriverSalaryCofflisDao;
import com.clt.sub.dao.IDriverSalarySubsideDao;
import com.clt.sub.dao.IDriverSubsideLinksDao;
import com.clt.sub.dao.IOrderDao;
import com.clt.sub.dao.IShipheadDao;
import com.clt.sub.dao.IShiplineDao;
import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.sub.dao.ITruckDriverLinkDao;
import com.clt.sub.model.TArkilometer;
import com.clt.sub.model.TArorder;
import com.clt.sub.model.TArorderDriver;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TDriverCost;
import com.clt.sub.model.TDriverSalaryCoefficient;
import com.clt.sub.model.TDriverSubsideLinks;
import com.clt.sub.model.TDriverSubsides;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TShipline;
import com.clt.sub.service.IArorderService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.DateUtil;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.sub.service.imp
 * @Description: 结算应收Service
 * @author chenbin
 * @date 2014-7-21 下午3:59:40
 * @version V1.0
 */
@Service
public class ArorderServiceImp implements IArorderService
{
	
	@Autowired
	IArorderDao arorderDao;
	@Autowired
	IShipheadDao shipheadDao;
	@Autowired
	IShiplineDao shiplineDao;
	@Autowired
	IOrderDao orderDao;
	@Autowired
	IDriverCostDao driverDao;
	@Autowired
	ISubsuppliersDao subDao;
	@Autowired
	IArkilometerDao iArkilometerDao;
	@Autowired
	IDriverSalaryCofflisDao iDriverSalaryCofflisDao;
	@Autowired
	IDriverCostDao iDriverCostDao;
	@Autowired
	ITruckDriverLinkDao iTruckDriverLinkDao;
	@Autowired
	IDriverDao iDriverDao;
	@Autowired
	IArorderDriverDao iArorderDriverDao;
	
	@Autowired
	IDriverSubsideLinksDao iDriverSubsideLinksDao;
	
	@Autowired
	IDriverSalarySubsideDao iDriverSalarySubsideDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:02:17
	 */
	public TArorder get( Integer id )
	{
		// TODO Auto-generated method stub
		return arorderDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:02:17
	 */
	public void update( TArorder entity )
	{
		// TODO Auto-generated method stub
		arorderDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:02:17
	 */
	public void save( TArorder entity )
	{
		// TODO Auto-generated method stub
		arorderDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:02:17
	 */
	public void saveOrUpdate( TArorder entity )
	{
		// TODO Auto-generated method stub
		arorderDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:02:17
	 */
	public void delete( TArorder entity )
	{
		// TODO Auto-generated method stub
		arorderDao.delete( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:02:17
	 */
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		arorderDao.deleteByKey( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:02:17
	 */
	public List< TArorder > loadAll()
	{
		// TODO Auto-generated method stub
		return arorderDao.loadAll();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:02:17
	 */
	public List< TArorder > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return arorderDao.findAll( page );
	}
	
	/**
	 * @Description: 根据调度指令确认 结算
	 * @param heads
	 *            发运指令id 多个,分隔
	 * @author chenbin
	 * @create_date 2014-7-21 下午6:27:46
	 */
	public String saveArorder( TUser user , int shiplineid )
	{
		String message = "success";
		String subbo = subDao.get( user.getiArchive() ).getVcSubno();
		
		TShipline line = shiplineDao.get( shiplineid );
		
		TShiphead head = shipheadDao.get( line.getIShiphead() );
		TOrder order = orderDao.get( line.getIOrderId() );
		// 如果是客户支付，且总价为0，提示先同步数据
		if ( order.getNPayType().equals( 1 ) )// 支付方式(0 现金 1客户)
		{
			if ( order.getNTotalPrice() == null || order.getNTotalPrice() == 0 )
			{
				message = "该订单为客户支付，请先同步数据！";
				return message;
			}
		}

		TArorder arorder = new TArorder();
		boolean nextFlag = true;
		arorder.setIOrder( order.getId() );// 订单表ID
		arorder.setIStart( order.getIStartId() );
		arorder.setIDest( order.getIEndId() );
		arorder.setVcStartCity( order.getVcStartCity() );
		arorder.setVcDestCity( order.getVcDestCity() );
		arorder.setICarStyle( order.getICarStyle() );
		arorder.setVcCarStyle( order.getVcCarName() );
		arorder.setNPrice( order.getNCost() );
		arorder.setNArkilometer( line.getNApkilometer() );
		arorder.setNQty( line.getNQty() );
		/*arorder.setDtShip( line.getDtShip() );
		arorder.setDtCome( line.getDtArrived() );*/
		arorder.setDtAudit( new Date() );// 审核时间
		arorder.setVcAuditName( user.getVcUsername() );// 审核人
		arorder.setNaudit( 0 );// 结算已审核
		arorder.setIAuditUser( user.getId() );
		arorder.setIShiphead( head.getId() );
		arorder.setIShipline( shiplineid );
		arorder.setVcSubNo( order.getVcSubno() );
		arorder.setnPaytype( order.getNPayType() );// 支付方式(0 现金 1客户)
		arorder.setVcCustomerNo( order.getVcCustOrderNo() );// 客户运单号
		arorder.setVcAuditName( user.getVcAccount() );// 审核人
		arorder.setVcShipNo( head.getVcShipno() );

		if ( order.getNPayType().equals( 0 ) )// 现金支付
		{
			
			arorder.setNTotalPrice( order.getNTotalPrice() );
			/*line.setNarorder( 0 );// 表示已经结算
			shiplineDao.update( line );
			arorderDao.save( arorder );*/

		}
		else if ( order.getNPayType().equals( 1 ) )// 客户支付(验证当前应收公里，应收单价)
		{
			int startId = order.getIStartId();
			int endId = order.getIEndId();
			
			String vcSubno = order.getVcSubno();
			List< TArkilometer > tArkilometers = iArkilometerDao
			        .findByPropertys( new String[] { "IStartId" , "IEndId" ,
			                "vcSubno" , "NEnable" , "nCheck" } , new Object[] {
			                startId , endId , vcSubno ,
			                SystemConstants.SYS_ENABLE , 1 } );
			List< TDriverCost > tDriverCosts = iDriverCostDao.findByPropertys(
			        new String[] { "IStartId" , "IEndId" , "ISubCarid" ,
			                "ICustomerid" , "vcSubno" , "NEnable" , "NCheck" } ,
			        new Object[] { startId , endId , order.getICarStyle() ,
			                order.getICustomerId() , vcSubno ,
			                SystemConstants.SYS_ENABLE , 1 } );
			
			if ( tDriverCosts.size() > 0 )
			{
				TDriverCost tDriverCost = tDriverCosts.get( 0 );
				Date nowtime = new Date();
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
				String strNow = format.format( nowtime );
				String endTime = format.format( tDriverCost.getDtEnd() );
				boolean flag = isDateBefore( strNow , endTime );
				if ( flag )
				{
					arorder.setNPrice( tDriverCost.getnCost() );
					
				}
				else
				{
					message = "当前起始地城市【" + order.getVcStartCity() + "】与目的地城市【"
					        + order.getVcDestCity() + "】对应车型【"
					        + order.getVcCarName() + "】应收单价过期，请维护！";
					nextFlag = false;
				}
			}
			else
			{
				message = "当前起始地城市【" + order.getVcStartCity() + "】与目的地城市【"
				        + order.getVcDestCity() + "】对应车型【"
				        + order.getVcCarName() + "】应收单价未维护";
				nextFlag = false;

			}
			
			/**
			 * 验证应收公里有效性
			 */
			if ( tArkilometers.size() > 0 )
			{
				TArkilometer arkilometer = tArkilometers.get( 0 );
				Date nowtime = new Date();
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
				String strNow = format.format( nowtime );
				String endTime = format.format( arkilometer.getDtEnd() );
				boolean flag = isDateBefore( strNow , endTime );
				if ( flag )
				{
					arorder.setNArkilometer( arkilometer.getNKilometer() );
					
				}
				else
				{
					message = "当前起始地城市【" + order.getVcStartCity() + "】与目的地城市【"
					        + order.getVcDestCity() + "】应收公里数过期，请维护";
					nextFlag = false;
				}
			}
			else
			{
				message = "当前起始地城市【" + order.getVcStartCity() + "】与目的地城市【"
				        + order.getVcDestCity() + "】应收公里数未维护";
				nextFlag = false;
			}
			
		}
		if ( nextFlag )
		{
			// 客户支付 总价=应收公里*应收单价*数量
			if ( order.getNPayType().equals( 1 ) )
			{
				arorder.setNTotalPrice( arorder.getNArkilometer()
				        * arorder.getNPrice() * arorder.getNQty() );
			}
			
			String[] driverIds = head.getVcDriverId().split( "," );
			List< TDriver > tDrivers = new ArrayList< TDriver >();
			for ( String driverId : driverIds )
			{
				TDriver tDriver = iDriverDao.get( Integer.parseInt( driverId ) );
				if ( tDriver != null )
				{
					tDrivers.add( tDriver );
				}
				
			}
			List< TArorderDriver > tArorderDrivers = new ArrayList< TArorderDriver >();
			for ( TDriver tDriver : tDrivers )
			{
				String[] propertys = { "iDriverId" , "nEnable" };
				Object[] valuelist = { tDriver.getId() ,
				        SystemConstants.SYS_ENABLE };
				// 司机工资系数
				List< TDriverSalaryCoefficient > tSalaryCoefficients = iDriverSalaryCofflisDao
				        .findByPropertys( propertys , valuelist );
				TArorderDriver tArorderDriver = new TArorderDriver();
				tArorderDriver.setiOrderId( line.getIOrderId() );
				tArorderDriver.setiShipHeadId( head.getId() );
				tArorderDriver.setiShipLineId( line.getId() );
				tArorderDriver.setnEnable( SystemConstants.SYS_ENABLE );
				tArorderDriver.setnQty( line.getNQty() );
				tArorderDriver.setVcSubno( subbo );
				tArorderDriver.setVcCarStyle( order.getVcCarName() );
				tArorderDriver.setVcEndCity( order.getVcDestCity() );
				tArorderDriver.setVcStartCity( order.getVcStartCity() );
				tArorderDriver.setnAudit( 0 );// 是否审核 (0 结算已审核 1结算未审核)
				tArorderDriver.setDtAudit( new Date() );
				tArorderDriver.setVcDriver( tDriver.getVcDriverName() );
				tArorderDriver.setiDriverId( tDriver.getId() );
				tArorderDriver.setVcShipNo( head.getVcShipno() );
				if ( tSalaryCoefficients.size() > 0 )
				{
					TDriverSalaryCoefficient tSalaryCoefficient = tSalaryCoefficients
					        .get( 0 );
					
					// 司机工资系数总和
					/*	float total_salaryCoefficient = tSalaryCoefficient
						        .getnPrice() * line.getNApkilometer();*/
					float total_salaryCoefficient = arorder.getNTotalPrice()
					        * tSalaryCoefficient.getnPrice();
					// 该司机的所有补贴
					List< TDriverSubsides > tDriverSubsides = getDriverSubsideBydriverId( tDriver
					        .getId() );
					if ( tDriverSubsides.size() > 0 )
					{
						float total_price = getTotalPrice(
						        total_salaryCoefficient , tDriverSubsides );
						tArorderDriver.setnTotalPrice( total_price );
					}
					else
					// 没有补贴
					{
						tArorderDriver.setnTotalPrice( total_salaryCoefficient );
					}
					
				}
				else
				{
					message = "司机:" + tDriver.getVcDriverName() + "工资系数没有维护";
					break;
				}
				//
				tArorderDrivers.add( tArorderDriver );
			}
			if ( message.equalsIgnoreCase( "success" ) )
			{
				try
				{
					line.setNarorder( 0 );// 表示已经结算
					shiplineDao.update( line );
					arorderDao.save( arorder );
					for ( TArorderDriver tArorderDriver : tArorderDrivers )
					{
						iArorderDriverDao.saveOrUpdate( tArorderDriver );
					}
				}
				catch ( Exception e )
				{
					message = "失败!原因：" + e.getMessage();
				}
			}
		}
		return message;
		
	}
	
	/**
	 * @Description: TODO(算出所有补贴)
	 * @param total_salaryCoefficient
	 * @param tDriverSubsides
	 * @return float 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-3 下午3:28:55
	 */
	private float getTotalPrice( float total_salaryCoefficient ,
	        List< TDriverSubsides > tDriverSubsides )
	{
		float total = 0;
		for ( TDriverSubsides tSubsides : tDriverSubsides )
		{
			total += tSubsides.getnNumber();
		}
		return total + total_salaryCoefficient;
	}
	
	/**
	 * @Description: TODO(通过司机ID找出对应补贴)
	 * @param id
	 * @return List<TDriverSubsides> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-3 下午2:59:28
	 */
	private List< TDriverSubsides > getDriverSubsideBydriverId( Integer id )
	{
		String[] propertyNames = { "iDriverId" , "nEnable" };
		Object[] values = { id , SystemConstants.SYS_ENABLE };
		List< TDriverSubsideLinks > tDriverSubsideLinks = iDriverSubsideLinksDao
		        .findByPropertys( propertyNames , values );
		List< TDriverSubsides > tDriverSubsides = new ArrayList< TDriverSubsides >();
		for ( TDriverSubsideLinks tdLinks : tDriverSubsideLinks )
		{
			TDriverSubsides tSubsides = iDriverSalarySubsideDao.get( tdLinks
			        .getiSubsidesId() );
			tDriverSubsides.add( tSubsides );
		}
		
		return tDriverSubsides;
	}
	
	/**
	 * @Description: TODO(判断当前是否在结束时间之前)
	 * @param strNow
	 * @param endTime
	 * @return boolean 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-15 下午4:48:19
	 */
	private boolean isDateBefore( String strNow , String endTime )
	{
		try
		{
			DateFormat df = DateFormat.getDateInstance();
			return df.parse( strNow ).before( df.parse( endTime ) );
		}
		catch ( Exception e )
		{
			return false;
		}
		
	}
	
	/**
	 * 
	 * @Description: 根据调度指令对结算审核确定
	 * @param shipno
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-21 下午5:53:23
	 */
	public void checkArorder( int headid , TUser us )
	{
		List< TArorder > arlist = arorderDao.findByProperty( "IShiphead" ,
		        headid );
		for ( TArorder ar : arlist )
		{
			ar.setNaudit( 0 );
			ar.setDtAudit( new Date() );
			ar.setVcAuditName( us.getVcUsername() );
			ar.setIAuditUser( us.getId() );
			arorderDao.update( ar );
		}
	}
	
	/**
	 * @Description: 保存结算应收 审核确定的
	 * @param user
	 * @param headids
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-24 下午6:24:36
	 */
	public void saveAuditArorder( TUser user , String headids )
	{
		String[] hids = headids.split( "," );
		for ( int i = 0 ; i < hids.length ; i++ )
		{
			int headid = Integer.parseInt( hids[i] );
			String hql = " from TArorder ark where ark.NEnable="
			        + SystemConstants.SYS_ENABLE + " and ark.IShiphead="
			        + headid;
			List< TArorder > arlist = arorderDao.find( hql );
			for ( TArorder ord : arlist )
			{
				ord.setNaudit( 0 );
				ord.setDtAudit( new Date() );
				ord.setIAuditUser( user.getId() );
				ord.setVcAuditName( user.getVcUsername() );
				arorderDao.update( ord );
			}
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ids
	 * @author liuwu
	 * @create_date 2014-12-31 下午1:51:33
	 */
	public void updateAudit( String[] ids )
	{
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int arorderId = Integer.parseInt( ids[i] );
			TArorder arorder = arorderDao.get( arorderId );
			int lineId = arorder.getIShipline();
			TShipline shipline = shiplineDao.get( lineId );
			// arorder.setNaudit( 1 );//未审核通过
			arorder.setNEnable( 1 );// 取消设为无效
			arorderDao.update( arorder );
			shipline.setNarorder( 1 );// 未结算
			shiplineDao.update( shipline );
			/**
			 * 取消审核时，分供方结算表无效，对应的司机结算也应无效
			 */
			String[] propertyNames = { "nEnable" , "iOrderId" };
			Object[] values = { SystemConstants.SYS_ENABLE ,
			        arorder.getIOrder() };
			List< TArorderDriver > arorderDrivers = iArorderDriverDao
			        .findByPropertys( propertyNames , values );
			for ( TArorderDriver tArorderDriver : arorderDrivers )
			{
				tArorderDriver.setnEnable( 1 );
				iArorderDriverDao.update( tArorderDriver );
			}
		}
		
	}
	
	/**
	 * @Description: TODO(生成对帐单)
	 * @param ids
	 * @author liuwu
	 * @create_date 2014-12-31 下午3:46:52
	 */
	public void updateCreateBill( String[] ids , TUser user )
	{
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int arorderId = Integer.parseInt( ids[i] );
			TArorder arorder = arorderDao.get( arorderId );
			arorder.setNCreateBill( 1 );// 是否生成对帐单(0:未生成，1已生成)
			arorder.setDtCreateBill( new Date() );
			arorder.setIBillUser( user.getId() );// 对帐人
			if ( arorder.getVcBillNo() == null )
			{
				String vcBillNo = getVcBillNO( arorderId );
				arorder.setVcBillNo( vcBillNo );
			}
			
			arorderDao.saveOrUpdate( arorder );
			/**
			 * 改变指令明细表状态
			 */
			/*TShipline tShipline = shiplineDao.get( arorder.getIShipline() );
			tShipline.setNCurrentStatus( SystemConstants.SYS_SUB_ARORDER );
			shiplineDao.update( tShipline );*/
			
			/**
			 * 司机支出审核列表的对账账
			 */
			String[] propertyNames = { "nEnable" , "iOrderId" };
			Object[] values = { SystemConstants.SYS_ENABLE ,
			        arorder.getIOrder() };
			List< TArorderDriver > arorderDrivers = iArorderDriverDao
			        .findByPropertys( propertyNames , values );
			for ( TArorderDriver tArorderDriver : arorderDrivers )
			{
				tArorderDriver.setVcBillUser( user.getVcAccount() );
				tArorderDriver.setDtCreateBill( new Date() );
				tArorderDriver.setnCreateBill( 1 );// 是否生成对帐单(0:未生成，1已生成)
				iArorderDriverDao.saveOrUpdate( tArorderDriver );
			}
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午3:56:35
	 */
	private String getVcBillNO( int arorderId )
	{
		String orderno = "DZ";
		String datestr = DateUtil.getDate( "yyMMddmmss" );
		DecimalFormat df = new DecimalFormat( "0000" );
		
		orderno += datestr + "_" + arorderId;
		return orderno;
	}
	
	/**
	 * @Description: TODO(确定对帐)
	 * @param ids
	 * @param user
	 * @author liuwu
	 * @create_date 2014-12-31 下午5:22:37
	 */
	public void updateCheckBill( String[] ids , TUser user )
	{
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int arorderId = Integer.parseInt( ids[i] );
			TArorder arorder = arorderDao.get( arorderId );
			arorder.setNBill( 1 );// 是否对帐(0:未对账，1已对帐)
			arorder.setDtBill( new Date() );// 对帐时间
			arorder.setICheckUser( user.getId() );// 对帐人
			arorderDao.saveOrUpdate( arorder );
			/**
			 * 司机支出审核列表的对账:当分供方对帐管理确认对账后，司机也默认已对账
			 */
			String[] propertyNames = { "nEnable" , "iOrderId" };
			Object[] values = { SystemConstants.SYS_ENABLE ,
			        arorder.getIOrder() };
			List< TArorderDriver > arorderDrivers = iArorderDriverDao
			        .findByPropertys( propertyNames , values );
			for(TArorderDriver tArorderDriver:arorderDrivers){
				tArorderDriver.setVcBillUser( user.getVcAccount() );
				tArorderDriver.setDtBill( new Date() );
				tArorderDriver.setnBill( 1 );
				iArorderDriverDao.saveOrUpdate( tArorderDriver );
			}
		}
		
	}
	
	/**
	 * @Description: TODO(取消对账单)
	 * @param ids
	 * @param user
	 * @author liuwu
	 * @create_date 2014-12-31 下午5:29:33
	 */
	public void updateCancelBill( String[] ids , TUser user )
	{
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int arorderId = Integer.parseInt( ids[i] );
			TArorder arorder = arorderDao.get( arorderId );
			arorder.setNCreateBill( 0 );// 是否生成对帐单(0:未生成，1已生成)
			arorderDao.saveOrUpdate( arorder );
			/**
			 * 对应的司机结算表的对账单也应取消
			 */
			String[] propertyNames = { "nEnable" , "iOrderId" };
			Object[] values = { SystemConstants.SYS_ENABLE ,
			        arorder.getIOrder() };
			List< TArorderDriver > arorderDrivers = iArorderDriverDao
			        .findByPropertys( propertyNames , values );
			for ( TArorderDriver tArorderDriver : arorderDrivers )
			{
				tArorderDriver.setVcBillUser( user.getVcAccount() );
				tArorderDriver.setDtBill( new Date() );
				tArorderDriver.setnCreateBill( 0 );
				iArorderDriverDao.saveOrUpdate( tArorderDriver );
			}
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-20 下午4:14:08
	 */
	public Map< String , Object > getSpringSql( String sql , Page page )
	{
		return arorderDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(同步订单表的信息)
	 * @param shiplineid
	 * @return
	 * @author liuwu
	 * @create_date 2015-8-24 下午2:57:40
	 */
	public String updateOrderInfo( int shiplineid )
	{
		TShipline tShipline = shiplineDao.get( shiplineid );
		TOrder order = orderDao.get( tShipline.getIOrderId() );
		boolean nextFlag = true;
		int startId = order.getIStartId();
		int endId = order.getIEndId();
		String message = "success";
		String vcSubno = order.getVcSubno();
		List< TArkilometer > tArkilometers = null;
		
		List< TDriverCost > tDriverCosts = null;
		if ( order.getICustomerId() != null )
		{
			tArkilometers = iArkilometerDao.findByPropertys( new String[] {
			        "IStartId" , "IEndId" , "vcSubno" , "NEnable" , "nCheck" ,
			        "iCustomerId" } , new Object[] { startId , endId , vcSubno ,
			        SystemConstants.SYS_ENABLE , 1 , order.getICustomerId() } );
			tDriverCosts = iDriverCostDao.findByPropertys( new String[] {
			        "IStartId" , "IEndId" , "ISubCarid" , "ICustomerid" ,
			        "vcSubno" , "NEnable" , "NCheck" } ,
			        new Object[] { startId , endId , order.getICarStyle() ,
			                order.getICustomerId() , vcSubno ,
			                SystemConstants.SYS_ENABLE , 1 } );
		}

		/**
		 * 验证应收单价有效性
		 */
		if ( order.getNPayType().equals( 1 ) )
		{// 支付方式(0 现金 1客户)
			if ( tDriverCosts != null && tDriverCosts.size() > 0 )
			{
				TDriverCost tDriverCost = tDriverCosts.get( 0 );
				Date nowtime = new Date();
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
				String strNow = format.format( nowtime );
				String endTime = format.format( tDriverCost.getDtEnd() );
				boolean flag = isDateBefore( strNow , endTime );
				if ( ! flag )
				{
					message = "当前起始地城市【" + order.getVcStartCity() + "】与目的地城市【"
					        + order.getVcDestCity() + "】对应车型【"
					        + order.getVcCarName() + "】应收单价过期，请维护！";
					nextFlag = false;
				}
				else
				{
					order.setNCost( tDriverCost.getnCost() );
				}
			}
			else
			{
				message = "当前起始地城市【" + order.getVcStartCity() + "】与目的地城市【"
				        + order.getVcDestCity() + "】对应车型【"
				        + order.getVcCarName() + "】应收单价未维护";
				nextFlag = false;
				return message;
			}
			
			/**
			 * 验证应收公里有效性
			 */
			if ( tArkilometers.size() > 0 )
			{
				TArkilometer arkilometer = tArkilometers.get( 0 );
				Date nowtime = new Date();
				SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
				String strNow = format.format( nowtime );
				String endTime = format.format( arkilometer.getDtEnd() );
				boolean flag = isDateBefore( strNow , endTime );
				if ( ! flag )

				{
					message = "当前起始地城市【" + order.getVcStartCity() + "】与目的地城市【"
					        + order.getVcDestCity() + "】应收公里数过期，请维护";
					nextFlag = false;
				}
				else
				{
					tShipline.setNApkilometer( arkilometer.getNKilometer() );
					// 总价= 单价*公里数*数量
					if ( order.getNPayType().equals( 1 ) ) // 支付方式(0 现金 1客户)
					{
						order.setNTotalPrice( order.getNCost()
						        * arkilometer.getNKilometer()
						        * tShipline.getNQty() );
					}

				}
			}
			else
			{
				message = "当前起始地城市【" + order.getVcStartCity() + "】与目的地城市【"
				        + order.getVcDestCity() + "】应收公里数未维护";
				nextFlag = false;
			}
		}
		
		if ( nextFlag )
		{
			orderDao.update( order );
			shiplineDao.update( tShipline );
		}

		return message;
	}
}
