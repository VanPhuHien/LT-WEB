package hien.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hien.project.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByOrderByPriceAsc();

	// Custom query for products by category (via users)
	@Query("SELECT p FROM Product p WHERE p.user IN (SELECT u FROM User u JOIN u.categories c WHERE c.id = ?1)")
	List<Product> findByCategoryId(Long categoryId);
}