package jor.empapp.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jor.empapp.models.Customer;
import jor.empapp.models.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Optional<Employee> findByEmail(String email);


	Boolean existsByEmail(String email);
}