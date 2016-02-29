package com.clt.systemmanger.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.systemmanger.service.IPictureService;
import com.clt.util.AjaxUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RequestMapping( value = "/pictureAction" )
@Controller
@Api( value = "picture-api" , description = "图片路径操作" , position = 5 )
public class PictureAction
{
	@Autowired
	private IPictureService pictureService;
	
	@RequestMapping( value = "/getPathById" , method = RequestMethod.POST )
	@ResponseBody
	@ApiOperation( value = "获取图片路径" , notes = "获取图片路径" )
	public Map< String , Object > getPathById(
	        @ApiParam( value = "图片信息" ) @RequestParam( value = "id" ) int id )
	{
		try
		{
			String vcPath = pictureService.getPathById( id );
			if ( StringUtils.isNotBlank( vcPath ) )
			{
				return AjaxUtil.getMapByNotException( true , vcPath );
			}
			else
			{
				return AjaxUtil.getMapByNotException( false , null );
			}
			
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			return AjaxUtil.getMapByException( e );
		}
		
	}
}
