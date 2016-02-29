package com.clt.systemmanger.service;

import java.util.List;

import com.clt.systemmanger.model.TClt;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface ICltService
{
	public TClt get( Integer id );
	
	public void update( TClt entity );
	
	public void save( TClt entity );
	
	public void saveOrUpdate( TClt entity );
	
	public void delete( TClt entity );
	
	public void deleteByKey( Integer id );
	
	public List< TClt > loadAll();
	
}
