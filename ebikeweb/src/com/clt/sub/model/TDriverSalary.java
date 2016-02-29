/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-21 下午4:04:04 
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
 * @Description: TODO(司机基本工资表)
 * @author liuwu
 * @date 2015-5-21 下午4:04:04
 * @version V1.0
 */
@Entity
@Table( name = "T_DRIVER_SALARY" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDriverSalary implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = - 976164111852805964L;
	
	private Integer id;// 主键
	private Integer nMonth;// 月份
	private Float nSalary;// 工资
	private Date dtStart;// 生效日期
	private Date dtEnd;// 失效日期
	private Integer iDriverId;// 司机ID
	private Integer nEnable;// 是否有效（0有效，1无效)
	private String vcDriverName;// 司机姓名
	private String vcAddUser;// 添加人
	private Date dtAdd;// 添加时间
	private String vcSubno;// 分供方编号
	
	@SequenceGenerator( name = "TDriverSalaryID" , sequenceName = "S_T_DRIVER_SALARY" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDriverSalaryID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_MONTH" )
	public Integer getnMonth()
	{
		return nMonth;
	}
	
	public void setnMonth( Integer nMonth )
	{
		this.nMonth = nMonth;
	}
	
	@Column( name = "N_SALARY" )
	public Float getnSalary()
	{
		return nSalary;
	}
	
	public void setnSalary( Float nSalary )
	{
		this.nSalary = nSalary;
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
	
	@Column( name = "I_DRIVERID" )
	public Integer getiDriverId()
	{
		return iDriverId;
	}
	
	public void setiDriverId( Integer iDriverId )
	{
		this.iDriverId = iDriverId;
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
	
	@Column( name = "VC_DRIVER" )
	public String getVcDriverName()
	{
		return vcDriverName;
	}
	
	public void setVcDriverName( String vcDriverName )
	{
		this.vcDriverName = vcDriverName;
	}
	
	@Column( name = "VC_ADDUSER" )
	public String getVcAddUser()
	{
		return vcAddUser;
	}
	
	public void setVcAddUser( String vcAddUser )
	{
		this.vcAddUser = vcAddUser;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_ADD" )
	public Date getDtAdd()
	{
		return dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
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

}
