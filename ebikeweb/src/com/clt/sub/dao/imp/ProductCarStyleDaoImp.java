package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IProductCarStyleDao;
import com.clt.sub.model.TProductCarStyle;

@Repository
public class ProductCarStyleDaoImp extends GenericHibernateDao< TProductCarStyle , Integer > implements IProductCarStyleDao
{	
	
}
