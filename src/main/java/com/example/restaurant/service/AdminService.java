package com.example.restaurant.service;

import com.example.restaurant.dto.AreaDto;
import com.example.restaurant.dto.DistrictDto;
import com.example.restaurant.dto.StateDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;

public interface AdminService {
	public void insertArea(AreaDto areadto) throws ContraintViolationException;
	public void deleteArea(Long id) throws BusinessServiceException;
	public void insertDistrict(DistrictDto districtdto) throws ContraintViolationException;
	public void deleteDistrict(Long id) throws BusinessServiceException;
	public void insertState(StateDto statedto) throws ContraintViolationException;
	public void deleteState(Long id) throws BusinessServiceException;
	

}
