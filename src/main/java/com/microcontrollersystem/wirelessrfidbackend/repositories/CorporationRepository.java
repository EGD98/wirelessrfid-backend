package com.microcontrollersystem.wirelessrfidbackend.repositories;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation, Integer> {
    List<Corporation> findAllByStatusTrue();
}
