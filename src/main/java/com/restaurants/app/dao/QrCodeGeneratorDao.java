package com.restaurants.app.dao;

import com.restaurants.app.domains.TableQrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QrCodeGeneratorDao extends JpaRepository<TableQrCode, Long> {


    @Query(value = "select u from TableQrCode u where u.tableNo=?1 AND u.enable=true ")
    TableQrCode findByTableNo(Long tableNo);

    @Query(value = "select u from TableQrCode u where u.enable=true ")
    List<TableQrCode> findAllEnabled();
}
