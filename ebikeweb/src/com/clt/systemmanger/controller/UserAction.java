package com.clt.systemmanger.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.common.UserSession;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.model.TUserCode;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.IUserCodeService;
import com.clt.systemmanger.model.TArchiveType;
import com.clt.systemmanger.model.TStores;
import com.clt.systemmanger.model.TTokenUser;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserGps;
import com.clt.systemmanger.service.IArchiveTypeService;
import com.clt.systemmanger.service.IStaticService;
import com.clt.systemmanger.service.IStoresService;
import com.clt.systemmanger.service.ITokenUserService;
import com.clt.systemmanger.service.IUserGpsService;
import com.clt.systemmanger.service.IUserRoleService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.clt.util.TokenUtil;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: 用户管理Action
 * @author hjx
 * @date 2014年7月17日 下午3:50:50
 * @version V1.0
 */
@Controller
@RequestMapping( "/userAction" )
@Api( value = "user-api" , description = "有关于用户的操作" , position = 5 )
public class UserAction
{
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserRoleService urService;
	@Autowired
	private IArchiveTypeService archiveTypeService;
	@Autowired
	private ITokenUserService tokenUserService;
	@Autowired
	private IUserGpsService gpsService;
	@Autowired
	private ISubsuppliersService iSubsuppliersService;
	@Autowired
	private IStoresService iStoresService;
	@Autowired
	private IUserCodeService userCodeService;
	@Autowired
	private IStaticService staticService;
	
	/**
	 * @Description: 登陆处理方法，把当前用户保存到session中，并跳转到主页
	 * @param session
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月17日 下午4:47:29
	 */
	@RequestMapping( "/login" )
	@ApiIgnore
	public String login( HttpSession session )
	{
		User userDt = ( User ) SecurityContextHolder.getContext().getAuthentication()
		        .getPrincipal();
		TUser user = userService.getByAccount( userDt.getUsername() );
		session.setAttribute( "user" , user );
		return "index";// 跳转到首页
	}
	
	/**
	 * @Description: 手机端登陆
	 * @param vcAccount
	 * @param vcPassword
	 * @param utypeID
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2015年4月13日 上午9:50:35
	 */
	@ApiOperation( value = "用户登录接口" , notes = "用户登录接口" , position = 5 )
	@RequestMapping( value = "/loginByApp" , method = RequestMethod.POST )
	public void loginByApp(
	        @ApiParam( value = "登陆用户名" , required = true ) @RequestParam( "vcAccount" ) String vcAccount ,
	        @ApiParam( value = "登陆密码" , required = true ) @RequestParam( "vcPassword" ) String vcPassword ,
	        @ApiParam( value = "用户类型，不可以为空，服务端没用，手机端需要根据这个参数调整到对应activity" , required = true ) @RequestParam( value = "utypeID" ) Integer utypeID ,
	        HttpServletResponse response , HttpServletRequest request )
	{
		TUser user = userService.getByAccount( vcAccount );
		if ( null == user )
		{
			// 可用用户名或者手机号码登陆
			user = userService.getByPhone( vcAccount );
		}
		try
		{
			if ( null != user )
			{
				// String temp = AndroidAes.decrypt( "unlcn" , vcPassword );
				org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
				String tt = t.encodePassword( vcPassword , user.getVcAccount() );
				if ( tt.equals( user.getVcPassword() ) )
				{
					// 验证角色是否存在
					int type = user.getIArchiveType();
					String msg = null;
					switch ( utypeID )
						{
						// 分供方
							case SystemConstants.SYS_TARCHIVE_SUB :
								if ( type != utypeID )
								{
									AjaxUtil.rendJson( response , false , "你的角色不是承运方" );
									return;
								}
								break;
							// 司机
							case SystemConstants.SYS_TARCHIVE_DRIVER :
								if ( type != utypeID )
								{
									AjaxUtil.rendJson( response , false , "你的角色不是司机" );
									return;
								}
								break;
							// 金融公司
							case SystemConstants.SYS_TARCHIVE_FINANCE :
								if ( type != utypeID )
								{
									AjaxUtil.rendJson( response , false , "你的角色不是金融公司" );
									return;
								}
								break;
							// 4s店
							case SystemConstants.SYS_TARCHIVE_STORES :
								if ( type != utypeID )
								{
									AjaxUtil.rendJson( response , false , "你的角色不是4s店" );
									return;
								}
								break;
						}
					List< TTokenUser > tokens = tokenUserService
					        .getTokenListByUserId( user.getId() );
					if ( CollectionUtils.isNotEmpty( tokens ) )
					{
						for ( TTokenUser oldToken : tokens )
						{
							tokenUserService.delToken( oldToken );
							
						}
					}
					String token = "";
					
					token = TokenUtil.getToken();
					// 把token和对应用户保存到登陆映射表
					TTokenUser tokenUser = new TTokenUser();
					tokenUser.setVcToken( token );
					tokenUser.setIUser( user.getId() );
					tokenUser.setDtLasttime( new Date() );
					tokenUserService.saveTokenUser( tokenUser );
					
					response.addHeader( "token" , token );
					response.addHeader( "userId" , String.valueOf( user.getId() ) );
					AjaxUtil.rendJson( response , true , "登录成功！" );
					return;
				}
				else
				{
					AjaxUtil.rendJson( response , false , "输入的密码有误！" );
					return;
				}
			}
			else
			{
				AjaxUtil.rendJson( response , false , "输入的用户名不存在" );
				return;
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "验证失败：" + e.getMessage() );
			return;
		}
	}
	
	/**
	 * @Description: 跳转到用户列表页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月2日 下午2:18:13
	 */
	@RequestMapping( "/openUserList" )
	@ApiIgnore
	public String openUserList()
	{
		return "back/system/userList";
	}
	
	/**
	 * @Description: 跳转到用户授予角色页面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 下午2:16:10
	 */
	@RequestMapping( "/giveRol" )
	@ApiIgnore
	public String giveRol( HttpServletRequest request )
	{
		String userId = request.getParameter( "userId" );
		String roleTree = urService.getAllAndUserHaveRole( userId );
		System.out.println( roleTree );
		request.setAttribute( "roleTree" , roleTree );
		return "back/system/roleTree";// 跳转到用户授予角色页面
	}
	
	/**
	 * @Description: 更新保存用户的角色
	 * @param userId
	 * @param roleids
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月4日 下午3:29:00
	 */
	@RequestMapping( "/saveUserRole" )
	@ApiIgnore
	public void saveUserRole( @RequestParam( "userId" ) String userId ,
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
	 * @Description: 跳转到首页
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 上午10:39:27
	 */
	@RequestMapping( "/index" )
	@ApiIgnore
	public String testaction()
	{
		return "index";
	}
	
	/**
	 * @Description: 网页端注册用
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 上午11:04:21
	 */
	@RequestMapping( value = "/register" , method = RequestMethod.POST )
	@ApiOperation( value = "注册" , notes = "注册用户" , position = 5 )
	@ResponseBody
	public Map< String , Object > register(
	        @ApiParam( value = "填写用户名" , required = true ) @RequestParam( "vcAccount" ) String vcAccount ,
	        @ApiParam( value = "填写用户密码" , required = true ) @RequestParam( "vcPassword" ) String vcPassword ,
	        @ApiParam( value = "填写用户类型" , required = true ) @RequestParam( "usertypeID" ) int utypeID ,
	        @ApiParam( value = "填写用户手机" , required = true ) @RequestParam( "phone" ) String phone ,
	        @ApiParam( value = "填写用户邮箱" , required = true ) @RequestParam( value = "email" ) String email ,
	        HttpServletResponse resp )
	{
		TUser user = new TUser();
		user.setIArchiveType( utypeID );
		user.setDtAddtime( new Date() );
		user.setVcAccount( vcAccount );
		user.setVcEmail( email );
		
		org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
		String tt = t.encodePassword( vcPassword , user.getVcAccount() );
		user.setVcPassword( tt );
		
		try
		{
			TUser userAccount = userService.getByAccount( vcAccount );
			if ( null == userAccount )
			{
				userService.saveRegister( user , phone );
				String token = TokenUtil.getToken();
				// 把token和对应用户保存到登陆映射表
				TTokenUser tokenUser = new TTokenUser();
				tokenUser.setVcToken( token );
				tokenUser.setIUser( user.getId() );
				tokenUser.setDtLasttime( new Date() );
				tokenUserService.saveTokenUser( tokenUser );
				
				resp.addHeader( "token" , token );
				return AjaxUtil.getMap( true , "注册成功!" );
			}
			else
			{
				return AjaxUtil.getMap( false , "注册失败，该用户名已经被占用！" );
			}
			
			/*request.setAttribute( "account" , uname );
			request.setAttribute( "password" , upass );
			
			request.getRequestDispatcher( "j_spring_security_check" ).forward( request ,
			        resp );*/
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 注册用户，并跳转到首页
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 上午11:04:21
	 */
	@RequestMapping( value = "/registerUser" , method = RequestMethod.POST )
	@ApiOperation( value = "注册" , notes = "注册用户" , position = 5 )
	@ResponseBody
	public Map< String , Object > registerUser(
	        @ApiParam( value = "填写用户名" , required = true ) @RequestParam( "vcAccount" ) String vcAccount ,
	        @ApiParam( value = "填写用户密码" , required = true ) @RequestParam( "vcPassword" ) String vcPassword ,
	        @ApiParam( value = "填写用户类型" , required = true ) @RequestParam( "usertypeID" ) int utypeID ,
	        @ApiParam( value = "填写用户手机" , required = true ) @RequestParam( "phone" ) String phone ,
	        @ApiParam( value = "填写用户邮箱" , required = true ) @RequestParam( value = "email" ) String email ,
	        @ApiParam( value = "短信验证码" , required = true ) @RequestParam( value = "code" ) String code ,
	        HttpServletResponse resp )
	{
		
		// int utypeID = Integer.parseInt( usertypeID );
		// 验证该手机号码是否有验证码，没有提示并终止
		String codeStr = userCodeService.getCodeByTel( phone );
		if ( StringUtils.isEmpty( codeStr ) )
		{
			
			return AjaxUtil.getMap( false , "该手机号码还没有获取短信验证码！" );
		}
		
		// 有短信验证码，验证是否相同
		if ( ! StringUtils.trim( code ).equals( codeStr ) )
		{
			return AjaxUtil.getMap( false , "您输入的短信验证码是错误的！" );
		}
		TUser user = new TUser();
		user.setIArchiveType( utypeID );
		user.setDtAddtime( new Date() );
		user.setVcAccount( vcAccount );
		user.setVcEmail( email );
		
		org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
		String tt = t.encodePassword( vcPassword , user.getVcAccount() );
		user.setVcPassword( tt );
		
		try
		{
			TUser userAccount = userService.getByAccount( vcAccount );
			if ( null == userAccount )
			{
				userService.saveRegister( user , phone );
				String token = TokenUtil.getToken();
				// 把token和对应用户保存到登陆映射表
				TTokenUser tokenUser = new TTokenUser();
				tokenUser.setVcToken( token );
				tokenUser.setIUser( user.getId() );
				tokenUser.setDtLasttime( new Date() );
				tokenUserService.saveTokenUser( tokenUser );
				
				resp.addHeader( "token" , token );
				return AjaxUtil.getMap( true , "注册成功!" );
			}
			else
			{
				return AjaxUtil.getMap( false , "注册失败，该用户名已经被占用！" );
			}
			
			/*request.setAttribute( "account" , uname );
			request.setAttribute( "password" , upass );
			
			request.getRequestDispatcher( "j_spring_security_check" ).forward( request ,
			        resp );*/
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 注册时 验证用户名是否存在
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 上午11:04:21
	 */
	@ApiOperation( value = "注册时,验证用户名是否存在" , notes = "验证用户名是否存在重复" , position = 5 )
	@ResponseBody
	@RequestMapping( value = "/checkRegisterUname" , method = RequestMethod.POST , produces = "application/json; charset=utf-8" )
	public void checkRegisterUname(
	        @ApiParam( value = "填写用户名" , required = true ) @RequestParam( "vcAccount" ) String vcAccount ,
	        HttpServletResponse resp )
	{
		
		TUser user = userService.getByAccount( vcAccount );
		
		if ( user != null )
		{
			AjaxUtil.rendJson( resp , false , vcAccount + " 已存在,请重新输入 " );
		}
		else
		{
			AjaxUtil.rendJson( resp , true , "该用户名可注册！" );
		}
		
	}
	
	/**
	 * @Description: 注册用户之前加载档案类型
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 上午11:04:21
	 */
	@RequestMapping( "/registerUserBefore" )
	@ApiIgnore
	public String registerUserBefore( HttpServletRequest request )
	{
		List< TArchiveType > typelist = archiveTypeService.loadAllByEnable();
		request.setAttribute( "typelist" , typelist );
		
		return "back/reg";
	}
	
	/**
	 * @Description: 用户列表
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月4日 下午3:38:39
	 */
	@RequestMapping( "/pageList" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > searchPrepayPage( HttpServletRequest request )
	{
		
		Page p = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TUser.class );
		helper.setQueryPage( p );
		helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );// 取有效值
		String userName = request.getParameter( "userName" );
		if ( StringUtils.isNotBlank( userName ) )
		{
			helper.addLike( "vcUsername" , userName );
		}
		String vcAccount = request.getParameter( "vcAccount" );
		if ( StringUtils.isNotBlank( vcAccount ) )
		{
			helper.addLikeIgnoreCase( "vcAccount" , vcAccount );
		}
		Map< String , Object > map = userService.findByHelper( helper );
		
		return map;
		
	}
	
	/**
	 * @Description: 禁用用户
	 * @param userIds
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年8月6日 上午11:29:09
	 */
	@RequestMapping( "/disableUser" )
	@ApiIgnore
	public void disableUser( @RequestParam( "userIds" ) String userIds ,
	        HttpServletResponse response )
	{
		if ( StringUtils.isNotBlank( userIds ) )
		{
			try
			{
				String[] arr = StringUtils.split( userIds , "," );
				for ( String id : arr )
				{
					TUser user = userService.getByid( id );
					if ( null != user )
					{
						user.setNEnable( 1 );
						userService.updateUser( user );
					}
				}
				AjaxUtil.rendJson( response , true , "禁用成功！" );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				AjaxUtil.rendJson( response , false , "禁用失败，失败原因：" + e.getMessage() );
			}
		}
		else
		{
			AjaxUtil.rendJson( response , true , "无效的数据，请重新选择！" );
		}
	}
	
	/**
	 * @Description: 打开新增用户界面
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月6日 上午11:46:42
	 */
	@RequestMapping( "/openAddUser" )
	@ApiIgnore
	public String openAddUser( HttpServletRequest request )
	{
		String paramType = "add";
		String userId = request.getParameter( "userId" );
		if ( StringUtils.isNotBlank( userId ) )
		{
			paramType = "update";
			TUser tuser = userService.getByid( userId );
			request.setAttribute( "tuser" , tuser );
		}
		List< TArchiveType > archiveTypes = archiveTypeService.loadAllByEnable();
		request.setAttribute( "archiveTypes" , archiveTypes );
		request.setAttribute( "paramType" , paramType );
		return "back/system/userForm";
	}
	
	/**
	 * 
	 * @Description: TODO(保存用户)
	 * @param request
	 * @param response
	 * @param tUser
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-15 下午3:18:38
	 */
	@RequestMapping( value = "/saveUser" , method = RequestMethod.POST )
	@ApiIgnore
	public void saveUser( HttpServletRequest request , String paramType ,
	        HttpServletResponse response , TUser tUser )
	{
		try
		{
			if ( tUser.getVcPassword() == null || tUser.getVcPassword() == "" )
			{
				tUser.setVcPassword( "123456" );
			}
			org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
			String tt = t.encodePassword( "123456" , tUser.getVcAccount() );
			tUser.setVcPassword( tt );
			userService.saveUpdateUser( tUser );
			if ( paramType.equalsIgnoreCase( "add" ) )// 新增
			{
				if ( tUser.getIArchiveType().equals( SystemConstants.SYS_TARCHIVE_SUB ) )
				{
					// 分供方
					TSubsuppliers tSubsuppliers = new TSubsuppliers();
					iSubsuppliersService.save( tSubsuppliers );
					tUser.setiArchive( tSubsuppliers.getId() );
					userService.updateUser( tUser );
				}
				if ( tUser.getIArchiveType().equals( SystemConstants.SYS_TARCHIVE_STORES ) )
				{
					// 4S店
					TStores tStores = new TStores();
					tStores.setiUserId( tUser.getId() );
					iStoresService.save( tStores );
					tUser.setiArchive( tStores.getId() );
					userService.updateUser( tUser );
				}
			}
			
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！原因：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description: 修改分供方用户的申请开通权限字段 修改为可重新申请
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 上午11:04:21
	 */
	@RequestMapping( "/resetUserApplyResource" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > resetUserApplyResource( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		String usid = request.getParameter( "usid" );
		try
		{
			userService.updateUserApplyResource( usid );
			request.getSession().setAttribute( "user" , userService.getByid( usid ) );
			return AjaxUtil.getMap( true , "操作成功！" );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
			
		}
		
	}
	
	/**
	 * @Description: 密码重置
	 * @param resp
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2015年4月10日 下午3:33:44
	 */
	@ApiOperation( value = "密码重置" , notes = "密码修改前，验证用户输入的手机号码是否为他注册时的号码，如果是，短信验证（验证码是否正确由客户端进行），加新密码 进行修改密码！" , position = 5 )
	@RequestMapping( value = "/passwordReset" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > passwordReset(
	        @ApiParam( value = "用户名" , required = true ) @RequestParam( "userId" ) String userId ,
	        @ApiParam( value = "用户的手机号码" , required = true ) @RequestParam( "phoneNo" ) String phoneNo ,
	        @ApiParam( value = "用户的新密码" , required = true ) @RequestParam( "passwrod" ) String passwrod ,
	        @ApiParam( value = "短信验证码" , required = true ) @RequestParam( "code" ) String code ,
	        
	        HttpServletResponse resp )
	{
		// TUser user = userService.getByid( userId );
		
		TUser user;
		try
		{
			user = userService.getByAccount( userId );
			if ( null == user )
			{
				
				return AjaxUtil.getMap( false , "没有找到相关用户，请检测用户名是否正确！" );
			}
		}
		catch ( Exception e1 )
		{
			e1.printStackTrace();
			return AjaxUtil.getMapByException( e1 );
		}
		String phone = user.getVcPhone();
		// 验证手机号码是否一致
		if ( phoneNo.equals( phone ) )
		{
			try
			{
				// 验证短信验证码
				// 验证该手机号码是否有验证码，没有提示并终止
				String codeStr = userCodeService.getCodeByTel( phone );
				if ( StringUtils.isEmpty( codeStr ) )
				{
					
					return AjaxUtil.getMap( false , "该手机号码还没有获取短信验证码！" );
				}
				
				// 有短信验证码，验证是否相同
				if ( ! StringUtils.trim( code ).equals( codeStr ) )
				{
					return AjaxUtil.getMap( false , "您输入的短信验证码是错误的！" );
				}
				// 重置密码
				org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
				String tt = t.encodePassword( passwrod , user.getVcAccount() );
				user.setVcPassword( tt );
				userService.updateUser( user );
				return AjaxUtil.getMap( true , "更新密码成功！" );
			}
			catch ( Exception e )
			{
				
				e.printStackTrace();
				return AjaxUtil.getMap( false , "更新密码失败！" );
			}
		}
		else
		{
			return AjaxUtil.getMap( false , "您输入的手机号码不是注册时的号码！" );
		}
		
	}
	
	/**
	 * @Description: 手机app，心跳请求，更新对应token的最新时间
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月17日 下午5:29:29
	 */
	@RequestMapping( value = "/heartbeat" , method = RequestMethod.POST )
	@ApiOperation( value = "手机app，心跳请求" , notes = "手机app，心跳请求，更新对应token的最新时间,更新成功或者失败都会返回信息给app,请求头带 token参数" , position = 5 )
	@ResponseBody
	public Map< String , Object > heartbeat( HttpServletRequest request )
	{
		System.out.println( "***********》》》》心跳请求：" + DateUtil.formatTime( new Date() ) );
		Map< String , Object > result;
		try
		{
			String token = request.getHeader( "token" );
			result = tokenUserService.updateLast( token );
			TUser user = ( TUser ) UserSession.get( "user" );
			user.setNIntegral( user.getNIntegral() + SystemConstants.SYS_ADD_INTEGRAL );
			userService.updateUser( user );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		return result;
	}
	
	@RequestMapping( value = "/logOutByApp" , method = RequestMethod.POST )
	@ApiOperation( value = "手机app，退出登陆" , notes = "手机app，退出登陆,返回信息给app,请求头带 token参数" , position = 5 )
	public Map< String , Object > logOutByApp( HttpServletRequest request )
	{
		try
		{
			String token = request.getHeader( "token" );
			tokenUserService.delToken( token );
			return AjaxUtil.getMapByNotException( true , null );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 保存gps信息
	 * 
	 * @param gps
	 * @param request
	 * @param response
	 */
	@RequestMapping( value = "/saveGps" , method = RequestMethod.POST )
	@ApiOperation( value = "手机app，用户定位接口" , notes = "用户定位接口" , position = 5 )
	@ResponseBody
	public Map< String , Object > saveGps(
	        @ApiParam( value = "经度" ) @RequestParam( "log" ) String log ,
	        @ApiParam( value = "纬度" ) @RequestParam( "lat" ) String lat ,
	        @ApiParam( value = "手机app，不用管这个值" ) HttpSession session ,
	        HttpServletResponse response )
	{
		try
		{
			System.out.println( "定位时间：" + DateUtil.formatTime( new Date() ) );
			TUserGps gps = new TUserGps();
			TUser user = ( TUser ) session.getAttribute( "user" );
			gps.setIUser( user.getId() );
			gps.setVcUser( user.getVcUsername() );
			gps.setVcLat( lat );
			gps.setVcLong( log );
			gpsService.save( gps );
			Map< String , String > map = new HashMap< String , String >();
			map.put( "nextTime" , "300" );
			return AjaxUtil.getMapByNotException( true , map );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-17 下午5:30:27
	 */
	@SuppressWarnings( "deprecation" )
	@ApiOperation( value = "用户第一次登录的时候绑定手机的唯一设备编号接口" , notes = "用户第一次登录的时候绑定手机的唯一设备编号接口" )
	@RequestMapping( value = "/bindDeviceTokens" , method = RequestMethod.POST )
	public void bindDeviceTokens(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "手机的唯一设备编号：vcDeviceTokens" , required = true ) @RequestParam( value = "vcDeviceTokens" , required = true ) String vcDeviceTokens )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		user.setVcDeviceTokens( vcDeviceTokens );
		try
		{
			userService.updateUser( user );
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(用户注册协议)
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-27 下午4:22:08
	 */
	
	@ApiOperation( value = "用户注册协议接口" , notes = "用户注册协议接口" )
	@RequestMapping( value = "/registerLicense" )
	public String registerLicense( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "sub/subuser/registerLicense";
		
	}
	
	@ApiOperation( value = "通过手机号码获得验证码" , notes = "通过手机号码获得验证码:调用该接口会返回一个json，"
	        + "<br/>json中包含两个字段，分别是 dispaly<是否显示>，message<验证码>。"
	        + "<br/>当dispaly为true，则用弹出窗口显示验证码，当dispaly为false，则不弹出。" )
	@RequestMapping( value = "/getVerificationCode" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getVerificationCode(
	        @ApiParam( value = "手机号码" , required = true ) @RequestParam( value = "tel" , required = true ) String tel )
	{
		// 生成验证码
		int i = ( int ) ( ( Math.random() * 9 + 1 ) * 100000 );
		String messageCode = String.valueOf( i );
		// 保存到数据库
		TUserCode userCode = new TUserCode();
		userCode.setVcPhone( tel );
		userCode.setVcCode( messageCode );
		userCodeService.save( userCode );
		// 返回给前台
		Map< String , Object > map = new HashMap< String , Object >();
		// 顺利返回结果内容
		map.put( "isSuccess" , true );
		map.put( "dispaly" , true );
		map.put( "verification" , messageCode );
		map.put( "message" , "短信验证码将发送到您的手机！" );
		
		return map;
	}
	
	/**
	 * 给手机用户返回分享信息内容。
	 * 
	 * @return
	 */
	@ApiOperation( value = "分享信息" , notes = "给手机用户返回分享信息内容，content 为内容。" )
	@RequestMapping( value = "" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getShareText()
	{
		Map< String , Object > result = null;
		try
		{
			String shareText = staticService.getStringByParame( "shareText" );
			String imgUrl = staticService.getStringByParame( "imgUrl" );
			String appurl = staticService.getStringByParame( "appUrl" );
			if ( StringUtils.isNotBlank( shareText ) )
			{
				result = AjaxUtil.getMap( true , "获取成功！" );
				result.put( "content" , shareText );
				result.put( "imgUrl" , imgUrl );
				result.put( "url" , appurl );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		return result;
	}
	
	/**
	 * @Description: 退出登录
	 * @param session
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2015年9月10日 下午3:05:04
	 */
	@RequestMapping( "/layoutByPC" )
	@ResponseBody
	public Map< String , Object > layoutByPC( HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null != user )
		{
			UserSession.set( "user" , null );
			session.setAttribute( "user" , null );
			session.setMaxInactiveInterval( 0 );
		}
		return AjaxUtil.getMap( true , "退出成功，回到首页！" );
	}
}
