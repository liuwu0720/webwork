/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-7-9 下午3:07:18 
 * @version V1.0 
 */
package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.sub.model
 * @Description: TODO(分供方绑定的银行卡)
 * @author liuwu
 * @date 2015-7-9 下午3:07:18
 * @version V1.0
 */
@Entity
@Table( name = "T_SUBSUPPLIERS_BANK" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TSubsuppliersBank implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = - 3485255103560803412L;
	@ApiParam( value = "ID" )
	private Integer id;
	@ApiParam( value = "开户行" )
	private String vcOpenPlace;
	@ApiParam( value = "帐号" )
	private String vcAccount;
	@ApiParam( value = "户主姓名" )
	private String vcUserName;
	@ApiParam( value = "分供方ID" )
	private Integer iSubId;
	@ApiParam( value = "是否有效(0有效，1无效)" )
	private Integer nEnable;
	
	@SequenceGenerator( name = "TSubsuppliersBankID" , sequenceName = "S_T_SUBSUPPLIERS_BANK" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TSubsuppliersBankID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_OPENPLACE" )
	public String getVcOpenPlace()
	{
		return vcOpenPlace;
	}
	
	public void setVcOpenPlace( String vcOpenPlace )
	{
		this.vcOpenPlace = vcOpenPlace;
	}
	
	@Column( name = "VC_ACCOUNT" )
	public String getVcAccount()
	{
		return vcAccount;
	}
	
	public void setVcAccount( String vcAccount )
	{
		this.vcAccount = vcAccount;
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
	
	@Column( name = "I_SUBID" )
	public Integer getiSubId()
	{
		return iSubId;
	}
	
	public void setiSubId( Integer iSubId )
	{
		this.iSubId = iSubId;
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



}
