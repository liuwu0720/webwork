/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-11 下午3:40:22 
 * @version V1.0 
 */
package com.clt.sub.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IShipStatusDao;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.service.IShipStatusService;
import com.clt.util.HqlHelper;

/** 
 * @Package com.clt.sub.service.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-11 下午3:40:22 
 * @version V1.0 
 */
@Service
public class ShipStatusServiceImp implements IShipStatusService
{
	@Autowired
	IShipStatusDao iShipStatusDao;
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-11 下午3:43:50
	 */
	public Map< String , Object > findOrderStatusByOrderId( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iShipStatusDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tShipStatus
	 * @author liuwu
	 * @create_date 2015-5-11 下午5:04:19
	 */
	public void saveOrUpdate( TShipStatus tShipStatus )
	{
		// TODO Auto-generated method stub
		iShipStatusDao.saveOrUpdate( tShipStatus );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-5-14 下午1:59:19
	 */
	public Map< String , Object > findByHQL( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iShipStatusDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tShipStatus
	 * @author liuwu
	 * @create_date 2015-9-10 上午10:36:34
	 */
	public void save( TShipStatus tShipStatus )
	{
		// TODO Auto-generated method stub
		iShipStatusDao.save( tShipStatus );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author liuwu
	 * @create_date 2015-9-14 下午2:52:15
	 */
	public List< TShipStatus > findByLine( Integer id )
    {
	    // TODO Auto-generated method stub
		return iShipStatusDao.findByProperty( "nLineId" , id );
    }
	
}
