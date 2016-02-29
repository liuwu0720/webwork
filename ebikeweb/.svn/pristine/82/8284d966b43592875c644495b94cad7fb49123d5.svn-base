package com.clt.sub.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ISubCarStyleDao;
import com.clt.sub.model.TSubCarStyle;
import com.clt.sub.service.ISubCarStyleService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

@Service
public class SubCarStyleServiceImp implements ISubCarStyleService
{
	
	@Autowired
	ISubCarStyleDao subCarStyleDao;
	
	public TSubCarStyle get( Integer id )
	{
		// TODO Auto-generated method stub
		return subCarStyleDao.get( id );
	}
	
	public void update( TSubCarStyle entity )
	{
		// TODO Auto-generated method stub
		subCarStyleDao.update( entity );
	}
	
	public void save( TSubCarStyle entity )
	{
		// TODO Auto-generated method stub
		subCarStyleDao.save( entity );
	}
	
	public void saveOrUpdate( TSubCarStyle entity )
	{
		// TODO Auto-generated method stub
		subCarStyleDao.saveOrUpdate( entity );
	}
	
	public void delete( TSubCarStyle entity )
	{
		// TODO Auto-generated method stub
		subCarStyleDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		subCarStyleDao.deleteByKey( id );
	}
	
	public List< TSubCarStyle > loadAll()
	{
		// TODO Auto-generated method stub
		return subCarStyleDao.loadAll();
	}
	
	public List< TSubCarStyle > findAll( String subno )
	{
		// TODO Auto-generated method stub
		return subCarStyleDao.findByPropertys( new String[] { "NEnable" , "vcSubno" } ,
		        new Object[] { 0 , subno } );
	}
	
	public List< TSubCarStyle > findAllByPage( String subno , Page page )
	{
		return subCarStyleDao.findByPropertys( new String[] { "NEnable" , "vcSubno" } ,
		        new Object[] { 0 , subno } , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2014-12-23 下午3:34:20
	 */
	public List< TSubCarStyle > findAllBySubNo(String subbo )
	{
		String hql = "from TSubCarStyle where vcSubno='" + subbo
		        + "' and NEnable=0";
		List< TSubCarStyle > tSubCarStyles = subCarStyleDao.find( hql );
		return tSubCarStyles;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-8-6 上午11:17:24
	 */
	public Map< String , Object > findAllByHql( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return subCarStyleDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param properties
	 * @param values
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-1 下午1:59:53
	 */
	public List< TSubCarStyle > findByProperties( String[] properties ,
	        Object[] values )
	{
		// TODO Auto-generated method stub
		return subCarStyleDao.findByPropertys( properties , values );
	}
	
}
