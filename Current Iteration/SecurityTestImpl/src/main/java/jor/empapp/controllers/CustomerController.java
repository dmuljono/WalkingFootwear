package jor.empapp.controllers;

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

import jor.empapp.models.CancelOrder;
import jor.empapp.models.Customer;
import jor.empapp.models.Employee;
import jor.empapp.models.OrderForm;
import jor.empapp.models.Product;
import jor.empapp.models.ReturnOrder;
import jor.empapp.payload.request.OrderRequest;
import jor.empapp.payload.request.ReturnRequest;
import jor.empapp.repositorys.CancelOrderRepository;
import jor.empapp.repositorys.CustomerRepository;
import jor.empapp.repositorys.EmployeeRepository;
import jor.empapp.repositorys.OrderFormRepository;
import jor.empapp.repositorys.ProductRepository;
import jor.empapp.repositorys.ReturnOrderRepository;

@CrossOrigin
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
	
	@PostMapping("/placingOrder")
	public String placingOrder(@RequestBody OrderRequest orderRequest) {
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
			return "Order Placed";
		} catch (Exception e) {
			e.printStackTrace();
			return "Product ID not found";
		}
	}
	
	@PostMapping("/cancelOrder")
	public void cancelOrder(@RequestBody ReturnRequest returnRequest) {
		OrderForm order = orderFormRepository.findById(returnRequest.getOrderId()).get();
		CancelOrder returnOrder = new CancelOrder();
		returnOrder.setOrderId(order.getOrderId());
		returnOrder.setCustomer(order.getCustomer());
		returnOrder.setProduct(order.getProduct());
		returnOrder.setPurchaseDate(order.getPurchaseDate());
		returnOrder.setQuantity(order.getQuantity());
		returnOrder.setTotalAmount(order.getTotalAmount());
		cancelOrderRepository.save(returnOrder);
		orderFormRepository.deleteById(order.getOrderId());
	}
	
	@PostMapping("/returnOrder")
	public void returnOrder(@RequestBody ReturnRequest returnRequest) {
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
		returnOrderRepository.save(returnOrder);
		orderFormRepository.deleteById(order.getOrderId());
	}
	
	//Product Search
	
	//Feedback
	
}
