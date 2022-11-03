package com.restaurants.app.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@javax.persistence.Table(name = "order")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private String orderId;

	@Column(name = "user_name")
	private String name;

	@Column(name = "table_no")
	private String tableNo;

	@Column(name = "created_order_at")
	private Long createdOrderAt;

	@Column(name = "finish_order_at")
	private Long finishOrderdAt;

	@Column(name = "checkout")
	private boolean checkout;

	@Column(name = "items")
	private Items items;

}
