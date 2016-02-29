/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-5-11 下午2:28:42
 * @version V1.0
 */
package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @Package com.clt.sub.model
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-5-11 下午2:28:42
 * @version V1.0
 */
@Entity
@Table( name = "T_SHIPSTATUS" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TShipStatus implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = - 298690670440523800L;
	private Integer id;// 状态表id
	private String vcStatusNote;// 状态描述
	private Date dtStatus;// 状态生成时间
	private String vcStatusTag;// 状态描述标志
	private String vcAddUser;// 状态确认人
	private Integer nLineId;// 指令明细表ID
	private String vcStatusPlace;// 状态生成地点的详细地址
	private String vcLongitude;// 状态生成的经度
	private String vcLatitude;// 状态生成的纬度
	private String vcHash;// 经纬度HASH值
	private Integer nOrderId;// 订单ID
	private Integer nHeadId;// 指令主表ID
	private Integer nEnable;// 状态有效性(0有效，1无效)
	private String vcNote;// 备注
	private String vcPath;// 状态图片（如回单照片等，多个以','相连）
	
	@SequenceGenerator( name = "TSHIPSTATUSID" , sequenceName = "s_t_shipstatus" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSHIPSTATUSID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_STATUSNOTE" , length = 50 )
	public String getVcStatusNote()
	{
		return vcStatusNote;
	}
	
	public void setVcStatusNote( String vcStatusNote )
	{
		this.vcStatusNote = vcStatusNote;
	}
	
	@Column( name = "DT_STATUS" , length = 7 )
	public Date getDtStatus()
	{
		return dtStatus;
	}
	
	public void setDtStatus( Date dtStatus )
	{
		this.dtStatus = dtStatus;
	}
	
	@Column( name = "VC_STATUSTAG" , length = 50 )
	public String getVcStatusTag()
	{
		return vcStatusTag;
	}
	
	public void setVcStatusTag( String vcStatusTag )
	{
		this.vcStatusTag = vcStatusTag;
	}
	
	@Column( name = "VC_ADDUSER" , length = 50 )
	public String getVcAddUser()
	{
		return vcAddUser;
	}
	
	public void setVcAddUser( String vcAddUser )
	{
		this.vcAddUser = vcAddUser;
	}
	
	@Column( name = "N_LINEID" )
	public Integer getnLineId()
	{
		return nLineId;
	}
	
	public void setnLineId( Integer nLineId )
	{
		this.nLineId = nLineId;
	}
	
	@Column( name = "VC_STATUS_PLACE" , length = 500 )
	public String getVcStatusPlace()
	{
		return vcStatusPlace;
	}
	
	public void setVcStatusPlace( String vcStatusPlace )
	{
		this.vcStatusPlace = vcStatusPlace;
	}
	
	@Column( name = "VC_LONGITUDE" , length = 50 )
	public String getVcLongitude()
	{
		return vcLongitude;
	}
	
	public void setVcLongitude( String vcLongitude )
	{
		this.vcLongitude = vcLongitude;
	}
	
	@Column( name = "VC_LATITUDE" , length = 50 )
	public String getVcLatitude()
	{
		return vcLatitude;
	}
	
	public void setVcLatitude( String vcLatitude )
	{
		this.vcLatitude = vcLatitude;
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
	
	@Column( name = "N_ORDERID" )
	public Integer getnOrderId()
	{
		return nOrderId;
	}
	
	public void setnOrderId( Integer nOrderId )
	{
		this.nOrderId = nOrderId;
	}
	
	@Column( name = "N_ENABLE" )
	public Integer getnEnable()
	{
		return nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	
	@Column( name = "N_HEADID" )
	public Integer getnHeadId()
	{
		return nHeadId;
	}
	
	public void setnHeadId( Integer nHeadId )
	{
		this.nHeadId = nHeadId;
	}
	
	@Column( name = "VC_NOTE" , length = 150 )
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	@Column( name = "VC_PATH" , length = 500 )
	public String getVcPath()
	{
		return vcPath;
	}
	
	public void setVcPath( String vcPath )
	{
		this.vcPath = vcPath;
	}
	
}
