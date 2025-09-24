package hien.project.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String fullname;
	private String email;
	private String password;
	private String phone;

	@OneToMany(mappedBy = "user")
	private Set<Product> products = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "user_categories", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
}