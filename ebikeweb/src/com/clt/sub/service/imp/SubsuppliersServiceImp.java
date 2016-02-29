package com.clt.sub.service.imp;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.common.UserSession;
import com.clt.sub.dao.ISubsupperliersBankDao;
import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.model.TSubsuppliersBank;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.dao.IApplyResourceDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TApplyResource;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IStaticService;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class SubsuppliersServiceImp implements ISubsuppliersService
{
	
	@Autowired
	ISubsuppliersDao subsuppliersDao;
	@Autowired
	IApplyResourceDao applyResourceDao;
	@Autowired
	IUserDao userDao;
	@Autowired
	ISubsupperliersBankDao iSubsupperliersBankDao;
	@Autowired
	private IStaticService staticService;
	
	public TSubsuppliers get( Integer id )
	{
		return subsuppliersDao.get( id );
	}
	
	public void update( TSubsuppliers entity )
	{
		subsuppliersDao.update( entity );
	}
	
	public void updateCleanBefore( TSubsuppliers entity )
	{
		subsuppliersDao.updateCleanBefore( entity );
	}
	
	public void save( TSubsuppliers entity )
	{
		entity.setVcSubno( getMaxOrderNo() );
		subsuppliersDao.save( entity );
	}
	
	public void saveOrUpdate( TSubsuppliers entity )
	{
		subsuppliersDao.saveOrUpdate( entity );
	}
	
	public void delete( TSubsuppliers entity )
	{
		subsuppliersDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		subsuppliersDao.deleteByKey( id );
	}
	
	public List< TSubsuppliers > loadAll()
	{
		return subsuppliersDao.loadAll();
	}
	
	public List< TSubsuppliers > findAll( Page page )
	{
		return subsuppliersDao.findAll( page );
	}
	
	public List< TSubsuppliers > findByPropertys( String[] propertyNames , Object[] values )
	{
		return subsuppliersDao.findByPropertys( propertyNames , values );
	}
	
	public List< TSubsuppliers > findByProperty( String propertyName , Object value )
	{
		return subsuppliersDao.findByProperty( propertyName , value );
	}
	
	/**
	 * @Description: 生产分供方编号
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 下午3:20:23
	 */
	private synchronized String getMaxOrderNo()
	{
		
		List< TSubsuppliers > orderlist = subsuppliersDao.findAllAndOrderByProperty(
		        "id" , false );
		String orderno = "S";
		String datestr = DateUtil.getDate( "yyMMdd" );
		DecimalFormat df = new DecimalFormat( "0000" );
		String str2 = "";
		if ( orderlist.size() == 0 )
		{
			str2 = df.format( Integer.parseInt( "1" ) );
			
		}
		else
		{
			TSubsuppliers sub = orderlist.get( 0 );
			
			String str = sub.getVcSubno().substring( 7 , sub.getVcSubno().length() );
			str2 = df.format( Integer.parseInt( str ) + 1 );
		}
		
		orderno += datestr + str2;
		System.out.println( "Max Order " + orderno );
		return orderno;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-19 下午1:55:10
	 */
	public List< TSubsuppliers > findByPropertys( String[] propertyNames ,
	        Object[] values , Page page )
	{
		return subsuppliersDao.findByPropertys( propertyNames , values , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-19 下午1:55:10
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return subsuppliersDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: 分供方申请权限开通，根据预先定义的角色来给用户增加角色，角色跟权限已经做好了关联 只添加角色即可，需审批后再开通
	 * @param roleids
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-10 下午3:23:59
	 */
	public void saveApplyResourseByRoles( int usid , String roleids )
	{
		// TODO Auto-generated method stub
		Date dtbatch = new Date();
		String[] roleid = roleids.split( "," );
		for ( String rid : roleid )
		{
			TApplyResource appres = new TApplyResource();
			appres.setIUser( usid );
			appres.setIRole( Integer.parseInt( rid ) );
			appres.setDtBatch( dtbatch );
			applyResourceDao.save( appres );
		}
		
		// 修改该用户的状态为 正在申请权限
		TUser us = userDao.get( usid );
		us.setNApplyResource( 0 );
		userDao.update( us );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-7 上午11:31:31
	 */
	public Map< String , Object > findSpringSql( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return subsuppliersDao.getSpringSQL( sql , page );
	}
	
	/**
	 * 获取可以抢单的分供方
	 * 
	 * @return
	 */
	public List< TSubsuppliers > getByEnableKill( String name )
	{
		List< TSubsuppliers > list = null;
		if ( StringUtils.isBlank( name ) )
		{
			
			String hql = "from TSubsuppliers a where a.NEnable="
			        + SystemConstants.SYS_ENABLE + " and a.NEnableKill="
			        + SystemConstants.SYS_ENABLE + " and a.vcAllName is not null ";
			
			TUser user = ( TUser ) UserSession.get( "user" );
			if ( null != user && null != user.getiArchive() )
			{
				hql += " and a.id !=" + user.getiArchive();
			}
			
			list = subsuppliersDao.find( hql );
		}
		else
		{
			String hql = "from TSubsuppliers a where a.NEnable="
			        + SystemConstants.SYS_ENABLE + " and a.NEnableKill="
			        + SystemConstants.SYS_ENABLE + " and a.vcAllName like '%" + name
			        + "%'  and a.vcAllName is not null ";
			list = subsuppliersDao.find( hql );
		}
		
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return list;
		}
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSubsuppliersBank
	 * @author liuwu
	 * @create_date 2015-7-10 下午2:34:15
	 */
	public void saveSubBank( TSubsuppliersBank tSubsuppliersBank )
	{
		iSubsupperliersBankDao.save( tSubsuppliersBank );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSubsuppliersBank
	 * @author liuwu
	 * @create_date 2015-9-1 下午5:26:44
	 */
	public void saveUpdateSubBank( TSubsuppliersBank tSubsuppliersBank )
	{
		iSubsupperliersBankDao.saveOrUpdate( tSubsuppliersBank );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sub
	 * @author liuwu
	 * @create_date 2015-9-9 上午10:25:37
	 */
	public void parseUrl( TSubsuppliers sub )
	{
		String perUrl = staticService.getStringByParame( "subUrl" );
		if ( sub != null )
		{
			
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}
			
			// tDamage.setVcPicpath( perUrl + tDamage.getVcPicpath() );
			/*String jpgPathStr = tApplyPic.getVcPicPath();
			String[] jpgPaths = jpgPathStr.split( "," );
			String vcImgpath = "";
			for ( int i = 0 ; i < jpgPaths.length ; i++ )
			{
				vcImgpath += perUrl + jpgPaths[i];
				if ( i != jpgPaths.length - 1 )
				{
					vcImgpath += ",";
				}
			}*/
			
			sub.setVcImgPath( perUrl + sub.getVcImgPath() );
			sub.setVcLicencePath( perUrl + sub.getVcLicencePath() );
			
		}
		
	}
}
