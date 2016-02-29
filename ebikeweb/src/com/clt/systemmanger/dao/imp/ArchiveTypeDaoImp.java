package com.clt.systemmanger.dao.imp;


import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IArchiveTypeDao;
import com.clt.systemmanger.model.TArchiveType;

@Repository
public class ArchiveTypeDaoImp extends GenericHibernateDao< TArchiveType , Integer > implements IArchiveTypeDao
{	
	
}
