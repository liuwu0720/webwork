package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IArorderDao;
import com.clt.sub.model.TArorder;

/**
 * @Package com.clt.sub.dao.imp
 * @Description: 结算应收Dao
 * @author chenbin
 * @date 2014-7-21 下午3:55:16
 * @version V1.0
 */
@Repository
public class ArorderDaoImp extends GenericHibernateDao< TArorder , Integer > implements
        IArorderDao
{	
	
}
