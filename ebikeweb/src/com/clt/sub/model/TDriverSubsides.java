/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-22 上午9:58:04 
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

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Package com.clt.sub.model
 * @Description: TODO(司机补贴表)
 * @author liuwu
 * @date 2015-5-22 上午9:58:04
 * @version V1.0
 */
@Entity
@Table( name = "T_DRIVER_SUBSIDES" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDriverSubsides implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = - 740629208129749183L;
	
	private Integer id;
	private String vcName;// 补贴名称
	private Float nNumber;// 补贴金额
	private Date dtStart;// 开始日期
	private Date dtEnd;// 结束日期
	private String vcNote;// 备注
	private String vcSubno;// 分供方编号
	private Integer nEnable;// 有效性
	@SequenceGenerator( name = "TDriverSubsidesID" , sequenceName = "S_T_DRIVER_SUBSIDES" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDriverSubsidesID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_NAME" )
	public String getVcName()
	{
		return vcName;
	}
	
	public void setVcName( String vcName )
	{
		this.vcName = vcName;
	}
	
	@Column( name = "N_NUMBER" )
	public Float getnNumber()
	{
		return nNumber;
	}
	
	public void setnNumber( Float nNumber )
	{
		this.nNumber = nNumber;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_START" )
	public Date getDtStart()
	{
		return dtStart;
	}
	
	public void setDtStart( Date dtStart )
	{
		this.dtStart = dtStart;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_END" )
	public Date getDtEnd()
	{
		return dtEnd;
	}
	
	public void setDtEnd( Date dtEnd )
	{
		this.dtEnd = dtEnd;
	}
	
	@Column( name = "VC_NOTE" )
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	@Column( name = "VC_SUBNO" )
	public String getVcSubno()
	{
		return vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
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
