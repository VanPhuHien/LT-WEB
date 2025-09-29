package hien.project.repository;

import hien.project.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByProductNameContaining(String name);

	@Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<Product> findByNameContainingIgnoreCase(@Param("keyword") String keyword, Pageable pageable);

	boolean existsByProductName(String productName);

	boolean existsByProductNameAndProductIdNot(String productName, Long productId);

}