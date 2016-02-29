/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-6 下午3:03:15 
 * @version V1.0 
 */
package com.clt.sub.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IDetourApplyDao;
import com.clt.sub.model.TDetour;
import com.clt.sub.service.IDetourApplyService;
import com.clt.systemmanger.dao.IMsgRecordDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.PushUtils;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.sub.service.imp
 * @Description: TODO(绕路罚款申请)
 * @author liuwu
 * @date 2015-1-6 下午3:03:15
 * @version V1.0
 */
@Service
public class DetourApplyServiceImp implements IDetourApplyService
{
	@Autowired
	IDetourApplyDao iDetourApplyDao;
	@Autowired
	IUserDao iUserDao;
	@Autowired
	private IMsgRecordDao msgDao;
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-6 下午4:21:35
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iDetourApplyDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param user
	 * @param subbo
	 * @param tDetour
	 * @author liuwu
	 * @create_date 2015-1-7 下午1:48:41
	 */
	public void updateSureList( TUser user , String subbo , TDetour tDetour )
	{
		int tDetourId = tDetour.getId();
		TDetour newDetour = iDetourApplyDao.get( tDetourId );
		newDetour.setDtApprove( new Date() );// 审核时间
		newDetour.setIApprove( user.getId() );
		newDetour.setVcApproveName( user.getVcAccount() );
		newDetour.setnApproveResult( 0 );
		newDetour.setVcDetourLength( tDetour.getVcDetourLength() );// 绕路长度
		newDetour.setVcNote( tDetour.getVcNote() );
		iDetourApplyDao.saveOrUpdate( newDetour );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param user
	 * @param subbo
	 * @param array
	 * @param vcResult
	 * @param vcLength
	 * @param vcNote
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-23 下午6:02:42
	 */
	public String updateSureList( TUser user , String subbo , String array ,
	        String vcResult , String vcLength , String vcNote )
	{
		
		int id = Integer.parseInt( array );
			try
			{
				TDetour tDetour = iDetourApplyDao.get( id );
				if ( tDetour != null )
				{
					tDetour.setDtApprove( new Date() );
					tDetour.setIApprove( user.getId() );
				tDetour.setVcApproveName( user.getVcAccount() );
					tDetour.setnApproveResult( Integer.parseInt( vcResult ) );
					tDetour.setVcDetourLength( vcLength );
					tDetour.setVcNote( vcNote );
					iDetourApplyDao.saveOrUpdate( tDetour );
				// 消息推送
				String[] propertyNames = { "IArchiveType" , "iArchive" };
				Object[] values = { SystemConstants.SYS_TARCHIVE_DRIVER ,
				        tDetour.getIUser() };
				List< TUser > tUsers = iUserDao.findByPropertys( propertyNames ,
				        values );
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "82" );// 82=绕路
				map.put( "id" , id + "" );
				PushUtils pushUtils = new PushUtils( "您的绕路申请有变化" , "来自"
				        + user.getVcAccount() + "的审批" , tUsers ,
				        "com.unlcn.carrier.approvalprocess.DetourDetailActivity" ,
				        map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( tUsers.get( 0 ).getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "来自" + user.getVcAccount() + "的审批" );
				tMsgRecord.setVcTitle( "您的绕路申请有变化" );
				msgDao.save( tMsgRecord );
				}
				else
				{
					return "找不到ID为" + id + "的绕路申请审批列表详情！";
				}
			}
			catch ( Exception e )
			{
				return e.getMessage();
			}
			

		return "success";
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDetour
	 * @author liuwu
	 * @create_date 2015-4-28 上午10:25:23
	 */
	public void save( TDetour tDetour )
	{
		iDetourApplyDao.save( tDetour );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDetour
	 * @author liuwu
	 * @create_date 2015-4-28 上午10:27:44
	 */
	public void update( TDetour tDetour )
	{
		// TODO Auto-generated method stub
		iDetourApplyDao.update( tDetour );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-28 上午10:32:54
	 */
	public TDetour findDetourById( int id )
	{
		// TODO Auto-generated method stub
		return iDetourApplyDao.get( id );
	}
	
}
