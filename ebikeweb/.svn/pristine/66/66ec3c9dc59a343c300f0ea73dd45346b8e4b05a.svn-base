/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午3:30:44 
 * @version V1.0 
 */
package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IRoleCompanyDao;
import com.clt.sub.model.TRoleCompany;
import com.clt.sub.service.IRoleCompanyService;
import com.clt.systemmanger.dao.IRoleDao;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUserRole;
import com.clt.util.SystemConstants;

/** 
 * @Package com.clt.sub.service.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-12 下午3:30:44 
 * @version V1.0 
 */
@Service
public class RoleCompanyServiceImp implements IRoleCompanyService
{
	@Autowired
	IRoleCompanyDao iRoleCompanyDao;
	@Autowired
	IRoleDao iRoleDao;

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param roleCompany
	 * @author liuwu
	 * @create_date 2015-1-12 下午3:53:37
	 */
	public void saveRoleCompany( TRoleCompany roleCompany )
	{
		iRoleCompanyDao.save( roleCompany );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ids
	 * @author liuwu
	 * @create_date 2015-1-12 下午4:33:51
	 */
	public void updateSetDiable( String[] ids )
	{
		for ( int i = 0 ; i < ids.length ; i++ )
		{
			int id = Integer.parseInt( ids[i] );
			TRole role = iRoleDao.get( id );
			role.setNEnable( SystemConstants.SYS_DISABLE );
			iRoleDao.update( role );
			List< TRoleCompany > roleCompanies = iRoleCompanyDao
			        .findByProperty( "roleId" , role.getId() );
			TRoleCompany tRoleCompany = roleCompanies.get( 0 );
			tRoleCompany.setnEnable( SystemConstants.SYS_DISABLE );
			iRoleCompanyDao.update( tRoleCompany );
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tUserRole
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-14 下午4:52:35
	 */
	public List< TRoleCompany > getObjectByUserId( TUserRole tUserRole )
	{
		List< TRoleCompany > tRoleCompanies = iRoleCompanyDao.findByPropertys(
		        new String[] { "iUserId" , "nEnable" } , new Object[] {
		                tUserRole.getIUser() , SystemConstants.SYS_ENABLE } );
		return tRoleCompanies;
	}
	
}
