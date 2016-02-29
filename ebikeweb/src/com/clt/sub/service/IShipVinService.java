package com.clt.sub.service;

import java.util.Map;

import com.clt.sub.model.TShipVin;
import com.clt.util.Page;

public interface IShipVinService
{
	public TShipVin get( int id );
	
	public void save( TShipVin entity );
	
	public void update( TShipVin entity );
	
	public Map< String , Object > getAllByShipno( String shipno , Page page );
}
