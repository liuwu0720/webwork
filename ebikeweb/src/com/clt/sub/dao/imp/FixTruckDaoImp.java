package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IFixTruckDao;
import com.clt.sub.model.TFixTruck;

@Repository
public class FixTruckDaoImp extends GenericHibernateDao< TFixTruck , Integer > implements
        IFixTruckDao
{	

}
