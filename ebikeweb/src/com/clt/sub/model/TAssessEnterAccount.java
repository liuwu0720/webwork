/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-7-21 下午2:10:11 
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

import com.wordnik.swagger.annotations.ApiParam;

/** 
 * @Package com.clt.sub.model 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-7-21 下午2:10:11 
 * @version V1.0 
 */
@Entity
@Table( name = "T_ASSESS_ENTER_ACCOUNT" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TAssessEnterAccount implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = 6331719242670730285L;
	private Integer id;
	@ApiParam( value = "是否有效（0有效，1无效）" )
	private Integer nEnable;
	@ApiParam( value = "添加人userId" )
	private Integer iUserId;
	@ApiParam( value = "添加时间" )
	private Date dtAdd;
	@ApiParam( value = "添加人姓名" )
	private String vcUserName;
	@ApiParam( value = "被评价用户id" )
	private Integer iUserBy;
	@ApiParam( value = "评价用户id" )
	private Integer iUserAcess;
	@ApiParam( value = "评价时间" )
	private Date dtAcess;
	@ApiParam( value = "业务编号（订单编号，抢单编号）" )
	private String vcBussiness;
	@ApiParam( value = "业务类型(1承运方入账评价，2：司机入账评价，3：中标者对发布者评价)" )
	private Integer nBussinessType;
	@ApiParam(value="服务态度评分")
	private Integer nQos;
	@ApiParam(value="回款速度评分")
	private Integer nReturnMoney;
	@ApiParam(value="诚信度评分")
	private Integer nIntegrity;
	@ApiParam(value="第四纬度内容")
	private String vcOther;
	@ApiParam(value="第四纬度评分")
	private Integer nOther;
	@ApiParam( value = "备注" )
	private String vcNote;
	
	@SequenceGenerator( name = "TASSESSENTERACCOUNT" , sequenceName = "S_ASSESS_ENTER_ACCOUNT" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TASSESSENTERACCOUNT" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
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
	
	@Column( name = "I_USER" )
	public Integer getiUserId()
	{
		return iUserId;
	}
	
	public void setiUserId( Integer iUserId )
	{
		this.iUserId = iUserId;
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
	
	@Column( name = "VC_USERNAME" )
	public String getVcUserName()
	{
		return vcUserName;
	}
	
	public void setVcUserName( String vcUserName )
	{
		this.vcUserName = vcUserName;
	}
	
	@Column( name = "I_USER_BY" )
	public Integer getiUserBy()
	{
		return iUserBy;
	}
	
	public void setiUserBy( Integer iUserBy )
	{
		this.iUserBy = iUserBy;
	}
	
	@Column( name = "I_USER_ASSESS" )
	public Integer getiUserAcess()
	{
		return iUserAcess;
	}
	
	public void setiUserAcess( Integer iUserAcess )
	{
		this.iUserAcess = iUserAcess;
	}
	
	@DateTimeFormat( pattern = "yyyy-MM-dd" )
	@Column( name = "DT_ASSESS" )
	public Date getDtAcess()
	{
		return dtAcess;
	}
	
	public void setDtAcess( Date dtAcess )
	{
		this.dtAcess = dtAcess;
	}
	
	@Column( name = "VC_BUSSINESS" )
	public String getVcBussiness()
	{
		return vcBussiness;
	}
	
	public void setVcBussiness( String vcBussiness )
	{
		this.vcBussiness = vcBussiness;
	}
	
	@Column( name = "N_BUSSINESS_TYPE" )
	public Integer getnBussinessType()
	{
		return nBussinessType;
	}
	
	public void setnBussinessType( Integer nBussinessType )
	{
		this.nBussinessType = nBussinessType;
	}
	
	@Column( name = "N_QOS" )
	public Integer getnQos()
	{
		return nQos;
	}
	
	public void setnQos( Integer nQos )
	{
		this.nQos = nQos;
	}
	
	@Column( name = "N_RETURN_MONEY" )
	public Integer getnReturnMoney()
	{
		return nReturnMoney;
	}
	
	public void setnReturnMoney( Integer nReturnMoney )
	{
		this.nReturnMoney = nReturnMoney;
	}
	
	@Column( name = "N_INTEGRITY" )
	public Integer getnIntegrity()
	{
		return nIntegrity;
	}
	
	public void setnIntegrity( Integer nIntegrity )
	{
		this.nIntegrity = nIntegrity;
	}
	
	@Column( name = "VC_OTHER" )
	public String getVcOther()
	{
		return vcOther;
	}
	
	public void setVcOther( String vcOther )
	{
		this.vcOther = vcOther;
	}
	
	@Column( name = "N_OTHER" )
	public Integer getnOther()
	{
		return nOther;
	}
	
	public void setnOther( Integer nOther )
	{
		this.nOther = nOther;
	}
	
	@Column( name = "VC_NOTE" )
	public String getVcNote()
	{
		return vcNote;
	}
	
	public void setVcNote( String vcNote )
	{
		this.vcNote = vcNote;
	}

}
