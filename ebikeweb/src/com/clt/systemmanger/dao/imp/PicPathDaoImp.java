/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-26 下午5:21:18 
 * @version V1.0 
 */
package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IPicPathDao;
import com.clt.systemmanger.model.TPicPath;

/** 
 * @Package com.clt.systemmanger.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-26 下午5:21:18 
 * @version V1.0 
 */
@Repository
public class PicPathDaoImp extends GenericHibernateDao< TPicPath , Integer >
        implements IPicPathDao
{	
	
}
