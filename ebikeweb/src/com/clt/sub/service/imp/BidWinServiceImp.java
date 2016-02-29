package com.clt.sub.service.imp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IBidWinDao;
import com.clt.sub.model.TBidWin;
import com.clt.sub.service.IBidWinService;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class BidWinServiceImp implements IBidWinService
{
	@Autowired
	IBidWinDao bidWinDao;
	
	public TBidWin get( Integer id )
	{
		return bidWinDao.get( id );
	}
	
	public void update( TBidWin entity )
	{
		bidWinDao.update( entity );
	}
	
	public void save( TBidWin entity )
	{
		bidWinDao.save( entity );
	}
	
	public void saveOrUpdate( TBidWin entity )
	{
		bidWinDao.saveOrUpdate( entity );
	}
	
	public void delete( TBidWin entity )
	{
		bidWinDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		bidWinDao.deleteByKey( id );
	}
	
	public List< TBidWin > loadAll()
	{
		return bidWinDao.loadAll();
	}
	
	public List< TBidWin > findAll( Page page )
	{
		return bidWinDao.findAll( page );
	}
	
	/**
	 * @Description: 查询我中标的历史记录
	 * @param userId
	 * @return List<TBidWin> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 下午2:52:06
	 */
	public List< TBidWin > getSelfBid( Integer userId )
	{
		return bidWinDao.findByProperty( "Integer" , userId );
	}
	
	/**
	 * @Description: 通过产品id 获得对应的中标信息
	 * @param productId
	 * @return TBidWin 返回值描述
	 * @author hjx
	 * @create_date 2014年8月22日 上午11:29:25
	 */
	public TBidWin getByProductid( Integer productId )
	{
		
		List< TBidWin > list = bidWinDao.findByPropertys( new String[] { "IProduct" ,
		        "NEnable" } , new Object[] { productId , SystemConstants.SYS_ENABLE } );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return list.get( 0 );
		}
		
		return null;
	}
}
