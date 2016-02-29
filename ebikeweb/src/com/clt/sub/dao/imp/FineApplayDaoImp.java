package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IFineApplayDao;
import com.clt.sub.model.TFineApplay;

@Repository
public class FineApplayDaoImp extends GenericHibernateDao< TFineApplay , Integer >
        implements IFineApplayDao
{	

}
