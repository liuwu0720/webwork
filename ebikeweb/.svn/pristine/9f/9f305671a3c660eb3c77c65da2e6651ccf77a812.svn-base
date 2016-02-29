package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 管理员档案 TClt entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_CLT" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TClt implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4214727740867077576L;
	private Integer id;
	private Integer IDept;
	private String vcName;
	private Integer NEnable;
	
	// Constructors
	
	/** default constructor */
	public TClt()
	{}
	
	/** minimal constructor */
	public TClt( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "CLTID" , sequenceName = "S_CLT" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "CLTID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_DEPT" , nullable = false , precision = 22 , scale = 0 )
	public Integer getTDept()
	{
		return this.IDept;
	}
	
	public void setTDept( Integer TDept )
	{
		this.IDept = TDept;
	}
	
	@Column( name = "VC_NAME" , length = 50 )
	public String getVcName()
	{
		return this.vcName;
	}
	
	public void setVcName( String vcName )
	{
		this.vcName = vcName;
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
	
}