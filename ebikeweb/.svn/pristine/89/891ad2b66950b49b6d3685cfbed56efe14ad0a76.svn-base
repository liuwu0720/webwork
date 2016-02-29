package com.clt.systemmanger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.systemmanger.model.TDept;
import com.clt.systemmanger.service.IDeptService;
import com.clt.util.AjaxUtil;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: 部门action
 * @author hjx
 * @date 2014年7月22日 下午3:33:27
 * @version V1.0
 */
@Controller
@RequestMapping( "/dept" )
@ApiIgnore
public class DeptAction
{
	
	@Autowired
	private IDeptService deptService;
	
	/**
	 * @Description: 跳转到部门管理
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月22日 下午3:55:14
	 */
	@RequestMapping( "/mangerDept" )
	public String mangerDept( HttpServletRequest request )
	{
		List< TDept > list = deptService.loadByEnableAndSort();
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			TDept dept = list.get( 0 );
			JSONObject deptJson = JSONObject.fromObject( dept );
			request.setAttribute( "dept" , deptJson.toString() );
		}
		JSONObject obj = JSONObject.fromObject( list );
		request.setAttribute( "deptJson" , obj.toString() );
		
		// 获得部门树
		return "";
	}
	
	/**
	 * @Description: 删除部门和子部门
	 * @param deptId
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月23日 上午9:52:16
	 */
	@RequestMapping( "/delDept" )
	public void delDept( @RequestParam( "deptId" ) String deptId ,
	        HttpServletResponse response )
	{
		boolean isdel = deptService.deleteByKey( Integer.parseInt( deptId ) );
		String msg = "";
		if ( isdel )
		{
			msg = "删除成功！该部门和子部门都删除！";
		}
		else
		{
			msg = "删除失败！该部门以及子部门还有用户，不能删除";
		}
		AjaxUtil.rendJson( response , isdel , msg );
	}
	
	/**
	 * @Description: 保存用户（新增或者更新）
	 * @param dept
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月23日 上午11:05:26
	 */
	@RequestMapping( "/save" )
	public void save( TDept dept , HttpServletResponse response )
	{
		try
		{
			deptService.saveOrUpdate( dept );
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败，失败原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 根据id获得部门信息 （部门树触发获得部门信息）
	 * @param deptId
	 * @return TDept 返回值描述
	 * @author hjx
	 * @create_date 2014年7月23日 上午11:08:27
	 */
	@RequestMapping( "/getDeptByid" )
	@ResponseBody
	public TDept getDeptByid( @RequestParam( "deptId" ) String deptId )
	{
		TDept dept = deptService.get( Integer.parseInt( deptId ) );
		return dept;
	}
	
}
