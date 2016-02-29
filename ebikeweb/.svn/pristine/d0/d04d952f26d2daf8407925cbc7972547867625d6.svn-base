package com.clt.systemmanger.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 资源表 TResource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table( name = "T_RESOURCE" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TResource implements java.io.Serializable , Comparable
{
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = - 991538113738481102L;
	private Integer id;
	private String vcResourceName;
	private Integer NType;
	private String vcUrl;
	private Integer NEnable;
	private Integer NRoot;
	private Integer NLeaf;
	private String vcDesc;
	private Integer IParent;
	private Integer NSort;
	private int ishave;
	private String vcIcon;
	private Integer nFlag;// 判断是在PC还手机端
	
	// Constructors
	
	/** default constructor */
	public TResource()
	{}
	
	// Property accessors
	@SequenceGenerator( name = "RESOURCE" , sequenceName = "S_RESOURCE" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "RESOURCE" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return this.id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "VC_RESOURCE_NAME" , length = 40 )
	public String getVcResourceName()
	{
		return this.vcResourceName;
	}
	
	public void setVcResourceName( String vcResourceName )
	{
		this.vcResourceName = vcResourceName;
	}
	
	@Column( name = "N_TYPE" , precision = 22 , scale = 0 )
	public Integer getNType()
	{
		return this.NType;
	}
	
	public void setNType( Integer NType )
	{
		this.NType = NType;
	}
	
	@Column( name = "VC_URL" , length = 100 )
	public String getVcUrl()
	{
		return this.vcUrl;
	}
	
	public void setVcUrl( String vcUrl )
	{
		this.vcUrl = vcUrl;
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
	
	@Column( name = "N_ROOT" , precision = 22 , scale = 0 )
	public Integer getNRoot()
	{
		return this.NRoot;
	}
	
	public void setNRoot( Integer NRoot )
	{
		this.NRoot = NRoot;
	}
	
	@Column( name = "N_LEAF" , precision = 22 , scale = 0 )
	public Integer getNLeaf()
	{
		return this.NLeaf;
	}
	
	public void setNLeaf( Integer NLeaf )
	{
		this.NLeaf = NLeaf;
	}
	
	@Column( name = "VC_DESC" , length = 100 )
	public String getVcDesc()
	{
		return this.vcDesc;
	}
	
	public void setVcDesc( String vcDesc )
	{
		this.vcDesc = vcDesc;
	}
	
	@Column( name = "I_PARENT" , precision = 22 , scale = 0 )
	public Integer getIParent()
	{
		return this.IParent;
	}
	
	public void setIParent( Integer IParent )
	{
		this.IParent = IParent;
	}
	
	@Column( name = "N_SORT" , precision = 22 , scale = 0 )
	public Integer getNSort()
	{
		return this.NSort;
	}
	
	public void setNSort( Integer NSort )
	{
		this.NSort = NSort;
	}
	
	@Transient
	public int getIshave()
	{
		return ishave;
	}
	
	public void setIshave( int ishave )
	{
		this.ishave = ishave;
	}
	
	@Column( name = "VC_ICON" )
	public String getVcIcon()
	{
		return vcIcon;
	}
	
	public void setVcIcon( String vcIcon )
	{
		this.vcIcon = vcIcon;
	}
	
	@Column( name = "N_FLAG" )
	public Integer getnFlag()
	{
		return nFlag;
	}
	
	public void setnFlag( Integer nFlag )
	{
		this.nFlag = nFlag;
	}

	/**
	 * @Description:
	 * @param o
	 * @return
	 * @author hjx
	 * @create_date 2014年8月20日 上午11:15:22
	 */
	public int compareTo( Object o )
	{
		if ( this.getId() > ( ( TResource ) o ).getId() )
		{
			return 1;
		}
		else
		{
			return - 1;
		}
	}
}