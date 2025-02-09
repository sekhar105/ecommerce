package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entites.OrderManagement;
@Repository
public interface OrderManagementRepository extends JpaRepository<OrderManagement, Long>{

	
}
