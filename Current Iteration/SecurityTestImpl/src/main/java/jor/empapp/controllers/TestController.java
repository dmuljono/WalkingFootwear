package jor.empapp.controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import jor.empapp.repositorys.OrderFormRepository;
import jor.empapp.repositorys.ProductCategoryRepository;
import jor.empapp.repositorys.ProductRepository;
import jor.empapp.security.services.UserDetailsImpl;
import jor.empapp.models.Customer;
import jor.empapp.models.CustomerRole;
import jor.empapp.models.ERole;
import jor.empapp.models.Employee;
import jor.empapp.models.EmployeeRole;
import jor.empapp.models.OrderForm;
import jor.empapp.models.Product;
import jor.empapp.models.ProductCategory;
import jor.empapp.payload.request.SignupRequest;
import jor.empapp.payload.response.MessageResponse;
import jor.empapp.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
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
	ProductRepository productRepository;
	
	@Autowired
	ProductCategoryRepository categoryRepository;

	@Autowired
	OrderFormRepository orderFormRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	ProductCategory pcCurrent;
	
	Product pCurrent;


	@GetMapping("/addProduct")
	public String addProduct() {
		Product p = new Product();
		p.setName("Java Python");
		p.setSku("BOOK-TECH-1001");
		p.setAvailable(true);
		p.setUnitPrice(100);
		p.setImageUrl("img.url");
		p.setUnitsInStock(10);
		p.setDescription("Nothing Much");
		p.setCategory(pcCurrent);
		pCurrent = p;
		productRepository.save(p);
		return "Yes";
	}
	
	@GetMapping("/addProductCategory")
	public String addProductCategory() {
		ProductCategory pc = new ProductCategory();
		pc.setCategoryName("Books");
		categoryRepository.save(pc);
		pcCurrent = pc;
		return "Yes";
		
	}
	
	@GetMapping("/testOrderForm")
	public String testOrderForm() {
		OrderForm of = new OrderForm();
		of.setProduct(pCurrent);
		of.setQuantity(10);
		long price = of.getProduct().getUnitPrice().longValue();
		int quantity = of.getQuantity();
		long total = price*quantity;
		of.setTotalAmount(total);
		orderFormRepository.save(of);
		return "Yes";
	}
		
		
}
