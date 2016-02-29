package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 中标表 TBidWin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_BID_WIN" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TBidWin implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * IKillInfo
	 */
	private static final long serialVersionUID = 1612426800341867168L;
	private Integer id;
	private Integer IKillInfo;
	private Integer IProduct;
	private String vcSubno;
	private String vcAllName;
	private Date dtBid;
	private Integer NEnable;
	private Integer IConfirm;
	private String vcConfirmName;
	private Date dtConfirm;
	private String vcConfirm;
	
	private Date dtAbandon;
	private Integer ICause;
	private Integer NAccept;
	
	// Constructors
	
	/** default constructor */
	public TBidWin()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TBidWinID" , sequenceName = "S_BID_WIN" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TBidWinID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_KILL_INFO" , precision = 22 , scale = 0 )
	public Integer getIKillInfo()
	{
		return this.IKillInfo;
	}
	
	public void setIKillInfo( Integer TKillInfo )
	{
		this.IKillInfo = TKillInfo;
	}
	
	@Column( name = "I_PRODUCT" , precision = 22 , scale = 0 )
	public Integer getIProduct()
	{
		return this.IProduct;
	}
	
	public void setIProduct( Integer TProduct )
	{
		this.IProduct = TProduct;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_BID" , length = 7 )
	public Date getDtBid()
	{
		return this.dtBid;
	}
	
	public void setDtBid( Date dtBid )
	{
		this.dtBid = dtBid;
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
	
	@Column( name = "I_CONFIRM" , precision = 22 , scale = 0 )
	public Integer getIConfirm()
	{
		return this.IConfirm;
	}
	
	public void setIConfirm( Integer IConfirm )
	{
		this.IConfirm = IConfirm;
	}
	
	@Column( name = "VC_CONFIRM_NAME" , length = 30 )
	public String getVcConfirmName()
	{
		return this.vcConfirmName;
	}
	
	public void setVcConfirmName( String vcConfirmName )
	{
		this.vcConfirmName = vcConfirmName;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_CONFIRM" , length = 7 )
	public Date getDtConfirm()
	{
		return this.dtConfirm;
	}
	
	public void setDtConfirm( Date dtConfirm )
	{
		this.dtConfirm = dtConfirm;
	}
	
	@Column( name = "VC_CONFIRM" , length = 400 )
	public String getVcConfirm()
	{
		return this.vcConfirm;
	}
	
	public void setVcConfirm( String vcConfirm )
	{
		this.vcConfirm = vcConfirm;
	}
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubno()
	{
		return vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "VC_ALL_NAME" , length = 100 )
	public String getVcAllName()
	{
		return vcAllName;
	}
	
	public void setVcAllName( String vcAllName )
	{
		this.vcAllName = vcAllName;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_ABANDON" )
	public Date getDtAbandon()
	{
		return dtAbandon;
	}
	
	public void setDtAbandon( Date dtAbandon )
	{
		this.dtAbandon = dtAbandon;
	}
	
	@Column( name = "I_CAUSE" )
	public Integer getICause()
	{
		return ICause;
	}
	
	public void setICause( Integer iCause )
	{
		ICause = iCause;
	}
	
	@Column( name = "N_ACCEPT" )
	public Integer getNAccept()
	{
		return NAccept;
	}
	
	public void setNAccept( Integer nAccept )
	{
		NAccept = nAccept;
	}
	
}