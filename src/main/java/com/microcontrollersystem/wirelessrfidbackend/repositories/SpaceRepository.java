package com.microcontrollersystem.wirelessrfidbackend.repositories;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceRepository extends JpaRepository<Space, Integer> {
    List<Space> findAllByStatusTrue();
}
