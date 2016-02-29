package com.clt.systemmanger.service;

import java.util.Map;

import com.clt.systemmanger.model.TMsgRecord;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

public interface IMsgRecordService
{
	public TMsgRecord get( int id );
	
	public void save( TMsgRecord entity );
	
	public void update( TMsgRecord entity );
	
	public void saveOrUpdate( TMsgRecord entity );
	
	public Map< String , Object > findByHelper( HqlHelper hql );
	
	public Map< String , Object > findAllMsgs( Page page , String vcTitle , int userId );
}
