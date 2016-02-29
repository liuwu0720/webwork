package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IShipVinDao;
import com.clt.sub.model.TShipVin;

@Repository
public class ShipVinDaoImp extends GenericHibernateDao< TShipVin , Integer > implements
        IShipVinDao
{	

}
