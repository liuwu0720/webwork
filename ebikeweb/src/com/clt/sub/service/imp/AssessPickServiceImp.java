package com.clt.sub.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IAssessPickDao;
import com.clt.sub.model.TAssessPick;
import com.clt.sub.service.IAssessPickService;

@Service
public class AssessPickServiceImp implements IAssessPickService
{
	@Autowired
	private IAssessPickDao assessDao;
	
	public TAssessPick get( int id )
	{
		return assessDao.get( id );
	}
	
	public void save( TAssessPick entity )
	{
		assessDao.save( entity );
	}
	
	public void update( TAssessPick entity )
	{
		assessDao.update( entity );
	}
	
}
