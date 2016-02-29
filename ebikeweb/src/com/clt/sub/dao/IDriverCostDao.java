package com.clt.sub.dao;

import com.clt.basedao.GenericDao;
import com.clt.sub.model.TDriverCost;

public interface IDriverCostDao extends GenericDao< TDriverCost , Integer >
{
	/**
	 * @Description: 根据分供方编号, 车辆ID 获取对应的单价信息
	 * @param subno
	 * @param driID
	 * @return TDriverCost 返回值描述 如果有多个返回null
	 * @author chenbin
	 * @create_date 2014-8-12 下午2:32:29
	 */
	public TDriverCost getTDriverCostByDriID( String subno , int driID );
}
