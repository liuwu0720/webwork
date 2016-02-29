package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TSpareCapacity;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.sub.service
 * @Description: 发布空闲运力服务类
 * @author hjx
 * @date 2014年10月10日 上午10:05:16
 * @version V1.0
 */
public interface ISpareCapacityService
{
	/**
	 * @Description: 保存或者更新 空闲运力
	 * @param spareCapacity
	 * @author hjx
	 * @create_date 2014年10月10日 上午10:08:33
	 */
	public void saveOrUpdate( TSpareCapacity spareCapacity );
	
	/**
	 * @Description:根据id获得空闲运力详情
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年10月10日 上午10:08:33
	 */
	public TSpareCapacity getById( int id );
	
	/**
	 * @Description: 根据id，逻辑删除空闲运力
	 * @param id
	 *            空闲运力id
	 * @author hjx
	 * @create_date 2014年10月10日 上午10:08:33
	 */
	public void delById( int id );
	
	/**
	 * @Description: 通过用户id和空闲运力信息id 获得联系方式，并扣除积分
	 * @param userId
	 * @param spareId
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年10月10日 下午6:32:23
	 */
	public String getLinkBy( int userId , int spareId );
	
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	public List< TSpareCapacity > findByHql( String hql );
	
	public Map< String , Object > getSpringSQL( String sql , Page page );
	
	/**
	 * 
	 * @Description: 获取分供方联系方式 并扣除用户积分
	 * @param user
	 *            要处理积分的用户
	 * @param paramType
	 *            add 增加积分 cut扣除积分
	 * @param integalID
	 *            积分对象 void 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-17 下午1:47:53
	 */
	public void saveAndIntegalCut( TSpareCapacity spare , TUser user , String paramType ,
	        int integalID );
	
	/**
	 * 通过用户id获得该用户可以查看的空闲运力联系方式的id
	 * 
	 * @return
	 */
	public List< Integer > getLimitByUserId( int userId );
	
	/**
	 * @Description :用于app通过id获取空闲运力详情 包含SpareCapacity全部字段和车型名称
	 * @author chengwzh
	 * @date 2015/5/14 15：40
	 */
	public Map< String , Object > getDetailByIdApp( int spareId );
	
	public List< TSpareCapacity > getByPropertys( String[] propertyNames , Object[] values );
	
	public List< TSpareCapacity > getByProperty( String propertyName , Object value );
}
