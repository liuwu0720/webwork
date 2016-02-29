package com.clt.sub.service;

import java.util.List;

/**
 * 发布抢单的制定 可见范围服务类
 * 
 * @author hjx86
 * 
 */
public interface IProductScopeService
{
	/**
	 * 保存一个抢单对应可见范围，保存之前先把 该抢单之前可见范围全部删除，在进行保存
	 * 
	 * @param productId
	 * @param scopeId
	 */
	public void save( int productId , String scopeId );
	
	/**
	 * 根据抢单id，获得对应的可见范围（分供方id）
	 * 
	 * @param productId
	 * @return
	 */
	public List< Integer > getScopeByProductId( int productId );
	
	/**
	 * 根据分供方档案id，获得他所能查看的所有指定范围的抢单信息
	 * 
	 * @param subIds
	 * @return
	 */
	public List< Integer > getProductIdsBySubId( int subIds );
}
