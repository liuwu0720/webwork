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
 * TSharecircle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_SHARECIRCLE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TSharecircle implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 5062288715201693135L;
	private Integer id;
	private String vcContent;
	private String vcImgpath;
	private String vcSite;
	private Integer IType;
	private String vcSubno;
	private String vcLongitude;
	private String vcLatitude;
	private Date dtShare;
	private Integer IUserid;
	private String vcUsername;
	private String vcShareTag;
	private String vcHash;
	private String vcHeadImg;
	
	// Constructors
	
	/** default constructor */
	public TSharecircle()
	{}
	
	/** minimal constructor */
	public TSharecircle( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TSHARECIRCLEID" , sequenceName = "S_SHARECIRCLE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSHARECIRCLEID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_CONTENT" , length = 500 )
	public String getVcContent()
	{
		return this.vcContent;
	}
	
	public void setVcContent( String vcContent )
	{
		this.vcContent = vcContent;
	}
	
	@Column( name = "VC_IMGPATH" , length = 1000 )
	public String getVcImgpath()
	{
		return this.vcImgpath;
	}
	
	public void setVcImgpath( String vcImgpath )
	{
		this.vcImgpath = vcImgpath;
	}
	
	@Column( name = "VC_SITE" , length = 100 )
	public String getVcSite()
	{
		return this.vcSite;
	}
	
	public void setVcSite( String vcSite )
	{
		this.vcSite = vcSite;
	}
	
	@Column( name = "I_TYPE" , precision = 22 , scale = 0 )
	public Integer getIType()
	{
		return this.IType;
	}
	
	public void setIType( Integer IType )
	{
		this.IType = IType;
	}
	
	@Column( name = "VC_SUBNO" , length = 50 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "VC_LONGITUDE" , length = 50 )
	public String getVcLongitude()
	{
		return this.vcLongitude;
	}
	
	public void setVcLongitude( String vcLongitude )
	{
		this.vcLongitude = vcLongitude;
	}
	
	@Column( name = "VC_LATITUDE" , length = 50 )
	public String getVcLatitude()
	{
		return this.vcLatitude;
	}
	
	public void setVcLatitude( String vcLatitude )
	{
		this.vcLatitude = vcLatitude;
	}
	
	@Column( name = "DT_SHARE" , length = 7 )
	public Date getDtShare()
	{
		return this.dtShare;
	}
	
	public void setDtShare( Date dtShare )
	{
		this.dtShare = dtShare;
	}
	
	@Column( name = "I_USERID" , precision = 22 , scale = 0 )
	public Integer getIUserid()
	{
		return this.IUserid;
	}
	
	public void setIUserid( Integer IUserid )
	{
		this.IUserid = IUserid;
	}
	
	@Column( name = "VC_USERNAME" , length = 50 )
	public String getVcUsername()
	{
		return this.vcUsername;
	}
	
	public void setVcUsername( String vcUsername )
	{
		this.vcUsername = vcUsername;
	}
	
	@Column( name = "VC_SHARE_TAG" , length = 50 )
	public String getVcShareTag()
	{
		return vcShareTag;
	}
	
	public void setVcShareTag( String vcShareTag )
	{
		this.vcShareTag = vcShareTag;
	}
	
	@Column( name = "VC_HASH" , length = 50 )
	public String getVcHash()
	{
		return vcHash;
	}
	
	public void setVcHash( String vcHash )
	{
		this.vcHash = vcHash;
	}
	
	@Column( name = "VC_HEADIMG" , length = 100 )
	public String getVcHeadImg()
	{
		return vcHeadImg;
	}
	
	public void setVcHeadImg( String vcHeadImg )
	{
		this.vcHeadImg = vcHeadImg;
	}
	
}