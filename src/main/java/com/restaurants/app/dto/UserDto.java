package com.restaurants.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;

    //    @NotBlank
    private String name;

    //    @NotBlank
    private String designation;

    private String createdAt;

    private String UpdatedAt;

    //    @NotBlank
    private String password;

}
