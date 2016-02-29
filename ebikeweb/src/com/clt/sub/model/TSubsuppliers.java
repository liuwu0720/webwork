package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * 分供方档案表 TSubsuppliers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_SUBSUPPLIERS" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TSubsuppliers implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 4426565671413816561L;
	@ApiParam( value = "(*)ID" )
	private Integer id;// ID
	@ApiParam( value = "(*)分供方全称" )
	private String vcAllName;// 分供方全称
	@ApiParam( value = "(*)分供方简称" )
	private String vcSmipleName;// 分供方简称
	@ApiParam( value = "分供方地址" )
	private String vcAddress;// 分供方地址
	@ApiParam( value = "(*)分供方规模（有多少辆车）" )
	private String vcScale;// 分供方规模（有多少辆车）
	@ApiParam( value = "是否有效(0有效，1无效)" )
	private Integer NEnable;// 是否有效(0有效，1无效)
	@ApiParam( value = "所属省份ID" )
	private Integer IProvince;// 所属省份ID
	@ApiParam( value = "(*)所属城市ID" )
	private Integer ICity;// 所属城市ID
	@ApiParam( value = "(*)所属城市名称" )
	private String vcCityName;// 所属城市名称
	@ApiParam( value = "(*)详细地址" )
	private String vcDetailedAddress;// 详细地址
	@ApiParam( value = "(*)公司注册地址" )
	private String vcRegisterAddress;// 公司注册地址
	@ApiParam( value = "是否可抢单(0为可，1为不可，默认为1)" )
	private Integer NEnableKill;// 是否可抢单(0为可，1为不可，默认为1)
	@ApiParam( value = "是否可下单(0为可，1为不可，2 正在审批 3 审批不通过 默认为1 )" )
	private Integer NEnableOrder;// 是否可下单(0为可，1为不可，2 正在审批 3 审批不通过 默认为1 )
	@ApiParam( value = "下单资质证明" )
	private String vcOrderFile;// 下单资质证明
	@ApiParam( value = "抢单资质证明" )
	private String vcKillFile;// 抢单资质证明
	@ApiParam( value = " 是否可贷款(0为可，1为不可，默认为1)" )
	private Integer NEnableApply;// 是否可贷款(0为可，1为不可，默认为1)
	@ApiParam( value = "分供方编号" )
	private String vcSubno;// 分供方编号
	@ApiParam( value = "(*)分供方电话" )
	private String vcTel;// 分供方电话
	@ApiParam( value = " 信誉" )
	private Integer IScore;// 信誉
	@ApiParam( value = " 积分" )
	private Integer inte;// 积分
	@ApiParam( value = "logo路径" )
	private String vcImgPath;// logo路径
	@ApiParam( value = " 支付信用" )
	private Integer iLoadScore;// 支付信用
	@ApiParam( value = "营业执照照片路径" )
	private String vcLicencePath;// 营业执照照片路径
	@ApiParam( value = " 运输证照片路径" )
	private String vcTransportPath;// 运输证照片路径
	
	// Constructors
	
	/** default constructor */
	public TSubsuppliers()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TSubsuppliersID" , sequenceName = "S_SUBSUPPLIERS" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSubsuppliersID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_ALL_NAME" , length = 100 )
	public String getVcAllName()
	{
		return this.vcAllName;
	}
	
	public void setVcAllName( String vcAllName )
	{
		this.vcAllName = vcAllName;
	}
	
	@Column( name = "VC_SMIPLE_NAME" , length = 50 )
	public String getVcSmipleName()
	{
		return this.vcSmipleName;
	}
	
	public void setVcSmipleName( String vcSmipleName )
	{
		this.vcSmipleName = vcSmipleName;
	}
	
	@Column( name = "VC_ADDRESS" , length = 150 )
	public String getVcAddress()
	{
		return this.vcAddress;
	}
	
	public void setVcAddress( String vcAddress )
	{
		this.vcAddress = vcAddress;
	}
	
	@Column( name = "VC_SCALE" , length = 50 )
	public String getVcScale()
	{
		return this.vcScale;
	}
	
	public void setVcScale( String vcScale )
	{
		this.vcScale = vcScale;
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
	
	@Column( name = "I_PROVINCE" )
	public Integer getIProvince()
	{
		return IProvince;
	}
	
	public void setIProvince( Integer iProvince )
	{
		this.IProvince = iProvince;
	}
	
	@Column( name = "I_CITY" )
	public Integer getICity()
	{
		return ICity;
	}
	
	public void setICity( Integer iCity )
	{
		this.ICity = iCity;
	}
	
	/*@Column( name = "VC_AREA" , length = 150 )
	public String getVcArea()
	{
		return this.vcArea;
	}
	
	public void setVcArea( String vcArea )
	{
		this.vcArea = vcArea;
	}*/

	@Column( name = "VC_DETAILED_ADDRESS" , length = 150 )
	public String getVcDetailedAddress()
	{
		return this.vcDetailedAddress;
	}
	
	public void setVcDetailedAddress( String vcDetailedAddress )
	{
		this.vcDetailedAddress = vcDetailedAddress;
	}
	
	@Column( name = "VC_REGISTER_ADDRESS" , length = 150 )
	public String getVcRegisterAddress()
	{
		return this.vcRegisterAddress;
	}
	
	public void setVcRegisterAddress( String vcRegisterAddress )
	{
		this.vcRegisterAddress = vcRegisterAddress;
	}
	
	@Column( name = "N_ENABLE_KILL" , precision = 22 , scale = 0 )
	public Integer getNEnableKill()
	{
		return this.NEnableKill;
	}
	
	public void setNEnableKill( Integer nEnableKill )
	{
		this.NEnableKill = nEnableKill;
	}
	
	@Column( name = "N_ENABLE_ORDER" , precision = 22 , scale = 0 )
	public Integer getNEnableOrder()
	{
		return this.NEnableOrder;
	}
	
	public void setNEnableOrder( Integer nEnableOrder )
	{
		this.NEnableOrder = nEnableOrder;
	}
	
	@Column( name = "VC_ORDER_FILE" , length = 50 )
	public String getVcOrderFile()
	{
		return this.vcOrderFile;
	}
	
	public void setVcOrderFile( String vcOrderFile )
	{
		this.vcOrderFile = vcOrderFile;
	}
	
	@Column( name = "VC_KILL_FILE" , length = 50 )
	public String getVcKillFile()
	{
		return this.vcKillFile;
	}
	
	public void setVcKillFile( String vcKillFile )
	{
		this.vcKillFile = vcKillFile;
	}
	
	@Column( name = "N_ENABLE_APPLY" , precision = 22 , scale = 0 )
	public Integer getNEnableApply()
	{
		return NEnableApply;
	}
	
	public void setNEnableApply( Integer nEnableApply )
	{
		NEnableApply = nEnableApply;
	}
	
	@Column( name = "VC_SUBNO" , nullable = false , length = 32 )
	public String getVcSubno()
	{
		return vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "VC_TEL" )
	public String getVcTel()
	{
		return vcTel;
	}
	
	public void setVcTel( String vcTel )
	{
		this.vcTel = vcTel;
	}
	
	@Column( name = "I_SCORE" )
	public Integer getIScore()
	{
		return IScore;
	}
	
	public void setIScore( Integer iScore )
	{
		this.IScore = iScore;
	}
	
	@Transient
	public Integer getInte()
	{
		return inte;
	}
	
	public void setInte( Integer inte )
	{
		this.inte = inte;
	}
	
	@Column( name = "VC_IMG_PATH" )
	public String getVcImgPath()
	{
		return vcImgPath;
	}
	
	public void setVcImgPath( String vcImgPath )
	{
		this.vcImgPath = vcImgPath;
	}
	
	@Column( name = "I_LOAD_SCORE" )
	public Integer getiLoadScore()
	{
		return iLoadScore;
	}
	
	public void setiLoadScore( Integer iLoadScore )
	{
		this.iLoadScore = iLoadScore;
	}
	
	@Column( name = "VC_LICENCEPATH" )
	public String getVcLicencePath()
	{
		return vcLicencePath;
	}
	
	public void setVcLicencePath( String vcLicencePath )
	{
		this.vcLicencePath = vcLicencePath;
	}
	
	@Column( name = "VC_TRANSPORTPATH" )
	public String getVcTransportPath()
	{
		return vcTransportPath;
	}
	
	public void setVcTransportPath( String vcTransportPath )
	{
		this.vcTransportPath = vcTransportPath;
	}
	
	@Column( name = "VC_CITYNAME" )
	public String getVcCityName()
	{
		return vcCityName;
	}
	
	public void setVcCityName( String vcCityName )
	{
		this.vcCityName = vcCityName;
	}
	
}