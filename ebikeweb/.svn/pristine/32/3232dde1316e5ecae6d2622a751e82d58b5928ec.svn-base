package com.clt.systemmanger.service;

import java.util.List;

import com.clt.systemmanger.model.TDept;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 角色管理服务类
 * @author hjx
 * @date 2014年7月12日 下午3:53:21
 * @version V1.0
 */
public interface IDeptService
{
	public TDept get( Integer id );
	
	public void update( TDept entity );
	
	public void save( TDept entity );
	
	public void saveOrUpdate( TDept entity );
	
	/**
	 * @Description: 删除部门
	 * @param entity
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月22日 下午3:28:20
	 */
	public boolean delete( TDept entity );
	
	/**
	 * @Description: 删除部门
	 * @param id
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月22日 下午3:28:32
	 */
	public boolean deleteByKey( Integer id );
	
	public List< TDept > loadAll();
	
	/**
	 * @Description: 加载有效的部门，并按排序字段升序 排序
	 * @return List<TDept> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月23日 上午9:58:23
	 */
	public List< TDept > loadByEnableAndSort();
	
}
