package com.clt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clt.common.UserSession;
import com.clt.systemmanger.model.TTokenUser;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.ITokenUserService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.JdkAesUtil;
import com.clt.util.SystemConstants;

public class SessionFilter extends OncePerRequestFilter
{
	
	@Override
	protected void doFilterInternal( HttpServletRequest request ,
	        HttpServletResponse response , FilterChain filterChain )
	        throws ServletException , IOException
	{
		Enumeration enu = request.getParameterNames();
		if ( enu.hasMoreElements() )
		{
			System.out.println( "-----------开始打印请求参数-------" );
			while ( enu.hasMoreElements() )
			{
				String paraName = ( String ) enu.nextElement();
				System.out.println( paraName + ": " + request.getParameter( paraName ) );
			}
			System.out.println( "-----------结束打印请求参数-------" );
		}
		
		// app url签名处理
		String timestamp = request.getParameter( "timestamp" );
		if ( StringUtils.isBlank( timestamp ) )
		{
			timestamp = request.getHeader( "timestamp" );
		}
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		if ( StringUtils.isBlank( visit ) )
		{
			visit = request.getHeader( SystemConstants.APP_VISIT_PARME );
		}
		
		String sign = request.getParameter( "sign" );// 获得url签名
		if ( StringUtils.isBlank( sign ) )
		{
			sign = request.getHeader( "sign" );
		}
		
		String token = request.getHeader( "token" );
		// String path = request.getContextPath();
		String urlPath = request.getScheme() + "://" + request.getServerName() + ":"
		        + request.getServerPort() + request.getRequestURI();
		System.out.println( "urlPath=" + urlPath );
		String uri = request.getRequestURI();
		if ( ( StringUtils.isNotBlank( visit ) && SystemConstants.APP_VISIT
		        .equals( visit ) )
		        && StringUtils.isNotBlank( timestamp )
		        && ! uri.endsWith( "loginByApp" )
		        && ! uri.endsWith( "registerUser" )
		        && ! uri.endsWith( "checkRegisterUname" )
		        && ! uri.endsWith( "getLatestAppVersion" )
		        && ! uri.endsWith( "getVerificationCode" )
		        && ! uri.endsWith( "passwordReset" ) )
		{
			long oldtime = Long.parseLong( timestamp );
			long currentTime = System.currentTimeMillis();
			if ( ( currentTime - oldtime < 0 ) && ( currentTime - oldtime > 5000 ) )
			{
				// 过时的请求，请app从新获得sign和timestamp，拒绝访问
				request.setCharacterEncoding( "UTF-8" );
				response.setCharacterEncoding( "UTF-8" );
				response.setContentType( "text/html; charset=utf-8" );
				PrintWriter out = response.getWriter();
				String json = "{'isSuccess':false,'message':'url访问过时！'}";
				out.print( json );
			}
			else
			{
				// String sign = request.getParameter( "sign" );// 获得url签名
				// String token = request.getHeader( "token" );
				// TODO-hjx 通过用户id在缓存中获得token
				WebApplicationContext web = WebApplicationContextUtils
				        .getWebApplicationContext( this.getServletContext() );
				ITokenUserService tokenService = ( ITokenUserService ) web
				        .getBean( "tokenUserServiceImp" );
				
				TTokenUser tokenUser = tokenService.getToken( token );
				if ( null != tokenUser )
				{
					int userId = tokenUser.getIUser();
					IUserService userService = ( IUserService ) web
					        .getBean( "userServiceImp" );
					
					// 验证签名
					// 获得访问uri
					// String uri = request.getRequestURI();
					urlPath += "?" + token + "&" + timestamp;
					String newSign = JdkAesUtil.MD5( urlPath );// 32位加密
					if ( newSign.equals( sign ) )
					{
						// TODO-hjx 从缓存中获取 TUser 对象 并保存到会话里。
						TUser user = userService.getByid( String.valueOf( userId ) );
						request.getSession().setAttribute( "user" , user );
						UserSession.set( "user" , user );
						
						filterChain.doFilter( request , response );// 通过验证 继续访问
					}
					else
					{
						// 签名验证不通过，
						request.setCharacterEncoding( "UTF-8" );
						response.setCharacterEncoding( "UTF-8" );
						response.setContentType( "text/html; charset=utf-8" );
						PrintWriter out = response.getWriter();
						String json = "{'isSuccess':false,'message':'url签名验证不通过!'}";
						out.print( json );
						
					}
				}
				else
				{
					// 没有通过token找到对应会话信息
					request.setCharacterEncoding( "UTF-8" );
					response.setCharacterEncoding( "UTF-8" );
					response.setContentType( "text/html; charset=utf-8" );
					PrintWriter out = response.getWriter();
					String json = "{'isSuccess':false,'message':'会话过时，请重新登陆！'}";
					out.print( json );
					// filterChain.doFilter( request , response );// 通过验证 继续访问
				}
				
			}
			
		}
		else
		{
			
			if ( StringUtils.isNotBlank( visit )
			        && SystemConstants.APP_VISIT.equals( visit ) )
			{
				filterChain.doFilter( request , response );
			}
			else
			{
				
				HttpServletRequest session_request = ( HttpServletRequest ) request;
				String path = request.getContextPath();
				String basePath = request.getScheme() + "://" + request.getServerName()
				        + ":" + request.getServerPort() + path + "/";
				
				HttpSession session = session_request.getSession();
				if ( null != session )
				{
					TUser user = ( TUser ) session.getAttribute( "user" );
					String ip = request.getRemoteAddr();
					UserSession.set( "basePath" , basePath );// 当前项目的web目录
					UserSession.set( "ip" , ip );// 当前项目的web目录
					if ( user != null )
					{
						UserSession.set( "user" , user );
					}
					else
					{
						/*user = ( TUser ) UserSession.get( "user" );
						if ( null != user )
							session.setAttribute( "user" , user );*/
					}
				}
				
				filterChain.doFilter( request , response );
			}
			
		}
		
		// 不过滤的uri
		/*String[] notFilter = new String[] { "login", "userLogin",
				"/UserServlet/openlogin", "static","UserServlet/ajaxCheckUser" };

		// 请求的uri
		String uri = request.getRequestURI();
		String path = request.getContextPath() + "/";

		// uri中包含background时才进行过滤
		// 是否过滤
		boolean doFilter = true;
		for (String s : notFilter) {
			if (uri.equals(path) || uri.indexOf(s) != -1) {
				// 如果uri中包含不过滤的uri，则不进行过滤
				doFilter = false;
				break;
			}
		}
		if (doFilter) {
			// 执行过滤
			// 从session中获取登录者实体
			Object obj = request.getSession().getAttribute("lineid");
			if (null == obj) {
				// 如果session中不存在登录者实体，则弹出框提示重新登录
				// 设置request和response的字符集，防止乱码
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				String loginPage = "login.jsp";
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\">");
				builder.append("alert('请重新登录！');");
				builder.append("window.top.location='");
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
				builder.append(basePath+loginPage);
				builder.append("';");
				builder.append("</script>");
				out.print(builder.toString());
			} else {
				// 如果session中存在登录者实体，则继续
				filterChain.doFilter(request, response);
			}
		} else {
			// 如果不执行过滤，则继续
			filterChain.doFilter(request, response);
		}*/
	}
}
