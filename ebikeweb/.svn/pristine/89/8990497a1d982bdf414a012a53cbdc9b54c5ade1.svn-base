/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午3:30:18 
 * @version V1.0 
 */
package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TRoleCompany;
import com.clt.systemmanger.model.TUserRole;

/** 
 * @Package com.clt.sub.service 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午3:30:18 
 * @version V1.0 
 */
public interface IRoleCompanyService
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param roleCompany
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-12 下午3:53:31
	 */
	void saveRoleCompany( TRoleCompany roleCompany );
	
	/**
	 * @Description: TODO(删除操作)
	 * @param ids
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-12 下午4:33:35
	 */
	void updateSetDiable( String[] ids );
	
	/**
	 * @Description: TODO(根据用户id查出所拥有的员工角色)
	 * @param tUserRole
	 * @return List<TRoleCompany> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-14 下午4:51:58
	 */
	List< TRoleCompany > getObjectByUserId( TUserRole tUserRole );
	
}
