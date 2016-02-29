/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-2 上午10:03:27 
 * @version V1.0 
 */
package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IDriverSubsideLinksDao;
import com.clt.sub.model.TDriverSubsideLinks;

/** 
 * @Package com.clt.sub.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-2 上午10:03:27 
 * @version V1.0 
 */
@Repository
public class DriverSubsideLinksDaoImp extends
        GenericHibernateDao< TDriverSubsideLinks , Integer > implements
        IDriverSubsideLinksDao
{	
	
}
