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
 * TArriveCode entity.抵达确定码记录
 *  @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ARRIVE_CODE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TArriveCode implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 581292181538358355L;
	private Integer id;
	private Integer NEnable;
	private Date dtAdd;
	private Integer IUser;
	private String vcUserName;
	private String vcCustOrderno;
	private String vcOrderno;
	private String vcCode;
	private String vcShipno;
	private Integer ICheckUser;
	private String vcCheckUserName;
	private Date dtCheck;
	
	private String vcLongitude;
	private String vcLatitude;
	private String vcArrivePlace;
	
	// Constructors
	
	/** default constructor */
	public TArriveCode()
	{}
	
	/** full constructor */
	public TArriveCode( Integer NEnable , Date dtAdd , Integer IUser , String vcUserName ,
	        String vcCustOrderno , String vcOrderno , String vcCode , String vcShipno )
	{
		this.NEnable = NEnable;
		this.dtAdd = dtAdd;
		this.IUser = IUser;
		this.vcUserName = vcUserName;
		this.vcCustOrderno = vcCustOrderno;
		this.vcOrderno = vcOrderno;
		this.vcCode = vcCode;
		this.vcShipno = vcShipno;
	}
	
	// Property accessors
	@SequenceGenerator( name = "ARRIVECODEID" , sequenceName = "S_ARRIVE_CODE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "ARRIVECODEID" )
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
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
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
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
	}
	
	@Column( name = "VC_USER_NAME" , length = 40 )
	public String getVcUserName()
	{
		return this.vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	@Column( name = "VC_CUST_ORDERNO" , length = 32 )
	public String getVcCustOrderno()
	{
		return this.vcCustOrderno;
	}
	
	public void setVcCustOrderno( String vcCustOrderno )
	{
		this.vcCustOrderno = vcCustOrderno;
	}
	
	@Column( name = "VC_ORDERNO" , length = 32 )
	public String getVcOrderno()
	{
		return this.vcOrderno;
	}
	
	public void setVcOrderno( String vcOrderno )
	{
		this.vcOrderno = vcOrderno;
	}
	
	@Column( name = "VC_CODE" , length = 10 )
	public String getVcCode()
	{
		return this.vcCode;
	}
	
	public void setVcCode( String vcCode )
	{
		this.vcCode = vcCode;
	}
	
	@Column( name = "VC_SHIPNO" , length = 32 )
	public String getVcShipno()
	{
		return this.vcShipno;
	}
	
	public void setVcShipno( String vcShipno )
	{
		this.vcShipno = vcShipno;
	}
	
	@Column( name = "I_CHECK_USER" )
	public Integer getICheckUser()
	{
		return ICheckUser;
	}
	
	public void setICheckUser( Integer iCheckUser )
	{
		ICheckUser = iCheckUser;
	}
	
	@Column( name = "VC_CHECK_USER_NAME" )
	public String getVcCheckUserName()
	{
		return vcCheckUserName;
	}
	
	public void setVcCheckUserName( String vcCheckUserName )
	{
		this.vcCheckUserName = vcCheckUserName;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_CHECK" )
	public Date getDtCheck()
	{
		return dtCheck;
	}
	
	public void setDtCheck( Date dtCheck )
	{
		this.dtCheck = dtCheck;
	}
	
	@Column( name = "VC_LONGITUDE" )
	public String getVcLongitude()
	{
		return vcLongitude;
	}
	
	public void setVcLongitude( String vcLongitude )
	{
		this.vcLongitude = vcLongitude;
	}
	
	@Column( name = "VC_LATITUDE" )
	public String getVcLatitude()
	{
		return vcLatitude;
	}
	
	public void setVcLatitude( String vcLatitude )
	{
		this.vcLatitude = vcLatitude;
	}
	
	@Column( name = "VC_ARRIVE_PLACE" )
	public String getVcArrivePlace()
	{
		return vcArrivePlace;
	}
	
	public void setVcArrivePlace( String vcArrivePlace )
	{
		this.vcArrivePlace = vcArrivePlace;
	}
	
}