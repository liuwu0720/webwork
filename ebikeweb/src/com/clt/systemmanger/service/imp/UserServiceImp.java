package com.clt.systemmanger.service.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.finance.model.TFinance;
import com.clt.finance.service.IFinanceService;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.dao.IApplyResourceDao;
import com.clt.systemmanger.dao.IIntegalCutDao;
import com.clt.systemmanger.dao.IRoleDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.dao.IUserRoleDao;
import com.clt.systemmanger.model.TApplyResource;
import com.clt.systemmanger.model.TIntegalCut;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TStores;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserRole;
import com.clt.systemmanger.service.IStoresService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.DataGridModel;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: 用户管理服务
 * @author hjx
 * @date 2014年7月12日 下午1:59:30
 * @version V1.0
 */
@Service
public class UserServiceImp implements IUserService
{
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserRoleDao urDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IApplyResourceDao applyResourceDao;
	
	@Autowired
	private ISubsuppliersService subService;
	
	@Autowired
	private IFinanceService finService;
	@Autowired
	private ITruckDriverService driverService;
	
	@Autowired
	private IIntegalCutDao integalCutService;
	@Autowired
	private IUserRoleDao uroleDao;
	@Autowired
	private IStoresService storeService;
	
	/**
	 * @Description: 通过用户账户获得用户
	 * @param account
	 *            用户账户
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午1:59:30
	 */
	public TUser getByAccount( String account )
	{
		List< TUser > list = userDao.findByProperty( "vcAccount" , account );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return list.get( 0 );
		}
		return null;
	}
	
	/**
	 * @Description: 根据用户id删除用户
	 * @param id
	 *            用户id
	 * @author hjx
	 * @create_date 2014年7月12日 下午1:59:30
	 */
	public void delUserbyId( Integer id )
	{
		userDao.deleteByKey( id );
		
	}
	
	/**
	 * @Description: 保存用户
	 * @param user
	 * @author hjx
	 * @create_date 2014年7月12日 下午1:59:30
	 */
	public void saveUser( TUser user )
	{
		userDao.save( user );
	}
	
	/**
	 * @Description: 更新用户
	 * @param user
	 * @author hjx
	 * @create_date 2014年7月12日 下午1:59:30
	 */
	public void updateUser( TUser user )
	{
		userDao.update( user );
		
	}
	
	public void updateCleanBefore( TUser user )
	{
		userDao.updateCleanBefore( user );
	}
	
	/**
	 * @Description: 通过用户id获得用户对象
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午1:59:30
	 */
	public TUser getById( Integer id )
	{
		return userDao.get( id );
		
	}
	
	/**
	 * @Description: 删除用户对象
	 * @param user
	 * @author hjx
	 * @create_date 2014年7月12日 下午2:02:52
	 */
	public void delUser( TUser user )
	{
		userDao.delete( user );
		
	}
	
	/**
	 * @Description: 根据用户获得，该用户所具有的角色List
	 * @param user
	 * @author hjx
	 * @create_date 2014年7月12日 下午3:33:48
	 */
	public List< TRole > getRoleByUser( TUser user )
	{
		List< TUserRole > urList = urDao.findByPropertys( new String[] { "IUser" ,
		        "IEnable" } , new Object[] { user.getId() , 0 } );
		List< TRole > roleList = new ArrayList< TRole >();
		if ( CollectionUtils.isNotEmpty( urList ) )
		{
			Iterator< TUserRole > urIt = urList.iterator();
			while ( urIt.hasNext() )
			{
				roleList.add( roleDao.get( urIt.next().getIRole() ) );
			}
		}
		return roleList;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param userId
	 * @return
	 * @author hjx
	 * @create_date 2014年7月14日 下午5:25:41
	 */
	public TUser getByid( String userId )
	{
		return userDao.get( Integer.parseInt( userId ) );
	}
	
	/**
	 * @Description: 获得有效的用户
	 * @return List<TUser> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午5:50:16
	 */
	public List< TUser > loadAllByEnable()
	{
		return userDao.loadAllByEnable();
	}
	
	/**
	 * @Description: 获得全部用户
	 * @return List<TUser> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午5:50:45
	 */
	public List< TUser > loadAll()
	{
		return userDao.loadAll();
	}
	
	/**
	 * @Description: 注册用户，并保存到对应的档案
	 * @param user
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 上午11:20:41
	 */
	public void saveRegister( TUser user , String tel )
	{
		int type = user.getIArchiveType();
		user.setVcPhone( tel );
		String rolehql = " from TRole ro where ro.NEnable=" + SystemConstants.SYS_ENABLE
		        + " and ro.vcRole=?";
		List< TRole > rolelist = null;
		
		switch ( type )
			{
				case SystemConstants.SYS_TARCHIVE_SUB :
					TSubsuppliers sub = new TSubsuppliers();
					sub.setVcTel( tel );
					subService.save( sub );
					user.setiArchive( sub.getId() );
					
					// 查询用户的基本角色
					rolelist = roleDao.find( rolehql ,
					        new Object[] { SystemConstants.SYS_SUB_ROLEBASE } );
					break;
				
				case SystemConstants.SYS_TARCHIVE_FINANCE :
					TFinance fin = new TFinance();
					fin.setVcTel( tel );
					finService.save( fin );
					user.setiArchive( fin.getId() );
					
					// 查询用户的基本角色
					rolelist = roleDao.find( rolehql ,
					        new Object[] { SystemConstants.SYS_FINANCE_ROLEBASE } );
					
					break;
				
				// case SystemConstants.SYS_TARCHIVE_DRIVER :
				// TTruckDriver dri = new TTruckDriver();
				// dri.setVcDriverTel( tel );
				// driverService.save( dri );
				// user.setiArchive( dri.getId() );
				// break;
				case SystemConstants.SYS_TARCHIVE_STORES :// 4s店
					TStores store = new TStores();
					store.setVcTel( tel );
					storeService.save( store );
					user.setiArchive( store.getId() );
					// 查询用户的基本角色
					rolelist = roleDao.find( rolehql ,
					        new Object[] { SystemConstants.SYS_STORES_ROLEBASE } );
					break;
				default :
					break;
			}
		
		userDao.save( user );
		
		TUserRole usrole = new TUserRole();
		usrole.setIRole( rolelist.get( 0 ).getId() );
		usrole.setIUser( user.getId() );
		urDao.save( usrole );
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return userDao.findAllByHqlHelp( helper );
	}
	
	public List< TUser > findAll( Page page )
	{
		return userDao.findAll( page );
	}
	
	public Map< String , Object > getPageList( DataGridModel dgm , TUser user )
	        throws Exception
	{
		return userDao.getPageList( dgm , user );
	}
	
	/**
	 * @Description: 修改用户积分
	 * @param user
	 * @param paramType
	 *            add 加分 cut 扣分
	 * @param obj
	 * @author chenbin
	 * @create_date 2014-10-11 下午3:40:46
	 */
	public void updateUserIntegal( TUser user , String paramType , int intagalID )
	{
		// TODO Auto-generated method stub
		if ( paramType.equals( "add" ) )
		{
			
			// TIntegalAdd integaladd = ( TIntegalAdd ) obj;
			// user.setNIntegral( user.getNIntegral() + integaladd.getNIntegal()
			// );
			// userDao.update( user );
		}
		if ( paramType.equals( "cut" ) )
		{
			TIntegalCut integalCut = integalCutService.get( intagalID );
			user.setNIntegral( user.getNIntegral() - integalCut.getNIntegal() );
			userDao.update( user );
		}
	}
	
	public void updateUserApplyResource( String userId )
	{
		// TODO Auto-generated method stub
		TUser us = userDao.get( Integer.parseInt( userId ) );
		us.setNApplyResource( 1 );
		us.setVcApplyNote( "" );
		userDao.update( us );
	}
	
	/**
	 * @Description: 获取用户最新申请的权限角色记录
	 * @param userId
	 * @author chenbin
	 * @create_date 2014-12-10 下午5:10:30
	 */
	public List< TApplyResource > getApplyResourseByUserID( int userId )
	{
		
		String hql = " select app2 from TApplyResource app2 where app2.dtBatch in ( select max(app.dtBatch) from TApplyResource app where app.IUser="
		        + userId + " )";
		List< TApplyResource > applist = applyResourceDao.find( hql );
		return applist;
	}
	
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		return userDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: 给用户增加角色权限
	 * @param appids
	 *            TApplyResource表id 逗号分隔 void 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-11 下午6:19:58
	 */
	public void saveUserRolsResource( String appids )
	{
		String[] appstrs = appids.split( "," );
		int usid = 0;
		for ( String str : appstrs )
		{
			TApplyResource appres = applyResourceDao.get( Integer.parseInt( str ) );
			appres.setNApplyStatus( 0 );
			
			TUserRole usrole = new TUserRole();
			usrole.setIUser( appres.getIUser() );
			usrole.setIRole( appres.getIRole() );
			
			if ( usid == 0 )
			{
				usid = appres.getIUser();
			}
			uroleDao.save( usrole );
			applyResourceDao.update( appres );
		}
		
		TUser us = userDao.get( usid );
		us.setNApplyResource( 2 );
		userDao.update( us );
	}
	
	public TUser getByPhone( String phone )
	{
		List< TUser > list = userDao.findByProperty( "vcPhone" , phone );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return list.get( 0 );
		}
		return null;
	}
	
	/**
	 * 根据司机id获得，该司机对应的user 对象
	 * 
	 * @param driverId
	 * @return
	 */
	public TUser getUserByDriverId( int driverId )
	{
		
		List< TUser > users = userDao.findByPropertys( new String[] { "IArchiveType" ,
		        "iArchive" } , new Object[] { SystemConstants.SYS_TARCHIVE_DRIVER ,
		        driverId } );
		if ( CollectionUtils.isNotEmpty( users ) )
		{
			return users.get( 0 );
		}
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tuser
	 * @author liuwu
	 * @create_date 2015-5-26 下午3:43:08
	 */
	public void saveUpdateUser( TUser tuser )
	{
		// TODO Auto-generated method stub
		userDao.saveOrUpdate( tuser );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param properties
	 * @param mainValues
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-25 上午11:01:01
	 */
	public List< TUser > findByProperties( String[] properties , Object[] mainValues )
	{
		// TODO Auto-generated method stub
		return userDao.findByPropertys( properties , mainValues );
	}
	
	public List< TUser > findByProperty( String propertyName , Object value )
	{
		return userDao.findByProperty( propertyName , value );
	}
}
