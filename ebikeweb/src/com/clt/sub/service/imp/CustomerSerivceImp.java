/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-16 下午2:54:28
 * @version V1.0
 */
package com.clt.sub.service.imp;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ICustomerDao;
import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.sub.model.TCustomer;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.sub.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2014-12-16 下午2:54:28
 * @version V1.0
 */
@Service
public class CustomerSerivceImp implements ICustomerSerivce
{
	@Autowired
	ICustomerDao iCustomerDao;
	@Autowired
	ISubsuppliersDao subsuppliersDao;
	
	/**
	 * @Description: TODO(根据对象查询并分页)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2014-12-16 下午2:54:39
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iCustomerDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parseInt
	 * @return
	 * @author liuwu
	 * @create_date 2014-12-16 下午4:20:35
	 */
	public TCustomer get( int id )
	{
		// TODO Auto-generated method stub
		return iCustomerDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param customer
	 * @author liuwu
	 * @create_date 2014-12-17 下午1:54:07
	 */
	public void saveOrUpdate( TCustomer customer )
	{
		iCustomerDao.saveOrUpdate( customer );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param customer
	 * @author liuwu
	 * @create_date 2014-12-17 下午2:11:38
	 */
	public void save( TCustomer customer )
	{
		iCustomerDao.save( customer );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param customer
	 * @author liuwu
	 * @create_date 2014-12-17 下午4:19:40
	 */
	public void update( TCustomer customer )
	{
		iCustomerDao.update( customer );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-12-18 下午4:38:20
	 */
	public List< TCustomer > findAllByEnable( Page page )
	{
		// TODO Auto-generated method stub
		return iCustomerDao.findAllByEnable( page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param vcSubno
	 * @return
	 * @author liuwu
	 * @create_date 2014-12-23 下午3:17:56
	 */
	public List< TCustomer > findAllBySubNo( String vcSubno )
	{
		String hql = "from TCustomer where vcSubno='" + vcSubno
		        + "' and NEnable=0";
		List< TCustomer > tCustomers = iCustomerDao.find( hql );
		return tCustomers;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2014-12-24 上午10:26:21
	 */
	public String getVcCustomerNo( String subbo )
	{

		String orderno = "C";
		String datestr = DateUtil.getDate( "yyMMddmmss" );
		DecimalFormat df = new DecimalFormat( "0000" );
		
		orderno += datestr + "_" + subbo;
		return orderno;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tCustomers
	 * @author liuwu
	 * @create_date 2014-12-24 下午3:24:59
	 */
	public void updateList( List< TCustomer > tCustomers )
	{
		for ( TCustomer tCustomer : tCustomers )
		{
			iCustomerDao.saveOrUpdate( tCustomer );
		}
		
	}
	
}
