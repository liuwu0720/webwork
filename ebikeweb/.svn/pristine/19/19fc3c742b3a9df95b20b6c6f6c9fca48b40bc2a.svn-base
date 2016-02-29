package com.clt.sub.service;

import com.clt.sub.model.TUserCode;

/**
 * 用户短信验证码服务类
 * 
 * @author hjx86
 * 
 */
public interface IUserCodeService
{
	/**
	 * 保存用户短信验证码，保存前要把之前的验证码删除，确保一个用户只有一条最新验证码
	 * 
	 * @param userCode
	 */
	public void save( TUserCode userCode );
	
	/**
	 * 通过手机号码获得对应验证码,返回验证码后。
	 * 
	 * @param tel
	 * @return
	 */
	public String getCodeByTel( String tel );
}
