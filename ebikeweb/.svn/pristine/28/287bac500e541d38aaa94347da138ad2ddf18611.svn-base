/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-5-22 上午11:05:30 
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

/**
 * @Package com.clt.sub.model
 * @Description: TODO(司机补贴关联表)
 * @author liuwu
 * @date 2015-5-22 上午11:05:30
 * @version V1.0
 */
@Entity
@Table( name = "T_DRIVER_SUBSIDES_LINK" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TDriverSubsideLinks implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = 2919390756995320813L;
	
	private Integer id;
	private Integer iDriverId;// 司机ID
	private Integer iSubsidesId;// 补贴表id
	private Integer nEnable;// 有效性（0:有效；1：无效）
	
	@SequenceGenerator( name = "T_DRIVER_SUBSIDES_LINKID" , sequenceName = "S_T_DRIVER_SUBSIDES_LINK" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "T_DRIVER_SUBSIDES_LINKID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_DRIVERID" )
	public Integer getiDriverId()
	{
		return iDriverId;
	}
	
	public void setiDriverId( Integer iDriverId )
	{
		this.iDriverId = iDriverId;
	}
	
	@Column( name = "I_SUBSIDESID" )
	public Integer getiSubsidesId()
	{
		return iSubsidesId;
	}
	
	public void setiSubsidesId( Integer iSubsidesId )
	{
		this.iSubsidesId = iSubsidesId;
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
