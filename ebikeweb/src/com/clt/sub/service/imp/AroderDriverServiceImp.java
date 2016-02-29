/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-8 下午1:48:33 
 * @version V1.0 
 */
package com.clt.sub.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IArorderDriverDao;
import com.clt.sub.model.TArorderDriver;
import com.clt.sub.service.IAorderDriverService;
import com.clt.util.HqlHelper;

/** 
 * @Package com.clt.sub.service.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-8 下午1:48:33 
 * @version V1.0 
 */
@Service
public class AroderDriverServiceImp implements IAorderDriverService
{
	
	@Autowired
	IArorderDriverDao iArorderDriverDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-8 下午1:53:11
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iArorderDriverDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-8 下午3:54:11
	 */
	public TArorderDriver getById( int id )
	{
		// TODO Auto-generated method stub
		return iArorderDriverDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tArorderDriver
	 * @author liuwu
	 * @create_date 2015-6-8 下午3:56:18
	 */
	public void saveOrUpdate( TArorderDriver tArorderDriver )
	{
		// TODO Auto-generated method stub
		iArorderDriverDao.saveOrUpdate( tArorderDriver );
	}
	
}
