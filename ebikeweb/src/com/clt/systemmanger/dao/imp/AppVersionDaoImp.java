package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IAppVersionDao;
import com.clt.systemmanger.model.TAppVersion;

@Repository
public class AppVersionDaoImp extends GenericHibernateDao< TAppVersion , Integer >
        implements IAppVersionDao
{	

}
