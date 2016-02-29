package com.clt.systemmanger.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IArchiveTypeDao;
import com.clt.systemmanger.dao.IResourceArchiveDao;
import com.clt.systemmanger.dao.IResourceDao;
import com.clt.systemmanger.model.TArchiveType;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TResourceArchive;
import com.clt.systemmanger.service.IResourceArchiveService;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:28:00
 * @version V1.0
 */
@Service
public class ResourceArchiveServiceImp implements IResourceArchiveService
{
	@Autowired
	private IResourceArchiveDao rArchiveDao;
	
	@Autowired
	private IResourceDao resourceDao;
	
	@Autowired
	private IArchiveTypeDao atypeDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public TResourceArchive get( Integer id )
	{
		return rArchiveDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void update( TResourceArchive entity )
	{
		rArchiveDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void save( TResourceArchive entity )
	{
		rArchiveDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void saveOrUpdate( TResourceArchive entity )
	{
		rArchiveDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void delete( TResourceArchive entity )
	{
		rArchiveDao.delete( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void deleteByKey( Integer id )
	{
		rArchiveDao.deleteByKey( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TResourceArchive > loadAll()
	{
		return rArchiveDao.loadAll();
	}
	
	/**
	 * @Description: 根据档案类型id获得对应资源
	 * @param archiveId
	 * @return
	 * @author hjx
	 * @create_date 2014年7月14日 下午1:49:12
	 */
	public List< TResource > getByArchive( String archiveId )
	{
		List< TResourceArchive > list = rArchiveDao.findByProperty( "IArchiveType" ,
		        Integer.parseInt( archiveId ) );
		List< TResource > resources = new ArrayList< TResource >();
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			for ( TResourceArchive tResourceArchive : list )
			{
				resources.add( resourceDao.get( tResourceArchive.getIResource() ) );
			}
			if ( CollectionUtils.isNotEmpty( resources ) )
				return resources;
		}
		else
		{
			
		}
		
		return null;
	}
	
	/**
	 * @Description: 根据资源和档案类型查找到 资源档案关联信息
	 * @param resource
	 * @param archiveType
	 * @return TResourceArchive 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午4:04:15
	 */
	public TResourceArchive getByResourceAndArchive( Integer resource ,
	        Integer archiveType )
	{
		List< TResourceArchive > list = rArchiveDao.findByPropertys( new String[] {
		        "IArchiveType" , "IResource" } , new Object[] { resource , archiveType } );
		
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return list.get( 0 );
		}
		return null;
	}
	
	/**
	 * @Description: 根据资源和档案类型， 判断是否有存在 资源档案关联信息
	 * @param resource
	 * @param archiveType
	 * @return boolean 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午4:55:26
	 */
	public boolean isExist( Integer resource , Integer archiveType )
	{
		List< TResourceArchive > list = rArchiveDao.findByPropertys( new String[] {
		        "IResource" , "IArchiveType" } , new Object[] { resource , archiveType } );
		
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 根据资源id和 档案类型ids 更新资源和档案的关联
	 * @param resource
	 *            资源id
	 * @param archiveTypeIds
	 *            多个档案类型id组合的字符串，以逗号分割 void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 上午11:42:28
	 */
	public void saveOrUpdateRAndT( Integer resource , String archiveTypeIds )
	{
		rArchiveDao.deleteByProperty( "IResource" , resource );
		if ( StringUtils.isNotBlank( archiveTypeIds ) )
		{
			String[] arr = archiveTypeIds.split( "," );
			for ( String id : arr )
			{
				TResourceArchive ra = new TResourceArchive();
				ra.setIArchiveType( Integer.parseInt( id ) );
				ra.setIResource( resource );
				ra.setNEnable( 0 );
				rArchiveDao.save( ra );
			}
		}
		
	}
	
	public void deleteByProperty( String propertyName , Object value )
	{
		rArchiveDao.deleteByProperty( propertyName , value );
	}
	
	/**
	 * @Description: 根据资源类型 id找到，资源所属类型信息
	 * @param resourceId
	 * @return List<TResourceArchive> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月29日 下午4:45:11
	 */
	public List< TArchiveType > getByResourceId( Integer resourceId )
	{
		List< TResourceArchive > ralist = rArchiveDao.findByPropertys( new String[] {
		        "NEnable" , "IResource" } , new Object[] { 0 , resourceId } );
		if ( CollectionUtils.isNotEmpty( ralist ) )
		{
			List< TArchiveType > atypeList = new ArrayList< TArchiveType >();
			for ( TResourceArchive ra : ralist )
			{
				atypeList.add( atypeDao.get( ra.getIArchiveType() ) );
			}
			return atypeList;
		}
		return null;
	}
}
