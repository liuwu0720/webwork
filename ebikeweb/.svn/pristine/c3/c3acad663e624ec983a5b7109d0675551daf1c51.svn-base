package com.clt.sub.dao.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.sub.dao.IDriverDao;
import com.clt.sub.model.TDriver;

@Repository
public class DriverDaoImp extends GenericHibernateDao< TDriver , Integer > implements
        IDriverDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * @Description: TODO(检查是否存在相同的数据)
	 * @param sql
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-22 下午5:25:05
	 */
	public String findIfExistSame( String sql )
	{
		int count = jdbcTemplate.queryForInt( sql );
		if ( count > 0 )
		{
			return "" + count;
		}
		else
		{
			return "success";
		}
		
	}
	
}
