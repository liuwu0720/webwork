/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-3-25 下午3:18:21
 * @version V1.0
 */
package com.clt.systemmanger.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.systemmanger.dao.IAppSwitchDao;
import com.clt.systemmanger.dao.ICarStylePicDao;
import com.clt.systemmanger.dao.IDriverClassDao;
import com.clt.systemmanger.dao.IGpsRateDao;
import com.clt.systemmanger.dao.IPicPathDao;
import com.clt.systemmanger.dao.IPicTypeDao;
import com.clt.systemmanger.dao.ITShareTagDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.dao.IUserStatusDao;
import com.clt.systemmanger.model.TAppSwitch;
import com.clt.systemmanger.model.TCarStylePic;
import com.clt.systemmanger.model.TDriverClass;
import com.clt.systemmanger.model.TGpsrate;
import com.clt.systemmanger.model.TPicPath;
import com.clt.systemmanger.model.TPicType;
import com.clt.systemmanger.model.TShareTag;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserStatus;
import com.clt.systemmanger.service.IBasicManageService;
import com.clt.util.HqlHelper;
import com.clt.util.push.PushMessageUtil;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-3-25 下午3:18:21
 * @version V1.0
 */
@Service
public class BasicManageServiceImp implements IBasicManageService
{
	private final static int indexsize = 40;
	
	@Autowired
	IDriverClassDao iDriverClassDao;
	
	@Autowired
	ITShareTagDao itShareTagDao;
	
	@Autowired
	IUserDao iUserDao;
	@Autowired
	IPicPathDao iPicPathDao;
	@Autowired
	IPicTypeDao iPicTypeDao;
	@Autowired
	IAppSwitchDao iAppSwitchDao;
	@Autowired
	IUserStatusDao iUserStatusDao;
	@Autowired
	ICarStylePicDao iCarStylePicDao;
	
	@Autowired
	IGpsRateDao iGpsRateDao;
	
	@Autowired
	ISubsuppliersDao iSubsuppliersDao;
	
	/**
	 * @Description: TODO(司机分类List)
	 * @param helper
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:58:09
	 */
	public Map< String , Object > findDriverClassList( HqlHelper helper )
	{
		return iDriverClassDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: TODO(根据ID查找司机分类信息)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:32:03
	 */
	public TDriverClass getDriverClassById( int parseInt )
	{
		return iDriverClassDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param driverClass
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:34:11
	 */
	@CacheEvict( value = "driverClassCache" , beforeInvocation = true )
	public void saveOrUpdateDriverClass( TDriverClass driverClass )
	{
		iDriverClassDao.saveOrUpdate( driverClass );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param driverClass
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:34:47
	 */
	@CacheEvict( value = "driverClassCache" , beforeInvocation = true )
	public void saveDriverClass( TDriverClass driverClass )
	{
		iDriverClassDao.save( driverClass );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-26 上午9:43:10
	 */
	public TShareTag getShareTagById( int id )
	{
		return itShareTagDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-26 上午9:45:04
	 */
	public Map< String , Object > findShareTagList( HqlHelper helper )
	{
		return itShareTagDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: TODO(保存分享圈分类标签)
	 * @param shareTag
	 * @author liuwu
	 * @create_date 2015-3-26 上午9:54:10
	 */
	@CacheEvict( value = "shareTagCache" )
	public void saveOrUpdateShareTag( TShareTag shareTag )
	{
		itShareTagDao.saveOrUpdate( shareTag );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param shareTag
	 * @author liuwu
	 * @create_date 2015-3-26 上午9:55:07
	 */
	@CacheEvict( value = "shareTagCache" )
	public void saveShareTag( TShareTag shareTag )
	{
		itShareTagDao.save( shareTag );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-26 上午10:24:41
	 */
	public List< TShareTag > findAllShareTags()
	{
		return itShareTagDao.findAll();
	}
	
	/**
	 * @Description: TODO(消息推送)
	 * @param type
	 *            类型：0-所有人 1：内部;2:分供方;3:司机;4:金融公司
	 * @param shareTitle
	 *            消息标题
	 * @param shareMessage
	 *            消息内容 void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 下午2:33:41
	 */
	public void pushMessageByType( String type , String shareTitle , String shareMessage )
	{
		if ( type.equals( "0" ) ) // 所有人都发送
		{
			List< TUser > users = iUserDao.findAll();
			if ( users.size() > indexsize )
			{
				int size = users.size() % indexsize == 0 ? users.size() / indexsize
				        : users.size() / indexsize + 1;
				List< String > tokens = new ArrayList< String >();
				int index = 0;
				for ( int i = 1 ; i <= size ; i++ )
				{
					index++ ;
					StringBuffer stringBuffer = new StringBuffer();
					int maxIndex = 0;
					if ( indexsize * index < users.size() )
					{
						maxIndex = index * indexsize;
					}
					else
					{
						maxIndex = users.size();
					}
					for ( int i2 = indexsize * ( index - 1 ) ; i2 < maxIndex ; i2++ )
					{
						stringBuffer.append( users.get( i2 ).getVcDeviceTokens() + "," );
					}
					int lastIndex = stringBuffer.lastIndexOf( "," );
					
					String deviceTokens = stringBuffer.deleteCharAt( lastIndex )
					        .toString();
					PushMessageUtil pUtil = new PushMessageUtil();
					try
					{
						pUtil.sendAndroidBroadcastType2( shareTitle , shareMessage ,
						        deviceTokens , deviceTokens , null );// 所有人
					}
					catch ( Exception e )
					{
						e.printStackTrace();
					}
				}
			}
			else
			// 手机数量小于500的情况下
			{
				PushMessageUtil pUtil = new PushMessageUtil();
				try
				{
					pUtil.sendAndroidBroadcastType1( shareTitle , shareMessage );// 所有人
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			
			List< TUser > users = iUserDao.findAllByType( type );
			if ( users.size() > indexsize )
			{
				int size = users.size() % indexsize == 0 ? users.size() / indexsize
				        : users.size() / indexsize + 1;
				List< String > tokens = new ArrayList< String >();
				int index = 0;
				for ( int i = 1 ; i <= size ; i++ )
				{
					index++ ;
					StringBuffer stringBuffer = new StringBuffer();
					int maxIndex = 0;
					if ( indexsize * index < users.size() )
					{
						maxIndex = index * indexsize;
					}
					else
					{
						maxIndex = users.size();
					}
					for ( int i2 = indexsize * ( index - 1 ) ; i2 < maxIndex ; i2++ )
					{
						stringBuffer.append( users.get( i2 ).getVcDeviceTokens() + "," );
					}
					int lastIndex = stringBuffer.lastIndexOf( "," );
					
					String deviceTokens = stringBuffer.deleteCharAt( lastIndex )
					        .toString();
					PushMessageUtil pUtil = new PushMessageUtil();
					try
					{
						pUtil.sendAndroidBroadcastType2( shareTitle , shareMessage ,
						        deviceTokens , deviceTokens , null );// 所有人
					}
					catch ( Exception e )
					{
						e.printStackTrace();
					}
				}
			}
			else
			// 手机数量小于500的情况下
			{
				PushMessageUtil pUtil = new PushMessageUtil();
				try
				{
					pUtil.sendAndroidBroadcastType1( shareTitle , shareMessage );// 所有人
				}
				catch ( Exception e )
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-26 下午5:37:06
	 */
	public Map< String , Object > findPicPathList( HqlHelper helper )
	{
		return iPicPathDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-26 下午5:52:41
	 */
	public TPicPath getPicPathById( int parseInt )
	{
		return iPicPathDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-27 上午9:50:47
	 */
	public List< TPicType > findAllPicTypes()
	{
		return iPicTypeDao.findAll();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tPicPath
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:19:27
	 */
	@CacheEvict( value = "picPathCache" , beforeInvocation = true )
	public void saveOrUpdateTpicPath( TPicPath tPicPath )
	{
		iPicPathDao.saveOrUpdate( tPicPath );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tPicPath
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:19:59
	 */
	@CacheEvict( value = "picPathCache" , beforeInvocation = true )
	public void saveTPicPath( TPicPath tPicPath )
	{
		iPicPathDao.save( tPicPath );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:38:18
	 */
	public Map< String , Object > findPicTypeList( HqlHelper helper )
	{
		return iPicTypeDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:45:46
	 */
	public TPicType getPicTypeById( int parseInt )
	{
		return iPicTypeDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tPicType
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:54:23
	 */
	@CacheEvict( value = "picPathCache" , beforeInvocation = true )
	public void saveOrUpdateTpicType( TPicType tPicType )
	{
		iPicTypeDao.saveOrUpdate( tPicType );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tPicType
	 * @author liuwu
	 * @create_date 2015-3-27 上午10:54:54
	 */
	@CacheEvict( value = "picPathCache" , beforeInvocation = true )
	public void saveTPicType( TPicType tPicType )
	{
		iPicTypeDao.save( tPicType );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-27 上午11:46:05
	 */
	public Map< String , Object > findAppSwitchList( HqlHelper helper )
	{
		return iAppSwitchDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-27 下午2:14:31
	 */
	public TAppSwitch getAppSwitchById( int parseInt )
	{
		return iAppSwitchDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tAppSwitch
	 * @author liuwu
	 * @create_date 2015-3-27 下午4:16:19
	 */
	public void saveOrUpdateAppSwitch( TAppSwitch tAppSwitch )
	{
		// TODO Auto-generated method stub
		iAppSwitchDao.saveOrUpdate( tAppSwitch );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tAppSwitch
	 * @author liuwu
	 * @create_date 2015-3-27 下午4:16:19
	 */
	public void saveAppSwitch( TAppSwitch tAppSwitch )
	{
		// TODO Auto-generated method stub
		iAppSwitchDao.save( tAppSwitch );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-30 下午1:49:10
	 */
	public Map< String , Object > findUserStatusList( HqlHelper helper )
	{
		// TODO Auto-generated method stub
		return iUserStatusDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-30 下午1:55:04
	 */
	public TUserStatus getUserStatusById( int parseInt )
	{
		// TODO Auto-generated method stub
		return iUserStatusDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tUserStatus
	 * @author liuwu
	 * @create_date 2015-3-30 下午2:10:52
	 */
	public void saveOrUpdateUserStatus( TUserStatus tUserStatus )
	{
		// TODO Auto-generated method stub
		iUserStatusDao.saveOrUpdate( tUserStatus );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tUserStatus
	 * @author liuwu
	 * @create_date 2015-3-30 下午2:11:20
	 */
	public void saveUserStatus( TUserStatus tUserStatus )
	{
		// TODO Auto-generated method stub
		iUserStatusDao.save( tUserStatus );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-30 下午5:38:51
	 */
	public Map< String , Object > findTCarStylePicList( HqlHelper helper )
	{
		// TODO Auto-generated method stub
		return iCarStylePicDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * 
	 * @Description: TODO(根据ID查出商标图片)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-30 下午5:46:44
	 */
	public TCarStylePic getCarStylePicById( int parseInt )
	{
		// TODO Auto-generated method stub
		return iCarStylePicDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(保存汽车商标图片)
	 * @param tCarStylePic
	 * @author liuwu
	 * @create_date 2015-3-30 下午6:07:46
	 */
	public void saveOrUpdateCarStylePic( TCarStylePic tCarStylePic )
	{
		// TODO Auto-generated method stub
		iCarStylePicDao.saveOrUpdate( tCarStylePic );
	}
	
	/**
	 * 
	 * @Description: TODO(保存汽车商标图片)
	 * @param tCarStylePic
	 * @author liuwu
	 * @create_date 2015-3-30 下午6:08:48
	 */
	public void saveCarStylePic( TCarStylePic tCarStylePic )
	{
		// TODO Auto-generated method stub
		iCarStylePicDao.save( tCarStylePic );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param helper
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-21 下午5:33:51
	 */
	public Map< String , Object > findGpsRate( HqlHelper helper )
	{
		// TODO Auto-generated method stub
		return iGpsRateDao.findAllByHqlHelp( helper );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-22 上午9:46:49
	 */
	public TGpsrate getGpsRateById( int parseInt )
	{
		// TODO Auto-generated method stub
		return iGpsRateDao.get( parseInt );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tGpsrate
	 * @author liuwu
	 * @create_date 2015-4-22 上午10:23:36
	 */
	public void updateGpsRate( TGpsrate tGpsrate )
	{
		
		iGpsRateDao.saveOrUpdate( tGpsrate );
		
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @author liuwu
	 * @create_date 2015-4-22 上午10:34:58
	 */
	public void deleteGPSrateById( int parseInt )
	{
		iGpsRateDao.deleteByKey( parseInt );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author liuwu
	 * @return
	 * @create_date 2015-4-22 下午1:54:13
	 */
	
	@SuppressWarnings( { "unchecked" , "rawtypes" } )
	public String updateDriverInfo( TUser user , HttpServletRequest request )
	{
		
		String subbo = iSubsuppliersDao.get( user.getiArchive() ).getVcSubno();
		
		String info = iGpsRateDao.updateByProcess( subbo );
		return info;
		
	}
	
}
