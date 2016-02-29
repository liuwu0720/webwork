/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-12-16 上午11:00:38 
 * @version V1.0 
 */
package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ICustomerDao;
import com.clt.sub.model.TCustomer;

/** 
 * @Package com.clt.sub.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2014-12-16 上午11:00:38 
 * @version V1.0 
 */
@Repository
public class CustomerDaoImp extends GenericHibernateDao< TCustomer , Integer >
        implements ICustomerDao
{	
	
}
