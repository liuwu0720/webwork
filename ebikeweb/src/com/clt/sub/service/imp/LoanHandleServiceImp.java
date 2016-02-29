package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ILoanHandleDao;
import com.clt.sub.model.TLoanHandle;
import com.clt.sub.service.ILoanHandleService;
import com.clt.util.Page;

@Service
public class LoanHandleServiceImp implements ILoanHandleService
{
	@Autowired
	ILoanHandleDao loanHandleDao;
	
	public TLoanHandle get( Integer id )
	{
		// TODO Auto-generated method stub
		return loanHandleDao.get( id );
	}
	
	public void update( TLoanHandle entity )
	{
		// TODO Auto-generated method stub
		loanHandleDao.update( entity );
	}
	
	public void save( TLoanHandle entity )
	{
		// TODO Auto-generated method stub
		loanHandleDao.save( entity );
	}
	
	public void saveOrUpdate( TLoanHandle entity )
	{
		// TODO Auto-generated method stub
		loanHandleDao.saveOrUpdate( entity );
	}
	
	public void delete( TLoanHandle entity )
	{
		// TODO Auto-generated method stub
		loanHandleDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		// TODO Auto-generated method stub
		loanHandleDao.deleteByKey( id );
	}
	
	public List< TLoanHandle > loadAll()
	{
		// TODO Auto-generated method stub
		return loanHandleDao.loadAll();
	}
	
	public List< TLoanHandle > findAll( Page page )
	{
		// TODO Auto-generated method stub
		return loanHandleDao.findAll( page );
	}
	
}
