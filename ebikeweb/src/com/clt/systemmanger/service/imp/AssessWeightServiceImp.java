package com.clt.systemmanger.service.imp;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.sub.service.IAssessWeightService;
import com.clt.systemmanger.dao.IAssessWeightDao;
import com.clt.systemmanger.model.TAssessWeight;
import com.clt.util.HqlHelper;
import com.clt.util.SystemConstants;

@Service
public class AssessWeightServiceImp implements IAssessWeightService
{
	@Autowired
	private IAssessWeightDao weightDao;
	@Autowired
	private JdbcTemplate jdbcDao;
	
	public TAssessWeight get( Integer id )
	{
		return weightDao.get( id );
	}
	
	public void save( TAssessWeight entity )
	{
		weightDao.save( entity );
	}
	
	public void delete( TAssessWeight entity )
	{
		weightDao.delete( entity );
	}
	
	public void update( TAssessWeight entity )
	{
		weightDao.update( entity );
	}
	
	public Map< String , Object > findByHql( HqlHelper hql )
	{
		return weightDao.findAllByHqlHelp( hql );
	}
	
	public Map< String , Object > findAll()
	{
		HqlHelper hql = new HqlHelper( TAssessWeight.class );
		hql.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		return weightDao.findAllByHqlHelp( hql );
	}
	
	// 获取当前用户下所有的表
	public List< Map< String , Object >> getAlltables()
	{
		String sql = "select a.TABLE_NAME,b.COMMENTS from user_tables a,"
		        + "user_tab_comments b WHERE a.TABLE_NAME=b.TABLE_NAME"
		        + " and a.TABLE_NAME like '__ASSESS%' order by TABLE_NAME ";
		List< Map< String , Object >> list = jdbcDao.queryForList( sql );
		JSONArray arr = JSONArray.fromObject( list );
		System.out.println( "getAllTables:" + arr );
		return list;
	}
	
	// 获取表中所有的字段，注释
	public List< Map< String , Object >> getAllFields( String tableName )
	{
		String sql = "select TABLE_NAME,COLUMN_NAME,COMMENTS from user_col_comments "
		        + "where table_name='" + tableName + "'";
		List< Map< String , Object >> result = jdbcDao.queryForList( sql );
		JSONArray arr = JSONArray.fromObject( result );
		System.out.println( tableName + ":" + arr );
		return result;
	}
	
	// 根据表名获取注释
	public Map< String , Object > getCommentByName( String tableName )
	{
		String sql = "select TABLE_NAME,TABLE_TYPE,COMMENTS from user_tab_comments "
		        + "where TABLE_NAME='" + tableName + "'";
		List< Map< String , Object >> result = jdbcDao.queryForList( sql );
		return result.get( 0 );
	}
}
