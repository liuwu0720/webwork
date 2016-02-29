package com.clt.sub.service;

import com.clt.sub.model.TLimitCheck;

/**
 * @Package com.clt.sub.service
 * @Description: 空闲运力查看权利服务类
 * @author hjx
 * @date 2014年10月10日 下午4:03:32
 * @version V1.0
 */
public interface ILimitCheckService
{
	/**
	 * @Description: 保存可查看空闲运力权限信息
	 * @param limitCheck
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年10月10日 下午4:05:23
	 */
	public void save( TLimitCheck limitCheck );
	
	/**
	 * @Description: 根据用户id，和空闲运力信息id 判断当前用户是否可查看该空闲运力信息
	 * @param userid
	 * @param spareId
	 * @return boolean 返回值描述 true有权限查看，false 无权限查看
	 * @author hjx
	 * @create_date 2014年10月10日 下午4:06:08
	 */
	public boolean getByUserIdAndSpareId( int userid , int spareId );
	
}
