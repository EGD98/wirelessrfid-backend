package com.microcontrollersystem.wirelessrfidbackend.repositories;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.CatUserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatUserTypeRepository  extends JpaRepository<CatUserType, Integer> {
    List<CatUserType> findAllByStatusTrue();
}
