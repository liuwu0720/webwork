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
 * TFixTruck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_FIX_TRUCK" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TFixTruck implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 7798053480051964994L;
	@ApiParam( value = "主键" )
	private Integer id;// id
	@ApiParam( value = "是否有效（0有效，1无效）" )
	private Integer nEnable;// 是否有效（0有效，1无效）
	@ApiParam( value = "申请人id" )
	private Integer iUser; // 申请人id
	@ApiParam( value = "申请人姓名" )
	private String vcUserName;// 申请人姓名
	@ApiParam( value = "申请时间" )
	private Date dtApplay;// 申请时间
	@ApiParam( value = "修车部位" )
	private String vcFixPart;// 修车部位
	@ApiParam( value = "修车所需时间（如：一天，一星期" )
	private String vcFixTime;// 修车所需时间（如：一天，一星期）
	@ApiParam( value = "修车时间(格式：2015-02-02)" )
	private Date dtFixTime;// 修车时间(格式：2015-02-02)
	@ApiParam( value = "修车金额" )
	private Float nFix;// 修车金额
	@ApiParam( value = "修车地点名字" )
	private String vcFixSite;// 修车地点名字
	@ApiParam( value = "修车经度" )
	private String vcLongitude;// 修车经度
	@ApiParam( value = "修车纬度" )
	private String vcLatitude;// 修车纬度
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
	@ApiParam( value = "是否有发票(0有，1没有，默认为有)" )
	private Integer nInvoice;// 是否有发票(0有，1没有，默认为有)
	@ApiParam( value = "申请备注" )
	private String vcApplyNote;// 申请备注
	@ApiParam( value = "申请人手机号" )
	private String vcUserPhone;// 申请人手机号
	@ApiParam( value = "修车照片路径（多张以“，”相连）" )
	private String vcPicPath;// 修车照片路径（多张以“，”相连）
	@ApiParam( value = "经纬度hash值" )
	private String vcHash;// 经纬度hash值
	
	// Constructors
	
	/** default constructor */
	public TFixTruck()
	{}
	
	/** minimal constructor */
	public TFixTruck( Integer id )
	{
		this.id = id;
	}
	
	@SequenceGenerator( name = "TFIXTRUCK" , sequenceName = "s_fix_truck" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TFIXTRUCK" )
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
	
	@Column( name = "VC_FIX_PART" , length = 80 )
	public String getVcFixPart()
	{
		return this.vcFixPart;
	}
	
	public void setVcFixPart( String vcFixPart )
	{
		this.vcFixPart = vcFixPart;
	}
	
	@Column( name = "VC_FIX_TIME" , length = 50 )
	public String getVcFixTime()
	{
		return this.vcFixTime;
	}
	
	public void setVcFixTime( String vcFixTime )
	{
		this.vcFixTime = vcFixTime;
	}
	
	@Column( name = "N_FIX" , precision = 22 , scale = 0 )
	public Float getnFix()
	{
		return nFix;
	}
	
	public void setnFix( Float nFix )
	{
		this.nFix = nFix;
	}
	
	@Column( name = "VC_FIX_SITE" , length = 300 )
	public String getVcFixSite()
	{
		return this.vcFixSite;
	}
	
	public void setVcFixSite( String vcFixSite )
	{
		this.vcFixSite = vcFixSite;
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
	
	@Column( name = "N_INVOICE" , precision = 22 , scale = 0 )
	public Integer getnInvoice()
	{
		return nInvoice;
	}
	
	public void setnInvoice( Integer nInvoice )
	{
		this.nInvoice = nInvoice;
	}
	
	@Column( name = "VC_APPLYNOTE" , precision = 22 , scale = 0 )
	public String getVcApplyNote()
	{
		return vcApplyNote;
	}
	
	public void setVcApplyNote( String vcApplyNote )
	{
		this.vcApplyNote = vcApplyNote;
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
	
	@Column( name = "VC_PICPATH" , length = 30 )
	public String getVcPicPath()
	{
		return vcPicPath;
	}
	
	public void setVcPicPath( String vcPicPath )
	{
		this.vcPicPath = vcPicPath;
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
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_FIXTIME" , length = 7 )
	public Date getDtFixTime()
	{
		return dtFixTime;
	}
	
	public void setDtFixTime( Date dtFixTime )
	{
		this.dtFixTime = dtFixTime;
	}
	
}