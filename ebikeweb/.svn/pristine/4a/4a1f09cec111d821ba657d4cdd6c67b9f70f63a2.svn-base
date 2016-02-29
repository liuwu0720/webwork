package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 车辆类型表 TTruckDriver entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_TRUCK_TYPE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TTruckType implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 9079604727127556920L;
	private Integer id;
	private Integer NEnable;
	private String vcTypeName;
	private Date DtAdd;
	private Integer IAddUserID;
	
	// Constructors
	
	/** default constructor */
	public TTruckType()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TTRUCKTYPEID" , sequenceName = "S_TRUCK_DRIVER_LINK" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TTRUCKTYPEID" )
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
	
	@Column( name = "VC_TYPE_NAME" , length = 32 )
	public String getVcTypeName()
	{
		return vcTypeName;
	}
	
	public void setVcTypeName( String vcTypeName )
	{
		this.vcTypeName = vcTypeName;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd " )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return DtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		DtAdd = dtAdd;
	}
	
	@Column( name = "I_Add_User" , precision = 22 , scale = 0 )
	public Integer getIAddUserID()
	{
		return IAddUserID;
	}
	
	public void setIAddUserID( Integer iAddUserID )
	{
		IAddUserID = iAddUserID;
	}
	
}