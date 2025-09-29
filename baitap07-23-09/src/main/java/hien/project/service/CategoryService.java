package hien.project.service;

import hien.project.entity.Category;
import hien.project.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {
	private final CategoryRepository categoryRepository;

	public Page<Category> getAllCategories(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public Page<Category> searchCategories(String keyword, Pageable pageable) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return getAllCategories(pageable);
		}
		return categoryRepository.findByNameContainingIgnoreCase(keyword, pageable);
	}

	public Category saveCategory(Category category) {
		if (category.getCategoryId() == null && categoryRepository.existsByCategoryName(category.getCategoryName())) {
			throw new RuntimeException("Tên danh mục đã tồn tại!");
		}
		if (category.getCategoryId() != null && categoryRepository
				.existsByCategoryNameAndCategoryIdNot(category.getCategoryName(), category.getCategoryId())) {
			throw new RuntimeException("Tên danh mục đã tồn tại!");
		}
		return categoryRepository.save(category);
	}

	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy danh mục!"));
	}

	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}