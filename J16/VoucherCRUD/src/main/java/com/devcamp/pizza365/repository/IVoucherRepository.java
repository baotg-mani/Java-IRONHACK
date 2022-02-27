package com.devcamp.pizza365.repository;

import com.devcamp.pizza365.model.CVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVoucherRepository extends JpaRepository<CVoucher, Long> {
}
