package com.clt.sub.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TNotepadType;
import com.clt.sub.service.INotepadTypeService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.HqlHelper;
import com.clt.util.SystemConstants;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Api( value = "NotepadTypeAction-api" , description = "记事本类型操作api" , position = 5 )
@RequestMapping( value = "/notepadTypeAction" , method = RequestMethod.POST )
@Controller
public class NotepadTypeAction
{
	@Autowired
	private INotepadTypeService typeService;
	
	/**
	 * @Description 查询全部记事类型
	 * @author chengwzh
	 * @create date 2015/5/5 17:35
	 */
	@RequestMapping( value = "/findAllTypes" , method = RequestMethod.POST )
	@ResponseBody
	@ApiIgnore
	public Map< String , Object > findAllTypes( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		String visit = request.getParameter( SystemConstants.APP_VISIT_PARME );
		try
		{
			HqlHelper helper = new HqlHelper( TNotepadType.class );
			helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );// 根据有效值查询
			HqlHelper or = new HqlHelper( TNotepadType.class );
			or.addEqual( "IUser" , 0 );
			or.addEqual( "IUser" , user.getId() );
			helper.addOr( or );
			Map< String , Object > result = typeService.findByHelper( helper );
			return AjaxUtil.getMapByResult( visit , result );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
	
	/**
	 * @Description 增加记事类型
	 * @param vcType
	 * @param request
	 *            ,response
	 * @author chengwzh
	 * @create date 2015/5/6 9:13
	 */
	@RequestMapping( value = "/addType" , method = RequestMethod.POST )
	@ApiOperation( value = "增加记事本类型" , notes = "增加记事本类型" , response = TNotepadType.class , position = 5 )
	@ResponseBody
	public Map< String , Object > addType( HttpServletRequest request ,
	        @ApiParam( value = "记事本类型" , required = true ) @RequestParam String vcType )
	{
		HttpSession session = request.getSession();
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		TNotepadType type = new TNotepadType();
		type.setVcType( vcType );
		type.setIUser( userId );
		try
		{
			typeService.save( type );
			return AjaxUtil.getMap( true , "发布成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
}
