package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IBidWinDao;
import com.clt.sub.dao.ICarStyleDao;
import com.clt.sub.model.TBidWin;
import com.clt.sub.model.TCarStyle;

@Repository
public class CarStyleDaoImp  extends GenericHibernateDao< TCarStyle , Integer > implements ICarStyleDao
{	
	
}
