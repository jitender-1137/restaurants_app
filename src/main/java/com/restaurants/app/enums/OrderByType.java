package com.restaurants.app.enums;

import lombok.Getter;

@Getter
public enum OrderByType {
    asc("ascending"),
    desc("descending");
    String methodAssociated;

    OrderByType(String methodAssociated) {
        this.methodAssociated = methodAssociated;
    }
}
