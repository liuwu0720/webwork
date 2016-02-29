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
 * TMsgRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_MSG_RECORD" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TMsgRecord implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 849741552571145550L;
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "是否有效" )
	private Integer NEnable;
	@ApiParam( "添加人姓名" )
	private String vcAdduser;
	@ApiParam( "添加时间(yyyy-MM-dd HH:mm:ss)" )
	private Date dtAdd;
	@ApiParam( "添加人ID" )
	private Integer IUser;
	@ApiParam( "消息类型（0为群发，1为单发消息）" )
	private Integer NMsgType;
	@ApiParam( "消息内容" )
	private String vcContent;
	@ApiParam( "接收人用户id" )
	private Integer IUserAccept;
	@ApiParam( "消息标题" )
	private String vcTitle;
	
	// Constructors
	
	/** default constructor */
	public TMsgRecord()
	{}
	
	/** minimal constructor */
	public TMsgRecord( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TMsgRecord( Integer id , Integer NEnable , String vcAdduser , Date dtAdd ,
	        Integer IUser , Integer NMsgType , String vcContent , Integer IUserAccept ,
	        String vcTitle )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.vcAdduser = vcAdduser;
		this.dtAdd = dtAdd;
		this.IUser = IUser;
		this.NMsgType = NMsgType;
		this.vcContent = vcContent;
		this.IUserAccept = IUserAccept;
		this.vcTitle = vcTitle;
	}
	
	// Property accessors
	@SequenceGenerator( name = "MSGRECORD" , sequenceName = "S_MSG_RECORD" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "MSGRECORD" )
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
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
	}
	
	@Column( name = "N_MSG_TYPE" , precision = 22 , scale = 0 )
	public Integer getNMsgType()
	{
		return this.NMsgType;
	}
	
	public void setNMsgType( Integer NMsgType )
	{
		this.NMsgType = NMsgType;
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
	
	@Column( name = "I_USER_ACCEPT" , precision = 22 , scale = 0 )
	public Integer getIUserAccept()
	{
		return this.IUserAccept;
	}
	
	public void setIUserAccept( Integer IUserAccept )
	{
		this.IUserAccept = IUserAccept;
	}
	
	@Column( name = "VC_TITLE" , length = 60 )
	public String getVcTitle()
	{
		return this.vcTitle;
	}
	
	public void setVcTitle( String vcTitle )
	{
		this.vcTitle = vcTitle;
	}
	
}