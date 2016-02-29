package com.clt.systemmanger.service;

import java.util.Map;

import com.clt.systemmanger.model.TUserGps;
import com.clt.util.HqlHelper;

/**
 * 用户定位服务类 用户gps服务类
 * 
 * @author hjx86
 * 
 */
public interface IUserGpsService
{
	/**
	 * 保存用户定位信息，如果没有hash值，进行计算赋值
	 * 
	 * @param gps
	 */
	public void save( TUserGps gps );
	
	/**
	 * 通过用户id，获得该用户最新的gps信息
	 * 
	 * @param userId
	 * @return
	 */
	public TUserGps getGpsByUserid( int userId );
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
}
