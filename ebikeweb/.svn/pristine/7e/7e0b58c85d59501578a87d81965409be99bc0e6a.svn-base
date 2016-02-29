package com.clt.sub.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.service.IMangeScopeService;
import com.clt.systemmanger.dao.IMangeScopeDao;
import com.clt.systemmanger.model.TMangeScope;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.sub.service.imp
 * @Description: 内部工作人员管理的分供方 管理映射服务类
 * @author hjx
 * @date 2014年9月9日 下午6:15:10
 * @version V1.0
 */
@Service
public class MangeScopeServiceImp implements IMangeScopeService
{
	@Autowired
	private IMangeScopeDao msDao;
	
	/**
	 * @Description: 根据用户id获得管理信息
	 * @param userId
	 * @return
	 * @author hjx
	 * @create_date 2014年9月9日 下午6:15:10
	 */
	public List< TMangeScope > getByUserId( Integer userId )
	{
		List< TMangeScope > mangeScopes = msDao.findByPropertys( new String[] { "IUser" ,
		        "NEnable" } , new Object[] { userId , SystemConstants.SYS_ENABLE } );
		return mangeScopes;
	}
	
	/**
	 * @Description: 根据用户id获得所管理的所有分供方编号
	 * @param userId
	 * @return
	 * @author hjx
	 * @create_date 2014年9月9日 下午6:15:10
	 */
	public List< String > getVcByUserId( Integer userId )
	{
		List< TMangeScope > mangeScopes = this.getByUserId( userId );
		List< String > list = new ArrayList< String >();
		for ( TMangeScope mangeScope : mangeScopes )
		{
			list.add( mangeScope.getVcSubno() );
		}
		return list;
	}
	
}
