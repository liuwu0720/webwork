package com.clt.sub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TCity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_CITY" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TCity implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 748605459906774744L;
	private Integer id;
	private String cityname;
	private String zipcode;
	private Integer provinceid;
	private Date dtcreated;
	private Date dtupdated;
	
	// Constructors
	
	/** default constructor */
	public TCity()
	{}
	
	/** minimal constructor */
	public TCity( Integer id )
	{
		this.id = id;
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
	
	@Column( name = "CITYNAME" , length = 50 )
	public String getCityname()
	{
		return this.cityname;
	}
	
	public void setCityname( String cityname )
	{
		this.cityname = cityname;
	}
	
	@Column( name = "ZIPCODE" , length = 50 )
	public String getZipcode()
	{
		return this.zipcode;
	}
	
	public void setZipcode( String zipcode )
	{
		this.zipcode = zipcode;
	}
	
	@Column( name = "PROVINCEID" , precision = 0 )
	public Integer getProvinceid()
	{
		return this.provinceid;
	}
	
	public void setProvinceid( Integer provinceid )
	{
		this.provinceid = provinceid;
	}
	
	@Column( name = "DATECREATED" , length = 7 )
	@Temporal( TemporalType.TIMESTAMP )
	public Date getDtcreated()
	{
		return dtcreated;
	}
	
	public void setDtcreated( Date dtcreated )
	{
		this.dtcreated = dtcreated;
	}
	
	@Column( name = "DATEUPDATED" , length = 7 )
	@Temporal( TemporalType.TIMESTAMP )
	public Date getDtupdated()
	{
		return dtupdated;
	}
	
	public void setDtupdated( Date dtupdated )
	{
		this.dtupdated = dtupdated;
	}
	
}