package com.clt.sub.model;

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
 * 评价表 TAssess entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ASSESS" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TAssess implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6036066882015873755L;
	@ApiParam( value = "主键" )
	private Integer id;
	@ApiParam( value = "是否有效" )
	private Integer nEnable;
	@ApiParam( value = "添加人" )
	private String vcAdduser;
	@ApiParam( value = "添加时间" )
	private Date dtAdd;
	@ApiParam( value = "服务态度分数" )
	private Integer nAttitude;
	@ApiParam( value = "运输速度分数" )
	private Integer nSpeed;
	@ApiParam( value = "运输质量分数" )
	private Integer nQuality;
	@ApiParam( value = "其他分数" )
	private Integer nOther;
	@ApiParam( value = "业务编号（可能是秒杀编号，可能是指令号）" )
	private String vcBussinesNo;
	@ApiParam( value = "评价类型（0为秒杀类型，1为运输指令类型）" )
	private Integer nType;
	@ApiParam( value = "被评价分供方" )
	private String vcSubno;
	@ApiParam( value = "备注" )
	private String vcNote;
	
	// Constructors
	
	/** default constructor */
	public TAssess()
	{}
	
	/** minimal constructor */
	public TAssess( Integer id )
	{
		this.id = id;
	}
	

	
	// Property accessors
	@SequenceGenerator( name = "ASSESSID" , sequenceName = "S_ASSESS" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "ASSESSID" )
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
	public Integer getnEnable()
	{
		return this.nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
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
	
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "N_ATTITUDE" , length = 20 )
	public Integer getnAttitude()
	{
		return this.nAttitude;
	}
	
	public void setnAttitude( Integer nAttitude )
	{
		this.nAttitude = nAttitude;
	}
	
	@Column( name = "N_SPEED" , precision = 22 , scale = 0 )
	public Integer getnSpeed()
	{
		return this.nSpeed;
	}
	
	public void setnSpeed( Integer nSpeed )
	{
		this.nSpeed = nSpeed;
	}
	
	@Column( name = "N_QUALITY" , precision = 22 , scale = 0 )
	public Integer getnQuality()
	{
		return this.nQuality;
	}
	
	public void setnQuality( Integer nQuality )
	{
		this.nQuality = nQuality;
	}
	
	@Column( name = "N_OTHER" , precision = 22 , scale = 0 )
	public Integer getnOther()
	{
		return nOther;
	}
	
	public void setnOther( Integer nOther )
	{
		this.nOther = nOther;
	}
	
	@Column( name = "VC_BUSSINES_NO" , length = 32 )
	public String getVcBussinesNo()
	{
		return this.vcBussinesNo;
	}
	

	public void setVcBussinesNo( String vcBussinesNo )
	{
		this.vcBussinesNo = vcBussinesNo;
	}
	
	@Column( name = "N_TYPE" , precision = 22 , scale = 0 )
	public Integer getnType()
	{
		return this.nType;
	}
	
	public void setnType( Integer nType )
	{
		this.nType = nType;
	}
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
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
	
}