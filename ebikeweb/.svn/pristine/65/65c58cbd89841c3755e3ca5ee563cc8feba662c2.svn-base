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
 * TDriverCost entity.应收单价 @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_DRIVER_COST" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDriverCost implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 8665021390613189029L;
	private Integer id;
	private Date dtStart;// 起效日期
	private Date dtEnd;// 失效日期
	private Float nCost;// 单价
	private Date dtAdd;// 最后一次维护时间
	private Integer NEnable;// 是否有效（0有效，1无效）
	private String vcSubno;// 所属分供方编号
	private String vcUpdateUser;// 最后一次维护人
	private String vcStartCity;// 起始地城市
	private String vcEndCity;// 目的地城市
	private Integer ICustomerid;// 分供方的客户ID
	private String vcCustomer;// 客户全称
	private Integer IStartId;// 起始地城市ID
	private Integer IEndId;// 目的地城市ID
	private Integer ISubCarid;// 分供方商品车ID
	private Integer NCheck;// 是否审核（0未审核，1已审核）
	private Integer nType;// 计价方式：1:单公里;2:单台
	private String vcCarName;// 车名
	private Float nTotal;// 总价
	// Constructors
	
	/** default constructor */
	public TDriverCost()
	{}
	
	/** minimal constructor */
	public TDriverCost( Integer id )
	{
		this.id = id;
	}
	
	
	// Property accessors
	@SequenceGenerator( name = "TDriverCostID" , sequenceName = "s_driver_cost" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDriverCostID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_START" )
	public Date getDtStart()
	{
		return this.dtStart;
	}
	
	public void setDtStart( Date dtStart )
	{
		this.dtStart = dtStart;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_END" )
	public Date getDtEnd()
	{
		return this.dtEnd;
	}
	
	public void setDtEnd( Date dtEnd )
	{
		this.dtEnd = dtEnd;
	}
	
	@Column( name = "N_COST" , precision = 22 , scale = 0 )
	public Float getnCost()
	{
		return this.nCost;
	}
	
	public void setnCost( Float nCost )
	{
		this.nCost = nCost;
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
	

	
	@Column( name = "VC_START_CITY" , length = 32 )
	public String getVcStartCity()
	{
		return this.vcStartCity;
	}
	
	public void setVcStartCity( String vcStartCity )
	{
		this.vcStartCity = vcStartCity;
	}
	
	@Column( name = "VC_END_CITY" , length = 32 )
	public String getVcEndCity()
	{
		return this.vcEndCity;
	}
	
	public void setVcEndCity( String vcEndCity )
	{
		this.vcEndCity = vcEndCity;
	}
	
	@Column( name = "I_CUSTOMERID" , precision = 22 , scale = 0 )
	public Integer getICustomerid()
	{
		return this.ICustomerid;
	}
	
	public void setICustomerid( Integer ICustomerid )
	{
		this.ICustomerid = ICustomerid;
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
	
	@Column( name = "I_SUB_CARID" , precision = 22 , scale = 0 )
	public Integer getISubCarid()
	{
		return this.ISubCarid;
	}
	
	public void setISubCarid( Integer ISubCarid )
	{
		this.ISubCarid = ISubCarid;
	}
	
	@Column( name = "N_CHECK" , precision = 22 , scale = 0 )
	public Integer getNCheck()
	{
		return NCheck;
	}
	
	public void setNCheck( Integer nCheck )
	{
		NCheck = nCheck;
	}
	
	@Column( name = "N_TYPE" , precision = 22 , scale = 0 )
	public Integer getnType()
	{
		return nType;
	}
	
	public void setnType( Integer nType )
	{
		this.nType = nType;
	}
	
	@Column( name = "VC_UPDATE_USER" , length = 32 )
	public String getVcUpdateUser()
	{
		return vcUpdateUser;
	}
	
	public void setVcUpdateUser( String vcUpdateUser )
	{
		this.vcUpdateUser = vcUpdateUser;
	}
	
	@Column( name = "VC_CUSTOMER" , length = 32 )
	public String getVcCustomer()
	{
		return vcCustomer;
	}
	
	public void setVcCustomer( String vcCustomer )
	{
		this.vcCustomer = vcCustomer;
	}
	
	@Column( name = "VC_CARNAME" , length = 32 )
	public String getVcCarName()
	{
		return vcCarName;
	}
	
	public void setVcCarName( String vcCarName )
	{
		this.vcCarName = vcCarName;
	}
	
	@Column( name = "N_TOTAL" , length = 32 )
	public Float getnTotal()
	{
		return nTotal;
	}
	
	public void setnTotal( Float nTotal )
	{
		this.nTotal = nTotal;
	}
	
}