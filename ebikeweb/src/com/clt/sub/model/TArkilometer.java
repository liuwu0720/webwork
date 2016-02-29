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

/**
 * 应收公里数 TArkilometer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ARKILOMETER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TArkilometer implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 6792911410541517562L;
	private Integer id;
	private Integer IStartId;
	private Integer IEndId;
	private Float NKilometer;
	private String vcStartCity;
	private String vcEndCity;
	private Date dtStart;
	private Date dtEnd;
	private Date dtUpdate;
	private Integer IUpdateUser;
	private Integer NEnable;
	private String vcSubno;
	private Integer iCustomerId;
	private String vcCustomer;
	private String vcUpdateUser;
	private Integer nCheck;// 是否审核（0未审核，1已审核）
	
	// Constructors
	
	/** default constructor */
	public TArkilometer()
	{}
	
	/** full constructor */
	public TArkilometer( Integer IStartId , Integer IEndId , Float NKilometer ,
	        String vcStartCity , String vcEndCity , Date dtStart , Date dtEnd ,
	        Date dtUpdate , Integer IUpdateUser , Integer NEnable , String vcSubno )
	{
		this.IStartId = IStartId;
		this.IEndId = IEndId;
		this.NKilometer = NKilometer;
		this.vcStartCity = vcStartCity;
		this.vcEndCity = vcEndCity;
		this.dtStart = dtStart;
		this.dtEnd = dtEnd;
		this.dtUpdate = dtUpdate;
		this.IUpdateUser = IUpdateUser;
		this.NEnable = NEnable;
		this.vcSubno = vcSubno;
	}
	
	// Property accessors
	@SequenceGenerator( name = "ARKILOMETERID" , sequenceName = "S_ARKILOMETER" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "ARKILOMETERID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_START_ID" , precision = 22 , scale = 0 )
	public Integer getIStartId()
	{
		return this.IStartId;
	}
	
	public void setIStartId( Integer IStartId )
	{
		this.IStartId = IStartId;
	}
	
	@Column( name = "I_END_ID" , precision = 22 , scale = 0 )
	public Integer getIEndId()
	{
		return this.IEndId;
	}
	
	public void setIEndId( Integer IEndId )
	{
		this.IEndId = IEndId;
	}
	
	@Column( name = "N_KILOMETER" , precision = 22 , scale = 0 )
	public Float getNKilometer()
	{
		return this.NKilometer;
	}
	
	public void setNKilometer( Float NKilometer )
	{
		this.NKilometer = NKilometer;
	}
	
	@Column( name = "VC_START_CITY" , length = 50 )
	public String getVcStartCity()
	{
		return this.vcStartCity;
	}
	
	public void setVcStartCity( String vcStartCity )
	{
		this.vcStartCity = vcStartCity;
	}
	
	@Column( name = "VC_END_CITY" , length = 50 )
	public String getVcEndCity()
	{
		return this.vcEndCity;
	}
	
	public void setVcEndCity( String vcEndCity )
	{
		this.vcEndCity = vcEndCity;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_START" , length = 7 )
	public Date getDtStart()
	{
		return this.dtStart;
	}
	
	public void setDtStart( Date dtStart )
	{
		this.dtStart = dtStart;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_END" , length = 7 )
	public Date getDtEnd()
	{
		return this.dtEnd;
	}
	
	public void setDtEnd( Date dtEnd )
	{
		this.dtEnd = dtEnd;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_UPDATE" , length = 7 )
	public Date getDtUpdate()
	{
		return this.dtUpdate;
	}
	
	public void setDtUpdate( Date dtUpdate )
	{
		this.dtUpdate = dtUpdate;
	}
	
	@Column( name = "I_UPDATE_USER" , precision = 22 , scale = 0 )
	public Integer getIUpdateUser()
	{
		return this.IUpdateUser;
	}
	
	public void setIUpdateUser( Integer IUpdateUser )
	{
		this.IUpdateUser = IUpdateUser;
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
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "I_CUSTOMERID" )
	public Integer getiCustomerId()
	{
		return iCustomerId;
	}
	
	public void setiCustomerId( Integer iCustomerId )
	{
		this.iCustomerId = iCustomerId;
	}
	
	@Column( name = "VC_CUSTOMER" )
	public String getVcCustomer()
	{
		return vcCustomer;
	}
	
	public void setVcCustomer( String vcCustomer )
	{
		this.vcCustomer = vcCustomer;
	}
	
	@Column( name = "VC_UPDATEUSER" )
	public String getVcUpdateUser()
	{
		return vcUpdateUser;
	}
	
	public void setVcUpdateUser( String vcUpdateUser )
	{
		this.vcUpdateUser = vcUpdateUser;
	}
	
	@Column( name = "N_CHECK" )
	public Integer getnCheck()
	{
		return nCheck;
	}
	
	public void setnCheck( Integer nCheck )
	{
		this.nCheck = nCheck;
	}
	
}