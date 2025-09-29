package hien.project.controller;

import hien.project.entity.Product;
import hien.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<Page<Product>> listProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String keyword) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> products = productService.searchProducts(keyword, pageable);
		return ResponseEntity.ok(products);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}

	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		Product savedProduct = productService.saveProduct(product);
		return ResponseEntity.ok(savedProduct);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		product.setProductId(id);
		Product updatedProduct = productService.saveProduct(product);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}