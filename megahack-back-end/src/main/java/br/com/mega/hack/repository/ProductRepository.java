package br.com.mega.hack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mega.hack.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("FROM Product p JOIN p.establishment e WHERE e.id= :id AND p.active= true")
	List<Product> getListProductByEstablishment(@Param("id") Long id);
	
	Optional<Product> findById(Long id);
}
