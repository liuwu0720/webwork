package com.clt.systemmanger.service.imp;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IMsgRecordDao;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.service.IMsgRecordService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class MsgRecordServiceImp implements IMsgRecordService
{
	@Autowired
	private IMsgRecordDao msgDao;
	
	public TMsgRecord get( int id )
	{
		return msgDao.get( id );
	}
	
	public void save( TMsgRecord entity )
	{
		msgDao.save( entity );
	}
	
	public void saveOrUpdate( TMsgRecord entity )
	{
		msgDao.saveOrUpdate( entity );
	}
	
	public void update( TMsgRecord entity )
	{
		msgDao.update( entity );
	}
	
	public Map< String , Object > findByHelper( HqlHelper hql )
	{
		return msgDao.findAllByHqlHelp( hql );
	}
	
	// 查询所有消息记录
	public Map< String , Object > findAllMsgs( Page page , String vcTitle , int userId )
	{
		String sql = "select ID,N_ENABLE,VC_ADDUSER,DT_ADD,I_USER,N_MSG_TYPE,VC_CONTENT,"
		        + "I_USER_ACCEPT,VC_TITLE from t_msg_record where N_ENABLE ="
		        + SystemConstants.SYS_ENABLE;
		if ( StringUtils.isNotBlank( vcTitle ) )
		{
			sql += " and VC_TITLE like '%" + vcTitle.trim() + "%'";
		}
		sql += " and (N_MSG_TYPE =0 or(N_MSG_TYPE =1 and I_USER_ACCEPT =" + userId
		        + "))  order by dt_add desc";
		System.out.println( "sql:" + sql );
		Map< String , Object > result = msgDao.getSpringSQL( sql , page );
		return result;
	}
}
