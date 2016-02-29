package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ITruckDriverDao;
import com.clt.sub.model.TTruckDriver;

@Repository
public class TruckDriverDaoImp extends GenericHibernateDao< TTruckDriver , Integer > implements ITruckDriverDao
{

	
}
