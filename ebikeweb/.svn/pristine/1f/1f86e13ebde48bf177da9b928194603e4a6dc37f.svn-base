/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-5-22 下午2:07:13
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

/**
 * @Package com.clt.sub.model
 * @Description: TODO(司机工资结算)
 * @author liuwu
 * @date 2015-5-22 下午2:07:13
 * @version V1.0
 */
@Entity
@Table( name = "T_ARORDER_DRIVER" )
@org.hibernate.annotations.Entity( dynamicInsert = true , dynamicUpdate = true )
public class TArorderDriver implements java.io.Serializable
{
	
	/**
     * 
     */
	private static final long serialVersionUID = 9180709694588504536L;
	
	private Integer id;
	private Integer iOrderId;// 订单表ID
	
	private String vcStartCity;// 起运地
	
	private String vcEndCity;// 目的地
	private String vcCarStyle;// 车型
	private Date dtAudit;// 审核时间
	private String vcAuditUser;// 审核姓名
	private Integer nCreateBill;// 是否生成对帐单(0:未生成，1已生成)
	private Date dtCreateBill;// 帐单时间
	private String vcBilloNo;// 帐单号
	private Integer nBill;// 是否对帐(0:未对账，1已对帐
	private Date dtBill;// 对帐时间
	private String vcBillUser;// 对帐人
	private Integer nEnable;// 是否有效（0有效，1无效）
	private Integer iShipHeadId;// 发运主表id
	private Integer nAudit;// 是否审核 (0 结算已审核 1结算未审核)
	private Integer iShipLineId;// 发运明细id
	private Float nTotalPrice;// 总价
	private Integer nQty;// 数量
	private String vcDriver;// 司机
	private String vcSubno;// 分供方编号
	private String vcShipNo;// 指令号
	private String vcAuditNote;// 审核意见
	private Integer iDriverId;// 司机ID
	
	@SequenceGenerator( name = "TArorderDriverID" , sequenceName = "S_T_ARORDER_DRIVER" , allocationSize = 1 )
	@Id
	@GeneratedValue( strategy = SEQUENCE , generator = "TArorderDriverID" )
	@Column( name = "ID" , unique = true , nullable = false , precision = 22 , scale = 0 )
	public Integer getId()
	{
		return id;
	}
	
	public void setId( Integer id )
	{
		this.id = id;
	}
	
	@Column( name = "I_ORDERID" )
	public Integer getiOrderId()
	{
		return iOrderId;
	}
	
	public void setiOrderId( Integer iOrderId )
	{
		this.iOrderId = iOrderId;
	}
	
	@Column( name = "VC_START_CITY" )
	public String getVcStartCity()
	{
		return vcStartCity;
	}
	
	public void setVcStartCity( String vcStartCity )
	{
		this.vcStartCity = vcStartCity;
	}
	
	@Column( name = "VC_DEST_CITY" )
	public String getVcEndCity()
	{
		return vcEndCity;
	}
	
	public void setVcEndCity( String vcEndCity )
	{
		this.vcEndCity = vcEndCity;
	}
	
	@Column( name = "VC_CAR_STYLE" )
	public String getVcCarStyle()
	{
		return vcCarStyle;
	}
	
	public void setVcCarStyle( String vcCarStyle )
	{
		this.vcCarStyle = vcCarStyle;
	}
	
	@Column( name = "DT_AUDIT" )
	public Date getDtAudit()
	{
		return dtAudit;
	}
	
	public void setDtAudit( Date dtAudit )
	{
		this.dtAudit = dtAudit;
	}
	
	@Column( name = "VC_AUDIT_NAME" )
	public String getVcAuditUser()
	{
		return vcAuditUser;
	}
	
	public void setVcAuditUser( String vcAuditUser )
	{
		this.vcAuditUser = vcAuditUser;
	}
	
	@Column( name = "N_CREATE_BILL" )
	public Integer getnCreateBill()
	{
		return nCreateBill;
	}
	
	public void setnCreateBill( Integer nCreateBill )
	{
		this.nCreateBill = nCreateBill;
	}
	
	@Column( name = "DT_CREATE_BILL" )
	public Date getDtCreateBill()
	{
		return dtCreateBill;
	}
	
	public void setDtCreateBill( Date dtCreateBill )
	{
		this.dtCreateBill = dtCreateBill;
	}
	
	@Column( name = "VC_BILL_NO" )
	public String getVcBilloNo()
	{
		return vcBilloNo;
	}
	
	public void setVcBilloNo( String vcBilloNo )
	{
		this.vcBilloNo = vcBilloNo;
	}
	
	@Column( name = "N_BILL" )
	public Integer getnBill()
	{
		return nBill;
	}
	
	public void setnBill( Integer nBill )
	{
		this.nBill = nBill;
	}
	
	@Column( name = "DT_BILL" )
	public Date getDtBill()
	{
		return dtBill;
	}
	
	public void setDtBill( Date dtBill )
	{
		this.dtBill = dtBill;
	}
	
	@Column( name = "VC_BILLUSER" )
	public String getVcBillUser()
	{
		return vcBillUser;
	}
	
	public void setVcBillUser( String vcBillUser )
	{
		this.vcBillUser = vcBillUser;
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
	
	@Column( name = "I_SHIPHEAD" )
	public Integer getiShipHeadId()
	{
		return iShipHeadId;
	}
	
	public void setiShipHeadId( Integer iShipHeadId )
	{
		this.iShipHeadId = iShipHeadId;
	}
	
	@Column( name = "N_AUDIT" )
	public Integer getnAudit()
	{
		return nAudit;
	}
	
	public void setnAudit( Integer nAudit )
	{
		this.nAudit = nAudit;
	}
	
	@Column( name = "I_SHIPLINE" )
	public Integer getiShipLineId()
	{
		return iShipLineId;
	}
	
	public void setiShipLineId( Integer iShipLineId )
	{
		this.iShipLineId = iShipLineId;
	}
	
	@Column( name = "N_TOTAL_PRICE" )
	public Float getnTotalPrice()
	{
		return nTotalPrice;
	}
	
	public void setnTotalPrice( Float nTotalPrice )
	{
		this.nTotalPrice = nTotalPrice;
	}
	
	@Column( name = "N_QTY" )
	public Integer getnQty()
	{
		return nQty;
	}
	
	public void setnQty( Integer nQty )
	{
		this.nQty = nQty;
	}
	
	@Column( name = "VC_DIRVER" )
	public String getVcDriver()
	{
		return vcDriver;
	}
	
	public void setVcDriver( String vcDriver )
	{
		this.vcDriver = vcDriver;
	}
	
	@Column( name = "VC_SUBNO" )
	public String getVcSubno()
	{
		return vcSubno;
	}
	
	public void setVcSubno( String vcSubno )
	{
		this.vcSubno = vcSubno;
	}
	
	@Column( name = "VC_SHIPNO" )
	public String getVcShipNo()
	{
		return vcShipNo;
	}
	
	public void setVcShipNo( String vcShipNo )
	{
		this.vcShipNo = vcShipNo;
	}
	
	@Column( name = "VC_AUDITNOTE" )
	public String getVcAuditNote()
	{
		return vcAuditNote;
	}

	public void setVcAuditNote( String vcAuditNote )
	{
		this.vcAuditNote = vcAuditNote;
	}
	
	@Column( name = "I_DRIVER" )
	public Integer getiDriverId()
	{
		return iDriverId;
	}
	
	public void setiDriverId( Integer iDriverId )
	{
		this.iDriverId = iDriverId;
	}

}
