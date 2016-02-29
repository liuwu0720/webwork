package com.clt.systemmanger.service;

import java.util.List;

import com.clt.systemmanger.model.TArchiveType;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TResourceArchive;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IResourceArchiveService
{
	public TResourceArchive get( Integer id );
	
	public void update( TResourceArchive entity );
	
	public void save( TResourceArchive entity );
	
	// public void saveByObject( String archivetype , String role );
	
	public void saveOrUpdate( TResourceArchive entity );
	
	public void delete( TResourceArchive entity );
	
	public void deleteByKey( Integer id );
	
	public List< TResourceArchive > loadAll();
	
	public void deleteByProperty( String propertyName , Object value );
	
	/**
	 * @Description: 根据档案类型id获得对应资源
	 * @param archiveId
	 * @return
	 * @author hjx
	 * @create_date 2014年7月14日 下午1:49:12
	 */
	public List< TResource > getByArchive( String archiveId );
	
	/**
	 * @Description: 根据资源和档案类型查找到 资源档案关联信息
	 * @param resource
	 * @param archiveType
	 * @return TResourceArchive 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午4:04:15
	 */
	public TResourceArchive getByResourceAndArchive( Integer resource ,
	        Integer archiveType );
	
	/**
	 * @Description: 根据资源和档案类型， 判断是否有存在 资源档案关联信息
	 * @param resource
	 * @param archiveType
	 * @return boolean 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午4:55:26
	 */
	public boolean isExist( Integer resource , Integer archiveType );
	
	/**
	 * @Description: 根据资源id和 档案类型ids 更新资源和档案的关联
	 * @param resource
	 *            资源id
	 * @param archiveTypeIds
	 *            多个档案类型id组合的字符串，以逗号分割 void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 上午11:42:28
	 */
	public void saveOrUpdateRAndT( Integer resource , String archiveTypeIds );
	
	/**
	 * @Description: 根据资源类型 id找到，资源所属类型信息
	 * @param resourceId
	 * @return List<TResourceArchive> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月29日 下午4:45:11
	 */
	public List< TArchiveType > getByResourceId( Integer resourceId );
	
}
