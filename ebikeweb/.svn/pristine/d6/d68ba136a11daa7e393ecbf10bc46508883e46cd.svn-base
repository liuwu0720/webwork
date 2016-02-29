package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TRoleCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ROLE_COMPANY" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TRoleCompany implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 5934827155192737047L;
	private Integer id;
	private Integer roleId;
	private String vcCompanyno;
	private String vcCompanytype;
	private Integer IArchiveType;
	private Integer nEnable;
	private Integer iUserId;
	
	// Constructors
	
	/** default constructor */
	public TRoleCompany()
	{}
	
	/** minimal constructor */
	public TRoleCompany( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TROLECOMPANY" , sequenceName = "s_t_role_company" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TROLECOMPANY" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_ROLE_ID" , precision = 22 , scale = 0 )
	public Integer getRoleId()
	{
		return roleId;
	}
	
	public void setRoleId( Integer roleId )
	{
		this.roleId = roleId;
	}
	
	@Column( name = "VC_COMPANYNO" , length = 50 )
	public String getVcCompanyno()
	{
		return this.vcCompanyno;
	}
	
	public void setVcCompanyno( String vcCompanyno )
	{
		this.vcCompanyno = vcCompanyno;
	}
	
	@Column( name = "VC_COMPANYTYPE" , length = 50 )
	public String getVcCompanytype()
	{
		return this.vcCompanytype;
	}
	
	public void setVcCompanytype( String vcCompanytype )
	{
		this.vcCompanytype = vcCompanytype;
	}
	
	@Column( name = "I_ARCHIVE_TYPE" , precision = 22 , scale = 0 )
	public Integer getIArchiveType()
	{
		return this.IArchiveType;
	}
	
	public void setIArchiveType( Integer IArchiveType )
	{
		this.IArchiveType = IArchiveType;
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
	
	@Column( name = "I_USER_ID" , precision = 22 , scale = 0 )
	public Integer getiUserId()
	{
		return iUserId;
	}
	
	public void setiUserId( Integer iUserId )
	{
		this.iUserId = iUserId;
	}
	
}