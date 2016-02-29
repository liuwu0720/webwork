package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TNotepad;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

public interface INotepadService
{
	public TNotepad get( Integer id );
	
	public void save( TNotepad entity );
	
	public void update( TNotepad entity );
	
	public void saveOrUpdate( TNotepad entity );
	
	public void delete( TNotepad entity );
	
	public void deleteByKey( Integer id );
	
	public List< TNotepad > loadAll();
	
	public List< TNotepad > findAll( Page page );
	
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	public Map< String , Object > findAllNotepads( Page page , int userId , Integer typeId );
	
	public Map< String , Object > getAllTypes( int userId );
	
	/**
	 * 保存记事本类型，types是以“;” 拼接的字符串。当检测到没有对应的类型，就保存
	 * 
	 * @param types
	 */
	public void saveNoteType( String types );
	
	// 计算总额
	public int getTotalMoney( int userId , Integer typeId );
}
