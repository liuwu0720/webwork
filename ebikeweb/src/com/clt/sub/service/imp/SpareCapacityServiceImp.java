package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ILimitCheckDao;
import com.clt.sub.dao.ISpareCapacityDao;
import com.clt.sub.model.TLimitCheck;
import com.clt.sub.model.TSpareCapacity;
import com.clt.sub.service.ISpareCapacityService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IIntegalService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.sub.service.imp
 * @Description: 发布空闲运力服务类
 * @author hjx
 * @date 2014年10月10日 上午10:08:33
 * @version V1.0
 */
@Service
public class SpareCapacityServiceImp implements ISpareCapacityService
{
	@Autowired
	private ISpareCapacityDao spareCapacityDao;
	@Autowired
	IUserService userService;
	@Autowired
	IIntegalService integalService;
	@Autowired
	ILimitCheckDao limitCheckDao;
	@Autowired
	JdbcTemplate jdbcDao;
	
	/**
	 * @Description: 保存或者更新 空闲运力
	 * @param spareCapacity
	 * @author hjx
	 * @create_date 2014年10月10日 上午10:08:33
	 */
	public void saveOrUpdate( TSpareCapacity spareCapacity )
	{
		spareCapacityDao.saveOrUpdate( spareCapacity );
	}
	
	/**
	 * @Description:根据id获得空闲运力详情,如果该空闲运力存在并且没被逻辑删除，则返回具体详情，否则返回null
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年10月10日 上午10:08:33
	 */
	public TSpareCapacity getById( int id )
	{
		TSpareCapacity spareCapacity = spareCapacityDao.get( id );
		if ( null != spareCapacity
		        && spareCapacity.getNEnable() == SystemConstants.SYS_ENABLE )
		{
			return spareCapacity;
		}
		return null;
	}
	
	public List< TSpareCapacity > getByPropertys( String[] propertyNames , Object[] values )
	{
		return spareCapacityDao.findByPropertys( propertyNames , values );
	}
	
	public List< TSpareCapacity > getByProperty( String propertyName , Object value )
	{
		return spareCapacityDao.findByProperty( propertyName , value );
	}
	
	/**
	 * @Description: 根据id，逻辑删除空闲运力
	 * @param id
	 * @author hjx
	 * @create_date 2014年10月10日 上午10:08:33
	 */
	public void delById( int id )
	{
		// TODO Auto-generated method stub
		TSpareCapacity spareCapacity = spareCapacityDao.get( id );
		if ( null != spareCapacity )
		{
			spareCapacity.setNEnable( SystemConstants.SYS_DISABLE );
			spareCapacityDao.update( spareCapacity );
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return
	 * @author hjx
	 * @create_date 2014年10月10日 上午10:08:33
	 */
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return spareCapacityDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: 通过用户id和空闲运力信息id 获得联系方式，并扣除积分
	 * @param userId
	 * @param spareId
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年10月10日 下午6:32:23
	 */
	public String getLinkBy( int userId , int spareId )
	{
		TSpareCapacity spareCapacity = spareCapacityDao.get( spareId );
		// TODO hjx 扣除积分
		return spareCapacity.getVcLink();
	}
	
	public List< TSpareCapacity > findByHql( String hql )
	{
		
		return spareCapacityDao.find( hql );
	}
	
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		
		return spareCapacityDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param user
	 * @param paramType
	 * @param integalID
	 * @author chenbin
	 * @create_date 2014-12-17 下午1:48:32
	 */
	public void saveAndIntegalCut( TSpareCapacity spare , TUser user , String paramType ,
	        int integalID )
	{
		// 修改用户积分
		userService.updateUserIntegal( user , paramType , integalID );
		// 添加积分历史记录
		integalService.saveTIntegral( user , paramType , integalID );
		TLimitCheck limt = new TLimitCheck();
		limt.setDtAdd( new Date() );
		limt.setISpareCapacity( spare.getId() );
		limt.setIUser( user.getId() );
		limitCheckDao.save( limt );
	}
	
	/**
	 * 通过用户id获得该用户可以查看的空闲运力联系方式的id
	 * 
	 * @return
	 */
	public List< Integer > getLimitByUserId( int userId )
	{
		List< TLimitCheck > limits = limitCheckDao.findByProperty( "IUser" , userId );
		if ( CollectionUtils.isNotEmpty( limits ) )
		{
			List< Integer > spareIds = new ArrayList< Integer >();
			for ( TLimitCheck limit : limits )
			{
				spareIds.add( limit.getISpareCapacity() );
			}
			return spareIds;
		}
		return null;
	}
	
	/**
	 * 用于app通过id获取空闲运力详情 包含SpareCapacity全部字段和车型名称
	 */
	public Map< String , Object > getDetailByIdApp( int spareId )
	{
		String sql = "select id, n_enable, dt_add, i_user, vc_user_name,"
		        + " dt_begin, dt_end, n_space, vc_start_address, "
		        + "vc_end_address, n_length, n_width, n_height, "
		        + "vc_desc, vc_link, vc_price, n_weight, i_province_start,"
		        + " i_city_start, i_province_end, i_city_end, i_truck_type, "
		        + "vc_province_start, vc_city_start, vc_province_end, vc_city_end, "
		        + "vc_long_start, vc_lat_start, vc_long_end, vc_lat_end, "
		        + "vc_truck_name " + "from t_spare_capacity " + "   where  id=" + spareId;
		Map< String , Object > map = jdbcDao.queryForMap( sql );
		return map;
	}
}
