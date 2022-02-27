package com.devcamp.repository;

import com.devcamp.model.CVoucher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVoucherRepository extends JpaRepository<CVoucher, Long> {
}
