package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IAssessDao;
import com.clt.sub.model.TAssess;
import com.clt.sub.service.IAssessService;
import com.clt.util.SystemConstants;

@Service
public class AssessServiceImp implements IAssessService
{
	
	@Autowired
	private IAssessDao assessDao;
	
	public void save( TAssess assess )
	{
		assessDao.save( assess );
	}
	
	public void getAssessByTypeAndBussines( int type , String businessNo )
	{
		List< TAssess > list = assessDao.findByPropertys( new String[] { "NEnable" ,
		        "NType" , "vcBussinesNo" } , new Object[] { SystemConstants.SYS_ENABLE ,
		        type , businessNo } );
		
	}
}
