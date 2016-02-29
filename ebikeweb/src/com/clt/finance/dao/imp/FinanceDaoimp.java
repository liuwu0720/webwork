package com.clt.finance.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.finance.dao.IFinanceDao;
import com.clt.finance.model.TFinance;

/**
 * @Package com.clt.finance.dao.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月17日 上午11:07:14
 * @version V1.0
 */
@Repository
public class FinanceDaoimp extends GenericHibernateDao< TFinance , Integer > implements
        IFinanceDao
{	
	
}
