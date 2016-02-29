package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TBidWin;
import com.clt.util.Page;

public interface IBidWinService
{
	public TBidWin get( Integer id );
	
	public void update( TBidWin entity );
	
	public void save( TBidWin entity );
	
	public void saveOrUpdate( TBidWin entity );
	
	public void delete( TBidWin entity );
	
	public void deleteByKey( Integer id );
	
	public List< TBidWin > loadAll();
	
	public abstract List< TBidWin > findAll( Page page );
	
	/**
	 * @Description: 查询我中标的历史记录
	 * @param userId
	 * @return List<TBidWin> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 下午2:52:06
	 */
	public List< TBidWin > getSelfBid( Integer userId );
	
	/**
	 * @Description: 通过产品id 获得对应的中标信息
	 * @param productId
	 * @return TBidWin 返回值描述
	 * @author hjx
	 * @create_date 2014年8月22日 上午11:29:25
	 */
	public TBidWin getByProductid( Integer productId );
	
}
