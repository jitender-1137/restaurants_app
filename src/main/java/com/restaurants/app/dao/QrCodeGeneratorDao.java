package com.restaurants.app.dao;

import com.restaurants.app.domains.QrCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeGeneratorDao extends JpaRepository<QrCode, Long> {

}
