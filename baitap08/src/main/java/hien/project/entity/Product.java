package hien.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private Integer quantity;
	private String description;
	private Double price;

	@ManyToOne
	private User user;
}