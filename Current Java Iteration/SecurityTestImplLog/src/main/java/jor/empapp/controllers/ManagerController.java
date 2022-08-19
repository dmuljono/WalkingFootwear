package jor.empapp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

import jor.empapp.models.CancelOrder;
import jor.empapp.models.Customer;
import jor.empapp.models.Employee;
import jor.empapp.models.Feedback;
import jor.empapp.models.OrderForm;
import jor.empapp.models.Product;
import jor.empapp.models.ProductCategory;
import jor.empapp.models.ReturnOrder;
import jor.empapp.models.WalkinOrderForm;
import jor.empapp.payload.request.OrderRequest;
import jor.empapp.payload.request.ProductRequest;
import jor.empapp.payload.request.StockUpdateRequest;
import jor.empapp.payload.request.WalkInOrderRequest;
import jor.empapp.payload.response.MessageResponse;
import jor.empapp.repositorys.CancelOrderRepository;
import jor.empapp.repositorys.CustomerRepository;
import jor.empapp.repositorys.EmployeeRepository;
import jor.empapp.repositorys.FeedbackRepository;
import jor.empapp.repositorys.OrderFormRepository;
import jor.empapp.repositorys.ProductCategoryRepository;
import jor.empapp.repositorys.ProductRepository;
import jor.empapp.repositorys.ReturnOrderRepository;
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
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private CancelOrderRepository cancelOrderRepository;
	
	@Autowired
	private ReturnOrderRepository returnOrderRepository;
	
	private static final Logger logger=LogManager.getLogger(ManagerController.class);
	
	// Product Registration
	@PostMapping("/products")
	@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
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
			logger.info("Unable to add Product: " + ex.getMessage());
			return  (ResponseEntity<?>) ResponseEntity.badRequest();
			
		}
		logger.info("Product Added Successfully");
		return ResponseEntity.ok(new MessageResponse("Product registered successfully!"));
	}
	
	// Product Registration
		@PostMapping("/addProduct")
		@PreAuthorize("hasAuthority('CUSTOMER') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
		public String addProduct(@RequestBody Product p) {
			String msg = "";
			try {
				pr.save(p);
				msg = "Product Added Successfully";
			} catch (Exception ex) {
				msg = "Unable to add Product: " + ex.getMessage();
				logger.info("Unable to add Product: " + ex.getMessage());
				
			}
			logger.info("Product Added Successfully");
			return msg;
		}
	
	// Employee Registration
	@PostMapping("/employees")
	@PreAuthorize("hasAuthority('MANAGER')")
	public ResponseEntity<?> addEmployee(@RequestBody Employee e) {
		try {
			er.save(e);
			
			
		} catch(Exception ex) {
			logger.info("Unable to add Employee: " + ex.getMessage());
			return (ResponseEntity<?>) ResponseEntity.badRequest();
			
		}
		logger.info("Employee Added Successfully");
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	// Create Customer Profile
	@PreAuthorize("hasAuthority('MANAGER')")
	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer c) {
		try {
			
			cr.save(c);
		} catch (Exception ex) {
			logger.info("Unable to add Customer: " + ex.getMessage());
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
		logger.info("Customer Added Successfully");
		return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
	}
	
	// Find Products
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/products/{id}")
	public Product findProduct(@PathVariable long id) {
		Product p = new Product();
		Optional<Product> op= pr.findById(id);
		
		if(op.isPresent()) {
			p = op.get();
			logger.info("Product Found Successfully");
		} else {logger.info("Unable to find Product");}
		
		
		return p;
				
	}
	
	// Employee View
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/employees/{id}")
	public Employee findEmployee(@PathVariable long id) {
		Employee e = new Employee();
		Optional<Employee> op = er.findById(id);
		if(op.isPresent()) {
			e = op.get();
			logger.info("Employee Found Successfully");
		} else {logger.info("Unable to find Employee");}
		
		return e;
	}
	
	
	// Delete Employee
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmployee(@PathVariable long id) {
		String msg = "";
		try {
			er.deleteById(id);
			msg = "Employee Deleted";
			logger.info("Employee Deleted Successfully");
		} catch (Exception ex) {
			msg = "Could not delete employee: " + ex.getMessage();
			logger.info("Could not delete Employee: " + ex.getMessage());
		}

		return msg;
	}
	
	//New Order Request
	@PreAuthorize("hasAuthority('MANAGER')")
	@PostMapping("/addStock")
	public ResponseEntity<?> addStock(@RequestBody StockUpdateRequest sup) {
		try {
			Product product = pr.findById(sup.getProductId()).get();
			//product.setUnitsInStock(sup.getQuantity()+product.getUnitsInStock());
			int totalStock = sup.getQuantity()+product.getUnitsInStock();
			System.out.println(sup.getQuantity()+" "+product.getUnitsInStock());
			pr.updateStockQuantity(totalStock, sup.getProductId());
			logger.info("Added Stock Successfully");
		} catch (Exception e) {
			logger.info("Could not Add Stock: " + e.getMessage());
			return ResponseEntity.badRequest().body("Not Okay");
		}
		System.out.println(sup.getQuantity()+"See"+sup.getProductId());
		
		return ResponseEntity.ok(new MessageResponse("Stock Updated!"));
	}
	
	//View All Products
	@PreAuthorize("hasAuthority('MANAGER')")
		@GetMapping("/allProducts")
		public List<Product> findAllProducts() {
			List<Product> ps = pr.findAll();
			logger.info("All Products Found Successfully");
			return ps;
			
		}

	//View All Orders
	
	//View Feedback
		
	//View ByCategory ID
	@PreAuthorize("hasAuthority('MANAGER')")
		@GetMapping("/allEmployees")
		public List<Employee> findAllEmployee() {
			List<Employee> empList = er.findAll();
			System.out.println(empList.get(0).toString());
			logger.info("All Employees Found Successfully");
			return empList;
		}
		
		//All Employees
	@PreAuthorize("hasAuthority('MANAGER')")
		@GetMapping("/category/{categoryId}")
		public List<Product> findProductByCategoryId(@PathVariable long categoryId) {
			List<Product> productList = null;
			try {
				if(categoryId == 0) {
					productList = pr.findAll();
					return productList;
					}
			productList = pr.findByCategoryCategoryId(categoryId);
			logger.info("All Products by Category Found Successfully");
			} catch (Exception ex) {
				logger.info("Could not find Products by Category: " + ex.getMessage());
				return null;
				
			}
			
			return productList;
		}
	
	//Add ProductCategory
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/addProductCategory/{name}")
	public String addProductCategory(@PathVariable String name) {
		String msg ="";
		
		try {
			ProductCategory pc = new ProductCategory();
			pc.setCategoryName(name);
			pcr.save(pc);
			msg = "Successfully added Product Category:" +name;
			logger.info("Added New Product Category Successfully");
		} catch (Exception e) {
			msg="Error adding Product Category";
			e.printStackTrace();
			logger.info("Could not add Product Category:" + e.getMessage());
		}
		return msg;
	}
	@PreAuthorize("hasAuthority('MANAGER')")
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
				Product p = pr.findById(ids[i]).get();
				int newQuantity = p.getUnitsInStock()-orderForm.getQuantity();
				p.setUnitsInStock(newQuantity);
				wofRepository.save(orderForm);
			}
			logger.info("Placed WalkIn Order Successfully");
			return ResponseEntity.ok(new MessageResponse("Stock Updated!"));
		} catch (Exception e) {
			System.out.println("Exception");
			logger.info("Could not place WalkIn Order:" + e.getMessage());
			return ResponseEntity.badRequest().body("Not Okay");
		}
	}
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/getAllFeedbacks")
	public List<Feedback> getAllFeedbacks() {
		System.out.println("View Feedbacks");
		List<Feedback> fList = feedbackRepository.findAll();
		logger.info("Got Feedback Successfully");
		return fList;
	}
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/getAllCancels")
	public List<CancelOrder> getAllCancels() {
		List<CancelOrder> cList = cancelOrderRepository.findAll();
		logger.info("Got Cancels Successfully");

		return cList;
	}
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/getAllReturns")
	public List<ReturnOrder> getAllReturns() {
		List<ReturnOrder> rList = returnOrderRepository.findAll();
		logger.info("Got Returns Successfully");

		return rList;
	}
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/getAllWalkInOrders")
	public List<WalkinOrderForm> getAllWalkInOrders() {
		List<WalkinOrderForm> wofList = wofRepository.findAll();
		logger.info("Got All WalkIn Orders Successfully");

		return wofList;
	}
	@PreAuthorize("hasAuthority('MANAGER')")
	@GetMapping("/getOnlineOrders")
	public List<OrderForm> getAllOnlineOrders() {
		List<OrderForm> oList = orderFormRepository.findAll();
		logger.info("Got All Online Orders Successfully");

		return oList;
	}
}
