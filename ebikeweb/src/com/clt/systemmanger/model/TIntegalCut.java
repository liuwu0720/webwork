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
 * 积分扣除类型表 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_INTEGAL_CUT" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TIntegalCut implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3571756868570141609L;
	private Integer id;
	private Integer NEnable;
	private Date dtBegin;
	private Date dtEnd;
	private String vcCause;
	private Integer NIntegal;
	private String vcCode;
	private Integer IOpUserId;
	private Date dtOpUser;
	
	// Constructors
	
	/** default constructor */
	public TIntegalCut()
	{}
	
	/** minimal constructor */
	public TIntegalCut( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TIntegalCut( Integer id , Integer NEnable , Timestamp dtBegin ,
	        Timestamp dtEnd , String vcCause , Integer NIntegal )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.dtBegin = dtBegin;
		this.dtEnd = dtEnd;
		this.vcCause = vcCause;
		this.NIntegal = NIntegal;
	}
	
	// Property accessors
	@SequenceGenerator( name = "INTEGALCUT" , sequenceName = "S_INTEGAL_CUT" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "INTEGALCUT" )
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
	
	@Column( name = "VC_CAUSE" , length = 100 )
	public String getVcCause()
	{
		return this.vcCause;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_BEGIN" , length = 7 )
	public Date getDtBegin()
	{
		return dtBegin;
	}
	
	public void setDtBegin( Date dtBegin )
	{
		this.dtBegin = dtBegin;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_END" , length = 7 )
	public Date getDtEnd()
	{
		return dtEnd;
	}
	
	public void setDtEnd( Date dtEnd )
	{
		this.dtEnd = dtEnd;
	}
	
	public void setVcCause( String vcCause )
	{
		this.vcCause = vcCause;
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
	
	@Column( name = "VC_CODE" , length = 200 )
	public String getVcCode()
	{
		return vcCode;
	}
	
	public void setVcCode( String vcCode )
	{
		this.vcCode = vcCode;
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
	
}