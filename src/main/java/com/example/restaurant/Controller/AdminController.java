package com.example.restaurant.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.AreaDto;
import com.example.restaurant.dto.DistrictDto;
import com.example.restaurant.dto.StateDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ContraintViolationException;
import com.example.restaurant.service.AdminService;
import com.example.restaurant.util.HttpStatusResponse;
import com.example.restaurant.util.ResponseUtils;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService areaservice;

	
	@PostMapping("/area")
	public ResponseEntity<HttpStatusResponse> toAddNewArea(@RequestBody AreaDto areadto)
	{
		try {
			areaservice.insertArea(areadto);
			return ResponseUtils.prepareAcceptedResponse("Added Successfully", areadto);
		}
		catch(ContraintViolationException exception) {
			return ResponseUtils.prepareUnProcessableEntityResponse("Failed to insert Area.Please enter the valid data");
		}
	}
	@DeleteMapping("/area")
	public ResponseEntity<HttpStatusResponse> toDeleteArea(@RequestParam("id") Long id) {
		try {
		areaservice.deleteArea(id);
		return ResponseUtils.prepareSuccessResponse("AreaDeleted Successfully",null);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid id");
		}
	}
	
	@PostMapping("/district")
	public ResponseEntity<HttpStatusResponse> toAddNewDistrict(@RequestBody DistrictDto districtdto)
	{
		try {
			areaservice.insertDistrict(districtdto);
			return ResponseUtils.prepareAcceptedResponse("Added Successfully", districtdto);
		}
		catch(ContraintViolationException exception) {
			return ResponseUtils.prepareUnProcessableEntityResponse("Failed to insert District.Please enter the valid data");
		}
	}
	@DeleteMapping("/district")
	public ResponseEntity<HttpStatusResponse> toDeleteDistrict(@RequestParam("id") Long id) {
		try {
		areaservice.deleteDistrict(id);
		return ResponseUtils.prepareSuccessResponse("Deleted Successfully",null);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid id");
		}
	}
	
	@PostMapping("/state")
	public ResponseEntity<HttpStatusResponse> toAddNewState(@RequestBody StateDto statedto)
	{
		try {
			areaservice.insertState(statedto);
			return ResponseUtils.prepareAcceptedResponse("Added Successfully", statedto);
		}
		catch(ContraintViolationException exception) {
			return ResponseUtils.prepareUnProcessableEntityResponse("Failed to insert state .Please enter the valid data");
		}
	}
	@DeleteMapping("/state")
	public ResponseEntity<HttpStatusResponse> toDeleteOrder(@RequestParam("id") Long id) {
		try {
		areaservice.deleteState(id);
		return ResponseUtils.prepareSuccessResponse("StateDeleted Successfully",null);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid  id");
		}
	}
	
}
