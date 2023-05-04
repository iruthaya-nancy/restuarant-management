package com.example.restaurant.service;

import java.util.List;

import com.example.restaurant.dto.AdminDto;
import com.example.restaurant.dto.AreaDto;
import com.example.restaurant.dto.DistrictDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.PaymentDto;
import com.example.restaurant.dto.StateDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
import com.example.restaurant.exception.InternalServerException;
import com.example.restaurant.exception.NotFoundException;

public interface AdminService {
	public void insertArea(AreaDto areadto) throws ContraintViolationException;
	public void deleteArea(Long id) throws BusinessServiceException;
	public void insertDistrict(DistrictDto districtdto) throws ContraintViolationException;
	public void deleteDistrict(Long id) throws BusinessServiceException;
	public void insertState(StateDto statedto) throws ContraintViolationException;
	public void deleteState(Long id) throws BusinessServiceException;
	public List<AreaDto> getArea() throws InternalServerException;
	public List<DistrictDto> getDistrict() throws InternalServerException;
	public List<StateDto> getState() throws InternalServerException;
	public List<PaymentDto> getPaymentMode() throws InternalServerException;
    public Long  authenticateAdmin(AdminDto adminDto)throws NotFoundException;
    public void updateStatus(Long id)throws NotFoundException;
}
