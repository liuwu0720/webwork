/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-4-21 下午5:36:06 
 * @version V1.0 
 */
package com.clt.systemmanger.dao.imp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IGpsRateDao;
import com.clt.systemmanger.model.TGpsrate;

/** 
 * @Package com.clt.systemmanger.dao.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-4-21 下午5:36:06 
 * @version V1.0 
 */
@Repository
public class IGpsRateDaoImp extends GenericHibernateDao< TGpsrate , Integer >
        implements IGpsRateDao
{
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param subbo
	 * @author liuwu
	 * @return
	 * @create_date 2015-4-22 下午3:23:08
	 */
	@SuppressWarnings( { "rawtypes" , "unchecked" } )
	public String updateByProcess( String subbo )
	{
		String callSql = "{Call synchronousdriverrate(?,?)}";
		ResultSet rs = null;
		Session session = this.getHibernateTemplate().getSessionFactory()
		        .openSession();
		String count = null;
		try
		{
			
			@SuppressWarnings( "deprecation" )
			Connection conn = session.connection();
			conn.setAutoCommit( false );
			CallableStatement statement = conn.prepareCall( callSql );
			statement.setString( 1 , subbo );
			statement.registerOutParameter( 2 , Types.VARCHAR );
			statement.execute();
			count = statement.getString( 2 );
			conn.commit();
			statement.close();
			conn.close();
			session.close();

		}
		catch ( Exception e )
		{
			e.printStackTrace();

		}
		return "新增了" + count + "条记录！";
		
	}
	
}
