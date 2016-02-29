/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-27 下午5:28:44 
 * @version V1.0 
 */
package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.ICarStylePicDao;
import com.clt.systemmanger.model.TCarStylePic;

/** 
 * @Package com.clt.systemmanger.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-3-27 下午5:28:44 
 * @version V1.0 
 */
@Repository
public class CarStylePicDaoImp extends
        GenericHibernateDao< TCarStylePic , Integer > implements
        ICarStylePicDao
{	
	
}
