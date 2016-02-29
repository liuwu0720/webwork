package com.clt.sub.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IOrderDao;
import com.clt.sub.model.TOrder;

@Repository
public class OrderDaoImp extends GenericHibernateDao< TOrder , Integer > implements
        IOrderDao
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param ordno
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-6 下午1:29:44
	 */
	public TOrder getOrderByOrderNo( String ordno )
	{
		List< TOrder > ordlist = findByPropertys(
		        new String[] { "vcOrderno" , "NEnable" } , new Object[] { ordno , 0 } );
		if ( ordlist.size() != 1 )
		{
			System.out.println( "获取订单出错，" + ordno + "  订单号有重复" );
			return null;
		}
		else
		{
			return ordlist.get( 0 );
		}
	}
	
}
