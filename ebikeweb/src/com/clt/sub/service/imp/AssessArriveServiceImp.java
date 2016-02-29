/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-7-24 下午4:14:14 
 * @version V1.0 
 */
package com.clt.sub.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IAssessArriveDao;
import com.clt.sub.model.TAssessArrive;
import com.clt.sub.service.IAssessArriveService;

/** 
 * @Package com.clt.sub.service.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-7-24 下午4:14:14 
 * @version V1.0 
 */
@Service
public class AssessArriveServiceImp implements IAssessArriveService
{
	@Autowired
	IAssessArriveDao iAssessArriveDao;
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tAssessArrive
	 * @author liuwu
	 * @create_date 2015-7-24 下午4:16:41
	 */
	public void saveUpdate( TAssessArrive tAssessArrive )
	{
		// TODO Auto-generated method stub
		iAssessArriveDao.saveOrUpdate( tAssessArrive );
	}
	
}
