package com.clt.sub.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.clt.sub.model.TDriver;
import com.clt.sub.model.TDriverSalary;
import com.clt.sub.model.TDriverSalaryCoefficient;
import com.clt.sub.model.TDriverSubsideLinks;
import com.clt.sub.model.TDriverSubsides;
import com.clt.sub.model.TSharecircle;
import com.clt.sub.service.IDriverSalaryService;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.ISharecircleService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserRole;
import com.clt.systemmanger.service.IPictureService;
import com.clt.systemmanger.service.IRoleService;
import com.clt.systemmanger.service.IStaticService;
import com.clt.systemmanger.service.IUserRoleService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping( "/driverAction" )
@Api( value = "driver-api" , description = "有关司机的操作" , position = 5 )
public class DriverAction
{
	@Autowired
	private IDriverService driverService;
	@Autowired
	private ISubsuppliersService subService;
	@Autowired
	private IStaticService staticService;
	@Autowired
	ITruckDriverService truckDriverService;
	
	@Autowired
	private IDriverSalaryService salaryService;
	@Autowired
	private IPictureService pictureService;
	@Autowired
	IUserService iUserService;
	@Autowired
	IRoleService roleService;
	@Autowired
	IUserRoleService iUserRoleService;
	@Autowired
	private ISharecircleService shareService;
	
	/**
	 * @Description: 获取该司机工资系数
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/intoDriverSalaryCoefficient" )
	@ApiIgnore
	public String intoDriverSalaryCoefficient( HttpServletRequest request )
	{
		
		return "sub/subBasicData/driverSalaryCoefflist";
		
	}
	
	/**
	 * 
	 * @Description: TODO(司机补贴)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-27 下午5:12:15
	 */
	@RequestMapping( "/intoDriverSubsidies" )
	@ApiIgnore
	public String intoDriverSubsidies( HttpServletRequest request )
	{
		return "sub/subBasicData/driverSalarySubsidList";
	}
	
	/**
	 * 
	 * @Description: TODO(司机与补贴关联维护)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-28 下午2:00:19
	 */
	@RequestMapping( "/intoDriverSubsidiesLinks" )
	@ApiIgnore
	public String intoDriverSubsidiesLinks( HttpServletRequest request )
	{
		return "sub/subBasicData/driverSubsidiesLinks";
	}
	
	/**
	 * 
	 * @Description: TODO(司机补贴关联)
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-29 上午10:25:57
	 */
	@RequestMapping( value = "/getDriverSubsidiesLinks" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getDriverSubsidiesLinks( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TDriver.class );
		hql.setQueryPage( page );
		hql.addEqual( "vcSubno" , subno );
		hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		
		String driverName = request.getParameter( "driverName" );
		if ( StringUtils.isNotBlank( driverName ) )
		{
			hql.addLike( "vcDriverName" , driverName );
			
		}
		hql.addOrderBy( "id" , "desc" );
		Map< String , Object > resuMap = driverService.findDriverSalaryCoffe( hql );
		
		return resuMap;
		
	}
	
	/**
	 * 
	 * @Description: TODO(新增司机补贴关联)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-1 下午3:25:08
	 */
	@RequestMapping( value = "/saveBeforeSubsidiesLinks" )
	@ApiIgnore
	public String saveBeforeDriverlink( HttpServletRequest request , String id )
	{
		System.out.println( "id = " + id );
		request.setAttribute( "driverIds" , id );
		return "sub/subBasicData/driverSubsidiesLinksSave";
	}
	
	/**
	 * 
	 * @Description: TODO(保存司机补贴关联)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-1 下午5:08:13
	 */
	@RequestMapping( value = "/saveSalarySubsideLinks" , method = RequestMethod.POST )
	@ApiIgnore
	public void saveSalarySubsideLinks( HttpServletRequest request ,
	        HttpServletResponse response , String strIds , String driverIds )
	{
		String[] subsidesIds = strIds.split( "," );// 补贴ID
		String[] driverId = driverIds.split( "," );// 司机id
		try
		{
			for ( String drId : driverId )
			{
				List< TDriverSubsideLinks > tLinks = driverService
				        .findTDriverSubsideLinksByDriverId( Integer.parseInt( drId ) );
				driverService.deleteDriverLinks( tLinks );
				for ( String subId : subsidesIds )
				{
					TDriverSubsideLinks tSubsideLinks = new TDriverSubsideLinks();
					tSubsideLinks.setiDriverId( Integer.parseInt( drId ) );
					tSubsideLinks.setnEnable( SystemConstants.SYS_ENABLE );
					tSubsideLinks.setiSubsidesId( Integer.parseInt( subId ) );
					driverService.saveUpdateDriverSubsideLink( tSubsideLinks );
				}
			}
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(删除司机补贴关联)
	 * @param request
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-3 上午9:45:42
	 */
	@RequestMapping( value = "/deleteSubsidiesLinks" )
	@ApiIgnore
	public void deleteSalarySubsideLinks( HttpServletRequest request ,
	        HttpServletResponse response , String driverIds )
	{
		String[] driverId = driverIds.split( "," );// 司机id
		try
		{
			for ( String drId : driverId )
			{
				List< TDriverSubsideLinks > tLinks = driverService
				        .findTDriverSubsideLinksByDriverId( Integer.parseInt( drId ) );
				driverService.deleteDriverLinks( tLinks );
			}
			AjaxUtil.rendJson( response , true , "成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "失败！" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(展开司机对应的补贴详情)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-29 上午11:27:39
	 */
	@RequestMapping( value = "/getDriverSubside" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getDriverSubside( HttpServletRequest request ,
	        String driverId )
	{
		int drId = Integer.parseInt( driverId );
		String sql = "SELECT td.* FROM T_DRIVER_SUBSIDES td , T_DRIVER_SUBSIDES_LINK t where td.id = t.i_subsidesid "
		        + " and t.i_driverid = " + drId;
		Map< String , Object > subsideMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		System.out.println( "sql = " + sql );
		try
		{
			// String subbo = subService.get( user.getiArchive() ).getVcSubno();
			Page page = ServiceUtil.getcurrPage( request );
			subsideMap = driverService.getSpringSql( sql , page );
			return AjaxUtil.getMapByResult( visit , subsideMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(获取司机补贴列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-27 下午5:36:21
	 */
	@RequestMapping( value = "/getDriverSalarySubsideList" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getDriverSalarySubsideList( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TDriverSubsides.class );
		hql.setQueryPage( page );
		hql.addEqual( "vcSubno" , subno );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		String vcName = request.getParameter( "vcName" );
		if ( StringUtils.isNotBlank( vcName ) )
		{
			hql.addEqual( "vcName" , vcName );
		}
		
		hql.addOrderBy( "id" , "desc" );
		Map< String , Object > resuMap = driverService.findDriverSalarySubside( hql );
		
		return resuMap;
	}
	
	/**
	 * 
	 * @Description: TODO(编辑、新增司机补贴)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-27 下午5:43:40
	 */
	@RequestMapping( value = "/saveBeforeSalarySubside" )
	@ApiIgnore
	public String saveBeforeSalarySubside( HttpServletRequest request , String salaryID )
	{
		HttpSession session = request.getSession();
		String paramType = "add";
		
		if ( StringUtils.isNotBlank( salaryID ) )
		{
			TDriverSubsides tDriverSubsides = driverService
			        .findDriverSalarySubsideById( Integer.parseInt( salaryID ) );
			request.setAttribute( "tDriverSubsides" , tDriverSubsides );
			paramType = "update";
		}
		
		request.setAttribute( "paramType" , paramType );
		return "sub/subBasicData/saveDriverSalarySubside";
	}
	
	/**
	 * 
	 * @Description: TODO(保存司机补贴)
	 * @param request
	 * @param response
	 * @param tDriverSubsides
	 * @param paramType
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-28 上午9:28:02
	 */
	@ApiIgnore
	@RequestMapping( value = "/saveDriverSalarySubside" , method = RequestMethod.POST )
	public void saveDriverSalarySubside( HttpServletRequest request ,
	        HttpServletResponse response , TDriverSubsides tDriverSubsides ,
	        String paramType )
	{
		try
		{
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			String subno = subService.get( user.getiArchive() ).getVcSubno();
			tDriverSubsides.setVcSubno( subno );
			if ( paramType.equalsIgnoreCase( "add" ) )
			{
				driverService.saveDriverSubsides( tDriverSubsides );
			}
			else if ( paramType.equalsIgnoreCase( "update" ) )
			{
				tDriverSubsides.setnEnable( SystemConstants.SYS_ENABLE );
				driverService.updateDriverSubsides( tDriverSubsides );
			}
			AjaxUtil.rendJson( response , true , "保存成功" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "保存失败！原因：" + e.getMessage() );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(获取司机工资系数列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-25 上午11:04:32
	 */
	@RequestMapping( value = "/getDriverSalaryCoefflist" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getDriverSalaryCoefflist( HttpServletRequest request )
	{
		
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TDriverSalaryCoefficient.class );
		hql.setQueryPage( page );
		hql.addEqual( "vcSubno" , subno );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		
		String driverName = request.getParameter( "vcDriver" );
		if ( StringUtils.isNotBlank( driverName ) )
		{
			hql.addLike( "vcDriver" , driverName );
			
		}
		hql.addOrderBy( "id" , "desc" );
		Map< String , Object > resuMap = driverService.findDriverSalaryCoffe( hql );
		
		return resuMap;
		
	}
	
	/**
	 * 
	 * @Description: TODO(司机工资系数)
	 * @param request
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-25 上午11:47:35
	 */
	@RequestMapping( value = "/saveBeforeSalaryCoff" )
	@ApiIgnore
	public String saveBeforeSalaryCoff( HttpServletRequest request , String salaryID )
	{
		HttpSession session = request.getSession();
		String paramType = "add";
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		String[] propertyNames = { "vcSubno" , "NEnable" };
		Object[] values = { subno , SystemConstants.SYS_ENABLE };
		List< TDriver > tDrivers = driverService.findByPropertys( propertyNames , values );
		if ( StringUtils.isNotBlank( salaryID ) )
		{
			TDriverSalaryCoefficient tSalaryCoefficient = driverService
			        .findDriverSalaryCoffById( Integer.parseInt( salaryID ) );
			request.setAttribute( "tSalaryCoefficient" , tSalaryCoefficient );
			paramType = "update";
		}
		
		request.setAttribute( "tDrivers" , tDrivers );
		request.setAttribute( "paramType" , paramType );
		return "sub/subBasicData/saveDriverSalaryCoff";
		
	}
	
	/**
	 * 
	 * @Description: TODO(司机工资系数删除)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-26 下午3:01:05
	 */
	@ApiIgnore
	@RequestMapping( value = "/delSalaryCoff" , method = RequestMethod.POST )
	public void delSalaryCoff( HttpServletRequest request , HttpServletResponse response ,
	        String scID )
	{
		int id = Integer.parseInt( scID );
		try
		{
			TDriverSalaryCoefficient driverSalaryCoefficient = driverService
			        .findDriverSalaryCoffById( id );
			driverSalaryCoefficient.setnEnable( SystemConstants.SYS_DISABLE );
			driverService.saveOrUpdateSalaryCoefficient( driverSalaryCoefficient );
			AjaxUtil.rendJson( response , true , "操作成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "操作失败!原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-25 下午3:31:18
	 */
	@ApiIgnore
	@RequestMapping( value = "/saveDriverSalaryCofflis" , method = RequestMethod.POST )
	public void saveSalaryCoff( HttpServletRequest request ,
	        HttpServletResponse response , String paramType ,
	        TDriverSalaryCoefficient tSalaryCoefficient )
	{
		try
		{
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			String subno = subService.get( user.getiArchive() ).getVcSubno();
			TDriver tDriver = driverService.get( tSalaryCoefficient.getiDriverId() );
			if ( paramType.equalsIgnoreCase( "add" ) )
			{
				// 检查重复性
				String message = driverService.checkSalaryCoefficient(
				        tSalaryCoefficient , subno , tDriver );
				if ( message.equalsIgnoreCase( "success" ) )
				{
					tSalaryCoefficient.setVcSubno( subno );
					tSalaryCoefficient.setnEnable( SystemConstants.SYS_ENABLE );
					
					tSalaryCoefficient.setVcDriver( tDriver.getVcDriverName() );
					driverService.saveOrUpdateSalaryCoefficient( tSalaryCoefficient );
					AjaxUtil.rendJson( response , true , "保存成功！" );
				}
				else
				{
					AjaxUtil.rendJson( response , false , "保存失败！" + message );
				}
			}
			else if ( paramType.equalsIgnoreCase( "update" ) )
			{
				TDriverSalaryCoefficient tCoefficient = driverService
				        .findDriverSalaryCoffById( tSalaryCoefficient.getId() );
				// 查出之前的如果司机如果没有改变司机，则放行不进行验证
				if ( tCoefficient.getiDriverId().equals(
				        tSalaryCoefficient.getiDriverId() ) )
				{
					tSalaryCoefficient.setVcSubno( subno );
					tSalaryCoefficient.setnEnable( SystemConstants.SYS_ENABLE );
					tSalaryCoefficient.setVcDriver( tDriver.getVcDriverName() );
					driverService.updateTSalaryCOfficient( tSalaryCoefficient );
					AjaxUtil.rendJson( response , true , "保存成功！" );
				}
				else
				{
					String message = driverService.checkSalaryCoefficient(
					        tSalaryCoefficient , subno , tDriver );
					if ( message.equalsIgnoreCase( "success" ) )
					{
						tSalaryCoefficient.setVcSubno( subno );
						tSalaryCoefficient.setnEnable( SystemConstants.SYS_ENABLE );
						
						tSalaryCoefficient.setVcDriver( tDriver.getVcDriverName() );
						driverService.updateTSalaryCOfficient( tSalaryCoefficient );
						AjaxUtil.rendJson( response , true , "保存成功！" );
					}
					else
					{
						AjaxUtil.rendJson( response , false , "保存失败！" + message );
					}
				}
			}
			
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "保存失败！原因：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 查询所有司机信息
	 * @param request
	 * @param
	 * @author chengwzh
	 * @date 2015/5/12 17:12
	 */
	@RequestMapping( value = "/getAllDrivers" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "查询所有司机信息" , notes = "查询所有司机信息" , position = 5 )
	public Map< String , Object > getAllDrivers( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		String subno = subService.get( subId ).getVcSubno();// 获取分供方编号
		HqlHelper hql = new HqlHelper( TDriver.class );
		Page page = ServiceUtil.getcurrPage( request );// 获取当前页
		hql.setQueryPage( page );
		hql.addEqual( "vcSubno" , subno );
		hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		hql.addOrderBy( "id" , "desc" );
		try
		{
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
			Map< String , Object > result = driverService.findByHelper( hql );
			result = driverService.parseUrl( result );// 解析图片路径
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 增加/修改司机
	 * @param driver
	 */
	@RequestMapping( value = "/addDriver" , method = RequestMethod.POST )
	@ApiOperation( value = "分供方角色：增加/修改司机" , notes = "增加/修改司机，并上传头像" , position = 5 , response = TDriver.class )
	public void addDriver(
	        @ApiParam( value = "司机信息" ) @ModelAttribute TDriver driver ,
	        @ApiParam( value = "上传司机图像用的字段" ) @RequestParam( value = "files" , required = false ) CommonsMultipartFile[] files ,
	        @ApiParam( value = "上传身份证照片用的字段" ) @RequestParam( value = "cardImgFile" , required = false ) CommonsMultipartFile[] cardImgFile ,
	        @ApiParam( value = "上传行驶证照片用的字段" ) @RequestParam( value = "driveImgFile" , required = false ) CommonsMultipartFile[] driveImgFile ,
	        @ApiParam( value = "上传车辆图片用的字段" ) @RequestParam( value = "truckImgFile" , required = false ) CommonsMultipartFile[] truckImgFile ,
	        HttpServletResponse response , HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			int subId = user.getiArchive();
			int type = user.getIArchiveType();
			String subno = "";
			if ( type == SystemConstants.SYS_TARCHIVE_SUB )
			{
				subno = subService.get( subId ).getVcSubno();// 获取分供方编号
			}
			// 验证司机号码是否被注册过
			boolean flag = true;
			if ( driver.getId() == null ) // 新增保存时
			{
				String[] propertyNames = { "NEnable" , "vcDriverTel" };
				Object[] values = { SystemConstants.SYS_ENABLE , driver.getVcDriverTel() };
				List< TDriver > tDrivers = driverService.findByPropertys( propertyNames ,
				        values );
				if ( tDrivers != null && tDrivers.size() > 0 )
				{
					flag = false;
					AjaxUtil.rendJson( response , false ,
					        "手机号码【" + driver.getVcDriverTel() + "】被注册了！" );
				}
			}
			else
			// 编辑保存时
			{
				TDriver newDriver = driverService.get( driver.getId() );
				// 如果编辑时用户的手机号码未更改则不进行重复性验证
				if ( ! newDriver.getVcDriverTel().equals( driver.getVcDriverTel() ) )
				{
					String[] propertyNames = { "NEnable" , "vcDriverTel" };
					Object[] values = { SystemConstants.SYS_ENABLE ,
					        driver.getVcDriverTel() };
					List< TDriver > tDrivers = driverService.findByPropertys(
					        propertyNames , values );
					if ( tDrivers != null && tDrivers.size() > 0 )
					{
						flag = false;
						AjaxUtil.rendJson( response , false ,
						        "手机号码【" + driver.getVcDriverTel() + "】被注册了！" );
					}
				}
				
			}
			
			if ( flag )
			{
				driver.setVcSubno( subno );
				driver.setiUserId( user.getId() );
				if ( driver.getId() == null ) // 新增
				{
					if ( files != null )
					{
						uploadImg( request , files , driver );// 上传司机图片
					}
					if ( cardImgFile != null )
					{
						uploadCardImg( request , cardImgFile , driver );// 上传身份证照片
					}
					if ( driveImgFile != null )
					{
						uploadDriveImg( request , driveImgFile , driver );// 上传行驶证照片
					}
					if ( truckImgFile != null )
					{
						uploadTruckImg( request , truckImgFile , driver );// 上传车辆照片
					}
					String headImgPath = driver.getVcImgPath();
					driverService.save( driver );
					/**
					 * 将司机存入用户表
					 */
					TUser tuser = new TUser();
					if ( StringUtils.isNotBlank( headImgPath ) )
					{
						tuser.setVcImgpath( headImgPath );// 设置头部图像
					}
					tuser.setNEnable( 0 );
					tuser.setDtAddtime( new Date() );
					tuser.setIArchiveType( SystemConstants.SYS_TARCHIVE_DRIVER );
					tuser.setiArchive( driver.getId() );
					tuser.setVcUsername( driver.getVcDriverName() );
					// 司机的手机号为登陆用户名 密码为123456 MD5加密
					tuser.setVcAccount( driver.getVcDriverTel() );
					org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
					String tt = t.encodePassword( "123456" , tuser.getVcAccount() );
					tuser.setVcPassword( tt );
					iUserService.saveUpdateUser( tuser );
					// 找出已创建的司机角色
					List< TRole > tRoles = roleService.findDriverRole();
					if ( tRoles.size() > 0 )
					{
						TUserRole tUserRole = new TUserRole();
						tUserRole.setIEnable( SystemConstants.SYS_ENABLE );
						tUserRole.setIRole( tRoles.get( 0 ).getId() );
						tUserRole.setIUser( tuser.getId() );
						iUserRoleService.save( tUserRole );
						AjaxUtil.rendJson( response , true , "保存成功！" );
					}
					else
					{
						AjaxUtil.rendJson( response , false , "司机角色未维护!" );
					}
					
				}
				else
				{
					if ( files != null )
					{
						uploadImg( request , files , driver );// 上传司机图片
					}
					if ( cardImgFile != null )
					{
						uploadCardImg( request , cardImgFile , driver );// 上传身份证照片
					}
					if ( driveImgFile != null )
					{
						uploadDriveImg( request , driveImgFile , driver );// 上传行驶证照片
					}
					if ( truckImgFile != null )
					{
						uploadTruckImg( request , truckImgFile , driver );// 上传车辆照片
					}
					
					String headImgPath = driver.getVcImgPath();// 获取司机图像路径
					TDriver oldDriver = driverService.get( driver.getId() );
					driver.setVcSubno( oldDriver.getVcSubno() );// 获取分供方编号
					driverService.updateCleanBefore( driver );// 更新时，会判断是否需要更新图片路径
					String[] properties = { "IArchiveType" , "iArchive" };
					Object[] mainValues = { SystemConstants.SYS_TARCHIVE_DRIVER ,
					        driver.getId() };
					TUser tUser = iUserService.findByProperties( properties , mainValues )
					        .get( 0 );
					if ( StringUtils.isNotBlank( headImgPath ) )
					{
						if ( headImgPath.startsWith( "http" ) )
						{
							headImgPath = headImgPath.substring( headImgPath
							        .lastIndexOf( "/" ) + 1 );
						}
						tUser.setVcImgpath( headImgPath );// 更新用户头部图像
						// 同时更新分享圈司机图像
						String[] propertyNames = { "IUserid" };
						Object[] values = { user.getId() };
						List< TSharecircle > shares = shareService.findByPropertys(
						        propertyNames , values );
						for ( TSharecircle share : shares )
						{
							share.setVcHeadImg( headImgPath );
							shareService.updateCleanBefore( share );
						}
					}
					tUser.setDtAddtime( new Date() );
					tUser.setVcAccount( driver.getVcDriverTel() );
					tUser.setVcUsername( driver.getVcDriverName() );
					// org.springframework.security.authentication.encoding.Md5PasswordEncoder
					// t = new Md5PasswordEncoder();
					// String tt = t.encodePassword( "123456" ,
					// tUser.getVcAccount() );
					// tUser.setVcPassword( tt );
					iUserService.updateUser( tUser );
					AjaxUtil.rendJson( response , true , "保存成功！" );
				}
				
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 通过Id查询司机信息
	 * @param driverId
	 * @author chengwzh
	 * @date 2015/5/13 10:30
	 */
	@RequestMapping( value = "/findDriverById" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "通过Id查询司机" , notes = "通过Id查询司机" , position = 5 )
	public Map< String , Object > findDriverById( HttpServletRequest request ,
	        @ApiParam( "司机id" ) @RequestParam( "driverId" ) int driverId )
	{
		try
		{
			Map< String , Object > driver = driverService.findById( driverId );
			Map< String , Object > truck = driverService.findCarNoById( driverId );
			if ( driver == null )
			{
				return AjaxUtil.getMap( false , "没有相关记录" );
			}
			if ( truck == null )
			{
				driver.put( "vcCarNo" , "暂无" );
			}
			else
			{
				driver.put( "truckId" , truck.get( "ID" ) );
				driver.put( "vcCarNo" , truck.get( "VC_CAR_NO" ) );
			}
			// 解析图片路径
			String url = staticService.getStringByParame( "driverUrl" );
			if ( StringUtils.isBlank( url ) )
			{
				return AjaxUtil.getMap( false , "图片加载主目录找不到" );
			}
			if ( ! url.endsWith( "/" ) )
			{
				url += "/";
			}
			Object imgPathObj = driver.get( "VC_IMGPATH" );
			if ( imgPathObj == null )
			{
				// return AjaxUtil.getMap( false , "图片路径不存在" );
			}
			else
			{
				String imgPath = imgPathObj.toString();
				String path = url + imgPath;
				driver.put( "VC_IMGPATH" , path );
			}
			
			JSONArray array = JSONArray.fromObject( driver );
			System.out.println( "driver json:" + array );
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
			if ( StringUtils.isNotBlank( visit )
			        && SystemConstants.APP_VISIT.equals( visit ) )
			{
				return AjaxUtil.getMapByNotException( true , driver );
			}
			else
			{
				return driver;
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 删除司机信息
	 * @param driverId
	 * @author chengwzh
	 * @date 2015/5/13 10:40
	 */
	@RequestMapping( value = "/deleteDriver" , method = RequestMethod.POST )
	@ApiOperation( value = "删除司机" , notes = "删除司机" , position = 5 , response = TDriver.class )
	@ResponseBody
	public Map< String , Object > deleteDriver(
	        @ApiParam( value = "司机id" ) @RequestParam( "driverId" ) Integer driverId ,
	        HttpServletResponse response )
	{
		try
		{
			driverService.deleteLinkById( driverId );// 删除link
			TDriver driver = driverService.get( driverId );
			driver.setNEnable( SystemConstants.SYS_DISABLE );
			
			/**
			 * 对应用户表也应删除
			 */
			String[] properties = { "IArchiveType" , "iArchive" , "NEnable" };
			Object[] mainvalues = { SystemConstants.SYS_TARCHIVE_DRIVER , driverId ,
			        SystemConstants.SYS_ENABLE };
			List< TUser > tUsers = iUserService
			        .findByProperties( properties , mainvalues );
			if ( tUsers != null && tUsers.size() > 0 )
			{
				for ( TUser tUser : tUsers )
				{
					tUser.setNEnable( SystemConstants.SYS_DISABLE );
					iUserService.updateUser( tUser );
				}
			}
			driverService.update( driver );
			return AjaxUtil.getMap( true , "删除司机，并把关联车辆关系去除！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 获取所有的司机类别
	 */
	@RequestMapping( value = "/getAllTypes" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有司机类别" , notes = "获取所有司机类别" , position = 5 )
	public Map< String , Object > getAllTypes( HttpServletRequest request )
	{
		try
		{
			
			Map< String , Object > result = driverService.findDriverClass();
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 进入司机工资列表页面
	 * @author chengwzh
	 * @date 2015/5/22 10:50
	 */
	@RequestMapping( "/intoDriverSalaryList" )
	@ApiIgnore
	public String intoDriverSalaryList()
	{
		return "sub/subBasicData/driverSalaryInfoList";
	}
	
	/**
	 * @Description 获取司机工资列表
	 * @author chengwzh
	 * @date 2015/5/22 11:40
	 */
	@RequestMapping( "/getDriverSalaryList" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getDriverSalaryList( HttpServletRequest request ,
	        @RequestParam( value = "driverName" , required = false ) String driverName ,
	        @RequestParam( value = "nMonth" , required = false ) Integer nMonth )
	
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		String subno = subService.get( subId ).getVcSubno();
		HqlHelper hql = new HqlHelper( TDriverSalary.class );
		Page page = ServiceUtil.getcurrPage( request );
		hql.setQueryPage( page );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqual( "vcSubno" , subno );
		// 匹配司机名字
		if ( StringUtils.isNotBlank( driverName ) )
		{
			hql.addEqual( "vcDriverName" , driverName );
		}
		// 匹配月份
		if ( nMonth != null )
		{
			hql.addEqual( "nMonth" , nMonth );
		}
		Map< String , Object > result = salaryService.findByHelper( hql );
		JSONArray array = JSONArray.fromObject( result );
		System.out.println( "driver salary json:" + array );
		return result;
	}
	
	/**
	 * @Description 跳转到司机工资保存之前的页面
	 * @author chengwzh
	 * @date 2015/5/22 15:43
	 */
	@RequestMapping( value = "/intoSave" )
	@ApiIgnore
	public String intoSave( @RequestParam( value = "id" , required = false ) Integer id ,
	        HttpServletRequest request )
	{
		if ( id != null )
		{
			TDriverSalary driverSalary = salaryService.get( id );
			request.setAttribute( "driverSalary" , driverSalary );
		}
		// 获取所有的司机
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		String[] propertyNames = { "vcSubno" , "NEnable" };
		Object[] values = { subno , SystemConstants.SYS_ENABLE };
		List< TDriver > drivers = driverService.findByPropertys( propertyNames , values );
		request.setAttribute( "drivers" , drivers );
		return "sub/subBasicData/driverSalarySave";
	}
	
	/**
	 * @Description 保存或修改司机工资
	 */
	@RequestMapping( "/saveDriverSalary" )
	@ApiIgnore
	public void saveDriverSalary( HttpServletRequest request , TDriverSalary entity ,
	        HttpServletResponse response )
	
	{
		try
		{
			int driverId = entity.getiDriverId();
			TDriver driver = driverService.get( driverId );
			String driverName = driver.getVcDriverName();
			entity.setVcDriverName( driverName );
			// 如果id为空则增加，不为空则修改
			if ( entity.getId() == null )
			{
				HttpSession session = request.getSession();
				TUser user = ( TUser ) session.getAttribute( "user" );
				String addUser = user.getVcUsername();// 获取添加人姓名
				int subId = user.getiArchive();
				String subno = subService.get( subId ).getVcSubno();// 获取分供方编号
				entity.setVcAddUser( addUser );
				entity.setVcSubno( subno );
				String[] nMonths = request.getParameterValues( "nMonths" );
				// 根据司机id获取存在的月份
				// List< Integer > months = salaryService.getMonthsExist(
				// driverId );
				List< TDriverSalary > entities = new ArrayList< TDriverSalary >();
				if ( nMonths.length > 0 )
				{
					for ( String nMonth : nMonths )
					{
						// if ( months != null
						// && months.contains( Integer.parseInt( nMonth ) ) )
						// {
						// AjaxUtil.rendJson( response , false , "该用户" + nMonth
						// + "月的记录已存在" );
						// return;
						// }
						TDriverSalary salary = new TDriverSalary();
						salary.setDtStart( entity.getDtStart() );
						salary.setDtEnd( entity.getDtEnd() );
						salary.setiDriverId( entity.getiDriverId() );
						salary.setnSalary( entity.getnSalary() );
						salary.setVcDriverName( entity.getVcDriverName() );
						salary.setnMonth( Integer.parseInt( nMonth ) );
						salary.setVcAddUser( entity.getVcAddUser() );
						salary.setVcSubno( entity.getVcSubno() );
						entities.add( salary );
					}
				}
				salaryService.saveOrUpdateAll( entities );
			}
			else
			{
				salaryService.saveOrUpdate( entity );
			}
			
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 删除司机工资
	 * @author chengwzh
	 * @date 2015/5/25 17:46
	 */
	@RequestMapping( "/delDriverSalary" )
	@ApiIgnore
	public void deleteDriverSalary( @RequestParam( value = "id" ) int id ,
	        HttpServletResponse response )
	{
		try
		{
			salaryService.delete( id );
			AjaxUtil.rendJson( response , true , "删除成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "删除失败：" + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 */
	@RequestMapping( value = "/getMonthsExist" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取月" , notes = "获取月" , position = 5 )
	public Map< String , Object > getMonthsExist(
	        @ApiParam( "driverId" ) @RequestParam int driverId )
	{
		try
		{
			List< Integer > months = salaryService.getMonthsExist( driverId );
			if ( months == null )
			{
				return AjaxUtil.getMap( false , "该用户记录不存在" );
			}
			else
			{
				return AjaxUtil.getMapByNotException( true , months );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 上传司机图片私有方法
	 * 
	 * @param request
	 * @param files
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void uploadImg( HttpServletRequest request , CommonsMultipartFile[] files ,
	        TDriver driver ) throws FileNotFoundException , IOException
	{
		if ( files == null )
		{
			return;
		}
		for ( int i = 0 ; i < files.length ; i++ )
		{
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				// String source = request.getSession().getServletContext()
				// .getRealPath( "/" );
				String source = pictureService.getPathById( 22 );// 司机图片
				if ( ! source.endsWith( "/" ) )
				{
					source += "/";
				}
				if ( StringUtils.isBlank( source ) )
				{
					System.out.println( "source路径查不到！" );
					return;
				}
				String path = source;
				File pathFile = new File( path );
				if ( ! pathFile.exists() )
				{
					pathFile.mkdirs();
				}
				String jpgPath = new Date().getTime() + files[i].getOriginalFilename();
				// path += new Date().getTime() +
				// files[i].getOriginalFilename();
				path += jpgPath;
				// 拿到输出流，同时重命名上传的文件
				FileOutputStream os = new FileOutputStream( path );
				// 拿到上传文件的输入流
				ByteArrayInputStream in = ( ByteArrayInputStream ) files[i]
				        .getInputStream();
				
				// 以写字节的方式写文件
				int b = 0;
				while ( ( b = in.read() ) != - 1 )
				{
					os.write( b );
				}
				os.flush();
				os.close();
				in.close();
				int finaltime = ( int ) System.currentTimeMillis();
				System.out.println( finaltime - pre );
				String imgPath = driver.getVcImgPath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					driver.setVcImgPath( jpgPath );
					
				}
				else
				{
					// share.setVcImgpath( imgPath + "," + path );
					imgPath = imgPath.substring( imgPath.lastIndexOf( "/" ) + 1 );// 截取图片路径
					String existImgPath = source + imgPath;
					File imgFile = new File( existImgPath );
					if ( imgFile.exists() )
					{
						boolean isDel = imgFile.delete();
						if ( isDel )
						{
							System.out.println( "图片'" + imgPath + "'已删除" );
						}
					}
					driver.setVcImgPath( jpgPath );
				}
			}
		}
	}
	
	/**
	 * 上传身份证图片私有方法
	 * 
	 * @param request
	 * @param files
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void uploadCardImg( HttpServletRequest request ,
	        CommonsMultipartFile[] files , TDriver driver ) throws FileNotFoundException ,
	        IOException
	{
		if ( files == null )
		{
			return;
		}
		for ( int i = 0 ; i < files.length ; i++ )
		{
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				// String source = request.getSession().getServletContext()
				// .getRealPath( "/" );
				String source = pictureService.getPathById( 22 );// 司机图片
				if ( ! source.endsWith( "/" ) )
				{
					source += "/";
				}
				if ( StringUtils.isBlank( source ) )
				{
					System.out.println( "source路径查不到！" );
					return;
				}
				String path = source;
				File pathFile = new File( path );
				if ( ! pathFile.exists() )
				{
					pathFile.mkdirs();
				}
				String jpgPath = new Date().getTime() + files[i].getOriginalFilename();
				// path += new Date().getTime() +
				// files[i].getOriginalFilename();
				path += jpgPath;
				// 拿到输出流，同时重命名上传的文件
				FileOutputStream os = new FileOutputStream( path );
				// 拿到上传文件的输入流
				ByteArrayInputStream in = ( ByteArrayInputStream ) files[i]
				        .getInputStream();
				
				// 以写字节的方式写文件
				int b = 0;
				while ( ( b = in.read() ) != - 1 )
				{
					os.write( b );
				}
				os.flush();
				os.close();
				in.close();
				int finaltime = ( int ) System.currentTimeMillis();
				System.out.println( finaltime - pre );
				String imgPath = driver.getVcCardImgPath();// 获取身份证图片路径
				if ( StringUtils.isBlank( imgPath ) )
				{
					driver.setVcCardImgPath( jpgPath );
				}
				else
				{
					// share.setVcImgpath( imgPath + "," + path );
					imgPath = imgPath.substring( imgPath.lastIndexOf( "/" ) + 1 );// 截取图片路径
					String existImgPath = source + imgPath;
					File imgFile = new File( existImgPath );
					if ( imgFile.exists() )
					{
						boolean isDel = imgFile.delete();
						if ( isDel )
						{
							System.out.println( "图片'" + imgPath + "'已删除" );
						}
					}
					driver.setVcCardImgPath( jpgPath );
				}
			}
		}
	}
	
	/**
	 * 上传行驶证照片私有方法
	 * 
	 * @param request
	 * @param files
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void uploadDriveImg( HttpServletRequest request ,
	        CommonsMultipartFile[] files , TDriver driver ) throws FileNotFoundException ,
	        IOException
	{
		if ( files == null )
		{
			return;
		}
		for ( int i = 0 ; i < files.length ; i++ )
		{
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				// String source = request.getSession().getServletContext()
				// .getRealPath( "/" );
				String source = pictureService.getPathById( 22 );// 司机图片
				if ( ! source.endsWith( "/" ) )
				{
					source += "/";
				}
				if ( StringUtils.isBlank( source ) )
				{
					System.out.println( "source路径查不到！" );
					return;
				}
				String path = source;
				File pathFile = new File( path );
				if ( ! pathFile.exists() )
				{
					pathFile.mkdirs();
				}
				String jpgPath = new Date().getTime() + files[i].getOriginalFilename();
				// path += new Date().getTime() +
				// files[i].getOriginalFilename();
				path += jpgPath;
				// 拿到输出流，同时重命名上传的文件
				FileOutputStream os = new FileOutputStream( path );
				// 拿到上传文件的输入流
				ByteArrayInputStream in = ( ByteArrayInputStream ) files[i]
				        .getInputStream();
				
				// 以写字节的方式写文件
				int b = 0;
				while ( ( b = in.read() ) != - 1 )
				{
					os.write( b );
				}
				os.flush();
				os.close();
				in.close();
				int finaltime = ( int ) System.currentTimeMillis();
				System.out.println( finaltime - pre );
				String imgPath = driver.getVcDriveImgPath();// 获取行驶证照片路径
				if ( StringUtils.isBlank( imgPath ) )
				{
					driver.setVcDriveImgPath( jpgPath );
				}
				else
				{
					// share.setVcImgpath( imgPath + "," + path );
					imgPath = imgPath.substring( imgPath.lastIndexOf( "/" ) + 1 );// 截取图片路径
					String existImgPath = source + imgPath;
					File imgFile = new File( existImgPath );
					if ( imgFile.exists() )
					{
						boolean isDel = imgFile.delete();
						if ( isDel )
						{
							System.out.println( "图片'" + imgPath + "'已删除" );
						}
					}
					driver.setVcDriveImgPath( jpgPath );
				}
			}
		}
	}
	
	/**
	 * 上传车辆图片私有方法
	 * 
	 * @param request
	 * @param files
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void uploadTruckImg( HttpServletRequest request ,
	        CommonsMultipartFile[] files , TDriver driver ) throws FileNotFoundException ,
	        IOException
	{
		if ( files == null )
		{
			return;
		}
		for ( int i = 0 ; i < files.length ; i++ )
		{
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				// String source = request.getSession().getServletContext()
				// .getRealPath( "/" );
				String source = pictureService.getPathById( 22 );// 司机图片
				if ( ! source.endsWith( "/" ) )
				{
					source += "/";
				}
				if ( StringUtils.isBlank( source ) )
				{
					System.out.println( "source路径查不到！" );
					return;
				}
				String path = source;
				File pathFile = new File( path );
				if ( ! pathFile.exists() )
				{
					pathFile.mkdirs();
				}
				String jpgPath = new Date().getTime() + files[i].getOriginalFilename();
				// path += new Date().getTime() +
				// files[i].getOriginalFilename();
				path += jpgPath;
				// 拿到输出流，同时重命名上传的文件
				FileOutputStream os = new FileOutputStream( path );
				// 拿到上传文件的输入流
				ByteArrayInputStream in = ( ByteArrayInputStream ) files[i]
				        .getInputStream();
				
				// 以写字节的方式写文件
				int b = 0;
				while ( ( b = in.read() ) != - 1 )
				{
					os.write( b );
				}
				os.flush();
				os.close();
				in.close();
				int finaltime = ( int ) System.currentTimeMillis();
				System.out.println( finaltime - pre );
				String imgPath = driver.getVcTruckImgPath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					driver.setVcTruckImgPath( jpgPath );
				}
				else
				{
					// share.setVcImgpath( imgPath + "," + path );
					imgPath = imgPath.substring( imgPath.lastIndexOf( "/" ) + 1 );// 截取图片路径
					String existImgPath = source + imgPath;
					File imgFile = new File( existImgPath );
					if ( imgFile.exists() )
					{
						boolean isDel = imgFile.delete();
						if ( isDel )
						{
							System.out.println( "图片'" + imgPath + "'已删除" );
						}
					}
					driver.setVcTruckImgPath( jpgPath );
				}
			}
		}
	}
	
	/**
	 * @Description 获取司机信息（点“我的”获取的页面）
	 */
	@RequestMapping( value = "/getDriverMessage" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取司机信息（点“我的”获取的页面）" , notes = "返回字段:driverId  司机id<br/>"
	        + "vcDriverName 司机名字<br/>" + "vcImgPath 图片路径<br/>" , position = 5 )
	public Map< String , Object > getDriverMessage( HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		int driverId = user.getiArchive();
		int typeId = user.getIArchiveType();// 判断角色是否是司机
		if ( typeId != SystemConstants.SYS_TARCHIVE_DRIVER )
		{
			return AjaxUtil.getMap( false , "你不是司机,无法获取司机信息" );
		}
		try
		{
			TDriver driver = driverService.get( driverId );
			Map< String , Object > result = new HashMap< String , Object >();
			result.put( "driverId" , driverId );
			result.put( "vcDriverName" , driver.getVcDriverName() );
			// result.put( "vcImgPath" , driver.getVcImgPath() );
			String path = staticService.getStringByParame( "driverUrl" );
			if ( StringUtils.isBlank( path ) )
			{
				return AjaxUtil.getMap( false , "图片主目录不存在" );
			}
			if ( ! path.endsWith( "/" ) )
			{
				path += "/";
			}
			path += driver.getVcImgPath();
			result.put( "vcImgPath" , path );// 拼接图片路径
			return AjaxUtil.getMapByNotException( true , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: 司机等级说明
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2015-7-27 下午4:22:08
	 */
	@RequestMapping( value = "/getSpecification" )
	@ApiIgnore
	public String getSpecification( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		return "sub/subuser/registerLicense";
		
	}
}
