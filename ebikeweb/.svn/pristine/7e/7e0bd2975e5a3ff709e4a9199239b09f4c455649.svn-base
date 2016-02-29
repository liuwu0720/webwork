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
 * TPicPath entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "t_pic_path" )
public class TPicPath implements java.io.Serializable
{

	/**
     * 
     */
	private static final long serialVersionUID = 2104125680569153778L;
	// Fields

	private Integer id;
	private Integer iPicType;
	private Integer nEnable;
	private String vcAdduser;
	private Date dtAdd;
	private String vcPath;
	
	// Constructors
	
	/** default constructor */
	public TPicPath()
	{}

	/** minimal constructor */
	public TPicPath( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TPICPATH" , sequenceName = "S_T_PIC_PATH" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TPICPATH" )
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
	
	@Column( name = "VC_PATH" , length = 100 )
	public String getVcPath()
	{
		return this.vcPath;
	}
	
	public void setVcPath( String vcPath )
	{
		this.vcPath = vcPath;
	}
	
	@Column( name = "I_PIC_TYPE" , precision = 22 , scale = 0 )
	public Integer getiPicType()
	{
		return iPicType;
	}
	
	public void setiPicType( Integer iPicType )
	{
		this.iPicType = iPicType;
	}

}