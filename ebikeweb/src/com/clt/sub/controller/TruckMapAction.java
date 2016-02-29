package com.clt.sub.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.common.Geohash;
import com.clt.sub.dao.IFineApplayDao;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.IShipStatusService;
import com.clt.sub.service.IShipheadService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverLinkService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.sub.service.ITruckMapService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserGps;
import com.clt.systemmanger.service.IUserGpsService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.SystemConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping( "/truckMapAction" )
@Controller
@Api( value = "truckMapAction-api" , description = "关于车辆地图的操作" , position = 5 )
public class TruckMapAction
{
	@Autowired
	private ITruckMapService mapService;
	@Autowired
	private ITruckDriverService truckService;
	@Autowired
	private IDriverService driverService;
	@Autowired
	private JdbcTemplate jdbcDao;
	@Autowired
	private IShipheadService shipheadService;
	@Autowired
	private IShipStatusService shipStatusService;
	@Autowired
	private ITruckDriverLinkService linkService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserGpsService gpsService;
	@Autowired
	private IFineApplayDao fineDao;
	@Autowired
	private ISubsuppliersService subService;
	
	/**
	 * @Description 获取所有的车牌号
	 * @return Map< String , Object >
	 * @author chengwzhu
	 * @date 2015/5/19 14:10
	 */
	@RequestMapping( value = "/getAllCarNo" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取车牌号列表(分供方:查看所有,司机角色：只能查看自己)" , notes = "获取车牌号列表:<br/>"
	        + "Map<String,Object>属性字段如下：<br/>" + "ID:车辆id <br/>" + "VC_CAR_NO:车牌号" , position = 5 )
	public Map< String , Object > getAllCarNo( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		int type = user.getIArchiveType();
		try
		{
			if ( type == SystemConstants.SYS_TARCHIVE_DRIVER )
			{
				// 司机:只能查看自己的路线
				List< TTruckDriverLlink > links = linkService.getByProperty( "IDriverID" ,
				        subId );
				if ( CollectionUtils.isEmpty( links ) )
				{
					return AjaxUtil.getMap( false , "查询不到相关数据" );
				}
				else
				{
					List< Map< String , Object >> list = new ArrayList< Map< String , Object >>();
					Map< String , Object > res = new HashMap< String , Object >();
					int truckId = links.get( 0 ).getITruckID();
					TTruckDriver truck = truckService.get( truckId );
					res.put( "ID" , truckId );
					res.put( "VC_CAR_NO" , truck.getVcCarNo() );
					// Map< String , Object > result = getLine( truckId ,
					// request );
					// List< Map< String , Object >> line = ( List< Map< String
					// , Object >> ) result
					// .get( "data" );
					// if ( CollectionUtils.isEmpty( line ) )
					// {
					// res.put( "line" , new ArrayList< TUserGps >() );
					// }
					// else
					// {
					// res.put( "line" , line );
					// }
					list.add( res );
					JSONArray arr = JSONArray.fromObject( list );
					System.out.println( "jsonArr:" + arr );
					return AjaxUtil.getMapByNotException( true , list );
				}
			}
			if ( type == SystemConstants.SYS_TARCHIVE_SUB )
			{
				// 分供方：查看所有司机的路线
				String subno = subService.get( subId ).getVcSubno();
				List< Map< String , Object >> results = mapService.getCarNoList( subno );
				// for ( Map< String , Object > map : results )
				// {
				// Object truckIdObj = map.get( "ID" );
				// if ( truckIdObj == null )
				// {
				// map.put( "line" , new ArrayList< TUserGps >() );
				// }
				// else
				// {
				// int truckId = Integer.parseInt( truckIdObj.toString() );
				// Map< String , Object > result = getLine( truckId , request );
				// List< Map< String , Object >> line = ( List< Map< String ,
				// Object >> ) result
				// .get( "data" );
				// if ( CollectionUtils.isEmpty( line ) )
				// {
				// map.put( "line" , new ArrayList< TUserGps >() );
				// }
				// else
				// {
				// map.put( "line" , line );
				// }
				// }
				// }
				return AjaxUtil.getMapByNotException( true , results );
			}
			return null;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 通过运输车id，获得他对应主驾司机的经纬度，按时间升序
	 * 
	 * @param truckId
	 * @return
	 */
	@RequestMapping( value = "/getLine" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获得他对应主驾司机的经纬度" , notes = "获得他对应主驾司机的经纬度:<br/>"
	        + "truckId:运输车id" , position = 5 )
	public Map< String , Object > getLine(
	        @ApiParam( "运输车id" ) @RequestParam( "truckId" ) int truckId ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		// 获取档案类型
		int type = user.getIArchiveType();
		try
		{
			// 获取发运时间和抵达时间
			TTruckDriver truck = truckService.get( truckId );
			String vcShipno = truck.getVcShipNo();
			List< TShiphead > shipheads = shipheadService.findByProperty( "vcShipno" ,
			        vcShipno );
			if ( CollectionUtils.isEmpty( shipheads ) )
			{
				return AjaxUtil.getMap( false , "找不到发运信息" );
			}
			int headId = shipheads.get( 0 ).getId();
			// 获取状态时间 取最早的发运时间
			TShipStatus status1 = mapService.getForwardStatus( headId );
			if ( status1 == null )
			{
				return AjaxUtil.getMap( false , "无法查到已发运时间" );
			}
			Date dtForward = status1.getDtStatus();// 获取已发运的时间
			// 根据userId和时间段获取所有的gps并按时间排序
			// HqlHelper hql = new HqlHelper( TUserGps.class );
			// hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			String forwadStr = format.format( dtForward );
			String sql = "select vc_long,vc_lat,vc_hash from t_user_gps where dt_add>=to_date('"
			        + forwadStr + "','yyyy-MM-dd HH24:mi:ss')";
			
			// 取最晚的抵达时间
			TShipStatus status2 = mapService.getArrivedStatus( headId );
			if ( status2 != null )
			{
				Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
				// hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
				String arrivedStr = format.format( dtArrived );
				sql += " and dt_add<=to_date('" + arrivedStr
				        + "','yyyy-MM-dd HH24:mi:ss')";
			}
			int userId = 0;
			if ( type == SystemConstants.SYS_TARCHIVE_DRIVER )
			{
				userId = user.getId();
			}
			else
			{
				// 通过truckId查询对应的主驾人userId
				String[] propertyNames = { "ITruckID" , "NPositionType" };
				Object[] values = { truckId , 1 };
				List< TTruckDriverLlink > links = linkService.findByPropertys(
				        propertyNames , values );
				if ( CollectionUtils.isEmpty( links ) )
				{
					return AjaxUtil.getMap( false , "该车辆没有指定司机" );
				}
				TTruckDriverLlink link = links.get( 0 );
				int driverId = link.getIDriverID();
				TDriver driver = driverService.get( driverId );
				if ( driver == null )
				{
					return AjaxUtil.getMap( false , "找不到该车辆司机" );
				}
				String driverName = driver.getVcDriverName();
				List< TUser > users = userService.findByProperty( "vcUsername" ,
				        driverName );
				if ( CollectionUtils.isEmpty( users ) )
				{
					return AjaxUtil.getMap( false , "主驾司机还未注册" );
				}
				TUser u = users.get( 0 );
				userId = u.getId();
			}
			// hql.addEqual( "IUser" , userId );
			// hql.addOrderBy( "dtAdd" );
			sql += " and i_user=" + userId + " order by dt_add";
			// Map< String , Object > result = gpsService.findAllByHqlHelp( hql
			// );
			System.out.println( "sql:" + sql );
			List< Map< String , Object >> res = jdbcDao.queryForList( sql );
			Set< Map< String , Object >> result = new HashSet< Map< String , Object >>(
			        res );// 去重
			JSONArray arr = JSONArray.fromObject( result );
			System.out.println( "jsonArr:" + arr );
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
				else
				{
					return AjaxUtil.getMapByNotException( false , null );
				}
				
			}
			return AjaxUtil.getMapByNotException( true , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 获取所有的罚款点
	 * @author chengwzh
	 * @date 2015/5/19 14:20
	 */
	@RequestMapping( value = "/getAllChargePoints" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有罚款点" , notes = "truckId:车辆id" , position = 5 )
	public Map< String , Object > getAllChargePoints(
	        @ApiParam( "运输车id" ) @RequestParam( "truckId" ) int truckId ,
	        HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		// 获取档案类型
		int type = user.getIArchiveType();
		try
		{
			// 获取发运时间和抵达时间
			TTruckDriver truck = truckService.get( truckId );
			String vcShipno = truck.getVcShipNo();
			List< TShiphead > shipheads = shipheadService.findByProperty( "vcShipno" ,
			        vcShipno );
			if ( CollectionUtils.isEmpty( shipheads ) )
			{
				return AjaxUtil.getMap( false , "找不到发运信息" );
			}
			int headId = shipheads.get( 0 ).getId();
			// 获取状态时间 取最早的发运时间
			TShipStatus status1 = mapService.getForwardStatus( headId );
			if ( status1 == null )
			{
				return AjaxUtil.getMap( false , "无法查到已发运时间" );
			}
			Date dtForward = status1.getDtStatus();// 获取已发运的时间
			// 根据userId和时间段获取所有的gps并按时间排序
			HqlHelper hql = new HqlHelper( TUserGps.class );
			
			hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
			
			// 取最晚的抵达时间
			TShipStatus status2 = mapService.getArrivedStatus( headId );
			if ( status2 != null )
			{
				Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
				hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
				
			}
			int userId = 0;
			if ( type == SystemConstants.SYS_TARCHIVE_DRIVER )
			{
				userId = user.getId();
			}
			else
			{
				// 通过truckId查询对应的主驾人userId
				String[] propertyNames = { "ITruckID" , "NPositionType" };
				Object[] values = { truckId , 1 };
				List< TTruckDriverLlink > links = linkService.findByPropertys(
				        propertyNames , values );
				if ( CollectionUtils.isEmpty( links ) )
				{
					return AjaxUtil.getMap( false , "该车辆没有指定司机" );
				}
				TTruckDriverLlink link = links.get( 0 );
				int driverId = link.getIDriverID();
				TDriver driver = driverService.get( driverId );
				if ( driver == null )
				{
					return AjaxUtil.getMap( false , "找不到该车辆司机" );
				}
				String driverName = driver.getVcDriverName();
				List< TUser > users = userService.findByProperty( "vcUsername" ,
				        driverName );
				if ( CollectionUtils.isEmpty( users ) )
				{
					return AjaxUtil.getMap( false , "主驾司机还未注册" );
				}
				TUser u = users.get( 0 );
				userId = u.getId();
			}
			
			hql.addEqual( "IUser" , userId );
			hql.addOrderBy( "dtAdd" );
			Map< String , Object > result = gpsService.findAllByHqlHelp( hql );
			List< TUserGps > gpss = ( List< TUserGps > ) result.get( "rows" );
			if ( CollectionUtils.isEmpty( gpss ) )
			{
				return AjaxUtil.getMap( false , "没有gps记录" );
			}
			List< Map< String , Object > > points = new ArrayList< Map< String , Object >>();
			Geohash geohash = new Geohash();
			for ( TUserGps gps : gpss )
			{
				String vcHash = gps.getVcHash();
				if ( StringUtils.isBlank( vcHash ) )
				{
					String vcLat = gps.getVcLat();
					String vcLon = gps.getVcLong();
					double lat = Double.parseDouble( vcLat );
					double lon = Double.parseDouble( vcLon );
					vcHash = geohash.encode( lat , lon );
					// System.out.println( "--------------经纬度：" + lat + "," +
					// lon + ","
					// + vcHash );
				}
				String subHash = vcHash.substring( 0 , 5 );// 截取前4位
				// System.out.println( "subHash:" + subHash );
				List< Map< String , Object >> fs = mapService.findFinesByHash( subHash );
				if ( CollectionUtils.isNotEmpty( fs ) )
				{
					points.removeAll( fs );// 先去重
					points.addAll( fs );// 合并集合
				}
			}
			JSONArray arr = JSONArray.fromObject( points );
			System.out.println( "罚款点：" + arr );
			return AjaxUtil.getMapByNotException( true , points );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 获取所有的修车点
	 * @author chengwz
	 * @date 2015/5/20 11:13
	 */
	@RequestMapping( value = "/getAllFixPoints" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有的修车点" , notes = "truckId:车辆id" , position = 5 )
	public Map< String , Object > getAllFixPoints(
	        @ApiParam( "运输船id" ) @RequestParam( "truckId" ) int truckId ,
	        HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		// 获取档案类型
		int type = user.getIArchiveType();
		try
		{
			// 获取发运时间和抵达时间
			TTruckDriver truck = truckService.get( truckId );
			String vcShipno = truck.getVcShipNo();
			List< TShiphead > shipheads = shipheadService.findByProperty( "vcShipno" ,
			        vcShipno );
			if ( CollectionUtils.isEmpty( shipheads ) )
			{
				return AjaxUtil.getMap( false , "找不到发运信息" );
			}
			int headId = shipheads.get( 0 ).getId();
			// 获取状态时间 取最早的发运时间
			TShipStatus status1 = mapService.getForwardStatus( headId );
			if ( status1 == null )
			{
				return AjaxUtil.getMap( false , "无法查到已发运时间" );
			}
			Date dtForward = status1.getDtStatus();// 获取已发运的时间
			
			HqlHelper hql = new HqlHelper( TUserGps.class );
			hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
			// 取最晚的抵达时间
			TShipStatus status2 = mapService.getArrivedStatus( headId );
			if ( status2 != null )
			{
				// return AjaxUtil.getMap( false , "无法查到已运抵时间" );
				Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
				hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
			}
			// System.out.println( dtForward + ":" + dtArrived );
			
			int userId = 0;
			if ( type == SystemConstants.SYS_TARCHIVE_DRIVER )
			{
				userId = user.getId();
			}
			else
			{
				// 通过truckId查询对应的主驾人userId
				String[] propertyNames = { "ITruckID" , "NPositionType" };
				Object[] values = { truckId , 1 };
				List< TTruckDriverLlink > links = linkService.findByPropertys(
				        propertyNames , values );
				if ( CollectionUtils.isEmpty( links ) )
				{
					return AjaxUtil.getMap( false , "无法查到主驾司机id" );
				}
				TTruckDriverLlink link = links.get( 0 );
				int driverId = link.getIDriverID();
				TDriver driver = driverService.get( driverId );
				if ( driver == null )
				{
					return AjaxUtil.getMap( false , "无法查到主驾司机" );
				}
				String driverName = driver.getVcDriverName();
				List< TUser > users = userService.findByProperty( "vcUsername" ,
				        driverName );
				if ( CollectionUtils.isEmpty( users ) )
				{
					return AjaxUtil.getMap( false , "主驾司机还未注册" );
				}
				TUser u = users.get( 0 );
				userId = u.getId();
			}
			
			// 根据userId和时间段获取所有的gps并按时间排序
			hql.addEqual( "IUser" , userId );
			hql.addOrderBy( "dtAdd" );
			Map< String , Object > result = gpsService.findAllByHqlHelp( hql );
			List< TUserGps > gpss = ( List< TUserGps > ) result.get( "rows" );
			if ( CollectionUtils.isEmpty( gpss ) )
			{
				return AjaxUtil.getMap( false , "没有gps记录" );
			}
			List< Map< String , Object > > points = new ArrayList< Map< String , Object >>();
			Geohash geohash = new Geohash();
			for ( TUserGps gps : gpss )
			{
				String vcHash = gps.getVcHash();
				if ( StringUtils.isBlank( vcHash ) )
				{
					String vcLat = gps.getVcLat();
					String vcLon = gps.getVcLong();
					double lat = Double.parseDouble( vcLat );
					double lon = Double.parseDouble( vcLon );
					vcHash = geohash.encode( lat , lon );
				}
				String subHash = vcHash.substring( 0 , 5 );// 截取前4位
				
				List< Map< String , Object >> fs = mapService.getFixByHash( subHash );
				if ( CollectionUtils.isNotEmpty( fs ) )
				{
					points.removeAll( fs );// 去重
					points.addAll( fs );// 合并
				}
			}
			JSONArray arr = JSONArray.fromObject( points );
			System.out.println( "修车点：" + arr );
			return AjaxUtil.getMapByNotException( true , points );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 获取所有的事故点
	 * @author chengwzh
	 * @date 2015/5/20 17:21
	 */
	@RequestMapping( value = "/getAccidentpoints" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有的事故点" , notes = "truckId:车辆id" , position = 5 )
	public Map< String , Object > getAccidentpoints(
	        @RequestParam( "truckId" ) int truckId , HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		// 获取档案类型
		int type = user.getIArchiveType();
		try
		{
			// 获取发运时间和抵达时间
			TTruckDriver truck = truckService.get( truckId );
			String vcShipno = truck.getVcShipNo();
			List< TShiphead > shipheads = shipheadService.findByProperty( "vcShipno" ,
			        vcShipno );
			if ( CollectionUtils.isEmpty( shipheads ) )
			{
				return AjaxUtil.getMap( false , "找不到发运信息" );
			}
			int headId = shipheads.get( 0 ).getId();
			// 获取状态时间 最早的发运时间
			TShipStatus status1 = mapService.getForwardStatus( headId );
			if ( status1 == null )
			{
				return AjaxUtil.getMap( false , "找不到已发运时间" );
			}
			Date dtForward = status1.getDtStatus();// 获取已发运的时间
			HqlHelper hql = new HqlHelper( TUserGps.class );
			hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
			// 取最晚的抵达时间
			TShipStatus status2 = mapService.getArrivedStatus( headId );
			if ( status2 != null )
			{
				// return AjaxUtil.getMap( false , "无法查到已运抵时间" );
				Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
				hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
			}
			// System.out.println( dtForward + ":" + dtArrived );
			
			int userId = 0;
			if ( type == SystemConstants.SYS_TARCHIVE_DRIVER )
			{
				userId = user.getId();
			}
			else
			{
				// 通过truckId查询对应的主驾人userId
				String[] propertyNames = { "ITruckID" , "NPositionType" };
				Object[] values = { truckId , 1 };
				List< TTruckDriverLlink > links = linkService.findByPropertys(
				        propertyNames , values );
				if ( CollectionUtils.isEmpty( links ) )
				{
					return AjaxUtil.getMap( false , "找不到主驾司机" );
				}
				TTruckDriverLlink link = links.get( 0 );
				int driverId = link.getIDriverID();
				TDriver driver = driverService.get( driverId );
				if ( driver == null )
				{
					return AjaxUtil.getMap( false , "找不到主驾司机" );
				}
				String driverName = driver.getVcDriverName();
				List< TUser > users = userService.findByProperty( "vcUsername" ,
				        driverName );
				if ( CollectionUtils.isEmpty( users ) )
				{
					return AjaxUtil.getMap( false , "主驾司机还未注册" );
				}
				TUser u = users.get( 0 );
				userId = u.getId();
			}
			
			// 根据userId和时间段获取所有的gps并按时间排序
			hql.addEqual( "IUser" , userId );
			hql.addOrderBy( "dtAdd" );
			Map< String , Object > result = gpsService.findAllByHqlHelp( hql );
			List< TUserGps > gpss = ( List< TUserGps > ) result.get( "rows" );
			if ( CollectionUtils.isEmpty( gpss ) )
			{
				return AjaxUtil.getMap( false , "没有gps记录" );
			}
			Geohash geohash = new Geohash();
			List< Map< String , Object > > points = new ArrayList< Map< String , Object >>();
			for ( TUserGps gps : gpss )
			{
				String vcHash = gps.getVcHash();
				if ( StringUtils.isBlank( vcHash ) )
				{
					String vcLat = gps.getVcLat();
					String vcLon = gps.getVcLong();
					double lat = Double.parseDouble( vcLat );
					double lon = Double.parseDouble( vcLon );
					vcHash = geohash.encode( lat , lon );
				}
				String subHash = vcHash.substring( 0 , 5 );// 截取前4位
				// System.out.println( "subHash:" + subHash );
				List< Map< String , Object >> fs = mapService.getAccidentByHash( subHash );
				if ( CollectionUtils.isNotEmpty( fs ) )
				{
					points.removeAll( fs );// 先去重
					points.addAll( fs );// 合并集合
				}
			}
			JSONArray arr = JSONArray.fromObject( points );
			System.out.println( "事故点：" + arr );
			return AjaxUtil.getMapByNotException( true , points );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 获取所有的堵车点
	 * @author chengwzh
	 * @date
	 */
	@RequestMapping( value = "/getTrafficPoints" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有的堵车点" , notes = "truckId:车辆id" , position = 5 )
	public Map< String , Object > getTrafficPoints(
	        @RequestParam( "truckId" ) int truckId , HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		// 获取档案类型
		int type = user.getIArchiveType();
		try
		{
			// 获取发运时间和抵达时间
			TTruckDriver truck = truckService.get( truckId );
			String vcShipno = truck.getVcShipNo();
			List< TShiphead > shipheads = shipheadService.findByProperty( "vcShipno" ,
			        vcShipno );
			if ( CollectionUtils.isEmpty( shipheads ) )
			{
				return AjaxUtil.getMap( false , "找不到发运信息" );
			}
			int headId = shipheads.get( 0 ).getId();
			// 获取状态时间 取最早的发运时间
			TShipStatus status1 = mapService.getForwardStatus( headId );
			if ( status1 == null )
			{
				return AjaxUtil.getMap( false , "无法查到已发运时间" );
			}
			Date dtForward = status1.getDtStatus();// 获取已发运的时间
			HqlHelper hql = new HqlHelper( TUserGps.class );
			hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
			// 取最晚的抵达时间
			TShipStatus status2 = mapService.getArrivedStatus( headId );
			if ( status2 != null )
			{
				// return AjaxUtil.getMap( false , "无法查到已运抵时间" );
				Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
				hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
			}
			// System.out.println( dtForward + ":" + dtArrived );
			
			int userId = 0;
			if ( type == SystemConstants.SYS_TARCHIVE_DRIVER )
			{
				userId = user.getId();
			}
			else
			{
				// 通过truckId查询对应的主驾人userId
				String[] propertyNames = { "ITruckID" , "NPositionType" };
				Object[] values = { truckId , 1 };
				List< TTruckDriverLlink > links = linkService.findByPropertys(
				        propertyNames , values );
				if ( CollectionUtils.isEmpty( links ) )
				{
					return AjaxUtil.getMap( false , "无法查到主驾司机信息" );
				}
				TTruckDriverLlink link = links.get( 0 );
				int driverId = link.getIDriverID();
				TDriver driver = driverService.get( driverId );
				if ( driver == null )
				{
					return AjaxUtil.getMap( false , "无法查到主驾司机" );
				}
				String driverName = driver.getVcDriverName();
				List< TUser > users = userService.findByProperty( "vcUsername" ,
				        driverName );
				if ( CollectionUtils.isEmpty( users ) )
				{
					return AjaxUtil.getMap( false , "主驾司机还未注册" );
				}
				TUser u = users.get( 0 );
				userId = u.getId();
			}
			
			// 根据userId和时间段获取所有的gps并按时间排序
			hql.addEqual( "IUser" , userId );
			hql.addOrderBy( "dtAdd" );
			Map< String , Object > result = gpsService.findAllByHqlHelp( hql );
			List< TUserGps > gpss = ( List< TUserGps > ) result.get( "rows" );
			if ( CollectionUtils.isEmpty( gpss ) )
			{
				return AjaxUtil.getMap( false , "没有gps记录" );
			}
			Geohash geohash = new Geohash();
			List< Map< String , Object > > points = new ArrayList< Map< String , Object >>();
			for ( TUserGps gps : gpss )
			{
				String vcHash = gps.getVcHash();
				if ( StringUtils.isBlank( vcHash ) )
				{
					String vcLat = gps.getVcLat();
					String vcLon = gps.getVcLong();
					double lat = Double.parseDouble( vcLat );
					double lon = Double.parseDouble( vcLon );
					vcHash = geohash.encode( lat , lon );
				}
				String subHash = vcHash.substring( 0 , 5 );// 截取前4位
				// System.out.println( "subHash:" + subHash );
				List< Map< String , Object >> fs = mapService.getTrafficByHash( subHash );
				if ( fs != null && CollectionUtils.isNotEmpty( fs ) )
				{
					points.removeAll( fs );// 先去重
					points.addAll( fs );// 合并集合
				}
			}
			JSONArray arr = JSONArray.fromObject( points );
			System.out.println( "堵车点：" + arr );
			return AjaxUtil.getMapByNotException( true , points );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 用于订单获取路线
	 * @param headId
	 * @return
	 */
	@RequestMapping( value = "/getPath" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取路线(用于订单获取路线)" , notes = "headId:发运主表id" , position = 5 )
	public Map< String , Object > getPath(
	        @ApiParam( "发运主表id" ) @RequestParam( value = "headId" ) int headId )
	{
		try
		{
			// 获取状态时间 取最早的发运时间
			TShipStatus status1 = mapService.getForwardStatus( headId );
			if ( status1 == null )
			{
				return AjaxUtil.getMap( false , "无法查到已发运时间" );
			}
			Date dtForward = status1.getDtStatus();// 获取已发运的时间
			// 根据userId和时间段获取所有的gps并按时间排序
			// HqlHelper hql = new HqlHelper( TUserGps.class );
			// hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			String forwadStr = format.format( dtForward );
			String sql = "select vc_long,vc_lat,vc_hash from t_user_gps where dt_add>=to_date('"
			        + forwadStr + "','yyyy-MM-dd HH24:mi:ss')";
			
			// 取最晚的抵达时间
			TShipStatus status2 = mapService.getArrivedStatus( headId );
			if ( status2 != null )
			{
				Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
				// hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
				String arrivedStr = format.format( dtArrived );
				sql += " and dt_add<=to_date('" + arrivedStr
				        + "','yyyy-MM-dd HH24:mi:ss')";
			}
			// 获取userId
			TShiphead shiphead = shipheadService.get( headId );
			String vcDriverId = shiphead.getVcDriverId();
			if ( StringUtils.isBlank( vcDriverId ) )
			{
				return AjaxUtil.getMap( false , "找不到关联的司机" );
			}
			String[] driverIds = vcDriverId.split( "," );
			int driverId = Integer.parseInt( driverIds[0] );// 获取主驾id
			int iArchive = driverId;
			String[] properties = { "NEnable" , "iArchive" };
			Object[] values = { SystemConstants.SYS_ENABLE , iArchive };
			List< TUser > users = userService.findByProperties( properties , values );
			if ( CollectionUtils.isEmpty( users ) )
			{
				return AjaxUtil.getMap( false , "主驾司机没有注册" );
			}
			TUser user = users.get( 0 );
			int userId = user.getId();
			sql += " and i_user=" + userId + " order by dt_add";
			// Map< String , Object > result = gpsService.findAllByHqlHelp( hql
			// );
			System.out.println( "sql:" + sql );
			List< Map< String , Object >> res = jdbcDao.queryForList( sql );
			Set< Map< String , Object >> result = new HashSet< Map< String , Object >>(
			        res );
			JSONArray arr = JSONArray.fromObject( result );
			System.out.println( "jsonArr:" + arr );
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
				else
				{
					return AjaxUtil.getMapByNotException( false , null );
				}
				
			}
			return AjaxUtil.getMapByNotException( true , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
}
