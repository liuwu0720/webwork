package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IIntegalAddDao;
import com.clt.systemmanger.model.TIntegalAdd;

/**
 * @Package com.clt.systemmanger.dao.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:15:43
 * @version V1.0
 */
@Repository
public class IntegalCutDaoImp extends GenericHibernateDao< TIntegalAdd , Integer >
        implements IIntegalAddDao
{	
	
}
