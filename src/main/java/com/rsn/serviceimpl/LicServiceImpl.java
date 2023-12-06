package com.rsn.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rsn.exception.RecordNotFoundException;
import com.rsn.model.Lic;
import com.rsn.repository.LicRepo;
import com.rsn.service.LicService;

@Service
public class LicServiceImpl implements LicService {

	@Autowired
	private LicRepo licRepo;

	@Override
	public Lic createLicId(Lic lic) {
		// TODO Auto-generated method stub
		return licRepo.save(lic);
	}

	public List<Lic> getLicById(Integer licId) throws RecordNotFoundException {
		List<Lic> lics = licRepo.findByLicId(licId);
		if (lics != null) {
			return lics;
		} else {
			throw new RecordNotFoundException();
		}
	}

}