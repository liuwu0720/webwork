package com.clt.sub.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @Description 记事本类型表
 * @author chengwzh
 * 
 */
@Entity
@Table( name = "T_NOTEPAD_TYPE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TNotepadType implements java.io.Serializable
{
	private static final long serialVersionUID = 9198524521295220493L;
	private Integer id;
	private Integer NEnable;
	private Date dtAdd;
	private String vcType;
	private Integer IUser;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO , generator = "TNotepadTypeID" )
	@SequenceGenerator( name = "TNotepadTypeID" , sequenceName = "S_NOTEPAD_TYPE" , allocationSize = 1 )
	@Column( name = "ID" , nullable = false , unique = true , precision = 22 , scale = 0 )
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
	
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "VC_TYPE" , length = 40 )
	public String getVcType()
	{
		return vcType;
	}
	
	public void setVcType( String vcType )
	{
		this.vcType = vcType;
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
	
}
