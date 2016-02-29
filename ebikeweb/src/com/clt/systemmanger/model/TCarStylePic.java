package com.clt.systemmanger.model;

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
 * TCarStylePic entity. 汽车商标图片
 */
@Entity
@Table( name = "T_CAR_STYLE_PIC" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TCarStylePic implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 4633060762976448788L;
	private Integer id;
	private Integer nEnable;
	private String vcAdduser;
	private Date dtAdd;
	private String vcStyle;
	private String vcPath;
	
	// Constructors
	
	/** default constructor */
	public TCarStylePic()
	{}
	
	/** minimal constructor */
	public TCarStylePic( Integer id )
	{
		this.id = id;
	}
	
	/** full constructor */
	public TCarStylePic( Integer id , Integer nEnable , String vcAdduser , Date dtAdd ,
	        String vcStyle , String vcPath )
	{
		this.id = id;
		this.nEnable = nEnable;
		this.vcAdduser = vcAdduser;
		this.dtAdd = dtAdd;
		this.vcStyle = vcStyle;
		this.vcPath = vcPath;
	}
	
	@SequenceGenerator( name = "TCARSTYLEPIC" , sequenceName = "S_T_CAR_STYLE_PIC" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TCARSTYLEPIC" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "N_ENABLE" , precision = 22 , scale = 0 )
	public Integer getnEnable()
	{
		return this.nEnable;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	
	@Column( name = "VC_ADDUSER" , length = 30 )
	public String getVcAdduser()
	{
		return this.vcAdduser;
	}
	
	public void setVcAdduser( String vcAdduser )
	{
		this.vcAdduser = vcAdduser;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_ADD" , length = 7 )
	public Date getDtAdd()
	{
		return this.dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "VC_STYLE" , length = 30 )
	public String getVcStyle()
	{
		return this.vcStyle;
	}
	
	public void setVcStyle( String vcStyle )
	{
		this.vcStyle = vcStyle;
	}
	
	@Column( name = "VC_PATH" , length = 100 )
	public String getVcPath()
	{
		return this.vcPath;
	}
	
	public void setVcPath( String vcPath )
	{
		this.vcPath = vcPath;
	}
	
}