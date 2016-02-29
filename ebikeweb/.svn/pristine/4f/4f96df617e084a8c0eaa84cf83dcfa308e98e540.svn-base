/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-1-4 下午1:53:48
 * @version V1.0
 */
package com.clt.sub.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IApproveDao;
import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.sub.dao.ITransitFineDao;
import com.clt.sub.model.TFineApplay;
import com.clt.sub.service.ITransitFineSerivce;
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
 * @date 2015-1-4 下午1:53:48
 * @version V1.0
 */
@Service
public class TransitFineServiceImp implements ITransitFineSerivce
{
	@Autowired
	ITransitFineDao iTransitFineDao;
	
	@Autowired
	IApproveDao iApproveDao;
	
	@Autowired
	ISubsuppliersDao subsuppliersDao;
	
	@Autowired
	IStaticService staticService;
	@Autowired
	IUserDao iUserDao;
	@Autowired
	private IMsgRecordDao msgDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-4 下午1:54:03
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iTransitFineDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param applyIds
	 * @author liuwu
	 * @return
	 * @create_date 2015-1-5 上午11:28:38
	 */
	public String updateSureList( String applyIds , TUser user , String subbo ,
	        String vcResult , String vcNote )
	{
		
		int id = Integer.parseInt( applyIds );
		TFineApplay tFineApplay;
		try
		{
			tFineApplay = iTransitFineDao.get( id );
			if ( tFineApplay != null )
			{
				tFineApplay.setIApprove( user.getId() );
				if ( StringUtils.isNotBlank( user.getVcUsername() ) )
				{
					tFineApplay.setVcApproveName( user.getVcUsername() );
				}
				else
				{
					tFineApplay.setVcApproveName( user.getVcAccount() );
				}
				tFineApplay.setDtApprove( new Date() );
				tFineApplay.setnApproveResult( Integer.parseInt( vcResult ) );
				tFineApplay.setVcNote( vcNote );
				iTransitFineDao.saveOrUpdate( tFineApplay );
				// 消息推送
				String[] propertyNames = { "IArchiveType" , "iArchive" };
				Object[] values = { SystemConstants.SYS_TARCHIVE_DRIVER ,
				        tFineApplay.getIUser() };
				List< TUser > tUsers = iUserDao.findByPropertys( propertyNames , values );
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "81" );// 81=罚款
				map.put( "id" , id + "" );
				PushUtils pushUtils = new PushUtils( "您的罚款申请有变化" , "来自"
				        + user.getVcAccount() + "的审批" , tUsers ,
				        "com.unlcn.carrier.approvalprocess.FineDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( tUsers.get( 0 ).getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "来自" + user.getVcAccount() + "的审批" );
				tMsgRecord.setVcTitle( "您的罚款申请有变化" );
				msgDao.save( tMsgRecord );
			}
			else
			{
				return "找不到ID为" + id + "的罚款审批列表详情！";
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
	 * @param tFineApplay
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:01:02
	 */
	public void save( TFineApplay tFineApplay )
	{
		// TODO Auto-generated method stub
		iTransitFineDao.save( tFineApplay );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFineApplay
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:04:40
	 */
	public void update( TFineApplay tFineApplay )
	{
		// TODO Auto-generated method stub
		iTransitFineDao.update( tFineApplay );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:16:22
	 */
	public TFineApplay findById( int id )
	{
		// TODO Auto-generated method stub
		return iTransitFineDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFineApplay
	 * @author liuwu
	 * @create_date 2015-6-15 下午3:57:50
	 */
	public void parseUrl( TFineApplay tFineApplay )
	{
		String perUrl = staticService.getStringByParame( "transitFine" );
		if ( tFineApplay != null )
		{
			
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}
			
			if ( tFineApplay.getVcFinePicPath() != null )
			{
				String jpgPathStr = tFineApplay.getVcFinePicPath();
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
				tFineApplay.setVcFinePicPath( vcImgpath );
			}
			
		}
		
	}
	
}
