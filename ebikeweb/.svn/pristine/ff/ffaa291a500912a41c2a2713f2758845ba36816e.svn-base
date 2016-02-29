package com.clt.sub.dao.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IShipheadDao;
import com.clt.sub.model.TShiphead;

@Repository
public class ShipheadDaoImp extends GenericHibernateDao< TShiphead , Integer > implements
        IShipheadDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * @Description: 根据发运指令获取发运主表对象
	 * @param shipno
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-21 下午4:42:54
	 */
	public TShiphead getShipHeadByshipNo( String shipno )
	{
		// TODO Auto-generated method stub
		List< TShiphead > headlsit = super.findByProperty( "vcShipno" , shipno );
		if ( headlsit.size() > 1 )
		{
			System.out.println( "发运指令号 不唯一  有错误 " );
			return null;
		}
		else
		{
			return headlsit.get( 0 );
		}
	}
	

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-22 上午10:15:30
	 */
	public List< Map< String , Object >> excuteSql( String sql )
	{
		List< Map< String , Object >> arlist = jdbcTemplate.queryForList( sql );
		return arlist;
	}
	
}
