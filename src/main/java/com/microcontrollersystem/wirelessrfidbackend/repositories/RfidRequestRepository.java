package com.microcontrollersystem.wirelessrfidbackend.repositories;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.RfidRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RfidRequestRepository extends JpaRepository<RfidRequest,Integer> {
    List<RfidRequest> findAllByStatusTrue();
}
