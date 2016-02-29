package com.clt.systemmanger.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.common.UserSession;
import com.clt.systemmanger.dao.IMessageDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TMessage;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IMessageService;
import com.clt.util.HqlHelper;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: 消息服务类
 * @author hjx
 * @date 2014年7月12日 下午4:28:00
 * @version V1.0
 */
@Service
public class MessageServiceImp implements IMessageService
{
	@Autowired
	private IMessageDao msgDao;
	@Autowired
	private IUserDao userDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public TMessage get( Integer id )
	{
		return msgDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void update( TMessage entity )
	{
		msgDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void save( TMessage entity )
	{
		msgDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void saveOrUpdate( TMessage entity )
	{
		msgDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void delete( TMessage entity )
	{
		entity.setNEnable( SystemConstants.SYS_DISABLE );
		msgDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void deleteByKey( Integer id )
	{
		TMessage msg = msgDao.get( id );
		msg.setNEnable( SystemConstants.SYS_DISABLE );
		msgDao.update( msg );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TMessage > loadAll()
	{
		return msgDao.loadAll();
	}
	
	/**
	 * @Description: 发给具体某个人
	 * @param title
	 * @param content
	 * @param url
	 * @param userid
	 * @param userName
	 * @param archiveTypeId
	 * @param archiveId
	 * @param iacceptUser
	 *            （接收用户id）
	 * @param vcAcceptUserName
	 *            （接收用户姓名）
	 * 
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月15日 下午2:56:26
	 */
	public void sendMsg( String title , String content , String url , String userid ,
	        String userName , String archiveTypeId , String archiveId ,
	        String iacceptUser , String vcAcceptUserName , String nTop , String vcMsgType )
	{
		TMessage msg = new TMessage();
		if ( StringUtils.isNotBlank( title ) )
		{
			msg.setVcTitle( title );
		}
		if ( StringUtils.isNotBlank( content ) )
		{
			msg.setVcContent( content );
		}
		if ( StringUtils.isNotBlank( url ) )
		{
			msg.setVcLinkUrl( url );
		}
		if ( StringUtils.isNotBlank( userid ) )
		{
			msg.setISendUser( Integer.parseInt( userid ) );
		}
		if ( StringUtils.isNotBlank( userName ) )
		{
			msg.setVcSendUserName( userName );
		}
		if ( StringUtils.isNotBlank( archiveTypeId ) )
		{
			msg.setiArchiveType( Integer.parseInt( archiveTypeId ) );
		}
		if ( StringUtils.isNotBlank( archiveId ) )
		{
			msg.setiArchive( Integer.parseInt( archiveId ) );
		}
		if ( StringUtils.isNotBlank( iacceptUser ) )
		{
			msg.setIAcceptUser( Integer.parseInt( iacceptUser ) );
			
		}
		if ( StringUtils.isNotBlank( vcAcceptUserName ) )
		{
			msg.setVcAcceptUserName( vcAcceptUserName );
			
		}
		if ( StringUtils.isNotBlank( nTop ) )
		{
			msg.setNTop( Integer.parseInt( nTop ) );
		}
		if ( StringUtils.isNotBlank( vcMsgType ) )
		{
			msg.setVcMsgType( vcMsgType );
		}
		msgDao.save( msg );
	}
	
	/**
	 * @Description: 发给某个档案类型下的某个档案下所有人
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param url
	 *            链接地址
	 * @param userid
	 *            发送人id
	 * @param userName
	 *            发送人姓名
	 * @param archiveTypeId
	 *            档案类型id
	 * @param archiveId
	 *            具体某个档案id
	 * @param vcMsgType
	 *            消息类型
	 * @author hjx
	 * @create_date 2014年9月16日 下午1:34:30
	 */
	public void sendMsg( String title , String content , String url , String userid ,
	        String userName , String archiveTypeId , String archiveId , String vcMsgType )
	{
		List< TUser > users = userDao
		        .findByPropertys(
		                new String[] { "NEnable" , "IArchiveType" , "IArchive" } ,
		                new Object[] { SystemConstants.SYS_ENABLE ,
		                        Integer.parseInt( archiveTypeId ) ,
		                        Integer.parseInt( archiveId ) } );
		for ( TUser user : users )
		{
			this.sendMsg( title , content , url , userid , userName , archiveTypeId ,
			        archiveId , String.valueOf( user.getId() ) , user.getVcUsername() ,
			        "" , vcMsgType );
		}
	}
	
	/**
	 * @Description: 发给某个档案类型的所有人
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @param url
	 *            链接地址
	 * @param userid
	 *            发送人id
	 * @param userName
	 *            发送人姓名
	 * @param archiveTypeId
	 *            档案类型id
	 * @param vcMsgType
	 *            消息类型
	 * @author hjx
	 * @create_date 2014年9月16日 下午1:33:13
	 */
	public void sendMsg( String title , String content , String url , String userid ,
	        String userName , String archiveTypeId , String vcMsgType )
	{
		List< TUser > users = userDao.findByPropertys(
		        new String[] { "NEnable" , "IArchiveType" } ,
		        new Object[] { SystemConstants.SYS_ENABLE ,
		                Integer.parseInt( archiveTypeId ) } );
		for ( TUser user : users )
		{
			this.sendMsg( title , content , url , userid , userName , archiveTypeId , "" ,
			        String.valueOf( user.getId() ) , user.getVcUsername() , "" ,
			        vcMsgType );
		}
		
	}
	
	/**
	 * @Description: 更新消息，收件人（阅读人） 是否忽略
	 * @param id
	 * @param iAcceptUser
	 * @param acceptUserName
	 * @param nIgnore
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月15日 下午4:20:54
	 */
	public void updateMsg( Integer id , String iAcceptUser , String acceptUserName ,
	        String nIgnore )
	{
		TMessage msg = msgDao.get( id );
		if ( null != msg )
		{
			
			if ( StringUtils.isNotBlank( nIgnore ) )
				msg.setNIgnore( Integer.parseInt( nIgnore ) );
			
			msg.setDtLook( new Date() );
			msgDao.update( msg );
		}
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return msgDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: 获得当前的 用户所有未忽略的消息总数
	 * @param id
	 * @return int 返回值描述
	 * @author hjx
	 * @create_date 2014年9月17日 下午1:47:32
	 */
	public int getMsgCountByCurrentUser()
	{
		int count = 0;
		
		TUser user = ( TUser ) UserSession.get( "user" );
		List< TMessage > msgList = msgDao.findByPropertys( new String[] { "NEnable" ,
		        "NIgnore" , "IAcceptUser" } , new Object[] { SystemConstants.SYS_ENABLE ,
		        SystemConstants.SYS_DISABLE , user.getId() } );
		
		if ( CollectionUtils.isNotEmpty( msgList ) )
		{
			count = msgList.size();
		}
		return count;
	}
}
