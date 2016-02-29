package com.clt.systemmanger.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IRoleCompanyDao;
import com.clt.sub.model.TRoleCompany;
import com.clt.systemmanger.dao.IRoleDao;
import com.clt.systemmanger.dao.IRoleResourceDao;
import com.clt.systemmanger.dao.IUserRoleDao;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUserRole;
import com.clt.systemmanger.service.IRoleService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:28:00
 * @version V1.0
 */
@Service
public class RoleServiceImp implements IRoleService
{
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IRoleResourceDao rrDao;
	@Autowired
	private IUserRoleDao iUserRoleDao;
	@Autowired
	private IRoleCompanyDao iRoleCompanyDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public TRole get( Integer id )
	{
		return roleDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void update( TRole entity )
	{
		roleDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void save( TRole entity )
	{
		String start = "ROLE_";
		String vcRole = entity.getVcRole();
		if ( ! vcRole.startsWith( start )
		        && ! vcRole.toUpperCase().startsWith( start ) )
		{
			entity.setVcRole( start + vcRole );
		}
		roleDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void saveOrUpdate( TRole entity )
	{
		String start = "ROLE_";
		String vcRole = entity.getVcRole();
		if ( ! vcRole.startsWith( start )
		        && ! vcRole.toUpperCase().startsWith( start ) )
		{
			entity.setVcRole( start + vcRole );
		}
		roleDao.saveOrUpdate( entity );
		// 1; 如果角色设为无效，则把该角色下面的用户都设为无效
		List< TUserRole > tUserRoles = iUserRoleDao.findByPropertys(
		        new String[] { "IRole" , "IEnable" } ,
		        new Object[] { entity.getId() , SystemConstants.SYS_ENABLE } );
		for ( TUserRole tUserRole : tUserRoles )
		{
			tUserRole.setIEnable( entity.getNEnable() );
			iUserRoleDao.saveOrUpdate( tUserRole );
		}
		// 2:找出下面的用户有没有创建自己的员工角色，如果有则把所有员工角色都设为无效
		List< TRoleCompany > tRoleCompanies = new ArrayList< TRoleCompany >();
		for ( TUserRole tUserRole : tUserRoles )
		{
			List< TRoleCompany > tList = iRoleCompanyDao
			        .findByPropertys( new String[] { "iUserId" , "nEnable" } ,
			                new Object[] { tUserRole.getIUser() ,
			                        SystemConstants.SYS_ENABLE } );
			tRoleCompanies.addAll( tList );
		}
		List< TUserRole > tUserRole_staff = new ArrayList< TUserRole >();
		for ( TRoleCompany tRoleCompany : tRoleCompanies )
		{
			List< TUserRole > tList = iUserRoleDao.findByPropertys(
			        new String[] { "IRole" , "nEnable" } , new Object[] {
			                tRoleCompany.getRoleId() ,
			                SystemConstants.SYS_ENABLE } );
			tUserRole_staff.addAll( tList );
		}
		if ( tUserRole_staff.size() > 0 )
		{
			for ( TUserRole tUserRole : tUserRole_staff )
			{
				tUserRole.setIEnable( entity.getNEnable() );
				iUserRoleDao.saveOrUpdate( tUserRole );
			}
		}

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void delete( TRole entity )
	{
		roleDao.delete( entity );
	}
	
	/**
	 * @Description: 删除角色，并把角色资源删除
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void deleteByKey( Integer id )
	{
		rrDao.deleteByProperty( "IRoleId" , id );
		roleDao.deleteByKey( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TRole > loadAll()
	{
		return roleDao.loadAll();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param pageSize
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TRole > findAll( Page page )
	{
		return roleDao.findAll( page );
	}
	
	/**
	 * @Description: 加载有效的角色
	 * @return List<TRole> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午6:41:03
	 */
	public List< TRole > loadAllByEnable()
	{
		return roleDao.loadAllByEnable();
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return roleDao.findAllByHqlHelp( helper );
	}
	
	public List< TRole > find( String hql )
	{
		// TODO Auto-generated method stub
		return roleDao.find( hql );
	}
	
	/**
	 * @Description: TODO((查出拥有该角色的用户
	 * @param role
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-14 下午4:36:24
	 */
	public List< TUserRole > getStaffRole( TRole role )
	{
		List< TUserRole > tUserRoles = iUserRoleDao.findByPropertys(
		        new String[] { "IRole" , "IEnable" } ,
		        new Object[] { role.getId() , SystemConstants.SYS_ENABLE } );

		return tUserRoles;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-26 下午5:17:56
	 */
	public List< TRole > findDriverRole()
	{
		// TODO Auto-generated method stub
		return roleDao.findByPropertys( new String[] { "vcRole" , "NEnable" } ,
		        new Object[] { "ROLE_DRIVER" , SystemConstants.SYS_ENABLE } );
	}
}
