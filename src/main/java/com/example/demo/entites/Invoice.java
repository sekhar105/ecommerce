package com.example.demo.entites;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "invoice_table")
public class Invoice extends BaseEntity{
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "bill_date")
	private LocalDate billdate;
	
	@OneToOne(mappedBy = "invoice", cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonBackReference
	private OrderManagement orderManagement;
}
