package com.clt.systemmanger.service.imp;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IIntegalCutDao;
import com.clt.systemmanger.dao.IIntegalDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TIntegalCut;
import com.clt.systemmanger.model.TIntegral;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IIntegalService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-10-9 下午2:57:28
 * @version V1.0
 */
@Service
public class IntegalServiceImp implements IIntegalService
{
	
	@Autowired
	private IIntegalCutDao integalCutDao;
	
	@Autowired
	private IIntegalDao integalDao;
	
	@Autowired
	private IUserDao userDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public TIntegral get( Integer id )
	{
		// TODO Auto-generated method stub
		return integalDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param code
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public TIntegral geTIntegralByCode( String code )
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public void update( TIntegral entity )
	{
		// TODO Auto-generated method stub
		integalDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public void save( TIntegral entity )
	{
		// TODO Auto-generated method stub
		integalDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public void saveOrUpdate( TIntegral entity )
	{
		// TODO Auto-generated method stub
		integalDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public List< TIntegral > loadAll()
	{
		// TODO Auto-generated method stub
		return integalDao.loadAll();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public List< TIntegral > loadByEnableAndSort()
	{
		// TODO Auto-generated method stub
		return integalDao.loadAllByEnable();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return integalDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public int getCountSQL( String sql )
	{
		// TODO Auto-generated method stub
		return integalDao.getCountSQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public List< String[] > getDateBySQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return integalDao.getDateBySQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public Object getDateBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return integalDao.getDateBySQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 上午11:13:57
	 */
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return integalDao.getSpringSQL( sql , page );
	}
	
	public List< TIntegral > findByProperties( String[] propertyNames , Object[] values )
	{
		return integalDao.findByPropertys( propertyNames , values );
	}
	
	/**
	 * @Description: 保存积分历史记录
	 * @param user
	 * @param paramType
	 * @param obj
	 * @author chenbin
	 * @create_date 2014-10-11 下午3:06:13
	 */
	public void saveTIntegral( TUser user , String paramType , int integalID )
	{
		// TODO Auto-generated method stub
		if ( paramType.equals( "add" ) )
		{
			
			// TIntegalAdd integaladd = ( TIntegalAdd ) obj;
			// TIntegral integal = new TIntegral();
			// integal.setDtChange( new Date() );
			// integal.setIUser( user.getId() );
			// integal.setNChange( integaladd.getNIntegal() );
			
		}
		if ( paramType.equals( "cut" ) )
		{
			
			TIntegalCut integalcut = integalCutDao.get( integalID );
			TIntegral integal = new TIntegral();
			integal.setDtChange( new Date() );
			integal.setIUser( user.getId() );
			integal.setNChange( integalcut.getNIntegal() );
			integal.setVcCause( integalcut.getVcCause() );
			integal.setNType( 1 );
			integalDao.save( integal );
		}
	}
	
}
