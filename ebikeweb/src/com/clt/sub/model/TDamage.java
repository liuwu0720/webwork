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

import com.wordnik.swagger.annotations.ApiParam;

/**
 * TDamage entity. @author 质损申请
 */
@Entity
@Table( name = "T_DAMAGE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDamage implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = 8745066736736815696L;
	@ApiParam( value = "主键" )
	private Integer id;
	@ApiParam( value = "是否有效（0有效，1无效）" )
	private Integer nEnable;// 是否有效（0有效，1无效）
	@ApiParam( value = "申请司机id" )
	private Integer iUser;// 申请人id
	@ApiParam( value = "申请司机姓名" )
	private String vcUserName;// 申请人姓名
	@ApiParam( value = "申请时间" )
	private Date dtApplay;// 申请时间
	@ApiParam( value = "质损描述" )
	private String vcDamage;// 质损描述
	@ApiParam( value = "质损部位" )
	private String vcDamagePart;// 质损部位
	@ApiParam( value = "申请地点名字" )
	private String vcApplaySite;// 申请地点名字
	@ApiParam( value = "申请经度" )
	private String vcLongitude;// 申请经度
	@ApiParam( value = "申请纬度" )
	private String vcLatitude;// 申请纬度
	@ApiParam( value = "调度指令号" )
	private String vcShipno;// 调度指令号
	@ApiParam( value = "车牌号" )
	private String vcCarNo;// 车牌号
	@ApiParam( value = "分供方编号" )
	private String vcSubno;// 分供方编号
	@ApiParam( value = "审批人id" )
	private Integer iApprove;// 审批人id
	@ApiParam( value = "审批人姓名" )
	private String vcApproveName;// 审批人姓名
	@ApiParam( value = "审批时间" )
	private Date dtApprove;// 审批时间
	@ApiParam( value = "审批结果(0,通过，1，未审批,2:未通过)" )
	private Integer nApproveResult;// 审批结果(0,通过，1，未审批,2:未通过)
	@ApiParam( value = "审批备注" )
	private String vcNote;// 审批备注
	@ApiParam( value = "质损数量" )
	private Integer nAmount;// 质损数量
	@ApiParam( value = " 照片路径 (多个以','相连)" )
	private String vcPicpath;// 照片路径 (多个以','相连)
	@ApiParam( value = "司机手机号" )
	private String vcUserPhone;// 司机手机号
	@ApiParam( value = " 经纬度hash值" )
	private String vcHash;// 经纬度hash值
	
	// Constructors
	
	/** default constructor */
	public TDamage()
	{}
	
	/** minimal constructor */
	public TDamage( Integer id )
	{
		this.id = id;
	}
	
	@SequenceGenerator( name = "TDAMAGE" , sequenceName = "s_damage" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDAMAGE" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
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
	public Integer getiUser()
	{
		return iUser;
	}
	
	public void setiUser( Integer iUser )
	{
		this.iUser = iUser;
	}
	
	@Column( name = "VC_USER_NAME" , length = 30 )
	public String getVcUserName()
	{
		return vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_APPLAY" , length = 7 )
	public Date getDtApplay()
	{
		return this.dtApplay;
	}
	
	public void setDtApplay( Date dtApplay )
	{
		this.dtApplay = dtApplay;
	}
	
	@Column( name = "VC_DAMAGE" , length = 200 )
	public String getVcDamage()
	{
		return this.vcDamage;
	}
	
	public void setVcDamage( String vcDamage )
	{
		this.vcDamage = vcDamage;
	}
	
	@Column( name = "VC_APPLAY_SITE" , length = 300 )
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
	public Integer getiApprove()
	{
		return iApprove;
	}
	
	public void setiApprove( Integer iApprove )
	{
		this.iApprove = iApprove;
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
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
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
	
	@Column( name = "N_AMOUNT" , precision = 22 , scale = 0 )
	public Integer getnAmount()
	{
		return nAmount;
	}
	
	public void setnAmount( Integer nAmount )
	{
		this.nAmount = nAmount;
	}
	
	@Column( name = "VC_PICPATH" , length = 300 )
	public String getVcPicpath()
	{
		return this.vcPicpath;
	}
	
	public void setVcPicpath( String vcPicpath )
	{
		this.vcPicpath = vcPicpath;
	}
	
	@Column( name = "VC_USERPHONE" , length = 50 )
	public String getVcUserPhone()
	{
		return vcUserPhone;
	}
	
	public void setVcUserPhone( String vcUserPhone )
	{
		this.vcUserPhone = vcUserPhone;
	}
	
	@Column( name = "VC_HASH" , length = 50 )
	public String getVcHash()
	{
		return vcHash;
	}
	
	public void setVcHash( String vcHash )
	{
		this.vcHash = vcHash;
	}
	
	@Column( name = "VC_DAMAGEPART" , length = 50 )
	public String getVcDamagePart()
	{
		return vcDamagePart;
	}
	
	public void setVcDamagePart( String vcDamagePart )
	{
		this.vcDamagePart = vcDamagePart;
	}
	
}