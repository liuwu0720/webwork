/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-4 下午2:08:42 
 * @version V1.0 
 */
package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ITransitFineDao;
import com.clt.sub.model.TFineApplay;

/** 
 * @Package com.clt.sub.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-4 下午2:08:42 
 * @version V1.0 
 */

@Repository
public class TransitFineDaoImp extends
        GenericHibernateDao< TFineApplay , Integer >
        implements ITransitFineDao
{
	
}
