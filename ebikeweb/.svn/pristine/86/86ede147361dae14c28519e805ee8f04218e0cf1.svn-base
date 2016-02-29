package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 抢单车型信息 TCarStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_CAR_STYLE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TCarStyle implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * IProduct
	 */
	private static final long serialVersionUID = - 9017085273087021410L;
	private Integer id;
	private Integer IProduct;
	private String vcCarStyle;
	private Integer NCarCount;
	private Integer NEnable;
	private Float NPrice;
	private String vcNote;
	private Integer ICarStyle;
	
	// Constructors
	
	/** default constructor */
	public TCarStyle()
	{}
	
	/** minimal constructor */
	public TCarStyle( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TCarStyleID" , sequenceName = "S_CAR_STYLE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TCarStyleID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_PRODUCT" , nullable = false , precision = 22 , scale = 0 )
	public Integer getIProduct()
	{
		return this.IProduct;
	}
	
	public void setIProduct( Integer TProduct )
	{
		this.IProduct = TProduct;
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
	
	@Column( name = "N_CAR_COUNT" , precision = 22 , scale = 0 )
	public Integer getNCarCount()
	{
		return this.NCarCount;
	}
	
	public void setNCarCount( Integer NCarCount )
	{
		this.NCarCount = NCarCount;
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
	
	@Column( name = "N_PRICE" , precision = 22 , scale = 0 )
	public Float getNPrice()
	{
		return this.NPrice;
	}
	
	public void setNPrice( Float NPrice )
	{
		this.NPrice = NPrice;
	}
	
	@Column( name = "VC_NOTE" , length = 400 )
	public String getVcNote()
	{
		return this.vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
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
	
}