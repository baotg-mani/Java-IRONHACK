package com.devcamp.pizza365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.pizza365.model.CMenu;

public interface IMenuRepository extends JpaRepository<CMenu, Long>{

}
