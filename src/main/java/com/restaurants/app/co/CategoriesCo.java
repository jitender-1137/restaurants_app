package com.restaurants.app.co;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CategoriesCo {

    private Long categoryId;

    @NotBlank
    private String categoryName;

    private Long parentCategory;

    @NotBlank
    private String level;

    private boolean isActive;

}
