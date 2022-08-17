package jor.empapp.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import jor.empapp.models.OrderForm;

@CrossOrigin("http://localhost:4200")
public interface OrderFormRepository extends JpaRepository<OrderForm, Long> {
	public List<OrderForm> findByCustomerCustomerId(long customerId);
}
