package hien.project.controller;

import hien.project.entity.Category;
import hien.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<Page<Category>> listCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String keyword) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Category> categories = categoryService.searchCategories(keyword, pageable);
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id) {
		Category category = categoryService.getCategoryById(id);
		return ResponseEntity.ok(category);
	}

	@PostMapping
	public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
		Category savedCategory = categoryService.saveCategory(category);
		return ResponseEntity.ok(savedCategory);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
		category.setCategoryId(id);
		Category updatedCategory = categoryService.saveCategory(category);
		return ResponseEntity.ok(updatedCategory);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}
}