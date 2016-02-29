package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TStatic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_STATIC" , uniqueConstraints = @UniqueConstraint( columnNames = "VC_PARAME" ) )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TStatic implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1490698798482261133L;
	private Integer id;
	private Integer NEnable;
	private String vcAdduser;
	private Date dtAdd;
	private String vcParame;
	private String vcValue;
	
	// Constructors
	
	/** default constructor */
	public TStatic()
	{}
	
	/** minimal constructor */
	public TStatic( Integer id , String vcParame , String vcValue )
	{
		this.id = id;
		this.vcParame = vcParame;
		this.vcValue = vcValue;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TStatic" , sequenceName = "S_Static" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TStatic" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
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
	
	@Column( name = "VC_ADDUSER" , length = 30 )
	public String getVcAdduser()
	{
		return this.vcAdduser;
	}
	
	public void setVcAdduser( String vcAdduser )
	{
		this.vcAdduser = vcAdduser;
	}
	
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "VC_PARAME" , unique = true , nullable = false , length = 50 )
	public String getVcParame()
	{
		return this.vcParame;
	}
	
	public void setVcParame( String vcParame )
	{
		this.vcParame = vcParame;
	}
	
	@Column( name = "VC_VALUE" , nullable = false , length = 200 )
	public String getVcValue()
	{
		return this.vcValue;
	}
	
	public void setVcValue( String vcValue )
	{
		this.vcValue = vcValue;
	}
	
}