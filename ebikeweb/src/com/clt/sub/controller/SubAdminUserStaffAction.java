package com.clt.sub.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TRoleCompany;
import com.clt.sub.model.TSubstaff;
import com.clt.sub.service.IRoleCompanyService;
import com.clt.sub.service.ISubAdminUserStaffService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TResource;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IResourceService;
import com.clt.systemmanger.service.IRoleResourceService;
import com.clt.systemmanger.service.IRoleService;
import com.clt.systemmanger.service.IUserRoleService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.sub.controller
 * @Description: TODO(分供方用户管理)
 * @author liuwu
 * @date 2015-1-9 下午5:19:27
 * @version V1.0
 */
@Controller
@RequestMapping( "/subAdminStaffAction" )
@ApiIgnore
public class SubAdminUserStaffAction
{
	@Autowired
	ISubsuppliersService subService;
	
	@Autowired
	ISubAdminUserStaffService iSubAdminUserStaffService;
	
	@Autowired
	IRoleService roleService;
	
	@Autowired
	IUserService userService;
	@Autowired
	IUserRoleService urService;
	
	@Autowired
	IRoleCompanyService roleCompanyService;
	
	@Autowired
	IResourceService iResourceService;
	
	@Autowired
	IUserRoleService userRoleService;
	@Autowired
	IResourceService resourseService;
	@Autowired
	IRoleResourceService roleResourseService;
	
	@Autowired
	IRoleResourceService rResourceService;// 角色资源关联服务类
	
	/**
	 * 
	 * @Description: TODO(所有用户)
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-12 上午10:47:14
	 */
	@RequestMapping( "/subUserEdit" )
	public String subUserEdit()
	{
		return "sub/subuser/subuserlist";
	}
	
	/**
	 * 
	 * @Description: TODO(用户角色编辑)
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-12 上午10:47:14
	 */
	@RequestMapping( "/subUserRole" )
	public String subUserRole()
	{
		return "sub/subuser/userRole";
	}
	
	/**
	 * 
	 * @Description: TODO(员工个人资料列表)
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 下午3:36:29
	 */
	@RequestMapping( "/subUserData " )
	public String subUserData()
	{
		return "sub/subuser/subUserData";
	}
	


	/**
	 * 
	 * @Description: TODO(新增,编辑用户)
	 * @param request
	 * @param response
	 * @author liuwu
	 * @create_date 2015-1-12 上午10:52:10
	 */
	@RequestMapping( "/openAddUser" )
	public String subuserList( HttpServletRequest request , HttpServletResponse response )
	{
		String userId = request.getParameter( "userId" );
		
		if ( StringUtils.isNotBlank( userId ) )// 修改密码时
		{
			TUser user = userService.getByid( userId );
			user.setVcPassword( null );
			request.setAttribute( "tuser" , user );
		}
		return "sub/subuser/userForm";
		
	}
	
	/**
	 * 
	 * @Description: TODO(用户角色编辑)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-12 上午10:53:17
	 */
	@RequestMapping( "/subuserrolelist" )
	@ResponseBody
	public Map< String , Object > subuserRoleList( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		Page page = ServiceUtil.getcurrPage( request );
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archiveTypeId = user.getIArchiveType();// 档案类型ID
		String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
		String sql = "select r.id, r.N_ENABLE, r.vc_role_name,r.vc_desc,r.vc_role from T_ROLE r,T_ROLE_COMPANY rc where r.id=rc.n_role_id and rc.vc_companyno='"
		        + subbo
		        + "' "
		        + "and rc.i_archive_type="
		        + SystemConstants.SYS_TARCHIVE_SUB
		        + " and rc.N_ENABLE = "
		        + SystemConstants.SYS_ENABLE;
		String roleName = request.getParameter( "roleName" );// 角色名
		String vcRole = request.getParameter( "vcRole" );// 角色代码
		if ( StringUtils.isNotBlank( roleName ) )
		{
			sql += "and r.VC_ROLE_NAME = '" + roleName + "'";
		}
		if ( StringUtils.isNotBlank( vcRole ) )
		{
			sql += "and r.VC_ROLE = '" + vcRole + "'";
		}
		
		System.out.println( "sql= " + sql );
		Map< String , Object > orderMap = iSubAdminUserStaffService.getSpringSQL( sql ,
		        page );
		return orderMap;
		
	}
	
	/**
	 * @Description: 获得某个具体角色的信息 编辑
	 * @param roleId
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午7:02:29
	 */
	@RequestMapping( "/getByid" )
	public String getByid( HttpServletRequest request )
	{
		String roleId = request.getParameter( "roleId" );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
		request.setAttribute( "subbo" , subbo );
		if ( StringUtils.isNotBlank( roleId ) )
		{
			TRole role = roleService.get( Integer.parseInt( roleId ) );
			request.setAttribute( "role" , role );
		}
		
		return "sub/subuser/roleForm";
	}
	
	/**
	 * @Description: VC_ROLE 必填 角色保存或者更新
	 * @param role
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午7:17:08
	 */
	@RequestMapping( "/save" )
	public void save( TRole role , HttpServletResponse response ,
	        HttpServletRequest request , String subbo )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			if ( role.getId() != null )// 编辑
			{
				role.setIArchiveType( SystemConstants.SYS_TARCHIVE_SUB );
				role.setiLeader( SystemConstants.NO_LEADER );
				roleService.saveOrUpdate( role );
			}
			else
			// 新增
			{
				role.setiLeader( SystemConstants.NO_LEADER );
				role.setIArchiveType( SystemConstants.SYS_TARCHIVE_SUB );
				roleService.save( role );
				TRoleCompany roleCompany = new TRoleCompany();
				roleCompany.setIArchiveType( SystemConstants.SYS_TARCHIVE_SUB );
				roleCompany.setRoleId( role.getId() );
				roleCompany.setVcCompanyno( subbo );
				roleCompany.setVcCompanytype( SystemConstants.SYS_SUB );
				roleCompany.setiUserId( user.getId() );
				roleCompany.setnEnable( SystemConstants.SYS_ENABLE );
				roleCompanyService.saveRoleCompany( roleCompany );
			}
			AjaxUtil.rendJson( response , true , "角色保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "角色保存失败！失败原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(删除角色)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-12 下午4:25:52
	 */
	@RequestMapping( "/del" )
	public void deleteUserRole( HttpServletRequest request , HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		try
		{
			roleCompanyService.updateSetDiable( ids );
			AjaxUtil.rendJson( response , true , "删除成功" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "删除失败" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(禁用用户帐号)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 下午4:40:47
	 */
	@RequestMapping( "/delUser" )
	public void delUser( HttpServletRequest request , HttpServletResponse response )
	{
		String[] ids = request.getParameterValues( "array[]" );
		try
		{
			iSubAdminUserStaffService.updateUserAcountDisable( ids );
			AjaxUtil.rendJson( response , true , "禁用成功" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "禁用失败" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(点击关联资源时展示的当前用户所拥有的资源)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-13 下午2:03:07
	 */
	@RequestMapping( "/getRoleResource" )
	public String getRoleResource( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
		String roleId = request.getParameter( "roleId" );// 角色ID
		if ( StringUtils.isNotBlank( roleId ) )
		{
			List< TRole > rolelist = userRoleService.getAllRoleByUserID( user.getId()
			        .toString() );
			List< TResource > resolist = new ArrayList< TResource >();
			
			for ( TRole role : rolelist )
			{
				List< TResource > resources = roleResourseService.getByRoleid( role
				        .getId().toString() );
				if ( CollectionUtils.isNotEmpty( resources ) )
					resolist.addAll( resources );
			}
			// 转换为 Set 去重
			Set< TResource > set = new HashSet< TResource >();
			set.addAll( resolist );
			
			// 再加入List
			List< TResource > reslist = new ArrayList< TResource >();// 当前用户所拥有的资源
			reslist.addAll( set );
			List< TResource > roleList = roleResourseService.getByRoleid( roleId );// 当前用户（分供方）所创建的角色所拥有的资源
			String json = "";
			JSONArray arr = new JSONArray();
			for ( TResource resource : reslist )
			{
				JSONObject obj = new JSONObject();
				obj.element( "id" , resource.getId() );
				obj.element( "pId" , resource.getIParent() );
				obj.element( "name" , resource.getVcResourceName() );
				obj.element( "t" , resource.getVcDesc() );
				if ( CollectionUtils.isNotEmpty( roleList ) )
				{
					if ( roleList.contains( resource ) )
					{
						obj.element( "checked" , true );
					}
					else
					{
						obj.element( "checked" , false );
					}
				}
				else
				{
					obj.element( "checked" , false );
				}
				arr.add( obj );
			}
			json = arr.toString();
			System.out.println( "json= " + json );
			if ( json.length() < 3 )
			{
				json = "[{\"id\":0,\"pId\":0,\"name\":\"该档案未关联资源\",\"t\":\"该档案未关联资源\",\"nocheck\":true}]";
			}
			request.setAttribute( "resourceList" , json );
			return "sub/subuser/roleResourceTree";// 跳转到 授权页面
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * @Description: TODO(保存角色资源)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-13 下午2:32:54
	 */
	@RequestMapping( "/saveRoleResource" )
	public void saveRoleResource( @RequestParam( "roleId" ) String roleId ,
	        @RequestParam( "resourceIds" ) String resourceIds ,
	        HttpServletResponse response )
	{
		try
		{
			List< String > resourceList = null;
			if ( StringUtils.isNotBlank( resourceIds ) )
			{
				String[] arr = resourceIds.split( "," );
				resourceList = Arrays.asList( arr );
			}
			
			rResourceService.updateRoleResourcesForStaff( roleId , resourceList );
			AjaxUtil.rendJson( response , true , "角色资源关联成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , true , "角色资源关联失败！失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(所有用户的帐号信息)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 下午4:56:12
	 */
	@RequestMapping( "/pageList" )
	@ResponseBody
	public Map< String , Object > pageList( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		Page page = ServiceUtil.getcurrPage( request );
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
		String sql = "select u.* from T_user u,t_Substaff staf where u.id = staf.i_userid and staf.N_ENABLE = "
		        + SystemConstants.SYS_ENABLE + " and staf.vc_subno = '" + subbo + "'";
		System.out.println( "sql = " + sql );
		Map< String , Object > orderMap = iSubAdminUserStaffService.getSpringSQL( sql ,
		        page );
		return orderMap;
	}
	
	/**
	 * 
	 * @Description: TODO(编辑，新增用户时点击保存触发)
	 * @param request
	 * @param response
	 * @param tUser
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 上午10:12:26
	 */
	@RequestMapping( "/saveUser" )
	public void saveUser( HttpServletRequest request , HttpServletResponse response ,
	        TUser tUser )
	{
		
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
		if ( tUser.getVcPassword() == null || tUser.getVcPassword() == "" )
		{
			tUser.setVcPassword( "123456" );
		}
		tUser.setDtAddtime( new Date() );
		if ( tUser.getId() == null )// 新增
		{
			// 先验证帐号是否存在
			boolean exist = iSubAdminUserStaffService.checkUserExist( tUser );
			if ( exist )
			{
				AjaxUtil.rendJson( response , false , "该帐号已经被注册,请修改" );
			}
			else
			{
				
				org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
				String md5Passord = t.encodePassword( tUser.getVcPassword() ,
				        tUser.getVcAccount() );
				tUser.setIArchiveType( SystemConstants.SYS_TARCHIVE_SUB );
				tUser.setVcPassword( md5Passord );
				tUser.setiArchive( user.getiArchive() );
				try
				{
					userService.saveUser( tUser );
					TSubstaff substaff = new TSubstaff();
					substaff.setiUserId( tUser.getId() );
					substaff.setVcSubno( subbo );
					substaff.setnEnable( tUser.getNEnable() );
					substaff.setiLeadId( user.getId() );
					iSubAdminUserStaffService.saveSubStaffUserInfo( substaff );
					AjaxUtil.rendJson( response , true , "新增用户成功" );
				}
				catch ( Exception e )
				{
					AjaxUtil.rendJson( response , false , "新增用户失败 " + e.getMessage() );
				}
			}
			
		}
		else
		// 编辑
		{
			org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
			String md5Passord = t.encodePassword( tUser.getVcPassword() ,
			        tUser.getVcAccount() );
			tUser.setVcPassword( md5Passord );
			try
			{
				userService.updateUser( tUser );
				AjaxUtil.rendJson( response , true , "编辑用户成功" );
			}
			catch ( Exception e )
			{
				AjaxUtil.rendJson( response , false , "编辑用户 失败 " + e.getMessage() );
			}
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(点击授权时展开的树)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 上午10:13:32
	 */
	@RequestMapping( "/giveRol" )
	public String giveRol( HttpServletRequest request , HttpServletResponse response )
	{
		String userId = request.getParameter( "userId" );
		String roleTree = iSubAdminUserStaffService.getUserRoleByUserId( userId );
		
		System.out.println( roleTree );
		request.setAttribute( "roleTree" , roleTree );
		return "sub/subuser/userRoleTree";// 跳转到用户授予角色页面
	}
	
	/**
	 * 
	 * @Description: TODO(保存选择的用户角色)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 下午2:35:35
	 */
	@RequestMapping( "/saveUserRoleTree" )
	public void saveUserRoleTree( @RequestParam( "userId" ) String userId ,
	        @RequestParam( "roleids" ) String roleids , HttpServletResponse response )
	{
		try
		{
			if ( StringUtils.isNotBlank( roleids ) )
			{
				String[] ids = roleids.split( "," );
				List< String > idList = Arrays.asList( ids );
				urService.updateUserRoles( userId , idList );
			}
			else
			{
				urService.updateUserRoles( userId , null );
			}
			AjaxUtil.rendJson( response , true , "授权成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , true , "授权失败，失败原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(所有用户的个人信息)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-16 下午4:58:23
	 */
	@RequestMapping( "/subUserDataList" )
	@ResponseBody
	public Map< String , Object > subUserDataList( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		Page page = ServiceUtil.getcurrPage( request );
		TUser user = ( TUser ) session.getAttribute( "user" );
		String sql = "select t.* , u.vc_username from T_SUBSTAFF t,T_User u where t.i_userid=u.id and t.n_enable="
		        + SystemConstants.SYS_ENABLE + " and t.i_leadid = " + user.getId();
		
		System.out.println( "sql= " + sql );
		Map< String , Object > orderMap = iSubAdminUserStaffService.getSpringSQL( sql ,
		        page );
		return orderMap;
		
	}
}
