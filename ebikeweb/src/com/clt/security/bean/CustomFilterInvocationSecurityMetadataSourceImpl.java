package com.clt.security.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.clt.common.UserSession;
import com.clt.systemmanger.dao.IRoleDao;
import com.clt.systemmanger.dao.IRoleResourceDao;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TRoleResource;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserLog;
import com.clt.systemmanger.service.IResourceService;
import com.clt.systemmanger.service.IUserLogService;

/**
 * @Package com.clt.security.bean
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月14日 下午8:01:25
 * @version V1.0
 */
public class CustomFilterInvocationSecurityMetadataSourceImpl implements
        FilterInvocationSecurityMetadataSource
{
	// key:url, value:权限
	private HashMap< String , Collection< ConfigAttribute >> resourceMap = null;
	
	/**
	 * @return the resourceMap
	 */
	public HashMap< String , Collection< ConfigAttribute >> getResourceMap()
	{
		return resourceMap;
	}
	
	/**
	 * @param resourceMap
	 *            the resourceMap to set
	 */
	public void setResourceMap(
	        HashMap< String , Collection< ConfigAttribute >> resourceMap )
	{
		this.resourceMap = resourceMap;
	}
	
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IRoleResourceDao rresourceDao;
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private IUserLogService userLogService;
	
	@PostConstruct
	public void init()
	{
		loadResourceDefine();
	}
	
	/**
	 * 
	 * TODO(程序启动的时候就加载所有资源信息).
	 */
	private void loadResourceDefine()
	{
		// 加载资源有效，且URL不为空的资源
		List< TResource > resources = resourceService.loadByEnableAndUrl();
		
		/*
		 * 应当是资源url为key， 角色名称为value。角色名称就是那些以ROLE_为前缀的值
		 */
		resourceMap = new HashMap< String , Collection< ConfigAttribute >>();
		
		for ( TResource res : resources )
		{
			List< TRoleResource > rrList = rresourceDao.findByProperty( "IResourceId" ,
			        res.getId() );
			for ( TRoleResource rr : rrList )
			{
				ConfigAttribute ca = new SecurityConfig( roleDao.get( rr.getIRoleId() )
				        .getVcRole() );
				
				String url = res.getVcUrl();
				if ( resourceMap.containsKey( url ) )
				{
					// 获得原来的集合
					Collection< ConfigAttribute > value = resourceMap.get( url );
					value.add( ca );
					resourceMap.put( url , value );
				}
				else
				{// 首次添加的key
					Collection< ConfigAttribute > atts = new ArrayList< ConfigAttribute >();
					atts.add( ca );
					resourceMap.put( url , atts );
				}
			}
			
		}
		
	}
	
	public Collection< ConfigAttribute > getAllConfigAttributes()
	{
		return null;
	}
	
	/**
	 * 根据用户访问的uri，加载该uri所需要角色列表　 Object object:uri地址
	 */
	public Collection< ConfigAttribute > getAttributes( Object object )
	        throws IllegalArgumentException
	{
		// 最初请求的uri格式:/crm/index.jsp
		// object 是一个URL ,为用户请求URL
		String url = ( ( FilterInvocation ) object ).getRequestUrl().trim();
		if ( "/".equals( url ) )
		{
			return null;
		}
		
		int firstQuestionMarkIndex = url.indexOf( "?" );
		if ( firstQuestionMarkIndex == - 1 )
			firstQuestionMarkIndex = url.length();
		// 判断请求是否带有参数 如果有参数就去掉后面的后缀和参数(/index.do --> /index)
		if ( firstQuestionMarkIndex != - 1 )
		{
			url = url.substring( 0 , firstQuestionMarkIndex );
		}
		// 最终形成的uri 格式:/index
		
		if ( url.startsWith( "/" ) )
		{
			url = url.substring( 1 );
		}
		
		// 当前有用户，通过URL 获得资源 ，并写入日志里面
		TUserLog userLog = new TUserLog();
		if ( null != UserSession.get( "user" ) )
		{
			TUser user = ( TUser ) UserSession.get( "user" );
			userLog.setIUser( user.getId() );
			userLog.setVcUserName( user.getVcAccount() );
		}
		
		String desc = resourceService.getResourceDescByUrl( url );
		userLog.setVcDesc( desc );
		// 把用户 时间 操作描述 保存到日志表里
		String ip = ( String ) UserSession.get( "ip" );
		userLog.setVcIp( ip );
		
		userLogService.saveLog( userLog );
		
		Iterator< String > ite = resourceMap.keySet().iterator();
		// 取到请求的URL后与上面取出来的资源做比较
		while ( ite.hasNext() )
		{
			String resURL = ite.next().trim();
			if ( url.equals( resURL ) )
			{
				// 获得该uri所需要的角色列表
				return resourceMap.get( resURL );
			}
		}
		// 如果数据库里面没有该uri的信息，表示该uri不需要经过权限验证了
		return null;
	}
	
	public boolean supports( Class< ? > arg0 )
	{
		return true;
	}
	
	/**
	 * 更新某个资源对应的权限
	 * 
	 * @param resourceId
	 */
	public void updateResourceRole( int resourceId )
	{
		
		TResource res = resourceService.get( resourceId );
		String url = res.getVcUrl();
		if ( StringUtils.isNotBlank( url ) )
		{
			resourceMap.remove( url );// 删除原先内容
			List< TRoleResource > rrList = rresourceDao.findByProperty( "IResourceId" ,
			        resourceId );
			for ( TRoleResource rr : rrList )
			{
				ConfigAttribute ca = new SecurityConfig( roleDao.get( rr.getIRoleId() )
				        .getVcRole() );
				
				if ( resourceMap.containsKey( url ) )
				{
					// 获得原来的集合
					Collection< ConfigAttribute > value = resourceMap.get( url );
					value.add( ca );
					resourceMap.put( url , value );
				}
				else
				{// 首次添加的key
					Collection< ConfigAttribute > atts = new ArrayList< ConfigAttribute >();
					atts.add( ca );
					resourceMap.put( url , atts );
				}
			}
		}
	}
}
