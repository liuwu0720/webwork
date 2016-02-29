package com.clt.util;

import java.util.HashMap;
import java.util.Map;

public interface SystemConstants
{
	/**
	 * 档案类型静态变量 1、内部 2、分供方 3、司机 4、金融公司 5、4S店
	 */
	public static final int SYS_TARCHIVE_CLT = 1;// 内部
	public static final int SYS_TARCHIVE_SUB = 2;// 分供方
	public static final int SYS_TARCHIVE_DRIVER = 3;// 司机
	public static final int SYS_TARCHIVE_FINANCE = 4;// 金融公司
	public static final int SYS_TARCHIVE_STORES = 5;// 4s店
	
	/**
	 * 分供方
	 */
	public static final String SYS_SUB = "分供方";
	public static final String SYS_ERROR_LOG = "c://wlpt//";// 系统错误日志输出目录
	public static final String SYS_ERROR_LOG_DEF = "c://wlpt//sys//";// 系统错误日志输出目录
	
	/**
	 * 司机默认密码 123456 md5 加密
	 */
	public static final String SYS_TARCHIVE_DRIVER_PASSWORD = "E10ADC3949BA59ABBE56E057F20F883E";
	
	/**
	 * 在线申请贷款静态变量
	 */
	public static final int SYS_LOAN_FLAG_SAVE = 0; // 保存未提交申请
	public static final int SYS_LOAN_FLAG_COMMIT = 1; // 申请贷款提交
	public static final int SYS_LOAN_FLAG_MEET = 2; // 申请贷款 洽谈中
	public static final int SYS_LOAN_FLAG_SUCCESS = 3; // 贷款成功
	public static final int SYS_LOAN_FLAG_FAIL = 4; // 贷款失败
	
	public static final int SYS_RESOURCE_TYPE_OPERATION = 2;// 操作
	public static final int SYS_RESOURCE_TYPE_MENU = 1;// 菜单
	
	/**
	 * 有效状态值
	 */
	public static final int SYS_ENABLE = 0;
	/**
	 * 无效状态值
	 */
	public static final int SYS_DISABLE = 1;
	
	/**
	 * 0：配载；3：接单;4,提货; 5 入场 10 装车 15 发运 18:运抵 20 回单
	 */
	/**
	 * 已接单变量 3
	 */
	public static final int SYS_SUB_SUREORDER = 3;
	/**
	 * 提货4
	 */
	public static final int SYS_SUB_PICK = 4;
	/**
	 * 已入场变量 5
	 */
	public static final int SYS_SUB_PARENTRANCE = 5;
	/**
	 * 已装车变量 10
	 */
	public static final int SYS_SUB_PARLOAD = 10;
	/**
	 * 已发运变量 15
	 */
	public static final int SYS_SUB_PARSHIP = 15;
	/**
	 * 已运抵变量 18
	 */
	public static final int SYS_SUB_PARARRIVED = 18;
	
	/**
	 * 已回单变量 20
	 */
	public static final int SYS_SUB_PARRETURN = 20;
	
	/**
	 * 已结算应收变量 30
	 */
	public static final int SYS_SUB_ARORDER = 30;
	
	@SuppressWarnings( "serial" )
	public static final Map< Integer , String > getstatusCollection = new HashMap< Integer , String >()
	{
		{
			put( 0 , "初始" );
			put( SYS_SUB_PARENTRANCE , "已入场" );
			put( SYS_SUB_PARLOAD , "已装车" );
			put( SYS_SUB_PARSHIP , "已发运" );
			put( SYS_SUB_PARARRIVED , "已运抵" );
			put( SYS_SUB_PARRETURN , "已回单" );
			put( SYS_SUB_ARORDER , "已结算应收" );
			
		}
	};
	
	/**
	 * 消息类型：内部通知
	 */
	public static final String SYS_MSG_INSIDE = "内部通知";
	/**
	 * 消息类型：分供方贷款
	 */
	public static final String SYS_MSG_LOAN = "分供方贷款";
	/**
	 * 消息类型：个人信息
	 */
	public static final String SYS_MSG_PERSON = "个人信息";
	
	/**
	 * 分供方注册时 赋予的基本角色代码
	 */
	public static final String SYS_SUB_ROLEBASE = "ROLE_SUB_BASE";
	/**
	 * 金融公司注册时 赋予的基本角色代码
	 */
	public static final String SYS_FINANCE_ROLEBASE = "ROLE_FINANCE_BASE";
	/**
	 * 4s店注册时赋予的基本角色代码
	 */
	public static final String SYS_STORES_ROLEBASE = "ROLE_STORE";
	/**
	 * 是否审核（0未审核，1已审核）
	 */
	public static final int SYS_CHECK_NO = 0;
	public static final int SYS_CHECK_YES = 1;
	/**
	 * 是否为领导（0为领导；1为员工）
	 */
	public static final int IS_LEADER = 0;
	public static final int NO_LEADER = 1;
	/**
	 * 用户状态图片路径
	 */
	public static final String USER_STATUS_IMG_PATH = "\\static\\statusimg";
	/**
	 * 汽车商标图片路径
	 */
	public static final String CAR_STYLE_IMG_PATH = "\\static\\carstyle_pic";
	/**
	 * 质损图片路径
	 */
	public static final String DAMAGE_IMG_PATH = "\\static\\damage_pic";
	/**
	 * 
	 */
	public static final String RETURN_IMG_PATH = "\\static\\return_pic";
	/**
	 * 手机app访问服务端请求地址附带的标识位字段; app请求url?visit=phone
	 */
	public static final String APP_VISIT_PARME = "visit";
	
	/**
	 * 手机app访问服务端请求地址附带的标识位内容; app请求url?visit=phone
	 */
	public static final String APP_VISIT = "phone";
	
	public static final double EARTH_RADIUS = 6371000;// 赤道半径(单位m)
	
	/**
	 * 状态描述及标识
	 */
	public static final String VC_LOADING_TRUE = "已配载";
	public static final String VC_LOADING_TRUE_TAG = "LOADING_TRUE";
	
	public static final String VC_SURE_TRUE = "确认接单";
	public static final String VC_SURE_TRUE_TAG = "SURE_TRUE";
	
	public static final String VC_ENTRANCE_TRUE = "已入场";
	public static final String VC_ENTRANCE_TRUE_TAG = "ENTRANCE_TRUE";
	
	public static final String VC_LOAD_TRUE = "已装车";
	public static final String VC_LOAD_TRUE_TAG = "LOAD_TRUE";
	
	public static final String VC_SHIP_TRUE = "已发运";
	public static final String VC_SHIP_TRUE_TAG = "SHIP_TRUE";
	
	public static final String VC_ARRIVED_TRUE = "已运抵";
	public static final String VC_ARRIVED_TRUE_TAG = "ARRIVED_TRUE";
	
	public static final String VC_RETURN_TRUE = "已回单";
	public static final String VC_RETURN_TRUE_TAG = "RETURN_TRUE";
	
	/**
	 * 添加积分
	 */
	public static final int SYS_ADD_INTEGRAL = 100;
	/**
	 * 选标时间差
	 */
	public static final int SYS_BID_HOUR = 3;
	public static final int ZHONGLIANID = 781;// 中联分供方表的ID
	
	// ERP 发送短信的中间件请求路径
	public static final String ERP_SEND_MSG_URL = "http://www.unlcn.com/erp/jsomAddSMS.jspx";
	public static final String ERP_SEND_MSG_USERNO = "appSys";
	public static final String ERP_SEND_MSG_COMPANY = "1";
	public static final int ERP_SEND_MSG_OUTTIME = 24;
	public static final String ERP_SEND_MSG_CONTENT_PRE = "您的订单编号为：";
	public static final String ERP_SEND_MSG_CONTENT_SUF = ",对应的收货确定码为：";
	
	public static final String ERP_SEND_CODE = "http://www.unlcn.com/erp/jsonConfirm4ZLShip.jspx";
	
}
