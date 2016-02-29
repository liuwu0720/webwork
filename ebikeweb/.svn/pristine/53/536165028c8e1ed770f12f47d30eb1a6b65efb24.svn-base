package com.clt.systemmanger.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TRoleCompany;
import com.clt.sub.service.IRoleCompanyService;
import com.clt.systemmanger.model.TArchiveType;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserRole;
import com.clt.systemmanger.service.IArchiveTypeService;
import com.clt.systemmanger.service.IRoleResourceService;
import com.clt.systemmanger.service.IRoleService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: 角色控制器
 * @author hjx
 * @date 2014年7月17日 下午6:42:43
 * @version V1.0
 */
@Controller
@RequestMapping( "/role" )
@ApiIgnore
public class RoleAction
{
	@Autowired
	IRoleService roleService;
	@Autowired
	IArchiveTypeService archiveTypeService;// 档案类型服务类
	
	@Autowired
	IRoleResourceService rResourceService;// 角色资源关联服务类
	
	@Autowired
	IRoleCompanyService iRoleCompanyService;
	
	/**
	 * @Description: 打开角色列表页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月4日 下午4:27:55
	 */
	@RequestMapping( "/openRoleList" )
	public String openRoleList()
	{
		return "back/system/roleList";
	}
	
	/**
	 * @Description: 获得所有有效的角色，跳转到角色树页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午6:45:57
	 */
	@RequestMapping( "/getAllByEnable" )
	@ResponseBody
	public Map< String , Object > getAllByEnable( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TRole.class );
		// helper.addEqual( "iLeader" , SystemConstants.IS_LEADER );//
		// 只查找为管理员的角色
		String vcRoleName = request.getParameter( "vcRoleName" );
		String vcRole = request.getParameter( "vcRole" );
		if ( StringUtils.isNotBlank( vcRoleName ) )
		{
			helper.addLike( "vcRoleName" , vcRoleName );
		}
		
		if ( StringUtils.isNotBlank( vcRole ) )
		{
			helper.addLike( "vcRole" , vcRole.toUpperCase() );
		}
		
		helper.setQueryPage( page );
		
		// 获得所有有效的角色
		return roleService.findByHelper( helper );
		
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
		if ( StringUtils.isNotBlank( roleId ) )
		{
			TRole role = roleService.get( Integer.parseInt( roleId ) );
			request.setAttribute( "role" , role );
		}
		
		return "back/system/roleForm";
	}
	
	/**
	 * @Description: VC_ROLE 必填 角色保存或者更新
	 * @param role
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午7:17:08
	 */
	@RequestMapping( "/save" )
	public void save( TRole role , HttpServletResponse response )
	{
		try
		{
			if ( StringUtils.isNotEmpty( role.getId() + "" ) )
			{
				roleService.saveOrUpdate( role );
			}
			else
			{
				role.setiLeader( SystemConstants.IS_LEADER );
				roleService.save( role );
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
	 * @Description: 跳转到角色授权页面，并且显示所有的有效资源树 和该角色所拥有的资源（ishave=1）
	 * @param userId
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月18日 上午9:53:16
	 */
	@RequestMapping( "/getRoleResource" )
	public String getRoleResource( HttpServletRequest request )
	{
		String roleId = request.getParameter( "roleId" );
		List< TArchiveType > atypes = archiveTypeService.loadAllByEnable();
		request.setAttribute( "typeList" , atypes );
		if ( CollectionUtils.isNotEmpty( atypes ) )
		{ // 默认取第一个档案
			String typeId = String.valueOf( atypes.get( 0 ).getId() );
			String json = rResourceService.getByRoleHave( typeId , roleId );
			if ( json.length() < 3 )
			{
				json = "[{\"id\":0,\"pId\":0,\"name\":\"该档案未关联资源\",\"t\":\"该档案未关联资源\",\"nocheck\":true}]";
			}
			request.setAttribute( "resourceList" , json );
		}
		return "back/system/roleResourceTree";// 跳转到 授权页面
	}
	
	/**
	 * @Description:根据角色id和档案id获得对应的资源，并标注出已经具有的资源 ishave=1
	 * @return String
	 * @author hjx
	 * @create_date 2014年7月18日 上午10:17:19
	 */
	@RequestMapping( "/getByRoleidAndTypeId" )
	public void getByRoleidAndTypeId( @RequestParam( "roleId" ) String roleId ,
	        @RequestParam( "typeId" ) String typeId , HttpServletResponse response )
	{
		try
		{
			String resources = rResourceService.getByRoleHave( typeId , roleId );
			if ( resources.length() < 3 )
			{
				resources = "[{\"id\":0,\"pId\":0,\"name\":\"该档案未关联资源\",\"t\":\"该档案未关联资源\",\"nocheck\":true}]";
			}
			AjaxUtil.rendJson( response , true , resources );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "该档案类型下没有相关资源" );
		}
		
	}
	
	/**
	 * @Description: 保存角色和资源的关联信息
	 * @param roleId
	 *            角色id
	 * @param resourceIds
	 *            资源ids
	 * @param typeId
	 *            资源所属档案类型id
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月18日 上午11:06:45
	 */
	@RequestMapping( "/saveRoleResource" )
	public void saveRoleResource( @RequestParam( "roleId" ) String roleId ,
	        @RequestParam( "resourceIds" ) String resourceIds ,
	        @RequestParam( "typeId" ) String typeId , HttpServletResponse response )
	{
		try
		{
			List< String > resourceList = null;
			if ( StringUtils.isNotBlank( resourceIds ) )
			{
				String[] arr = resourceIds.split( "," );
				resourceList = Arrays.asList( arr );
			}
			rResourceService.updateRoleResources( typeId , roleId , resourceList );
			AjaxUtil.rendJson( response , true , "角色资源关联成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false ,
			        "角色资源关联失败！失败原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 删除角色，并把相关的 角色和资源关联信息删除
	 * @param roleId
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 下午1:29:38
	 */
	@RequestMapping( "/delRole" )
	public void delRole( @RequestParam( "roleIds" ) String roleIds ,
	        HttpServletResponse response )
	{
		try
		{
			String[] arr = roleIds.split( "," );
			for ( String roleId : arr )
			{
				roleService.deleteByKey( Integer.parseInt( roleId ) );
			}
			AjaxUtil.rendJson( response , true , "删除角色成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , true , "删除角色失败！失败原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 获取该用户所属的档案类型的角色信息并且是可展示的
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月18日 上午9:53:16
	 */
	@RequestMapping( "/getRoleByArchiveTypeID" )
	public String getRoleByArchiveTypeID( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int archid = user.getIArchiveType();
		
		String hql = " from TRole role where role.NSelect=0 and role.IArchiveType= "
		        + archid;
		List< TRole > rolelist = roleService.find( hql );
		System.out.println( "aaa" + JSONArray.fromObject( rolelist ).toString() );
		return JSONArray.fromObject( rolelist ).toString();// 跳转到 授权页面
	}
	
	/**
	 * 
	 * @Description: TODO(查询当前编辑的角色是否存在下属)
	 * @param role
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-14 下午4:02:51
	 */
	@RequestMapping( "/searchifstaff" )
	public void searchifstaff( TRole role , HttpServletResponse response )
	{
		if ( role.getNEnable().equals( SystemConstants.SYS_DISABLE ) )
		{
			List< TUserRole > tUserRoles = roleService.getStaffRole( role );
			// 所拥有的下属角色
			List< TRoleCompany > tRoleCompanies = new ArrayList< TRoleCompany >();
			for ( TUserRole tUserRole : tUserRoles )
			{
				List< TRoleCompany > tList = iRoleCompanyService
				        .getObjectByUserId( tUserRole );
				tRoleCompanies.addAll( tList );
			}
			List< TRole > tRoles = new ArrayList< TRole >();
			for ( TRoleCompany tRoleCompany : tRoleCompanies )
			{
				TRole tRole = roleService.get( tRoleCompany.getRoleId() );
				tRoles.add( tRole );
			}
			String string = "";
			for ( TRole tRole : tRoles )
			{
				string += tRole.getVcRoleName() + " ";
			}
			System.out.println( "string = " + string );
			AjaxUtil.rendJson( response , true , "该角色是管理员，有创建员工角色:" + string
			        + "，如果设为无效则该管理员的下属员工角色都会无效，请确认" );
		}
		else
		{
			AjaxUtil.rendJson( response , false , "" );
		}
	}
}
