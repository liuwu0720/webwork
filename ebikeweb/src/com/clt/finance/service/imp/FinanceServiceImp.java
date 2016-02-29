package com.clt.finance.service.imp;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.finance.dao.IFinanceDao;
import com.clt.finance.model.TFinance;
import com.clt.finance.service.IFinanceService;
import com.clt.util.DateUtil;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.finance.service.imp
 * @Description:
 * @author hjx
 * @date 2014年7月17日 上午11:09:52
 * @version V1.0
 */
@Service
public class FinanceServiceImp implements IFinanceService
{
	@Autowired
	private IFinanceDao financeDao;
	
	public TFinance get( Integer id )
	{
		return financeDao.get( id );
	}
	
	public void update( TFinance entity )
	{
		financeDao.update( entity );
	}
	
	public void save( TFinance entity )
	{
		entity.setVcFinanceno( getMaxOrderNo() );
		financeDao.save( entity );
	}
	
	public void saveOrUpdate( TFinance entity )
	{
		financeDao.saveOrUpdate( entity );
	}
	
	public void delete( TFinance entity )
	{
		financeDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		financeDao.deleteByKey( id );
	}
	
	public List< TFinance > loadAll()
	{
		return financeDao.loadAll();
	}
	
	public List< TFinance > findAll( Page page )
	{
		return financeDao.findAll( page );
	}
	
	public List< TFinance > loadAllByEnable()
	{
		return financeDao.loadAllByEnable();
	}
	
	/**
	 * @Description: 生产分供方编号
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月21日 下午3:20:23
	 */
	private synchronized String getMaxOrderNo()
	{
		
		List< TFinance > orderlist = financeDao.findAllAndOrderByProperty( "id" , false );
		String orderno = "F";
		String datestr = DateUtil.getDate( "yyMMdd" );
		DecimalFormat df = new DecimalFormat( "0000" );
		String str2 = "";
		if ( orderlist.size() == 0 )
		{
			str2 = df.format( Integer.parseInt( "1" ) );
			
		}
		else
		{
			TFinance fin = orderlist.get( 0 );
			
			String str = fin.getVcFinanceno().substring( 7 ,
			        fin.getVcFinanceno().length() );
			str2 = df.format( Integer.parseInt( str ) + 1 );
		}
		
		orderno += datestr + str2;
		return orderno;
	}
	
	// 获取所有的可用的金融公司
	public List< TFinance > getEnableFinance()
	{
		String[] propertyName = { "NEnable" , "NApply" };
		Object[] value = { SystemConstants.SYS_ENABLE , SystemConstants.SYS_ENABLE };
		List< TFinance > finances = financeDao.findByPropertys( propertyName , value );
		return finances;
	}
	
	public void updateCleanBefore( TFinance entity )
	{
		financeDao.updateCleanBefore( entity );
	}
}
