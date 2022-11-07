package com.restaurants.app.domains;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "items")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long itemId;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "username")
	private String userName;

	@Column(name = "description")
	private String description;

	@Column(name = "created_at")
	private Long createdAt;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "price")
	private String price;

}
