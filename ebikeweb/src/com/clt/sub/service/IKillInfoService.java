package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TKillDetail;
import com.clt.sub.model.TKillInfo;
import com.clt.sub.model.TProduct;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * 参与抢单竞标的 竞标服务类
 * 
 * @author hjx86
 * 
 */
public interface IKillInfoService
{
	public TKillInfo get( Integer id );
	
	public void update( TKillInfo entity );
	
	public void save( TKillInfo entity );
	
	public void saveOrUpdate( TKillInfo entity );
	
	public void delete( TKillInfo entity );
	
	public void deleteByKey( Integer id );
	
	public List< TKillInfo > loadAll();
	
	public abstract List< TKillInfo > findAll( Page page );
	
	/**
	 * @Description: 根据秒杀产品获得对应的 秒杀信息，并且是按照发布时间排序
	 * @return List<TKillInfo> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午11:53:28
	 */
	public List< TKillInfo > getByProduct( TProduct product );
	
	/**
	 * @Description: 保存秒杀信息和秒杀详情信息
	 * @param killInfo
	 * @param killDetails
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 下午2:12:22
	 */
	public void saveOrUpdate( TKillInfo killInfo , List< TKillDetail > killDetails );
	
	/**
	 * @Description: 获得列表数据
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月15日 下午1:34:16
	 */
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	/**
	 * 根据当前用户的subno 获得他参与的投标信息id 去重复
	 * 
	 * @param subNo
	 * @return
	 */
	public List< Integer > getProductIdsBySubNo( String subNo );
	
	/**
	 * 根据分供方编号和产品id获得对应投标价格
	 * 
	 * @param subNo
	 * @param productId
	 * @return 投标价格
	 */
	public Float getPrice( String subNo , int productId );
	
	/**
	 * 根据产品id，把秒杀信息和秒杀详情信息删除。适用于抢单从新发布时
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param id 
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月10日 下午8:53:26
	 */
	public void delByProductId( int id );
}
