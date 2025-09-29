package hien.project.controller;

import hien.project.entity.Product;
import hien.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class WebProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public String listProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(required = false) String keyword, Model model) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> products = productService.searchProducts(keyword, pageable);
		model.addAttribute("products", products);
		model.addAttribute("keyword", keyword);
		model.addAttribute("currentPage", page);
		model.addAttribute("contentTemplate", "fragments/content/list-product");
		return "layouts/layout_admin";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("isEdit", false);
		model.addAttribute("contentTemplate", "fragments/content/addoredit-product");
		return "layouts/layout_admin";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("isEdit", true);
		model.addAttribute("contentTemplate", "fragments/content/addoredit-product");
		return "layouts/layout_admin";
	}

	@PostMapping("/save")
	public String saveProduct(@Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("isEdit", product.getProductId() != null);
			model.addAttribute("contentTemplate", "fragments/content/addoredit-product");
			return "layouts/layout_admin";
		}
		try {
			productService.saveProduct(product);
			return "redirect:/products";
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("isEdit", product.getProductId() != null);
			model.addAttribute("contentTemplate", "fragments/content/addoredit-product");
			return "layouts/layout_admin";
		}
	}

	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}
}