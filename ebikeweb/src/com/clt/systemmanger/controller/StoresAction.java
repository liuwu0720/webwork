/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-11 下午2:46:02
 * @version V1.0
 */
package com.clt.systemmanger.controller;

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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.clt.common.UserSession;
import com.clt.sub.model.TAssess;
import com.clt.sub.model.TCity;
import com.clt.sub.model.TOrder;
import com.clt.sub.model.TProvince;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.IOrderService;
import com.clt.sub.service.IProvinceService;
import com.clt.systemmanger.model.TStores;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IPictureService;
import com.clt.systemmanger.service.IStoresService;
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
 * @Package com.clt.systemmanger.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-11 下午2:46:02
 * @version V1.0
 */
@Controller
@RequestMapping( "/tstoresAction" )
@Api( value = "StoresAction-api" , description = "有关4S店的API" , position = 5 )
public class StoresAction
{
	@Autowired
	IStoresService iStoresService;
	@Autowired
	IOrderService iOrderService;
	@Autowired
	IUserService userService;
	@Autowired
	private IPictureService pictureService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private IProvinceService provinceService;
	
	@ApiOperation( value = "手机端4S店获取订单接口(4S店帐号：15135367659)" , notes = "ID:订单表ID<BR/>LINEID:指令明细ID<br/>"
	        + "VC_START_CITY:起始地<br/>VC_DEST_CITY:目的地<br/>N_TOTAL_CAR:总数<br/>"
	        + "VC_CUST_ORDERNO:客户运单号<br/>VC_CAR_NAME:车型<br/>VC_ORDERNO:订单号<br/>I_EVALUATION:是否评价（0为未评价，1为已评价）" , position = 5 )
	@RequestMapping( value = "/getStoresOrders" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getStoresOrders(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "订单类型(N:未抵达;Y:已抵达)" ) @RequestParam( value = "type" ) String type ,
	        @ApiParam( value = "客运单号(vcCustomerNo:客户运单号)" ) @RequestParam( value = "vcCustomerNo" , required = false ) String vcCustomerNo )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		Page page = ServiceUtil.getcurrPage( request );
		Map< String , Object > orderMap;
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		int storeId = user.getiArchive();// 对应类型表的ID
		int typeId = user.getIArchiveType();// 类型ID
		if ( SystemConstants.SYS_TARCHIVE_STORES == typeId )
		{
			String sql = "select t.vc_cust_orderno,t.vc_orderno,t.id, t.vc_start_city, t.vc_dest_city, "
			        + "t.n_total_car,t.vc_car_name,t.N_EVALUATION,line.id as lineId"
			        + "  from T_ORDER t  left join t_Shipline line on t.id = line.i_order_id";
			if ( type.equalsIgnoreCase( "Y" ) )// 已抵达
			{
				sql += " where line.n_current_status > 15 ";
			}
			if ( type.equalsIgnoreCase( "N" ) )// 未抵达
			{
				sql += " where line.n_current_status < 18 ";
			}
			if ( StringUtils.isNotBlank( vcCustomerNo ) )
			{
				sql += " and t.VC_CUST_ORDERNO = '" + vcCustomerNo + "'";
			}
			sql += " and t.I_STOREID = "
			        + storeId
			        + " and t.N_ENABLE=0  order by t.N_EVALUATION asc, t.id desc,t.vc_cust_orderno  ";
			System.out.println( "sql = " + sql );
			try
			{
				orderMap = iStoresService.getSpringSQL( sql , page );
				List< Map< String , Object >> arlist = ( List< Map< String , Object >> ) orderMap
				        .get( "rows" );
				String custNo = "";
				String vcEvalution = "";
				Map< String , Object > rMap = null;
				Map< String , Object > rMap2 = null;
				List< Map< String , Object > > resultList = new ArrayList< Map< String , Object > >();
				List itemList = null;
				List itemList2 = null;
				for ( Map< String , Object > map : arlist )
				{
					System.out.println( "***=" + map.get( "VC_CUST_ORDERNO" ) );
					if ( null != map.get( "VC_CUST_ORDERNO" ) )
					{
						
						if ( ! custNo.equals( map.get( "VC_CUST_ORDERNO" ) ) )
						{
							custNo = map.get( "VC_CUST_ORDERNO" ).toString();
							vcEvalution = map.get( "N_EVALUATION" ).toString();
							rMap = new HashMap< String , Object >();
							
							itemList = new ArrayList();
							
							rMap.put( "custNo" , custNo );
							rMap.put( "N_EVALUATION" , vcEvalution );
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
						rMap2.put( "custNo" , "客户单号为空" );
						rMap2.put( "I_EVALUATION" , map.get( "N_EVALUATION" ).toString() );
						itemList2.add( map );
						rMap2.put( "item" , itemList2 );
					}
					//
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
					return AjaxUtil.getMap( false , "查询不到数据" );
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
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 * @param vcStoreName
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-12 下午1:31:59
	 */
	@ApiOperation( value = "获取所有4S店列表详情接口" , notes = "private Integer id;<br/>"
	        + "private Integer nEnable;//是否有效（0有效，1无效）<br/>"
	        + "private Integer iProvinceId;//所属省份ID<br/>"
	        + "private Integer iCityId;//所属城市ID<br/>"
	        + "private String vcArea;//所属地区<br/>"
	        + "private String vcDetailAddress;//详细地址<br/>"
	        + "private String vcStoreNo;//4S店编号<br/>"
	        + "private String vcTel;//4S店电话<br/>" + "private Float iScore;// 信誉<br/>"
	        + "private String vcProvince;//省份<br/>" + "private String vcCity;//城市<br/>"
	        + "private String vcRegisterAddress;//公司注册地址<br/>"
	        + "private String vcImagePath;//图片路径<br/>"
	        + "private String vcStoreName;// 4S店名称" , position = 5 )
	@RequestMapping( value = "/getStoresList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getStoresList(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "vcStoreName" , required = false ) @RequestParam( value = "vcStoreName" , required = false ) String vcStoreName )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( TStores.class );
		hql.setQueryPage( page );
		Map< String , Object > storesMap;
		try
		{
			if ( StringUtils.isNotBlank( vcStoreName ) )
			{
				hql.addLike( "vcStoreName" , vcStoreName );
				
			}
			hql.addIsNotNull( "vcStoreName" );
			storesMap = iStoresService.findByHql( hql );
			return AjaxUtil.getMapByNotException( true , storesMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(根据ID获取4S店详情)
	 * @param request
	 * @param response
	 * @param id
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-15 下午3:44:33
	 */
	@RequestMapping( value = "/getStoresDetail" , method = RequestMethod.POST )
	@ApiOperation( value = "根据当前用户获取4S店详情" , notes = "根据当前用户获取4S店详情" )
	@ResponseBody
	public Map< String , Object > getStoresDetailById( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int storeId = user.getiArchive();// 对应类型表的ID
		/*List< TUser > tUsers = userService.loadAllByEnable();
		Map< String , String > map = new HashMap< String , String >();
		map.put( "msgType" , "71" );// 消息类型
		map.put( "id" , "2" );// id
		PushUtils pushUtils = new PushUtils( "消息标题" , "消息内容......" , tUsers ,
		        "com.unlcn.carrier.approvalprocess.DamageDetailActivity" , map );
		pushUtils.run();*/
		try
		{
			TStores tStores = iStoresService.getById( storeId );
			iStoresService.parseUrl( tStores );
			return AjaxUtil.getMapByNotException( true , tStores );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(4S对所运输的订单进行评价的接口)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-12 下午2:00:40
	 */
	@ApiOperation( value = "4S对所运输的订单进行评价的接口" , notes = "4S对所运输的订单进行评价的接口" )
	@RequestMapping( value = "/saveEvalution" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > saveEvalution(
	        HttpServletRequest request ,
	        @ApiParam( value = "订单客户运单号:vcCustOrderNo" , required = false ) @RequestParam( value = "vcCustOrderNo" ) String vcCustOrderNo ,
	        @ModelAttribute TAssess tAssess )
	{
		/*int orderId = Integer.parseInt( vcOrderId );
		TShiphead tShiphead = iOrderService.getByOrderId( orderId );*/
		String[] propertyNames = { "vcCustOrderNo" , "NEnable" };
		Object[] values = { vcCustOrderNo , SystemConstants.SYS_ENABLE };
		List< TOrder > orders = iOrderService.findByPropertys( propertyNames , values );
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		tAssess.setDtAdd( new Date() );
		tAssess.setVcAdduser( user.getVcAccount() );
		if ( orders.size() > 0 )
		{
			tAssess.setVcSubno( orders.get( 0 ).getVcSubno() );
			
		}
		tAssess.setVcBussinesNo( vcCustOrderNo );
		tAssess.setnType( 1 );
		
		try
		{
			for ( TOrder tOrder : orders )
			{
				tOrder.setnEvalution( 1 );
				iOrderService.save( tOrder );
			}
			iStoresService.saveAssess( tAssess );
			return AjaxUtil.getMap( true , "操作成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 增加修改4s店信息
	 * @param entity
	 * @param files
	 * @param response
	 * @param request
	 * @author chengwzh
	 * @Date 2015/6/30 11:00
	 */
	@RequestMapping( value = "/saveStores" , method = RequestMethod.POST )
	// @ApiOperation( value = "增加/修改4s店信息" , notes = "增加/修改4s店信息" , response =
	// TStores.class , position = 5 )
	@ApiIgnore
	public void saveStores(
	        @ModelAttribute TStores entity ,
	        @ApiParam( value = "上传图片用的字段" ) @RequestParam( value = "files" , required = false ) CommonsMultipartFile[] files ,
	        HttpServletResponse response , HttpServletRequest request )
	{
		Integer provinceId = entity.getiProvinceId();
		if ( provinceId != null )
		{
			TProvince province = provinceService.get( provinceId );
			entity.setVcProvince( province.getVcProvinceName() );
		}
		Integer cityId = entity.getiCityId();
		if ( cityId != null )
		{
			TCity city = cityService.getCityByID( cityId );
			entity.setVcCity( city.getCityname() );
		}
		try
		{
			if ( entity.getId() == null )
			{
				uploadImg( request , files , entity );
				iStoresService.save( entity );
			}
			else
			{
				// 修改时对图片路径处理
				String imgPath = entity.getVcImagePath();
				if ( StringUtils.isNotBlank( imgPath ) )
				{
					imgPath = imgPath.substring( imgPath.lastIndexOf( "/" ) + 1 );
					entity.setVcImagePath( imgPath );
				}
				uploadImg( request , files , entity );
				iStoresService.update( entity );
			}
			AjaxUtil.rendJson( response , true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败：" + e.getMessage() );
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
	        TStores store ) throws FileNotFoundException , IOException
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
				String source = pictureService.getPathById( 24 );// 4s店图片（营业执照）
				if ( StringUtils.isBlank( source ) )
				{
					System.out.println( "source路径查不到！" );
					return;
				}
				if ( ! source.endsWith( "/" ) )
				{
					source += "/";
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
				String imgPath = store.getVcImagePath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					store.setVcImagePath( jpgPath );
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
					store.setVcImagePath( jpgPath );
				}
				System.out.println( "图片'" + path + "'上传成功" );
			}
		}
	}
	
	/**
	 * 上传4s店logo图片私有方法
	 * 
	 * @param request
	 * @param files
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void uploadLogoImg( HttpServletRequest request ,
	        CommonsMultipartFile[] files , TStores store ) throws FileNotFoundException ,
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
				String source = pictureService.getPathById( 24 );// 4s店logo图片
				if ( StringUtils.isBlank( source ) )
				{
					System.out.println( "source路径查不到！" );
					return;
				}
				if ( ! source.endsWith( "/" ) )
				{
					source += "/";
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
				String imgPath = store.getVcLogoPath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					// store.setVcImagePath( jpgPath );
					store.setVcLogoPath( jpgPath );
				}
				else
				{
					String existImgPath = rootPath + imgPath;
					File existImg = new File( existImgPath );
					boolean isDel = existImg.delete();
					if ( isDel )
					{
						System.out.println( "logo图片'" + existImgPath + "'已删除" );
					}
					store.setVcLogoPath( jpgPath );
				}
				System.out.println( "logo图片'" + path + "'上传成功" );
			}
		}
	}
	
	/**
	 * @Description 获取省列表
	 * @return
	 */
	@RequestMapping( value = "/getProvinceList" , method = RequestMethod.POST )
	@ApiOperation( value = "获取省列表" , notes = "获取省列表" , position = 5 )
	@ResponseBody
	public Map< String , Object > getProvinceList()
	{
		try
		{
			List< TProvince > provinces = cityService.getAllProvince();
			return AjaxUtil.getMapByNotException( true , provinces );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 通过省ID获取该省所有的城市
	 * @param provinceId
	 * @return
	 */
	@RequestMapping( value = "/getCitysByProId" , method = RequestMethod.POST )
	@ApiOperation( value = "通过省ID获取该省所有的城市" , notes = "通过省ID获取该省所有的城市" , position = 5 )
	@ResponseBody
	public Map< String , Object > getCitysByProId(
	        @ApiParam( "省id" ) @RequestParam( value = "provinceId" ) int provinceId )
	{
		try
		{
			List< TCity > citys = cityService.getCitysByProID( provinceId );
			return AjaxUtil.getMapByNotException( true , citys );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	@RequestMapping( value = "/updateStores" , method = RequestMethod.POST )
	@ApiOperation( value = "4s店信息完善" , notes = "4s店信息完善" , position = 5 , response = TStores.class )
	@ResponseBody
	public Map< String , Object > updateStores(
	        @ModelAttribute TStores entity ,
	        @ApiParam( "上传营业执照用的字段" ) @RequestParam( value = "logoImg" , required = false ) CommonsMultipartFile[] logoImg ,
	        @ApiParam( "上传logo图片用的字段" ) @RequestParam( value = "lisenceImg" , required = false ) CommonsMultipartFile[] lisenceImg ,
	        HttpServletRequest request , HttpServletResponse response )
	{
		try
		{
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			entity.setiUserId( user.getId() );
			// 修改时对图片路径处理
			String logoPath = entity.getVcLogoPath();
			if ( StringUtils.isNotBlank( logoPath ) )
			{
				logoPath = logoPath.substring( logoPath.lastIndexOf( "/" ) + 1 );
				entity.setVcLogoPath( logoPath );
			}
			uploadLogoImg( request , logoImg , entity );// 上传4s店logo
			// 修改时对图片路径处理
			String imgPath = entity.getVcImagePath();
			if ( StringUtils.isNotBlank( imgPath ) )
			{
				imgPath = imgPath.substring( imgPath.lastIndexOf( "/" ) + 1 );
				entity.setVcImagePath( imgPath );
			}
			uploadImg( request , lisenceImg , entity );// 上传4s店营业执照
			iStoresService.update( entity );
			return AjaxUtil.getMap( true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
}
