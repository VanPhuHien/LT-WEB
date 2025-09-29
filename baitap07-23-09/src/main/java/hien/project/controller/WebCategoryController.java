package hien.project.controller;

import hien.project.entity.Category;
import hien.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categories")
public class WebCategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public String listCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(required = false) String keyword, Model model) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Category> categories = categoryService.searchCategories(keyword, pageable);
		model.addAttribute("categories", categories);
		model.addAttribute("currentPage", page);
		return "fragments/content/list"; // Trả về template list.html
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("category", new Category());
		return "fragments/content/addoredit"; // Trả về template addoredit.html
	}
}