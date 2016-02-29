/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-4-24 上午11:08:16
 * @version V1.0
 */
package com.clt.sub.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.common.UserSession;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.THolidayApply;
import com.clt.sub.model.THolidayType;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.IHolidayApplyService;
import com.clt.sub.service.IHolidayTypeService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IMsgRecordService;
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
 * @Description: TODO(请假申请审批)
 * @author liuwu
 * @date 2015-4-24 上午11:08:16
 * @version V1.0
 */
@Controller
@RequestMapping( "/holidaysApprovalAction" )
@Api( value = "holidaysApproval-api" , description = "有关请假申请审批的API" , position = 5 )
public class HolidaysAppovalAction
{
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	private IDriverService driverService;
	
	@Autowired
	IHolidayApplyService iHolidayApplyService;
	@Autowired
	IUserService iUserService;
	@Autowired
	IMsgRecordService msgService;
	@Autowired
	IDriverService iDriverService;
	@Autowired
	IHolidayTypeService typeService;
	
	@RequestMapping( "/getHolidays" )
	@ApiIgnore
	public String getHolidays()
	{
		return "sub/flowApprove/holidayApplyList";
		
	}
	
	@ApiOperation( value = "获取请假申请审批列表返回json" , notes = ""
	        + "private Integer id;// id<br/>"
	        + "private Integer nEnable;// 是否有效(0:有效，1：无效)<br/>"
	        + "private String vcUserName;// 申请人姓名<br/>"
	        + "private Date dtApply;// 申请时间<br/>" + "private Integer iType;// 请假类型<br/>"
	        + "private Date dtStart;// 请假开始时间<br/>" + "private Date dtEnd;// 请假结束时间<br/>"
	        + "private String vcApplyNote;// 申请备注<br/>"
	        + "private Integer iUser;// 申请人id<br/>"
	        + "private String vcSubNo;// 分供方编号<br/>"
	        + "private Integer iApprove;// 审批人id<br/>"
	        + "private String vcApprveName;// 审批人姓名<br/>"
	        + "private Date dtApprove;// 审批时间<br/>"
	        + "private Integer nApproveResult;// 审批结果(0,通过，1，未审批,2:未通过)<br/>"
	        + "private String vcApproveNote;// 审批备注" , position = 5 )
	@RequestMapping( value = "/getHolidayList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getHolidayList(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "申请人" , required = false ) @RequestParam( value = "vcUserName" , required = false ) String vcUserName ,
	        @ApiParam( value = "审批状态(0,审核通过，1，未审批(默认),2:审核不通过)" , required = true ) @RequestParam( value = "nApproveResult" , required = false ) String nApproveResult )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		if ( null == user )
		{
			user = ( TUser ) UserSession.get( "user" );
		}
		Map< String , Object > resuMap = new HashMap< String , Object >();
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		int subId = user.getiArchive();
		String vcSubno = "";
		int typeId = user.getIArchiveType();
		if ( SystemConstants.SYS_TARCHIVE_DRIVER == typeId )
		{
			vcSubno = driverService.get( subId ).getVcSubno();
		}
		else
		{
			vcSubno = subService.get( subId ).getVcSubno();// 获取分供方编号
		}
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( THolidayApply.class );
		hql.setQueryPage( page );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		hql.addEqual( "vcSubNo" , vcSubno );
		try
		{
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
			hql.addOrderBy( "dtApply" , "desc" );
			resuMap = iHolidayApplyService.findAllByHqlHelp( hql );
			return AjaxUtil.getMapByResult( visit , resuMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(请假信息审核通过)
	 * @param array
	 * @param vcResult
	 * @param vcApproveNote
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:43:55
	 */
	@ApiOperation( value = "请假信息审核通过" , notes = "请假信息审核通过" , position = 5 )
	@RequestMapping( value = "/sure" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > sure(
	        @ApiParam( value = "请假列表的id(传非0的整数数字)" , required = true ) @RequestParam( "id" ) String array ,
	        @ApiParam( value = "审批结果(0,审核通过，1，未审批(默认),2:审核不通过)" , required = true ) @RequestParam( "vcResult" ) String vcResult ,
	        @ApiParam( value = "审批意见" , required = false ) @RequestParam( value = "vcApproveNote" , required = false ) String vcApproveNote ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		// String subbo = subService.get( user.getiArchive() ).getVcSubno();//
		// 所属分供方编号
		// String damageId = request.getParameter( "id" );
		// String note = request.getParameter( "note" );// 备注
		try
		{
			String message = iHolidayApplyService.updateHolidayApply( array , vcResult ,
			        vcApproveNote , user );
			if ( message.equalsIgnoreCase( "success" ) )
			{
				return AjaxUtil.getMap( true , "通过审核" );
			}
			else
			{
				return AjaxUtil.getMap( false , "审核失败，原因：" + message );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	@ApiOperation( value = "获取请假类型列表返回json" , notes = ""
	        + "private Integer id;// id<br/>"
	        + "private Integer nEnable;// 是否有效(0:有效，1：无效)<br/>"
	        + "private String vcAddUser;// 添加人<br/>" + "private Date dtAdd;// 添加时间<br/>"
	        + "private String vcType;// 请假类型<br/>" , position = 5 )
	@RequestMapping( value = "/getHolidayTypes" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getHolidayTypes( HttpServletRequest request )
	{
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper hql = new HqlHelper( THolidayType.class );
		hql.setQueryPage( page );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		Map< String , Object > resuMap = new HashMap< String , Object >();
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			
			resuMap = iHolidayApplyService.findAllByHqlHelp( hql );
			return AjaxUtil.getMapByResult( visit , resuMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * 
	 * @Description: TODO(请假信息申请接口(新增、修改申请))
	 * @param request
	 * @param response
	 * @param paramType
	 * @param tHolidayApply
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:53:08
	 */
	@ApiOperation( value = "请假信息申请接口(新增、修改申请)" , notes = "保存请假申请信息,用form提交封装为THolidayApply对象" , position = 5 , response = THolidayApply.class )
	@RequestMapping( value = "/save" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > save(
	        HttpServletRequest request ,
	        @ApiParam( value = "add为新增、update为修改(注:修改的话id不能为空)" , required = true ) @RequestParam( value = "paramType" , required = true ) String paramType ,
	        @ApiParam( value = "请假信息" ) @ModelAttribute THolidayApply tHolidayApply )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int driverId = user.getiArchive();// 司机ID
		int typeId = user.getIArchiveType();
		TDriver tDriver = null;
		try
		{
			// 获取请假日期
			Date dtStart = tHolidayApply.getDtStart();
			Date dtEnd = tHolidayApply.getDtEnd();
			double days = 1.0 * ( dtEnd.getTime() - dtStart.getTime() )
			        / ( 1000 * 60 * 60 * 24 );
			DecimalFormat df = new DecimalFormat( "0.0" );
			days = Double.parseDouble( df.format( days ) );
			tHolidayApply.setnDays( days );
			// 获取请假类型
			int itype = tHolidayApply.getiType();
			THolidayType holidayType = typeService.get( itype );
			tHolidayApply.setVcTypeName( holidayType.getVcType() );
			if ( paramType.equalsIgnoreCase( "add" ) )
			{
				tDriver = iDriverService.get( driverId );
				String vcSubno = tDriver.getVcSubno();
				tHolidayApply.setVcTel( tDriver.getVcDriverTel() );
				tHolidayApply.setVcSubNo( vcSubno );
				tHolidayApply.setVcUserName( user.getVcAccount() );
				tHolidayApply.setiUser( tDriver.getId() );
				iHolidayApplyService.save( tHolidayApply );
				// 消息推送
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "76" );// 76=请假
				map.put( "id" , tHolidayApply.getId() + "" );
				TUser bossUser = iUserService.getByid( tDriver.getiUserId() + "" );
				List< TUser > tUsers = new ArrayList< TUser >();
				tUsers.add( bossUser );
				PushUtils pushUtils = new PushUtils( "有请假申请，请点击查看" , "来自"
				        + tDriver.getVcDriverName() + "的请假申请" , tUsers ,
				        "com.unlcn.carrier.approvalprocess.HolidayDetailActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( user.getId() );// 添加人ID
				tMsgRecord.setVcAdduser( user.getVcAccount() );// 添加人姓名
				tMsgRecord.setIUserAccept( bossUser.getId() );// 接收人用户id
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "来自" + user.getVcAccount() + "的请假申请" );
				tMsgRecord.setVcTitle( "有请假申请，请点击查看" );
				msgService.save( tMsgRecord );
			}
			else
			{
				iHolidayApplyService.update( tHolidayApply );
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
	 * @Description: TODO(申请撤销)
	 * @param request
	 * @param response
	 * @param id
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-4-28 上午11:56:29
	 */
	@ApiOperation( value = "申请撤销" , notes = "申请撤销" )
	@RequestMapping( value = "/delete" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > delete(
	        HttpServletRequest request ,
	        @ApiParam( value = "列表ID" , required = true ) @RequestParam( value = "id" , required = true ) String id )
	{
		if ( StringUtils.isNotBlank( id ) )
		{
			try
			{
				THolidayApply tHolidayApply = iHolidayApplyService.findById( Integer
				        .parseInt( id ) );
				tHolidayApply.setnEnable( SystemConstants.SYS_DISABLE );
				iHolidayApplyService.update( tHolidayApply );
				return AjaxUtil.getMap( true , "成功撤销申请" );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
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
	@ApiOperation( value = "根据id获取详情" , notes = "根据id获取详情" )
	@RequestMapping( value = "/getDetailById" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getDetailById(
	        HttpServletRequest request ,
	        @ApiParam( value = "id" , required = true ) @RequestParam( value = "id" , required = true ) String id )
	{
		
		try
		{
			
			THolidayApply tHolidayApply = iHolidayApplyService.findById( Integer
			        .parseInt( id ) );
			if ( null != tHolidayApply )
			{
				return AjaxUtil.getMapByNotException( true , tHolidayApply );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , tHolidayApply );
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
}
