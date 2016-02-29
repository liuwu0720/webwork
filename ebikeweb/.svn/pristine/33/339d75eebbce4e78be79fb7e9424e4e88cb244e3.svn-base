package com.clt.systemmanger.service;

import java.util.List;
import java.util.Map;

import com.clt.systemmanger.model.TMessage;
import com.clt.util.HqlHelper;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 消息服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IMessageService
{
	public TMessage get( Integer id );
	
	public void update( TMessage entity );
	
	public void save( TMessage entity );
	
	public void saveOrUpdate( TMessage entity );
	
	public void delete( TMessage entity );
	
	public void deleteByKey( Integer id );
	
	public List< TMessage > loadAll();
	
	/**
	 * @Description: 发给具体某个人
	 * @param title
	 * @param content
	 * @param url
	 * @param userid
	 * @param userName
	 * @param archiveTypeId
	 * @param archiveId
	 * @param sendToUser
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月15日 下午2:56:26
	 */
	public void sendMsg( String title , String content , String url , String userid ,
	        String userName , String archiveTypeId , String archiveId ,
	        String sendToUser , String vcAcceptUserName , String nTop , String vcMsgType );
	
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
	        String userName , String archiveTypeId , String archiveId , String vcMsgType );
	
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
	        String userName , String archiveTypeId , String vcMsgType );
	
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
	        String nIgnore );
	
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	/**
	 * @Description: 获得当前的 用户所有未忽略的消息总数
	 * @param id
	 * @return int 返回值描述
	 * @author hjx
	 * @create_date 2014年9月17日 下午1:47:32
	 */
	public int getMsgCountByCurrentUser();
	
}
