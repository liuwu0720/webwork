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
 * 审批记录表(下单 抢单 贷款 资质审批) entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_APPROVAL_RECORD" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TApprovalRecord implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * IUserId
	 */
	private static final long serialVersionUID = - 4919780984352023940L;
	private Integer id;
	private Integer ISubsuppliers;
	private Integer NType;
	private Integer NStatus;
	private Integer NIsRead;
	private Date dtCheckdate;
	
	private String vcRemarks;
	private Integer NEnable;
	private Integer ICheckUser;
	
	// Constructors
	
	/** default constructor */
	public TApprovalRecord()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "TApprovalRecordID" , sequenceName = "S_APPROVAL_RECORD" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TApprovalRecordID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_SUBSUPPLIERS" )
	public Integer getISubsuppliers()
	{
		return ISubsuppliers;
	}
	
	public void setISubsuppliers( Integer iSubsuppliers )
	{
		ISubsuppliers = iSubsuppliers;
	}
	
	@Column( name = "N_TYPE" )
	public Integer getNType()
	{
		return NType;
	}
	
	public void setNType( Integer nType )
	{
		NType = nType;
	}
	
	@Column( name = "N_STATUS" )
	public Integer getNStatus()
	{
		return NStatus;
	}
	
	public void setNStatus( Integer nStatus )
	{
		NStatus = nStatus;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_CHECKDATE" )
	public Date getDtCheckdate()
	{
		return dtCheckdate;
	}
	
	public void setDtCheckdate( Date dtCheckdate )
	{
		this.dtCheckdate = dtCheckdate;
	}
	
	@Column( name = "VC_REMARKS" , length = 20 )
	public String getVcRemarks()
	{
		return vcRemarks;
	}
	
	public void setVcRemarks( String vcRemarks )
	{
		this.vcRemarks = vcRemarks;
	}
	
	@Column( name = "N_ENABLE" )
	public Integer getNEnable()
	{
		return NEnable;
	}
	
	public void setNEnable( Integer nEnable )
	{
		NEnable = nEnable;
	}
	
	@Column( name = "N_ISREAD" )
	public Integer getNIsRead()
	{
		return NIsRead;
	}
	
	public void setNIsRead( Integer nIsRead )
	{
		NIsRead = nIsRead;
	}
	
	@Column( name = "I_CHECK_USER" , precision = 0 )
	public Integer getICheckUser()
	{
		return ICheckUser;
	}
	
	public void setICheckUser( Integer iCheckUser )
	{
		ICheckUser = iCheckUser;
	}
	
}