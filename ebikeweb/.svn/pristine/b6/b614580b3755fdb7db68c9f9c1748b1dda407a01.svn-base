package com.clt.systemmanger.service.imp;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.common.UserSession;
import com.clt.systemmanger.dao.IResourceDao;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TResourceArchive;
import com.clt.systemmanger.service.IResourceArchiveService;
import com.clt.systemmanger.service.IResourceService;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: 资源管理
 * @author hjx
 * @date 2014年7月12日 下午4:28:00
 * @version V1.0
 */
@Service
public class ResourceServiceImp implements IResourceService
{
	
	@Autowired
	private IResourceDao resourceDao;
	
	@Autowired
	private IResourceArchiveService rArchiveService;
	
	/**
	 * @Description:
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public TResource get( Integer id )
	{
		return resourceDao.get( id );
	}
	
	/**
	 * @Description:
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void update( TResource entity )
	{
		resourceDao.update( entity );
	}
	
	/**
	 * @Description:
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void save( TResource entity )
	{
		resourceDao.save( entity );
	}
	
	/**
	 * @Description:
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void saveOrUpdate( TResource entity )
	{
		resourceDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description:
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void delete( TResource entity )
	{
		rArchiveService.deleteByProperty( "IRoleId" , entity.getId() );
		resourceDao.delete( entity );
	}
	
	/**
	 * @Description:删除资源，并把子节点删除
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void deleteByKey( Integer id )
	{
		rArchiveService.deleteByProperty( "IResource" , id );
		List< TResource > list = resourceDao.findByProperty( "IParent" , id );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			deleteByList( list );
		}
		resourceDao.deleteByKey( id );
	}
	
	/**
	 * @Description: 迭代删除子节点
	 * @param list
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月22日 下午3:19:00
	 */
	private void deleteByList( List< TResource > list )
	{
		for ( TResource r : list )
		{
			List< TResource > sunlist = resourceDao
			        .findByProperty( "IParent" , r.getId() );
			if ( CollectionUtils.isNotEmpty( sunlist ) )
			{
				deleteByList( sunlist );
			}
			resourceDao.delete( r );
		}
	}
	
	/**
	 * @Description:
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TResource > loadAll()
	{
		return resourceDao.loadAll();
	}
	
	/**
	 * @Description: 加载资源有效，且URL不为空的资源
	 * @return List<TResource> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月15日 下午1:43:28
	 */
	public List< TResource > loadByEnableAndUrl()
	{
		String queryString = "from TResource t where t.NEnable=0 and t.vcUrl is not null ";
		return resourceDao.find( queryString );
	}
	
	/**
	 * @Description: 新增时保存资源，并把保存 资源档案关联表
	 * @param entity
	 * @param archiveTypeId
	 * @author hjx
	 * @create_date 2014年7月14日 下午2:26:31
	 */
	public void saveOrUpdateRA( TResource entity , String archiveTypeId )
	{
		this.saveOrUpdate( entity );
		rArchiveService.saveOrUpdateRAndT( entity.getId() , archiveTypeId );
	}
	
	/**
	 * @Description: 更新时保存资源，并把更新 资源档案关联表
	 * @param entity
	 *            资源实体类
	 * @param archiveTypeIdOld
	 *            原来的 资源档案id
	 * @param archiveTypeId
	 *            更新后 新的资源档案id void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月14日 下午4:00:06
	 */
	public void update( TResource entity , String archiveTypeIdOld , String archiveTypeId )
	{
		this.update( entity );
		if ( ! archiveTypeIdOld.equals( archiveTypeId ) )
		{
			TResourceArchive resourceArchive = rArchiveService.getByResourceAndArchive(
			        entity.getId() , Integer.parseInt( archiveTypeId ) );
			resourceArchive.setIArchiveType( Integer.parseInt( archiveTypeIdOld ) );
			rArchiveService.update( resourceArchive );
		}
	}
	
	public List< TResource > loadAllByEnable()
	{
		return resourceDao.loadAllByEnable();
	}
	
	/**
	 * @Description: 获得json树
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午2:24:37
	 */
	public String getJsonTree( List< TResource > list )
	{
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			String path = ( String ) UserSession.get( "basePath" );
			JSONArray arr = new JSONArray();
			for ( TResource r : list )
			{
				JSONObject obj = new JSONObject();
				obj.element( "id" , r.getId() );
				obj.element( "pId" , r.getIParent() );
				obj.element( "name" , r.getVcResourceName() );
				obj.element( "vurl" , r.getVcUrl() );
				
				/*if ( StringUtils.isNotBlank( r.getVcIcon() ) )
				{
					obj.element( "iconSkin" , r.getVcIcon() );
				}*/
				obj.element( "ntype" , r.getNType() );// 添加 资源类型
				obj.element( "click" , "false" );
				obj.element( "leaf" , r.getNLeaf() );// 添加 资源类型
				arr.add( obj );
			}
			
			return arr.toString();
		}
		
		return "";
	}
	
	/**
	 * @Description: 
	 *               根据url获得对应资源名称以及资源的上一级资源的名称（不断递归上去，直到没有上一级为止），如果没有找到资源，则把url的值返回
	 *               。
	 * @param Url
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年12月10日 下午4:58:04
	 */
	public String getResourceDescByUrl( String Url )
	{
		List< TResource > resources = resourceDao.findByProperty( "vcUrl" , Url );
		if ( CollectionUtils.isNotEmpty( resources ) )
		{
			String desc = "";
			for ( TResource resource : resources )
			{
				desc = resource.getVcResourceName() + desc;
				desc = getDesc( resource , desc );
			}
			return desc;
		}
		return "访问：" + Url;
	}
	
	/**
	 * @Description: 递归得出资源上级名称
	 * @param r
	 * @param desc
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年12月10日 下午5:14:14
	 */
	private String getDesc( TResource r , String desc )
	{
		if ( 0 != r.getNType() )
		{
			TResource tResource = resourceDao.get( r.getIParent() );
			desc = tResource.getVcResourceName() + " -->" + desc;
			getDesc( tResource , desc );
		}
		return desc;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author liuwu
	 * @create_date 2015-3-27 下午2:41:08
	 */
	public List< TResource > getAll()
	{
		// TODO Auto-generated method stub
		return resourceDao.findAll();
	}
}
