package com.clt.systemmanger.model;

// default package

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * TAssessWeight entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_ASSESS_WEIGHT" , schema = "CLTWL1" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TAssessWeight implements java.io.Serializable
{
	
	// Fields
	
	private Integer id;
	private Integer NEnable;
	private String vcTableName;
	private String vcRow;
	private Integer nWeight;
	private Integer iUser;
	private Date dtAdd;
	private String vcUsername;
	
	// Constructors
	
	/** default constructor */
	public TAssessWeight()
	{}
	
	/** full constructor */
	public TAssessWeight( Integer NEnable , String vcTableName , String vcRow ,
	        Integer nWeight , Integer iUser , Timestamp dtAdd , String vcUsername )
	{
		this.NEnable = NEnable;
		this.vcTableName = vcTableName;
		this.vcRow = vcRow;
		this.nWeight = nWeight;
		this.iUser = iUser;
		this.dtAdd = dtAdd;
		this.vcUsername = vcUsername;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TAssessWeightId" , sequenceName = "S_ASSESS_WEIGHT " , allocationSize = 1 )
	@Id
	@GeneratedValue( generator = "TAssessWeightId" , strategy = GenerationType.SEQUENCE )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
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
	
	@Column( name = "VC_TABLE_NAME" , length = 50 )
	public String getVcTableName()
	{
		return this.vcTableName;
	}
	
	public void setVcTableName( String vcTableName )
	{
		this.vcTableName = vcTableName;
	}
	
	@Column( name = "VC_ROW" , length = 20 )
	public String getVcRow()
	{
		return this.vcRow;
	}
	
	public void setVcRow( String vcRow )
	{
		this.vcRow = vcRow;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	@Column( name = "N_WEIGHT" , precision = 22 , scale = 0 )
	public Integer getnWeight()
	{
		return nWeight;
	}
	
	public void setnWeight( Integer nWeight )
	{
		this.nWeight = nWeight;
	}
	
	@Column( name = "I_USER" , precision = 22 , scale = 0 )
	public Integer getiUser()
	{
		return iUser;
	}
	
	public void setiUser( Integer iUser )
	{
		this.iUser = iUser;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "VC_USERNAME" , length = 40 )
	public String getVcUsername()
	{
		return this.vcUsername;
	}
	
	public void setVcUsername( String vcUsername )
	{
		this.vcUsername = vcUsername;
	}
	
}