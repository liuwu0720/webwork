package com.clt.systemmanger.service;

import java.util.Map;

import com.clt.systemmanger.model.TAppVersion;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

public interface IAppVersionService
{
	public TAppVersion get( int id );
	
	public void save( TAppVersion entity );
	
	public void update( TAppVersion entity );
	
	public void saveOrUpdate( TAppVersion entity );
	
	public Map< String , Object > findByHelper( HqlHelper hql );
	
	public Map< String , Object > findAllVersions( Page page );
	
	// 获取最新的版本号
	public TAppVersion getLatestVersion();
	
	// url处理
	public String urlHandle( String url );
}
