package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ISharecircleDao;
import com.clt.sub.model.TSharecircle;
import com.clt.sub.service.ISharecircleService;
import com.clt.systemmanger.dao.ITShareTagDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TShareTag;
import com.clt.systemmanger.service.IStaticService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Description 分享圈服务类
 * @author chengwzhuo
 * @date 2015/4/30 10:28
 * @version V1.0
 */
@Service
public class SharecircleServiceImp implements ISharecircleService
{
	
	@Autowired
	private ISharecircleDao shareDao;
	@Autowired
	private ITShareTagDao tagDao;
	@Autowired
	private IStaticService staticService;
	@Autowired
	private IUserDao userDao;
	
	public TSharecircle get( Integer id )
	{
		return shareDao.get( id );
	}
	
	public void save( TSharecircle entity )
	{
		shareDao.save( entity );
	}
	
	public void update( TSharecircle entity )
	{
		shareDao.update( entity );
		
	}
	
	public void updateCleanBefore( TSharecircle entity )
	{
		shareDao.updateCleanBefore( entity );
	}
	
	public void saveOrUpdate( TSharecircle entity )
	{
		shareDao.saveOrUpdate( entity );
	}
	
	public void delete( TSharecircle entity )
	{
		shareDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		shareDao.deleteByKey( id );
	}
	
	public List< TSharecircle > findAll( Page page )
	{
		return shareDao.findAll();
	}
	
	public List< TSharecircle > loadAll()
	{
		return shareDao.loadAll();
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return shareDao.findAllByHqlHelp( helper );
	}
	
	public List< TSharecircle > findByPropertys( String[] propertyNames , Object[] values )
	{
		return shareDao.findByPropertys( propertyNames , values );
	}
	
	public Map< String , Object > getSpringSQL( String sql , Page page )
	{
		return shareDao.getSpringSQL( sql , page );
	}
	
	@Cacheable( value = "shareTagCache" )
	public List< TShareTag > getAllTags()
	{
		return tagDao.findByProperty( "nEnable" , SystemConstants.SYS_ENABLE );
	}
	
	/**
	 * 解析结果集中 每个对象里的url，修改url，使其可以在客户端进行加载 hjx
	 * 
	 * @param map
	 * @return
	 */
	public Map< String , Object > parseUrl( Map< String , Object > map )
	{
		List< TSharecircle > list = ( List< TSharecircle > ) map.get( "rows" );
		String perUrl = staticService.getStringByParame( "perUrl" );
		if ( CollectionUtils.isNotEmpty( list ) && StringUtils.isNotEmpty( perUrl ) )
		{
			
			if ( ! perUrl.endsWith( "/" ) )
			{
				perUrl += "/";
			}
			// 默认头像
			String subImgPath = staticService.getStringByParame( "imgUrl" );
			if ( StringUtils.isNotBlank( subImgPath ) )
			{
				if ( ! subImgPath.endsWith( "/" ) )
				{
					subImgPath += "/";
				}
			}
			// 获取司机图片存放路径
			String driverPath = staticService.getStringByParame( "driverUrl" );
			if ( StringUtils.isNotBlank( driverPath ) )
			{
				if ( ! driverPath.endsWith( "/" ) )
				{
					driverPath += "/";
				}
			}
			// 获取分供方图片存放路径
			String subPath = staticService.getStringByParame( "subUrl" );
			if ( StringUtils.isNotBlank( subPath ) )
			{
				if ( ! subPath.endsWith( "/" ) )
				{
					subPath += "/";
				}
			}
			List< TSharecircle > parseList = new ArrayList< TSharecircle >();
			for ( TSharecircle share : list )
			{
				String vcHeadImg = share.getVcHeadImg();
				// 判断如果分享人头部图像为空则显示分供方图片
				if ( StringUtils.isEmpty( vcHeadImg ) )
				{
					share.setVcHeadImg( subImgPath );
				}
				else
				{
					// share.setVcHeadImg( perUrl + vcHeadImg );
					int iUserId = share.getIUserid();
					int type = userDao.get( iUserId ).getIArchiveType();// 获取档案类型
					if ( type == SystemConstants.SYS_TARCHIVE_DRIVER )
					{
						share.setVcHeadImg( driverPath + vcHeadImg );
					}
					else
					{
						share.setVcHeadImg( subPath + vcHeadImg );
					}
				}
				String jpgPathStr = share.getVcImgpath();
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
				share.setVcImgpath( vcImgpath );
				String vcShareTag = share.getVcShareTag();
				if ( StringUtils.isNotBlank( vcShareTag ) )
				{
					String[] tagIdStrs = vcShareTag.split( "," );// id 数组
					String tagNameStr = "";
					for ( int i = 0 ; i < tagIdStrs.length ; i++ )
					{
						Integer tagId = Integer.parseInt( tagIdStrs[i] );
						String tagName = tagDao.get( tagId ).getVcTag();
						tagNameStr += tagName;
						if ( i != tagIdStrs.length - 1 )
						{
							tagNameStr += ",";
						}
					}
					share.setVcShareTag( tagNameStr );// 把id字符串替换成分享标签字符串
				}
				parseList.add( share );
				
			}
			map.put( "rows" , parseList );
		}
		return map;
	}
}
