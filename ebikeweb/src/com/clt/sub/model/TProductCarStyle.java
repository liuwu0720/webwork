package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 抢单车型信息 TProductCarStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_PRODUCT_CAR_STYLE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TProductCarStyle implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 3768629508911869695L;
	private Integer id;
	private String vcCarStyle;
	private Integer NCarCount;
	private Integer IProductId;
	private Integer NEnable;
	private Float NPrice;
	private Float NWeight;
	private String vcNote;
	private String vcDetailAddress;
	private String vcLong;
	private String vcLat;
	private String vcHash;
	private Integer ICarStyle;
	private String vcCustOrderno;
	
	// Constructors
	
	/** default constructor */
	public TProductCarStyle()
	{}
	
	/** full constructor */
	public TProductCarStyle( String vcCarStyle , Integer NCarCount , Integer IProductId ,
	        Integer NEnable )
	{
		this.vcCarStyle = vcCarStyle;
		this.NCarCount = NCarCount;
		this.IProductId = IProductId;
		this.NEnable = NEnable;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TProductCarStyleID" , sequenceName = "S_PRODUCT_CAR_STYLE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TProductCarStyleID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_CAR_STYLE" , length = 20 )
	public String getVcCarStyle()
	{
		return this.vcCarStyle;
	}
	
	public void setVcCarStyle( String vcCarStyle )
	{
		this.vcCarStyle = vcCarStyle;
	}
	
	@Column( name = "N_PRICE" )
	public Float getNPrice()
	{
		return NPrice;
	}
	
	public void setNPrice( Float nPrice )
	{
		NPrice = nPrice;
	}
	
	@Column( name = "N_CAR_COUNT" , precision = 22 , scale = 0 )
	public Integer getNCarCount()
	{
		return this.NCarCount;
	}
	
	public void setNCarCount( Integer NCarCount )
	{
		this.NCarCount = NCarCount;
	}
	
	@Column( name = "I_PRODUCT_ID" , precision = 22 , scale = 0 , nullable = false )
	public Integer getIProductId()
	{
		return this.IProductId;
	}
	
	public void setIProductId( Integer IProductId )
	{
		this.IProductId = IProductId;
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
	
	@Column( name = "N_WEIGHT" , precision = 22 , scale = 0 , nullable = false )
	public Float getNWeight()
	{
		return NWeight;
	}
	
	public void setNWeight( Float nWeight )
	{
		NWeight = nWeight;
	}
	
	@Column( name = "VC_NOTE" , length = 20 )
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	@Column( name = "VC_DETAIL_ADDRESS" , length = 20 )
	public String getVcDetailAddress()
	{
		return vcDetailAddress;
	}
	
	public void setVcDetailAddress( String vcDetailAddress )
	{
		this.vcDetailAddress = vcDetailAddress;
	}
	
	@Column( name = "VC_LONG" )
	public String getVcLong()
	{
		return vcLong;
	}
	
	public void setVcLong( String vcLong )
	{
		this.vcLong = vcLong;
	}
	
	@Column( name = "VC_LAT" )
	public String getVcLat()
	{
		return vcLat;
	}
	
	public void setVcLat( String vcLat )
	{
		this.vcLat = vcLat;
	}
	
	@Column( name = "VC_HASH" )
	public String getVcHash()
	{
		return vcHash;
	}
	
	public void setVcHash( String vcHash )
	{
		this.vcHash = vcHash;
	}
	
	@Column( name = "I_CAR_STYLE" )
	public Integer getICarStyle()
	{
		return ICarStyle;
	}
	
	public void setICarStyle( Integer iCarStyle )
	{
		ICarStyle = iCarStyle;
	}
	
	@Column( name = "VC_CUST_ORDERNO" )
	public String getVcCustOrderno()
	{
		return vcCustOrderno;
	}
	
	public void setVcCustOrderno( String vcCustOrderno )
	{
		this.vcCustOrderno = vcCustOrderno;
	}
	
}