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
 * TUserStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_USER_STATUS" )
public class TUserStatus implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 2637935406636616296L;
	private Integer id;
	private Integer nEnable;
	private String vcAdduser;
	private Date dtAdd;
	private String vcStatus;
	private String vcPath;
	

	// Constructors
	
	/** default constructor */
	public TUserStatus()
	{}
	
	/** minimal constructor */
	public TUserStatus( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TUserStatus( Integer id , Integer nEnable , String vcAdduser ,
	        Date dtAdd , String vcStatus , String vcPath )
	{
		this.id = id;
		this.nEnable = nEnable;
		this.vcAdduser = vcAdduser;
		this.dtAdd = dtAdd;
		this.vcStatus = vcStatus;
		this.vcPath = vcPath;
	}
	
	@SequenceGenerator( name = "TUSERSTATUS" , sequenceName = "S_T_USER_STATUS" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TUSERSTATUS" )
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
	
	@Column( name = "VC_STATUS" , length = 20 )
	public String getVcStatus()
	{
		return this.vcStatus;
	}
	
	public void setVcStatus( String vcStatus )
	{
		this.vcStatus = vcStatus;
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
	
	
}