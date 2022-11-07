package com.restaurants.app.dao;

import com.restaurants.app.domains.TableOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableOrdeDao extends JpaRepository<TableOrder, Long> {
//    @Query(value = "select enable from table_order u WHERE u.table_no = 1",  nativeQuery = true)
//    boolean isTableReserved(Long tableNo);
}
