package com.example.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.dto.AreaDto;
import com.example.restaurant.dto.DistrictDto;
import com.example.restaurant.dto.StateDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
import com.example.restaurant.model.Area;
import com.example.restaurant.model.District;
import com.example.restaurant.model.State;
import com.example.restaurant.repository.AreaRepository;
import com.example.restaurant.repository.DistrictRepository;
import com.example.restaurant.repository.StateRepository;
import com.example.restaurant.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AreaRepository areaRepo;
	
	@Autowired
	private DistrictRepository districtRepo;
	
	@Autowired
	private StateRepository stateRepo;

	public void insertArea(AreaDto areadto) throws ContraintViolationException{
		if(areadto!=null) {
		Area area = new Area();
		area.setId(areadto.getId());
		area.setName(areadto.getName());
		area.setPincode(areadto.getPincode());
		area.setIsActive(areadto.getIsActive());
		areaRepo.save(area);
		}
		else {
			throw new ContraintViolationException("Please enter the proper data");
		}
	}

	public void deleteArea(Long id) throws BusinessServiceException{
			if(areaRepo.existsById(id)){
					areaRepo.deleteById(id);
			}
			else {
				throw new BusinessServiceException("Id does not exists");
			}	
		
	}

	public void insertDistrict(DistrictDto districtdto) throws ContraintViolationException {
		if(districtdto!=null) {
			District district = new District();
			district.setId(districtdto.getId());
			district.setName(districtdto.getName());
			district.setIsActive(districtdto.getIsActive());
			districtRepo.save(district);
			}
			else {
				throw new ContraintViolationException("Please enter the proper data");
			}
		
	}

	public void deleteDistrict(Long id) throws BusinessServiceException {
		if(districtRepo.existsById(id)){
			districtRepo.deleteById(id);
	}
	else {
		throw new BusinessServiceException("Id does not exists");
	}	
		
	}

	public void insertState(StateDto statedto) throws ContraintViolationException {
	if(statedto!=null) {
		State state = new State();
		state.setId(statedto.getId());
		state.setName(statedto.getName());
		state.setIsActive(statedto.getIsActive());
		stateRepo.save(state);
	}
	else {
		throw new ContraintViolationException("Please enter the proper data");
	}
		
	}

	public void deleteState(Long id) throws BusinessServiceException {
		if(stateRepo.existsById(id)){
			stateRepo.deleteById(id);
	}
	else {
		throw new BusinessServiceException("Id does not exists");
	}	
		
	}
	
	
	

}
