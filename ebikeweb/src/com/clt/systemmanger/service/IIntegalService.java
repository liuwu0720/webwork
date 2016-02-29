package com.clt.systemmanger.service;

import java.util.List;
import java.util.Map;

import com.clt.systemmanger.model.TIntegral;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IIntegalService
{
	public TIntegral get( Integer id );
	
	public TIntegral geTIntegralByCode( String code );
	
	public void update( TIntegral entity );
	
	public void save( TIntegral entity );
	
	public void saveOrUpdate( TIntegral entity );
	
	public List< TIntegral > loadAll();
	
	/**
	 * @Description: 加载有效的数据，并按排序字段升序 排序
	 * @return List<TIntegral> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月23日 上午9:58:23
	 */
	public List< TIntegral > loadByEnableAndSort();
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	public int getCountSQL( String sql );
	
	public List< String[] > getDateBySQL( String sql , Page page );
	
	public Object getDateBySQL( String sql );
	
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
	/**
	 * 
	 * @Description: 保存积分的历史记录
	 * @param user
	 * @param paramType
	 * @param obj
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-10-11 下午3:05:52
	 */
	public void saveTIntegral( TUser user , String paramType , int integalID );
	
	public List< TIntegral > findByProperties( String[] propertyNames , Object[] values );
}
