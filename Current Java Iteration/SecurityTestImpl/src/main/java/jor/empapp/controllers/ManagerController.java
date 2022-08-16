package jor.empapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import jor.empapp.models.OrderForm;
import jor.empapp.models.Product;
import jor.empapp.models.ProductCategory;
import jor.empapp.models.WalkinOrderForm;
import jor.empapp.payload.request.OrderRequest;
import jor.empapp.payload.request.ProductRequest;
import jor.empapp.payload.request.StockUpdateRequest;
import jor.empapp.payload.request.WalkInOrderRequest;
import jor.empapp.payload.response.MessageResponse;
import jor.empapp.repositorys.CustomerRepository;
import jor.empapp.repositorys.EmployeeRepository;
import jor.empapp.repositorys.OrderFormRepository;
import jor.empapp.repositorys.ProductCategoryRepository;
import jor.empapp.repositorys.ProductRepository;
import jor.empapp.repositorys.WalkinOrderFormRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/test/manager")
public class ManagerController {
	
	@Autowired
	private ProductRepository pr;
	
	@Autowired
	private EmployeeRepository er;
	
	@Autowired
	private CustomerRepository cr;
	
	@Autowired
	private ProductCategoryRepository pcr;
	
	@Autowired
	private OrderFormRepository orderFormRepository;
	
	@Autowired
	private WalkinOrderFormRepository wofRepository;
	
	
	
	// Product Registration
	@PostMapping("/products")
	public ResponseEntity<?> addProductByCategoryId(@RequestBody ProductRequest preq) {
		Product p = new Product();
		p.setDescription(preq.getDescription());
		p.setSku(preq.getSku());
		p.setImageUrl(preq.getImageUrl());
		p.setUnitPrice(preq.getUnitPrice());
		p.setUnitsInStock(preq.getUnitsInStock());
		p.setAvailable(preq.isAvailable());
		p.setName(preq.getName());
		p.setCategory(pcr.findById(preq.getCategoryId()).get());
		try {
			pr.save(p);
		} catch (Exception ex) {
			return  (ResponseEntity<?>) ResponseEntity.badRequest();
			
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	// Product Registration
		@PostMapping("/addProduct")
		public String addProduct(@RequestBody Product p) {
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
	public ResponseEntity<?> addEmployee(@RequestBody Employee e) {
		try {
			er.save(e);
			
			
		} catch(Exception ex) {
			return (ResponseEntity<?>) ResponseEntity.badRequest();
			
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	// Create Customer Profile
	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer c) {
		try {
			cr.save(c);
		} catch (Exception ex) {
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
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
	@PostMapping("/addStock")
	public ResponseEntity<?> addStock(@RequestBody StockUpdateRequest sup) {
		try {
			Product product = pr.findById(sup.getProductId()).get();
			product.setUnitsInStock(sup.getQuantity()+product.getUnitsInStock());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Not Okay");
		}
		pr.updateStockQuantity( sup.getQuantity(), sup.getProductId());
		return ResponseEntity.ok(new MessageResponse("Stock Updated!"));
	}
	
	//View All Products
		@GetMapping("/allProducts")
		public List<Product> findAllProducts() {
			List<Product> ps = pr.findAll();
			
			return ps;
			
		}

	//View All Orders
	
	//View Feedback
		
	//View ByCategory ID

		@GetMapping("/allEmployees")
		public List<Employee> findAllEmployee() {
			List<Employee> empList = er.findAll();
			System.out.println(empList.get(0).toString());
			return empList;
		}
		
		//All Employees
		@GetMapping("/category/{categoryId}")
		public List<Product> findProductByCategoryId(@PathVariable long categoryId) {
			List<Product> productList = null;
			try {
			productList = pr.findByCategoryCategoryId(categoryId);
			
			} catch (Exception ex) {
				return null;
				
			}
			
			return productList;
		}
	
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
	
	@PostMapping("/placeOrderWalkIn")
	public ResponseEntity<?> placeWalkInOrder(@RequestBody WalkInOrderRequest orderRequest) {
		try {
			Long[] ids = orderRequest.getProductIds();
			Long[] quantities = orderRequest.getQuantity();
			System.out.println(ids.length+"ids");
			System.out.println(quantities.length+"qs");
			if(ids.length != quantities.length) {
				return ResponseEntity.badRequest().body("Not Okay");
			}
			for(int i = 0; i<ids.length;i++) {
				WalkinOrderForm orderForm = new WalkinOrderForm();
				orderForm.setProduct(pr.findById(ids[i]).get());
				orderForm.setQuantity(quantities[i].intValue());
				orderForm.setEmail(orderRequest.getEmail());
				orderForm.setPhoneNumber(orderRequest.getPhoneNumber());
				orderForm.findTotalAmount();
				wofRepository.save(orderForm);
			}
			return ResponseEntity.ok(new MessageResponse("Stock Updated!"));
		} catch (Exception e) {
			System.out.println("Exception");
			return ResponseEntity.badRequest().body("Not Okay");
		}
	}
}
