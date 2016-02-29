package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 档案类型表 TArchiveType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ARCHIVE_TYPE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TArchiveType implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 453041300631675292L;
	private Integer id;
	private String vcArchive;
	private Integer NEnable;
	private String vcTablename;
	
	// Constructors
	
	/** default constructor */
	public TArchiveType()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TArchiveTypeID" , sequenceName = "S_ARCHIVE_TYPE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TArchiveTypeID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_ARCHIVE" , length = 32 )
	public String getVcArchive()
	{
		return this.vcArchive;
	}
	
	public void setVcArchive( String vcArchive )
	{
		this.vcArchive = vcArchive;
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
	
	@Column( name = "VC_TABLENAME" , length = 100 )
	public String getVcTablename()
	{
		return vcTablename;
	}
	
	/**
	 * @param vcTablename
	 *            the vcTablename to set
	 */
	public void setVcTablename( String vcTablename )
	{
		this.vcTablename = vcTablename;
	}
	
}