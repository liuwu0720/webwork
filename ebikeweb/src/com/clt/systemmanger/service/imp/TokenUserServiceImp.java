package com.clt.systemmanger.service.imp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.ITokenUserDao;
import com.clt.systemmanger.model.TTokenUser;
import com.clt.systemmanger.service.ITokenUserService;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: token 服务类，处理手机用户登录的token
 * @author hjx
 * @date 2015年4月17日 下午4:43:30
 * @version V1.0
 */
@Service
public class TokenUserServiceImp implements ITokenUserService
{
	@Autowired
	private ITokenUserDao tokenDao;
	
	/**
	 * @return the tokenDao
	 */
	public ITokenUserDao getTokenDao()
	{
		return tokenDao;
	}
	
	/**
	 * @param tokenDao
	 *            the tokenDao to set
	 */
	public void setTokenDao( ITokenUserDao tokenDao )
	{
		this.tokenDao = tokenDao;
	}
	
	/**
	 * @Description: 新增用户token在线信息，新增时验证，该用户是否有token信息，如果有更新token时间，如果没有新增token。
	 * @param tokenUser
	 * @author hjx
	 * @create_date 2015年4月17日 下午4:43:30
	 */
	public void saveTokenUser( TTokenUser tokenUser )
	{
		Integer userId = tokenUser.getIUser();
		List< TTokenUser > tokens = tokenDao.findByProperty( "IUser" , userId );
		if ( CollectionUtils.isNotEmpty( tokens ) && tokens.size() > 0 )
		{
			TTokenUser tokenOld = tokens.get( 0 );
			tokenOld.setDtLasttime( new Date() );
			tokenDao.update( tokenOld );
			tokenUser = null;
		}
		else
		{
			tokenDao.save( tokenUser );
		}
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param token
	 * @author hjx
	 * @create_date 2015年4月17日 下午4:43:30
	 */
	public Map< String , Object > updateLast( String token )
	{
		Map< String , Object > map = new HashMap< String , Object >();
		List< TTokenUser > tokens = tokenDao.findByProperty( "vcToken" , token );
		if ( CollectionUtils.isNotEmpty( tokens ) )
		{
			TTokenUser tokenUser = tokens.get( 0 );
			tokenUser.setDtLasttime( new Date() );
			tokenDao.update( tokenUser );
			map.put( "isSuccess" , true );
			map.put( "message" , "更新成功！" );
		}
		else
		{
			map.put( "isSuccess" , false );
			map.put( "message" , "会话已经过时请重新登陆！" );
		}
		
		return map;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2015年4月17日 下午4:43:30
	 */
	public List< TTokenUser > getList()
	{
		
		return tokenDao.findAll();
	}
	
	/**
	 * @Description: 删除token登陆信息
	 * @param tokenUser
	 * @author hjx
	 * @create_date 2015年4月17日 下午4:43:30
	 */
	public void delToken( TTokenUser tokenUser )
	{
		tokenDao.delete( tokenUser );
	}
	
	/**
	 * @Description: 删除token登陆信息
	 * @param tokenId
	 * @author hjx
	 * @create_date 2015年4月17日 下午4:43:30
	 */
	public void delToken( Integer tokenId )
	{
		tokenDao.deleteByKey( tokenId );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param token
	 * @author hjx
	 * @create_date 2015年4月17日 下午4:43:30
	 */
	public void delToken( String token )
	{
		tokenDao.deleteByProperty( "vcToken" , token );
	}
	
	/**
	 * @Description: 检查当前时间内，所有的token是否有效，如果三十分前没有更新，则视为无效，作业调度运行
	 * @author hjx
	 * @create_date 2015年4月17日 下午4:43:30
	 */
	public void delLogout()
	{
		List< TTokenUser > tokens = tokenDao.findAll();
		for ( TTokenUser token : tokens )
		{
			long last = token.getDtLasttime().getTime();
			long nlast = new Date().getTime();
			if ( ( nlast - last ) > ( 30 * 60 * 1000 ) )
			{
				// 如果10分钟前，没更新last，则视为该token无效
				tokenDao.delete( token );
				
			}
			
		}
		
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param token
	 * @return
	 * @author hjx
	 * @create_date 2015年4月17日 下午6:17:01
	 */
	public TTokenUser getToken( String token )
	{
		List< TTokenUser > tokens = tokenDao.findByProperty( "vcToken" , token );
		if ( CollectionUtils.isNotEmpty( tokens ) )
		{
			return tokens.get( 0 );
		}
		return null;
	}
	
	public TTokenUser getTokenByUserId( int userid )
	{
		List< TTokenUser > tokens = tokenDao.findByProperty( "IUser" , userid );
		if ( CollectionUtils.isNotEmpty( tokens ) && tokens.size() > 0 )
		{
			TTokenUser tokenOld = tokens.get( 0 );
			return tokenOld;
		}
		return null;
	}
	
	public List< TTokenUser > getTokenListByUserId( int userid )
	{
		List< TTokenUser > tokens = tokenDao.findByProperty( "IUser" , userid );
		if ( CollectionUtils.isNotEmpty( tokens ) && tokens.size() > 0 )
		{
			return tokens;
		}
		return null;
	}
	
}
