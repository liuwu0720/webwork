package com.clt.systemmanger.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IMessageService;
import com.clt.systemmanger.service.IResourceService;
import com.clt.systemmanger.service.IRoleResourceService;
import com.clt.systemmanger.service.IUserRoleService;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: 后台首页action，处理各种类型首页切换
 * @author hjx
 * @date 2014年7月28日 上午11:05:14
 * @version V1.0
 */
@Controller
@RequestMapping( "/back" )
@ApiIgnore
public class BackHomeAction
{
	@Autowired
	private IUserRoleService userRoleService;
	@Autowired
	private IResourceService resourseService;
	@Autowired
	private IRoleResourceService roleResourseService;
	@Autowired
	private IMessageService msgService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	ICityService cityService;
	
	/**
	 * @Description: 后台头文件
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 上午11:09:59
	 */
	@RequestMapping( "/getHeader" )
	public String getHeader( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		request.setAttribute( "user" , user );
		// 获取消息总数
		int msgCount = msgService.getMsgCountByCurrentUser();
		request.setAttribute( "msgCount" , msgCount );
		return "back/header";
	}
	
	/**
	 * @Description: 获得左边树页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 上午11:10:20
	 */
	@SuppressWarnings( "unchecked" )
	@RequestMapping( "/getSidebar" )
	public String getSidebar( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		List< TRole > rolelist = userRoleService.getAllRoleByUserID( user.getId()
		        .toString() );
		List< TResource > resolist = new ArrayList< TResource >();
		
		for ( TRole role : rolelist )
		{
			List< TResource > resources = roleResourseService.getByRoleid( role.getId()
			        .toString() );
			if ( CollectionUtils.isNotEmpty( resources ) )
				resolist.addAll( resources );
		}
		// 转换为 Set 去重
		Set< TResource > set = new HashSet< TResource >();
		set.addAll( resolist );
		
		// 再加入List
		List< TResource > reslist = new ArrayList< TResource >();
		reslist.addAll( set );
		
		JSONArray jsarr = new JSONArray();
		Collections.sort( reslist );// 排序
		if ( CollectionUtils.isNotEmpty( reslist ) )
		{
			for ( TResource p : reslist )
			{
				if ( p.getNType() != SystemConstants.SYS_RESOURCE_TYPE_OPERATION )
				{
					JSONObject obj = new JSONObject();
					obj.element( "id" , p.getId() );
					obj.element( "pId" , p.getIParent() );
					obj.element( "name" , p.getVcResourceName() );
					obj.element( "url" , p.getVcUrl() );
					obj.element( "target" , "main" );
					if ( StringUtils.isNotBlank( p.getVcIcon() ) )
					{
						obj.element( "iconSkin" , p.getVcIcon() );
					}
					if ( p.getNType() == 0 )
					{
						obj.element( "open" , true );
					}
					jsarr.add( obj );
				}
			}
		}
		else
		{
			JSONObject obj = new JSONObject();
			obj.element( "id" , 0 );
			obj.element( "pId" , 0 );
			obj.element( "name" , "您还没有授权，请联系管理员" );
			obj.element( "target" , "main" );
			jsarr.add( obj );
		}
		System.out.println( "meaustr" + jsarr.toString() );
		request.setAttribute( "meaustr" , jsarr.toString() );
		return "back/sidebar";
	}
	
	/**
	 * 
	 * @Description: TODO(手机端获取资源接口)
	 * @param request
	 * @return Object 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @create_date 2015-3-10 下午2:54:05
	 */
	@RequestMapping( "/getResoureByPhone" )
	@ResponseBody
	public Object getResoureByPhone( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		String userId = request.getParameter( "userId" );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		List< TRole > rolelist = userRoleService.getAllRoleByUserID( user.getId()
		        .toString() );
		List< TResource > resolist = new ArrayList< TResource >();
		
		for ( TRole role : rolelist )
		{
			List< TResource > resources = roleResourseService.getByRoleid( role.getId()
			        .toString() );
			if ( CollectionUtils.isNotEmpty( resources ) )
				resolist.addAll( resources );
		}
		// 转换为 Set 去重
		Set< TResource > set = new HashSet< TResource >();
		set.addAll( resolist );
		
		// 再加入List
		List< TResource > reslist = new ArrayList< TResource >();
		reslist.addAll( set );
		
		JSONArray jsarr = new JSONArray();
		Collections.sort( reslist );// 排序
		if ( CollectionUtils.isNotEmpty( reslist ) )
		{
			for ( TResource p : reslist )
			{
				// 过虑掉操作及菜单的选项
				if ( p.getNType() != SystemConstants.SYS_RESOURCE_TYPE_OPERATION
				        && p.getNType() != SystemConstants.SYS_RESOURCE_TYPE_MENU )
				{
					JSONObject obj = new JSONObject();
					obj.element( "id" , p.getId() );
					obj.element( "name" , p.getVcResourceName() );
					
					jsarr.add( obj );
				}
			}
		}
		else
		{
			JSONObject obj = new JSONObject();
			obj.element( "id" , 0 );
			obj.element( "name" , "您还没有授权，请联系管理员" );
			jsarr.add( obj );
		}
		return jsarr;
		
	}
	
	/**
	 * @Description: 获得主页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 上午11:10:47
	 */
	@RequestMapping( "/getMain" )
	public String getMain( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		if ( user.getIArchiveType() == SystemConstants.SYS_TARCHIVE_SUB )
		{
			TSubsuppliers sub = subService.get( user.getiArchive() );
			
			// 查看该分供方是否已完善信息
			if ( sub.getVcAllName() == null || sub.getVcAllName() == "" )
			{
				
				return "redirect:/subsuppliersAction/SubsuppliersSaveBefore";
			}
		}
		
		return "back/main";
	}
	
	/**
	 * @Description: 跳转到后台主页
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 上午11:12:50
	 */
	@RequestMapping( "/getBackHome" )
	public String getBackHome( HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null != user )
		{
			return "back/backhome";// 有会话跳转到后台主页
		}
		else
		{
			return "index";// 没有会话，跳转到前台首页
		}
	}
	
	/**
	 * @Description: 展示隐藏按钮
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月12日 上午9:34:11
	 */
	@RequestMapping( "/getControl" )
	public String getControl()
	{
		return "back/control";
	}
	
	/**
	 * 
	 * @Description: 文件 下载
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 *             void 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-19 下午3:04:39
	 */
	@RequestMapping( "downFilebyPath" )
	public void downFilebyPath( HttpServletRequest request , HttpServletResponse response )
	        throws ServletException , IOException
	{
		
		String filepath = request.getParameter( "filepath" ); // 取得文件名
		
		filepath = new String( filepath.getBytes( "ISO-8859-1" ) , "UTF-8" );
		System.out.println( filepath );
		try
		{
			// fname = new String(fname.getBytes("iso8859-1"),"utf-8");
			// //为其重新编码，目的是为了让其能下载中文文件
			OutputStream os = response.getOutputStream(); // 输出流（从response中获取）
			FileInputStream fis = new FileInputStream( filepath ); // 以服务器端文件为基础构建输入流（读）
			
			String s = new String( filepath.getBytes( "GBK" ) , "ISO-8859-1" );
			response.setContentType( "unknown" ); // 设置文件响应类型
			response.setHeader( "Content-Disposition" , "attachment; filename=\"" + s
			        + "\"" ); // 主要是向用户显示文件名
			response.setCharacterEncoding( "UTF-8" );
			request.setCharacterEncoding( "UTF-8" );
			
			BufferedInputStream bufIn = new BufferedInputStream( fis ); // 对流进行封装
			BufferedOutputStream bufOut = new BufferedOutputStream( os );
			byte[] b = new byte[1024];
			int i = 0;
			while ( ( i = bufIn.read( b ) ) > 0 )
			{ // 依次将各种信息写入到response中。
				bufOut.write( b , 0 , i );
			}
			bufOut.flush();
			bufOut.close();
			bufIn.close();
			fis.close();
			os.flush();
			os.close();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 打开测试页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2015年4月7日 下午3:00:17
	 */
	@RequestMapping( "openTest" )
	public String openTest()
	{
		return "apiIndex";
	}
}
