package com.example.restaurant.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.dto.AdminDto;
import com.example.restaurant.dto.AreaDto;
import com.example.restaurant.dto.DistrictDto;
import com.example.restaurant.dto.LoginDto;
import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.PaymentDto;
import com.example.restaurant.dto.StateDto;
import com.example.restaurant.exception.BusinessServiceException;
import com.example.restaurant.exception.ConstraintViolationException;
import com.example.restaurant.exception.InternalServerException;
import com.example.restaurant.exception.NotFoundException;
import com.example.restaurant.service.AdminService;
import com.example.restaurant.util.HttpStatusResponse;
import com.example.restaurant.util.ResponseUtils;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login")
	public ResponseEntity<HttpStatusResponse> toLogin(@RequestBody AdminDto adminDto, HttpSession session) {

		try {
			Long customer = adminService.authenticateAdmin(adminDto);
			session.setAttribute("customer", customer);
			return ResponseUtils.prepareSuccessResponse(null, customer);
		} catch (NotFoundException exception) {
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid Username or Password");
		}
	}

	
	@PostMapping("/area")
	public ResponseEntity<HttpStatusResponse> toAddNewArea(@RequestBody AreaDto areadto)
	{
		try {
			adminService.insertArea(areadto);
			return ResponseUtils.prepareAcceptedResponse("Added Successfully", areadto);
		}
		catch(ConstraintViolationException exception) {
			return ResponseUtils.prepareUnProcessableEntityResponse("Failed to insert Area.Please enter the valid data");
		}
	}
	@DeleteMapping("/area")
	public ResponseEntity<HttpStatusResponse> toDeleteArea(@RequestParam("id") Long id) {
		try {
		adminService.deleteArea(id);
		return ResponseUtils.prepareSuccessResponse("AreaDeleted Successfully",null);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid id");
		}
	}
	
	@GetMapping("/area")
	public ResponseEntity<HttpStatusResponse> toGetArea(HttpSession session) {
		try {
			List<AreaDto> area = adminService.getArea();
			return ResponseUtils.prepareSuccessResponse(null, area);
		} catch (InternalServerException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("Area Not Available");
		}
	}

	
	@PostMapping("/district")
	public ResponseEntity<HttpStatusResponse> toAddNewDistrict(@RequestBody DistrictDto districtdto)
	{
		try {
			adminService.insertDistrict(districtdto);
			return ResponseUtils.prepareAcceptedResponse("Added Successfully", districtdto);
		}
		catch(ConstraintViolationException exception) {
			return ResponseUtils.prepareUnProcessableEntityResponse("Failed to insert District.Please enter the valid data");
		}
	}
	@DeleteMapping("/district")
	public ResponseEntity<HttpStatusResponse> toDeleteDistrict(@RequestParam("id") Long id) {
		try {
		adminService.deleteDistrict(id);
		return ResponseUtils.prepareSuccessResponse("Deleted Successfully",null);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid id");
		}
	}
	
	@GetMapping("/district")
	public ResponseEntity<HttpStatusResponse> toGetDistrict(HttpSession session) {
		try {
			
			List<DistrictDto> district = adminService.getDistrict();
			return ResponseUtils.prepareSuccessResponse(null, district);
		} catch (InternalServerException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("District Not Available");
		}
	}
	
	@PostMapping("/state")
	public ResponseEntity<HttpStatusResponse> toAddNewState(@RequestBody StateDto statedto)
	{
		try {
			adminService.insertState(statedto);
			return ResponseUtils.prepareAcceptedResponse("Added Successfully", statedto);
		}
		catch(ConstraintViolationException exception) {
			return ResponseUtils.prepareUnProcessableEntityResponse("Failed to insert state .Please enter the valid data");
		}
	}
	@DeleteMapping("/state")
	public ResponseEntity<HttpStatusResponse> toDeleteOrder(@RequestParam("id") Long id) {
		try {
		adminService.deleteState(id);
		return ResponseUtils.prepareSuccessResponse("StateDeleted Successfully",null);
		}
		catch(BusinessServiceException exception) {
			return ResponseUtils.prepareUnAuthorizedResponse("Invalid  id");
		}
	}
	
	@GetMapping("/state")
	public ResponseEntity<HttpStatusResponse> toGetState(HttpSession session) {
		try {
			
			List<StateDto> state = adminService.getState();
			return ResponseUtils.prepareSuccessResponse(null, state);
		} catch (InternalServerException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("State Not Available");
		}
	}
	
	@GetMapping("/paymentMode")
	public ResponseEntity<HttpStatusResponse> toGetPaymentMode(HttpSession session) {
		try {
		
			List<PaymentDto> mode = adminService.getPaymentMode();
			return ResponseUtils.prepareSuccessResponse(null, mode);
		} catch (InternalServerException exception) {
			return ResponseUtils.prepareNoRecordFoundResponse("Mode not Available");
		}
	}

	
}
