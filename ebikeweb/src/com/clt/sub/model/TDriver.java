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
 * 司机表 TTruckDriver entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_DRIVER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDriver implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 9079604727127556920L;
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "司机姓名" )
	private String vcDriverName;
	@ApiParam( "司机电话" )
	private String vcDriverTel;
	@ApiParam( "是否有效，默认为0" )
	private Integer NEnable;
	@ApiParam( "承运方编号" )
	private String vcSubno;
	@ApiParam( "承运方用户表ID" )
	private Integer iUserId;
	
	@ApiParam( "身份证号码" )
	private String vcCard;
	@ApiParam( "性别" )
	private String vcSex;
	@ApiParam( "籍贯" )
	private String vcPlace;
	@ApiParam( "出生日期，格式：yyyy-MM-dd" )
	private Date dtBirthday;
	@ApiParam( "司机类型" )
	private Integer IDriverType;
	@ApiParam( "图像路径，不用管该字段" )
	private String vcImgPath;// 图像路径
	@ApiParam( "驾驶证号" )
	private String vcDriverLi;// 驾驶证号
	@ApiParam( "保险号" )
	private String vcSafe;// 保险号
	@ApiParam( "评分" )
	private Integer IScore;// 评分
	@ApiParam( "" )
	private String vcBank;// 开户银行
	@ApiParam( "开户银行" )
	private String vcBankno;// 银行账号
	@ApiParam( "户主" )
	private String vcHost;// 户主
	@ApiParam( "身份证图片路径" )
	private String vcCardImgPath;// 身份证图片路径
	@ApiParam( "行驶证图片路径" )
	private String vcDriveImgPath;// 行驶证图片路径
	@ApiParam( "车辆图片路径" )
	private String vcTruckImgPath;// 车辆图片路径
	
	// Constructors
	
	/** default constructor */
	public TDriver()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TDRIVERID" , sequenceName = "S_DRIVER" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDRIVERID" )
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
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "VC_DRIVER_NAME" , length = 32 )
	public String getVcDriverName()
	{
		return vcDriverName;
	}
	
	public void setVcDriverName( String vcDriverName )
	{
		this.vcDriverName = vcDriverName;
	}
	
	@Column( name = "VC_DRIVER_TEL" , length = 32 )
	public String getVcDriverTel()
	{
		return vcDriverTel;
	}
	
	public void setVcDriverTel( String vcDriverTel )
	{
		this.vcDriverTel = vcDriverTel;
	}
	
	@Column( name = "VC_CARD" , length = 32 )
	public String getVcCard()
	{
		return vcCard;
	}
	
	public void setVcCard( String vcCard )
	{
		this.vcCard = vcCard;
	}
	
	@Column( name = "VC_SEX" , length = 32 )
	public String getVcSex()
	{
		return vcSex;
	}
	
	public void setVcSex( String vcSex )
	{
		this.vcSex = vcSex;
	}
	
	@Column( name = "VC_PLACE" , length = 32 )
	public String getVcPlace()
	{
		return vcPlace;
	}
	
	public void setVcPlace( String vcPlace )
	{
		this.vcPlace = vcPlace;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_BIRTHDAY" , length = 7 )
	public Date getDtBirthday()
	{
		return dtBirthday;
	}
	
	public void setDtBirthday( Date dtBirthday )
	{
		this.dtBirthday = dtBirthday;
	}
	
	@Column( name = "I_DRIVER_TYPE" , precision = 22 , scale = 0 )
	public Integer getIDriverType()
	{
		return IDriverType;
	}
	
	public void setIDriverType( Integer iDriverType )
	{
		IDriverType = iDriverType;
	}
	
	@Column( name = "VC_IMGPATH" , length = 100 )
	public String getVcImgPath()
	{
		return vcImgPath;
	}
	
	public void setVcImgPath( String vcImgPath )
	{
		this.vcImgPath = vcImgPath;
	}
	
	@Column( name = "VC_DRIVERCAR_lI" , length = 50 )
	public String getVcDriverLi()
	{
		return vcDriverLi;
	}
	
	public void setVcDriverLi( String vcDriverLi )
	{
		this.vcDriverLi = vcDriverLi;
	}
	
	@Column( name = "VC_SAFE" , length = 50 )
	public String getVcSafe()
	{
		return vcSafe;
	}
	
	public void setVcSafe( String vcSafe )
	{
		this.vcSafe = vcSafe;
	}
	
	@Column( name = "I_SCORE" , precision = 22 , scale = 0 )
	public Integer getIScore()
	{
		return IScore;
	}
	
	public void setIScore( Integer iScore )
	{
		IScore = iScore;
	}
	
	@Column( name = "I_USERID" , precision = 22 , scale = 0 )
	public Integer getiUserId()
	{
		return iUserId;
	}
	
	public void setiUserId( Integer iUserId )
	{
		this.iUserId = iUserId;
	}
	
	@Column( name = "VC_BANK" , length = 30 )
	public String getVcBank()
	{
		return vcBank;
	}
	
	public void setVcBank( String vcBank )
	{
		this.vcBank = vcBank;
	}
	
	@Column( name = "VC_BANKNO" , length = 30 )
	public String getVcBankno()
	{
		return vcBankno;
	}
	
	public void setVcBankno( String vcBankno )
	{
		this.vcBankno = vcBankno;
	}
	
	@Column( name = "VC_HOST" , length = 30 )
	public String getVcHost()
	{
		return vcHost;
	}
	
	public void setVcHost( String vcHost )
	{
		this.vcHost = vcHost;
	}
	
	@Column( name = "VC_CARD_IMGPATH" , length = 100 )
	public String getVcCardImgPath()
	{
		return vcCardImgPath;
	}
	
	public void setVcCardImgPath( String vcCardImgPath )
	{
		this.vcCardImgPath = vcCardImgPath;
	}
	
	@Column( name = "VC_DRIVE_IMGPATH" , length = 100 )
	public String getVcDriveImgPath()
	{
		return vcDriveImgPath;
	}
	
	public void setVcDriveImgPath( String vcDriveImgPath )
	{
		this.vcDriveImgPath = vcDriveImgPath;
	}
	
	@Column( name = "VC_TRUCK_IMGPATH" , length = 100 )
	public String getVcTruckImgPath()
	{
		return vcTruckImgPath;
	}
	
	public void setVcTruckImgPath( String vcTruckImgPath )
	{
		this.vcTruckImgPath = vcTruckImgPath;
	}
	
}