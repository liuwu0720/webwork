package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IProductScopeDao;
import com.clt.sub.model.TProductScope;

@Repository
public class ProductScopeDaoImp extends GenericHibernateDao< TProductScope , Integer >
        implements IProductScopeDao
{	
	
}
