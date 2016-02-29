package com.clt.finance.service;

import java.util.List;

import com.clt.finance.model.TFinance;
import com.clt.util.Page;

/**
 * @Package com.clt.finance.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月17日 上午11:08:45
 * @version V1.0
 */
public interface IFinanceService
{
	public TFinance get( Integer id );
	
	public void update( TFinance entity );
	
	public void save( TFinance entity );
	
	public void saveOrUpdate( TFinance entity );
	
	public void delete( TFinance entity );
	
	public void deleteByKey( Integer id );
	
	public List< TFinance > loadAll();
	
	public abstract List< TFinance > findAll( Page page );
	
	public List< TFinance > loadAllByEnable();
	
	public List< TFinance > getEnableFinance();
	
	public void updateCleanBefore( TFinance entity );
}
