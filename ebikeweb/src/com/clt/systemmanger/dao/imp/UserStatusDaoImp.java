/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-30 下午1:46:24 
 * @version V1.0 
 */
package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IUserStatusDao;
import com.clt.systemmanger.model.TUserStatus;

/** 
 * @Package com.clt.systemmanger.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-30 下午1:46:24 
 * @version V1.0 
 */
@Repository
public class UserStatusDaoImp extends
        GenericHibernateDao< TUserStatus , Integer > implements IUserStatusDao
{	
	
}
