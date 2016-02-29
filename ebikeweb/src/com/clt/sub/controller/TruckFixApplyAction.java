package com.clt.sub.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.clt.common.Geohash;
import com.clt.sub.model.TApplyPic;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TFixTruck;
import com.clt.sub.model.TShipStatus;
import com.clt.sub.model.TShiphead;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.model.TTruckDriverLlink;
import com.clt.sub.service.IDamagePicService;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.IShipheadService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverLinkService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.sub.service.ITruckFixService;
import com.clt.sub.service.ITruckMapService;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserGps;
import com.clt.systemmanger.service.IMsgRecordService;
import com.clt.systemmanger.service.IPictureService;
import com.clt.systemmanger.service.IUserGpsService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.PushUtils;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.sub.controller
 * @Description: TODO(修车申请审批)
 * @author liuwu
 * @date 2015-1-5 下午5:15:15
 * @version V1.0
 */
@Controller
@RequestMapping( "/fixTruckAction" )
@Api( value = "fixTruck-api" , description = "有关修改车申请审批的API" , position = 5 )
public class TruckFixApplyAction
{
	@Autowired
	IDriverService iDriverService;
	@Autowired
	ITruckDriverService iTruckDriverService;
	@Autowired
	ITruckDriverLinkService iTruckDriverLinkService;
	@Autowired
	private IDriverService driverService;
	@Autowired
	IDamagePicService idamagePicService;
	@Autowired
	IUserService iUserService;
	@Autowired
	IMsgRecordService msgService;
	@Autowired
	ISubsuppliersService subService;
	
	@Autowired
	ITruckFixService itruckFiexService;
	@Autowired
	private IPictureService pictureService;
	@Autowired
	IShipheadService iShipheadService;
	@Autowired
	private ITruckMapService mapService;
	@Autowired
	private JdbcTemplate jdbcDao;
	@Autowired
	private IUserGpsService gpsService;
	
	@RequestMapping( "/fixApproval" )
	@ApiIgnore
	public String fixApproval()
	{
		return "sub/flowApprove/truckfixApply";
		
	}
	
	@ApiOperation( value = "获取有关修车申请审批列表返回json" , notes = "获取有关修改车申请审批列表返回json"
	        + "private Integer nEnable;// 是否有效（0有效，1无效）<br/>"
	        + "private Integer iUser; // 申请人id<br/>"
	        + "private String vcUserName;// 申请人姓名<br/>"
	        + "private Date dtApplay;// 申请时间<br/>"
	        + "private String vcFixPart;// 修车部位<br/>"
	        + "private String vcFixTime;// 修车所需时间<br/>"
	        + "	private Date dtFixTime;// 修车时间(格式：2015-02-02)<br/>"
	        + "private Float nFix;// 修车金额<br/>"
	        + "private String vcFixSite;// 修车地点名字<br/>"
	        + "private String vcLongitude;// 修车经度<br/>"
	        + "private String vcLatitude;// 修车纬度<br/>"
	        + "private String vcShipno;// 调度指令号<br/>"
	        + "private String vcCarNo;// 车牌号<br/>"
	        + "private String vcSubno;// 分供方编号<br/>"
	        + "private Integer iApprove;// 审批人id<br/>"
	        + "private String vcApproveName;// 审批人姓名<br/>"
	        + "private Date dtApprove;// 审批时间<br/>"
	        + "private Integer nApproveResult;// 审批结果(0,通过，1，未审批,2:未通过)<br/>"
	        + "private String vcNote;// 审批备注<br/>"
	        + "private Integer nInvoice;// 是否有发票(0有，1没有，默认为有)<br/>"
	        + "private String vcApplyNote;// 申请备注" + "iUser" , position = 5 )
	@RequestMapping( value = "/getFixTrucklist" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getFixTrucklist(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "申请人" , required = false ) @RequestParam( value = "vcUserName" , required = false ) String vcUserName ,
	        @ApiParam( value = "调度指令号" , required = false ) @RequestParam( value = "vcShipno" , required = false ) String vcShipno ,
	        @ApiParam( value = "车牌号" , required = false ) @RequestParam( value = "vcCarNo" , required = false ) String vcCarNo ,
	        @ApiParam( value = "审批状态(0,审核通过，1，未审批(默认),2:审核不通过)" , required = true ) @RequestParam( value = "nApproveResult" , required = false ) String nApproveResult )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int typeId = user.getIArchiveType();
		int subId = user.getiArchive();
		String vcSubno = "";
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )
		{
			vcSubno = driverService.get( subId ).getVcSubno();
		}
		else
		{
			vcSubno = subService.get( subId ).getVcSubno();// 获取分供方编号
		}
		Map< String , Object > resuMap = new HashMap< String , Object >();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TFixTruck.class );
		hql.setQueryPage( page );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqual( "vcSubno" , vcSubno );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			// hql.addEqual( "nApproveResult" , 1 );
			// String vcShipno = request.getParameter( "vcShipno" );
			// String vcCarNo = request.getParameter( "vcCarNo" );
			if ( StringUtils.isNotBlank( vcShipno ) )
			{
				hql.addEqual( "vcShipno" , vcShipno );
			}
			if ( StringUtils.isNotBlank( vcCarNo ) )
			{
				hql.addEqual( "vcCarNo" , vcCarNo );
			}
			if ( StringUtils.isNotBlank( nApproveResult ) )
			{
				hql.addEqual( "nApproveResult" , Integer.parseInt( nApproveResult ) );
			}
			if ( StringUtils.isNotBlank( vcUserName ) )
			{
				hql.addEqual( "vcUserName" , vcUserName );
			}
			if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，只查询当前司机的
			{
				TDriver tDriver = iDriverService.get( user.getiArchive() );
				hql.addEqual( "iUser" , tDriver.getId() );
			}
			hql.addOrderBy( "id" , "desc" );
			resuMap = itruckFiexService.findAllByHqlHelp( hql );
			
			return AjaxUtil.getMapByResult( visit , resuMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(审核通过)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-1-6 下午1:35:54
	 */
	@ApiOperation( value = "修车申请审批接口，同意或者拒绝审批" , notes = "修车申请审批接口，同意或者拒绝审批" , position = 5 , response = TFixTruck.class )
	@RequestMapping( value = "/sure" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > sure(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "修改申请审批列表ID(多个以数组形式传入，单个就传非0的整数数字)" , required = true ) @RequestParam( "id" ) String array ,
	        @ApiParam( value = "审批结果(0,审核通过，1，未审批(默认),2:审核不通过)" , required = true ) @RequestParam( "vcResult" ) String vcResult ,
	        @ApiParam( value = "审批意见(String类型,进行长度限制(30字内))" , required = false ) @RequestParam( value = "vcNote" , required = false ) String vcNote )
	{
		// String[] applyIds = request.getParameterValues( "array[]" );
		
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			String subbo = subService.get( user.getiArchive() ).getVcSubno();// 所属分供方编号
			String message = itruckFiexService.updateSureList( array , user , subbo ,
			        vcResult , vcNote );
			if ( message.equalsIgnoreCase( "success" ) )
			{
				return AjaxUtil.getMap( true , "审核成功!" );
			}
			else
			{
				return AjaxUtil.getMap( false , "审核失败!原因：" + message );
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
	 * @Description: TODO(修车申请接口(新增、修改申请))
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:25:02
	 */
	@ApiOperation( value = "修车申请接口(新增、修改申请)" , notes = "修车申请接口(新增、修改申请)" , position = 5 , response = TFixTruck.class )
	@RequestMapping( value = "/save" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > save(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "上传图片用的字段" ) @RequestParam( "files" ) CommonsMultipartFile[] files ,
	        @ApiParam( value = "add为新增、update为修改(注:修改的话id不能为空)" , required = true ) @RequestParam( value = "paramType" , required = true ) String paramType ,
	        @ApiParam( value = "修车申请信息(所有信息封装成form对象)" , required = true ) @ModelAttribute TFixTruck tFixTruck )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int typeId = user.getIArchiveType();
		TDriver tDriver = null;
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )// 如果是司机，获取到他的车牌号
		{
			int driverId = user.getiArchive();// 司机ID
			tDriver = iDriverService.get( driverId );
			String[] propertyNames2 = { "NEnable" , "IDriverID" };
			Object[] values2 = { SystemConstants.SYS_ENABLE , driverId };
			List< TTruckDriverLlink > tLlinks = iTruckDriverLinkService.findByPropertys(
			        propertyNames2 , values2 );
			if ( tLlinks.size() > 0 )
			{
				TTruckDriverLlink tlink = tLlinks.get( 0 );
				int truckId = tlink.getITruckID();
				TTruckDriver truckDriver = iTruckDriverService.get( truckId );
				tFixTruck.setVcCarNo( truckDriver.getVcCarNo() );
				tFixTruck.setVcSubno( tDriver.getVcSubno() );
				tFixTruck.setVcUserName( tDriver.getVcDriverName() );
				tFixTruck.setiUser( tDriver.getId() );
				tFixTruck.setVcUserPhone( tDriver.getVcDriverTel() );
				tFixTruck.setDtApplay( new Date() );
				// 获取vcHash
				String vcLatitude = tFixTruck.getVcLatitude();
				String vcLongitude = tFixTruck.getVcLongitude();
				double lat = Double.parseDouble( vcLatitude );
				double lon = Double.parseDouble( vcLongitude );
				Geohash geohash = new Geohash();
				String vcHash = geohash.encode( lat , lon );
				tFixTruck.setVcHash( vcHash );
				
				/**
				 * 验证调度指令 获取该司机拖车所关联的最新指令号
				 */
				String[] properties = { "ITruckId" , "NEnable" };
				Object[] values = { truckId , SystemConstants.SYS_ENABLE };
				String orderByParam = "id";// 需要排序的字段
				List< TShiphead > tShipheads = iShipheadService
				        .findByPropertysOrderBy( properties , values ,
				                orderByParam );
				if ( tShipheads != null && tShipheads.size() > 0 )
				{
					tFixTruck.setVcShipno( tShipheads.get( 0 ).getVcShipno() );
				}
				else
				{
					return AjaxUtil.getMap( false , "查询不到你现在的指令号，你不能申请修车！" );
				}
			}
			else
			{
				return AjaxUtil.getMap( false , "查询不到你所关联的运输车，你不能申请修车！" );
			}

		}
		if ( paramType.equalsIgnoreCase( "add" ) )
		{
			try
			{
				uploadImg( request , files , tFixTruck );
				itruckFiexService.save( tFixTruck );
				saveApplyPic( tFixTruck );// 保存图片到图片表
				
				// 消息推送
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "74" );// 74=修车
				map.put( "id" , tFixTruck.getId() + "" );
				TUser bossUser = iUserService.getByid( tDriver.getiUserId() + "" );
				List< TUser > tUsers = new ArrayList< TUser >();
				tUsers.add( bossUser );
				PushUtils pushUtils = new PushUtils( "有修车申请，请点击查看" , "来自"
				        + tFixTruck.getVcUserName() + "的修车申请" , tUsers ,
				        "com.unlcn.carrier.approvalprocess.FixTruckDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );
				tMsgRecord.setIUserAccept( bossUser.getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "来自" + tFixTruck.getVcUserName() + "的修车申请" );
				tMsgRecord.setVcTitle( "有修车申请，请点击查看" );
				msgService.save( tMsgRecord );
				return AjaxUtil.getMap( true , "保存成功！" );
			}
			catch ( Exception e )
			{
				return AjaxUtil.getMapByException( e );
			}
		}
		if ( paramType.equalsIgnoreCase( "update" ) )
		{
			try
			{
				if ( files != null )
				{
					uploadImg( request , files , tFixTruck );
				}
				
				itruckFiexService.update( tFixTruck );
				return AjaxUtil.getMap( true , "保存成功！" );
			}
			catch ( Exception e )
			{
				return AjaxUtil.getMapByException( e );
			}
		}
		return AjaxUtil.getMap( true , "保存成功！" );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tFixTruck
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-5 上午11:13:40
	 */
	private void saveApplyPic( TFixTruck tFixTruck )
	{
		String[] paths = tFixTruck.getVcPicPath().split( "," );
		for ( String path : paths )
		{
			TApplyPic tApplyPic = new TApplyPic();
			// tApplyPic.setDtAdd( new Date() );
			tApplyPic.setiTableId( tFixTruck.getId() );
			tApplyPic.setVcPicName( path );
			tApplyPic.setVcTableName( "TFIXTRUCK" );
			idamagePicService.saveApplyTdamagePic( tApplyPic );
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param files
	 * @param tFixTruck
	 *            void 返回值描述
	 * @author liuwu
	 * @throws IOException
	 * @create_date 2015-6-4 下午1:31:05
	 */
	private void uploadImg( HttpServletRequest request , CommonsMultipartFile[] files ,
	        TFixTruck tFixTruck ) throws FileNotFoundException , IOException
	{
		for ( int i = 0 ; i < files.length ; i++ )
		{
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				// String source = request.getSession().getServletContext()
				// .getRealPath( "/" );
				String source = pictureService.getPathById( 13 );// 分享圈图片
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
				String imgPath = tFixTruck.getVcPicPath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					// share.setVcImgpath( path );
					tFixTruck.setVcPicPath( jpgPath );
				}
				else
				{
					// share.setVcImgpath( imgPath + "," + path );
					tFixTruck.setVcPicPath( imgPath + "," + jpgPath );
				}
				
			}
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(修车申请)
	 * @param request
	 * @param response
	 * @param id
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:21:32
	 */
	@ApiOperation( value = "修车申请撤销" , notes = "修车申请撤销" , position = 5 )
	@RequestMapping( value = "/delete" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > delete(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "需要修车申请的列表id" , required = true ) @RequestParam( value = "id" , required = true ) String id )
	{
		if ( StringUtils.isNotBlank( id ) )
		{
			TFixTruck tFixTruck = itruckFiexService.findById( Integer.parseInt( id ) );
			tFixTruck.setnEnable( SystemConstants.SYS_DISABLE );
			try
			{
				itruckFiexService.update( tFixTruck );
				return AjaxUtil.getMap( true , "撤销申请成功" );
			}
			catch ( Exception e )
			{
				return AjaxUtil.getMapByException( e );
			}
		}
		else
		{
			return AjaxUtil.getMap( false , "撤销申请失败！没有找到ID为" + id + "的申请" );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(根据id获取详情)
	 * @param request
	 * @param response
	 * @param id
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-7 下午1:31:59
	 */
	@ApiOperation( value = "根据id获取详情" , notes = "private Integer id;// id<br/>"
	        + "private Integer nEnable;// 是否有效（0有效，1无效）<br/>"
	        + "private Integer iUser; // 申请人id<br/>"
	        + "private String vcUserName;// 申请人姓名<br/>"
	        + "private Date dtApplay;// 申请时间<br/>"
	        + "private String vcFixPart;// 修车部位<br/>"
	        + "private String vcFixTime;// 修车所需时间<br/>"
	        + "private Float nFix;// 修车金额<br/>"
	        + "private String vcFixSite;// 修车地点名字<br/>"
	        + "private String vcLongitude;// 修车经度<br/>"
	        + "private String vcLatitude;// 修车纬度<br/>"
	        + "private String vcShipno;// 调度指令号<br/>"
	        + "private String vcCarNo;// 车牌号<br/>"
	        + "private String vcSubno;// 分供方编号<br/>"
	        + "private Integer iApprove;// 审批人id<br/>"
	        + "private String vcApproveName;// 审批人姓名<br/>"
	        + "private Date dtApprove;// 审批时间<br/>"
	        + "private Integer nApproveResult;// 审批结果(0,通过，1，未审批,2:未通过)<br/>"
	        + "private String vcNote;// 审批备注<br/>"
	        + "private Integer nInvoice;// 是否有发票(0有，1没有，默认为有)<br/>"
	        + "private String vcApplyNote;// 申请备注<br/>"
	        + "private String vcUserPhone;// 申请人手机号<br/>"
	        + "private String vcPicPath;// 修车照片路径（多张以“，”相连）" )
	@RequestMapping( value = "/getDetailById" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDetailById(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "id" , required = true ) @RequestParam( value = "id" , required = true ) String id )
	{
		
		try
		{
			
			TFixTruck tFixTruck = itruckFiexService.findById( Integer.parseInt( id ) );
			if ( null != tFixTruck )
			{
				itruckFiexService.parseUrl( tFixTruck );
				return AjaxUtil.getMapByNotException( true , tFixTruck );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , tFixTruck );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	@SuppressWarnings( "unchecked" )
	@RequestMapping( value = "/getPicDetail" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getPicDetail( HttpServletRequest request ,
	        HttpServletResponse response ) throws UnsupportedEncodingException
	{
		int picId = Integer.parseInt( request.getParameter( "id" ) );
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TApplyPic.class );
		hql.setQueryPage( page );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqualIgnoreCase( "vcTableName" , "TFIXTRUCK" );
		hql.addEqual( "iTableId" , picId );
		Map< String , Object > resuMap = idamagePicService
		        .findAllByHqlHelp( hql );
		List< TApplyPic > applyPics = ( List< TApplyPic > ) resuMap
		        .get( "rows" );
		for ( TApplyPic tApplyPic : applyPics )
		{
			itruckFiexService.parseUrl( tApplyPic );
		}
		
		return resuMap;
		
	}
	
	/**
	 * 
	 * @Description: TODO(获取地图)
	 * @param request
	 * @param response
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-8 下午2:37:07
	 */
	@RequestMapping( value = "/getCurrentMap" )
	@ResponseBody
	@ApiIgnore
	public ModelAndView getCurrentMap( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		ModelMap map = new ModelMap();
		int id = Integer.parseInt( request.getParameter( "id" ) );
		TFixTruck tFixTruck = itruckFiexService.findById( id );
		int driverId = tFixTruck.getiUser();// 司机ID
		/**
		 * 找出司机对应的userId
		 */
		String[] properties = { "iArchive" , "IArchiveType" };
		Object[] mainValues = { driverId , SystemConstants.SYS_TARCHIVE_DRIVER };
		
		TUser tUser = iUserService.findByProperties( properties , mainValues )
		        .get( 0 );
		int userId = tUser.getId();
		List< TShiphead > tShipheads = iShipheadService.findByProperty(
		        "vcShipno" , tFixTruck.getVcShipno() );
		List< Map< String , Object >> result = null;
		
		if ( tShipheads != null && tShipheads.size() > 0 )
		{
			
			TShiphead tShiphead = tShipheads.get( 0 );
			// 获取状态时间 取最早的发运时间
			TShipStatus status1 = mapService.getForwardStatus( tShiphead
			        .getId() );
			
			if ( status1 != null )
			{
				
				Date dtForward = status1.getDtStatus();// 获取已发运的时间
				// 根据userId和时间段获取所有的gps并按时间排序
				// HqlHelper hql = new HqlHelper( TUserGps.class );
				// hql.addGreatEqualThan( "dtAdd" , dtForward );// 大于等于发运时间
				SimpleDateFormat format = new SimpleDateFormat(
				        "yyyy-MM-dd HH:mm:ss" );
				String forwadStr = format.format( dtForward );
				String sql = "select  vc_long,vc_lat,vc_hash from t_user_gps where dt_add>=to_date('"
				        + forwadStr + "','yyyy-MM-dd HH24:mi:ss')";
				
				// 取最晚的抵达时间
				TShipStatus status2 = mapService.getArrivedStatus( tShiphead
				        .getId() );
				if ( status2 != null )
				{
					Date dtArrived = status2.getDtStatus();// 获取已运抵的时间
					// hql.addLessEqualThan( "dtAdd" , dtArrived );// 小于等于抵达时间
					String arrivedStr = format.format( dtArrived );
					sql += " and dt_add<=to_date('" + arrivedStr
					        + "','yyyy-MM-dd HH24:mi:ss')";
				}
				sql += " and i_user=" + userId + " order by dt_add";
				// Map< String , Object > result = gpsService.findAllByHqlHelp(
				// hql
				// );
				System.out.println( "sql:" + sql );
				result = jdbcDao.queryForList( sql );
				if ( CollectionUtils.isEmpty( result ) )
				{
					// return AjaxUtil.getMapByNotException( false , null );
					// 如果找不到gps信息取司机最新的gps定位
					TUserGps gps = gpsService.getGpsByUserid( userId );
					Map< String , Object > data = new HashMap< String , Object >();
					if ( gps != null )
					{
						data.put( "VC_LONG" , gps.getVcLong() );
						data.put( "VC_LAT" , gps.getVcLat() );
						data.put( "VC_HASH" , gps.getVcHash() );
						result.add( data );
					}
					
				}
				map.put( "tFixTruck" , tFixTruck );
				result = filterRepeate( result );// 过滤掉重复的gps信息
				if ( result.size() == 1 )
				{
					map.put( "error" , "起始点与终点位置一置" );
				}
			}
			
		}
		else
		{
			map.put( "error" , "查询不到指令号" );
		}
		if ( CollectionUtils.isNotEmpty( result ) )
		{
			JSONArray jsonArray = JSONArray.fromObject( result );
			map.put( "result" , jsonArray );
		}
		
		ModelAndView view = new ModelAndView( "sub/flowApprove/tFixTruckMap" ,
		        map );
		return view;
	}
	
	/**
	 * @Description: TODO(过滤掉重复的gps信息)
	 * @param result
	 * @return List<Map<String,Object>> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-9 下午1:59:54
	 */
	private List< Map< String , Object >> filterRepeate(
	        List< Map< String , Object >> result )
	{
		List< Map< String , Object > > newMapList = new ArrayList< Map< String , Object > >();
		
		for ( int i = 0 ; i < result.size() ; i++ )
		{
			// Map< String , Object > newMap = new HashMap< String , Object >();
			if ( ! newMapList.contains( result.get( i ) ) )
			{
				newMapList.add( result.get( i ) );
			}
		}
		
		return newMapList;
	}

}
