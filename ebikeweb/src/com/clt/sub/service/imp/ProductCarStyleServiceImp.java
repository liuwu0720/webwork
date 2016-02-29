package com.clt.sub.service.imp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IProductCarStyleDao;
import com.clt.sub.model.TProductCarStyle;
import com.clt.sub.service.IProductCarStyleService;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.sub.service.imp
 * @Description: 秒杀信息 对应的 车型服务
 * @author hjx
 * @date 2014年7月16日 上午10:22:46
 * @version V1.0
 */
@Service
public class ProductCarStyleServiceImp implements IProductCarStyleService
{
	
	@Autowired
	IProductCarStyleDao productCarStyleDao;
	
	public TProductCarStyle get( Integer id )
	{
		// TODO Auto-generated method stub
		return productCarStyleDao.get( id );
	}
	
	public void update( TProductCarStyle entity )
	{
		// TODO Auto-generated method stub
		productCarStyleDao.update( entity );
	}
	
	public void save( TProductCarStyle entity )
	{
		// TODO Auto-generated method stub
		productCarStyleDao.save( entity );
	}
	
	public void saveOrUpdate( TProductCarStyle entity )
	{
		productCarStyleDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: 逻辑删除
	 * @param id
	 * @author hjx
	 * @create_date 2014年8月25日 下午3:38:14
	 */
	public void delete( TProductCarStyle entity )
	{
		// productCarStyleDao.delete( entity );
		entity.setNEnable( SystemConstants.SYS_DISABLE );
		productCarStyleDao.update( entity );
	}
	
	/**
	 * @Description: 逻辑删除
	 * @param id
	 * @author hjx
	 * @create_date 2014年8月25日 下午3:38:14
	 */
	public void deleteByKey( Integer id )
	{
		// productCarStyleDao.deleteByKey( id );
		TProductCarStyle car = productCarStyleDao.get( id );
		if ( null != car )
		{
			this.delete( car );
		}
	}
	
	public List< TProductCarStyle > loadAll()
	{
		return productCarStyleDao.loadAll();
	}
	
	public List< TProductCarStyle > findAll( Page page )
	{
		return productCarStyleDao.findAll( page );
	}
	
	/**
	 * @Description:批量保存车型
	 * @param carStyles
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:23:23
	 */
	public void saveList( List< TProductCarStyle > carStyles )
	{
		productCarStyleDao.saveOrUpdateAll( carStyles );
	}
	
	/**
	 * @Description: 根据产品id，获得所有有效的车型信息
	 * @param productId
	 * @return
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:23:23
	 */
	public List< TProductCarStyle > getByProductId( String productId )
	{
		return productCarStyleDao.findByPropertys( new String[] { "IProductId" ,
		        "NEnable" } , new Object[] { Integer.parseInt( productId ) ,
		        SystemConstants.SYS_ENABLE } );
	}
	
	/**
	 * @Description: 根据产品id，获得所有的车型信息
	 * @param productId
	 * @return
	 * @author hjx
	 * @create_date 2014年7月16日 上午10:23:23
	 */
	public List< TProductCarStyle > getAllByProductId( int productId )
	{
		return productCarStyleDao.findByPropertys( new String[] { "IProductId" ,
		        "NEnable" } , new Object[] { productId , SystemConstants.SYS_ENABLE } );
	}
	
	/**
	 * @Description: 根据产品id删除相关产品车型的所有信息
	 * @param productId
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月7日 下午5:12:28
	 */
	public void deleteByProductId( Integer productId )
	{
		// productCarStyleDao.deleteByProperty( "IProductId" , productId );
		List< TProductCarStyle > list = productCarStyleDao.findByPropertys( new String[] {
		        "IProductId" , "NEnable" } , new Object[] { productId ,
		        SystemConstants.SYS_ENABLE } );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			for ( TProductCarStyle car : list )
			{
				car.setNEnable( SystemConstants.SYS_DISABLE );
				productCarStyleDao.update( car );
			}
		}
		
	}
	
}
