package com.clt.systemmanger.service;

import java.util.List;

import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TRoleResource;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色资源关联管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IRoleResourceService
{
	public TRoleResource get( Integer id );
	
	public void update( TRoleResource entity );
	
	public void save( TRoleResource entity );
	
	public void saveOrUpdate( TRoleResource entity );
	
	public void delete( TRoleResource entity );
	
	public void deleteByKey( Integer id );
	
	public List< TRoleResource > loadAll();
	
	public List< TResource > getByRoleid( String roleid );
	
	/**
	 * @Description: 根据档案id获得 档案下的资源list,把角色有具有的资源 把 ishave设置为1，如不具有该资源则设置为0
	 * @param list
	 * @return
	 * @author hjx
	 * @create_date 2014年7月14日 下午2:59:31
	 */
	public String getByRoleHave( String archiveId , String roleid );
	
	/**
	 * @Description: 档案id下所有资源中，和角色id有关联的资源
	 * @param roleid
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午3:32:44
	 */
	public void delByRoleId( String roleid , String archiveId );
	
	/**
	 * @Description: 批量给角色添加关联资源
	 * @param roleid
	 * @param resourceids
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午3:41:15
	 */
	public void saveRoleResources( String roleid , List< String > resourceids );
	
	/**
	 * @Description: 批量更新角色在某个档案类型对应资源的关联关系
	 * @param archiveId
	 *            档案类型id
	 * @param roleid
	 *            角色id
	 * @param resourceids
	 *            要关联的资源idList void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午3:46:36
	 */
	public void updateRoleResources( String archiveId , String roleid ,
	        List< String > resourceids );
	
	/**
	 * @Description: 根据角色id和资源id，判断是否存在角色资源关联信息
	 * @param roleid
	 * @param resourceid
	 * @return boolean 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午4:47:26
	 */
	public boolean isExist( String roleid , String resourceid );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param typeId
	 * @param roleId
	 * @param resourceList
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-13 下午4:00:16
	 */
	public void updateRoleResourcesForStaff( String roleId ,
	        List< String > resourceList );
	
	

}
