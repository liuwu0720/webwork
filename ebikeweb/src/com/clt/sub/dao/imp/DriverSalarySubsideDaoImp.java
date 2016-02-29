/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-27 下午5:50:33 
 * @version V1.0 
 */
package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IDriverSalarySubsideDao;
import com.clt.sub.model.TDriverSubsides;

/** 
 * @Package com.clt.sub.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-27 下午5:50:33 
 * @version V1.0 
 */
@Repository
public class DriverSalarySubsideDaoImp extends
        GenericHibernateDao< TDriverSubsides , Integer > implements
        IDriverSalarySubsideDao
{
	

	
}
