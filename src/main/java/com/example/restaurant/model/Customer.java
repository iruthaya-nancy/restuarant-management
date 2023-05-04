package com.example.restaurant.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "CUSTOMER")
public class Customer {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "FIRST_NAME",length = 20)
	private String firstName;
	
	@Column(name = "LAST_NAME",length = 20,nullable = false)
	private String  lastName;
	
	@Column(name= "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "EMAIL",unique = true,nullable = false )
	@Email(message = "Invalid email address")// throws constraint violation exception
	private String email;
	
	@Column(name = "PHONE_NUMBER",unique = true,nullable = false)
	@Size(max = 10, message = "Phone number must be at most {max} characters long")
	private String phoneNumber;
	
	 @Column(name = "reset_password_token",length = 30)
	 private String resetPasswordToken;
	
	@Column(name = "DOOR_NO",length = 20,unique= true,nullable = false)
	private String doorNo;
	
	@Column(name = "STREET_NAME",length = 50,nullable = false)
	private String street;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AREA_ID")
	private Area area;
	
	//@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    //private District district;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DISTRICT_ID")
	private District district ;
	
	
	//@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    //private State state;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATE_ID")
	private State state;
	
		
//	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//    private Order order;
//	
//		
	//@CreationTimestamp
	//@Temporal(TemporalType.DATE)
	//@Column(name = "CREATED_AT")
	//private Date createDate;
	//@CreationTimestamp
	 @CreationTimestamp
	 @Column(name ="CREATED_AT", updatable = false)
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date createdAt;
	 
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_AT")
	private Date modifiedAt;

	



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	/*public List<Area> getAddress() {
		return address;
	}

	public void setAddress(List<Area> address) {
		this.address = address;
	}//-

	public List<District> getDistrict() {
		return district;
	}

	public void setDistrict(List<District> district) {
		this.district = district;
	}

	public List<State> getState() {
		return state;
	}

	public void setState(List<State> state) {
		this.state = state;
	}*/
	

//	public Order getOrder() {
//		return order;
//	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}
//	
	public Customer(Long id) {
		this.id = id;
	}
	
	
	public Customer(Long id, String firstName, String lastName, String password,
			@Email(message = "Invalid email address") String email,
			@Size(max = 10, message = "Phone number must be at most {max} characters long") String phoneNumber,
			String doorNo, String street) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		//this.doorNo = doorNo;
		//this.street = street;
		//this.address = address;
		//this.district = district;
		//this.state = state;
		//List<Area> address, List<District> district, List<State> state
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	/*public List<AddressDto> getAddressOfCustomer(){
		List<AddressDto> add1 = new ArrayList<>();
		for (Area add2 :address ) {
            //AddressDto AddressDTO = new AddressDto(add2.getId(), add2.getArea(),add2.getPincode(),add2.getIsActive(),add2.getDistrict(),add2.getState());
			AddressDto dto = new AddressDto(add2.getId(), add2.getArea(),add2.getPincode(),add2.getIsActive());
            add1.add(dto);
        }
        return add1;
    }*/
	
//	public String createPasswordResetTokenForUser() {
//        String token = UUID.randomUUID().toString();
//        resetPasswordToken = token;
//        this.passwordResetTokenExpiration = LocalDateTime.now().plusHours(24);
//        return resetPasswordToken;
//    }
	
	
	}

