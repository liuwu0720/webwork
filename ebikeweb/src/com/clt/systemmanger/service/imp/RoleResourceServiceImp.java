package com.clt.systemmanger.service.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.security.bean.CustomFilterInvocationSecurityMetadataSourceImpl;
import com.clt.sub.dao.IRoleCompanyDao;
import com.clt.sub.model.TRoleCompany;
import com.clt.systemmanger.dao.IResourceDao;
import com.clt.systemmanger.dao.IRoleResourceDao;
import com.clt.systemmanger.dao.IUserRoleDao;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TRoleResource;
import com.clt.systemmanger.model.TUserRole;
import com.clt.systemmanger.service.IResourceArchiveService;
import com.clt.systemmanger.service.IRoleResourceService;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: 角色资源管理
 * @author hjx
 * @date 2014年7月12日 下午4:28:00
 * @version V1.0
 */
@Service
public class RoleResourceServiceImp implements IRoleResourceService
{
	@Autowired
	private IRoleResourceDao rResourceDao;
	
	@Autowired
	private IResourceArchiveService rArchiveService;
	
	@Autowired
	private IResourceDao resourceDao;
	
	@Autowired
	private IUserRoleDao iUserRoleDao;
	
	@Autowired
	private IRoleCompanyDao iRoleCompanyDao;
	@Autowired
	private CustomFilterInvocationSecurityMetadataSourceImpl custom;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public TRoleResource get( Integer id )
	{
		return rResourceDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void update( TRoleResource entity )
	{
		rResourceDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void save( TRoleResource entity )
	{
		rResourceDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void saveOrUpdate( TRoleResource entity )
	{
		rResourceDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void delete( TRoleResource entity )
	{
		rResourceDao.delete( entity );
	}
	
	/**
	 * @Description: 删除角色，并把用户角色关联信息删除
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void deleteByKey( Integer id )
	{
		
		rResourceDao.deleteByKey( id );
	}
	
	/**
	 * @Description:通过角色id，获得该角色有具有的所有资源List
	 * @param roleid
	 * @return
	 * @author hjx
	 * @create_date 2014年7月14日 下午3:10:50
	 */
	public List< TResource > getByRoleid( String roleid )
	{
		
		List< TRoleResource > rResources = rResourceDao.findByProperty( "IRoleId" ,
		        Integer.parseInt( roleid ) );
		List< TResource > resources = new ArrayList< TResource >();
		if ( CollectionUtils.isNotEmpty( rResources ) )
		{
			for ( TRoleResource rr : rResources )
			{
				resources.add( resourceDao.get( rr.getIResourceId() ) );
			}
			if ( CollectionUtils.isNotEmpty( resources ) )
				return resources;
		}
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TRoleResource > loadAll()
	{
		return rResourceDao.loadAll();
	}
	
	/**
	 * @Description: 根据档案id获得 档案下的资源list,把角色有具有的资源 把 ishave设置为1，如不具有该资源则设置为0
	 * @param list
	 * @return
	 * @author hjx
	 * @create_date 2014年7月14日 下午2:59:31
	 */
	public String getByRoleHave( String archiveId , String roleid )
	{
		List< TResource > list = rArchiveService.getByArchive( archiveId );
		List< TResource > handlList = new ArrayList< TResource >();
		// 通过角色id，获得该角色有具有的所有资源List
		List< TResource > roleResources = this.getByRoleid( roleid );
		JSONArray arr = new JSONArray();
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			for ( TResource resource : list )
			{
				JSONObject obj = new JSONObject();
				obj.element( "id" , resource.getId() );
				obj.element( "pId" , resource.getIParent() );
				obj.element( "name" , resource.getVcResourceName() );
				obj.element( "t" , resource.getVcDesc() );
				if ( CollectionUtils.isNotEmpty( roleResources ) )
				{
					if ( roleResources.contains( resource ) )
					{
						obj.element( "checked" , true );
					}
					else
					{
						obj.element( "checked" , false );
					}
				}
				else
				{
					obj.element( "checked" , false );
				}
				arr.add( obj );
			}
		}
		return arr.toString();
	}
	
	/**
	 * @Description: 档案id下所有资源中，和角色id有关联的资源
	 * @param roleid
	 * @param archiveId
	 * @author hjx
	 * @create_date 2014年7月14日 下午3:39:01
	 */
	public void delByRoleId( String roleid , String archiveId )
	{
		List< TResource > list = rArchiveService.getByArchive( archiveId );
		if ( list != null )
		{
			for ( TResource resource : list )
			{
				List< TRoleResource > rresources = rResourceDao.findByPropertys(
				        new String[] { "IRoleId" , "IResourceId" } , new Object[] {
				                Integer.parseInt( roleid ) , resource.getId() } );
				if ( CollectionUtils.isNotEmpty( rresources ) )
				{
					rResourceDao.deleteAll( rresources );
				}
			}
		}
	}
	
	/**
	 * @Description: 批量给角色添加关联资源
	 * @param roleid
	 * @param resourceids
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午3:41:15
	 */
	public void saveRoleResources( String roleid , List< String > resourceids )
	{
		for ( String id : resourceids )
		{
			if ( ! this.isExist( roleid , id ) )
			{
				TRoleResource entity = new TRoleResource();
				entity.setIResourceId( Integer.parseInt( id ) );
				entity.setIRoleId( Integer.parseInt( roleid ) );
				rResourceDao.save( entity );
				
				custom.updateResourceRole( Integer.parseInt( id ) );// 更新某个资源对应的权限
			}
		}
	}
	
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
	        List< String > resourceids )
	{
		
		this.delByRoleId( roleid , archiveId );
		if ( CollectionUtils.isNotEmpty( resourceids ) )
		{
			this.saveRoleResources( roleid , resourceids );
		}
		this.delResourceByAdmin( roleid , archiveId , resourceids );
	}
	
	/**
	 * @Description: TODO(删除二级管理员下属员工角色的资源)
	 * @param roleid
	 * @param archiveId
	 * @param resourceids
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-14 下午1:59:04
	 */
	private void delResourceByAdmin( String roleid , String archiveId ,
	        List< String > resourceids )
	{
		List< TRoleResource > tRoleResources = rResourceDao.findByProperty( "IRoleId" ,
		        Integer.parseInt( roleid ) );
		List< TResource > resources1 = getByRoleid( roleid );// 当前角色的所有资源
		
		// 1：找出拥有当前角色的二级管理员
		List< TUserRole > tUserRoles = iUserRoleDao.findByProperty( "IRole" ,
		        Integer.parseInt( roleid ) );
		List< TRoleCompany > tRoleCompanieList = new ArrayList< TRoleCompany >();// 二级管理员所创建的员工角色
		// 2:找出这个二级管理员所创建的员工角色
		for ( TUserRole tUserRole : tUserRoles )
		{
			if ( tUserRole.getIUser() != null )
			{
				List< TRoleCompany > tRoleCompanies = iRoleCompanyDao
				        .findByPropertys(
				                new String[] { "iUserId" , "nEnable" } ,
				                new Object[] { tUserRole.getIUser() ,
				                        SystemConstants.SYS_ENABLE } );
				// .findByProperty( "iUserId" , tUserRole.getIUser() );
				tRoleCompanieList.addAll( tRoleCompanies );
			}

		}
		// 3:找出所有员工角色所拥有的资源
		List< TResource > staffList = new ArrayList< TResource >();
		for ( TRoleCompany tRoleCompany : tRoleCompanieList )
		{
			List< TResource > tRoleResources2 = getByRoleid( tRoleCompany.getRoleId()
			        + "" );// 当前员工角色的所有资源
			if ( tRoleResources2 != null )
			{
				staffList.addAll( tRoleResources2 );
			}
			
		}
		for ( TResource resource : staffList )
		{
			if ( ! resources1.contains( resource ) )
			{
				for ( TRoleCompany tRoleCompany : tRoleCompanieList )
				{
					List< TRoleResource > tResources = rResourceDao.findByPropertys(
					        new String[] { "IRoleId" , "IResourceId" } , new Object[] {
					                tRoleCompany.getRoleId() , resource.getId() } );
					rResourceDao.deleteAll( tResources );
				}
			}
		}
	}
	
	/**
	 * @Description: TODO(分供方管理员对自己的员工访问资源的控制)
	 * @param typeId
	 * @param roleId
	 * @param resourceList
	 * @author liuwu
	 * @create_date 2015-1-13 下午4:00:34
	 */
	public void updateRoleResourcesForStaff( String roleId , List< String > resourceList )
	{
		// 第一步：根据角色删除对应的资源
		List< TRoleResource > rresources = rResourceDao.findByProperty( "IRoleId" ,
		        Integer.parseInt( roleId ) );
		if ( rresources != null )
		{
			rResourceDao.deleteAll( rresources );
		}
		// 第二步：保存传过来的资源
		if ( CollectionUtils.isNotEmpty( resourceList ) )
		{
			// System.out.println( resourceList.size() );
			this.saveRoleResources( roleId , resourceList );
		}
	}
	
	/**
	 * @Description: 根据角色id和资源id，判断是否存在角色资源关联信息
	 * @param roleid
	 * @param resourceid
	 * @return boolean 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午4:47:26
	 */
	public boolean isExist( String roleid , String resourceid )
	{
		List< TRoleResource > list = rResourceDao.findByPropertys( new String[] {
		        "IRoleId" , "IResourceId" } , new Object[] { Integer.parseInt( roleid ) ,
		        Integer.parseInt( resourceid ) } );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return true;
		}
		return false;
	}
	
}
