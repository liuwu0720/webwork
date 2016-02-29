package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * TDriverClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_DRIVER_CLASS" )
public class TDriverClass implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 9061932514385061821L;
	private Integer id;
	private Integer nEnable;
	private String vcAdduser;
	private Date dtAdd;
	private String vcTag;
	
	// Constructors
	
	/** default constructor */
	public TDriverClass()
	{}
	
	/** minimal constructor */
	public TDriverClass( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TDriverClass( Integer id , Integer nEnable , String vcAdduser ,
	        Date dtAdd , String vcTag )
	{
		this.id = id;
		this.nEnable = nEnable;
		this.vcAdduser = vcAdduser;
		this.dtAdd = dtAdd;
		this.vcTag = vcTag;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TDRIVERCLASS" , sequenceName = "S_T_DRIVER_CLASS" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDRIVERCLASS" )
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
	public Integer getnEnable()
	{
		return this.nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
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
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "VC_TAG" , length = 20 )
	public String getVcTag()
	{
		return this.vcTag;
	}
	
	public void setVcTag( String vcTag )
	{
		this.vcTag = vcTag;
	}
	
}