package com.clt.sub.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IAssessPickDao;
import com.clt.sub.model.TAssessPick;

@Repository
public class AssessPickDaoImp extends GenericHibernateDao< TAssessPick , Integer >
        implements IAssessPickDao
{	

}
