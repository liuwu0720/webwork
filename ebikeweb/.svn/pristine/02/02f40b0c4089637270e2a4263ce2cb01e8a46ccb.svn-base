package com.clt.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.clt.sub.model.TProduct;
import com.clt.sub.service.IProductService;
import com.clt.systemmanger.model.TMsgRecord;
import com.clt.systemmanger.model.TUser;
import com.clt.systemmanger.service.IMsgRecordService;
import com.clt.systemmanger.service.ITokenUserService;
import com.clt.systemmanger.service.IUserService;
import com.clt.util.PushUtils;
import com.clt.util.SystemConstants;

/**
 * @Package com.clt.task
 * @Description: 定时作业集合类
 * @author hjx
 * @date 2015年4月17日 下午5:40:51
 * @version V1.0
 */
@Component( "taskSet" )
public class TaskSet
{
	@Autowired
	private ITokenUserService tokenService;
	@Autowired
	IProductService iProductService;
	@Autowired
	IUserService iUserService;
	@Autowired
	IMsgRecordService iMsgRecordService;
	
	/**
	 * @Description: 每10分钟，检查一次token，如果过期，清除token void
	 * @author hjx
	 * @create_date 2015年4月17日 下午5:43:55
	 */
	@Scheduled( cron = "0 0/5 * * * ?" )
	public void tokenReview()
	{
		System.out.println( "每10分钟，检查一次token，如果过期，清除token" );
		// tokenService.delLogout();
		// System.out.println( "如果过期，清除token" );
	}
	
	@Scheduled( fixedRate = 1000 * 10 * 1 )
	public void heartbeat()
	{
		// System.out.println( "心跳更新... " + new Date() );
	}
	
	/**
	 * 
	 * @Description: void CRON表达式 含义 "0 0 12 * * ?" 每天中午十二点触发 "0 15 10 ? * *"
	 *               每天早上10：15触发 "0 15 10 * * ?" 每天早上10：15触发 "0 15 10 * * ? *"
	 *               每天早上10：15触发 "0 15 10 * * ? 2005" 2005年的每天早上10：15触发
	 *               "0 * 14 * * ?" 每天从下午2点开始到2点59分每分钟一次触发 "0 0/5 14 * * ?"
	 *               每天从下午2点开始到2：55分结束每5分钟一次触发 "0 0/5 14,18 * * ?"
	 *               每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发 "0 0-5 14 * * ?"
	 *               每天14:00至14:05每分钟一次触发 "0 10,44 14 ? 3 WED"
	 *               三月的每周三的14：10和14：44触发 "0 15 10 ? * MON-FRI"
	 *               每个周一、周二、周三、周四、周五的10：15触发
	 * @author liuwu
	 * @create_date 2015-8-17 下午4:16:19
	 */
	@Scheduled( cron = "0 15 10 * * ?" )
	public void checkKillInfo()
	{
		String[] properties = { "NEnable" , "NEnd" };
		Object[] values = { SystemConstants.SYS_ENABLE , 1 };
		// 查询出有效的，未结束的
		List< TProduct > tProducts = iProductService.findByProperties( properties ,
		        values );
		for ( TProduct tProduct : tProducts )
		{
			if ( DateUtils.truncate( new Date() , Calendar.DAY_OF_MONTH ).compareTo(
			        tProduct.getDtKillEnd() ) == 0 )
			{
				// 秒杀时间与系统时间当天要过期的时候提醒
				// 消息推送
				Map< String , String > map = new HashMap< String , String >();
				map.put( "msgType" , "2" );
				map.put( "id" , tProduct.getId() + "" );
				TUser bossUser = iUserService.getByid( tProduct.getIUserId() + "" );
				List< TUser > tUsers = new ArrayList< TUser >();
				tUsers.add( bossUser );
				PushUtils pushUtils = new PushUtils( "抢单过期提醒，请点击查看" , "您发布的抢单编号为"
				        + tProduct.getVcKillno() + "今天即将过期，请选择中标人！" , tUsers ,
				        "com.unlcn.carrier.release.ReleaseBidSelectionActivity" , map );
				pushUtils.run();
				// 保存消息记录
				TMsgRecord tMsgRecord = new TMsgRecord();
				tMsgRecord.setIUser( tProduct.getIUserId() );// 添加人ID
				tMsgRecord.setVcAdduser( bossUser.getVcAccount() );
				tMsgRecord.setIUserAccept( bossUser.getId() );
				tMsgRecord.setNMsgType( 1 );// 单发
				tMsgRecord.setVcContent( "您发布的抢单编号为" + tProduct.getVcKillno()
				        + "今天即将过期，请选择中标人！" );
				tMsgRecord.setVcTitle( "抢单过期提醒，请点击查看" );
				iMsgRecordService.save( tMsgRecord );
			}
			else if ( DateUtils.truncate( new Date() , Calendar.DAY_OF_MONTH ).compareTo(
			        tProduct.getDtKillEnd() ) > 0 )
			{
				// 已过期的
				tProduct.setNEnd( 0 );
				iProductService.update( tProduct );
			}
		}
		
	}
	
	/**
	 * @Description: 每天凌晨3点15分，检查抢单里 历史订单并且没人投标的抢单， 
	 * 把他们设置为中标者废标，方便发布者重新发布或者废标
	 *               void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月10日 下午8:28:15
	 */
	@Scheduled( cron = "0 15 3 * * ?" )
	public void checkNoSkill()
	{
		List< TProduct > list = iProductService.findOutTimeAndNotKillProduct();
		if ( CollectionUtils.isNotEmpty( list ) )
		{
			for ( TProduct product : list )
			{
				product.setNBid( 2 );
				iProductService.update( product );
			}
		}
	}
}
