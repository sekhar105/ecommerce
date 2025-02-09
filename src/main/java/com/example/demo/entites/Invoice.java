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
@Table(name = "invoice_table")
public class Invoice extends BaseEntity{
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "bill_date")
	private LocalDate billdate;
	
	@OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL,orphanRemoval = true)
	private OrderManagement orderManagement;
}
