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
 * TTokenUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_TOKEN_USER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TTokenUser implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6630523635136672433L;
	/**
	 * 
	 */
	private Integer ID;
	private String vcToken;
	private Integer IUser;
	private Date dtLasttime;
	
	// Constructors
	
	/** default constructor */
	public TTokenUser()
	{}
	
	// Property accessors
	
	@Column( name = "VC_TOKEN" , unique = true , nullable = false , length = 32 )
	public String getVcToken()
	{
		return this.vcToken;
	}
	
	/**
	 * @return the iD
	 */
	@SequenceGenerator( name = "TOKENID" , sequenceName = "S_TOKEN_USER" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TOKENID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getID()
	{
		return ID;
	}
	
	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID( Integer iD )
	{
		ID = iD;
	}
	
	public void setVcToken( String vcToken )
	{
		this.vcToken = vcToken;
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
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_LASTTIME" , length = 7 )
	public Date getDtLasttime()
	{
		return this.dtLasttime;
	}
	
	public void setDtLasttime( Date dtLasttime )
	{
		this.dtLasttime = dtLasttime;
	}
	
}