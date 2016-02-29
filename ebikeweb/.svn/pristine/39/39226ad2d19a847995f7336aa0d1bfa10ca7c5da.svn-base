package com.clt.systemmanger.service;

import java.util.List;

import com.clt.systemmanger.model.TResource;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IResourceService
{
	public TResource get( Integer id );
	
	public void update( TResource entity );
	
	public void save( TResource entity );
	
	public void saveOrUpdate( TResource entity );
	
	public void delete( TResource entity );
	
	public void deleteByKey( Integer id );
	
	public List< TResource > loadAll();
	
	public List< TResource > loadAllByEnable();
	
	/**
	 * @Description: 加载资源有效，且URL不为空的资源
	 * @return List<TResource> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月15日 下午1:43:28
	 */
	public List< TResource > loadByEnableAndUrl();
	
	/**
	 * @Description: 保存资源，并把保存 资源档案关联表
	 * @param entity
	 * @param archiveTypeIds
	 * @author hjx
	 * @create_date 2014年7月14日 下午2:26:31
	 */
	public void saveOrUpdateRA( TResource entity , String archiveTypeIds );
	
	/**
	 * @Description: 更新时保存资源，并把更新 资源档案关联表
	 * @param entity
	 *            资源实体类
	 * @param archiveTypeIdOld
	 *            原来的 资源档案id
	 * @param archiveTypeId
	 *            更新后 新的资源档案id void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午4:00:06
	 */
	public void update( TResource entity , String archiveTypeIdOld , String archiveTypeId );
	
	/**
	 * @Description: 获得json树
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午2:24:37
	 */
	public String getJsonTree( List< TResource > list );
	
	/**
	 * @Description: 
	 *               根据url获得对应资源名称以及资源的上一级资源的名称（不断递归上去，直到没有上一级为止），如果没有找到资源，则把url的值返回
	 *               。
	 * @param Url
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年12月10日 下午4:58:04
	 */
	public String getResourceDescByUrl( String Url );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return List<TResource> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-27 下午2:40:20
	 */
	public List< TResource > getAll();
	
}
