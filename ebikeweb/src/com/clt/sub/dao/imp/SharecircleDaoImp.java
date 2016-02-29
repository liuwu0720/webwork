package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ISharecircleDao;
import com.clt.sub.model.TSharecircle;

@Repository
public class SharecircleDaoImp extends GenericHibernateDao< TSharecircle , Integer >
        implements ISharecircleDao
{	

}
