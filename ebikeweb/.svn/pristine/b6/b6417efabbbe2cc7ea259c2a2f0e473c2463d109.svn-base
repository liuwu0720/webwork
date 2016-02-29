/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-3-25 下午3:04:20
 * @version V1.0
 */
package com.clt.systemmanger.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TAppSwitch;
import com.clt.systemmanger.model.TCarStylePic;
import com.clt.systemmanger.model.TDriverClass;
import com.clt.systemmanger.model.TGpsrate;
import com.clt.systemmanger.model.TPicPath;
import com.clt.systemmanger.model.TPicType;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TShareTag;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserStatus;
import com.clt.systemmanger.service.IBasicManageService;
import com.clt.systemmanger.service.IResourceService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.ImageUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: TODO(基础信息维护)
 * @author liuwu
 * @date 2015-3-25 下午3:04:20
 * @version V1.0
 */
@Controller
@RequestMapping( "/basicManage" )
@ApiIgnore
public class BasicManageAction
{
	@Autowired
	IBasicManageService iBasicManageService;
	@Autowired
	IResourceService resourceService;
	@Autowired
	ISubsuppliersService subService;
	
	/**
	 * 
	 * @Description: TODO(跳转至司机分类页面)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:32:28
	 */
	@RequestMapping( "/driverClassificationAction " )
	public String getCustomerList( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "back/system/driverClass";
		
	}
	
	@RequestMapping( "/userStatusAction" )
	public String userStatusAction( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "back/system/userStatus";
	}
	
	/**
	 * 
	 * @Description: TODO(跳转至车型商标)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:32:28
	 */
	@RequestMapping( "/carStylePic " )
	public String carStylePic( HttpServletRequest request , HttpServletResponse response )
	{
		return "back/system/carStylePic";
		
	}
	
	/**
	 * 
	 * @Description: TODO(跳转至分享圈页面)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:32:28
	 */
	@RequestMapping( "/shareTagAction " )
	public String shareCircleAction( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "back/system/shareTag";
		
	}
	
	/**
	 * 
	 * @Description: TODO(跳转至图片路径维护页面)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:32:28
	 */
	@RequestMapping( "/picPathManage " )
	public String picPathManage( HttpServletRequest request , HttpServletResponse response )
	{
		return "back/system/picPathManage";
		
	}
	
	/**
	 * 
	 * @Description: TODO(跳转至图片分类)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:32:28
	 */
	@RequestMapping( "/picTypes " )
	public String picTypes( HttpServletRequest request , HttpServletResponse response )
	{
		return "back/system/picTypes";
		
	}
	
	/**
	 * 
	 * @Description: TODO(跳转至APP开关维护)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:32:28
	 */
	@RequestMapping( "/appSwitchManage " )
	public String appSwitchManage( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "back/system/appSwitchManage";
		
	}
	
	/**
	 * 
	 * @Description: TODO(跳转至汽车商标)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:32:28
	 */
	@RequestMapping( "/carStylePicAction " )
	public String carStylePicAction( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "back/system/carStylePic";
		
	}
	
	/**
	 * 
	 * @Description: TODO(跳转至GPS频率定位)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @create_date 2015-3-25 下午3:32:28
	 */
	@RequestMapping( "/getGpsRateAction " )
	public String getGpsRateAction( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "back/system/getGpsRate";
		
	}
	
	/**
	 * 
	 * @Description: TODO(gps定位List)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:45:53
	 */
	@RequestMapping( "/getGpsRateList" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getGpsRateList( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TGpsrate.class );
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();
		helper.addEqual( "vcSubno" , subbo );
		helper.setQueryPage( page );
		Map< String , Object > resultMap = iBasicManageService
		        .findGpsRate( helper );
		
		return resultMap;
	}
	
	/**
	 * 
	 * @Description: TODO(用户状态列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:45:53
	 */
	@RequestMapping( "/getUserStatusList" )
	@ResponseBody
	public Map< String , Object > getUserStatusList( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TUserStatus.class );
		helper.setQueryPage( page );
		Map< String , Object > resultMap = iBasicManageService
		        .findUserStatusList( helper );
		
		return resultMap;
	}
	
	/**
	 * 
	 * @Description: TODO(图片路径列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:45:53
	 */
	@RequestMapping( "/getPicPathList" )
	@ResponseBody
	public Map< String , Object > getPicPathList( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TPicPath.class );
		helper.setQueryPage( page );
		Map< String , Object > resultMap = iBasicManageService.findPicPathList( helper );
		
		return resultMap;
	}
	
	/**
	 * 
	 * @Description: TODO(汽车商标图片维护)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:45:53
	 */
	@RequestMapping( "/getCarPicStyleList" )
	@ResponseBody
	public Map< String , Object > getCarPicStyleList( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TCarStylePic.class );
		helper.setQueryPage( page );
		Map< String , Object > resultMap = iBasicManageService
		        .findTCarStylePicList( helper );
		
		return resultMap;
	}
	
	/**
	 * 
	 * @Description: TODO(app开关)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:45:53
	 */
	@RequestMapping( "/getAppSwitchList" )
	@ResponseBody
	public Map< String , Object > getAppSwitchList( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TAppSwitch.class );
		helper.setQueryPage( page );
		Map< String , Object > resultMap = iBasicManageService.findAppSwitchList( helper );
		
		return resultMap;
	}
	
	/**
	 * 
	 * @Description: TODO(图片分类列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:45:53
	 */
	@RequestMapping( "/getPicTypeList" )
	@ResponseBody
	public Map< String , Object > getPicTypeList( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TPicType.class );
		helper.setQueryPage( page );
		Map< String , Object > resultMap = iBasicManageService.findPicTypeList( helper );
		
		return resultMap;
	}
	
	/**
	 * 
	 * @Description: TODO(司机分类列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:45:53
	 */
	@RequestMapping( "/getDriverClassList" )
	@ResponseBody
	public Map< String , Object > getDriverClassList( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TDriverClass.class );
		helper.setQueryPage( page );
		Map< String , Object > resultMap = iBasicManageService
		        .findDriverClassList( helper );
		
		return resultMap;
	}
	
	/**
	 * 
	 * @Description: TODO(分享圈标签分类列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午3:45:53
	 */
	@RequestMapping( "/getShareTagList" )
	@ResponseBody
	public Map< String , Object > getShareTagList( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TShareTag.class );
		helper.setQueryPage( page );
		Map< String , Object > resultMap = iBasicManageService.findShareTagList( helper );
		
		return resultMap;
	}
	


	/**
	 * 
	 * @Description: TODO(图片路径分类新增、编辑)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:29:54
	 */
	@RequestMapping( "/savePicPathBefore" )
	public String savePicPathBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String costID = request.getParameter( "cuID" );// 表主键ID
		TPicPath tPicPath = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tPicPath = iBasicManageService.getPicPathById( Integer.parseInt( costID ) );
			
		}
		List< TPicType > newPicTypes = new ArrayList< TPicType >();
		List< TPicType > tPicTypes = iBasicManageService.findAllPicTypes();
		// 过滤掉无效的图片分类
		for ( TPicType iPicType : tPicTypes )
		{
			if ( iPicType.getnEnable().equals( 0 ) )
			{
				newPicTypes.add( iPicType );
			}
			
		}
		request.setAttribute( "tPictypes" , newPicTypes );
		request.setAttribute( "tPicPath" , tPicPath );
		return "back/system/picPathManageSave";
		
	}
	
	/**
	 * 
	 * @Description: TODO(图片分类新增、编辑)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:29:54
	 */
	@RequestMapping( "/savePicTypeBefore" )
	public String savePicTypeBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String costID = request.getParameter( "cuID" );// 表主键ID
		TPicType tPicType = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tPicType = iBasicManageService.getPicTypeById( Integer.parseInt( costID ) );
			
		}
		
		request.setAttribute( "tPicType" , tPicType );
		return "back/system/picTypesSave";
		
	}
	
	/**
	 * 
	 * @Description: TODO(图片分类新增、编辑)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:29:54
	 */
	@RequestMapping( "/saveGpsRateBefore" )
	public String saveGpsRateBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String costID = request.getParameter( "cuID" );// 表主键ID
		TGpsrate tGpsrate = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tGpsrate = iBasicManageService.getGpsRateById( Integer
			        .parseInt( costID ) );
			request.setAttribute( "tGpsrate" , tGpsrate );
		}
		
		return "back/system/getGpsRateSave";
		
	}
	
	/**
	 * 
	 * @Description: TODO(用户状态)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:29:54
	 */
	@RequestMapping( "/saveUserStatusBefore" )
	public String saveUserStatusBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String costID = request.getParameter( "cuID" );// 表主键ID
		TUserStatus tUserStatus = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tUserStatus = iBasicManageService.getUserStatusById( Integer
			        .parseInt( costID ) );
			
		}
		
		request.setAttribute( "tUserStatus" , tUserStatus );
		return "back/system/userStatusSave";
		
	}
	
	/**
	 * 
	 * @Description: TODO(用户状态)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:29:54
	 */
	@RequestMapping( "/saveCarStylePicBefore" )
	public String saveCarStylePicBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String costID = request.getParameter( "cuID" );// 表主键ID
		TCarStylePic tCarStylePic = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tCarStylePic = iBasicManageService.getCarStylePicById( Integer
			        .parseInt( costID ) );
			
		}
		
		request.setAttribute( "tCarStylePic" , tCarStylePic );
		return "back/system/carStylePicSave";
		
	}
	
	/**
	 * 
	 * @Description: TODO(司机分类新增、编辑)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:29:54
	 */
	@RequestMapping( "/saveDirverClassBefore" )
	public String saveDirverClassBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String costID = request.getParameter( "cuID" );// 表主键ID
		TDriverClass tDriverClass = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tDriverClass = iBasicManageService.getDriverClassById( Integer
			        .parseInt( costID ) );
			
		}
		request.setAttribute( "tDriverClass" , tDriverClass );
		return "back/system/driverClassSave";
		
	}
	
	/**
	 * 
	 * @Description: TODO(分享圈分类新增、编辑)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:29:54
	 */
	@RequestMapping( "/saveShareTagBefore" )
	public String saveShareTagBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String costID = request.getParameter( "id" );// 表主键ID
		TShareTag tShareTag = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tShareTag = iBasicManageService.getShareTagById( Integer.parseInt( costID ) );
			
		}
		request.setAttribute( "tShareTag" , tShareTag );
		return "back/system/shareTagSave";
		
	}
	
	/**
	 * 
	 * @Description: TODO(App开关新增、编辑)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午4:29:54
	 */
	@RequestMapping( "/saveAppSwitchBefore" )
	public String saveAppSwitchBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String paramType = "add";
		String costID = request.getParameter( "id" );// 表主键ID
		TAppSwitch tAppSwitch = null;
		TResource tResource = null;
		if ( StringUtils.isNotBlank( costID ) )
		{
			paramType = "update";
			tAppSwitch = iBasicManageService
			        .getAppSwitchById( Integer.parseInt( costID ) );
			if ( tAppSwitch.getiResource() != null )
			{
				tResource = resourceService.get( tAppSwitch.getiResource() );
				request.setAttribute( "typeStr" , tResource.getVcResourceName() );
			}
			
		}
		List< TResource > tResources = resourceService.getAll();
		List< TResource > newResources = new ArrayList< TResource >();
		// 筛选出有效的，手机端的资源
		for ( TResource tr : tResources )
		{
			if ( tr.getNEnable().equals( 0 ) )
			{
				if ( tr.getnFlag().equals( 0 ) || tr.getnFlag().equals( 2 ) )
				{
					newResources.add( tr );
				}
			}
		}
		JSONArray arr = new JSONArray();
		for ( TResource newtResource : newResources )
		{
			JSONObject obj = new JSONObject();
			obj.put( "id" , newtResource.getId() );
			obj.put( "name" , newtResource.getVcResourceName() );
			obj.put( "pId" , newtResource.getIParent() );
			/*if ( CollectionUtils.isNotEmpty( newResources ) )
			{
				if ( newResources.contains( tResource ) )
				{
					obj.put( "checked" , "true" );
				}
				
			
			}*/
			arr.add( obj );
		}
		
		request.setAttribute( "typeJson" , arr.toString() );// 资源所属类型 下拉树 json数据
		request.setAttribute( "tAppSwitch" , tAppSwitch );
		return "back/system/appSwitchManageSave";
		
	}
	
	/**
	 * 
	 * @Description: TODO(保存司机分类)
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:31:00
	 */
	@RequestMapping( "/saveDirverClass" )
	public void saveDirverClass( HttpServletRequest request , TDriverClass driverClass ,
	        HttpServletResponse response )
	{
		try
		{
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			driverClass.setVcAdduser( user.getVcAccount() );
			if ( StringUtils.isNotEmpty( driverClass.getId() + "" ) )
			{
				driverClass.setDtAdd( new Date() );
				iBasicManageService.saveOrUpdateDriverClass( driverClass );
			}
			else
			{
				driverClass.setDtAdd( new Date() );
				iBasicManageService.saveDriverClass( driverClass );
			}
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败！失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(保存分享圈标签分类)
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:31:00
	 */
	@RequestMapping( "/saveShareTag" )
	public void saveShareTag( HttpServletRequest request , TShareTag shareTag ,
	        HttpServletResponse response )
	{
		try
		{
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			shareTag.setVcAdduser( user.getVcAccount() );
			if ( StringUtils.isNotEmpty( shareTag.getId() + "" ) )
			{
				shareTag.setDtAdd( new Date() );
				iBasicManageService.saveOrUpdateShareTag( shareTag );
			}
			else
			{
				shareTag.setDtAdd( new Date() );
				iBasicManageService.saveShareTag( shareTag );
			}
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败！失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(保存图片路径分类)
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:31:00
	 */
	@RequestMapping( "/savePicPath" )
	public void savePicPath( HttpServletRequest request , TPicPath tPicPath ,
	        HttpServletResponse response )
	{
		try
		{
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			tPicPath.setVcAdduser( user.getVcAccount() );
			if ( StringUtils.isNotEmpty( tPicPath.getId() + "" ) )
			{
				tPicPath.setDtAdd( new Date() );
				iBasicManageService.saveOrUpdateTpicPath( tPicPath );
			}
			else
			{
				tPicPath.setDtAdd( new Date() );
				iBasicManageService.saveTPicPath( tPicPath );
			}
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败！失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(保存图片分类)
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:31:00
	 */
	@RequestMapping( "/savePicType" )
	public void savePicType( HttpServletRequest request , TPicType tPicType ,
	        HttpServletResponse response )
	{
		try
		{
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			tPicType.setVcAdduser( user.getVcAccount() );
			if ( StringUtils.isNotEmpty( tPicType.getId() + "" ) )
			{
				tPicType.setDtAdd( new Date() );
				iBasicManageService.saveOrUpdateTpicType( tPicType );
			}
			else
			{
				tPicType.setDtAdd( new Date() );
				iBasicManageService.saveTPicType( tPicType );
			}
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败！失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(保存APP开关)
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-25 下午5:31:00
	 */
	@RequestMapping( "/saveAppSwitch" )
	public void saveAppSwitch( HttpServletRequest request , String typeStr ,
	        TAppSwitch tAppSwitch , HttpServletResponse response )
	{
		try
		{
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			tAppSwitch.setVcAdduser( user.getVcAccount() );
			tAppSwitch.setnEnable( tAppSwitch.getnOnOff() );
			tAppSwitch.setDtAdd( new Date() );
			if ( StringUtils.isNotEmpty( tAppSwitch.getId() + "" ) )
			{
				iBasicManageService.saveOrUpdateAppSwitch( tAppSwitch );
			}
			else
			{
				iBasicManageService.saveAppSwitch( tAppSwitch );
			}
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败！失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(打开消息推送页面)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 下午1:40:50
	 */
	@RequestMapping( "/pushShareTag" )
	public String pushShareTag( HttpServletRequest request )
	{
		List< TShareTag > tShareTags = iBasicManageService.findAllShareTags();
		List< JSONObject > newList = new ArrayList< JSONObject >();
		for ( TShareTag tag : tShareTags )
		{
			if ( tag.getnEnable().equals( 0 ) )
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put( "id" , tag.getId() );
				jsonObject.put( "tagname" , tag.getVcTag() );
				newList.add( jsonObject );
			}
			
		}
		System.out.println( " JSONArray.fromObject( newList ) = "
		        + JSONArray.fromObject( newList ).toString() );
		request.setAttribute( "shareTitle" , "分享圈分类标签有所更新" );
		request.setAttribute( "shareTag" , JSONArray.fromObject( newList ).toString() );
		return "back/system/shareTagPush";
	}
	
	/**
	 * 
	 * @Description: TODO(点击确认按钮时开始推送)
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-3-26 下午2:10:33
	 */
	@RequestMapping( "/savePushShareTag" )
	public void savePushShareTag( HttpServletRequest request ,
	        HttpServletResponse response , String shareTitle , String type )
	{
		List< TShareTag > tShareTags = iBasicManageService.findAllShareTags();
		List< JSONObject > newList = new ArrayList< JSONObject >();
		for ( TShareTag tag : tShareTags )
		{
			if ( tag.getnEnable().equals( 0 ) )
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put( "id" , tag.getId() );
				jsonObject.put( "tagname" , tag.getVcTag() );
				newList.add( jsonObject );
			}
			
		}
		System.out.println( " JSONArray.fromObject( newList ) = "
		        + JSONArray.fromObject( newList ).toString() );
		String shareMessage = JSONArray.fromObject( newList ).toString();
		try
		{
			iBasicManageService.pushMessageByType( type , shareTitle , shareMessage );
			AjaxUtil.rendJson( response , true , "消息推送成功" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "消息推送失败！" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(保存用户状态)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @throws UnsupportedEncodingException
	 * @create_date 2015-3-26 下午1:40:50
	 */
	@RequestMapping( "/saveUserStatus" )
	public void saveUserStatus( HttpServletRequest request ,
	        HttpServletResponse response , TUserStatus tUserStatus )
	        throws UnsupportedEncodingException
	{
		String path = null;
		String imgPath = SystemConstants.USER_STATUS_IMG_PATH;
		try
		{
			
			MultipartHttpServletRequest multipartRequest = ( MultipartHttpServletRequest ) request;
			String source = request.getSession().getServletContext().getRealPath( "/" );
			// 获得第1张图片（根据前台的name名称得到上传的文件）
			MultipartFile imgFile1 = multipartRequest.getFile( "imageFile" );
			// 判断是否是重复上传图片（如果重复将删除旧图片）
			String sourceImg = request.getParameter( "path" );
			if ( imgFile1.getContentType().split( "/" )[0].equals( "image" ) )
			{
				if ( null != sourceImg && ! "".equals( sourceImg ) )
				{
					System.out.println( "旧图片路径:" + sourceImg );
					File f = new File( source + sourceImg );
					if ( f.isFile() )
					{
						f.delete();
						System.out.println( " 删除成功" );
					}
					else
						System.out.println( " 删除失败!" );
				}
				
				if ( imgFile1 != null && imgFile1.getSize() > 0 )
				{
					path = this.storeFile( request.getSession() , imgFile1 , imgPath );
				}
				
			}
			
		}
		catch ( Exception e )
		{
			// TODO: handle exception
			
		}
		
		try
		{
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			tUserStatus.setVcAdduser( user.getVcAccount() );
			tUserStatus.setDtAdd( new Date() );
			if ( path != null )
			{
				tUserStatus.setVcPath( path );
			}
			
			if ( StringUtils.isNotEmpty( tUserStatus.getId() + "" ) )
			{
				iBasicManageService.saveOrUpdateUserStatus( tUserStatus );
			}
			else
			{
				
				iBasicManageService.saveUserStatus( tUserStatus );
			}
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败！失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(保存GPS频率修改)
	 * @param request
	 * @param response
	 * @param tGpsrate
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-22 上午10:20:08
	 */
	@RequestMapping( "/saveGpsRate" )
	
	public void saveGpsRate( HttpServletRequest request ,
	        HttpServletResponse response , TGpsrate tGpsrate )
	{
		if ( StringUtils.isNotBlank( tGpsrate.getId() + "" ) )
		{
			try
			{
				iBasicManageService.updateGpsRate( tGpsrate );
				AjaxUtil.rendJson( response , true , "修改成功！" );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				AjaxUtil.rendJson( response , false ,
				        "修改失败！原因:" + e.getMessage() );
			}
		}
	}
	
	/**
	 * 
	 * @Description: TODO(删除GPS定位频率)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-22 上午10:29:47
	 */
	@RequestMapping( "/delGpsRate" )
	public void delGpsRate( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		String selectID = request.getParameter( "selectId" );// 表主键ID
		try
		{
			iBasicManageService
			        .deleteGPSrateById( Integer.parseInt( selectID ) );
			AjaxUtil.rendJson( response , true , "删除成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "删除失败！" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(同步司机信息表)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-22 下午1:44:06
	 */
	@RequestMapping( "/synchronousDriver" )
	public void synchronousDriver( HttpServletRequest request ,
	        HttpServletResponse response )
	{	
		try
		{
			TUser tUser = ( TUser ) request.getSession().getAttribute( "user" );
			String info = iBasicManageService
			        .updateDriverInfo( tUser , request );// 同步司机个人信息（当前分供方）
			AjaxUtil.rendJson( response , true , "成功！" + info );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！" );
		}
	}

	/**
	 * 
	 * @Description: TODO(保存汽车商标)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @throws UnsupportedEncodingException
	 * @create_date 2015-3-26 下午1:40:50
	 */
	@RequestMapping( "/saveCarStylePic" )
	public void saveCarStylePic( HttpServletRequest request ,
	        HttpServletResponse response , TCarStylePic tCarStylePic )
	        throws UnsupportedEncodingException
	{
		String path = null;// 页面显示时图片路径
		String imgPath = SystemConstants.CAR_STYLE_IMG_PATH;// 服务器保存的路径
		try
		{
			
			MultipartHttpServletRequest multipartRequest = ( MultipartHttpServletRequest ) request;
			String source = request.getSession().getServletContext().getRealPath( "/" );
			Map< String , MultipartFile > fileMap = multipartRequest
			        .getFileMap();
			for ( Map.Entry< String , MultipartFile > entity : fileMap
			        .entrySet() )
			{
				// 获得第1张图片（根据前台的name名称得到上传的文件）
				MultipartFile imgFile1 = entity.getValue();
				String sourceImg = request.getParameter( "path" );
				if ( imgFile1.getContentType().split( "/" )[0].equals( "image" ) )
				{
					if ( null != sourceImg && ! "".equals( sourceImg ) )
					{
						File f = new File( source + sourceImg );
						if ( f.isFile() )
						{
							f.delete();
							System.out.println( " 删除成功" );
						}
						else
							System.out.println( " 删除失败!" );
					}
					
					if ( imgFile1 != null && imgFile1.getSize() > 0 )
					{
						path = this.storeFile( request.getSession() , imgFile1 ,
						        imgPath );
					}

				}
				
			}
			// 获得第1张图片（根据前台的name名称得到上传的文件）
			// MultipartFile imgFile1 = multipartRequest.getFile( "imageFile" );
			// 判断是否是重复上传图片（如果重复将删除旧图片）
			
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "文件上传失败：" + e.getMessage() );
			
		}
		
		try
		{
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			tCarStylePic.setVcAdduser( user.getVcAccount() );
			tCarStylePic.setDtAdd( new Date() );
			if ( path != null )
			{
				tCarStylePic.setVcPath( path );
			}
			
			if ( StringUtils.isNotEmpty( tCarStylePic.getId() + "" ) )
			{
				iBasicManageService.saveOrUpdateCarStylePic( tCarStylePic );
			}
			else
			{
				
				iBasicManageService.saveCarStylePic( tCarStylePic );
			}
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败！失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param session
	 * @param imgFile1
	 * @return String 返回值描述 重新命名的图片名
	 * @author liuwu
	 * @throws Exception
	 * @create_date 2015-3-30 下午4:12:40
	 */
	private String storeFile( HttpSession session , MultipartFile file , String filepath )
	        throws Exception
	{
		String fileType = file.getContentType().split( "/" )[1];
		String path = session.getServletContext().getRealPath( "/" ) + filepath;
		// + SystemConstants.USER_STATUS_IMG_PATH;
		String separator = File.separator;
		String uuid = UUID.randomUUID().toString();
		FileOutputStream fos = null;
		String fileName = null;
		try
		{
			InputStream fis = file.getInputStream();
			// 转换文件为png格式，并保存在同名目录下
			File files = new File( path );
			// 判断文件夹是否存在,如果不存在则创建文件夹
			if ( ! files.exists() )
			{
				files.mkdir();
			}
			if ( file.getContentType().split( "/" )[0].equals( "image" ) )
			{
				if ( path.endsWith( separator ) )
					fileName = path + uuid + ".png";
				else
					fileName = path + separator + uuid + ".png";
				fos = new FileOutputStream( fileName );
				ImageUtil.convertFormat( fis , fos , fileType , "png" , 0 , 0 );
				fos.flush();
				fos.close();
			}
		}
		catch ( Exception ex )
		{
			System.out.println( "文件取出失败，错误信息: " + ex.getMessage() );
			if ( fos != null )
				fos.close();
			throw ex;
		}
		return uuid + ".png";
	}
}
