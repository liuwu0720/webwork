package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.model.TSubsuppliersBank;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.sub.service
 * @Description: 分供方档案表
 * @author chenbin
 * @date 2014-8-1 上午10:58:09
 * @version V1.0
 */
public interface ISubsuppliersService
{
	public TSubsuppliers get( Integer id );
	
	public void update( TSubsuppliers entity );
	
	public void save( TSubsuppliers entity );
	
	public void saveOrUpdate( TSubsuppliers entity );
	
	public void delete( TSubsuppliers entity );
	
	public void deleteByKey( Integer id );
	
	public List< TSubsuppliers > loadAll();
	
	public void updateCleanBefore( TSubsuppliers entity );
	
	public abstract List< TSubsuppliers > findAll( Page page );
	
	public List< TSubsuppliers > findByPropertys( String[] propertyNames , Object[] values );
	
	public List< TSubsuppliers > findByProperty( String propertyName , Object value );
	
	/**
	 * 
	 * @Description: 根据对象的属性和值 查询 并分页
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return List<TArkilometer> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午7:00:05
	 */
	public List< TSubsuppliers > findByPropertys( String[] propertyNames ,
	        Object[] values , Page page );
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	/**
	 * @Description: 分供方申请权限开通，根据预先定义的角色来给用户增加角色，角色跟权限已经做好了关联 只添加角色即可，需审批后再开通
	 * @param usid
	 *            申请的用户ID roleids 申请开通的角色ID 多个用逗号隔开 void 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-10 下午3:23:59
	 */
	public void saveApplyResourseByRoles( int usid , String roleids );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-7 上午11:31:19
	 */
	public Map< String , Object > findSpringSql( String sql , Page page );
	
	/**
	 * 获取可以抢单的分供方
	 * 
	 * @return
	 */
	public List< TSubsuppliers > getByEnableKill( String name );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSubsuppliersBank
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-10 下午2:34:03
	 */
	public void saveSubBank( TSubsuppliersBank tSubsuppliersBank );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tSubsuppliersBank
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-1 下午5:26:38
	 */
	public void saveUpdateSubBank( TSubsuppliersBank tSubsuppliersBank );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sub
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-9 上午10:25:27
	 */
	public void parseUrl( TSubsuppliers sub );
	
}
