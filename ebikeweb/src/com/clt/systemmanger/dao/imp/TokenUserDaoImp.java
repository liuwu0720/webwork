package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.ITokenUserDao;
import com.clt.systemmanger.model.TTokenUser;

/**
 * @Package com.clt.systemmanger.dao.imp
 * @Description: 手机用户登录 dao
 * @author hjx
 * @date 2015年4月17日 上午10:46:10
 * @version V1.0
 */
@Repository
public class TokenUserDaoImp extends GenericHibernateDao< TTokenUser , Integer >
        implements ITokenUserDao
{	
	
}
