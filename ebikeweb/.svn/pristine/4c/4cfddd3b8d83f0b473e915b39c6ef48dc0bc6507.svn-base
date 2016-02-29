package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TGpsrate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_GPSRATE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TGpsrate implements java.io.Serializable
{
	/**
     * 
     */
	private static final long serialVersionUID = 7398870568637276139L;
	private Integer id;
	private Integer userId;
	private String vcUserNo;
	private Integer nGps;
	private Integer nRate;
	private String vcSubno;
	
	public TGpsrate()
	{}
	
	public TGpsrate( Integer id )
	{
		this.id = id;
	}
	
	@SequenceGenerator( name = "TGPSRATE" , sequenceName = "S_T_GPSRATE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TGPSRATE" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_USERID" , precision = 22 , scale = 0 )
	public Integer getUserId()
	{
		return userId;
	}
	
	public void setUserId( Integer userId )
	{
		this.userId = userId;
	}
	
	@Column( name = "VC_USERNO" , length = 60 )
	public String getVcUserNo()
	{
		return vcUserNo;
	}
	
	public void setVcUserNo( String vcUserNo )
	{
		this.vcUserNo = vcUserNo;
	}
	
	@Column( name = "N_GPS" , precision = 22 , scale = 0 )
	public Integer getnGps()
	{
		return nGps;
	}
	
	public void setnGps( Integer nGps )
	{
		this.nGps = nGps;
	}
	
	@Column( name = "N_RATE" , precision = 22 , scale = 0 )
	public Integer getnRate()
	{
		return nRate;
	}
	
	public void setnRate( Integer nRate )
	{
		this.nRate = nRate;
	}
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubno()
	{
		return vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
}