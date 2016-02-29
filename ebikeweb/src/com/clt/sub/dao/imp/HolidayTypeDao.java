package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IHolidayTypeDao;
import com.clt.sub.model.THolidayType;

@Repository
public class HolidayTypeDao extends GenericHibernateDao< THolidayType , Integer >
        implements IHolidayTypeDao
{	

}
