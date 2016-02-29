package com.clt.systemmanger.service;

import java.util.List;
import java.util.Map;

import com.clt.systemmanger.model.TTokenUser;

/**
 * @Package com.clt.systemmanger.service
 * @Description: token用户登录记录服务泪
 * @author hjx
 * @date 2015年4月17日 上午10:48:24
 * @version V1.0
 */
public interface ITokenUserService
{
	/**
	 * @Description: 新增用户token在线信息，新增时验证，该用户是否有token信息，如果有更新token时间，如果没有新增token。
	 * @param tokenUser
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2015年4月17日 上午10:49:07
	 */
	public void saveTokenUser( TTokenUser tokenUser );
	
	public Map< String , Object > updateLast( String token );
	
	public List< TTokenUser > getList();
	
	public void delToken( TTokenUser tokenUser );
	
	public void delToken( Integer tokenId );
	
	public void delToken( String token );
	
	public void delLogout();
	
	public TTokenUser getToken( String token );
	
	public TTokenUser getTokenByUserId( int userid );
	
	public List< TTokenUser > getTokenListByUserId( int userid );
	
}
