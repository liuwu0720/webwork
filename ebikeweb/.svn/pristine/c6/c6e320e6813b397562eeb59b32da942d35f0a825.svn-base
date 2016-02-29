package com.clt.systemmanger.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.ICltDao;
import com.clt.systemmanger.model.TClt;
import com.clt.systemmanger.service.ICltService;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:28:00
 * @version V1.0
 */
@Service
public class CltServiceImp implements ICltService
{
	@Autowired
	private ICltDao cltDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public TClt get( Integer id )
	{
		return cltDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void update( TClt entity )
	{
		cltDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void save( TClt entity )
	{
		cltDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void saveOrUpdate( TClt entity )
	{
		cltDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void delete( TClt entity )
	{
		cltDao.delete( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void deleteByKey( Integer id )
	{
		cltDao.deleteByKey( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TClt > loadAll()
	{
		return cltDao.loadAll();
	}
	
}
