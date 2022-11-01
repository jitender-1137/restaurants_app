package com.restaurants.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "username")
    private String userName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String UpdatedAt;

    @Column(name = "password")
    private String password;

}
