package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TLoan;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

public interface ILoanService
{
	public TLoan get( Integer id );
	
	public void update( TLoan entity );
	
	public void save( TLoan entity );
	
	public void saveOrUpdate( TLoan entity );
	
	public void delete( TLoan entity );
	
	public void deleteByKey( Integer id );
	
	public List< TLoan > loadAll();
	
	public List< TLoan > findAll( Page page );
	
	/**
	 * @Description: 对申请贷款信息做处理，和指定的（或者全部）金融公司进行洽谈，更改申请记录状态为 洽谈，并给金融公司发送消息
	 * @param loanId
	 *            要洽谈的对象 申请贷款记录
	 * @param financeIds
	 *            金融公司们的id，和这些金融公司洽谈，如果为null，则是和所有金融公司洽谈 void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月16日 下午6:49:18
	 */
	public void saveMeetFinance( Integer loanId , List< String > financeIds );
	
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	// 获取司机已审批的贷款列表
	public Map< String , Object > getDriverApprovaledLoans( Page page , int userId ,
	        String vcLoanno , Integer NApprovalResult );
	
	// 获取分供方已审批的贷款列表
	public Map< String , Object > getSubApprovaledLoans( Page page , String subno ,
	        String applyName , String vcLoanno , Integer NApprovalResult );
	
	// 获取司机待审批的贷款列表
	public Map< String , Object > getDriverWaitedLoans( Page page , int userId ,
	        String vcLoanno );
	
	// 获取分供方待审批的贷款列表
	public Map< String , Object > getSubWaitedLoans( Page page , String subno ,
	        String applyName , String vcLoanno );
	
	// 获取审核成功可以提交申请的贷款申请
	public Map< String , Object > getSuccessLoan( Page page , String subno );
	
	// 获取利率列表
	public List< Map< String , Object >> getRateList();
	
	public Map< String , Object > getById( int id );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hqlHelper
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-6-3 下午4:41:40
	 */
	public Map< String , Object > findAllByHqlHelp( HqlHelper hqlHelper );
}
