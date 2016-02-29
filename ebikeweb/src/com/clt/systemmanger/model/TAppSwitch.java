package com.clt.systemmanger.model;

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
 * TAppSwitch entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_APP_SWITCH" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TAppSwitch implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 6460261130809963777L;
	private Integer id;
	private Integer nEnable;
	private String vcAdduser;
	private Date dtAdd;
	private Integer iResource;
	private String vcSwitchName;
	private Integer nOnOff;
	private String vcNote;
	
	// Constructors
	
	/** default constructor */
	public TAppSwitch()
	{}
	
	/** minimal constructor */
	public TAppSwitch( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TAppSwitch( Integer id , Integer nEnable , String vcAdduser , Date dtAdd ,
	        Integer iResource , String vcSwitchName , Integer nOnOff , String vcNote )
	{
		this.id = id;
		this.nEnable = nEnable;
		this.vcAdduser = vcAdduser;
		this.dtAdd = dtAdd;
		this.iResource = iResource;
		this.vcSwitchName = vcSwitchName;
		this.nOnOff = nOnOff;
		this.vcNote = vcNote;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TAPPSWITCH" , sequenceName = "S_T_APP_SWITCH" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TAPPSWITCH" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
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
	
	@Column( name = "VC_ADDUSER" , length = 30 )
	public String getVcAdduser()
	{
		return this.vcAdduser;
	}
	
	public void setVcAdduser( String vcAdduser )
	{
		this.vcAdduser = vcAdduser;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "I_RESOURCE" , precision = 22 , scale = 0 )
	public Integer getiResource()
	{
		return this.iResource;
	}
	
	public void setiResource( Integer iResource )
	{
		this.iResource = iResource;
	}
	
	@Column( name = "VC_SWITCH_NAME" , length = 30 )
	public String getVcSwitchName()
	{
		return this.vcSwitchName;
	}
	
	public void setVcSwitchName( String vcSwitchName )
	{
		this.vcSwitchName = vcSwitchName;
	}
	
	@Column( name = "N_ON_OFF" , precision = 22 , scale = 0 )
	public Integer getnOnOff()
	{
		return this.nOnOff;
	}
	
	public void setnOnOff( Integer nOnOff )
	{
		this.nOnOff = nOnOff;
	}
	
	@Column( name = "VC_NOTE" , length = 250 )
	public String getVcNote()
	{
		return this.vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
}