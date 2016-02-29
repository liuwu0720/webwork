package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IKillInfoDao;
import com.clt.sub.model.TKillInfo;

@Repository
public class KillInfoDaoImp extends GenericHibernateDao<TKillInfo,Integer> implements IKillInfoDao
{	
	
}
