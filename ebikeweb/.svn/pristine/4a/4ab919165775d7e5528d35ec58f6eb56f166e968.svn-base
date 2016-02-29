package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Future;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * 抢单产品表 TProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_PRODUCT" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TProduct implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * IUserId
	 */
	private static final long serialVersionUID = - 4919780984352023940L;
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "发布者id" )
	private Integer IUserId;
	@NotBlank( message = "请填写起始点" )
	@ApiParam( "路线起始点" )
	private String vcStart;
	@NotBlank( message = "请填写终点" )
	@ApiParam( "路线终点" )
	private String vcEnd;
	@Future
	@ApiParam( "要求抵达时间" )
	private Date dtArriveTime;
	@ApiParam( "公里数" )
	private Float NKilometre;
	@ApiParam( "标准价格（秒杀起步价）" )
	private Float NMinPrice;
	@ApiParam( "秒杀开始时间" )
	private Date dtKillStart;
	@Future
	@ApiParam( "秒杀结束时间" )
	private Date dtKillEnd;
	@ApiParam( "要求" )
	private String vcRequire;
	@ApiParam( "状态（0为有效，1为无效）" )
	private Integer NEnable;
	@ApiParam( "发布者姓名" )
	private String vcUserName;
	@ApiParam( "秒杀产品编号" )
	private String vcKillno;
	@ApiParam( "所属分供方编号" )
	private String vcSubno;
	@ApiParam( "发布时间" )
	private Date dtRelease;
	@ApiParam( "是否中标（0为中标，1为初始化状态，2为中标者废标，3为发布者废标，4为中标者接受中标，5为发布者评价）" )
	private Integer NBid;
	@ApiParam( "最高价" )
	private Float NMaxPrice;
	@ApiParam( "发布范围类型（0：全部，1为指定范围）" )
	private Integer NScope;
	@ApiParam( "路线起始点经度" )
	private String vcLong;
	@ApiParam( "路线起始点纬度" )
	private String vcLat;
	@ApiParam( "路线起始点geohash值" )
	private String vcHash;
	@ApiParam( "投标人次" )
	private Integer NPersonTime;
	@ApiParam( "数量" )
	private Integer NCount;
	@ApiParam( "图标路径" )
	private String vcPicPath;
	@ApiParam( "图标描述" )
	private String vcPicDesc;
	@ApiParam( "车型名称" )
	private String vcCarName;
	@ApiParam( "火点" )
	private Integer NFire;
	@ApiParam( "客户订单号(客户自己录入)" )
	private String vcCustOrderno;
	@ApiParam( "客户运单数量" )
	private String NCustOrder;
	private String bidPrice;
	@ApiParam( "是否结束（默认为1 没结束，0为结束<抢单转换的订单都抵达了>）" )
	private Integer NEnd;
	
	// Constructors
	
	@Transient
	public String getBidPrice()
	{
		return bidPrice;
	}
	
	public void setBidPrice( String bidPrice )
	{
		this.bidPrice = bidPrice;
	}
	
	/** default constructor */
	public TProduct()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TProductID" , sequenceName = "S_PRODUCT" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TProductID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_USER_ID" , precision = 22 , scale = 0 )
	public Integer getIUserId()
	{
		return this.IUserId;
	}
	
	public void setIUserId( Integer TUser )
	{
		this.IUserId = TUser;
	}
	
	@Column( name = "VC_START" , length = 100 )
	public String getVcStart()
	{
		return this.vcStart;
	}
	
	public void setVcStart( String vcStart )
	{
		this.vcStart = vcStart;
	}
	
	@Column( name = "VC_END" , length = 100 )
	public String getVcEnd()
	{
		return this.vcEnd;
	}
	
	public void setVcEnd( String vcEnd )
	{
		this.vcEnd = vcEnd;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_ARRIVE_TIME" , length = 7 )
	public Date getDtArriveTime()
	{
		return this.dtArriveTime;
	}
	
	public void setDtArriveTime( Date dtArriveTime )
	{
		this.dtArriveTime = dtArriveTime;
	}
	
	@Column( name = "N_KILOMETRE" , precision = 22 , scale = 0 )
	public Float getNKilometre()
	{
		return this.NKilometre;
	}
	
	public void setNKilometre( Float NKilometre )
	{
		this.NKilometre = NKilometre;
	}
	
	@Column( name = "N_MIN_PRICE" , precision = 22 , scale = 0 )
	public Float getNMinPrice()
	{
		return this.NMinPrice;
	}
	
	public void setNMinPrice( Float NMinPrice )
	{
		this.NMinPrice = NMinPrice;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_KILL_START" , length = 7 )
	public Date getDtKillStart()
	{
		return this.dtKillStart;
	}
	
	public void setDtKillStart( Date dtKillStart )
	{
		this.dtKillStart = dtKillStart;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_KILL_END" , length = 7 )
	public Date getDtKillEnd()
	{
		return this.dtKillEnd;
	}
	
	public void setDtKillEnd( Date dtKillEnd )
	{
		this.dtKillEnd = dtKillEnd;
	}
	
	@Column( name = "VC_REQUIRE" , length = 500 )
	public String getVcRequire()
	{
		return this.vcRequire;
	}
	
	public void setVcRequire( String vcRequire )
	{
		this.vcRequire = vcRequire;
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
	
	@Column( name = "VC_USER_NAME" , length = 30 )
	public String getVcUserName()
	{
		return this.vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	@Column( name = "VC_KILLNO" , length = 32 , nullable = false )
	public String getVcKillno()
	{
		return vcKillno;
	}
	
	/**
	 * @param vcKillno
	 *            the vcKillno to set
	 */
	public void setVcKillno( String vcKillno )
	{
		this.vcKillno = vcKillno;
	}
	
	@Column( name = "VC_SUBNO" , length = 32 , nullable = false )
	public String getVcSubno()
	{
		return vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_RELEASE" , length = 7 )
	public Date getDtRelease()
	{
		return dtRelease;
	}
	
	public void setDtRelease( Date dtRelease )
	{
		this.dtRelease = dtRelease;
	}
	
	@Column( name = "N_BID" , precision = 22 , scale = 0 )
	public Integer getNBid()
	{
		return NBid;
	}
	
	public void setNBid( Integer nBid )
	{
		NBid = nBid;
	}
	
	@Column( name = "N_MAX_PRICE" )
	public Float getNMaxPrice()
	{
		return NMaxPrice;
	}
	
	public void setNMaxPrice( Float nMaxPrice )
	{
		NMaxPrice = nMaxPrice;
	}
	
	@Column( name = "N_SCOPE" )
	public Integer getNScope()
	{
		return NScope;
	}
	
	public void setNScope( Integer nScope )
	{
		NScope = nScope;
	}
	
	@Column( name = "VC_LONG" )
	public String getVcLong()
	{
		return vcLong;
	}
	
	public void setVcLong( String vcLong )
	{
		this.vcLong = vcLong;
	}
	
	@Column( name = "VC_LAT" )
	public String getVcLat()
	{
		return vcLat;
	}
	
	public void setVcLat( String vcLat )
	{
		this.vcLat = vcLat;
	}
	
	@Column( name = "VC_HASH" )
	public String getVcHash()
	{
		return vcHash;
	}
	
	public void setVcHash( String vcHash )
	{
		this.vcHash = vcHash;
	}
	
	@Column( name = "N_PERSON_TIME" )
	public Integer getNPersonTime()
	{
		return NPersonTime;
	}
	
	public void setNPersonTime( Integer nPersonTime )
	{
		NPersonTime = nPersonTime;
	}
	
	@Column( name = "N_COUNT" )
	public Integer getNCount()
	{
		return NCount;
	}
	
	public void setNCount( Integer nCount )
	{
		NCount = nCount;
	}
	
	@Column( name = "VC_PIC_PATH" )
	public String getVcPicPath()
	{
		return vcPicPath;
	}
	
	public void setVcPicPath( String vcPicPath )
	{
		this.vcPicPath = vcPicPath;
	}
	
	@Column( name = "VC_PIC_DESC" )
	public String getVcPicDesc()
	{
		return vcPicDesc;
	}
	
	public void setVcPicDesc( String vcPicDesc )
	{
		this.vcPicDesc = vcPicDesc;
	}
	
	@Column( name = "VC_CAR_NAME" )
	public String getVcCarName()
	{
		return vcCarName;
	}
	
	public void setVcCarName( String vcCarName )
	{
		this.vcCarName = vcCarName;
	}
	
	@Column( name = "N_FIRE" )
	public Integer getNFire()
	{
		return NFire;
	}
	
	public void setNFire( Integer nFire )
	{
		NFire = nFire;
	}
	
	@Column( name = "VC_CUST_ORDERNO" )
	public String getVcCustOrderno()
	{
		return vcCustOrderno;
	}
	
	public void setVcCustOrderno( String vcCustOrderno )
	{
		this.vcCustOrderno = vcCustOrderno;
	}
	
	@Column( name = "N_CUST_ORDER" )
	public String getNCustOrder()
	{
		return NCustOrder;
	}
	
	public void setNCustOrder( String nCustOrder )
	{
		NCustOrder = nCustOrder;
	}
	
	@Column( name = "N_END" )
	public Integer getNEnd()
	{
		return NEnd;
	}
	
	public void setNEnd( Integer nEnd )
	{
		this.NEnd = nEnd;
	}
	
}