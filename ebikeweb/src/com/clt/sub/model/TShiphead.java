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
 * 发运主表 TShiphead entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_SHIPHEAD" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TShiphead implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * ITruckId
	 */
	private static final long serialVersionUID = - 4296323806701738052L;
	private Integer id;
	private Integer ITruckId;
	private String vcShipno; // 发运指令号
	private Date dtCreate; // 生成指令时间
	private Integer NShipType;// 发运指令类型(0:重载、1空载)
	private Integer NEnable;// 是否有效（0有效，1无效）
	private String vcSubno;// 所属分供方编号
	private Integer NArorder;// 该指令是否结算应收 0已 1未
	private Integer IArorderUser;// 指令 结算确认 确认人
	private Date dtArorder;// 指令 结算确认时间
	private String vcTruckName;// 运输车名称
	private Integer nSure;// 司机是否确认接单
	private Date dtSure;// 确认时间
	private String vcSure;// 确认备注
	private Date dtPick;// 预计提货时间
	private String vcSureName;// 确认接单人
	private String vcLongitudeShip;// 确认发运的经度
	private String vcLatitudeShip;// 确认发运的纬度
	private String vcShipPlace;// 确认发运地址
	private String vcShipUser;// 确认发运人
	private Date dtShipTime;// 确认发运时间
	private String vcDriverId;// 拖车的主驾及副驾司机ID,以，相连
	private Integer nCurrentStatus;// 当前状态0：配载；3：接单;4：提货; 5 入场 10 装车 15 发运 18:运抵
								   // 20 回单;30:结算
	
	// Constructors
	
	/** default constructor */
	public TShiphead()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TShipheadID" , sequenceName = "S_SHIPHEAD" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TShipheadID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_TRUCK_ID" , precision = 22 , scale = 0 )
	public Integer getITruckId()
	{
		return this.ITruckId;
	}
	
	public void setITruckId( Integer TTruckDriver )
	{
		this.ITruckId = TTruckDriver;
	}
	
	@Column( name = "VC_SHIPNO" , length = 30 )
	public String getVcShipno()
	{
		return this.vcShipno;
	}
	
	public void setVcShipno( String vcShipno )
	{
		this.vcShipno = vcShipno;
	}
	
	@Column( name = "DT_CREATE" , length = 7 )
	public Date getDtCreate()
	{
		return this.dtCreate;
	}
	
	public void setDtCreate( Date dtCreate )
	{
		this.dtCreate = dtCreate;
	}
	
	@Column( name = "N_SHIP_TYPE" , precision = 22 , scale = 0 )
	public Integer getNShipType()
	{
		return this.NShipType;
	}
	
	public void setNShipType( Integer NShipType )
	{
		this.NShipType = NShipType;
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
	
	@Column( name = "N_Arorder" , precision = 22 , scale = 0 )
	public Integer getNArorder()
	{
		return NArorder;
	}
	
	public void setNArorder( Integer nArorder )
	{
		NArorder = nArorder;
	}
	
	@Column( name = "I_Arorder_USER" , precision = 22 , scale = 0 )
	public Integer getIArorderUser()
	{
		return IArorderUser;
	}
	
	public void setIArorderUser( Integer iArorderUser )
	{
		IArorderUser = iArorderUser;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd " )
	@Column( name = "DT_Arorder" , length = 7 )
	public Date getDtArorder()
	{
		return dtArorder;
	}
	
	public void setDtArorder( Date dtArorder )
	{
		this.dtArorder = dtArorder;
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
	
	@Column( name = "N_SURE" )
	public Integer getnSure()
	{
		return nSure;
	}
	
	public void setnSure( Integer nSure )
	{
		this.nSure = nSure;
	}
	
	@Column( name = "DT_SURE" )
	public Date getDtSure()
	{
		return dtSure;
	}
	
	public void setDtSure( Date dtSure )
	{
		this.dtSure = dtSure;
	}
	
	@Column( name = "VC_SURE" , length = 200 )
	public String getVcSure()
	{
		return vcSure;
	}
	
	public void setVcSure( String vcSure )
	{
		this.vcSure = vcSure;
	}
	
	@Column( name = "VC_SURENAME" , length = 30 )
	public String getVcSureName()
	{
		return vcSureName;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_PICK" )
	public Date getDtPick()
	{
		return dtPick;
	}
	
	public void setDtPick( Date dtPick )
	{
		this.dtPick = dtPick;
	}
	
	public void setVcSureName( String vcSureName )
	{
		this.vcSureName = vcSureName;
	}
	
	@Column( name = "VC_LONGITUDESHIP" , length = 30 )
	public String getVcLongitudeShip()
	{
		return vcLongitudeShip;
	}
	
	public void setVcLongitudeShip( String vcLongitudeShip )
	{
		this.vcLongitudeShip = vcLongitudeShip;
	}
	
	@Column( name = "VC_LATITUDESHIP" , length = 30 )
	public String getVcLatitudeShip()
	{
		return vcLatitudeShip;
	}
	
	public void setVcLatitudeShip( String vcLatitudeShip )
	{
		this.vcLatitudeShip = vcLatitudeShip;
	}
	
	@Column( name = "VC_SHIPPLACE" , length = 200 )
	public String getVcShipPlace()
	{
		return vcShipPlace;
	}
	
	public void setVcShipPlace( String vcShipPlace )
	{
		this.vcShipPlace = vcShipPlace;
	}
	
	@Column( name = "VC_SHIPUSER" , length = 30 )
	public String getVcShipUser()
	{
		return vcShipUser;
	}
	
	public void setVcShipUser( String vcShipUser )
	{
		this.vcShipUser = vcShipUser;
	}
	
	@Column( name = "DT_SHIPTIME" )
	public Date getDtShipTime()
	{
		return dtShipTime;
	}
	
	public void setDtShipTime( Date dtShipTime )
	{
		this.dtShipTime = dtShipTime;
	}
	
	@Column( name = "VC_DRIVERID" , length = 30 )
	public String getVcDriverId()
	{
		return vcDriverId;
	}
	
	public void setVcDriverId( String vcDriverId )
	{
		this.vcDriverId = vcDriverId;
	}
	
	@Column( name = "N_CURRENT_STATUS" )
	public Integer getnCurrentStatus()
	{
		return nCurrentStatus;
	}
	
	public void setnCurrentStatus( Integer nCurrentStatus )
	{
		this.nCurrentStatus = nCurrentStatus;
	}
	
}