package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IPicNewDao;
import com.clt.systemmanger.model.TPicNew;

/**
 * @Package com.clt.systemmanger.dao.imp
 * @Description:
 * @author hjx
 * @date 2014年7月28日 下午4:25:15
 * @version V1.0
 */
@Repository
public class PicNewDaoImp extends GenericHibernateDao< TPicNew , Integer > implements
        IPicNewDao
{	
	
}
