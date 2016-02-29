package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IKillInfoDao;
import com.clt.sub.dao.IProductDao;
import com.clt.sub.model.TKillDetail;
import com.clt.sub.model.TKillInfo;
import com.clt.sub.model.TProduct;
import com.clt.sub.service.IKillDetailService;
import com.clt.sub.service.IKillInfoService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Description: 秒杀信息服务类
 * @author hjx
 * @date 2014年8月15日 下午1:29:50
 * @version V1.0
 */
@Service
public class KillInfoServiceImp implements IKillInfoService
{
	
	@Autowired
	IKillInfoDao killInfoDao;
	@Autowired
	IKillDetailService killDetailService;
	@Autowired
	IProductDao productDao;
	
	public TKillInfo get( Integer id )
	{
		return killInfoDao.get( id );
	}
	
	public void update( TKillInfo entity )
	{
		killInfoDao.update( entity );
	}
	
	public void save( TKillInfo entity )
	{
		killInfoDao.save( entity );
	}
	
	public void saveOrUpdate( TKillInfo entity )
	{
		killInfoDao.saveOrUpdate( entity );
	}
	
	public void delete( TKillInfo entity )
	{
		killInfoDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		killInfoDao.deleteByKey( id );
	}
	
	public List< TKillInfo > loadAll()
	{
		return killInfoDao.loadAll();
	}
	
	public List< TKillInfo > findAll( Page page )
	{
		return killInfoDao.findAll( page );
	}
	
	/**
	 * @Description: 根据秒杀产品获得对应的 秒杀信息，并且是按照发布时间排序
	 * @return List<TKillInfo> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 上午11:53:28
	 */
	public List< TKillInfo > getByProduct( TProduct product )
	{
		return killInfoDao.findByProperty( "TProduct" , product );
	}
	
	/**
	 * @Description: 保存秒杀信息和秒杀详情信息
	 * @param killInfo
	 * @param killDetails
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 下午2:12:22
	 */
	public void saveOrUpdate( TKillInfo killInfo , List< TKillDetail > killDetails )
	{
		Date killEnd = productDao.get( killInfo.getIProductId() ).getDtKillEnd();
		
		// 判断是否秒杀结束
		if ( killEnd.getTime() - new Date().getTime() > 0 )
		{
			killInfoDao.saveOrUpdate( killInfo );
			if ( CollectionUtils.isNotEmpty( killDetails ) )
			{
				for ( TKillDetail killDetail : killDetails )
				{
					killDetail.setIKillInfo( killInfo.getId() );
					killDetailService.saveOrUpdate( killDetail );
				}
			}
		}
	}
	
	/**
	 * @Description: 获得列表数据
	 * @param helper
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月15日 下午1:34:16
	 */
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return killInfoDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * 根据当前用户的subno 获得他参与的投标信息id 去重复
	 * 
	 * @param subNo
	 * @return
	 */
	public List< Integer > getProductIdsBySubNo( String subNo )
	{
		List< TKillInfo > list = killInfoDao.findByPropertys( new String[] { "vcSubno" ,
		        "NEnable" } , new Object[] { subNo , SystemConstants.SYS_ENABLE } );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			List< Integer > productIds = new ArrayList< Integer >();
			HashSet< TKillInfo > hs = new HashSet< TKillInfo >( list ); // 此时已经去掉重复的数据保存在hashset中
			for ( TKillInfo kill : hs )
			{
				productIds.add( kill.getIProductId() );
			}
			return productIds;
		}
		return null;
	}
	
	/**
	 * 根据分供方编号和产品id获得对应投标价格
	 * 
	 * @param subNo
	 * @param productId
	 * @return
	 */
	public Float getPrice( String subNo , int productId )
	{
		
		String sql = "from TKillInfo a where a.NEnable=" + SystemConstants.SYS_ENABLE
		        + " and a.vcSubno ='" + subNo + "' and a.IProductId =" + productId
		        + "  order by a.dtBid desc";
		
		List< TKillInfo > list = killInfoDao.find( sql );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			return list.get( 0 ).getNTotalPrice();
			
		}
		
		return null;
	}
	
	/**
	 * 根据产品id，把秒杀信息和秒杀详情信息删除。适用于抢单从新发布时
	 * 非物理删除，是逻辑删除
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param id 
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月10日 下午8:53:26
	 */
	public void delByProductId( int id )
	{
		
		List< TKillInfo > list = killInfoDao.findByProperty( "IProductId" , id );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			for ( TKillInfo info : list )
			{
				List< TKillDetail > details = killDetailService.getByKillInfo( info );
				for ( TKillDetail detail : details )
				{
					detail.setNEnable( SystemConstants.SYS_DISABLE );
					killDetailService.update( detail );
				}
				info.setNEnable( SystemConstants.SYS_DISABLE );
				killInfoDao.update( info );
			}
		}
		
	}
}
