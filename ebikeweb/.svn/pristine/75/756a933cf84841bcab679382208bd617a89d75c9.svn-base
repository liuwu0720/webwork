package com.clt.systemmanger.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IAppVersionDao;
import com.clt.systemmanger.model.TAppVersion;
import com.clt.systemmanger.service.IAppVersionService;
import com.clt.systemmanger.service.IStaticService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class AppVersionServiceImp implements IAppVersionService
{
	@Autowired
	private IAppVersionDao versionDao;
	@Autowired
	private JdbcTemplate jdbcDao;
	@Autowired
	private IStaticService staticService;
	
	public TAppVersion get( int id )
	{
		return versionDao.get( id );
	}
	
	public void save( TAppVersion entity )
	{
		versionDao.save( entity );
	}
	
	public void update( TAppVersion entity )
	{
		versionDao.update( entity );
	}
	
	public void saveOrUpdate( TAppVersion entity )
	{
		versionDao.saveOrUpdate( entity );
	}
	
	public Map< String , Object > findByHelper( HqlHelper hql )
	{
		return versionDao.findAllByHqlHelp( hql );
	}
	
	public Map< String , Object > findAllVersions( Page page )
	{
		HqlHelper hql = new HqlHelper( TAppVersion.class );
		hql.setQueryPage( page );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		Map< String , Object > result = versionDao.findAllByHqlHelp( hql );
		return result;
	}
	
	// 获取最新的版本
	public TAppVersion getLatestVersion()
	{
		HqlHelper hql = new HqlHelper( TAppVersion.class );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		hql.addOrderBy( "dtAdd" , "desc" );
		Map< String , Object > result = versionDao.findAllByHqlHelp( hql );
		List< TAppVersion > versions = ( List< TAppVersion > ) result.get( "rows" );
		if ( CollectionUtils.isNotEmpty( versions ) )
		{
			return versions.get( 0 );
		}
		else
		{
			return null;
		}
	}
	
	// url处理
	public String urlHandle( String url )
	{
		String rootPath = staticService.getStringByParame( "appVersionUrl" );
		if ( ! rootPath.endsWith( "/" ) )
		{
			rootPath += "/";
		}
		url = rootPath + url;
		return url;
	}
}
