package com.clt.sub.service.imp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.common.UserSession;
import com.clt.sub.dao.IArriveCodeDao;
import com.clt.sub.model.TArriveCode;
import com.clt.sub.service.IArriveCodeService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.SystemConstants;

@Service
public class ArriveCodeServiceImp implements IArriveCodeService
{
	
	@Autowired
	private IArriveCodeDao codeDao;
	
	/**
	 * @Description: 发送短信验证码成功时，
	 * 把验证码和指令号，订单号，客户运单号保存到数据包，方便抵达验证！ 
	 * @param vcShipno 指令号
	 * @param vcOrderno 订单号
	 * @param vcCustOrderno 客户运单号
	 * @param code 验证码
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月23日 下午2:49:54
	 */
	public void saveCode( String vcShipno , String vcOrderno , String vcCustOrderno ,
	        String code )
	{
		TArriveCode acode = new TArriveCode();
		TUser user = ( TUser ) UserSession.get( "user" );
		if ( null != user )
		{
			acode.setIUser( user.getId() );
			acode.setVcUserName( user.getVcAccount() );
		}
		acode.setDtAdd( new Date() );
		acode.setVcCode( code );
		acode.setVcCustOrderno( vcCustOrderno );
		acode.setVcOrderno( vcOrderno );
		acode.setVcShipno( vcShipno );
		
		codeDao.save( acode );
		
	}
	
	/**
	 * @Description: 查询对应订单编号是否需要核对验证码
	 * @param vcOrderno 订单编号
	 * @return 
	 *   boolean 返回值描述
	 * @author hjx
	 * @create_date 2015年9月23日 下午2:57:59
	 */
	public boolean hasCodeByOrderNo( String vcOrderno )
	{
		List< TArriveCode > codes = codeDao.findByPropertys( new String[] { "NEnable" ,
		        "vcOrderno" } , new Object[] { SystemConstants.SYS_ENABLE , vcOrderno } );
		
		if ( CollectionUtils.isNotEmpty( codes ) )
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 通过订单编号和验证码，去数据库核对是否一致，
	 * 					如果设置为已经验证，并返回true，否则返回false
	 * @param vcOrderno 订单编号
	 * @param code 验证码
	 * @param vcLongitude 经度
	 * @param vcLatitude 纬度
	 * @param vcArrivePlace 确认抵达的详细地址
	 * @return 
	 *   boolean 返回值描述
	 * @author hjx
	 * @create_date 2015年9月23日 下午2:59:57
	 */
	public boolean isCheckSuccess( String vcOrderno , String code , String vcLongitude ,
	        String vcLatitude , String vcArrivePlace )
	{
		List< TArriveCode > codes = codeDao.findByPropertys( new String[] { "NEnable" ,
		        "vcOrderno" , "vcCode" } , new Object[] { SystemConstants.SYS_ENABLE ,
		        vcOrderno , code } );
		
		if ( CollectionUtils.isNotEmpty( codes ) )
		{
			for ( TArriveCode tArriveCode : codes )
			{
				TUser user = ( TUser ) UserSession.get( "user" );
				
				if ( null != user )
				{
					tArriveCode.setICheckUser( user.getId() );
					tArriveCode.setVcCheckUserName( user.getVcAccount() );
				}
				tArriveCode.setDtCheck( new Date() );
				tArriveCode.setVcLatitude( vcLatitude );
				tArriveCode.setVcLongitude( vcLongitude );
				tArriveCode.setVcArrivePlace( vcArrivePlace );
				codeDao.update( tArriveCode );
				// 请求erp，把验证码回写到erp里
				postToErp( vcOrderno , code , tArriveCode );
			}
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 请求erp，把收货确定码回写到erp里
	 * @param vcOrderno 订单编号
	 * @param code 收货确定码
	 * @param tArriveCode  抵达确定码记录
	 *   void 返回值描述
	 * @author hjx
	 * @create_date 2015年9月25日 上午9:36:35
	 */
	private void postToErp( String vcOrderno , String code , TArriveCode tArriveCode )
	{
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost( SystemConstants.ERP_SEND_CODE );
		// 创建参数队列
		List< NameValuePair > formparams = new ArrayList< NameValuePair >();
		
		// 建立一个10位的随机数
		int j = ( int ) ( ( Math.random() * 9 + 1 ) * 1000000000 );
		String sessionid = String.valueOf( j );
		// 添加erp中间件需要的参数
		formparams.add( new BasicNameValuePair( "userno" ,
		        SystemConstants.ERP_SEND_MSG_USERNO ) );
		formparams.add( new BasicNameValuePair( "pwd" , sessionid ) );
		formparams.add( new BasicNameValuePair( "company" ,
		        SystemConstants.ERP_SEND_MSG_COMPANY ) );
		formparams.add( new BasicNameValuePair( "shipNo" , tArriveCode.getVcShipno() ) );// 指令号
		
		formparams.add( new BasicNameValuePair( "orderNo" , vcOrderno ) );// 单号
		
		formparams.add( new BasicNameValuePair( "code" , code ) );// 确认码
		
		formparams.add( new BasicNameValuePair( "mobile" , "" ) );// 做确认的手机号
		
		formparams.add( new BasicNameValuePair( "dateConfirm" , tArriveCode.getDtCheck()
		        .toString() ) );// 确认时间
		
		formparams.add( new BasicNameValuePair( "address" , tArriveCode
		        .getVcArrivePlace() ) );// 确认地址
		
		UrlEncodedFormEntity uefEntity;
		try
		{
			uefEntity = new UrlEncodedFormEntity( formparams , "UTF-8" );
			httppost.setEntity( uefEntity );
			System.out.println( "executing request " + httppost.getURI() );
			CloseableHttpResponse response = httpclient.execute( httppost );
			try
			{
				HttpEntity entity = response.getEntity();
				if ( entity != null )
				{
					String resultStr = EntityUtils.toString( entity , "UTF-8" );
					System.out.println( "--------------------------------------" );
					System.out.println( "Response content: " + resultStr );
					System.out.println( "--------------------------------------" );
					JSONObject fromObject = JSONObject.fromObject( resultStr );
					if ( null != fromObject )
					{
						String result = ( String ) fromObject.get( "result" );
						if ( null != result )
						{
							result = result.trim();
							if ( ! "0".equals( result ) )// 为0，说明发送成功
							{
								String exceptionStr = fromObject.get( "addInfo" )
								        .toString();
								// 记录异常信息
								
							}
						}
					}
				}
			}
			finally
			{
				response.close();
			}
		}
		catch ( ClientProtocolException e )
		{
			e.printStackTrace();
		}
		catch ( UnsupportedEncodingException e1 )
		{
			e1.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			// 关闭连接,释放资源
			try
			{
				httpclient.close();
			}
			catch ( IOException e )
			{
				e.printStackTrace();
			}
		}
	}
	
}
