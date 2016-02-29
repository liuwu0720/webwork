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

import org.springframework.format.annotation.DateTimeFormat;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * 订单表 TOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ORDER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TOrder implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * IUser
	 */
	private static final long serialVersionUID = - 524356640885143166L;
	@ApiParam( value = "订单ID" )
	private Integer id;
	@ApiParam( value = "商品车ID" )
	private Integer ICarStyle;// 商品车ID
	@ApiParam( value = "商品车车名" )
	private String vcCarName;// 商品车车名
	@ApiParam( value = "录入用户id" )
	private Integer IUser;// 录入用户id
	@ApiParam( value = " 装货地址" )
	private String vcLoadAddress;// 装货地址
	@ApiParam( value = "联系人" )
	private String vcLoadContact;// 联系人
	@ApiParam( value = "联系电话" )
	private String vcLoadTel;// 联系电话
	@ApiParam( value = "要求发运日yyyy-MM-dd" )
	private Date dtShip;// 要求发运日
	@ApiParam( value = "要求到货日yyyy-MM-dd" )
	private Date dtArrive;// 要求到货日
	@ApiParam( value = "起运城市id" )
	private Integer IStartId;// 起运城市id
	@ApiParam( value = "目的城市id" )
	private Integer IEndId;// 目的城市id
	@ApiParam( value = "起运城市" )
	private String vcStartCity;// 起运城市
	@ApiParam( value = "目的城市" )
	private String vcDestCity;// 目的城市
	@ApiParam( value = "收货地址" )
	private String vcReceiveAddress;// 收货地址
	@ApiParam( value = "收货联系人" )
	private String vcReceiveContact;// 收货联系人
	@ApiParam( value = "收货人电话" )
	private String vcReceiveTel;// 收货人电话
	@ApiParam( value = "数量" )
	private Integer NTotalCar;// 数量
	@ApiParam( value = "发运数量" )
	private Integer NShipedQty;// 发运数量
	@ApiParam( value = "状态（0为有效，1为无效）" )
	private Integer NEnable;// 状态（0为有效，1为无效）
	@ApiParam( value = "所属分供方编号" )
	private String vcSubno;// 所属分供方编号
	@ApiParam( value = "订单号" )
	private String vcOrderno;// 订单号
	@ApiParam( value = "支付方式(0 现金 1客户)" )
	private Integer NPayType;// 支付方式(0 现金 1客户)
	@ApiParam( value = "订单总价" )
	private Float NTotalPrice;// 订单总价
	@ApiParam( value = " 客户表ID" )
	private Integer ICustomerId;// 客户表ID
	@ApiParam( value = "客户订单号(客户自己录入)" )
	private String vcCustOrderNo;// 客户订单号(客户自己录入)
	@ApiParam( value = "创建时间" )
	private Date dtCreateDate;// 创建时间
	@ApiParam( value = "应收单价(如果是现金支付此字段为空)" )
	private Float NCost;// 应收单价(如果是现金支付此字段为空)
	@ApiParam( value = " 是否配载（0：未配载[默认]；1已配载）" )
	private Integer nLoad;// 是否配载（0：未配载[默认]；1已配载）
	@ApiParam( value = "收货地址经度" )
	private String vcLong;// 收货地址经度
	@ApiParam( value = "收货地址纬度" )
	private String vcLat; // 收货地址纬度
	@ApiParam( value = "出发地经度" )
	private String vcLongStart;// 出发地经度
	@ApiParam( value = "出发地纬度" )
	private String vcLatStart;// 出发地纬度
	@ApiParam( value = "调度指令号" )
	private String vcShipNo;// 调度指令号
	@ApiParam( value = "拖车ID" )
	private Integer iTruckId;// 拖车ID
	@ApiParam( value = "4S店ID" )
	private Integer iStoreId;// 4S店ID
	@ApiParam( value = "4S店名称" )
	private String vcStoreName;// 4S店名称
	@ApiParam( value = "是否评价（0为未评价，1为已评价）" )
	private Integer nEvalution;// 是否评价（0为未评价，1为已评价）
	@ApiParam( value = "是否是抢单转换的（0：为是，1为不是）" )
	private Integer NProduct;// 是否是抢单转换的（0：为是，1为不是）
	@ApiParam( value = "支付比例" )
	private Float nRatio;// 支付比例
	@ApiParam( value = "支付周期（1:N+1（默认） 2:N+2 3:N+3）" )
	private Integer nPayCtyle;
	private Integer ICarStyleId;// 抢单转订单对应的抢单车型ID
	@ApiParam( value = "特殊要求" )
	private String vcNote;
	
	// Constructors
	
	/** default constructor */
	public TOrder()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TOrderID" , sequenceName = "S_ORDER" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TOrderID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_CAR_STYLE" , precision = 22 , scale = 0 )
	public Integer getICarStyle()
	{
		return this.ICarStyle;
	}
	
	public void setICarStyle( Integer TSubCarStyle )
	{
		this.ICarStyle = TSubCarStyle;
	}
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getIUser()
	{
		return this.IUser;
	}
	
	public void setIUser( Integer TUser )
	{
		this.IUser = TUser;
	}
	
	@Column( name = "VC_ORDERNO" , length = 150 )
	public String getVcOrderno()
	{
		return vcOrderno;
	}
	
	public void setVcOrderno( String vcOrderno )
	{
		this.vcOrderno = vcOrderno;
	}
	
	@Column( name = "VC_LOAD_ADDRESS" , length = 150 )
	public String getVcLoadAddress()
	{
		return this.vcLoadAddress;
	}
	
	public void setVcLoadAddress( String vcLoadAddress )
	{
		this.vcLoadAddress = vcLoadAddress;
	}
	
	@Column( name = "VC_LOAD_CONTACT" , length = 20 )
	public String getVcLoadContact()
	{
		return this.vcLoadContact;
	}
	
	public void setVcLoadContact( String vcLoadContact )
	{
		this.vcLoadContact = vcLoadContact;
	}
	
	@Column( name = "VC_LOAD_TEL" , length = 20 )
	public String getVcLoadTel()
	{
		return this.vcLoadTel;
	}
	
	public void setVcLoadTel( String vcLoadTel )
	{
		this.vcLoadTel = vcLoadTel;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_SHIP" , length = 7 )
	public Date getDtShip()
	{
		return this.dtShip;
	}
	
	public void setDtShip( Date dtShip )
	{
		this.dtShip = dtShip;
	}
	
	@Column( name = "I_START_ID" , precision = 22 , scale = 0 )
	public Integer getIStartId()
	{
		return IStartId;
	}
	
	public void setIStartId( Integer iStartId )
	{
		IStartId = iStartId;
	}
	
	@Column( name = "I_END_ID" , precision = 22 , scale = 0 )
	public Integer getIEndId()
	{
		return IEndId;
	}
	
	public void setIEndId( Integer iEndId )
	{
		IEndId = iEndId;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_ARRIVE" , length = 7 )
	public Date getDtArrive()
	{
		return this.dtArrive;
	}
	
	public void setDtArrive( Date dtArrive )
	{
		this.dtArrive = dtArrive;
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
	
	@Column( name = "VC_DEST_CITY" , length = 50 )
	public String getVcDestCity()
	{
		return this.vcDestCity;
	}
	
	public void setVcDestCity( String vcDestCity )
	{
		this.vcDestCity = vcDestCity;
	}
	
	@Column( name = "VC_RECEIVE_ADDRESS" , length = 150 )
	public String getVcReceiveAddress()
	{
		return this.vcReceiveAddress;
	}
	
	public void setVcReceiveAddress( String vcReceiveAddress )
	{
		this.vcReceiveAddress = vcReceiveAddress;
	}
	
	@Column( name = "VC_RECEIVE_CONTACT" , length = 20 )
	public String getVcReceiveContact()
	{
		return this.vcReceiveContact;
	}
	
	public void setVcReceiveContact( String vcReceiveContact )
	{
		this.vcReceiveContact = vcReceiveContact;
	}
	
	@Column( name = "VC_RECEIVE_TEL" , length = 20 )
	public String getVcReceiveTel()
	{
		return this.vcReceiveTel;
	}
	
	public void setVcReceiveTel( String vcReceiveTel )
	{
		this.vcReceiveTel = vcReceiveTel;
	}
	
	@Column( name = "N_TOTAL_CAR" , precision = 22 , scale = 0 )
	public Integer getNTotalCar()
	{
		return this.NTotalCar;
	}
	
	public void setNTotalCar( Integer NTotalCar )
	{
		this.NTotalCar = NTotalCar;
	}
	
	@Column( name = "N_ShipedQty" , precision = 22 , scale = 0 )
	public Integer getNShipedQty()
	{
		return NShipedQty;
	}
	
	public void setNShipedQty( Integer nShipedQty )
	{
		NShipedQty = nShipedQty;
	}
	
	@Column( name = "N_ENABLE" , precision = 22 , scale = 0 , columnDefinition = "INT" )
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
	
	@Column( name = "N_PAYTYPE" , precision = 22 , scale = 0 )
	public Integer getNPayType()
	{
		return NPayType;
	}
	
	public void setNPayType( Integer nPayType )
	{
		NPayType = nPayType;
	}
	
	@Column( name = "N_TOTAL_PRICE" , precision = 22 , scale = 0 )
	public Float getNTotalPrice()
	{
		return NTotalPrice;
	}
	
	public void setNTotalPrice( Float nTotalPrice )
	{
		NTotalPrice = nTotalPrice;
	}
	
	@Column( name = "I_CUSTOMER_ID" , precision = 22 , scale = 0 )
	public Integer getICustomerId()
	{
		return ICustomerId;
	}
	
	public void setICustomerId( Integer iCustomerId )
	{
		ICustomerId = iCustomerId;
	}
	
	@Column( name = "VC_CUST_ORDERNO" )
	public String getVcCustOrderNo()
	{
		return vcCustOrderNo;
	}
	
	public void setVcCustOrderNo( String vcCustOrderNo )
	{
		this.vcCustOrderNo = vcCustOrderNo;
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
	
	@Column( name = "N_COST" )
	public Float getNCost()
	{
		return NCost;
	}
	
	public void setNCost( Float nCost )
	{
		NCost = nCost;
	}
	
	@Column( name = "DT_CREATEDATE" )
	public Date getDtCreateDate()
	{
		return dtCreateDate;
	}
	
	public void setDtCreateDate( Date dtCreateDate )
	{
		this.dtCreateDate = dtCreateDate;
	}
	
	@Column( name = "N_LOADING" , precision = 22 , scale = 0 )
	public Integer getnLoad()
	{
		return nLoad;
	}
	
	public void setnLoad( Integer nLoad )
	{
		this.nLoad = nLoad;
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
	
	@Column( name = "VC_LONG_START" )
	public String getVcLongStart()
	{
		return vcLongStart;
	}
	
	public void setVcLongStart( String vcLongStart )
	{
		this.vcLongStart = vcLongStart;
	}
	
	@Column( name = "VC_LAT_START" )
	public String getVcLatStart()
	{
		return vcLatStart;
	}
	
	public void setVcLatStart( String vcLatStart )
	{
		this.vcLatStart = vcLatStart;
	}
	
	@Transient
	public String getVcShipNo()
	{
		return vcShipNo;
	}
	
	public void setVcShipNo( String vcShipNo )
	{
		this.vcShipNo = vcShipNo;
	}
	
	@Transient
	public Integer getiTruckId()
	{
		return iTruckId;
	}
	
	public void setiTruckId( Integer iTruckId )
	{
		this.iTruckId = iTruckId;
	}
	
	@Column( name = "I_STOREID" )
	public Integer getiStoreId()
	{
		return iStoreId;
	}
	
	public void setiStoreId( Integer iStoreId )
	{
		this.iStoreId = iStoreId;
	}
	
	@Column( name = "VC_STORENAME" )
	public String getVcStoreName()
	{
		return vcStoreName;
	}
	
	public void setVcStoreName( String vcStoreName )
	{
		this.vcStoreName = vcStoreName;
	}
	
	@Column( name = "N_EVALUATION" )
	public Integer getnEvalution()
	{
		return nEvalution;
	}
	
	public void setnEvalution( Integer nEvalution )
	{
		this.nEvalution = nEvalution;
	}
	
	@Column( name = "N_PRODUCT" )
	public Integer getNProduct()
	{
		return NProduct;
	}
	
	public void setNProduct( Integer nProduct )
	{
		NProduct = nProduct;
	}
	
	@Column( name = "N_RATIO" )
	public Float getnRatio()
	{
		return nRatio;
	}
	
	public void setnRatio( Float nRatio )
	{
		this.nRatio = nRatio;
	}
	
	@Column( name = "N_PAYCYCLE" )
	public Integer getnPayCtyle()
	{
		return nPayCtyle;
	}
	
	public void setnPayCtyle( Integer nPayCtyle )
	{
		this.nPayCtyle = nPayCtyle;
	}
	
	@Column( name = "I_CARSTYLE_ID" , precision = 22 , scale = 0 )
	public Integer getICarStyleId()
	{
		return ICarStyleId;
	}
	
	public void setICarStyleId( Integer iCarStyleId )
	{
		ICarStyleId = iCarStyleId;
	}
	
	@Column( name = "VC_NOTE" )
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
}