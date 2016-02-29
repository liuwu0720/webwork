package com.clt.systemmanger.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.clt.systemmanger.model.TFeedback;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IFeedbackService;
import com.clt.util.AjaxUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping( value = "/feedbackAction" )
@Api( value = "feedback-api" , description = "有关意见与反馈的操作" , position = 5 )
public class FeedbackAction
{
	@Autowired
	private IFeedbackService feedbackService;
	
	/**
	 * @Description 增加意见反馈
	 * @param entity
	 * @param session
	 * @param response
	 */
	@RequestMapping( value = "/addFeedback" , method = RequestMethod.POST )
	@ApiOperation( value = "增加意见反馈" , notes = "增加意见反馈" , position = 5 , response = TFeedback.class )
	public void addFeedback( @ModelAttribute TFeedback entity , HttpSession session ,
	        HttpServletResponse response )
	{
		TUser user = ( TUser ) session.getAttribute( "user" );
		entity.setIUser( user.getId() );
		entity.setVcUsername( user.getVcUsername() );
		try
		{
			feedbackService.save( entity );
			AjaxUtil.rendJson( response , true , "保存成功" );
		}
		catch ( Exception e )
		{
			e.printStackTrace();
			AjaxUtil.rendJson( response , false , "保存失败：" + e.getMessage() );
		}
	}
}
