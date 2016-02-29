package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TDept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_DEPT" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDept implements java.io.Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 7297097890766282930L;
	// Fields
	
	private Integer id;
	private String vcName;
	private Integer IDept;
	private Integer NEnable;
	private Integer NSort;
	
	// Constructors
	
	public TDept()
	{}
	
	public TDept( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "DEPTID" , sequenceName = "S_DEPT" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "DEPTID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_NAME" , length = 60 )
	public String getVcName()
	{
		return this.vcName;
	}
	
	public void setVcName( String vcName )
	{
		this.vcName = vcName;
	}
	
	@Column( name = "I_DEPT" , precision = 22 , scale = 0 )
	public Integer getIDept()
	{
		return this.IDept;
	}
	
	public void setIDept( Integer IDept )
	{
		this.IDept = IDept;
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
	
	@Column( name = "N_SORT" , precision = 22 , scale = 0 )
	public Integer getNSort()
	{
		return NSort;
	}
	
	/**
	 * @param nSort
	 *            the nSort to set
	 */
	public void setNSort( Integer nSort )
	{
		NSort = nSort;
	}
	
}