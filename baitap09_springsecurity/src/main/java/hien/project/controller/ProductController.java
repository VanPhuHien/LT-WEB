package hien.project.controller;

import hien.project.entity.Product;
import hien.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping({ "/", "/products" })
	public String index(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "index";
	}

	@GetMapping("/products/new")
	public String newProduct(Model model) {
		model.addAttribute("product", new Product());
		return "new_product";
	}

	@PostMapping("/products/save")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.saveProduct(product);
		return "redirect:/products";
	}

	@GetMapping("/products/edit/{id}")
	public String editProduct(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id)));
		return "edit_product";
	}

	@PostMapping("/products/update/{id}")
	public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product) {
		Product existingProduct = productService.getProductById(id)
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		productService.updateProduct(existingProduct);
		return "redirect:/products";
	}

	@GetMapping("/products/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProductById(id);
		return "redirect:/products";
	}
}