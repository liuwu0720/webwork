package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ICityDao;
import com.clt.sub.model.TCity;

@Repository
public class CityDaoImp extends GenericHibernateDao< TCity , Integer > implements ICityDao
{	
	
}
