package jor.empapp.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jor.empapp.models.ERole;
import jor.empapp.models.EmployeeRole;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {
	Optional<EmployeeRole> findByName(ERole name);
}