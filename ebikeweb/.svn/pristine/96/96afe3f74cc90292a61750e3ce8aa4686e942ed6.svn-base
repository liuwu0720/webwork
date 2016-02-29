package com.clt.sub.service.imp;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IDriverDao;
import com.clt.sub.dao.IDriverSalaryCofflisDao;
import com.clt.sub.dao.IDriverSalarySubsideDao;
import com.clt.sub.dao.IDriverSubsideLinksDao;
import com.clt.sub.dao.ITruckDriverLinkDao;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TDriverSalaryCoefficient;
import com.clt.sub.model.TDriverSubsideLinks;
import com.clt.sub.model.TDriverSubsides;
import com.clt.sub.service.IDriverService;
import com.clt.systemmanger.dao.IDriverClassDao;
import com.clt.systemmanger.model.TDriverClass;
import com.clt.systemmanger.service.IStaticService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class DriverServiceImp implements IDriverService
{
	@Autowired
	private IDriverDao driverDao;
	@Autowired
	private JdbcTemplate jdbcTemp;
	@Autowired
	private ITruckDriverLinkDao linkDao;
	@Autowired
	private IDriverClassDao driverClassDao;
	@Autowired
	private IDriverSalaryCofflisDao iSalaryCofflisDao;
	@Autowired
	private IDriverSalarySubsideDao iSalarySubsideDao;
	@Autowired
	IDriverSubsideLinksDao iDriverSubsideLinksDao;
	@Autowired
	private IStaticService staticService;
	
	public void delete( TDriver entity )
	{
		driverDao.delete( entity );
	}
	
	public Map< String , Object > findByHelper( HqlHelper hql )
	{
		return driverDao.findAllByHqlHelp( hql );
	}
	
	public List< TDriver > findByProperty( String propertyName , Object value )
	{
		
		return driverDao.findByProperty( propertyName , value );
	}
	
	public TDriver get( Integer id )
	{
		
		return driverDao.get( id );
	}
	
	public void save( TDriver entity )
	{
		driverDao.save( entity );
	}
	
	/**
	 * 更新的时候，判断图片路径是否需要更换
	 */
	public void update( TDriver entity )
	{
		String path = entity.getVcImgPath();
		if ( StringUtils.isNotBlank( path ) && path.startsWith( "http" ) )
		{
			TDriver tDriver = driverDao.get( entity.getId() );
			if ( null != tDriver )
			{
				String vcImgPath = tDriver.getVcImgPath();
				entity.setVcImgPath( vcImgPath );
			}
		}
		driverDao.updateCleanBefore( entity );
	}
	
	/**
	 * @Description 通过id查询司机信息 @
	 */
	public Map< String , Object > findById( int id )
	{
		String sql = "select d.id,d.vc_driver_name,d.vc_driver_tel,d.n_enable,d.vc_subno,"
		        + "d.vc_card,d.vc_sex,d.dt_birthday,d.vc_place,d.i_driver_type,"
		        + "d.vc_imgpath,d.vc_drivercar_li,d.vc_safe,d.i_score,c.vc_tag "
		        + "from t_driver d  left join t_driver_class c on d.i_driver_type=c.id"
		        + " where d.id=" + id;
		List< Map< String , Object >> drivers = jdbcTemp.queryForList( sql );
		if ( CollectionUtils.isNotEmpty( drivers ) )
		{
			return drivers.get( 0 );
		}
		return null;
	}
	
	/**
	 * 根据driverId查询车牌号,truckId
	 */
	public Map< String , Object > findCarNoById( int id )
	{
		String sql = "select t.id,t.vc_car_no from t_driver d,t_truck_driver t,t_truck_driver_link l"
		        + " where d.id=l.i_driver and t.id=l.i_truck and d.id=" + id;
		List< Map< String , Object >> trucks = jdbcTemp.queryForList( sql );
		if ( CollectionUtils.isEmpty( trucks ) )
		{
			return null;
		}
		else
		{
			Map< String , Object > truck = trucks.get( 0 );
			JSONArray array = JSONArray.fromObject( truck );
			System.out.println( "truck  json:" + array );
			String vcCarNo = null;
			if ( truck.get( "VC_CAR_NO" ) == null )
			{
				vcCarNo = "暂无";
			}
			else
			{
				vcCarNo = truck.get( "VC_CAR_NO" ).toString();
			}
			System.out.println( truck.get( "ID" ) + ":" + vcCarNo );
			return truck;
		}
	}
	
	/**
	 * 根据driverId删除link
	 */
	public void deleteLinkById( int driverId )
	{
		linkDao.deleteByProperty( "IDriverID" , driverId );
	}
	
	/**
	 * 获取所有的司机类型
	 */
	@Cacheable( value = "driverClassCache" )
	public Map< String , Object > findDriverClass()
	{
		HqlHelper hql = new HqlHelper( TDriverClass.class );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		return driverClassDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames
	 * @param values
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-25 上午11:52:52
	 */
	public List< TDriver > findByPropertys( String[] propertyNames , Object[] values )
	{
		// TODO Auto-generated method stub
		return driverDao.findByPropertys( propertyNames , values );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-25 下午2:07:45
	 */
	public Map< String , Object > findDriverSalaryCoffe( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iSalaryCofflisDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-25 下午2:17:40
	 */
	public TDriverSalaryCoefficient findDriverSalaryCoffById( int parseInt )
	{
		// TODO Auto-generated method stub
		return iSalaryCofflisDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSalaryCoefficient
	 * @author liuwu
	 * @create_date 2015-5-25 下午3:42:56
	 */
	public void saveOrUpdateSalaryCoefficient( TDriverSalaryCoefficient tSalaryCoefficient )
	{
		// TODO Auto-generated method stub
		iSalaryCofflisDao.saveOrUpdate( tSalaryCoefficient );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSalaryCoefficient
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-26 下午2:24:19
	 */
	public String checkSalaryCoefficient( TDriverSalaryCoefficient tSalaryCoefficient ,
	        String subno , TDriver tDriver )
	{
		String[] propertyNames = { "iDriverId" , "nEnable" , "vcSubno" };
		Object[] values = { tSalaryCoefficient.getiDriverId() ,
		        SystemConstants.SYS_ENABLE , subno };
		List< TDriverSalaryCoefficient > tDriverSalaryCoefficients = iSalaryCofflisDao
		        .findByPropertys( propertyNames , values );
		
		if ( tDriverSalaryCoefficients.size() > 0 )
		{
			return "司机:" + tDriver.getVcDriverName() + "工资系数已维护";
		}
		else
		{
			return "success";
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSalaryCoefficient
	 * @author liuwu
	 * @create_date 2015-5-26 下午2:46:59
	 */
	public void updateTSalaryCOfficient( TDriverSalaryCoefficient tSalaryCoefficient )
	{
		// TODO Auto-generated method stub
		iSalaryCofflisDao.updateCleanBefore( tSalaryCoefficient );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param driver
	 * @author liuwu
	 * @create_date 2015-5-27 下午2:24:32
	 */
	public void updateCleanBefore( TDriver driver )
	{
		// TODO Auto-generated method stub
		driverDao.updateCleanBefore( driver );
	}
	
	/**
	 * @Description: TODO(查找司机补贴)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-27 下午5:47:19
	 */
	public TDriverSubsides findDriverSalarySubsideById( int parseInt )
	{
		// TODO Auto-generated method stub
		return iSalarySubsideDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDriverSubsides
	 * @author liuwu
	 * @create_date 2015-5-28 上午9:32:49
	 */
	public void saveDriverSubsides( TDriverSubsides tDriverSubsides )
	{
		// TODO Auto-generated method stub
		iSalarySubsideDao.save( tDriverSubsides );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDriverSubsides
	 * @author liuwu
	 * @create_date 2015-5-28 上午9:35:05
	 */
	public void updateDriverSubsides( TDriverSubsides tDriverSubsides )
	{
		// TODO Auto-generated method stub
		iSalarySubsideDao.updateCleanBefore( tDriverSubsides );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-29 上午11:08:31
	 */
	public Map< String , Object > findDriverSalarySubside( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iSalarySubsideDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-29 上午11:55:29
	 */
	public Map< String , Object > getSpringSql( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return driverDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSubsideLinks
	 * @author liuwu
	 * @create_date 2015-6-2 上午9:57:47
	 */
	public void saveUpdateDriverSubsideLink( TDriverSubsideLinks tSubsideLinks )
	{
		// TODO Auto-generated method stub
		iDriverSubsideLinksDao.saveOrUpdate( tSubsideLinks );
	}
	
	public List< TDriverSubsideLinks > findTDriverSubsideLinksByDriverId( int driverId )
	{
		String[] propertyNames = { "iDriverId" , "nEnable" };
		Object[] values = { driverId , SystemConstants.SYS_ENABLE };
		return iDriverSubsideLinksDao.findByPropertys( propertyNames , values );
	}
	
	public void deleteDriverLinks( List< TDriverSubsideLinks > tLinks )
	{
		for ( TDriverSubsideLinks tDriverSubsideLinks : tLinks )
		{
			iDriverSubsideLinksDao.delete( tDriverSubsideLinks );
		}
		
	}
	
	/**
	 * @Description 解析图片路径
	 * @param map
	 * @return
	 */
	public Map< String , Object > parseUrl( Map< String , Object > map )
	{
		List< TDriver > list = ( List< TDriver > ) map.get( "rows" );
		String perUrl = staticService.getStringByParame( "driverUrl" );
		if ( CollectionUtils.isNotEmpty( list ) && StringUtils.isNotBlank( perUrl ) )
		{
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}
			for ( TDriver driver : list )
			{
				String imgPath = driver.getVcImgPath();
				if ( StringUtils.isNotBlank( imgPath ) )
				{
					String path = perUrl + imgPath;
					driver.setVcImgPath( path );
				}
				
			}
			map.put( "rows" , list );
		}
		return map;
	}
	
}
