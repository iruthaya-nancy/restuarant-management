package com.example.restaurant.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restaurant.dto.AdminDto;
import com.example.restaurant.dto.AreaDto;
import com.example.restaurant.dto.DistrictDto;
import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.PaymentDto;
import com.example.restaurant.dto.StateDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
import com.example.restaurant.exception.InternalServerException;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.model.Admin;
import com.example.restaurant.model.Area;
import com.example.restaurant.model.Customer;
import com.example.restaurant.model.District;
import com.example.restaurant.model.Menu;
import com.example.restaurant.model.PaymentMode;
import com.example.restaurant.model.State;
import com.example.restaurant.repository.AdminRepository;
import com.example.restaurant.repository.AreaRepository;
import com.example.restaurant.repository.DistrictRepository;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.PaymentModeRepository;
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

	@Autowired
	private PaymentModeRepository paymentRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private MenuRepository menuRepo;
	
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

	@Override
	public List<AreaDto> getArea() throws InternalServerException {
		// TODO Auto-generated method stub
		
			List<Area> Areas = areaRepo.findAll();
			List<AreaDto> area = new ArrayList<>();
			for (Area a : Areas) {
				AreaDto dto = new AreaDto();
				dto.setId(a.getId());
				dto.setName(a.getName());
				//dto.setDescription(item.getDescription());
				//dto.setIsActive(item.getIsActive());
				dto.setPincode(a.getPincode());
			    area.add(dto);
			}

			return area;
		}

	@Override
	public List<DistrictDto> getDistrict() throws InternalServerException {
		// TODO Auto-generated method stub
		List<District> districts = districtRepo.findAll();
		List<DistrictDto> district = new ArrayList<>();
		for (District d: districts) {
			DistrictDto dto = new DistrictDto();
			dto.setId(d.getId());
			dto.setName(d.getName());
			//dto.setDescription(item.getDescription());
			//dto.setIsActive(item.getIsActive());
			//dto.setPincode(a.getPincode());
		    district.add(dto);
		}
		return district;
	}

	@Override
	public List<StateDto> getState() throws InternalServerException {
		// TODO Auto-generated method stub
		List<State> states = stateRepo.findAll();
		List<StateDto> state = new ArrayList<>();
		for (State s : states) {
			StateDto dto = new StateDto();
			dto.setId(s.getId());
			dto.setName(s.getName());
			//dto.setDescription(item.getDescription());
			//dto.setIsActive(item.getIsActive());
			//dto.setPincode(a.getPincode());
		    state.add(dto);
		}
		return state;
	}
	
	public List<PaymentDto>  getPaymentMode() throws InternalServerException{
		 
		List<PaymentMode> modes = paymentRepo.findAll();
		List<PaymentDto> mode = new ArrayList<>();
		for(PaymentMode m : modes) {
			PaymentDto dto = new PaymentDto();
			dto.setId(m.getId());
			dto.setName(m.getName());
			mode.add(dto);
		}
		return mode;
	}

	@Override
	public Long authenticateAdmin(AdminDto adminDto) throws NotFoundException{
		// TODO Auto-generated method stub
		Admin admin = adminRepo.findByFirstNameAndPassword(adminDto.getFirstName(),adminDto.getPassword());
		if(admin!=null) {
			return admin.getId();
		}
		else
		{
			throw new NotFoundException("Please Check your username or Password");
		}
	
	}
	
	public void updateStatus(Long id) throws NotFoundException {
		boolean status;
		Menu menu = menuRepo.findById(id).get();
		if(menu!=null)
		
		{
	    if(menu.getIsActive() == true) {
	    	status = false;
	    	menu.setIsActive(status);
	    }
	    else {
	    	status = true;
	    	menu.setIsActive(status);
	    }
	    menuRepo.save(menu);
		}
		else {
			throw new NotFoundException("the food not available");
		}
	}
	
	
	
	

}
