package com.restaurants.app.co;

import com.restaurants.app.annotations.EnsureNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TableQrCodeCo {

    private Long tableQRId;

    @NotBlank
    private String tableName;

    @NotBlank
    private String tableNo;

    private boolean enable;

}
