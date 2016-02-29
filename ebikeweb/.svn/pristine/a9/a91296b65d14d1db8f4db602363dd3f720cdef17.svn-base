package com.clt.sub.model;

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
 * TLimitCheck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_LIMIT_CHECK" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TLimitCheck implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1322347844538405042L;
	private Integer id;
	private Integer NEnable;
	private Integer ISpareCapacity;
	private Integer IUser;
	private Date dtAdd;
	
	// Constructors
	
	/** default constructor */
	public TLimitCheck()
	{}
	
	/** minimal constructor */
	public TLimitCheck( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TLimitCheck( Integer id , Integer NEnable , Integer ISpareCapacity ,
	        Integer IUser , Date dtAdd )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.ISpareCapacity = ISpareCapacity;
		this.IUser = IUser;
		this.dtAdd = dtAdd;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TLIMITCHECKID" , sequenceName = "S_LIMIT_CHECK" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TLIMITCHECKID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_ENABLE" , precision = 0 )
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
	}
	
	@Column( name = "I_SPARE_CAPACITY" , precision = 0 )
	public Integer getISpareCapacity()
	{
		return this.ISpareCapacity;
	}
	
	public void setISpareCapacity( Integer ISpareCapacity )
	{
		this.ISpareCapacity = ISpareCapacity;
	}
	
	@Column( name = "I_USER" , precision = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
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
	
}