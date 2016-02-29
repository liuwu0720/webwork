/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-4-24 上午11:15:13
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.sub.model
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-4-24 上午11:15:13
 * @version V1.0
 */
@Entity
@Table( name = "T_HOLIDAY_APPLAY" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class THolidayApply implements java.io.Serializable
{
	/**
     * 
     */
	private static final long serialVersionUID = 3614234174026712746L;
	@ApiParam( value = "主键" )
	private Integer id;// id
	@ApiParam( value = "是否有效(0:有效，1：无效)" )
	private Integer nEnable;// 是否有效(0:有效，1：无效)
	@ApiParam( value = "申请人姓名" )
	private String vcUserName;// 申请人姓名
	@ApiParam( value = " 申请时间" )
	private Date dtApply;// 申请时间
	@ApiParam( value = " 请假类型id" )
	private Integer iType;// 请假类型id
	@ApiParam( value = " 请假开始时间yyyy-MM-dd HH:mm:ss" )
	private Date dtStart;// 请假开始时间
	@ApiParam( value = "请假结束时间yyyy-MM-dd HH:mm:ss" )
	private Date dtEnd;// 请假结束时间
	@ApiParam( value = "请假事由" )
	private String vcApplyNote;
	@ApiParam( value = "申请人id" )
	private Integer iUser;// 申请人id
	@ApiParam( value = "分供方编号" )
	private String vcSubNo;// 分供方编号
	@ApiParam( value = "审批人id" )
	private Integer iApprove;// 审批人id
	@ApiParam( value = "审批人姓名" )
	private String vcApprveName;// 审批人姓名
	@ApiParam( value = "审批时间" )
	private Date dtApprove;// 审批时间
	@ApiParam( value = "审批结果(0,通过，1，未审批,2:未通过)" )
	private Integer nApproveResult;// 审批结果(0,通过，1，未审批,2:未通过)
	@ApiParam( value = "审批意见" )
	private String vcApproveNote;// 审批意见
	@ApiParam( value = "请假类型" )
	private String vcTypeName;// 请假类型
	@ApiParam( value = "请假天数" )
	private Double nDays;
	@ApiParam( value = "手机号码" )
	private String vcTel;
	
	/** default constructor */
	public THolidayApply()
	{}
	
	/** minimal constructor */
	public THolidayApply( Integer id )
	{
		this.id = id;
	}
	
	@SequenceGenerator( name = "THolidayApply" , sequenceName = "S_T_HOLIDAY_APPLAY" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "THolidayApply" )
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
	
	@Column( name = "VC_TEL" , length = 11 )
	public String getVcTel()
	{
		return vcTel;
	}
	
	public void setVcTel( String vcTel )
	{
		this.vcTel = vcTel;
	}
	
	public void setnEnable( Integer nEnable )
	{
		this.nEnable = nEnable;
	}
	
	@Column( name = "VC_NSER_NAME" , length = 30 )
	public String getVcUserName()
	{
		return vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_APPLAY" , length = 7 )
	public Date getDtApply()
	{
		return dtApply;
	}
	
	public void setDtApply( Date dtApply )
	{
		this.dtApply = dtApply;
	}
	
	@Column( name = "I_TYPE" , precision = 22 , scale = 0 )
	public Integer getiType()
	{
		return iType;
	}
	
	public void setiType( Integer iType )
	{
		this.iType = iType;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_START" , length = 7 )
	public Date getDtStart()
	{
		return dtStart;
	}
	
	public void setDtStart( Date dtStart )
	{
		this.dtStart = dtStart;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
	@Column( name = "DT_END" , length = 7 )
	public Date getDtEnd()
	{
		return dtEnd;
	}
	
	public void setDtEnd( Date dtEnd )
	{
		this.dtEnd = dtEnd;
	}
	
	@Column( name = "VC_APPLYNOTE" , length = 100 )
	public String getVcApplyNote()
	{
		return vcApplyNote;
	}
	
	public void setVcApplyNote( String vcApplyNote )
	{
		this.vcApplyNote = vcApplyNote;
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
	
	@Column( name = "VC_SUBNO" , length = 32 )
	public String getVcSubNo()
	{
		return vcSubNo;
	}
	
	public void setVcSubNo( String vcSubNo )
	{
		this.vcSubNo = vcSubNo;
	}
	
	@Column( name = "I_APPROVE" , precision = 22 , scale = 0 )
	public Integer getiApprove()
	{
		return iApprove;
	}
	
	public void setiApprove( Integer iApprove )
	{
		this.iApprove = iApprove;
	}
	
	@Column( name = "VC_APPROVE_NAME" , length = 30 )
	public String getVcApprveName()
	{
		return vcApprveName;
	}
	
	public void setVcApprveName( String vcApprveName )
	{
		this.vcApprveName = vcApprveName;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_APPROVE" , length = 7 )
	public Date getDtApprove()
	{
		return dtApprove;
	}
	
	public void setDtApprove( Date dtApprove )
	{
		this.dtApprove = dtApprove;
	}
	
	@Column( name = "N_APPROVE_RESULT" , precision = 22 , scale = 0 )
	public Integer getnApproveResult()
	{
		return nApproveResult;
	}
	
	public void setnApproveResult( Integer nApproveResult )
	{
		this.nApproveResult = nApproveResult;
	}
	
	@Column( name = "VC_NOTE" , length = 30 )
	public String getVcApproveNote()
	{
		return vcApproveNote;
	}
	
	public void setVcApproveNote( String vcApproveNote )
	{
		this.vcApproveNote = vcApproveNote;
	}
	
	@Column( name = "VC_TYPENAME" , length = 100 )
	public String getVcTypeName()
	{
		return vcTypeName;
	}
	
	public void setVcTypeName( String vcTypeName )
	{
		this.vcTypeName = vcTypeName;
	}
	
	@Column( name = "N_DAYS" , precision = 22 , scale = 0 )
	public Double getnDays()
	{
		return nDays;
	}
	
	public void setnDays( Double nDays )
	{
		this.nDays = nDays;
	}
}
