package com.clt.systemmanger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clt.systemmanger.model.TArchiveType;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.service.IArchiveTypeService;
import com.clt.systemmanger.service.IResourceArchiveService;
import com.clt.systemmanger.service.IResourceService;
import com.clt.util.AjaxUtil;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description:
 * @author hjx
 * @date 2014年7月19日 上午10:03:27
 * @version V1.0
 */
@Controller
@RequestMapping( "/resource" )
@ApiIgnore
public class ResourceAction
{
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IArchiveTypeService atypeService;
	@Autowired
	private IResourceArchiveService raService;
	
	/**
	 * @Description: 打开资源页面，展示资源树 json
	 * @return List<TResource> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 下午1:49:52
	 */
	@RequestMapping( "/getAllResource" )
	public String getAllResource( HttpServletRequest request )
	{
		List< TResource > list = resourceService.loadAll();
		// 获得所有资源
		String jsonStr = resourceService.getJsonTree( list );
		request.setAttribute( "jsonStr" , jsonStr );
		System.out.println( jsonStr );
		// 获得所有档案类型
		List< TArchiveType > atypes = atypeService.loadAllByEnable();
		String types = atypeService.getJsonTree( atypes );
		request.setAttribute( "types" , types );
		return "back/system/resourcetree";// 跳转到资源页面
	}
	
	@RequestMapping( "/openAddPage" )
	public String openAddPage( HttpServletRequest request )
	{
		List< TArchiveType > allList = atypeService.loadAllByEnable();
		JSONArray arr = new JSONArray();
		for ( TArchiveType atype : allList )
		{
			JSONObject obj = new JSONObject();
			obj.put( "id" , atype.getId() );
			obj.put( "name" , atype.getVcArchive() );
			obj.put( "pId" , "0" );
			arr.add( obj );
		}
		request.setAttribute( "typeJson" , arr.toString() );// 资源所属类型 下拉树 json数据
		return "back/system/resource";
	}
	
	/**
	 * @Description: 根据资源id获得资源信息
	 * @param resourceId
	 *            资源id
	 * @return TResource 资源信息json
	 * @author hjx
	 * @create_date 2014年7月19日 上午11:05:40
	 */
	@RequestMapping( "/getResourceByid" )
	public String getResourceByid( HttpServletRequest request ,
	        @RequestParam( "resourceId" ) String resourceId )
	{
		
		TResource resource = resourceService.get( Integer.parseInt( resourceId ) );
		request.setAttribute( "resource" , resource );
		List< TArchiveType > list = raService.getByResourceId( Integer
		        .parseInt( resourceId ) );
		List< TArchiveType > allList = atypeService.loadAllByEnable();
		JSONArray arr = new JSONArray();
		for ( TArchiveType atype : allList )
		{
			JSONObject obj = new JSONObject();
			obj.put( "id" , atype.getId() );
			obj.put( "name" , atype.getVcArchive() );
			obj.put( "pId" , "0" );
			if ( CollectionUtils.isNotEmpty( list ) )
			{
				if ( list.contains( atype ) )
				{
					obj.put( "checked" , "true" );
				}
			}
			arr.add( obj );
		}
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			String typeStr = "";
			String typeId = "";
			for ( TArchiveType type : list )
			{
				typeStr += "," + type.getVcArchive();
				typeId += "," + type.getId();
			}
			
			if ( ! "".equals( typeStr ) )
			{
				request.setAttribute( "typeStr" , typeStr.substring( 1 ) );// 当前所属资源名称
				request.setAttribute( "typeId" , typeId.substring( 1 ) );// 当前所属资源类型id
			}
		}
		request.setAttribute( "typeJson" , arr.toString() );// 资源所属类型 下拉树 json数据
		return "back/system/resource";
	}
	
	/**
	 * @Description: 新增或者更新 资源信息，并更新资源和档案的关联信息
	 * @param resource
	 *            资源信息
	 * @param archiveTypeIds
	 *            多个档案类型id以逗号组合的 ids字符串
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 上午11:47:11
	 */
	@RequestMapping( "/saveResource" )
	public void saveResource( TResource resource ,
	        @RequestParam( "archiveTypeIds" ) String archiveTypeIds ,
	        HttpServletResponse response )
	{
		try
		{
			resourceService.saveOrUpdateRA( resource , archiveTypeIds );
			AjaxUtil.rendJson( response , true , "资源保存成功，并更新资源和档案的关联信息！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "资源保存失败！" + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 删除资源，并把相关的资源档案关联信息删除 如果不是叶子节点应该其子、孙节点都删除
	 * @param resourceId
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 上午11:59:12
	 */
	@RequestMapping( "/delResource" )
	public void delResource( @RequestParam( "resourceId" ) String resourceId ,
	        HttpServletResponse response )
	{
		try
		{
			resourceService.deleteByKey( Integer.parseInt( resourceId ) );
			AjaxUtil.rendJson( response , true , "资源删除成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "资源删除失败！失败原因：" + e.getMessage() );
		}
		
	}
	
}
