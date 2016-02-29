package com.clt.systemmanger.service;

import java.util.List;
import java.util.Map;

import com.clt.systemmanger.model.TApplyResource;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUser;
import com.clt.util.DataGridModel;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

public interface IUserService
{
	/**
	 * 根据账号名获得 对应用户
	 * 
	 * @param account
	 * @return
	 */
	public TUser getByAccount( String account );
	
	public void updateCleanBefore( TUser user );
	
	public TUser getByPhone( String phone );
	
	/**
	 * 根据用户id，删除用户
	 * 
	 * @param id
	 */
	public void delUserbyId( Integer id );
	
	/**
	 * 保存User
	 * 
	 * @param user
	 */
	public void saveUser( TUser user );
	
	/**
	 * 更新User
	 * 
	 * @param user
	 */
	public void updateUser( TUser user );
	
	public void delUser( TUser user );
	
	public List< TRole > getRoleByUser( TUser user );
	
	public TUser getById( Integer id );
	
	public TUser getByid( String userId );
	
	/**
	 * @Description: 获得有效的用户
	 * @return List<TUser> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午5:50:16
	 */
	public List< TUser > loadAllByEnable();
	
	/**
	 * @Description: 获得全部用户
	 * @return List<TUser> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午5:50:45
	 */
	public List< TUser > loadAll();
	
	/**
	 * @Description: 注册用户，并保存到对应的档案
	 * @param user
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 上午11:20:41
	 */
	public void saveRegister( TUser user , String tel );
	
	public List< TUser > findAll( Page page );
	
	Map< String , Object > getPageList( DataGridModel dgm , TUser user ) throws Exception;
	
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	public void updateUserIntegal( TUser user , String paramType , int intagalID );
	
	/**
	 * 
	 * @Description: 修改分供方用户的申请开通权限字段 修改为可重新申请
	 * @param userId
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-10 下午5:08:43
	 */
	public void updateUserApplyResource( String userId );
	
	public List< TApplyResource > getApplyResourseByUserID( int userId );
	
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
	/**
	 * 
	 * @Description: 给分供方用户开通角色权限
	 * @param appids
	 *            申请权限 用户表ID 多个用逗号分隔 void 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-12 上午10:20:58
	 */
	public void saveUserRolsResource( String appids );
	
	/**
	 * 根据司机id获得，该司机对应的user 对象
	 * 
	 * @param driverId
	 * @return
	 */
	public TUser getUserByDriverId( int driverId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tuser
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-26 下午3:42:58
	 */
	public void saveUpdateUser( TUser tuser );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param properties
	 * @param mainValues
	 * @return List<TUser> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-25 上午11:00:51
	 */
	public List< TUser > findByProperties( String[] properties , Object[] mainValues );
	
	public List< TUser > findByProperty( String propertyName , Object value );
	
}
