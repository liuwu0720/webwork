package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IDriverDao;
import com.clt.sub.dao.IShipStatusDao;
import com.clt.sub.dao.IShipheadDao;
import com.clt.sub.dao.ITruckDriverDao;
import com.clt.sub.dao.ITruckDriverLinkDao;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.service.ITruckMapService;
import com.clt.systemmanger.dao.ITShareTagDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.dao.IUserGpsDao;
import com.clt.systemmanger.model.TShareTag;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserGps;
import com.clt.util.HqlHelper;
import com.clt.util.SystemConstants;

@Service
public class TruckMapServiceImp implements ITruckMapService
{
	@Autowired
	private ITruckDriverDao truckDao;
	@Autowired
	private IDriverDao driverDao;
	@Autowired
	private JdbcTemplate jdbcDao;
	@Autowired
	private IShipheadDao shipheadDao;
	@Autowired
	private IShipStatusDao shipStatusDao;
	@Autowired
	private ITruckDriverLinkDao linkDao;
	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserGpsDao gpsDao;
	@Autowired
	private ITShareTagDao tagDao;
	
	/*获取车牌号列表*/
	public List< Map< String , Object >> getCarNoList( String subno )
	{
		String sql = "select t.id,t.vc_car_no from t_truck_driver t,t_truck_driver_link l "
		        + " where t.id=l.i_truck and l.n_position_type=1 and t.N_ENABLE="
		        + SystemConstants.SYS_ENABLE + " and t.VC_SUBNO='" + subno + "'";
		List< Map< String , Object >> trucks = jdbcDao.queryForList( sql );
		JSONArray array = JSONArray.fromObject( trucks );
		System.out.println( "getAllCarNo  json:" + array );
		return trucks;
	}
	
	/**
	 * @Description 通过headId获取已运抵的TShipStatus
	 * @param type
	 *            按升序还是降序排列 升序：asc ，降序：desc
	 */
	public TShipStatus getArrivedStatus( int headId )
	{
		
		String sql = "select N_CURRENT_STATUS from T_SHIPLINE where I_SHIPHEAD = "
		        + headId;
		
		List< Integer > list = jdbcDao.queryForList( sql , Integer.class );
		boolean hasDd = true;
		for ( Integer integer : list )
		{
			if ( integer < 18 )
			{
				hasDd = false;
				break;
			}
		}
		if ( hasDd )
		{
			HqlHelper hql = new HqlHelper( TShipStatus.class );
			hql.addEqual( "nHeadId" , headId );
			hql.addEqual( "vcStatusNote" , "已运抵" );
			hql.addOrderBy( "dtStatus" , "desc" );// 倒序排列
			Map< String , Object > result = shipStatusDao.findAllByHqlHelp( hql );
			List< TShipStatus > sslist = ( List< TShipStatus > ) result.get( "rows" );
			return sslist.get( 0 );
		}
		else
		{
			return null;
		}
		
		// String[] propertyNames = new String[] { "nHeadId" , "vcStatusNote" };
		// Object[] values = { headId , status };
		// List< TShipStatus > list = shipStatusDao.findByPropertys(
		// propertyNames , values );
		// if ( CollectionUtils.isEmpty( list ) )
		// {
		// return null;
		// }
		// else
		// {
		// return list.get( 0 );
		// }
		
	}
	
	/**
	 * @Description 通过headId获取已发运的TShipStatus
	 * @param type
	 *            按升序还是降序排列 升序：asc ，降序：desc
	 */
	public TShipStatus getForwardStatus( int headId )
	{
		HqlHelper hql = new HqlHelper( TShipStatus.class );
		hql.addEqual( "nHeadId" , headId );
		hql.addEqual( "vcStatusNote" , "已发运" );
		hql.addOrderBy( "dtStatus" , "asc" );// 顺序排列
		Map< String , Object > result = shipStatusDao.findAllByHqlHelp( hql );
		List< TShipStatus > sslist = ( List< TShipStatus > ) result.get( "rows" );
		if ( CollectionUtils.isEmpty( sslist ) )
		{
			return null;
		}
		else
		{
			return sslist.get( 0 );
		}
	}
	
	/*根据hash值模糊匹配查询罚款点*/
	public List< Map< String , Object >> findFinesByHash( String hash )
	{
		// HqlHelper hql = new HqlHelper( TFineApplay.class );
		// hql.addLike( "vcHash" , hash );
		// hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		// Map< String , Object > map = fineDao.findAllByHqlHelp( hql );
		// List< TFineApplay > fines = ( List< TFineApplay > ) map.get( "rows"
		// );
		// return fines;
		String sql = "select distinct VC_LONGITUDE,VC_LATITUDE,N_FINE "
		        + " from t_fine_applay where N_ENABLE=" + SystemConstants.SYS_ENABLE
		        + "and VC_HASH like '" + hash + "%'";
		List< Map< String , Object >> list = jdbcDao.queryForList( sql );
		return list;
	}
	
	/*获取罚款点列表*/
	public List< Map< String , Object >> getChargeList( int truckId )
	{
		// 通过truckId查询最新的指令号
		TTruckDriver truck = truckDao.get( truckId );
		String vcShipNo = truck.getVcShipNo();
		// 通过最新的指令号查找headId
		List< TShiphead > shipheads = shipheadDao.findByProperty( "vcShipno" , vcShipNo );
		if ( CollectionUtils.isEmpty( shipheads ) )
		{
			return null;
		}
		TShiphead shiphead = shipheads.get( 0 );
		int headId = shiphead.getId();
		// 获取状态时间
		// TShipStatus status1 = getByIdAndStatus( headId , "已发运" , "asc" );
		TShipStatus status1 = getForwardStatus( headId );
		if ( status1 == null )
		{
			return null;
		}
		Date dtForward = status1.getDtStatus();// 获取已发运的时间
		// TShipStatus status2 = getByIdAndStatus( headId , "已运抵" , "desc" );
		TShipStatus status2 = getArrivedStatus( headId );
		if ( status2 == null )
		{
			return null;
		}
		Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
		// 通过truckId查询对应的主驾人userId
		String[] propertyNames = { "ITruckID" , "NPositionType" };
		Object[] values = { truckId , 1 };
		List< TTruckDriverLlink > links = linkDao
		        .findByPropertys( propertyNames , values );
		if ( CollectionUtils.isEmpty( links ) )
		{
			return null;
		}
		TTruckDriverLlink link = links.get( 0 );
		int driverId = link.getIDriverID();
		TDriver driver = driverDao.get( driverId );
		if ( driver == null )
		{
			return null;
		}
		String driverName = driver.getVcDriverName();
		List< TUser > users = userDao.findByProperty( "vcUsername" , driverName );
		if ( CollectionUtils.isEmpty( users ) )
		{
			return null;
		}
		TUser user = users.get( 0 );
		int userId = user.getId();
		// 根据userId和时间段获取所有的gps并按时间排序
		HqlHelper hql = new HqlHelper( TUserGps.class );
		hql.addEqual( "IUser" , userId );
		hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
		hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
		hql.addOrderBy( "dtAdd" );
		Map< String , Object > result = gpsDao.findAllByHqlHelp( hql );
		List< TUserGps > gpss = ( List< TUserGps > ) result.get( "rows" );
		List< Map< String , Object > > points = new ArrayList< Map< String , Object >>();
		for ( TUserGps gps : gpss )
		{
			String vcHash = gps.getVcHash();
			String subHash = vcHash.substring( 0 , 8 );// 截取前7位
			List< Map< String , Object >> fs = findFinesByHash( subHash );
			points.removeAll( fs );
			points.addAll( fs );// 迭代查询后汇总到points中
		}
		return points;
	}
	
	/*根据hash值模糊匹配查询修车点*/
	public List< Map< String , Object >> getFixByHash( String hash )
	{
		String sql = "select distinct VC_LONGITUDE,VC_LATITUDE,N_FIX"
		        + " from t_fix_truck where N_ENABLE=" + SystemConstants.SYS_ENABLE
		        + " and VC_HASH like '" + hash + "%'";
		List< Map< String , Object >> list = jdbcDao.queryForList( sql );
		return list;
	}
	
	/*根据hash值模糊匹配查询事故点*/
	public List< Map< String , Object >> getAccidentByHash( String hash )
	{
		String sql = "select distinct VC_LONGITUDE,VC_LATITUDE,VC_HASH"
		        + " from t_damage where N_ENABLE=" + SystemConstants.SYS_ENABLE
		        + " and VC_HASH like '" + hash + "%'";
		List< Map< String , Object >> list = jdbcDao.queryForList( sql );
		return list;
	}
	
	/*根据hash值模糊匹配查询堵车点*/
	public List< Map< String , Object >> getTrafficByHash( String hash )
	{
		// 先查询堵车标签的id
		List< TShareTag > shareTags = tagDao.findByProperty( "vcTag" , "堵车" );
		if ( CollectionUtils.isEmpty( shareTags ) )
		{
			System.out.println( "堵车标签不存在！" );
			return null;
		}
		int tagId = shareTags.get( 0 ).getId();
		// 根据hash值模糊查询分享圈
		String sql = "select distinct VC_LONGITUDE,VC_LATITUDE,VC_HASH,VC_SHARE_TAG "
		        + " from t_sharecircle where VC_HASH like '" + hash + "%'";
		List< Map< String , Object >> list = jdbcDao.queryForList( sql );
		if ( CollectionUtils.isEmpty( list ) )
		{
			System.out.println( "没有分享记录" );
			return null;
		}
		// 根据tagId筛选过滤堵车点
		List< Map< String , Object >> trafficList = new ArrayList< Map< String , Object >>();
		for ( Map< String , Object > map : list )
		{
			String vcTag = map.get( "VC_SHARE_TAG" ).toString();
			String[] vcTags = vcTag.split( "," );
			for ( int i = 0 ; i < vcTags.length ; i++ )
			{
				if ( vcTags[i].trim().equals( String.valueOf( tagId ) ) )
				{
					trafficList.add( map );// 包含堵车标签就挑选出
					break;
				}
			}
		}
		return trafficList;
	}
}
