package com.clt.sub.service.imp;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.common.UserSession;
import com.clt.finance.model.TFinance;
import com.clt.finance.model.TLoanAsk;
import com.clt.finance.service.IFinanceService;
import com.clt.finance.service.ILoanAskService;
import com.clt.sub.dao.ILoanDao;
import com.clt.sub.dao.ILoanTypeDao;
import com.clt.sub.model.TLoan;
import com.clt.sub.model.TLoanHandle;
import com.clt.sub.model.TLoanType;
import com.clt.sub.service.ILoanHandleService;
import com.clt.sub.service.ILoanService;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IMessageService;
import com.clt.util.DateUtil;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;

@Service
public class LoanServiceImp implements ILoanService
{
	@Autowired
	ILoanDao loanDao;
	@Autowired
	IMessageService msgService;
	@Autowired
	ILoanHandleService handService;
	@Autowired
	ILoanTypeDao typeDao;
	@Autowired
	ILoanAskService askService;
	@Autowired
	JdbcTemplate jdbcDao;
	@Autowired
	IFinanceService financeService;
	
	public TLoan get( Integer id )
	{
		return loanDao.get( id );
	}
	
	public void update( TLoan entity )
	{
		loanDao.update( entity );
	}
	
	public void save( TLoan entity )
	{
		// 生成贷款编号
		String maxOrderNo = this.getMaxOrderNo();
		entity.setVcLoanno( maxOrderNo );
		loanDao.save( entity );
	}
	
	public void saveOrUpdate( TLoan entity )
	{
		String vcLoanno = entity.getVcLoanno();
		if ( StringUtils.isBlank( vcLoanno ) )
		{
			String maxOrderNo = this.getMaxOrderNo();
			entity.setVcLoanno( maxOrderNo );
		}
		loanDao.saveOrUpdate( entity );
	}
	
	public void delete( TLoan entity )
	{
		entity.setNEnable( SystemConstants.SYS_DISABLE );
		loanDao.update( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		TLoan tLoan = loanDao.get( id );
		this.delete( tLoan );
		// loanDao.deleteByKey( id );
	}
	
	public List< TLoan > loadAll()
	{
		return loanDao.loadAll();
	}
	
	public List< TLoan > findAll( Page page )
	{
		return loanDao.findAll( page );
	}
	
	/**
	 * @Description: 洽谈操作。 对申请贷款信息做处理，和指定的（或者全部）金融公司进行洽谈，更改申请记录状态为 洽谈，并给金融公司发送消息
	 * @param loanId
	 *            要洽谈的对象 申请贷款记录
	 * @param financeIds
	 *            金融公司们的id，和这些金融公司洽谈，如果为null，则是和所有金融公司洽谈 void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 下午6:49:18
	 */
	public void saveMeetFinance( Integer loanId , List< String > financeIds )
	{
		TLoan loan = loanDao.get( loanId );
		if ( null == loan )
		{
			ServiceUtil.rollback();
		}
		else
		{
			// 获得当前Session中的用户
			TUser user = ( TUser ) UserSession.get( "user" );
			loan.setNFlag( SystemConstants.SYS_LOAN_FLAG_MEET );
			loanDao.save( loan );
			// 保存到《贷款处理信息表》
			TLoanHandle entity = new TLoanHandle();
			entity.setIUser( user.getId() );
			entity.setILoan( loanId );
			entity.setVcUserName( user.getVcUsername() );
			entity.setNTalk( 0 );
			Date date = new Date();
			entity.setDtTalk( date );
			handService.save( entity );
			if ( CollectionUtils.isNotEmpty( financeIds ) )
			{
				// 不为空，指定金融公司
				for ( String id : financeIds )
				{
					// 保存到贷款处理信息表
					TLoanAsk ask = new TLoanAsk();
					ask.setDtAsk( new Date() );
					ask.setILoan( loan.getId() );
					ask.setILoanHandle( entity.getId() );
					TFinance tFinance = financeService.get( Integer.parseInt( id ) );
					ask.setVcFinanceno( tFinance.getVcFinanceno() );
					ask.setVcFinanceName( tFinance.getVcAllName() );
					ask.setVcLoanno( loan.getVcLoanno() );
					ask.setDtAsk( date );
					askService.save( ask );
					msgService.sendMsg( "申请贷款" ,
					        StringUtils.trimToEmpty( loan.getVcSubAllName() ) + "申请贷款:"
					                + loan.getNLoanTotal() + "元，请查看！" ,
					        "loanAskAction/openAskPage?id=" + ask.getId() + "&loanId"
					                + loan.getId() , String.valueOf( user.getId() ) ,
					        user.getVcUsername() ,
					        String.valueOf( SystemConstants.SYS_TARCHIVE_FINANCE ) , id ,
					        SystemConstants.SYS_MSG_LOAN );
					
				}
				
			}
			else
			{
				// 为空，指定档案类型为金融公司的
				/*msgService.sendMsg(
				        "申请贷款" ,
				        loan.getVcSubAllName() + "申请贷款:" + loan.getNLoanTotal()
				                + "元，请查看！" ,
				        "loanAskAction/openAskPage?id=" + ask.getId() + "&loanId"
				                + loan.getId() , String.valueOf( user.getId() ) ,
				        user.getVcUsername() ,
				        String.valueOf( SystemConstants.SYS_TARCHIVE_FINANCE ) ,
				        SystemConstants.SYS_MSG_LOAN );*/
				List< TFinance > list = financeService.loadAllByEnable();
				for ( TFinance finance : list )
				{
					// 保存到贷款处理信息表
					TLoanAsk ask = new TLoanAsk();
					ask.setDtAsk( new Date() );
					ask.setILoan( loan.getId() );
					ask.setILoanHandle( entity.getId() );
					ask.setVcFinanceno( finance.getVcFinanceno() );
					ask.setVcFinanceName( finance.getVcAllName() );
					ask.setVcLoanno( loan.getVcLoanno() );
					ask.setDtAsk( date );
					askService.save( ask );
					msgService.sendMsg( "申请贷款" ,
					        StringUtils.trimToEmpty( loan.getVcSubAllName() ) + "申请贷款:"
					                + loan.getNLoanTotal() + "元，请查看！" ,
					        "loanAskAction/openAskPage?id=" + ask.getId() + "&loanId"
					                + loan.getId() , String.valueOf( user.getId() ) ,
					        user.getVcUsername() ,
					        String.valueOf( SystemConstants.SYS_TARCHIVE_FINANCE ) ,
					        String.valueOf( finance.getId() ) ,
					        SystemConstants.SYS_MSG_LOAN );
				}
				
			}
			
		}
	}
	
	/**
	 * @Description: 根据HqlHelper查询列表信息
	 * @param helper
	 * @return
	 * @author hjx
	 * @create_date 2014年8月28日 下午2:41:44
	 */
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return loanDao.findAllByHqlHelp( helper );
	}
	
	/**
	 * @Description: 生成贷款编号，以L开始
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年9月3日 下午3:21:16
	 */
	private synchronized String getMaxOrderNo()
	{
		
		List< TLoan > orderlist = loanDao.findAllAndOrderByProperty( "id" , false );
		String orderno = "L";
		String datestr = DateUtil.getDate( "yyMMdd" );
		DecimalFormat df = new DecimalFormat( "0000" );
		String str2 = "";
		if ( orderlist.size() == 0 )
		{
			str2 = df.format( Integer.parseInt( "1" ) );
			
		}
		else
		{
			TLoan loan = orderlist.get( 0 );
			
			String str = loan.getVcLoanno().substring( 7 , loan.getVcLoanno().length() );
			str2 = df.format( Integer.parseInt( str ) + 1 );
		}
		
		orderno += datestr + str2;
		System.out.println( "Max Order " + orderno );
		return orderno;
	}
	
	/**
	 * @Description 司机获取待审批贷款列表
	 * @param approvalResult
	 *            审批结果（0:未审批（默认），1:分供方审批通过，2：分供方拒绝审批)
	 */
	public Map< String , Object > getDriverWaitedLoans( Page page , int userId ,
	        String vcLoanno )
	{
		
		String sql = "SELECT ID,I_APPLY_USER,VC_APPLY_USER_NAME,VC_SUBNO,"
		        + "VC_SUB_ALL_NAME,DT_APPLY,VC_EXCUSE,N_LOAN_TOTAL,DT_DEADLINE,"
		        + "VC_NOTE,VC_LOANNO,N_GETID,N_PAYID FROM T_LOAN WHERE N_ENABLE ="
		        + SystemConstants.SYS_ENABLE
		        + " and N_APPROVAL_RESULT=0 and I_APPLY_USER=" + userId;
		// + " order by DT_APPLY";
		if ( StringUtils.isNotBlank( vcLoanno ) )
		{
			sql += " and VC_LOANNO='" + vcLoanno.trim() + "'";
		}
		sql += " order by DT_APPLY";
		Map< String , Object > map = loanDao.getSpringSQL( sql , page );
		List< Map< String , Object >> list = ( List< Map< String , Object >> ) map
		        .get( "rows" );
		for ( Map< String , Object > loan : list )
		{
			Object getIdObj = loan.get( "N_GETID" );
			if ( getIdObj != null )
			{
				int getId = Integer.parseInt( getIdObj.toString() );// 获取贷款id
				TLoanType t1 = typeDao.get( getId );
				String getTypeName = t1.getVcTypename();
				loan.put( "getTypeName" , getTypeName );// 添加贷款方式字段
			}
			else
			{
				loan.put( "getTypeName" , "暂无" );
			}
			Object payIdObj = loan.get( "N_PAYID" );
			if ( payIdObj != null )
			{
				int payId = Integer.parseInt( payIdObj.toString() );// 获取还款id
				TLoanType t2 = typeDao.get( payId );
				String payTypeName = t2.getVcTypename();
				loan.put( "payTypeName" , payTypeName );// 添加还款方式字段
			}
			else
			{
				loan.put( "payTypeName" , "暂无" );
			}
		}
		map.put( "rows" , list );
		return map;
	}
	
	/**
	 * @Description 分供方获取待审批贷款列表（app）
	 * @param approvalResult
	 *            审批结果（0:未审批（默认），1:分供方审批通过，2：分供方拒绝审批)
	 * 
	 * @author chengwzh
	 * @date 2015/5/27 16:40
	 */
	public Map< String , Object > getSubWaitedLoans( Page page , String subno ,
	        String applyName , String vcLoanno )
	{
		String sql = "SELECT ID,I_APPLY_USER,VC_APPLY_USER_NAME,VC_SUBNO,"
		        + "VC_SUB_ALL_NAME,DT_APPLY,VC_EXCUSE,N_LOAN_TOTAL,DT_DEADLINE,"
		        + "VC_NOTE,VC_LOANNO,N_GETID,N_PAYID FROM T_LOAN WHERE N_ENABLE ="
		        + SystemConstants.SYS_ENABLE + " and N_APPROVAL_RESULT=0 and VC_SUBNO='"
		        + subno + "'";
		if ( StringUtils.isNotBlank( applyName ) )
		{
			sql += " and VC_APPLY_USER_NAME='" + applyName.trim() + "'";
		}
		if ( StringUtils.isNotBlank( vcLoanno ) )
		{
			sql += " and VC_LOANNO='" + vcLoanno + "'";
		}
		sql += " order by DT_APPLY";
		Map< String , Object > map = loanDao.getSpringSQL( sql , page );
		List< Map< String , Object >> list = ( List< Map< String , Object >> ) map
		        .get( "rows" );
		for ( Map< String , Object > loan : list )
		{
			Object getIdObj = loan.get( "N_GETID" );
			if ( getIdObj != null )
			{
				int getId = Integer.parseInt( getIdObj.toString() );// 获取贷款id
				TLoanType t1 = typeDao.get( getId );
				String getTypeName = t1.getVcTypename();
				loan.put( "getTypeName" , getTypeName );// 添加贷款方式字段
			}
			else
			{
				loan.put( "getTypeName" , "暂无" );
			}
			Object payIdObj = loan.get( "N_PAYID" );
			if ( payIdObj != null )
			{
				int payId = Integer.parseInt( payIdObj.toString() );// 获取还款id
				TLoanType t2 = typeDao.get( payId );
				String payTypeName = t2.getVcTypename();
				loan.put( "payTypeName" , payTypeName );// 添加还款方式字段
			}
			else
			{
				loan.put( "payTypeName" , "暂无" );
			}
		}
		map.put( "rows" , list );
		return map;
	}
	
	/**
	 * @Description 根据id获取贷款申请详情
	 * 
	 */
	public Map< String , Object > getById( int id )
	{
		String sql = "select id,VC_APPLY_USER_NAME,DT_APPLY,N_GETID,VC_SUB_ALL_NAME,VC_EXCUSE,"
		        + "N_LOAN_TOTAL,N_RATE,DT_DEADLINE,N_PAYID,VC_NOTE,N_APPROVAL_RESULT, "
		        + "VC_APPROVAL_NAME,DT_APPROVAL,VC_APPROVAL_OPTION from t_loan where id="
		        + id;
		List< Map< String , Object >> result = jdbcDao.queryForList( sql );
		if ( CollectionUtils.isEmpty( result ) )
		{
			return null;
		}
		Map< String , Object > loan = result.get( 0 );
		Object getIdObj = loan.get( "N_GETID" );
		if ( getIdObj != null )
		{
			int getId = Integer.parseInt( getIdObj.toString() );
			TLoanType t1 = typeDao.get( getId );
			String getTypeName = t1.getVcTypename();
			loan.put( "getTypeName" , getTypeName );
		}
		else
		{
			loan.put( "getTypeName" , "暂无" );
		}
		Object payIdObj = loan.get( "N_PAYID" );
		if ( payIdObj != null )
		{
			int payId = Integer.parseInt( payIdObj.toString() );
			TLoanType t2 = typeDao.get( payId );
			String payTypeName = t2.getVcTypename();
			loan.put( "payTypeName" , payTypeName );
		}
		else
		{
			loan.put( "payTypeName" , "暂无" );
		}
		return loan;
	}
	
	/**
	 * @Description 获取司机已审批的贷款列表
	 */
	public Map< String , Object > getDriverApprovaledLoans( Page page , int userId ,
	        String vcLoanno , Integer NApprovalResult )
	{
		
		String sql = "SELECT ID,I_APPLY_USER,VC_APPLY_USER_NAME,VC_SUBNO,"
		        + "VC_SUB_ALL_NAME,DT_APPLY,VC_EXCUSE,N_LOAN_TOTAL,DT_DEADLINE,"
		        + "VC_NOTE,VC_LOANNO,N_GETID,N_PAYID ,N_APPROVAL_RESULT,DT_APPROVAL, "
		        + "VC_APPROVAL_NAME,VC_APPROVAL_OPTION FROM T_LOAN WHERE N_ENABLE ="
		        + SystemConstants.SYS_ENABLE + " and I_APPLY_USER=" + userId
		        + " and N_APPROVAL_RESULT!=0";
		if ( StringUtils.isNotBlank( vcLoanno ) )
		{
			sql += " and VC_LOANNO='" + vcLoanno.trim() + "'";
		}
		if ( NApprovalResult != null )
		{
			sql += " and N_APPROVAL_RESULT=" + NApprovalResult;
		}
		sql += " order by DT_APPROVAL";
		Map< String , Object > map = loanDao.getSpringSQL( sql , page );
		List< Map< String , Object >> list = ( List< Map< String , Object >> ) map
		        .get( "rows" );
		for ( Map< String , Object > loan : list )
		{
			Object getIdObj = loan.get( "N_GETID" );
			if ( getIdObj != null )
			{
				int getId = Integer.parseInt( getIdObj.toString() );// 获取贷款id
				TLoanType t1 = typeDao.get( getId );
				String getTypeName = t1.getVcTypename();
				loan.put( "getTypeName" , getTypeName );// 添加贷款方式字段
			}
			else
			{
				loan.put( "getTypeName" , "暂无" );
			}
			Object payIdObj = loan.get( "N_PAYID" );
			if ( payIdObj != null )
			{
				int payId = Integer.parseInt( payIdObj.toString() );// 获取还款id
				TLoanType t2 = typeDao.get( payId );
				String payTypeName = t2.getVcTypename();
				loan.put( "payTypeName" , payTypeName );// 添加还款方式字段
			}
			else
			{
				loan.put( "payTypeName" , "暂无" );
			}
		}
		map.put( "rows" , list );
		return map;
		
	}
	
	/**
	 * @Description 获取分供方已审批的贷款列表
	 * @author chengwzh
	 * @date 2015/5/28 16:30
	 */
	public Map< String , Object > getSubApprovaledLoans( Page page , String subno ,
	        String applyName , String vcLoanno , Integer NApprovalResult )
	{
		
		String sql = "SELECT ID,I_APPLY_USER,VC_APPLY_USER_NAME,VC_SUBNO,"
		        + "VC_SUB_ALL_NAME,DT_APPLY,VC_EXCUSE,N_LOAN_TOTAL,DT_DEADLINE,"
		        + "VC_NOTE,VC_LOANNO,N_GETID,N_PAYID ,N_APPROVAL_RESULT,DT_APPROVAL, "
		        + "VC_APPROVAL_NAME,VC_APPROVAL_OPTION FROM T_LOAN WHERE N_ENABLE ="
		        + SystemConstants.SYS_ENABLE + " and VC_SUBNO='" + subno
		        + "' and N_APPROVAL_RESULT!=0";
		if ( StringUtils.isNotBlank( applyName ) )
		{
			sql += " and VC_APPLY_USER_NAME='" + applyName.trim() + "'";
		}
		if ( StringUtils.isNotBlank( vcLoanno ) )
		{
			sql += " and VC_LOANNO='" + vcLoanno.trim() + "'";
		}
		if ( NApprovalResult != null )
		{
			sql += " and N_APPROVAL_RESULT=" + NApprovalResult;
		}
		sql += " order by DT_APPROVAL desc ";
		Map< String , Object > map = loanDao.getSpringSQL( sql , page );
		List< Map< String , Object >> list = ( List< Map< String , Object >> ) map
		        .get( "rows" );
		for ( Map< String , Object > loan : list )
		{
			Object getIdObj = loan.get( "N_GETID" );
			if ( getIdObj != null )
			{
				int getId = Integer.parseInt( getIdObj.toString() );// 获取贷款id
				TLoanType t1 = typeDao.get( getId );
				String getTypeName = t1.getVcTypename();
				loan.put( "getTypeName" , getTypeName );// 添加贷款方式字段
			}
			else
			{
				loan.put( "getTypeName" , "暂无" );
			}
			Object payIdObj = loan.get( "N_PAYID" );
			if ( payIdObj != null )
			{
				int payId = Integer.parseInt( payIdObj.toString() );// 获取还款id
				TLoanType t2 = typeDao.get( payId );
				String payTypeName = t2.getVcTypename();
				loan.put( "payTypeName" , payTypeName );// 添加还款方式字段
			}
			else
			{
				loan.put( "payTypeName" , "暂无" );
			}
		}
		map.put( "rows" , list );
		return map;
	}
	
	/**
	 * 获取分供方审核成功可以提交申请的贷款申请 approvalResult 审批结果（0:未审批（默认），1:分供方审批通过，2：分供方拒绝审批)
	 * 
	 * @return
	 */
	public Map< String , Object > getSuccessLoan( Page page , String subno )
	{
		String sql = "SELECT ID,I_APPLY_USER,VC_APPLY_USER_NAME,VC_SUBNO,"
		        + "VC_SUB_ALL_NAME,DT_APPLY,VC_EXCUSE,N_LOAN_TOTAL,DT_DEADLINE,"
		        + "VC_NOTE,VC_LOANNO,N_GETID,N_PAYID ,N_APPROVAL_RESULT,DT_APPROVAL "
		        + "FROM T_LOAN WHERE N_ENABLE =" + SystemConstants.SYS_ENABLE
		        + " and VC_SUBNO='" + subno
		        + "' and N_APPROVAL_RESULT=1 order by DT_APPROVAL";
		Map< String , Object > map = loanDao.getSpringSQL( sql , page );
		List< Map< String , Object >> list = ( List< Map< String , Object >> ) map
		        .get( "rows" );
		for ( Map< String , Object > loan : list )
		{
			Object getIdObj = loan.get( "N_GETID" );
			if ( getIdObj != null )
			{
				int getId = Integer.parseInt( getIdObj.toString() );// 获取贷款id
				TLoanType t1 = typeDao.get( getId );
				String getTypeName = t1.getVcTypename();
				loan.put( "getTypeName" , getTypeName );// 添加贷款方式字段
			}
			else
			{
				loan.put( "getTypeName" , "暂无" );
			}
			Object payIdObj = loan.get( "N_PAYID" );
			if ( payIdObj != null )
			{
				int payId = Integer.parseInt( payIdObj.toString() );// 获取还款id
				TLoanType t2 = typeDao.get( payId );
				String payTypeName = t2.getVcTypename();
				loan.put( "payTypeName" , payTypeName );// 添加还款方式字段
			}
			else
			{
				loan.put( "payTypeName" , "暂无" );
			}
		}
		map.put( "rows" , list );
		return map;
	}
	
	// 获取利率列表
	public List< Map< String , Object >> getRateList()
	{
		String sql = "select id,VC_FINANCE_NAME,N_ACCRUAL_LAST,VC_TEL"
		        + " from t_loan_ask where N_ENABLE=" + SystemConstants.SYS_ENABLE
		        + " and N_REPULSE=0";
		List< Map< String , Object >> result = jdbcDao.queryForList( sql );
		return result;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hqlHelper
	 * @return
	 * @author liuwu
	 * @create_date 2015-6-3 下午4:41:49
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hqlHelper )
	{
		// TODO Auto-generated method stub
		return loanDao.findAllByHqlHelp( hqlHelper );
	}
	
}
