package com.clt.finance.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 贷款询问 TLoanAsk entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_LOAN_ASK" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TLoanAsk implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * ILoanHandle
	 */
	private static final long serialVersionUID = - 8438488589470719031L;
	private Integer id;
	private Integer ILoan;
	private Date dtAsk;
	private Integer NRepulse;
	private String vcNote;
	private Float NAccrual;
	private Date dtAccrual;
	private String vcFinanceno;
	private Integer IUser;
	private String vcUserName;
	private Integer NEnable;
	private Float NAccrualLast;
	private String vcLoanno;
	private String vcFinanceName;
	private Integer ILoanHandle;
	private Date dtApproval;
	private Double NFee;
	private String vcTel;
	private String vcCause;
	
	// Constructors
	
	/** default constructor */
	public TLoanAsk()
	{}
	
	/** minimal constructor */
	public TLoanAsk( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TLoanAskID" , sequenceName = "S_LOAN_ASK" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TLoanAskID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_LOAN" , precision = 22 , scale = 0 )
	public Integer getILoan()
	{
		return this.ILoan;
	}
	
	public void setILoan( Integer TLoan )
	{
		this.ILoan = TLoan;
	}
	
	@Column( name = "DT_ASK" , length = 7 )
	public Date getDtAsk()
	{
		return this.dtAsk;
	}
	
	public void setDtAsk( Date dtAsk )
	{
		this.dtAsk = dtAsk;
	}
	
	@Column( name = "N_REPULSE" , precision = 22 , scale = 0 )
	public Integer getNRepulse()
	{
		return this.NRepulse;
	}
	
	public void setNRepulse( Integer NRepulse )
	{
		this.NRepulse = NRepulse;
	}
	
	@Column( name = "N_ACCRUAL" , precision = 22 , scale = 0 )
	public Float getNAccrual()
	{
		return this.NAccrual;
	}
	
	@Column( name = "VC_NOTE" , length = 50 )
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	public void setNAccrual( Float NAccrual )
	{
		this.NAccrual = NAccrual;
	}
	
	@Column( name = "DT_ACCRUAL" , length = 7 )
	public Date getDtAccrual()
	{
		return this.dtAccrual;
	}
	
	public void setDtAccrual( Date dtAccrual )
	{
		this.dtAccrual = dtAccrual;
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
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
	}
	
	@Column( name = "VC_USER_NAME" , length = 30 )
	public String getVcUserName()
	{
		return this.vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
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
	
	@Column( name = "N_ACCRUAL_LAST" , precision = 22 , scale = 0 )
	public Float getNAccrualLast()
	{
		return this.NAccrualLast;
	}
	
	public void setNAccrualLast( Float NAccrualLast )
	{
		this.NAccrualLast = NAccrualLast;
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
	
	@Column( name = "VC_FINANCE_NAME" , length = 100 )
	public String getVcFinanceName()
	{
		return vcFinanceName;
	}
	
	public void setVcFinanceName( String vcFinanceName )
	{
		this.vcFinanceName = vcFinanceName;
	}
	
	@Column( name = "I_LOAN_HANDLE" , precision = 22 , scale = 0 )
	public Integer getILoanHandle()
	{
		return ILoanHandle;
	}
	
	public void setILoanHandle( Integer ILoanHandle )
	{
		this.ILoanHandle = ILoanHandle;
	}
	
	@Column( name = "DT_APPROVAL" , length = 7 )
	public Date getDtApproval()
	{
		return dtApproval;
	}
	
	public void setDtApproval( Date dtApproval )
	{
		this.dtApproval = dtApproval;
	}
	
	@Column( name = "N_FEE" , precision = 22 , scale = 0 )
	public Double getNFee()
	{
		return NFee;
	}
	
	public void setNFee( Double nFee )
	{
		NFee = nFee;
	}
	
	@Column( name = "VC_TEL" , length = 50 )
	public String getVcTel()
	{
		return vcTel;
	}
	
	public void setVcTel( String vcTel )
	{
		this.vcTel = vcTel;
	}
	
	@Column( name = "VC_CAUSE" , length = 400 )
	public String getVcCause()
	{
		return vcCause;
	}
	
	public void setVcCause( String vcCause )
	{
		this.vcCause = vcCause;
	}
	
}