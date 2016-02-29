package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 分供方申请权限表 按角色申请 TRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_APPLY_RESOURCE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TApplyResource implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 7698657067625537210L;
	private Integer id;
	private Integer IUser;
	private Integer IRole;
	
	private Date dtBatch; // 批次
	private Integer NApplyStatus;
	
	// Constructors
	
	// Property accessors
	@SequenceGenerator( name = "APPLYRESOURCEID" , sequenceName = "S_APPLY_RESOURCE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "APPLYRESOURCEID" )
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
		return IUser;
	}
	
	public void setIUser( Integer iUser )
	{
		IUser = iUser;
	}
	
	@Column( name = "I_ROLE" , precision = 22 , scale = 0 )
	public Integer getIRole()
	{
		return IRole;
	}
	
	public void setIRole( Integer iRole )
	{
		IRole = iRole;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_BATCH" , length = 7 )
	public Date getDtBatch()
	{
		return dtBatch;
	}
	
	public void setDtBatch( Date dtBatch )
	{
		this.dtBatch = dtBatch;
	}
	
	@Column( name = "N_APPLY_STATUS" , precision = 22 , scale = 0 )
	public Integer getNApplyStatus()
	{
		return NApplyStatus;
	}
	
	public void setNApplyStatus( Integer nApplyStatus )
	{
		NApplyStatus = nApplyStatus;
	}
	
}