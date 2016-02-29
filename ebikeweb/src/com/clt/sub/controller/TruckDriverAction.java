package com.clt.sub.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.clt.common.Geohash;
import com.clt.sub.model.TApplyPic;
import com.clt.sub.model.TAssessArrive;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.model.TShipVin;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TShipline;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.model.TTruckType;
import com.clt.sub.service.IArriveCodeService;
import com.clt.sub.service.IAssessArriveService;
import com.clt.sub.service.IAssessPickService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.IDamagePicService;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.IOrderService;
import com.clt.sub.service.IShipStatusService;
import com.clt.sub.service.IShipVinService;
import com.clt.sub.service.IShipheadService;
import com.clt.sub.service.IShiplineService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverLinkService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TStores;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserRole;
import com.clt.systemmanger.service.IArchiveTypeService;
import com.clt.systemmanger.service.IMsgRecordService;
import com.clt.systemmanger.service.IPictureService;
import com.clt.systemmanger.service.IRoleService;
import com.clt.systemmanger.service.IStoresService;
import com.clt.systemmanger.service.IUserRoleService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.PushUtils;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping( "/truckDriverAction" )
@Api( value = "truck-api" , description = "有关车辆的API：司机确认提货、接单、装车、发运接口" , position = 5 )
public class TruckDriverAction
{
	@Autowired
	IStoresService iStoresService;
	@Autowired
	IDriverService driverService;
	@Autowired
	ITruckDriverService truckDriverService;
	@Autowired
	ITruckDriverLinkService linkService;
	@Autowired
	IArchiveTypeService archiveTypeService;
	@Autowired
	IUserService userService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	IOrderService iOrderService;
	@Autowired
	IShiplineService iShiplineService;
	@Autowired
	IShipheadService iShipheadService;
	@Autowired
	IShipStatusService iShipStatusService;
	@Autowired
	IRoleService roleService;
	@Autowired
	IUserRoleService iUserRoleService;
	@Autowired
	IPictureService pictureService;
	@Autowired
	IOrderService orderService;
	@Autowired
	private IShipVinService vinService;
	@Autowired
	private IAssessPickService pickService;
	@Autowired
	IAssessArriveService iAssessArriveService;
	@Autowired
	IMsgRecordService iMsgRecordService;
	@Autowired
	IDamagePicService idamagePicService;
	
	@Autowired
	private ICustomerSerivce customerSerivce;
	
	@Autowired
	private IArriveCodeService codeService;
	
	/**
	 * @Description: 获取该分供方所有司机车辆信息 前
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/intogetAlltruckDriverBysubno" )
	@ApiIgnore
	public String intogetAlltruckDriverBysubno( HttpServletRequest request )
	{
		
		return "sub/subBasicData/truckDriverInfoList";
		
	}
	
	/**
	 * @Description: 获取该分供方的所有车辆信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/getAllTrucksBysubno" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllTrucksBysubno( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		
		String sql = "select truck.id,truck.vc_car_no,ttype.vc_type_name,truck.n_length,truck.n_width,truck.n_height,truck.n_loan_weight from t_truck_driver truck,t_truck_type ttype where truck.i_truck_type = ttype.id and truck.vc_subno='"
		        + subno
		        + "' and truck.n_enable="
		        + SystemConstants.SYS_ENABLE
		        + " and truck.N_STATUS = 0 ";
		
		String gropsql = "";
		
		String carNo = request.getParameter( "carNo" );
		if ( StringUtils.isNotBlank( carNo ) )
		{
			sql += " and truck.vc_car_no like '%" + carNo + "%' ";
			
		}
		String driverName = request.getParameter( "driverName" );
		String driverTel = request.getParameter( "driverTel" );
		
		if ( StringUtils.isNotBlank( driverName ) || StringUtils.isNotBlank( driverTel ) )
		{
			sql = "select truck.id,truck.vc_car_no,ttype.vc_type_name,truck.n_length,truck.n_width,truck.n_height,truck.n_loan_weight "
			        + " from t_truck_driver truck,t_truck_type ttype,t_driver driver,t_truck_driver_link lik "
			        + " where truck.i_truck_type = ttype.id and lik.i_driver=driver.id and lik.i_truck=truck.id "
			        + " and truck.vc_subno='"
			        + subno
			        + "' and truck.n_enable= "
			        + SystemConstants.SYS_ENABLE;
			
			gropsql += "  group by truck.id,truck.vc_car_no,ttype.vc_type_name,truck.n_length,truck.n_width,truck.n_height,truck.n_loan_weight  ";
			
			if ( StringUtils.isNotBlank( driverName ) )
			{
				sql += " and driver.VC_DRIVER_NAME like '%" + driverName + "%' ";
				gropsql += " ,driver.VC_DRIVER_NAME ";
			}
			
			if ( StringUtils.isNotBlank( driverTel ) )
			{
				sql += " and driver.VC_DRIVER_TEL like '%" + driverTel + "%' ";
				gropsql += " ,driver.VC_DRIVER_TEL ";
			}
			gropsql += " order by truck.id desc ";
		}
		else
		{
			sql += " order by truck.id desc ";
		}
		
		Map< String , Object > resuMap = truckDriverService.getSpringSQL( sql + gropsql ,
		        page );
		if ( resuMap.get( "rows" ) != null )
		{
			JSONArray jsarr = JSONArray.fromObject( resuMap );
			
			System.out.println( " getAllDriverBysubno jsarr  " + jsarr.toString() );
		}
		else
		{
			System.out.println( "无数据  " );
		}
		return resuMap;
		
	}
	
	/**
	 * @Description: 获取该分供方所有司机车辆信息 前
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveBefore" )
	@ApiIgnore
	public String saveBefore( HttpServletRequest request )
	{
		
		List< TTruckType > truckTypeList = truckDriverService.getAllTruckType();
		request.setAttribute( "truckTypeList" , truckTypeList );
		return "sub/subBasicData/saveTruckDriver";
		
	}
	
	/**
	 * @Description :修改车辆编号和车辆类型
	 * @author chengwzh
	 * @create date 2015/5/8 17:00
	 */
	@RequestMapping( "/updateCardNoAndCarStyle" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > updateCardNoAndCarStyle( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		String vcCarNo = request.getParameter( "vcCarNo" );
		if ( request.getParameter( "ITuckType" ) == null
		        || request.getParameter( "truckID" ) == null )
		{
			return AjaxUtil.getMap( false , "更新失败!" );
		}
		try
		{
			// System.out.println( "ITuckType:" + request.getParameter(
			// "ITuckType" ) );
			// System.out.println( "truckID:" + request.getParameter( "truckID"
			// ) );
			int ITuckType = Integer.parseInt( request.getParameter( "ITuckType" ) );
			int truckID = Integer.parseInt( request.getParameter( "truckID" ) );
			TTruckDriver truck = truckDriverService.get( truckID );
			truck.setVcCarNo( vcCarNo );
			truck.setITruckTypeID( ITuckType );
			truckDriverService.update( truck );
			return AjaxUtil.getMap( true , "更新成功!" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 跳转到司机信息保存前 操作
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveDriverBefore" )
	@ApiIgnore
	public String saveDriverBefore( HttpServletRequest request )
	{
		int driID = Integer.parseInt( request.getParameter( "driID" ) );
		int driverType = Integer.parseInt( request.getParameter( "driverType" ) );
		System.out.println( "   saveDriverBefore   " + driID + " driverType  "
		        + driverType );
		request.setAttribute( "driID" , driID );
		request.setAttribute( "driverType" , driverType );
		System.out.println( "123" );
		return "sub/subBasicData/saveDriver";
		
	}
	
	/**
	 * @Description: 批量增加分供方的司机车辆信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveAndsaveUsers" )
	@ApiIgnore
	public String saveAndsaveUsers( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser us = ( TUser ) session.getAttribute( "user" );
		
		int params = Integer.parseInt( request.getParameter( "params" ) );
		String[] carnos = request.getParameterValues( "carno" );
		String[] drivernames = request.getParameterValues( "drivername" );
		String[] drivertels = request.getParameterValues( "drivertel" );
		
		truckDriverService.saveAndsaveUsers( carnos , drivernames , drivertels , us ,
		        params );
		
		return "index";
		
	}
	
	/**
	 * @Description: 单独开通分供方的司机账号
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveAndsaveUser" )
	@ApiIgnore
	public String saveAndsaveUser( HttpServletRequest request )
	{
		int driverID = Integer.parseInt( request.getParameter( "driverID" ) );
		truckDriverService.saveAndsaveUser( driverID );
		
		return "index";
		
	}
	
	/**
	 * @Description: 保存车辆信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveTruckDriver" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > saveTruckDriver( HttpServletRequest request ,
	        HttpServletResponse resp , TTruckDriver truck )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		truck.setVcSubno( subno );
		
		try
		{
			truckDriverService.save( truck );
			return AjaxUtil.getMap( true , "保存成功;" + truck.getId() );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 获取该分供方所有司机车辆信息 前
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/intogetAllDriversBysubno" )
	@ApiIgnore
	public String intogetAllDriversBysubno( HttpServletRequest request )
	{
		
		return "sub/subBasicData/driverInfoList";
		
	}
	
	/**
	 * @Description: 获取该分供方的所有司机信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/getAllDriversBysubno" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllDriversBysubno( HttpServletRequest request )
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
		String driverTel = request.getParameter( "driverTel" );
		if ( StringUtils.isNotBlank( driverTel ) )
		{
			hql.addLike( "vcDriverTel" , driverTel );
			
		}
		
		hql.addOrderBy( "id" , "desc" );
		Map< String , Object > resuMap = truckDriverService.findAllByHqlHelp( hql );
		if ( resuMap.get( "rows" ) != null )
		{
			JSONArray jsarr = JSONArray.fromObject( resuMap );
			
			System.out.println( " getAllDriversBysubno jsarr  " + jsarr.toString() );
		}
		else
		{
			System.out.println( "无数据  " );
		}
		return resuMap;
		
	}
	
	/**
	 * @Description: 保存司机信息及车辆和司机的映射
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveDriverAndLink" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > saveDriverAndLink( HttpServletRequest request ,
	        TDriver driver , HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		
		int driID = Integer.parseInt( request.getParameter( "driID" ) );
		int driverType = Integer.parseInt( request.getParameter( "driType" ) );
		
		driver.setNEnable( SystemConstants.SYS_ENABLE );
		driver.setVcSubno( subno );
		driver.setiUserId( user.getId() );
		TTruckDriverLlink drilink = new TTruckDriverLlink();
		
		drilink.setITruckID( driID );
		drilink.setNPositionType( driverType );
		
		try
		{
			truckDriverService.saveDriver( driver );
			drilink.setIDriverID( driver.getId() );
			truckDriverService.saveTruckDriverLlink( drilink );
			return AjaxUtil.getMap( true , "保存成功;" + driID );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
			
		}
	}
	
	/**
	 * @Description: 获取该分供方 可用的司机信息 即该司机没被其他车辆关联的 之前的跳转
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/intosaveDriver" )
	@ApiIgnore
	public String intosaveDriver( HttpServletRequest request )
	{
		
		String driverID = request.getParameter( "driverID" );
		String paramType = "add";
		if ( StringUtils.isNotBlank( driverID ) )
		{
			paramType = "update";
			TDriver driver = truckDriverService.getDriver( Integer.parseInt( driverID ) );
			request.setAttribute( "driver" , driver );
			
		}
		request.setAttribute( "paramType" , paramType );
		return "sub/subBasicData/saveDriver";
		
	}
	
	/**
	 * @Description: 保存司机信息 PC
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( value = "/saveDriver" , method = RequestMethod.POST )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > saveDriver( HttpServletRequest request ,
	        TDriver driver , String paramType , HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		try
		{
			String subno = subService.get( user.getiArchive() ).getVcSubno();
			
			driver.setNEnable( SystemConstants.SYS_ENABLE );
			driver.setVcSubno( subno );
			driver.setiUserId( user.getId() );
			// 验证司机号码是否被注册过
			boolean flag = true;
			String[] propertyNames = { "NEnable" , "vcDriverTel" };
			Object[] values = { SystemConstants.SYS_ENABLE , driver.getVcDriverTel() };
			List< TDriver > tDrivers = driverService.findByPropertys( propertyNames ,
			        values );
			if ( tDrivers != null && tDrivers.size() > 0 )
			{
				flag = false;
				return AjaxUtil.getMap( false , "手机号码【" + driver.getVcDriverTel()
				        + "】被注册了！" );
			}
			if ( flag )
			{
				if ( paramType.equalsIgnoreCase( "add" ) )
				{
					String message = truckDriverService.checkIfExiste( driver , subno );
					if ( message.equalsIgnoreCase( "success" ) )
					{
						truckDriverService.saveOrUpdateTDriver( driver );
						TUser tuser = new TUser();
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
						userService.saveUpdateUser( tuser );
						
						// 找出已创建的司机角色
						List< TRole > tRoles = roleService.findDriverRole();
						if ( tRoles.size() > 0 )
						{
							TUserRole tUserRole = new TUserRole();
							tUserRole.setIEnable( SystemConstants.SYS_ENABLE );
							tUserRole.setIRole( tRoles.get( 0 ).getId() );
							tUserRole.setIUser( tuser.getId() );
							iUserRoleService.save( tUserRole );
							
						}
						else
						{
							return AjaxUtil.getMap( false , "司机角色未维护!" );
						}
						return AjaxUtil.getMap( true , "保存成功!" );
					}
					else
					{
						return AjaxUtil.getMap( false , "失败！原因：" + message );
					}
					
				}
				
			}
			else if ( paramType.equalsIgnoreCase( "update" ) )
			{
				TDriver tDriver_before = driverService.get( driver.getId() );
				// 如果手机没有修改则放行不作验证
				if ( tDriver_before.getVcDriverTel().equals( driver.getVcDriverTel() ) )
				{
					driverService.updateCleanBefore( driver );
					return AjaxUtil.getMap( true , "保存成功!" );
				}
				else
				{
					String message = truckDriverService.checkIfExiste( driver , subno );
					if ( message.equalsIgnoreCase( "success" ) )
					{
						driverService.updateCleanBefore( driver );
						return AjaxUtil.getMap( true , "保存成功!" );
					}
					else
					{
						return AjaxUtil.getMap( false , "失败！原因：" + message );
					}
				}
				
			}
			return AjaxUtil.getMap( false , "缺少paramType参数，系统未处理！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
			
		}
		
	}
	
	/**
	 * @Description: 获取车辆的司机信息 主驾 副驾 根据车辆ID
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/getDriverInfoByTruckID" )
	@ApiIgnore
	public String getDriversByTruckID( HttpServletRequest request )
	{
		int truckID = Integer.parseInt( request.getParameter( "truckID" ) );
		// 查询车辆 主驾
		TDriver mainTDriver = truckDriverService.getMainDriver( truckID );
		TDriver copilotDriver = truckDriverService.getcopilotDriver( truckID );
		request.setAttribute( "mainDriver" , mainTDriver );
		request.setAttribute( "copilotDriver" , copilotDriver );
		
		return "sub/subBasicData/showDriverInfo";
		
	}
	
	/**
	 * @Description: 获取该分供方 可用的司机信息 即该司机没被其他车辆关联的 之前的跳转
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/intogetNoTruckDriversBysubno" )
	@ApiIgnore
	public String intogetNoTruckDriversBysubno( HttpServletRequest request )
	{
		
		int truckID = Integer.parseInt( request.getParameter( "truckID" ) );
		TTruckDriver truck = truckDriverService.get( truckID );
		TTruckType ttype = truckDriverService.getTTruckType( truck.getITruckTypeID() );
		// 查询车辆的主驾 副驾信息
		TDriver mainTDriver = truckDriverService.getMainDriver( truckID );
		TDriver copilotDriver = truckDriverService.getcopilotDriver( truckID );
		request.setAttribute( "mainDriver" , mainTDriver );
		request.setAttribute( "copilotDriver" , copilotDriver );
		
		request.setAttribute( "truck" , truck );
		request.setAttribute( "ttype" , ttype );
		// 获取车辆类型列表
		List< TTruckType > truckTypeList = truckDriverService.getAllTruckType();
		request.setAttribute( "truckTypeList" , truckTypeList );
		return "sub/subBasicData/updateDriverInfo";
		
	}
	
	/**
	 * @Description: 获取该分供方 可用的司机信息 即该司机没被其他车辆关联的
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( value = "/getNoTruckDriversBysubno" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getNoTruckDriversBysubno( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		String subno = subService.get( user.getiArchive() ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		// String visit = request.getParameter( SystemConstants.APP_VISIT_PARME
		// );
		
		String sql = " select driver.id,driver.vc_driver_name,driver.vc_driver_tel,driver.vc_card from t_driver driver where driver.n_enable="
		        + SystemConstants.SYS_ENABLE
		        + " and driver.vc_subno='"
		        + subno
		        + "' and  driver.id in ( "
		        + "		 select dri.id from t_driver dri left join t_truck_driver_link lik on lik.i_driver=dri.id where lik.id is null )";
		
		sql += " order by driver.id desc ";
		
		Map< String , Object > resuMap = truckDriverService.getSpringSQL( sql , page );
		if ( resuMap.get( "rows" ) != null )
		{
			JSONArray jsarr = JSONArray.fromObject( resuMap );
			
			System.out.println( " 获取该分供方 可用的司机信息     " + jsarr.toString() );
		}
		else
		{
			System.out.println( "无数据  " );
		}
		return resuMap;
		
	}
	
	/**
	 * @Description 获取可用的司机
	 */
	@RequestMapping( value = "/getEnableDriver" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取可用的司机信息 即该司机没被其他车辆关联的" , notes = "获取可用的司机信息 即该司机没被其他车辆关联的" , position = 5 )
	public Map< String , Object > getEnableDriver( HttpServletRequest request )
	{
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		Map< String , Object > result = getNoTruckDriversBysubno( request );
		List< Map< String , Object >> rows = ( List< Map< String , Object >> ) result
		        .get( "rows" );
		List< Map< String , Object >> drivers = new ArrayList< Map< String , Object >>();
		for ( Map< String , Object > map : rows )
		{
			Map< String , Object > driver = new HashMap< String , Object >();
			driver.put( "id" , map.get( "ID" ) );
			driver.put( "driverName" , map.get( "VC_DRIVER_NAME" ) );
			drivers.add( driver );
		}
		result.put( "rows" , drivers );
		return AjaxUtil.getMapByResult( visit , result );
	}
	
	/**
	 * @Description: 修改车辆的主驾和副驾司机信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/updateMainDeiver" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > updateMainDeiver( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		
		try
		{
			int truckID = Integer.parseInt( request.getParameter( "truckID" ) );
			TTruckDriver truckDriver = truckDriverService.get( truckID );
			Integer mainDriverID = null;
			if ( request.getParameter( "mainDriverID" ) != null
			        && request.getParameter( "mainDriverID" ) != "" )
			{
				mainDriverID = Integer.parseInt( request.getParameter( "mainDriverID" ) );
			}
			Integer copilotDriverID = null;
			if ( request.getParameter( "copilotDriverID" ) != null
			        && request.getParameter( "copilotDriverID" ) != "" )
			{
				copilotDriverID = Integer.parseInt( request
				        .getParameter( "copilotDriverID" ) );
			}
			truckDriverService
			        .updateMainDeiver( truckID , mainDriverID , copilotDriverID );
			// 消息推送
			String[] properties = { "IArchiveType" , "iArchive" , "NEnable" };
			Object[] mainValues = { SystemConstants.SYS_TARCHIVE_DRIVER , mainDriverID ,
			        SystemConstants.SYS_ENABLE };// 主驾司机的参数
			Object[] copilotValues = { SystemConstants.SYS_TARCHIVE_DRIVER ,
			        copilotDriverID , SystemConstants.SYS_ENABLE };// 副驾司机的参数
			List< TUser > mainUsers = userService.findByProperties( properties ,
			        mainValues );// size应为0 主驾司机
			List< TUser > copilotUsers = userService.findByProperties( properties ,
			        copilotValues );// size应为0 副驾司机
			Map< String , String > mainMap = new HashMap< String , String >();
			Map< String , String > copilotMap = new HashMap< String , String >();
			// 主驾司机消息推送
			mainMap.put( "driverId" , mainDriverID + "" );
			if ( mainUsers.size() > 0 )
			{
				PushUtils pUtils1 = new PushUtils( "拖车主驾确认" , mainUsers.get( 0 )
				        .getVcUsername() + "你现在是" + truckDriver.getVcCarNo() + "主驾！" ,
				        mainUsers , "com.unlcn.driver.mine.MyInfoDetailActivity" ,
				        mainMap );
				pUtils1.run();
			}
			if ( copilotUsers.size() > 0 )
			{
				// 副驾司机消息推送
				copilotMap.put( "driverId" , copilotDriverID + "" );
				PushUtils pUtils2 = new PushUtils( "拖车副驾确认" , copilotUsers.get( 0 )
				        .getVcUsername() + "你现在是" + truckDriver.getVcCarNo() + "副驾！" ,
				        mainUsers , "com.unlcn.driver.mine.MyInfoDetailActivity" ,
				        copilotMap );
				pUtils2.run();
			}
			
			return AjaxUtil.getMap( true , "保存成功！" );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
			
		}
		
	}
	
	/**
	 * 
	 * @Description: 取消车辆和司机的关联
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-10-8 下午3:59:48
	 */
	@RequestMapping( "/delTruckDriverLink" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > delTruckDriverLink( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		
		try
		{
			int truckID = Integer.parseInt( request.getParameter( "truckID" ) );
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			truckDriverService.delTruckDriverLink( truckID , user.getId() );
			return AjaxUtil.getMap( true , "保存成功！" );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
			
		}
	}
	
	/**
	 * 
	 * @Description: 删除司机信息
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-10-8 下午3:59:48
	 */
	@RequestMapping( "/delTruckDriver" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > delTruckDriver( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		try
		{
			int driverID = Integer.parseInt( request.getParameter( "driverID" ) );
			truckDriverService.delTruckDriver( driverID );
			return AjaxUtil.getMap( true , "操作成功！" );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
			
		}
	}
	
	/**
	 * @Description :删除车辆信息
	 * @author chengwzh
	 * @date 2015/5/7 14:10
	 */
	@RequestMapping( "/delTruck" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > delTruck( HttpServletRequest request ,
	        HttpServletResponse resp )
	{
		// System.out.println( "truckID:" + request.getParameter( "truckID" ) );
		
		try
		{
			int truckID = Integer.parseInt( request.getParameter( "truckID" ) );
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			int userId = user.getId();
			truckDriverService.delTruck( truckID , userId );
			return AjaxUtil.getMap( true , "操作成功！" );
			
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
			
		}
	}
	
	/**
	 * 
	 * @Description: TODO(手机端批量新增)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @throws JSONException
	 * @create_date 2015-4-22 下午4:20:29
	 */
	@ApiOperation( value = "手机批量新增司机信息接口" , notes = "手机批量新增司机信息接口" , position = 5 )
	@RequestMapping( value = "/saveDriverByPhone" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > saveDriverByPhone(
	        HttpServletRequest request ,
	        @ApiParam( value = "司机信息（JSON格式,如:[{'vcDriverName':'liuwu2','vcTel':'15877556522'},{'vcDriverName':'node','vcTel':'15877556522'}],vcDriverName(司机姓名)，vcTel(手机号码)）" , required = true ) @RequestParam( "strJson" ) String strJson ,
	        HttpServletResponse response ) throws JSONException
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		if ( strJson != "" )
		{
			String subno = subService.get( user.getiArchive() ).getVcSubno();
			org.json.JSONArray jsonArray;
			try
			{
				jsonArray = new org.json.JSONArray( strJson );
				int size = jsonArray.length();
				List< TDriver > tDrivers = new ArrayList< TDriver >();
				for ( int i = 0 ; i < size ; i++ )
				{
					org.json.JSONObject obj = jsonArray.getJSONObject( i );
					String vcDriver = ( String ) obj.get( "vcDriverName" );
					String vcTel = ( String ) obj.get( "vcTel" );
					TDriver tDriver = new TDriver();
					tDriver.setVcDriverName( vcDriver.trim() );
					tDriver.setVcDriverTel( vcTel );
					tDriver.setVcSubno( subno );
					tDriver.setiUserId( user.getId() );
					tDrivers.add( tDriver );
				}
				try
				{
					boolean flag = true;
					List< TDriver > newDrivers = new ArrayList< TDriver >();// 验证通过后司机集合
					// 查检同一名字、同一手机号是否存在相同记录
					for ( TDriver tDriver : tDrivers )
					{
						String sql = "select count(1) from t_driver where "
						        + " VC_DRIVER_TEL = '" + tDriver.getVcDriverTel()
						        + "' and N_ENABLE = 0";
						String message = truckDriverService.findIfExistSame( sql );
						if ( message.equalsIgnoreCase( "success" ) )
						{
							newDrivers.add( tDriver );
						}
						else
						{
							flag = false;
							return AjaxUtil.getMap( false ,
							        "手机号码【" + tDriver.getVcDriverTel() + "】已被注册！" );
						}
					}
					if ( flag )
					{
						for ( TDriver tDriver : newDrivers )
						{
							driverService.save( tDriver );
							/**
							 * 将司机存入用户表
							 */
							TUser tuser = new TUser();
							tuser.setNEnable( 0 );
							tuser.setDtAddtime( new Date() );
							tuser.setIArchiveType( SystemConstants.SYS_TARCHIVE_DRIVER );
							tuser.setiArchive( tDriver.getId() );
							tuser.setVcUsername( tDriver.getVcDriverName() );
							// 司机的手机号为登陆用户名 密码为123456 MD5加密
							tuser.setVcAccount( tDriver.getVcDriverTel() );
							org.springframework.security.authentication.encoding.Md5PasswordEncoder t = new Md5PasswordEncoder();
							String tt = t
							        .encodePassword( "123456" , tuser.getVcAccount() );
							tuser.setVcPassword( tt );
							userService.saveUpdateUser( tuser );
							// 找出已创建的司机角色
							List< TRole > tRoles = roleService.findDriverRole();
							if ( tRoles.size() > 0 )
							{
								TUserRole tUserRole = new TUserRole();
								tUserRole.setIEnable( SystemConstants.SYS_ENABLE );
								tUserRole.setIRole( tRoles.get( 0 ).getId() );
								tUserRole.setIUser( tuser.getId() );
								iUserRoleService.save( tUserRole );
								
							}
						}
						return AjaxUtil.getMap( true , "注册成功！" );
					}
					
				}
				catch ( Exception e )
				{
					return AjaxUtil.getMapByException( e );
				}
			}
			catch ( Exception e )
			{
				// AjaxUtil.rendJson( response , false , "传入的参数不是JSON格式！" +
				// e.getMessage() );
				e.printStackTrace();
				return AjaxUtil.getMap( false , "传入的参数不是JSON格式！" );
			}
			return AjaxUtil.getMap( true , "操作成功！" );
		}
		else
		{
			return AjaxUtil.getMap( false , "服务器未获取参数！" );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(手机端司机角色获取当前司机的订单（全部、未回单、已回单）)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-5 下午3:32:44
	 */
	@ApiOperation( value = "手机端司机角色获取订单（A:所有;N:未回单;Y:已回单;J:待接单；F:待发运；D:待抵达）接口" , notes = "HEADID:发运指令id()<br/>"
	        + "VC_SHIPNO:发运指令号<br/>LINEID:指令明细表id<br/>"
	        + "VC_CAR_NAME:商品车车名<br/>"
	        + "N_QTY :已回单数量<br/>"
	        + "N_SHIP_QTY:装运数量<br/>"
	        + "VC_START_CITY:起运地城市<br/>"
	        + "VC_DEST_CITY:目的地城市<br/>"
	        + "I_ORDER_ID:订单id<br/>"
	        + "N_NOT_QTY:未发运数量<br/>"
	        + "N_ARRIVEQTY:未回单数量（抵达数量）"
	        + "VC_CUST_ORDERNO:客户订单号(客户自己录入)<br/>VC_ORDERNO:订单号<br/>"
	        + "待接单和待发运列表按指令号进行分组显示" , position = 5 )
	@RequestMapping( value = "/getDriverOrder" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDriverOrder(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "订单类型(A:所有;N:未回单;Y:已回单;J:待接单；F:待发运；D:待抵达)" , required = true ) @RequestParam( value = "orderType" , required = true ) String orderType ,
	        @ApiParam( value = "起始地城市" , required = false ) @RequestParam( value = "vcStartCity" , required = false ) String vcStartCity ,
	        @ApiParam( value = "目的地城市" , required = false ) @RequestParam( value = "vcEndCity" , required = false ) String vcEndCity )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		Page page = ServiceUtil.getcurrPage( request );
		Map< String , Object > orderMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		int subId = user.getiArchive();
		int typeId = user.getIArchiveType();
		// 找出司机所属的分供方编号
		String subno = "";
		
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )
		{
			subno = driverService.get( subId ).getVcSubno();
		}
		else
		{
			subno = subService.get( subId ).getVcSubno();// 获取分供方编号
		}
		
		String[] propertyNames = { "vcDriverTel" , " NEnable" };
		Object[] values = { user.getVcAccount() , SystemConstants.SYS_ENABLE };
		List< TDriver > tDrivers = driverService.findByPropertys( propertyNames , values );
		// 通过用户（司机）的帐号即手机号找到司机信息，再通过司机找到对应的卡车
		if ( tDrivers.size() == 0 )
		{
			return AjaxUtil.getMapByNotException( false , null );
		}
		TDriver tDriver = tDrivers.get( 0 );
		int driverId = tDriver.getId();
		/*String[] propertyNames2 = { "IDriverID" , " NEnable" };
		Object[] values2 = { tDriver.getId() , SystemConstants.SYS_ENABLE };
		List< TTruckDriverLlink > truckDriverLlinks = linkService.findByPropertys(
		        propertyNames2 , values2 );
		TTruckDriverLlink tLlink = truckDriverLlinks.get( 0 );*/
		
		String sql = "select head.vc_shipno ,head.id as headId, od.vc_car_name ,od.VC_CUST_ORDERNO,od.VC_ORDERNO,line.id as lineId,"
		        + "nvl(line.n_qty,0) as n_qty, line.n_ship_qty, nvl(line.N_ARRIVEQTY,0) as N_ARRIVEQTY,line.vc_start_city, line.vc_dest_city,line.i_order_id,"
		        + "nvl(line.n_ship_qty-line.n_qty,0)  as n_not_Qty";
		if ( orderType.equalsIgnoreCase( "A" ) )// 所有--历史任务
		{
			sql += " from t_Shipline line, t_Shiphead head, t_Order  od  where (line.N_CURRENT_STATUS = 18 or line.N_CURRENT_STATUS = 20 ) and head.id = line.i_shiphead "
			        + "and line.i_order_id = od.id and head.vc_subno='"
			        + subno
			        + "'  and  head.vc_driverid like '%" + driverId + "%'";
		}
		if ( orderType.equalsIgnoreCase( "N" ) ) // 未回单
		{
			sql += " from t_Shipline line, t_Shiphead head, t_Order  od  where head.id = line.i_shiphead and line.i_order_id = od.id and head.vc_subno='"
			        + subno
			        + "' and line.N_CURRENT_STATUS = 18 and head.vc_driverid like  '%"
			        + driverId + "%'";
		}
		if ( orderType.equalsIgnoreCase( "Y" ) ) // 已回单
		{
			sql += " from t_Shipline line, t_Shiphead head, t_Order  od  where head.id = line.i_shiphead and line.i_order_id = od.id and head.vc_subno='"
			        + subno
			        + "' and line.N_CURRENT_STATUS = 20 and head.vc_driverid like  '%"
			        + driverId + "%'";
		}
		if ( orderType.equalsIgnoreCase( "J" ) )// 待接单
		{
			sql += " from t_Shipline line, t_Shiphead head, t_Order  od  where head.id = line.i_shiphead and line.i_order_id = od.id and head.vc_subno='"
			        + subno
			        + "' and line.N_CURRENT_STATUS = 0 and head.vc_driverid like  '%"
			        + driverId + "%'";
		}
		if ( orderType.equalsIgnoreCase( "F" ) )// 待发运
		{
			sql += " from t_Shipline line, t_Shiphead head, t_Order  od  where head.id = line.i_shiphead and line.i_order_id = od.id and head.vc_subno='"
			        + subno
			        + "' and line.N_CURRENT_STATUS >=3 and  line.N_CURRENT_STATUS <= 10 and head.vc_driverid like  '%"
			        + driverId + "%'";
		}
		if ( orderType.equalsIgnoreCase( "D" ) )// 待抵达
		{
			sql += " from t_Shipline line, t_Shiphead head, t_Order  od  where head.id = line.i_shiphead and line.i_order_id = od.id and head.vc_subno='"
			        + subno
			        + "' and line.N_CURRENT_STATUS =15  and head.vc_driverid like  '%"
			        + driverId + "%'";
		}
		
		if ( StringUtils.isNotBlank( vcStartCity ) )
		{
			sql += " and line.vc_start_city like '%" + vcStartCity + "%' ";
		}
		if ( StringUtils.isNotBlank( vcEndCity ) )
		{
			sql += "  and line.vc_dest_city like '%" + vcEndCity + "%' ";
		}
		sql += "and line.N_ENABLE = 0 order by head.id desc";
		
		System.out.println( "sql = " + sql );
		orderMap = truckDriverService.getSpringSQL( sql , page );
		// 按指令headId进行分组显示
		
		List< Map< String , Object >> arlist = ( List< Map< String , Object >> ) orderMap
		        .get( "rows" );
		if ( CollectionUtils.isEmpty( arlist ) )
		{
			return AjaxUtil.getMap( false , "没有相关数据！" );
		}
		String custNo = "";
		Map< String , Object > rMap = null;
		Map< String , Object > rMap2 = null;
		List< Map< String , Object > > resultList = new ArrayList< Map< String , Object > >();
		List itemList = null;
		List itemList2 = null;
		for ( Map< String , Object > map : arlist )
		{
			System.out.println( "***=" + map.get( "VC_SHIPNO" ) );
			if ( null != map.get( "VC_SHIPNO" ) )
			{
				
				if ( ! custNo.equals( map.get( "VC_SHIPNO" ) ) )
				{
					custNo = ( String ) map.get( "VC_SHIPNO" );
					// map.remove( "VC_CUST_ORDERNO" );
					/*	
						if ( null != rMap )
						{
							rMap.put( "item" , itemList );
							resultList.add( rMap );
						}*/
					
					rMap = new HashMap< String , Object >();
					
					itemList = new ArrayList();
					
					rMap.put( "vcShipNo" , custNo );
					itemList.add( map );
					if ( null != rMap )
					{
						rMap.put( "item" , itemList );
						resultList.add( rMap );
					}
				}
				else
				{
					// map.remove( "VC_CUST_ORDERNO" );
					itemList.add( map );
				}
			}
			else
			{
				
				if ( itemList2 == null )
				{
					rMap2 = new HashMap< String , Object >();
					itemList2 = new ArrayList();
				}
				
				rMap2.put( "vcShipNo" , "指令号为空" );
				
				itemList2.add( map );
				rMap2.put( "item" , itemList2 );
				
			}
			
		}
		if ( rMap2 != null )
		{
			resultList.add( rMap2 );
		}
		
		if ( CollectionUtils.isNotEmpty( resultList ) )
		{
			
			return AjaxUtil.getMapByNotException( true , resultList );
		}
		else
		{
			return AjaxUtil.getMap( false , "没有数据" );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-23 下午4:25:49
	 */
	@ApiOperation( value = "手机端司机角色获取当前司机所有指令接口(司机帐号:15022223333)" , notes = "根据指令状态显示不同的按钮<br/>"
	        + "HEADID:指令ID<BR/>VC_SHIPNO:指令号<br/>N_CURRENT_STATUS:指令状态：0：配载；3：接单;4：提货; 5 入场 10 装车 15 发运 18:运抵 20 回单;30:结算<br/>" , position = 5 )
	@RequestMapping( value = "/getOrderByDriver" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getOrderByDriver( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		Page page = ServiceUtil.getcurrPage( request );
		Map< String , Object > orderMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		int subId = user.getiArchive();
		int typeId = user.getIArchiveType();
		// 找出司机所属的分供方编号
		String subno = "";
		
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )
		{
			subno = driverService.get( subId ).getVcSubno();
		}
		else
		{
			subno = subService.get( subId ).getVcSubno();// 获取分供方编号
		}
		
		String[] propertyNames = { "vcDriverTel" , " NEnable" };
		Object[] values = { user.getVcAccount() , SystemConstants.SYS_ENABLE };
		List< TDriver > tDrivers = driverService.findByPropertys( propertyNames , values );
		// 通过用户（司机）的帐号即手机号找到司机信息，再通过司机找到对应的卡车
		if ( tDrivers.size() == 0 )
		{
			return AjaxUtil.getMapByNotException( false , null );
		}
		TDriver tDriver = tDrivers.get( 0 );
		String[] propertyNames2 = { "IDriverID" , " NEnable" };
		Object[] values2 = { tDriver.getId() , SystemConstants.SYS_ENABLE };
		List< TTruckDriverLlink > truckDriverLlinks = linkService.findByPropertys(
		        propertyNames2 , values2 );
		TTruckDriverLlink tLlink = truckDriverLlinks.get( 0 );
		String sql = "select head.ID as headID,head.VC_SHIPNO,head.N_CURRENT_STATUS "
		        + "from T_SHIPHEAD head where head.I_TRUCK_ID = " + tLlink.getITruckID()
		        + " and head.VC_SUBNO = '" + subno + "'";
		if ( truckDriverLlinks.size() > 1 )
		{
			for ( int i = 1 ; i < truckDriverLlinks.size() ; i++ )
			{
				sql += " or head.I_TRUCK_ID =" + truckDriverLlinks.get( i ).getITruckID();
			}
			
		}
		sql += " order by head.id desc";
		System.out.println( "sql = " + sql );
		try
		{
			orderMap = truckDriverService.getSpringSQL( sql , page );
			return AjaxUtil.getMapByResult( visit , orderMap );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(根据订单Id获取订单详情)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-5 下午5:02:36
	 */
	@ApiOperation( value = "根据订单Id获取订单详情" , notes = "private Integer id;// 订单ID<br/>"
	        + "private Integer ICarStyle;// 商品车ID<br/>"
	        + "private String vcCarName;// 商品车车名<br/>"
	        + "private Integer IUser;// 录入用户id<br/>"
	        + "private String vcLoadAddress;// 装货地址<br/>"
	        + "private String vcLoadContact;// 联系人<br/>"
	        + "private String vcLoadTel;// 联系电话<br/>"
	        + "private Date dtShip;// 要求发运日<br/>" + "private Date dtArrive;// 要求到货日<br/>"
	        + "private Integer IStartId;// 起运城市id<br/>"
	        + "private Integer IEndId;// 目的城市id<br/>"
	        + "private String vcStartCity;// 起运城市<br/>"
	        + "private String vcDestCity;// 目的城市<br/>"
	        + "private String vcReceiveAddress;// 收货地址<br/>"
	        + "private String vcReceiveContact;// 收货联系人<br/>"
	        + "private String vcReceiveTel;// 收货人电话<br/>"
	        + "private Integer NTotalCar;// 数量<br/>"
	        + "private Integer NShipedQty;// 发运数量<br/>"
	        + "private Integer NEnable;// 状态（0为有效，1为无效<br/>"
	        + "private String vcSubno;// 所属分供方编号<br/>"
	        + "private String vcOrderno;// 订单号<br/>"
	        + "private Integer NPayType;// 支付方式(0 现金 1客户)<br/>"
	        + "private Float NTotalPrice;// 订单总价<br/>"
	        + "private Integer ICustomerId;// 客户表ID<br/>"
	        + "private String vcCustOrderNo;// 客户订单号(客户自己录入)<br/>"
	        + "private Date dtCreateDate;// 创建时间<br/>"
	        + "private Float NCost;// 应收单价(如果是现金支付此字段为空)<br/>"
	        + "private Integer nLoad;// 是否配载（0：未配载[默认]；1已配载）<br/>"
	        + "private String vcLong;// 收货地址经度<br/>"
	        + "private String vcLat; // 收货地址纬度<br/>"
	        + "private String vcLongStart;// 出发地经度<br/>"
	        + "private String vcLatStart;// 出发地纬度<br/>"
	        + "private String vcShipNo;// 调度指令号<br/>"
	        + "private Integer iTruckId;// 拖车ID<br/>" )
	@ApiIgnore
	@RequestMapping( value = "/getDriverOrderDetail" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDriverOrderDetail(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "订单id,例如357" , required = true ) @RequestParam( value = "orderId" , required = true ) String orderId )
	{
		
		try
		{
			TOrder tOrder = iOrderService.get( Integer.parseInt( orderId ) );
			if ( null != tOrder )
			{
				TShiphead tShiphead = iOrderService.getByOrderId( tOrder.getId() );
				tOrder.setVcShipNo( tShiphead.getVcShipno() );
				tOrder.setiTruckId( tShiphead.getITruckId() );
			}
			
			if ( null != tOrder )
			{
				return AjaxUtil.getMapByNotException( true , tOrder );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , tOrder );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(根据指令号ID获取该指令的所有订单详情)
	 * @param request
	 * @param vcShipId
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-19 上午10:20:02
	 */
	@ApiOperation( value = "根据指令号ID获取该指令的所有订单详情" , notes = "根据指令号ID获取该指令的所有订单详情<br/>"
	        + "ORDERID:订单ID;<BR/>" + "VC_CAR_NAME:车型<br/>" + "N_TOTAL_CAR:车型数量<br/>"
	        + "VC_START_CITY:起始地城市<br/>" + "VC_DEST_CITY:目的地城市<br/>"
	        + "LINEID:指令明细ID<BR/>" + "VC_RECEIVE_ADDRESS:收货地址<br/>"
	        + "VC_LOAD_CONTACT:联系人<br/>VC_LOAD_TEL:联系电话<br/>"
	        + "HEADID:指令号ID<BR/>VC_SHIPNO:指令号<br/>DT_PICK:提货时间<br/>" )
	@RequestMapping( value = "/getOrderDetailByShipId" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getOrderDetailByShipId(
	        HttpServletRequest request ,
	        @ApiParam( value = "指令ID" , required = true ) @RequestParam( value = "vcShipId" , required = true ) String vcShipId )
	{
		int shipId = Integer.parseInt( vcShipId );
		String sql = "select t.id as orderId,t.vc_car_name,t.n_total_car,t.vc_start_city,t.vc_dest_city,t.vc_receive_address,t.vc_load_contact,t.vc_load_tel,head.id as headid , head.vc_shipno,head.dt_pick,line.id as lineid "
		        + " from T_ORDER t , t_Shipline line , t_shiphead head where line.i_shiphead = head.id and t.id = line.i_order_id"
		        + " and head.id = " + shipId;
		sql += " order by line.N_ARORDER desc,line.id ";
		System.out.println( "sql = " + sql );
		Map< String , Object > orderMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Page page = ServiceUtil.getcurrPage( request );
			orderMap = orderService.getSpringSql( sql , page );
			return AjaxUtil.getMapByResult( visit , orderMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(手机端司机确认是否接单接口)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-6 上午11:08:31
	 */
	@RequestMapping( value = "/sureOrderByDriver" , method = RequestMethod.POST )
	@ApiOperation( value = "手机端司机确认是否接单接口" , notes = "订单进行配载后司机可以进行接单操作;<br/>测试数据查询待接单订单:truckDriverAction/getDriverOrder(J) " )
	@ResponseBody
	public Map< String , Object > sureOrderByDriver(
	        HttpServletRequest request ,
	        @ApiParam( value = "指令id" , required = true ) @RequestParam( value = "headId" , required = true ) String headId ,
	        @ApiParam( value = "是否接单(0:确认接单;2:取消接单)" , required = true ) @RequestParam( value = "nSure" , required = true ) String nSure ,
	        @ApiParam( value = "预计提货时间(如果确认接单，则此字段不能为空)" , required = false ) @RequestParam( value = "dtExpect" , required = false ) String dtExpect ,
	        @ApiParam( value = "接单备注(手机端进行长度限制30字内)" , required = false ) @RequestParam( value = "vcSureNote" , required = false ) String vcSureNote )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		int shipId = Integer.parseInt( headId );// 指令主表id
		TShiphead tShiphead = iShipheadService.get( shipId );
		tShiphead.setnSure( Integer.parseInt( nSure ) );
		tShiphead.setVcSureName( user.getVcUsername() );// 确认接单人
		tShiphead.setDtSure( new Date() );
		if ( StringUtils.isNotBlank( dtExpect ) )
		{
			Date d;
			try
			{
				d = DateUtil.parseDate( dtExpect );
				tShiphead.setDtPick( d );// 预计抵达时间
			}
			catch ( ParseException e )
			{
				e.printStackTrace();
			}
			
		}
		
		try
		{
			List< TShipline > tShiplines = iShiplineService.findByHeadId( shipId );
			for ( TShipline tShipline : tShiplines )
			{
				
				if ( nSure.equals( "0" ) )// 确认接单
				{
					tShipline.setNCurrentStatus( SystemConstants.SYS_SUB_SUREORDER );
					
					iShiplineService.saveOrUpdate( tShipline );
					// 保存状态表
					TShipStatus tShipStatus = new TShipStatus();
					tShipStatus.setnOrderId( tShipline.getIOrderId() );
					tShipStatus.setnLineId( tShipline.getId() );
					tShipStatus.setVcAddUser( user.getVcUsername() );
					tShipStatus.setVcStatusNote( SystemConstants.VC_SURE_TRUE );
					tShipStatus.setVcStatusTag( SystemConstants.VC_SURE_TRUE_TAG );
					tShipStatus.setnHeadId( tShipline.getIShiphead() );
					tShipStatus.setVcNote( vcSureNote );
					iShipStatusService.saveOrUpdate( tShipStatus );
				}
				else if ( nSure.equals( "2" ) )
				// 取消接单
				{
					tShipline.setNEnable( SystemConstants.SYS_DISABLE );
					iShiplineService.saveOrUpdate( tShipline );
					
				}
				
			}
			if ( nSure.equals( "0" ) ) // 确认接单
			{
				tShiphead.setnCurrentStatus( SystemConstants.SYS_SUB_SUREORDER );// 已接单
				iShipheadService.saveOrUpdate( tShiphead );
				// return AjaxUtil.getMap( true , "确认接单操作成功!" );
			}
			else if ( nSure.equals( "2" ) )// 取消接单:所有订单恢复到可配载状态
			{
				tShiphead.setNEnable( SystemConstants.SYS_DISABLE );
				iShipheadService.saveOrUpdate( tShiphead );// 主指令设为无效
				// List< TOrder > orders = new ArrayList< TOrder >();
				for ( TShipline tShipline : tShiplines )
				{
					TOrder tOrder = orderService.get( tShipline.getIOrderId() );
					tOrder.setnLoad( 0 );
					orderService.update( tOrder );
				}
				/**
				 * 相应的拖车也要释放
				 */
				TTruckDriver truckDriver = truckDriverService.get( tShiphead
				        .getITruckId() );
				truckDriver.setNStatus( 0 );
				truckDriverService.update( truckDriver );
				
				/**
				 * 消息推送，推送给他的分供方
				 */
				// 找出该司机对应的分供方
				List< TUser > bossUsers = new ArrayList< TUser >();
				TDriver tDriver = driverService.get( user.getiArchive() );
				int bossUserId = tDriver.getiUserId();
				TUser bossTUser = userService.getById( bossUserId );
				bossUsers.add( bossTUser );
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "12" );// 11=抵达
				// map.put( "ID" , tOrder.getId() + "" );
				PushUtils pushUtils = new PushUtils( "您的指令被拒绝接单" , "指令【"
				        + tShiphead.getVcShipno() + "】被司机【" + user.getVcUsername()
				        + "】拒绝接单" , bossUsers ,
				        "com.unlcn.foursstore.StoreLookOrderDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( bossUsers.get( 0 ).getId() );// 接受信息的人
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "指令【" + tShiphead.getVcShipno() + "】被司机【"
				        + user.getVcUsername() + "】拒绝接单" );
				tMsgRecord.setVcTitle( "您的指令被拒绝接单" );
				iMsgRecordService.save( tMsgRecord );
			}
			return AjaxUtil.getMap( true , "操作成功!" );
			
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(确认是否发运)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-6 下午6:31:06
	 */
	@RequestMapping( value = "/shipOrderByDriver" , method = RequestMethod.POST )
	@ApiOperation( value = "手机端司机确认是否发运接口" , notes = "订单流程：配载-接单-入场-装车-发运;<br/>"
	        + "确认发运时需要实时获取GPS信息：vcLongitude（经度）、vcLatitude（纬度）" )
	@ResponseBody
	public Map< String , Object > shipOrderByDriver(
	        HttpServletRequest request ,
	        @ApiParam( value = "指令id" , required = true ) @RequestParam( value = "headId" , required = true ) String headId ,
	        @ApiParam( value = "经度" , required = false ) @RequestParam( value = "vcLongitude" , required = false ) String vcLongitude ,
	        @ApiParam( value = "纬度" , required = false ) @RequestParam( value = "vcLatitude" , required = false ) String vcLatitude ,
	        @ApiParam( value = "确认发运的详细地址" , required = false ) @RequestParam( value = "vcShipPlace" , required = false ) String vcShipPlace )
	{
		
		try
		{
			int shipId = Integer.parseInt( headId );
			TShiphead tShiphead = iShipheadService.get( shipId );
			List< TShipline > tShiplines = iShiplineService.findByHeadId( tShiphead
			        .getId() );
			// 获得指令号，判断指令号长度是否等于10，如果等于10，就确定该指令号是从ERP过来的。需要进行数据核实
			if ( null != tShiphead )
			{
				String vcShipno = tShiphead.getVcShipno();
				if ( StringUtils.isNotBlank( vcShipno ) && vcShipno.length() == 10 )
				{
					// 发送短信
					tShiplines = iShiplineService.findByHeadId( tShiphead.getId() );
					Map< String , Object > map = sendMsgToCustomer( tShiplines , vcShipno );
					if ( null != map )
					{
						return map;
					}
				}
			}
			
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			
			Geohash geohash = new Geohash();
			TOrder tOrder = iOrderService.get( tShiplines.get( 0 ).getIOrderId() );
			TStores tStores = new TStores(); // 4S店
			TUser storeUser = null;
			if ( tOrder.getiStoreId() != null && tOrder.getiStoreId() > 0 )
			{
				tStores = iStoresService.get( tOrder.getiStoreId() );
				if ( null != tStores && null != tStores.getiUserId() )
				{
					storeUser = userService.getByid( tStores.getiUserId() + "" );// 接受推送消息的4S店用户名
				}
			}
			for ( TShipline tShipline : tShiplines )
			{
				tShipline.setNCurrentStatus( SystemConstants.SYS_SUB_PARSHIP );
				TShipStatus tShipStatus = new TShipStatus();
				tShipStatus.setnLineId( tShipline.getId() );
				tShipStatus.setnOrderId( tShipline.getIOrderId() );
				tShipStatus.setVcAddUser( user.getVcUsername() );
				tShipStatus.setVcStatusNote( SystemConstants.VC_SHIP_TRUE );
				tShipStatus.setVcStatusTag( SystemConstants.VC_SHIP_TRUE_TAG );
				tShipStatus.setVcLatitude( vcLatitude );
				tShipStatus.setVcLongitude( vcLongitude );
				tShipStatus.setnHeadId( tShipline.getIShiphead() );
				if ( vcLatitude != null && vcLongitude != null )
				{
					String vcHash = geohash.encode( Double.parseDouble( vcLatitude ) ,
					        Double.parseDouble( vcLongitude ) );
					tShipStatus.setVcHash( vcHash );
				}
				tShipStatus.setVcStatusPlace( vcShipPlace );
				iShipStatusService.saveOrUpdate( tShipStatus );
				iShiplineService.saveOrUpdate( tShipline );
				// 消息推送
				if ( storeUser != null )
				{
					sentMsgToStore( user , tOrder , tStores , storeUser );// 给4s店推送消息
				}
			}
			// 绑定指令号
			int truckId = tShiphead.getITruckId();
			TTruckDriver truck = truckDriverService.get( truckId );
			truck.setVcShipNo( tShiphead.getVcShipno() );
			truckDriverService.update( truck );
			return AjaxUtil.getMap( true , "操作成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/** 
	 * @Description: 给指令下每个二手车客户发送验证码（调用erp发送），
	 * 				 如果发送不成功，返回map 错误信息，否则返回null
	 * @param tShiplines 指令明细集合 不为空
	 * @param vcShipno 指令编号
	 * @return 
	 *   Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年9月25日 上午10:06:30
	 */
	private Map< String , Object > sendMsgToCustomer( List< TShipline > tShiplines ,
	        String vcShipno )
	{
		for ( TShipline line : tShiplines )
		{
			int oid = line.getIOrderId();
			TOrder tOrder = orderService.get( oid );
			int customerId = tOrder.getICustomerId();
			// 获得客户信息
			TCustomer customer = customerSerivce.get( customerId );
			if ( null != customer )
			{
				// 客户为二手车客户才发送短信
				if ( 0 == customer.getNSecondHandCar() )
				{
					// 获得收货人联系电话，如果没有就获取对应客户电话
					String phone = tOrder.getVcReceiveTel();
					if ( StringUtils.isBlank( phone ) )
					{
						phone = customer.getVcPhone();
					}
					// 生成验证码
					int i = ( int ) ( ( Math.random() * 9 + 1 ) * 100000 );
					String messageCode = String.valueOf( i );
					
					// 调用erp中间件发短信
					boolean sendCodeMsg = sendCodeMsg( tOrder , phone , messageCode );
					
					if ( sendCodeMsg )
					{
						// 短信发送成功，把指令号 订单号 验证码 保存到数据库
						codeService.saveCode( vcShipno , tOrder.getVcOrderno() ,
						        tOrder.getVcCustOrderNo() , messageCode );
					}
					else
					{
						// 发送失败，请重试确定发运
						return AjaxUtil.getMap( false , "发送收货验证密码失败，请重试确定发运" );
					}
				}
			}
			
		}
		
		return null;
	}
	
	/**
	 * @Description: TODO(先从erp 查询指令主表 进行核对)
	 * @param tShiphead
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-24 上午9:50:22
	 */
	private void checkShipHead( TShiphead tShiphead )
	{
		String sql = "SELECT * FROM v_shiphead_erp@link_erp.unlcn.com head where head.VC_SHIPNO = '"
		        + tShiphead.getVcShipno() + "'";
		List< Map< String , Object > > arList = iShipheadService.excuteSql( sql );
		
	}
	
	/**
	 * @Description: 发送post请求到erp，进行短信发送 
	 * @param tOrder
	 * @param phone
	 * @param messageCode
	 * @return 
	 *   boolean 返回值描述
	 * @author hjx
	 * @create_date 2015年9月23日 下午4:15:21
	 */
	private boolean sendCodeMsg( TOrder tOrder , String phone , String messageCode )
	{
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost( SystemConstants.ERP_SEND_MSG_URL );
		// 创建参数队列
		List< NameValuePair > formparams = new ArrayList< NameValuePair >();
		
		// 建立一个10位的随机数
		int j = ( int ) ( ( Math.random() * 9 + 1 ) * 1000000000 );
		String sessionid = String.valueOf( j );
		// 添加erp中间件需要的参数
		formparams.add( new BasicNameValuePair( "userno" ,
		        SystemConstants.ERP_SEND_MSG_USERNO ) );
		formparams.add( new BasicNameValuePair( "sessionid" , sessionid ) );
		formparams.add( new BasicNameValuePair( "pwd" , sessionid ) );
		formparams.add( new BasicNameValuePair( "company" ,
		        SystemConstants.ERP_SEND_MSG_COMPANY ) );
		
		JSONObject object = new JSONObject();
		object.put( "mobileNo" , phone );// 收短信手机号码
		object.put( "hours" , SystemConstants.ERP_SEND_MSG_OUTTIME );// 超时时间
		// 短信内容
		object.put( "content" ,
		        SystemConstants.ERP_SEND_MSG_CONTENT_PRE + tOrder.getVcOrderno()
		                + SystemConstants.ERP_SEND_MSG_CONTENT_SUF + messageCode );
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.add( object );
		formparams.add( new BasicNameValuePair( "smsContent" , jsonArray.toString() ) );
		
		UrlEncodedFormEntity uefEntity;
		try
		{
			uefEntity = new UrlEncodedFormEntity( formparams , "UTF-8" );
			httppost.setEntity( uefEntity );
			System.out.println( "executing request " + httppost.getURI() );
			CloseableHttpResponse response = httpclient.execute( httppost );
			try
			{
				HttpEntity entity = response.getEntity();
				if ( entity != null )
				{
					System.out.println( "--------------------------------------" );
					System.out.println( "Response content: "
					        + EntityUtils.toString( entity , "UTF-8" ) );
					System.out.println( "--------------------------------------" );
					
					String resultStr = EntityUtils.toString( entity , "UTF-8" );
					JSONObject fromObject = JSONObject.fromObject( resultStr );
					if ( null != fromObject )
					{
						String result = ( String ) fromObject.get( "result" );
						if ( null != result )
						{
							result = result.trim();
							if ( "0".equals( result ) )// 为0，说明发送成功
							{
								return true;
							}
							else
							{
								System.out.println( fromObject.get( "addInfo" )
								        .toString() );
								return false;
							}
						}
					}
					return false;
				}
			}
			finally
			{
				response.close();
			}
		}
		catch ( ClientProtocolException e )
		{
			e.printStackTrace();
		}
		catch ( UnsupportedEncodingException e1 )
		{
			e1.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭连接,释放资源
			try
			{
				httpclient.close();
			}
			catch ( IOException e )
			{
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * @Description: 给4s店推送消息
	 * @param user
	 * @param tOrder
	 * @param tStores
	 * @param storeUser 
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月23日 上午9:55:45
	 */
	private void sentMsgToStore( TUser user , TOrder tOrder , TStores tStores ,
	        TUser storeUser )
	{
		Map< String , String > map = new HashMap< String , String >();
		map.put( "msgType" , "10" );
		map.put( "ID" , tOrder.getId() + "" );
		
		List< TUser > tUsers = new ArrayList< TUser >();
		tUsers.add( storeUser );
		PushUtils pushUtils = new PushUtils( "您的订单已经发运了，请点击查看" , "订单"
		        + tOrder.getVcOrderno() + "已经发运" , tUsers ,
		        "com.unlcn.foursstore.StoreLookOrderDetailActivity" , map );
		pushUtils.run();
		// 保存消息记录
		TMsgRecord tMsgRecord = new TMsgRecord();
		tMsgRecord.setIUser( user.getId() );// 添加人ID
		tMsgRecord.setVcAdduser( user.getVcAccount() );
		tMsgRecord.setIUserAccept( tStores.getId() );// 接受信息的人
		tMsgRecord.setNMsgType( 1 );// 单发
		tMsgRecord.setVcContent( "订单" + tOrder.getVcOrderno() + "已经发运" );
		tMsgRecord.setVcTitle( "订单已发运" );
		iMsgRecordService.save( tMsgRecord );
	}
	
	/**
	 * 
	 * @Description: 手机端司机确认是否抵达接口 接口新增字段：确定码vcCode，当为二手车订单抵达时，要填写收货人的确定码
	 * @param request
	 * @param vcLineId
	 *            指令明细ID
	 * @param vcLongitude
	 * @param vcLatitude
	 * @param vcShipPlace
	 * @param vcSureNote
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-12 上午9:59:34
	 */
	@RequestMapping( value = "/arriveOrderByDriver" , method = RequestMethod.POST )
	@ApiOperation( value = "手机端司机确认是否抵达接口" , notes = "订单流程：配载-接单-入场-装车-发运-抵达;<br/>"
	        + "确认抵达时需要实时获取GPS信息：vcLongitude（经度）、vcLatitude（纬度）<br/>"
	        + "注意：确认抵达是获取指令明细id，分开处理" )
	@ResponseBody
	public Map< String , Object > arriveOrderByDriver(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "指令明细id" , required = true ) @RequestParam( value = "vcLineId" , required = true ) String vcLineId ,
	        @ApiParam( value = "经度" , required = false ) @RequestParam( value = "vcLongitude" , required = false ) String vcLongitude ,
	        @ApiParam( value = "纬度" , required = false ) @RequestParam( value = "vcLatitude" , required = false ) String vcLatitude ,
	        @ApiParam( value = "确定码，非必填，当为二手车时，要填写" , required = false ) @RequestParam( value = "vcCode" , required = false ) String vcCode ,
	        @ApiParam( value = "上传图片用的字段" ) @RequestParam( "files" ) CommonsMultipartFile[] files ,
	        @ApiParam( value = "抵达数量(注意抵达数量不能大于装运数量)" , required = true ) @RequestParam( value = "vcArriveQty" , required = true ) String vcArriveQty ,
	        @ApiParam( value = "客户运单号(如果为空需要填写)" , required = false ) @RequestParam( value = "vcCustomeNo" , required = false ) String vcCustomeNo ,
	        @ApiParam( value = "确认抵达的详细地址" , required = false ) @RequestParam( value = "vcArrivePlace" , required = false ) String vcArrivePlace )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		Geohash geohash = new Geohash();
		if ( StringUtils.isNotBlank( vcLineId ) )
		{
			try
			{
				TShipline tShipline = iShiplineService.get( Integer.parseInt( vcLineId ) );
				TOrder tOrder = iOrderService.get( tShipline.getIOrderId() );
				// 判断该订单是否有收货确定码
				boolean hasCode = codeService.hasCodeByOrderNo( tOrder.getVcOrderno() );
				if ( hasCode )
				{
					// 检查确定码是否正确
					boolean checkSuccess = codeService.isCheckSuccess(
					        tOrder.getVcOrderno() , vcCode , vcLongitude , vcLatitude ,
					        vcArrivePlace );
					if ( ! checkSuccess )
					{
						return AjaxUtil.getMap( true , "您输入的确定码不正确！" );
					}
				}
				
				if ( vcCustomeNo != null )
				{
					tOrder.setVcCustOrderNo( vcCustomeNo );
				}
				TStores tStores = new TStores(); // 4S店
				TUser storeUser = null;
				if ( tOrder.getiStoreId() != null && tOrder.getiStoreId() > 0 )
				{
					tStores = iStoresService.get( tOrder.getiStoreId() );
					if ( null != tStores.getiUserId() )
					{
						storeUser = userService.getByid( tStores.getiUserId() + "" );// 接受推送消息的4S店用户名
					}
					else
					{
						storeUser = null;
					}
				}
				
				int aQty = Integer.parseInt( vcArriveQty );// 抵达数量
				if ( aQty <= tShipline.getnShipQty() )// 抵达数量小于发运数量
				{
					
					tShipline.setnArriveQty( Integer.parseInt( vcArriveQty ) );
					tShipline.setNCurrentStatus( SystemConstants.SYS_SUB_PARARRIVED );
					TShipStatus tShipStatus = new TShipStatus();
					tShipStatus.setnLineId( tShipline.getId() );
					tShipStatus.setnOrderId( tShipline.getIOrderId() );
					tShipStatus.setVcAddUser( user.getVcUsername() );
					tShipStatus.setVcStatusNote( SystemConstants.VC_ARRIVED_TRUE );
					tShipStatus.setVcStatusTag( SystemConstants.VC_ARRIVED_TRUE_TAG );
					tShipStatus.setVcLatitude( vcLatitude );
					tShipStatus.setVcLongitude( vcLongitude );
					tShipStatus.setnHeadId( tShipline.getIShiphead() );
					if ( vcLatitude != null && vcLongitude != null )
					{
						String vcHash = geohash.encode( Double.parseDouble( vcLatitude ) ,
						        Double.parseDouble( vcLongitude ) );
						tShipStatus.setVcHash( vcHash );
					}
					uploadImg( request , files , tShipStatus );
					saveApplyPic( tShipStatus );
					tShipStatus.setVcStatusPlace( vcArrivePlace );
					iShipStatusService.saveOrUpdate( tShipStatus );
					iShiplineService.saveOrUpdate( tShipline );
					iOrderService.saveOrUpdateOrder( tOrder );
					// 如果主表headId中所有子表的状态都为已抵达，则改变主表的状态为已抵达
					List< TShipline > tShiplines = iShiplineService
					        .findByHeadId( tShipline.getIShiphead() );
					TShiphead tShiphead = iShipheadService.get( tShipline.getIShiphead() );
					boolean flag = true;
					for ( TShipline shipline : tShiplines )
					{
						if ( ! shipline.getNCurrentStatus().equals(
						        SystemConstants.SYS_SUB_PARARRIVED ) )
						{
							flag = false;
							break;
						}
					}
					if ( flag )
					{
						tShiphead.setnCurrentStatus( SystemConstants.SYS_SUB_PARARRIVED );
						iShipheadService.saveOrUpdate( tShiphead );
						// 车辆设置为可用
						truckDriverService.saveTruckEnabledStatus( tShiphead
						        .getITruckId() );
						// 消息推送
						if ( storeUser != null )
						{
							// 消息处理，发送消息，并记录发送记录
							handleMsg( user , tOrder , tStores , storeUser );
						}
						
						return AjaxUtil.getMap( true , "抵达成功！" );
					}
					else
					{
						return AjaxUtil.getMap( false , "该订单已经抵达过！" );
					}
					
				}
				else
				{
					return AjaxUtil.getMap( false , "抵达数量大于发运数量" );
				}
				
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				return AjaxUtil.getMapByException( e );
			}
		}
		return null;
		
	}
	
	/**
	 * @Description: 消息处理，发送消息，并记录发送记录
	 * @param user
	 * @param tOrder
	 * @param tStores
	 * @param storeUser
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月17日 下午2:39:15
	 */
	private void handleMsg( TUser user , TOrder tOrder , TStores tStores , TUser storeUser )
	{
		Map< String , String > map = new HashMap< String , String >();
		map.put( "msgType" , "11" );// 11=抵达
		map.put( "ID" , tOrder.getId() + "" );
		
		List< TUser > tUsers = new ArrayList< TUser >();
		tUsers.add( storeUser );
		PushUtils pushUtils = new PushUtils( "您的订单，已经抵达了，请点击查看" , "订单"
		        + tOrder.getVcOrderno() + "已经抵达" , tUsers ,
		        "com.unlcn.foursstore.StoreLookOrderDetailActivity" , map );
		pushUtils.run();
		// 保存消息记录
		TMsgRecord tMsgRecord = new TMsgRecord();
		tMsgRecord.setIUser( user.getId() );// 添加人ID
		tMsgRecord.setVcAdduser( user.getVcAccount() );
		tMsgRecord.setIUserAccept( tStores.getId() );// 接受信息的人
		tMsgRecord.setNMsgType( 1 );// 单发
		tMsgRecord.setVcContent( "订单" + tOrder.getVcOrderno() + "已经抵达" );
		tMsgRecord.setVcTitle( "订单已抵达" );
		iMsgRecordService.save( tMsgRecord );
	}
	
	/**
	 * 
	 * @Description: 手机端司机确认是否抵达接口,第二期需求接口，暂时没用 2015-9-17
	 * @param request
	 * @param vcLineId
	 *            指令明细ID
	 * @param vcLongitude
	 * @param vcLatitude
	 * @param vcShipPlace
	 * @param vcSureNote
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-12 上午9:59:34
	 */
	@RequestMapping( value = "/arriveOrderByDriver2" , method = RequestMethod.POST )
	@ApiOperation( value = "手机端司机确认是否抵达接口" , notes = "订单流程：配载-接单-入场-装车-发运-抵达;<br/>"
	        + "确认抵达时需要实时获取GPS信息：vcLongitude（经度）、vcLatitude（纬度）<br/>"
	        + "注意：确认抵达是获取指令明细id，分开处理" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > arriveOrderByDriver2(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "类型：1：不是绑定多个客户运单号；2：绑定多个客户运单号" ) @RequestParam( value = "vcType" , required = true ) String vcType ,
	        @ApiParam( value = "指令明细id" , required = true ) @RequestParam( value = "vcLineId" , required = true ) String vcLineId ,
	        @ApiParam( value = "经度" , required = false ) @RequestParam( value = "vcLongitude" , required = false ) String vcLongitude ,
	        @ApiParam( value = "纬度" , required = false ) @RequestParam( value = "vcLatitude" , required = false ) String vcLatitude ,
	        @ApiParam( value = "上传图片用的字段" ) @RequestParam( "files" ) CommonsMultipartFile[] files ,
	        @ApiParam( value = "抵达数量(注意抵达数量不能大于装运数量)" , required = true ) @RequestParam( value = "vcArriveQty" , required = true ) String vcArriveQty ,
	        @ApiParam( value = "客户运单号(如果为空需要填写)" , required = false ) @RequestParam( value = "vcCustomeNo" , required = false ) String vcCustomeNo ,
	        @ApiParam( value = "确认抵达的详细地址" , required = false ) @RequestParam( value = "vcArrivePlace" , required = false ) String vcArrivePlace )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		Geohash geohash = new Geohash();
		if ( StringUtils.isNotBlank( vcLineId ) )
		{
			if ( vcType.equals( 1 ) )// 不是绑定多个客户运单号
			{
				try
				{
					TShipline tShipline = iShiplineService.get( Integer
					        .parseInt( vcLineId ) );
					TOrder tOrder = iOrderService.get( tShipline.getIOrderId() );
					if ( vcCustomeNo != null )
					{
						tOrder.setVcCustOrderNo( vcCustomeNo );
					}
					int aQty = Integer.parseInt( vcArriveQty );// 抵达数量
					if ( aQty <= tShipline.getnShipQty() )
					{
						
						tShipline.setnArriveQty( Integer.parseInt( vcArriveQty ) );
						tShipline.setNCurrentStatus( SystemConstants.SYS_SUB_PARARRIVED );
						TShipStatus tShipStatus = new TShipStatus();
						tShipStatus.setnLineId( tShipline.getId() );
						tShipStatus.setnOrderId( tShipline.getIOrderId() );
						tShipStatus.setVcAddUser( user.getVcUsername() );
						tShipStatus.setVcStatusNote( SystemConstants.VC_ARRIVED_TRUE );
						tShipStatus.setVcStatusTag( SystemConstants.VC_ARRIVED_TRUE_TAG );
						tShipStatus.setVcLatitude( vcLatitude );
						tShipStatus.setVcLongitude( vcLongitude );
						tShipStatus.setnHeadId( tShipline.getIShiphead() );
						if ( vcLatitude != null && vcLongitude != null )
						{
							String vcHash = geohash.encode(
							        Double.parseDouble( vcLatitude ) ,
							        Double.parseDouble( vcLongitude ) );
							tShipStatus.setVcHash( vcHash );
						}
						uploadImg( request , files , tShipStatus );
						saveApplyPic( tShipStatus );
						tShipStatus.setVcStatusPlace( vcArrivePlace );
						iShipStatusService.save( tShipStatus );
						iShiplineService.saveOrUpdate( tShipline );
						iOrderService.saveOrUpdateOrder( tOrder );
						// 如果主表headId中所有子表的状态都为已抵达，则改变主表的状态为已抵达
						List< TShipline > tShiplines = iShiplineService
						        .findByHeadId( tShipline.getIShiphead() );
						TShiphead tShiphead = iShipheadService.get( tShipline
						        .getIShiphead() );
						boolean flag = true;
						for ( TShipline shipline : tShiplines )
						{
							if ( ! shipline.getNCurrentStatus().equals(
							        SystemConstants.SYS_SUB_PARARRIVED ) )
							{
								flag = false;
								break;
							}
						}
						if ( flag )
						{
							tShiphead
							        .setnCurrentStatus( SystemConstants.SYS_SUB_PARARRIVED );
							iShipheadService.saveOrUpdate( tShiphead );
						}
						
						return AjaxUtil.getMap( true , "发布成功！" );
					}
					else
					{
						return AjaxUtil.getMap( false , "抵达数量大于发运数量" );
					}
					
				}
				catch ( Exception e )
				{
					
					return AjaxUtil.getMapByException( e );
				}
			}
			else
			// 绑定多个客户运单号的情况
			{
				TShipline tShipline = iShiplineService.get( Integer.parseInt( vcLineId ) );
				TOrder tOrder = iOrderService.get( tShipline.getIOrderId() );
				if ( vcCustomeNo != null )
				{
					tOrder.setVcCustOrderNo( vcCustomeNo );
				}
				int aQty = Integer.parseInt( vcArriveQty );// 抵达数量
				if ( aQty <= tShipline.getnShipQty() )
				{
					TShipline copyShipline = new TShipline();
					try
					{
						BeanUtils.copyProperties( copyShipline , tShipline );
						tShipline.setNEnable( SystemConstants.SYS_DISABLE );// 拆单后原指令设为无效
						copyShipline.setnArriveQty( Integer.parseInt( vcArriveQty ) );
						copyShipline
						        .setNCurrentStatus( SystemConstants.SYS_SUB_PARARRIVED );
						TShipStatus tShipStatus = new TShipStatus();
						tShipStatus.setnLineId( tShipline.getId() );
						tShipStatus.setnOrderId( tShipline.getIOrderId() );
						tShipStatus.setVcAddUser( user.getVcUsername() );
						tShipStatus.setVcStatusNote( SystemConstants.VC_ARRIVED_TRUE );
						tShipStatus.setVcStatusTag( SystemConstants.VC_ARRIVED_TRUE_TAG );
						tShipStatus.setVcLatitude( vcLatitude );
						tShipStatus.setVcLongitude( vcLongitude );
						tShipStatus.setnHeadId( tShipline.getIShiphead() );
						if ( vcLatitude != null && vcLongitude != null )
						{
							String vcHash = geohash.encode(
							        Double.parseDouble( vcLatitude ) ,
							        Double.parseDouble( vcLongitude ) );
							tShipStatus.setVcHash( vcHash );
						}
						uploadImg( request , files , tShipStatus );
						saveApplyPic( tShipStatus );
						tShipStatus.setVcStatusPlace( vcArrivePlace );
						iShipStatusService.save( tShipStatus );
						iShiplineService.saveOrUpdate( copyShipline );
						iShiplineService.update( tShipline );
						iOrderService.saveOrUpdateOrder( tOrder );
						// 如果主表headId中所有子表的状态都为已抵达，则改变主表的状态为已抵达
						List< TShipline > tShiplines = iShiplineService
						        .findByHeadId( tShipline.getIShiphead() );
						TShiphead tShiphead = iShipheadService.get( tShipline
						        .getIShiphead() );
						boolean flag = true;
						for ( TShipline shipline : tShiplines )
						{
							if ( ! shipline.getNCurrentStatus().equals(
							        SystemConstants.SYS_SUB_PARARRIVED ) )
							{
								flag = false;
								break;
							}
						}
						if ( flag )
						{
							tShiphead
							        .setnCurrentStatus( SystemConstants.SYS_SUB_PARARRIVED );
							iShipheadService.saveOrUpdate( tShiphead );
						}
						
						return AjaxUtil.getMap( true , "发布成功！" );
						
					}
					catch ( IllegalAccessException e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch ( InvocationTargetException e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					catch ( Exception e )
					{
						e.printStackTrace();
					}
					
				}
				else
				{
					return AjaxUtil.getMap( false , "抵达数量大于发运数量" );
				}
			}
			
		}
		return null;
		
	}
	
	/**
	 * @Description: TODO(保存图片到图片表)
	 * @param tShipStatus
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-10 上午10:44:58
	 */
	private void saveApplyPic( TShipStatus tShipStatus )
	{
		String[] paths = tShipStatus.getVcPath().split( "," );
		for ( String path : paths )
		{
			TApplyPic tApplyPic = new TApplyPic();
			// tApplyPic.setDtAdd( new Date() );
			tApplyPic.setiTableId( tShipStatus.getnLineId() );
			tApplyPic.setVcPicName( path );
			tApplyPic.setVcTableName( "TSHIPLINE" );
			idamagePicService.saveApplyTdamagePic( tApplyPic );
		}
	}
	
	/**
	 * @Description: TODO(处理图片上传)
	 * @param request
	 * @param files
	 * @param tShipStatus
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @create_date 2015-6-2 下午1:31:27
	 */
	private void uploadImg( HttpServletRequest request , CommonsMultipartFile[] files ,
	        TShipStatus tShipStatus ) throws FileNotFoundException , IOException
	{
		for ( int i = 0 ; i < files.length ; i++ )
		{
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				String source = pictureService.getPathById( 21 );// 司机抵达图片
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
				String imgPath = tShipStatus.getVcPath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					tShipStatus.setVcPath( jpgPath );
				}
				else
				{
					tShipStatus.setVcPath( imgPath + "," + jpgPath );
				}
			}
		}
		
	}
	
	/**
	 * @Description:获取所有的车辆信息（手机端）
	 * @param request
	 * @author chengwzh
	 * @create date 2015/5/9 15:13
	 */
	@RequestMapping( value = "/getAllTrucksBysubnoByApp" , method = RequestMethod.POST )
	@ApiOperation( value = "获取所有车辆信息" , notes = "获取所有车辆信息" , position = 5 )
	@ResponseBody
	public Map< String , Object > getAllTrucksBysubnoByApp( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		String vcSubno = subService.get( subId ).getVcSubno();// 获取分供方编号
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = truckDriverService.getAllTrucksByApp( page ,
			        vcSubno );
			JSONArray array = JSONArray.fromObject( result );
			System.out.println( "getAllTrucksBysubnoByApp jsonarr:" + array.toString() );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Decription 增加车辆信息（手机端）
	 * @param truck
	 * @param mainDriverName
	 *            主驾名字
	 * @param copilotDriverName
	 *            副驾名字
	 * @param TTruckDriver
	 *            truck
	 * @author chengwzh
	 * @date 2015/5/9 15:50
	 */
	@RequestMapping( value = "/addTruck" , method = RequestMethod.POST )
	@ApiOperation( value = "增加车辆" , notes = "增加车辆,车辆属性;<br/>"
	        + "private Integer id;主键<br/>" + "private String vcCarNo;车牌号<br/>"
	        + "private Integer NEnable;是否有效（0有效，1无效）<br/>"
	        + "private String vcSubno;所属分供方编号<br/>"
	        + "private Integer NStatus;车辆是否可用（0有效，1无效）  调度指令回单确认释放车辆<br/>"
	        + "private Integer ITruckTypeID;运输车类型<br/>" + "private Float NLength;车长<br/>"
	        + "private Float NWidth;车宽<br/>" + "private Float NHeight;车高<br/>"
	        + "private Float NSelfWeight;车自重<br/>" + "private Float NLoanWeight;车载重<br/>"
	        + "private String vcShipNo;最新的指令号<br/>"
	        + "private String vcDriverNo;车辆行驶证号码<br/>" + "driverId1;主驾id<br/>"
	        + "driverId2;副驾id<br/>" , position = 5 , response = TTruckDriver.class )
	@ResponseBody
	public Map< String , Object > addTruck(
	        HttpServletRequest request ,
	        @Valid @ModelAttribute TTruckDriver truck ,
	        BindingResult error ,
	        @ApiParam( value = "主驾id" ) @RequestParam( value = "driverId1" , required = false ) Integer driverId1 ,
	        @ApiParam( value = "副驾id" ) @RequestParam( value = "driverId2" , required = false ) Integer driverId2 ,
	        // @ApiParam( value = "运输车类型" , required = true ) @RequestParam(
	        // value = "ITruckTypeID" ) Integer ITruckTypeID ,
	        HttpServletResponse response )
	{
		if ( error.hasErrors() )
		{
			return ServiceUtil.getErrorVerification( error );
		}
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		try
		{
			String subno = subService.get( subId ).getVcSubno();
			truck.setVcSubno( subno );
			// truck.setITruckTypeID( ITruckTypeID );
			if ( driverId1.equals( driverId2 ) )
			{
				return AjaxUtil.getMap( false , "主驾司机和副驾司机不能是同一个" );
			}
			// 验证车辆重复性
			String carno = truck.getVcCarNo();
			String[] propertys = { "vcCarNo" , "NEnable" };
			Object[] vals = { carno , SystemConstants.SYS_ENABLE };
			List< TTruckDriver > trucks = truckDriverService.findByPropertys( propertys ,
			        vals );
			if ( CollectionUtils.isNotEmpty( trucks ) )
			{
				return AjaxUtil.getMap( false , "保存失败，该车牌号已经存在" );
			}
			truckDriverService.save( truck );
			// 根据主驾id创建车辆与司机的连接
			TDriver mainDriver = driverService.get( driverId1 );
			if ( mainDriver != null )
			{
				int truckId1 = truck.getId();
				TTruckDriverLlink mainLink = new TTruckDriverLlink();
				mainLink.setIDriverID( driverId1 );
				mainLink.setITruckID( truckId1 );
				mainLink.setNPositionType( 1 );
				linkService.save( mainLink );
				// 消息推送
				String[] propertyNames = { "IArchiveType" , "iArchive" };
				Object[] values = { SystemConstants.SYS_TARCHIVE_DRIVER ,
				        mainLink.getIDriverID() };
				List< TUser > tUsers = userService.findByProperties( propertyNames ,
				        values );
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "9" );
				map.put( "driverId" , mainLink.getIDriverID() + "" );
				PushUtils pushUtils = new PushUtils( "车辆配载消息" , "你现在是"
				        + truck.getVcCarNo() + "的主驾!" , tUsers ,
				        "com.unlcn.driver.mine.MyInfoDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( tUsers.get( 0 ).getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "你现在是" + truck.getVcCarNo() + "的主驾!" );
				tMsgRecord.setVcTitle( "车辆配载消息" );
				iMsgRecordService.save( tMsgRecord );
			}
			else
			{
				return AjaxUtil.getMap( false , "添加失败：主驾司机不存在" );
			}
			
			// 根据副驾id创建车辆与司机的连接
			TDriver copilotdriver = driverService.get( driverId2 );
			if ( copilotdriver == null )
			{
				return AjaxUtil.getMap( false , "添加失败：副驾司机不存在" );
				
			}
			else
			{
				int truckId2 = truck.getId();
				TTruckDriverLlink copilotLink = new TTruckDriverLlink();
				copilotLink.setIDriverID( driverId2 );
				copilotLink.setITruckID( truckId2 );
				copilotLink.setNPositionType( 2 );
				linkService.save( copilotLink );
				
				// 消息推送
				String[] propertyNames = { "IArchiveType" , "iArchive" };
				Object[] values = { SystemConstants.SYS_TARCHIVE_DRIVER ,
				        copilotLink.getIDriverID() };
				List< TUser > tUsers = userService.findByProperties( propertyNames ,
				        values );
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "9" );
				map.put( "driverId" , copilotLink.getIDriverID() + "" );
				PushUtils pushUtils = new PushUtils( "车辆配载消息" , "你现在是"
				        + truck.getVcCarNo() + "的副驾!" , tUsers ,
				        "com.unlcn.driver.mine.MyInfoDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( tUsers.get( 0 ).getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "你现在是" + truck.getVcCarNo() + "的副驾!" );
				tMsgRecord.setVcTitle( "车辆配载消息" );
				return AjaxUtil.getMap( true , "添加成功" );
				
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 通过truckId获取车辆详细信息
	 * @Param truckId
	 * @return Map< String , Object >
	 * @author chengwzh
	 * @date 2015/5/12 14:25
	 */
	@RequestMapping( value = "/getTruckById" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "通过Id获取车辆详细信息" , notes = "通过Id获取车辆详细信息" , position = 5 )
	public Map< String , Object > getTruckById(
	        HttpServletRequest request ,
	        @ApiParam( value = "车辆Id" , required = true ) @RequestParam( value = "truckId" ) int truckId )
	{
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		// System.out.println( "车牌号：" + vcCarNo );
		try
		{
			Map< String , Object > result = truckDriverService.getTruckById( truckId );
			if ( result == null )
			{
				return AjaxUtil.getMap( false , "车辆不存在" );
			}
			else
			{
				JSONArray array = JSONArray.fromObject( result );
				System.out.println( "getTruckByCarNo json:" + array );
				// 判断是否是app端
				if ( StringUtils.isNotBlank( visit )
				        && SystemConstants.APP_VISIT.equals( visit ) )
				{
					return AjaxUtil.getMapByNotException( true , result );
				}
				else
				{
					return result;
				}
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 修改车辆信息（手机端）
	 * @author chengwzh
	 * @create date 2015/5/12
	 */
	@RequestMapping( value = "/updateTruckByApp" , method = RequestMethod.POST )
	@ApiOperation( value = "修改车辆信息" , notes = "修改车辆信息" , position = 5 , response = TTruckDriver.class )
	@ResponseBody
	public Map< String , Object > updateTruckByApp(
	        @ModelAttribute TTruckDriver truck ,
	        HttpServletRequest request ,
	        @ApiParam( value = "主驾司机id" ) @RequestParam( value = "driverId1" , required = false ) Integer driverId1 ,
	        @ApiParam( value = "副驾司机id" ) @RequestParam( value = "driverId2" , required = false ) Integer driverId2 )
	{
		
		try
		{
			if ( driverId1.equals( driverId2 ) )
			{
				return AjaxUtil.getMap( false , "主驾司机与副驾司机不能为同一人!" );
			}
			if ( truck.getNStatus().equals( 1 ) )
			{
				return AjaxUtil.getMap( false , "该拖车已做配载，不能做改!" );
			}
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			truckDriverService.update( truck );
			Integer truckId = truck.getId();
			truckDriverService.delTruckDriverLink( truckId , user.getId() );// 删除该车的所有关联司机信息
			String[] properties = { "IArchiveType" , "iArchive" , "NEnable" };
			if ( driverId1 != null )
			{
				// 新增主驾连接
				TTruckDriverLlink link = new TTruckDriverLlink();
				link.setITruckID( truckId );
				link.setIDriverID( driverId1 );
				link.setNPositionType( 1 );
				truckDriverService.saveTruckDriverLlink( link );
				/**
				 * 消息推送
				 */
				
				Object[] mainValues = { SystemConstants.SYS_TARCHIVE_DRIVER , driverId1 ,
				        SystemConstants.SYS_ENABLE };// 主驾司机的参数
				List< TUser > mainUsers = userService.findByProperties( properties ,
				        mainValues );// 主驾司机
				Map< String , String > mainMap = new HashMap< String , String >();
				// 主驾司机消息推送
				mainMap.put( "driverId" , driverId1 + "" );
				mainMap.put( "msgType" , "9" );
				if ( mainUsers.size() > 0 )
				{
					PushUtils pUtils1 = new PushUtils( "拖车主驾确认" , mainUsers.get( 0 )
					        .getVcUsername() + "你现在是" + truck.getVcCarNo() + "主驾！" ,
					        mainUsers , "com.unlcn.driver.mine.MyInfoDetailActivity" ,
					        mainMap );
					pUtils1.run();
					// 保存消息记录
					TMsgRecord tMsgRecord = new TMsgRecord();
					tMsgRecord.setIUser( user.getId() );// 添加人ID
					tMsgRecord.setVcAdduser( user.getVcAccount() );
					tMsgRecord.setIUserAccept( mainUsers.get( 0 ).getId() );
					tMsgRecord.setNMsgType( 1 );// 单发
					tMsgRecord.setVcContent( "你现在是" + truck.getVcCarNo() + "主驾！" );
					tMsgRecord.setVcTitle( "拖车主驾确认" );
					iMsgRecordService.save( tMsgRecord );
				}
			}
			if ( driverId2 != null )
			{
				// 新增副驾连接
				TTruckDriverLlink link2 = new TTruckDriverLlink();
				link2.setITruckID( truckId );
				link2.setIDriverID( driverId2 );
				link2.setNPositionType( 2 );
				truckDriverService.saveTruckDriverLlink( link2 );
				/**
				 * 消息推送
				 */
				
				Object[] copilotValues = { SystemConstants.SYS_TARCHIVE_DRIVER ,
				        driverId2 , SystemConstants.SYS_ENABLE };// 副驾司机的参数
				
				List< TUser > copilotUsers = userService.findByProperties( properties ,
				        copilotValues );// size应为0 副驾司机
				
				Map< String , String > copilotMap = new HashMap< String , String >();
				
				if ( copilotUsers.size() > 0 )
				{
					// 副驾司机消息推送
					copilotMap.put( "driverId" , driverId2 + "" );
					copilotMap.put( "msgType" , "9" );
					PushUtils pUtils2 = new PushUtils( "拖车副驾确认" , copilotUsers.get( 0 )
					        .getVcUsername() + "你现在是" + truck.getVcCarNo() + "副驾！" ,
					        copilotUsers , "com.unlcn.driver.mine.MyInfoDetailActivity" ,
					        copilotMap );
					pUtils2.run();
					// 保存消息记录
					TMsgRecord tMsgRecord = new TMsgRecord();
					tMsgRecord.setIUser( user.getId() );// 添加人ID
					tMsgRecord.setVcAdduser( user.getVcAccount() );
					tMsgRecord.setIUserAccept( copilotUsers.get( 0 ).getId() );
					tMsgRecord.setNMsgType( 1 );// 单发
					tMsgRecord.setVcContent( "你现在是" + truck.getVcCarNo() + "副驾！" );
					tMsgRecord.setVcTitle( "拖车副驾确认" );
					iMsgRecordService.save( tMsgRecord );
				}
			}
			
			return AjaxUtil.getMap( true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 删除车辆信息（手机端）
	 * @param truckId
	 * @author chengwzh
	 * @2015/5/12 15:50
	 */
	@RequestMapping( value = "/deleteTruckByApp" , method = RequestMethod.POST )
	@ApiOperation( value = "删除车辆信息" , notes = "删除车辆信息" , position = 5 , response = TTruckDriver.class )
	@ResponseBody
	public Map< String , Object > deleteTruckByApp(
	        @ApiParam( value = "车辆ID" ) @RequestParam( value = "truckId" ) int truckId ,
	        HttpServletRequest request )
	{
		try
		{
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			
			truckDriverService.delTruck( truckId , user.getId() );
			return AjaxUtil.getMap( true , "删除成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(司机确认装运)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-19 下午1:57:38
	 */
	@ApiOperation( value = "手机端司机确认装运数量接口" , notes = "手机端司机确认装运接口<BR/>"
	        + "vcShipLineId:指令明细ID<br/>" + "nQty:装运数量<br/>" )
	@RequestMapping( value = "/saveShipQty" , method = RequestMethod.POST )
	public void saveShipQty(
	        HttpServletRequest request ,
	        @ApiParam( value = "多个指令明细:封装JSON格式如:<br/>[{'id':'225','nShipQty':'2'},<br/>{'id':'226','nShipQty':'2'}]" , required = true ) @RequestParam( value = "lineOrderJson" , required = true ) String lineOrderJson ,
	        HttpServletResponse response )
	{
		List< TShipline > shiplines = JSON.parseArray( lineOrderJson , TShipline.class );
		List< TShipline > newShiplines = new ArrayList< TShipline >();
		boolean flag = true;
		String message = "";
		for ( TShipline tShipline : shiplines )
		{
			TShipline newShipline = iShiplineService.get( tShipline.getId() );
			if ( newShipline.getNCurrentStatus() >= 10 )
			{
				flag = false;
				
				break;
			}
			newShipline.setnShipQty( tShipline.getnShipQty() );
			newShiplines.add( newShipline );
		}
		if ( flag )
		{
			try
			{
				for ( TShipline newShipline : newShiplines )
				{
					newShipline.setNCurrentStatus( SystemConstants.SYS_SUB_PARLOAD );
					iShiplineService.saveOrUpdate( newShipline );
					AjaxUtil.rendJson( response , true , "成功！" );
				}
			}
			catch ( Exception e )
			{
				AjaxUtil.rendJson( response , false , "操作失败！原因：" + e.getMessage() );
			}
		}
		else
		{
			AjaxUtil.rendJson( response , false , "该指令号已做装车确认" );
		}
		
	}
	
	/**
	 * @Description 获取所有车辆类型
	 * 
	 */
	@RequestMapping( value = "/getTruckTypes" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有车辆类型" , notes = "获取所有车辆类型<br/>{"
	        + " \"message\": \"获取成功！\"," + " \"data\": [" + "  {" + "    \"id\": 1,"
	        + "    \"vcTypeName\": \"4轴单车\"" + "  }" + "]," + " \"isSuccess\": true}" , position = 5 )
	public Map< String , Object > getTruckTypes()
	{
		try
		{
			List< TTruckType > types = truckDriverService.getAllTruckType();
			List< Map< String , Object >> list = new ArrayList< Map< String , Object >>();
			for ( TTruckType type : types )
			{
				Map< String , Object > map = new HashMap< String , Object >();
				map.put( "id" , type.getId() );
				map.put( "vcTypeName" , type.getVcTypeName() );
				list.add( map );
			}
			return AjaxUtil.getMapByNotException( true , list );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 根据运力主表Id获取提货详情
	 * @param headId
	 * @return
	 * @author chengwzh
	 * @date 2015/6/19 16:00
	 */
	@RequestMapping( value = "/getPickDetailById" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "根据运力主表Id获取提货详情" , notes = "根据运力主表Id获取提货详情" , position = 5 )
	public Map< String , Object > getPickDetailById(
	        @ApiParam( value = "发运主表id" ) @RequestParam( value = "headId" ) int headId )
	{
		TShiphead entity = iShipheadService.get( headId );
		Map< String , Object > result = new HashMap< String , Object >();
		result.put( "id" , entity.getId() );
		result.put( "vcShipno" , entity.getVcShipno() );
		result.put( "dtPick" , entity.getDtPick() );
		result.put( "vcShipPlace" , entity.getVcShipPlace() );
		String[] propertyNames = { "NEnable" , "IShiphead" };
		Object[] values = { SystemConstants.SYS_ENABLE , headId };
		List< TShipline > list = iShiplineService
		        .findByPropertys( propertyNames , values );
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			TShipline line = list.get( 0 );
			int orderId = line.getIOrderId();
			TOrder order = iOrderService.get( orderId );
			result.put( "vcReceiveContact" , order.getVcReceiveContact() );// 联系人
			result.put( "vcReceiveTel" , order.getVcReceiveTel() );// 联系人电话
		}
		else
		{
			result.put( "vcReceiveContact" , "暂无" );
			result.put( "vcReceiveTel" , "暂无" );
		}
		return result;
	}
	
	/**
	 * @Description 根据headId获取提货订单列表
	 * @param headId
	 * @return
	 * @author chengwzh
	 * @date 2015/6/19 14:10
	 */
	@RequestMapping( value = "/getOrderlistByHeadId" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "根据headId获取提货订单列表" , notes = "根据headId获取提货订单列表" , position = 5 )
	public Map< String , Object > getOrderlistByHeadId(
	        @ApiParam( value = "发运主表id" ) @RequestParam( value = "headId" ) int headId )
	{
		try
		{
			List< Map< String , Object >> result = truckDriverService
			        .getOrderlistByHeadId( headId );
			return AjaxUtil.getMapByNotException( true , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 提货确认
	 * @param pickJson
	 * @param response
	 * @author chengwzh
	 * @date 2015/6/19 15:30
	 */
	@RequestMapping( value = "/pickConfirm" , method = RequestMethod.POST )
	@ApiOperation( value = "提货确认" , notes = "提货确认" , position = 5 , response = TOrder.class )
	public void pickConfirm(
	        @ApiParam( value = "将订单列表的每一条数据的主键,数量封装成json字符串形如：<br/>"
	                + "[{\"orderId\":\"1\",\"nPickQty\":\"100\"},{\"orderId\":\"2\",\"nPickQty\":\"300\"}]" ) @RequestParam( value = "pickJson" ) String pickJson ,
	        @ApiParam( value = "发运主表id" ) @RequestParam( value = "headId" ) int headId ,
	        HttpServletResponse response )
	{
		try
		{
			List< Map< String , Object >> list = JSONArray.fromObject( pickJson );
			for ( Map< String , Object > map : list )
			{
				int orderId = Integer.parseInt( map.get( "orderId" ).toString() );
				int nPickQty = Integer.parseInt( map.get( "nPickQty" ).toString() );
				List< TShipline > lines = iShiplineService.findByProperty( "IOrderId" ,
				        orderId );
				if ( CollectionUtils.isEmpty( lines ) )
				{
					AjaxUtil.rendJson( response , false , "提货确认失败:找不到" + orderId
					        + "对应的发运明细记录" );
					return;
				}
				TShipline line = lines.get( 0 );
				line.setnPickQty( nPickQty );
				iShiplineService.update( line );
			}
			// 主表状态更新
			TShiphead shiphead = iShipheadService.get( headId );
			shiphead.setnCurrentStatus( SystemConstants.SYS_SUB_PICK );
			iShipheadService.update( shiphead );
			// 绑定指令
			int truckId = shiphead.getITruckId();
			TTruckDriver truck = truckDriverService.get( truckId );
			truck.setVcShipNo( shiphead.getVcShipno() );
			truckDriverService.update( truck );
			AjaxUtil.rendJson( response , true , "提货确认成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "提货确认失败：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 根据发运主表id获取检查装车详情
	 * @param headId
	 * @return
	 * @author chengwzh
	 * @date 2015/06/23 14:10
	 */
	@RequestMapping( value = "/getCheckLoadingById" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "根据发运主表id获取检查装车详情" , notes = "根据发运主表id获取检查装车详情" , position = 5 )
	public Map< String , Object > getCheckLoadingById(
	        @ApiParam( value = "发运主表id" ) @RequestParam( value = "headId" ) int headId )
	{
		try
		{
			List< Map< String , Object >> result = truckDriverService
			        .getCheckLoadingById( headId );
			return AjaxUtil.getMapByNotException( true , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 确认检查装车
	 * @param shipJson
	 * @param response
	 * @param vinstr
	 * @param shipno
	 * @param session
	 * @author chengwzh
	 * @date 2015/6/23 15:38
	 */
	@RequestMapping( value = "/confirmCheckLoading" , method = RequestMethod.POST )
	@ApiOperation( value = "检查装车确认" , notes = "检查装车确认" , response = TShipline.class , position = 5 )
	@ResponseBody
	public Map< String , Object > confirmCheckLoading(
	        @ApiParam( value = "将订单列表的每一条数据的主键,数量封装成json字符串形如：<br/>"
	                + "[{\"orderId\":\"1\",\"nShipQty\":\"100\"},{\"orderId\":\"2\",\"nShipQty\":\"300\"}]" ) @RequestParam( value = "shipJson" ) String shipJson ,
	        HttpServletResponse response ,
	        @ApiParam( value = "vinstr：vin码拼接的字符串','隔开" ) @RequestParam( value = "vinstr" ) String vinstr ,
	        @ApiParam( value = "指令号" ) @RequestParam( value = "shipno" ) String shipno ,
	        @ApiParam( value = "发运主表id" ) @RequestParam( value = "headId" ) int headId ,
	        HttpSession session )
	{
		try
		{
			List< Map< String , Object >> list = JSONArray.fromObject( shipJson );
			for ( Map< String , Object > map : list )
			{
				int orderId = Integer.parseInt( map.get( "orderId" ).toString() );
				int shipQty = Integer.parseInt( map.get( "nShipQty" ).toString() );
				List< TShipline > lines = iShiplineService.findByProperty( "IOrderId" ,
				        orderId );
				if ( CollectionUtils.isEmpty( lines ) )
				{
					return AjaxUtil.getMap( false , "检查装车确认失败：找不到" + orderId
					        + "对应的发运明细记录" );
				}
				TShipline line = lines.get( 0 );
				line.setnShipQty( shipQty );
				iShiplineService.update( line );
			}
			// 更新当前状态
			TShiphead head = iShipheadService.get( headId );
			head.setnCurrentStatus( SystemConstants.SYS_SUB_PARLOAD );
			iShipheadService.update( head );
			
			TUser user = ( TUser ) session.getAttribute( "user" );
			int userId = user.getId();
			String username = user.getVcUsername();
			String[] vins = vinstr.split( "," );
			for ( String vin : vins )
			{
				TShipVin entity = new TShipVin();
				entity.setiUser( userId );
				entity.setVcAdduser( username );
				entity.setVcShipno( shipno );
				entity.setVcVin( vin );
				vinService.save( entity );
			}
			return AjaxUtil.getMap( true , "检查装车成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 解除指令和当前订单的绑定
	 * 
	 * @param headId
	 * @param orderId
	 * @param response
	 */
	@RequestMapping( value = "/delBind" , method = RequestMethod.POST )
	@ApiOperation( value = "解除指令和当前订单的绑定" , notes = "解除指令和当前订单的绑定" , response = TShipline.class , position = 5 )
	@ResponseBody
	public Map< String , Object > delBind(
	        @ApiParam( value = "发运主表id" ) @RequestParam( value = "headId" ) int headId ,
	        @ApiParam( value = "订单id" ) @RequestParam( value = "orderId" ) int orderId )
	{
		try
		{
			String[] propertyNames = { "IShiphead" , "IOrderId" };
			Object[] values = { headId , orderId };
			List< TShipline > lines = iShiplineService.findByPropertys( propertyNames ,
			        values );
			if ( CollectionUtils.isNotEmpty( lines ) )
			{
				for ( TShipline line : lines )
				{
					line.setNEnable( SystemConstants.SYS_DISABLE );// 无效
					iShiplineService.update( line );
				}
			}
			return AjaxUtil.getMap( true , "解绑成功" );
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
	 * @param orderId
	 * @param tAssessArrive
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-24 下午1:51:28
	 */
	@RequestMapping( value = "/ariverAssessOrder" , method = RequestMethod.POST )
	@ApiOperation( value = "司机抵达评价接口" , notes = "司机抵达评价接口" )
	@ResponseBody
	public Map< String , Object > ariverAssessOrder(
	        HttpServletRequest request ,
	        @ApiParam( value = "订单ID：orderId" , required = true ) @RequestParam( value = "orderId" , required = true ) String orderId ,
	        @ApiParam( value = "抵达评价封装类" , required = true ) @ModelAttribute TAssessArrive tAssessArrive ,
	        HttpServletResponse response )
	{
		try
		{
			int iOrderId = Integer.parseInt( orderId );
			TOrder tOrder = orderService.get( iOrderId );
			TStores tStores = iStoresService.get( tOrder.getiStoreId() );
			TUser user = ( TUser ) request.getSession().getAttribute( "user" );
			tAssessArrive.setiUser( user.getId() );// 添加人userId
			tAssessArrive.setDtAdd( new Date() );// 添加时间
			tAssessArrive.setVcUsername( user.getVcAccount() );// 添加人姓名
			tAssessArrive.setiUserBy( tStores.getiUserId() );// 被评价用户id(4S店的用户ID)
			tAssessArrive.setiUserAssess( user.getId() );// 评价用户id
			tAssessArrive.setDtAssess( new Date() );
			iAssessArriveService.saveUpdate( tAssessArrive );
			return AjaxUtil.getMap( true , "成功！" );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
}
