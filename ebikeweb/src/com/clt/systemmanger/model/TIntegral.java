package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 积分历史记录. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_INTEGRAL" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TIntegral implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 931423978884457469L;
	private Integer id;
	private Integer NEnable;
	private Integer NType;
	private Integer IUser;
	private Date dtChange;
	private Integer NChange;
	private String vcCause;
	
	// Constructors
	
	/** default constructor */
	public TIntegral()
	{}
	
	/** minimal constructor */
	public TIntegral( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TIntegral( Integer id , Integer NEnable , Integer NType , Integer IUser ,
	        Integer NChange , String vcCause )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.NType = NType;
		this.IUser = IUser;
		
		this.NChange = NChange;
		this.vcCause = vcCause;
	}
	
	// Property accessors
	@SequenceGenerator( name = "INTEGAL" , sequenceName = "S_INTEGRAL" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "INTEGAL" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_ENABLE" , precision = 0 )
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
	}
	
	@Column( name = "N_TYPE" , precision = 0 )
	public Integer getNType()
	{
		return this.NType;
	}
	
	public void setNType( Integer NType )
	{
		this.NType = NType;
	}
	
	@Column( name = "I_USER" , precision = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
	}
	
	@Column( name = "DT_CHANGE" , length = 7 )
	public Date getDtChange()
	{
		return this.dtChange;
	}
	
	public void setDtChange( Date dtChange )
	{
		this.dtChange = dtChange;
	}
	
	@Column( name = "N_CHANGE" , precision = 0 )
	public Integer getNChange()
	{
		return this.NChange;
	}
	
	public void setNChange( Integer NChange )
	{
		this.NChange = NChange;
	}
	
	@Column( name = "VC_CAUSE" , length = 100 )
	public String getVcCause()
	{
		return this.vcCause;
	}
	
	public void setVcCause( String vcCause )
	{
		this.vcCause = vcCause;
	}
	
}