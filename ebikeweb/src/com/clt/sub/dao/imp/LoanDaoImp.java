package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ILoanDao;
import com.clt.sub.model.TLoan;

@Repository
public class LoanDaoImp extends GenericHibernateDao< TLoan , Integer > implements ILoanDao
{	
	
}
