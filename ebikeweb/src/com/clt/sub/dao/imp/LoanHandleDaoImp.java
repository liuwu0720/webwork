package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ILoanHandleDao;
import com.clt.sub.model.TLoanHandle;

@Repository
public class LoanHandleDaoImp extends GenericHibernateDao< TLoanHandle , Integer > implements ILoanHandleDao
{	
	
}
