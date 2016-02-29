package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * TSpareCapacity entity. 空闲运力实体类 @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_SPARE_CAPACITY" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TSpareCapacity implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8107773993212108501L;
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "是否有效" )
	private Integer NEnable;
	@ApiParam( "发布时间(pattern='yyyy-MM-dd HH:mm:ss')" )
	private Date dtAdd;// 发布时间
	@ApiParam( "发布人id" )
	private Integer IUser;// 发布人id
	@ApiParam( "发布人姓名" )
	private String vcUserName;// 发布人姓名
	@ApiParam( "信息有效开始时间(pattern ='yyyy-MM-dd')" )
	private Date dtBegin;// 信息有效开始时间
	@ApiParam( "信息有效结束时间(pattern = 'yyyy-MM-dd')" )
	private Date dtEnd;// 信息有效结束时间
	@ApiParam( "有效仓位数量" )
	@Min( value = 1 , message = "仓位至少一个" )
	private Integer NSpace;// 有效仓位数量
	@ApiParam( "起始地" )
	@NotEmpty( message = "起始地不为空" )
	private String vcStartAddress;// 起始地
	@ApiParam( "目的地" )
	@NotEmpty( message = "目的地不为空" )
	private String vcEndAddress;// 目的地
	@ApiParam( "车长" )
	private Double NLength;// 车长
	@ApiParam( "车宽" )
	private Double NWidth;// 车宽
	@ApiParam( "车高" )
	private Double NHeight;// 车高
	@ApiParam( "描述" )
	private String vcDesc;// 描述
	@ApiParam( "联系电话（默认隐藏，可用积分换得查看权利）" )
	private String vcLink;// 联系电话（默认隐藏，可用积分换得查看权利）
	@ApiParam( "仓位价格(可填写具体单价或者价格范围)" )
	private String vcPrice;// 仓位价格(可填写具体单价或者价格范围)
	@ApiParam( "总吨位" )
	private Double NWeight;// 总吨位
	@ApiParam( "出发地省份id" )
	private Integer IProvinceStart;// 出发地省份
	@ApiParam( "出发地城市id" )
	private Integer ICityStart;// 出发地城市
	@ApiParam( "目的地省份id" )
	private Integer IProvinceEnd;// 目的地省份
	@ApiParam( "目的地城市id" )
	private Integer ICityEnd;// 目的地城市
	@ApiParam( "车型id" )
	private Integer ITruckType;// 车型id
	@ApiParam( "出发地省份名称" )
	private String vcProvinceStart;// 出发地省份名称
	@ApiParam( "出发地城市名称" )
	private String vcCityStart;// 出发地城市名称
	@ApiParam( "目的地省份名称" )
	private String vcProvinceEnd;// 目的地省份名称
	@ApiParam( "目的地城市名称" )
	private String vcCityEnd;// 目的地城市名称
	@ApiParam( "出发地经度" )
	private String vcLongStart;// 出发地经度
	@ApiParam( "出发地纬度" )
	private String vcLatStart;// 出发地纬度
	@ApiParam( "目的地经度" )
	private String vcLongEnd;// 目的地经度
	@ApiParam( "目的地纬度" )
	private String vcLatEnd;// 目的地纬度
	@ApiParam( "车型名称" )
	private String vcTruckName;
	
	// Constructors
	
	/** default constructor */
	public TSpareCapacity()
	{}
	
	/** minimal constructor */
	public TSpareCapacity( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TSPARECAPACITYID" , sequenceName = "S_SPARE_CAPACITY" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSPARECAPACITYID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public TSpareCapacity( Integer id , Integer nEnable , Date dtAdd , Integer iUser ,
	        String vcUserName , Date dtBegin , Date dtEnd , Integer nSpace ,
	        String vcStartAddress , String vcEndAddress , Double nLength , Double nWidth ,
	        Double nHeight , String vcDesc , String vcLink , String vcPrice ,
	        Double nWeight , Integer iProvinceStart , Integer iCityStart ,
	        Integer iProvinceEnd , Integer iCityEnd )
	{
		super();
		this.id = id;
		NEnable = nEnable;
		this.dtAdd = dtAdd;
		IUser = iUser;
		this.vcUserName = vcUserName;
		this.dtBegin = dtBegin;
		this.dtEnd = dtEnd;
		NSpace = nSpace;
		this.vcStartAddress = vcStartAddress;
		this.vcEndAddress = vcEndAddress;
		NLength = nLength;
		NWidth = nWidth;
		NHeight = nHeight;
		this.vcDesc = vcDesc;
		this.vcLink = vcLink;
		this.vcPrice = vcPrice;
		NWeight = nWeight;
		IProvinceStart = iProvinceStart;
		ICityStart = iCityStart;
		IProvinceEnd = iProvinceEnd;
		ICityEnd = iCityEnd;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_ENABLE" )
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
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
	
	@Column( name = "I_USER" )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer IUser )
	{
		this.IUser = IUser;
	}
	
	@Column( name = "VC_USER_NAME" , length = 30 )
	public String getVcUserName()
	{
		return this.vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_BEGIN" )
	public Date getDtBegin()
	{
		return this.dtBegin;
	}
	
	public void setDtBegin( Date dtBegin )
	{
		this.dtBegin = dtBegin;
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
	
	@Column( name = "N_SPACE" )
	public Integer getNSpace()
	{
		return this.NSpace;
	}
	
	public void setNSpace( Integer NSpace )
	{
		this.NSpace = NSpace;
	}
	
	@Column( name = "VC_START_ADDRESS" , length = 100 )
	public String getVcStartAddress()
	{
		return this.vcStartAddress;
	}
	
	public void setVcStartAddress( String vcStartAddress )
	{
		this.vcStartAddress = vcStartAddress;
	}
	
	@Column( name = "VC_END_ADDRESS" , length = 100 )
	public String getVcEndAddress()
	{
		return this.vcEndAddress;
	}
	
	public void setVcEndAddress( String vcEndAddress )
	{
		this.vcEndAddress = vcEndAddress;
	}
	
	@Column( name = "N_LENGTH" )
	public Double getNLength()
	{
		return this.NLength;
	}
	
	public void setNLength( Double NLength )
	{
		this.NLength = NLength;
	}
	
	@Column( name = "N_WIDTH" )
	public Double getNWidth()
	{
		return this.NWidth;
	}
	
	public void setNWidth( Double NWidth )
	{
		this.NWidth = NWidth;
	}
	
	@Column( name = "N_HEIGHT" )
	public Double getNHeight()
	{
		return this.NHeight;
	}
	
	public void setNHeight( Double NHeight )
	{
		this.NHeight = NHeight;
	}
	
	@Column( name = "VC_DESC" , length = 400 )
	public String getVcDesc()
	{
		return this.vcDesc;
	}
	
	public void setVcDesc( String vcDesc )
	{
		this.vcDesc = vcDesc;
	}
	
	@Column( name = "VC_LINK" , length = 50 )
	public String getVcLink()
	{
		return this.vcLink;
	}
	
	public void setVcLink( String vcLink )
	{
		this.vcLink = vcLink;
	}
	
	@Column( name = "VC_PRICE" , length = 40 )
	public String getVcPrice()
	{
		return this.vcPrice;
	}
	
	public void setVcPrice( String vcPrice )
	{
		this.vcPrice = vcPrice;
	}
	
	@Column( name = "N_WEIGHT" )
	public Double getNWeight()
	{
		return this.NWeight;
	}
	
	public void setNWeight( Double NWeight )
	{
		this.NWeight = NWeight;
	}
	
	@Column( name = "I_PROVINCE_START" )
	public Integer getIProvinceStart()
	{
		return IProvinceStart;
	}
	
	public void setIProvinceStart( Integer iProvinceStart )
	{
		IProvinceStart = iProvinceStart;
	}
	
	@Column( name = "I_CITY_START" )
	public Integer getICityStart()
	{
		return ICityStart;
	}
	
	public void setICityStart( Integer iCityStart )
	{
		ICityStart = iCityStart;
	}
	
	@Column( name = "I_PROVINCE_END" )
	public Integer getIProvinceEnd()
	{
		return IProvinceEnd;
	}
	
	public void setIProvinceEnd( Integer iProvinceEnd )
	{
		IProvinceEnd = iProvinceEnd;
	}
	
	@Column( name = "I_CITY_END" )
	public Integer getICityEnd()
	{
		return ICityEnd;
	}
	
	public void setICityEnd( Integer iCityEnd )
	{
		ICityEnd = iCityEnd;
	}
	
	@Column( name = "I_TRUCK_TYPE" )
	public Integer getITruckType()
	{
		return ITruckType;
	}
	
	public void setITruckType( Integer iTruckType )
	{
		ITruckType = iTruckType;
	}
	
	@Column( name = "VC_PROVINCE_START" , length = 40 )
	public String getVcProvinceStart()
	{
		return vcProvinceStart;
	}
	
	public void setVcProvinceStart( String vcProvinceStart )
	{
		this.vcProvinceStart = vcProvinceStart;
	}
	
	@Column( name = "VC_CITY_START" , length = 40 )
	public String getVcCityStart()
	{
		return vcCityStart;
	}
	
	public void setVcCityStart( String vcCityStart )
	{
		this.vcCityStart = vcCityStart;
	}
	
	@Column( name = "VC_PROVINCE_END" , length = 40 )
	public String getVcProvinceEnd()
	{
		return vcProvinceEnd;
	}
	
	public void setVcProvinceEnd( String vcProvinceEnd )
	{
		this.vcProvinceEnd = vcProvinceEnd;
	}
	
	@Column( name = "VC_CITY_END" , length = 40 )
	public String getVcCityEnd()
	{
		return vcCityEnd;
	}
	
	public void setVcCityEnd( String vcCityEnd )
	{
		this.vcCityEnd = vcCityEnd;
	}
	
	@Column( name = "VC_LONG_START" , length = 40 )
	public String getVcLongStart()
	{
		return vcLongStart;
	}
	
	public void setVcLongStart( String vcLongStart )
	{
		this.vcLongStart = vcLongStart;
	}
	
	@Column( name = "VC_LAT_START" , length = 40 )
	public String getVcLatStart()
	{
		return vcLatStart;
	}
	
	public void setVcLatStart( String vcLatStart )
	{
		this.vcLatStart = vcLatStart;
	}
	
	@Column( name = "VC_LONG_END" , length = 40 )
	public String getVcLongEnd()
	{
		return vcLongEnd;
	}
	
	public void setVcLongEnd( String vcLongEnd )
	{
		this.vcLongEnd = vcLongEnd;
	}
	
	@Column( name = "VC_LAT_END" , length = 40 )
	public String getVcLatEnd()
	{
		return vcLatEnd;
	}
	
	public void setVcLatEnd( String vcLatEnd )
	{
		this.vcLatEnd = vcLatEnd;
	}
	
	public String toString()
	{
		return vcUserName + "," + vcStartAddress + "," + vcEndAddress + "," + vcLink;
	}
	
	@Column( name = "VC_TRUCK_NAME" )
	public String getVcTruckName()
	{
		return vcTruckName;
	}
	
	public void setVcTruckName( String vcTruckName )
	{
		this.vcTruckName = vcTruckName;
	}
	
}