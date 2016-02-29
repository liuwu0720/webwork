package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TProductCarStyle;
import com.clt.util.Page;

/**
 * 抢单信息里 抢单车型目的地 ，抢单详情服务类
 * 
 * @author hjx86
 * 
 */
public interface IProductCarStyleService
{
	public TProductCarStyle get( Integer id );
	
	public void update( TProductCarStyle entity );
	
	public void save( TProductCarStyle entity );
	
	public void saveOrUpdate( TProductCarStyle entity );
	
	public void delete( TProductCarStyle entity );
	
	public void deleteByKey( Integer id );
	
	public List< TProductCarStyle > loadAll();
	
	public abstract List< TProductCarStyle > findAll( Page page );
	
	/**
	 * @Description: 根据产品id删除相关产品车型的所有信息
	 * @param productId
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月7日 下午5:12:28
	 */
	public void deleteByProductId( Integer productId );
	
	/**
	 * @Description: 批量保存车型
	 * @param carStyles
	 *            void
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:13:47
	 */
	public void saveList( List< TProductCarStyle > carStyles );
	
	/**
	 * @Description: 根据产品id，获得所有有效的车型信息
	 * @param productId
	 * @return List<TProductCarStyle> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:20:21
	 */
	public List< TProductCarStyle > getByProductId( String productId );
	
	/**
	 * @Description: 根据产品id，获得所有的车型信息
	 * @param productId
	 * @return List<TProductCarStyle> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:20:21
	 */
	public List< TProductCarStyle > getAllByProductId( int productId );
}
