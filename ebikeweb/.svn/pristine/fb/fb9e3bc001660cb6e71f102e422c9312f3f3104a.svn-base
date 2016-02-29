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
 * 秒杀信息表 TKillInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_KILL_INFO" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TKillInfo implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * IProductId
	 */
	private static final long serialVersionUID = 6225949181933648376L;
	private Integer id;
	private Integer IProductId;
	private String vcSubno;
	private String vcAllName;
	private Date dtBid;
	private Integer NType;
	private Float NTotalPrice;
	private Integer NEnable;
	private Integer IDriver;
	private Double NDistance;
	private Integer iSubId;// 分供方表的ID
	// Constructors
	
	/** default constructor */
	public TKillInfo()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TKillInfoID" , sequenceName = "S_KILL_INFO" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TKillInfoID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_PRODUCT_ID" , precision = 22 , scale = 0 )
	public Integer getIProductId()
	{
		return this.IProductId;
	}
	
	public void setIProductId( Integer TProduct )
	{
		this.IProductId = TProduct;
	}
	
	@Column( name = "DT_BID" , length = 7 )
	public Date getDtBid()
	{
		return this.dtBid;
	}
	
	public void setDtBid( Date dtBid )
	{
		this.dtBid = dtBid;
	}
	
	@Column( name = "N_TYPE" , precision = 22 , scale = 0 )
	public Integer getNType()
	{
		return this.NType;
	}
	
	public void setNType( Integer NType )
	{
		this.NType = NType;
	}
	
	@Column( name = "N_TOTAL_PRICE" , precision = 22 , scale = 0 )
	public Float getNTotalPrice()
	{
		return this.NTotalPrice;
	}
	
	public void setNTotalPrice( Float NTotalPrice )
	{
		this.NTotalPrice = NTotalPrice;
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
	
	@Column( name = "I_DRIVER" , length = 32 )
	public Integer getIDriver()
	{
		return IDriver;
	}
	
	public void setIDriver( Integer iDriver )
	{
		IDriver = iDriver;
	}
	
	@Column( name = "N_DISTANCE" , length = 32 )
	public Double getNDistance()
	{
		return NDistance;
	}
	
	public void setNDistance( Double nDistance )
	{
		NDistance = nDistance;
	}
	
	@Column( name = "I_SUBID" , length = 32 )
	public Integer getiSubId()
	{
		return iSubId;
	}
	
	public void setiSubId( Integer iSubId )
	{
		this.iSubId = iSubId;
	}
	
}