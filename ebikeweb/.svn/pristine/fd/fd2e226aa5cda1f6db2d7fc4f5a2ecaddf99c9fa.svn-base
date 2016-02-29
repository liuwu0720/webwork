package com.clt.finance.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TCompanyType entity. @author MyEclipse Persistence Tools 金融公司类型表
 */
@Entity
@Table( name = "T_COMPANY_TYPE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TCompanyType implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9203705303262020540L;
	private Integer id;
	private String vcType;
	private Integer NEnable;
	
	// Constructors
	
	/** default constructor */
	public TCompanyType()
	{}
	
	/** minimal constructor */
	public TCompanyType( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TCompanyTypeID" , sequenceName = "S_COMPANY_TYPE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TCompanyTypeID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_TYPE" , length = 50 )
	public String getVcType()
	{
		return this.vcType;
	}
	
	public void setVcType( String vcType )
	{
		this.vcType = vcType;
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