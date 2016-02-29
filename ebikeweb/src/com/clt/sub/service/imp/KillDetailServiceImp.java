package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IKillDetailDao;
import com.clt.sub.model.TKillDetail;
import com.clt.sub.model.TKillInfo;
import com.clt.sub.service.IKillDetailService;
import com.clt.util.Page;

@Service
public class KillDetailServiceImp implements IKillDetailService
{
	
	@Autowired
	IKillDetailDao killDetailDao;
	
	public TKillDetail get( Integer id )
	{
		return killDetailDao.get( id );
	}
	
	public void update( TKillDetail entity )
	{
		killDetailDao.update( entity );
	}
	
	public void save( TKillDetail entity )
	{
		killDetailDao.save( entity );
	}
	
	public void saveOrUpdate( TKillDetail entity )
	{
		killDetailDao.saveOrUpdate( entity );
	}
	
	public void delete( TKillDetail entity )
	{
		killDetailDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		killDetailDao.deleteByKey( id );
	}
	
	public List< TKillDetail > loadAll()
	{
		return killDetailDao.loadAll();
	}
	
	public List< TKillDetail > findAll( Page page )
	{
		return killDetailDao.findAll( page );
	}
	
	/**
	 * @Description: 根据秒杀信息获得秒杀详情
	 * @param killInfo
	 * @return TKillDetail 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 下午2:08:52
	 */
	public List< TKillDetail > getByKillInfo( TKillInfo killInfo )
	{
		return killDetailDao.findByProperty( "IKillInfo" , killInfo.getId() );
	}
	
}
