package com.clt.sub.service.imp;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IDriverCostDao;
import com.clt.sub.dao.ISubsuppliersDao;
import com.clt.sub.model.TDriverCost;
import com.clt.sub.service.IDriverCostService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class DriverCostServiceImp implements IDriverCostService
{
	
	@Autowired
	IDriverCostDao driverCostDao;
	@Autowired
	ISubsuppliersDao subDao;
	
	public TDriverCost get( Integer id )
	{
		// TODO Auto-generated method stub
		return driverCostDao.get( id );
	}
	
	public void update( TDriverCost entity )
	{
		// TODO Auto-generated method stub
		driverCostDao.update( entity );
	}
	
	public void save( TDriverCost entity )
	{
		// TODO Auto-generated method stub
		driverCostDao.save( entity );
	}
	
	public void saveOrUpdate( TDriverCost entity )
	{
		// TODO Auto-generated method stub
		driverCostDao.saveOrUpdate( entity );
	}
	
	public void delete( TDriverCost entity )
	{
		// TODO Auto-generated method stub
		driverCostDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		driverCostDao.deleteByKey( id );
	}
	
	public List< TDriverCost > loadAll()
	{
		// TODO Auto-generated method stub
		return driverCostDao.loadAll();
	}
	
	public List< TDriverCost > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return driverCostDao.findAll( page );
	}
	
	/**
	 * @Description: 传 SQL 调用spring 分页
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-11 下午2:26:30
	 */
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return driverCostDao.getSpringSQL( sql , page );
	}
	
	/**
	 * 
	 * @Description: 批量保存应付单价
	 * @param msgstr
	 *            字符串 数组 格式 >> 车辆ID,单价,开始时间, 结束时间
	 * @author chenbin
	 * @throws ParseException
	 * @create_date 2014-8-11 下午5:34:45
	 */
	public void saveTDriverCosts( String[] msgstr , TUser us ) throws Exception
	{
		String subbo = subDao.get( us.getiArchive() ).getVcSubno();
		for ( String strs : msgstr )
		{
			// strs [0] 车辆司机ID [1] 单价 [2] 开始时间 [3] 结束时间
			String[] str = strs.split( "," );
			int driID = Integer.parseInt( str[0] );
			
			TDriverCost drivercost = driverCostDao.getTDriverCostByDriID(
			        subbo , driID );
			if ( drivercost == null )
			{
				drivercost = new TDriverCost();
			}
			
			drivercost.setDtStart( DateUtil.parseDate( str[2] ) );
			drivercost.setDtEnd( DateUtil.parseDate( str[3] ) );
			drivercost.setVcUpdateUser( us.getVcAccount() );
			drivercost.setnCost( Float.parseFloat( str[1] ) );
			drivercost.setNEnable( 0 );
			// drivercost.setITruckId( driID );
			drivercost.setVcSubno( subbo );
			
			driverCostDao.saveOrUpdate( drivercost );
			/*  Service 抛错 可手动抛错 运行时异常 
			 * try
			{	
			}
			catch ( Exception e )
			{
			System.out.println( e.getMessage() );
			throw new RuntimeException();
			}*/
			
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDriverCosts
	 * @author liuwu
	 * @create_date 2014-12-24 下午3:19:23
	 */
	public void saveOrUpdateList( List< TDriverCost > tDriverCosts )
	{
		for ( TDriverCost tDriverCost : tDriverCosts )
		{
			driverCostDao.saveOrUpdate( tDriverCost );
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param iCarStyleId
	 * @param stacity
	 * @param endcity
	 * @param iCustomerId
	 * @return
	 * @author liuwu
	 * @create_date 2014-12-25 下午3:25:54
	 */
	public float getCostPrice( int iCarStyleId , int stacity , int endcity ,
	        int iCustomerId , String subno )
	{
		String hql = "from TDriverCost where vcSubno='" + subno
		        + "' and IStartId = " + stacity + " and IEndId = " + endcity
		        + " and ICustomerid=" + iCustomerId + " and ISubCarid ="
		        + iCarStyleId + "and dtEnd > sysdate";
		List< TDriverCost > tDriverCosts = driverCostDao.find( hql );
		if ( tDriverCosts.size() > 0 )
		{
			TDriverCost tDriverCost = driverCostDao.find( hql ).get( 0 );
			System.out.println( "price = " + tDriverCost.getnCost() );
			return tDriverCost.getnCost();
		}
		else
		{
			return - 1;
		}

	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tDriverCost
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-19 上午11:59:02
	 */
	public List< TDriverCost > checkIfExist( TDriverCost tDriverCost )
	{
		List< TDriverCost > tDriverCosts = driverCostDao.findByPropertys(
		        new String[] { "IStartId" , "IEndId" , "ICustomerid" ,
		                "vcSubno" , "NEnable" , "ISubCarid" } , new Object[] {
		                tDriverCost.getIStartId() , tDriverCost.getIEndId() ,
		                tDriverCost.getICustomerid() ,
		                tDriverCost.getVcSubno() , SystemConstants.SYS_ENABLE ,
		                tDriverCost.getISubCarid() } );
		return tDriverCosts;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-19 下午6:24:57
	 */
	public Map< String , Object > findByHql( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return driverCostDao.findAllByHqlHelp( hql );
	}
}
