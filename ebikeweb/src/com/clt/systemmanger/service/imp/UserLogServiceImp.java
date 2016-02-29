package com.clt.systemmanger.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.IUserLogDao;
import com.clt.systemmanger.model.TUserLog;
import com.clt.systemmanger.service.IUserLogService;
import com.clt.util.HqlHelper;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: 用户操作日志信息
 * @author hjx
 * @date 2014年12月16日 上午11:39:25
 * @version V1.0
 */
@Service
public class UserLogServiceImp implements IUserLogService
{
	@Autowired
	private IUserLogDao userLogDao;
	
	/**
	 * @Description: 保存日志信息
	 * @param userLog
	 * @author hjx
	 * @create_date 2014年12月16日 上午11:39:25
	 */
	public void saveLog( TUserLog userLog )
	{
		userLogDao.save( userLog );
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return userLogDao.findAllByHqlHelp( helper );
	}
}
