package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 车辆司机映射表 TTruckDriver entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_TRUCK_DRIVER_LINK" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TTruckDriverLlink implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 9079604727127556920L;
	private Integer id;
	private Integer NEnable;
	private Integer IDriverID; // 司机表ID
	private Integer ITruckID; // 车辆表ID
	private Integer NPositionType;// 职位（1、主驾，2、副驾、机动）
	
	// Constructors
	
	/** default constructor */
	public TTruckDriverLlink()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TDRIVERID" , sequenceName = "S_DRIVER" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDRIVERID" )
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
	
	@Column( name = "I_DRIVER" , precision = 22 , scale = 0 )
	public Integer getIDriverID()
	{
		return IDriverID;
	}
	
	public void setIDriverID( Integer iDriverID )
	{
		IDriverID = iDriverID;
	}
	
	@Column( name = "I_TRUCK" , precision = 22 , scale = 0 )
	public Integer getITruckID()
	{
		return ITruckID;
	}
	
	public void setITruckID( Integer iTruckID )
	{
		ITruckID = iTruckID;
	}
	
	@Column( name = "N_POSITION_TYPE" , precision = 22 , scale = 0 )
	public Integer getNPositionType()
	{
		return NPositionType;
	}
	
	public void setNPositionType( Integer nPositionType )
	{
		NPositionType = nPositionType;
	}
	
}