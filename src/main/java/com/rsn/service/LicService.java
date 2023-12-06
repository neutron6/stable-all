package com.rsn.service;

import java.util.List;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Lic;

public interface LicService {
	
    Lic createLicId(Lic lic);
    
    //List<Lic> getAllData();
	
    List<Lic> getLicById(Integer licId)throws RecordNotFoundException;
	
	
}
