package com.clt.systemmanger.service.imp;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.systemmanger.dao.ICltDao;
import com.clt.systemmanger.dao.IDeptDao;
import com.clt.systemmanger.dao.IUserDao;
import com.clt.systemmanger.model.TClt;
import com.clt.systemmanger.model.TDept;
import com.clt.systemmanger.service.IDeptService;

/**
 * @Package com.clt.systemmanger.service.imp
 * @Description: TODO(用一句话描述该文件做什么)
 * @author hjx
 * @date 2014年7月12日 下午4:28:00
 * @version V1.0
 */
@Service
public class DeptServiceImp implements IDeptService
{
	@Autowired
	private IDeptDao deptDao;
	
	@Autowired
	private ICltDao cltDao;
	
	@Autowired
	private IUserDao userDao;
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public TDept get( Integer id )
	{
		return deptDao.get( id );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void update( TDept entity )
	{
		deptDao.update( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void save( TDept entity )
	{
		deptDao.save( entity );
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public void saveOrUpdate( TDept entity )
	{
		deptDao.saveOrUpdate( entity );
	}
	
	/**
	 * @Description: 如果部门和子部门都没有用户，才可以删除； 删除部门，并把子部门删除，部门下的用户删除
	 * @param entity
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public boolean delete( TDept entity )
	{
		List< TDept > slist = deptDao.findByProperty( "IDept" , entity.getId() );
		if ( isDel( slist ) )
		{
			deleteByList( slist );
			deptDao.delete( entity );
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 如果部门和子部门都没有用户，才可以删除；删除部门，并把部门下的用户删除
	 * @param id
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public boolean deleteByKey( Integer id )
	{
		List< TDept > slist = deptDao.findByProperty( "IDept" , id );
		if ( isDel( slist ) )
		{
			deleteByList( slist );
			deptDao.deleteByKey( id );
			return true;
		}
		return false;
	}
	
	/**
	 * @Description: 删除子部门和子部门
	 * @param list
	 *            void 返回值描述
	 * @author hjx
	 * @create_date 2014年7月22日 下午3:42:46
	 */
	private void deleteByList( List< TDept > list )
	{
		for ( TDept dept : list )
		{
			List< TDept > slist = deptDao.findByProperty( "IDept" , dept.getId() );
			if ( CollectionUtils.isNotEmpty( slist ) )
			{
				deleteByList( slist );
			}
			deptDao.delete( dept );
			
		}
	}
	
	/**
	 * @Description: 判断是否可以删除
	 * @param list
	 * @return boolean 如果该部门以及子部门下没有用户可以删除，否则不能删除
	 * @author hjx
	 * @create_date 2014年7月23日 上午9:28:07
	 */
	private boolean isDel( List< TDept > list )
	{
		boolean isdel = true;
		for ( TDept dept : list )
		{
			List< TClt > cltList = cltDao.findByProperty( "IDept" , dept.getId() );
			if ( CollectionUtils.isNotEmpty( cltList ) )
			{
				isdel = false;
				break;
			}
			else
			{
				List< TDept > slist = deptDao.findByProperty( "IDept" , dept.getId() );
				isdel = isDel( slist );
			}
		}
		return isdel;
	}
	
	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 * @author hjx
	 * @create_date 2014年7月12日 下午4:28:21
	 */
	public List< TDept > loadAll()
	{
		return deptDao.loadAll();
	}
	
	/**
	 * @Description: 加载有效的部门，并按排序字段升序 排序
	 * @return List<TDept> 返回值描述
	 * @author hjx
	 * @create_date 2014年7月23日 上午9:58:23
	 */
	public List< TDept > loadByEnableAndSort()
	{
		String queryString = "from TDept dept where dept.NEnable=0 order by  dept.NSort asc ";
		List< TDept > list = deptDao.find( queryString );
		return list;
	}
	
}
