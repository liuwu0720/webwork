package com.clt.sub.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TNotepad;
import com.clt.sub.service.INotepadService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping( value = "/notepadAction" , method = RequestMethod.POST )
@Controller
@Api( value = "NotepadAction-api" , description = "记事本操作api" , position = 5 )
public class NotepadAction
{
	@Autowired
	private INotepadService notepadService;
	
	/**
	 * @Description 查询所有记事本记录并根据日期排序
	 * @param request
	 * @author chengwzh
	 * @create date 2015/5/5 15:50
	 */
	@RequestMapping( value = "/findAllByDate" , method = RequestMethod.POST )
	@ApiOperation( value = "查询全部记事" , notes = "查询全部记事" , position = 5 )
	@ResponseBody
	public Map< String , Object > findAllByDate(
	        @ApiParam( value = "记事本类型id" ) @RequestParam( value = "typeId" , required = false ) Integer typeId ,
	        HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		Integer userId = user.getId();// 获取用户id
		System.out.println( "userId:" + userId );
		Page page = ServiceUtil.getcurrPage( request );
		// HqlHelper helper = new HqlHelper( TNotepad.class );
		// helper.setQueryPage( page );
		// helper.addEqual( "IUser" , userId );
		// helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );
		// helper.addOrderBy( "dtAdd" );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			Map< String , Object > result = notepadService.findAllNotepads( page ,
			        userId , typeId );
			List< Map< String , Object >> rows = ( List< Map< String , Object >> ) result
			        .get( "rows" );
			int totalMoney = 0;// 总金额
			totalMoney = notepadService.getTotalMoney( userId , typeId );
			result.put( "totalMoney" , totalMoney );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 增加记事本
	 * @param request
	 * @param vcNote
	 * @param NMoney
	 * @author chengwzh
	 * @create date 2015/5/6 10:40
	 */
	@RequestMapping( value = "/addNotepad" , method = RequestMethod.POST )
	@ApiOperation( value = "增加记事本" , notes = "增加记事本" , position = 5 )
	@ResponseBody
	public Map< String , Object > addNotepad(
	        HttpServletRequest request ,
	        @ModelAttribute TNotepad entity ,
	        @ApiParam( "登记事项,是以“;”拼接的字符串" ) @RequestParam( value = "types" , required = false ) String types ,
	        HttpServletResponse response )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		try
		{
			if ( null != entity && null != entity.getIType() && entity.getIType() != 0 )
			{
				entity.setIUser( userId );
				notepadService.save( entity );
			}
			
			if ( StringUtils.isNotBlank( types ) )
			{
				notepadService.saveNoteType( types );
			}
			
			return AjaxUtil.getMap( true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 获取所有的笔记本类型
	 * @author chengwzh
	 * @date 2015/5/18
	 */
	@RequestMapping( value = "/getAllTypes" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取所有的笔记本类型" , notes = "获取所有的笔记本类型:<br/>"
	        + "list 参数说明：list是笔记本类型集合 ，笔记本类型属性如下<br/>" + "private Integer id;<br/>"
	        + "private Integer NEnable;<br/>" + "private Date dtAdd;<br/>"
	        + "private String vcType;<br/>" + "private Integer IUser;<br/>" , position = 5 )
	public Map< String , Object > getAllTypes( HttpSession session )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		try
		{
			Map< String , Object > types = notepadService.getAllTypes( userId );
			Map< String , Object > result = AjaxUtil.getMapByNotException( true , types );
			Map< String , Object > data = ( Map< String , Object > ) result.get( "data" );
			List< Map< String , Object >> rows = ( List< Map< String , Object >> ) data
			        .get( "rows" );
			result.put( "data" , rows );
			return result;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
}
