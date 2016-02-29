package com.clt.sub.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IApprovalRecordDao;
import com.clt.sub.model.TApprovalRecord;
import com.clt.sub.service.IApprovalRecordService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.sub.service.imp
 * @Description: 分供方下单 抢单 贷款 审批ServiceImp
 * @author chenbin
 * @date 2014-9-19 上午9:23:18
 * @version V1.0
 */
@Service
public class ApprovalRecordServiceImp implements IApprovalRecordService
{
	
	@Autowired
	IApprovalRecordDao approvalRecordDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public TApprovalRecord get( Integer id )
	{
		// TODO Auto-generated method stub
		return approvalRecordDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public void update( TApprovalRecord entity )
	{
		// TODO Auto-generated method stub
		approvalRecordDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public void save( TApprovalRecord entity )
	{
		// TODO Auto-generated method stub
		approvalRecordDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public void saveOrUpdate( TApprovalRecord entity )
	{
		// TODO Auto-generated method stub
		approvalRecordDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public void delete( TApprovalRecord entity )
	{
		// TODO Auto-generated method stub
		approvalRecordDao.delete( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		approvalRecordDao.deleteByKey( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public List< TApprovalRecord > loadAll()
	{
		// TODO Auto-generated method stub
		return approvalRecordDao.loadAll();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public List< TApprovalRecord > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return approvalRecordDao.findAll( page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public List< TApprovalRecord > findByPropertys( String[] propertyNames ,
	        Object[] values , Page page )
	{
		// TODO Auto-generated method stub
		return approvalRecordDao.findByPropertys( propertyNames , values , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-19 上午9:23:18
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return approvalRecordDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param queryString
	 * @return
	 * @author chenbin
	 * @create_date 2014-9-19 上午10:40:05
	 */
	public List< TApprovalRecord > find( String queryString )
	{
		// TODO Auto-generated method stub
		return approvalRecordDao.find( queryString );
	}
	
}
