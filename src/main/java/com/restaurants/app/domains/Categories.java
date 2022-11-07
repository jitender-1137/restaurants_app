package com.restaurants.app.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "categories")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Categories {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	
	private String categoryName;
	
	private Long parentCategory;
	
	private Long childCategory;
	
	@Column(nullable = false)
	private boolean isActive;
	
	private Long createdAt;
	
	private Long updtedAt;
	
	@Column(nullable = false)
	private boolean isExists;

}
