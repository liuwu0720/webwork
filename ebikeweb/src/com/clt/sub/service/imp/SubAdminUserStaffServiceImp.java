/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午2:17:37 
 * @version V1.0 
 */
package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IRoleCompanyDao;
import com.clt.sub.dao.ISubStaffDao;
import com.clt.sub.model.TRoleCompany;
import com.clt.sub.model.TSubstaff;
import com.clt.sub.service.ISubAdminUserStaffService;
import com.clt.systemmanger.dao.IRoleDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/** 
 * @Package com.clt.sub.service.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午2:17:37 
 * @version V1.0 
 */
@Service
public class SubAdminUserStaffServiceImp implements ISubAdminUserStaffService
{
	@Autowired
	IRoleDao iRoleDao;
	
	@Autowired
	IUserDao iUserDao;
	
	@Autowired
	ISubStaffDao iSubStaffDao;
	
	@Autowired
	IRoleCompanyDao iRoleCompanyDao;
	
	@Autowired
	private IUserService userService;
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-12 下午2:20:52
	 */
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return iRoleDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tUser
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-15 下午3:42:37
	 */
	public boolean checkUserExist( TUser tUser )
	{
		List< TUser > users = iUserDao.findByPropertys( new String[]{"vcAccount","NEnable"} , new Object[]{
		        tUser.getVcAccount() , SystemConstants.SYS_ENABLE } );
		if ( users.size() > 0 )
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param substaff
	 * @author liuwu
	 * @create_date 2015-1-15 下午4:11:31
	 */
	public void saveSubStaffUserInfo( TSubstaff substaff )
	{
		iSubStaffDao.save( substaff );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param substaff
	 * @author liuwu
	 * @create_date 2015-1-15 下午4:36:20
	 */
	public void updateSubStaffUserInfo( TSubstaff substaff )
	{
		iSubStaffDao.update( substaff );
	}
	
	/**
	 * @Description: TODO(根据用户Id查出可授权的角色)
	 * @param userId
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-16 上午10:17:43
	 */
	public String getUserRoleByUserId( String userId )
	{
		// 1:找出该 用户的领导
		List< TSubstaff > substaffs = iSubStaffDao.findByProperty( "iUserId" ,
		        Integer.parseInt( userId ) );
		// 2:找出该领导所创建的角色ID
		List< TRoleCompany > roleCompanies = new ArrayList< TRoleCompany >();
		for ( TSubstaff tSubstaff : substaffs )
		{
			List< TRoleCompany > tRoleCompanies = iRoleCompanyDao
			        .findByPropertys( new String[] { "iUserId" , "nEnable" } ,
			                new Object[] { tSubstaff.getiLeadId() ,
			                        SystemConstants.SYS_ENABLE } );
			roleCompanies.addAll( tRoleCompanies );
		}
		List< TRole > tRoles = new ArrayList< TRole >();
		// 3找出角色名称
		for ( TRoleCompany tRoleCompany : roleCompanies )
		{
			List< TRole > roles = iRoleDao.findByPropertys( new String[] {
			        "id" , "NEnable" } ,
			        new Object[] { tRoleCompany.getRoleId() ,
			                SystemConstants.SYS_ENABLE } );
			tRoles.addAll( roles );
		}
		TUser user = userService.getByid( userId );
		List< TRole > uRoles = new ArrayList< TRole >();// 已有的角色
		if ( null != user )
		{
			uRoles = userService.getRoleByUser( user );
		}
		JSONArray arr = new JSONArray();
		for ( TRole r : tRoles )
		{
			JSONObject obj = new JSONObject();
			
			obj.element( "id" , r.getId() );
			obj.element( "pId" , 0 );
			obj.element( "name" , r.getVcRoleName() );
			obj.element( "t" , r.getVcDesc() );
			if ( CollectionUtils.isNotEmpty( uRoles ) )
			{
				if ( uRoles.contains( r ) )
				{
					obj.element( "checked" , true );
				}
				else
				{
					obj.element( "checked" , false );
				}
			}
			else
			{
				obj.put( "checked" , false );
			}
			arr.add( obj );
		}
		return arr.toString();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ids
	 * @author liuwu
	 * @create_date 2015-1-16 下午4:44:43
	 */
	public void updateUserAcountDisable( String[] ids )
	{
		List< TSubstaff > iSubstaffs = new ArrayList< TSubstaff >();
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int userId = Integer.parseInt( ids[i] );
			TUser user = iUserDao.get( userId );
			user.setNEnable( SystemConstants.SYS_DISABLE );
			iUserDao.saveOrUpdate( user );
			List< TSubstaff > tList = iSubStaffDao.findByProperty(
			        "iUserId" , user.getId() );
			iSubstaffs.addAll( tList );
		}
		for ( TSubstaff tSubstaff : iSubstaffs )
		{
			tSubstaff.setnEnable( SystemConstants.SYS_DISABLE );
			iSubStaffDao.saveOrUpdate( tSubstaff );
		}
		
	}
	
}
