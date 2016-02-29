package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ILoanTypeDao;
import com.clt.sub.model.TLoanType;

@Repository
public class LoanTypeDaoImp extends GenericHibernateDao< TLoanType , Integer > implements
        ILoanTypeDao
{	

}
