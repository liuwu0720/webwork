package com.clt.sub.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TCity;
import com.clt.sub.model.TProvince;
import com.clt.sub.service.ICityService;
import com.mangofactory.swagger.annotations.ApiIgnore;

/**
 * @Description 获取省份，城市相关的接口
 * @author chengwzh
 * @date 2015/8/6
 * 
 */
@RequestMapping( "/cityAction" )
@Controller
@ApiIgnore
public class CityAction
{
	@Autowired
	private ICityService cityService;
	
	@RequestMapping( "/intoAddr" )
	public String intoAddr( HttpServletRequest request )
	{
		List< TProvince > provinces = getAllProvince();
		JSONArray arr = JSONArray.fromObject( provinces );
		System.out.println( "provinces:" + arr );
		request.setAttribute( "provinces" , provinces );
		return "citySelector/addr";
	}
	
	@RequestMapping( "/intoAddr1" )
	public String intoAddr1( HttpServletRequest request )
	{
		List< TProvince > provinces = getAllProvince();
		JSONArray arr = JSONArray.fromObject( provinces );
		System.out.println( "provinces:" + arr );
		request.setAttribute( "provinces" , provinces );
		return "citySelector/addr1";
	}
	
	@RequestMapping( "/intoAddr2" )
	public String intoAddr2( HttpServletRequest request )
	{
		List< TProvince > provinces = getAllProvince();
		JSONArray arr = JSONArray.fromObject( provinces );
		System.out.println( "provinces:" + arr );
		request.setAttribute( "provinces" , provinces );
		return "citySelector/addr2";
	}
	
	@RequestMapping( "/intoStartAddr" )
	public String intoStartAddr( HttpServletRequest request )
	{
		List< TProvince > provinces = getAllProvince();
		JSONArray arr = JSONArray.fromObject( provinces );
		System.out.println( "provinces:" + arr );
		request.setAttribute( "provinces" , provinces );
		return "citySelector/startAddr";
	}
	
	@RequestMapping( "/intoEndAddr" )
	public String intoEndAddr( HttpServletRequest request )
	{
		List< TProvince > provinces = getAllProvince();
		JSONArray arr = JSONArray.fromObject( provinces );
		System.out.println( "provinces:" + arr );
		request.setAttribute( "provinces" , provinces );
		return "citySelector/endAddr";
	}
	
	@RequestMapping( "/getAllProvince" )
	@ResponseBody
	public List< TProvince > getAllProvince()
	{
		return cityService.getAllProvince();
	}
	
	@RequestMapping( "/getCitysByProID" )
	@ResponseBody
	public List< TCity > getCitysByProID(
	        @RequestParam( value = "id" , required = true ) int id )
	{
		List< TCity > citys = cityService.getCitysByProID( id );
		JSONArray arr = JSONArray.fromObject( citys );
		System.out.println( "citys:" + arr );
		return citys;
	}
}
