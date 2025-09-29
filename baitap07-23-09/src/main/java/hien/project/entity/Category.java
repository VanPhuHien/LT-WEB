package hien.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@Column(name = "icon")
	private String icon;

	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private Set<Product> products;

}