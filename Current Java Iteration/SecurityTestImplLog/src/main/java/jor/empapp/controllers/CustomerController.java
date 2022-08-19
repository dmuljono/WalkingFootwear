package jor.empapp.controllers;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jor.empapp.models.CancelOrder;
import jor.empapp.models.Customer;
import jor.empapp.models.Employee;
import jor.empapp.models.Feedback;
import jor.empapp.models.OrderForm;
import jor.empapp.models.Product;
import jor.empapp.models.ReturnOrder;
import jor.empapp.payload.request.AddressRequest;
import jor.empapp.payload.request.CartItem;
import jor.empapp.payload.request.FeedbackRequest;
import jor.empapp.payload.request.OnlineOrderRequest;
import jor.empapp.payload.request.OrderRequest;
import jor.empapp.payload.request.ReturnRequest;
import jor.empapp.payload.response.MessageResponse;
import jor.empapp.repositorys.CancelOrderRepository;
import jor.empapp.repositorys.CustomerRepository;
import jor.empapp.repositorys.EmployeeRepository;
import jor.empapp.repositorys.FeedbackRepository;
import jor.empapp.repositorys.OrderFormRepository;
import jor.empapp.repositorys.ProductRepository;
import jor.empapp.repositorys.ReturnOrderRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/test/customer")
public class CustomerController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private OrderFormRepository orderFormRepository;
	
	@Autowired
	private ReturnOrderRepository returnOrderRepository;
	
	@Autowired
	private CancelOrderRepository cancelOrderRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	private static final Logger logger=LogManager.getLogger(CustomerController.class);
	
	@GetMapping("/allOrdersOnCustomer/{customerId}")
	public List<OrderForm> allOrdersOnCustomer(@PathVariable long customerId ){
		try {
			List<OrderForm> orderList = orderFormRepository.findByCustomerCustomerId(customerId);
			logger.info("Found Orders");
			return orderList;
		} catch (Exception e) {
			logger.info("Could Not Find Orders" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/setCustomerAddressOrder")
	public ResponseEntity<?> setAddress(@RequestBody OnlineOrderRequest addReq){
		System.out.println(addReq.getAddr()+addReq.getCustomId());
		Customer current = customerRepository.findById(addReq.getCustomId()).get();
		current.setAddress(addReq.getAddr());
		System.out.println("Looping");
		Long[] quantitys = addReq.getQuantitys();
		Long[] productIds = addReq.getProductIds();
		System.out.println(productIds.length);
		System.out.println(quantitys.length);
		for(int i = 0; i < productIds.length; i++) {
			System.out.println("Looping3");
			OrderForm newOrder = new OrderForm();
			newOrder.setCustomer(current);
			newOrder.setProduct(productRepository.findById(productIds[i]).get());
			newOrder.setQuantity(quantitys[i].intValue());
			System.out.println(newOrder.getProduct().getProductId());
			newOrder.findTotalAmount();
			orderFormRepository.save(newOrder);
		}
		logger.info("Order Submitted");
		return ResponseEntity.ok(new MessageResponse("Submitted!"));
	}
	
	@PostMapping("/placingOrderTest")
	public String placingOrderTest(@RequestBody OrderRequest orderRequest) {
		try {
			Optional<Product> orderedProduct = productRepository.findById(orderRequest.getProductId());
			OrderForm orderForm = new OrderForm();
			if(orderedProduct.isEmpty()) {
				throw new Exception("Empty");
			}
			orderForm.setProduct(orderedProduct.get());
			orderForm.setQuantity(orderRequest.getQuantity());
			orderForm.findTotalAmount();
			orderFormRepository.save(orderForm);
			logger.info("Test Order Placed");
			return "Order Placed";
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Unable to Place Order: " + e.getMessage());
			return "Product ID not found";
		}
	}
	
	@PostMapping("/cancelOrder")
	public ResponseEntity<?> cancelOrderTest(@RequestBody ReturnRequest returnRequest) {
		OrderForm order = orderFormRepository.findById(returnRequest.getOrderId()).get();
		CancelOrder cancelOrder = new CancelOrder();
		cancelOrder.setOrderId(order.getOrderId());
		cancelOrder.setCustomer(order.getCustomer());
		cancelOrder.setProduct(order.getProduct());
		cancelOrder.setPurchaseDate(order.getPurchaseDate());
		cancelOrder.setQuantity(order.getQuantity());
		cancelOrder.setTotalAmount(order.getTotalAmount());
		cancelOrder.setReasonForCancel(returnRequest.getReasonForReturn());
		System.out.println("Cancel order");
		cancelOrderRepository.save(cancelOrder);
		orderFormRepository.deleteById(order.getOrderId());
		logger.info("Successfully Cancelled Order");
		return ResponseEntity.ok(new MessageResponse("Submitted!"));
	}
	
	@PostMapping("/returnOrder")
	public ResponseEntity<?> returnOrderTest(@RequestBody ReturnRequest returnRequest) {
		OrderForm order = orderFormRepository.findById(returnRequest.getOrderId()).get();
		ReturnOrder returnOrder = new ReturnOrder();
		returnOrder.setOrderId(order.getOrderId());
		returnOrder.setCustomer(order.getCustomer());
		returnOrder.setProduct(order.getProduct());
		returnOrder.setPurchaseDate(order.getPurchaseDate());
		returnOrder.setQuantity(order.getQuantity());
		returnOrder.setTotalAmount(order.getTotalAmount());
		returnOrder.setReasonForReturn(returnRequest.getReasonForReturn());
		//returnOrder.setReturnApproved(true);
		System.out.println("Return order");
		returnOrderRepository.save(returnOrder);
		orderFormRepository.deleteById(order.getOrderId());
		logger.info("Successfully Returned Order");
		return ResponseEntity.ok(new MessageResponse("Submitted!"));
	}
	
//	@GetMapping("/search/findByNameContaining?name={name}")
//	public Page<Product> findByName(@RequestParam String name) {
//		Page<Product> p;
//		p = productRepository.findByNameContaining(name, );
//		return p;
//	}
//	
	
	@GetMapping("/search")
	public List<Product> findByName(@RequestParam(name = "name") String name){
		List<Product> p;
		p = productRepository.findByNameContaining(name);
		return p;
	}
	//Product Search
	
	//Feedback
	@PostMapping("/customerFeedback")
	public ResponseEntity<?> customerFeedback(@RequestBody FeedbackRequest feedbackRequest){
		Feedback feedback = new Feedback();
		OrderForm currentOrder = orderFormRepository.findById(feedbackRequest.getOrderId()).get();
		feedback.setProduct(currentOrder.getProduct());
		feedback.setCustomer(customerRepository.findById(feedbackRequest.getCustomerId()).get());
		feedback.setComment(feedbackRequest.getComment());
		feedback.setRating(feedbackRequest.getRating());
		feedback.setDeliveryOnTime(feedbackRequest.isDeliveryOnTime());
		feedback.setOrder(currentOrder);
		feedbackRepository.save(feedback);
		logger.info("Submitted Feedback");
		return ResponseEntity.ok(new MessageResponse("Submitted!"));
		
	}
	//All Products
	@GetMapping("/allProducts")
	public List<Product> findAllProducts() {
		List<Product> productList = null;
		try {
		productList = productRepository.findAll();
		
		} catch (Exception ex) {
			logger.info("Unable to Find Products: " + ex.getMessage());
			return null;
			
		}
		logger.info("Found all Products");
		return productList;
	}
	
	//Category Products
			@GetMapping("/category/{categoryId}")
			public List<Product> findProductByCategoryId(@PathVariable long categoryId) {
				List<Product> productList = null;
				try {
					if(categoryId == 0) {
						productList = productRepository.findAll();
						return productList;
					}
				productList = productRepository.findByCategoryCategoryId(categoryId);
				
				} catch (Exception ex) {
					logger.info("Unable to Find Products By Category: " + ex.getMessage());
					return null;
					
				}
				logger.info("Searched For Products By Category");
				return productList;
			}
		
//			@PostMapping("/giveOrder")
//			public ResponseEntity<?> giveOrder(@RequestBody OnlineOrderRequest ooReq){
//				Customer current = customerRepository.findById(ooReq.getCustomerId()).get();
//				Long[] quantitys = ooReq.getQuantity();
//				Long[] productIds = ooReq.getProductIds();
//				for(int i = 0; i > productIds.length; i++) {
//					OrderForm newOrder = new OrderForm();
//					newOrder.setCustomer(current);
//					newOrder.setProduct(productRepository.findById(productIds[i]).get());
//					newOrder.setQuantity(quantitys[i].intValue());
//					orderFormRepository.save(newOrder);
//					newOrder.findTotalAmount();
//				}
//				System.out.println("yoyo");
//				return ResponseEntity.ok(new MessageResponse("Submitted!"));
//			}

}

