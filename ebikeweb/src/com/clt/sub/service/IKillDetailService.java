package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TKillDetail;
import com.clt.sub.model.TKillInfo;
import com.clt.util.Page;

/**
 * 参与抢单竞标的 竞标详情服务类
 * 
 * @author hjx86
 * 
 */
public interface IKillDetailService
{
	public TKillDetail get( Integer id );
	
	public void update( TKillDetail entity );
	
	public void save( TKillDetail entity );
	
	public void saveOrUpdate( TKillDetail entity );
	
	public void delete( TKillDetail entity );
	
	public void deleteByKey( Integer id );
	
	public List< TKillDetail > loadAll();
	
	public abstract List< TKillDetail > findAll( Page Page );
	
	/**
	 * @Description: 根据秒杀信息获得秒杀详情
	 * @param killInfo
	 * @return TKillDetail 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 下午2:08:52
	 */
	public List< TKillDetail > getByKillInfo( TKillInfo killInfo );
}
