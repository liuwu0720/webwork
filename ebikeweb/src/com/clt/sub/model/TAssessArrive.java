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

import com.wordnik.swagger.annotations.ApiParam;

/**
 * TAssessArrive entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ASSESS_ARRIVE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TAssessArrive implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 7885119213869660736L;
	@ApiParam( value = "主键" )
	private Integer id;
	@ApiParam( value = "是否有效（0有效，1无效）" )
	private Integer nEnable;
	@ApiParam( value = "添加人userId" )
	private Integer iUser;
	@ApiParam( value = "添加时间" )
	private Date dtAdd;
	@ApiParam( value = "添加人姓名" )
	private String vcUsername;
	@ApiParam( value = "被评价用户id" )
	private Integer iUserBy;
	@ApiParam( value = "评价用户id" )
	private Integer iUserAssess;
	@ApiParam( value = "评价时间" )
	private Date dtAssess;
	@ApiParam( value = "第四纬度内容" )
	private String vcOther;
	@ApiParam( value = "第四纬度评分" )
	private Integer nOther;
	@ApiParam( value = "备注" )
	private String vcNote;
	@ApiParam( value = "收车态度" )
	private Integer nAcceptAttitude;
	@ApiParam( value = "收车效率" )
	private Integer nAcceptEfficiency;
	@ApiParam( value = "收车信誉" )
	private Integer nAcceptReputation;
	
	// Constructors
	
	/** default constructor */
	public TAssessArrive()
	{}
	
	
	// Property accessors
	@SequenceGenerator( name = "T_ASSESS_ARRIVEID" , sequenceName = "S_ASSESS_ARRIVE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "T_ASSESS_ARRIVEID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_ENABLE" , precision = 0 )
	public Integer getnEnable()
	{
		return this.nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	
	@Column( name = "I_USER" , precision = 0 )
	public Integer getiUser()
	{
		return this.iUser;
	}
	
	public void setiUser( Integer iUser )
	{
		this.iUser = iUser;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
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
	
	@Column( name = "I_USER_BY" , precision = 0 )
	public Integer getiUserBy()
	{
		return this.iUserBy;
	}
	
	public void setiUserBy( Integer iUserBy )
	{
		this.iUserBy = iUserBy;
	}
	
	@Column( name = "I_USER_ASSESS" , precision = 0 )
	public Integer getiUserAssess()
	{
		return this.iUserAssess;
	}
	
	public void setiUserAssess( Integer iUserAssess )
	{
		this.iUserAssess = iUserAssess;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_ASSESS" , length = 7 )
	public Date getDtAssess()
	{
		return this.dtAssess;
	}
	
	public void setDtAssess( Date dtAssess )
	{
		this.dtAssess = dtAssess;
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
	
	@Column( name = "N_OTHER" , precision = 0 )
	public Integer getnOther()
	{
		return this.nOther;
	}
	
	public void setnOther( Integer nOther )
	{
		this.nOther = nOther;
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
	
	@Column( name = "N_ACCEPT_ATTITUDE" , precision = 0 )
	public Integer getnAcceptAttitude()
	{
		return this.nAcceptAttitude;
	}
	
	public void setnAcceptAttitude( Integer nAcceptAttitude )
	{
		this.nAcceptAttitude = nAcceptAttitude;
	}
	
	@Column( name = "N_ACCEPT_EFFICIENCY" , precision = 0 )
	public Integer getnAcceptEfficiency()
	{
		return this.nAcceptEfficiency;
	}
	
	public void setnAcceptEfficiency( Integer nAcceptEfficiency )
	{
		this.nAcceptEfficiency = nAcceptEfficiency;
	}
	
	@Column( name = "N_ACCEPT_REPUTATION" , precision = 0 )
	public Integer getnAcceptReputation()
	{
		return this.nAcceptReputation;
	}
	
	public void setnAcceptReputation( Integer nAcceptReputation )
	{
		this.nAcceptReputation = nAcceptReputation;
	}
	
}