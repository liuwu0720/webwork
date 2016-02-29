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
 * TCustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_CUSTOMER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TCustomer implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3749422429800224430L;
	private Integer id;
	private String vcCustomerNo;// 客户编号
	private String vcShortName;// 客户简称
	private String vcLinkman;// 客户联系人
	private String vcPhone; // 联系电话
	private String vcRegAddress;// 公司注册地址
	private Integer IProvince; // 公司所属省份ID
	private Integer ICity; // 公司所属城市ID
	private String vcAddress; // 公司地址
	private String vcCustomerName;// 客户全名
	private Integer NEnable; // 是否有效
	private Integer IUser;// 维护人ID
	private Date dtAdd; // 维护时间
	private String vcSubno;// 所属分供方编号
	
	private String vcProvince;// 公司所属省份
	private String vcCity; // 公司所属城市
	
	private Integer NSecondHandCar;// 是否是从erp导过来二手车客户（0为是，1为不是）
	
	// Constructors
	
	/** default constructor */
	public TCustomer()
	{}
	
	/** minimal constructor */
	public TCustomer( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TCustomer( Integer id , String vcCustomerNo , String vcShortName ,
	        String vcLinkman , String vcPhone , String vcRegAddress , Integer IProvince ,
	        Integer ICity , String vcAddress , String vcCustomerName , Integer NEnable ,
	        Integer IUser , Date dtAdd , String vcSubno , String vcProvince ,
	        String vcCity )
	{
		this.id = id;
		this.vcCustomerNo = vcCustomerNo;
		this.vcShortName = vcShortName;
		this.vcLinkman = vcLinkman;
		this.vcPhone = vcPhone;
		this.vcRegAddress = vcRegAddress;
		this.IProvince = IProvince;
		this.ICity = ICity;
		this.vcAddress = vcAddress;
		this.vcCustomerName = vcCustomerName;
		this.NEnable = NEnable;
		this.IUser = IUser;
		this.dtAdd = dtAdd;
		this.vcSubno = vcSubno;
		this.vcProvince = vcProvince;
		this.vcCity = vcCity;
	}
	
	public TCustomer( Integer id , String vcCustomerNo , String vcShortName ,
	        String vcLinkman , String vcPhone , String vcRegAddress , Integer IProvince ,
	        Integer ICity , String vcAddress , String vcCustomerName , Integer NEnable ,
	        Integer IUser , Date dtAdd , String vcSubno )
	{
		this.id = id;
		this.vcCustomerNo = vcCustomerNo;
		this.vcShortName = vcShortName;
		this.vcLinkman = vcLinkman;
		this.vcPhone = vcPhone;
		this.vcRegAddress = vcRegAddress;
		this.IProvince = IProvince;
		this.ICity = ICity;
		this.vcAddress = vcAddress;
		this.vcCustomerName = vcCustomerName;
		this.NEnable = NEnable;
		this.IUser = IUser;
		this.dtAdd = dtAdd;
		this.vcSubno = vcSubno;
		
	}
	
	// Property accessors
	@SequenceGenerator( name = "TCustomerID" , sequenceName = "s_customer" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TCustomerID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_CUSTOMER_NO" , length = 32 )
	public String getVcCustomerNo()
	{
		return this.vcCustomerNo;
	}
	
	public void setVcCustomerNo( String vcCustomerNo )
	{
		this.vcCustomerNo = vcCustomerNo;
	}
	
	@Column( name = "VC_SHORT_NAME" , length = 50 )
	public String getVcShortName()
	{
		return this.vcShortName;
	}
	
	public void setVcShortName( String vcShortName )
	{
		this.vcShortName = vcShortName;
	}
	
	@Column( name = "VC_LINKMAN" , length = 20 )
	public String getVcLinkman()
	{
		return this.vcLinkman;
	}
	
	public void setVcLinkman( String vcLinkman )
	{
		this.vcLinkman = vcLinkman;
	}
	
	@Column( name = "VC_PHONE" , length = 50 )
	public String getVcPhone()
	{
		return this.vcPhone;
	}
	
	public void setVcPhone( String vcPhone )
	{
		this.vcPhone = vcPhone;
	}
	
	@Column( name = "VC_REG_ADDRESS" , length = 120 )
	public String getVcRegAddress()
	{
		return this.vcRegAddress;
	}
	
	public void setVcRegAddress( String vcRegAddress )
	{
		this.vcRegAddress = vcRegAddress;
	}
	
	@Column( name = "I_PROVINCE" , precision = 0 )
	public Integer getIProvince()
	{
		return this.IProvince;
	}
	
	public void setIProvince( Integer IProvince )
	{
		this.IProvince = IProvince;
	}
	
	@Column( name = "I_CITY" , precision = 0 )
	public Integer getICity()
	{
		return this.ICity;
	}
	
	public void setICity( Integer ICity )
	{
		this.ICity = ICity;
	}
	
	@Column( name = "VC_ADDRESS" , length = 120 )
	public String getVcAddress()
	{
		return this.vcAddress;
	}
	
	public void setVcAddress( String vcAddress )
	{
		this.vcAddress = vcAddress;
	}
	
	@Column( name = "VC_CUSTOMER_NAME" , length = 100 )
	public String getVcCustomerName()
	{
		return this.vcCustomerName;
	}
	
	public void setVcCustomerName( String vcCustomerName )
	{
		this.vcCustomerName = vcCustomerName;
	}
	
	@Column( name = "N_ENABLE" , precision = 0 )
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
	}
	
	@Column( name = "I_USER" , precision = 0 )
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
	
	@Column( name = "VC_SUBNO" , length = 100 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "VC_PROVINCE" , length = 100 )
	public String getVcProvince()
	{
		return vcProvince;
	}
	
	public void setVcProvince( String vcProvince )
	{
		this.vcProvince = vcProvince;
	}
	
	@Column( name = "VC_CITY" , length = 100 )
	public String getVcCity()
	{
		return vcCity;
	}
	
	public void setVcCity( String vcCity )
	{
		this.vcCity = vcCity;
	}
	
	@Column( name = "N_SECOND_HAND_CAR" )
	public Integer getNSecondHandCar()
	{
		return NSecondHandCar;
	}
	
	public void setNSecondHandCar( Integer nSecondHandCar )
	{
		NSecondHandCar = nSecondHandCar;
	}
	
}