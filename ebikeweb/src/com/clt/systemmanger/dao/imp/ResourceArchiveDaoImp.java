package com.clt.systemmanger.dao.imp;

import org.springframework.stereotype.Repository;

import com.clt.basedao.imp.GenericHibernateDao;
import com.clt.systemmanger.dao.IResourceArchiveDao;
import com.clt.systemmanger.model.TResourceArchive;

/**
 * @Package com.clt.systemmanger.dao.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:14:59
 * @version V1.0
 */
@Repository
public class ResourceArchiveDaoImp extends
        GenericHibernateDao< TResourceArchive , Integer > implements IResourceArchiveDao
{	
	
}
