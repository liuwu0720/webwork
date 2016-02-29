package com.clt.sub.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.common.UserSession;
import com.clt.sub.model.TApplyPic;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TShipline;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.service.IDamagePicService;
import com.clt.sub.service.IOrderService;
import com.clt.sub.service.IShipheadService;
import com.clt.sub.service.IShiplineService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITApplyPicService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.sub.controller
 * @Description: 发运
 * @author chenbin
 * @date 2014-7-17 上午11:21:52
 * @version V1.0
 */

@Controller
@RequestMapping( "/shipheadAction" )
@Api( value = "shipheadAction-api" , description = "订单的配载、入场、发运等api" , position = 5 )
public class ShipheadAction
{
	
	@Autowired
	IShipheadService shipheadService;
	@Autowired
	ITruckDriverService driverService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IShiplineService iShiplineService;
	@Autowired
	IDamagePicService idamagePicService;
	@Autowired
	ITApplyPicService iApplyPicService;
	
	/**
	 * @Description: 保存发运指令
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/save" )
	@ResponseBody
	@ApiIgnore
	public String save( HttpServletRequest request , HttpServletResponse resp )
	{
		// msgstr 订单,发运数量; 格式
		String msgstr = request.getParameter( "msgstr" );
		String[] strs = msgstr.split( ";" );
		
		// 司机车辆ID
		int driverID = Integer.parseInt( request.getParameter( "driverID" ) );
		String msg = shipheadService.saveshipHead( strs , driverID );
		System.out.println( "return msg " + msg );
		return msg;
	}
	
	/**
	 * 
	 * @Description: TODO(订单配载)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-29 下午6:00:01
	 */
	@RequestMapping( value = "/loading" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "订单配载接口" , notes = "订单配载接口,返回提示格式如:{'resultCode':'no','errorContent':'获取订单出错，N1501220007  订单号有重复'}<br/>"
	        + "{'resultCode':'ok','errorContent':'原订单N1501220007进行了下拆分'}<br/>"
	        + "/subsuppliersAction/getAllShiDateBySubno：获取可用的车辆" , position = 5 , response = TOrder.class )
	public String loading(
	        HttpServletRequest request ,
	        @ApiParam( value = "订单编号及数量msgstr格式如:N1505040011,7;N1501220007,5;其中N1505040011为订单号,7为数量，多个订单都以这种格式进行拼接，String类型" ) @RequestParam( value = "msgstr" , required = true ) String msgstr ,
	        @ApiParam( value = "拖车ID" ) @RequestParam( value = "driverID" , required = true ) String driverID ,
	        HttpServletResponse response )
	{
		// msgstr 订单,发运数量; 格式
		// String msgstr = request.getParameter( "msgstr" );
		String[] strs = msgstr.split( ";" );
		
		// 司机车辆ID
		int driveID = Integer.parseInt( driverID );
		String msg = shipheadService.saveshipHead( strs , driveID );
		System.out.println( "return msg " + msg );
		return msg;
	}
	
	/**
	 * 追加配载
	 * 
	 * @param request
	 * @param msgstr
	 * @param truckId
	 * @param response
	 * @return
	 */
	@RequestMapping( "/appendLoading" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > appendLoading(
	        HttpServletRequest request ,
	        @ApiParam( value = "订单编号及数量msgstr格式如:N1505040011,7;N1501220007,5;其中N1505040011为订单号,7为数量，多个订单都以这种格式进行拼接，String类型" ) @RequestParam( value = "msgstr" , required = true ) String msgstr ,
	        @ApiParam( value = "拖车ID" ) @RequestParam( value = "truckId" , required = true ) int truckId ,
	        HttpServletResponse response )
	{
		Map< String , Object > map = shipheadService.getLatestShiphead( truckId );
		if ( map == null )
		{
			return AjaxUtil.getMap( false , "追加配载失败，找不到配载信息" );
		}
		int headId = Integer.parseInt( map.get( "ID" ).toString() );
		
		String[] strs = msgstr.split( ";" );
		try
		{
			Map< String , Object > result = shipheadService.saveAppendLoading( strs ,
			        headId );
			return result;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(添加配载)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-7 下午4:09:55
	 */
	@RequestMapping( value = "/addLoading" , method = RequestMethod.POST )
	@ApiOperation( value = "增加配载接口(车队订单--订单详情--增加配载)" , notes = "在已配载订单中增加多个订单进行配载<br/>"
	        + "查询已配载的订单接口：orderAction/getAllOrderBySubnoPhone <br/>"
	        + "查询订单详情接口：orderAction/getOrderDetailPhone " )
	@ApiIgnore
	public String addLoading(
	        HttpServletRequest request ,
	        @ApiParam( value = "订单编号及数量msgstr格式如:N1505040011,7;N1501220007,5;其中N1505040011为订单号,7为数量，多个订单都以这种格式进行拼接，String类型" ) @RequestParam( value = "msgstr" , required = true ) String msgstr ,
	        @ApiParam( value = "拖车ID" ) @RequestParam( value = "driverID" , required = true ) String driverID ,
	        @ApiParam( value = "指令主表ID" ) @RequestParam( value = "headID" , required = true ) String headID ,
	        HttpServletResponse response )
	{
		String[] strs = msgstr.split( ";" );// msgstr 订单,发运数量; 格式
		// 司机车辆ID
		int driveID = Integer.parseInt( driverID );
		int iHeadId = Integer.parseInt( headID );
		String msg = shipheadService.saveUpdateShipHead( strs , driveID ,
		        iHeadId );
		System.out.println( "return msg " + msg );
		return msg;
	}
	
	/**
	 * 
	 * @Description: TODO(删除配载)
	 * @param request
	 * @param orderId
	 *            订单ID
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-8 下午3:57:49
	 */
	@RequestMapping( value = "deleteLoading" , method = RequestMethod.POST )
	public void deleteLoading(
	        HttpServletRequest request ,
	        @ApiParam( value = "订单ID" ) @RequestParam( value = "orderId" , required = true ) String orderId ,
	        HttpServletResponse response )
	{
		TOrder tOrder = orderService.get( Integer.parseInt( orderId ) );
		tOrder.setnLoad( 0 );// 设为未配载
		try
		{
			orderService.saveOrUpdateOrder( tOrder );
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！原因:" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(手机端获取拖车及拖车主驾司机接口)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-25 下午5:10:51
	 */
	@RequestMapping( value = "/getTruckAndDriver" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "手机端获取拖车及拖车主驾司机接口" , notes = "VC_DRIVER_NAME:司机名字<br/>"
	        + "ID:拖车的id<br/>VC_CAR_NO:拖车车牌号<br/>" )
	public Map< String , Object > getTruckAndDriver( HttpServletRequest request ,
	        HttpServletResponse response)
	{
		Map< String , Object > orderMap;
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		String sql = "select driver.vc_driver_name,driver.VC_DRIVER_TEL,truck.id,truck.vc_car_no from t_driver driver, "
		        + "t_truck_driver truck, t_truck_driver_link drilink where drilink.i_truck = truck.id "
		        + "and drilink.i_driver = driver.id and drilink.n_position_type = 1 and truck.n_status=0 and driver.I_USERID = "
		        + user.getId()+ "  order by truck.vc_car_no";
		Page page = null;
		try
		{
			orderMap = shipheadService.getSpringSQL( sql , page );
			int total = ( Integer ) orderMap.get( "total" );
			if ( 0 == total )
			{
				return AjaxUtil.getMap( false , "当前没有空闲运力！司机做抵达操作才可释放运力！" );
			}
			return AjaxUtil.getMapByResult( visit , orderMap );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	@RequestMapping( "/intoShipHeadInfoList" )
	@ApiIgnore
	public String intoShipHeadInfoList( HttpServletRequest request )
	{
		
		return "sub/dispatch/shipHeadInfoList";
		
	}
	
	/**
	 * @Description: 进入发运指令前加载的数据 获取该分供方可用车辆
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiIgnore
	@RequestMapping( value = "/getAllShiDateBySubno" , method = RequestMethod.POST )
	public List< TTruckDriver > getAllShiDateBySubno( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		Page page = ServiceUtil.getcurrPage( request );
		// 获取分供方所有可用的车辆信息
		List< TTruckDriver > driverlist = driverService.findByPropertys( 
		new String[] {"NEnable" , "vcSubno" , "Nstatus" } ,
		new Object[] { 0 , subbo , 0 } ,page );
		
		return driverlist;
		
	}
	
	/**
	 * @Description: 获取分供方下可配载的订单
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiIgnore
	@RequestMapping( "/getEnableOrdersBySubno" )
	@ResponseBody
	public Map< String , Object > getEnableOrdersBySubno( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		Map< String , Object > orderMap = null;
		String visit = request.getParameter( "visit" );
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();
			
			Page page = ServiceUtil.getcurrPage( request );
			
			String sql = "select ord.id,ord.vc_start_city,ord.vc_dest_city,ord.vc_orderno,ord.vc_receive_contact,ord.vc_receive_address,ord.vc_receive_tel,ord.n_total_car,ord.n_shipedqty, ord.n_total_car-ord.n_shipedqty as quantity,sty.vc_car_style  "
			        + " from t_order ord,t_sub_car_style sty where ord.i_car_style = sty.id and ord.n_enable="
			        + SystemConstants.SYS_ENABLE
			        + " and ord.vc_subno='"
			        + subbo
			        + "' and ord.N_LOADING=0";
			System.out.println( "getAllOrderBySubno=  " + sql );
			String orderNo = request.getParameter( "orderNo" );
			if ( StringUtils.isNotBlank( orderNo ) )
			{
				System.out.println( "orderNo=" + orderNo );
				sql += " and ord.vc_orderno like '%" + orderNo + "%' ";
			}
			String staCity = request.getParameter( "staCity" );
			if ( StringUtils.isNotBlank( staCity ) )
			{
				System.out.println( "staCity=" + staCity );
				sql += " and ord.vc_start_city like '%" + staCity + "%' ";
			}
			String destCity = request.getParameter( "destCity" );
			if ( StringUtils.isNotBlank( destCity ) )
			{
				System.out.println( "destCity=" + destCity );
				sql += " and ord.vc_dest_city like '%" + destCity + "%' ";
			}
			sql += " order by ord.DT_CREATEDATE desc";
			System.out.println( "sql = " + sql );
			orderMap = shipheadService.getSpringSQL( sql , page );
			
			List relist = ( List ) orderMap.get( "rows" );
			// System.out.println( " getAllOrderBySubno  size " + relist.size()
			// );
			if ( StringUtils.isNotBlank( visit )
			        && SystemConstants.APP_VISIT.equals( visit ) )
			{
				if ( CollectionUtils.isNotEmpty( relist ) )
				{
					return AjaxUtil.getMapByNotException( true , orderMap );
				}
				else
				{
					return AjaxUtil.getMapByNotException( false , orderMap );
				}
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			if ( StringUtils.isNotBlank( visit )
			        && SystemConstants.APP_VISIT.equals( visit ) )
			{
				return AjaxUtil.getMapByException( e );
			}
		}
		return orderMap;
	}
	
	/**
	 * @Description: 进入发运指令前加载的数据 获取该分供方可用订单
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiOperation( value = "车队订单-新订单" , notes = "获得新订单的接口，该订单未全部调度完，可配载，从当前会话中获取分供方编号" , position = 5 )
	@RequestMapping( value = "/getAllOrderBySubno" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllOrderBySubno( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		Map< String , Object > orderMap = null;
		String visit = request.getParameter( "visit" );
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();
			
			Page page = ServiceUtil.getcurrPage( request );
			
			String sql = "select ord.id,ord.vc_start_city,ord.vc_dest_city,ord.vc_orderno,ord.vc_receive_contact,ord.vc_receive_address,ord.vc_receive_tel,ord.n_total_car,ord.n_shipedqty, ord.n_total_car-ord.n_shipedqty as quantity,sty.vc_car_style  "
			        + " from t_order ord,t_sub_car_style sty where ord.i_car_style = sty.id and ord.n_enable="
			        + SystemConstants.SYS_ENABLE
			        + " and ord.vc_subno='"
			        + subbo + "' and ord.n_shipedqty < ord.n_total_car";
			System.out.println( "getAllOrderBySubno=  " + sql );
			String orderNo = request.getParameter( "orderNo" );
			if ( StringUtils.isNotBlank( orderNo ) )
			{
				System.out.println( "orderNo=" + orderNo );
				sql += " and ord.vc_orderno like '%" + orderNo + "%' ";
			}
			String staCity = request.getParameter( "staCity" );
			if ( StringUtils.isNotBlank( staCity ) )
			{
				System.out.println( "staCity=" + staCity );
				sql += " and ord.vc_start_city like '%" + staCity + "%' ";
			}
			String destCity = request.getParameter( "destCity" );
			if ( StringUtils.isNotBlank( destCity ) )
			{
				System.out.println( "destCity=" + destCity );
				sql += " and ord.vc_dest_city like '%" + destCity + "%' ";
			}
			sql += " order by ord.DT_CREATEDATE desc";
			System.out.println( "sql = " + sql );
			orderMap = shipheadService.getSpringSQL( sql , page );
			
			List relist = ( List ) orderMap.get( "rows" );
			// System.out.println( " getAllOrderBySubno  size " + relist.size()
			// );
			if ( StringUtils.isNotBlank( visit )
			        && SystemConstants.APP_VISIT.equals( visit ) )
			{
				if ( CollectionUtils.isNotEmpty( relist ) )
				{
					return AjaxUtil.getMapByNotException( true , orderMap );
				}
				else
				{
					return AjaxUtil.getMapByNotException( false , orderMap );
				}
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			if ( StringUtils.isNotBlank( visit )
			        && SystemConstants.APP_VISIT.equals( visit ) )
			{
				return AjaxUtil.getMapByException( e );
			}
		}
		return orderMap;
	}
	
	/**
	 * @Description: 根据车辆 查询该所载的订单车数车型
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiOperation( value = "承运方， 查询他的车辆所载的订单车数车型" , notes = "承运方， 查询他的车辆所载的订单车数车型，可指定对应车牌号；vcCarNo 车牌号" , position = 5 )
	@RequestMapping( value = "/getDriverInfoBySubno" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDriverInfoBySubno(
	        @ApiParam( value = "车牌号" , required = true ) @RequestParam( "vcCarNo" ) String vcCarNo ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		Map< String , Object > drimap = null;
		String visit = request.getParameter( "visit" );
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();
			
			Page page = ServiceUtil.getcurrPage( request );
			HqlHelper hql = new HqlHelper( TTruckDriver.class );
			hql.setQueryPage( null );
			
			hql.addEqual( "vcSubno" , subbo );
			hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
			// hql.addEqual( "NStatus" , 0 );
			
			// String vcCarNo = request.getParameter( "vcCarNo" );
			if ( StringUtils.isNotBlank( vcCarNo ) )
			{
				System.out.println( "vcCarNo=" + vcCarNo );
				hql.addLike( "vcCarNo" , vcCarNo );
			}
			
			drimap = driverService.findAllByHqlHelp( hql );
			return AjaxUtil.getMapByResult( visit , drimap );
			// List relist = ( List ) drimap.get( "rows" );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			if ( StringUtils.isNotBlank( visit )
			        && SystemConstants.APP_VISIT.equals( visit ) )
			{
				return AjaxUtil.getMapByException( e );
			}
			
		}
		return drimap;
		
	}
	
	// 进入 发运管理页面
	@RequestMapping( "/intoAllShipdetailBySubno" )
	@ApiIgnore
	public String intoAllShipdetailBySubno( HttpServletRequest request )
	{
		
		return "sub/dispatch/despatchInfoList";
	}
	
	// 查询发运数据 查询 未发运的，发运的，司机抵达的，分供方回单的订单数据
	@ApiOperation( value = "获得车队订单信息" , notes = "发运的，司机抵达的，分供方回单的订单数据" , position = 5 )
	@RequestMapping( value = "/getAllShipdetailBySubno" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllShipdetailBySubno(
	        @ApiParam( value = "订单类型，未发运为parEntrance，未回单parship，回单parArrived等" , required = true ) @RequestParam( "partype" ) String partype ,
	        @ApiParam( value = "指令号，查询条件" , required = false ) @RequestParam( value = "shipno" , required = false ) String shipno ,
	        @ApiParam( value = "车牌号，查询条件" , required = false ) @RequestParam( value = "driverno" , required = false ) String driverno ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		Map< String , Object > orderMap;
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();
			// String partype = "";
			if ( partype == null || "".equals( partype ) )
			{
				partype = "parEntrance";
			}
			else
			{
				partype = request.getParameter( "partype" );
			}
			
			Page page = ServiceUtil.getcurrPage( request );
			String sql = "select head.id as headid,head.vc_shipno,dri.id as driverID,to_char(head.dt_create,'yy-mm-dd hh24:mi') as opdate,dri.vc_car_no,getlineDestCitys(head.id,'startcity') vc_start_city,getlineDestCitys(head.id,'endcity') vc_dest_city,sum(line.n_ship_qty) shipCount "
			        + " from t_shiphead head,t_shipline line,t_truck_driver dri "
			        + " where head.id=line.i_shiphead and head.i_truck_id=dri.id and head.n_enable = "
			        + SystemConstants.SYS_ENABLE + " and head.vc_subno='" + subbo + "' ";
			
			String groupsql = " group by head.id,head.vc_shipno,to_char(head.dt_create,'yy-mm-dd hh24:mi')  ,dri.id,dri.vc_car_no ";
			
			// 获取查询条件
			// String shipno = request.getParameter( "shipno" );
			if ( StringUtils.isNotBlank( shipno ) )
			{
				System.out.println( "shipno=" + shipno );
				sql += " and head.vc_shipno like '%" + shipno + "%' ";
			}
			
			// String driverno = request.getParameter( "driverno" );
			if ( StringUtils.isNotBlank( driverno ) )
			{
				System.out.println( "driverno=" + driverno );
				sql += " and dri.vc_car_no like '%" + driverno + "%' ";
			}
			
			// 发运管理 查询条件 未发运 未装车 ..
			if ( partype.equals( "parEntrance" ) )
			{
				sql += " and line.N_CURRENT_STATUS = 0 ";
			}
			else if ( partype.equals( "parload" ) )
			{
				sql += " and line.N_CURRENT_STATUS= "
				        + SystemConstants.SYS_SUB_PARENTRANCE;
			}
			else if ( partype.equals( "parship" ) )
			{
				sql += " and line.N_CURRENT_STATUS= " + SystemConstants.SYS_SUB_PARLOAD;
			}
			else if ( partype.equals( "parArrived" ) )
			{
				sql += " and line.N_CURRENT_STATUS= " + SystemConstants.SYS_SUB_PARSHIP;
			}
			else if ( partype.equals( "parReturn" ) )
			{
				sql += " and line.N_CURRENT_STATUS= "
				        + SystemConstants.SYS_SUB_PARARRIVED;
			}
			else if ( partype.equals( "parsecure" ) )
			{
				sql += " and line.N_CURRENT_STATUS>" + SystemConstants.SYS_SUB_PARLOAD
				        + " and line.N_CURRENT_STATUS<" + SystemConstants.SYS_SUB_PARSHIP;
			}
			sql = sql + groupsql + " order by head.vc_shipno desc ";
			
			System.out.println( "sql >>>" + sql );
			orderMap = shipheadService.getSpringSQL( sql , page );
			orderMap.put( "partype" , partype );
			List< Map< String , Object >> list = ( List< Map< String , Object >> ) orderMap
			        .get( "rows" );
			for ( Map< String , Object > map : list )
			{
				map.put( "partype" , partype );
			}
			orderMap.put( "rows" , list );
			JSONArray arr = JSONArray.fromObject( orderMap );
			System.out.println( "jsonArr:" + arr );
			return AjaxUtil.getMapByResult( visit , orderMap );
			
			/*if ( orderMap.get( "rows" ) != null )
			{
				
				List relist = ( List ) orderMap.get( "rows" );
				System.out.println( " getAllShipdetailBySubno  size " + relist.size() );
			}
			else
			{
				System.out.println( " getAllShipdetailBySubno  size  0 " );
			}*/
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 得到每种类型的总数
	 * @param request
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2015年4月14日 上午11:00:31
	 */
	@RequestMapping( "/getShipCount" )
	@ResponseBody
	@ApiIgnore
	public String getShipCount( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		String sql = " select  count(1) ncount "
		        + "  from t_shiphead head,t_shipline line ,t_truck_driver dri "
		        + "  where head.id=line.i_shiphead  and head.i_truck_id=dri.id and head.n_enable = "
		        + SystemConstants.SYS_ENABLE + " and head.vc_subno= '" + subbo + "'";
		
		String sqlparam = " and line.N_CURRENT_STATUS =0 ";
		String groupParam = " group by head.id ";
		
		int entranceCount = orderService.getCountSQL( sql + sqlparam + groupParam );
		
		sqlparam = " and line.N_CURRENT_STATUS= " + SystemConstants.SYS_SUB_PARENTRANCE;
		int loadCount = orderService.getCountSQL( sql + sqlparam + groupParam );
		// 保险统计（10<status<15）
		sqlparam = " and line.N_CURRENT_STATUS>"
		        + SystemConstants.SYS_SUB_PARLOAD
		        + " and line.N_CURRENT_STATUS<"
		        + SystemConstants.SYS_SUB_PARSHIP;
		int secureCount = orderService
		        .getCountSQL( sql + sqlparam + groupParam );
		
		sqlparam = " and line.N_CURRENT_STATUS= "
		        + SystemConstants.SYS_SUB_PARLOAD;
		int shipCount = orderService.getCountSQL( sql + sqlparam + groupParam );
		
		sqlparam = " and line.N_CURRENT_STATUS = "
		        + SystemConstants.SYS_SUB_PARSHIP;
		int arrivedCount = orderService.getCountSQL( sql + sqlparam
		        + groupParam );
		
		sqlparam = " and line.N_CURRENT_STATUS = "
		        + SystemConstants.SYS_SUB_PARARRIVED;
		int returnCount = orderService
		        .getCountSQL( sql + sqlparam + groupParam );
		
		JSONObject obj = new JSONObject();
		// 未入场确定
		obj.element( "entranceCount" , entranceCount );
		// 未装车
		obj.element( "loadCount" , loadCount );
		// 未买保险
		obj.element( "secureCount" , secureCount );
		// 未发运
		obj.element( "shipCount" , shipCount );
		// 未运抵
		obj.element( "arrivedCount" , arrivedCount );
		// 未回单
		obj.element( "returnCount" , returnCount );
		return obj.toString();
	}
	
	/**
	 * 
	 * @Description: TODO(入场、装车、买保险、发运、运抵、回单)
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-24 上午10:48:11
	 */
	@RequestMapping( "/saveDespatchInfo" )
	@ApiIgnore
	public void saveDespatchInfo( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		// 入场 装车 发车 回单 参数类型
		String partype = request.getParameter( "partype" );
		// 发运指令 号
		String shipnos = request.getParameter( "shipnos" );
		boolean flag = true;
		/**
		 * 回单的时候验证
		 */
		if ( partype.equals( "parReturn" ) )
		{
			String[] strs = shipnos.split( "," );
			strs = shipnos.split( ";" );
			for ( String str : strs )
			{
				String[] shipstr = str.split( "," );
				TShipline ship = iShiplineService.get( Integer.parseInt( shipstr[0] ) );
				if ( ! ship.getNCurrentStatus().equals(
				        SystemConstants.SYS_SUB_PARARRIVED ) )
				{
					flag = false;
					break;
				}
			}
		}
		if ( flag )
		{
			try
			{
				
				shipheadService.saveDespatchInfo( partype , shipnos , user.getId() );
				AjaxUtil.rendJson( resp , true , "保存成功." );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				AjaxUtil.rendJson( resp , false , "保存失败！失败原因：" + e.getMessage() );
			}
		}
		else
		{
			AjaxUtil.rendJson( resp , false , "该指令还有订单未抵达不 能回单！" );
		}
		
	}
	
	/**
	 * 
	 * @Description: 访问页面 根据发运主表ID 获取对应的订单发运详情 因为Web-info 无法直接访问
	 * @param request
	 * @param resp
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-9 下午2:04:21
	 */
	@RequestMapping( "/IntogetShipInfoByHeadID" )
	@ApiIgnore
	public String IntogetShipInfoByHeadID( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		String headID = request.getParameter( "headID" );
		if ( request.getParameter( "partype" ) != null )
		{
			String partype = request.getParameter( "partype" );
			request.setAttribute( "partype" , partype );
		}
		request.setAttribute( "headID" , headID );
		return "sub/dispatch/showShipHeadInfo";
	}
	
	/**
	 * 
	 * @Description: 根据发运主表ID 获取对应的订单发运详情
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-9 下午1:41:22
	 */
	@RequestMapping( "/getShipInfoByHeadID" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getShipInfoByHeadID(
	        HttpServletRequest request )
	{
		// 发运主表ID
		String headID = request.getParameter( "headID" );
		String sql = " select line.id,ord.vc_orderno as orderno,line.n_ship_qty as shipqty,line.N_ARRIVEQTY from t_shipline line ,t_order ord where line.i_order_id=ord.id and line.i_shiphead=  "
		        + headID;
		System.out.println( "headID >> " + headID );
		Page page = ServiceUtil.getcurrPage( request );
		
		Map< String , Object > orderMap = shipheadService.getSpringSQL( sql ,
		        page );
		
		List relist = ( List ) orderMap.get( "rows" );
		System.out.println( " getOrderInfoByHeadID  size " + relist.size() );
		return orderMap;
	}
	
	/**
	 * 
	 * @Description: 获取未发运指令的页面 前
	 * @param request
	 * @param resp
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-9 下午2:04:21
	 */
	@RequestMapping( "/IntogetNotShipInfo" )
	@ApiIgnore
	public String IntogetNotShipInfo( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		
		return "sub/dispatch/shipHeadCancel";
	}
	
	/**
	 * 
	 * @Description: 获取未发运的指令 用于指令取消
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-9 下午1:41:22
	 */
	@RequestMapping( "/getNotShipInfo" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getNotShipInfo( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		// Map maps = SystemConstants.statusCollection;
		// System.out.println( maps.get( 5 ) );
		String sql = " select head.id as headid,head.vc_shipno,dri.id as driverID,to_char(head.dt_create,'yy-mm-dd hh24:mi') as opdate,dri.vc_car_no,getlineDestCitys(head.id,'startcity') vc_start_city,getlineDestCitys(head.id,'endcity') vc_dest_city,sum(line.n_ship_qty) shipCount "
		        + " from t_shiphead head,t_shipline line,t_truck_driver dri  "
		        + " where head.id=line.i_shiphead and head.i_truck_id=dri.id and head.n_enable ="
		        + SystemConstants.SYS_ENABLE
		        + "  and head.vc_subno='"
		        + subbo
		        + "' "
		        + " and line.n_current_status <"
		        + SystemConstants.SYS_SUB_PARSHIP;
		
		String groupsql = " group by head.id,head.vc_shipno,to_char(head.dt_create,'yy-mm-dd hh24:mi')  ,dri.id,dri.vc_car_no ";
		
		// 获取查询条件
		String shipno = request.getParameter( "shipno" );
		if ( StringUtils.isNotBlank( shipno ) )
		{
			System.out.println( "shipno=" + shipno );
			sql += " and head.vc_shipno like '%" + shipno + "%' ";
		}
		
		String driverno = request.getParameter( "driverno" );
		if ( StringUtils.isNotBlank( driverno ) )
		{
			System.out.println( "driverno=" + driverno );
			sql += " and dri.vc_car_no like '%" + driverno + "%' ";
		}
		
		sql = sql + groupsql + " order by head.vc_shipno desc ";
		Page page = ServiceUtil.getcurrPage( request );
		
		Map< String , Object > orderMap = shipheadService.getSpringSQL( sql ,
		        page );
		
		List relist = ( List ) orderMap.get( "rows" );
		System.out.println( " getNotShipInfo  size " + relist.size() );
		
		return orderMap;
	}
	
	/**
	 * 
	 * @Description: 保存发运指令取消
	 * @param request
	 * @param resp
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-9 下午2:04:21
	 */
	@RequestMapping( "/saveShipInfoCancel" )
	@ApiIgnore
	public void saveShipInfoCancel( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		String headids = request.getParameter( "headids" );
		String msgstr = shipheadService.saveShipInfoCancel( headids );
		
		if ( msgstr == "ok" )
		{
			AjaxUtil.rendJson( resp , true , "保存成功." );
		}
		else if ( msgstr == "shiperror" )
		{
			AjaxUtil.rendJson( resp , false , "指令号已发运." );
		}
		
	}
	
	/**
	 * 
	 * @Description: 显示调度指令详情
	 * @param request
	 * @param resp
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-9 下午2:04:21
	 */
	@RequestMapping( "/intogetShipDetailsByID" )
	@ApiIgnore
	public String intogetShipDetailsByID( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		int headID = Integer.parseInt( request.getParameter( "headID" ) );
		TShiphead head = shipheadService.get( headID );
		TTruckDriver truck = driverService.get( head.getITruckId() );
		
		Map< Integer , String > staMap = SystemConstants.getstatusCollection;
		JSONArray jsarr = JSONArray.fromObject( staMap );
		request.setAttribute( "staMapJson" , jsarr.toString() );
		System.out.println( "jsarr.toString() " + jsarr.toString() );
		
		List< TDriver > driverlist = driverService.getDriversByTruckID( head
		        .getITruckId() );
		request.setAttribute( "head" , head );
		request.setAttribute( "truck" , truck );
		request.setAttribute( "driverlist" , driverlist );
		String partype = request.getParameter( "partype" );
		request.setAttribute( "partype" , partype );
		return "sub/dispatch/showShipHeadDetails";
		
	}
	
	/**
	 * 
	 * @Description: 保存发运指令取消
	 * @param request
	 * @param resp
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-9 下午2:04:21
	 */
	@RequestMapping( "/getShipDetailsByID" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getShipDetailsByID(
	        HttpServletRequest request , HttpServletResponse resp )
	{
		int headID = Integer.parseInt( request.getParameter( "headID" ) );
		
		String sql = "select line.id ,ord.vc_orderno,line.vc_start_city,line.vc_dest_city,line.n_arkilometer, line.n_ship_qty ,line.n_current_status linestatus "
		        + " from t_shipline line,t_order ord  where ord.id=line.i_order_id "
		        + " and  line.i_shiphead=" + headID;
		
		Page page = ServiceUtil.getcurrPage( request );
		Map< String , Object > resuMap = shipheadService.getSpringSQL( sql ,
		        page );
		return resuMap;
	}
	
	/**
	 * 
	 * @Description: TODO(车队获取订单分类接口)
	 * @param partype
	 * @param shipno
	 * @param driverno
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-25 下午4:45:16
	 */
	@ApiIgnore
	@RequestMapping( value = "/getCurrentOrderBySubno" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getCurrentOrderBySubno(
	        @ApiParam( value = "订单类型，'now':当前订单；'begin'：未开始订单;'before':历史订单" , required = true ) @RequestParam( "orderType" ) String orderType ,
	        @ApiParam( value = "订单类型2：'Z':待装车；'F':待发运；'D':待抵达<br/>注意：该条件查询只针对当前订单" , required = false ) @RequestParam( "orderType2" ) String orderType2 ,
	        @ApiParam( value = "起始地" , required = false ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "目的地" , required = false ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		Map< String , Object > orderMap;
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();
			
			Page page = ServiceUtil.getcurrPage( request );
			String sql = "select o.* from t_order o , t_shipline ts where o.id = ts.i_order_id and o.n_enable = 0 ";
			try
			{
				if ( orderType.equalsIgnoreCase( "now" ) )// 当前订单 即已配载的订单
				{
					sql += " ";
				}
				if ( orderType.equalsIgnoreCase( "begin" ) )// 未开始订单 即等待配载的订单
				{
					sql = "SELECT o.* FROM T_ORDER o where o.N_ENABLE = 0 and o.N_LOADING = 0";// 有效未配载的订单
				}
				if ( orderType.equalsIgnoreCase( "before" ) )// 历史订单 已运抵的订单
				{
					sql += " and ts.n_current_status = 18";
				}
				if ( StringUtils.isNotBlank( orderType2 ) )
				{
					if ( orderType2.equalsIgnoreCase( "Z" ) )// 待装车(已配载，已接单)
					{
						sql = " select o.* ,ts.n_current_status from t_order o , t_shipline ts where"
						        + " (ts.n_current_status = 0 or ts.n_current_status=3) and o.id = ts.i_order_id and o.n_enable = 0 ";
					}
					if ( orderType2.equalsIgnoreCase( "F" ) )// 待发运
					{
						sql += " and ts.n_current_status = 10";
					}
					if ( orderType2.equalsIgnoreCase( "D" ) )// 待抵达
					{
						sql += " and ts.n_current_status = 15";
					}
				}
				sql += " order by o.VC_CUST_ORDERNO ";
			}
			catch ( Exception e )
			{
				return AjaxUtil.getMapByException( e );
			}
			
			System.out.println( "sql >>>" + sql );
			orderMap = shipheadService.getSpringSQL( sql , page );
			return AjaxUtil.getMapByResult( visit , orderMap );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 *             Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-10 下午6:20:13
	 */
	@SuppressWarnings( "unchecked" )
	@RequestMapping( value = "/getPicDetail" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getPicDetail( HttpServletRequest request ,
	        HttpServletResponse response ) throws UnsupportedEncodingException
	{
		int picId = Integer.parseInt( request.getParameter( "damaId" ) );
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TApplyPic.class );
		hql.setQueryPage( page );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqualIgnoreCase( "vcTableName" , "TSHIPLINE" );
		hql.addEqual( "iTableId" , picId );
		Map< String , Object > resuMap = idamagePicService.findAllByHqlHelp( hql );
		List< TApplyPic > applyPics = ( List< TApplyPic > ) resuMap.get( "rows" );
		String vcType = "orderArrive";
		for ( TApplyPic tApplyPic : applyPics )
		{
			iApplyPicService.parseUrl( tApplyPic , vcType );
		}
		
		return resuMap;
		
	}
	
	/**
	 * 
	 * @Description: TODO(ERP二手车)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-21 上午10:01:28
	 */
	@ApiOperation( value = "ERP二手车业务接口:ERP二手车" , notes = "根据ERP二手车的指令主表ID向新平台发送根据请求" , position = 5 )
	@RequestMapping( value = "/getInfosFromErp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getInfosFromErp( HttpServletRequest request ,
	        @ApiParam( value = "ERP指令主表ID" ) @RequestParam( "headId" ) String headId ,
	        HttpServletResponse response )
	{
		int iheadId = Integer.parseInt( headId );
		// String sql1 =
		// "SELECT * FROM v_shiphead_erp@link_erp.unlcn.com head where head.ILINEID = ?";
		String message = "";
		
		message = shipheadService.saveShipHeadFromErp( iheadId );
		if ( message.equalsIgnoreCase( "success" ) )
		{
			return AjaxUtil.getMap( true , "配载成功" );
		}
		else
		{
			return AjaxUtil.getMap( false , message );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(取消来自ERP的指令)
	 * @param request
	 * @param headId
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-24 下午2:16:25
	 */
	@ApiOperation( value = "ERP二手车业务接口:取消来自ERP的指令" , notes = "取消来自ERP的指令" , position = 5 )
	@RequestMapping( value = "/cancelShipFromErp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > cancelShipFromErp(
	        HttpServletRequest request ,
	        @ApiParam( value = "ERP指令主表ID" ) @RequestParam( value = "headId" , required = true ) String headId ,
	        HttpServletResponse response )
	{	
		int iheadId = Integer.parseInt( headId );
		String message = "";
		
		message = shipheadService.updateCancelShipHead( iheadId );
		if ( message.equalsIgnoreCase( "success" ) )
		{
			return AjaxUtil.getMap( true , "成功！" );
		}
		else
		{
			return AjaxUtil.getMap( false , message );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(修改车辆和司机)
	 * @param request
	 * @param headId
	 * @param truckId
	 * @param driverId
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-24 下午2:57:01
	 */
	@ApiOperation( value = "ERP二手车业务接口:修改车辆和司机" , notes = "根据指令主表ID修改拖车和司机ID" , position = 5 )
	@RequestMapping( value = "/changeTruckAndDriverFromErp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > changeTruckAndDriverFromErp(
	        HttpServletRequest request ,
	        @ApiParam( value = "EPR指令主表ID" ) @RequestParam( value = "headId" , required = true ) String headId ,
	        @ApiParam( value = "ERP拖车ID" ) @RequestParam( value = "truckId" , required = true ) String truckId ,
	        @ApiParam( value = "ERP司机ID" ) @RequestParam( value = "driverId" , required = true ) String driverId )
	{	
		int iheadId = Integer.parseInt( headId );
		int itruckId = Integer.parseInt( truckId );
		int idriverId = Integer.parseInt( driverId );
		String message = "";
		
		message = shipheadService.updateShipHeadTruckAndDriver( iheadId ,
		        itruckId , idriverId );
		if ( message.equalsIgnoreCase( "success" ) )
		{
			return AjaxUtil.getMap( true , "修改成功！" );
		}
		else
		{
			return AjaxUtil.getMap( false , message );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param headId
	 * @param orderIds
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-25 上午10:39:35
	 */
	@ApiOperation( value = "ERP二手车业务接口:增加或删除订单接口" , notes = "增加或删除订单接口" , position = 5 )
	@RequestMapping( value = "/changeOrderQtysFromErp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > changeOrderQtysFromErp(
	        @ApiParam( value = "EPR指令主表ID" ) @RequestParam( value = "headId" , required = true ) String headId ,
	        @ApiParam( value = "add:增加；delete:删除" ) @RequestParam( value = "type" , required = true ) String type ,
	        @ApiParam( value = "ERP订单ID:需要增加和删除的订单，多个以','相连" ) @RequestParam( value = "orderIds" , required = true ) String orderIds )
	{
		int iheadId = Integer.parseInt( headId );
		String message = shipheadService.updateChangeOrderQtysFromErp( iheadId ,
		        type , orderIds );
		if ( message.equalsIgnoreCase( "success" ) )
		{
			return AjaxUtil.getMap( true , "成功！" );
		}
		else
		{
			return AjaxUtil.getMap( false , message );
		}
	}
	
	
	/**
	 * 
	 * @Description: TODO(ERP二手车业务接口:修改订单信息)
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-25 下午2:46:40
	 */
	@ApiOperation( value = "ERP二手车业务接口:修改订单信息" , notes = "修改订单信息接口" , position = 5 )
	@RequestMapping( value = "/updateOrderDetialsFromErp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > updateOrderDetialsFromErp(

	        @ApiParam( value = "ERP订单ID" ) @RequestParam( value = "orderId" , required = true ) String orderId )
	{

		int iorderId = Integer.parseInt( orderId );
		String message = shipheadService.updateOrderDetialsFromErp( iorderId );
		if ( message.equalsIgnoreCase( "success" ) )
		{
			return AjaxUtil.getMap( true , "成功！" );
		}
		else
		{
			return AjaxUtil.getMap( false , message );
		}
	}
}
