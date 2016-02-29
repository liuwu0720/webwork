/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-7 下午3:18:09 
 * @version V1.0 
 */
package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IDamageDao;
import com.clt.sub.model.TDamage;

/** 
 * @Package com.clt.sub.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-7 下午3:18:09 
 * @version V1.0 
 */
@Repository
public class DamageDaoImp extends GenericHibernateDao< TDamage , Integer >
        implements IDamageDao
{	
	
}
