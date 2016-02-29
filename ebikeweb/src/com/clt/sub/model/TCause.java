package com.clt.sub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 废标原因 TCause entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_CAUSE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TCause implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 1730791056390952641L;
	private Integer id;
	private Integer NEnable;
	private Date dtAdd;
	private Integer NType;
	private String vcCause;
	
	// Constructors
	
	/** default constructor */
	public TCause()
	{}
	
	/** minimal constructor */
	public TCause( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@Id
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
	
	@Column( name = "N_TYPE" , precision = 22 , scale = 0 )
	public Integer getNType()
	{
		return this.NType;
	}
	
	public void setNType( Integer NType )
	{
		this.NType = NType;
	}
	
	@Column( name = "VC_CAUSE" , length = 40 )
	public String getVcCause()
	{
		return this.vcCause;
	}
	
	public void setVcCause( String vcCause )
	{
		this.vcCause = vcCause;
	}
	
}