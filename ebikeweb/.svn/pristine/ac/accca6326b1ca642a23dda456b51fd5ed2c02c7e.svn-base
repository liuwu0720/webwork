package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TShipVin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_SHIP_VIN" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TShipVin implements java.io.Serializable
{
	
	// Fields
	
	private Integer id;
	private Integer nEnable;
	private String vcAdduser;
	private Date dtAdd;
	private Integer iUser;
	private String vcVin;
	private String vcShipno;
	
	// Constructors
	
	/** default constructor */
	public TShipVin()
	{}
	
	/** minimal constructor */
	public TShipVin( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TShipVin( Integer id , Integer NEnable , String vcAdduser , Date dtAdd ,
	        Integer IUser , String vcVin , String vcShipno )
	{
		this.id = id;
		this.nEnable = nEnable;
		this.vcAdduser = vcAdduser;
		this.dtAdd = dtAdd;
		this.iUser = iUser;
		this.vcVin = vcVin;
		this.vcShipno = vcShipno;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TSHIPVIN" , sequenceName = "S_SHIP_VIN" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSHIPVIN" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_ADDUSER" , length = 30 )
	public String getVcAdduser()
	{
		return this.vcAdduser;
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
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getiUser()
	{
		return iUser;
	}
	
	public void setiUser( Integer iUser )
	{
		this.iUser = iUser;
	}
	
	public void setVcAdduser( String vcAdduser )
	{
		this.vcAdduser = vcAdduser;
	}
	
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "VC_VIN" , length = 50 )
	public String getVcVin()
	{
		return this.vcVin;
	}
	
	public void setVcVin( String vcVin )
	{
		this.vcVin = vcVin;
	}
	
	@Column( name = "VC_SHIPNO" , length = 30 )
	public String getVcShipno()
	{
		return this.vcShipno;
	}
	
	public void setVcShipno( String vcShipno )
	{
		this.vcShipno = vcShipno;
	}
	
}