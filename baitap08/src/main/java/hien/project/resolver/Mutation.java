package hien.project.resolver;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import hien.project.entity.Category;
import hien.project.entity.Product;
import hien.project.entity.User;
import hien.project.repository.CategoryRepository;
import hien.project.repository.ProductRepository;
import hien.project.repository.UserRepository;

@Controller
public class Mutation {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	// Product CRUD
	@MutationMapping
	public Product createProduct(@Argument String title, @Argument Integer quantity, @Argument String description,
			@Argument Double price, @Argument Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Product product = new Product();
		product.setTitle(title);
		product.setQuantity(quantity);
		product.setDescription(description);
		product.setPrice(price);
		product.setUser(user);
		return productRepository.save(product);
	}

	@MutationMapping
	public Product updateProduct(@Argument Long id, @Argument String title, @Argument Integer quantity,
			@Argument String description, @Argument Double price) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		if (title != null)
			product.setTitle(title);
		if (quantity != null)
			product.setQuantity(quantity);
		if (description != null)
			product.setDescription(description);
		if (price != null)
			product.setPrice(price);
		return productRepository.save(product);
	}

	@MutationMapping
	public Boolean deleteProduct(@Argument Long id) {
		productRepository.deleteById(id);
		return true;
	}

	// Category CRUD
	@MutationMapping
	public Category createCategory(@Argument String name, @Argument String images) {
		Category category = new Category();
		category.setName(name);
		category.setImages(images);
		return categoryRepository.save(category);
	}

	@MutationMapping
	public Category updateCategory(@Argument Long id, @Argument String name, @Argument String images) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Category not found"));
		if (name != null)
			category.setName(name);
		if (images != null)
			category.setImages(images);
		return categoryRepository.save(category);
	}

	@MutationMapping
	public Boolean deleteCategory(@Argument Long id) {
		categoryRepository.deleteById(id);
		return true;
	}

	// User CRUD (with categories as Set<Long> ids for input)
	@MutationMapping
	public User createUser(@Argument String fullname, @Argument String email, @Argument String password,
			@Argument String phone, @Argument Set<Long> categoryIds) {
		User user = new User();
		user.setFullname(fullname);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		if (categoryIds != null) {
			Set<Category> categories = new java.util.HashSet<>();
			for (Long catId : categoryIds) {
				categories.add(categoryRepository.findById(catId)
						.orElseThrow(() -> new RuntimeException("Category not found")));
			}
			user.setCategories(categories);
		}
		return userRepository.save(user);
	}

	@MutationMapping
	public User updateUser(@Argument Long id, @Argument String fullname, @Argument String email,
			@Argument String password, @Argument String phone, @Argument Set<Long> categoryIds) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		if (fullname != null)
			user.setFullname(fullname);
		if (email != null)
			user.setEmail(email);
		if (password != null)
			user.setPassword(password);
		if (phone != null)
			user.setPhone(phone);
		if (categoryIds != null) {
			Set<Category> categories = new java.util.HashSet<>();
			for (Long catId : categoryIds) {
				categories.add(categoryRepository.findById(catId)
						.orElseThrow(() -> new RuntimeException("Category not found")));
			}
			user.setCategories(categories);
		}
		return userRepository.save(user);
	}

	@MutationMapping
	public Boolean deleteUser(@Argument Long id) {
		userRepository.deleteById(id);
		return true;
	}
}