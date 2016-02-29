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

import com.wordnik.swagger.annotations.ApiParam;

/**
 * TAppVersion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_APP_VERSION" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TAppVersion implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 3454518988118811432L;
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "是否有效" )
	private Integer nEnable;
	@ApiParam( "添加时间(yyyy-MM-dd HH:mm:ss)" )
	private Date dtAdd;
	@ApiParam( "版本号" )
	private Integer nVersionCode;
	@ApiParam( "版本号名称" )
	private String vcVersionName;
	@ApiParam( "app下载地址" )
	private String vcAppUrl;
	@ApiParam( "更新类型（0为选择更新，1为强制更新）" )
	private Integer nUpdateType;
	@ApiParam( "添加人id" )
	private Integer iUser;
	@ApiParam( "添加人姓名" )
	private String vcUserName;
	@ApiParam( "版本说明" )
	private String vcNote;
	
	// Constructors
	
	/** default constructor */
	public TAppVersion()
	{}
	
	/** minimal constructor */
	public TAppVersion( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TAppVersion( Integer id , Integer nEnable , Date dtAdd , Integer nVersionCode ,
	        String vcVersionName , String vcAppUrl , Integer nUpdateType , Integer iUser ,
	        String vcUserName )
	{
		this.id = id;
		this.nEnable = nEnable;
		this.dtAdd = dtAdd;
		this.nVersionCode = nVersionCode;
		this.vcVersionName = vcVersionName;
		this.vcAppUrl = vcAppUrl;
		this.nUpdateType = nUpdateType;
		this.iUser = iUser;
		this.vcUserName = vcUserName;
	}
	
	// Property accessors
	@SequenceGenerator( name = "APPVERSIONID" , sequenceName = "S_APP_VERSION" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "APPVERSIONID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "VC_VERSION_NAME" , length = 20 )
	public String getVcVersionName()
	{
		return vcVersionName;
	}
	
	public void setVcVersionName( String vcVersionName )
	{
		this.vcVersionName = vcVersionName;
	}
	
	@Column( name = "VC_APP_URL" , length = 100 )
	public String getVcAppUrl()
	{
		return vcAppUrl;
	}
	
	public void setVcAppUrl( String vcAppUrl )
	{
		this.vcAppUrl = vcAppUrl;
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
	
	@Column( name = "VC_USER_NAME" , length = 40 )
	public String getVcUserName()
	{
		return vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	@Column( name = "VC_NOTE" , length = 200 )
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	@Column( name = "N_VERSION_CODE" , precision = 22 , scale = 0 )
	public Integer getnVersionCode()
	{
		return nVersionCode;
	}
	
	public void setnVersionCode( Integer nVersionCode )
	{
		this.nVersionCode = nVersionCode;
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
	
	@Column( name = "N_UPDATE_TYPE" , precision = 22 , scale = 0 )
	public Integer getnUpdateType()
	{
		return nUpdateType;
	}
	
	public void setnUpdateType( Integer nUpdateType )
	{
		this.nUpdateType = nUpdateType;
	}
	
}