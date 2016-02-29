/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-8 下午4:37:47 
 * @version V1.0 
 */
package com.clt.sub.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IApplyPicDao;
import com.clt.sub.model.TApplyPic;
import com.clt.sub.service.IDamagePicService;
import com.clt.util.HqlHelper;

/** 
 * @Package com.clt.sub.service.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-1-8 下午4:37:47 
 * @version V1.0 
 */
@Service
public class DamagePicServiceImp implements IDamagePicService
{
	@Autowired
	IApplyPicDao iApplyPicDao;

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param hql
	 * @return
	 * @author liuwu
	 * @create_date 2015-1-8 下午4:50:22
	 */
	public  Map< String , Object > findAllByHqlHelp( HqlHelper hql ){
		// TODO Auto-generated method stub
		return iApplyPicDao.findAllByHqlHelp( hql );
	}
	

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tApplyPic
	 * @author liuwu
	 * @create_date 2015-6-4 下午5:26:48
	 */
	public void saveApplyTdamagePic( TApplyPic tApplyPic )
	{
		iApplyPicDao.save( tApplyPic );
		
	}
	
}
