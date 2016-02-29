package com.clt.finance.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.finance.dao.ILoanAskDao;
import com.clt.finance.model.TLoanAsk;
import com.clt.finance.service.ILoanAskService;
import com.clt.sub.dao.ILoanDao;
import com.clt.sub.dao.ILoanTypeDao;
import com.clt.sub.model.TLoanType;
import com.clt.sub.service.ILoanTypeService;
import com.clt.systemmanger.service.IStaticService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.finance.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月17日 上午10:55:13
 * @version V1.0
 */
@Service
public class LoanAskServiceImp implements ILoanAskService
{
	@Autowired
	private ILoanAskDao loanAskDao;
	@Autowired
	private JdbcTemplate jdbcDao;
	@Autowired
	private ILoanDao loanDao;
	@Autowired
	private ILoanTypeDao typeDao;
	@Autowired
	private ILoanTypeService typeService;
	@Autowired
	private IStaticService staticService;
	
	public TLoanAsk get( Integer id )
	{
		return loanAskDao.get( id );
	}
	
	public void update( TLoanAsk entity )
	{
		loanAskDao.update( entity );
	}
	
	public void save( TLoanAsk entity )
	{
		loanAskDao.save( entity );
	}
	
	public void saveOrUpdate( TLoanAsk entity )
	{
		loanAskDao.saveOrUpdate( entity );
	}
	
	public void delete( TLoanAsk entity )
	{
		loanAskDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		loanAskDao.deleteByKey( id );
	}
	
	public List< TLoanAsk > loadAll()
	{
		return loanAskDao.loadAll();
	}
	
	public List< TLoanAsk > findAll( Page page )
	{
		return loanAskDao.findAll( page );
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return loanAskDao.findAllByHqlHelp( helper );
	}
	
	// 获取待办贷款
	public Map< String , Object > getWaitLoanList( Page page , String financeno ,
	        String vcApplyUserName )
	{
		String sql = "select a.id,l.vc_loanno,l.i_apply_user,l.n_loan_total,l.dt_deadline,l.n_payid,l.dt_apply,l.vc_apply_user_name "
		        + "from t_loan_ask a,t_loan l where a.i_loan=l.id and a.n_enable="
		        + SystemConstants.SYS_ENABLE
		        + " and l.n_flag="
		        + SystemConstants.SYS_LOAN_FLAG_COMMIT + " and a.N_REPULSE=0";
		if ( StringUtils.isNotBlank( vcApplyUserName ) )
		{
			sql += " and l.VC_APPLY_USER_NAME like '%" + vcApplyUserName + "%'";
		}
		sql += " and a.vc_financeno='" + financeno + "' order by DT_APPLY desc";
		System.out.println( "sql:" + sql );
		Map< String , Object > result = loanDao.getSpringSQL( sql , page );
		List< Map< String , Object >> list = ( List< Map< String , Object >> ) result
		        .get( "rows" );
		for ( Map< String , Object > map : list )
		{
			Object payIdObj = map.get( "N_PAYID" );
			if ( payIdObj != null )
			{
				int payId = Integer.parseInt( payIdObj.toString() );
				TLoanType type = typeService.get( payId );
				if ( type != null )
				{
					String payName = type.getVcTypename();
					map.put( "payName" , payName );
				}
				else
				{
					map.put( "payName" , "暂无" );
				}
				
			}
			else
			{
				map.put( "payName" , "暂无" );
			}
		}
		result.put( "rows" , list );
		return result;
	}
	
	// 获取已办贷款
	public Map< String , Object > getDoneLoans( Page page , String financeNo ,
	        String vcApplyUserName )
	{
		String sql = "SELECT A.ID,L.VC_LOANNO,L.VC_APPLY_USER_NAME,L.N_LOAN_TOTAL,"
		        + "L.DT_DEADLINE,L.N_PAYID,L.DT_APPLY,A.VC_USER_NAME,A.DT_APPROVAL"
		        + " FROM T_LOAN L,T_LOAN_ASK A WHERE L.ID=A.I_LOAN " + " AND A.N_ENABLE="
		        + SystemConstants.SYS_ENABLE;
		if ( StringUtils.isNotBlank( vcApplyUserName ) )
		{
			sql += " and l.VC_APPLY_USER_NAME like '%" + vcApplyUserName + "%'";
		}
		sql += " AND A.N_REPULSE=1 AND A.VC_FINANCENO='" + financeNo
		        + "' ORDER BY A.DT_APPROVAL desc";
		Map< String , Object > result = loanDao.getSpringSQL( sql , page );
		List< Map< String , Object >> list = ( List< Map< String , Object >> ) result
		        .get( "rows" );
		for ( Map< String , Object > map : list )
		{
			Object payIdObj = map.get( "N_PAYID" );
			if ( payIdObj != null )
			{
				int payId = Integer.parseInt( payIdObj.toString() );
				TLoanType type = typeService.get( payId );
				if ( type != null )
				{
					map.put( "payName" , type.getVcTypename() );
				}
				else
				{
					map.put( "payName" , "暂无" );
				}
			}
			else
			{
				map.put( "payName" , "暂无" );
			}
		}
		result.put( "rows" , list );
		return result;
	}
	
	/**
	 * 根据id获取贷款
	 */
	public Map< String , Object > getLoanById( int id )
	{
		String sql = "select l.id,l.VC_APPLY_USER_NAME,l.DT_APPLY,l.N_GETID,l.VC_SUB_ALL_NAME,"
		        + "l.VC_EXCUSE,l.N_LOAN_TOTAL,l.DT_DEADLINE,l.N_RATE,l.N_PAYID,l.VC_NOTE,l.VC_APPRAISE,"
		        + "l.N_IFAPPAISE,l.N_SCORE,a.N_ACCRUAL,a.N_FEE,a.DT_ACCRUAL  from t_loan l,t_loan_ask a where l.id=a.i_loan and a.id="
		        + id;
		List< Map< String , Object >> list = jdbcDao.queryForList( sql );
		if ( CollectionUtils.isEmpty( list ) )
		{
			return null;
		}
		Map< String , Object > map = list.get( 0 );
		Object payIdObj = map.get( "N_PAYID" );
		if ( payIdObj == null )
		{
			map.put( "payName" , "无" );
		}
		else
		{
			int payId = Integer.parseInt( payIdObj.toString() );
			TLoanType type1 = typeService.get( payId );
			map.put( "payName" , type1.getVcTypename() );
		}
		Object getIdObj = map.get( "N_GETID" );
		if ( getIdObj == null )
		{
			map.put( "getName" , "无" );
		}
		else
		{
			int getId = Integer.parseInt( getIdObj.toString() );
			TLoanType type2 = typeService.get( getId );
			map.put( "getName" , type2.getVcTypename() );
		}
		return map;
	}
	
	// 图片路径处理方法
	public String pathManage( String path )
	{
		String rootPath = staticService.getStringByParame( "finance" );
		if ( ! rootPath.endsWith( "/" ) )
		{
			rootPath += "/";
		}
		return rootPath + path;
	}
}
