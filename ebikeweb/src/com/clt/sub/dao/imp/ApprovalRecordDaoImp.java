package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IApprovalRecordDao;
import com.clt.sub.model.TApprovalRecord;

@Repository
public class ApprovalRecordDaoImp extends GenericHibernateDao< TApprovalRecord , Integer >
        implements IApprovalRecordDao
{	
	
}
