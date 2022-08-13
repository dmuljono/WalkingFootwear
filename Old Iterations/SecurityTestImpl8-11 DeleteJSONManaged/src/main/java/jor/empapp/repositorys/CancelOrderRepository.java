package jor.empapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import jor.empapp.models.CancelOrder;
import jor.empapp.models.OrderForm;
import jor.empapp.models.ReturnOrder;

@CrossOrigin("http://localhost:4200")
public interface CancelOrderRepository extends JpaRepository<CancelOrder, Long> {

}
