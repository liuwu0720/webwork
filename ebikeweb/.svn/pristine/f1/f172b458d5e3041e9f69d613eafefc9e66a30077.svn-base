package com.clt.sub.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IArkilometerDao;
import com.clt.sub.model.TArkilometer;
import com.clt.sub.service.IArkilometerService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class ArkilometerServiceImp implements IArkilometerService
{
	@Autowired
	IArkilometerDao arkilometerDao;
	
	public TArkilometer get( Integer id )
	{
		// TODO Auto-generated method stub
		return arkilometerDao.get( id );
	}
	
	public void update( TArkilometer entity )
	{

		arkilometerDao.update( entity );
	}
	
	public void save( TArkilometer entity )
	{
		// TODO Auto-generated method stub
		arkilometerDao.save( entity );
	}
	
	public void saveOrUpdate( TArkilometer entity )
	{

		arkilometerDao.saveOrUpdate( entity );
	}
	
	public void delete( TArkilometer entity )
	{
		// TODO Auto-generated method stub
		arkilometerDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		arkilometerDao.deleteByKey( id );
	}
	
	public List< TArkilometer > loadAll()
	{
		// TODO Auto-generated method stub
		return arkilometerDao.loadAll();
	}
	
	public List< TArkilometer > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return arkilometerDao.findAll( page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-16 下午7:00:38
	 */
	public List< TArkilometer > findByPropertys( String[] propertyNames ,
	        Object[] values , Page page )
	{
		// TODO Auto-generated method stub
		return arkilometerDao.findByPropertys( propertyNames , values , page );
	}
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		return arkilometerDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-22 下午2:14:24
	 */
	public List< String[] > getDateBySQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return arkilometerDao.getDateBySQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param startID
	 * @param endID
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-30 上午11:25:41
	 */
	public float getKilomter( int startID , int endID )
	{
		String sql = "from TArkilometer where IStartId = " + startID
		        + " and IEndId = " + endID + " and dtEnd >sysdate ";
		List< TArkilometer > list = arkilometerDao.find( sql );
		if ( list.size() > 0 )
		{
			TArkilometer tArkilometer = list.get( 0 );
			return tArkilometer.getNKilometer();
		}
		return 0;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-19 上午9:24:19
	 */
	public Map< String , Object > findBySpringSql( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return arkilometerDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tArkilometer
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-19 上午11:38:03
	 */
	public List< TArkilometer > checkIfExiste( TArkilometer tArkilometer )
	{

		List< TArkilometer > tArkilometers = arkilometerDao.findByPropertys(
		        new String[] { "IStartId" , "IEndId" , "iCustomerId" ,
		                        "vcSubno" , "NEnable" } ,
		                new Object[] { tArkilometer.getIStartId() ,
		                        tArkilometer.getIEndId() ,
		                        tArkilometer.getiCustomerId() ,
		                        tArkilometer.getVcSubno() ,
		                        SystemConstants.SYS_ENABLE } );
		
		return tArkilometers;
	}
	
	/**
	 * @Description: TODO(防止同一session两个对象时Hibernate报错)
	 * @param ark
	 * @author liuwu
	 * @create_date 2015-5-19 下午5:57:40
	 */
	public void saveOrUpdateCleanBefore( TArkilometer ark )
	{
		// TODO Auto-generated method stub
		arkilometerDao.updateCleanBefore( ark );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tArkilometers
	 * @author liuwu
	 * @create_date 2015-8-21 下午2:29:35
	 */
	public void saveOrUpdateList( List< TArkilometer > tArkilometers )
	{
		arkilometerDao.saveOrUpdateAll( tArkilometers );
		
	}
}
