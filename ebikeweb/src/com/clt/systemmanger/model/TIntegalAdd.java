package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 积分充值类型表 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_INTEGAL_ADD" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TIntegalAdd implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 885311803130682551L;
	private Integer id;
	private Integer NEnable;
	private Date dtBegin;
	private Date dtEnd;
	
	private Double NCredit;
	private Integer NIntegal;
	private Integer IOpUserId;
	private Date dtOpUser;
	private String vcCause;
	private String vcCode;
	
	// Constructors
	
	/** default constructor */
	public TIntegalAdd()
	{}
	
	/** minimal constructor */
	public TIntegalAdd( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TIntegalAdd( Integer id , Integer NEnable , Timestamp dtBegin ,
	        Timestamp dtEnd , Double NCredit , Integer NIntegal )
	{
		this.id = id;
		this.NEnable = NEnable;
		
		this.NCredit = NCredit;
		this.NIntegal = NIntegal;
	}
	
	// Property accessors
	@SequenceGenerator( name = "INTEGALADD" , sequenceName = "S_INTEGAL_ADD" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "INTEGALADD" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_ENABLE" , precision = 0 )
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_BEGIN" , length = 7 )
	public Date getDtBegin()
	{
		return this.dtBegin;
	}
	
	public void setDtBegin( Date dtBegin )
	{
		this.dtBegin = dtBegin;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_END" , length = 7 )
	public Date getDtEnd()
	{
		return this.dtEnd;
	}
	
	public void setDtEnd( Date dtEnd )
	{
		this.dtEnd = dtEnd;
	}
	
	@Column( name = "N_CREDIT" , precision = 0 )
	public Double getNCredit()
	{
		return this.NCredit;
	}
	
	public void setNCredit( Double NCredit )
	{
		this.NCredit = NCredit;
	}
	
	@Column( name = "N_INTEGAL" , precision = 0 )
	public Integer getNIntegal()
	{
		return this.NIntegal;
	}
	
	public void setNIntegal( Integer NIntegal )
	{
		this.NIntegal = NIntegal;
	}
	
	@Column( name = "I_OPUSER" , precision = 0 )
	public Integer getIOpUserId()
	{
		return IOpUserId;
	}
	
	public void setIOpUserId( Integer iOpUserId )
	{
		IOpUserId = iOpUserId;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_OPDATE" , length = 7 )
	public Date getDtOpUser()
	{
		return dtOpUser;
	}
	
	public void setDtOpUser( Date dtOpUser )
	{
		this.dtOpUser = dtOpUser;
	}
	
	@Column( name = "VC_CAUSE" , length = 100 )
	public String getVcCause()
	{
		return vcCause;
	}
	
	public void setVcCause( String vcCause )
	{
		this.vcCause = vcCause;
	}
	
	@Column( name = "VC_CODE" , length = 200 )
	public String getVcCode()
	{
		return vcCode;
	}
	
	public void setVcCode( String vcCode )
	{
		this.vcCode = vcCode;
	}
	
}