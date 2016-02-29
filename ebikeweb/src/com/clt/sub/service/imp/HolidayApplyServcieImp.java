/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-4-24 下午3:25:37
 * @version V1.0
 */
package com.clt.sub.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IHolidayApplyDao;
import com.clt.sub.model.THolidayApply;
import com.clt.sub.service.IHolidayApplyService;
import com.clt.systemmanger.dao.IMsgRecordDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.PushUtils;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.sub.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-4-24 下午3:25:37
 * @version V1.0
 */
@Service
public class HolidayApplyServcieImp implements IHolidayApplyService
{
	@Autowired
	IHolidayApplyDao iHolidayApplyDao;
	@Autowired
	IUserDao iUserDao;
	@Autowired
	private IMsgRecordDao msgDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-24 下午3:26:36
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iHolidayApplyDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param array
	 * @param vcResult
	 * @param vcApproveNote
	 * @param user
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-24 下午4:22:17
	 */
	public String updateHolidayApply( String array , String vcResult ,
	        String vcApproveNote , TUser user )
	{
		
		int id = Integer.parseInt( array );
		try
		{
			THolidayApply tHolidayApply = iHolidayApplyDao.get( id );
			if ( tHolidayApply != null )
			{
				tHolidayApply.setDtApprove( new Date() );
				tHolidayApply.setVcApproveNote( vcApproveNote );
				tHolidayApply.setVcApprveName( user.getVcAccount() );
				tHolidayApply.setiApprove( user.getId() );
				tHolidayApply.setnApproveResult( Integer.parseInt( vcResult ) );
				iHolidayApplyDao.saveOrUpdate( tHolidayApply );
				// 消息推送
				String[] propertyNames = { "IArchiveType" , "iArchive" };
				Object[] values = { SystemConstants.SYS_TARCHIVE_DRIVER ,
				        tHolidayApply.getiUser() };
				List< TUser > tUsers = iUserDao.findByPropertys( propertyNames , values );
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "86" );// 86=请假
				map.put( "id" , id + "" );
				PushUtils pushUtils = new PushUtils( "您的请假申请有变化" , "来自"
				        + user.getVcAccount() + "的审批" , tUsers ,
				        "com.unlcn.carrier.approvalprocess.HolidayDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				if ( CollectionUtils.isNotEmpty( tUsers ) )
				{
					tMsgRecord.setIUserAccept( tUsers.get( 0 ).getId() );
				}
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "来自" + user.getVcAccount() + "的审批" );
				tMsgRecord.setVcTitle( "您的请假申请有变化" );
				msgDao.save( tMsgRecord );
			}
			else
			{
				return "找不到ID为" + id + "的请假申请审批列表详情！";
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return e.getMessage();
		}
		
		return "success";
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tHolidayApply
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:52:24
	 */
	public void save( THolidayApply tHolidayApply )
	{
		// TODO Auto-generated method stub
		iHolidayApplyDao.save( tHolidayApply );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tHolidayApply
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:52:46
	 */
	public void update( THolidayApply tHolidayApply )
	{
		// TODO Auto-generated method stub
		iHolidayApplyDao.update( tHolidayApply );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:56:47
	 */
	public THolidayApply findById( int id )
	{
		// TODO Auto-generated method stub
		return iHolidayApplyDao.get( id );
	}
	
}
