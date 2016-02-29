package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IProductScopeDao;
import com.clt.sub.model.TProductScope;
import com.clt.sub.service.IProductScopeService;

@Service
public class ProductScopeServiceImp implements IProductScopeService
{
	@Autowired
	private IProductScopeDao pScopeDao;
	
	public void save( int productId , String scopeId )
	{
		List< TProductScope > scopes = pScopeDao
		        .findByProperty( "IProductId" , productId );
		
		if ( CollectionUtils.isNotEmpty( scopes ) )
		{
			for ( TProductScope scope : scopes )
			{
				pScopeDao.delete( scope );
			}
		}
		
		String[] scopeIdArr = StringUtils.split( scopeId , "," );
		TProductScope scope = null;
		for ( String id : scopeIdArr )
		{
			scope = new TProductScope();
			scope.setIProductId( productId );
			scope.setISub( Integer.valueOf( id ) );
			pScopeDao.save( scope );
			
		}
		
	}
	
	public List< Integer > getScopeByProductId( int productId )
	{
		List< TProductScope > scopes = pScopeDao
		        .findByProperty( "IProductId" , productId );
		if ( CollectionUtils.isNotEmpty( scopes ) )
		{
			List< Integer > list = new ArrayList< Integer >();
			for ( TProductScope tProductScope : scopes )
			{
				list.add( tProductScope.getId() );
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 根据分供方档案id，获得他所能查看的所有指定范围的抢单信息
	 * 
	 * @param subIds
	 * @return
	 */
	public List< Integer > getProductIdsBySubId( int subIds )
	{
		List< TProductScope > list = pScopeDao.findByProperty( "ISub" , subIds );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			List< Integer > productIds = new ArrayList< Integer >();
			for ( TProductScope pScope : list )
			{
				
				productIds.add( pScope.getIProductId() );
			}
			return productIds;
		}
		return null;
	}
}
