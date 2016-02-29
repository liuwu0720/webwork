/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-1-6 上午10:19:04
 * @version V1.0
 */
package com.clt.sub.service.imp;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ITruckFixDao;
import com.clt.sub.model.TApplyPic;
import com.clt.sub.model.TFixTruck;
import com.clt.sub.service.ITruckFixService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IStaticService;
import com.clt.util.HqlHelper;

/**
 * @Package com.clt.sub.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-1-6 上午10:19:04
 * @version V1.0
 */
@Service
public class TruckFixServiceImp implements ITruckFixService
{
	@Autowired
	ITruckFixDao iTruckFixDao;
	@Autowired
	private IStaticService staticService;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-6 上午10:19:18
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		return iTruckFixDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param array
	 * @param user
	 * @param subbo
	 * @param vcResult
	 * @param vcApplyNote
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-23 下午5:21:31
	 */
	public String updateSureList( String array , TUser user , String subbo ,
	        String vcResult , String vcNote )
	{
		
		int id = Integer.parseInt( array );
		try
		{
			TFixTruck tFixTruck = iTruckFixDao.get( id );
			if ( tFixTruck != null )
			{
				tFixTruck.setiApprove( user.getId() );// 审批人ID
				tFixTruck.setVcApproveName( user.getVcUsername() );// 审批人姓名
				tFixTruck.setDtApprove( new Date() );
				tFixTruck.setnApproveResult( Integer.parseInt( vcResult ) );
				tFixTruck.setVcNote( vcNote );
				iTruckFixDao.saveOrUpdate( tFixTruck );
			}
			else
			{
				return "找不到ID为" + id + "的候车申请审批列表详情！";
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
	 * @param tFixTruck
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:32:49
	 */
	public void save( TFixTruck tFixTruck )
	{
		// TODO Auto-generated method stub
		iTruckFixDao.save( tFixTruck );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFixTruck
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:34:42
	 */
	public void update( TFixTruck tFixTruck )
	{
		// TODO Auto-generated method stub
		iTruckFixDao.update( tFixTruck );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:39:01
	 */
	public TFixTruck findById( int id )
	{
		// TODO Auto-generated method stub
		return iTruckFixDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFixTruck
	 * @author liuwu
	 * @create_date 2015-6-4 下午1:36:52
	 */
	public void parseUrl( TFixTruck tFixTruck )
	{
		
		String perUrl = staticService.getStringByParame( "truckfix" );
		if ( tFixTruck != null )
		{
			
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}
			
			if ( tFixTruck.getVcPicPath() != null )
			{
				String jpgPathStr = tFixTruck.getVcPicPath();
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
				tFixTruck.setVcPicPath( vcImgpath );
			}
			
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tApplyPic
	 * @author liuwu
	 * @create_date 2015-9-9 下午5:25:26
	 */
	public void parseUrl( TApplyPic tApplyPic )
	{
		String perUrl = staticService.getStringByParame( "truckfix" );
		if ( tApplyPic != null )
		{
			
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}

			tApplyPic.setVcPicPath( perUrl + tApplyPic.getVcPicName() );
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param applyIds
	 * @param user
	 * @param subbo
	 * @param vcResult
	 * @param vcApplyNote
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-23 下午5:16:20
	 */
	/*public String updateSureList( String[] applyIds , TUser user ,
	        String subbo , String vcResult , String vcApplyNote )
	{
		for ( int i = 0 ; i < applyIds.length ; i++ )
		{
			int id = Integer.parseInt( applyIds[i] );
			TFixTruck tFixTruck = iTruckFixDao.get( id );
			tFixTruck.setiApprove( user.getId() );// 审批人ID
			tFixTruck.setVcApproveName( user.getVcUsername() );// 审批人姓名
			tFixTruck.setDtApprove( new Date() );
			tFixTruck.setnApproveResult( 0 );
			iTruckFixDao.saveOrUpdate( tFixTruck );
			
		}
		return "success";

	}*/
	
}
