package com.microcontrollersystem.wirelessrfidbackend.repositories;

import com.microcontrollersystem.wirelessrfidbackend.models.orm.CatPaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatPaymentTypeRepository  extends JpaRepository<CatPaymentType, Integer> {

}


