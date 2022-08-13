package jor.empapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jor.empapp.models.Customer;
import jor.empapp.models.Employee;
import jor.empapp.models.Product;
import jor.empapp.models.ProductCategory;
import jor.empapp.payload.request.ProductRequest;
import jor.empapp.repositorys.CustomerRepository;
import jor.empapp.repositorys.EmployeeRepository;
import jor.empapp.repositorys.ProductCategoryRepository;
import jor.empapp.repositorys.ProductRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class ManagerController {
	
	@Autowired
	private ProductRepository pr;
	
	@Autowired
	private EmployeeRepository er;
	
	@Autowired
	private CustomerRepository cr;
	
	@Autowired
	private ProductCategoryRepository pcr;
	
	
	// Product Registration
	@PostMapping("/products")
	public String addProductByCategoryId(@RequestBody ProductRequest preq) {
		Product p = new Product();
		p.setDescription(preq.getDescription());
		p.setSku(preq.getSku());
		p.setImageUrl(preq.getImageUrl());
		p.setUnitPrice(preq.getUnitPrice());
		p.setUnitsInStock(preq.getUnitsInStock());
		p.setAvailable(preq.isAvailable());
		p.setName(preq.getName());
		p.setCategory(pcr.findById(preq.getCategoryId()).get());
		String msg = "";
		try {
			pr.save(p);
			msg = "Product Added Successfully";
		} catch (Exception ex) {
			msg = "Unable to add Product: " + ex.getMessage();
			
		}
		return msg;
	}
	
	// Employee Registration
	@PostMapping("/employees")
	public String addEmployee(@RequestBody Employee e) {
		String msg = "";
		try {
			er.save(e);
			msg = "Employee Added Successfully";
			
			
		} catch(Exception ex) {
			msg = "Unable to add Employee: " + ex.getMessage();
			
		}
		return msg;
	}
	
	// Create Customer Profile
	@PostMapping("/customers")
	public String addCustomer(@RequestBody Customer c) {
		String msg = "";
		try {
			cr.save(c);
			msg = "Customer Added Successfully";
		} catch (Exception ex) {
			msg = "Unable to add Customer: " + ex.getMessage();
		}
		return msg;
	}
	
	// Find Products
	@GetMapping("/products/{id}")
	public Product findProduct(@PathVariable long id) {
		Product p = new Product();
		Optional<Product> op= pr.findById(id);
		
		if(op.isPresent()) {
			p = op.get();
		}
		
		return p;
				
	}
	
	// Employee View
	@GetMapping("/employees/{id}")
	public Employee findEmployee(@PathVariable long id) {
		Employee e = new Employee();
		Optional<Employee> op = er.findById(id);
		if(op.isPresent()) {
			e = op.get();
		}
		return e;
	}
	
	
	// Delete Employee
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmployee(@PathVariable long id) {
		String msg = "";
		try {
			er.deleteById(id);
			msg = "Employee Deleted";
		} catch (Exception ex) {
			msg = "Could not delete employee: " + ex.getMessage();
		}
		return msg;
	}
	
	//New Order Request
	@GetMapping("/addStock/{id}/{amount}")
	public String addStock(@PathVariable long id, @PathVariable int amount) {
		String msg = "";
		try {
			Product product = pr.findById(id).get();
			product.setUnitsInStock(amount);
			msg = "Stocks updated";
		} catch (Exception e) {
			msg = "Could not find ID";
			e.printStackTrace();
		}
		return msg;
	}
	
	//View All Employees
		@GetMapping("/allProducts")
		public List<Product> findAllProducts() {
			List<Product> ps = pr.findAll();
			
			return ps;
			
		}

	//View All Orders
	
	//View Feedback
	
	//Add ProductCategory
	@GetMapping("/addProductCategory/{name}")
	public String addProductCategory(@PathVariable String name) {
		String msg ="";
		
		try {
			ProductCategory pc = new ProductCategory();
			pc.setCategoryName(name);
			pcr.save(pc);
			msg = "Successfully added Product Category:" +name;
		} catch (Exception e) {
			msg="Error adding Product Category";
			e.printStackTrace();
		}
		return msg;
	}
}
