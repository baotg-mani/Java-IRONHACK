package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.CVoucher;

public interface IVoucherRepository extends JpaRepository<CVoucher, Long> {

}
