package com.restaurants.app.dto;

import com.restaurants.app.enums.OrderByType;
import com.restaurants.app.enums.QueriesCombinationType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class GenericSearchFilter {

    private QueriesCombinationType combinationType = QueriesCombinationType.all_and;
    private Map<String, ComparativeRelationAndValue> searchParams;
    private Map<String, OrderByType> orderBy;
}
