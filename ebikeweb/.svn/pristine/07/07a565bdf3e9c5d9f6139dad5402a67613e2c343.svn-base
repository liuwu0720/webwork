package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TUserCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_USER_CODE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TUserCode implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4188170266320824677L;
	private Integer id;
	private Integer NEnable;
	private Date dtAdd;
	private Integer IUser;
	private String vcPhone;
	private String vcCode;
	
	// Constructors
	
	/** default constructor */
	public TUserCode()
	{}
	
	/** minimal constructor */
	public TUserCode( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TUserCode( Integer id , Integer NEnable , Date dtAdd , Integer IUser ,
	        String vcPhone , String vcCode )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.dtAdd = dtAdd;
		this.IUser = IUser;
		this.vcPhone = vcPhone;
		this.vcCode = vcCode;
	}
	
	// Property accessors
	@SequenceGenerator( name = "USERCODE" , sequenceName = "S_USER_CODE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "USERCODE" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
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
	
	@Column( name = "VC_PHONE" , length = 50 )
	public String getVcPhone()
	{
		return this.vcPhone;
	}
	
	public void setVcPhone( String vcPhone )
	{
		this.vcPhone = vcPhone;
	}
	
	@Column( name = "VC_CODE" , length = 10 )
	public String getVcCode()
	{
		return this.vcCode;
	}
	
	public void setVcCode( String vcCode )
	{
		this.vcCode = vcCode;
	}
	
}