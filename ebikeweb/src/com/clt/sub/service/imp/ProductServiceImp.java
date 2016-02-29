package com.clt.sub.service.imp;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.common.UserSession;
import com.clt.sub.dao.IBidWinDao;
import com.clt.sub.dao.ICityDao;
import com.clt.sub.dao.IKillInfoDao;
import com.clt.sub.dao.IProductCarStyleDao;
import com.clt.sub.dao.IProductDao;
import com.clt.sub.dao.ISubCarStyleDao;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TProduct;
import com.clt.sub.model.TProductCarStyle;
import com.clt.sub.model.TSubCarStyle;
import com.clt.sub.service.IKillInfoService;
import com.clt.sub.service.IOrderService;
import com.clt.sub.service.IProductCarStyleService;
import com.clt.sub.service.IProductService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class ProductServiceImp implements IProductService
{
	
	@Autowired
	IProductDao productDao;
	@Autowired
	IProductCarStyleService pCarStyleService;
	@Autowired
	IProductCarStyleDao carStyleDao;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	IOrderService orderService;
	@Autowired
	ICityDao cityDao;
	@Autowired
	private IKillInfoService killInfoService;
	@Autowired
	private ISubCarStyleDao subCarStyleDao;
	@Autowired
	private IKillInfoDao killInfoDao;
	@Autowired
	private IBidWinDao bidwinDao;
	@Autowired
	private JdbcTemplate jdbcDao;
	
	public TProduct get( Integer id )
	{
		return productDao.get( id );
	}
	
	public void update( TProduct entity )
	{
		productDao.update( entity );
	}
	
	public void save( TProduct entity )
	{
		// 生成秒杀编号
		entity.setVcKillno( this.getMaxKillNo() );
		productDao.save( entity );
	}
	
	public void saveOrUpdate( TProduct entity )
	{
		if ( null != entity.getId() )
		{
			// entity.setVcKillno( productDao.get( entity.getId()
			// ).getVcKillno() );
		}
		else
		{
			entity.setVcKillno( this.getMaxKillNo() );
		}
		// productDao.update( entity );
		productDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: 逻辑删除,并且把该产品下的车型信息也逻辑删除
	 * @param entity
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月25日 下午3:43:56
	 */
	public void delete( TProduct entity )
	{
		entity.setNEnable( SystemConstants.SYS_DISABLE );
		productDao.update( entity );
		pCarStyleService.deleteByProductId( entity.getId() );
	}
	
	/**
	 * @Description: 逻辑删除
	 * @param id
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月25日 下午3:44:15
	 */
	public void deleteByKey( Integer id )
	{
		TProduct product = productDao.get( id );
		if ( null != product )
		{
			this.delete( product );
		}
	}
	
	public List< TProduct > loadAll()
	{
		return productDao.loadAll();
	}
	
	public List< TProduct > findAll( Page page )
	{
		return productDao.findAll( page );
	}
	
	/**
	 * @Description: 保存秒杀信息以及秒杀对应的车型信息
	 * @param entity
	 * @param productCars
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:43:50
	 */
	public void saveOrUpdate( TProduct entity , List< TProductCarStyle > productCars )
	{
		if ( null != entity.getId() )
		{
			// entity.setVcKillno( productDao.get( entity.getId()
			// ).getVcKillno() );
		}
		else
		{
			entity.setVcKillno( this.getMaxKillNo() );
		}
		this.saveOrUpdate( entity );
		pCarStyleService.deleteByProductId( entity.getId() );
		for ( TProductCarStyle carStyle : productCars )
		{
			carStyle.setIProductId( entity.getId() );
			pCarStyleService.saveOrUpdate( carStyle );
		}
		
	}
	
	/**
	 * @Description: 获得今天正在秒杀的产品信息
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:52:45
	 */
	@SuppressWarnings( "unchecked" )
	public List< TProduct > findTodayProduct()
	{
		List< TProduct > todayProducts = null;
		Date today = new Date();
		String todayStr = DateUtil.formatTime( today );
		String queryString = " from TProduct t where t.dtKillStart>=" + todayStr
		        + " and t.dtKillEnd<=" + todayStr + " and t.NEnable="
		        + SystemConstants.SYS_ENABLE;
		todayProducts = productDao.find( queryString );
		return todayProducts;
	}
	
	/**
	 * @Description: 获得即将开始秒杀的产品信息 ，并按秒杀开始时间升序
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:52:45
	 */
	public List< TProduct > findComeKillProduct()
	{
		List< TProduct > cKillProducts = null;
		Date today = new Date();
		String todayStr = DateUtil.formatTime( today );
		String queryString = " from TProduct t where t.dtKillStart<" + todayStr
		        + "  and t.NEnable= " + SystemConstants.SYS_ENABLE;
		cKillProducts = productDao.find( queryString );
		return cKillProducts;
	}
	
	/**
	 * @Description: 获得秒杀的历史信息（已经过了秒杀的）
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:58:51
	 */
	public List< TProduct > findOutTimeKillProduct()
	{
		List< TProduct > OutTimeProducts = null;
		Date today = new Date();
		String todayStr = DateUtil.formatTime( today );
		String queryString = " from TProduct t where t.dtKillEnd<" + todayStr
		        + "  and t.NEnable= " + SystemConstants.SYS_ENABLE;
		OutTimeProducts = productDao.find( queryString );
		return OutTimeProducts;
	}
	
	/**
	 * @Description: 获得自己发布的秒杀信息
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午11:01:30
	 */
	public List< TProduct > findSelfReleaseProduct( TUser user )
	{
		return productDao.findByPropertys( new String[] { "NEnable" , "TUser" } ,
		        new Object[] { 0 , user } );
	}
	
	/**
	 * @Description: 生产分供方编号
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 下午3:20:23
	 */
	private synchronized String getMaxKillNo()
	{
		
		List< TProduct > orderlist = productDao.findAllAndOrderByProperty( "id" , false );
		String killno = "K";
		String datestr = DateUtil.getDate( "yyMMdd" );
		DecimalFormat df = new DecimalFormat( "0000" );
		String str2 = "";
		if ( orderlist.size() == 0 )
		{
			str2 = df.format( Integer.parseInt( "1" ) );
			
		}
		else
		{
			TProduct pro = orderlist.get( 0 );
			
			String str = pro.getVcKillno().substring( 7 , pro.getVcKillno().length() );
			str2 = df.format( Integer.parseInt( str ) + 1 );
		}
		
		killno += datestr + str2;
		System.out.println( "Max Order " + killno );
		return killno;
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		
		return productDao.findAllByHqlHelp( helper );
		
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper , String subNo )
	{
		
		Map< String , Object > result = productDao.findAllByHqlHelp( helper );
		
		List< TProduct > list = ( List< TProduct > ) result.get( "rows" );
		for ( TProduct m : list )
		{
			int productId = ( Integer ) m.getId();
			String price = String.valueOf( killInfoService.getPrice( subNo , productId ) );
			if ( "null".equals( price ) )
				price = "0";
			m.setBidPrice( price );
		}
		
		return result;
	}
	
	/**
	 * @Description: 根据sql和page获得列表信息
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月26日 上午9:59:28
	 */
	public Map< String , Object > findBySqlAndPage( String sql , Page page )
	{
		return productDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description 中标后生成临时合同（订单）
	 * @param productId
	 */
	public boolean saveTempAgreement( int productId )
	{
		TProduct entity = productDao.get( productId );
		// 中标者接受中标
		String sql = "select i.N_TOTAL_PRICE from t_kill_info i,t_bid_win w "
		        + "where w.i_kill_info=i.id and w.N_ACCEPT=0 and w.N_ENABLE="
		        + SystemConstants.SYS_ENABLE + "and w.I_PRODUCT=" + productId;
		float total = 0;// 总价
		List< Map< String , Object >> moneys = jdbcDao.queryForList( sql );
		if ( CollectionUtils.isNotEmpty( moneys ) )
		{
			total = Float.parseFloat( moneys.get( 0 ).get( "N_TOTAL_PRICE" ).toString() );
			total = ( float ) Math.floor( total * 100 ) / 100;// 截取小数点后2位，小数点后第3位舍去
		}
		if ( entity.getNBid() == 4 && entity.getNEnable() == SystemConstants.SYS_ENABLE )
		{
			String[] propertyNames = { "NEnable" , "IProductId" };
			Object[] values = { SystemConstants.SYS_ENABLE , productId };
			List< TProductCarStyle > list = carStyleDao.findByPropertys( propertyNames ,
			        values );
			// 获取车总数根据比较分配总价
			int totalCars = 0;
			for ( TProductCarStyle style : list )
			{
				totalCars += style.getNCarCount();
			}
			float money = 0;
			for ( int i = 0 ; i < list.size() ; i++ )
			{
				TProductCarStyle style = list.get( i );
				Map< String , Object > initOrder = initOrder( entity , total , list ,
				        totalCars , money , i , style );
				
				TOrder order = ( TOrder ) initOrder.get( "order" );
				money = ( Float ) initOrder.get( "money" );
				orderService.save( order );
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private Map< String , Object > initOrder( TProduct entity , float total ,
	        List< TProductCarStyle > list , int totalCars , float money , int i ,
	        TProductCarStyle style )
	{
		TOrder order = new TOrder();
		TUser user = ( TUser ) UserSession.get( "user" );
		String subno = "";
		if ( null != user )
		{
			order.setIUser( user.getId() );
			int subId = user.getiArchive();
			subno = subService.get( subId ).getVcSubno();
			order.setVcSubno( subno );
		}
		order.setVcOrderno( orderService.getMaxOrderNo() );// 生成订单号
		// order.setVcStartCity( entity.getVcStart() );// 起运地
		String vcStart = entity.getVcStart();
		order.setVcLoadAddress( vcStart );// 装货地址
		String stacity = "";
		if ( vcStart.indexOf( "市" ) != - 1 )
		{
			int begin = 0;
			if ( vcStart.indexOf( "省" ) != - 1 )
			{
				begin = vcStart.indexOf( "省" ) + 1;
			}
			stacity = vcStart.substring( begin , vcStart.indexOf( "市" ) + 1 );
		}
		else if ( vcStart.indexOf( "区" ) != - 1 )
		{
			stacity = vcStart.substring( 0 , vcStart.indexOf( "区" ) + 1 );
		}
		order.setVcStartCity( stacity );// 起运城市
		// order.setVcDestCity( entity.getVcEnd() );// 目的地
		String vcEnd = style.getVcDetailAddress();
		order.setVcReceiveAddress( vcEnd );// 收货地址
		String endcity = "";
		if ( vcEnd.indexOf( "市" ) != - 1 )
		{
			int begin = 0;
			if ( vcEnd.indexOf( "省" ) != - 1 )
			{
				begin = vcEnd.indexOf( "省" ) + 1;
			}
			endcity = vcEnd.substring( begin , vcEnd.indexOf( "市" ) + 1 );
		}
		else if ( vcEnd.indexOf( "区" ) != - 1 )
		{
			endcity = vcEnd.substring( 0 , vcEnd.indexOf( "区" ) + 1 );
		}
		order.setVcDestCity( endcity );// 目的城市
		order.setDtArrive( entity.getDtArriveTime() );// 到货时间
		order.setVcCarName( style.getVcCarStyle() );// 商品车名称
		order.setNTotalCar( style.getNCarCount() );// 数量
		order.setVcLatStart( entity.getVcLat() );// 起始点纬度
		order.setVcLongStart( entity.getVcLong() );// 起始点经度
		order.setVcLat( style.getVcLat() );// 到货点纬度
		order.setVcLong( style.getVcLong() );// 到货点经度
		order.setNProduct( 0 );// 抢单转换（0）
		order.setVcCustOrderNo( entity.getVcKillno() );// 客户订单号
		order.setICarStyleId( style.getId() );// 抢单车型Id
		order.setnPayCtyle( 0 );// 设置支付方式（现金）
		// 设置总价
		if ( i != list.size() - 1 )
		{
			int carNum = style.getNCarCount();
			float totalMoney = total * carNum / totalCars;
			float ordertotal = ( float ) Math.round( totalMoney * 100 ) / 100;
			order.setNTotalPrice( ordertotal );
			money += ordertotal;
		}
		else
		{
			order.setNTotalPrice( total - money );
		}
		String carStyle = style.getVcCarStyle();
		String[] propertys = { "vcCarStyle" , "NEnable" , "vcSubno" };
		Object[] vals = { carStyle , SystemConstants.SYS_ENABLE , subno };
		List< TSubCarStyle > carStyles = subCarStyleDao
		        .findByPropertys( propertys , vals );
		if ( CollectionUtils.isNotEmpty( carStyles ) )
		{
			TSubCarStyle subCar = carStyles.get( 0 );
			order.setICarStyle( subCar.getId() );
		}
		else
		{
			TSubCarStyle subCar = new TSubCarStyle();
			subCar.setVcCarStyle( carStyle );
			subCar.setVcSubno( subno );
			subCarStyleDao.save( subCar );
			order.setICarStyle( subCar.getId() );
		}
		Map< String , Object > map = new HashMap< String , Object >();
		map.put( "order" , order );
		map.put( "money" , money );
		return map;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param properties
	 * @param values
	 * @return
	 * @author liuwu
	 * @create_date 2015-8-17 下午3:55:44
	 */
	public List< TProduct > findByProperties( String[] properties , Object[] values )
	{
		// TODO Auto-generated method stub
		return productDao.findByPropertys( properties , values );
	}
	
	/**
	 * @Description: 获得秒杀的历史信息（已经过了秒杀的）,并且没有人参与秒杀的,并且是初始状态的抢单
	 * @return List<TProduct> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:58:51
	 */
	public List< TProduct > findOutTimeAndNotKillProduct()
	{
		List< TProduct > OutTimeProducts = null;
		Date today = new Date();
		String todayStr = DateUtil.formatTime( today );
		String queryString = " from TProduct t where t.dtKillEnd<" + todayStr
		        + "  and t.NEnable= " + SystemConstants.SYS_ENABLE
		        + " and t.NPersonTime=0  and t.NBid=1 ";
		OutTimeProducts = productDao.find( queryString );
		return OutTimeProducts;
	}
	
	/**
	 * 获取pc端历史订单
	 * 
	 * @return
	 */
	public Map< String , Object > getEndProduct( int userId , String vcKillno ,
	        String vcStart , String vcEnd , String isArrive , Page page )
	{
		String sql = "select s.ID,p.VC_KILLNO,p.VC_START,s.VC_DETAIL_ADDRESS,p.DT_KILL_START,"
		        + "p.DT_KILL_END,p.DT_ARRIVE_TIME,p.N_MIN_PRICE,p.N_MAX_PRICE,o.N_PAYTYPE,"
		        + "o.N_PAYCYCLE,o.VC_NOTE,l.N_CURRENT_STATUS"
		        + " from t_product p, t_product_car_style s, t_order o,t_shipline l"
		        + " where p.id = s.i_product_id and o.i_carstyle_id = s.id and l.i_order_id=o.id and p.DT_KILL_END<sysdate";
		if ( StringUtils.isNotBlank( vcKillno ) )
		{
			sql += " and p.vc_killno='" + vcKillno + "'";
		}
		if ( StringUtils.isNotBlank( vcStart ) )
		{
			sql += " and p.vc_start='" + vcStart + "'";
		}
		if ( StringUtils.isNotBlank( vcEnd ) )
		{
			sql += " and p.vc_end='" + vcEnd + "'";
		}
		if ( StringUtils.isNotBlank( isArrive ) )
		{
			if ( "Y".equalsIgnoreCase( isArrive ) )
			{
				sql += " and l.n_current_status>=18";
			}
			else
			{
				sql += " and l.n_current_status<18";
			}
		}
		sql += " and o.i_user=" + userId;
		System.out.println( "sql:" + sql );
		Map< String , Object > result = productDao.getSpringSQL( sql , page );
		return result;
	}
	
	// 通过抢单车型id获取订单列表
	public Map< String , Object > getOrdersByCarId( int productCarStyleId )
	{
		String sql = "select O.ID,O.VC_ORDERNO, O.VC_RECEIVE_ADDRESS, O.VC_CAR_NAME, O.N_TOTAL_CAR"
		        + " from t_product_car_style c, t_order O where O.i_carstyle_id = c.id"
		        + " and c.id =" + productCarStyleId;
		Map< String , Object > result = productDao.getSpringSQL( sql , null );
		return result;
	}
	
	// 根据orderId获取订单状态
	public Map< String , Object > getStatusByOid( int orderId )
	{
		String sql = "select ID,DT_STATUS,VC_STATUSNOTE from t_shipstatus where N_ORDERID="
		        + orderId + "order by DT_STATUS";
		Map< String , Object > result = productDao.getSpringSQL( sql , null );
		return result;
	}
	
	/**
	 * @Description: 根据抢单编号，获得抢单信息，修改对应抢单状态为 发布者评价。 适用于 发布者评价后，修改状态
	 * @param killNo
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月21日 下午3:50:48
	 */
	public void saveBidByKillNo( String killNo )
	{
		List< TProduct > properties = this.findByProperties( new String[] { "vcKillno" } ,
		        new Object[] { killNo } );
		
		if ( CollectionUtils.isNotEmpty( properties ) )
		{
			for ( TProduct tProduct : properties )
			{
				tProduct.setNBid( 5 );
				this.update( tProduct );
			}
		}
	}
}
