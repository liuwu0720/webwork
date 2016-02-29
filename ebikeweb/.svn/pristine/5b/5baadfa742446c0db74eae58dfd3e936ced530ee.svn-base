package com.clt.finance.service;

import java.util.List;
import java.util.Map;

import com.clt.finance.model.TLoanAsk;
import com.clt.util.HqlHelper;
import com.clt.util.Page;

public interface ILoanAskService
{
	public TLoanAsk get( Integer id );
	
	public void update( TLoanAsk entity );
	
	public void save( TLoanAsk entity );
	
	public void saveOrUpdate( TLoanAsk entity );
	
	public void delete( TLoanAsk entity );
	
	public void deleteByKey( Integer id );
	
	public List< TLoanAsk > loadAll();
	
	public abstract List< TLoanAsk > findAll( Page page );
	
	public Map< String , Object > findByHelper( HqlHelper helper );
	
	// 获取代办贷款
	public Map< String , Object > getWaitLoanList( Page page , String financeno ,
	        String vcApplyUserName );
	
	// 获取已办贷款
	public Map< String , Object > getDoneLoans( Page page , String financeNo ,
	        String vcApplyUserName );
	
	public Map< String , Object > getLoanById( int id );
	
	// 图片路径处理方法
	public String pathManage( String path );
	
}
