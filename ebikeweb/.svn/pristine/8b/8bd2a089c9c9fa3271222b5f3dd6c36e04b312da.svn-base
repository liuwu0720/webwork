/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-11 下午2:55:45
 * @version V1.0
 */
package com.clt.systemmanger.service.imp;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IAssessDao;
import com.clt.sub.model.TAssess;
import com.clt.systemmanger.dao.IStoresDao;
import com.clt.systemmanger.model.TStores;
import com.clt.systemmanger.service.IStaticService;
import com.clt.systemmanger.service.IStoresService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-11 下午2:55:45
 * @version V1.0
 */
@Service
public class StoresServiceImp implements IStoresService
{
	@Autowired
	IStoresDao iStoresDao;
	@Autowired
	IAssessDao iAssessDao;
	@Autowired
	private IStaticService staticService;
	
	public TStores get( int id )
	{
		return iStoresDao.get( id );
	}
	
	public void save( TStores entity )
	{
		// 获取4s店编号
		String storeno = getTStoresno();
		entity.setVcStoreNo( storeno );
		iStoresDao.save( entity );
	}
	
	public void update( TStores entity )
	{
		iStoresDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @param page
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-11 下午4:26:22
	 */
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		// TODO Auto-generated method stub
		return iStoresDao.getSpringSQL( sql , page );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-12 上午9:52:17
	 */
	public Map< String , Object > findByHql( HqlHelper hql )
	{
		// TODO Auto-generated method stub
		return iStoresDao.findAllByHqlHelp( hql );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tAssess
	 * @author liuwu
	 * @create_date 2015-6-12 下午3:38:14
	 */
	public void saveAssess( TAssess tAssess )
	{
		// TODO Auto-generated method stub
		iAssessDao.save( tAssess );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param stroeId
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-15 下午3:43:10
	 */
	public TStores getById( int stroeId )
	{
		return iStoresDao.get( stroeId );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tStores
	 * @author liuwu
	 * @create_date 2015-6-15 下午4:04:46
	 */
	public void parseUrl( TStores tStores )
	{
		String perUrl = staticService.getStringByParame( "4sUrl" );
		if ( tStores != null )
		{
			
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}
			
			if ( tStores.getVcImagePath() == null )
			{
				tStores.setVcImagePath( null );
			}
			else
			{
				String jpgPathStr = tStores.getVcImagePath();
				String[] jpgPaths = jpgPathStr.split( "," );
				String vcImgpath = "";
				for ( int i = 0 ; i < jpgPaths.length ; i++ )
				{
					vcImgpath += perUrl + jpgPaths[i];
					if ( i != jpgPaths.length - 1 )
					{
						vcImgpath += ",";
					}
				}
				tStores.setVcImagePath( vcImgpath );
			}
			// 拼接logo图片路径
			String logoPath = tStores.getVcLogoPath();
			logoPath = perUrl + logoPath;
			tStores.setVcLogoPath( logoPath );
		}
	}
	
	public synchronized String getTStoresno()
	{
		List< TStores > list = iStoresDao.findAllAndOrderByProperty( "id" , false );
		String code = "S";
		SimpleDateFormat sft = new SimpleDateFormat( "yyyyMMdd" );
		String dateStr = sft.format( new Date() );
		DecimalFormat dft = new DecimalFormat( "000" );
		String numStr = "";
		if ( CollectionUtils.isEmpty( list ) )
		{
			numStr = dft.format( 1 );
		}
		else
		{
			String storeno = list.get( 0 ).getVcStoreNo();
			if ( storeno.length() < 9 )
			{
				storeno = "0";
			}
			else
			{
				storeno = storeno.substring( 9 , storeno.length() );
			}
			numStr = dft.format( Integer.parseInt( storeno ) + 1 );
		}
		code += dateStr + numStr;
		System.out.println( "4s店新编号：" + code );
		return code;
	}
}
