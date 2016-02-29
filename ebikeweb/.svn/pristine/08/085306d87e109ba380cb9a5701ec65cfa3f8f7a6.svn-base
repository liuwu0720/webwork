package com.clt.sub.model;

// default package

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
 * TAssessPick entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ASSESS_PICK" , schema = "CLTWL1" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TAssessPick implements java.io.Serializable
{
	
	// Fields
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "是否有效" )
	private Integer NEnable;
	@ApiParam( "添加人userId" )
	private Integer IUser;
	@ApiParam( "添加时间" )
	private Date dtAdd;
	@ApiParam( "添加人姓名" )
	private String vcUsername;
	@ApiParam( "被评价用户id" )
	private Integer IUserBy;
	@ApiParam( "评价用户id" )
	private Integer IUserAssess;
	@ApiParam( "评价时间" )
	private Date dtAssess;
	@ApiParam( "服务态度评分" )
	private Integer NQos;
	@ApiParam( "诚信度评分" )
	private Integer NIntegrity;
	@ApiParam( "第四纬度内容" )
	private String vcOther;
	@ApiParam( "第四纬度评分" )
	private Integer NOther;
	@ApiParam( "备注" )
	private String vcNote;
	@ApiParam( "服务效率" )
	private Integer NServiceEfficiency;
	@ApiParam( "车辆品质" )
	private Integer NCarQuality;
	
	// Constructors
	
	/** default constructor */
	public TAssessPick()
	{}
	
	/** full constructor */
	public TAssessPick( Integer NEnable , Integer IUser , Date dtAdd , String vcUsername ,
	        Integer IUserBy , Integer IUserAssess , Date dtAssess , Integer NQos ,
	        Integer NIntegrity , String vcOther , Integer NOther , String vcNote ,
	        Integer NServiceEfficiency , Integer NCarQuality )
	{
		this.NEnable = NEnable;
		this.IUser = IUser;
		this.dtAdd = dtAdd;
		this.vcUsername = vcUsername;
		this.IUserBy = IUserBy;
		this.IUserAssess = IUserAssess;
		this.dtAssess = dtAssess;
		this.NQos = NQos;
		this.NIntegrity = NIntegrity;
		this.vcOther = vcOther;
		this.NOther = NOther;
		this.vcNote = vcNote;
		this.NServiceEfficiency = NServiceEfficiency;
		this.NCarQuality = NCarQuality;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TAssessPickId" , sequenceName = "S_ASSESS_PICK" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TAssessPickId" )
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
	
	@Column( name = "VC_USERNAME" , length = 40 )
	public String getVcUsername()
	{
		return this.vcUsername;
	}
	
	public void setVcUsername( String vcUsername )
	{
		this.vcUsername = vcUsername;
	}
	
	@Column( name = "I_USER_BY" , precision = 22 , scale = 0 )
	public Integer getIUserBy()
	{
		return this.IUserBy;
	}
	
	public void setIUserBy( Integer IUserBy )
	{
		this.IUserBy = IUserBy;
	}
	
	@Column( name = "I_USER_ASSESS" , precision = 22 , scale = 0 )
	public Integer getIUserAssess()
	{
		return this.IUserAssess;
	}
	
	public void setIUserAssess( Integer IUserAssess )
	{
		this.IUserAssess = IUserAssess;
	}
	
	@Column( name = "DT_ASSESS" , length = 7 )
	public Date getDtAssess()
	{
		return this.dtAssess;
	}
	
	public void setDtAssess( Date dtAssess )
	{
		this.dtAssess = dtAssess;
	}
	
	@Column( name = "N_QOS" , precision = 22 , scale = 0 )
	public Integer getNQos()
	{
		return this.NQos;
	}
	
	public void setNQos( Integer NQos )
	{
		this.NQos = NQos;
	}
	
	@Column( name = "N_INTEGRITY" , precision = 22 , scale = 0 )
	public Integer getNIntegrity()
	{
		return this.NIntegrity;
	}
	
	public void setNIntegrity( Integer NIntegrity )
	{
		this.NIntegrity = NIntegrity;
	}
	
	@Column( name = "VC_OTHER" , length = 20 )
	public String getVcOther()
	{
		return this.vcOther;
	}
	
	public void setVcOther( String vcOther )
	{
		this.vcOther = vcOther;
	}
	
	@Column( name = "N_OTHER" , precision = 22 , scale = 0 )
	public Integer getNOther()
	{
		return this.NOther;
	}
	
	public void setNOther( Integer NOther )
	{
		this.NOther = NOther;
	}
	
	@Column( name = "VC_NOTE" , length = 200 )
	public String getVcNote()
	{
		return this.vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	@Column( name = "N_SERVICE_EFFICIENCY" , precision = 22 , scale = 0 )
	public Integer getNServiceEfficiency()
	{
		return this.NServiceEfficiency;
	}
	
	public void setNServiceEfficiency( Integer NServiceEfficiency )
	{
		this.NServiceEfficiency = NServiceEfficiency;
	}
	
	@Column( name = "N_CAR_QUALITY" , precision = 22 , scale = 0 )
	public Integer getNCarQuality()
	{
		return this.NCarQuality;
	}
	
	public void setNCarQuality( Integer NCarQuality )
	{
		this.NCarQuality = NCarQuality;
	}
	
}