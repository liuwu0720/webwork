package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * 在线申请贷款 TLoan entity. @author MyEclipse Persistence Tools
 */
/**
 * @Package com.clt.sub.model
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年9月5日 上午11:44:13
 * @version V1.0
 */
@Entity
@Table( name = "T_LOAN" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TLoan implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4324134261004275627L;
	@ApiParam( value = "主键" )
	private Integer id;
	@ApiParam( value = "是否有效（0有效，1无效）" )
	private Integer NEnable;
	@ApiParam( value = "申请人id" )
	private Integer IApplyUser;
	@ApiParam( value = "申请人姓名" )
	private String vcApplyUserName;
	@ApiParam( value = "分供方编号" )
	private String vcSubno;
	@ApiParam( value = "分供方全名" )
	private String vcSubAllName;
	@ApiParam( value = "申请时间" )
	private Date dtApply;
	@ApiParam( value = "申请理由(格式：yyyy-MM-dd)" )
	private String vcExcuse;
	@ApiParam( value = "贷款总额" )
	private Float NLoanTotal;
	@ApiParam( value = "期限(格式：yyyy-MM-dd)" )
	private Date dtDeadline;
	@ApiParam( value = "抵押" )
	private String vcMortgage;
	@ApiParam( value = "备注" )
	private String vcNote;
	@ApiParam( value = "贷款状态（0，保存，1、提交申请，2在洽谈，3，已贷款，4，贷不到款）" )
	private Integer NFlag;
	@ApiParam( value = "预向贷款公司编号" )
	private String vcFinanceno;
	@ApiParam( value = "预向贷款公司名称" )
	private String vcFinanceName;
	@ApiParam( value = "实际贷款公司编号" )
	private String vcFinancenoReal;
	@ApiParam( value = "实际贷款公司名称" )
	private String vcFinanceNameReal;
	@ApiParam( value = "最终返回结果时间(格式：yyyy-MM-dd)" )
	private Date dtResult;
	@ApiParam( value = "申请贷款编号" )
	private String vcLoanno;
	@ApiParam( value = "添加时间(格式：yyyy-MM-dd)" )
	private Date dtAdd;
	@ApiParam( value = "洽谈开始时间(格式：yyyy-MM-dd)" )
	private Date dtTalk;
	@ApiParam( value = "贷款方式id" )
	private Integer NGetId;
	@ApiParam( value = "还款方式id" )
	private Integer NPayId;
	@ApiParam( value = "利率" )
	private Double NRate;
	@ApiParam( value = "审批结果（0:未审批（默认），1:分供方审批通过，2：分供方拒绝审批）" )
	private Integer NApprovalResult;
	@ApiParam( value = "审批时间(格式：yyyy-MM-dd)" )
	private Date dtApproval;
	@ApiParam( value = "审批人姓名" )
	private String vcApprovalName;
	@ApiParam( value = "审批意见" )
	private String vcApprovalOption;
	@ApiParam( value = "评分" )
	private Integer NScore;
	@ApiParam( value = "还款评价" )
	private String vcAppraise;
	@ApiParam( value = "是否评价（0为未评价（默认），1为已评价）" )
	private Integer nIfAppraise;
	
	// Constructors
	
	/** default constructor */
	public TLoan()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TLoanID" , sequenceName = "S_LOAN" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TLoanID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_IFAPPAISE" , precision = 22 , scale = 0 )
	public Integer getnIfAppraise()
	{
		return nIfAppraise;
	}
	
	public void setnIfAppraise( Integer nIfAppraise )
	{
		this.nIfAppraise = nIfAppraise;
	}
	
	@Column( name = "N_ENABLE" , precision = 22 , scale = 0 )
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
	}
	
	@Column( name = "I_APPLY_USER" , precision = 22 , scale = 0 )
	public Integer getIApplyUser()
	{
		return this.IApplyUser;
	}
	
	public void setIApplyUser( Integer IApplyUser )
	{
		this.IApplyUser = IApplyUser;
	}
	
	@Column( name = "VC_APPLY_USER_NAME" , length = 20 )
	public String getVcApplyUserName()
	{
		return this.vcApplyUserName;
	}
	
	public void setVcApplyUserName( String vcApplyUserName )
	{
		this.vcApplyUserName = vcApplyUserName;
	}
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "VC_SUB_ALL_NAME" , length = 100 )
	public String getVcSubAllName()
	{
		return this.vcSubAllName;
	}
	
	public void setVcSubAllName( String vcSubAllName )
	{
		this.vcSubAllName = vcSubAllName;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_APPLY" , length = 7 )
	public Date getDtApply()
	{
		return this.dtApply;
	}
	
	public void setDtApply( Date dtApply )
	{
		this.dtApply = dtApply;
	}
	
	@Column( name = "VC_EXCUSE" , length = 600 )
	public String getVcExcuse()
	{
		return this.vcExcuse;
	}
	
	public void setVcExcuse( String vcExcuse )
	{
		this.vcExcuse = vcExcuse;
	}
	
	@Column( name = "N_LOAN_TOTAL" , precision = 22 , scale = 0 )
	public Float getNLoanTotal()
	{
		return this.NLoanTotal;
	}
	
	public void setNLoanTotal( Float NLoanTotal )
	{
		this.NLoanTotal = NLoanTotal;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_DEADLINE" , length = 7 )
	public Date getDtDeadline()
	{
		return this.dtDeadline;
	}
	
	public void setDtDeadline( Date dtDeadline )
	{
		this.dtDeadline = dtDeadline;
	}
	
	@Column( name = "VC_MORTGAGE" , length = 200 )
	public String getVcMortgage()
	{
		return this.vcMortgage;
	}
	
	public void setVcMortgage( String vcMortgage )
	{
		this.vcMortgage = vcMortgage;
	}
	
	@Column( name = "VC_NOTE" , length = 1000 )
	public String getVcNote()
	{
		return this.vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	@Column( name = "N_FLAG" , precision = 22 , scale = 0 )
	public Integer getNFlag()
	{
		return this.NFlag;
	}
	
	public void setNFlag( Integer NFlag )
	{
		this.NFlag = NFlag;
	}
	
	@Column( name = "VC_FINANCENO" , length = 32 )
	public String getVcFinanceno()
	{
		return this.vcFinanceno;
	}
	
	public void setVcFinanceno( String vcFinanceno )
	{
		this.vcFinanceno = vcFinanceno;
	}
	
	@Column( name = "VC_FINANCE_NAME" , length = 100 )
	public String getVcFinanceName()
	{
		return this.vcFinanceName;
	}
	
	public void setVcFinanceName( String vcFinanceName )
	{
		this.vcFinanceName = vcFinanceName;
	}
	
	@Column( name = "VC_FINANCENO_REAL" , length = 32 )
	public String getVcFinancenoReal()
	{
		return this.vcFinancenoReal;
	}
	
	public void setVcFinancenoReal( String vcFinancenoReal )
	{
		this.vcFinancenoReal = vcFinancenoReal;
	}
	
	@Column( name = "VC_FINANCE_NAME_REAL" , length = 100 )
	public String getVcFinanceNameReal()
	{
		return this.vcFinanceNameReal;
	}
	
	public void setVcFinanceNameReal( String vcFinanceNameReal )
	{
		this.vcFinanceNameReal = vcFinanceNameReal;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_RESULT" , length = 7 )
	public Date getDtResult()
	{
		return this.dtResult;
	}
	
	public void setDtResult( Date dtResult )
	{
		this.dtResult = dtResult;
	}
	
	@Column( name = "VC_LOANNO" , length = 32 , nullable = false )
	public String getVcLoanno()
	{
		return vcLoanno;
	}
	
	public void setVcLoanno( String vcLoanno )
	{
		this.vcLoanno = vcLoanno;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return dtAdd;
	}
	
	/**
	 * @param dtAdd
	 *            the dtAdd to set
	 */
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_TALK" , length = 7 )
	public Date getDtTalk()
	{
		return dtTalk;
	}
	
	@Column( name = "VC_APPRAISE" , length = 100 )
	public String getVcAppraise()
	{
		return vcAppraise;
	}
	
	public void setVcAppraise( String vcAppraise )
	{
		this.vcAppraise = vcAppraise;
	}
	
	/**
	 * @param dtTalk
	 *            the dtTalk to set
	 */
	public void setDtTalk( Date dtTalk )
	{
		this.dtTalk = dtTalk;
	}
	
	@Column( name = "N_GETID" , precision = 22 , scale = 0 )
	public Integer getNGetId()
	{
		return NGetId;
	}
	
	public void setNGetId( Integer nGetId )
	{
		NGetId = nGetId;
	}
	
	@Column( name = "N_PAYID" , precision = 22 , scale = 0 )
	public Integer getNPayId()
	{
		return NPayId;
	}
	
	public void setNPayId( Integer nPayId )
	{
		NPayId = nPayId;
	}
	
	@Column( name = "N_RATE" , precision = 22 , scale = 0 )
	public Double getNRate()
	{
		return NRate;
	}
	
	public void setNRate( Double nRate )
	{
		NRate = nRate;
	}
	
	@Column( name = "N_APPROVAL_RESULT" , precision = 22 , scale = 0 )
	public Integer getNApprovalResult()
	{
		return NApprovalResult;
	}
	
	public void setNApprovalResult( Integer nApprovalResult )
	{
		NApprovalResult = nApprovalResult;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd " )
	@Column( name = "DT_APPROVAL" , length = 7 )
	public Date getDtApproval()
	{
		return dtApproval;
	}
	
	public void setDtApproval( Date dtApproval )
	{
		this.dtApproval = dtApproval;
	}
	
	@Column( name = "VC_APPROVAL_NAME" , length = 50 )
	public String getVcApprovalName()
	{
		return vcApprovalName;
	}
	
	public void setVcApprovalName( String vcApprovalName )
	{
		this.vcApprovalName = vcApprovalName;
	}
	
	@Column( name = "VC_APPROVAL_OPTION" , length = 100 )
	public String getVcApprovalOption()
	{
		return vcApprovalOption;
	}
	
	public void setVcApprovalOption( String vcApprovalOption )
	{
		this.vcApprovalOption = vcApprovalOption;
	}
	
	@Column( name = "N_SCORE" , precision = 22 , scale = 0 )
	public Integer getNScore()
	{
		return NScore;
	}
	
	public void setNScore( Integer nScore )
	{
		NScore = nScore;
	}
	
}