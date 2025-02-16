package com.example.demo.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "order_management")
public class OrderManagement extends BaseEntity{

	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
	@JsonBackReference
    private Order order;
	@Column(name = "product_id")
	private long productId;
	@Column(name = "product_count")
	private int count;
	@OneToOne(cascade =CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="invoice_id",referencedColumnName ="id")
	@JsonManagedReference
	private Invoice invoice;
	
	
}
