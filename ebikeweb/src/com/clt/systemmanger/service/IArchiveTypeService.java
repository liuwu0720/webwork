package com.clt.systemmanger.service;

import java.util.List;

import com.clt.systemmanger.model.TArchiveType;
import com.clt.util.Page;

/**
 * @Description: 档案类型Service
 */
public interface IArchiveTypeService
{
	public TArchiveType get( Integer id );
	
	public void update( TArchiveType entity );
	
	public void save( TArchiveType entity );
	
	public void saveOrUpdate( TArchiveType entity );
	
	public void delete( TArchiveType entity );
	
	public void deleteByKey( Integer id );
	
	public List< TArchiveType > loadAll();
	
	public abstract List< TArchiveType > findAll( Page page );
	
	/**
	 * @Description: 加载所有有效的资源
	 * @return List<TArchiveType> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月18日 上午10:01:32
	 */
	public List< TArchiveType > loadAllByEnable();
	
	/**
	 * @Description: 获得资源json树
	 * @param list
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午3:40:23
	 */
	public String getJsonTree( List< TArchiveType > list );
	
}
