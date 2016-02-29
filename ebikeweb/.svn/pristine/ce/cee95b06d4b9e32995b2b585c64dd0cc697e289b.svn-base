/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午2:16:49 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.TSubstaff;
import com.clt.systemmanger.model.TUser;
import com.clt.util.Page;

/**
 * @Package com.clt.sub.service
 * @Description: TODO(分供方员工管理)
 * @author liuwu
 * @date 2015-1-12 下午2:16:49
 * @version V1.0
 */
public interface ISubAdminUserStaffService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-12 下午2:20:44
	 */
	Map< String , Object > getSpringSQL( String sql , Page page );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tUser
	 * @return boolean 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-15 下午3:42:29
	 */
	boolean checkUserExist( TUser tUser );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param substaff
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-15 下午4:11:23
	 */
	void saveSubStaffUserInfo( TSubstaff substaff );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param substaff
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-15 下午4:36:14
	 */
	void updateSubStaffUserInfo( TSubstaff substaff );
	
	/**
	 * @Description: TODO(根据用户Id查出可授权的角色)
	 * @param userId
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 上午10:17:34
	 */
	String getUserRoleByUserId( String userId );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ids
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 下午4:44:34
	 */
	void updateUserAcountDisable( String[] ids );
	
}
