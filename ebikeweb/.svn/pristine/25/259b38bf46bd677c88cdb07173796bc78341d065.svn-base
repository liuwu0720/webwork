package com.clt.sub.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.INotepadTypeDao;
import com.clt.sub.model.TNotepadType;
import com.clt.sub.service.INotepadTypeService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Description 记事本类型服务类
 * @author f
 * 
 */
@Service
public class NotepadTypeServiceImp implements INotepadTypeService
{
	@Autowired
	private INotepadTypeDao npTypeDao;
	
	public void delete( TNotepadType entity )
	{
		npTypeDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		npTypeDao.deleteByKey( id );
	}
	
	public List< TNotepadType > findAll( Page page )
	{
		
		return npTypeDao.findAll( page );
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		
		return npTypeDao.findAllByHqlHelp( helper );
	}
	
	public TNotepadType get( Integer id )
	{
		
		return npTypeDao.get( id );
	}
	
	public void save( TNotepadType entity )
	{
		npTypeDao.save( entity );
	}
	
	public void saveOrUpdate( TNotepadType entity )
	{
		npTypeDao.saveOrUpdate( entity );
	}
	
	public void update( TNotepadType entity )
	{
		npTypeDao.update( entity );
	}
	
	public List< TNotepadType > findAll()
	{
		return npTypeDao.findAll();
	}
	
}
