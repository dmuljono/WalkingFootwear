package jor.empapp.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jor.empapp.models.Customer;
import jor.empapp.models.Employee;
import jor.empapp.repositorys.CustomerRepository;
import jor.empapp.repositorys.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Customer customer;
		Employee employee;
		try {
			customer = customerRepository.findByEmail(email)
					.orElseThrow(() -> 
					new UsernameNotFoundException("Customer Not Found with email: " + email));
				return UserDetailsImpl.build(customer);
		} catch (UsernameNotFoundException e) {
			//Skipped
		}
		try {
			employee = employeeRepository.findByEmail(email)
					.orElseThrow(() -> 
					new UsernameNotFoundException("Manager Not Found with email: " + email));
			return UserDetailsImpl.build(employee);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("No Manager or Customer found with email: " + email);
		}
		

	}

}