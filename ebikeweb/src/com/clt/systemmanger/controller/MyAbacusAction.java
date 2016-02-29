/**
 * @Description: TODO(用一句话描述该文件做什么)
 * @author liuwu
 * @date 2015-4-27 下午1:51:18
 * @version V1.0
 */
package com.clt.systemmanger.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clt.sub.model.TArkilometer;
import com.clt.sub.model.TCity;
import com.clt.sub.model.TCustomer;
import com.clt.sub.model.TDriverCost;
import com.clt.sub.model.TSubCarStyle;
import com.clt.sub.model.TSubsuppliers;
import com.clt.sub.service.IArkilometerService;
import com.clt.sub.service.ICityService;
import com.clt.sub.service.ICustomerSerivce;
import com.clt.sub.service.IDriverCostService;
import com.clt.sub.service.ISubCarStyleService;
import com.clt.sub.service.ISubsuppliersService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.AjaxUtil;
import com.clt.util.Page;
import com.clt.util.ServiceUtil;
import com.clt.util.SystemConstants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Package com.clt.systemmanger.controller
 * @Description: TODO(我的小算盘)
 * @author liuwu
 * @date 2015-4-27 下午1:51:18
 * @version V1.0
 */
@Controller
@RequestMapping( "/myAbacusAction" )
@Api( value = "myAbacusAction-api" , description = "我的小算盘" , position = 5 )
public class MyAbacusAction
{
	@Autowired
	ICityService cityService;
	@Autowired
	ICustomerSerivce iCustomerSerivce;
	@Autowired
	ISubCarStyleService subCarStyleService;
	@Autowired
	ISubsuppliersService subService;
	@Autowired
	IArkilometerService arkilometerService;
	@Autowired
	IDriverCostService iDriverCostService;
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @param response
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-20 上午11:16:08
	 */
	@ApiOperation( value = "删除我的小算当前列表" , notes = "删除我的小算当前列表" )
	@RequestMapping( value = "delete" , method = RequestMethod.POST )
	public void delete(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "应收公里表ID(如果编辑的时候，该字段不能为空)" , required = true ) @RequestParam( value = "KILOID" , required = true ) String KILOID ,
	        @ApiParam( value = "应收单价表ID(如果编辑的时候，该字段不能为空)" , required = true ) @RequestParam( value = "COSTID" , required = true ) String COSTID )
	{	
		int costId = Integer.parseInt( COSTID );
		int kiloId = Integer.parseInt( KILOID );
		try
		{
			TDriverCost tDriverCost = iDriverCostService.get( costId );
			TArkilometer tArkilometer = arkilometerService.get( kiloId );
			tDriverCost.setNEnable( SystemConstants.SYS_DISABLE );
			tArkilometer.setNEnable( SystemConstants.SYS_DISABLE );
			iDriverCostService.saveOrUpdate( tDriverCost );
			arkilometerService.saveOrUpdate( tArkilometer );
			AjaxUtil.rendJson( response , true , "操作成功！" );
		}
		catch ( Exception e )
		{
			AjaxUtil.rendJson( response , false , "操作失败" );
		}

	}

	/**
	 * 
	 * @Description: TODO(获取我的小算盘列表)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-19 上午10:23:51
	 */
	@ApiOperation( value = "获取我的小算盘列表" , notes = "KILOID:应收公里表ID<br/>VC_START_CITY:起始地城市<br/>"
	        + "I_START_ID:起始地城市ID<br/>;I_END_ID:目的地城市ID<br/>COSTID:应收单价表id<br/>"
	        + "VC_END_CITY:目的地城市<br/>N_KILOMETER:公里数<br/>N_COST:价格<br/>VC_CARNAME:商品车车名;<br/>I_SUB_CARID:商品车ID<br/>"
	        + "N_TYPE:计价方式：1:单公里;2:单台<br/>VC_CUSTOMER:客户<br/>I_CUSTOMERID:客户ID<br/>DT_START:起始日期<br/>DT_END:结束日期<br/>" )
	@RequestMapping( value = "/getMyAbacusList" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getMyAbacusList( HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "vcCustomer" , required = false ) @RequestParam( value = "vcCustomer" , required = false ) String vcCustomer )
	{
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		int archid = user.getiArchive();
		TSubsuppliers sub = subService.get( archid );
		String sql = "select t.id as kiloid, t.vc_start_city,t.i_start_id,t.i_end_id, t.vc_end_city,t.n_kilometer,t.DT_START,t.DT_END,"
		        + "td.id as costid ,td.n_cost,td.vc_carname,td.I_SUB_CARID,td.n_type,td.vc_customer,td.I_CUSTOMERID,td.n_total "
		        + " from T_ARKILOMETER t, t_Driver_Cost td where t.i_start_id = td.i_start_id and t.i_end_id = td.i_end_id"
		        + " and t.i_customerid = td.i_customerid and t.n_enable=0 and td.n_enable=0  and t.vc_subno=td.vc_subno and td.vc_subno='"
		        + sub.getVcSubno() + "'";
		if ( StringUtils.isNotBlank( vcCustomer ) )
		{
			sql += " and td.vc_customer = '" + vcCustomer + "'";
		}
		sql += " order by t.id ";
		System.out.println( "sql = " + sql );
		Page page = ServiceUtil.getcurrPage( request );
		Map< String , Object > resultMap;
		try
		{
			resultMap = arkilometerService.findBySpringSql( sql , page );
			return AjaxUtil.getMapByNotException( true , resultMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}
	
	/**
	 * 
	 * @Description: TODO(根据应收单价表id和应收公里表id获取详情)
	 * @param request
	 * @return Map<String,Object> 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-20 上午9:55:52
	 */
	@ApiOperation( value = "编辑查看：根据应收单价表id和应收公里表id获取详情接口" , notes = "KILOID:应收公里表ID<br/>VC_START_CITY:起始地城市<br/>"
	        + "I_START_ID:起始地城市ID<br/>;I_END_ID:目的地城市ID<br/>COSTID:应收单价表id<br/>"
	        + "VC_END_CITY:目的地城市<br/>N_KILOMETER:公里数<br/>N_COST:价格<br/>VC_CARNAME:商品车车名;<br/>I_SUB_CARID:商品车ID<br/>"
	        + "N_TYPE:计价方式：1:单公里;2:单台<br/>VC_CUSTOMER:客户<br/>I_CUSTOMERID:客户ID<br/>DT_START:起始日期<br/>DT_END:结束日期<br/>" )
	@RequestMapping( value = "/getMyAbacusDetail" , method = RequestMethod.POST )
	@ResponseBody
	public Map< String , Object > getMyAbacusDetail(
	        HttpServletRequest request ,
	        @ApiParam( value = "应收公里表ID(如果编辑的时候，该字段不能为空)" , required = true ) @RequestParam( value = "KILOID" , required = true ) String KILOID ,
	        @ApiParam( value = "应收单价表ID(如果编辑的时候，该字段不能为空)" , required = true ) @RequestParam( value = "COSTID" , required = true ) String COSTID )

	{
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		int archid = user.getiArchive();
		TSubsuppliers sub = subService.get( archid );
		String sql = "select t.id as kiloid, t.vc_start_city,t.i_start_id,t.i_end_id, t.vc_end_city,t.n_kilometer,t.DT_START,t.DT_END,"
		        + "td.id as costid ,td.n_cost,td.vc_carname,td.I_SUB_CARID,td.n_type,td.vc_customer,td.I_CUSTOMERID,td.n_total "
		        + " from T_ARKILOMETER t, t_Driver_Cost td where t.i_start_id = td.i_start_id and t.i_end_id = td.i_end_id"
		        + " and t.i_customerid = td.i_customerid and t.n_enable=0 and td.n_enable=0  and t.vc_subno=td.vc_subno and td.vc_subno='"
		        + sub.getVcSubno()
		        + "' and t.id="
		        + Integer.parseInt( KILOID )
		        + " and td.id=" + Integer.parseInt( COSTID ) + "";
		Page page = ServiceUtil.getcurrPage( request );
		Map< String , Object > resultMap;
		try
		{
			resultMap = arkilometerService.findBySpringSql( sql , page );
			return AjaxUtil.getMapByNotException( true , resultMap );
		}
		catch ( Exception e )
		{
			return AjaxUtil.getMapByException( e );
		}
		
	}

	/**
	 * 
	 * @Description: TODO(我的小算盘中新增修改接口)
	 * @param request
	 * @param response
	 * @param paramType
	 * @param startCityId
	 * @param endCityId
	 * @param carId
	 * @param priceType
	 * @param kilometer
	 * @param price
	 * @param dtStart
	 * @param dtEnd
	 * @param customerId
	 *            void 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-19 上午10:24:03
	 */
	@RequestMapping( value = "/saveMyAbacus" , method = RequestMethod.POST )
	@ApiOperation( value = "我的小算盘中新增修改接口" , notes = "" )
	public void saveMyAbacus(
	        HttpServletRequest request ,
	        HttpServletResponse response ,
	        @ApiParam( value = "add为新增、update为修改(注:修改的话应收公里kiloId和应收单价costId不能为空)" , required = true ) @RequestParam( value = "paramType" , required = true ) String paramType ,
	        @ApiParam( value = "应收公里表ID(如果编辑的时候，该字段不能为空)" , required = false ) @RequestParam( value = "KILOID" , required = false ) String KILOID ,
	        @ApiParam( value = "应收单价表ID(如果编辑的时候，该字段不能为空)" , required = false ) @RequestParam( value = "COSTID" , required = false ) String COSTID ,
	        @ApiParam( value = "起始地城市ID" , required = true ) @RequestParam( value = "I_START_ID" , required = true ) String I_START_ID ,
	        @ApiParam( value = "目的地城市ID" , required = true ) @RequestParam( value = "I_END_ID" , required = true ) String I_END_ID ,
	        @ApiParam( value = "车型ID（获取接口:subsuppliersAction/getCarList）" , required = true ) @RequestParam( value = "I_SUB_CARID" , required = true ) String I_SUB_CARID ,
	        @ApiParam( value = "计价方式：1:单公里;2:单台" , required = true ) @RequestParam( value = "N_TYPE" , required = true ) String N_TYPE ,
	        @ApiParam( value = "总价：1:单公里时总价=单公里价*公里数;2:单台时总价=单台价；由手机端进行计算得到" , required = true ) @RequestParam( value = "N_TOTAL" , required = true ) String N_TOTAL ,
	        @ApiParam( value = "总公里(如果选择单公里方式，此字段不能为空; 选择单台时可以为空)" , required = false ) @RequestParam( value = "N_KILOMETER" , required = false ) String N_KILOMETER ,
	        @ApiParam( value = "价格" , required = true ) @RequestParam( value = "N_COST" , required = true ) String N_COST ,
	        @ApiParam( value = "开始日期(string类型，格式如：2015-1-18)" , required = true ) @RequestParam( value = "DT_START" , required = true ) String DT_START ,
	        @ApiParam( value = "结束日期(string类型，格式如：2015-11-18)" , required = true ) @RequestParam( value = "DT_END" , required = true ) String DT_END ,
	        @ApiParam( value = "客户ID(获取接口:subsuppliersAction/getCustomer)" , required = true ) @RequestParam( value = "I_CUSTOMERID" , required = true ) String I_CUSTOMERID )
	{
		int iStartId = Integer.parseInt( I_START_ID );
		int iEndId = Integer.parseInt( I_END_ID );
		int iCustId = Integer.parseInt( I_CUSTOMERID );
		int iCarId = Integer.parseInt( I_SUB_CARID );
		TUser user = ( TUser ) request.getSession().getAttribute( "user" );
		DateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd" );
		boolean isTrue = isDateBefore( DT_START , DT_END );
		if ( ! isTrue )
		{
			AjaxUtil.rendJson( response , false , "开始日期应该在结束日期之前！" );
		}
		else
		{
			try
			{
				TCity startCity = cityService.getCityByID( iStartId );
				TCity endCity = cityService.getCityByID( iEndId );
				TCustomer tCustomer = iCustomerSerivce.get( iCustId );
				TSubCarStyle tSubCarStyle = subCarStyleService.get( iCarId );
				int archid = user.getiArchive();
				TSubsuppliers sub = subService.get( archid );
				
				// 应收公里
				TArkilometer tArkilometer = new TArkilometer();

				tArkilometer.setiCustomerId( iCustId );
				tArkilometer.setIUpdateUser( user.getId() );
				tArkilometer.setVcUpdateUser( user.getVcAccount() );
				tArkilometer.setIStartId( startCity.getId() );
				tArkilometer.setIEndId( endCity.getId() );
				tArkilometer.setDtStart( format1.parse( DT_START ) );
				tArkilometer.setDtEnd( format1.parse( DT_END ) );
				tArkilometer.setVcStartCity( startCity.getCityname() );
				tArkilometer.setVcEndCity( endCity.getCityname() );
				tArkilometer.setVcSubno( sub.getVcSubno() );
				tArkilometer.setVcCustomer( tCustomer.getVcCustomerName() );
				tArkilometer.setiCustomerId( iCustId );
				tArkilometer.setNEnable( SystemConstants.SYS_ENABLE );
				tArkilometer.setDtUpdate( new Date() );
				
				// 应收单价
				TDriverCost tDriverCost = new TDriverCost();
				tDriverCost.setDtStart( format1.parse( DT_START ) );
				tDriverCost.setDtEnd( format1.parse( DT_END ) );
				tDriverCost.setVcSubno( sub.getVcSubno() );
				tDriverCost.setVcUpdateUser( user.getVcAccount() );
				tDriverCost.setVcStartCity( startCity.getCityname() );
				tDriverCost.setVcEndCity( endCity.getCityname() );
				tDriverCost.setICustomerid( iCustId );
				tDriverCost.setVcCustomer( tCustomer.getVcCustomerName() );
				tDriverCost.setIStartId( startCity.getId() );
				tDriverCost.setIEndId( endCity.getId() );
				tDriverCost.setISubCarid( iCarId );
				tDriverCost.setnCost( Float.parseFloat( N_COST ) );
				tDriverCost.setVcCarName( tSubCarStyle.getVcCarStyle() );
				tDriverCost.setNEnable( SystemConstants.SYS_ENABLE );
				tDriverCost.setDtAdd( new Date() );
				tDriverCost.setnTotal( Float.parseFloat( N_TOTAL ) );
				if ( N_TYPE.equals( "1" ) )// 1:单公里;2:单台
				{
					tArkilometer
					        .setNKilometer( Float.parseFloat( N_KILOMETER ) );
					tDriverCost.setnType( 1 );

				}
				else
				{
					tArkilometer.setNKilometer( Float.parseFloat( "1" ) );
					tDriverCost.setnType( 2 );
				}
				if ( paramType.equalsIgnoreCase( "add" ) )
				{
					
					String message = checkIfExiste( tArkilometer , tDriverCost );// 验证应收公里，应收单价是否重复
					if ( message.equalsIgnoreCase( "success" ) )
					{
						arkilometerService.save( tArkilometer );
						iDriverCostService.save( tDriverCost );
						AjaxUtil.rendJson( response , true , "保存成功！" );
					}
					else
					{
						AjaxUtil.rendJson( response , false , message );
					}

				}
				else
				// 编辑
				{
					TArkilometer tArkilometer2 = arkilometerService
					        .get( Integer.parseInt( KILOID ) );
					TDriverCost tDriverCost2 = iDriverCostService.get( Integer
					        .parseInt( COSTID ) );
					String message = "success";
					int kilometerId = tArkilometer2.getId();
					int driverId = tDriverCost2.getId();
					
					if ( tArkilometer2.getIStartId().equals(
					        tArkilometer.getIStartId() )
					        && tArkilometer2.getIEndId().equals(
					                tArkilometer.getIEndId() )
					        && tArkilometer2.getiCustomerId().equals(
					                tArkilometer.getiCustomerId() ) )
					{
						
						BeanUtils.copyProperties( tArkilometer2 , tArkilometer );
						tArkilometer2.setId( kilometerId );
					}
					else
					{
						List< TArkilometer > tArkilometers = arkilometerService
						        .checkIfExiste( tArkilometer );
						if ( tArkilometers.size() > 0 )
						{
							message = "【" + tArkilometer.getVcStartCity()
							        + "】至【" + tArkilometer.getVcEndCity()
							        + "】客户【" + tArkilometer.getVcCustomer()
							        + "】的应收公里已维护，不能有重复数据！";
						}
						else
						{
							BeanUtils.copyProperties( tArkilometer2 ,
							        tArkilometer );
							tArkilometer2.setId( kilometerId );
						}
					}
					
					if ( tDriverCost2.getIStartId().equals(
					        tDriverCost.getIStartId() )
					        && tDriverCost2.getIEndId().equals(
					                tDriverCost.getIEndId() )
					        && tDriverCost2.getICustomerid().equals(
					                tDriverCost.getICustomerid() )
					        && tDriverCost2.getISubCarid().equals(
					                tDriverCost.getISubCarid() ) )
					{
						BeanUtils.copyProperties( tDriverCost2 , tDriverCost );
						tDriverCost2.setId( driverId );
					}
					else
					{
						List< TDriverCost > tDriverCosts = iDriverCostService
						        .checkIfExist( tDriverCost );
						if ( tDriverCosts.size() > 0 )
						{
							message = "【" + tDriverCost.getVcStartCity()
							        + "】至【" + tDriverCost.getVcEndCity()
							        + "】客户【" + tDriverCost.getVcCustomer()
							        + "】车型【" + tDriverCost.getVcCarName()
							        + "】的应收单价已 维护，不能有重复数据！";
						}
						else
						{
							BeanUtils.copyProperties( tDriverCost2 ,
							        tDriverCost );
							tDriverCost2.setId( driverId );
						}
					}
					
					if ( message.equalsIgnoreCase( "success" ) )
					{
						arkilometerService.update( tArkilometer2 );
						tDriverCost2.setnTotal( tDriverCost2.getnCost()
						        * tArkilometer2.getNKilometer() );
						iDriverCostService.update( tDriverCost2 );
						AjaxUtil.rendJson( response , true , message );
					}
					else
					{
						AjaxUtil.rendJson( response , false , message );
					}

				}

			}
			catch ( Exception e )
			{
				AjaxUtil.rendJson( response , false , "保存失败！" + e.getMessage() );
			}
		}


	}
	
	/**
	 * @Description: TODO(比较时间)
	 * @param dT_START
	 * @param dT_END
	 * @return boolean 返回值描述
	 * @author liuwu
	 * @create_date 2015-9-1 上午9:59:45
	 */
	private boolean isDateBefore( String dtStart , String dtEnd )
	{
		try
		{
			DateFormat df = new SimpleDateFormat( "yyyy-MM-dd" );
			System.out.println( df.parse( dtStart ) );
			return df.parse( dtStart ).before( df.parse( dtEnd ) );
		}
		catch ( Exception e )
		{
			return false;
		}

	}

	/**
	 * @Description: TODO(验证应收公里，应收单价是否重复)
	 * @param tArkilometer
	 * @param tDriverCost
	 * @return String 返回值描述
	 * @author liuwu
	 * @create_date 2015-5-19 上午11:30:42
	 */
	private String checkIfExiste( TArkilometer tArkilometer ,
	        TDriverCost tDriverCost )
	{
		String message = "success";
		try
		{
			List< TArkilometer > tArkilometers = arkilometerService
			        .checkIfExiste( tArkilometer );
			if ( tArkilometers.size() > 0 )
			{
				message = "【" + tArkilometer.getVcStartCity() + "】至【"
				        + tArkilometer.getVcEndCity() + "】客户【"
				        + tArkilometer.getVcCustomer() + "】的应收公里已维护，不能有重复数据！";
			}
			else
			{
				List< TDriverCost > tDriverCosts = iDriverCostService
				        .checkIfExist( tDriverCost );
				if ( tDriverCosts.size() > 0 )
				{
					message = "【" + tDriverCost.getVcStartCity() + "】至【"
					        + tDriverCost.getVcEndCity() + "】客户【"
					        + tDriverCost.getVcCustomer() + "】车型【"
					        + tDriverCost.getVcCarName()
					        + "】的应收单价已 维护，不能有重复数据！";
				}
			}

		}
		catch ( Exception e )
		{
			message = e.getMessage();
		}
		return message;
	}

}
