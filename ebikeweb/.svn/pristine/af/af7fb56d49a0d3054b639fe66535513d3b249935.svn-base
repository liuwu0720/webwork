package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ICauseDao;
import com.clt.sub.model.TCause;
import com.clt.sub.service.ICauseService;
import com.clt.util.SystemConstants;

@Service
public class CauseServiceImp implements ICauseService
{
	@Autowired
	private ICauseDao causeDao;
	
	/**
	 * 按类型获得原因数据
	 * 
	 * @param type
	 * @return
	 */
	public List< TCause > getByType( int type )
	{
		return causeDao.findByPropertys( new String[] { "NEnable" , "NType" } ,
		        new Object[] { SystemConstants.SYS_ENABLE , type } );
	}
	
}
