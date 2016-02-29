package com.clt.systemmanger.service.imp;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IRoleDao;
import com.clt.systemmanger.dao.IUserRoleDao;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserRole;
import com.clt.systemmanger.service.IUserRoleService;
import com.clt.systemmanger.service.IUserService;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:28:00
 * @version V1.0
 */
@Service
public class UserRoleServiceImp implements IUserRoleService
{
	@Autowired
	private IUserRoleDao uroleDao;
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IUserService userService;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public TUserRole get( Integer id )
	{
		return uroleDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void update( TUserRole entity )
	{
		uroleDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void save( TUserRole entity )
	{
		uroleDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void saveOrUpdate( TUserRole entity )
	{
		uroleDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void delete( TUserRole entity )
	{
		uroleDao.delete( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void deleteByKey( Integer id )
	{
		uroleDao.deleteByKey( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TUserRole > loadAll()
	{
		return uroleDao.loadAll();
	}
	
	/**
	 * @Description: 删除用户下所有关联角色
	 * @param userId
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 上午11:32:02
	 */
	public void delByUserId( String userId )
	{
		uroleDao.deleteByProperty( "IUser" , Integer.parseInt( userId ) );
	}
	
	/**
	 * @Description: 批量添加用户的角色
	 * @param userId
	 * @param roleids
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 上午11:33:15
	 */
	public void addUserRoles( String userId , List< String > roleids )
	{
		Integer uid = Integer.parseInt( userId );
		
		for ( String rolesid : roleids )
		{
			Integer rid = Integer.parseInt( rolesid );
			if ( ! this.isExits( uid , rid ) )
			{
				TUserRole ur = new TUserRole();
				ur.setIUser( uid );
				ur.setIRole( rid );
				uroleDao.save( ur );
			}
			
		}
	}
	
	/**
	 * @Description: 更新用户的所有角色信息
	 * @param userId
	 * @param roleids
	 * @author hjx
	 * @create_date 2014年7月14日 下午1:30:33
	 */
	public void updateUserRoles( String userId , List< String > roleids )
	{
		this.delByUserId( userId );
		if ( CollectionUtils.isNotEmpty( roleids ) )
		{
			this.addUserRoles( userId , roleids );
		}
	}
	
	/**
	 * @Description: 获得所有角色，并标注出该用户具有的角色 该用户具有的角色 Ishave=1
	 * @param userId
	 * @return List<TRole> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午5:33:17
	 */
	public String getAllAndUserHaveRole( String userId )
	{
		List< TRole > allList = roleDao.loadAllByEnable();
		TUser user = userService.getByid( userId );
		List< TRole > uRoles = new ArrayList< TRole >();
		if ( null != user )
		{
			uRoles = userService.getRoleByUser( user );
		}
		JSONArray arr = new JSONArray();
		for ( TRole r : allList )
		{
			JSONObject obj = new JSONObject();
			
			obj.element( "id" , r.getId() );
			obj.element( "pId" , 0 );
			obj.element( "name" , r.getVcRoleName() );
			obj.element( "t" , r.getVcDesc() );
			if ( CollectionUtils.isNotEmpty( uRoles ) )
			{
				if ( uRoles.contains( r ) )
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
				obj.put( "checked" , false );
			}
			arr.add( obj );
		}
		return arr.toString();
	}
	
	/**
	 * @Description: 判断该用户下，具备该角色
	 * @param user
	 * @param role
	 * @return boolean 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午5:36:55
	 */
	public boolean isExits( Integer user , Integer role )
	{
		List< TUserRole > list = uroleDao.findByPropertys( new String[] { "IUser" ,
		        "IRole" } , new Object[] { user , role } );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * @Description: 根据用户id 获取该用户所有的角色信息
	 * @param userId
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-28 下午3:26:58
	 */
	public List< TRole > getAllRoleByUserID( String userId )
	{
		
		TUser user = userService.getByid( userId );
		List< TRole > uRoles = new ArrayList< TRole >();
		if ( null != user )
		{
			uRoles = userService.getRoleByUser( user );
		}
		
		return uRoles;
	}
}
