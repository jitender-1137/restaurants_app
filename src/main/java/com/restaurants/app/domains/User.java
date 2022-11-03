package com.restaurants.app.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name")
	private String name;

	@Column(name = "username")
	private String userName;

	@Column(name = "designation")
	private String designation;

	@Column(name = "created_at")
	private Long createdAt;

	@Column(name = "updated_at")
	private Long updatedAt;

	@Column(name = "password")
	private String password;

}
