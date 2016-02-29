/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-21 下午4:25:29 
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
 * @Description: TODO(司机提成表)
 * @author liuwu
 * @date 2015-5-21 下午4:25:29
 * @version V1.0
 */
@Entity
@Table( name = "T_DRIVER_COMMISSION" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDriverCommsion implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = 8869624577056068680L;
	
	private Integer id;
	private Integer iHeadId;
	private Integer iLineId;
	private String vcShipNo;
	private Float nKilometer;
	private Date dtAdd;
	private Integer nEnable;
	
	@SequenceGenerator( name = "TDriverCommsionID" , sequenceName = "S_T_DRIVER_SALARY" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDriverCommsionID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_HEADID" )
	public Integer getiHeadId()
	{
		return iHeadId;
	}
	
	public void setiHeadId( Integer iHeadId )
	{
		this.iHeadId = iHeadId;
	}
	
	@Column( name = "I_LINEID" )
	public Integer getiLineId()
	{
		return iLineId;
	}
	
	public void setiLineId( Integer iLineId )
	{
		this.iLineId = iLineId;
	}
	
	@Column( name = "VC_SHIPNO" )
	public String getVcShipNo()
	{
		return vcShipNo;
	}
	
	public void setVcShipNo( String vcShipNo )
	{
		this.vcShipNo = vcShipNo;
	}
	
	@Column( name = "N_KILOMETER" )
	public Float getnKilometer()
	{
		return nKilometer;
	}
	
	public void setnKilometer( Float nKilometer )
	{
		this.nKilometer = nKilometer;
	}
	
	@Column( name = "DT_ADD" )
	public Date getDtAdd()
	{
		return dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
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

}
