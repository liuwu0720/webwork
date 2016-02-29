package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TSubstaff entity. @author 分供方员工信息表
 */
@Entity
@Table( name = "T_SUBSTAFF" )
public class TSubstaff implements java.io.Serializable
{
	
	// Fields
	
	/**
     * 
     */
	private static final long serialVersionUID = - 1573697433699799318L;
	private Integer id;
	private String vcUsercardid;
	private String vcUseraddress;
	private String vcContact;
	private String vcDept;
	private Date dtJoin;
	private String vcSubno;
	private Integer iUserId;
	private Integer nEnable;
	private Integer iLeadId;

	
	public TSubstaff()
	{}

	// Property accessors
	@SequenceGenerator( name = "TSUBSTAFF" , sequenceName = "s_t_substaff" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSUBSTAFF" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return this.id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}
	

	
	@Column( name = "VC_USERCARDID" , length = 50 )
	public String getVcUsercardid()
	{
		return this.vcUsercardid;
	}
	
	public void setVcUsercardid( String vcUsercardid )
	{
		this.vcUsercardid = vcUsercardid;
	}
	
	@Column( name = "VC_USERADDRESS" , length = 50 )
	public String getVcUseraddress()
	{
		return this.vcUseraddress;
	}
	
	public void setVcUseraddress( String vcUseraddress )
	{
		this.vcUseraddress = vcUseraddress;
	}
	
	@Column( name = "VC_CONTACT" , length = 50 )
	public String getVcContact()
	{
		return vcContact;
	}
	
	public void setVcContact( String vcContact )
	{
		this.vcContact = vcContact;
	}
	
	@Column( name = "VC_DEPT" , length = 50 )
	public String getVcDept()
	{
		return this.vcDept;
	}
	

	public void setVcDept( String vcDept )
	{
		this.vcDept = vcDept;
	}
	
	@Temporal( TemporalType.DATE )
	@Column( name = "DT_JOIN" , length = 7 )
	public Date getDtJoin()
	{
		return this.dtJoin;
	}
	
	public void setDtJoin( Date dtJoin )
	{
		this.dtJoin = dtJoin;
	}
	
	@Column( name = "VC_SUBNO" , length = 50 )
	public String getVcSubno()
	{
		return this.vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "I_USERID" )
	public Integer getiUserId()
	{
		return iUserId;
	}
	
	public void setiUserId( Integer iUserId )
	{
		this.iUserId = iUserId;
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
	
	@Column( name = "I_LEADID" , precision = 22 , scale = 0 )
	public Integer getiLeadId()
	{
		return iLeadId;
	}
	
	public void setiLeadId( Integer iLeadId )
	{
		this.iLeadId = iLeadId;
	}
	
	
}