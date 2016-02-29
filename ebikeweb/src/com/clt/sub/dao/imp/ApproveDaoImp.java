/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-5 上午11:37:21 
 * @version V1.0 
 */
package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IApproveDao;
import com.clt.sub.model.TApprove;

/** 
 * @Package com.clt.sub.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-5 上午11:37:21 
 * @version V1.0 
 */
@Repository
public class ApproveDaoImp extends GenericHibernateDao< TApprove , Integer >
        implements IApproveDao
{	
	
}
