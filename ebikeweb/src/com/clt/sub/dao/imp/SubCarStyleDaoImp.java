package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ISubCarStyleDao;
import com.clt.sub.model.TSubCarStyle;

@Repository
public class SubCarStyleDaoImp extends GenericHibernateDao< TSubCarStyle , Integer > implements ISubCarStyleDao
{	
	
}
