package com.clt.sub.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.common.UserSession;
import com.clt.finance.dao.ILoanAskDao;
import com.clt.finance.model.TFinance;
import com.clt.finance.model.TLoanAsk;
import com.clt.finance.service.IFinanceService;
import com.clt.finance.service.ILoanAskService;
import com.clt.sub.model.TDriver;
import com.clt.sub.model.TLoan;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.service.IDriverService;
import com.clt.sub.service.ILoanService;
import com.clt.sub.service.ILoanTypeService;
import com.clt.sub.service.IMangeScopeService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TUser;
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

/**
 * @Package com.clt.sub.controller
 * @Description: 处理贷款
 * @author hjx
 * @date 2014年8月28日 上午9:51:59
 * @version V1.0
 */
@Controller
@RequestMapping( "/loanAction" )
@Api( value = "loan-api" , description = "关于贷款的操作" , position = 5 )
public class LoanAction
{
	@Autowired
	private ILoanService loanService;
	@Autowired
	private IFinanceService financeServicce;
	@Autowired
	private ISubsuppliersService subService;
	@Autowired
	private ILoanTypeService typeService;
	@Autowired
	private IMangeScopeService mangeScopeService;
	@Autowired
	private IFinanceService financeService;
	@Autowired
	private ILoanAskDao askDao;
	@Autowired
	private ILoanAskService askService;
	@Autowired
	private IDriverService driverService;
	@Autowired
	private IUserService userService;
	
	/**
	 * @Description: 打开贷款列表。这里可以写申请，或者查看申请内容
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月28日 上午9:53:36
	 */
	@RequestMapping( "/openLoanList" )
	@ApiIgnore
	public String openLoanList()
	{
		
		return "sub/loan/myLoanList";
	}
	
	/**
	 * @Description: 给贷款列表提供数据
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年8月28日 上午9:55:45
	 */
	@RequestMapping( "/getLoanList" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > getLoanList( HttpServletRequest request )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TLoan.class );
		helper.setQueryPage( page );
		helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );// 取有效值
		// 加载当前用户的 贷款记录信息
		TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
		TSubsuppliers tSubsuppliers = subService.get( user.getiArchive() );
		String vcSubno = tSubsuppliers.getVcSubno();
		helper.addEqual( "vcSubno" , vcSubno );
		
		// 搜索条件
		String applayDate = request.getParameter( "applayDate" );
		if ( StringUtils.isNotBlank( applayDate ) )
		{
			try
			{
				helper.addEqual( "dtApply" , DateUtil.parseDate( applayDate ,
				        "yyyy-MM-dd" ) );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		String nflag = request.getParameter( "nflag" );
		if ( StringUtils.isNotBlank( nflag ) )
		{
			helper.addEqual( "NFlag" , Integer.parseInt( nflag ) );
		}
		result = loanService.findByHelper( helper );
		return result;
	}
	
	/**
	 * @Description: 打开新增页面 -->获得当前用户，判断是否是分供方，如果不是分供方挑战到提示页面，
	 *               如果是分供方判断是否有权限申请贷款，没有权限跳转到提示页面 。
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年8月28日 上午10:23:45
	 */
	@RequestMapping( "/openAddLoan" )
	@ApiIgnore
	public String openAddLoan( HttpServletRequest request )
	{
		TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
		Integer iArchiveType = user.getIArchiveType();
		if ( SystemConstants.SYS_TARCHIVE_SUB == iArchiveType )
		{
			Integer archive = user.getiArchive();
			if ( null != archive )
			{
				TSubsuppliers tSubsuppliers = subService.get( archive );
				// 具备贷款权限
				if ( SystemConstants.SYS_ENABLE == tSubsuppliers.getNEnableApply() )
				{
					// 获得所有的金融公司
					List< TFinance > finances = financeServicce.loadAllByEnable();
					StringBuffer sb = new StringBuffer();
					if ( CollectionUtils.isNotEmpty( finances ) )
					{
						sb.append( "<option value=\"\">" ).append( "--请选择--" ).append(
						        "</option>" );
						
						for ( TFinance tFinance : finances )
						{
							sb.append( "<option value=\"" ).append(
							        tFinance.getVcFinanceno() ).append( "\">" ).append(
							        tFinance.getVcAllName() ).append( "</option>" );
						}
						request.setAttribute( "options" , sb.toString() );
					}
					
					String idStr = request.getParameter( "id" );
					if ( StringUtils.isNotBlank( idStr ) )
					{
						TLoan loan = loanService.get( Integer.parseInt( idStr ) );
						request.setAttribute( "loan" , loan );
					}
					else
					{
						TLoan loan = new TLoan();
						loan.setIApplyUser( user.getId() );
						loan.setVcApplyUserName( user.getVcUsername() );
						loan.setVcSubno( tSubsuppliers.getVcSubno() );
						loan.setVcSubAllName( tSubsuppliers.getVcAllName() );
						request.setAttribute( "loan" , loan );
					}
					
					return "sub/loan/applayLoanForm";
				}
				else
				{
					// 不具备贷款权限
					request.setAttribute( "msg" , "您不具备贷款权限,<br/>请现在分供方完善信息页面进行申请！" );
					return "front/msg";// 提示页面
				}
			}
			else
			{
				// 当前用户还没和分供方档案信息关联
				request.setAttribute( "msg" , "您当前用户还没和分供方档案信息关联，请和管理员联系" );
				return "front/msg";// 提示页面
			}
			
		}
		else
		{
			// 跳转到提示页面
			request.setAttribute( "msg" , "您不具备分供方档案，不能进行贷款！" );
			return "front/msg";// 提示页面
		}
		// return "sub/loan/applayLoanForm";// 该跳转后面要删除
	}
	
	/**
	 * @Description: 分供方查看他自己填写的贷款申请（跳转到详情界面）
	 * @param request
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年9月5日 上午11:13:26
	 */
	@RequestMapping( "/lookLoanDetail" )
	@ApiIgnore
	public String lookLoanDetail( HttpServletRequest request )
	{
		String idStr = request.getParameter( "id" );
		if ( StringUtils.isNotBlank( idStr ) )
		{
			TLoan loan = loanService.get( Integer.parseInt( idStr ) );
			request.setAttribute( "loan" , loan );
			
			String msg1 = "您于"
			        + DateUtil.formatDate( loan.getDtAdd() , "yyyy-MM-dd HH:mm:ss" )
			        + "填写了申请。";
			String msg2 = "您于"
			        + DateUtil.formatDate( loan.getDtApply() , "yyyy-MM-dd HH:mm:ss" )
			        + "提交申请";
			String msg3 = "工作人员会尽快给您寻找合适的贷款公司";
			if ( null != loan.getDtTalk() )
			{
				msg3 = "工作人员于"
				        + DateUtil.formatDate( loan.getDtTalk() , "yyyy-MM-dd HH:mm:ss" )
				        + "开始给您寻找合适的贷款公司";
			}
			String msg4 = "还未结束。";
			if ( null != loan.getDtResult() )
			{
				switch ( loan.getNFlag() )
					{
						case 3 :
							msg4 = "您于"
							        + DateUtil.formatDate( loan.getDtResult() ,
							                "yyyy-MM-dd HH:mm:ss" ) + "成功贷款，贷款公司："
							        + loan.getVcFinanceNameReal();
							break;
						case 4 :
							msg4 = "很抱歉，没有为您寻找到合适的贷款公司！";
							break;
						
						default :
							break;
					}
			}
			
			int step = loan.getNFlag();
			if ( step >= 3 )
			{
				step = 4;
			}
			else
			{
				step++ ;
			}
			request.setAttribute( "step" , step );
			request.setAttribute( "msg1" , msg1 );
			request.setAttribute( "msg2" , msg2 );
			request.setAttribute( "msg3" , msg3 );
			request.setAttribute( "msg4" , msg4 );
			
		}
		return "sub/loan/applayLoanDetail";
	}
	
	/**
	 * @Description: 保存在线贷款申请内容
	 * @param loan
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年9月1日 下午4:38:10
	 */
	@RequestMapping( "/saveLoan" )
	@ApiIgnore
	public void saveLoan( TLoan loan , HttpServletRequest request ,
	        HttpServletResponse response )
	{
		String msg = "";
		try
		{
			String type = request.getParameter( "applayType" );
			if ( "applay".equals( type ) )
			{
				loan.setDtApply( new Date() );
				loan.setNFlag( SystemConstants.SYS_LOAN_FLAG_COMMIT );
				msg = "数据保存并提交申请成功！";
			}
			else
			{
				msg = "数据保存成功！";
			}
			if ( loan.getId() == null )
			{
				
				// 新增
				loanService.save( loan );
				AjaxUtil.rendJson( response , true , msg );
			}
			else
			{
				// 更新
				loanService.update( loan );
				AjaxUtil.rendJson( response , true , msg );
			}
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "数据保存失败，失败原因：" + e.getMessage() );
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 删除 (逻辑删除)贷款申请。
	 * @param ids
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年9月2日 下午1:32:59
	 */
	@RequestMapping( "/disableLoan" )
	@ApiIgnore
	public void disableLoan( @RequestParam( "id" ) String id ,
	        HttpServletResponse response )
	{
		try
		{
			loanService.deleteByKey( Integer.parseInt( id ) );
			AjaxUtil.rendJson( response , true , "删除成功！" );
		}
		catch ( NumberFormatException e )
		{
			AjaxUtil.rendJson( response , false , "删除失败，失败原因：" + e.getMessage() );
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 提交申请
	 * @param id
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年9月2日 下午1:56:11
	 */
	@RequestMapping( "/applayLoan" )
	@ApiIgnore
	public void applayLoan( @RequestParam( "id" ) int id , HttpServletResponse response )
	{
		try
		{
			TLoan tLoan = loanService.get( id );
			tLoan.setNFlag( SystemConstants.SYS_LOAN_FLAG_COMMIT );
			tLoan.setDtApply( new Date() );
			loanService.update( tLoan );
			AjaxUtil.rendJson( response , true , "提交申请成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "提交申请失败，失败原因：" + e.getMessage() );
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 打开所有贷款信息页面，主要是系统内部人员查看和进行操作的。
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年9月3日 上午11:22
	 */
	@RequestMapping( "/openAllLoanPage" )
	@ApiIgnore
	public String openAllLoanPage()
	{
		return "sub/loan/loanAllList";
	}
	
	/**
	 * @Description: 打开洽谈页面，查看申请信息和可贷款公司
	 * @param request
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年9月9日 下午2:46:25
	 */
	@RequestMapping( "/openTalkLoan" )
	@ApiIgnore
	public String openTalkLoan( HttpServletRequest request )
	{
		String idStr = request.getParameter( "id" );
		if ( StringUtils.isNotBlank( idStr ) )
		{
			TLoan loan = loanService.get( Integer.parseInt( idStr ) );
			request.setAttribute( "loan" , loan );
			// 获得所有的金融公司
			List< TFinance > finances = financeServicce.loadAllByEnable();
			request.setAttribute( "finances" , finances );
		}
		return "sub/loan/talkLoan";
	}
	
	/**
	 * @Description: 内部员工对贷款进行洽谈，给对应金融公司发消息
	 * @param loanId
	 *            申请贷款id
	 * @param financeIds
	 *            金融公司的ids
	 * @author hjx
	 * @create_date 2014年9月3日 下午4:52:35
	 */
	@RequestMapping( "/meetFinance" )
	@ApiIgnore
	public void meetFinance( @RequestParam( "loanId" ) Integer loanId ,
	        @RequestParam( "financeIds" ) String financeId , HttpServletResponse response )
	{
		List< String > financeIds = null;
		if ( ! financeId.equals( "0" ) )
		{
			String[] arr = financeId.split( "," );
			financeIds = Arrays.asList( arr );
		}
		try
		{
			loanService.saveMeetFinance( loanId , financeIds );
			AjaxUtil.rendJson( response , true , "操作成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "操作失败，失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description: 展示当前工作人员所能查看的分供方的贷款信息
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年9月9日 下午5:44:51
	 */
	@RequestMapping( "/mangerLoanList" )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > mangerLoanList( HttpServletRequest request )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TLoan.class );
		helper.setQueryPage( page );
		helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );// 取有效值
		// 获得当前用户管理的所有分供方编号
		TUser user = ( TUser ) request.getSession( false ).getAttribute( "user" );
		TUser user1 = ( TUser ) UserSession.get( "user" );
		List< String > vcSubNos = mangeScopeService.getVcByUserId( user.getId() );
		helper.addIn( "vcSubno" , vcSubNos );
		
		// 搜索条件
		String applayDate = request.getParameter( "applayDate" );
		if ( StringUtils.isNotBlank( applayDate ) )
		{
			try
			{
				helper.addEqual( "dtApply" , DateUtil.parseDate( applayDate ,
				        "yyyy-MM-dd" ) );
			}
			catch ( Exception e )
			{
				e.printStackTrace();
			}
		}
		String nflag = request.getParameter( "nflag" );
		if ( StringUtils.isNotBlank( nflag ) )
		{
			helper.addEqual( "NFlag" , Integer.parseInt( nflag ) );
		}
		result = loanService.findByHelper( helper );
		return result;
	}
	
	/**
	 * @Description 获取所有的贷款/还款方式
	 * @param int IType （IType=1 贷款方式， IType=2还款方式）
	 * @author chengwzh
	 * @date 2015/5/27 15:20
	 */
	@RequestMapping( value = "/getTypeList" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有的贷款/还款方式" , notes = "id:主键<br/> " + "dtAdd: 添加时间<br/>"
	        + "vcAdduser:添加人姓名<br/> " + "vcTypename: 贷款/还款方式<br/>" + "nenable:是否可以<br/>"
	        + "itype: 类型<br/>" , position = 5 )
	public Map< String , Object > getTypeList(
	        @ApiParam( "参数：IType=1 贷款方式， IType=2还款方式" ) @RequestParam( value = "IType" ) int IType ,
	        HttpServletRequest request )
	{
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = typeService.getTypeList( IType );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 获取司机待审批贷款列表
	 */
	@RequestMapping( value = "/getDriverWaitApprovalLoan" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取司机待办事项贷款列表" , notes = "{<br/>" + "\"ID\": 主键<br/>"
	        + "\"I_APPLY_USER\": 申请人id<br/>" + "\"VC_APPLY_USER_NAME\": 申请人姓名<br/>"
	        + "\"VC_SUBNO\":分供方编号<br/>" + "\"VC_SUB_ALL_NAME\":分供方全名<br/>"
	        + "\"DT_APPLY\": 申请时间<br/>" + "\"VC_EXCUSE\": 申请理由<br/>"
	        + "\"N_LOAN_TOTAL\":贷款总额 <br/>" + "\"DT_DEADLINE\": 期限<br/>"
	        + "\"VC_NOTE\": 备注<br/>" + "\"VC_LOANNO\":申请贷款编号<br/>"
	        + "\"N_GETID\": 贷款方式id<br/>" + "\"N_PAYID\": 还款方式id<br/>"
	        + "\"getTypeName\": 贷款方式<br/>" + "\"payTypeName\": 还款方式<br/>" + "}," , position = 5 )
	public Map< String , Object > getDriverWaitApprovalLoan(
	        HttpServletRequest request ,
	        @ApiParam( value = "申请贷款编号" ) @RequestParam( value = "vcLoanno" , required = false ) String vcLoanno )
	{
		
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = loanService.getDriverWaitedLoans( page ,
			        userId , vcLoanno );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 获取分供方待审批贷款列表（app）
	 * @author chengwzh
	 * @date 2015/5/27 16:00
	 */
	@RequestMapping( value = "/getSubWaitApprovalLoan" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取分供方待办事项贷款列表" , notes = "{<br/>" + "\"ID\": 主键<br/>"
	        + "\"I_APPLY_USER\": 申请人id<br/>" + "\"VC_APPLY_USER_NAME\": 申请人姓名<br/>"
	        + "\"VC_SUBNO\":分供方编号<br/>" + "\"VC_SUB_ALL_NAME\":分供方全名<br/>"
	        + "\"DT_APPLY\": 申请时间<br/>" + "\"VC_EXCUSE\": 申请理由<br/>"
	        + "\"N_LOAN_TOTAL\":贷款总额 <br/>" + "\"DT_DEADLINE\": 期限<br/>"
	        + "\"VC_NOTE\": 备注<br/>" + "\"VC_LOANNO\":申请贷款编号<br/>"
	        + "\"N_GETID\": 贷款方式id<br/>" + "\"N_PAYID\": 还款方式id<br/>"
	        + "\"getTypeName\": 贷款方式<br/>" + "\"payTypeName\": 还款方式<br/>" + "}," , position = 5 )
	public Map< String , Object > getSubWaitApprovalLoan(
	        HttpServletRequest request ,
	        @ApiParam( value = "申请人名字" ) @RequestParam( value = "applyName" , required = false ) String applyName ,
	        @ApiParam( value = "申请贷款编号" ) @RequestParam( value = "vcLoanno" , required = false ) String vcLoanno )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		String subno = subService.get( subId ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = loanService.getSubWaitedLoans( page , subno ,
			        applyName , vcLoanno );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 获取司机已审批的贷款申请
	 */
	@RequestMapping( value = "/getDriverApprovaledLoans" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取司机已审批的贷款申请" , notes = "获取司机已审批的贷款申请列表：<br/>"
	        + "ID:主键<br/> " + "I_APPLY_USER: 申请人id<br/>"
	        + "VC_APPLY_USER_NAME:申请人姓名<br/>" + "VC_SUBNO: 分供方编号<br/>"
	        + "VC_SUB_ALL_NAME:分供方全名<br/>" + "DT_APPLY:申请日期<br/>"
	        + "VC_EXCUSE:申请理由<br/> " + "N_LOAN_TOTAL:贷款总额<br/>" + "DT_DEADLINE:期限<br/>"
	        + "VC_NOTE: 备注<br/>" + "VC_LOANNO:申请贷款编号<br/> " + "N_GETID:贷款方式id<br/>"
	        + "N_PAYID:还款方式id<br/>"
	        + "N_APPROVAL_RESULT:审批结果（0:未审批（默认），1:分供方审批通过，2：分供方拒绝审批）<br/>"
	        + "DT_APPROVAL: 审批时间<br/>" + "VC_APPROVAL_NAME: 审批人姓名<br/>"
	        + "VC_APPROVAL_OPTION: 审批意见<br/>" + "getTypeName:贷款方式<br/>"
	        + "payTypeName: 还款方式<br/>" )
	public Map< String , Object > getDriverApprovaledLoans(
	        HttpServletRequest request ,
	        @ApiParam( value = "申请贷款编号" ) @RequestParam( value = "vcLoanno" , required = false ) String vcLoanno ,
	        @ApiParam( value = "审批结果（1:分供方审批通过，2：分供方拒绝审批）" ) @RequestParam( value = "NApprovalResult" , required = false ) Integer NApprovalResult )
	{
		
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = loanService.getDriverApprovaledLoans( page ,
			        userId , vcLoanno , NApprovalResult );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 获取分供方已审批的贷款申请列表
	 * @author chengwzh
	 * @date 2015/5/28 16:30
	 */
	@RequestMapping( value = "/getSubApprovaledLoans" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取分供方已审批的贷款申请列表" , notes = "获取分供方已审批的贷款申请列表:<br/>"
	        + "ID:主键<br/>" + "I_APPLY_USER:申请人id<br/>" + "VC_APPLY_USER_NAME:申请人姓名<br/>"
	        + "VC_SUBNO: 分供方编号<br/>" + "VC_SUB_ALL_NAME:分供方全名<br/>"
	        + "DT_APPLY:申请时间<br/>" + "VC_EXCUSE:申请理由 <br/>" + "N_LOAN_TOTAL:贷款总额<br/>"
	        + "DT_DEADLINE:期限<br/>" + "VC_NOTE:备注<br/>" + "VC_LOANNO:申请贷款编号<br/> "
	        + "N_GETID:贷款方式id<br/>" + "N_PAYID:还款方式id<br/>"
	        + "N_APPROVAL_RESULT:审批结果（0:未审批（默认），1:分供方审批通过，2：分供方拒绝审批）<br/>"
	        + "DT_APPROVAL:审批时间<br/> " + "VC_APPROVAL_NAME: 审批人姓名<br/>"
	        + "VC_APPROVAL_OPTION:审批意见<br/>" + "getTypeName: 贷款方式<br/>"
	        + "payTypeName:还款方式<br/> " , position = 5 )
	public Map< String , Object > getSubApprovaledLoans(
	        HttpServletRequest request ,
	        @ApiParam( value = "申请人名字" ) @RequestParam( value = "applyName" , required = false ) String applyName ,
	        @ApiParam( value = "申请贷款编号" ) @RequestParam( value = "vcLoanno" , required = false ) String vcLoanno ,
	        @ApiParam( value = "审批结果（1:分供方审批通过，2：分供方拒绝审批）" ) @RequestParam( value = "NApprovalResult" , required = false ) Integer NApprovalResult )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		String subno = subService.get( subId ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = loanService.getSubApprovaledLoans( page ,
			        subno , applyName , vcLoanno , NApprovalResult );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 根据id获取贷款申请详情（app）
	 * @author chengwzh
	 * @date 2015/5/27
	 */
	@RequestMapping( value = "/getLoanById" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "根据id获取贷款申请详情（app）" , notes = "ID: 主键<br/>"
	        + "VC_APPLY_USER_NAME: 申请人名字<br/>" + "DT_APPLY:申请时间 <br/>"
	        + "N_GETID:贷款方式id<br/>" + "VC_SUB_ALL_NAME:分供方全名<br/> "
	        + "VC_EXCUSE: 申请理由<br/>" + "N_LOAN_TOTAL:贷款总额<br/>" + "N_RATE: 利率<br/>"
	        + "DT_DEADLINE:期限<br/>" + "N_PAYID:还款方式id<br/>" + "VC_NOTE: 备注<br/>"
	        + "N_APPROVAL_RESULT:审批结果（0:未审批（默认），1:分供方审批通过，2：分供方拒绝审批）<br/> "
	        + "VC_APPROVAL_NAME:审批人姓名<br/>" + "DT_APPROVAL:审批时间<br/>"
	        + "VC_APPROVAL_OPTION:审批意见<br/>" + "getTypeName:贷款方式<br/>"
	        + "payTypeName: 还款方式<br/>" , position = 5 )
	public Map< String , Object > getLoanById(
	        @ApiParam( "主键" ) @RequestParam( value = "id" , required = true ) int id ,
	        HttpServletRequest request )
	{
		try
		{
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
			Map< String , Object > result = loanService.getById( id );
			if ( result == null )
			{
				return AjaxUtil.getMapByNotException( false , null );
			}
			// 判断是app还是pc端
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
	 * @Description 贷款审批
	 * @param int NApprovalResult 审批结果（0:未审批（默认），1:分供方审批通过，2：分供方拒绝审批)
	 * @author chengwzh
	 * @date 2015/5/29 9:32
	 */
	@RequestMapping( value = "/loanApproval" , method = RequestMethod.POST )
	@ApiOperation( value = "贷款审批" , notes = "参数:int NApprovalResult  审批结果(1,审批通过，2,拒绝审批)" , response = TLoan.class , position = 5 )
	@ResponseBody
	public Map< String , Object > loanApproval(
	        @RequestParam( value = "id" , required = true ) int id ,
	        @ApiParam( value = "审批结果(1,审批通过，2,拒绝审批)" , required = true ) @RequestParam( value = "NApprovalResult" , required = true ) int NApprovalResult ,
	        @ApiParam( value = "审批意见" ) @RequestParam( value = "approvalOption" , required = false ) String approvalOption ,
	        HttpServletRequest request , HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			if ( NApprovalResult != 1 && NApprovalResult != 2 )
			{
				// AjaxUtil.rendJson( response , false , "审批失败: " );
				return AjaxUtil.getMap( false , "审批失败：审批结果参数不符合规范" );
			}
			TLoan entity = loanService.get( id );
			entity.setNApprovalResult( NApprovalResult );// 设置审批结果
			if ( NApprovalResult == 2 )
			{
				entity.setVcApprovalOption( approvalOption );// 设置审批意见
			}
			else
			{
				if ( StringUtils.isBlank( approvalOption ) )
				{
					entity.setVcApprovalOption( "暂无" );
				}
				else
				{
					entity.setVcApprovalOption( approvalOption );// 设置审批意见
				}
				// 审批通过 每个金融公司插入贷款询问记录
				List< TFinance > finances = financeService.getEnableFinance();
				if ( CollectionUtils.isEmpty( finances ) )
				{
					return AjaxUtil.getMap( false , "没有获取到金融公司信息" );
				}
				for ( TFinance finance : finances )
				{
					TLoanAsk ask = new TLoanAsk();
					ask.setILoan( entity.getId() );// 设置申请贷款id
					ask.setVcFinanceno( finance.getVcFinanceno() );// 金融公司编号
					ask.setVcFinanceName( finance.getVcAllName() );// 金融公司名称
					ask.setVcLoanno( entity.getVcLoanno() );// 申请贷款编号
					askService.save( ask );
				}
				entity.setNFlag( SystemConstants.SYS_LOAN_FLAG_COMMIT );// 提交申请
			}
			
			// SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
			// entity.setDtApproval( format.parse( format.format( new Date() ) )
			// );
			entity.setDtApproval( new Date() );// 设置审批时间
			if ( StringUtils.isNotBlank( user.getVcUsername() ) )
			{
				entity.setVcApprovalName( user.getVcUsername() );// 设置审批人名字
			}
			else
			{
				entity.setVcApprovalName( user.getVcAccount() );// 设置审批人名字
			}
			loanService.update( entity );
			// 消息推送
			List< TUser > users = new ArrayList< TUser >();
			TUser applyUser = userService.getByid( entity.getIApplyUser() + "" );
			users.add( applyUser );
			Map< String , String > map = new HashMap< String , String >();
			map.put( "msgType" , "85" );// 85 贷款
			map.put( "id" , entity.getId() + "" );
			PushUtils pushUtil = new PushUtils( "贷款申请通知" , "您的申请有变化，请点击查看！" , users ,
			        "com.unlcn.carrier.approvalprocess.LoanDetailActivity" , map );
			pushUtil.run();
			// AjaxUtil.rendJson( response , true , "审批成功" );
			return AjaxUtil.getMap( true , "审批成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			// AjaxUtil.rendJson( response , false , "审批失败：" + e.getMessage() );
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 撤销申请
	 * @author chengwzh
	 * @date 2015/5/29 15:10
	 */
	@RequestMapping( value = "/delLoanApplay" , method = RequestMethod.POST )
	@ApiOperation( value = "撤销申请" , notes = "撤销申请" , response = TLoan.class )
	public void delLoanApplay(
	        @ApiParam( value = "id:贷款id" ) @RequestParam( value = "id" ) int id ,
	        HttpServletResponse response )
	{
		try
		{
			TLoan entity = loanService.get( id );
			entity.setNEnable( SystemConstants.SYS_DISABLE );
			loanService.update( entity );
			AjaxUtil.rendJson( response , true , "删除成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "删除失败：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 获取审核通过可以提交申请的贷款列表
	 * @author chengwzh
	 * @date 2015/5/29
	 */
	@RequestMapping( value = "/ListSuccessLoans" , method = RequestMethod.POST )
	@ResponseBody
	// @ApiOperation( value = "获取所有审核通过的贷款申请" , notes = "获取所有审核通过的贷款申请" ,
	// position = 5 )
	@ApiIgnore
	public Map< String , Object > ListSuccessLoans( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		String subno = subService.get( subId ).getVcSubno();
		Page page = ServiceUtil.getcurrPage( request );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = loanService.getSuccessLoan( page , subno );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 提交申请(分供方/司机通用)
	 * @author chengwzh
	 * @date 2015/5/29
	 */
	@RequestMapping( value = "/submitApplay" , method = RequestMethod.POST )
	@ApiOperation( value = "分供方/司机贷款申请" , notes = "分供方/司机贷款申请" , response = TLoan.class , position = 5 )
	public void submitApplay(
	        @ApiParam( "贷款总额" ) @RequestParam( value = "NLoanTotal" ) Float NLoanTotal ,
	        @ApiParam( "贷款方式id" ) @RequestParam( value = "NGetId" ) Integer NGetId ,
	        @ApiParam( "还款方式id" ) @RequestParam( value = "NPayId" ) Integer NPayId ,
	        @ApiParam( "申请理由" ) @RequestParam( value = "vcExcuse" ) String vcExcuse ,
	        @ApiParam( "期限（格式：\"yyyy-MM-dd\"）" ) @RequestParam( value = "dtDeadline" ) String dtDeadline ,
	        @ApiParam( "备注" ) @RequestParam( value = "vcNote" ) String vcNote ,
	        HttpServletResponse response , HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();// 获取申请人id
		String userName = user.getVcAccount();// 获取申请人名字
		int subId = user.getiArchive();
		int archiveType = user.getIArchiveType();// 分供方 2，司机 3
		if ( archiveType != SystemConstants.SYS_TARCHIVE_SUB
		        && archiveType != SystemConstants.SYS_TARCHIVE_DRIVER )
		{
			AjaxUtil.rendJson( response , false , "你不能提交申请" );
			return;
		}
		TLoan entity = new TLoan();
		entity.setIApplyUser( userId );
		entity.setVcApplyUserName( userName );
		entity.setNPayId( NPayId );
		entity.setNGetId( NGetId );
		entity.setNLoanTotal( NLoanTotal );
		entity.setVcNote( vcNote );
		entity.setVcExcuse( vcExcuse );
		try
		{
			SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
			entity.setDtDeadline( format.parse( dtDeadline ) );
			if ( archiveType == SystemConstants.SYS_TARCHIVE_DRIVER )
			{
				TDriver driver = driverService.get( subId );
				String subno = driver.getVcSubno();
				entity.setVcSubno( subno );
				String[] propertyNames = { "vcSubno" , "NEnable" };
				Object[] values = { subno , SystemConstants.SYS_ENABLE };
				List< TSubsuppliers > subs = subService.findByPropertys( propertyNames ,
				        values );
				if ( CollectionUtils.isNotEmpty( subs ) )
				{
					TSubsuppliers sub = subs.get( 0 );
					entity.setVcSubAllName( sub.getVcAllName() );
				}
				loanService.save( entity );
				// 消息推送
				List< TUser > users = new ArrayList< TUser >();
				Integer pushUserId = driver.getiUserId();
				if ( pushUserId == null )
				{
					AjaxUtil.rendJson( response , false , "消息推送失败，司机绑定的所属分供方的用户ID为空" );
					return;
				}
				TUser bossUser = userService.getByid( pushUserId + "" );
				users.add( bossUser );
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "75" );// 75=贷款
				map.put( "id" , entity.getId() + "" );
				PushUtils pushUtil = new PushUtils( "贷款申请通知" , "有贷款申请，请点击查看！" , users ,
				        "com.unlcn.carrier.approvalprocess.LoanDetailActivity" , map );
				pushUtil.run();
			}
			else
			{
				TSubsuppliers supplier = subService.get( subId );
				String subno = supplier.getVcSubno();// 获取分工方编号
				String subAllName = supplier.getVcAllName();// 获取分供方全名
				entity.setVcSubno( subno );
				entity.setVcSubAllName( subAllName );
				entity.setNFlag( SystemConstants.SYS_LOAN_FLAG_COMMIT );// 提交申请
				entity.setNApprovalResult( 1 );// 设置为审批通过
				loanService.save( entity );
				// 审批通过 每个金融公司插入贷款询问记录
				List< TFinance > finances = financeService.getEnableFinance();
				if ( CollectionUtils.isEmpty( finances ) )
				{
					AjaxUtil.rendJson( response , true , "提交申请成功" );
					return;
				}
				for ( TFinance finance : finances )
				{
					TLoanAsk ask = new TLoanAsk();
					ask.setILoan( entity.getId() );// 设置申请贷款id
					ask.setVcFinanceno( finance.getVcFinanceno() );// 金融公司编号
					ask.setVcFinanceName( finance.getVcAllName() );// 金融公司名称
					ask.setVcLoanno( entity.getVcLoanno() );// 申请贷款编号
					askService.save( ask );
				}
			}
			AjaxUtil.rendJson( response , true , "提交申请成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "提交申请失败 ：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 获取金融方利率列表
	 */
	@RequestMapping( value = "/getRateList" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取金融方利率列表" , notes = "ID:主键<br/>"
	        + "VC_FINANCE_NAME:金融公司名称<br/>" + "N_ACCRUAL_LAST: 成交利息（我们给申请贷款方看的利息数据）<br/>"
	        + "VC_TEL:联系方式<br/>" , position = 5 )
	public Map< String , Object > getRateList()
	{
		try
		{
			List< Map< String , Object >> result = loanService.getRateList();
			return AjaxUtil.getMapByNotException( true , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 利率选择
	 */
	@RequestMapping( value = "/selectRate" , method = RequestMethod.POST )
	@ApiOperation( value = "利率选择" , notes = "利率选择" , position = 5 , response = TLoan.class )
	public void selectRate(
	        @ApiParam( "loanId:贷款id" ) @RequestParam( value = "loanId" ) int loanId ,
	        @ApiParam( "loanAskId:贷款询问id" ) @RequestParam( value = "loanAskId" ) int loanAskId ,
	        HttpServletResponse response )
	{
		TLoan loan = loanService.get( loanId );
		TLoanAsk ask = askDao.get( loanAskId );
		loan.setVcFinanceno( ask.getVcFinanceno() );// 预向贷款公司编号
		loan.setVcFinanceName( ask.getVcFinanceName() );// 预向贷款公司名称
		try
		{
			loanService.update( loan );
			AjaxUtil.rendJson( response , true , "利率选择成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "利率选择失败" );
		}
	}
}
