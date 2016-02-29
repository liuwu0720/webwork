package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IUserLogDao;
import com.clt.systemmanger.model.TUserLog;

/**
 * @Package com.clt.systemmanger.dao.imp
 * @Description: 用户操作日志dao
 * @author hjx
 * @date 2014年12月16日 上午11:36:15
 * @version V1.0
 */
@Repository
public class UserLogDaoImp extends GenericHibernateDao< TUserLog , Integer > implements
        IUserLogDao
{	
	
}
