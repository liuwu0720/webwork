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
 * TUserLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_USER_LOG" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TUserLog implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 2656074305464649471L;
	private Integer id;
	private Date dtAdd;
	private Integer IUser;
	private String vcUserName;
	private String vcDesc;
	private String vcIp;
	
	// Constructors
	
	/** default constructor */
	public TUserLog()
	{}
	
	/** minimal constructor */
	public TUserLog( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TUserLog( Integer id , Date dtAdd , Integer IUser , String vcUserName ,
	        String vcDesc , String vcIp )
	{
		this.id = id;
		this.dtAdd = dtAdd;
		this.IUser = IUser;
		this.vcUserName = vcUserName;
		this.vcDesc = vcDesc;
		this.vcIp = vcIp;
	}
	
	// Property accessors
	@SequenceGenerator( name = "USER_LOG" , sequenceName = "S_USER_LOG" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "USER_LOG" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
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
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
	}
	
	@Column( name = "VC_USER_NAME" , length = 50 )
	public String getVcUserName()
	{
		return this.vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	@Column( name = "VC_DESC" , length = 100 )
	public String getVcDesc()
	{
		return this.vcDesc;
	}
	
	public void setVcDesc( String vcDesc )
	{
		this.vcDesc = vcDesc;
	}
	
	@Column( name = "VC_IP" , length = 25 )
	public String getVcIp()
	{
		return this.vcIp;
	}
	
	public void setVcIp( String vcIp )
	{
		this.vcIp = vcIp;
	}
	
}