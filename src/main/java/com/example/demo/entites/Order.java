package com.example.demo.entites;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_table")
public class Order extends BaseEntity {

	@Column(name = "user_id", nullable = false)
	private Long userid;
	
	@Column(name = "order_date", nullable = false)
	private LocalDate orderDate;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
	private OrderManagement orderManagement;
}

