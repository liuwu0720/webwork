package com.clt.finance.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * 金融公司档案 TFinance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_FINANCE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TFinance implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * NType
	 */
	private static final long serialVersionUID = - 3794130481779403350L;
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "金融公司全称" )
	private String vcAllName;
	@ApiParam( "金融公司简称" )
	private String vcShortName;
	@ApiParam( "金融公司注册地址" )
	private String vcAddress;
	@ApiParam( "所属省份" )
	private String vcProvince;
	@ApiParam( "所属城市" )
	private String vcCity;
	@ApiParam( "所属地区" )
	private String vcArea;
	@ApiParam( "金融公司地址" )
	private String vcDetailedAddress;
	@ApiParam( "是否可贷款（0可，1不可，默认为1）" )
	private Integer NApply;
	@ApiParam( "贷款证明" )
	private String vcApplyFile;
	@ApiParam( "是否有效（0有效，1无效）" )
	private Integer NEnable;
	@ApiParam( "公司类型" )
	private Integer NType;
	@ApiParam( "金融公司编号" )
	private String vcFinanceno;
	@ApiParam( "金融公司联系电话" )
	private String vcTel;
	@ApiParam( "金融公司营业执照路径" )
	private String vcLisencePath;
	@ApiParam( "金融公司logo图片路径" )
	private String vcLogoPath;
	
	// Constructors
	
	/** default constructor */
	public TFinance()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TFinanceID" , sequenceName = "S_FINANCE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TFinanceID" )
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
	
	@Column( name = "VC_SHORT_NAME" , length = 50 )
	public String getVcShortName()
	{
		return this.vcShortName;
	}
	
	public void setVcShortName( String vcShortName )
	{
		this.vcShortName = vcShortName;
	}
	
	@Column( name = "VC_ADDRESS" , length = 100 )
	public String getVcAddress()
	{
		return this.vcAddress;
	}
	
	public void setVcAddress( String vcAddress )
	{
		this.vcAddress = vcAddress;
	}
	
	@Column( name = "VC_PROVINCE" , length = 50 )
	public String getVcProvince()
	{
		return this.vcProvince;
	}
	
	public void setVcProvince( String vcProvince )
	{
		this.vcProvince = vcProvince;
	}
	
	@Column( name = "VC_CITY" , length = 60 )
	public String getVcCity()
	{
		return this.vcCity;
	}
	
	public void setVcCity( String vcCity )
	{
		this.vcCity = vcCity;
	}
	
	@Column( name = "VC_AREA" , length = 150 )
	public String getVcArea()
	{
		return this.vcArea;
	}
	
	public void setVcArea( String vcArea )
	{
		this.vcArea = vcArea;
	}
	
	@Column( name = "VC_DETAILED_ADDRESS" , length = 150 )
	public String getVcDetailedAddress()
	{
		return this.vcDetailedAddress;
	}
	
	public void setVcDetailedAddress( String vcDetailedAddress )
	{
		this.vcDetailedAddress = vcDetailedAddress;
	}
	
	@Column( name = "N_APPLY" , precision = 22 , scale = 0 )
	public Integer getNApply()
	{
		return this.NApply;
	}
	
	public void setNApply( Integer NApply )
	{
		this.NApply = NApply;
	}
	
	@Column( name = "VC_APPLY_FILE" , length = 50 )
	public String getVcApplyFile()
	{
		return this.vcApplyFile;
	}
	
	public void setVcApplyFile( String vcApplyFile )
	{
		this.vcApplyFile = vcApplyFile;
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
	
	@Column( name = "VC_FINANCENO" , length = 32 )
	public String getVcFinanceno()
	{
		return this.vcFinanceno;
	}
	
	public void setVcFinanceno( String vcFinanceno )
	{
		this.vcFinanceno = vcFinanceno;
	}
	
	@Column( name = "VC_TEL" , length = 20 )
	public String getVcTel()
	{
		return vcTel;
	}
	
	public void setVcTel( String vcTel )
	{
		this.vcTel = vcTel;
	}
	
	@Column( name = "N_TYPE" , precision = 22 , scale = 0 )
	public Integer getNType()
	{
		return NType;
	}
	
	public void setNType( Integer nType )
	{
		NType = nType;
	}
	
	@Column( name = "VC_LISENCE_PATH" , length = 200 )
	public String getVcLisencePath()
	{
		return vcLisencePath;
	}
	
	public void setVcLisencePath( String vcLisencePath )
	{
		this.vcLisencePath = vcLisencePath;
	}
	
	@Column( name = "VC_LOGO_PATH" , length = 200 )
	public String getVcLogoPath()
	{
		return vcLogoPath;
	}
	
	public void setVcLogoPath( String vcLogoPath )
	{
		this.vcLogoPath = vcLogoPath;
	}
	
}