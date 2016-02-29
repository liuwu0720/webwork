package com.clt.sub.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.clt.sub.model.TCity;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TProvince;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.model.TShipline;
import com.clt.sub.model.TSubCarStyle;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.service.IArkilometerService;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.IDriverCostService;
import com.clt.sub.service.IOrderService;
import com.clt.sub.service.IShipStatusService;
import com.clt.sub.service.IShiplineService;
import com.clt.sub.service.ISubCarStyleService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IIntegalCutService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Api( value = "OrderAction-api" , description = "车队订单的新增、查询、修改api和获取省份城市的api" , position = 5 )
@Controller
@RequestMapping( "/orderAction" )
public class OrderAction
{
	@Autowired
	IArkilometerService arkilometerService;
	@Autowired
	IOrderService orderService;
	@Autowired
	ICityService cityService;
	@Autowired
	ISubCarStyleService subCarStyleService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	IIntegalCutService integalCutService;
	
	@Autowired
	IUserService userService;
	@Autowired
	ICustomerSerivce customerService;
	@Autowired
	IDriverCostService iDriverCostService;
	@Autowired
	IShiplineService iShiplineService;
	
	@Autowired
	IShipStatusService iShipStatusService;
	
	/**
	 * @Description: 订单新增前 加载数据(省市区 )
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveBefore" )
	@ApiIgnore
	public String saveBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getiArchive();
		
		TSubsuppliers sub = subService.get( archid );
		
		String paramType = "add";
		String orderID = request.getParameter( "orderID" );
		if ( StringUtils.isNotBlank( orderID ) )
		{
			System.out.println( "orderId >>" + orderID );
			TOrder order = orderService.get( Integer.parseInt( orderID ) );
			Integer stacityID = order.getIStartId();
			Integer endcityID = order.getIEndId();
			if ( stacityID != null )
			{
				TCity stacity = cityService.getCityByID( stacityID );
				TProvince stapro = cityService.getProvinceByID( stacity.getProvinceid() );
				request.setAttribute( "stapro" , stapro );
				request.setAttribute( "stacity" , stacity );
			}
			if ( endcityID != null )
			{
				TCity endcity = cityService.getCityByID( endcityID );
				TProvince endpro = cityService.getProvinceByID( endcity.getProvinceid() );
				request.setAttribute( "endpro" , endpro );
				request.setAttribute( "endcity" , endcity );
			}
			request.setAttribute( "order" , order );
			paramType = "update";
		}
		
		// 获取分供方车型
		List< TSubCarStyle > substylist = subCarStyleService.findAll( sub.getVcSubno() );
		List< TProvince > prolist = cityService.getAllProvince();
		// List< TCustomer > custlist = customerService.findAllByEnable( null );
		List< TCustomer > custlist = customerService.findAllBySubNo( sub.getVcSubno() );
		request.setAttribute( "custlist" , custlist );
		request.setAttribute( "substylist" , substylist );
		request.setAttribute( "prolist" , prolist );
		request.setAttribute( "paramType" , paramType );
		return "sub/orderInfo/save";
		
	}
	
	/**
	 * 
	 * @Description: TODO(手机端获取订单详情)
	 * @param orderId
	 * @return Object 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-5 上午9:53:11
	 */
	@RequestMapping( value = "/getOrderDetailPhone" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "手机端根据ID获取订单详情" , notes = "private Integer id;"
	        + "ICARSTYLE;// 商品车ID<br/>" + "VCCARNAME;// 商品车车名<br/>"
	        + "IUSER;// 录入用户id<br/>" + "VCLOADADDRESS ;// 装货地址<br/>"
	        + "VCLOADCONTACT ;// 联系人<br/>" + "VCLOADTEL ;// 联系电话<br/>"
	        + "DTSHIP;// 要求发运日<br/>" + "DTARRIVE;// 要求到货日<br/>"
	        + "ISTARTID;// 起运城市id<br/>" + "IENDID;// 目的城市id<br/>"
	        + "VCSTARTCITY;// 起运城市<br/>" + "VCDESTCITY;// 目的城市	<br/>"
	        + "VCRECEIVEADDRESS;// 收货地址<br/>" + "VCRECEIVECONTACT;// 收货联系人<br/>"
	        + "VCRECEIVETEL;// 收货人电话<br/>" + "NTOTALCAR;// 数量<br/>"
	        + "NSHIPEDQTY;// 发运数量<br/>" + "NENABLE;// 状态（0为有效，1为无效)<br/>"
	        + "VCSUBNO;// 所属分供方编号<br/>" + "VCORDERNO;// 订单号<br/>"
	        + "NPAYTYPE;// 支付方式(0 现金 1客户)<br/>" + "NTOTALPRICE;// 订单总价<br/>"
	        + "ICUSTOMERIC;// 客户表ID<br/>" + "VCCUSTORDERNO;// 客户订单号(客户自己录入)<br/>"
	        + "DTCREATEDATE;// 创建时间<br/>" + "NCOST;// 应收单价(如果是现金支付此字段为空)<br/>"
	        + "NLOAD;// 是否配载（0：未配载[默认]；1已配载）)<br/>" + "VCLONG;// 收货地址经度<br/>"
	        + "VCLAT; // 收货地址纬度<br/>" + "VCLONGSTART;// 出发地经度<br/>"
	        + "VCLATSTART vcLatStart;// 出发地纬度<br/>" + "I_TRUCK_ID://运输车ID<br/>"
	        + "ISTOREID: 4S店ID<br/>" + "VCSTORENAME: 4S店名称<br/>"
	        + "N_EVALUATION:是否评价（0为未评价(默认)，1为已评价）<br/>"
	        + "N_PRODUCT:是否是抢单转换的（0：为是，1为不是(默认)）<br/>"
	        + "I_CARSTYLE_ID:抢单转订单对应的抢单车型ID<br/>" + "N_RATIO:支付比例<br/>"
	        + "N_PAYCYCLE:支付周期（1:N+1 2:N+2 3:N+3）" + "HEADID:发运主表ID<br/>"
	        + "VC_SHIPNO:发运指令号" )
	public Map< String , Object > getOrderDetailPhone(
	        HttpServletRequest request ,
	        @ApiParam( value = "订单Id" , required = true ) @RequestParam( value = "vcorderId" , required = true ) String vcorderId )
	{
		
		int orderId = Integer.parseInt( vcorderId );
		String sql = "SELECT ord.*,head.id as headid, head.vc_shipno,head.I_TRUCK_ID FROM T_order ord left join t_Shipline line on ord.id = line.i_order_id  left join t_Shiphead head"
		        + " on line.i_shiphead = head.id where ord.id =" + orderId;
		Map< String , Object > orderMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		System.out.println( "sql = " + sql );
		try
		{
			// String subbo = subService.get( user.getiArchive() ).getVcSubno();
			Page page = ServiceUtil.getcurrPage( request );
			orderMap = orderService.getSpringSql( sql , page );
			return AjaxUtil.getMapByResult( visit , orderMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 手机app订单新增保存方法：
	 * 新增时，新增多个目的地和新增多个客户运单号 ，这两个新增是互斥的。
	 * @param stacity
	 * @param endcity
	 * @param paramType
	 * @param request
	 * @param resp
	 * @param order
	 * @param array2
	 * @param array
	 * @throws ParseException 
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月17日 下午3:09:03
	 */
	@SuppressWarnings( "deprecation" )
	@ApiOperation( value = "新增订单接口" , notes = "新增订单<br/>" , response = TOrder.class )
	@RequestMapping( value = "/save" , method = RequestMethod.POST )
	public void save(
	        @ApiParam( value = "起始城市Id(PC端用到)" , required = false ) @RequestParam( value = "stacity" , required = false ) String stacity ,
	        @ApiParam( value = "目的城市Id(PC端用到)" , required = false ) @RequestParam( value = "endcity" , required = false ) String endcity ,
	        @ApiParam( value = "操作方式，add为新增，update为更新(修改时ID不能为空)" , required = true ) @RequestParam( value = "paramType" , required = false ) String paramType ,
	        HttpServletRequest request ,
	        HttpServletResponse resp ,
	        @ApiParam( value = "订单form对象" , required = true ) @ModelAttribute TOrder order ,
	        @ApiParam( value = "添加多个客户单号时:封装JSON格式如:<br/>[{'vcCustOrderNo':'A000001','NTotalCar':'2'},<br/>{'vcCustOrderNo':'A000002','NTotalCar':'4'}]" , required = false ) @RequestParam( value = "array2" , required = false ) String array2 ,
	        @ApiParam( value = "多个目的地,封装JSON格式如：<br/> "
	                + "[{'IEndId':'2','vcDestCity':'天津市','vcReceiveAddress':'广州市政府',<br/> 'vcReceiveContact':'John','vcReceiveTel':'13222223659','ICarStyle':'2','vcNote':'特殊要求',<br/>'vcCarName':'车型2','NTotalCar':'6',iStoreId:'4',vcStoreName:'4S店','vcNote':'特殊要求'},<br/> "
	                + "{'IEndId':'3','vcDestCity':'石家庄市','vcReceiveAddress':'深圳市政府',<br/> 'vcReceiveContact':'John','vcReceiveTel':'13222223659','ICarStyle':'2','vcNote':'特殊要求',<br/>'vcCarName':'车型2','NTotalCar':'6',iStoreId:'5',vcStoreName:'4S店','vcNote':'特殊要求'}]" , required = false ) @RequestParam( value = "array" , required = false ) String array )
	        throws ParseException
	{
		TCity startcity = null;
		TCity endCity = null;
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		// 订单信息，缺省字段初始化
		order = initOrder( stacity , endcity , order , startcity , endCity );
		// 当前操作类型为新增订单，设置订单里的 所属承运方编号 和发布者id 为当前用户的承运方编号和用户id
		if ( StringUtils.isNotBlank( paramType ) && paramType.equals( "add" ) )
		{
			order.setVcSubno( subService.get( user.getiArchive() ).getVcSubno() );
			order.setIUser( user.getId() );
		}
		
		if ( order.getIStartId().equals( order.getIEndId() ) )// 验证起始地和目的地是否相同，不允许相同
		{
			AjaxUtil.rendJson( resp , false , "起始地城市与目的地城市相同!" );
			return;
		}
		// 积分扣除
		JSONObject json = integalCutService.checkIntegalCutByUserCode( user , "F" );
		if ( json.getBoolean( "isSuccess" ) == false )
		{
			AjaxUtil.rendJson( resp , false , json.getString( "message" ) );
			return;
		}
		
		List< TOrder > tOrders = new ArrayList< TOrder >();// 第一种情况绑定目的地
		List< TOrder > tOrders2 = new ArrayList< TOrder >();// 第二种情况 绑定客户运单号
		if ( StringUtils.isNotBlank( array ) ) // 第一种情况绑定目的地
		{
			tOrders = JSON.parseArray( array , TOrder.class );
		}
		if ( StringUtils.isNotBlank( array2 ) )// 第二种情况 绑定客户运单号
		{
			tOrders2 = JSON.parseArray( array2 , TOrder.class );
		}
		
		boolean flag = true;
		int totalCar = 0;// 这个总数指另外绑定的多个订单的总数
		int sumTotalCar = 0;// sumTotalCar = totalCar+order的数量
		// 该变量在计算总价的时候要用到
		totalCar = getTotalCar( tOrders , tOrders2 , totalCar );
		
		if ( tOrders2.size() > 1 && order.getNTotalCar() <= totalCar )// 第一种情况
		{
			AjaxUtil.rendJson( resp , false , "拆分后的子订单发运数的和为" + totalCar + "大于等于总数"
			        + order.getNTotalCar() );
			return;
		}
		
		if ( tOrders2.size() == 1 && order.getNTotalCar() < totalCar )// 第一种情况
		{
			AjaxUtil.rendJson( resp , false ,
			        "拆分后的子订单发运数的和为" + totalCar + "大于总数" + order.getNTotalCar() );
			return;
		}
		
		/**
		 * 第三种情况 ：只绑定一个客户运单号且数量刚好与ORDER封装对象的数量相同
		 * 这种情况下，只生成一个订单，将torder2的客户运单号赋值给原order对象
		 */
		if ( tOrders2.size() == 1
		        && tOrders2.get( 0 ).getNTotalCar().equals( order.getNTotalCar() ) )// 第三种情况
		// 绑定一个客户运单号且数量刚好与ORDER封装对象的数量相同
		{
			order.setVcCustOrderNo( tOrders2.get( 0 ).getVcCustOrderNo() );
			flag = false;
			
		}
		// 第二种情况,添加多个客户运动号，并且绑定数量小于订单数量,请结合ui理解
		if ( tOrders2.size() > 0 && order.getNTotalCar() > totalCar )
		{
			order.setNTotalCar( order.getNTotalCar() - totalCar );
		}
		
		sumTotalCar = totalCar + order.getNTotalCar();
		
		try
		{
			int integalID = json.getInt( "integalID" );
			
			if ( tOrders.size() > 0 )
			{
				for ( TOrder newOrder : tOrders )
				{
					if ( order.getIStartId().equals( newOrder.getIEndId() ) )
					{
						AjaxUtil.rendJson( resp , false , "起始地城市与目的地城市相同!" );
						return;
					}
					copyOrder( order , order.getNTotalPrice() , sumTotalCar , newOrder );
					orderService.saveOrUpdate( newOrder , user , "cut" , integalID );
				}
				if ( order.getNTotalCar() > 0 && order.getNPayType() == 0 ) // 支付方式(0现金
				// 1客户)
				{
					float price = ( Float.parseFloat( order.getNTotalCar() + "" ) / Float
					        .parseFloat( sumTotalCar + "" ) ) * order.getNTotalPrice();
					BigDecimal b = new BigDecimal( price );
					float f2 = b.setScale( 2 , BigDecimal.ROUND_HALF_UP ).floatValue();// 四舍五入，保留两位小数
					order.setNTotalPrice( f2 );
					paramType = "udpate";
				}
				
				order.setVcOrderno( orderService.getMaxOrderNo() );
				orderService.saveOrUpdate( order , user , "cut" , integalID );
			}
			if ( flag && tOrders2.size() > 0 )
			{
				for ( TOrder newOrder2 : tOrders2 )
				{
					copyOrder( order , order.getNTotalPrice() , sumTotalCar , newOrder2 );
					orderService.saveOrUpdate( newOrder2 , user , "cut" , integalID );
				}
				if ( order.getNTotalCar() > 0 && order.getNPayType() == 0 )
				{
					float price = ( Float.parseFloat( order.getNTotalCar() + "" ) / Float
					        .parseFloat( sumTotalCar + "" ) ) * order.getNTotalPrice();
					BigDecimal b = new BigDecimal( price );
					float f2 = b.setScale( 2 , BigDecimal.ROUND_HALF_UP ).floatValue();// 四舍五入，保留两位小数
					order.setNTotalPrice( f2 );
					
				}
				order.setVcOrderno( orderService.getMaxOrderNo() );
				orderService.saveOrUpdate( order , user , "cut" , integalID );
				paramType = "udpate";
			}
			
			else
			{
				if ( paramType.equalsIgnoreCase( "add" ) )
				{
					order.setVcOrderno( orderService.getMaxOrderNo() );
					orderService.saveOrUpdate( order , user , "cut" , integalID );
				}
				else if ( paramType.equalsIgnoreCase( "update" ) )
				{
					orderService.saveOrUpdate( order , user , "cut" , integalID );
				}
				
			}
			
			AjaxUtil.rendJson( resp , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( resp , false , "保存失败，失败原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 获得所有订单车辆的总数，兼容两种情况 
	 * @param tOrders
	 * @param tOrders2
	 * @param totalCar
	 * @return 
	 *   int 返回值描述
	 * @author hjx
	 * @create_date 2015年9月17日 下午3:16:33
	 */
	private int getTotalCar( List< TOrder > tOrders , List< TOrder > tOrders2 ,
	        int totalCar )
	{
		if ( tOrders2.size() > 0 )
		{
			for ( TOrder tOrder : tOrders2 )
			{
				totalCar += tOrder.getNTotalCar();
			}
		}
		
		if ( tOrders.size() > 0 )
		{
			for ( TOrder tOrder : tOrders )
			{
				totalCar += tOrder.getNTotalCar();
			}
		}
		return totalCar;
	}
	
	/**
	 * @Description: 订单信息，缺省字段初始化 
	 * @param stacity
	 * @param endcity
	 * @param order
	 * @param startcity
	 * @param endCity 
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月17日 下午3:06:48
	 */
	private TOrder initOrder( String stacity , String endcity , TOrder order ,
	        TCity startcity , TCity endCity )
	{
		// 起运地 目的地
		if ( StringUtils.isNotBlank( stacity ) )
		{
			int startID = Integer.parseInt( stacity );
			startcity = cityService.getCityByID( startID );
		}
		if ( StringUtils.isNotBlank( endcity ) )
		{
			int endID = Integer.parseInt( endcity );
			endCity = cityService.getCityByID( endID );
		}
		if ( startcity != null )
		{
			order.setIStartId( startcity.getId() );
			order.setVcStartCity( startcity.getCityname() );
		}
		if ( endCity != null )
		{
			order.setIEndId( endCity.getId() );
			order.setVcDestCity( endCity.getCityname() );
		}
		order.setDtCreateDate( new Date() );
		TSubCarStyle carStyle = subCarStyleService.get( order.getICarStyle() );
		order.setVcCarName( carStyle.getVcCarStyle() );
		
		if ( order.getNPayType() == 1 )// 支付方式(0 现金 1客户)
		{
			order.setNCost( order.getNCost() );
		}
		else
		{
			order.setNCost( null );
		}
		return order;
	}
	
	/**
	 * @Description: 订单详情里绑定客户运单号的处理方法
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-5 上午11:56:23
	 */
	@ApiOperation( value = "手机端点击绑定客户单号,并填写客户单号后的保存接口" , notes = "手机端点击绑定客户单号,并填写客户单号后的保存接口" )
	@RequestMapping( value = "/saveBindNo" , method = RequestMethod.POST )
	public Map< String , Object > saveBindNo(
	        @ApiParam( value = "添加多个客户单号时:封装JSON格式如:<br/>[{'vcCustOrderNo':'A000001','NTotalCar':'2'},<br/>{'vcCustOrderNo':'A000002','NTotalCar':'4'}]" , required = true ) @RequestParam( value = "custOrderJson" ) String custOrderJson ,
	        @ApiParam( value = "订单号ID" , required = true ) @RequestParam( value = "vcOrderId" , required = true ) String vcOrderId )
	{
		try
		{
			// 获得原来订单对象
			TOrder order = orderService.get( Integer.parseInt( vcOrderId ) );
			List< TOrder > orderList = JSON.parseArray( custOrderJson , TOrder.class );// 解析json变成对应订单对象
			if ( CollectionUtils.isNotEmpty( orderList ) )
			{
				Float totalPrice = order.getNTotalPrice();
				int nTotalCar = order.getNTotalCar();
				
				// 验证总数
				int sumNum = 0;
				for ( TOrder tOrder : orderList )
				{
					sumNum += tOrder.getNTotalCar();
				}
				if ( nTotalCar < sumNum )
				{
					return AjaxUtil.getMap( false , "总数大于原订单数！" );
				}
				int temp = 0;
				// 如果有多个客户单号，则进行订单拆分
				for ( TOrder od : orderList )
				{
					temp = order.getNTotalCar() - od.getNTotalCar();
					
					if ( temp > 0 )
					{
						// 对新订单信息处理，同时把旧订单信息进行修改（修改总金额）
						TOrder copyOrder = new TOrder();
						BeanUtils.copyProperties( copyOrder , order );
						order.setNTotalCar( temp );// 修改旧订单的商品车数量
						if ( order.getNShipedQty() != null )
						{
							order.setNShipedQty( temp );
						}
						/**
						 * 改变订单总价
						 */
						if ( order.getNPayType() == 0 )
						{// 原先订单为 现金支付 要对总价进行修改
						
							float price = ( Float.parseFloat( od.getNTotalCar() + "" ) / Float
							        .parseFloat( nTotalCar + "" ) ) * totalPrice;
							BigDecimal b = new BigDecimal( price );
							float f1 = b.setScale( 2 , BigDecimal.ROUND_HALF_UP )
							        .floatValue();// 四舍五入，保留两位小数
							copyOrder.setNTotalPrice( f1 );
							order.setNTotalPrice( order.getNTotalPrice() - f1 );
							
						}
						
						copyOrder.setVcOrderno( orderService.getMaxOrderNo() );
						copyOrder.setVcCustOrderNo( od.getVcCustOrderNo() );
						copyOrder.setNTotalCar( od.getNTotalCar() );
						copyOrder.setNShipedQty( od.getNTotalCar() );
						copyOrder.setId( null );
						orderService.save( copyOrder );
						// 如果原订单已配载
						if ( order.getnLoad() == 1 )
						{
							TShipline tShipline = iShiplineService.findByOrderId(
							        order.getId() ).get( 0 );
							TShipline newShipline = new TShipline();
							BeanUtils.copyProperties( newShipline , tShipline );
							tShipline.setnShipQty( order.getNShipedQty() );
							iShiplineService.update( tShipline );
							newShipline.setId( null );
							newShipline.setIOrderId( copyOrder.getId() );
							newShipline.setnShipQty( copyOrder.getNShipedQty() );
							iShiplineService.save( newShipline );
							// 订单状态表也相应的改变
							List< TShipStatus > tShipStatusList = iShipStatusService
							        .findByLine( tShipline.getId() );
							for ( TShipStatus shipStatus : tShipStatusList )
							{
								TShipStatus newShipStatus = new TShipStatus();
								BeanUtils.copyProperties( newShipStatus , shipStatus );
								newShipStatus.setnLineId( newShipline.getId() );
								newShipStatus.setnOrderId( copyOrder.getId() );
								newShipStatus.setId( null );
								iShipStatusService.save( newShipStatus );
							}
						}
					}
					else if ( temp == 0 )
					{
						
						order.setVcCustOrderNo( od.getVcCustOrderNo() );
						order.setNTotalCar( od.getNTotalCar() );
					}
				}
				
				orderService.update( order );
				int size = orderList.size();
				if ( temp > 0 )
				{
					size += 1;
				}
				return AjaxUtil.getMap( true , "保存成功！该订单拆分为" + size + "个订单。" );
			}
			else
			{
				return AjaxUtil.getMap( false , "请输入客户运单号和数量！" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 拆分复制订单
	 * 
	 * @param order
	 *            原先订单信息
	 * @param totalPrice
	 *            原先订单的总金额
	 * @param nTotalCar
	 *            原先订单的总数量
	 * @param od
	 *            新订单对象
	 * @author hjx
	 * @create by 2015-5-9 15:40
	 */
	private void copyOrder( TOrder order , Float totalPrice , int nTotalCar , TOrder od )
	{
		od.setVcOrderno( orderService.getMaxOrderNo() );// 订单号
		od.setDtArrive( order.getDtArrive() );// 要求到货日
		od.setDtCreateDate( order.getDtCreateDate() );// 创建时间
		od.setDtShip( order.getDtShip() );// 要求发运日
		od.setICarStyle( order.getICarStyle() );// 商品车ID
		od.setVcCarName( order.getVcCarName() );// 商品车车名
		od.setICustomerId( order.getICustomerId() );// 客户表ID
		od.setIStartId( order.getIStartId() );// 起运城市id
		od.setIUser( order.getIUser() );// 录入用户id
		od.setNCost( order.getNCost() );// 应收单价(如果是现金支付此字段为空)
		od.setnLoad( order.getnLoad() );// 是否配载（0：未配载[默认]；1已配载）
		od.setNPayType( order.getNPayType() );// 支付方式(0 现金 1客户)
		
		if ( order.getNPayType() == 0 )
		{// 原先订单为 现金支付 要对总价进行修改
		
			float price = ( Float.parseFloat( od.getNTotalCar() + "" ) / Float
			        .parseFloat( nTotalCar + "" ) ) * totalPrice;
			BigDecimal b = new BigDecimal( price );
			float f1 = b.setScale( 2 , BigDecimal.ROUND_HALF_UP ).floatValue();// 四舍五入，保留两位小数
			od.setNTotalPrice( f1 );
			// order.setNTotalPrice( order.getNTotalPrice() - f1 );
			
		}
		/*if ( order.getVcCustOrderNo() != null )
		{
			od.setVcCustOrderNo( order.getVcCustOrderNo() );
		}*/
		if ( od.getIEndId() == null )
		{
			od.setIEndId( order.getIEndId() );
		}
		
		if ( od.getVcDestCity() == null )
		{
			od.setVcDestCity( order.getVcDestCity() );
		}
		if ( od.getVcReceiveAddress() == null )
		{
			od.setVcReceiveAddress( order.getVcReceiveAddress() );// 收货地址
		}
		if ( od.getVcReceiveContact() == null )
		{
			od.setVcReceiveContact( order.getVcReceiveContact() );// 收货联系人
		}
		if ( od.getVcReceiveTel() == null )
		{
			od.setVcReceiveTel( order.getVcReceiveTel() );// 收货人电话
		}
		if ( od.getiStoreId() == null )
		{
			od.setiStoreId( order.getiStoreId() );// 4s店
		}
		if ( od.getVcStoreName() == null )
		{
			od.setVcStoreName( order.getVcStoreName() );
		}
		
		od.setVcLoadAddress( order.getVcLoadAddress() );// 装货地址
		od.setVcLoadContact( order.getVcLoadContact() );// 联系人
		od.setVcLoadTel( order.getVcLoadTel() );// 联系电话
		od.setVcStartCity( order.getVcStartCity() );
		od.setVcSubno( order.getVcSubno() );
		od.setVcLong( order.getVcLong() );
		od.setVcLat( order.getVcLat() );
		od.setVcLongStart( order.getVcLongStart() );// 出发地经度
		od.setVcLatStart( order.getVcLatStart() );// 出发地纬度
	}
	
	/**
	 * @Description: 获取所有的省份 返回json
	 * @return List<TProvince> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月13日 下午4:14:02
	 */
	@ApiOperation( value = "获取所有的省份 返回json" , notes = "获取所有的省份 返回 List< TProvince > json" , position = 5 )
	@RequestMapping( value = "/getAllProvince" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllProvince()
	{
		List< TProvince > prolist;
		try
		{
			prolist = cityService.getAllProvince();
			if ( CollectionUtils.isNotEmpty( prolist ) )
			{
				return AjaxUtil.getMapByNotException( true , prolist );
			}
			else
			{
				return AjaxUtil.getMapByNotException( true , null );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 根据省份id 获取对应的所有的城市 返回json
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiOperation( value = "根据省份id，获取对应的所有的城市 返回json" , notes = "根据省份id，获取对应的所有的城市 返回 List< TCity > json" , position = 5 )
	@RequestMapping( value = "/getAllCityByProID" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllCityByProID(
	        @ApiParam( value = "省份id" , required = true ) @RequestParam( "proID" ) String proID )
	{
		
		List< TCity > citylist;
		try
		{
			int proId = Integer.parseInt( proID );
			citylist = cityService.getCitysByProID( proId );
			if ( CollectionUtils.isNotEmpty( citylist ) )
			{
				return AjaxUtil.getMapByNotException( true , citylist );
			}
			else
			{
				return AjaxUtil.getMapByNotException( true , citylist );
			}
		}
		catch ( NumberFormatException e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 获取所有的城市 返回json,APP使用！
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiOperation( value = "获取所有的城市,按省份分组排列 返回json" , notes = "获取所有的城市,按省份分组排列 返回json" , position = 5 )
	@RequestMapping( value = "/getAllCity" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllCity()
	{
		
		List< TProvince > prolist;
		try
		{
			prolist = cityService.getAllProvince();
			Map< String , Object > dMap = new HashMap< String , Object >();
			if ( CollectionUtils.isNotEmpty( prolist ) )
			{
				
				List< Map< String , Object > > pList = new ArrayList< Map< String , Object > >();
				for ( TProvince p : prolist )
				{
					Map< String , Object > pMap = new HashMap< String , Object >();
					int key = p.getId();
					String name = p.getVcProvinceName();
					List< TCity > citylist = cityService.getCitysByProID( key );
					pMap.put( "key" , key );
					pMap.put( "name" , name );
					pMap.put( "citys" , citylist );
					pList.add( pMap );
				}
				
				return AjaxUtil.getMapByNotException( true , pList );
			}
			else
			{
				return AjaxUtil.getMapByNotException( true , null );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 获取该分供方的所有订单
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( value = "/getAllOrderBysubno" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllOrderBysubno(
	        @RequestParam( value = "orderNo" , required = false ) String orderNo ,
	        @RequestParam( value = "receiveAddress" , required = false ) String receiveAddress ,
	        @RequestParam( value = "destCity" , required = false ) String destCity ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getIArchiveType();
		
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TOrder.class );
		hql.setQueryPage( page );
		
		// 该用户类型等于分供方
		if ( archid == SystemConstants.SYS_TARCHIVE_SUB )
		{
			
			String subbo = subService.get( user.getiArchive() ).getVcSubno();
			hql.addEqual( "vcSubno" , subbo );
			hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
			hql.addEqual( "NShipedQty" , 0 );// 已发运的不能再编辑
			hql.addOrderBy( "id" , "desc" );
			
			// String orderNo = request.getParameter( "orderNo" );
			if ( StringUtils.isNotBlank( orderNo ) )
			{

				hql.addLike( "vcOrderno" , orderNo );
				
			}
			// String receiveAddress = request.getParameter( "receiveAddress" );
			if ( StringUtils.isNotBlank( receiveAddress ) )
			{

				hql.addLike( "vcReceiveAddress" , receiveAddress );
				
			}
			// String destCity = request.getParameter( "destCity" );
			if ( StringUtils.isNotBlank( destCity ) )
			{

				hql.addEqual( "vcDestCity" , destCity );
				
			}
			
			Map< String , Object > resuMap = orderService.findAllByHqlHelp( hql );
			
			if ( resuMap.get( "rows" ) != null )
			{
				List relist = ( List ) resuMap.get( "rows" );

			}
			else
			{
				System.out.println( "无数据  " );
			}
			
			return resuMap;
			
		}
		
		return null;
		
	}
	
	@RequestMapping( value = "/getAllOrderBySubnoPhone" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "分供方角色：手机端获取车队订单(当前订单(即已配置的订单，下一步入场)、未开始订单(新建的订单，等待配载)、历史订单(已经运抵的订单))" , notes = ""
	        + "分供方角色：ID：订单表ID；VC_LOAD_ADDRESS：订单的装货地址；VC_LOAD_CONTACT：联系人<br/>"
	        + "VC_LOAD_TEL:联系电话；DT_SHIP：要求发运日；DT_ARRIVE：要求到货日；<br/>"
	        + "VC_START_CITY:起运地城市：VC_DEST_CITY：目的地城市；VC_RECEIVE_ADDRESS：收货地址<br/>"
	        + "VC_RECEIVE_CONTACT:收货联系人;VC_RECEIVE_TEL:收货人电话;I_CAR_STYLE:商品车型号<br/>"
	        + "N_TOTAL_CAR:数量；N_ENABLE:状态（0为有效，1为无效）I_USER:录入用户ID；<br/>"
	        + "VC_SUBNO:所属分供方编号;I_START_ID:起始地城市ID；I_END_ID：目的地城市ID<br/>"
	        + "VC_ORDERNO:订单号;N_SHIPEDQTY:发运数量;N_TOTAL_PRICE:支付方式(0 现金 1客户)<br/>"
	        + "N_TOTAL_PRICE:订单总价;VC_CUST_ORDERNO:客户订单号(客户自己录入);I_CUSTOMER_ID:客户id<br/>"
	        + "VC_CAR_NAME:商品车车名；DT_CREATEDATE：录入时间;N_PRICE:应收单价(如果是现金支付此字段为空)" )
	public Map< String , Object > getAllOrderBySubnoPhone(
	        @ApiParam( value = "订单类型('now':当前订单；'begin'：未开始订单;'before':历史订单)" , required = true ) @RequestParam( value = "orderType" , required = false ) String orderType ,
	        @ApiParam( value = "订单类型2：'Z':待装车；'F':待发运；'D':待抵达<br/>注意：该条件查询只针对当前订单" , required = false ) @RequestParam( value = "orderType2" , required = false ) String orderType2 ,
	        @ApiParam( value = "订单类型3：'WH':未回单；'YH':已回单；'WJ':未结算 ;'YJ':已结算<br/>注意：该条件查询只针对历史订单" , required = false ) @RequestParam( value = "orderType3" , required = false ) String orderType3 ,
	        @ApiParam( value = "起始地" , required = false ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "目的地" , required = false ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request , HttpServletResponse response )
	{
		
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getIArchiveType();
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		String sql = "";
		Map< String , Object > resuMap = new HashMap< String , Object >();
		try
		{
			if ( orderType.equalsIgnoreCase( "now" ) )// 当前订单 即已配载的订单
			{
				sql = " select distinct o.* ,ts.n_current_status from t_order o , t_shipline ts where"
				        + " (ts.n_current_status = 0 or ts.n_current_status=3 "
				        + "or ts.n_current_status=4 or ts.n_current_status=5 or ts.n_current_status = 10 or ts.n_current_status = 15)"
				        + " and o.id = ts.i_order_id and o.n_enable = 0 and ts.N_ENABLE = 0";
			}
			if ( orderType.equalsIgnoreCase( "begin" ) )// 未开始订单 即等待配载的订单
			{
				sql = " SELECT distinct o.* FROM T_ORDER o where o.N_ENABLE = 0 and o.N_LOADING = 0 ";// 有效未配载的订单
			}
			if ( orderType.equalsIgnoreCase( "before" ) )// 历史订单 已运抵的订单
			{
				sql = " select distinct o.* ,ts.n_current_status from t_order o , t_shipline ts where"
				        + " (ts.n_current_status = 18 or ts.n_current_status=20 or ts.n_current_status=30) and o.id = ts.i_order_id and o.n_enable = 0 and ts.N_ENABLE = 0";
			}
			if ( StringUtils.isNotBlank( orderType2 ) )
			{
				if ( orderType2.equalsIgnoreCase( "Z" ) )// 待装车(已配载，已接单)
				{
					sql = " select distinct o.* ,ts.n_current_status from t_order o , t_shipline ts where"
					        + " (ts.n_current_status = 0 or ts.n_current_status=3 or ts.n_current_status=5 ) and o.id = ts.i_order_id and o.n_enable = 0 and ts.N_ENABLE = 0";
				}
				if ( orderType2.equalsIgnoreCase( "F" ) )// 待发运
				{
					sql = "select distinct o.* from t_order o , t_shipline ts where o.id = ts.i_order_id and o.n_enable = 0  and ts.N_CURRENT_STATUS >=3 and  ts.N_CURRENT_STATUS <= 10 and ts.N_ENABLE = 0";
				}
				if ( orderType2.equalsIgnoreCase( "D" ) )// 待抵达
				{
					sql = "select distinct o.* from t_order o , t_shipline ts where o.id = ts.i_order_id and o.n_enable = 0  and ts.n_current_status = 15 and ts.N_ENABLE = 0";
				}
			}
			if ( StringUtils.isNotBlank( orderType3 ) )
			{
				if ( orderType3.equalsIgnoreCase( "WH" ) )// 未回单：即已运抵
				{
					sql = " select distinct o.* ,ts.n_current_status from t_order o , t_shipline ts where"
					        + " ts.n_current_status = 18  and o.id = ts.i_order_id and o.n_enable = 0 and ts.N_ENABLE = 0";
				}
				if ( orderType3.equalsIgnoreCase( "YH" ) )// 已回单
				{
					sql = " select distinct o.* ,ts.n_current_status from t_order o , t_shipline ts where"
					        + " ts.n_current_status = 20  and o.id = ts.i_order_id and o.n_enable = 0 and ts.N_ENABLE = 0";
				}
				if ( orderType3.equalsIgnoreCase( "WJ" ) )// 未结算
				{
					sql = " select distinct o.* ,ts.n_current_status from t_order o , t_shipline ts where"
					        + " ts.n_current_status = 20  and o.id = ts.i_order_id and o.n_enable = 0 and ts.N_ENABLE = 0 and ts.N_ARORDER = 1";
				}
				if ( orderType3.equalsIgnoreCase( "YJ" ) )// 已结算
				{
					sql = " select distinct o.* ,ts.n_current_status from t_order o , t_shipline ts where"
					        + " ts.n_current_status = 20  and o.id = ts.i_order_id and o.n_enable = 0 and ts.N_ENABLE = 0 and ts.N_ARORDER = 0";
				}
			}
			
			if ( StringUtils.isNotBlank( vcStart ) )
			{
				sql += " and o.VC_START_CITY = '" + vcStart + "' ";
			}
			if ( StringUtils.isNotBlank( vcEnd ) )
			{
				sql += " and o.VC_DEST_CITY = '" + vcEnd + "' ";
			}
			sql += "and o.VC_SUBNO = '" + subbo
			        + "'  order by o.id desc, o.VC_CUST_ORDERNO asc";
			System.out.println( "sql = " + sql );
			resuMap = orderService.findBySpringSql( sql , page );
			List< Map< String , Object >> arlist = ( List< Map< String , Object >> ) resuMap
			        .get( "rows" );
			if ( CollectionUtils.isEmpty( arlist ) )
			{
				return AjaxUtil.getMap( false , "没有相关数据！" );
			}
			String custNo = "";
			Map< String , Object > rMap = null;
			Map< String , Object > rMap2 = null;
			List< Map< String , Object > > resultList = new ArrayList< Map< String , Object > >();
			List itemList = null;
			List itemList2 = null;
			for ( Map< String , Object > map : arlist )
			{
				if ( null != map.get( "VC_CUST_ORDERNO" ) )
				{
					
					if ( ! custNo.equals( map.get( "VC_CUST_ORDERNO" ) ) )
					{
						custNo = ( String ) map.get( "VC_CUST_ORDERNO" );
						// map.remove( "VC_CUST_ORDERNO" );
						/*	
							if ( null != rMap )
							{
								rMap.put( "item" , itemList );
								resultList.add( rMap );
							}*/
						
						rMap = new HashMap< String , Object >();
						
						itemList = new ArrayList();
						
						rMap.put( "custNo" , custNo );
						itemList.add( map );
						if ( null != rMap )
						{
							rMap.put( "item" , itemList );
							resultList.add( rMap );
						}
					}
					else
					{
						// map.remove( "VC_CUST_ORDERNO" );
						itemList.add( map );
					}
				}
				else
				{
					
					if ( itemList2 == null )
					{
						rMap2 = new HashMap< String , Object >();
						itemList2 = new ArrayList();
					}
					
					rMap2.put( "custNo" , "客户单号为空" );
					
					itemList2.add( map );
					rMap2.put( "item" , itemList2 );
					
				}
				
			}
			if ( rMap2 != null )
			{
				resultList.add( rMap2 );
			}
			
			if ( CollectionUtils.isNotEmpty( resultList ) )
			{
				
				return AjaxUtil.getMapByNotException( true , resultList );
			}
			else
			{
				return AjaxUtil.getMap( false , "查询不到数据" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(手机端获取订单接口)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 下午5:41:26
	 */
	
	@RequestMapping( value = "/getAllOrderBySubnoPhone2" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "手机端获取车队订单(订单类型：'X':新订单；'P':配载；'Z':装车;'F':发运;'D':抵达)" , notes = "private Integer id;"
	        + "ICARSTYLE;// 商品车ID<br/>"
	        + "VCCARNAME;// 商品车车名<br/>"
	        + "IUSER;// 录入用户id<br/>"
	        + "VCLOADADDRESS ;// 装货地址<br/>"
	        + "VCLOADCONTACT ;// 联系人<br/>"
	        + "VCLOADTEL ;// 联系电话<br/>"
	        + "DTSHIP;// 要求发运日<br/>"
	        + "DTARRIVE;// 要求到货日<br/>"
	        + "ISTARTID;// 起运城市id<br/>"
	        + "IENDID;// 目的城市id<br/>"
	        + "VCSTARTCITY;// 起运城市<br/>"
	        + "VCDESTCITY;// 目的城市	<br/>"
	        + "VCRECEIVEADDRESS;// 收货地址<br/>"
	        + "VCRECEIVECONTACT;// 收货联系人<br/>"
	        + "VCRECEIVETEL;// 收货人电话<br/>"
	        + "NTOTALCAR;// 数量<br/>"
	        + "NSHIPEDQTY;// 发运数量<br/>"
	        + "NENABLE;// 状态（0为有效，1为无效)<br/>"
	        + "VCSUBNO;// 所属分供方编号<br/>"
	        + "VCORDERNO;// 订单号<br/>"
	        + "NPAYTYPE;// 支付方式(0 现金 1客户)<br/>"
	        + "NTOTALPRICE;// 订单总价<br/>"
	        + "ICUSTOMERIC;// 客户表ID<br/>"
	        + "VCCUSTORDERNO;// 客户订单号(客户自己录入)<br/>"
	        + "DTCREATEDATE;// 创建时间<br/>"
	        + "NCOST;// 应收单价(如果是现金支付此字段为空)<br/>"
	        + "NLOAD;// 是否配载（0：未配载[默认]；1已配载）)<br/>"
	        + "VCLONG;// 收货地址经度<br/>"
	        + "VCLAT; // 收货地址纬度<br/>"
	        + "VCLONGSTART;// 出发地经度<br/>"
	        + "VCLATSTART vcLatStart;// 出发地纬度<br/>"
	        + "I_TRUCK_ID://运输车ID<br/>"
	        + "ISTOREID: 4S店ID<br/>"
	        + "VCSTORENAME: 4S店名称<br/>"
	        + "N_EVALUATION:是否评价（0为未评价(默认)，1为已评价）<br/>"
	        + "N_PRODUCT:是否是抢单转换的（0：为是，1为不是(默认)）<br/>"
	        + "I_CARSTYLE_ID:抢单转订单对应的抢单车型ID<br/>"
	        + "N_RATIO:支付比例<br/>"
	        + "N_PAYCYCLE:支付周期（1:N+1 2:N+2 3:N+3）" )
	@ApiIgnore
	public Map< String , Object > getAllOrderBySubnoPhone2(
	        @ApiParam( value = "订单类型：'X':新订单；'P':配载；'Z':装车;'F':发运;'D':抵达<br/>" , required = true ) @RequestParam( value = "orderType" , required = true ) String orderType ,
	        @ApiParam( value = "起始地" , required = false ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "目的地" , required = false ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request , HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getIArchiveType();
		
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		String sql = "";
		Map< String , Object > resuMap = new HashMap< String , Object >();
		try
		{
			
			if ( StringUtils.isNotBlank( orderType ) )
			{
				if ( orderType.equalsIgnoreCase( "X" ) )// 新订单
				{
					sql = " select o.* from  t_order o where o.N_LOADING = 0";
				}
				
				if ( orderType.equalsIgnoreCase( "P" ) )// 配载
				{
					sql = " select o.* ,ts.n_current_status from t_order o , t_shipline ts where"
					        + " ts.n_current_status = 0 and o.id = ts.i_order_id and o.n_enable = 0 ";
				}
				if ( orderType.equalsIgnoreCase( "Z" ) )// 装车
				{
					sql = "select o.* from t_order o , t_shipline ts where o.id = ts.i_order_id and o.n_enable = 0  and ts.n_current_status = 10";
				}
				if ( orderType.equalsIgnoreCase( "F" ) )// 发运
				{
					sql = "select o.* from t_order o , t_shipline ts where o.id = ts.i_order_id and o.n_enable = 0  and ts.n_current_status = 15";
				}
				if ( orderType.equalsIgnoreCase( "D" ) )// 抵达
				{
					sql = "select o.* from t_order o , t_shipline ts where o.id = ts.i_order_id and o.n_enable = 0  and ts.n_current_status = 18";
				}
			}
			
			if ( StringUtils.isNotBlank( vcStart ) )
			{
				sql += " and o.VC_START_CITY = '" + vcStart + "' ";
			}
			if ( StringUtils.isNotBlank( vcEnd ) )
			{
				sql += " and o.VC_DEST_CITY = '" + vcEnd + "' ";
			}
			sql += " order by o.id desc";
			System.out.println( "sql = " + sql );
			resuMap = orderService.findBySpringSql( sql , page );
			List< Map< String , Object >> arlist = ( List< Map< String , Object >> ) resuMap
			        .get( "rows" );
			String custNo = "";
			Map< String , Object > rMap = null;
			Map< String , Object > rMap2 = null;
			List< Map< String , Object > > resultList = new ArrayList< Map< String , Object > >();
			List itemList = null;
			List itemList2 = null;
			for ( Map< String , Object > map : arlist )
			{
				System.out.println( "***=" + map.get( "VC_CUST_ORDERNO" ) );
				if ( null != map.get( "VC_CUST_ORDERNO" ) )
				{
					
					if ( ! custNo.equals( map.get( "VC_CUST_ORDERNO" ) ) )
					{
						custNo = ( String ) map.get( "VC_CUST_ORDERNO" );
						// map.remove( "VC_CUST_ORDERNO" );
						/*	
							if ( null != rMap )
							{
								rMap.put( "item" , itemList );
								resultList.add( rMap );
							}*/
						
						rMap = new HashMap< String , Object >();
						
						itemList = new ArrayList();
						
						rMap.put( "custNo" , custNo );
						itemList.add( map );
						if ( null != rMap )
						{
							rMap.put( "item" , itemList );
							resultList.add( rMap );
						}
					}
					else
					{
						// map.remove( "VC_CUST_ORDERNO" );
						itemList.add( map );
					}
				}
				else
				{
					
					if ( itemList2 == null )
					{
						rMap2 = new HashMap< String , Object >();
						itemList2 = new ArrayList();
					}
					
					rMap2.put( "custNo" , "客户单号为空" );
					
					itemList2.add( map );
					rMap2.put( "item" , itemList2 );
					
				}
				
			}
			resultList.add( rMap2 );
			// System.out.println( resultList );
			return AjaxUtil.getMapByNotException( true , resultList );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	@RequestMapping( "/intoOrderInfoBysubno" )
	@ApiIgnore
	public String intoOrderInfoBysubno( HttpServletRequest request )
	{
		
		return "sub/orderInfo/orderInfoList";
		
	}
	
	/**
	 * @Description: 删除订单 逻辑删除
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( value = "/del" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "删除订单接口" , notes = "删除订单信息" , position = 5 )
	public Map< String , Object > del( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		try
		{
			String orderIDStr = request.getParameter( "orderID" );
			String[] orderIdStrs = orderIDStr.split( "," );
			boolean isTrue = true;
			String msg = "订单号[";
			for ( String orderIdStr : orderIdStrs )
			{
				int orderId = Integer.parseInt( orderIdStr );
				TOrder order = orderService.get( orderId );
				if ( order.getnLoad() == 0 )
				{
					order.setNEnable( SystemConstants.SYS_DISABLE );
					orderService.update( order );
				}
				else
				{
					if ( ! isTrue )
					{
						msg += ",";
					}
					msg += order.getVcOrderno();
					isTrue = false;
				}
			}
			msg += "]已做调度指令,删除失败";
			if ( isTrue )
			{
				return AjaxUtil.getMap( true , "删除成功" );
			}
			else
			{
				return AjaxUtil.getMap( false , msg );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(根据参数查出应收单价)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-25 下午3:06:47
	 */
	@RequestMapping( value = "/getCost" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public float getCarCost(
	        HttpServletRequest request ,
	        @ApiParam( value = "起始地" , required = true ) @RequestParam( value = "stacity" , required = true ) String stacity ,
	        @ApiParam( value = "目的地" , required = true ) @RequestParam( value = "endcity" , required = true ) String endcity ,
	        @ApiParam( value = "商品车ID" , required = true ) @RequestParam( value = "ICarStyle" , required = true ) String ICarStyle ,
	        HttpServletResponse response )
	{
		
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		int archid = user.getiArchive();
		
		TSubsuppliers sub = subService.get( archid );
		int ICarStyleId = Integer.parseInt( ICarStyle );// 商品车ID
		int stacityId = Integer.parseInt( stacity );
		int endcityId = Integer.parseInt( endcity );
		
		int ICustomerId = Integer.parseInt( request.getParameter( "ICustomerId" ) );
		float costPrice = iDriverCostService.getCostPrice( ICarStyleId , stacityId ,
		        endcityId , ICustomerId , sub.getVcSubno() );
		
		return costPrice;
	}
	
	/**
	 * 
	 * @Description: TODO(根据参数查出应收单价:手机端)
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-4 上午11:25:37
	 */
	@RequestMapping( value = "/getCarCostByPhone" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "查询应收单价接口，根据起始地、目的地、车型查询并返回对应的应收单价" , notes = "成功返回具体数值，如果为0说明应收公里未维护!" )
	public void getCarCostByPhone(
	        HttpServletRequest request ,
	        @ApiParam( value = "起始地城市ID" , required = true ) @RequestParam( value = "stacity" , required = true ) String stacity ,
	        @ApiParam( value = "目的地城市ID" , required = true ) @RequestParam( value = "endcity" , required = true ) String endcity ,
	        @ApiParam( value = "商品车ID" , required = true ) @RequestParam( value = "ICarStyle" , required = true ) String ICarStyle ,
	        @ApiParam( value = "客户ID" , required = true ) @RequestParam( value = "ICustomerId" , required = true ) String ICustomerId ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		int archid = user.getiArchive();
		
		TSubsuppliers sub = subService.get( archid );
		int ICarStyleId = Integer.parseInt( ICarStyle );// 商品车ID
		int stacityId = Integer.parseInt( stacity );
		int endcityId = Integer.parseInt( endcity );
		int CustomerId = Integer.parseInt( ICustomerId );
		try
		{
			float costPrice = iDriverCostService.getCostPrice( ICarStyleId , stacityId ,
			        endcityId , CustomerId , sub.getVcSubno() );
			AjaxUtil.rendJson( response , true , costPrice + "" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "获取失败" );
		}
	}
	
	/**
	 * 
	 * @Description: TODO()
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-4 上午11:37:34
	 */
	@RequestMapping( value = "/getKilometerByPhone" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "查询应收公里接口，根据起始地、目的地、车型查询并返回对应的应收公里" , notes = "成功返回具体数值，如果为0说明应收公里未维护!" )
	public void getKilometerByPhone(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "起始地城市ID" , required = true ) @RequestParam( value = "startID" , required = true ) String startID ,
	        @ApiParam( value = "目的地城市ID" , required = true ) @RequestParam( value = "endcity" , required = true ) String endId )
	{
		int startCityId = Integer.parseInt( startID );
		int endCityId = Integer.parseInt( endId );
		try
		{
			float kilometer = arkilometerService.getKilomter( startCityId , endCityId );
			AjaxUtil.rendJson( response , true , kilometer + "" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "查询失败，原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(根据订单id查询订单状态)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-7 上午10:15:12
	 */
	@SuppressWarnings( "unused" )
	@RequestMapping( value = "/getStatusById" , method = RequestMethod.POST )
	@ApiOperation( value = "根据订单id查询订单发运状态状态" , notes = "private Integer id;//id<br/>"
	        + "private String vcStatusNote;// 状态描述<br/>"
	        + "private Date dtStatus;// 状态生成时间<br/>"
	        + "private String vcStatusTag;// 状态描述标志<br/>"
	        + "private String vcAddUser;// 状态确认人<br/>"
	        + "private Integer nLineId;// 指令明细表ID<br/>"
	        + "private String vcStatusPlace;// 状态生成地点的详细地址<br/>"
	        + "private String vcLongitude;// 状态生成的经度<br/>"
	        + "private String vcLatitude;// 状态生成的纬度<br/>"
	        + "private String vcHash;// 经纬度HASH值<br/>"
	        + "private Integer nOrderId;// 订单ID<br/>"
	        + "private Integer nEnable;// 状态有效性(0有效，1无效)" )
	@ResponseBody
	public Map< String , Object > getStatusById(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "订单的id" , required = true ) @RequestParam( value = "orderId" , required = true ) String orderId )
	{
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		int shipOrId = Integer.parseInt( orderId );
		HqlHelper hql = new HqlHelper( TShipStatus.class );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqual( "nOrderId" , shipOrId );
		hql.setQueryPage( page );
		hql.addOrderBy( "id" , "asc" );
		Map< String , Object > resultMap;
		try
		{
			resultMap = iShipStatusService.findOrderStatusByOrderId( hql );
			
			return AjaxUtil.getMapByResult( visit , resultMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(查询订单按客户运单号进行分组)
	 * @param request
	 * @param response
	 * @param vcStartCity
	 * @param vcEndCity
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-8 下午4:50:33
	 */
	@RequestMapping( value = "/getOrderByCustomerNo" , method = RequestMethod.POST )
	@ApiOperation( value = "查询订单按客户运单号进行分组" , notes = "VC_CUST_ORDERNO : 客户订单号(客户自己录入)<br/>"
	        + "VC_ORDERNO:订单号<br/>VC_START_CITY:起运城市<br/>VC_DEST_CITY:目的城市<br/>N_TOTAL_CAR:数量<br/>VC_CAR_NAME:商品车车名" )
	@ResponseBody
	public Map< String , Object > getOrderByCustomerNo(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "起始地城市" , required = false ) @RequestParam( value = "vcStartCity" , required = false ) String vcStartCity ,
	        @ApiParam( value = "目的地城市" , required = false ) @RequestParam( value = "vcEndCity" , required = false ) String vcEndCity )
	{
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		int archid = user.getiArchive();
		TSubsuppliers sub = subService.get( archid );
		String subNo = sub.getVcSubno();// 分供方编号
		
		String sql = "SELECT ord.id, ord.vc_cust_orderno,  ord.vc_orderno, ord.vc_start_city,"
		        + " ord.vc_dest_city,ord.n_total_car, ord.vc_car_name  FROM T_order ord where ord.vc_subno='"
		        + subNo + "'" + " and ord.n_enable=0";
		Map< String , Object > resultMap;
		if ( StringUtils.isNotBlank( vcStartCity ) )
		{
			sql += " and ord.vc_start_city = '" + vcStartCity + "'";
		}
		if ( StringUtils.isNotBlank( vcEndCity ) )
		{
			sql += "  and ord.vc_dest_city = '" + vcStartCity + "'";
		}
		sql += "  order by ord.vc_cust_orderno";
		System.out.println( "sql = " + sql );
		try
		{
			resultMap = orderService.findBySpringSql( sql , page );
			List< Map< String , Object >> arlist = ( List< Map< String , Object >> ) resultMap
			        .get( "rows" );
			String custNo = "";
			Map< String , Object > rMap = null;
			Map< String , Object > rMap2 = null;
			List< Map< String , Object > > resultList = new ArrayList< Map< String , Object > >();
			List itemList = null;
			for ( Map< String , Object > map : arlist )
			{
				System.out.println( "***=" + map.get( "VC_CUST_ORDERNO" ) );
				if ( null != map.get( "VC_CUST_ORDERNO" ) )
				{
					
					if ( ! custNo.equals( map.get( "VC_CUST_ORDERNO" ) ) )
					{
						custNo = ( String ) map.get( "VC_CUST_ORDERNO" );
						// map.remove( "VC_CUST_ORDERNO" );
						/*	
							if ( null != rMap )
							{
								rMap.put( "item" , itemList );
								resultList.add( rMap );
							}*/
						
						rMap = new HashMap< String , Object >();
						
						itemList = new ArrayList();
						
						rMap.put( "custNo" , custNo );
						itemList.add( map );
						if ( null != rMap )
						{
							rMap.put( "item" , itemList );
							resultList.add( rMap );
						}
					}
					else
					{
						// map.remove( "VC_CUST_ORDERNO" );
						itemList.add( map );
					}
				}
				
			}
			
			return AjaxUtil.getMapByNotException( true , resultList );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(根据客户运单号查询详情)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-8 下午4:53:47
	 */
	@ApiOperation( value = "根据客户运单号查询详情" , notes = "VC_CUST_ORDERNO : 客户订单号(客户自己录入)<br/>"
	        + "VC_ORDERNO:订单号<br/>VC_START_CITY:起运城市<br/>VC_DEST_CITY:目的城市<br/>N_TOTAL_CAR:数量<br/>VC_CAR_NAME:商品车车名" )
	@RequestMapping( value = "/getDetailByCusNo" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDetailByCusNo(
	        HttpServletRequest request ,
	        @ApiParam( value = "客户运单号" , required = true ) @RequestParam( value = "custNo" , required = true ) String custNo ,
	        HttpServletResponse response )
	{
		String sql = "";
		if ( StringUtils.isNotBlank( custNo ) )
		{
			sql = "SELECT  ord.id, ord.vc_orderno, ord.vc_start_city,"
			        + " ord.vc_dest_city,ord.n_total_car, ord.vc_car_name  FROM T_order ord where ord.vc_cust_orderno='"
			        + custNo + "'";
		}
		else
		{
			sql = "SELECT  ord.id, ord.vc_orderno, ord.vc_start_city,"
			        + " ord.vc_dest_city,ord.n_total_car, ord.vc_car_name  FROM T_order ord where ord.vc_cust_orderno is null";
		}
		Page page = ServiceUtil.getcurrPage( request );
		Map< String , Object > resultMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			resultMap = orderService.findBySpringSql( sql , page );
			return AjaxUtil.getMapByResult( visit , resultMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
}
