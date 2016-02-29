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
 * 发运明细表 TShipline entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_SHIPLINE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TShipline implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 4931773817520969220L;
	private Integer id; // 主键
	private Integer IOrderId;// 订单id
	private Integer IShiphead;// 发运主表id
	private Integer NEnable;// 是否有效（0有效，1无效）
	private String vcStartCity;// 开始城市
	private String vcDestCity;// 目的城市
	private Date dtAdd;// 添加时间
	private Integer nShipQty;// 装运数量
	private Integer NQty;// 应收结算数量
	private Float NApkilometer;// 应收公里
	
	private Integer NCurrentStatus;// 当前状态0：配载；3：接单; 5 入场 10 装车 15 发运 18:运抵 20
								   // 回单
	
	private Integer Narorder;// 该指令是否结算应收 0已 1未
	private Integer nArriveQty;// 抵达数量
	private Integer nPickQty;// 提货数量

	// Constructors
	
	/** default constructor */
	public TShipline()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TShiplineID" , sequenceName = "S_SHIPLINE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TShiplineID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_ORDER_ID" , precision = 22 , scale = 0 )
	public Integer getIOrderId()
	{
		return this.IOrderId;
	}
	
	public void setIOrderId( Integer TOrder )
	{
		this.IOrderId = TOrder;
	}
	
	@Column( name = "I_SHIPHEAD" , precision = 22 , scale = 0 )
	public Integer getIShiphead()
	{
		return this.IShiphead;
	}
	
	public void setIShiphead( Integer TShiphead )
	{
		this.IShiphead = TShiphead;
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
	
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "N_SHIP_QTY" , precision = 22 , scale = 0 )
	public Integer getnShipQty()
	{
		return this.nShipQty;
	}
	
	public void setnShipQty( Integer nShipQty )
	{
		this.nShipQty = nShipQty;
	}
	
	@Column( name = "N_QTY" , precision = 22 , scale = 0 )
	public Integer getNQty()
	{
		return this.NQty;
	}
	
	public void setNQty( Integer NQty )
	{
		this.NQty = NQty;
	}
	
	@Column( name = "N_ARKILOMETER" , precision = 22 , scale = 0 )
	public Float getNApkilometer()
	{
		return this.NApkilometer;
	}
	
	public void setNApkilometer( Float NApkilometer )
	{
		this.NApkilometer = NApkilometer;
	}
	

	@Column( name = "N_CURRENT_STATUS" , precision = 22 , scale = 0 )
	public Integer getNCurrentStatus()
	{
		return NCurrentStatus;
	}
	
	public void setNCurrentStatus( Integer nCurrentStatus )
	{
		NCurrentStatus = nCurrentStatus;
	}
	
	
	
	@Column( name = "N_ARORDER" )
	public Integer getNarorder()
	{
		return Narorder;
	}
	
	public void setNarorder( Integer narorder )
	{
		Narorder = narorder;
	}
	
	@Column( name = "N_ARRIVEQTY" )
	public Integer getnArriveQty()
	{
		return nArriveQty;
	}
	
	public void setnArriveQty( Integer nArriveQty )
	{
		this.nArriveQty = nArriveQty;
	}
	
	@Column( name = "N_PICK_QTY" )
	public Integer getnPickQty()
	{
		return nPickQty;
	}
	
	public void setnPickQty( Integer nPickQty )
	{
		this.nPickQty = nPickQty;
	}
	
	
	
}