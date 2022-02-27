package com.devcamp.task77.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.task77.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
