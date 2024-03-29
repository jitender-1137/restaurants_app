package com.restaurants.app.domains;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "table_order")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TableOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
