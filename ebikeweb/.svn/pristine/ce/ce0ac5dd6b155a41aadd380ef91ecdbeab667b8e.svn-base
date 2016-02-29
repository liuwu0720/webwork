package com.clt.systemmanger.service;

import java.util.List;
import java.util.Map;

import com.clt.systemmanger.model.TIntegalAdd;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IIntegalAddService
{
	public TIntegalAdd get( Integer id );
	
	public TIntegalAdd geTIntegalAddByCode( String code );
	
	public void update( TIntegalAdd entity );
	
	public void save( TIntegalAdd entity );
	
	public void saveOrUpdate( TIntegalAdd entity );
	
	public List< TIntegalAdd > loadAll();
	
	/**
	 * @Description: 加载有效的数据，并按排序字段升序 排序
	 * @return List<TIntegalAdd> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月23日 上午9:58:23
	 */
	public List< TIntegalAdd > loadByEnableAndSort();
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	public int getCountSQL( String sql );
	
	public List< String[] > getDateBySQL( String sql , Page page );
	
	public Object getDateBySQL( String sql );
	
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
}
