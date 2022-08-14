package jor.empapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "*", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/test")
public class TestAuthorityController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/customer")
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
	public String userAccess() {
		// to connect service or JPA respository
		return "Customer Content.";
	}

	@GetMapping("/employee")
	@PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
	public String moderatorAccess() {
		return "Employee Board.";
	}

	@GetMapping("/manager")
	@PreAuthorize("hasAuthority('MANAGER')")
	public String adminAccess() {
		return "Manager Board.";
	}
}
