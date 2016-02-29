package com.clt.sub.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.clt.common.Geohash;
import com.clt.common.UserSession;
import com.clt.sub.model.TAssess;
import com.clt.sub.model.TBidWin;
import com.clt.sub.model.TCause;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TKillDetail;
import com.clt.sub.model.TKillInfo;
import com.clt.sub.model.TProduct;
import com.clt.sub.model.TProductCarStyle;
import com.clt.sub.model.TShipline;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.service.IAssessService;
import com.clt.sub.service.IBidWinService;
import com.clt.sub.service.ICauseService;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.IKillDetailService;
import com.clt.sub.service.IKillInfoService;
import com.clt.sub.service.IProductCarStyleService;
import com.clt.sub.service.IProductScopeService;
import com.clt.sub.service.IProductService;
import com.clt.sub.service.IShiplineService;
import com.clt.sub.service.ISpareCapacityService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserGps;
import com.clt.systemmanger.service.IMsgRecordService;
import com.clt.systemmanger.service.IStaticService;
import com.clt.systemmanger.service.IUserGpsService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.PushUtils;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.sub.controller
 * @Description: 秒杀action
 * @author hjx
 * @date 2014年8月7日 上午10:36:43
 * @version V1.0
 */
@Controller
@RequestMapping( "/seckillAction" )
@Api( value = "Seckill-api" , description = "有关于抢单的操作" , position = 5 )
public class SeckillAction
{
	@Autowired
	private IKillInfoService killInfoService;
	@Autowired
	private IKillDetailService killDetailService;
	@Autowired
	private IProductService productService;
	@Autowired
	private IProductCarStyleService pCarStyleService;
	@Autowired
	private ISubsuppliersService subService;
	@Autowired
	private IBidWinService winService;
	@Autowired
	private IProductScopeService pScopeService;
	@Autowired
	private IShiplineService shiplineService;
	@Autowired
	private ITruckDriverService truckDriverService;
	
	@Autowired
	private IUserGpsService gpsService;
	@Autowired
	private IUserService userService;
	@Autowired
	private Geohash geohash;
	@Autowired
	private IAssessService assessService;
	@Autowired
	private ICauseService causeService;
	@Autowired
	private IStaticService staticService;
	@Autowired
	private ISpareCapacityService spareService;
	@Autowired
	private IDriverService driverService;
	@Autowired
	private IMsgRecordService msgService;
	
	/**
	 * @Description: 打开秒杀订单生成页面（新增或编辑）
	 * @param orderId
	 * @param request
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月7日 上午10:48:50
	 */
	@RequestMapping( "/openSeckillOrder" )
	@ApiIgnore
	public String openSeckillOrder( HttpServletRequest request )
	{
		String productId = request.getParameter( "productId" );
		if ( StringUtils.isNotBlank( productId ) )
		{// 编辑情况
			TProduct product = productService.get( Integer.parseInt( productId ) );
			List< TProductCarStyle > list = pCarStyleService.getAllByProductId( Integer
			        .parseInt( productId ) );
			request.setAttribute( "product" , product );
			request.setAttribute( "list" , list );
			JSONArray jsonArray = new JSONArray();
			request.setAttribute( "index" , list.size() );
		}
		else
		{// 新增情况
			TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
			if ( null == user )
			{
				request.setAttribute( "msg" , "您未登录，不能进行下单，请先登陆！" );
				return "front/msg";// 提示页面
			}
			else
			{
				if ( subService.get( user.getiArchive() ).getNEnableOrder() == 1 )
				{
					request.setAttribute( "msg" , "您还没有下单资格，请在分供方信息完善中，进行申请下单资格！</a>" );
					return "seckill/order";// "front/msg";// 还没有下单的资格，跳转到提示页面
				}
			}
		}
		return "seckill/order";
	}
	
	/**
	 * @Description: 打开列表
	 * @return String 返回值描述
	 * @author hjx
	 * @throws UnsupportedEncodingException
	 * @create_date 2014年8月7日 上午10:51:14
	 */
	@RequestMapping( "/openList" )
	@ApiIgnore
	public String openList( HttpServletRequest request )
	        throws UnsupportedEncodingException
	{
		String vcStart = request.getParameter( "vcStart" );
		if ( StringUtils.isNotBlank( vcStart ) )
		{
			request.setAttribute( "vcStart" , URLDecoder.decode( vcStart , "UTF-8" ) );
		}
		String vcEnd = request.getParameter( "vcEnd" );
		if ( StringUtils.isNotBlank( vcEnd ) )
		{
			request.setAttribute( "vcEnd" , URLDecoder.decode( vcEnd , "UTF-8" ) );
		}
		return "front/seckillList";
	}
	
	/**
	 * @Description: 打开抢单页面，方便进行抢单
	 * @param request
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月13日 下午4:14:19
	 */
	@RequestMapping( "/grabSeckill" )
	@ApiIgnore
	public String grabSeckill( HttpServletRequest request )
	{
		String orderId = request.getParameter( "orderId" );
		TProduct product = productService.get( Integer.parseInt( orderId ) );
		List< TProductCarStyle > list = pCarStyleService.getAllByProductId( Integer
		        .parseInt( orderId ) );
		request.setAttribute( "product" , product );
		request.setAttribute( "list" , list );
		return "front/grab";
	}
	
	/**
	 * @Description: 获得抢单详情，手机端处理
	 * @param orderId
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月10日 下午6:45:53
	 */
	@ApiOperation( value = "获得抢单详情" , notes = "返回主表信息 product，和车型详情列表 list;<br/>product类的属性：</br>private Integer id;</br>"
	        + "private Integer IUserId; 用户id </br> "
	        + "private String vcStart; 出发地 </br> "
	        + "private String vcEnd; 目的地</br> "
	        + "private Date dtArriveTime; 抵达时间</br> "
	        + "private Float NKilometre;公里数</br> "
	        + "private Float NMinPrice;最低价格</br> "
	        + "private Date dtKillStart; 开枪时间 </br>"
	        + "private Date dtKillEnd; 结束时间</br> "
	        + "private String vcRequire; 特殊要求</br> "
	        + "private Integer NEnable; 是否有效</br> "
	        + "private String vcUserName; 发布人姓名</br> "
	        + "private String vcKillno; 抢单编号</br> "
	        + "private String vcSubno; 分供方编号</br> "
	        + "private Date dtRelease;</br>"
	        + "private Integer NBid; 是否中标</br>"
	        + "private Float NMaxPrice; 最高价</br>"
	        + "private Integer NScope;是否制定范围</br>"
	        + "<br/> list 里面参数说明：<br/>"
	        + "private Integer id;"
	        + "private String vcCarStyle;车型名称</br>"
	        + "private Integer NCarCount;数量</br>"
	        + "private Integer IProductId;对应抢单id</br>"
	        + "private Integer NEnable;是否有效<br/>"
	        + "private Float NPrice;价格</br>"
	        + "private Float NWeight;重量</br>"
	        + "private String vcNote;特殊要求</br>"
	        + "private Integer IProvince;目的地省份id</br>"
	        + "	private Integer ICity;目的地城市id</br>"
	        + "private String vcProvince;目的地省份名称</br>"
	        + "private String vcCity;目的地城市名称</br>"
	        + "private String vcDetailAddress;详细地址</br>"
	        + "</br>"
	        + "</br>"
	        + "list 参数说明：list是抢单里对应多个目的地和车型的总信息集合</br>"
	        + "private Integer id;</br> 抢单详情id"
	        + "private String vcCarStyle;车型名称</br> "
	        + "private Integer NCarCount;车型数量</br> "
	        + "private Integer IProductId;抢单id</br> "
	        + "private Integer NEnable;是否有效</br> "
	        + "private Float NPrice;价格</br> "
	        + "private Float NWeight;重量</br> "
	        + "private String vcNote;特别要求</br>"
	        + "private String vcDetailAddress;详情地址</br>"
	        + "private String vcLong;经度</br>"
	        + "private String vcLat;纬度</br>"
	        + "private String vcHash;geohash值</br>"
	        + "private Integer ICarStyle;商品车型id</br>"
	        + "</br>"
	        + "如果有可见范围，就传可见范围给前端  字段名称：scopeList</br>" , position = 5 )
	@RequestMapping( value = "/grabSeckillByApp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > grabSeckillByApp(
	        @ApiParam( value = "抢单主表id" , required = true ) @RequestParam( "orderId" ) int orderId )
	{
		Map< String , Object > map = new HashMap< String , Object >();
		TProduct product;
		List< TProductCarStyle > list;
		try
		{
			product = productService.get( orderId );
			List< Integer > scopeList = pScopeService.getScopeByProductId( orderId );
			list = pCarStyleService.getAllByProductId( orderId );
			if ( null != product )
			{
				map.put( "isSuccess" , true );
				map.put( "message" , "成功获得数据！" );
				map.put( "product" , product );
				map.put( "list" , list );
				// 如果有可见范围，就传可见范围给前端
				if ( null != scopeList )
				{
					map.put( "scopeList" , StringUtils.join( scopeList , "," ) );
				}
				
			}
			else
			{
				map.put( "isSuccess" , false );
				map.put( "message" , "没有获取到相关数据！" );
			}
		}
		catch ( NumberFormatException e )
		{
			map.put( "isSuccess" , false );
			map.put( "message" , "获取失败，失败原因：" + e.getMessage() );
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @Description: 竞价秒杀保存 秒杀信息和详情
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月14日 下午8:11:05
	 */
	@RequestMapping( value = "/getSeckill" , method = RequestMethod.POST )
	@ApiOperation( value = "点击抢单（即秒杀）时保存秒杀信息" , notes = "点击抢单（即秒杀）时保存秒杀信息，从会话获得当前用户？" , position = 5 )
	@ResponseBody
	public Map< String , Object > getSeckill(
	        @ApiParam( value = "总额" , required = true ) @RequestParam( "total" ) String total ,
	        @ApiParam( value = "抢单主键" , required = true ) @RequestParam( "productId" ) String productId ,
	        @ApiParam( value = "抢单详情表主键们，多个主键用英文逗号分隔拼装" , required = false ) @RequestParam( value = "ids" , required = false ) String ids ,
	        @ApiParam( value = "每个详情的对应价格用英文逗号分隔拼装，可以为空" , required = false ) @RequestParam( value = "prices" , required = false ) String prices ,
	        @ApiParam( value = "司机id" , required = false ) @RequestParam( value = "driverId" , required = false ) Integer driverId ,
	        @ApiParam( value = "司机到出发地的距离" , required = false ) @RequestParam( value = "distance" , required = false ) Double distance ,
	        HttpServletRequest request , HttpServletResponse response )
	{
		
		try
		{
			TKillInfo killInfo = new TKillInfo();
			killInfo.setDtBid( new Date() );
			int pId = Integer.parseInt( productId.trim() );
			killInfo.setIProductId( pId );
			TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
			if ( null == user )
			{
				user = ( TUser ) UserSession.get( "user" );
			}
			int userId = user.getId();
			// 判断是否发布运力并且发布运力时间在抢单时间内
			/*String[] propertyNames = { "NEnable" , "IUser" };
			Object[] values = { SystemConstants.SYS_ENABLE , userId };
			List< TSpareCapacity > spareCapacitys = spareService
			        .getByPropertys( propertyNames , values );
			// 判断是否发布过运力
			if ( CollectionUtils.isEmpty( spareCapacitys ) )
			{
				AjaxUtil.rendJson( response , false , "竞价失败，你没有发布过空闲运力" );
				return;
			}*/
			TProduct tProduct = productService.get( pId );
			if ( null == tProduct )
			{
				return AjaxUtil.getMap( false , "您传来的productId找不到对应信息，请核对！productId="
				        + productId );
			}
			// 判断发布运力是否在有效时间内
			/*
			 * 以下被注释的代码是第二期需求，暂时不用2015-8-3，hjx
			 * boolean isEnable = false;
			
			for ( TSpareCapacity spare : spareCapacitys )
			{
				Date dtBegin = spare.getDtBegin();
				Date dtEnd = spare.getDtEnd();
				if ( dtBegin != null && dtEnd != null
				        && System.currentTimeMillis() > dtBegin.getTime()
				        && System.currentTimeMillis() < dtEnd.getTime() )
				{
					isEnable = true;
					break;
				}
			}
			if ( ! isEnable )
			{
				AjaxUtil.rendJson( response , false , "竞价失败，你发布的空闲运力过期了" );
				return;
			}*/
			int archiveType = user.getIArchiveType();
			// 判断是否是分供方
			boolean isSub = isSub( archiveType );
			if ( isSub )
			{
				TSubsuppliers tSubsuppliers = subService.get( user.getiArchive() );
				killInfo.setVcSubno( tSubsuppliers.getVcSubno() );
				killInfo.setiSubId( tSubsuppliers.getId() );
				killInfo.setVcAllName( tSubsuppliers.getVcAllName() );
			}
			else
			{
				TDriver driver = driverService.get( user.getiArchive() );
				String subno = driver.getVcSubno();
				killInfo.setVcSubno( subno );
				killInfo.setiSubId( driver.getiUserId() );
				List< TSubsuppliers > tSubsuppliers = subService.findByProperty(
				        "vcSubno" , subno );
				if ( CollectionUtils.isNotEmpty( tSubsuppliers ) )
				{
					TSubsuppliers subSupplier = tSubsuppliers.get( 0 );
					killInfo.setVcAllName( subSupplier.getVcAllName() );
				}
				
			}
			killInfo.setNTotalPrice( Float.parseFloat( total ) );
			if ( null != driverId )
			{
				killInfo.setIDriver( driverId );
			}
			if ( null != distance )
			{
				killInfo.setNDistance( distance );
			}
			if ( StringUtils.isNotBlank( ids ) )
			{
				String[] idArr = StringUtils.split( ids , "," );
				String[] priceArr = StringUtils.split( prices , "," );
				List< TKillDetail > killDetails = new ArrayList< TKillDetail >();
				for ( int i = 0 ; i < idArr.length ; i++ )
				{
					TKillDetail detail = new TKillDetail();
					Integer carStyleId = Integer.parseInt( idArr[i] );
					Float price = Float.parseFloat( priceArr[i] );
					detail.setICarStyle( carStyleId );
					detail.setNPrice( price );
					killDetails.add( detail );
				}
				killInfoService.saveOrUpdate( killInfo , killDetails );
			}
			else
			{
				killInfoService.saveOrUpdate( killInfo );
			}
			if ( null == tProduct.getNPersonTime() )
			{
				tProduct.setNPersonTime( 1 );
			}
			else
			{
				int nPersonTime = tProduct.getNPersonTime();
				tProduct.setNPersonTime( nPersonTime + 1 );
			}
			
			String value = staticService.getStringByParame( "fireNumber" );
			if ( StringUtils.isNotBlank( value ) )
			{
				int intValue = Integer.parseInt( value );
				if ( tProduct.getNPersonTime() >= intValue )
				{
					tProduct.setNFire( 5 );
				}
				else
				{
					NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
					format.setMinimumFractionDigits( 1 );// 设置小数位
					
					String str = format.format( ( double ) tProduct.getNPersonTime()
					        / ( double ) intValue );
					double ceil = Math.ceil( Double.parseDouble( str ) / 2 );
					int c = ( int ) ceil;
					tProduct.setNFire( c );
				}
			}
			productService.update( tProduct );// 更新参与人次
			// 消息推送，消息推送给信息发布者
			int iuserId = tProduct.getIUserId();
			// 消息推送
			Map< String , String > map = new HashMap< String , String >();
			map.put( "msgType" , "1" );
			map.put( "id" , tProduct.getId() + "" );
			TUser bossUser = userService.getByid( iuserId + "" );
			List< TUser > tUsers = new ArrayList< TUser >();
			tUsers.add( bossUser );
			PushUtils pushUtils = new PushUtils( "有人竞价抢单，请点击查看" ,
			        "您发布的订单，有人开始进行竞标了，请点击查看！" , tUsers ,
			        "com.unlcn.carrier.bid.BidForOrderDetailActivity" , map );
			pushUtils.run();
			// 保存消息记录
			TMsgRecord tMsgRecord = new TMsgRecord();
			tMsgRecord.setIUser( user.getId() );// 添加人ID
			tMsgRecord.setVcAdduser( user.getVcAccount() );
			tMsgRecord.setIUserAccept( bossUser.getId() );
			tMsgRecord.setNMsgType( 1 );// 单发
			tMsgRecord.setVcContent( "您发布的订单，有人开始进行竞标了，请点击查看！" );
			tMsgRecord.setVcTitle( "有人竞价抢单，请点击查看" );
			msgService.save( tMsgRecord );
			
			return AjaxUtil.getMap( true , "竞价成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 打开 个人发布秒杀信息列表
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月21日 下午5:18:45
	 */
	@RequestMapping( "/openMyProduct" )
	@ApiIgnore
	public String openMyProduct()
	{
		return "seckill/mySeckillList";
	}
	
	/**
	 * @Description: 获得个人发布的秒杀产品(网页端)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author chengwzh
	 * @create_date 2015年9月14日 下午9:56
	 */
	@ApiIgnore
	@RequestMapping( value = "/getMyProduct" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getMyProduct(
	        @ApiParam( value = "类型：end为历史，begin为未开始，between为正在进行" , required = true ) @RequestParam( "type" ) String type ,
	        @ApiParam( value = "抢单编号" ) @RequestParam( value = "vcKillno" , required = false ) String vcKillno ,
	        @ApiParam( value = "路线起始点，查询条件" ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "路线终点，查询条件" ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        @ApiParam( value = "是否完成运抵（'Y'为完成，'N'为未完成），查询条件" ) @RequestParam( value = "isArrive" , required = false ) String isArrive ,
	        HttpServletRequest request )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		Page p = ServiceUtil.getcurrPage( request );
		TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
		// String visit = request.getParameter( SystemConstants.APP_VISIT_PARME
		// );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		try
		{
			if ( "end".equals( type ) )
			{
				result = productService.getEndProduct( user.getId() , vcKillno , vcStart ,
				        vcEnd , isArrive , p );
				JSONObject json = JSONObject.fromObject( result );
				System.out.println( "json:" + json );
			}
			else
			{
				HqlHelper helper = new HqlHelper( TProduct.class );
				helper.setQueryPage( p );
				helper.addEqual( "IUserId" , user.getId() );
				helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
				helper.addOrderBy( "id" , "desc" );
				// String type = request.getParameter( "vctype" );
				if ( "begin".equals( type ) )
				{
					helper.addGreatThan( "dtKillStart" , new Date() );
				}
				else if ( "between".equals( type ) )
				{
					Date date = new Date();
					helper.addGreatThan( "dtKillEnd" , date );
					helper.addLessThan( "dtKillStart" , date );
				}
				// else if ( "end".equals( type ) )
				// {
				// helper.addLessThan( "dtKillEnd" , new Date() );
				// }
				
				// 搜索参数
				// String vcKillno = request.getParameter( "vcKillno" );
				if ( StringUtils.isNotBlank( vcKillno ) )
				{
					helper.addLike( "vcKillno" , vcKillno );
				}
				// String vcStart = request.getParameter( "vcStart" );
				if ( StringUtils.isNotBlank( vcStart ) )
				{
					helper.addLike( "vcStart" , vcStart );
				}
				// String vcEnd = request.getParameter( "vcEnd" );
				if ( StringUtils.isNotBlank( vcEnd ) )
				{
					helper.addLike( "vcEnd" , vcEnd );
				}
				result = productService.findByHelper( helper );
			}
			return result;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 根据抢单车型id获取对应的车队订单
	 * 
	 * @return
	 */
	@RequestMapping( "/getOrdersByCarId" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getOrdersByCarId( int productCarStyleId )
	{
		try
		{
			Map< String , Object > result = productService
			        .getOrdersByCarId( productCarStyleId );
			return result;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMap( false , null );
		}
	}
	
	/**
	 * 根据orderId获取发运状态
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping( "/getStatusByOid" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getStatusByOid( int orderId )
	{
		try
		{
			Map< String , Object > result = productService.getStatusByOid( orderId );
			List< Map< String , Object >> rows = ( List< Map< String , Object >> ) result
			        .get( "rows" );
			// 获取订单对应的数量（发运数，抵达数..）
			List< TShipline > lines = shiplineService.findByOrderId( orderId );
			if ( CollectionUtils.isNotEmpty( lines ) )
			{
				TShipline line = lines.get( 0 );
				for ( Map< String , Object > map : rows )
				{
					String statusNote = ( ( String ) map.get( "VC_STATUSNOTE" ) ).trim();
					if ( "已配载".equals( statusNote ) )
					{
						map.put( "num" , line.getnShipQty() );
					}
					else if ( "确认接单".equals( statusNote ) )
					{
						map.put( "num" , line.getnShipQty() );
					}
					else if ( "已发运".equals( statusNote ) )
					{
						map.put( "num" , line.getnPickQty() );
					}
					else if ( "已运抵".equals( statusNote ) )
					{
						map.put( "num" , line.getnArriveQty() );
					}
				}
				result.put( "rows" , rows );
			}
			JSONArray arr = JSONArray.fromObject( result );
			System.out.println( "arr:" + arr );
			return result;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 获得个人发布的所有秒杀产品
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月8日 下午1:55:58
	 */
	@ApiOperation( value = "获得个人发布的所有抢单产品" , notes = "获得个人发布的所有抢单产品，从会话获得用户信息，参数信息：page第几页 默认为1；   rows 每页展示多少行，默认为10； sort 排列方式（升级或者降序） 可不填写； order 排列项， 可不填写" , position = 5 )
	@RequestMapping( value = "/getPersonalProduct" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getPersonalProduct(
	        @ApiParam( value = "类型：end为历史，begin为未开始，between为正在进行" , required = true ) @RequestParam( "type" ) String type ,
	        @ApiParam( value = "抢单编号" ) @RequestParam( value = "vcKillno" , required = false ) String vcKillno ,
	        @ApiParam( value = "路线起始点，查询条件" ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "路线终点，查询条件" ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		Page p = ServiceUtil.getcurrPage( request );
		TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		try
		{
			HqlHelper helper = new HqlHelper( TProduct.class );
			helper.setQueryPage( p );
			helper.addEqual( "IUserId" , user.getId() );
			helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
			helper.addOrderBy( "id" , "desc" );
			// String type = request.getParameter( "vctype" );
			if ( "end".equals( type ) )
			{
				helper.addLessThan( "dtKillEnd" , new Date() );
			}
			else if ( "begin".equals( type ) )
			{
				helper.addGreatThan( "dtKillStart" , new Date() );
			}
			else if ( "between".equals( type ) )
			{
				Date date = new Date();
				helper.addGreatThan( "dtKillEnd" , date );
				helper.addLessThan( "dtKillStart" , date );
			}
			
			// 搜索参数
			// String vcKillno = request.getParameter( "vcKillno" );
			if ( StringUtils.isNotBlank( vcKillno ) )
			{
				helper.addLike( "vcKillno" , vcKillno );
			}
			// String vcStart = request.getParameter( "vcStart" );
			if ( StringUtils.isNotBlank( vcStart ) )
			{
				helper.addLike( "vcStart" , vcStart );
			}
			// String vcEnd = request.getParameter( "vcEnd" );
			if ( StringUtils.isNotBlank( vcEnd ) )
			{
				helper.addLike( "vcEnd" , vcEnd );
			}
			result = productService.findByHelper( helper );
			
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 保存秒杀订单
	 * @param product
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月7日 下午6:28:14
	 */
	@ApiIgnore
	@RequestMapping( value = "/saveSeckKillOrder" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > saveSeckKillOrder(
	        @ApiParam( value = "抢单信息" ) TProduct product ,
	        HttpServletRequest request ,
	        @ApiParam( value = "目的地个数" ) @RequestParam( value = "size" , required = false ) String size ,
	        @ApiParam( value = "可见范围，subids" ) @RequestParam( value = "subIds" , required = false ) String subIds ,
	        HttpServletResponse response )
	{
		try
		{
			// 验证 当前时间是否超过 秒杀结束时间
			if ( DateUtils.truncate( new Date() , Calendar.MINUTE ).compareTo(
			        product.getDtKillEnd() ) < 0 )
			{
				// TUser user = ( TUser ) request.getSession( false
				// ).getAttribute( "user" );
				TUser user = ( TUser ) UserSession.get( "user" );
				product.setIUserId( user.getId() );
				product.setVcUserName( user.getVcUsername() );
				product.setVcSubno( subService.get( user.getiArchive() ).getVcSubno() );
				
				// String size = request.getParameter( "size" );
				// String subIds = request.getParameter( "subIds" );//
				// 如果是指定可见范围，有对应可见的分供方档案id
				if ( StringUtils.isNotBlank( size ) )
				{
					List< TProductCarStyle > list = new ArrayList< TProductCarStyle >();
					int index = Integer.parseInt( size );
					for ( int i = 0 ; i <= index ; i++ )
					{
						String vcCarStyle = request.getParameter( "vcCarStyle_" + i );
						String NCarCount = request.getParameter( "NCarCount_" + i );
						String NPrice = request.getParameter( "NPrice_" + i );
						String vcDetailAddress = request.getParameter( "vcDetailAddress_"
						        + i );
						
						String vcHash = request.getParameter( "vcHash_" + i );
						String vcLong = request.getParameter( "vcLong_" + i );
						String vcLat = request.getParameter( "vcLat_" + i );
						
						if ( StringUtils.isNotBlank( vcCarStyle )
						        && StringUtils.isNotBlank( NCarCount )
						        && StringUtils.isNotBlank( NPrice ) )
						{
							TProductCarStyle pCarStyle = new TProductCarStyle();
							pCarStyle.setVcCarStyle( vcCarStyle );
							pCarStyle.setNCarCount( Integer.parseInt( NCarCount ) );
							pCarStyle.setNPrice( Float.parseFloat( NPrice ) );
							pCarStyle.setVcLat( vcLat );
							pCarStyle.setVcLong( vcLong );
							pCarStyle.setVcHash( vcHash );
							pCarStyle.setVcDetailAddress( vcDetailAddress );
							list.add( pCarStyle );
						}
					}
					productService.saveOrUpdate( product , list );
					if ( product.getNScope() == 1 )// 如果指定可见范围，保存对应可见范围
					{
						pScopeService.save( product.getId() , subIds );
					}
				}
				else
				{
					productService.saveOrUpdate( product );
				}
				return AjaxUtil.getMap( true , "数据保存成功" );
			}
			else
			{
				return AjaxUtil.getMap( false , "数据保存失败，失败原因：已经超过了秒杀结束时间！" );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 打开秒杀信息（选择中标者）
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月21日 下午5:43:40
	 */
	@RequestMapping( "/openBidPage" )
	@ApiIgnore
	public String openBidPage()
	{
		return "seckill/bidList";
	}
	
	/**
	 * @Description: 获得所有的秒杀信息，按开始秒杀时间升序（结束时间大于现在的时间）
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月12日 下午4:34:01
	 */
	@RequestMapping( value = "/getSecKillList" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getSecKillList(
	        @ApiParam( value = "抢单编号" , required = false ) @RequestParam( value = "vcKillno" , required = false ) String vcKillno ,
	        @ApiParam( "路线起始点，查询条件" ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( "路线终点，查询条件" ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		Map< String , Object > result = new HashMap< String , Object >();
		Page p = ServiceUtil.getcurrPage( request );
		try
		{
			HqlHelper helper = new HqlHelper( TProduct.class );
			Date date = new Date();
			helper.addLessThan( "dtKillStart" , date );
			helper.addGreatThan( "dtKillEnd" , date );
			helper.addOrderBy( "dtKillStart" , "asc" );
			helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
			helper.setQueryPage( p );
			// String vcKillno = request.getParameter( "vcKillno" );
			if ( StringUtils.isNotBlank( vcKillno ) )
			{
				helper.addLike( "vcKillno" , vcKillno );
			}
			// String vcStart = request.getParameter( "vcStart" );
			if ( StringUtils.isNotBlank( vcStart ) )
			{
				helper.addLike( "vcStart" , vcStart );
			}
			// String vcEnd = request.getParameter( "vcEnd" );
			if ( StringUtils.isNotBlank( vcEnd ) )
			{
				helper.addLike( "vcEnd" , vcEnd );
			}
			System.out.println( helper.getHQL() );
			result = productService.findByHelper( helper );
			
			return AjaxUtil.getMapByResult( visit , result );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 获得当前秒杀产品的所有秒杀信息
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月15日 上午11:18:58
	 */
	@ApiOperation( value = "获得当前抢单产品的所有竞标信息" , notes = "参数信息：page第几页 默认为1；   rows 每页展示多少行，默认为10； sort 排列方式（升级或者降序） 可不填写； order 排列项， 可不填写<br>"
	        + "返回数据字段注释："
	        + "id: 竞标id <br/>"
	        + "vcSubno: 分供方编号 <br/>"
	        + "vcAllName: 分供方全称,<br/>"
	        + "dtBid: 竞标时间,<br/>"
	        + "iproductId: 所竞标的产品id,<br/>"
	        + "nenable: 是否有效,<br/>"
	        + "ntotalPrice: 出价,<br/>" + "ntype:  出价类型" , position = 5 )
	@RequestMapping( value = "/openSeckillList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > openSeckillList(
	        @ApiParam( value = "抢单信息id" , required = true ) @RequestParam( "productId" ) String productId ,
	        @ApiParam( value = "出价人分供方全名，可空，为查询条件" ) @RequestParam( value = "vcSubName" , required = false ) String vcSubName ,
	        @ApiParam( value = "竞标信息排列类型，手机端要传过来。type=1为按信誉，2为按距离，3按价格" ) @RequestParam( value = "type" , required = true , defaultValue = "1" ) int type ,
	        @ApiParam( value = "按信誉排序：不填<br/>"
	                + "按距离排序：0:全部, 1:5千米以内,2:10千米以内,3:20千米以内,4:30千米以内,5:30千米以外<br/>"
	                + "按价格排序：0:由高到底,1:由低到高" ) @RequestParam( value = "param" , required = false ) Integer param ,
	        HttpServletRequest request )
	{
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		Map< String , Object > result = new HashMap< String , Object >();
		// String productId = request.getParameter( "productId" );
		try
		{
			Page p = ServiceUtil.getcurrPage( request );
			HqlHelper helper = new HqlHelper( TKillInfo.class );
			// String vcSubName = request.getParameter( "vcSubName" );
			helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
			if ( StringUtils.isNotBlank( vcSubName ) )
			{
				helper.addLike( "vcAllName" , vcSubName );
			}
			helper.setQueryPage( p );
			helper.addEqual( "IProductId" , Integer.parseInt( productId ) );
			
			if ( 0 != type )
			{
				switch ( type )
					{
						case 1 :// 按信誉
							
							break;
						case 2 :// 按距离
							if ( param == 0 )
							{
								// 全部
							}
							else if ( param == 1 )
							{
								// 5千米以内
								helper.addLessEqualThan( "NDistance" , 5.0 );
							}
							else if ( param == 2 )
							{
								// 10千米以内
								helper.addLessEqualThan( "NDistance" , 10.0 );
							}
							else if ( param == 3 )
							{
								// 20千米以内
								helper.addLessEqualThan( "NDistance" , 20.0 );
							}
							else if ( param == 4 )
							{
								// 30千米以内
								helper.addLessEqualThan( "NDistance" , 30.0 );
							}
							else if ( param == 5 )
							{
								// 30千米以外
								helper.addGreatThan( "NDistance" , 30.0 );
							}
							else
							{
								return AjaxUtil.getMap( false ,
								        "参数输入不正确，只能是（0,1,2,3,4,5）" );
							}
							helper.addOrderBy( "NDistance" , "asc" );
							break;
						case 3 :// 按价格
							if ( param == 0 )
							{
								// 由高到底
								helper.addOrderBy( "NTotalPrice" , "desc" );
							}
							else if ( param == 1 )
							{
								// 由低到高
								helper.addOrderBy( "NTotalPrice" , "asc" );
							}
							else
							{
								return AjaxUtil.getMap( false , "参数输入不正确，只能是0或1" );
							}
							break;
						
						default :
							helper.addOrderBy( "dtBid" , "desc" );
							break;
					}
			}
			
			result = killInfoService.findByHelper( helper );
			JSONObject json = JSONObject.fromObject( result );
			System.out.println( "json:" + json );
			return AjaxUtil.getMapByResult( visit , result );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 选择中标人
	 * @param killInfoId
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月20日 下午4:33:52
	 */
	@ApiOperation( value = "选择中标人，也适合重新选择中标人" , notes = "选择中标人（也适合重新选择中标人），在某个抢单的竞标信息里，选择某个指定的竞标信息为中标" , position = 5 )
	@RequestMapping( value = "/chooseBider" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > chooseBider(
	        @ApiParam( value = "竞标信息id" , required = true ) @RequestParam( "killInfoId" ) String killInfoId ,
	        @ApiParam( value = "抢单信息id" , required = true ) @RequestParam( "productId" ) String productId )
	{
		TUser user = ( TUser ) UserSession.get( "user" );
		Integer kid = Integer.parseInt( killInfoId );
		TKillInfo killInfo = killInfoService.get( kid );
		TBidWin winer = new TBidWin();
		winer.setDtBid( new Date() );
		winer.setIKillInfo( kid );
		Integer pid = Integer.parseInt( productId );
		winer.setIProduct( pid );
		winer.setVcSubno( killInfo.getVcSubno() );
		winer.setVcAllName( killInfo.getVcAllName() );
		winer.setDtConfirm( new Date() );
		winer.setIConfirm( user.getId() );
		winer.setVcConfirmName( user.getVcUsername() );
		
		try
		{
			TProduct product = productService.get( pid );
			if ( 4 == product.getNBid() )
			{
				return AjaxUtil.getMap( false , "该抢单已经被中标者接受，不能在选表！" );
			}
			product.setNBid( SystemConstants.SYS_ENABLE );
			product.setNPersonTime( product.getNPersonTime() + 1 );
			productService.update( product );
			TBidWin bidWin = winService.getByProductid( product.getId() );// 如果是重新选表，把之前选表的信息设为无效
			if ( null != bidWin )
			{
				long between = new Date().getTime() - bidWin.getDtBid().getTime();
				long hour = between / ( 1000 * 60 * 60 * 60 );
				if ( hour > SystemConstants.SYS_BID_HOUR )
				{
					bidWin.setNEnable( SystemConstants.SYS_DISABLE );
					winService.update( bidWin );
				}
				else
				{
					return AjaxUtil.getMap( false , "您之前选择了中标方，未超过"
					        + SystemConstants.SYS_BID_HOUR + "小时，不能在选！" );
				}
			}
			winService.save( winer );
			/**
			 * 消息推送 推送给所选择的中标人
			 */
			Map< String , String > map = new HashMap< String , String >();
			map.put( "msgType" , "3" );
			String[] properties = { "IArchiveType" , "iArchive" };
			Object[] values = { SystemConstants.SYS_TARCHIVE_SUB , killInfo.getiSubId() };
			List< TUser > tUsers = userService.findByProperties( properties , values );
			
			PushUtils pushUtils = new PushUtils( "中标了，请点击查看" , "您参与的竞标订单，已经中标了，请点击查看！" ,
			        tUsers , "com.unlcn.carrier.release.ReleaseHistoryActivity" , map );
			pushUtils.run();
			// 保存消息记录
			for ( TUser tUser : tUsers )
			{
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( tUser.getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "您参与的竞标订单，已经中标了，请点击查看！" );
				tMsgRecord.setVcTitle( "中标了，请点击查看!" );
				msgService.save( tMsgRecord );
			}
			
			return AjaxUtil.getMap( true , "选标成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 查看发布订单 和 该订单的中标信息
	 * @param request
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月22日 上午11:32:25
	 */
	@RequestMapping( "/openSeckillBid" )
	@ApiIgnore
	public String openSeckillBid( HttpServletRequest request )
	{
		String productId = request.getParameter( "productId" );
		String type = request.getParameter( "atype" );
		if ( StringUtils.isNotBlank( type ) )
		{
			request.setAttribute( "atype" , type );
		}
		Integer pid = Integer.parseInt( productId );
		// 获得 产品信息
		TProduct product = productService.get( pid );
		request.setAttribute( "product" , product );
		List< TProductCarStyle > list = pCarStyleService.getAllByProductId( Integer
		        .parseInt( productId ) );
		request.setAttribute( "list" , list );
		TBidWin win = winService.getByProductid( pid );
		if ( null != win )
		{
			Integer killId = win.getIKillInfo();
			TKillInfo kill = killInfoService.get( killId );
			request.setAttribute( "kill" , kill );
		}
		
		return "seckill/orderDetail";
	}
	
	/**
	 * @Description: 删除产品已经其他基本信息
	 * @param productId
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月23日 下午3:02:38
	 */
	@RequestMapping( "/delByProductId" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > delByProductId(
	        @RequestParam( "productId" ) String productId , HttpServletResponse response )
	{
		try
		{
			productService.deleteByKey( Integer.parseInt( productId ) );
			return AjaxUtil.getMap( true , "删除成功！" );
		}
		catch ( NumberFormatException e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 查看订单的秒杀情况
	 * @param request
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月25日 下午2:39:10
	 */
	@RequestMapping( "/lookSeckill" )
	@ApiIgnore
	public String lookSeckill( HttpServletRequest request )
	{
		return "seckill/bidingList";
	}
	
	/**
	 * @Description: 打开我中标的列表页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月25日 下午4:20:06
	 */
	@RequestMapping( "/openMyBid" )
	@ApiIgnore
	public String openMyBid()
	{
		return "seckill/myBidList";
	}
	
	/**
	 * @Description: 获得我中标列表的数据
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月25日 下午4:21:38
	 */
	@ApiOperation( value = "获得我中标列表的数据" , notes = "参数信息：page第几页 默认为1；   rows 每页展示多少行，默认为10； sort 排列方式（升级或者降序） 可不填写； order 排列项， 可不填写" , position = 5 )
	@RequestMapping( value = "/getMyBidList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getMyBidList(
	        @ApiParam( value = "抢单编号" , required = false ) @RequestParam( value = "vcKillno" , required = false ) String vcKillno ,
	        @ApiParam( value = "路线起始点，查询条件" ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "路线终点，查询条件" ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		// 获得当前用户
		TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
		Page page;
		String sql;
		try
		{
			Integer subId = user.getiArchive();
			TSubsuppliers subsuppliers = subService.get( subId );
			String vcSubno = subsuppliers.getVcSubno();
			page = ServiceUtil.getcurrPage( request );
			sql = "SELECT t.id, t.vc_start, t.vc_end, t.dt_arrive_time, t.n_kilometre,"
			        + " t.n_min_price, t.dt_kill_start, t.dt_kill_end, t.vc_require, t.n_enable,"
			        + " t.i_user_id, t.vc_user_name, t.vc_subno, t.dt_release, t.vc_killno,t.vc_car_name,t.n_count,k.n_total_price,"
			        + " t.n_bid FROM t_product t ,T_BID_WIN w ,T_KILL_INFO k where t.id = w.i_product  and  w.I_KILL_INFO=k.id "
			        + " and k.i_product_id = t.id  and t.N_ENABLE="
			        + SystemConstants.SYS_ENABLE + " and w.N_ENABLE="
			        + SystemConstants.SYS_ENABLE + " and k.N_ENABLE="
			        + SystemConstants.SYS_ENABLE + "  and w.vc_subno ='" + vcSubno + "' ";
			
			// 添加搜索条件
			if ( StringUtils.isNotBlank( vcKillno ) )
			{
				sql += " and t.vc_killno like '%" + vcKillno + "%' ";
			}
			if ( StringUtils.isNotBlank( vcStart ) )
			{
				sql += " and t.vc_start like '%" + vcStart + "%' ";
			}
			if ( StringUtils.isNotBlank( vcEnd ) )
			{
				sql += " and t.vc_end like '%" + vcEnd + "%' ";
			}
			sql += " ORDER BY t.id DESC ";
			System.out.println( "sql =" + sql );
			result = productService.findBySqlAndPage( sql , page );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 获得所有的秒杀信息，按开始秒杀时间升序（结束时间大于现在的时间）
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月12日 下午4:34:01
	 */
	@ApiOperation( value = "获得抢单列表信息,按开始秒杀时间升序（结束时间大于现在的时间）" , notes = "参数信息：page第几页 默认为1；   rows 每页展示多少行，默认为10； sort 排列方式（升级或者降序） 可不填写； order 排列项， 可不填写;<br/>product类的属性：</br>private Integer id;</br>"
	        + "private Integer IUserId; 用户id </br> "
	        + "private String vcStart; 出发地 </br> "
	        + "private String vcEnd; 目的地</br> "
	        + "private Date dtArriveTime; 抵达时间</br> "
	        + "private Float NKilometre;公里数</br> "
	        + "private Float NMinPrice;最低价格</br> "
	        + "private Date dtKillStart; 开枪时间 </br>"
	        + "private Date dtKillEnd; 结束时间</br> "
	        + "private String vcRequire; 特殊要求</br> "
	        + "private Integer NEnable; 是否有效</br> "
	        + "private String vcUserName; 发布人姓名</br> "
	        + "private String vcKillno; 抢单编号</br> "
	        + "private String vcSubno; 分供方编号</br> "
	        + "private Date dtRelease;发布时间</br>"
	        + "private Integer NBid; 是否中标（0为中标，1为没中标）</br>"
	        + "private Float NMaxPrice; 最高价</br>"
	        + "private Integer NScope;发布范围类型（0：全部，1为指定范围）</br>"
	        + "private Integer NCount;车型数量</br>"
	        + "private String vcPicPath;图标路径</br>"
	        + "private String vcPicDesc;图标描述</br>"
	        + "private String vcCarName; 车型名称</br>"
	        + "private Integer NFire;火热程度</br>"
	        + "</br>" , position = 5 )
	@RequestMapping( value = "/getSecKillListByApp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getSecKillListByApp(
	        @ApiParam( value = "抢单编号" , required = false ) @RequestParam( value = "vcKillno" , required = false ) String vcKillno ,
	        @ApiParam( "路线起始点，查询条件" ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( "路线终点，查询条件" ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		Map< String , Object > result = new HashMap< String , Object >();
		Page p = ServiceUtil.getcurrPage( request );
		try
		{
			HqlHelper helper = new HqlHelper( TProduct.class );
			Date date = new Date();
			helper.addLessThan( "dtKillStart" , date );
			helper.addGreatThan( "dtKillEnd" , date );
			helper.addOrderBy( "dtKillStart" , "asc" );
			
			helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
			helper.setQueryPage( p );
			
			// String vcKillno = request.getParameter( "vcKillno" );
			if ( StringUtils.isNotBlank( vcKillno ) )
			{
				helper.addLike( "vcKillno" , vcKillno );
			}
			// String vcStart = request.getParameter( "vcStart" );
			if ( StringUtils.isNotBlank( vcStart ) )
			{
				helper.addLike( "vcStart" , vcStart );
			}
			// String vcEnd = request.getParameter( "vcEnd" );
			if ( StringUtils.isNotBlank( vcEnd ) )
			{
				helper.addLike( "vcEnd" , vcEnd );
			}
			// 获得当前用户的 分供方编号id，并通过该编号id获得该分供方所能查看的所有限制抢单信息
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			helper.addNotEqual( "IUserId" , user.getId() );// 抢单列表里不能查看自己发布的
			Integer subId = user.getiArchive();
			List< Integer > productids = pScopeService.getProductIdsBySubId( subId );
			if ( CollectionUtils.isNotEmpty( productids ) )
			{
				
				HqlHelper or1 = new HqlHelper( TProduct.class );
				or1.addEqual( "NScope" , SystemConstants.SYS_ENABLE );
				HqlHelper or2 = new HqlHelper( TProduct.class );
				or2.addEqual( "NScope" , 1 );
				or2.addIn( "id" , productids );
				helper.addArrayOR( or1 , or2 );
			}
			else
			{
				helper.addEqual( "NScope" , SystemConstants.SYS_ENABLE );
			}
			result = productService.findByHelper( helper );
			
			return AjaxUtil.getMapByResult( visit , result );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 获得当前分供方下所有司机和距离
	 * 
	 * @param session
	 * @return
	 */
	@ApiOperation( value = "获得当前分供方下所有司机和距离" , notes = "获得当前分供方下所有司机和距离" , position = 5 )
	@RequestMapping( value = "/getDriverAndDistance" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDriverAndDistance( HttpSession session ,
	        @ApiParam( "抢单主表id" ) @RequestParam( "productId" ) int productId )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null != user )
		{
			try
			{
				List< Map< String , Object >> dataList = new ArrayList< Map< String , Object > >();
				Integer iArchiveType = user.getIArchiveType();
				
				TProduct tProduct = productService.get( productId );
				String longStr1 = tProduct.getVcLong();
				String latStr1 = tProduct.getVcLat();
				// double long1 = Double.parseDouble( tProduct.getVcLong() );
				// double lat1 = Double.parseDouble( tProduct.getVcLat() );
				
				if ( SystemConstants.SYS_TARCHIVE_SUB == iArchiveType )
				{// 如果当前用户是分供方
					int subid = user.getiArchive();
					TSubsuppliers sub = subService.get( subid );
					
					String subno = sub.getVcSubno();
					List< TDriver > list = truckDriverService.getDriverBysubno( subno );
					// TODO-hjx 获得产品信息，从而获得对应 出发地坐标
					if ( CollectionUtils.isNotEmpty( list ) )
					{
						
						for ( TDriver driver : list )
						{
							int driverId = driver.getId();
							TUser duser = userService.getUserByDriverId( driverId );
							String longStr2 = null;
							String latStr2 = null;
							if ( duser != null )
							{
								TUserGps gps = gpsService.getGpsByUserid( duser.getId() );
								if ( gps != null )
								{
									longStr2 = gps.getVcLong();
									latStr2 = gps.getVcLat();
								}
							}
							// TODO-hjx 通过出发地坐标和当前用户最新坐标计算出当前用户的距离
							Map< String , Object > map = new HashMap< String , Object >();
							
							// double long2 = Double.parseDouble(
							// gps.getVcLong() );
							// double lat2 = Double.parseDouble( gps.getVcLat()
							// );
							if ( StringUtils.isNotBlank( longStr1 )
							        && StringUtils.isNotBlank( latStr1 )
							        && StringUtils.isNotBlank( longStr2 )
							        && StringUtils.isNotBlank( latStr2 ) )
							{
								double long1 = Double.parseDouble( longStr1 );
								double lat1 = Double.parseDouble( latStr1 );
								double long2 = Double.parseDouble( longStr2 );
								double lat2 = Double.parseDouble( latStr2 );
								double distance = ServiceUtil.getDistance( long1 , lat1 ,
								        long2 , lat2 );
								map.put( "distance" , distance );
							}
							else
							{
								map.put( "distance" , 0 );
							}
							map.put( "driverId" , driver.getId() );
							map.put( "driverName" , driver.getVcDriverName() );
							dataList.add( map );
						}
					}
					else
					{
						return AjaxUtil.getMap( false , "当前承运方没有司机!" );
					}
				}
				else if ( SystemConstants.SYS_TARCHIVE_DRIVER == iArchiveType )
				{// 如果当前用户是司机，就只有他一个司机和距离数据返回
					TDriver driver = driverService.get( user.getiArchive() );
					
					TUserGps gps = gpsService.getGpsByUserid( user.getId() );
					// TODO-hjx 通过出发地坐标和当前用户最新坐标计算出当前用户的距离
					// double long2 = Double.parseDouble( gps.getVcLong() );
					// double lat2 = Double.parseDouble( gps.getVcLat() );
					Map< String , Object > map = new HashMap< String , Object >();
					String longStr2 = null;
					String latStr2 = null;
					if ( gps != null )
					{
						longStr2 = gps.getVcLong();
						latStr2 = gps.getVcLat();
					}
					if ( StringUtils.isNotBlank( longStr1 )
					        && StringUtils.isNotBlank( latStr1 )
					        && StringUtils.isNotBlank( longStr2 )
					        && StringUtils.isNotBlank( latStr2 ) )
					{
						double long1 = Double.parseDouble( longStr1 );
						double lat1 = Double.parseDouble( latStr1 );
						double long2 = Double.parseDouble( longStr2 );
						double lat2 = Double.parseDouble( latStr2 );
						double distance = ServiceUtil.getDistance( long1 , lat1 , long2 ,
						        lat2 );
						map.put( "distance" , distance );
					}
					else
					{
						map.put( "distance" , 0 );
					}
					map.put( "driverId" , driver.getId() );
					map.put( "driverName" , driver.getVcDriverName() );
					dataList.add( map );
				}
				return AjaxUtil.getMapByNotException( true , dataList );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				return AjaxUtil.getMapByException( e );
			}
			
		}
		return AjaxUtil.getMapByNotException( false , null );
	}
	
	/**
	 * 中标者进行 接受中标 或者废标的操作
	 * 
	 * @param productId
	 *            产品id
	 * @param result
	 *            2为废标，4为接受
	 */
	@RequestMapping( value = "/resureBid" , method = RequestMethod.POST )
	@ApiOperation( value = "中标者进行 接受中标  或者废标的操作" , notes = "中标者进行 接受中标  或者废标的操作" , position = 5 )
	@ResponseBody
	public Map< String , Object > resureBid(
	        @ApiParam( value = "产品id" ) @RequestParam( "productId" ) int productId ,
	        @ApiParam( value = "2为废标，4为接受" ) @RequestParam( "result" ) int result ,
	        @ApiParam( value = "废表原因id" ) @RequestParam( value = "cause" , required = false ) String cause ,
	        @ApiParam( value = "会话信息，手机端不用考虑" ) HttpSession session )
	{
		try
		{
			TProduct tProduct = productService.get( productId );
			tProduct.setNBid( result );
			
			TBidWin win = winService.getByProductid( tProduct.getId() );
			// 验证是否已废标或接受中标
			int nAccept = win.getNAccept();
			if ( nAccept == 0 )
			{
				return AjaxUtil.getMap( false , "已接受中标，不能重复操作" );
			}
			if ( nAccept == 2 )
			{
				return AjaxUtil.getMap( false , "已废标，不能重复操作" );
			}
			TUser user = ( TUser ) session.getAttribute( "user" );
			switch ( result )
				{
					case 2 :
						win.setDtAbandon( new Date() );
						if ( StringUtils.isNotBlank( cause ) )
						{
							win.setICause( Integer.parseInt( cause ) );
						}
						win.setNAccept( 2 );
						win.setNEnable( SystemConstants.SYS_DISABLE );
						
						break;
					case 4 :
						
						win.setNAccept( 0 );
						break;
					default :
						
						break;
				}
			
			win.setIConfirm( user.getId() );
			win.setVcConfirmName( user.getVcUsername() );
			win.setDtConfirm( new Date() );
			winService.update( win );
			productService.saveOrUpdate( tProduct );
			if ( 2 == result )
			{
				/**
				 * 消息推送 推送给发布者
				 */
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "5" );
				String[] properties = { "IArchiveType" , "iArchive" };
				Object[] values = { SystemConstants.SYS_TARCHIVE_SUB ,
				        tProduct.getIUserId() };
				List< TUser > tUsers = userService.findByProperties( properties , values );
				
				PushUtils pushUtils = new PushUtils( "您选择的中标者弃标了，请点击查看" , "用户【"
				        + user.getVcAccount() + "】废标！" , tUsers ,
				        "com.unlcn.carrier.release.ReleaseHistoryActivity" , map );
				pushUtils.run();
				// 保存消息记录
				for ( TUser tUser : tUsers )
				{
					TMsgRecord tMsgRecord = new TMsgRecord();
					tMsgRecord.setIUser( user.getId() );// 添加人ID
					tMsgRecord.setVcAdduser( user.getVcAccount() );
					tMsgRecord.setIUserAccept( tUser.getId() );
					tMsgRecord.setNMsgType( 1 );// 单发
					tMsgRecord.setVcContent( "您选择的中标者弃标了，请点击查看" );
					tMsgRecord.setVcTitle( "用户【" + user.getVcAccount() + "】废标！" );
					msgService.save( tMsgRecord );
				}
			}
			else if ( 4 == result )
			{
				boolean isTrue = productService.saveTempAgreement( productId );
				if ( isTrue )
				{
					return AjaxUtil.getMap( true ,
					        "接受中标，并转为订单，客户运单号：" + tProduct.getVcKillno() );
				}
				else
				{
					return AjaxUtil.getMap( false , "接受中标，但转订单失败" );
				}
			}
			
			return AjaxUtil.getMap( true , "操作成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 发布抢单--app专用接口
	 * 
	 * @param product
	 * @param request
	 * @param array
	 * @param subIds
	 * @param response
	 * @return
	 */
	@ApiOperation( value = "发布抢单--app专用接口" , notes = "发布抢单，输入用form提交，已经一个TProduct对象，该接口要交流;参数：subIds 可见范围ids,用英文逗号拼接字符串;" , position = 5 , response = TProduct.class )
	@RequestMapping( value = "/saveSeckKill" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > saveSeckKill(
	        @ApiParam( value = "抢单信息" ) @ModelAttribute TProduct product ,
	        HttpServletRequest request ,
	        @ApiParam( value = "多车型json字符串" ) @RequestParam( value = "array" , required = false ) String array ,
	        @ApiParam( value = "可见范围，subids" ) @RequestParam( value = "subIds" , required = false ) String subIds )
	{
		try
		{
			// 验证 当前时间是否超过 秒杀结束时间
			if ( DateUtils.truncate( new Date() , Calendar.MINUTE ).compareTo(
			        product.getDtKillEnd() ) < 0 )
			{
				// TUser user = ( TUser ) request.getSession( false
				// ).getAttribute( "user" );
				if ( product.getDtKillEnd().compareTo( product.getDtKillStart() ) < 0 )
				{
					return AjaxUtil.getMap( false , "结束时间要大于开始时间！" );
				}
				TUser user = ( TUser ) UserSession.get( "user" );
				product.setIUserId( user.getId() );
				product.setVcUserName( user.getVcAccount() );
				product.setVcSubno( subService.get( user.getiArchive() ).getVcSubno() );
				// 编辑时，手机端缺少传过来的值，赋予默认值--begin
				if ( null == product.getNBid() )
				{
					product.setNBid( 1 );
				}
				
				if ( null == product.getNKilometre() )
				{
					product.setNKilometre( 0f );
				}
				if ( null == product.getNPersonTime() )
				{
					product.setNPersonTime( 0 );
				}
				if ( null == product.getNEnd() )
				{
					product.setNEnd( 1 );
				}
				if ( null == product.getNFire() )
				{
					product.setNFire( 0 );
				}// end 赋予默认值结束
				
				String vcHash = product.getVcHash();
				if ( StringUtils.isBlank( vcHash ) )
				{
					vcHash = geohash.encode( Double.parseDouble( product.getVcLat() ) ,
					        Double.parseDouble( product.getVcLong() ) );
					product.setVcHash( vcHash );
				}
				
				// 从新发布，把之前秒杀信息清除掉
				if ( null != product.getId() )
				{
					killInfoService.delByProductId( product.getId() );
				}
				
				// 如果是指定可见范围，有对应可见的分供方档案id
				if ( StringUtils.isNotBlank( array ) )
				{
					
					List< TProductCarStyle > list = JSON.parseArray( array ,
					        TProductCarStyle.class );
					if ( CollectionUtils.isNotEmpty( list ) )
					{
						TProductCarStyle tProductCarStyle = list.get( 0 );
						int count = tProductCarStyle.getNCarCount();
						product.setNCount( count );
						String vcCarStyle = tProductCarStyle.getVcCarStyle();
						if ( StringUtils.isNotBlank( vcCarStyle ) )
						{
							// 设置 产品的车型图片和描述
							product.setVcPicPath( "" );
							product.setVcPicDesc( "暂无" );
							product.setVcCarName( vcCarStyle );
						}
					}
					productService.saveOrUpdate( product , list );
					if ( product.getNScope() == 1 )// 如果指定可见范围，保存对应可见范围
					{
						pScopeService.save( product.getId() , subIds );
					}
				}
				else
				{
					productService.saveOrUpdate( product );
				}
				return AjaxUtil.getMap( true , "数据保存成功" );
			}
			else
			{
				return AjaxUtil.getMap( false , "数据保存失败，失败原因：结束时间小于当前时间！" );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 确认废标操作 （2为中标者废标，3为发布者废标）
	 * @param productId
	 * @autor chengwzh
	 * @date 2015/5/8 9:36
	 */
	@ApiOperation( value = "确认废标" , notes = "确认废标" , response = TProduct.class )
	@RequestMapping( value = "/confirmOblishBid" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > confirmOblishBid(
	        @ApiParam( value = "产品id，必填" ) @RequestParam( value = "productId" ) int productId ,
	        HttpServletRequest request )
	{
		TProduct product = productService.get( productId );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );// 确认废标人
		int NBid = product.getNBid();
		if ( NBid != 2 )
		{
			return AjaxUtil.getMap( false , "确认废标失败:中标者未废标，发布者不能确认废标！" );
		}
		try
		{
			product.setNEnable( SystemConstants.SYS_DISABLE );
			product.setNBid( 3 );
			productService.update( product );
			// 根据productId获取对应的List<TProductCarStyle>
			List< TProductCarStyle > pCarStyles = ( List< TProductCarStyle > ) pCarStyleService
			        .getByProductId( String.valueOf( productId ) );
			// 将List<TProductCarStyle>设置为无效
			for ( TProductCarStyle pCarStyle : pCarStyles )
			{
				pCarStyle.setNEnable( SystemConstants.SYS_DISABLE );
				pCarStyleService.update( pCarStyle );
			}
			// 消息推送，废标消息推送给发布者
			int iuserId = product.getIUserId();
			// 消息推送
			Map< String , String > map = new HashMap< String , String >();
			map.put( "msgType" , "4" );
			
			TUser bossUser = userService.getByid( iuserId + "" );
			List< TUser > tUsers = new ArrayList< TUser >();
			tUsers.add( bossUser );
			PushUtils pushUtils = new PushUtils( "有人废标，请点击查看" , "您发布的竞标信息，编号为"
			        + product.getVcKillno() + "，用户【" + user.getVcAccount()
			        + "】废标了，请点击查看！" , tUsers ,
			        "com.unlcn.carrier.release.ReleaseHistoryActivity" , map );
			pushUtils.run();
			// 保存消息记录
			TMsgRecord tMsgRecord = new TMsgRecord();
			tMsgRecord.setIUser( user.getId() );// 添加人ID
			tMsgRecord.setVcAdduser( user.getVcAccount() );
			tMsgRecord.setIUserAccept( bossUser.getId() );
			tMsgRecord.setNMsgType( 1 );// 单发
			tMsgRecord.setVcContent( "您发布的竞标信息，编号为【" + product.getVcKillno() + "】，用户【"
			        + user.getVcAccount() + "】废标了，请点击查看！" );
			tMsgRecord.setVcTitle( "有人废标，请点击查看" );
			msgService.save( tMsgRecord );
			return AjaxUtil.getMap( true , "确认废标成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 获得我参与的投标记录
	 * 
	 * @return
	 */
	@ApiOperation( value = "获得我参与的投标记录" , notes = "获得我参与的投标记录,展示该用户参与投标过的抢单信息" , position = 5 )
	@RequestMapping( value = "/getMyParticipation" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getMyParticipation(
	        @ApiParam( "会话信息，手机端不考虑这个参数" ) HttpSession session ,
	        @ApiParam( "路线起始点，查询条件" ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( "路线终点，查询条件" ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TProduct.class );
		if ( StringUtils.isNotBlank( vcStart ) )
		{
			hql.addLike( "vcStart" , vcStart );
		}
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		if ( StringUtils.isNotBlank( vcEnd ) )
		{
			hql.addLike( "vcEnd" , vcEnd );
		}
		
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			String vcSubno = subService.get( user.getiArchive() ).getVcSubno();
			
			List< Integer > productIds = killInfoService.getProductIdsBySubNo( vcSubno );// 获得当前承运方参与投标的记录
			
			if ( CollectionUtils.isNotEmpty( productIds ) )
			{
				hql.addIn( "id" , productIds );
				hql.setQueryPage( page );
				
				Map< String , Object > result = productService.findByHelper( hql ,
				        vcSubno );
				
				return AjaxUtil.getMapByResult( visit , result );
			}
			else
			{
				// 如果当前承运方没有投标，则返回空信息
				Map< String , Object > result = new HashMap< String , Object >();
				result.put( "total" , 0 );
				result.put( "rows" , null );
				
				return AjaxUtil.getMapByResult( visit , result );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description : 保存抢单评价,发布者评价
	 * @param:request
	 * @param:response
	 * @param:entity
	 * @author
	 * @date
	 */
	@RequestMapping( value = "/saveEvaluateOrder" , method = RequestMethod.POST )
	@ApiOperation( value = "保存抢单评价" , notes = "保存抢单评价" , position = 5 , response = TAssess.class )
	@ResponseBody
	public Map< String , Object > saveEvaluateOrder(
	        @ApiParam( value = "该字段，不用管" ) HttpSession session ,
	        @ModelAttribute TAssess entity )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		String addUser = user.getVcUsername();// 获取添加人
		Integer subId = user.getiArchive();
		String subNO = subService.get( subId ).getVcSubno();// 获取分供方编号
		entity.setVcAdduser( addUser );
		entity.setVcSubno( subNO );
		try
		{
			assessService.save( entity );
			// 评价成功后，给抢单信息更该状态。hjx
			String vcBussinesNo = entity.getVcBussinesNo();
			if ( StringUtils.isNotBlank( vcBussinesNo ) )
			{
				productService.saveBidByKillNo( vcBussinesNo );
			}
			return AjaxUtil.getMap( true , "保存成功" );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 获得废标原因
	 * 
	 * @param type
	 * @param visit
	 * @return
	 */
	@ApiOperation( value = "获得废标原因" , notes = "获得废标原因，可获得中标者废标原因列表，和发布者废标的原因" , position = 5 )
	@RequestMapping( value = "/getCauseByType" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getCauseByType(
	        @ApiParam( value = "原因类型(0:为中标者废标类型，1为发布者中标类型)" ) @RequestParam( "type" ) int type ,
	        @ApiParam( value = "访问类型，手机端有默认，不用管" , required = false ) @RequestParam( value = "visit" , required = false ) String visit )
	{
		try
		{
			List< TCause > list = causeService.getByType( type );
			if ( CollectionUtils.isNotEmpty( list ) )
			{
				return AjaxUtil.getMapByNotException( true , list );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , null );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	// 判断是否是分供方
	public boolean isSub( int archiveType )
	{
		if ( archiveType == SystemConstants.SYS_TARCHIVE_SUB )
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @Description 中标后生成临时合同(订单)
	 * @param productId
	 * @param response
	 * @author chengwzh
	 * @date 2015/6/29 14:32
	 */
	@RequestMapping( value = "/makeTempAgreement" , method = RequestMethod.POST )
	@ApiOperation( value = "中标后生成临时合同(订单)" , notes = "中标后生成临时合同(订单)" , response = TProduct.class , position = 5 )
	@ResponseBody
	public Map< String , Object > makeTempAgreement(
	        @ApiParam( value = "产品id，必填" ) @RequestParam( value = "productId" ) int productId )
	{
		try
		{
			boolean isTrue = productService.saveTempAgreement( productId );
			if ( isTrue )
			{
				return AjaxUtil.getMap( true , "临时合同生成成功" );
			}
			else
			{
				return AjaxUtil.getMap( false , "你没有中标，无法生成临时合同" );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
}
