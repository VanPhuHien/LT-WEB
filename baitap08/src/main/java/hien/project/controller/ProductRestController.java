package hien.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hien.project.entity.Product;
import hien.project.repository.ProductRepository;

@RestController
@RequestMapping("/api/rest/products")
public class ProductRestController {

	@Autowired
	private ProductRepository repository;

	@GetMapping
	public List<Product> getAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id) {
		return repository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return repository.save(product);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product updatedProduct) {
		return repository.findById(id).map(product -> {
			product.setTitle(updatedProduct.getTitle());
			product.setQuantity(updatedProduct.getQuantity());
			product.setDescription(updatedProduct.getDescription());
			product.setPrice(updatedProduct.getPrice());
			return ResponseEntity.ok(repository.save(product));
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}