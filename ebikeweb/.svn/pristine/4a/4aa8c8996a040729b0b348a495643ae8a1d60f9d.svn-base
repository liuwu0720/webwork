package com.clt.sub.model;



import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TApprove entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_APPROVE" )
public class TApprove implements java.io.Serializable
{
	
	// Fields

	/**
     * 
     */
	private static final long serialVersionUID = 3586403873867231320L;
	private Integer id;
	private Integer NEnable;
	private String vcSubno;
	private String vcType;
	private Integer IUser;
	
	// Constructors
	
	/** default constructor */
	public TApprove()
	{}

	/** minimal constructor */
	public TApprove( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TApprove( Integer id , Integer NEnable , String vcSubno ,
	        String vcType , Integer IUser )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.vcSubno = vcSubno;
		this.vcType = vcType;
		this.IUser = IUser;
	}
	
	@SequenceGenerator( name = "TAPPROVE" , sequenceName = "S_APPROVE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TAPPROVE" )
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
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "VC_TYPE" , length = 32 )
	public String getVcType()
	{
		return this.vcType;
	}
	
	public void setVcType( String vcType )
	{
		this.vcType = vcType;
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
	

}