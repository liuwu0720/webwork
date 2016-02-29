package com.clt.sub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.wordnik.swagger.annotations.ApiParam;

@Entity
@Table( name = "T_NOTEPAD" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TNotepad implements java.io.Serializable
{
	private static final long serialVersionUID = 6280360370431914379L;
	
	@ApiParam( value = "主键" )
	private Integer id;
	@ApiParam( "是否有效（0为有效，1为无效）" )
	private Integer NEnable;
	@ApiParam( "添加时间,手机端不用考虑该字段的值(yyyy-MM-dd)" )
	private Date dtAdd;
	@ApiParam( "添加用户id" )
	private Integer IUser;
	@ApiParam( "等级类型" )
	private Integer IType;
	@ApiParam( "备注" )
	private String vcNote;
	@ApiParam( "金额" )
	private Integer NMoney;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO , generator = "TNotepadId" )
	@SequenceGenerator( name = "TNotepadId" , sequenceName = "S_NOTEPAD" , allocationSize = 1 )
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
	public Integer getNEnable()
	{
		return NEnable;
	}
	
	public void setNEnable( Integer nEnable )
	{
		NEnable = nEnable;
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
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getIUser()
	{
		return IUser;
	}
	
	public void setIUser( Integer iUser )
	{
		IUser = iUser;
	}
	
	@Column( name = "I_TYPE" , precision = 22 , scale = 0 )
	public Integer getIType()
	{
		return IType;
	}
	
	public void setIType( Integer iType )
	{
		IType = iType;
	}
	
	@Column( name = "VC_NOTE" , length = 40 )
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}
	
	@Column( name = "N_MONEY" , precision = 22 , scale = 0 )
	public Integer getNMoney()
	{
		return NMoney;
	}
	
	public void setNMoney( Integer nMoney )
	{
		NMoney = nMoney;
	}
	
}
