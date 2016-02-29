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
 * TFineApplay entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_FINE_APPLAY" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TFineApplay implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 3600323330896085938L;
	@ApiParam( value = "主键" )
	private Integer id;// id
	@ApiParam( value = "是否有效（0有效，1无效）" )
	private Integer NEnable;// 是否有效（0有效，1无效）
	@ApiParam( value = "申请人id(司机表ID)" )
	private Integer IUser; // 申请人id(司机表ID)
	@ApiParam( value = "申请人姓名" )
	private String vcNserName;// 申请人姓名
	@ApiParam( value = " 申请时间" )
	private Date dtApplay;// 申请时间
	@ApiParam( value = "罚款金额" )
	private Float NFine;// 罚款金额
	@ApiParam( value = " 罚款照片路径（多张以“，”相连）" )
	private String vcFinePicPath;// 罚款照片路径（多张以“，”相连）
	@ApiParam( value = "罚款地点名字" )
	private String vcFineSite;// 罚款地点名字
	@ApiParam( value = "罚款经度" )
	private String vcLongitude;// 罚款经度
	@ApiParam( value = "罚款纬度" )
	private String vcLatitude;// 罚款纬度
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
	@ApiParam( value = "是否有发票(0有，1没有，默认为有)" )
	private Integer NInvoice;// 是否有发票(0有，1没有，默认为有)
	@ApiParam( value = "罚款参考金额" )
	private Integer NReference;// 罚款参考金额
	@ApiParam( value = "审批备注" )
	private String vcApplyNote;// 审批备注
	@ApiParam( value = "司机手机号" )
	private String vcUserPhone;// 司机手机号
	@ApiParam( value = "经纬度hash值" )
	private String vcHash;// 经纬度hash值
	@ApiParam( value = "罚款类型" )
	private String vcFineType;// 罚款类型
	
	// Constructors
	
	/** default constructor */
	public TFineApplay()
	{}
	
	/** minimal constructor */
	public TFineApplay( Integer id )
	{
		this.id = id;
	}
	
	@SequenceGenerator( name = "TFINEAPPLAY" , sequenceName = "S_FINE_APPLAY" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TFINEAPPLAY" )
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
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
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
	
	@Column( name = "VC_NSER_NAME" , length = 30 )
	public String getVcNserName()
	{
		return this.vcNserName;
	}
	
	public void setVcNserName( String vcNserName )
	{
		this.vcNserName = vcNserName;
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
	
	@Column( name = "N_FINE" , precision = 22 , scale = 0 )
	public Float getNFine()
	{
		return this.NFine;
	}
	
	public void setNFine( Float NFine )
	{
		this.NFine = NFine;
	}
	
	@Column( name = "VC_FINEPIC_PATH" , length = 300 )
	public String getVcFinePicPath()
	{
		return vcFinePicPath;
	}
	
	public void setVcFinePicPath( String vcFinePicPath )
	{
		this.vcFinePicPath = vcFinePicPath;
	}
	
	@Column( name = "VC_FINE_SITE" , length = 500 )
	public String getVcFineSite()
	{
		return this.vcFineSite;
	}
	
	public void setVcFineSite( String vcFineSite )
	{
		this.vcFineSite = vcFineSite;
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
	public Integer getNInvoice()
	{
		return this.NInvoice;
	}
	
	public void setNInvoice( Integer NInvoice )
	{
		this.NInvoice = NInvoice;
	}
	
	@Column( name = "N_REFERENCE" , precision = 22 , scale = 0 )
	public Integer getNReference()
	{
		return this.NReference;
	}
	
	public void setNReference( Integer NReference )
	{
		this.NReference = NReference;
	}
	
	@Column( name = "VC_APPLYNOTE" , length = 300 )
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
	
	@Column( name = "VC_HASH" , length = 300 )
	public String getVcHash()
	{
		return vcHash;
	}
	
	public void setVcHash( String vcHash )
	{
		this.vcHash = vcHash;
	}
	
	@Column( name = "VC_FINETYPE" , length = 100 )
	public String getVcFineType()
	{
		return vcFineType;
	}
	
	public void setVcFineType( String vcFineType )
	{
		this.vcFineType = vcFineType;
	}
	
}