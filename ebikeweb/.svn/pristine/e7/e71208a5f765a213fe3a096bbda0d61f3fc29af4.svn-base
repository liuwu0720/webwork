package com.clt.sub.service.imp;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IOrderDao;
import com.clt.sub.dao.IReturnPicDao;
import com.clt.sub.dao.IShipheadDao;
import com.clt.sub.dao.IShiplineDao;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TReturnPic;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TShipline;
import com.clt.sub.service.IOrderService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IIntegalCutService;
import com.clt.systemmanger.service.IIntegalService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

@Service
public class OrderServiceImp implements IOrderService
{
	@Autowired
	IOrderDao orderDao;
	@Autowired
	IIntegalCutService integalCutService;
	@Autowired
	IUserService userService;
	@Autowired
	IIntegalService integalService;
	@Autowired
	IReturnPicDao iReturnPicDao;
	@Autowired
	IShiplineDao iShiplineDao;
	@Autowired
	IShipheadDao iShipheadDao;
	
	public TOrder get( Integer id )
	{
		// TODO Auto-generated method stub
		return orderDao.get( id );
	}
	
	public void update( TOrder entity )
	{
		// TODO Auto-generated method stub
		orderDao.updateCleanBefore( entity );
	}
	
	public void save( TOrder entity )
	{
		// TODO Auto-generated method stub
		orderDao.save( entity );
	}
	
	public void saveOrUpdate( TOrder entity , TUser user , String paramType ,
	        int integalID )
	{
		// 修改用户积分
		userService.updateUserIntegal( user , paramType , integalID );
		// 添加积分历史记录
		integalService.saveTIntegral( user , paramType , integalID );
		
		// 起始地和目的地城市名称修正，在app某些情况下城市名带对应省份名称，需去除，避免影响结算
		String vcStartCity = entity.getVcStartCity();
		if ( StringUtils.isNotBlank( vcStartCity ) )
		{
			vcStartCity = getCityName( vcStartCity );
			entity.setVcStartCity( vcStartCity );
		}
		
		String vcDestCity = entity.getVcDestCity();
		if ( StringUtils.isNotBlank( vcDestCity ) )
		{
			vcDestCity = getCityName( vcDestCity );
			entity.setVcDestCity( vcDestCity );
		}
		orderDao.saveOrUpdate( entity );
		
	}
	
	/**
	 * @Description: 过滤非正常的城市名称，把多余的部分去掉。 
	 * @param vcStartCity
	 * @return 
	 *   String 返回值描述
	 * @author hjx
	 * @create_date 2015年9月22日 上午11:42:23
	 */
	private String getCityName( String vcStartCity )
	{
		if ( vcStartCity.contains( "省" ) )
		{
			vcStartCity = vcStartCity.substring( vcStartCity.indexOf( "省" ) + 1 );
		}
		else if ( vcStartCity.contains( "区" )
		        && ( vcStartCity.indexOf( "区" ) != vcStartCity.lastIndexOf( "区" ) ) )
		{
			vcStartCity = vcStartCity.substring( vcStartCity.indexOf( "区" ) + 1 );
		}
		else if ( vcStartCity.indexOf( "市" ) != vcStartCity.lastIndexOf( "市" ) )
		{
			vcStartCity = vcStartCity.substring( vcStartCity.indexOf( "市" ) + 1 );
		}
		return vcStartCity;
	}
	
	public void delete( TOrder entity )
	{
		// TODO Auto-generated method stub
		orderDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		orderDao.deleteByKey( id );
	}
	
	public List< TOrder > loadAll()
	{
		// TODO Auto-generated method stub
		return orderDao.loadAll();
	}
	
	public List< TOrder > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return orderDao.findAll( page );
	}
	
	public synchronized String getMaxOrderNo()
	{
		
		List< TOrder > orderlist = orderDao.findAllAndOrderByProperty( "id" ,
		        false );
		String orderno = "N";
		String datestr = DateUtil.getDate( "yyMMdd" );
		DecimalFormat df = new DecimalFormat( "0000" );
		String str2 = "";
		if ( orderlist.size() == 0 )
		{
			str2 = df.format( Integer.parseInt( "1" ) );
			
		}
		else
		{
			TOrder order = orderlist.get( 0 );
			
			String str = null;
			if ( order.getVcOrderno().length() > 11 )// 出现子订单时
			{
				str = order.getVcOrderno().substring( 7 , 11 );
			}
			else
			{
				str = order.getVcOrderno().substring( 7 , order.getVcOrderno().length() );
			}
			
			str2 = df.format( Integer.parseInt( str ) + 1 );
		}
		
		orderno += datestr + str2;
		System.out.println( "Max Order " + orderno );
		return orderno;
	}
	
	/**
	 * @Description: 获取该分供方的所有订单 根据分供方编号
	 * @param subno
	 * @return
	 * @author chenbin
	 * @create_date 2014-7-16 下午4:07:25
	 */
	public List< TOrder > getAllOrderBysubno( String subno , Page page )
	{
		// TODO Auto-generated method stub
		List< TOrder > orderlist = orderDao.findByPropertys( new String[] { "NEnable" ,
		        "vcSubno" } , new Object[] { 0 , subno } , page );
		
		return orderlist;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-2 下午8:33:54
	 */
	public List< TOrder > findByPropertys( String[] propertyNames , Object[] values ,
	        Page page )
	{
		// TODO Auto-generated method stub
		return orderDao.findByPropertys( propertyNames , values , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-2 下午8:47:40
	 */
	public List< TOrder > find( String hql )
	{
		// TODO Auto-generated method stub
		return orderDao.find( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-4 下午2:17:52
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return orderDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-8-4 下午4:35:57
	 */
	public int getCountSQL( String sql )
	{
		// TODO Auto-generated method stub
		return orderDao.getCountSQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author liuwu
	 * @create_date 2015-4-29 上午10:15:00
	 */
	public Map< String , Object > findBySpringSql( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return orderDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param order
	 * @author liuwu
	 * @create_date 2015-5-5 下午2:20:01
	 */
	public void saveOrUpdateOrder( TOrder order )
	{
		// TODO Auto-generated method stub
		orderDao.saveOrUpdate( order );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tReturnPic
	 * @author liuwu
	 * @create_date 2015-5-8 上午10:56:39
	 */
	public void saveTreturnPic( TReturnPic tReturnPic )
	{
		// TODO Auto-generated method stub
		iReturnPicDao.saveOrUpdate( tReturnPic );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-8 下午2:17:13
	 */
	public Map< String , Object > getSpringSql( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return orderDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-12 下午2:47:06
	 */
	public TShiphead getByOrderId( Integer id )
	{
		TShipline tShipline = iShiplineDao.findByProperty( "IOrderId" , id ).get( 0 );
		TShiphead tShiphead = iShipheadDao.get( tShipline.getIShiphead() );
		return tShiphead;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames
	 * @param values
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-16 上午9:39:33
	 */
	public List< TOrder > findByPropertys( String[] propertyNames , Object[] values )
	{
		// TODO Auto-generated method stub
		return orderDao.findByPropertys( propertyNames , values );
	}
	
}
