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
 * TPicType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_PIC_TYPE" )
public class TPicType implements java.io.Serializable
{
	
	// Fields

	/**
     * 
     */
	private static final long serialVersionUID = - 7835090981458478424L;
	private Integer id;
	private Integer nEnable;
	private String vcAdduser;
	private Date dtAdd;
	private String vcPicType;
	// Constructors
	
	/** default constructor */
	public TPicType()
	{}

	/** minimal constructor */
	public TPicType( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TPicType( Integer id , Integer NEnable , String vcAdduser ,
	        Date dtAdd , String vcPicType )
	{
		this.id = id;
		this.nEnable = nEnable;
		this.vcAdduser = vcAdduser;
		this.dtAdd = dtAdd;
		this.vcPicType = vcPicType;

	}
	
	// Property accessors
	@SequenceGenerator( name = "TPICTYPE" , sequenceName = "S_T_PIC_TYPE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TPICTYPE" )
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
		return nEnable;
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
	
	@Column( name = "VC_PIC_TYPE" , length = 50 )
	public String getVcPicType()
	{
		return this.vcPicType;
	}
	
	public void setVcPicType( String vcPicType )
	{
		this.vcPicType = vcPicType;
	}


}