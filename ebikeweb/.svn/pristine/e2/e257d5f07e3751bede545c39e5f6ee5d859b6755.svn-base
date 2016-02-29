package com.clt.sub.service.imp;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.clt.common.UserSession;
import com.clt.sub.dao.INotepadDao;
import com.clt.sub.dao.INotepadTypeDao;
import com.clt.sub.model.TNotepad;
import com.clt.sub.model.TNotepadType;
import com.clt.sub.service.INotepadService;
import com.clt.systemmanger.model.TUser;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class NotepadServiceImp implements INotepadService
{
	@Autowired
	private INotepadDao notepadDao;
	@Autowired
	private INotepadTypeDao typeDao;
	@Autowired
	private JdbcTemplate jdbcDao;
	
	public void delete( TNotepad entity )
	{
		notepadDao.delete( entity );
	}
	
	public void deleteByKey( Integer id )
	{
		notepadDao.deleteByKey( id );
	}
	
	public List< TNotepad > findAll( Page page )
	{
		
		return notepadDao.findAll( page );
	}
	
	public Map< String , Object > findByHelper( HqlHelper helper )
	{
		return notepadDao.findAllByHqlHelp( helper );
	}
	
	public TNotepad get( Integer id )
	{
		return notepadDao.get( id );
	}
	
	public List< TNotepad > loadAll()
	{
		return notepadDao.loadAll();
	}
	
	public void save( TNotepad entity )
	{
		notepadDao.save( entity );
	}
	
	public void saveOrUpdate( TNotepad entity )
	{
		notepadDao.saveOrUpdate( entity );
	}
	
	public void update( TNotepad entity )
	{
		notepadDao.update( entity );
	}
	
	public int getTotalMoney( int userId , Integer typeId )
	{
		String sql = "select sum(n.n_money) total from t_notepad n,t_notepad_type t "
		        + " where n.i_type=t.id and n.i_user=" + userId + " and n.n_enable="
		        + SystemConstants.SYS_ENABLE;
		if ( typeId != null )
		{
			sql += " and n.I_TYPE=" + typeId;
		}
		int sum = jdbcDao.queryForInt( sql );
		return sum;
	}
	
	public Map< String , Object > findAllNotepads( Page page , int userId , Integer typeId )
	{
		String sql = "select n.id,n.n_enable,n.dt_add,n.i_user,n.i_type,n.vc_note,"
		        + "n.n_money,t.vc_type  from t_notepad n,t_notepad_type t "
		        + " where n.i_type=t.id and n.i_user=" + userId + " and n.n_enable="
		        + SystemConstants.SYS_ENABLE;
		if ( typeId != null )
		{
			sql += " and n.I_TYPE=" + typeId;
		}
		sql += " order by n.dt_add";
		Map< String , Object > result = notepadDao.getSpringSQL( sql , page );
		return result;
	}
	
	public Map< String , Object > getAllTypes( int userId )
	{
		// String[] propertyNames = { "NEnable" , "IUser" };
		// Object[] values = { SystemConstants.SYS_ENABLE , userId };
		// return typeDao.findByPropertys( propertyNames , values );
		HqlHelper helper = new HqlHelper( TNotepadType.class );
		helper.addEqual( "NEnable" , SystemConstants.SYS_ENABLE );// 根据有效值查询
		HqlHelper or = new HqlHelper( TNotepadType.class );
		or.addEqual( "IUser" , 0 );
		or.addEqual( "IUser" , userId );
		helper.addOr( or );
		Map< String , Object > result = typeDao.findAllByHqlHelp( helper );
		return result;
	}
	
	/**
	 * 保存记事本类型，types是以“;” 拼接的字符串。当检测到没有对应的类型，就保存
	 * 
	 * @param types
	 */
	public void saveNoteType( String types )
	{
		String[] arr = types.split( ";" );
		for ( String type : arr )
		{
			List< TNotepadType > list = typeDao.findByPropertys( new String[] {
			        "NEnable" , "vcType" } , new Object[] { SystemConstants.SYS_ENABLE ,
			        type } );
			if ( CollectionUtils.isEmpty( list ) )
			{
				TNotepadType ntype = new TNotepadType();
				ntype.setNEnable( SystemConstants.SYS_ENABLE );
				ntype.setVcType( type );
				TUser user = ( TUser ) UserSession.get( "user" );
				if ( null != user )
				{
					ntype.setIUser( user.getId() );
				}
				typeDao.save( ntype );
			}
		}
	}
}
