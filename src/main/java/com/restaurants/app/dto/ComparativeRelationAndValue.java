package com.restaurants.app.dto;

import com.restaurants.app.enums.ComparativeRelation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ComparativeRelationAndValue {
    private Object value;
    private ComparativeRelation comparativeRelation;
}
