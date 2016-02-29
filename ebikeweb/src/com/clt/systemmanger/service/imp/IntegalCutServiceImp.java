package com.clt.systemmanger.service.imp;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IIntegalCutDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TIntegalCut;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IIntegalCutService;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author chenbin
 * @date 2014-10-9 下午2:57:28
 * @version V1.0
 */
@Service
public class IntegalCutServiceImp implements IIntegalCutService
{
	
	@Autowired
	private IIntegalCutDao integalCutDao;
	
	@Autowired
	private IUserDao userDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-9 下午2:57:28
	 */
	public TIntegalCut get( Integer id )
	{
		// TODO Auto-generated method stub
		return integalCutDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-9 下午2:57:28
	 */
	public void update( TIntegalCut entity )
	{
		// TODO Auto-generated method stub
		integalCutDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-9 下午2:57:28
	 */
	public void save( TIntegalCut entity )
	{
		// TODO Auto-generated method stub
		integalCutDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author chenbin
	 * @create_date 2014-10-9 下午2:57:28
	 */
	public void saveOrUpdate( TIntegalCut entity )
	{
		// TODO Auto-generated method stub
		integalCutDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-9 下午2:57:28
	 */
	public List< TIntegalCut > loadAll()
	{
		// TODO Auto-generated method stub
		return integalCutDao.loadAll();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-9 下午2:57:28
	 */
	public List< TIntegalCut > loadByEnableAndSort()
	{
		// TODO Auto-generated method stub
		return integalCutDao.loadAllByEnable();
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-9 下午3:30:45
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return integalCutDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-9 下午3:30:45
	 */
	public int getCountSQL( String sql )
	{
		// TODO Auto-generated method stub
		return integalCutDao.getCountSQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-9 下午3:30:45
	 */
	public List< String[] > getDateBySQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return integalCutDao.getDateBySQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-9 下午3:30:45
	 */
	public Object getDateBySQL( String sql )
	{
		// TODO Auto-generated method stub
		return integalCutDao.getDateBySQL( sql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-9 下午3:30:45
	 */
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return integalCutDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: 根据扣分编码 和开始结束日期 查询可用的扣分类型
	 * @param code
	 * @return
	 * @author chenbin
	 * @create_date 2014-10-10 下午5:30:53
	 */
	public TIntegalCut getIntegalCutByCode( String code )
	{
		String DateStr = DateUtil.getDate( "yyyy-MM-dd" );
		String sql = " select inteCut.Id  from t_integal_cut inteCut "
		        + " where inteCut.Vc_Code='" + code + "' and  to_date('" + DateStr
		        + "','yyyy/mm/dd') >= inteCut.Dt_Begin and to_date('" + DateStr
		        + "','yyyy/mm/dd') <=  inteCut.Dt_End order by inteCut.Id desc  ";
		
		List datelist = integalCutDao.getDateBySQL( sql , null );
		System.out.println( " datelist Size  >>" + datelist.size() );
		if ( datelist.size() > 0 )
		{
			TIntegalCut integalCut = integalCutDao.get( Integer.parseInt( datelist
			        .get( 0 ) + "" ) );
			
			return integalCut;
		}
		else
		{
			return null;
		}
		
	}
	
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
	public JSONObject checkIntegalCutByUserCode( TUser user , String code )
	{
		JSONObject json = new JSONObject();
		json.put( "isSuccess" , true );
		json.put( "message" , "ok" );
		
		int curIntegal = user.getNIntegral();
		System.out.println( "当前用户的积分:" + curIntegal );
		
		TIntegalCut integalCut = getIntegalCutByCode( code );
		if ( integalCut == null )
		{
			json.put( "isSuccess" , false );
			json.put( "message" , "保存失败.扣分类型不存在" );
			return json;
		}
		if ( curIntegal < integalCut.getNIntegal() )
		{
			json.put( "isSuccess" , false );
			json.put( "message" , "保存失败.当前用户积分不足 " + integalCut.getNIntegal() + " 请充值." );
			return json;
		}
		else
		{
			// int curIntegalCount = json.getInt( "IntegalCount" );
			// user.setNIntegral( user.getNIntegral() - integalCut.getNIntegal()
			// );
			// userDao.update( user );
			json.element( "integalID" , integalCut.getId() );
			
		}
		return json;
	}
}
