package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.sub.model.TSubsuppliers;

@Repository
public class SubsuppliersDaoImp extends GenericHibernateDao< TSubsuppliers , Integer > implements ISubsuppliersDao
{	
	
}
