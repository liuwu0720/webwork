/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-6-11 下午1:31:49
 * @version V1.0
 */
package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.sub.model
 * @Description: TODO(4S店)
 * @author liuwu
 * @date 2015-6-11 下午1:31:49
 * @version V1.0
 */

@Entity
@Table( name = "T_STORES" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TStores implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = - 516436238094471848L;
	@ApiParam( "主键" )
	private Integer id;
	@ApiParam( "是否有效(0有效，1无效)" )
	private Integer nEnable;// 是否有效（0有效，1无效）
	@ApiParam( "所属省份ID" )
	private Integer iProvinceId;// 所属省份ID
	@ApiParam( "所属城市ID" )
	private Integer iCityId;// 所属城市ID
	@ApiParam( "所属地区" )
	private String vcArea;// 所属地区
	@ApiParam( "详细地址" )
	private String vcDetailAddress;// 详细地址
	@ApiParam( "4S店编号" )
	private String vcStoreNo;// 4S店编号
	@ApiParam( "4S店电话" )
	private String vcTel;// 4S店电话
	@ApiParam( "信誉" )
	private Float iScore;// 信誉
	@ApiParam( "省份" )
	private String vcProvince;// 省份
	@ApiParam( "城市" )
	private String vcCity;// 城市
	@ApiParam( "公司注册地址" )
	private String vcRegisterAddress;// 公司注册地址
	@ApiParam( "图片路径" )
	private String vcImagePath;// 图片路径
	@ApiParam( "4S店名称" )
	private String vcStoreName;// 4S店名称
	@ApiParam( "店铺简称" )
	private String vcShotName;// 店铺简称
	@ApiParam( "联系人" )
	private String vcLinkman;// 联系人
	@ApiParam( "logo图片路径" )
	private String vcLogoPath;// logo图片路径
	
	@ApiParam( "用户表ID" )
	private Integer iUserId;// 用户表ID

	@SequenceGenerator( name = "TSTORES" , sequenceName = "S_T_STORES" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSTORES" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return id;
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
	
	@Column( name = "I_PROVINCE" )
	public Integer getiProvinceId()
	{
		return iProvinceId;
	}
	
	public void setiProvinceId( Integer iProvinceId )
	{
		this.iProvinceId = iProvinceId;
	}
	
	@Column( name = "I_CITY" )
	public Integer getiCityId()
	{
		return iCityId;
	}
	
	public void setiCityId( Integer iCityId )
	{
		this.iCityId = iCityId;
	}
	
	@Column( name = "VC_AREA" )
	public String getVcArea()
	{
		return vcArea;
	}
	
	public void setVcArea( String vcArea )
	{
		this.vcArea = vcArea;
	}
	
	@Column( name = "VC_DETAILED_ADDRESS" )
	public String getVcDetailAddress()
	{
		return vcDetailAddress;
	}
	
	public void setVcDetailAddress( String vcDetailAddress )
	{
		this.vcDetailAddress = vcDetailAddress;
	}
	
	@Column( name = "VC_STORESNO" )
	public String getVcStoreNo()
	{
		return vcStoreNo;
	}
	
	public void setVcStoreNo( String vcStoreNo )
	{
		this.vcStoreNo = vcStoreNo;
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
	public Float getiScore()
	{
		return iScore;
	}
	
	public void setiScore( Float iScore )
	{
		this.iScore = iScore;
	}
	
	@Column( name = "VC_PROVINCE" )
	public String getVcProvince()
	{
		return vcProvince;
	}
	
	public void setVcProvince( String vcProvince )
	{
		this.vcProvince = vcProvince;
	}
	
	@Column( name = "VC_CITY" )
	public String getVcCity()
	{
		return vcCity;
	}
	
	public void setVcCity( String vcCity )
	{
		this.vcCity = vcCity;
	}
	
	@Column( name = "VC_REGISTER_ADDRESS" )
	public String getVcRegisterAddress()
	{
		return vcRegisterAddress;
	}
	
	public void setVcRegisterAddress( String vcRegisterAddress )
	{
		this.vcRegisterAddress = vcRegisterAddress;
	}
	
	@Column( name = "VC_IMG_PATH" )
	public String getVcImagePath()
	{
		return vcImagePath;
	}
	
	public void setVcImagePath( String vcImagePath )
	{
		this.vcImagePath = vcImagePath;
	}
	
	@Column( name = "VC_STORES_NAME" )
	public String getVcStoreName()
	{
		return vcStoreName;
	}
	
	public void setVcStoreName( String vcStoreName )
	{
		this.vcStoreName = vcStoreName;
	}
	
	@Column( name = "VC_SHOT_NAME" , length = 50 )
	public String getVcShotName()
	{
		return vcShotName;
	}
	
	public void setVcShotName( String vcShotName )
	{
		this.vcShotName = vcShotName;
	}
	
	@Column( name = "VC_LINKMAN" , length = 50 )
	public String getVcLinkman()
	{
		return vcLinkman;
	}
	
	public void setVcLinkman( String vcLinkman )
	{
		this.vcLinkman = vcLinkman;
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
	
	@Column( name = "I_USERID" )
	public Integer getiUserId()
	{
		return iUserId;
	}
	
	public void setiUserId( Integer iUserId )
	{
		this.iUserId = iUserId;
	}
	
}
