package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 用户角色关联表 IUserRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_USER_ROLE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TUserRole implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3109148825966970473L;
	private Integer id;
	private Integer IUser;
	private Integer IRole;
	private Integer IEnable;
	
	// Constructors
	
	/** default constructor */
	public TUserRole()
	{}
	
	/** full constructor */
	public TUserRole( Integer IUser , Integer TRole , Integer IEnable )
	{
		this.IUser = IUser;
		this.IRole = TRole;
		this.IEnable = IEnable;
	}
	
	// Property accessors
	@SequenceGenerator( name = "USER_ROLE" , sequenceName = "S_USER_ROLE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "USER_ROLE" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
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
	
	@Column( name = "I_ROLE" , precision = 22 , scale = 0 )
	public Integer getIRole()
	{
		return this.IRole;
	}
	
	public void setIRole( Integer TRole )
	{
		this.IRole = TRole;
	}
	
	@Column( name = "I_ENABLE" , precision = 22 , scale = 0 )
	public Integer getIEnable()
	{
		return this.IEnable;
	}
	
	public void setIEnable( Integer IEnable )
	{
		this.IEnable = IEnable;
	}
	
}