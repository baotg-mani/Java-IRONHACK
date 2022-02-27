package com.devcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.model.CMenu;

public interface IMenuRepository extends JpaRepository<CMenu, Long> {

}
