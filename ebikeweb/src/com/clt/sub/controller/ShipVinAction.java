package com.clt.sub.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TShipVin;
import com.clt.sub.service.IShipVinService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiOperation;

@RequestMapping( "/shipVinAction" )
@Controller
// @Api( value = "ShipVinAction-api" , description = "指令vin记录相关操作" , position =
// 5 )
@ApiIgnore
public class ShipVinAction
{
	@Autowired
	private IShipVinService vinService;
	
	@RequestMapping( value = "/save" , method = RequestMethod.POST )
	@ApiOperation( value = "保存指令vin码" , notes = "String vinstr：vin码拼接的字符串','隔开<br/>"
	        + "String shipno:指令号" , response = TShipVin.class , position = 5 )
	public void save( @RequestParam( value = "vinstr" ) String vinstr ,
	        @RequestParam( value = "shipno" ) String shipno , HttpSession session ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		int userId = user.getId();
		String username = user.getVcUsername();
		try
		{
			String[] vins = vinstr.split( "," );
			for ( String vin : vins )
			{
				TShipVin entity = new TShipVin();
				entity.setiUser( userId );
				entity.setVcAdduser( username );
				entity.setVcShipno( shipno );
				entity.setVcVin( vin );
				vinService.save( entity );
			}
			AjaxUtil.rendJson( response , true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , true , "保存失败，原因：" + e.getMessage() );
		}
	}
	
	@RequestMapping( "/getAllByShipno" )
	@ResponseBody
	@ApiIgnore
	// @ApiOperation( value = "" )
	public Map< String , Object > getAllByShipno(
	        @RequestParam( value = "shipno" ) String shipno , HttpServletRequest request )
	{
		try
		{
			Page page = ServiceUtil.getcurrPage( request );
			Map< String , Object > result = vinService.getAllByShipno( shipno , page );
			JSONArray arr = JSONArray.fromObject( result );
			System.out.println( "jsonArr" + arr );
			return result;
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
	}
}
