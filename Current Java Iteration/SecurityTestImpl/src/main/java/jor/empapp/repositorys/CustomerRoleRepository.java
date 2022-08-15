package jor.empapp.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jor.empapp.models.CustomerRole;
import jor.empapp.models.ERole;

@Repository
public interface CustomerRoleRepository extends JpaRepository<CustomerRole, Long> {
	Optional<CustomerRole> findByName(ERole name);
}