/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午3:28:55 
 * @version V1.0 
 */
package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IRoleCompanyDao;
import com.clt.sub.model.TRoleCompany;

/** 
 * @Package com.clt.sub.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午3:28:55 
 * @version V1.0 
 */
@Repository
public class RoleCompanyDaoImp extends
        GenericHibernateDao< TRoleCompany , Integer > implements
        IRoleCompanyDao
{	
	
}
