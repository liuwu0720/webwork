package com.clt.systemmanger.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IFeedbackDao;
import com.clt.systemmanger.model.TFeedback;
import com.clt.systemmanger.service.IFeedbackService;
import com.clt.util.HqlHelper;

@Service
public class FeedbackServiceImp implements IFeedbackService
{
	@Autowired
	private IFeedbackDao feedbackDao;
	
	public TFeedback get( int id )
	{
		return feedbackDao.get( id );
	}
	
	public void save( TFeedback entity )
	{
		feedbackDao.save( entity );
	}
	
	public void update( TFeedback entity )
	{
		feedbackDao.update( entity );
	}
	
	public void saveOrUpdate( TFeedback entity )
	{
		feedbackDao.saveOrUpdate( entity );
	}
	
	public void delete( TFeedback entity )
	{
		feedbackDao.delete( entity );
	}
	
	public Map< String , Object > findByHelper( HqlHelper hql )
	{
		return feedbackDao.findAllByHqlHelp( hql );
	}
}
