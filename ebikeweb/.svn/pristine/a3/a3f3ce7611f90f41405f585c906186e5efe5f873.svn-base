package com.clt.systemmanger.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.common.Geohash;
import com.clt.systemmanger.dao.IUserGpsDao;
import com.clt.systemmanger.model.TUserGps;
import com.clt.systemmanger.service.IUserGpsService;
import com.clt.util.HqlHelper;

@Service
public class UserGpsServiceImp implements IUserGpsService
{
	@Autowired
	private IUserGpsDao gpsDao;
	
	private Geohash geoHash;
	
	/**
	 * 保存用户定位信息，如果没有hash值，进行计算赋值
	 * 
	 * @param gps
	 */
	public void save( TUserGps gps )
	{
		if ( StringUtils.isEmpty( gps.getVcHash() ) )
		{
			// 通过经纬度 数据，计算hash值
			double vcLong = Double.parseDouble( gps.getVcLong() );// 获取经度并解析
			double vcLat = Double.parseDouble( gps.getVcLat() );// 获取纬度并解析
			Geohash geoHash = new Geohash();
			String vcHash = geoHash.encode( vcLat , vcLong );
			gps.setVcHash( vcHash );
		}
		gpsDao.save( gps );
	}
	
	/**
	 * 通过用户id，获得该用户最新的gps信息
	 * 
	 * @param userId
	 * @return
	 */
	public TUserGps getGpsByUserid( int userId )
	{
		String hql = "from TUserGps t where t.IUser=" + userId + " order by t.dtAdd desc";
		List< TUserGps > gpsList = gpsDao.find( hql );
		if ( CollectionUtils.isNotEmpty( gpsList ) )
		{
			return gpsList.get( 0 );
		}
		return null;
	}
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		return gpsDao.findAllByHqlHelp( hql );
	}
}
