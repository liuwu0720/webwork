package com.clt.sub.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.IShipVinDao;
import com.clt.sub.model.TShipVin;
import com.clt.sub.service.IShipVinService;
import com.clt.util.HqlHelper;
import com.clt.util.Page;
import com.clt.util.SystemConstants;

@Service
public class ShipVinServiceImp implements IShipVinService
{
	@Autowired
	private IShipVinDao vinDao;
	
	public TShipVin get( int id )
	{
		return vinDao.get( id );
	}
	
	public void save( TShipVin entity )
	{
		vinDao.save( entity );
	}
	
	public void update( TShipVin entity )
	{
		vinDao.update( entity );
	}
	
	public Map< String , Object > getAllByShipno( String shipno , Page page )
	{
		// String[] propertyNames = { "vcShipno" , "NEnable" };
		// Object[] values = { shipno , SystemConstants.SYS_ENABLE };
		// List< TShipVin > result = vinDao.findByPropertys( propertyNames ,
		// values );
		HqlHelper hql = new HqlHelper( TShipVin.class );
		hql.setQueryPage( page );
		hql.addEqual( "vcShipno" , shipno.trim() );
		hql.addEqual( "nEnable" , SystemConstants.SYS_ENABLE );
		Map< String , Object > result = vinDao.findAllByHqlHelp( hql );
		return result;
	}
}
