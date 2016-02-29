package com.clt.sub.service;

import java.util.List;
import java.util.Map;

import com.clt.sub.model.TShipStatus;

public interface ITruckMapService
{
	public List< Map< String , Object >> getCarNoList( String subno );
	
	public List< Map< String , Object >> findFinesByHash( String hash );
	
	// 获取已运抵的状态
	public TShipStatus getArrivedStatus( int headId );
	
	// 获取已发运的状态
	public TShipStatus getForwardStatus( int headId );
	
	public List< Map< String , Object >> getFixByHash( String hash );
	
	public List< Map< String , Object >> getAccidentByHash( String hash );
	
	public List< Map< String , Object >> getChargeList( int truckId );
	
	public List< Map< String , Object >> getTrafficByHash( String hash );
}
