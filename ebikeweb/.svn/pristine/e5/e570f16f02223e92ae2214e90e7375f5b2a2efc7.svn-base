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
 * TDetour entity.绕路申请
 */
@Entity
@Table( name = "T_DETOUR" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDetour implements java.io.Serializable
{

	private static final long serialVersionUID = 7066914496802647573L;
	@ApiParam( value = "主键" )
	private Integer id;// id
	@ApiParam( value = " 是否有效（0有效，1无效）" )
	private Integer nEnable;// 是否有效（0有效，1无效）
	@ApiParam( value = " 申请司机id" )
	private Integer IUser;// 申请人id
	@ApiParam( value = "申请司机姓名" )
	private String vcUserName;// 申请人姓名
	@ApiParam( value = "申请时间" )
	private Date dtApplay;// 申请时间
	@ApiParam( value = "原路线" )
	private String vcOldRoute;// 原路线
	@ApiParam( value = "新路线" )
	private String vcNewRoute;// 新路线
	@ApiParam( value = "不可经地点" )
	private String vcNoPlace;// 不可经地点
	@ApiParam( value = "不可经地点的经度" )
	private String vcNoPlaceLongtide;// 不可经地点的经度
	@ApiParam( value = "不可经地点的纬度" )
	private String vcNoPlaceLatitude;// 不可经地点的纬度
	@ApiParam( value = "不可经地点的gps的hash值" )
	private String vcNoplaceHash;// 不可经地点的gps的hash值
	@ApiParam( value = " 绕路原因" )
	private String vcDetourCase;// 绕路原因
	@ApiParam( value = "申请地点名字" )
	private String vcApplaySite;// 申请地点名字
	@ApiParam( value = " 申请地点经度" )
	private String vcLongitude;// 申请地点经度
	@ApiParam( value = "申请地点纬度" )
	private String vcLatitude;// 申请地点纬度
	@ApiParam( value = "申请地点的hash值" )
	private String vcHash;// 申请地点的hash值
	@ApiParam( value = "调度指令号" )
	private String vcShipno;// 调度指令号
	@ApiParam( value = "车牌号" )
	private String vcCarNo;// 车牌号
	@ApiParam( value = "分供方编号" )
	private String vcSubno;// 分供方编号
	@ApiParam( value = "审批人id" )
	private Integer IApprove;// 审批人id
	@ApiParam( value = "审批人姓名" )
	private String vcApproveName;// 审批人姓名
	@ApiParam( value = "审批时间" )
	private Date dtApprove;// 审批时间
	@ApiParam( value = "审批结果(0,通过，1，未审批,2:未通过)" )
	private Integer nApproveResult;// 审批结果(0,通过，1，未审批,2:未通过)
	@ApiParam( value = "审批备注" )
	private String vcNote;// 审批备注
	@ApiParam( value = "绕路长度（审批人填写）" )
	private String vcDetourLength;// 绕路长度（审批人填写）
	@ApiParam( value = "申请人手机号" )
	private String vcUserPhone;// 申请人手机号
	// Constructors
	
	/** default constructor */
	public TDetour()
	{}

	/** minimal constructor */
	public TDetour( Integer id )
	{
		this.id = id;
	}
	
	@SequenceGenerator( name = "TDETOUR" , sequenceName = "s_detour" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDETOUR" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	

	@Column( name = "N_ENABLE" , precision = 22 , scale = 0 )
	public Integer getnEnable()
	{
		return nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
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
	
	@Temporal( TemporalType.DATE )
	@Column( name = "DT_APPLAY" , length = 7 )
	public Date getDtApplay()
	{
		return this.dtApplay;
	}
	
	public void setDtApplay( Date dtApplay )
	{
		this.dtApplay = dtApplay;
	}
	
	@Column( name = "VC_OLD_ROUTE" , length = 200 )
	public String getVcOldRoute()
	{
		return this.vcOldRoute;
	}
	
	public void setVcOldRoute( String vcOldRoute )
	{
		this.vcOldRoute = vcOldRoute;
	}
	
	@Column( name = "VC_NEW_ROUTE" , length = 200 )
	public String getVcNewRoute()
	{
		return this.vcNewRoute;
	}
	
	public void setVcNewRoute( String vcNewRoute )
	{
		this.vcNewRoute = vcNewRoute;
	}
	
	@Column( name = "VC_DETOUR_CASE" , length = 200 )
	public String getVcDetourCase()
	{
		return this.vcDetourCase;
	}
	
	public void setVcDetourCase( String vcDetourCase )
	{
		this.vcDetourCase = vcDetourCase;
	}
	
	@Column( name = "VC_APPLAY_SITE" , length = 100 )
	public String getVcApplaySite()
	{
		return this.vcApplaySite;
	}
	
	public void setVcApplaySite( String vcApplaySite )
	{
		this.vcApplaySite = vcApplaySite;
	}
	
	@Column( name = "VC_LONGITUDE" , length = 50 )
	public String getVcLongitude()
	{
		return this.vcLongitude;
	}
	
	public void setVcLongitude( String vcLongitude )
	{
		this.vcLongitude = vcLongitude;
	}
	
	@Column( name = "VC_LATITUDE" , length = 50 )
	public String getVcLatitude()
	{
		return this.vcLatitude;
	}
	
	public void setVcLatitude( String vcLatitude )
	{
		this.vcLatitude = vcLatitude;
	}
	
	@Column( name = "VC_SHIPNO" , length = 32 )
	public String getVcShipno()
	{
		return this.vcShipno;
	}
	
	public void setVcShipno( String vcShipno )
	{
		this.vcShipno = vcShipno;
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
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "I_APPROVE" , precision = 22 , scale = 0 )
	public Integer getIApprove()
	{
		return this.IApprove;
	}
	
	public void setIApprove( Integer IApprove )
	{
		this.IApprove = IApprove;
	}
	
	@Column( name = "VC_APPROVE_NAME" , length = 30 )
	public String getVcApproveName()
	{
		return this.vcApproveName;
	}
	
	public void setVcApproveName( String vcApproveName )
	{
		this.vcApproveName = vcApproveName;
	}
	
	@Temporal( TemporalType.DATE )
	@Column( name = "DT_APPROVE" , length = 7 )
	public Date getDtApprove()
	{
		return this.dtApprove;
	}
	
	public void setDtApprove( Date dtApprove )
	{
		this.dtApprove = dtApprove;
	}
	
	@Column( name = "N_APPROVE_RESULT" , precision = 22 , scale = 0 )
	public Integer getnApproveResult()
	{
		return nApproveResult;
	}
	
	public void setnApproveResult( Integer nApproveResult )
	{
		this.nApproveResult = nApproveResult;
	}
	
	@Column( name = "VC_NOTE" , length = 100 )
	public String getVcNote()
	{
		return this.vcNote;
	}
	

	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	@Column( name = "VC_DETOUR_LENGTH" , length = 50 )
	public String getVcDetourLength()
	{
		return this.vcDetourLength;
	}
	
	public void setVcDetourLength( String vcDetourLength )
	{
		this.vcDetourLength = vcDetourLength;
	}
	
	@Column( name = "VC_USERPHONE" , length = 30 )
	public String getVcUserPhone()
	{
		return vcUserPhone;
	}
	
	public void setVcUserPhone( String vcUserPhone )
	{
		this.vcUserPhone = vcUserPhone;
	}
	
	@Column( name = "VC_NOPLACE" , length = 300 )
	public String getVcNoPlace()
	{
		return vcNoPlace;
	}
	
	public void setVcNoPlace( String vcNoPlace )
	{
		this.vcNoPlace = vcNoPlace;
	}
	
	@Column( name = "VC_NOPLACE_LONGTIDE" , length = 300 )
	public String getVcNoPlaceLongtide()
	{
		return vcNoPlaceLongtide;
	}
	
	public void setVcNoPlaceLongtide( String vcNoPlaceLongtide )
	{
		this.vcNoPlaceLongtide = vcNoPlaceLongtide;
	}
	
	@Column( name = "VC_NOPLACE_LATITUDE" , length = 300 )
	public String getVcNoPlaceLatitude()
	{
		return vcNoPlaceLatitude;
	}
	
	public void setVcNoPlaceLatitude( String vcNoPlaceLatitude )
	{
		this.vcNoPlaceLatitude = vcNoPlaceLatitude;
	}
	
	@Column( name = "VC_NOPLACE_HASH" , length = 300 )
	public String getVcNoplaceHash()
	{
		return vcNoplaceHash;
	}
	
	public void setVcNoplaceHash( String vcNoplaceHash )
	{
		this.vcNoplaceHash = vcNoplaceHash;
	}
	
	@Column( name = "VC_HASH" , length = 300 )
	public String getVcHash()
	{
		return vcHash;
	}
	
	public void setVcHash( String vcHash )
	{
		this.vcHash = vcHash;
	}

}