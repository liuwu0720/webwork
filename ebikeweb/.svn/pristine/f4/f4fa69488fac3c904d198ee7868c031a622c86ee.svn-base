package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TNotepadType;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Description 记事本类型服务接口
 * @author f
 * 
 */
public interface INotepadTypeService
{
	public TNotepadType get( Integer id );
	
	public void save( TNotepadType entity );
	
	public void update( TNotepadType entity );
	
	public void saveOrUpdate( TNotepadType entity );
	
	public void delete( TNotepadType entity );
	
	public void deleteByKey( Integer id );
	
	public List< TNotepadType > findAll();
	
	public List< TNotepadType > findAll( Page page );
	
	public Map< String , Object > findByHelper( HqlHelper helper );
}
