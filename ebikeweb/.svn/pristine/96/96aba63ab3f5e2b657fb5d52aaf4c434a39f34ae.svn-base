package com.clt.systemmanger.service.imp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IArchiveTypeDao;
import com.clt.systemmanger.dao.IResourceArchiveDao;
import com.clt.systemmanger.model.TArchiveType;
import com.clt.systemmanger.service.IArchiveTypeService;
import com.clt.util.Page;

@Service
public class ArchiveTypeServiceImp implements IArchiveTypeService
{
	@Autowired
	IArchiveTypeDao archiveTypeDao;
	
	@Autowired
	IResourceArchiveDao raDao;
	
	public TArchiveType get( Integer id )
	{
		return archiveTypeDao.get( id );
	}
	
	public void update( TArchiveType entity )
	{
		archiveTypeDao.update( entity );
	}
	
	public void save( TArchiveType entity )
	{
		archiveTypeDao.save( entity );
	}
	
	public void saveOrUpdate( TArchiveType entity )
	{
		archiveTypeDao.saveOrUpdate( entity );
	}
	
	public void delete( TArchiveType entity )
	{
		archiveTypeDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		archiveTypeDao.deleteByKey( id );
	}
	
	public List< TArchiveType > loadAll()
	{
		return archiveTypeDao.loadAll();
	}
	
	public List< TArchiveType > findAll( Page page )
	{
		return archiveTypeDao.findAll( page );
	}
	
	/**
	 * @Description: 加载所有有效的资源
	 * @return List<TArchiveType> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月18日 上午10:01:32
	 * 
	 *              and artype.id>1
	 */
	public List< TArchiveType > loadAllByEnable()
	{
		String hql = " from TArchiveType artype where artype.NEnable=0  order by id asc";
		
		return archiveTypeDao.find( hql );
	}
	
	/**
	 * @Description: 获得资源json树
	 * @param list
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午3:40:23
	 */
	public String getJsonTree( List< TArchiveType > list )
	{
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			StringBuffer sb = new StringBuffer();
			sb.append( "[" );
			for ( TArchiveType atype : list )
			{
				sb.append( "{id:" ).append( atype.getId() ).append( ",name:\"" )
				        .append( atype.getVcArchive() ).append( "\",pId:0}," );
			}
			String str = sb.substring( 0 , sb.length() - 2 );
			str += "]";
			return str;
		}
		return "";
	}
	
}
