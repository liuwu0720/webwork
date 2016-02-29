/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-1-7 下午3:16:24
 * @version V1.0
 */
package com.clt.sub.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IDamageDao;
import com.clt.sub.model.TDamage;
import com.clt.sub.service.IDamageService;
import com.clt.systemmanger.dao.IMsgRecordDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IStaticService;
import com.clt.util.HqlHelper;
import com.clt.util.PushUtils;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.sub.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-1-7 下午3:16:24
 * @version V1.0
 */
@Service
public class DamageServiceImp implements IDamageService
{
	@Autowired
	IDamageDao iDamageDao;
	@Autowired
	private IStaticService staticService;
	@Autowired
	IUserDao iUserDao;
	@Autowired
	private IMsgRecordDao msgDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-8 上午11:37:42
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iDamageDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param damageId
	 * @param note
	 * @param subbo
	 * @param user
	 * @author liuwu
	 * @create_date 2015-1-8 下午3:34:46
	 */
	public String updateDamageList( String damageId , String note , String subbo ,
	        TUser user , String vcResult )
	{
		int id = Integer.parseInt( damageId );
		TDamage tDamage;
		try
		{
			tDamage = iDamageDao.get( id );
			if ( tDamage != null )
			{
				tDamage.setDtApprove( new Date() );
				tDamage.setiApprove( user.getId() );
				tDamage.setVcApproveName( user.getVcAccount() );
				tDamage.setnApproveResult( Integer.parseInt( vcResult ) );// 审批结果(0,通过，1，未审批,2:未通过)
				tDamage.setVcNote( note );
				iDamageDao.saveOrUpdate( tDamage );
				// 消息推送
				String[] propertyNames = { "IArchiveType" , "iArchive" };
				Object[] values = { SystemConstants.SYS_TARCHIVE_DRIVER ,
				        tDamage.getiUser() };
				List< TUser > tUsers = iUserDao.findByPropertys( propertyNames , values );
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "83" );// 83=事故质损
				map.put( "id" , id + "" );
				PushUtils pushUtils = new PushUtils( "您的质损申请有变化" , "来自"
				        + user.getVcAccount() + "的审批" , tUsers ,
				        "com.unlcn.carrier.approvalprocess.DamageDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( tUsers.get( 0 ).getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "来自" + user.getVcAccount() + "的审批" );
				tMsgRecord.setVcTitle( "有质损申请，请点击查看" );
				msgDao.save( tMsgRecord );
			}
			else
			{
				return "找不到ID为" + id + "的质损审批列表详情！";
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
	 * @param tDamage
	 * @author liuwu
	 * @create_date 2015-4-24 下午5:09:02
	 */
	public void saveDamage( TDamage tDamage )
	{
		iDamageDao.save( tDamage );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDamage
	 * @author liuwu
	 * @create_date 2015-4-28 上午9:55:21
	 */
	public void updateDamage( TDamage tDamage )
	{
		// TODO Auto-generated method stub
		iDamageDao.update( tDamage );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-28 上午10:01:36
	 */
	public TDamage findDamageById( int id )
	{
		// TODO Auto-generated method stub
		return iDamageDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDamage
	 * @author liuwu
	 * @create_date 2015-6-4 上午11:52:04
	 */
	public void parseUrl( TDamage tDamage )
	{
		String perUrl = staticService.getStringByParame( "damage" );
		if ( tDamage != null )
		{
			
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}
			if ( tDamage.getVcPicpath() != null )
			{
				String jpgPathStr = tDamage.getVcPicpath();
				String[] jpgPaths = jpgPathStr.split( "," );
				String vcImgpath = "";
				for ( int i = 0 ; i < jpgPaths.length ; i++ )
				{
					vcImgpath += perUrl + jpgPaths[i];
					if ( i != jpgPaths.length - 1 )
					{
						vcImgpath += ",";
					}
				}
				tDamage.setVcPicpath( vcImgpath );
			}
			
		}
	}
	
}
