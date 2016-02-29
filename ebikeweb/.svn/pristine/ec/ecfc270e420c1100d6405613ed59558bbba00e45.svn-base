package com.clt.sub.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clt.sub.dao.ITruckDriverDao;
import com.clt.sub.model.TTruckDriver;
import com.clt.sub.service.ISubsuppliersPubService;

@Service
public class SubsuppliersPubServiceImp implements ISubsuppliersPubService
{
	
	@Autowired ITruckDriverDao truckDriverDao;
	
	/*
	 *  param  String subno 分供方编号
	 *  return 该分供方下的所有司机车辆信息
	 */
	public List< TTruckDriver > getAllTruckDriverByID(String subno)
    {
		List<TTruckDriver> alldriver = truckDriverDao.findByProperty( "vcSubno" , subno );
	    return alldriver;
    }	
	
	
}
