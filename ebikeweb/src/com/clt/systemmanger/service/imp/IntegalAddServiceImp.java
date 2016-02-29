package com.clt.systemmanger.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IIntegalAddDao;
import com.clt.systemmanger.dao.IIntegalCutDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TIntegalAdd;
import com.clt.systemmanger.service.IIntegalAddService;
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
public class IntegalAddServiceImp implements IIntegalAddService
{
	
	@Autowired
	private IIntegalCutDao integalCutDao;
	@Autowired
	private IIntegalAddDao integalAddDao;
	
	@Autowired
	private IUserDao userDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public TIntegalAdd get( Integer id )
	{
		// TODO Auto-generated method stub
		return integalAddDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param code
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public TIntegalAdd geTIntegalAddByCode( String code )
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public void update( TIntegalAdd entity )
	{
		// TODO Auto-generated method stub
		integalAddDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public void save( TIntegalAdd entity )
	{
		// TODO Auto-generated method stub
		integalAddDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public void saveOrUpdate( TIntegalAdd entity )
	{
		// TODO Auto-generated method stub
		integalAddDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public List< TIntegalAdd > loadAll()
	{
		// TODO Auto-generated method stub
		return integalAddDao.loadAll();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public List< TIntegalAdd > loadByEnableAndSort()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return integalAddDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public int getCountSQL( String sql )
	{
		// TODO Auto-generated method stub
		return integalAddDao.getCountSQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public List< String[] > getDateBySQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return integalAddDao.getDateBySQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public Object getDateBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return integalAddDao.getDateBySQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-11 下午5:29:30
	 */
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return integalAddDao.getSpringSQL( sql , page );
	}
	
}
