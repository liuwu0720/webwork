package com.clt.sub.dao;

import java.util.List;
import java.util.Map;

import com.clt.basedao.GenericDao;
import com.clt.sub.model.TShiphead;

public interface IShipheadDao extends GenericDao< TShiphead , Integer >
{
	
	public TShiphead getShipHeadByshipNo( String shipno );
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param sql
	 * @return List<Map<String,Object>> 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-22 上午10:14:32
	 */
	public List< Map< String , Object >> excuteSql( String sql );
	
	
}
