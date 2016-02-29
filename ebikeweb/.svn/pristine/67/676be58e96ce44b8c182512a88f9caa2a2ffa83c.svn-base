package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TArorder;
import com.clt.systemmanger.model.TUser;
import com.clt.util.Page;

/**
 * @Package com.clt.sub.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-7-21 下午3:58:20
 * @version V1.0
 */
public interface IArorderService
{
	public TArorder get( Integer id );
	
	public void update( TArorder entity );
	
	public void save( TArorder entity );
	
	public void saveOrUpdate( TArorder entity );
	
	public void delete( TArorder entity );
	
	public void deleteByKey( Integer id );
	
	public List< TArorder > loadAll();
	
	public abstract List< TArorder > findAll( Page page );
	
	/**
	 * @Description: 根据调度指令确认 结算
	 * @param shiplineids
	 *            void 返回值描述
	 * @author chenbin
	 * @return
	 * @create_date 2014-7-21 下午6:27:46
	 */
	public String saveArorder( TUser user , int shiplineids );
	
	/**
	 * @Description: 根据调度指令结算应收确认
	 * @param shipno
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-21 下午6:27:46
	 */
	public void saveAuditArorder( TUser user , String headids );
	
	/**
	 * 
	 * @Description: 根据调度指令对结算审核确定
	 * @param shipno
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-7-21 下午5:53:23
	 */
	public void checkArorder( int headid , TUser us );
	
	/**
	 * @Description: TODO(取消审核)
	 * @param ids
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午1:51:17
	 */
	public void updateAudit( String[] ids );
	
	/**
	 * @Description: TODO(生成对帐单)
	 * @param ids
	 *            void 返回值描述
	 * @author liuwu
	 * @param user
	 * @create_date 2014-12-31 下午3:46:30
	 */
	public void updateCreateBill( String[] ids , TUser user );
	
	/**
	 * @Description: TODO(确定对帐)
	 * @param ids
	 * @param user
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午5:22:21
	 */
	public void updateCheckBill( String[] ids , TUser user );
	
	/**
	 * @Description: TODO(取消对帐单)
	 * @param ids
	 * @param user
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2014-12-31 下午5:29:14
	 */
	public void updateCancelBill( String[] ids , TUser user );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-20 下午4:13:56
	 */
	public Map< String , Object > getSpringSql( String sql , Page page );
	
	/**
	 * @Description: TODO(同步订单表的信息)
	 * @param shiplineid
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-8-24 下午2:57:31
	 */
	public String updateOrderInfo( int shiplineid );
	
}
