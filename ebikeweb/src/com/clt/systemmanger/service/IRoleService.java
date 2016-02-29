package com.clt.systemmanger.service;

import java.util.List;
import java.util.Map;

import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUserRole;
import com.clt.util.HqlHelper;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IRoleService
{
	public TRole get( Integer id );
	
	public void update( TRole entity );
	
	public void save( TRole entity );
	
	public void saveOrUpdate( TRole entity );
	
	public void delete( TRole entity );
	
	public void deleteByKey( Integer id );
	
	public List< TRole > loadAll();
	
	/**
	 * @Description: 加载有效的角色
	 * @return List<TRole> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午6:41:03
	 */
	public List< TRole > loadAllByEnable();
	
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	public List< TRole > find( String hql );
	
	/**
	 * @Description: TODO(查出拥有该角色的用户)
	 * @param role
	 * @return TUserRole 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-14 下午4:36:18
	 */
	public List< TUserRole > getStaffRole( TRole role );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return List<TRole> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-26 下午5:17:48
	 */
	public List< TRole > findDriverRole();
	
}
