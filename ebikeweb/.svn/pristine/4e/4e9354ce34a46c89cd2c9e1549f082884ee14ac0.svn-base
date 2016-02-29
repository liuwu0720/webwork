package com.clt.sub.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.clt.common.UserSession;
import com.clt.sub.model.TApprovalRecord;
import com.clt.sub.model.TCity;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TDamage;
import com.clt.sub.model.TDetour;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TFineApplay;
import com.clt.sub.model.TFixTruck;
import com.clt.sub.model.THolidayApply;
import com.clt.sub.model.TLoan;
import com.clt.sub.model.TProvince;
import com.clt.sub.model.TSharecircle;
import com.clt.sub.model.TSubCarStyle;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.model.TSubsuppliersBank;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.service.IApprovalRecordService;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.IDamageService;
import com.clt.sub.service.IDetourApplyService;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.IHolidayApplyService;
import com.clt.sub.service.ILoanService;
import com.clt.sub.service.IOrderService;
import com.clt.sub.service.IProvinceService;
import com.clt.sub.service.ISharecircleService;
import com.clt.sub.service.IShipheadService;
import com.clt.sub.service.ISubCarStyleService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITransitFineSerivce;
import com.clt.sub.service.ITruckDriverService;
import com.clt.sub.service.ITruckFixService;
import com.clt.systemmanger.model.TApplyResource;
import com.clt.systemmanger.model.TRole;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IPictureService;
import com.clt.systemmanger.service.IRoleService;
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

/**
 * @Package com.clt.sub.controller
 * @Description: 分供方信息完善及下单资质
 * @author chenbin
 * @date 2014-9-17 上午10:35:04
 * @version V1.0
 */
@Controller
@RequestMapping( "/subsuppliersAction" )
@Api( value = "sub-api" , description = "有关于承运方相关API" , position = 5 )
public class SubsuppliersAction
{
	@Autowired
	IShipheadService shipheadService;
	@Autowired
	ITruckDriverService driverService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	IOrderService orderService;
	@Autowired
	ICityService cityService;
	@Autowired
	IApprovalRecordService appRecordService;
	@Autowired
	IRoleService roleService;
	@Autowired
	IUserService userService;
	@Autowired
	IDriverService iDriverService;
	@Autowired
	ISubCarStyleService subCarStyleService;
	@Autowired
	ICustomerSerivce customerService;
	@Autowired
	ITransitFineSerivce iTransitFineService;
	@Autowired
	IDamageService iDamageService;
	@Autowired
	IHolidayApplyService iHolidayApplyService;
	@Autowired
	IDetourApplyService iDetourApplyService;
	@Autowired
	ITruckFixService iTruckFixService;
	@Autowired
	ILoanService iLoanService;
	@Autowired
	IPictureService iPictureService;
	@Autowired
	ISubsuppliersService iSubsuppliersService;
	@Autowired
	ISharecircleService shareService;
	@Autowired
	private IProvinceService provinceService;
	
	/**
	 * 分供方信息初始化
	 * 
	 * @return
	 */
	@RequestMapping( "/initSub" )
	public String initSub()
	{
		return "sub/personInfo/subInit";
	}
	
	/**
	 * @Description: 获得承运方公司信息，该方法提供给手机
	 * @param request
	 * @return TSubsuppliers 返回值描述
	 * @author hjx
	 * @create_date 2015年4月10日 下午5:06:38
	 */
	@ApiOperation( value = "获得承运方公司信息，手机端使用该api" , notes = "从会话里获得当前用户，并从用户关联获得他的承运方公司信息<br/>"
	        + "private Integer id;// ID<br/>"
	        + "private String vcAllName;// 分供方全称<br/>"
	        + "private String vcSmipleName;// 分供方简称<br/>"
	        + "private String vcAddress;// 分供方地址<br/>"
	        + "private String vcScale;// 分供方规模（有多少辆车）<br/>"
	        + "private Integer NEnable;// 是否有效(0有效，1无效)<br/>"
	        + "private Integer IProvince;// 所属省份ID<br/>"
	        + "private Integer ICity;// 所属城市ID<br/>"
	        + "private String vcArea;// 所属地区<br/>"
	        + "private String vcDetailedAddress;// 详细地址<br/>"
	        + "private String vcRegisterAddress;// 公司注册地址<br/>"
	        + "private Integer NEnableKill;// 是否可抢单(0为可，1为不可，默认为1)<br/>"
	        + "private Integer NEnableOrder;// 是否可下单(0为可，1为不可，2 正在审批 3 审批不通过 默认为1 )<br/>"
	        + "private String vcOrderFile;// 下单资质证明<br/>"
	        + "private String vcKillFile;// 抢单资质证明<br/>"
	        + "private Integer NEnableApply;// 是否可贷款(0为可，1为不可，默认为1)<br/>"
	        + "private String vcSubno;// 分供方编号<br/>"
	        + "private Integer IScore 信誉<br/>"
	        + "private String vcTel;// 分供方电话<br/>"
	        + "private Integer inte;// 积分<br/>"
	        + "private String vcImgPath;// logo路径<br/>"
	        + "private Integer iLoadScore;// 支付信用<br/>"
	        + "private String vcLicencePath;// 营业执照照片路径<br/>"
	        + "private String vcTransportPath;// 运输证照片路径<br/>"
	        + "private String vcCityName;// 所属城市名称" , response = TSubsuppliers.class , position = 5 )
	@RequestMapping( value = "/getSubInfoByApp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getSubInfoByApp( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TSubsuppliers sub;
		try
		{
			TUser user = ( TUser ) session.getAttribute( "user" );
			if ( null == user )
			{
				user = ( TUser ) UserSession.get( "user" );
			}
			sub = subService.get( user.getiArchive() );
			int inte = user.getNIntegral();// 积分
			
			if ( null != sub )
			{
				sub.setInte( inte );
				subService.parseUrl( sub );
				return AjaxUtil.getMapByNotException( true , sub );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , sub );
			}
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(分供方信息完善接口)
	 * @param request
	 * @param files
	 * @param tSubsuppliers
	 * @param array
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @create_date 2015-7-10 上午11:21:34
	 */
	@ApiOperation( value = "分供方信息完善接口" , notes = "分供方信息完善接口" )
	@RequestMapping( value = "/saveSubInfo" , method = RequestMethod.POST )
	public void saveSubInfo(
	        HttpServletRequest request ,
	        @ApiParam( value = "上传LOGO图片" , required = false ) @RequestParam( value = "files" , required = false ) CommonsMultipartFile[] files ,
	        @ApiParam( value = "上传营业执照图片" , required = false ) @RequestParam( value = "licenceFiles" , required = false ) CommonsMultipartFile[] licenceFiles ,
	        @ApiParam( value = "分供方个人信息封装对象" , required = true ) @ModelAttribute TSubsuppliers tSubsuppliers ,
	        @ApiParam( value = "绑定银行卡信息,多个银行卡信息封装JSON格式如：<br/> "
	                + "[{'vcOpenPlace':'中国工商银行','vcAccount':'2145525366355','vcUserName':'李秋风'},{'vcOpenPlace':'中国工商银行','vcAccount':'2145525366355','vcUserName':'李秋风'}]" , required = false ) @RequestParam( value = "bankCardArray" , required = false ) String bankCardArray ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		List< TSubsuppliersBank > tSubsuppliersBanks = null;
		
		try
		{
			// logo
			String logoPath = tSubsuppliers.getVcImgPath();
			if ( StringUtils.isNotBlank( logoPath ) )
			{
				logoPath = logoPath.substring( logoPath.lastIndexOf( "/" ) + 1 );
				tSubsuppliers.setVcImgPath( logoPath );
			}


			if ( files != null )
			{
				upLoadImg( request , tSubsuppliers , files );
			}
			
			// 营业执照
			String licenceImg = tSubsuppliers.getVcLicencePath();
			if ( StringUtils.isNotBlank( licenceImg ) )
			{
				licenceImg = licenceImg
				        .substring( licenceImg.lastIndexOf( "/" ) + 1 );
				tSubsuppliers.setVcLicencePath( licenceImg );
			}
			if ( licenceFiles != null )
			{
				upLoadLicenceImg( request , tSubsuppliers , licenceFiles );
			}
			String headImgPath = tSubsuppliers.getVcImgPath();
			if ( tSubsuppliers.getId() == null )
			{
				tSubsuppliers.setId( user.getId() );
			}
			
			tSubsuppliers.setNEnable( SystemConstants.SYS_ENABLE );
			tSubsuppliers.setNEnableApply( SystemConstants.SYS_ENABLE );
			tSubsuppliers.setNEnableKill( SystemConstants.SYS_ENABLE );
			tSubsuppliers.setNEnableOrder( SystemConstants.SYS_ENABLE );
			TCity vcCity = cityService.getCityByID( tSubsuppliers.getICity() );
			tSubsuppliers.setVcCityName( vcCity.getCityname() );
			iSubsuppliersService.saveOrUpdate( tSubsuppliers );
			// 上传图片的同时更新头部图片到用户表
			int userId = user.getId();
			TUser tuser = userService.getById( userId );
			tuser.setVcImgpath( headImgPath );
			userService.updateCleanBefore( tuser );
			// 更新分享圈图片
			String[] propertyNames = { "IUserid" };
			Object[] values = { user.getId() };
			List< TSharecircle > shares = shareService.findByPropertys( propertyNames ,
			        values );
			for ( TSharecircle share : shares )
			{
				share.setVcHeadImg( headImgPath );
				shareService.updateCleanBefore( share );
			}
			if ( tSubsuppliersBanks != null && tSubsuppliersBanks.size() > 0 )
			{
				for ( TSubsuppliersBank tSubsuppliersBank : tSubsuppliersBanks )
				{
					tSubsuppliersBank.setiSubId( tSubsuppliers.getId() );
					// iSubsuppliersService.saveSubBank( tSubsuppliersBank
					// );
					iSubsuppliersService.saveUpdateSubBank( tSubsuppliersBank );
				}
			}
			
			AjaxUtil.rendJson( response , true , "成功！" );
			
			/*iSubsuppliersService.saveOrUpdate( tSubsuppliers );
			for ( TSubsuppliersBank tSubsuppliersBank : tSubsuppliersBanks )
			{
				tSubsuppliersBank.setiSubId( tSubsuppliers.getId() );
				iSubsuppliersService.saveSubBank( tSubsuppliersBank );
			}
			AjaxUtil.rendJson( response , true , "成功！" );*/

		}
		catch ( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			AjaxUtil.rendJson( response , false , "失败！" + e.getMessage() );
		}
		catch ( IOException e )
		{
			// TODO Auto-generated catch block
			AjaxUtil.rendJson( response , false , "失败！" + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: TODO(上传营业执照)
	 * @param request
	 * @param tSubsuppliers
	 * @param files
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @create_date 2015-9-7 下午2:27:44
	 */
	private void upLoadLicenceImg( HttpServletRequest request ,
	        TSubsuppliers tSubsuppliers , CommonsMultipartFile[] files )
	        throws IOException
	{
		
		for ( int i = 0 ; i < files.length ; i++ )
		{
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				String source = iPictureService.getPathById( 42 );// 图片路径
				if ( ! source.endsWith( "/" ) )
				{
					source += "/";
				}
				if ( StringUtils.isBlank( source ) )
				{
					System.out.println( "source路径查不到！" );
					return;
				}
				String rootPath = source;
				File pathFile = new File( rootPath );
				if ( ! pathFile.exists() )
				{
					pathFile.mkdirs();
				}
				String jpgPath = new Date().getTime() + files[i].getOriginalFilename();
				// path += new Date().getTime() +
				// files[i].getOriginalFilename();
				String path = rootPath + jpgPath;
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
				String imgPath = tSubsuppliers.getVcLicencePath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					tSubsuppliers.setVcLicencePath( jpgPath );
				}
				else
				{
					String existImgPath = rootPath + imgPath;
					File existImg = new File( existImgPath );
					boolean isDel = existImg.delete();
					if ( isDel )
					{
						System.out.println( "图片'" + existImgPath + "'已删除" );
					}
					tSubsuppliers.setVcLicencePath( jpgPath );
				}
				
			}
		}
		
	}
	
	/**
	 * @Description: TODO(处理图片)
	 * @param request
	 * @param tSubsuppliers
	 * @param files
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @create_date 2015-7-10 下午1:45:30
	 */
	private void upLoadImg( HttpServletRequest request , TSubsuppliers tSubsuppliers ,
	        CommonsMultipartFile[] files ) throws FileNotFoundException , IOException
	{
		for ( int i = 0 ; i < files.length ; i++ )
		{
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				String source = iPictureService.getPathById( 42 );// 图片路径
				if ( ! source.endsWith( "/" ) )
				{
					source += "/";
				}
				if ( StringUtils.isBlank( source ) )
				{
					System.out.println( "source路径查不到！" );
					return;
				}
				String rootPath = source;
				File pathFile = new File( rootPath );
				if ( ! pathFile.exists() )
				{
					pathFile.mkdirs();
				}
				String jpgPath = new Date().getTime() + files[i].getOriginalFilename();
				// path += new Date().getTime() +
				// files[i].getOriginalFilename();
				String path = rootPath + jpgPath;
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
				String imgPath = tSubsuppliers.getVcImgPath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					tSubsuppliers.setVcImgPath( jpgPath );
				}
				else
				{
					String existImgPath = rootPath + imgPath;
					File existImg = new File( existImgPath );
					boolean isDel = existImg.delete();
					if ( isDel )
					{
						System.out.println( "图片'" + existImgPath + "'已删除" );
					}
					tSubsuppliers.setVcImgPath( jpgPath );
				}
				System.out.println( "图片'" + path + "'上传成功" );
			}
		}
		
	}
	
	@ApiOperation( value = "获取分供方的客户信息" , notes = "获取分供方客户信息，从当前会话中获得分供方编号，并通过编号，获得该分供方所服务的客户信息" , position = 5 )
	@RequestMapping( value = "/getCustomer" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getCustomer( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		try
		{
			TUser user = ( TUser ) session.getAttribute( "user" );
			if ( null == user )
			{
				user = ( TUser ) UserSession.get( "user" );
			}
			int archid = user.getiArchive();
			
			TSubsuppliers sub = subService.get( archid );
			// 获取分供方车型
			List< TCustomer > custlist = customerService
			        .findAllBySubNo( sub.getVcSubno() );
			if ( CollectionUtils.isNotEmpty( custlist ) )
			{
				return AjaxUtil.getMapByNotException( true , custlist );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , null );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 获取分供方车型，该方法给分供方
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月15日 下午3:42:31
	 */
	@ApiOperation( value = "获取分供方的商品车信息" , notes = "获取分供方的商品车信息，从当前会话中获得分供方编号，并通过编号，获得该分供方所运输的商品车信息" , position = 5 )
	@RequestMapping( value = "/getCarList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getCustlist( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		try
		{
			TUser user = ( TUser ) session.getAttribute( "user" );
			if ( null == user )
			{
				user = ( TUser ) UserSession.get( "user" );
			}
			int archid = user.getiArchive();
			
			TSubsuppliers sub = subService.get( archid );
			// 获取分供方车型
			List< TSubCarStyle > substylist = subCarStyleService.findAll( sub
			        .getVcSubno() );
			if ( CollectionUtils.isNotEmpty( substylist ) )
			{
				return AjaxUtil.getMapByNotException( true , substylist );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , null );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 进入发运指令前加载的数据 获取该分供方可用车辆
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( value = "/getAllShiDateBySubno" , method = RequestMethod.POST )
	@ApiOperation( value = "获取该分供方可用的拖车车辆" , notes = "进入发运指令前加载的数据 获取该分供方可用的拖车车辆,没有被调度指令所指定的车辆" , position = 5 )
	@ResponseBody
	public Map< String , Object > getAllShiDateBySubno( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		// 获取分供方所有可用的车辆信息
		List< TTruckDriver > driverlist;
		try
		{
			TUser user = ( TUser ) session.getAttribute( "user" );
			if ( null == user )
			{
				user = ( TUser ) UserSession.get( "user" );
			}
			String subbo = subService.get( user.getiArchive() ).getVcSubno();
			
			Page page = ServiceUtil.getcurrPage( request );
			
			driverlist = driverService.findByPropertys( new String[] { "NEnable" ,
			        "vcSubno" , "NStatus" } , new Object[] { 0 , subbo , 0 } , page );
			if ( CollectionUtils.isNotEmpty( driverlist ) )
			{
				return AjaxUtil.getMapByNotException( true , driverlist );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , null );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	@RequestMapping( "/SubsuppliersSaveBefore" )
	@ApiIgnore
	public String SubsuppliersSaveBefore( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		TSubsuppliers sub = subService.get( user.getiArchive() );
		Integer proId = sub.getIProvince();
		Integer cityId = sub.getICity();
		TProvince province = null;
		TCity city = null;
		if ( proId != null )
		{
			province = provinceService.get( proId );
		}
		if ( cityId != null )
		{
			city = cityService.getCityByID( cityId );
		}
		
		JSONObject jsonObject = new JSONObject();
		System.out.println( "json : " + jsonObject.fromObject( sub ) );
		// 查看该分供方是否已完善信息
		String paramType = "save";
		if ( sub.getVcAllName() != null && sub.getVcAllName() != "" )
		{
			paramType = "show";
		}
		
		int archid = user.getIArchiveType();
		String hql = " from TRole role where role.NSelect=0 and role.IArchiveType= "
		        + archid;
		List< TRole > rolelist = roleService.find( hql );
		JSONArray arr = new JSONArray();
		JSONObject jsobj = new JSONObject();
		jsobj.element( "id" , "-1" );
		jsobj.element( "name" , "分供方" );
		jsobj.element( "pid" , "-2" );
		arr.add( jsobj );
		
		List< TApplyResource > applist = null;
		for ( int i = 0 ; i < rolelist.size() ; i++ )
		{
			TRole role = rolelist.get( i );
			JSONObject json = new JSONObject();
			json.element( "id" , role.getId() );
			json.element( "name" , role.getVcRoleName() );
			json.element( "pid" , "-1" );
			arr.add( json );
		}
		System.out.println( "arr tostring " + arr.toString() );
		
		// 如果该用户申请开通权限为已审批 (只要审批过就是已审批 不管是否通过)
		if ( user.getNApplyResource() == 2 )
		{
			applist = userService.getApplyResourseByUserID( user.getId() );
			System.out.println( "该用户申请权限条数  " + applist.size() );
			
		}
		request.setAttribute( "applist" , JSONArray.fromObject( applist ).toString() );
		List< TProvince > prolist = cityService.getAllProvince();
		request.setAttribute( "sub" , sub );
		request.setAttribute( "prolist" , prolist );
		request.setAttribute( "paramType" , paramType );
		request.setAttribute( "roleStr" , arr.toString() );
		request.setAttribute( "user" , user );
		request.setAttribute( "pro" , province );// 省
		request.setAttribute( "city" , city );// 市
		return "sub/personInfo/savesubInfo";
		
	}
	
	/**
	 * 
	 * @Description: TODO(保存修改的资料)
	 * @param request
	 * @param response
	 * @param sub
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-26 下午4:55:39
	 */
	@RequestMapping( "/save" )
	@ApiIgnore
	public void save( HttpServletRequest request , HttpServletResponse response ,
	        TSubsuppliers sub )
	{
		// int proid = Integer.parseInt( request.getParameter( "stapro" ) );
		// int cityid = Integer.parseInt( request.getParameter( "stacity" ) );
		/*TProvince pro = cityService.getProvinceByID( proid );
		TCity city = cityService.getCityByID( cityid );*/

		// sub.setIProvince( proid );
		// sub.setICity( cityid );
		// System.out.println( "" + sub.getVcAddress() );
		
		try
		{
			subService.saveOrUpdate( sub );
			AjaxUtil.rendJson( response , true , "保存成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败." + e.getMessage() );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(修改保存) 点击申请权限按钮
	 * @param request
	 * @param response
	 * @param sub
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-26 下午4:56:36
	 */
	@RequestMapping( "/updateinfo" )
	@ApiIgnore
	public void updateinfo( HttpServletRequest request , HttpServletResponse response ,
	        TSubsuppliers sub )
	{
		int proid = Integer.parseInt( request.getParameter( "stapro" ) );
		int cityid = Integer.parseInt( request.getParameter( "stacity" ) );
		TProvince pro = cityService.getProvinceByID( proid );
		TCity city = cityService.getCityByID( cityid );
		
		sub.setIProvince( proid );
		sub.setICity( cityid );
		System.out.println( "" + sub.getVcAddress() );
		
		try
		{
			subService.saveOrUpdate( sub );
			AjaxUtil.rendJson( response , true , "保存成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败." + e.getMessage() );
		}
		
	}
	
	@RequestMapping( "/applyOrder" )
	@ApiIgnore
	public String applyOrder( HttpServletRequest request , HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		TSubsuppliers sub = subService.get( user.getiArchive() );
		
		// 查询未审批的 下单资质
		String hql = " from TApprovalRecord app where app.NType=1 and app.NEnable=0  and app.NIsRead=0 and app.ISubsuppliers="
		        + sub.getId();
		List< TApprovalRecord > recordlist = appRecordService.find( hql );
		
		// 查看该分供方是否已完善信息
		String paramType = "yes";
		if ( sub.getVcAllName() == null || sub.getVcAllName() == "" )
		{
			paramType = "subInfoError";
		}
		
		// 已审批
		if ( sub.getNEnableOrder() == 3 )
		{
			TApprovalRecord appcord = recordlist.get( 0 );
			request.setAttribute( "appcord" , appcord );
		}
		
		request.setAttribute( "paramType" , paramType );
		request.setAttribute( "sub" , sub );
		return "sub/personInfo/subApplyOrder";
	}
	
	/**
	 * @Description: 重新申请 上传下单资质 清楚状态
	 * @param request
	 * @param response
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-19 下午5:38:09
	 */
	@ApiIgnore
	@RequestMapping( "/againApplyOrder" )
	@ResponseBody
	public String againApplyOrder( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		int subID = Integer.parseInt( request.getParameter( "subID" ) );
		TSubsuppliers sub = subService.get( subID );
		sub.setNEnableOrder( 1 );
		sub.setVcOrderFile( null );
		
		// 查询未审批的 NType=1 下单资质
		String hql = " from TApprovalRecord app where app.NType=1 and app.NEnable=0  and app.NIsRead=0 and app.ISubsuppliers="
		        + sub.getId();
		List< TApprovalRecord > recordlist = appRecordService.find( hql );
		
		JSONObject json = new JSONObject();
		json.put( "result" , "ok" );
		try
		{
			subService.update( sub );
			TApprovalRecord appcord = recordlist.get( 0 );
			appcord.setNIsRead( 1 );
			appRecordService.update( appcord );
		}
		catch ( Exception e )
		{
			// TODO: handle exception
			json.put( "result" , e.getMessage() );
		}
		
		return json.toString();
	}
	
	@RequestMapping( "/applyOrderUpload" )
	@ApiIgnore
	public String applyOrderUpload( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		TSubsuppliers sub = subService.get( user.getiArchive() );
		MultipartHttpServletRequest mrequest = ( MultipartHttpServletRequest ) request;
		
		Map< String , MultipartFile > fileMap = mrequest.getFileMap();
		SimpleDateFormat sdff = new SimpleDateFormat( "yyyyMMddHHmmss" );
		
		String basePath = request.getScheme() + "://" + request.getServerName() + ":"
		        + request.getServerPort() + request.getContextPath()
		        + "/subsuppliersAction/applyOrder";
		
		String fpath = "d:\\wlptfiles\\";
		File f = new File( fpath );
		if ( ! f.exists() )
		{
			f.mkdirs();
		}
		
		for ( Map.Entry< String , MultipartFile > entity : fileMap.entrySet() )
		{
			
			// 上传文件名
			MultipartFile mf = entity.getValue();
			String fileName = mf.getOriginalFilename();
			System.out.println( "mf.getSize(); " + mf.getSize() );
			PrintWriter out = null;
			if ( mf.getSize() > 3145728 )
			{
				try
				{
					request.setCharacterEncoding( "UTF-8" );
					out = response.getWriter();
				}
				catch ( Exception e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String msg = "上传文件大小有问题";
				try
				{
					msg = new String( msg.getBytes( "UTF-8" ) , "ISO-8859-1" );
				}
				catch ( UnsupportedEncodingException e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setCharacterEncoding( "UTF-8" );
				response.setContentType( "text/html; charset=UTF-8" );
				
				StringBuilder builder = new StringBuilder();
				builder.append( "<script type=\"text/javascript\">" );
				builder.append( "alert('" + msg + "');" );
				builder.append( "window.location.href='" + basePath + "';" );
				
				builder.append( "</script>" );
				out.print( builder.toString() );
				return null;
			}
			if ( ! fileName.equals( "" ) && ! ( fileName == null ) )
			{
				
				fileName = sdff.format( new Date() ) + "_" + sub.getVcSubno() + "_"
				        + fileName;
				File uploadFile = new File( fpath + fileName );
				
				try
				{
					FileCopyUtils.copy( mf.getBytes() , uploadFile );
					sub.setVcOrderFile( fpath + fileName );
					sub.setNEnableOrder( 2 );
					subService.update( sub );
					
					// TApprovalRecord appRecord = new TApprovalRecord();
					// appRecord.setISubsuppliers( sub.getId() );
					// appRecord.setNType( 1 );
					// appRecordService.save( appRecord );
				}
				catch ( IOException e )
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
		return "redirect:/subsuppliersAction/applyOrder";
	}
	
	// 分供方申请下单权限 跳转页面
	@RequestMapping( "/intogetAllApplyOrder" )
	@ApiIgnore
	public String intogetAllApplyOrder( HttpServletRequest request )
	{
		
		return "sub/subApproval/applyOrderInfoList";
		
	}
	
	// 分供方权限申请 跳转页面
	@RequestMapping( "/intogetAllApplyResource" )
	@ApiIgnore
	public String intogetAllApplyResource( HttpServletRequest request )
	{
		
		return "sub/subApproval/applyResourceInfoList";
		
	}
	
	/**
	 * @Description: 获取所有分供方提出的权限申请信息
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/getAllApplyResource" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getAllApplyResource( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		String userNo = request.getParameter( "userNo" );
		String subNo = request.getParameter( "subNo" );
		
		String sql = "select us.id,us.vc_account,us.vc_username,sub.vc_subno,sub.vc_all_name,sub.vc_smiple_name,sub.vc_address from t_user us,t_subsuppliers sub where us.i_archive=sub.id and us.n_apply_resource=0 and us.i_archive_type="
		        + SystemConstants.SYS_TARCHIVE_SUB;
		
		if ( StringUtils.isNotBlank( userNo ) )
		{
			sql += " and us.vc_account like '%" + userNo + "%'";
		}
		if ( StringUtils.isNotBlank( subNo ) )
		{
			sql += " and sub.vc_subno like '%" + subNo + "%'";
		}
		Map< String , Object > resuMap = userService.getSpringSQL( sql , page );
		
		if ( resuMap.get( "rows" ) != null )
		{
			List relist = ( List ) resuMap.get( "rows" );
			System.out.println( "resuMap size " + relist.size() );
		}
		else
		{
			System.out.println( "无数据  " );
		}
		
		return resuMap;
		
	}
	
	/**
	 * 
	 * @Description: 获取分供方申请权限信息 根据用户ID
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-19 下午2:25:36
	 */
	@RequestMapping( "/getSubApplyResourceByID" )
	@ApiIgnore
	public String getSubApplyResourceByID( HttpServletRequest request )
	{
		int usid = Integer.parseInt( request.getParameter( "usid" ) );
		List< TApplyResource > applist = userService.getApplyResourseByUserID( usid );
		
		JSONArray arr = new JSONArray();
		JSONObject jsobj = new JSONObject();
		jsobj.element( "id" , "-1" );
		jsobj.element( "name" , "分供方" );
		jsobj.element( "pid" , "-2" );
		arr.add( jsobj );
		
		for ( int i = 0 ; i < applist.size() ; i++ )
		{
			TApplyResource app = applist.get( i );
			JSONObject json = new JSONObject();
			json.element( "id" , app.getId() );
			json.element( "roleID" , app.getIRole() );
			json.element( "name" , roleService.get( app.getIRole() ).getVcRoleName() );
			json.element( "pid" , "-1" );
			arr.add( json );
		}
		request.setAttribute( "applist" , arr.toString() );
		return "sub/subApproval/approvlApplyResource";
		
	}
	
	/**
	 * 
	 * @Description: 开通分供方申请的角色权限
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-19 下午2:25:36
	 */
	@RequestMapping( "/execApplyResource" )
	@ApiIgnore
	public void execApplyResource( HttpServletRequest request , HttpServletResponse resp )
	{
		String appids = request.getParameter( "appids" );
		try
		{
			
			userService.saveUserRolsResource( appids );
			AjaxUtil.rendJson( resp , true , "操作成功！" );
			
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( resp , false , "操作失败,原因：" + e.getMessage() );
			
		}
		
	}
	
	/**
	 * @Description: 获取所有分供方提出的下单申请 的审批
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/getAllApplyOrder" )
	@ApiIgnore
	@ResponseBody
	public Map< String , Object > getAllApplyOrder( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		TSubsuppliers sub = subService.get( user.getiArchive() );
		
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TSubsuppliers.class );
		hql.setQueryPage( page );
		
		hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqual( "NEnableOrder" , 2 );
		
		Map< String , Object > resuMap = subService.findAllByHqlHelp( hql );
		
		if ( resuMap.get( "rows" ) != null )
		{
			List relist = ( List ) resuMap.get( "rows" );
			System.out.println( "resuMap size " + relist.size() );
		}
		else
		{
			System.out.println( "无数据  " );
		}
		
		return resuMap;
		
	}
	
	/**
	 * 
	 * @Description: 获取分供方信息 用来审批下单资质
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-19 下午2:25:36
	 */
	@RequestMapping( "/getSubApprovalInfoByID" )
	@ApiIgnore
	public String getSubApprovalInfoByID( HttpServletRequest request )
	{
		int subID = Integer.parseInt( request.getParameter( "subID" ) );
		TSubsuppliers sub = subService.get( subID );
		TProvince pro = cityService.getProvinceByID( sub.getIProvince() );
		TCity city = cityService.getCityByID( sub.getICity() );
		
		request.setAttribute( "sub" , sub );
		request.setAttribute( "pro" , pro );
		request.setAttribute( "city" , city );
		
		return "sub/subApproval/approvlApplyOrder";
		
	}
	
	/**
	 * 
	 * @Description: 审核分供方下单资质
	 * @param request
	 * @return String 返回值描述
	 * @author chenbin
	 * @create_date 2014-9-19 下午2:25:36
	 */
	@RequestMapping( "/approvalApplyOrder" )
	@ApiIgnore
	@ResponseBody
	public String approvalApplyOrder( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		int subID = Integer.parseInt( request.getParameter( "subID" ) );
		TSubsuppliers sub = subService.get( subID );
		int selval = Integer.parseInt( request.getParameter( "selval" ) );
		String remarks = request.getParameter( "remarks" );
		
		System.out
		        .println( "subID" + subID + " selval " + selval + " remarks " + remarks );
		// 通过
		if ( selval == 1 )
		{
			sub.setNEnableOrder( 0 );
		}
		// 没通过的
		else if ( selval == 2 )
		{
			sub.setNEnableOrder( 3 );
		}
		TApprovalRecord appcord = new TApprovalRecord();
		appcord.setNType( 1 );
		appcord.setICheckUser( user.getId() );
		appcord.setDtCheckdate( new Date() );
		appcord.setISubsuppliers( sub.getId() );
		appcord.setVcRemarks( remarks );
		
		JSONObject obj = new JSONObject();
		obj.put( "result" , "ok" );
		try
		{
			subService.update( sub );
			appRecordService.save( appcord );
			
		}
		catch ( Exception e )
		{
			System.out.println( e.getMessage() );
			obj.put( "result" , e.getMessage() );
		}
		
		return obj.toString();
		
	}
	
	/**
	 * 
	 * @Description: 分供方用户申请权限保存
	 * @param request
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-10-8 下午3:59:48
	 */
	@RequestMapping( "/saveApplyResource" )
	@ApiIgnore
	public void saveApplyResource( HttpServletRequest request , HttpServletResponse resp )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String roleids = request.getParameter( "roleids" );
		System.out.println( "roleids  " + roleids );
		try
		{
			subService.saveApplyResourseByRoles( user.getId() , roleids );
			request.getSession().setAttribute( "user" ,
			        userService.getByid( user.getId() + "" ) );
			AjaxUtil.rendJson( resp , true , "操作成功！" );
			
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( resp , false , "操作失败,原因：" + e.getMessage() );
			
		}
	}
	
	@RequestMapping( value = "/getHaveDoneList" , method = RequestMethod.POST )
	@ApiOperation( value = "获取承运角色的已办事项，分为罚款、质损、请假、绕路、修车等" , notes = "已办事项：即管理员审核过的列表，包括审批通过的和审批没有通过的" )
	@ResponseBody
	public Map< String , Object > getHaveDoneList(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "申请人" , required = false ) @RequestParam( value = "vcUserName" , required = false ) String vcApplyUser ,
	        @ApiParam( value = "已办事项类型，D:贷款；F：罚款；Z:质损；Q:请假；Y:绕路;X:修车" , required = true ) @RequestParam( value = "thingType" , required = true ) String thingType )
	{
		
		Page page = ServiceUtil.getcurrPage( request );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		String vcSubno = "";
		int typeId = user.getIArchiveType();
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )
		{
			vcSubno = iDriverService.get( subId ).getVcSubno();
		}
		else
		{
			vcSubno = subService.get( subId ).getVcSubno();// 获取分供方编号
		}
		Map< String , Object > resultMap = null;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			if ( thingType.equalsIgnoreCase( "F" ) )// 罚款
			{
				/*sql = "select * from T_FINE_APPLAY t where t.n_approve_result <>1 and t.n_enable=0 and t.vc_subno = '"
				        + subNo + "'";*/
				HqlHelper helper = new HqlHelper( TFineApplay.class );
				helper.setQueryPage( page );
				helper.addEqual( "vcSubno" , vcSubno );
				helper.addNotEqual( "nApproveResult" , 1 );
				helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
				if ( StringUtils.isNotBlank( vcApplyUser ) )
				{
					helper.addEqual( "vcNserName" , vcApplyUser );
				}
				if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，只查询当前司机的
				{
					TDriver tDriver = iDriverService.get( user.getiArchive() );
					helper.addEqual( "IUser" , tDriver.getId() );
				}
				helper.addOrderBy( "id" , "desc" );
				resultMap = iTransitFineService.findAllByHqlHelp( helper );
				
			}
			if ( thingType.equalsIgnoreCase( "Z" ) )// 质损
			{
				/*sql = "select * from T_DAMAGE t where t.n_approve_result <>1 and t.n_enable=0 and t.vc_subno = '"
				        + subNo + "'";*/
				HqlHelper helper = new HqlHelper( TDamage.class );
				helper.setQueryPage( page );
				helper.addEqual( "vcSubno" , vcSubno );
				helper.addNotEqual( "nApproveResult" , 1 );
				helper.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
				if ( StringUtils.isNotBlank( vcApplyUser ) )
				{
					helper.addEqual( "vcUserName" , vcApplyUser );
				}
				if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，只查询当前司机的
				{
					TDriver tDriver = iDriverService.get( user.getiArchive() );
					helper.addEqual( "iUser" , tDriver.getId() );
				}
				helper.addOrderBy( "id" , "desc" );
				resultMap = iDamageService.findAllByHqlHelp( helper );
				
			}
			if ( thingType.equalsIgnoreCase( "Q" ) )// 请假
			{
				/*sql = "select * from t_holiday_applay t where t.n_approve_result <>1 and t.n_enable=0 and t.vc_subno = '"
				        + subNo + "'";*/
				HqlHelper helper = new HqlHelper( THolidayApply.class );
				helper.setQueryPage( page );
				helper.addEqual( "vcSubNo" , vcSubno );
				helper.addNotEqual( "nApproveResult" , 1 );
				helper.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
				if ( StringUtils.isNotBlank( vcApplyUser ) )
				{
					helper.addEqual( "vcUserName" , vcApplyUser );
				}
				if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，只查询当前司机的
				{
					TDriver tDriver = iDriverService.get( user.getiArchive() );
					helper.addEqual( "iUser" , tDriver.getId() );
				}
				helper.addOrderBy( "id" , "desc" );
				resultMap = iHolidayApplyService.findAllByHqlHelp( helper );
				
			}
			if ( thingType.equalsIgnoreCase( "Y" ) )// 绕路
			{
				/*sql = "select * from t_detour t where t.n_approve_result <>1 and t.n_enable=0 and t.vc_subno = '"
				        + subNo + "'";*/
				HqlHelper helper = new HqlHelper( TDetour.class );
				helper.setQueryPage( page );
				helper.addEqual( "vcSubno" , vcSubno );
				helper.addNotEqual( "nApproveResult" , 1 );
				helper.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
				if ( StringUtils.isNotBlank( vcApplyUser ) )
				{
					helper.addEqual( "vcUserName" , vcApplyUser );
				}
				if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，只查询当前司机的
				{
					TDriver tDriver = iDriverService.get( user.getiArchive() );
					helper.addEqual( "IUser" , tDriver.getId() );
				}
				helper.addOrderBy( "id" , "desc" );
				resultMap = iDetourApplyService.findAllByHqlHelp( helper );
			}
			if ( thingType.equalsIgnoreCase( "X" ) )// 修车
			{
				HqlHelper hqlHelper = new HqlHelper( TFixTruck.class );
				hqlHelper.setQueryPage( page );
				hqlHelper.addEqual( "vcSubno" , vcSubno );
				hqlHelper.addNotEqual( "nApproveResult" , 1 );
				hqlHelper.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
				if ( StringUtils.isNotBlank( vcApplyUser ) )
				{
					hqlHelper.addEqual( "vcUserName" , vcApplyUser );
				}
				if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，只查询当前司机的
				{
					TDriver tDriver = iDriverService.get( user.getiArchive() );
					hqlHelper.addEqual( "iUser" , tDriver.getId() );
				}
				hqlHelper.addOrderBy( "id" , "desc" );
				resultMap = iTruckFixService.findAllByHqlHelp( hqlHelper );
			}
			if ( thingType.equalsIgnoreCase( "D" ) )// 贷款
			{
				HqlHelper hqlHelper = new HqlHelper( TLoan.class );
				hqlHelper.setQueryPage( page );
				hqlHelper.addEqual( "vcSubno" , vcSubno );
				hqlHelper.addNotEqual( "NApprovalResult" , 1 );
				hqlHelper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
				if ( StringUtils.isNotBlank( vcApplyUser ) )
				{
					hqlHelper.addEqual( "vcApplyUserName" , vcApplyUser );
				}
				
				if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，只查询当前司机的
				{
					hqlHelper.addEqual( "IApplyUser" , user.getId() );
				}
				hqlHelper.addOrderBy( "id" , "desc" );
				resultMap = iLoanService.findAllByHqlHelp( hqlHelper );
			}
			
			return AjaxUtil.getMapByResult( visit , resultMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 指定参与秒杀承运方，发布抢单里，指定范围
	 * 
	 * @return
	 */
	@ApiOperation( value = "指定参与秒杀承运方" , notes = "指定参与秒杀承运方，发布抢单里，指定范围" , position = 5 )
	@RequestMapping( value = "/getSubByEnableKill" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getSubByEnableKill(
	        @ApiParam( value = "查询参数，主要是按名称查询承运方" ) @RequestParam( value = "name" , required = false ) String name )
	{
		
		try
		{
			List< TSubsuppliers > list = subService.getByEnableKill( name );
			if ( CollectionUtils.isNotEmpty( list ) )
			{
				return AjaxUtil.getMapByNotException( true , list );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , null );
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
	 * @Description: TODO(获取该分供方所有的拖车车辆)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-14 下午2:40:43
	 */
	@RequestMapping( value = "/getAllTruckBySubno" , method = RequestMethod.POST )
	@ApiOperation( value = "获取该分供方所有的拖车车辆" , notes = "获取该分供方所有的拖车车辆，查询车队收入时调用" , position = 5 )
	@ResponseBody
	public Map< String , Object > getAllTruckBySubno( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		// 获取分供方所有可用的车辆信息
		List< TTruckDriver > driverlist;
		try
		{
			TUser user = ( TUser ) session.getAttribute( "user" );
			if ( null == user )
			{
				user = ( TUser ) UserSession.get( "user" );
			}
			String subbo = subService.get( user.getiArchive() ).getVcSubno();
			
			Page page = ServiceUtil.getcurrPage( request );
			
			driverlist = driverService.findByPropertys( new String[] { "NEnable" ,
			        "vcSubno" } , new Object[] { 0 , subbo } , page );
			if ( CollectionUtils.isNotEmpty( driverlist ) )
			{
				return AjaxUtil.getMapByNotException( true , driverlist );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , null );
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
	 * @Description: TODO(手机端：根据拖车ID获取该拖车详细信息)
	 * @param request
	 * @param response
	 * @param id
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-7-14 下午2:48:42
	 */
	@RequestMapping( value = "/getTruckDetailById" , method = RequestMethod.POST )
	@ApiOperation( value = "根据拖车ID获取该拖车详细信息" , notes = "根据拖车ID获取该拖车详细信息" , position = 5 )
	@ResponseBody
	public Map< String , Object > getTruckDetailById(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "拖车id" , required = true ) @RequestParam( value = "id" , required = true ) String id )
	{
		int truckId = Integer.parseInt( id );
		try
		{
			TTruckDriver truckDriver = driverService.get( truckId );
			if ( null != truckDriver )
			{
				return AjaxUtil.getMapByNotException( true , truckDriver );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , truckDriver );
			}
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	// 网页端完善分供方信息
	@RequestMapping( "/updateSub" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > updateSub( TSubsuppliers entity ,
	        @RequestParam( value = "file" , required = false ) MultipartFile file )
	{
		try
		{
			Integer subId = entity.getId();
			if ( subId == null )
			{
				return AjaxUtil.getMap( false , "保存失败：主键为空" );
			}
			else
			{
				uploadFile( file , entity );
				TSubsuppliers sub = subService.get( subId );
				entity.setNEnable( sub.getNEnable() );
				entity.setNEnableKill( sub.getNEnableKill() );
				entity.setNEnableOrder( sub.getNEnableOrder() );
				entity.setNEnableApply( sub.getNEnableApply() );
				entity.setVcSubno( sub.getVcSubno() );
				entity.setVcTel( sub.getVcTel() );
				entity.setIScore( sub.getIScore() );
				entity.setiLoadScore( sub.getiLoadScore() );
				subService.updateCleanBefore( entity );
			}
			Map< String , Object > result = AjaxUtil.getMap( true , "保存成功" );
			// 获取客户编号
			String subno = entity.getVcSubno();
			String vcCustomerNo = customerService.getVcCustomerNo( subno );
			result.put( "vcCustomNo" , vcCustomerNo );
			return result;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 上传分供方logo图片
	 */
	@RequestMapping( value = "/uploadFile" , method = RequestMethod.POST )
	@ApiIgnore
	// @ApiOperation( value = "上传文件" , notes = "上传文件" , response =
	// TAppVersion.class , position = 5 )
	public void uploadFile( MultipartFile file , TSubsuppliers entity )
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
				String rootPath = iPictureService.getPathById( 26 );// 获取分供方根目录
				if ( StringUtils.isBlank( rootPath ) )
				{
					return;
				}
				if ( ! rootPath.endsWith( "/" ) )
				{
					rootPath += "/";
				}
				rootPath += "sub/";
				File f = new File( rootPath );
				if ( ! f.exists() )
				{
					f.mkdirs();
				}
				String path = rootPath + filePath;
				out = new BufferedOutputStream( new FileOutputStream( path ) );
				out.write( bytes );
				entity.setVcImgPath( filePath );
				// entity.setVcAppUrl( filePath );
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
