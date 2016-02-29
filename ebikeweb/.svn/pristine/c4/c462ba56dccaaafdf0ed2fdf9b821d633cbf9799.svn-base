/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-16 下午2:53:13
 * @version V1.0
 */
package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TCustomer;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.sub.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-16 下午2:53:13
 * @version V1.0
 */
public interface ICustomerSerivce
{
	
	/**
	 * @Description: TODO(根据对象的属性和值 查询 并分页)
	 * @param hql
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-16 下午2:53:26
	 */
	Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return TCustomer 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-16 下午4:20:24
	 */
	TCustomer get( int id );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param customer
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-17 下午1:53:59
	 */
	void saveOrUpdate( TCustomer customer );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param customer
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-17 下午2:11:30
	 */
	void save( TCustomer customer );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param customer
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-17 下午4:19:31
	 */
	void update( TCustomer customer );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param customer
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-17 下午4:19:31
	 */
	public List< TCustomer > findAllByEnable( Page page );
	
	/**
	 * @Description: TODO(查出分供方对应的所有客户)
	 * @param vcSubno
	 * @return List<TCustomer> 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-23 下午3:17:43
	 */
	List< TCustomer > findAllBySubNo( String vcSubno );
	
	/**
	 * @Description: TODO(随机生成客户编号)
	 * @return String 返回值描述
	 * @author liuwu
	 * @param subbo
	 * @create_date 2014-12-24 上午10:26:04
	 */
	String getVcCustomerNo( String subbo );
	
	/**
	 * @Description: TODO(批量修改)
	 * @param tCustomers
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-24 下午3:24:44
	 */
	void updateList( List< TCustomer > tCustomers );
	
}
