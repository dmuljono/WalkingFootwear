package jor.empapp.controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jor.empapp.payload.request.LoginRequest;
import jor.empapp.payload.response.JwtResponse;
import jor.empapp.repositorys.CustomerRepository;
import jor.empapp.repositorys.CustomerRoleRepository;
import jor.empapp.repositorys.EmployeeRepository;
import jor.empapp.repositorys.EmployeeRoleRepository;
import jor.empapp.security.services.UserDetailsImpl;
import jor.empapp.models.Customer;
import jor.empapp.models.CustomerRole;
import jor.empapp.models.ERole;
import jor.empapp.models.Employee;
import jor.empapp.models.EmployeeRole;
import jor.empapp.payload.request.SignupRequest;
import jor.empapp.payload.response.MessageResponse;
import jor.empapp.security.jwt.JwtUtils;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private static final Logger logger=LogManager.getLogger(AuthController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	CustomerRoleRepository customerRoleRepository;
	
	@Autowired
	EmployeeRoleRepository employeeRoleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	Long currentId;
	
	String currentUsername;
	
	String currentEmail;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		logger.info("Signed in Successfully");

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getFirstName(), 
												 userDetails.getEmail(), 
												 roles));

	}

	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		
		Set<String> strRoles = signUpRequest.getRole();
		Set<CustomerRole> toBeCRole = new HashSet<>();
		Set<EmployeeRole> toBeERole = new HashSet<>();
		String strRole ="";
		
		if(strRoles == null) {
			strRole = "customer";
		}
		else {
			Iterator<String> roleIterator = strRoles.iterator();
			strRole = roleIterator.next();
		}
		
		strRole = strRole.toLowerCase();
		System.out.println(strRole);
		
		
		if(strRole.contentEquals("customer")) {
			System.out.println("Done1");
			logger.info("Signed Up As Customer");

			CustomerRole customerRole = customerRoleRepository.findByName(
					ERole.CUSTOMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
			toBeCRole.add(customerRole);
			Customer newCustomer = new Customer();
			newCustomer.setEmail(signUpRequest.getEmail());
			newCustomer.setFirstName(signUpRequest.getFirstName());
			newCustomer.setLastName(signUpRequest.getLastName());
			newCustomer.setPassword(encoder.encode(signUpRequest.getPassword()));
			newCustomer.setRoles(toBeCRole);
			
			customerRepository.save(newCustomer);
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		else if(strRole.contentEquals("employee")) {
			System.out.println("Done2");
			logger.info("Signed Up As Employee");

			EmployeeRole employeeRole = employeeRoleRepository.findByName(
					ERole.EMPLOYEE)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
			toBeERole.add(employeeRole);
			Employee newEmployee = new Employee();
			newEmployee.setEmail(signUpRequest.getEmail());
			newEmployee.setFirstName(signUpRequest.getFirstName());
			newEmployee.setLastName(signUpRequest.getLastName());
			newEmployee.setPassword(encoder.encode(signUpRequest.getPassword()));
			newEmployee.setRoles(toBeERole);
			
			employeeRepository.save(newEmployee);
			

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		else if (strRole.contentEquals("manager")) {
			System.out.println("Done3");
			logger.info("Signed Up As Manager");

			EmployeeRole managerRole = employeeRoleRepository.findByName(
					ERole.MANAGER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
			System.out.println("Done3");
			
			toBeERole.add(managerRole);
			Employee newEmployee = new Employee();
			newEmployee.setEmail(signUpRequest.getEmail());
			newEmployee.setFirstName(signUpRequest.getFirstName());
			newEmployee.setLastName(signUpRequest.getLastName());
			newEmployee.setPassword(encoder.encode(signUpRequest.getPassword()));
			newEmployee.setRoles(toBeERole);
			System.out.println("Done");
			employeeRepository.save(newEmployee);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		
		else {
			System.out.println("Done4");
			logger.info("Signed Up As Customer Default");

			CustomerRole customerRole = customerRoleRepository.findByName(
					ERole.CUSTOMER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			
			toBeCRole.add(customerRole);
			Customer newCustomer = new Customer();
			newCustomer.setEmail(signUpRequest.getEmail());
			newCustomer.setFirstName(signUpRequest.getFirstName());
			newCustomer.setLastName(signUpRequest.getLastName());
			newCustomer.setPassword(encoder.encode(signUpRequest.getPassword()));
			newCustomer.setRoles(toBeCRole);
			
			customerRepository.save(newCustomer);

			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
	}
	
	@PostMapping("/signout")
	  public ResponseEntity<?> logoutUser() {
		
	   // ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		logger.info("Signed Out");

	    return ResponseEntity.ok(new MessageResponse("You've been signed out!"));
	  }
		
}
