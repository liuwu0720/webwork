package com.clt.finance.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.clt.finance.model.TFinance;
import com.clt.finance.model.TLoanAsk;
import com.clt.finance.service.IFinanceService;
import com.clt.finance.service.ILoanAskService;
import com.clt.sub.model.TLoan;
import com.clt.sub.service.ILoanService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IPictureService;
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
 * @Package com.clt.finance.controller
 * @Description: 金融公司对贷款申请的答复处理Action
 * @author hjx
 * @date 2014年9月10日 下午2:01:03
 * @version V1.0
 */
@Controller
@RequestMapping( "/loanAskAction" )
@Api( value = "LoanAskAction-api" , description = "金融相关接口" , position = 5 )
public class LoanAskAction
{
	@Autowired
	private ILoanService loanService;
	@Autowired
	private ILoanAskService askService;
	@Autowired
	private IFinanceService financeService;
	@Autowired
	private IPictureService pictureService;
	
	/**
	 * @Description: 打开贷款询问页面，展示贷款申请内容，和对于贷款的处理内容
	 * @param request
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年9月10日 下午2:02:11
	 */
	@RequestMapping( value = "/openAskPage" , method = RequestMethod.GET )
	@ApiIgnore
	public String openAskPage( HttpServletRequest request )
	{
		String askId = request.getParameter( "id" );
		String loanId = request.getParameter( "loanId" );
		if ( StringUtils.isNotBlank( loanId ) && StringUtils.isNotBlank( loanId ) )
		{
			TLoan loan = loanService.get( Integer.parseInt( loanId ) );
			TLoanAsk loanAsk = askService.get( Integer.parseInt( askId ) );
			request.setAttribute( "loan" , loan );
			request.setAttribute( "loanAsk" , loanAsk );
		}
		else
		{
			request.setAttribute( "msg" , "参数信息不完整！" );
			// 跳转到提示页面
			return "front/msg";
		}
		return "finance/loan/loanDetail";
	}
	
	/**
	 * @Description: 打开某个贷款申请的对应 给予贷款信息
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年9月19日 上午9:54:26
	 */
	@RequestMapping( value = "/openMeetList" , method = RequestMethod.GET )
	@ApiIgnore
	public String openMeetList()
	{
		return "finance/loan/loanMeetList";
	}
	
	/**
	 * @Description: 查看正在洽谈中的信息
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author hjx
	 * @create_date 2014年9月18日 下午2:50:04
	 */
	@ApiIgnore
	@RequestMapping( value = "/getMeetList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getMeetList( HttpServletRequest request )
	{
		Map< String , Object > result = new HashMap< String , Object >();
		Page page = ServiceUtil.getcurrPage( request );
		HqlHelper helper = new HqlHelper( TLoanAsk.class );
		helper.setQueryPage( page );
		helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );// 取有效值
		// 限定条件
		String loanId = request.getParameter( "loanId" );
		helper.addEqual( "ILoan" , Integer.parseInt( loanId ) );
		// 查询条件
		String vcFinanceName = request.getParameter( "vcFinanceName" );
		if ( StringUtils.isNotBlank( vcFinanceName ) )
		{
			helper.addLike( "vcFinanceName" , vcFinanceName );
		}
		
		result = askService.findByHelper( helper );
		return result;
	}
	
	/**
	 * @Description: 更新Ask信息
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年9月19日 下午2:48:35
	 */
	@ApiIgnore
	@RequestMapping( value = "/saveLoanAsk" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > saveLoanAsk( HttpServletRequest request ,
	        HttpServletResponse response )
	{
		String loanAskId = request.getParameter( "loanAskId" );
		String NRepulse = request.getParameter( "NRepulse" );
		String NAccrual = request.getParameter( "NAccrual" );
		String vcCause = request.getParameter( "vcCause" );
		
		try
		{
			TLoanAsk ask = askService.get( Integer.parseInt( loanAskId ) );
			ask.setNRepulse( Integer.parseInt( NRepulse ) );
			
			if ( "0".equals( NRepulse ) )
			{
				ask.setVcCause( null );
				ask.setNAccrual( Float.parseFloat( NAccrual ) );
			}
			else
			{
				ask.setNAccrual( null );
				ask.setVcCause( vcCause );
			}
			askService.update( ask );
			return AjaxUtil.getMap( true , "操作成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description: 保存复利 （我们给分供方说看到的利息）
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年9月19日 下午2:54:35
	 */
	@ApiIgnore
	@RequestMapping( value = "/saveTwoAccrual" , method = RequestMethod.POST )
	public void saveTwoAccrual( HttpServletRequest request , HttpServletResponse response )
	{
		String loanAskId = request.getParameter( "loanAskId" );
		String NAccrualLast = request.getParameter( "NAccrualLast" );
		TLoanAsk ask = askService.get( Integer.parseInt( loanAskId ) );
		ask.setNAccrualLast( Float.parseFloat( NAccrualLast ) );
		try
		{
			askService.update( ask );
			AjaxUtil.rendJson( response , true , "操作成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "操作失败，失败原因：" + e.getMessage() );
		}
	}
	
	/**
	 * @Description 获取代办贷款列表
	 * @author chengwzh
	 * @date 2015/5/29 16:56
	 */
	@RequestMapping( value = "/getWaitLoanList" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取待办贷款列表" , notes = "获取待办贷款列表:<br/>" + "ID: 主键,<br/>"
	        + "VC_LOANNO:申请贷款编号 <br/>" + "VC_APPLY_USER_NAME:申请人姓名<br/>"
	        + "N_LOAN_TOTAL:贷款总额<br/>" + "DT_DEADLINE:期限<br/>" + "N_PAYID:还款方式id<br/>"
	        + "DT_APPLY:申请时间<br/>" + "payName:还款方式 <br/>" , position = 5 )
	public Map< String , Object > getWaitLoanList(
	        HttpServletRequest request ,
	        @ApiParam( "申请人姓名" ) @RequestParam( value = "vcApplyUserName" , required = false ) String vcApplyUserName )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		TFinance finance = financeService.get( subId );
		String financeNo = finance.getVcFinanceno();
		Page page = ServiceUtil.getcurrPage( request );
		try
		{
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
			Map< String , Object > map = askService.getWaitLoanList( page , financeNo ,
			        vcApplyUserName );
			return AjaxUtil.getMapByResult( visit , map );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 获取已办贷款
	 * @author chengwzh
	 * @date 2015/6/2 9:10
	 */
	@RequestMapping( value = "/getDoneLoanList" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取已办贷款列表" , notes = "获取已办贷款列表:<br/>" + "ID: 主键,<br/>"
	        + "VC_LOANNO:申请贷款编号<br/> " + "VC_APPLY_USER_NAME:申请人姓名<br/>"
	        + "N_LOAN_TOTAL:贷款总额<br/>" + "DT_DEADLINE:期限<br/>" + "N_PAYID:还款方式id<br/>"
	        + "DT_APPLY:申请时间<br/>" + "VC_USER_NAME:审批人姓名<br/>" + "DT_APPROVAL:审批时间<br/>"
	        + "payName:还款方式<br/> " , position = 5 )
	public Map< String , Object > getDoneLoanList(
	        HttpServletRequest request ,
	        @ApiParam( "申请人姓名" ) @RequestParam( value = "vcApplyUserName" , required = false ) String vcApplyUserName )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			int subId = user.getiArchive();
			TFinance finance = financeService.get( subId );
			String financeNo = finance.getVcFinanceno();
			Page page = ServiceUtil.getcurrPage( request );
			String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
			Map< String , Object > result = askService.getDoneLoans( page , financeNo ,
			        vcApplyUserName );
			return AjaxUtil.getMapByResult( visit , result );
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Description 贷款审批
	 * @author chengwzh
	 * @date 2015/6/2 11:00
	 */
	@RequestMapping( value = "/manageLoanAsk" , method = RequestMethod.POST )
	@ApiOperation( value = "贷款审批" , notes = "int NRepulse 是否给予贷款（1是，2否）" , response = TLoanAsk.class , position = 5 )
	@ResponseBody
	public Map< String , Object > manageLoanAsk(
	        @ApiParam( value = "是否给予贷款（1是，2否）" ) @RequestParam( value = "NRepulse" ) int NRepulse ,
	        @ApiParam( value = "贷款询问id" ) @RequestParam( value = "askId" ) int askId ,
	        @ApiParam( value = "年利率" , required = false ) @RequestParam( value = "NAccrual" , required = false ) Float NAccrual ,
	        @ApiParam( value = "手续费" , required = false ) @RequestParam( value = "NFee" , required = false ) Double NFee ,
	        @ApiParam( value = "备注" , required = false ) @RequestParam( value = "vcNote" ) String vcNote ,
	        HttpServletRequest request , HttpServletResponse response )
	{
		if ( NRepulse != 1 && NRepulse != 2 )
		{
			return AjaxUtil.getMap( false , "请输入1或2" );
		}
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		try
		{
			TLoanAsk entity = askService.get( askId );
			entity.setNRepulse( NRepulse );// 是否贷款
			entity.setVcUserName( user.getVcUsername() );// 添加审批人
			entity.setDtApproval( new Date() );// 添加审批时间
			// 如果拒绝贷款
			if ( NRepulse == 2 )
			{
				entity.setNEnable( SystemConstants.SYS_DISABLE );
				entity.setNRepulse( 2 );
			}
			else
			{
				if ( NAccrual != null )
				{
					entity.setNAccrual( NAccrual );
				}
				if ( NFee != null )
				{
					entity.setNFee( NFee );
				}
			}
			if ( StringUtils.isNotBlank( vcNote ) )
			{
				entity.setVcNote( vcNote );
			}
			askService.update( entity );
			return AjaxUtil.getMap( true , "审批成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 通过id获取页面详情
	 * @author chengwzh
	 * @date 2015/6/2
	 */
	@RequestMapping( value = "/getDetailById" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "通过id获取页面详情" , notes = "ID:主键<br/>"
	        + "VC_APPLY_USER_NAME:申请人姓名<br/>" + "DT_APPLY:申请时间<br/>"
	        + "N_GETID:贷款方式id<br/>" + "VC_SUB_ALL_NAME:分供方全名<br/>"
	        + "VC_EXCUSE:申请理由<br/>" + "N_LOAN_TOTAL:贷款总额<br/> " + "DT_DEADLINE:期限<br/>"
	        + "N_RATE:利率<br/>" + "N_PAYID:还款方式id<br/>" + "VC_NOTE:备注<br/>"
	        + "N_ACCRUAL: 年利率<br/>" + "N_FEE:手续费<br/>" + "DT_ACCRUAL:放贷时间<br/>"
	        + "payName: 还款方式<br/>" + "getName: 贷款方式<br/>" , position = 5 )
	public Map< String , Object > getDetailById(
	        @ApiParam( value = "主键" ) @RequestParam( value = "id" ) int id )
	{
		TLoanAsk ask = askService.get( id );
		if ( ask == null )
		{
			return AjaxUtil.getMapByNotException( false , null );
		}
		int loanAskId = ask.getId();
		try
		{
			Map< String , Object > result = askService.getLoanById( loanAskId );
			return AjaxUtil.getMapByNotException( true , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * @Descrption 评价
	 */
	@RequestMapping( value = "/appraise" , method = RequestMethod.POST )
	@ApiOperation( value = "评价" , notes = "id:贷款询问id,score 评分" , position = 5 , response = TLoanAsk.class )
	public void appraise(
	        @ApiParam( value = "主键" ) @RequestParam( value = "id" ) int id ,
	        @ApiParam( value = "评分" ) @RequestParam( value = "score" ) int score ,
	        @ApiParam( value = "还款评价" , required = false ) @RequestParam( value = "vcAppraise" , required = false ) String vcAppraise ,
	        HttpServletResponse response )
	{
		TLoanAsk ask = askService.get( id );
		int loanId = ask.getILoan();
		try
		{
			TLoan entity = loanService.get( loanId );
			entity.setNScore( score );
			entity.setVcAppraise( vcAppraise );
			entity.setnIfAppraise( 1 );// 已评价
			loanService.update( entity );
			AjaxUtil.rendJson( response , true , "评价成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "评价失败" );
		}
	}
	
	@RequestMapping( value = "/updateFinance" , method = RequestMethod.POST )
	@ApiOperation( value = "完善金融方信息" , position = 5 , response = TLoanAsk.class )
	public void updateFinance(
	        @ApiParam( "上传营业执照用的字段:lisenceImg" ) @RequestParam( value = "lisenceImg" , required = false ) CommonsMultipartFile[] lisenceImg ,
	        @ApiParam( "上传营业执照用的字段:logoImg" ) @RequestParam( value = "logoImg" , required = false ) CommonsMultipartFile[] logoImg ,
	        @ModelAttribute TFinance entity , HttpServletResponse response ,
	        HttpServletRequest request )
	{
		Integer id = entity.getId();
		if ( id == null )
		{
			AjaxUtil.rendJson( response , true , "保存失败：主键不能为空" );
		}
		TFinance finance = financeService.get( id );
		entity.setVcFinanceno( finance.getVcFinanceno() );
		entity.setNEnable( finance.getNEnable() );
		String imgPath = entity.getVcLisencePath();
		if ( StringUtils.isNotBlank( imgPath ) )
		{
			imgPath = imgPath.substring( imgPath.lastIndexOf( "/" ) + 1 );
			entity.setVcLisencePath( imgPath );
		}
		String logoPath = entity.getVcLogoPath();
		if ( StringUtils.isNotBlank( logoPath ) )
		{
			logoPath = logoPath.substring( logoPath.lastIndexOf( "/" ) + 1 );
			entity.setVcLogoPath( logoPath );
		}
		try
		{
			uploadImg( request , lisenceImg , entity );
			uploadLogo( request , logoImg , entity );
			financeService.updateCleanBefore( entity );
			AjaxUtil.rendJson( response , true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , true , "保存失败：" + e.getMessage() );
		}
	}
	
	/**
	 * 上传营业执照图片私有方法
	 * 
	 * @param request
	 * @param files
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void uploadImg( HttpServletRequest request , CommonsMultipartFile[] files ,
	        TFinance entity ) throws FileNotFoundException , IOException
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
				String source = pictureService.getPathById( 25 );// 金融公司图片（营业执照）
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
				String imgPath = entity.getVcLisencePath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					// store.setVcImagePath( jpgPath );
					entity.setVcLisencePath( jpgPath );
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
					entity.setVcLisencePath( jpgPath );
				}
				System.out.println( "图片'" + path + "'上传成功" );
			}
		}
	}
	
	/**
	 * 上传logo图片私有方法
	 * 
	 * @param request
	 * @param files
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void uploadLogo( HttpServletRequest request , CommonsMultipartFile[] files ,
	        TFinance entity ) throws FileNotFoundException , IOException
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
				String source = pictureService.getPathById( 25 );// 金融公司图片（营业执照）
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
				String imgPath = entity.getVcLogoPath();
				if ( StringUtils.isBlank( imgPath ) )
				{
					// store.setVcImagePath( jpgPath );
					entity.setVcLogoPath( jpgPath );
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
					entity.setVcLogoPath( jpgPath );
				}
				System.out.println( "图片'" + path + "'上传成功" );
			}
		}
	}
	
	@RequestMapping( value = "/getFinanceMsg" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取金融公司信息" , notes = "id:主键<br/>" + "vcAllName:金融公司全称<br/>"
	        + "vcShortName:金融公司简称<br/>" + "vcAddress:金融公司注册地址<br/>"
	        + "vcProvince:所属省份<br/>" + "vcCity:所属城市<br/>" + "vcArea:所属地区<br/>"
	        + "vcDetailedAddress:金融公司地址<br/>" + "NApply:是否可贷款（0可，1不可，默认为1）<br/>"
	        + "vcApplyFile贷款证明<br/>" + "NEnable:是否有效（0有效，1无效）<br/>" + "NType:公司类型<br/>"
	        + "vcFinanceno:金融公司编号<br/>" + "vcTel:金融公司联系电话<br/>"
	        + "vcLisencePath:金融公司营业执照路径<br/>" + "vcLogoPath:金融公司logo图片路径<br/>" , position = 5 )
	public Map< String , Object > getFinanceMsg( HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		int subId = user.getiArchive();
		try
		{
			TFinance entity = financeService.get( subId );
			String imgPath = entity.getVcLisencePath();// 营业执照图片路径
			String logoPath = entity.getVcLogoPath();// logo图片路径
			imgPath = askService.pathManage( imgPath );// 处理营业执照图片路径
			entity.setVcLisencePath( imgPath );
			logoPath = askService.pathManage( logoPath );// 处理logo图片路径
			entity.setVcLogoPath( logoPath );
			return AjaxUtil.getMapByNotException( true , entity );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
}
