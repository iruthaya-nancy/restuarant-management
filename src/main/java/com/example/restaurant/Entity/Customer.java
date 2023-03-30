package com.example.restaurant.Entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "CUSTOMER_DETAILS")
public class Customer {
	@Id
	@Column(name = "ID",unique = true,nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "FIRST_NAME",length = 20,nullable = false)
	private String firstname;
	
	@Column(name = "LAST_NAME",length = 20)
	private String  lastname;
	
	@Column(name = "EMAIL",unique = true,nullable = false )
	@Email(message = "Invalid email address")// throws constraint violation exception
	private String email;
	
	@Column(name = "PHONE_NUMBER",unique = true,nullable = false)
	@Size(max = 15, message = "Phone number must be at most {max} characters long")
	private String phonenumber;
	
	@Column(name = "DOOR_NO",length = 20,unique= true,nullable = false)
	private String doorno;
	
	@Column(name = "STREET_NAME",length = 20,nullable = false)
	private String street;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer1")
	private List<Address> address;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private District district;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private State state;
	
		
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private Orders order;
	//@OneToOne
    //@JoinColumn(name="ACCOUNT_ID")
    //private Orders account;
	
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "MODIFY_DATE")
	private Date modifyDate;

}
