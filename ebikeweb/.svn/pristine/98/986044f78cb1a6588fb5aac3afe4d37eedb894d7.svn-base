package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 分供方车型管理 TSubCarStyle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_SUB_CAR_STYLE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TSubCarStyle implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7193920169185617096L;
	private Integer id;
	private String vcCarStyle;
	private Integer NEnable;
	private String vcSubno;
	
	// Constructors
	
	/** default constructor */
	public TSubCarStyle()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TSubCarStyleID" , sequenceName = "S_SUB_CAR_STYLE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSubCarStyleID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_CAR_STYLE" , length = 50 )
	public String getVcCarStyle()
	{
		return this.vcCarStyle;
	}
	
	public void setVcCarStyle( String vcCarStyle )
	{
		this.vcCarStyle = vcCarStyle;
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
	
}