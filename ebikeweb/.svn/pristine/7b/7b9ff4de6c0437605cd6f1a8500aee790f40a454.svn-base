package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 贷款处理信息表 TLoanHandle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_LOAN_HANDLE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TLoanHandle implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6213081956533062809L;
	private Integer id;
	private Integer ILoan;
	private Integer IUser;
	private String vcUserName;
	private Integer NResult;
	private Integer NTalk;
	private Date dtTalk;
	private Date dtConfirm;
	private Integer NEnable;
	private String vcFinanceno;
	private Integer IAskLoan;
	
	// Constructors
	
	/** default constructor */
	public TLoanHandle()
	{}
	
	/** minimal constructor */
	public TLoanHandle( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TLoanHandleID" , sequenceName = "S_LOAN_HANDLE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TLoanHandleID" )
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
	
	@Column( name = "N_RESULT" , precision = 22 , scale = 0 )
	public Integer getNResult()
	{
		return this.NResult;
	}
	
	public void setNResult( Integer NResult )
	{
		this.NResult = NResult;
	}
	
	@Column( name = "N_TALK" , precision = 22 , scale = 0 )
	public Integer getNTalk()
	{
		return this.NTalk;
	}
	
	public void setNTalk( Integer NTalk )
	{
		this.NTalk = NTalk;
	}
	
	@Column( name = "DT_TALK" , length = 7 )
	public Date getDtTalk()
	{
		return this.dtTalk;
	}
	
	public void setDtTalk( Date dtTalk )
	{
		this.dtTalk = dtTalk;
	}
	
	@Column( name = "DT_CONFIRM" , length = 7 )
	public Date getDtConfirm()
	{
		return this.dtConfirm;
	}
	
	public void setDtConfirm( Date dtConfirm )
	{
		this.dtConfirm = dtConfirm;
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
	
	@Column( name = "VC_FINANCENO" , length = 32 )
	public String getVcFinanceno()
	{
		return this.vcFinanceno;
	}
	
	public void setVcFinanceno( String vcFinanceno )
	{
		this.vcFinanceno = vcFinanceno;
	}
	
	@Column( name = "I_ASK_LOAN" , precision = 22 , scale = 0 )
	public Integer getIAskLoan()
	{
		return IAskLoan;
	}
	
	public void setIAskLoan( Integer iAskLoan )
	{
		IAskLoan = iAskLoan;
	}
	
}