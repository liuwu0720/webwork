package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * 结算应收对象 TArorder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ARORDER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TArorder implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 7234738077554262355L;
	@ApiParam( value = "ID" )
	private Integer id;
	@ApiParam( value = "订单表ID" )
	private Integer IOrder;
	@ApiParam( value = "起始地城市ID" )
	private Integer IStart;
	@ApiParam( value = "起始地城市" )
	private String vcStartCity;
	@ApiParam( value = "目的地城市ID" )
	private Integer IDest;
	@ApiParam( value = "目的地城市" )
	private String vcDestCity;
	@ApiParam( value = "车型ID" )
	private Integer ICarStyle;
	@ApiParam( value = "车型" )
	private String vcCarStyle;
	@ApiParam( value = "应收单价" )
	private Float NPrice;
	@ApiParam( value = "应收公里" )
	private Float NArkilometer;
	@ApiParam( value = "数量" )
	private Integer NQty;
	@ApiParam( value = "最初起运时间" )
	private Date dtShip;
	@ApiParam( value = "最后抵达时间" )
	private Date dtCome;
	@ApiParam( value = "审核时间" )
	private Date dtAudit;
	@ApiParam( value = "是否审核 (0 结算已审核 1结算未审核)" )
	private Integer Naudit;// 是否审核 (0 结算已审核 1结算未审核)
	@ApiParam( value = "审核人" )
	private String vcAuditName;
	@ApiParam( value = "审核人ID" )
	private Integer IAuditUser;
	@ApiParam( value = "是否生成对帐单(0:未入帐，1已入帐)" )
	private Integer NCreateBill;// 是否生成对帐单(0:未入帐，1已入帐)
	@ApiParam( value = "帐单时间" )
	private Date dtCreateBill;
	@ApiParam( value = "帐单人id" )
	private Integer IBillUser;
	@ApiParam( value = "帐单号" )
	private String vcBillNo;
	@ApiParam( value = "是否对帐(0:未对账，1已对帐)" )
	private Integer NBill;// 是否对帐(0:未对账，1已对帐)
	@ApiParam( value = "对帐时间" )
	private Date dtBill;
	@ApiParam( value = "对帐人" )
	private Integer ICheckUser;
	@ApiParam( value = "是否有效（0有效，1无效）" )
	private Integer NEnable;
	@ApiParam( value = "发运主表id" )
	private Integer IShiphead;
	@ApiParam( value = "发运明细id" )
	private Integer IShipline;
	@ApiParam( value = "发运明细总价" )
	private Float NTotalPrice;
	@ApiParam( value = "支付方式(0 现金 1客户)" )
	private Integer nPaytype;// 支付方式(0 现金 1客户)
	@ApiParam( value = "分供方编号" )
	private String vcSubNo;// 分供方编号
	@ApiParam( value = "客户运单号" )
	private String vcCustomerNo;// 客户运单号
	@ApiParam( value = "调度指令号" )
	private String vcShipNo;// 调度指令号
	
	// Constructors
	
	/** default constructor */
	public TArorder()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "ARORDERID" , sequenceName = "S_ARORDER" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "ARORDERID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_ORDER" , precision = 0 )
	public Integer getIOrder()
	{
		return this.IOrder;
	}
	
	public void setIOrder( Integer IOrder )
	{
		this.IOrder = IOrder;
	}
	
	@Column( name = "I_START_ID" , precision = 0 )
	public Integer getIStart()
	{
		return this.IStart;
	}
	
	public void setIStart( Integer IStart )
	{
		this.IStart = IStart;
	}
	
	@Column( name = "VC_START_CITY" , length = 100 )
	public String getVcStartCity()
	{
		return this.vcStartCity;
	}
	
	public void setVcStartCity( String vcStartCity )
	{
		this.vcStartCity = vcStartCity;
	}
	
	@Column( name = "I_END_ID" , precision = 0 )
	public Integer getIDest()
	{
		return this.IDest;
	}
	
	public void setIDest( Integer IDest )
	{
		this.IDest = IDest;
	}
	
	@Column( name = "N_AUDIT" , precision = 0 )
	public Integer getNaudit()
	{
		return Naudit;
	}
	
	public void setNaudit( Integer naudit )
	{
		Naudit = naudit;
	}
	
	@Column( name = "VC_DEST_CITY" , length = 100 )
	public String getVcDestCity()
	{
		return this.vcDestCity;
	}
	
	public void setVcDestCity( String vcDestCity )
	{
		this.vcDestCity = vcDestCity;
	}
	
	@Column( name = "I_CAR_STYLE" , precision = 0 )
	public Integer getICarStyle()
	{
		return this.ICarStyle;
	}
	
	public void setICarStyle( Integer ICarStyle )
	{
		this.ICarStyle = ICarStyle;
	}
	
	@Column( name = "VC_CAR_STYLE" , length = 50 )
	public String getVcCarStyle()
	{
		return this.vcCarStyle;
	}
	
	public void setVcCarStyle( String vcCarStyle )
	{
		this.vcCarStyle = vcCarStyle;
	}
	
	@Column( name = "N_PRICE" , precision = 0 )
	public Float getNPrice()
	{
		return this.NPrice;
	}
	
	public void setNPrice( Float NPrice )
	{
		this.NPrice = NPrice;
	}
	
	@Column( name = "N_ARKILOMETER" , precision = 0 )
	public Float getNArkilometer()
	{
		return this.NArkilometer;
	}
	
	public void setNArkilometer( Float NArkilometer )
	{
		this.NArkilometer = NArkilometer;
	}
	
	@Column( name = "N_QTY" , precision = 0 )
	public Integer getNQty()
	{
		return this.NQty;
	}
	
	public void setNQty( Integer NQty )
	{
		this.NQty = NQty;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_SHIP" , length = 7 )
	public Date getDtShip()
	{
		return this.dtShip;
	}
	
	public void setDtShip( Date dtShip )
	{
		this.dtShip = dtShip;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_ARRIVED" , length = 7 )
	public Date getDtCome()
	{
		return this.dtCome;
	}
	
	public void setDtCome( Date dtCome )
	{
		this.dtCome = dtCome;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_AUDIT" , length = 7 )
	public Date getDtAudit()
	{
		return this.dtAudit;
	}
	
	public void setDtAudit( Date dtAudit )
	{
		this.dtAudit = dtAudit;
	}
	
	@Column( name = "VC_AUDIT_NAME" , length = 20 )
	public String getVcAuditName()
	{
		return this.vcAuditName;
	}
	
	public void setVcAuditName( String vcAuditName )
	{
		this.vcAuditName = vcAuditName;
	}
	
	@Column( name = "I_AUDIT_USER" , precision = 0 )
	public Integer getIAuditUser()
	{
		return this.IAuditUser;
	}
	
	public void setIAuditUser( Integer IAuditUser )
	{
		this.IAuditUser = IAuditUser;
	}
	
	@Column( name = "N_CREATE_BILL" , precision = 0 )
	public Integer getNCreateBill()
	{
		return this.NCreateBill;
	}
	
	public void setNCreateBill( Integer NCreateBill )
	{
		this.NCreateBill = NCreateBill;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_CREATE_BILL" , length = 7 )
	public Date getDtCreateBill()
	{
		return this.dtCreateBill;
	}
	
	public void setDtCreateBill( Date dtCreateBill )
	{
		this.dtCreateBill = dtCreateBill;
	}
	
	@Column( name = "I_BILL_USER" , precision = 0 )
	public Integer getIBillUser()
	{
		return this.IBillUser;
	}
	
	public void setIBillUser( Integer IBillUser )
	{
		this.IBillUser = IBillUser;
	}
	

	@Column( name = "VC_BILL_NO" , length = 50 )
	public String getVcBillNo()
	{
		return vcBillNo;
	}
	
	public void setVcBillNo( String vcBillNo )
	{
		this.vcBillNo = vcBillNo;
	}

	@Column( name = "N_BILL" , precision = 0 )
	public Integer getNBill()
	{
		return this.NBill;
	}
	
	public void setNBill( Integer NBill )
	{
		this.NBill = NBill;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_BILL" , length = 7 )
	public Date getDtBill()
	{
		return this.dtBill;
	}
	
	public void setDtBill( Date dtBill )
	{
		this.dtBill = dtBill;
	}
	
	@Column( name = "I_CHECK_USER" , precision = 0 )
	public Integer getICheckUser()
	{
		return this.ICheckUser;
	}
	
	public void setICheckUser( Integer ICheckUser )
	{
		this.ICheckUser = ICheckUser;
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
	
	@Column( name = "I_SHIPHEAD" , precision = 0 )
	public Integer getIShiphead()
	{
		return this.IShiphead;
	}
	
	public void setIShiphead( Integer IShiphead )
	{
		this.IShiphead = IShiphead;
	}
	
	@Column( name = "I_SHIPLINE" , precision = 0 )
	public Integer getIShipline()
	{
		return IShipline;
	}
	
	public void setIShipline( Integer iShipline )
	{
		IShipline = iShipline;
	}
	
	@Column( name = "N_TOTAL_PRICE" , precision = 0 )
	public Float getNTotalPrice()
	{
		return NTotalPrice;
	}
	
	public void setNTotalPrice( Float nTotalPrice )
	{
		NTotalPrice = nTotalPrice;
	}
	
	@Column( name = "N_PAYTYPE" , precision = 0 )
	public Integer getnPaytype()
	{
		return nPaytype;
	}
	
	public void setnPaytype( Integer nPaytype )
	{
		this.nPaytype = nPaytype;
	}
	
	@Column( name = "VC_SUBNO" , precision = 0 )
	public String getVcSubNo()
	{
		return vcSubNo;
	}
	
	public void setVcSubNo( String vcSubNo )
	{
		this.vcSubNo = vcSubNo;
	}
	
	@Column( name = "VC_CUSTNO" , precision = 0 )
	public String getVcCustomerNo()
	{
		return vcCustomerNo;
	}
	
	public void setVcCustomerNo( String vcCustomerNo )
	{
		this.vcCustomerNo = vcCustomerNo;
	}
	
	@Column( name = "VC_SHIPNO" )
	public String getVcShipNo()
	{
		return vcShipNo;
	}
	
	public void setVcShipNo( String vcShipNo )
	{
		this.vcShipNo = vcShipNo;
	}
	
}