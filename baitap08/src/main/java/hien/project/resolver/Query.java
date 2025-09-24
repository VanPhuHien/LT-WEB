package hien.project.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import hien.project.entity.Category;
import hien.project.entity.Product;
import hien.project.entity.User;
import hien.project.repository.CategoryRepository;
import hien.project.repository.ProductRepository;
import hien.project.repository.UserRepository;

@Controller
public class Query {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@QueryMapping
	public List<Product> productsSortedByPrice() {
		return productRepository.findAllByOrderByPriceAsc();
	}

	@QueryMapping
	public List<Product> productsByCategory(@Argument Long categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@QueryMapping
	public List<Product> allProducts() {
		return productRepository.findAll();
	}

	@QueryMapping
	public Product product(@Argument Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@QueryMapping
	public List<Category> allCategories() {
		return categoryRepository.findAll();
	}

	@QueryMapping
	public Category category(@Argument Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@QueryMapping
	public List<User> allUsers() {
		return userRepository.findAll();
	}

	@QueryMapping
	public User user(@Argument Long id) {
		return userRepository.findById(id).orElse(null);
	}
}