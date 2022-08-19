package jor.empapp.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;


import jor.empapp.models.WalkinOrderForm;

@CrossOrigin("http://localhost:4200")
public interface WalkinOrderFormRepository extends JpaRepository<WalkinOrderForm, Long> {

}
