/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-5-8 上午9:55:36
 * @version V1.0
 */
package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Entity;

/**
 * @Package com.clt.sub.model
 * @Description: TODO(订单回单照片)
 * @author liuwu
 * @date 2015-5-8 上午9:55:36
 * @version V1.0
 */
@Entity
@Table( name = "T_RETURN_PIC" )
public class TReturnPic implements java.io.Serializable
{
	/**
     * 
     */
	private static final long serialVersionUID = 6129947569685602477L;
	private Integer id;
	private String vcPath;
	private Date dtAdd;
	private Integer orderId;
	
	// Property accessors
	@SequenceGenerator( name = "TRETURNPIC" , sequenceName = "S_T_RETURN_PIC" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TRETURNPIC" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_START_CITY" , length = 100 )
	public String getVcPath()
	{
		return vcPath;
	}
	
	public void setVcPath( String vcPath )
	{
		this.vcPath = vcPath;
	}
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "DT_ADD" )
	public Date getDtAdd()
	{
		return dtAdd;
	}
	
	public void setDtAdd( Date dtAdd )
	{
		this.dtAdd = dtAdd;
	}
	
	@Column( name = "I_ORDERID" , precision = 0 )
	public Integer getOrderId()
	{
		return orderId;
	}
	
	public void setOrderId( Integer orderId )
	{
		this.orderId = orderId;
	}
	
}
