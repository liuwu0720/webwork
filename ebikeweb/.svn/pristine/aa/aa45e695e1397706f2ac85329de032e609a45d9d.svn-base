package com.clt.systemmanger.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.clt.systemmanger.model.TIntegalCut;
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
public interface IIntegalCutService
{
	public TIntegalCut get( Integer id );
	
	public TIntegalCut getIntegalCutByCode( String code );
	
	public void update( TIntegalCut entity );
	
	public void save( TIntegalCut entity );
	
	public void saveOrUpdate( TIntegalCut entity );
	
	public List< TIntegalCut > loadAll();
	
	/**
	 * @Description: 加载有效的数据，并按排序字段升序 排序
	 * @return List<TIntegalCut> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月23日 上午9:58:23
	 */
	public List< TIntegalCut > loadByEnableAndSort();
	
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql );
	
	public int getCountSQL( String sql );
	
	public List< String[] > getDateBySQL( String sql , Page page );
	
	public Object getDateBySQL( String sql );
	
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
	/**
	 * 
	 * @Description: 验证积分扣除
	 * @param user
	 * @param code
	 *            扣除积分编码
	 * @return JSONObject isSuccess 是否可扣除 message 描述信息 integalID 积分扣除类型对象的ID
	 * @author chenbin
	 * @create_date 2014-10-13 上午11:47:26
	 */
	public JSONObject checkIntegalCutByUserCode( TUser user , String code );
	
}
