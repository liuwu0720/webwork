package com.clt.systemmanger.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TPicNew entity. 图片新闻
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_PIC_NEW" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TPicNew implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6261227365212085501L;
	private Integer id;
	private Integer NEnable;
	private String vcDesc;
	private String vcPicUrl;
	private String vcLinkUrl;
	private Integer NDisaplay;
	private Integer IUser;
	private Date dtAdd;
	private Integer NType;
	
	// Constructors
	
	/** default constructor */
	public TPicNew()
	{}
	
	/** minimal constructor */
	public TPicNew( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TPicNew( Integer id , Integer NEnable , String vcDesc , String vcPicUrl ,
	        String vcLinkUrl , Integer NDisaplay , Integer IUser , Date dtAdd )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.vcDesc = vcDesc;
		this.vcPicUrl = vcPicUrl;
		this.vcLinkUrl = vcLinkUrl;
		this.NDisaplay = NDisaplay;
		this.IUser = IUser;
		this.dtAdd = dtAdd;
	}
	
	// Property accessors
	@Id
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
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
	
	@Column( name = "VC_DESC" , length = 200 )
	public String getVcDesc()
	{
		return this.vcDesc;
	}
	
	public void setVcDesc( String vcDesc )
	{
		this.vcDesc = vcDesc;
	}
	
	@Column( name = "VC_PIC_URL" , length = 200 )
	public String getVcPicUrl()
	{
		return this.vcPicUrl;
	}
	
	public void setVcPicUrl( String vcPicUrl )
	{
		this.vcPicUrl = vcPicUrl;
	}
	
	@Column( name = "VC_LINK_URL" , length = 200 )
	public String getVcLinkUrl()
	{
		return this.vcLinkUrl;
	}
	
	public void setVcLinkUrl( String vcLinkUrl )
	{
		this.vcLinkUrl = vcLinkUrl;
	}
	
	@Column( name = "N_DISAPLAY" , precision = 0 )
	public Integer getNDisaplay()
	{
		return this.NDisaplay;
	}
	
	public void setNDisaplay( Integer NDisaplay )
	{
		this.NDisaplay = NDisaplay;
	}
	
	@Column( name = "I_USER" , precision = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "N_TYPE" , precision = 0 )
	public Integer getNType()
	{
		return NType;
	}
	
	/**
	 * @param nType
	 *            the nType to set
	 */
	public void setNType( Integer nType )
	{
		NType = nType;
	}
	
}