package com.clt.systemmanger.dao;

import java.util.List;
import java.util.Map;

import com.clt.basedao.GenericDao;
import com.clt.systemmanger.model.TUser;
import com.clt.util.DataGridModel;

public interface IUserDao extends GenericDao< TUser , Integer >
{
	Map< String , Object > getPageList( DataGridModel dgm , TUser user ) throws Exception;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return List<TUser> 返回值描述
	 * @author liuwu
	 * @param type
	 * @create_date 2015-3-26 下午4:18:55
	 */
	List< TUser > findAllByType( String type );
}
