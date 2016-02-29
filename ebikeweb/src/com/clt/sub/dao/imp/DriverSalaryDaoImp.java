package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IDriverSalaryDao;
import com.clt.sub.model.TDriverSalary;

@Repository
public class DriverSalaryDaoImp extends GenericHibernateDao< TDriverSalary , Integer >
        implements IDriverSalaryDao
{	

}
