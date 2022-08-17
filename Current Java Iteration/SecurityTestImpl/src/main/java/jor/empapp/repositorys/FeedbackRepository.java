package jor.empapp.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jor.empapp.models.Feedback;



@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	
	List<Feedback> findByCustomerCustomerId(Long customerId);
	
	List<Feedback> findByProductProductId(Long productId);
	
}