package com.clt.sub.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ILoanTypeDao;
import com.clt.sub.model.TLoanType;
import com.clt.sub.service.ILoanTypeService;
import com.clt.util.HqlHelper;

@Service
public class LoanTypeServiceImp implements ILoanTypeService
{
	@Autowired
	private ILoanTypeDao typeDao;
	
	public TLoanType get( int id )
	{
		return typeDao.get( id );
	}
	
	/**
	 * @Description 获取所有贷款方式/还款方式（）
	 * @param int IType （IType=1 贷款方式， IType=2还款方式）
	 * @author chengwzh
	 * @date 2015/5/27
	 */
	public Map< String , Object > getTypeList( int IType )
	{
		HqlHelper hql = new HqlHelper( TLoanType.class );
		hql.addEqual( "IType" , IType );
		Map< String , Object > result = typeDao.findAllByHqlHelp( hql );
		return result;
	}
}
