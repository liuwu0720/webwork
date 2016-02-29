package com.clt.sub.service.imp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IUserCodeDao;
import com.clt.sub.model.TUserCode;
import com.clt.sub.service.IUserCodeService;

@Service
public class UserCodeServiceImp implements IUserCodeService
{
	@Autowired
	private IUserCodeDao userCodeDao;
	
	/**
	 * 保存用户短信验证码，保存前要把之前的验证码删除，确保一个用户只有一条最新验证码
	 * 
	 * @param userCode
	 */
	public void save( TUserCode userCode )
	{
		userCodeDao.deleteByProperty( "vcPhone" , userCode.getVcPhone() );
		userCodeDao.save( userCode );
	}
	
	/**
	 * 通过手机号码获得对应验证码,返回验证码。
	 * 
	 * @param tel
	 * @return
	 */
	public String getCodeByTel( String tel )
	{
		List< TUserCode > userCodes = userCodeDao.findByProperty( "vcPhone" , tel );
		if ( CollectionUtils.isNotEmpty( userCodes ) )
		{
			String code = userCodes.get( 0 ).getVcCode();
			return code;
		}
		return null;
	}
}
