package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 抢单发布范围表
 * 
 * @author hjx86
 * 
 */
@Entity
@Table( name = "T_PRODUCT_SCOPE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TProductScope implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8411716380475350404L;
	private Integer id;
	private Integer NEnable;
	private Integer IProductId;
	private Integer ISub;
	
	@SequenceGenerator( name = "TProductScopeID" , sequenceName = "S_PRODUCT_SCOPE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TProductScopeID" )
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
	public Integer getNEnable()
	{
		return NEnable;
	}
	
	public void setNEnable( Integer nEnable )
	{
		NEnable = nEnable;
	}
	
	@Column( name = "I_PRODUCT_ID" , precision = 22 , scale = 0 )
	public Integer getIProductId()
	{
		return IProductId;
	}
	
	public void setIProductId( Integer iProductId )
	{
		IProductId = iProductId;
	}
	
	@Column( name = "I_SUB" , precision = 22 , scale = 0 )
	public Integer getISub()
	{
		return ISub;
	}
	
	public void setISub( Integer iSub )
	{
		ISub = iSub;
	}
	
}
