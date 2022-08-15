package jor.empapp.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import jor.empapp.models.Product;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findByCategoryCategoryId(Long id);
	//Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
	List<Product> findByNameContaining(String name);
}
