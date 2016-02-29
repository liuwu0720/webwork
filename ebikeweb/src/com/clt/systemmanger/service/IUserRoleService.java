package com.clt.systemmanger.service;

import java.util.List;

import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUserRole;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IUserRoleService
{
	public TUserRole get( Integer id );
	
	public void update( TUserRole entity );
	
	public void save( TUserRole entity );
	
	public void saveOrUpdate( TUserRole entity );
	
	public void delete( TUserRole entity );
	
	public void deleteByKey( Integer id );
	
	public List< TUserRole > loadAll();
	
	public void delByUserId( String userId );
	
	public void addUserRoles( String userId , List< String > roleids );
	
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
	public void updateUserRoles( String userId , List< String > roleids );
	
	/**
	 * @Description: 获得所有角色，并标注出该用户具有的角色 该用户具有的角色 Ishave=1
	 * @param userId
	 * @return List<TRole> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午5:33:17
	 */
	public String getAllAndUserHaveRole( String userId );
	
	/**
	 * @Description: 根据用户id 获取该用户所有的角色
	 * @param userId
	 * @return List<TRole> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午5:33:17
	 */
	public List< TRole > getAllRoleByUserID( String userId );
	
	/**
	 * @Description: 判断该用户下，具备该角色
	 * @param user
	 * @param role
	 * @return boolean 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午5:36:55
	 */
	public boolean isExits( Integer user , Integer role );
	
}
