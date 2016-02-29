package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TLoanHandle;
import com.clt.util.Page;

public interface ILoanHandleService
{
	public TLoanHandle get( Integer id );
	
	public void update( TLoanHandle entity );
	
	public void save( TLoanHandle entity );
	
	public void saveOrUpdate( TLoanHandle entity );
	
	public void delete( TLoanHandle entity );
	
	public void deleteByKey( Integer id );
	
	public List< TLoanHandle > loadAll();
	
	public abstract List< TLoanHandle > findAll( Page page );
	
}
