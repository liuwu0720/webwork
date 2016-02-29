package com.clt.sub.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.clt.common.Geohash;
import com.clt.sub.model.TSharecircle;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.ISharecircleService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TShareTag;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.model.TUserGps;
import com.clt.systemmanger.service.IPictureService;
import com.clt.systemmanger.service.IUserGpsService;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping( "/sharecircleAction" )
@Controller
@Api( value = "SharecircleAction-api" , description = "分享圈操作api" , position = 5 )
public class SharecircleAction
{
	@Autowired
	private ISubsuppliersService subService;
	@Autowired
	private ISharecircleService shareService;
	@Autowired
	private IUserGpsService gpsService;
	@Autowired
	private IPictureService pictureService;
	@Autowired
	private IDriverService driverService;
	
	/**
	 * @description 获取车队分享记录
	 * @param session
	 * @param request
	 * @return Map< String , Object >
	 * @author chengwzh
	 * @create date 2015/4/30 15:00
	 */
	@ApiOperation( value = "获取车队分享记录" , notes = "获取车队分享记录" , position = 5 )
	@RequestMapping( value = "/driverShareList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > driverShareList( HttpSession session ,
	        HttpServletRequest request )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			Integer subId = user.getiArchive();
			Integer iArchiveType = user.getIArchiveType();
			String vcSubno = null;
			// 判断当前用户是承运方或者司机，并获取对应承运方编号
			if ( SystemConstants.SYS_TARCHIVE_SUB == iArchiveType )
			{
				vcSubno = subService.get( subId ).getVcSubno();
			}
			else if ( SystemConstants.SYS_TARCHIVE_DRIVER == iArchiveType )
			{
				vcSubno = driverService.get( subId ).getVcSubno();
			}
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
			Page p = ServiceUtil.getcurrPage( request );
			HqlHelper helper = new HqlHelper( TSharecircle.class );
			helper.setQueryPage( p );
			helper.addEqual( "vcSubno" , vcSubno );
			helper.addEqual( "IType" , 1 );
			helper.addOrderBy( "dtShare" , "desc" );
			Map< String , Object > result = shareService.findByHelper( helper );
			result = shareService.parseUrl( result );// 翻译图片的url
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Descraption 新增分享圈记录
	 * @param entity
	 * @param request
	 * @param vcLatitude
	 * @param vcLongitude
	 * @author chengwzh
	 * @author response
	 * @create date 2015/4/30 15:28
	 */
	@ApiOperation( value = "新增分享圈记录" , notes = "新增分享圈记录:分享圈属性如下：<br/>"
	        + "private Integer id;主键<br/>" + "private String vcContent;分享内容<br/>"
	        + "private String vcImgpath;图片在服务器中的路径（多张路径用英文逗号分隔，限制最多9张）<br/>"
	        + "private String vcSite;所有位置<br/>"
	        + "private Integer IType;类型：0表示所有人；1表示车队<br/>"
	        + "private String vcSubno;分供方编号<br/>"
	        + "private String vcLongitude;所在经度<br/>"
	        + "private String vcLatitude;所在纬度<br/>" + "private Date dtShare;分享时间<br/>"
	        + "private Integer IUserid;分享人ID<br/>"
	        + "private String vcUsername;分享人名字<br/>"
	        + "private String vcShareTag;分类标签（对应分类标签id）<br/>"
	        + "private String vcHash;经纬度hash值<br/>"
	        + "private String vcHeadImg;分享人图像保存路径<br/>" , response = TSharecircle.class , position = 5 )
	@RequestMapping( value = "/shareAdd" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > saveShareCircle(
	        HttpServletRequest request ,
	        @ApiParam( value = "上传图片用的字段" ) @RequestParam( "files" ) CommonsMultipartFile[] files ,
	        @ApiParam( value = "纬度" ) @RequestParam( "vcLatitude" ) String vcLatitude ,
	        @ApiParam( value = "经度" ) @RequestParam( "vcLongitude" ) String vcLongitude ,
	        @ApiParam( value = "分享圈" ) TSharecircle entity )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		
		int subId = user.getiArchive();
		int typeId = user.getIArchiveType();
		String vcSubno = "";
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )
		{
			vcSubno = driverService.get( subId ).getVcSubno();
		}
		else
		{
			vcSubno = subService.get( subId ).getVcSubno();// 获取分供方编号
		}
		entity.setVcSubno( vcSubno );
		int IUserid = user.getId();// 获取分享人ID
		entity.setIUserid( IUserid );
		String imgPath = user.getVcImgpath();
		if ( StringUtils.isNotBlank( imgPath ) )
		{
			entity.setVcHeadImg( imgPath );// 设置分享人头像路径
		}
		String userName = user.getVcUsername();// 获取分享人名字
		entity.setVcUsername( userName );
		// String vcLatitude = request.getParameter( "vcLatitude" );// 获取纬度
		entity.setVcLatitude( vcLatitude );
		// String vcLongitude = request.getParameter( "vcLongitude" );// 获取经度
		entity.setVcLongitude( vcLongitude );
		Geohash geohash = new Geohash();
		double lat = Double.parseDouble( vcLatitude );
		double lon = Double.parseDouble( vcLongitude );
		String vcHash = geohash.encode( lat , lon );
		entity.setVcHash( vcHash );
		try
		{
			uploadImg( request , files , entity );
			shareService.save( entity );
			// System.out.println( "新增分享圈记录成功............" );
			// 将当前gps信息插入userGps表
			TUserGps gps = new TUserGps();
			gps.setIUser( user.getId() );
			gps.setVcLat( vcLatitude );
			gps.setVcLong( vcLongitude );
			gps.setVcHash( vcHash );
			gps.setVcUser( userName );
			gpsService.save( gps );
			return AjaxUtil.getMap( true , "发布成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 上传图片私有方法
	 * 
	 * @param request
	 * @param files
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void uploadImg( HttpServletRequest request , CommonsMultipartFile[] files ,
	        TSharecircle share ) throws FileNotFoundException , IOException
	{
		for ( int i = 0 ; i < files.length ; i++ )
		{
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				// String source = request.getSession().getServletContext()
				// .getRealPath( "/" );
				String source = pictureService.getPathById( 1 );// 分享圈图片
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
				String imgPath = share.getVcImgpath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					// share.setVcImgpath( path );
					share.setVcImgpath( jpgPath );
				}
				else
				{
					// share.setVcImgpath( imgPath + "," + path );
					share.setVcImgpath( imgPath + "," + jpgPath );
				}
				
			}
		}
	}
	
	/**
	 * android 图片上传处理方法 其他地方可以参考
	 * 
	 * @param files
	 * @param request
	 * @return
	 */
	@RequestMapping( value = "/upload" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > upload(
	        @RequestParam( "files" ) CommonsMultipartFile[] files ,
	        HttpServletRequest request )
	{
		
		for ( int i = 0 ; i < files.length ; i++ )
		{
			
			System.out.println( "fileName---------->" + files[i].getOriginalFilename() );
			
			if ( ! files[i].isEmpty() )
			{
				int pre = ( int ) System.currentTimeMillis();
				try
				{
					// String source =
					// request.getSession().getServletContext().getRealPath(
					// "/" );
					String source = pictureService.getPathById( 1 );// 分享圈图片
					if ( StringUtils.isBlank( source ) )
					{
						return AjaxUtil.getMapByNotException( false , null );
					}
					// 拿到输出流，同时重命名上传的文件
					FileOutputStream os = new FileOutputStream( source + "sharecircle/"
					        + new Date().getTime() + files[i].getOriginalFilename() );
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
					
				}
				catch ( Exception e )
				{
					e.printStackTrace();
					return AjaxUtil.getMapByException( e );
				}
			}
		}
		return AjaxUtil.getMap( true , "图片上传成功" );
	}
	
	@RequestMapping( value = "/imgUpdate" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > imgUpdate( HttpServletRequest request )
	{
		MultipartHttpServletRequest multipartRequest = ( MultipartHttpServletRequest ) request;
		// String source = request.getSession().getServletContext().getRealPath(
		// "/" );
		String source = pictureService.getPathById( 1 );// 分享圈图片
		if ( StringUtils.isBlank( source ) )
		{
			return AjaxUtil.getMapByNotException( false , null );
		}
		try
		{
			Map< String , MultipartFile > fileMap = multipartRequest.getFileMap();
			for ( Map.Entry< String , MultipartFile > ent : fileMap.entrySet() )
			{
				// 上传文件名
				MultipartFile mf = ent.getValue();
				String fileName = mf.getOriginalFilename();
				if ( ! fileName.equals( "" ) && ! ( fileName == null ) )
				{
					String filename = new Date().getTime() + ".jpg";
					// 保存
					File uploadFile = new File( source + "sharecircle/" + filename );
					FileCopyUtils.copy( mf.getBytes() , uploadFile );
					
				}
			}
			return AjaxUtil.getMap( true , "图片上传成功!" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMap( false , "图片上传失败!" );
			
		}
	}
	
	/**
	 * @Description 根据当前经纬度获取附近分享记录
	 * @param request
	 * @return Map< String , Object >
	 * @author chengwzh
	 * @create date 2015/5/5 10:30
	 */
	@ApiOperation( value = "获取附近分享记录" , notes = "获取附近分享记录" , position = 5 )
	@RequestMapping( value = "/nearbySharecircleList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getNearbyShare( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		/*根据经纬度计算附近网店geoHash值*/
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		TUserGps userGps = gpsService.getGpsByUserid( userId );
		String subHash = null;
		if ( userGps != null )
		{
			String vcHash = userGps.getVcHash();
			// 截取geohash值的前4位进行匹配
			subHash = vcHash.substring( 0 , 5 );
		}
		
		Page page = ServiceUtil.getcurrPage( request );// 获取当前页
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		HqlHelper helper = new HqlHelper( TSharecircle.class );
		helper.setQueryPage( page );
		helper.addEqual( "IType" , 0 );
		helper.addLike( "vcHash" , subHash );
		helper.addOrderBy( "dtShare" , "desc" );
		try
		{
			Map< String , Object > result = shareService.findByHelper( helper );
			result = shareService.parseUrl( result );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 获取所有的分享圈标签
	 * @author chengwzh
	 * @date 2015/5/18 16:54
	 */
	@RequestMapping( value = "/getAllTags" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有的分享圈标签 " , notes = "获取所有的分享圈标签</br>"
	        + "list 参数说明：list是 多个标签集合，他们的属性如下</br>" + "private Integer id;主键"
	        + "private Integer nEnable;是否有效</br>" + "private String vcAdduser;添加人</br>"
	        + "private Date dtAdd;添加时间</br>" + "private String vcTag;标签</br>" , position = 5 )
	public Map< String , Object > getAllTags()
	{
		List< TShareTag > tags = shareService.getAllTags();
		JSONArray array = JSONArray.fromObject( tags );
		System.out.println( "tags array:" + array );
		if ( CollectionUtils.isEmpty( tags ) )
		{
			return AjaxUtil.getMapByNotException( false , null );
		}
		else
		{
			return AjaxUtil.getMapByNotException( true , tags );
		}
	}
}
