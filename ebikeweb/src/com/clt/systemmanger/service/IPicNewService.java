package com.clt.systemmanger.service;

import java.util.List;

import com.clt.systemmanger.model.TPicNew;

/**
 * @Package com.clt.systemmanger.service
 * @Description: 图片新闻服务类
 * @author hjx
 * @date 2014年7月28日 下午4:26:35
 * @version V1.0
 */
public interface IPicNewService
{
	public TPicNew get( Integer id );
	
	public void update( TPicNew entity );
	
	public void save( TPicNew entity );
	
	public void saveOrUpdate( TPicNew entity );
	
	public void delete( TPicNew entity );
	
	public void deleteByKey( Integer id );
	
	public List< TPicNew > loadAll();
	
	/**
	 * @Description: 获得正在展示的图片新闻
	 * @return List<TPicNew> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:31:06
	 */
	public List< TPicNew > loadByDisplay();
	
	/**
	 * @Description: 获得正在展示的图片新闻的HTML
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:35:01
	 */
	public String getPicDisplayHtml();
	
	/**
	 * @Description: 获得正在展示的文字新闻的HTML
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月28日 下午4:35:01
	 */
	public String getNewDisplayHtml();
}
