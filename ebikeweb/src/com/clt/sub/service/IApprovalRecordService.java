package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TApprovalRecord;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * 
 * @Package com.clt.sub.service
 * @Description: 分供方下单 抢单 贷款 审批Service
 * @author chenbin
 * @date 2014-8-11 下午1:19:58
 * @version V1.0
 */
public interface IApprovalRecordService
{
	public TApprovalRecord get( Integer id );
	
	public void update( TApprovalRecord entity );
	
	public void save( TApprovalRecord entity );
	
	public void saveOrUpdate( TApprovalRecord entity );
	
	public void delete( TApprovalRecord entity );
	
	public void deleteByKey( Integer id );
	
	public List< TApprovalRecord > loadAll();
	
	public abstract List< TApprovalRecord > findAll( Page page );
	
	public List< TApprovalRecord > find( String queryString );
	
	/**
	 * 
	 * @Description: 根据对象的属性和值 查询 并分页
	 * @param propertyNames
	 * @param values
	 * @param page
	 * @return List<TArkilometer> 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-16 下午7:00:05
	 */
	public List< TApprovalRecord > findByPropertys( String[] propertyNames ,
	        Object[] values , Page page );
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
}
