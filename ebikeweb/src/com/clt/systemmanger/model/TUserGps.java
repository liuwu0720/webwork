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
 * TUserGps entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_USER_GPS" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TUserGps implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5791336844075391519L;
	private Integer id;// 主键
	private Integer NEnable;// 是否有效
	private Date dtAdd;// 添加时间
	private Integer IUser;// 用户id
	private String vcUser;// 用户名
	private String vcLong;// 经度
	private String vcLat;// 纬度
	private String vcHash;// 经纬度hash值
	
	// Constructors
	
	/** default constructor */
	public TUserGps()
	{}
	
	/** minimal constructor */
	public TUserGps( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TUserGps( Integer id , Integer NEnable , Date dtAdd , Integer IUser ,
	        String vcUser , String vcLong , String vcLat , String vcHash )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.dtAdd = dtAdd;
		this.IUser = IUser;
		this.vcUser = vcUser;
		this.vcLong = vcLong;
		this.vcLat = vcLat;
		this.vcHash = vcHash;
	}
	
	// Property accessors
	@Id
	@SequenceGenerator( name = "GPSID" , sequenceName = "S_USER_GPS" , allocationSize = 1 )
	@GeneratedValue( strategy = SEQUENCE , generator = "GPSID" )
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
	
	@Column( name = "VC_USER" , length = 50 )
	public String getVcUser()
	{
		return this.vcUser;
	}
	
	public void setVcUser( String vcUser )
	{
		this.vcUser = vcUser;
	}
	
	@Column( name = "VC_LONG" , length = 50 )
	public String getVcLong()
	{
		return this.vcLong;
	}
	
	public void setVcLong( String vcLong )
	{
		this.vcLong = vcLong;
	}
	
	@Column( name = "VC_LAT" , length = 50 )
	public String getVcLat()
	{
		return this.vcLat;
	}
	
	public void setVcLat( String vcLat )
	{
		this.vcLat = vcLat;
	}
	
	@Column( name = "VC_HASH" , length = 50 )
	public String getVcHash()
	{
		return this.vcHash;
	}
	
	public void setVcHash( String vcHash )
	{
		this.vcHash = vcHash;
	}
	
}