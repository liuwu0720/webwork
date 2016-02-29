package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TTruckDriverLlink;

public interface ITruckDriverLinkService
{
	public void get( Integer id );
	
	/**
	 * @Description 通过属性查找车辆与司机关联
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List< TTruckDriverLlink > getByProperty( String propertyName , Object value );
	
	public void save( TTruckDriverLlink entity );
	
	public void update( TTruckDriverLlink entity );
	
	public void delete( TTruckDriverLlink entity );
	
	public void deleteById( Integer id );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param propertyNames2
	 * @param values2
	 * @return List<TTruckDriverLlink> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-27 下午4:28:40
	 */
	public List< TTruckDriverLlink > findByPropertys( String[] propertyNames2 ,
	        Object[] values2 );
}
