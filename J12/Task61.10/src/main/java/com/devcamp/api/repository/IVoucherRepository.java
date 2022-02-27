package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.model.CVoucher;

public interface IVoucherRepository extends JpaRepository<CVoucher, Long>{

}
