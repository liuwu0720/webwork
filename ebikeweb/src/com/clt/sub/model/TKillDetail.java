package com.clt.sub.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 秒杀明细表 TKillDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_KILL_DETAIL" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TKillDetail implements java.io.Serializable
{
	
	// Fields
	
	/**
	 * ICarStyle
	 */
	private static final long serialVersionUID = 44511983984542868L;
	private Integer id;
	private Integer ICarStyle;
	private Integer IKillInfo;
	private Integer NEnable;
	private Float NPrice;
	
	// Constructors
	
	/** default constructor */
	public TKillDetail()
	{}
	
	/** minimal constructor */
	public TKillDetail( Integer id )
	{
		this.id = id;
	}
	
	// Property accessors
	@SequenceGenerator( name = "TKillDetailID" , sequenceName = "S_KILL_DETAIL" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TKillDetailID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_CAR_STYLE" , precision = 22 , scale = 0 )
	public Integer getICarStyle()
	{
		return this.ICarStyle;
	}
	
	public void setICarStyle( Integer TCarStyle )
	{
		this.ICarStyle = TCarStyle;
	}
	
	@Column( name = "I_KILL_INFO" , precision = 22 , scale = 0 )
	public Integer getIKillInfo()
	{
		return this.IKillInfo;
	}
	
	public void setIKillInfo( Integer TKillInfo )
	{
		this.IKillInfo = TKillInfo;
	}
	
	@Column( name = "N_ENABLE" , precision = 22 , scale = 0 )
	public Integer getNEnable()
	{
		return this.NEnable;
	}
	
	public void setNEnable( Integer NEnable )
	{
		this.NEnable = NEnable;
	}
	
	@Column( name = "N_PRICE" , precision = 22 , scale = 0 )
	public Float getNPrice()
	{
		return this.NPrice;
	}
	
	public void setNPrice( Float NPrice )
	{
		this.NPrice = NPrice;
	}
	
}