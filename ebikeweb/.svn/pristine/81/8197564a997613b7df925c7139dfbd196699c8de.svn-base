package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 资源所属表 TResourceArchive entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_RESOURCE_ARCHIVE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TResourceArchive implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3444068927319543239L;
	private Integer id;
	private Integer IArchiveType;
	private Integer IResource;
	private Integer NEnable;
	
	// Constructors
	
	/** default constructor */
	public TResourceArchive()
	{}
	
	/** minimal constructor */
	public TResourceArchive( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "RESOURCE_ARCHIVE" , sequenceName = "S_RESOURCE_ARCHIVE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "RESOURCE_ARCHIVE" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_ARCHIVE_TYPE" , precision = 22 , scale = 0 )
	public Integer getIArchiveType()
	{
		return this.IArchiveType;
	}
	
	public void setIArchiveType( Integer iArchiveType )
	{
		this.IArchiveType = iArchiveType;
	}
	
	@Column( name = "I_RESOURCE" , precision = 22 , scale = 0 )
	public Integer getIResource()
	{
		return this.IResource;
	}
	
	public void setIResource( Integer iResource )
	{
		this.IResource = iResource;
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