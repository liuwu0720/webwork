package com.clt.sub.service;

import java.util.List;

import com.clt.sub.model.TTruckDriver;

public interface ISubsuppliersPubService
{	
	List<TTruckDriver> getAllTruckDriverByID(String subno);
}
