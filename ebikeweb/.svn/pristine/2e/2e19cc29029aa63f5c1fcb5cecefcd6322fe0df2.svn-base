package com.clt.sub.dao.imp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IDriverCostDao;
import com.clt.sub.model.TDriverCost;
import com.clt.util.DateUtil;

@Repository
public class DriverCostDaoImp extends GenericHibernateDao< TDriverCost , Integer >
        implements IDriverCostDao
{
	
	public TDriverCost getTDriverCostByDriID( String subno , int driID )
	{
		String datestr = DateUtil.getDate( "yyyy-MM-dd" );
		// and to_char(ark.dtStart,'yyyy-MM-dd') between ? and ?
		/*String sql = " select ark.id from t_driver_cost ark where ark.i_truck_id ="
		        + driID + "  and ark.vc_subno='" + subno + "'  to_date('" + datestr
		        + "','yyyy/mm/dd ') > ark.dt_start  and to_date('" + datestr
		        + "','yyyy/mm/dd ')  <= ark.dt_end  ";
		System.out.println( "getTDriverCostByDriID sql " + sql );*/
		String hql = " from TDriverCost t where t.ITruckId =? ";
		List< TDriverCost > list = super.find( hql , new Object[] { driID } );
		if ( list.size() > 1 )
		{
			System.out.println( "该车有多条单价纪录 " + driID );
			return null;
		}
		else if ( list.size() == 1 )
		{
			TDriverCost dricost = list.get( 0 );
			return dricost;
		}
		else
		{
			return null;
		}
	}
}
