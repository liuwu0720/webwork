package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IArkilometerDao;
import com.clt.sub.dao.IBidWinDao;
import com.clt.sub.model.TArkilometer;
import com.clt.sub.model.TBidWin;

@Repository
public class BidWinDaoImp extends GenericHibernateDao< TBidWin , Integer > implements IBidWinDao
{	
	
}
