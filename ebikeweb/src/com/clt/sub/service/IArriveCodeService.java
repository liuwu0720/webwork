package com.clt.sub.service;

/**
 * @Package com.clt.sub.service 
 * @Description: 抵达确定码记录 的服务类。
 * @author hjx 
 * @date 2015年9月23日 下午2:47:38 
 * @version V1.0
 */
public interface IArriveCodeService
{
	/**
	 * @Description: 发送短信验证码成功时，
	 * 把验证码和指令号，订单号，客户运单号保存到数据包，方便抵达验证！ 
	 * @param vcShipno 指令号
	 * @param vcOrderno 订单号
	 * @param vcCustOrderno 客户运单号
	 * @param code 验证码
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月23日 下午2:49:54
	 */
	public void saveCode( String vcShipno , String vcOrderno , String vcCustOrderno ,
	        String code );
	
	/**
	 * @Description: 查询对应订单编号是否需要核对验证码
	 * @param vcOrderno 订单编号
	 * @return 
	 *   boolean 返回值描述
	 * @author hjx
	 * @create_date 2015年9月23日 下午2:57:59
	 */
	public boolean hasCodeByOrderNo( String vcOrderno );
	
	/**
	 * @Description: 通过订单编号和验证码，去数据库核对是否一致，
	 * 					如果设置为已经验证，并返回true，否则返回false
	 * @param vcOrderno 订单编号
	 * @param code 验证码
	 * @param vcLongitude 经度
	 * @param vcLatitude 纬度
	 * @param vcArrivePlace 确认抵达的详细地址
	 * @return 
	 *   boolean 返回值描述
	 * @author hjx
	 * @create_date 2015年9月23日 下午2:59:57
	 */
	public boolean isCheckSuccess( String vcOrderno , String code , String vcLongitude ,
	        String vcLatitude , String vcArrivePlace );
}
