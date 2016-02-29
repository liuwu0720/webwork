package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TLoanType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_LOAN_TYPE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TLoanType implements java.io.Serializable
{
	
	/**
     * 
     */
	
	// Fields
	private static final long serialVersionUID = 11511231231212L;
	private Integer id;
	private Integer NEnable;
	private Date dtAdd;
	private String vcAdduser;
	private Integer IType;
	private String vcTypename;
	
	// Constructors
	
	/** default constructor */
	public TLoanType()
	{}
	
	/** minimal constructor */
	public TLoanType( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TLoanType( Integer id , Integer IType , Integer NEnable , Date dtAdd ,
	        String vcAdduser , String vcTypename )
	{
		this.id = id;
		this.IType = IType;
		this.NEnable = NEnable;
		this.dtAdd = dtAdd;
		this.vcAdduser = vcAdduser;
		this.vcTypename = vcTypename;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TLoanTypeID" , sequenceName = "S_T_LOAN_TYPE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TLoanTypeID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_TYPE" , precision = 22 , scale = 0 )
	public Integer getIType()
	{
		return IType;
	}
	
	public void setIType( Integer iType )
	{
		IType = iType;
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
	
	@Column( name = "VC_ADDUSER" , length = 50 )
	public String getVcAdduser()
	{
		return this.vcAdduser;
	}
	
	public void setVcAdduser( String vcAdduser )
	{
		this.vcAdduser = vcAdduser;
	}
	
	@Column( name = "VC_TYPENAME" , length = 50 )
	public String getVcTypename()
	{
		return this.vcTypename;
	}
	
	public void setVcTypename( String vcTypename )
	{
		this.vcTypename = vcTypename;
	}
	
}