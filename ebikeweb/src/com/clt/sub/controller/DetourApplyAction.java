package com.clt.sub.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.clt.common.Geohash;
import com.clt.common.UserSession;
import com.clt.sub.model.TDetour;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.service.IDetourApplyService;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.IShipheadService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverLinkService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.sub.service.ITruckMapService;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserGps;
import com.clt.systemmanger.service.IMsgRecordService;
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
 * @Description: TODO(绕路申请)
 * @author liuwu
 * @date 2015-1-6 下午3:00:46
 * @version V1.0
 */
@Controller
@RequestMapping( "/detourApplyAction" )
@Api( value = "detourApply-api" , description = "有关绕路申请审批的API" , position = 5 )
public class DetourApplyAction
{
	@Autowired
	IDriverService iDriverService;
	@Autowired
	ITruckDriverService iTruckDriverService;
	@Autowired
	ITruckDriverLinkService iTruckDriverLinkService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	private IDriverService driverService;
	@Autowired
	IDetourApplyService iDetourApplyService;
	@Autowired
	IUserService iUserService;
	@Autowired
	IMsgRecordService msgService;
	@Autowired
	private Geohash geohash;
	@Autowired
	IShipheadService iShipheadService;
	@Autowired
	private ITruckMapService mapService;
	@Autowired
	private JdbcTemplate jdbcDao;
	@Autowired
	private IUserGpsService gpsService;
	
	@RequestMapping( "/getDetourList" )
	@ApiIgnore
	public String getDetourList()
	{
		return "sub/flowApprove/deTourList";
	}
	
	/**
	 * 
	 * @Description: TODO(绕路申请列表)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-6 下午4:11:24
	 */
	@ApiOperation( value = "绕路申请审批列表，返回json列表" , notes = "绕路申请审批列表，返回json列表"
	        + "private Integer nEnable;// 是否有效（0有效，1无效）<br/>"
	        + "private Integer IUser;// 申请人id<br/>"
	        + "private String vcUserName;// 申请人姓名<br/>"
	        + "private Date dtApplay;// 罚款金额<br/>"
	        + "private String vcOldRoute;// 原路线<br/>"
	        + "private String vcNewRoute;// 新路线<br/>"
	        + "private String vcDetourCase;// 绕路原因<br/>"
	        + "private String vcApplaySite;// 申请地点名字<br/>"
	        + "private String vcLongitude;// 申请经度<br/>"
	        + "private String vcLatitude;// 申请纬度<br/>"
	        + "private String vcShipno;// 调度指令号<br/>"
	        + "private String vcCarNo;// 车牌号<br/>"
	        + "private String vcSubno;// 分供方编号<br/>"
	        + "private Integer IApprove;// 审批人id<br/>"
	        + "private String vcApproveName;// 审批人姓名<br/>"
	        + "private Date dtApprove;// 审批时间<br/>"
	        + "private Integer nApproveResult;// 审批结果(0,通过，1，未审批,2:未通过)<br/>"
	        + "private String vcNote;// 审批备注<br/>"
	        + "private String vcDetourLength;// 绕路长度（审批人填写）" , position = 5 )
	@RequestMapping( value = "/detourList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > detourList(
	        HttpServletRequest request ,
	        @ApiParam( value = "申请人" , required = false ) @RequestParam( value = "vcUserName" , required = false ) String vcUserName ,
	        @ApiParam( value = "调度指令号" , required = false ) @RequestParam( value = "vcShipno" , required = false ) String vcShipno ,
	        @ApiParam( value = "车牌号" , required = false ) @RequestParam( value = "vcCarNo" , required = false ) String vcCarNo ,
	        @ApiParam( value = "审批状态(0:已通过审批，1:未审批(默认)，2：未通过审批)" , required = true ) @RequestParam( value = "nApproveResult" , required = false ) String nApproveResult ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		int subId = user.getiArchive();
		String vcSubno = "";
		int typeId = user.getIArchiveType();
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )
		{
			vcSubno = driverService.get( subId ).getVcSubno();
		}
		else
		{
			vcSubno = subService.get( subId ).getVcSubno();// 获取分供方编号
		}
		Map< String , Object > resuMap = new HashMap< String , Object >();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TDetour.class );
		hql.setQueryPage( page );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqual( "vcSubno" , vcSubno );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			// hql.addEqual( "nApproveResult" , 1 );
			// String vcShipno = request.getParameter( "vcShipno" );
			// String vcCarNo = request.getParameter( "vcCarNo" );
			if ( StringUtils.isNotBlank( vcShipno ) )
			{
				hql.addEqual( "vcShipno" , vcShipno );
			}
			if ( StringUtils.isNotBlank( vcCarNo ) )
			{
				hql.addEqual( "vcCarNo" , vcCarNo );
			}
			if ( StringUtils.isNotBlank( nApproveResult ) )
			{
				hql.addEqual( "nApproveResult" , Integer.parseInt( nApproveResult ) );
			}
			if ( StringUtils.isNotBlank( vcUserName ) )
			{
				hql.addEqual( "vcUserName" , vcUserName );
			}
			if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，只查询当前司机的
			{
				TDriver tDriver = iDriverService.get( user.getiArchive() );
				hql.addEqual( "IUser" , tDriver.getId() );
			}
			hql.addOrderBy( "id" , "desc" );
			resuMap = iDetourApplyService.findAllByHqlHelp( hql );
			return AjaxUtil.getMapByResult( visit , resuMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(审核通过)
	 * @param request
	 * @param response
	 * @param tDetour
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-7 下午1:47:45
	 */
	@ApiOperation( value = "绕路申请审批接口，同意或者拒绝审批" , notes = "绕路申请，同意或者拒绝审批" , position = 5 )
	@RequestMapping( value = "/sure" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > sure(
	        HttpServletRequest request ,
	        @ApiParam( value = "罚款审批列表ID(多个以数组形式传入，单个就传非0的整数数字)" , required = true ) @RequestParam( "id" ) String array ,
	        @ApiParam( value = "审批结果(0,通过，2:不通过)" , required = true ) @RequestParam( "vcResult" ) String vcResult ,
	        @ApiParam( value = "绕路长度" , required = false ) @RequestParam( value = "vcLength" , required = false ) String vcLength ,
	        @ApiParam( value = "审批意见(String类型,进行长度限制(30字内))" , required = false ) @RequestParam( value = "vcNote" , required = false ) String vcNote )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
			String message = iDetourApplyService.updateSureList( user , subbo , array ,
			        vcResult , vcLength , vcNote );
			if ( message.equalsIgnoreCase( "success" ) )
			{
				return AjaxUtil.getMap( true , "审核成功!" );
			}
			else
			{
				return AjaxUtil.getMap( false , "审核失败!原因：" + message );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	@ApiOperation( value = "绕路申请接口(新增、修改申请)" , notes = "保存绕路申请信息,用form提交封装为TDetour对象" , position = 5 , response = TDetour.class )
	@RequestMapping( value = "/save" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > save(
	        HttpServletRequest request ,
	        @ApiParam( value = "add为新增、update为修改(注:修改的话id不能为空)" , required = true ) @RequestParam( value = "paramType" , required = true ) String paramType ,
	        @ApiParam( value = "绕路信息" ) @ModelAttribute TDetour tDetour )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int typeId = user.getIArchiveType();
		TDriver tDriver = null;
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，获取到他的车牌号
		{
			try
			{
				int driverId = user.getiArchive();// 司机ID
				tDriver = iDriverService.get( driverId );
				tDetour.setVcUserPhone( tDriver.getVcDriverTel() );
				String[] propertyNames2 = { "NEnable" , "IDriverID" };
				Object[] values2 = { SystemConstants.SYS_ENABLE , driverId };
				List< TTruckDriverLlink > tLlinks = iTruckDriverLinkService
				        .findByPropertys( propertyNames2 , values2 );
				if ( tLlinks.size() > 0 )
				{
					TTruckDriverLlink tlink = tLlinks.get( 0 );
					int truckId = tlink.getITruckID();
					TTruckDriver truckDriver = iTruckDriverService.get( truckId );
					tDetour.setVcCarNo( truckDriver.getVcCarNo() );
					tDetour.setVcUserName( tDriver.getVcDriverName() );
					tDetour.setVcSubno( tDriver.getVcSubno() );
					tDetour.setIUser( tDriver.getId() );
					if ( StringUtils.isBlank( tDriver.getVcDriverTel() ) )
					{
						tDetour.setVcUserPhone( tDriver.getVcDriverTel() );
					}
					tDetour.setDtApplay( new Date() );
					tDetour.setnApproveResult( 1 );
					if ( tDetour.getVcLatitude() != null
					        && tDetour.getVcLongitude() != null )
					{
						
						String hash = geohash.encode(
						        Double.parseDouble( tDetour.getVcLatitude() ) ,
						        Double.parseDouble( tDetour.getVcLongitude() ) );
						tDetour.setVcHash( hash );// 申请地点的hash值
					}
					if ( tDetour.getVcNoPlaceLatitude() != null
					        && tDetour.getVcNoPlaceLongtide() != null )
					{
						String hash2 = geohash.encode( Double
						        .parseDouble( tDetour.getVcNoPlaceLatitude() ) ,
						        Double.parseDouble( tDetour
						                .getVcNoPlaceLongtide() ) );
						tDetour.setVcNoplaceHash( hash2 );// 不可经地点的hash值
					}
					/**
					 * 验证调度指令 获取该司机拖车所关联的最新指令号
					 */
					String[] properties = { "ITruckId" , "NEnable" };
					Object[] values = { truckId , SystemConstants.SYS_ENABLE };
					String orderByParam = "id";// 需要排序的字段
					List< TShiphead > tShipheads = iShipheadService
					        .findByPropertysOrderBy( properties , values ,
					                orderByParam );
					if ( tShipheads != null && tShipheads.size() > 0 )
					{
						tDetour.setVcShipno( tShipheads.get( 0 )
						        .getVcShipno() );
					}
					else
					{
						return AjaxUtil.getMap( false , "查询不到你现在的指令号，你不能申请绕路！" );
					}
				}
				else
				{
					return AjaxUtil.getMap( false , "查询不到你所关联的运输车，你不能申请绕路！" );
				}

			}
			catch ( Exception e )
			{
				e.printStackTrace();
				return AjaxUtil.getMapByException( e );
			}
			
		}
		if ( paramType.equalsIgnoreCase( "add" ) )
		{
			try
			{
				iDetourApplyService.save( tDetour );
				
				// 消息推送
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "72" );// 72=绕路
				map.put( "id" , tDetour.getId() + "" );
				TUser bossUser = iUserService.getByid( tDriver.getiUserId() + "" );
				List< TUser > tUsers = new ArrayList< TUser >();
				tUsers.add( bossUser );
				PushUtils pushUtils = new PushUtils( "有绕路申请，请点击查看" , "来自"
				        + tDriver.getVcDriverName() + "的绕路申请！" , tUsers ,
				        "com.unlcn.carrier.approvalprocess.DetourDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( bossUser.getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( tDetour.getVcDetourCase() );
				tMsgRecord.setVcTitle( "有绕路申请，请点击查看" );
				msgService.save( tMsgRecord );
				return AjaxUtil.getMap( true , "新增保存成功" );
			}
			catch ( Exception e )
			{
				return AjaxUtil.getMapByException( e );
			}
		}
		if ( paramType.equalsIgnoreCase( "update" ) && tDetour.getId() != null )
		{
			try
			{
				
				iDetourApplyService.update( tDetour );
				return AjaxUtil.getMap( true , "编辑保存成功" );
			}
			catch ( Exception e )
			{
				return AjaxUtil.getMapByException( e );
			}
		}
		return AjaxUtil.getMap( false , "未知操作类型，系统未处理！" );
	}
	
	/**
	 * 
	 * @Description: TODO(绕路申请申请)
	 * @param request
	 * @param response
	 * @param id
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:21:32
	 */
	@ApiOperation( value = "绕路申请撤销" , notes = "绕路申请撤销" , position = 5 )
	@RequestMapping( value = "/delete" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > delete(
	        HttpServletRequest request ,
	        @ApiParam( value = "需要撤销申请的列表id" , required = true ) @RequestParam( value = "id" , required = true ) String id )
	{
		if ( StringUtils.isNotBlank( id ) )
		{
			TDetour tDetour = iDetourApplyService.findDetourById( Integer.parseInt( id ) );
			tDetour.setnEnable( SystemConstants.SYS_DISABLE );
			try
			{
				iDetourApplyService.update( tDetour );
				return AjaxUtil.getMap( true , "撤销申请成功" );
			}
			catch ( Exception e )
			{
				
				e.printStackTrace();
				return AjaxUtil.getMapByException( e );
			}
		}
		return AjaxUtil.getMap( false , "未传需撤销申请的id" );
	}
	
	/**
	 * 
	 * @Description: TODO(根据id获取详情)
	 * @param request
	 * @param response
	 * @param id
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-7 下午1:31:59
	 */
	@ApiOperation( value = "根据id获取详情" , notes = "private Integer id;// id<br/>"
	        + "private Integer nEnable;// 是否有效（0有效，1无效）<br/>"
	        + "private Integer IUser;// 申请人id<br/>"
	        + "private String vcUserName;// 申请人姓名<br/>"
	        + "private Date dtApplay;// 罚款金额<br/>"
	        + "private String vcOldRoute;// 原路线<br/>"
	        + "private String vcNewRoute;// 新路线<br/>"
	        + "private String vcDetourCase;// 绕路原因<br/>"
	        + "private String vcApplaySite;// 申请地点名字<br/>"
	        + "private String vcLongitude;// 申请经度<br/>"
	        + "private String vcLatitude;// 申请纬度<br/>"
	        + "private String vcShipno;// 调度指令号<br/>"
	        + "private String vcCarNo;// 车牌号<br/>"
	        + "private String vcSubno;// 分供方编号<br/>"
	        + "private Integer IApprove;// 审批人id<br/>"
	        + "private String vcApproveName;// 审批人姓名<br/>"
	        + "private Date dtApprove;// 审批时间<br/>"
	        + "private Integer nApproveResult;// 审批结果(0,通过，1，未审批,2:未通过)<br/>"
	        + "private String vcNote;// 审批备注<br/>"
	        + "private String vcDetourLength;// 绕路长度（审批人填写）<br/>"
	        + "private String vcUserPhone;// 申请人手机号" )
	@RequestMapping( value = "/getDetailById" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDetailById(
	        HttpServletRequest request ,
	        @ApiParam( value = "id" , required = true ) @RequestParam( value = "id" , required = true ) String id )
	{
		
		try
		{
			
			TDetour tDetour = iDetourApplyService.findDetourById( Integer.parseInt( id ) );
			if ( null != tDetour )
			{
				return AjaxUtil.getMapByNotException( true , tDetour );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , tDetour );
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
	 * @Description: TODO(获取地图)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-8 下午2:37:07
	 */
	@RequestMapping( value = "/getCurrentMap" )
	@ResponseBody
	@ApiIgnore
	public ModelAndView getCurrentMap( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		ModelMap map = new ModelMap();
		int detourId = Integer.parseInt( request.getParameter( "id" ) );
		TDetour tDetour = iDetourApplyService.findDetourById( detourId );
		int driverId = tDetour.getIUser();// 司机ID
		/**
		 * 找出司机对应的userId
		 */
		String[] properties = { "iArchive" , "IArchiveType" };
		Object[] mainValues = { driverId , SystemConstants.SYS_TARCHIVE_DRIVER };
		
		TUser tUser = iUserService.findByProperties( properties , mainValues )
		        .get( 0 );
		int userId = tUser.getId();
		List< TShiphead > tShipheads = iShipheadService.findByProperty(
		        "vcShipno" , tDetour.getVcShipno() );
		List< Map< String , Object >> result = null;

		if ( tShipheads != null && tShipheads.size() > 0 )
		{
			
			TShiphead tShiphead = tShipheads.get( 0 );
			// 获取状态时间 取最早的发运时间
			TShipStatus status1 = mapService.getForwardStatus( tShiphead
			        .getId() );
			
			if ( status1 != null )
			{
				
				Date dtForward = status1.getDtStatus();// 获取已发运的时间
				// 根据userId和时间段获取所有的gps并按时间排序
				// HqlHelper hql = new HqlHelper( TUserGps.class );
				// hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
				SimpleDateFormat format = new SimpleDateFormat(
				        "yyyy-MM-dd HH:mm:ss" );
				String forwadStr = format.format( dtForward );
				String sql = "select  vc_long,vc_lat,vc_hash from t_user_gps where dt_add>=to_date('"
				        + forwadStr + "','yyyy-MM-dd HH24:mi:ss')";
				
				// 取最晚的抵达时间
				TShipStatus status2 = mapService.getArrivedStatus( tShiphead
				        .getId() );
				if ( status2 != null )
				{
					Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
					// hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
					String arrivedStr = format.format( dtArrived );
					sql += " and dt_add<=to_date('" + arrivedStr
					        + "','yyyy-MM-dd HH24:mi:ss')";
				}
				sql += " and i_user=" + userId + " order by dt_add";
				// Map< String , Object > result = gpsService.findAllByHqlHelp(
				// hql
				// );
				System.out.println( "sql:" + sql );
				result = jdbcDao.queryForList( sql );
				if ( CollectionUtils.isEmpty( result ) )
				{
					// return AjaxUtil.getMapByNotException( false , null );
					// 如果找不到gps信息取司机最新的gps定位
					TUserGps gps = gpsService.getGpsByUserid( userId );
					Map< String , Object > data = new HashMap< String , Object >();
					if ( gps != null )
					{
						data.put( "VC_LONG" , gps.getVcLong() );
						data.put( "VC_LAT" , gps.getVcLat() );
						data.put( "VC_HASH" , gps.getVcHash() );
						result.add( data );
					}
					
				}
				map.put( "tDetour" , tDetour );
				result = filterRepeate( result );// 过滤掉重复的gps信息
				if ( result.size() == 1 )
				{
					map.put( "error" , "起始点与终点位置一置" );
				}
			}
			
		}
		else
		{
			map.put( "error" , "查询不到指令号" );
		}
		if ( CollectionUtils.isNotEmpty( result ) )
		{
			JSONArray jsonArray = JSONArray.fromObject( result );
			map.put( "result" , jsonArray );
		}
		
		ModelAndView view = new ModelAndView( "sub/flowApprove/tDetourMap" ,
		        map );
		return view;
	}
	
	/**
	 * @Description: TODO(过滤掉重复的gps信息)
	 * @param result
	 * @return List<Map<String,Object>> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-9 下午1:59:54
	 */
	private List< Map< String , Object >> filterRepeate(
	        List< Map< String , Object >> result )
	{
		List< Map< String , Object > > newMapList = new ArrayList< Map< String , Object > >();
		
		for ( int i = 0 ; i < result.size() ; i++ )
		{
			// Map< String , Object > newMap = new HashMap< String , Object >();
			if ( ! newMapList.contains( result.get( i ) ) )
			{
				newMapList.add( result.get( i ) );
			}
		}
		
		return newMapList;
	}
}
