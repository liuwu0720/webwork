package com.clt.systemmanger.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clt.systemmanger.model.TArchiveType;
import com.clt.systemmanger.service.IArchiveTypeService;
import com.clt.util.AjaxUtil;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: 档案类型action
 * @author hjx
 * @date 2014年7月19日 下午2:36:44
 * @version V1.0
 */
@Controller
@RequestMapping( "/archiveType" )
@ApiIgnore
public class ArchiveTypeAction
{
	
	@Autowired
	IArchiveTypeService atService;
	
	/**
	 * @Description: 档案管理页面（列表页面）
	 * @return String 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 下午3:39:45
	 */
	@RequestMapping( "/getAllType" )
	public String getAllType( HttpServletRequest request )
	{
		List< TArchiveType > aTypes = atService.loadAllByEnable();
		request.setAttribute( "aTypes" , aTypes );
		return "";// 跳转到档案管理页面（列表页面）
	}
	
	/**
	 * @Description: 新增或者编辑的 保存档案信息
	 * @param atype
	 * @param response
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月19日 下午3:44:54
	 */
	@RequestMapping( "/sOuAType" )
	public void sOuAType( TArchiveType atype , HttpServletResponse response )
	{
		try
		{
			atService.saveOrUpdate( atype );
			AjaxUtil.rendJson( response , true , "保存成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , true , "保存失败！失败原因：" + e.getMessage() );
		}
		
	}
	
	/**
	 * @Description: 档案逻辑删除，设置为无效
	 * @param typeId
	 * @param response
	 *            void 操作结果json
	 * @author hjx
	 * @create_date 2014年7月19日 下午4:19:54
	 */
	@RequestMapping( "/delAType" )
	public void delAType( @RequestParam( "typeId" ) String typeId ,
	        HttpServletResponse response )
	{
		try
		{
			TArchiveType atype = atService.get( Integer.parseInt( typeId ) );
			atype.setNEnable( 1 );
			atService.update( atype );
			AjaxUtil.rendJson( response , true , "删除成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , true , "删除失败！失败原因：" + e.getMessage() );
		}
		
	}
}
