/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-11 下午2:50:51 
 * @version V1.0 
 */
package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IStoresDao;
import com.clt.systemmanger.model.TStores;

/** 
 * @Package com.clt.systemmanger.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-11 下午2:50:51 
 * @version V1.0 
 */
@Repository
public class TStoresDaoImp extends GenericHibernateDao< TStores , Integer >
        implements IStoresDao
{	
	
}
