package com.clt.sub.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.common.UserSession;
import com.clt.sub.model.TSpareCapacity;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ILimitCheckService;
import com.clt.sub.service.IProvinceService;
import com.clt.sub.service.ISpareCapacityService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.sub.service.ITruckDriverService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IIntegalCutService;
import com.clt.util.AjaxUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.sub.controller
 * @Description: 分供方/司机发布空闲运力
 * @author chenbin
 * @date 2014-9-17 上午10:35:04
 * @version V1.0
 */
@Controller
@RequestMapping( "/spareCapacityAction" )
@Api( value = "SpareCapacityAction-api" , description = "分供方/司机发布空闲运力的CURD操作" , position = 5 )
public class SpareCapacityAction
{
	@Autowired
	private ISubsuppliersService subService;
	@Autowired
	private ISpareCapacityService spareService;
	@Autowired
	private IIntegalCutService integalCutService;
	@Autowired
	private ITruckDriverService truckService;
	@Autowired
	private IProvinceService provinceService;
	@Autowired
	private ICityService cityService;
	@Autowired
	private ILimitCheckService limitService;
	
	// 分供方/司机 发布空闲运力列表页面
	@RequestMapping( "/intogetAllCapacityBySelf" )
	@ApiIgnore
	public String intogetAllCapacityBySelf( HttpServletRequest request )
	{
		
		return "sub/spareCapacity/spareCapacityInfoList";
		
	}
	
	/**
	 * @Description: 获取所有的空闲运力信息 根据当前用户ID查询
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-15 上午11:58:58
	 */
	@ApiOperation( value = "运力平台-发布记录" , notes = "admin 用户有数据，获取所有的空闲运力信息 根据当前用户ID查询，根据空闲运力id 降序排序;隐藏可用参数信息：page第几页 默认为1；   rows 每页展示多少行，默认为10； sort 排列方式（升级或者降序） 可不填写； order 排列项， 可不填写" , position = 5 )
	@RequestMapping( value = "/getAllCapacityBySelf" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllCapacityBySelf(
	        @ApiParam( value = "起运地，查询条件，可为空" , required = false ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "目的地，查询条件，可为空" , required = false ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		Page page = ServiceUtil.getcurrPage( request );
		// HqlHelper hql = new HqlHelper( TSpareCapacity.class );
		// hql.setQueryPage( page );
		// hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		// hql.addEqual( "IUser" , user.getId() );
		// hql.addOrderBy( "id" , "desc" );
		String sql = "select c.id,c.vc_user_name,c.dt_begin,c.dt_end,c.vc_start_address,c.vc_end_address,"
		        + "c.i_truck_type,c.n_space,c.n_weight,c.vc_truck_name"
		        + " from t_spare_capacity c "
		        + " where  c.n_enable="
		        + SystemConstants.SYS_ENABLE + " and  c.i_user=" + user.getId();
		if ( StringUtils.isNotBlank( vcStart ) )
		{
			sql += " and c.vc_start_address like '%" + vcStart + "%'";
		}
		if ( StringUtils.isNotBlank( vcEnd ) )
		{
			sql += " and c.vc_end_address like '%" + vcEnd + "%'";
		}
		
		sql += " order by id desc ";
		try
		{
			Map< String , Object > result = spareService.getSpringSQL( sql , page );
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
		// String vcStart = request.getParameter( "vcStart" );
		// if ( StringUtils.isNotBlank( vcStart ) )
		// {
		// System.out.println( "vcStart=" + vcStart );
		// hql.addLike( "vcStartAddress" , vcStart );
		//
		// }
		// // String vcEnd = request.getParameter( "vcEnd" );
		// if ( StringUtils.isNotBlank( vcEnd ) )
		// {
		// System.out.println( "vcEnd=" + vcEnd );
		// hql.addLike( "vcEndAddress" , vcEnd );
		//
		// }
		//
		// Map< String , Object > resuMap;
		// try
		// {
		// resuMap = spareService.findByHelper( hql );
		// return AjaxUtil.getMapByResult( visit , resuMap );
		// }
		// catch ( Exception e )
		// {
		// e.printStackTrace();
		// return AjaxUtil.getMapByException( e );
		// }
		//
	}
	
	/**
	 * @Description: 获取所有的空闲运力信息 除了自己的
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-15 上午11:58:58
	 */
	@ApiIgnore
	@RequestMapping( value = "/getAllCapacityByNotSelf" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllCapacityByNotSelf(
	        @ApiParam( value = "发布者，查询条件，可为空" , required = false ) @RequestParam( value = "vcUserNo" , required = false ) String vcUserNo ,
	        @ApiParam( value = "起运地，查询条件，可为空" , required = false ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "目的地，查询条件，可为空" , required = false ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		
		Page page = ServiceUtil.getcurrPage( request );
		/*HqlHelper hql = new HqlHelper( TSpareCapacity.class );
		hql.addNotEqual( "IUser" , user.getId() );
		List< Integer > spareIds = spareService.getLimitByUserId( user.getId() );
		if ( CollectionUtils.isNotEmpty( spareIds ) )
		{
			hql.addIn( "ID" , spareIds );
		}
		
		if ( StringUtils.isNotBlank( vcUserNo ) )
		{
			hql.addLike( "vcUserName" , vcUserNo );
		}
		
		if ( StringUtils.isNotBlank( vcStart ) )
		{
			hql.addLike( "vcStartAddress" , vcStart );
		}
		
		if ( StringUtils.isNotBlank( vcEnd ) )
		{
			hql.addLike( "vcEndAddress" , vcEnd );
		}
		
		hql.addOrderBy( "ID" );
		
		Map< String , Object > resuMap;
		try
		{
			resuMap = spareService.findByHelper( hql );
			return AjaxUtil.getMapByResult( visit , resuMap );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}*/
		Map< String , Object > resuMap = new HashMap< String , Object >();
		try
		{
			String sql = "select spare.*,(select count(1) from t_limit_check limi where limi.i_user="
			        + user.getId()
			        + " and limi.n_enable="
			        + SystemConstants.SYS_ENABLE
			        + " and limi.i_spare_capacity=spare.id) icount from t_spare_capacity spare where spare.I_USER!="
			        + user.getId() + " ";
			
			// String vcUserNo = request.getParameter( "vcUserNo" );
			if ( StringUtils.isNotBlank( vcUserNo ) )
			{
				sql += " and spare.VC_USER_NAME like '%" + vcUserNo + "%'";
			}
			// String vcStart = request.getParameter( "vcStart" );
			if ( StringUtils.isNotBlank( vcStart ) )
			{
				sql += " and spare.VC_START_ADDRESS like '%" + vcStart + "%'";
				
			}
			// String vcEnd = request.getParameter( "vcEnd" );
			if ( StringUtils.isNotBlank( vcEnd ) )
			{
				sql += " and spare.VC_END_ADDRESS like '%" + vcEnd + "%'";
				
			}
			
			sql += " order by id desc ";
			resuMap = spareService.getSpringSQL( sql , page );
			
			return AjaxUtil.getMapByResult( visit , resuMap );
			
		}
		catch ( Exception e )
		{
			
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 获取所有的空闲运力信息 除了自己的
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-15 上午11:58:58
	 */
	@ApiOperation( value = "运力平台-运力平台  ；获取所有的空闲运力信息 除了自己的" , notes = "获取所有的空闲运力信息 除了自己的" , position = 5 )
	@RequestMapping( value = "/getAllCapacityByNotSelfApp" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getAllCapacityByNotSelfApp(
	        @ApiParam( value = "发布者，查询条件，可为空" , required = false ) @RequestParam( value = "vcUserName" , required = false ) String vcUserName ,
	        @ApiParam( value = "起运地，查询条件，可为空" , required = false ) @RequestParam( value = "vcStart" , required = false ) String vcStart ,
	        @ApiParam( value = "目的地，查询条件，可为空" , required = false ) @RequestParam( value = "vcEnd" , required = false ) String vcEnd ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		
		Page page = ServiceUtil.getcurrPage( request );
		String sql = "select c.id,c.vc_user_name,c.dt_begin,c.dt_end,c.vc_start_address,"
		        + "c.vc_end_address,c.i_truck_type,c.n_space,c.n_weight,nvl(c.VC_TRUCK_NAME,' ') VC_TRUCK_NAME "
		        + " from t_spare_capacity c " + " where c.n_enable="
		        + SystemConstants.SYS_ENABLE + " and c.i_user!=" + user.getId();
		if ( StringUtils.isNotBlank( vcUserName ) )
		{
			sql += " and c.vc_user_name like '%" + vcUserName + "%' ";
		}
		if ( StringUtils.isNotBlank( vcStart ) )
		{
			sql += " and c.vc_start_address like '%" + vcStart + "%' ";
		}
		if ( StringUtils.isNotBlank( vcEnd ) )
		{
			sql += " and c.vc_end_address like '%" + vcEnd + "%' ";
		}
		sql += " order by c.dt_add desc  ";
		try
		{
			Map< String , Object > result = spareService.getSpringSQL( sql , page );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 保存修改空闲运力 之前的操作 获取所需数据
	 * @return String 跳转的页面
	 * @throws
	 */
	@RequestMapping( "/saveBefore" )
	@ApiIgnore
	public String saveBefore( HttpServletRequest request )
	{
		String paramType = "add";
		String spareID = request.getParameter( "spareID" );
		if ( StringUtils.isNotBlank( spareID ) )
		{
			
			paramType = "update";
			
			TSpareCapacity capacity = spareService.getById( Integer.parseInt( spareID ) );
			JSONObject jsonObject = new JSONObject();
			// System.out.println( "json = " + jsonObject.fromObject( capacity )
			// );
			
			request.setAttribute( "capacity" , capacity );
		}
		request.setAttribute( "paramType" , paramType );
		// 获取可用的车辆
		HttpSession session = request.getSession();
		Map< String , Object > map = getTruck( session );
		List< Map< String , Object >> trucks = ( List< Map< String , Object >> ) map
		        .get( "data" );
		// System.out.println( "trucks:" + trucks );
		request.setAttribute( "trucks" , trucks );
		return "sub/spareCapacity/saveSpareCapacity";
		
	}
	
	/**
	 * @Description: 该方法提供给app，获得空闲运力详情
	 * @param spareID
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2015年4月15日 上午10:57:45
	 */
	@ApiOperation( value = "获得空闲运力详情" , notes = "获得空闲运力详情,通过id获得，id不为空" , position = 5 )
	@RequestMapping( value = "/getById" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getById(
	        @ApiParam( value = "空闲信息id" , required = true ) @RequestParam( "spareID" ) int spareID ,
	        HttpServletRequest request )
	{
		try
		{
			Map< String , Object > result = spareService.getDetailByIdApp( spareID );
			// 判断是否扣除积分
			HttpSession session = request.getSession();
			TUser user = ( TUser ) session.getAttribute( "user" );
			int userId = user.getId();
			boolean flag = limitService.getByUserIdAndSpareId( userId , spareID );
			String isTrue = "N";// 联系方式是否可见字段
			if ( flag )
			{
				isTrue = "Y";
			}
			result.put( "isTrue" , isTrue );
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
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
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: 保存或修改空闲运力对象
	 * @param request
	 * @param ark
	 * @param resp
	 *            void 返回值描述
	 * @author chenbin
	 * @create_date 2014-12-15 上午11:58:58
	 */
	@ApiOperation( value = "保存或修改空闲运力对象" , notes = "保存或修改空闲运力对象" , position = 5 )
	@RequestMapping( value = "/saveCapatity" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > saveCapatity(
	        @Valid @ModelAttribute TSpareCapacity capacity , BindingResult error ,
	        HttpServletRequest request )
	{
		
		if ( error.hasErrors() )
		{
			return ServiceUtil.getErrorVerification( error );
		}
		
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		capacity.setIUser( user.getId() );
		capacity.setVcUserName( user.getVcAccount() );
		try
		{
			String provinceStart = capacity.getVcProvinceStart();// 获取出发地省份
			String cityStart = capacity.getVcCityStart();// 获取出发地城市
			String provinceEnd = capacity.getVcProvinceEnd();// 获取目的地省份
			String cityEnd = capacity.getVcCityEnd();// 获取目的地城市
			if ( StringUtils.isNotBlank( provinceStart ) )
			{
				int provinceStartId = provinceService.getIdByName( provinceStart );
				capacity.setIProvinceStart( provinceStartId );
			}
			if ( StringUtils.isNotBlank( cityStart ) )
			{
				int cityStartId = cityService.getIdByName( cityStart );
				capacity.setICityStart( cityStartId );
			}
			if ( StringUtils.isNotBlank( provinceEnd ) )
			{
				int provinceEndId = provinceService.getIdByName( provinceEnd );
				capacity.setIProvinceEnd( provinceEndId );
			}
			if ( StringUtils.isNotBlank( cityEnd ) )
			{
				int cityEndId = cityService.getIdByName( cityEnd );
				capacity.setICityEnd( cityEndId );
			}
			// System.out.println( "capacity:" + capacity );
			spareService.saveOrUpdate( capacity );
			return AjaxUtil.getMap( true , "保存成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 删除空闲运力信息 逻辑删除
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiOperation( value = "运力平台-发布记录-取消发布" , notes = "删除空闲运力信息 逻辑删除" , position = 5 )
	@RequestMapping( value = "/del" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > del(
	        @ApiParam( value = "空闲运力信息id" , required = true ) @RequestParam( "spareID" ) String spareID )
	{
		
		try
		{
			int ID = Integer.parseInt( spareID );
			TSpareCapacity spare = spareService.getById( ID );
			if ( null != spare )
			{
				spare.setNEnable( SystemConstants.SYS_DISABLE );
				spareService.saveOrUpdate( spare );
				return AjaxUtil.getMap( true , "已经删除了！" );
			}
			return AjaxUtil.getMap( true , "删除成功." );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description: 获得分供方发布的空闲运力的联系方式，验证积分
	 * @return String 跳转的页面
	 * @throws
	 */
	@ApiOperation( value = "获得联系方式" , notes = "获得分供方发布的空闲运力的联系方式，验证积分,操作成功后，把屏蔽手机联系方式去掉，显示联系方式。" , position = 5 )
	@RequestMapping( value = "/getLinkByIntegalCut" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getLinkByIntegalCut(
	        @ApiParam( value = "闲运力信息id" , required = true ) @RequestParam( "spareID" ) String spareID ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		
		try
		{
			int ID = Integer.parseInt( spareID );
			TSpareCapacity spare = spareService.getById( ID );
			if ( null == spare )
			{
				return AjaxUtil.getMap( false , "您传的空闲运力id找不到对应信息。" );
			}
			
			// 验证获取联系方式 积分是否足够
			JSONObject json = integalCutService.checkIntegalCutByUserCode( user , "G" );
			// System.out.println( "isSuccess" + json.getBoolean( "isSuccess" )
			// );
			if ( json.getBoolean( "isSuccess" ) == false )
			{
				return AjaxUtil.getMap( false , json.getString( "message" ) );
			}
			int integalID = json.getInt( "integalID" );
			spareService.saveAndIntegalCut( spare , user , "cut" , integalID );
			return AjaxUtil.getMap( true , spare.getVcLink() );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 获得运输车，发布空闲运力时，获得当前用户下的运输车
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping( value = "/getTruck" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获得运输车" , notes = "获得运输车，发布空闲运力时，获得当前用户下的运输车" , position = 5 )
	public Map< String , Object > getTruck(
	        @ApiParam( value = "会话信息，不用给该参数传数据，不管" , required = false ) HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null != user )
		{
			int archiveType = user.getIArchiveType();// 获得用户类型
			List< Map< String , Object > > list = null;
			try
			{
				switch ( archiveType )
					{
						case SystemConstants.SYS_TARCHIVE_SUB :
							// 如果当前用户是分供方，获得所有运输车
							String vcSubno = subService.get( user.getiArchive() )
							        .getVcSubno();
							list = truckService.getTruckDriverBySubno( vcSubno );
							break;
						case SystemConstants.SYS_TARCHIVE_DRIVER :
							// 如果当前用户是司机，获得和他有关的运输车
							list = truckService.getTruckDriverByDriverId( user
							        .getiArchive() );
							break;
						
						default :
							break;
					}
				if ( null != list && list.size() > 0 )
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
		
		return AjaxUtil.getMapByNotException( false , null );
	}
	
	/**
	 * 获得运输车长宽高信息，根据运输车id获取
	 * 
	 * @param truckId
	 *            运输车id
	 * @return
	 */
	@ApiOperation( value = "获得运输车长宽高信息" , notes = "获得运输车长宽高信息，根据运输车id获取" , position = 5 )
	@RequestMapping( value = "/getTruckInfo" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getTruckInfo(
	        @ApiParam( value = "运输车id" , required = true ) @RequestParam( "truckId" ) int truckId )
	{
		try
		{
			TTruckDriver tTruckDriver = truckService.get( truckId );
			if ( null != tTruckDriver )
			{
				Map< String , Object > map = new HashMap< String , Object >();
				map.put( "nlength" , tTruckDriver.getNLength() );
				map.put( "nwidth" , tTruckDriver.getNWidth() );
				map.put( "nheight" , tTruckDriver.getNHeight() );
				return AjaxUtil.getMapByNotException( true , map );
			}
			else
			{
				return AjaxUtil.getMap( false , "没有对应运输车信息!" );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
}
