package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.ISpareCapacityDao;
import com.clt.sub.model.TSpareCapacity;

/**
 * @Package com.clt.sub.dao.imp
 * @Description: 空闲运力发布类Dao
 * @author hjx
 * @date 2014年10月9日 下午4:02:16
 * @version V1.0
 */
@Repository
public class SpareCapacityDaoImp extends GenericHibernateDao< TSpareCapacity , Integer >
        implements ISpareCapacityDao
{	
	
}
