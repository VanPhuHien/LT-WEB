package hien.project;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hien.project.entity.Category;
import hien.project.entity.Product;
import hien.project.entity.User;
import hien.project.repository.CategoryRepository;
import hien.project.repository.ProductRepository;
import hien.project.repository.UserRepository;

@SpringBootApplication
public class Baitap08Application {

	public static void main(String[] args) {
		SpringApplication.run(Baitap08Application.class, args);
	}

	@Bean
	CommandLineRunner initData(UserRepository userRepo, CategoryRepository catRepo, ProductRepository prodRepo) {
		return args -> {
			// Tạo data mẫu
			Category cat1 = new Category();
			cat1.setName("Thiet bi dien tu");
			cat1.setImages("tbdt.jpg");
			catRepo.save(cat1);

			Category cat2 = new Category();
			cat2.setName("Sach");
			cat2.setImages("sach.jpg");
			catRepo.save(cat2);

			User user1 = new User();
			user1.setFullname("Phu Hien");
			user1.setEmail("phuhien@gmail.com");
			user1.setPassword("123");
			user1.setPhone("123456789");
			Set<Category> categories1 = new HashSet<>();
			categories1.add(cat1);
			user1.setCategories(categories1);
			userRepo.save(user1);

			User user2 = new User();
			user2.setFullname("Hien");
			user2.setEmail("hien@gmail.com");
			user2.setPassword("456");
			user2.setPhone("987654321");
			Set<Category> categories2 = new HashSet<>();
			categories2.add(cat1);
			categories2.add(cat2);
			user2.setCategories(categories2);
			userRepo.save(user2);

			Product prod1 = new Product();
			prod1.setTitle("Laptop");
			prod1.setQuantity(10);
			prod1.setDescription("Laptop tot");
			prod1.setPrice(1000.0);
			prod1.setUser(user1);
			prodRepo.save(prod1);

			Product prod2 = new Product();
			prod2.setTitle("Sach");
			prod2.setQuantity(5);
			prod2.setDescription("Sach hay");
			prod2.setPrice(20.0);
			prod2.setUser(user2);
			prodRepo.save(prod2);
		};
	}
}