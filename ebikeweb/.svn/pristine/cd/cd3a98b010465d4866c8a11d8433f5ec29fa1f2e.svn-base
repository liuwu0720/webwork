package com.clt.sub.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TArorder;
import com.clt.sub.model.TAssessEnterAccount;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.model.TShiphead;
import com.clt.sub.service.IArorderService;
import com.clt.sub.service.IAssessEnterAccountService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.IOrderService;
import com.clt.sub.service.IShipStatusService;
import com.clt.sub.service.IShipheadService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Description: 结算应收Action
 * @author chenbin
 * @date 2014-7-17 上午11:21:52
 * @version V1.0
 */

@Controller
@RequestMapping( "/arorderAction" )
@Api( value = "arorderAction-api" , description = "有关于收入的API(车队收入、司机收入)" , position = 5 )
public class ArorderAction
{
	
	@Autowired
	IShipheadService shipheadService;
	@Autowired
	ITruckDriverService driverService;
	@Autowired
	IDriverService iDriverService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IArorderService arorderService;
	@Autowired
	IShipStatusService iShipStatusService;
	@Autowired
	ICustomerSerivce iCustomerSerivce;
	@Autowired
	IAssessEnterAccountService iAssessEnterAccountService;
	
	@Autowired
	IUserService iUserService;


	/**
	 * 
	 * @Description: TODO(结算应收)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午4:31:02
	 */
	@RequestMapping( "/intogetAllReturnInfoBySubno" )
	@ApiIgnore
	public String intogetAllReturnInfoBySubno( HttpServletRequest request )
	{
		return "sub/arorder/arReturnInfoList";
	}
	
	/**
	 * @Description: 获取所有结算应收 根据分供方
	 * @param request
	 * @return Map<String,Object> 返回值描述：获取已回单，未结算的列表
	 * @author chenbin
	 * @create_date 2014-8-13 下午4:16:06
	 */
	@RequestMapping( "/getAllReturnInfoBySubno" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllReturnInfoBySubno(
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		String partype = "";
		
		String nowstr = DateUtil.getDate( "yyyy/MM/dd" );
		Page page = ServiceUtil.getcurrPage( request );
		
		String sql = "select head.vc_shipno,line.id as i_shipline,  line.n_qty, "
		        + "line.n_ship_qty,line.n_arkilometer,"
		        + "head.id as i_shiphead,"
		        + "head.i_truck_id, head.vc_truck_name,ord.id as i_order,ord.dt_ship,ord.dt_arrive,ord.vc_start_city,"
		        + "ord.i_start_id,ord.vc_dest_city,ord.i_end_id,ord.vc_orderno,ord.vc_cust_orderno,"
		        + "ord.vc_car_name,ord.N_COST,ord.n_paytype,ord.n_total_price"
		        + " from t_Shipline line, t_Shiphead head, t_order ord where line.i_shiphead = head.id and line.i_order_id = ord.id"
		        + " and line.n_current_status = 20 and line.N_ARORDER = 1 and head.VC_SUBNO = '"
		        + subbo + "' ";
		System.out.println( "sql --->" + sql );
		String shipNo = request.getParameter( "shipNo" );
		String orderNo = request.getParameter( "orderNo" );
		String driverName = request.getParameter( "driverName" );
		if ( StringUtils.isNotBlank( shipNo ) )
		{
			sql += "and head.vc_shipno = '" + shipNo + "'";
		}
		if ( StringUtils.isNotBlank( orderNo ) )
		{
			sql += "and ord.vc_orderno = '" + orderNo + "'";
		}
		if ( StringUtils.isNotBlank( driverName ) )
		{
			sql += "and head.vc_truck_name = '" + driverName + "'";
		}
		sql += "order by head.id desc, ord.vc_cust_orderno,ord.vc_orderno";
		Map< String , Object > orderMap = shipheadService.getSpringSQL( sql ,
		        page );
		if ( orderMap.get( "rows" ) != null )
		{
			
			List relist = ( List ) orderMap.get( "rows" );
			System.out.println( " getAllReturnInfoBySubno  size "
			        + relist.size() );
		}
		else
		{
			System.out.println( " getAllReturnInfoBySubno  size  0 " );
		}
		return orderMap;
	}
	
	/**
	 * 
	 * @Description: TODO(展开的详情列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午2:52:47
	 */
	@RequestMapping( "/getAllReturnInfoBySubnoDetail" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllReturnInfoBySubnoDetail(
	        HttpServletRequest request )
	{
		System.out.println( request.getParameter( "shiplineId" ) );
		Page page = ServiceUtil.getcurrPage( request );
		int shipLineId = Integer
		        .parseInt( request.getParameter( "shiplineId" ) );
		HqlHelper hql = new HqlHelper( TShipStatus.class );
		
		hql.addEqual( "nLineId" , shipLineId );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );

		Map< String , Object > orderMap = iShipStatusService.findByHQL( hql );
		return orderMap;
		
	}
	
	/**
	 * 
	 * @Description: TODO(结算应收审核)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午4:31:27
	 */
	@RequestMapping( "/intogetAllArorderInfoBySubno" )
	@ApiIgnore
	public String intogetAllArorderInfoBySubno( HttpServletRequest request )
	{
		return "sub/arorder/arArorderInfoList";
	}
	
	/**
	 * @Description: 未生成对帐单 可以取消结算审核的列表
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-13 下午4:16:06
	 */
	@RequestMapping( "/getAllArorderInfoBySubno" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllArorderInfoBySubno(
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		String partype = "";
		
		Page page = ServiceUtil.getcurrPage( request );
		// 查询已回单但未点结算应收的数据 左关联车辆单价表
		String sql = "select ord.vc_cust_orderno, ord.n_paytype,ard.id, ard.vc_start_city, ard.vc_dest_city, ard.vc_car_style,"
		        + " ard.n_price, ard.n_arkilometer, ard.n_total_price, ard.dt_audit, ard.n_qty,ard.vc_audit_name ,ard.VC_SHIPNO from t_arorder ard, t_Order ord, t_shipline line"
		        + " where ard.i_order = ord.id  and ard.i_shipline = line.id and line.n_arorder = 0 and ord.vc_subno = '"
		        + subbo
		        + "' and ard.n_audit = 0 and ard.N_ENABLE = 0 and ard.N_CREATE_BILL = 0";
		System.out.println( "sql---->" + sql );
		String orderNo = request.getParameter( "orderNo" );
		String carName = request.getParameter( "carName" );
		if ( StringUtils.isNotBlank( orderNo ) )
		{
			sql += "and  ord.VC_CUST_ORDERNO = '" + orderNo + "'";
		}
		if ( StringUtils.isNotBlank( carName ) )
		{
			sql += "and ard.vc_car_style = '" + carName + "'";
		}
		
		sql += " order by ord.vc_cust_orderno";
		
		System.out.println( ">> sql " + sql );
		Map< String , Object > orderMap = shipheadService.getSpringSQL( sql ,
		        page );
		orderMap.put( "partype" , partype );
		if ( orderMap.get( "rows" ) != null )
		{
			
			List relist = ( List ) orderMap.get( "rows" );
			System.out.println( " getAllArorderInfoBySubno  size "
			        + relist.size() );
		}
		else
		{
			System.out.println( " getAllArorderInfoBySubno  size  0 " );
		}
		
		return orderMap;
	}
	
	/**
	 * 
	 * @Description: TODO(结算应收--应收确定)
	 * @param request
	 * @param resp
	 * @param id
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-14 下午2:00:29
	 */
	@SuppressWarnings( "deprecation" )
	// 保存结算应收
	@RequestMapping( "/saveArorder" )
	@ApiIgnore
	public void saveArorder( HttpServletRequest request ,
	        HttpServletResponse resp , String id )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		int shiplineid = Integer.parseInt( id );
		try
		{
			
			String message = arorderService.saveArorder( user , shiplineid );
			if ( message.equals( "success" ) )
			{
				AjaxUtil.rendJson( resp , true , "同步完成" );
			}
			else
			{
				AjaxUtil.rendJson( resp , false , "同步失败，原因：" + message );
			}

		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( resp , false , "结算失败." + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(同步)
	 * @param request
	 * @param resp
	 * @param id
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-24 下午2:50:57
	 */
	@SuppressWarnings( "deprecation" )
	@RequestMapping( "/synchronous" )
	@ApiIgnore
	public void synchronous( HttpServletRequest request ,
	        HttpServletResponse resp , String id )
	{
		int shiplineid = Integer.parseInt( id );
		try
		{
			String message = arorderService.updateOrderInfo( shiplineid );
			if ( message.equals( "success" ) )
			{
				AjaxUtil.rendJson( resp , true , "结算完成" );
			}
			else
			{
				AjaxUtil.rendJson( resp , false , "结算失败，原因：" + message );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( resp , false , "结算失败." + e.getMessage() );
		}
	}

	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-14 下午2:02:11
	 */
	@SuppressWarnings( "deprecation" )
	// 保存结算应收
	@RequestMapping( "/saveAuditArorder" )
	@ApiIgnore
	public void saveAuditArorder( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		
		// shiplineIDS 字符串 shiphead表id,号 分隔
		String shiplineids = request.getParameter( "shiplineIDS" );
		try
		{
			arorderService.saveAuditArorder( user , shiplineids );
			AjaxUtil.rendJson( resp , true , "保存成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( resp , false , "保存失败." + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(结算应收审核--取消审核)
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午3:43:54
	 */
	@RequestMapping( "/cancelAudit" )
	@ApiIgnore
	public void cancelAudit( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		
		String[] ids = request.getParameterValues( "array[]" );
		
		try
		{
			arorderService.updateAudit( ids );
			AjaxUtil.rendJson( resp , true , "已经取消审核" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( resp , false , "取消审核失败." + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(生成对帐单)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午3:43:47
	 */
	@RequestMapping( "/createBill" )
	@ApiIgnore
	public void createBill( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			arorderService.updateCreateBill( ids , user );
			AjaxUtil.rendJson( response , true , "已生成对帐单" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , true , "对帐单生成失败" + e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(对帐管理)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午4:33:14
	 */
	@RequestMapping( "/checkAuditBySubno" )
	@ApiIgnore
	public String getCheckAuditList( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "sub/arorder/arCheckAuditList";
	}
	
	/**
	 * @Description: 对帐管理
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author chenbin
	 * @create_date 2014-8-13 下午4:16:06
	 */
	@RequestMapping( "/getAllCheckAudit" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllCheckAudit( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		String partype = "";
		
		Page page = ServiceUtil.getcurrPage( request );
		// 查询已回单但未点结算应收的数据 左关联车辆单价表
		String sql = "select ord.vc_cust_orderno,  ord.n_paytype,  ard.id, ard.vc_bill_no, ard.vc_start_city, ard.vc_dest_city,"
		        + " ard.vc_car_style,ard.n_price, ard.n_arkilometer, ard.n_total_price,ard.dt_create_bill,ard.n_qty,"
		        + " us.vc_username,ard.N_BILL from t_arorder ard, t_Order ord, t_shipline line,t_user us where ard.i_order = ord.id  and ard.i_shipline = line.id"
		        + " and ard.i_bill_user = us.id and ard.n_create_bill = 1 and line.n_arorder = 0 and ord.vc_subno = '"
		        + subbo + "' and ard.n_audit = 0 ";
		System.out.println( "sql---->" + sql );
		String orderNo = request.getParameter( "orderNo" );
		String carName = request.getParameter( "carName" );
		if ( StringUtils.isNotBlank( orderNo ) )
		{
			sql += "and  ord.VC_CUST_ORDERNO = '" + orderNo + "'";
		}
		if ( StringUtils.isNotBlank( carName ) )
		{
			sql += "and ard.vc_car_style = '" + carName + "'";
		}
		
		sql += " order by ord.vc_cust_orderno";
		
		System.out.println( ">> sql " + sql );
		Map< String , Object > orderMap = shipheadService.getSpringSQL( sql ,
		        page );
		orderMap.put( "partype" , partype );
		if ( orderMap.get( "rows" ) != null )
		{
			
			List relist = ( List ) orderMap.get( "rows" );
			System.out.println( " getAllArorderInfoBySubno  size "
			        + relist.size() );
		}
		else
		{
			System.out.println( " getAllArorderInfoBySubno  size  0 " );
		}
		
		return orderMap;
	}
	
	/**
	 * 
	 * @Description: TODO(确定对帐单)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午3:43:47
	 */
	@RequestMapping( "/checkBill" )
	@ApiIgnore
	public void checkBill( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			arorderService.updateCheckBill( ids , user );
			AjaxUtil.rendJson( response , true , "对帐单已确认" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , true , "对帐单确认失败" + e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(取消对帐单)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午3:43:47
	 */
	@RequestMapping( "/cancelBill" )
	@ApiIgnore
	public void cancelBill( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			arorderService.updateCancelBill( ids , user );
			AjaxUtil.rendJson( response , true , "取消对帐单成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , true , "取消对帐单失败" + e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(手机端获取车型收入列表)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-20 下午2:37:15
	 */
	@ApiOperation( value = "分供方角色和司机角色：手机端获取车队收入列表接口" , notes = ""
	        + "注意：两种查询方式只能二选一<br/>N_QTY：数量<br/>"
	        + "ID:结算表ID<BR/>VC_START_CITY:起始地城市<br/>VC_DEST_CITY:目的地城市<br/>VC_CAR_STYLE:车型<br/>N_TOTAL_PRICE:金额<br/>"
	        + "N_BILL:是否结算(0:未结算，1已结算)DT_AUDIT:结算日期<br/>VC_SHIPNO:指令号;<br/>ALLTOTAL:统计查询范围内的总价，即金额的总和" )
	@RequestMapping( value = "/getAorderList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAorderList(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "N:未入账；Y:已入账" , required = false ) @RequestParam( value = "type" , required = false ) String type ,
	        @ApiParam( value = "方式一(两种查询方式，二选一)：0:全部(默认)；1:近一个月；2：近2个月；3近3个月；6：近半年；12：近一年" , required = false ) @RequestParam( value = "time" , required = false ) String time ,
	        @ApiParam( value = "方式二(两种查询方式，二选一)：其它时间；格式如 2015-01-25;" , required = false ) @RequestParam( value = "otherTime" , required = false ) String otherTime )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String vcSubno = "";
		String sql1 = "";// 查询字段
		String sql2 = "";// 统计总和
		String sql3 = "";// 条件
		String sql = "";
		int month = 0;
		if ( StringUtils.isNotBlank( time ) )
		{
			month = Integer.parseInt( time );
		}
		
		Page page = ServiceUtil.getcurrPage( request );
		
		if ( user.getIArchiveType().equals( SystemConstants.SYS_TARCHIVE_SUB ) )// 分供方角色
		{
			vcSubno = subService.get( user.getiArchive() ).getVcSubno();// 获取分供方编号
			sql1 = "SELECT ar.id,ar.N_QTY,ar.vc_start_city, ar.vc_dest_city,ar.vc_car_style, ar.n_total_price,ar.n_bill, ar.dt_audit,"
			        + " head.vc_shipno,";
			if ( time == null && otherTime == null )
			{
				time = "0";
			}
			
			if ( StringUtils.isNotBlank( time ) )
			{
				if ( time.equals( "0" ) ) // 全部
				{
					sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable=0 and t.VC_SUBNO='"
					        + vcSubno
					        + "' and t.n_create_bill=1 ) "
					        + " as alltotal FROM T_ARORDER ar, t_Shiphead head where ar.i_shiphead = head.id and ar.n_create_bill=1";
					if ( StringUtils.isNotBlank( type )
					        && type.equalsIgnoreCase( "N" ) )
					{
						sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable=0 and t.VC_SUBNO='"
						        + vcSubno
						        + "' and t.n_create_bill=1 and ar.n_bill=0 ) "
						        + " as alltotal FROM T_ARORDER ar, t_Shiphead head where ar.i_shiphead = head.id and ar.n_create_bill=1";
					}
					else if ( StringUtils.isNotBlank( type )
					        && type.equalsIgnoreCase( "Y" ) )
					{
						sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable=0 and t.VC_SUBNO='"
						        + vcSubno
						        + "' and t.n_bill=1  and t.n_create_bill=1 ) "
						        + " as alltotal FROM T_ARORDER ar, t_Shiphead head where ar.i_shiphead = head.id and ar.n_create_bill=1";
					}
				}
				else
				{
					sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable = 0 and t.VC_SUBNO='"
					        + vcSubno
					        + "' and "
					        + "ar.dt_audit > add_months(sysdate, -"
					        + month
					        + ") and t.n_create_bill=1) as alltotal  FROM T_ARORDER ar, t_Shiphead head "
					        + " where ar.i_shiphead = head.id and ar.dt_audit > add_months(sysdate, -"
					        + month + ") and ar.n_create_bill=1";
					if ( StringUtils.isNotBlank( type )
					        && type.equalsIgnoreCase( "N" ) )
					{
						sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable = 0 and t.VC_SUBNO='"
						        + vcSubno
						        + "' and "
						        + "t.dt_audit > add_months(sysdate, -"
						        + month
						        + ") and t.n_create_bill=1 and t.n_bill=0) as alltotal  FROM T_ARORDER ar, t_Shiphead head "
						        + " where ar.i_shiphead = head.id and ar.dt_audit > add_months(sysdate, -"
						        + month + ") and ar.n_create_bill=1";
					}
					else if ( StringUtils.isNotBlank( type )
					        && type.equalsIgnoreCase( "Y" ) )
					{
						sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable = 0 and t.VC_SUBNO='"
						        + vcSubno
						        + "' and "
						        + "t.dt_audit > add_months(sysdate, -"
						        + month
						        + ") and t.n_create_bill=1 and t.n_bill=1) as alltotal  FROM T_ARORDER ar, t_Shiphead head "
						        + " where ar.i_shiphead = head.id and ar.dt_audit > add_months(sysdate, -"
						        + month + ") and ar.n_create_bill=1";
					}


				}
			}
			if ( StringUtils.isNotBlank( otherTime ) )
			{
				
				sql2 = "(SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable=0 and t.VC_SUBNO='"
				        + vcSubno
				        + "' and t.dt_audit > to_date('"
				        + otherTime
				        + "','yyyy-MM-dd') and t.n_create_bill=1)"
				        + "as alltotal FROM T_ARORDER ar, t_Shiphead head where ar.i_shiphead = head.id "
				        + " and ar.dt_audit > to_date('"
				        + otherTime
				        + "','yyyy-MM-dd')  and t.n_create_bill=1";
				if ( StringUtils.isNotBlank( type )
				        && type.equalsIgnoreCase( "N" ) )
				{
					sql2 = "(SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable=0 and t.VC_SUBNO='"
					        + vcSubno
					        + "' and t.dt_audit > to_date('"
					        + otherTime
					        + "','yyyy-MM-dd') and t.n_bill=0  and t.n_create_bill=1)"
					        + "as alltotal FROM T_ARORDER ar, t_Shiphead head where ar.i_shiphead = head.id "
					        + " and ar.dt_audit > to_date('"
					        + otherTime
					        + "','yyyy-MM-dd')  and t.n_create_bill=1";
				}
				else if ( StringUtils.isNotBlank( type )
				        && type.equalsIgnoreCase( "Y" ) )
				{
					sql2 = "(SELECT sum(t.n_total_price) FROM T_ARORDER t where t.n_enable=0 and t.VC_SUBNO='"
					        + vcSubno
					        + "' and t.dt_audit > to_date('"
					        + otherTime
					        + "','yyyy-MM-dd') and t.n_bill=1  and t.n_create_bill=1)"
					        + "as alltotal FROM T_ARORDER ar, t_Shiphead head where ar.i_shiphead = head.id "
					        + " and ar.dt_audit > to_date('"
					        + otherTime
					        + "','yyyy-MM-dd')  and t.n_create_bill=1";
				}

			}
			if ( StringUtils.isNotBlank( type ) )
			{
				if ( type.equalsIgnoreCase( "N" ) )
				{
					
					sql3 = " and ar.n_bill=0  and ar.n_create_bill=1";
				}
				if ( type.equalsIgnoreCase( "Y" ) )
				{
					sql3 = " and ar.n_bill=1  and ar.n_create_bill=1";
				}
			}
			sql = sql1 + sql2 + sql3 + " and ar.VC_SUBNO = '" + vcSubno
			        + "' order by ar.id desc";

		}
		else if ( user.getIArchiveType().equals(
		        SystemConstants.SYS_TARCHIVE_DRIVER ) )// 司机角色
		{
			int driverId = user.getiArchive();// 司机ID
			sql1 = "SELECT ar.id,ar.N_QTY,ar.vc_start_city, ar.vc_dest_city,ar.vc_car_style, ar.n_total_price,ar.n_bill, ar.dt_audit,"
			        + " ar.vc_shipno,";
			if ( time == null && otherTime == null )
			{
				time = "0";
			}
			
			if ( StringUtils.isNotBlank( time ) )
			{
				if ( time.equals( "0" ) ) // 全部
				{
					sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable=0 and t.I_DRIVER = "
					        + driverId
					        + ") "
					        + " as alltotal FROM T_ARORDER_DRIVER ar where ar.N_ENABLE = 0";
					if ( StringUtils.isNotBlank( type )
					        && type.equalsIgnoreCase( "N" ) )
					{
						sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable=0 and t.I_DRIVER = "
						        + driverId
						        + " and t.n_bill=0 ) "
						        + " as alltotal FROM T_ARORDER_DRIVER ar where ar.N_ENABLE = 0";
					}
					else if ( StringUtils.isNotBlank( type )
					        && type.equalsIgnoreCase( "Y" ) )
					{
						sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable=0 and t.I_DRIVER = "
						        + driverId
						        + " and t.n_bill=1 ) "
						        + " as alltotal FROM T_ARORDER_DRIVER ar where ar.N_ENABLE = 0";
					}
				}
				else
				{
					sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable = 0 and t.I_DRIVER = "
					        + driverId
					        + " and "
					        + "t.dt_audit > add_months(sysdate, -"
					        + month
					        + ")) as alltotal  FROM T_ARORDER_DRIVER ar "
					        + " where  ar.dt_audit > add_months(sysdate, -"
					        + month + ")";
					if ( StringUtils.isNotBlank( type )
					        && type.equalsIgnoreCase( "N" ) )
					{
						sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable = 0 and t.I_DRIVER = "
						        + driverId
						        + " and "
						        + "t.dt_audit > add_months(sysdate, -"
						        + month
						        + ") and  t.n_bill=0) as alltotal  FROM T_ARORDER_DRIVER ar "
						        + " where  ar.dt_audit > add_months(sysdate, -"
						        + month + ")";
					}
					else if ( StringUtils.isNotBlank( type )
					        && type.equalsIgnoreCase( "Y" ) )
					{
						sql2 = " (SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable = 0 and t.I_DRIVER = "
						        + driverId
						        + " and "
						        + "t.dt_audit > add_months(sysdate, -"
						        + month
						        + ") and  t.n_bill=1) as alltotal  FROM T_ARORDER_DRIVER ar "
						        + " where  ar.dt_audit > add_months(sysdate, -"
						        + month + ")";
					}
					
				}
			}
			if ( StringUtils.isNotBlank( otherTime ) )
			{
				
				sql2 = "(SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable=0 and t.I_DRIVER = "
				        + driverId
				        + " and t.dt_audit > to_date('"
				        + otherTime
				        + "','yyyy-MM-dd') )"
				        + "as alltotal FROM T_ARORDER_DRIVER ar where "
				        + "  ar.dt_audit > to_date('"
				        + otherTime
				        + "','yyyy-MM-dd') ";
				if ( StringUtils.isNotBlank( type )
				        && type.equalsIgnoreCase( "N" ) )
				{
					sql2 = "(SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable=0 and t.I_DRIVER = "
					        + driverId
					        + " and t.dt_audit > to_date('"
					        + otherTime
					        + "','yyyy-MM-dd') and  t.n_bill=0)"
					        + "as alltotal FROM T_ARORDER_DRIVER ar where "
					        + "  ar.dt_audit > to_date('"
					        + otherTime
					        + "','yyyy-MM-dd') ";
				}
				else if ( StringUtils.isNotBlank( type )
				        && type.equalsIgnoreCase( "Y" ) )
				{
					sql2 = "(SELECT sum(t.n_total_price) FROM T_ARORDER_DRIVER t where t.n_enable=0 and t.I_DRIVER = "
					        + driverId
					        + " and t.dt_audit > to_date('"
					        + otherTime
					        + "','yyyy-MM-dd') and  t.n_bill=1)"
					        + "as alltotal FROM T_ARORDER_DRIVER ar where "
					        + "  ar.dt_audit > to_date('"
					        + otherTime
					        + "','yyyy-MM-dd') ";
				}


			}
			if ( StringUtils.isNotBlank( type ) )
			{
				if ( type.equalsIgnoreCase( "N" ) )
				{
					sql3 = " and ar.n_bill=0 and ar.N_ENABLE=0";
				}
				if ( type.equalsIgnoreCase( "Y" ) )
				{
					sql3 = " and ar.n_bill=1 and ar.N_ENABLE=0";
				}
			}
			sql = sql1 + sql2 + sql3 + " and ar.I_DRIVER = " + driverId
			        + " order by ar.id desc";
		}
		
		System.out.println( "sql >>> " + sql );

		try
		{
			Map< String , Object > resultMap = arorderService.getSpringSql(
			        sql , page );
			if ( Integer.parseInt( resultMap.get( "total" ).toString() ) > 0 )
			{
				return AjaxUtil.getMapByNotException( true , resultMap );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有获取到数据" );
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
	 * @Description: TODO(获取司机收入结算信息)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-22 下午3:20:36
	 */
	@ApiOperation( value = "手机端获取司机收入列表接口" , notes = ""
	        + "注意：两种查询方式只能二选一<br/>"
	        + "ID:结算表ID<BR/>VC_START_CITY:起始地城市<br/>VC_DEST_CITY:目的地城市<br/>VC_CAR_STYLE:车型<br/>N_TOTAL_PRICE:金额<br/>"
	        + "N_BILL:是否结算(0:未结算，1已结算)DT_AUDIT:结算日期<br/>VC_SHIPNO:指令号;<br/>ALLTOTA:统计查询范围内的总价，即金额的总和" )
	@RequestMapping( value = "/getDriverAorders" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDriverAorders(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "N:未入账；Y:已入账" , required = false ) @RequestParam( value = "type" , required = false ) String type ,
	        @ApiParam( value = "方式一(两种查询方式，二选一)：0:全部(默认)；1:近一个月；2：近2个月；3近3个月；6：近半年；12：近一年" , required = false ) @RequestParam( value = "time" , required = false ) String time ,
	        @ApiParam( value = "方式二(两种查询方式，二选一)：其它时间；格式如 2015-01-25;" , required = false ) @RequestParam( value = "otherTime" , required = false ) String otherTime )
	{
		
		int month = 0;
		if ( StringUtils.isNotBlank( time ) )
		{
			month = Integer.parseInt( time );
		}
		
		Page page = ServiceUtil.getcurrPage( request );
		String sql = "SELECT ar.id,ar.vc_start_city, ar.vc_dest_city,ar.vc_car_style, ar.n_total_price,ar.n_bill, ar.dt_audit,"
		        + " head.vc_shipno,";
		if ( time == null && otherTime == null )
		{
			time = "0";
		}
		
		if ( StringUtils.isNotBlank( time ) )
		{
			if ( time.equals( "0" ) ) // 全部
			{
				sql += " (SELECT nvl(sum(t.n_total_price),0) FROM T_ARORDER_DRIVER t where t.n_enable=0 ) "
				        + " as alltotal FROM T_ARORDER_DRIVER ar, t_Shiphead head where ar.i_shiphead = head.id ";
			}
			else
			{
				sql += " (SELECT nvl(sum(t.n_total_price),0) FROM T_ARORDER_DRIVER t where t.n_enable = 0 and "
				        + "ar.dt_audit > add_months(sysdate, -"
				        + month
				        + ")) as alltotal  FROM T_ARORDER_DRIVER ar, t_Shiphead head "
				        + " where ar.i_shiphead = head.id and ar.dt_audit > add_months(sysdate, -"
				        + month + ")";
				
			}
		}
		if ( StringUtils.isNotBlank( otherTime ) )
		{
			
			sql += "(SELECT nvl(sum(t.n_total_price),0) FROM T_ARORDER_DRIVER t where t.n_enable=0 and ar.dt_audit > to_date('"
			        + otherTime
			        + "','yyyy-MM-dd') )"
			        + "as alltotal FROM T_ARORDER_DRIVER ar, t_Shiphead head where ar.i_shiphead = head.id "
			        + " and ar.dt_audit > to_date('"
			        + otherTime
			        + "','yyyy-MM-dd') ";
			
		}
		if ( StringUtils.isNotBlank( type ) )
		{
			if ( type.equalsIgnoreCase( "N" ) )
			{
				sql += " and ar.n_bill=0 ";
			}
			if ( type.equalsIgnoreCase( "Y" ) )
			{
				sql += " and ar.n_bill=1 ";
			}
		}
		
		try
		{
			Map< String , Object > resultMap = arorderService.getSpringSql(
			        sql , page );
			if ( Integer.parseInt( resultMap.get( "total" ).toString() ) > 0 )
			{
				return AjaxUtil.getMapByNotException( true , resultMap );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有获取到数据" );
			}
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(根据拖车ID查询出车队收入)
	 * @param request
	 * @param truckId
	 * @param dtDate
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-14 下午5:14:39
	 */
	@RequestMapping( value = "/getAorderByTruckId" , method = RequestMethod.POST )
	@ApiOperation( value = "分供方角色：根据拖车ID查询出该车辆的未入账和已入账收入的总和" , notes = "分供方角色：根据拖车ID查询出该车辆收入的未入账和已入账收入的总和<br/>注意该接口是查询某辆车辆的收入"
	        + "TOTALPRICE:收入；N_CREATE_BILL:(0:未入帐，1已入帐)" )
	@ResponseBody
	public Map< String , Object > getAorderByTruckId(
	        HttpServletRequest request ,
	        @ApiParam( value = "拖车ID" , required = true ) @RequestParam( value = "truckId" , required = true ) String truckId ,
	        @ApiParam( value = "日期格式：2014-12,如果为空默认查询当前系统时间" , required = false ) @RequestParam( value = "dtDate" , required = false ) String dtDate ,
	        HttpServletResponse response )
	{
		int iTruck = Integer.parseInt( truckId );
		Page page = ServiceUtil.getcurrPage( request );
		String sql = "SELECT sum(ar.n_total_price) as totalPrice,ar.n_create_bill FROM t_arorder ar "
		        + "left join t_Shiphead head on ar.i_shiphead = head.id where head.i_truck_id = "
		        + iTruck;
		if ( StringUtils.isNotBlank( dtDate ) )
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date('"
			        + dtDate + "','yyyy-MM') ";
		}
		else
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date(to_char(sysdate,'yyyy-MM'),'yyyy-MM') ";
		}
		sql += " group by ar.n_create_bill ";
		System.out.println( "sql = " + sql );
		try
		{
			Map< String , Object > resultMap = arorderService.getSpringSql(
			        sql , page );
			if ( Integer.parseInt( resultMap.get( "total" ).toString() ) > 0 )
			{
				return AjaxUtil.getMapByNotException( true , resultMap );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有获取到数据" );
			}
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(查询该分供方所有未入账和已入账收入的总和)
	 * @param request
	 * @param dtDate
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-15 下午2:34:43
	 */
	@RequestMapping( value = "/getAorderByTrucks" , method = RequestMethod.POST )
	@ApiOperation( value = "分供方角色：查询该分供方所有未入账和已入账收入的总和" , notes = "分供方角色：查询该分供方所有未入账和已入账收入的总和<br/>"
	        + "TOTALPRICE:收入；N_CREATE_BILL:(0:未入帐，1已入帐)" )
	@ResponseBody
	public Map< String , Object > getAorderByTrucks(
	        HttpServletRequest request ,
	        @ApiParam( value = "日期格式：2014-12,如果为空默认查询当前系统时间" , required = false ) @RequestParam( value = "dtDate" , required = false ) String dtDate ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		String sql = "SELECT sum(ar.n_total_price) as totalPrice,ar.n_create_bill FROM t_arorder ar "
		        + "left join t_Shiphead head on ar.i_shiphead = head.id where head.VC_SUBNO = '"
		        + subbo + "'";
		if ( StringUtils.isNotBlank( dtDate ) )
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date('"
			        + dtDate + "','yyyy-MM') ";
		}
		else
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date(to_char(sysdate,'yyyy-MM'),'yyyy-MM') ";
		}
		sql += " group by ar.n_create_bill ";
		System.out.println( "sql = " + sql );
		try
		{
			Map< String , Object > resultMap = arorderService.getSpringSql(
			        sql , page );

			if ( Integer.parseInt( resultMap.get( "total" ).toString() ) > 0 )
			{
				return AjaxUtil.getMapByNotException( true , resultMap );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有获取到数据" );
			}

		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(根据拖车ID查询该车辆的对帐、未入账、已入账列表信息)
	 * @param request
	 * @param truckId
	 * @param dtDate
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-15 下午3:07:35
	 */
	@RequestMapping( value = "/getAroderListByTruckId" , method = RequestMethod.POST )
	@ApiOperation( value = "分供方角色：根据拖车ID查询该车辆的:D:对帐、NR:未入账、YR:已入账列表信息" , notes = "分供方角色：根据拖车ID查询该车辆的D:对帐、NR:未入账、YR:已入账列表信息<br/>"
	        + "ARID:结算表ID；<br/>VC_START_CITY:起始地城市；<br/>VC_DEST_CITY:目的地城市；<br/>"
	        + "VC_CUSTNO:客户运单号；<br/>VC_CAR_STYLE:车型；<br/>N_QTY:数量；N_TOTAL_PRICE:总价" )
	@ResponseBody
	public Map< String , Object > getAroderListByTruckId(
	        HttpServletRequest request ,
	        @ApiParam( value = "拖车ID:truckId" , required = true ) @RequestParam( value = "truckId" , required = true ) String truckId ,
	        @ApiParam( value = "D:对帐、NR:未入账、YR:已入账列表信息" , required = false ) @RequestParam( value = "vcType" , required = false ) String vcType ,
	        @ApiParam( value = "日期格式：2014-12,如果为空默认查询当前系统时间" , required = false ) @RequestParam( value = "dtDate" , required = false ) String dtDate ,
	        HttpServletResponse response )
	{
		int iTruckId = Integer.parseInt( truckId );
		Page page = ServiceUtil.getcurrPage( request );
		String sql = "SELECT ar.id as arid,ar.vc_start_city, ar.vc_dest_city,ar.vc_custno,"
		        + " ar.vc_car_style,ar.n_qty,ar.n_total_price,"
		        + " head.vc_shipno FROM t_Arorder ar left join t_Shiphead head on ar.i_shiphead = head.id where head.i_truck_id = "
		        + iTruckId;
		if ( StringUtils.isNotBlank( dtDate ) )
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date('"
			        + dtDate + "','yyyy-MM') ";
		}
		else
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date(to_char(sysdate,'yyyy-MM'),'yyyy-MM') ";
		}
		if ( vcType.equalsIgnoreCase( "D" ) )// 对账：即已审核通过的
		{
			sql += " and ar.N_AUDIT = 0   ";
		}
		if ( vcType.equalsIgnoreCase( "NR" ) )// 未入账:已审核，已对帐
		{
			sql += " and ar.N_AUDIT = 0 and ar.N_BILL = 1";
		}
		if ( vcType.equalsIgnoreCase( "YR" ) )// 已入账
		{
			sql += " and ar.N_CREATE_BILL = 1";
		}
		sql += " order by ar.id desc ";
		System.out.println( "sql = " + sql );
		try
		{
			Map< String , Object > resultMap = arorderService.getSpringSql(
			        sql , page );
			if ( Integer.parseInt( resultMap.get( "total" ).toString() ) > 0 )
			{
				return AjaxUtil.getMapByNotException( true , resultMap );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有获取到数据" );
			}
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(查询当前分供方的:D:对帐、NR:未入账、YR:已入账列表信息)
	 * @param request
	 * @param truckId
	 * @param vcType
	 * @param dtDate
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-21 下午2:02:09
	 */
	@RequestMapping( value = "/getAroderListBySubNo" , method = RequestMethod.POST )
	@ApiOperation( value = "分供方角色：查询当前分供方的:D:对帐、NR:未入账、YR:已入账列表信息" , notes = "分供方角色：查询当前分供方的D:对帐、NR:未入账、YR:已入账列表信息<br/>"
	        + "ARID:结算表ID；<br/>VC_START_CITY:起始地城市；<br/>VC_DEST_CITY:目的地城市；<br/>"
	        + "VC_CUSTNO:客户运单号；<br/>VC_CAR_STYLE:车型；<br/>N_QTY:数量；N_TOTAL_PRICE:总价" )
	@ResponseBody
	public Map< String , Object > getAroderListBySubNo(
	        HttpServletRequest request ,
	        @ApiParam( value = "拖车ID:truckId" , required = true ) @RequestParam( value = "truckId" , required = true ) String truckId ,
	        @ApiParam( value = "D:对帐、NR:未入账、YR:已入账列表信息" , required = false ) @RequestParam( value = "vcType" , required = false ) String vcType ,
	        @ApiParam( value = "日期格式：2014-12,如果为空默认查询当前系统时间" , required = false ) @RequestParam( value = "dtDate" , required = false ) String dtDate ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		String sql = "SELECT ar.id as arid,ar.vc_start_city, ar.vc_dest_city,ar.vc_custno,"
		        + " ar.vc_car_style,ar.n_qty,ar.n_total_price,"
		        + " head.vc_shipno FROM t_Arorder ar left join t_Shiphead head on ar.i_shiphead = head.id where head.VC_SUBNO = '"
		        + subbo + "'";
		if ( StringUtils.isNotBlank( dtDate ) )
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date('"
			        + dtDate + "','yyyy-MM') ";
		}
		else
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date(to_char(sysdate,'yyyy-MM'),'yyyy-MM') ";
		}
		if ( vcType.equalsIgnoreCase( "D" ) )// 对账：即已审核通过的、未对账、未入账
		{
			sql += " and ar.N_AUDIT = 0 and ar.N_BILL = 0 and ar.N_CREATE_BILL = 0 ";
		}
		if ( vcType.equalsIgnoreCase( "NR" ) )// 未入账:已审核，已对帐、未入账
		{
			sql += " and ar.N_AUDIT = 0 and ar.N_BILL = 1 and ar.N_CREATE_BILL = 0";
		}
		if ( vcType.equalsIgnoreCase( "YR" ) )// 已入账
		{
			sql += " and ar.N_CREATE_BILL = 1";
		}
		sql += " order by ar.id desc ";
		System.out.println( "sql = " + sql );
		try
		{
			Map< String , Object > resultMap = arorderService.getSpringSql(
			        sql , page );
			if ( Integer.parseInt( resultMap.get( "total" ).toString() ) > 0 )
			{
				return AjaxUtil.getMapByNotException( true , resultMap );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有获取到数据" );
			}
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}

	/**
	 * 
	 * @Description: TODO(根据拖车ID查询该车辆的对帐、未入账、已入账列表信息)
	 * @param request
	 * @param arId
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-15 下午4:29:53
	 */
	@SuppressWarnings( "unused" )
	@RequestMapping( value = "/getArodderDetailById" , method = RequestMethod.POST )
	@ApiOperation( value = "分供方角色：手机端承运角色根据结算表ID获取结算信息详情()" , notes = "分供方角色：手机端承运角色根据结算表ID获取结算信息详情<br/>"
	        + "承运角色1对帐列表中编辑时获取结算信息详情"
	        + "String vcShipNo;// 调度指令号 不与数据库关联<br/>"
	        + "String vcCustomerNo;// 客户运单号<br/>"
	        + "String vcStartCity;//起运地城市<br/>"
	        + "String vcDestCity;//目的地城市<br/>"
	        + " String vcCarStyle;车型<br/>Integer NQty;数量 " )
	@ResponseBody
	public Map< String , Object > getArodderDetailById(
	        HttpServletRequest request ,
	        @ApiParam( value = "结算应收表ID：arId" , required = true ) @RequestParam( value = "arId" , required = true ) String arId ,
	        HttpServletResponse response )
	{
		int iAroderId = Integer.parseInt( arId );
		try
		{
			TArorder tArorder = arorderService.get( iAroderId );
			TShiphead tShiphead = shipheadService.get( tArorder.getIShiphead() );

			if ( null != tArorder )
			{
				tArorder.setVcShipNo( tShiphead.getVcShipno() );
				return AjaxUtil.getMapByNotException( true , tArorder );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , tArorder );
			}
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}

	}
	
	/**
	 * 
	 * @Description: TODO(编辑保存修改后的结算信息)
	 * @param response
	 * @param tArorder
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-15 下午5:04:20
	 */
	@RequestMapping( value = "/saveArorderByApp" , method = RequestMethod.POST )
	@ApiOperation( value = "分供方角色：手机端承运角色编辑保存修改后的结算信息" , notes = "分供方角色：手机端承运角色编辑保存修改后的结算信息" )
	public void saveArorderByApp(
	        HttpServletResponse response ,
	        @ApiParam( value = "结算信息(所有信息封装成form对象)" , required = true ) @ModelAttribute TArorder tArorder ,
	        HttpServletRequest request )
	{
		try
		{
			arorderService.saveOrUpdate( tArorder );
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(确认对账，确认入账的接口)
	 * @param request
	 * @param vcType
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-16 下午2:14:43
	 */
	@ApiOperation( value = "分供方角色：确认对账，确认入账的接口" , notes = "分供方角色：确认对账，确认入账的接口" )
	@RequestMapping( value = "/updateArOrderState" , method = RequestMethod.POST )
	public void updateArOrderState(
	        HttpServletRequest request ,
	        @ApiParam( value = "结算表ID" , required = true ) @RequestParam( value = "arId" , required = true ) String arId ,
	        @ApiParam( value = "状态：D:确认对账；R:确认入账" , required = true ) @RequestParam( value = "vcState" , required = true ) String vcState ,
	        HttpServletResponse response )
	{
		int iArId = Integer.parseInt( arId );
		try
		{
			TArorder tArorder = arorderService.get( iArId );
			if ( vcState.equalsIgnoreCase( "D" ) )// 对账
			{
				tArorder.setNBill( 1 );
			}
			else if ( vcState.equalsIgnoreCase( "R" ) )// 入账
			{
				tArorder.setNCreateBill( 1 );
			}
			arorderService.saveOrUpdate( tArorder );
			AjaxUtil.rendJson( response , true , "成功!" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(分供方对客户的评价)
	 * @param request
	 * @param tAssessEnterAccount
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-21 下午2:44:55
	 */
	@ApiOperation( value = "分供方角色：分供方对客户的评价" , notes = "分供方角色：分供方对客户的评价" )
	@RequestMapping( value = "/saveAccessBySubno" , method = RequestMethod.POST )
	public void saveAccessBySubno(
	        HttpServletRequest request ,
	        @ApiParam( value = "结算表ID" , required = true ) @RequestParam( value = "strArId" ) String strArId ,
	        @ApiParam( value = "评价详情封装类" , required = true ) @ModelAttribute TAssessEnterAccount tAssessEnterAccount ,
	        HttpServletResponse response )
	{
		int arId = Integer.parseInt( strArId );
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		try
		{
			/**
			 * 通过结算表ID，找到该订单所关联的客户
			 */
			TArorder tArorder = arorderService.get( arId );
			TOrder tOrder = orderService.get( tArorder.getIOrder() );
			TCustomer tCustomer = iCustomerSerivce
			        .get( tOrder.getICustomerId() );
			tAssessEnterAccount.setiUserAcess( user.getId() );// 评价用户id
			tAssessEnterAccount.setiUserBy( tCustomer.getId() );// 被评价用户id
			tAssessEnterAccount.setiUserId( user.getId() );// 添加人userId
			tAssessEnterAccount.setnBussinessType( 1 );// (1承运方入账评价，2：司机入账评价，3：中标者对发布者评价)
			tAssessEnterAccount.setVcBussiness( tOrder.getVcOrderno() );// 业务编号（订单编号，抢单编号）
			tAssessEnterAccount.setVcUserName( user.getVcAccount() );// 添加人姓名
			iAssessEnterAccountService.saveUpdate( tAssessEnterAccount );
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(当前司机用户获取:D:对帐、NR:未入账、YR:已入账列表信息)
	 * @param request
	 * @param vcType
	 * @param dtDate
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-22 下午5:16:41
	 */
	@RequestMapping( value = "/getAroderListByDriver" , method = RequestMethod.POST )
	@ApiOperation( value = "司机角色：当前司机用户获取:D:对帐、NR:未入账、YR:已入账列表信息（司机帐号：15911114444）" , notes = "司机角色：当前司机用户获取D:对帐、NR:未入账、YR:已入账列表信息<br/>"
	        + "ARID:结算表ID；<br/>VC_START_CITY:起始地城市；<br/>VC_DEST_CITY:目的地城市；<br/>"
	        + "VC_CUSTNO:客户运单号；<br/>VC_CAR_STYLE:车型；<br/>N_QTY:数量；N_TOTAL_PRICE:总价" )
	@ResponseBody
	public Map< String , Object > getAroderListByDriver(
	        HttpServletRequest request ,
	        @ApiParam( value = "D:对帐、NR:未入账、YR:已入账列表信息" , required = true ) @RequestParam( value = "vcType" , required = true ) String vcType ,
	        @ApiParam( value = "日期格式：2014-12,如果为空默认查询当前系统时间" , required = false ) @RequestParam( value = "dtDate" , required = false ) String dtDate ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		/**
		 * 通过当前用户ID查出对应的司机ID，当前用户应为司机角色
		 */
		TDriver tDriver = iDriverService.get( user.getiArchive() );
		
		Page page = ServiceUtil.getcurrPage( request );
		String sql = "select t.id as arDriverId ,t.vc_shipno,t.vc_start_city,t.vc_dest_city,"
		        + "t.vc_car_style,t.n_qty,t.n_total_price,ord.vc_cust_orderno from T_ARORDER_DRIVER t "
		        + "left join t_Order ord on t.i_orderid = ord.id  where t.I_DRIVER = "
		        + tDriver.getId();

		if ( StringUtils.isNotBlank( dtDate ) )
		{
			sql += " and to_date(to_char(t.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date('"
			        + dtDate + "','yyyy-MM') ";
		}
		else
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date(to_char(sysdate,'yyyy-MM'),'yyyy-MM') ";
		}
		if ( vcType.equalsIgnoreCase( "D" ) )// 对账：即已审核通过的、未对账、未入账
		{
			sql += " and ar.N_AUDIT = 0 and ar.N_BILL = 0 and ar.N_CREATE_BILL = 0 ";
		}
		if ( vcType.equalsIgnoreCase( "NR" ) )// 未入账:已审核，已对帐、未入账
		{
			sql += " and ar.N_AUDIT = 0 and ar.N_BILL = 1 and ar.N_CREATE_BILL = 0";
		}
		if ( vcType.equalsIgnoreCase( "YR" ) )// 已入账
		{
			sql += " and ar.N_CREATE_BILL = 1";
		}
		sql += " order by ar.id desc ";
		System.out.println( "sql = " + sql );
		try
		{
			Map< String , Object > resultMap = arorderService.getSpringSql(
			        sql , page );
			if ( Integer.parseInt( resultMap.get( "total" ).toString() ) > 0 )
			{
				return AjaxUtil.getMapByNotException( true , resultMap );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有获取到数据" );
			}
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(查询该司机所有未入账和已入账收入的总和)
	 * @param request
	 * @param dtDate
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-22 下午5:24:45
	 */
	@RequestMapping( value = "/getAorderByDriver" , method = RequestMethod.POST )
	@ApiOperation( value = "司机角色：查询该司机所有未入账和已入账收入的总和（司机帐号：15911114444）" , notes = "司机角色：查询该司机所有未入账和已入账收入的总和<br/>"
	        + "TOTALPRICE:收入；N_CREATE_BILL:(0:未入帐，1已入帐)" )
	@ResponseBody
	public Map< String , Object > getAorderByDriver(
	        HttpServletRequest request ,
	        @ApiParam( value = "日期格式：2014-12,如果为空默认查询当前系统时间" , required = false ) @RequestParam( value = "dtDate" , required = false ) String dtDate ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		/**
		 * 通过当前用户ID查出对应的司机ID，当前用户应为司机角色
		 */
		TDriver tDriver = iDriverService.get( user.getiArchive() );

		Page page = ServiceUtil.getcurrPage( request );
		String sql = "SELECT sum(ar.n_total_price) as totalPrice,ar.n_create_bill FROM T_ARORDER_DRIVER ar "
		        + "left join t_Shiphead head on ar.i_shiphead = head.id where ar.I_DRIVER = "
		        + tDriver.getId();
		if ( StringUtils.isNotBlank( dtDate ) )
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date('"
			        + dtDate + "','yyyy-MM') ";
		}
		else
		{
			sql += " and to_date(to_char(ar.dt_audit,'yyyy-mm'),'yyyy-MM') = to_date(to_char(sysdate,'yyyy-MM'),'yyyy-MM') ";
		}
		sql += " group by ar.n_create_bill ";
		System.out.println( "sql = " + sql );
		try
		{
			Map< String , Object > resultMap = arorderService.getSpringSql(
			        sql , page );
			if ( Integer.parseInt( resultMap.get( "total" ).toString() ) > 0 )
			{
				return AjaxUtil.getMapByNotException( true , resultMap );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有获取到数据" );
			}
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(司机角色：司机对分供方的评价)
	 * @param request
	 * @param strArId
	 * @param tAssessEnterAccount
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-23 下午2:30:11
	 */
	@ApiOperation( value = "司机角色：司机对分供方的评价" , notes = "司机角色：司机对分供方的评价" )
	@RequestMapping( value = "/saveAccessByDriver" , method = RequestMethod.POST )
	public void saveAccessByDriver(
	        HttpServletRequest request ,
	        @ApiParam( value = "结算表ID" , required = true ) @RequestParam( value = "strArId" ) String strArId ,
	        @ApiParam( value = "评价详情封装类" , required = true ) @ModelAttribute TAssessEnterAccount tAssessEnterAccount ,
	        HttpServletResponse response )
	{
		int arId = Integer.parseInt( strArId );
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		/**
		 * 通过当前用户ID查出对应的司机ID，当前用户应为司机角色
		 */
		TDriver tDriver = iDriverService.get( user.getiArchive() );
		/**
		 * 找出该 司机的分供方的用户ID
		 */
		TUser bossUser = iUserService.getByid( tDriver.getiUserId() + "" );
		try
		{
			TArorder tArorder = arorderService.get( arId );
			TOrder tOrder = orderService.get( tArorder.getIOrder() );
			tAssessEnterAccount.setiUserAcess( user.getId() );// 评价用户id
			tAssessEnterAccount.setiUserBy( bossUser.getId() );// 被评价用户id
			tAssessEnterAccount.setiUserId( user.getId() );// 添加人userId
			tAssessEnterAccount.setnBussinessType( 2 );// (1承运方入账评价，2：司机入账评价，3：中标者对发布者评价)
			tAssessEnterAccount.setVcBussiness( tOrder.getVcOrderno() );// 业务编号（订单编号，抢单编号）
			tAssessEnterAccount.setVcUserName( user.getVcAccount() );// 添加人姓名
			iAssessEnterAccountService.saveUpdate( tAssessEnterAccount );
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！原因：" + e.getMessage() );
		}
		
	}
}
