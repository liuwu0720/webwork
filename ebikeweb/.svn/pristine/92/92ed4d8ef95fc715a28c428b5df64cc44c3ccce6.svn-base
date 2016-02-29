package com.clt.systemmanger.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IPicNewDao;
import com.clt.systemmanger.model.TPicNew;
import com.clt.systemmanger.service.IPicNewService;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description:
 * @author hjx
 * @date 2014年7月28日 下午4:27:21
 * @version V1.0
 */
@Service
public class PicNewServiceImp implements IPicNewService
{
	
	@Autowired
	private IPicNewDao picNewDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:28:37
	 */
	public TPicNew get( Integer id )
	{
		return picNewDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:28:37
	 */
	public void update( TPicNew entity )
	{
		picNewDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:28:37
	 */
	public void save( TPicNew entity )
	{
		picNewDao.save( entity );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:28:37
	 */
	public void saveOrUpdate( TPicNew entity )
	{
		picNewDao.saveOrUpdate( entity );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:28:37
	 */
	public void delete( TPicNew entity )
	{
		picNewDao.delete( entity );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:28:37
	 */
	public void deleteByKey( Integer id )
	{
		picNewDao.deleteByKey( id );
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:28:37
	 */
	public List< TPicNew > loadAll()
	{
		return picNewDao.loadAll();
	}
	
	/**
	 * @Description: 获得正在展示的图片新闻
	 * @return List<TPicNew> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:31:06
	 */
	public List< TPicNew > loadByDisplay()
	{
		return picNewDao.findByPropertys( new String[] { "NEnable" , "NDisaplay" } ,
		        new Object[] { 0 , 0 } );
	}
	
	/**
	 * @Description: 获得正在展示的图片新闻的HTML
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:35:01
	 */
	public String getPicDisplayHtml()
	{
		List< TPicNew > list = picNewDao.findByPropertys( new String[] { "NEnable" ,
		        "NDisaplay" , "NType" } , new Object[] { 0 , 0 , 1 } );
		StringBuffer sb = new StringBuffer();
		for ( TPicNew pic : list )
		{
			sb.append( "<li><a href=\"" ).append( pic.getVcLinkUrl() )
			        .append( "\"><img src=\"" ).append( pic.getVcPicUrl() )
			        .append( "\"/></a></li>" );
		}
		return sb.toString();
	}
	
	/**
	 * @Description: 获得正在展示的文字新闻的HTML
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:35:01
	 */
	public String getNewDisplayHtml()
	{
		List< TPicNew > list = picNewDao.findByPropertys( new String[] { "NEnable" ,
		        "NDisaplay" , "NType" } , new Object[] { 0 , 0 , 0 } );
		StringBuffer sb = new StringBuffer();
		for ( TPicNew pic : list )
		{
			sb.append( "<li><a href=\"" ).append( pic.getVcLinkUrl() ).append( "\">" )
			        .append( pic.getVcDesc() ).append( "</a></li>" );
		}
		return sb.toString();
	}
	
}
