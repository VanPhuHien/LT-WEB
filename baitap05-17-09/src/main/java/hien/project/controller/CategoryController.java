package hien.project.controller;

import hien.project.entity.Category;
import hien.project.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;

	@GetMapping
	public String listCategories(@RequestParam(defaultValue = "0") int page,
			@RequestParam(required = false) String keyword, Model model) {
		Pageable pageable = PageRequest.of(page, 5);
		Page<Category> categories;
		if (keyword == null || keyword.trim().isEmpty()) {
			categories = categoryService.getAllCategories(pageable);
			model.addAttribute("categories", categories);
			return "fragments/content/list";
		} else {
			categories = categoryService.searchCategories(keyword, pageable);
			model.addAttribute("categories", categories);
			model.addAttribute("keyword", keyword);
			model.addAttribute("currentPage", page);
			return "fragments/content/searchpaging";
		}
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("category", Category.builder().build());
		model.addAttribute("isEdit", false);
		return "fragments/content/addoredit";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Category category = categoryService.getCategoryById(id);
		model.addAttribute("category", category);
		model.addAttribute("isEdit", true);
		return "fragments/content/addoredit";
	}

	@PostMapping("/save")
	public String saveCategory(@Valid @ModelAttribute Category category, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("isEdit", category.getId() != null);
			return "fragments/content/addoredit";
		}
		try {
			categoryService.saveCategory(category);
			return "redirect:/categories";
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("isEdit", category.getId() != null);
			return "fragments/content/addoredit";
		}
	}

	@PostMapping("/delete/{id}")
	public String deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return "redirect:/categories";
	}
}