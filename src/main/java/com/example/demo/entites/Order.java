package com.example.demo.entites;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "order_table")
public class Order extends BaseEntity{

	@Column(name = "user_id", nullable = false)
	private Long userid;
	
	@Column(name = "order_date", nullable = false)
	private LocalDate orderDate;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
	   @JsonManagedReference
	private OrderManagement orderManagement;
}

