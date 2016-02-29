/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-5 下午2:16:48 
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
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-6-5 下午2:16:48 
 * @version V1.0 
 */
@Entity
@Table( name = "T_HOLIDAY_TYPE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class THolidayType implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = - 8722169069375628974L;
	
	private Integer id;
	private Integer nEnable;// 有效性 0：有效 1 无效
	private String vcAddUser;// 添加人
	private Date dtAdd;// 添加时间
	private String vcType;// 请假类型
	
	@SequenceGenerator( name = "THolidayType" , sequenceName = "S_T_HOLIDAY_TYPE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "THolidayType" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
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
	
	@Column( name = "VC_ADDUSER" , length = 30 )
	public String getVcAddUser()
	{
		return vcAddUser;
	}
	
	public void setVcAddUser( String vcAddUser )
	{
		this.vcAddUser = vcAddUser;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "VC_TYPE" , length = 20 )
	public String getVcType()
	{
		return vcType;
	}
	
	public void setVcType( String vcType )
	{
		this.vcType = vcType;
	}

}
