/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-27 上午9:48:15 
 * @version V1.0 
 */
package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IPicTypeDao;
import com.clt.systemmanger.model.TPicType;

/** 
 * @Package com.clt.systemmanger.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-27 上午9:48:15 
 * @version V1.0 
 */
@Repository
public class PicTypeDaoImp extends GenericHibernateDao< TPicType , Integer >
        implements IPicTypeDao
{	
	
}
