/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-22 上午10:18:38 
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
 * @Description: TODO(司机工资系数表)
 * @author liuwu
 * @date 2015-5-22 上午10:18:38
 * @version V1.0
 */
@Entity
@Table( name = "T_DRIVER_SALARY_COEFFICENT" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDriverSalaryCoefficient implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = 4682441132431346001L;
	
	private Integer id;
	private Integer iDriverId;//司机ID
	private String vcDriver;// 司机姓名
	private Float nPrice;// 提成系数
	private Date dtStart;//生效日期
	private Date dtEnd;//失效日期
	private Integer nEnable;// 有效性（0:有效；1无效）
	private String vcSubno;// 分供方编号
	
	@SequenceGenerator( name = "TDriverSalaryCoefficientID" , sequenceName = "S_T_DRIVER_SALARY_COEFFICENT" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TDriverSalaryCoefficientID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
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
	
	@Column( name = "N_PRICE" )
	public Float getnPrice()
	{
		return nPrice;
	}
	
	public void setnPrice( Float nPrice )
	{
		this.nPrice = nPrice;
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
	
	@Column( name = "N_ENABLE" )
	public Integer getnEnable()
	{
		return nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
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
	
	@Column( name = "VC_DRIVER" )
	public String getVcDriver()
	{
		return vcDriver;
	}
	
	public void setVcDriver( String vcDriver )
	{
		this.vcDriver = vcDriver;
	}
	
}
