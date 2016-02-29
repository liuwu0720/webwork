package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * 车辆 表 TTruckDriver entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_TRUCK_DRIVER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TTruckDriver implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = - 9079604727127556920L;
	@ApiParam( value = "主键" )
	private Integer id;
	@ApiParam( value = "车牌号" )
	private String vcCarNo;
	@ApiParam( value = "是否有效（0有效，1无效）" )
	private Integer NEnable;
	@ApiParam( value = "所属分供方编号" )
	private String vcSubno;
	@ApiParam( value = "车辆是否可用（0有效，1无效）  调度指令回单确认释放车辆" )
	private Integer NStatus;
	@ApiParam( value = "运输车类型" )
	private Integer ITruckTypeID;
	@ApiParam( value = "车长" )
	@Max( value = 99 , message = "车长不能超过99米" )
	private Float NLength;
	@ApiParam( value = "车宽" )
	@Max( value = 99 , message = "车宽不能超过99米" )
	private Float NWidth;
	@ApiParam( value = "车高" )
	@Max( value = 99 , message = "车高不能超过99米" )
	private Float NHeight;
	@ApiParam( value = "车自重" )
	private Float NSelfWeight;
	@ApiParam( value = "车载重" )
	private Float NLoanWeight;
	@ApiParam( value = "最新的指令号" )
	private String vcShipNo;
	@ApiParam( value = "车辆行驶证号码" )
	private String vcDriverNo;
	
	// Constructors
	
	/** default constructor */
	public TTruckDriver()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TTruckDriverID" , sequenceName = "S_TRUCK_DRIVER" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TTruckDriverID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_CAR_NO" , length = 20 )
	public String getVcCarNo()
	{
		return this.vcCarNo;
	}
	
	public void setVcCarNo( String vcCarNo )
	{
		this.vcCarNo = vcCarNo;
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
	
	@Column( name = "N_STATUS" , precision = 22 , scale = 0 )
	public Integer getNStatus()
	{
		return NStatus;
	}
	
	public void setNStatus( Integer nStatus )
	{
		NStatus = nStatus;
	}
	
	@Column( name = "N_LENGTH" , precision = 22 , scale = 0 )
	public Float getNLength()
	{
		return NLength;
	}
	
	public void setNLength( Float nLength )
	{
		NLength = nLength;
	}
	
	@Column( name = "N_WIDTH" , precision = 22 , scale = 0 )
	public Float getNWidth()
	{
		return NWidth;
	}
	
	public void setNWidth( Float nWidth )
	{
		NWidth = nWidth;
	}
	
	@Column( name = "N_HEIGHT" , precision = 22 , scale = 0 )
	public Float getNHeight()
	{
		return NHeight;
	}
	
	public void setNHeight( Float nHeight )
	{
		NHeight = nHeight;
	}
	
	@Column( name = "N_SELF_WEIGHT" , precision = 22 , scale = 0 )
	public Float getNSelfWeight()
	{
		return NSelfWeight;
	}
	
	public void setNSelfWeight( Float nSelfWeight )
	{
		NSelfWeight = nSelfWeight;
	}
	
	@Column( name = "N_LOAN_WEIGHT" , precision = 22 , scale = 0 )
	public Float getNLoanWeight()
	{
		return NLoanWeight;
	}
	
	public void setNLoanWeight( Float nLoanWeight )
	{
		NLoanWeight = nLoanWeight;
	}
	
	@Column( name = "I_TRUCK_TYPE" , precision = 22 , scale = 0 )
	public Integer getITruckTypeID()
	{
		return ITruckTypeID;
	}
	
	public void setITruckTypeID( Integer iTruckTypeID )
	{
		ITruckTypeID = iTruckTypeID;
	}
	
	@Column( name = "VC_SHIPNO" , length = 50 )
	public String getVcShipNo()
	{
		return vcShipNo;
	}
	
	public void setVcShipNo( String vcShipNo )
	{
		this.vcShipNo = vcShipNo;
	}
	
	@Column( name = "VC_DRIVERNO" , length = 50 )
	public String getVcDriverNo()
	{
		return vcDriverNo;
	}
	
	public void setVcDriverNo( String vcDriverNo )
	{
		this.vcDriverNo = vcDriverNo;
	}
	
}