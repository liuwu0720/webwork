package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * TFeedback entity. @author MyEclipse Persistence Tools 意见反馈
 */
@Entity
@Table( name = "T_FEEDBACK" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TFeedback implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 744758871162135079L;
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "状态（0为有效，1为无效）" )
	private Integer NEnable;
	@ApiParam( "用户id" )
	private Integer IUser;
	@ApiParam( "添加时间" )
	private Date dtAdd;
	@ApiParam( "用户姓名" )
	private String vcUsername;
	@ApiParam( "反馈意见" )
	private String vcContent;
	
	// Constructors
	
	/** default constructor */
	public TFeedback()
	{}
	
	/** minimal constructor */
	public TFeedback( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TFeedback( Integer id , Integer NEnable , Integer IUser , Date dtAdd ,
	        String vcUsername , String vcContent )
	{
		this.id = id;
		this.NEnable = NEnable;
		this.IUser = IUser;
		this.dtAdd = dtAdd;
		this.vcUsername = vcUsername;
		this.vcContent = vcContent;
	}
	
	// Property accessors
	@SequenceGenerator( name = "FEEDBACKID" , sequenceName = "S_FEEDBACK" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "FEEDBACKID" )
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
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
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
	
	@Column( name = "VC_USERNAME" , length = 50 )
	public String getVcUsername()
	{
		return this.vcUsername;
	}
	
	public void setVcUsername( String vcUsername )
	{
		this.vcUsername = vcUsername;
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
	
}