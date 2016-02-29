package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.TLoanType;

public interface ILoanTypeService
{
	public TLoanType get( int id );
	
	public Map< String , Object > getTypeList( int IType );
}
