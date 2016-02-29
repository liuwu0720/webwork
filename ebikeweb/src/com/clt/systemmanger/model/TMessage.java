package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 消息表 TMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_MESSAGE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TMessage implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7564493919792335148L;
	private Integer id;
	private String vcTitle;
	private String vcContent;
	private String vcLinkUrl;
	private Integer ISendUser;
	private String vcSendUserName;
	private Date dtSend;
	private Integer IAcceptUser;
	private String vcAcceptUserName;
	private Date dtLook;
	private Integer NIgnore;
	private Integer NEnable;
	private Integer NTop;
	private Integer iArchiveType;
	private Integer iArchive;
	private String vcMsgType;
	
	@Column( name = "I_ARCHIVE_TYPE" , precision = 22 , scale = 0 )
	public Integer getiArchiveType()
	{
		return iArchiveType;
	}
	
	public void setiArchiveType( Integer iArchiveType )
	{
		this.iArchiveType = iArchiveType;
	}
	
	@Column( name = "I_ARCHIVE" , precision = 22 , scale = 0 )
	public Integer getiArchive()
	{
		return iArchive;
	}
	
	public void setiArchive( Integer iArchive )
	{
		this.iArchive = iArchive;
	}
	
	// Constructors
	
	/** default constructor */
	public TMessage()
	{}
	
	/** full constructor */
	public TMessage( String vcTitle , String vcContent , String vcLinkUrl ,
	        Integer ISendUser , String vcSendUserName , Date dtSend ,
	        Integer IAcceptUser , String vcAcceptUserName , Date dtLook ,
	        Integer NIgnore , Integer NEnable , Integer NTop )
	{
		this.vcTitle = vcTitle;
		this.vcContent = vcContent;
		this.vcLinkUrl = vcLinkUrl;
		this.ISendUser = ISendUser;
		this.vcSendUserName = vcSendUserName;
		this.dtSend = dtSend;
		this.IAcceptUser = IAcceptUser;
		this.vcAcceptUserName = vcAcceptUserName;
		this.dtLook = dtLook;
		this.NIgnore = NIgnore;
		this.NEnable = NEnable;
		this.NTop = NTop;
	}
	
	// Property accessors
	@SequenceGenerator( name = "MESSAGEID" , sequenceName = "S_MESSAGE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "MESSAGEID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_TITLE" , length = 50 )
	public String getVcTitle()
	{
		return this.vcTitle;
	}
	
	public void setVcTitle( String vcTitle )
	{
		this.vcTitle = vcTitle;
	}
	
	@Column( name = "VC_CONTENT" , length = 400 )
	public String getVcContent()
	{
		return this.vcContent;
	}
	
	public void setVcContent( String vcContent )
	{
		this.vcContent = vcContent;
	}
	
	@Column( name = "VC_LINK_URL" , length = 100 )
	public String getVcLinkUrl()
	{
		return this.vcLinkUrl;
	}
	
	public void setVcLinkUrl( String vcLinkUrl )
	{
		this.vcLinkUrl = vcLinkUrl;
	}
	
	@Column( name = "I_SEND_USER" , precision = 22 , scale = 0 )
	public Integer getISendUser()
	{
		return this.ISendUser;
	}
	
	public void setISendUser( Integer ISendUser )
	{
		this.ISendUser = ISendUser;
	}
	
	@Column( name = "VC_SEND_USER_NAME" , length = 50 )
	public String getVcSendUserName()
	{
		return this.vcSendUserName;
	}
	
	public void setVcSendUserName( String vcSendUserName )
	{
		this.vcSendUserName = vcSendUserName;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_SEND" , length = 7 )
	public Date getDtSend()
	{
		return this.dtSend;
	}
	
	public void setDtSend( Date dtSend )
	{
		this.dtSend = dtSend;
	}
	
	@Column( name = "I_ACCEPT_USER" , precision = 22 , scale = 0 )
	public Integer getIAcceptUser()
	{
		return this.IAcceptUser;
	}
	
	public void setIAcceptUser( Integer IAcceptUser )
	{
		this.IAcceptUser = IAcceptUser;
	}
	
	@Column( name = "VC_ACCEPT_USER_NAME" , length = 50 )
	public String getVcAcceptUserName()
	{
		return this.vcAcceptUserName;
	}
	
	public void setVcAcceptUserName( String vcAcceptUserName )
	{
		this.vcAcceptUserName = vcAcceptUserName;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_LOOK" , length = 7 )
	public Date getDtLook()
	{
		return this.dtLook;
	}
	
	public void setDtLook( Date dtLook )
	{
		this.dtLook = dtLook;
	}
	
	@Column( name = "N_IGNORE" , precision = 22 , scale = 0 )
	public Integer getNIgnore()
	{
		return this.NIgnore;
	}
	
	public void setNIgnore( Integer NIgnore )
	{
		this.NIgnore = NIgnore;
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
	
	@Column( name = "N_TOP" , precision = 22 , scale = 0 )
	public Integer getNTop()
	{
		return this.NTop;
	}
	
	public void setNTop( Integer NTop )
	{
		this.NTop = NTop;
	}
	
	@Column( name = "VC_MSG_TYPE" , length = 100 )
	public String getVcMsgType()
	{
		return vcMsgType;
	}
	
	/**
	 * @param vcMsgType
	 *            the vcMsgType to set
	 */
	public void setVcMsgType( String vcMsgType )
	{
		this.vcMsgType = vcMsgType;
	}
	
}