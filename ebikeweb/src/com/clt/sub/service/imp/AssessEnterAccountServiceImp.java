/** 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-7-21 下午3:33:45 
 * @version V1.0 
 */
package com.clt.sub.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ITassessEnterAccountDao;
import com.clt.sub.model.TAssessEnterAccount;
import com.clt.sub.service.IAssessEnterAccountService;

/** 
 * @Package com.clt.sub.service.imp 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author liuwu 
 * @date 2015-7-21 下午3:33:45 
 * @version V1.0 
 */
@Service
public class AssessEnterAccountServiceImp implements IAssessEnterAccountService
{
	@Autowired
	ITassessEnterAccountDao iTassessEnterAccountDao;
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param tAssessEnterAccount
	 * @author liuwu
	 * @create_date 2015-7-21 下午3:35:04
	 */
	public void saveUpdate( TAssessEnterAccount tAssessEnterAccount )
	{
		// TODO Auto-generated method stub
		iTassessEnterAccountDao.saveOrUpdate( tAssessEnterAccount );
	}
	
}
