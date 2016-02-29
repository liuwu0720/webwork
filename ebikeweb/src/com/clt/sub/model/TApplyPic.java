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
 * TDamagePic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_APPLY_PIC" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TApplyPic implements java.io.Serializable
{
	
	// Fields

	/**
     * 
     */
	private static final long serialVersionUID = - 4838290220298709503L;
	private Integer id;
	private Integer iTableId;// 申请表id
	private Integer nEnable;// 是否有效（0有效，1无效）
	private String vcPicNote;// 照片描述
	private String vcPicPath;// 照片路径
	private String vcPicName;// 照片名称
	private String vcTableName;// 申请表
	private Date dtAdd;// 添加时间
	
	// Constructors
	
	/** default constructor */
	public TApplyPic()
	{}

	/** minimal constructor */
	public TApplyPic( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TAPPLYPIC" , sequenceName = "S_T_APPLY_PIC" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TAPPLYPIC" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_TABLEID" , precision = 22 , scale = 0 )
	public Integer getiTableId()
	{
		return iTableId;
	}
	
	public void setiTableId( Integer iTableId )
	{
		this.iTableId = iTableId;
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
	
	@Column( name = "VC_PIC_NOTE" , length = 200 )
	public String getVcPicNote()
	{
		return vcPicNote;
	}
	
	public void setVcPicNote( String vcPicNote )
	{
		this.vcPicNote = vcPicNote;
	}
	
	@Column( name = "VC_PIC_PATH" , length = 200 )
	public String getVcPicPath()
	{
		return vcPicPath;
	}
	
	public void setVcPicPath( String vcPicPath )
	{
		this.vcPicPath = vcPicPath;
	}
	
	@Column( name = "VC_PIC_NAME" , length = 200 )
	public String getVcPicName()
	{
		return vcPicName;
	}
	
	public void setVcPicName( String vcPicName )
	{
		this.vcPicName = vcPicName;
	}
	
	@Column( name = "VC_TABLENAME" , length = 200 )
	public String getVcTableName()
	{
		return vcTableName;
	}
	
	public void setVcTableName( String vcTableName )
	{
		this.vcTableName = vcTableName;
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
	
	
	

}