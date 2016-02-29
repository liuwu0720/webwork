package com.clt.systemmanger.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.clt.systemmanger.model.TAppVersion;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IAppVersionService;
import com.clt.systemmanger.service.IPictureService;
import com.clt.util.AjaxUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping( "/appVersionAction" )
@Controller
@Api( value = "AppVersionAction-api" , description = "有关App版本的操作" , position = 5 )
public class AppVersionAction
{
	@Autowired
	private IAppVersionService versionService;
	@Autowired
	private IPictureService pictureService;
	
	/**
	 * @Description:进入列表页面
	 * @return
	 */
	@RequestMapping( "/intoAppVersionList" )
	@ApiIgnore
	public String intoAppVersionList()
	{
		return "back/system/appVersionList";
	}
	
	/**
	 * @Description:进入增加/修改页面
	 * @return
	 */
	@RequestMapping( "/intoSavepage" )
	@ApiIgnore
	public String intoSavepage( HttpServletRequest request )
	{
		String idStr = request.getParameter( "id" );
		if ( StringUtils.isNotBlank( idStr ) )
		{
			int id = Integer.parseInt( idStr );
			TAppVersion entity = versionService.get( id );
			String appUrl = entity.getVcAppUrl();
			if ( ! appUrl.startsWith( "http" ) )
			{
				appUrl = versionService.urlHandle( appUrl );
			}
			entity.setVcAppUrl( appUrl );
			// System.out.println( "IUser:" + entity.getIUser() );
			request.setAttribute( "appVersion" , entity );
		}
		return "back/system/appVersionSave";
	}
	
	/**
	 * @Description 增加/修改app版本号
	 * @param entity
	 * @param request
	 * @param response
	 * @author chengwzh
	 * @create date 2015/6/15 11:50
	 */
	@ApiIgnore
	@RequestMapping( value = "/save" , method = RequestMethod.POST )
	public void save( @ModelAttribute TAppVersion entity , HttpSession session ,
	        HttpServletResponse response ,
	        @RequestParam( value = "file" , required = false ) MultipartFile file )
	{
		try
		{
			if ( entity.getId() == null )
			{
				TUser user = ( TUser ) session.getAttribute( "user" );
				int userId = user.getId();
				String username = user.getVcUsername();
				entity.setiUser( userId );
				entity.setVcUserName( username );
				uploadFile( file , entity );
				versionService.save( entity );
			}
			else
			{
				uploadFile( file , entity );
				String appUrl = entity.getVcAppUrl();
				appUrl = appUrl.substring( appUrl.lastIndexOf( "/" ) + 1 );
				entity.setVcAppUrl( appUrl );
				versionService.update( entity );
			}
			AjaxUtil.rendJson( response , true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 删除app版本
	 * @param id
	 * @param response
	 * @author chengwzh
	 * @date 2015/6/15 13:40
	 */
	@ApiIgnore
	@RequestMapping( value = "/del" , method = RequestMethod.POST )
	public void del(
	        @ApiParam( value = "app版本主键,隔开拼接成的字符串" ) @RequestParam( value = "idStr" ) String idStr ,
	        HttpServletResponse response )
	{
		try
		{
			String[] ids = idStr.split( "," );
			for ( String idstr : ids )
			{
				int id = Integer.parseInt( idstr );
				TAppVersion appVersion = versionService.get( id );
				appVersion.setnEnable( SystemConstants.SYS_DISABLE );
				versionService.update( appVersion );
			}
			AjaxUtil.rendJson( response , true , "删除成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "删除失败：" + e.getMessage() );
		}
	}
	
	/**
	 * 查询app版本列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping( value = "/findAllAppVersions" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > findAllAppVersions( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = versionService.findAllVersions( page );
			List< TAppVersion > versions = ( List< TAppVersion > ) result.get( "rows" );
			for ( TAppVersion version : versions )
			{
				String url = version.getVcAppUrl();
				String appUrl = versionService.urlHandle( url );
				version.setVcAppUrl( appUrl );
			}
			result.put( "rows" , versions );
			JSONArray array = JSONArray.fromObject( result );
			System.out.println( "json:" + array );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	@RequestMapping( value = "/getLatestAppVersion" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取最新的app版本" , notes = "获取最新的app版本" , position = 5 )
	public Map< String , Object > getLatestAppVersion( HttpServletRequest request )
	{
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			TAppVersion result = versionService.getLatestVersion();
			if ( null != result )
			{
				String url = result.getVcAppUrl();
				String vcAppUrl = versionService.urlHandle( url );
				result.setVcAppUrl( vcAppUrl );
				return AjaxUtil.getMapByNotException( true , result );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有最新版本！" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 上传文件
	 */
	@RequestMapping( value = "/uploadFile" , method = RequestMethod.POST )
	@ApiIgnore
	// @ApiOperation( value = "上传文件" , notes = "上传文件" , response =
	// TAppVersion.class , position = 5 )
	public void uploadFile( MultipartFile file , TAppVersion entity )
	{
		if ( file == null )
		{
			return;
		}
		if ( ! file.isEmpty() )
		{
			BufferedOutputStream out = null;
			try
			{
				byte[] bytes = file.getBytes();
				String filePath = file.getOriginalFilename();
				String rootPath = pictureService.getPathById( 23 );// 获取app主目录
				if ( ! rootPath.endsWith( "/" ) )
				{
					rootPath += "/";
				}
				rootPath += "app/";
				File f = new File( rootPath );
				if ( ! f.exists() )
				{
					f.mkdirs();
				}
				String path = rootPath + filePath;
				out = new BufferedOutputStream( new FileOutputStream( path ) );
				out.write( bytes );
				entity.setVcAppUrl( filePath );
				// String existPath = entity.getVcAppUrl();
				// if ( StringUtils.isBlank( existPath ) )
				// {
				// entity.setVcAppUrl( filePath );
				// }
				// else
				// {
				// if ( ! existPath.equals( filePath ) )
				// {
				// File existFile = new File( rootPath + existPath );
				// boolean isDel = existFile.delete();
				// if ( isDel )
				// {
				// System.out.println( existPath + "删除成功" );
				// }
				// }
				// entity.setVcAppUrl( filePath );
				// }
				System.out.println( filePath + "上传成功！" );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				// AjaxUtil.rendJson( response , false , "文件：" +
				// file.getOriginalFilename()
				// + "上传失败=>" + e.getMessage() );
				System.out.println( file.getOriginalFilename() + "上传失败！失败原因："
				        + e.getMessage() );
			}
			finally
			{
				if ( out != null )
				{
					try
					{
						out.close();
					}
					catch ( IOException e1 )
					{
						e1.printStackTrace();
						System.out.println( "流关闭异常" );
					}
				}
			}
		}
	}
}
