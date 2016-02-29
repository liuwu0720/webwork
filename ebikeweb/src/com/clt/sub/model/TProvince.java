package com.clt.sub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TProvince entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_PROVINCE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TProvince implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5819207105782246008L;
	private Integer id;
	private String vcProvinceName;
	private Date dtcreate;
	private Date dtupdate;
	
	// Constructors
	
	/** default constructor */
	public TProvince()
	{}
	
	/** minimal constructor */
	public TProvince( Integer id )
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
	
	@Column( name = "PROVINCENAME" , length = 50 )
	public String getVcProvinceName()
	{
		return vcProvinceName;
	}
	
	public void setVcProvinceName( String vcProvinceName )
	{
		this.vcProvinceName = vcProvinceName;
	}
	
	@Column( name = "DATECREATED" , length = 7 )
	@Temporal( TemporalType.TIMESTAMP )
	public Date getDtcreate()
	{
		return dtcreate;
	}
	
	public void setDtcreate( Date dtcreate )
	{
		this.dtcreate = dtcreate;
	}
	
	@Column( name = "DATEUPDATED" , length = 7 )
	@Temporal( TemporalType.TIMESTAMP )
	public Date getDtupdate()
	{
		return dtupdate;
	}
	
	public void setDtupdate( Date dtupdate )
	{
		this.dtupdate = dtupdate;
	}
	
}