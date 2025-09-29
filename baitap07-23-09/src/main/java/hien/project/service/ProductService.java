package hien.project.service;

import hien.project.entity.Product;
import hien.project.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
	private final ProductRepository productRepository;

	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Page<Product> searchProducts(String keyword, Pageable pageable) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return getAllProducts(pageable);
		}
		return productRepository.findByNameContainingIgnoreCase(keyword, pageable);
	}

	public Product saveProduct(Product product) {
		if (product.getProductId() == null && productRepository.existsByProductName(product.getProductName())) {
			throw new RuntimeException("Tên sản phẩm đã tồn tại!");
		}
		if (product.getProductId() != null && productRepository
				.existsByProductNameAndProductIdNot(product.getProductName(), product.getProductId())) {
			throw new RuntimeException("Tên sản phẩm đã tồn tại!");
		}
		return productRepository.save(product);
	}

	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm!"));
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}