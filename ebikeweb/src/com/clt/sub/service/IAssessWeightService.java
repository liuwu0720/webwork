package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.systemmanger.model.TAssessWeight;
import com.clt.util.HqlHelper;

public interface IAssessWeightService
{
	public TAssessWeight get( Integer id );
	
	public void save( TAssessWeight entity );
	
	public void update( TAssessWeight entity );
	
	public void delete( TAssessWeight entity );
	
	public Map< String , Object > findByHql( HqlHelper hql );
	
	public Map< String , Object > findAll();
	
	// 获取当前用户下所有的表
	public List< Map< String , Object >> getAlltables();
	
	// 获取表中所有的字段，注释
	public List< Map< String , Object >> getAllFields( String tableName );
	
	// 根据表名获取注释
	public Map< String , Object > getCommentByName( String tableName );
}
