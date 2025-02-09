package com.example.demo.entites;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_management")
public class OrderManagement extends BaseEntity{

	
	@OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
	@Column(name = "product_id")
	private long productId;
	@Column(name = "product_count")
	private int count;
	@OneToOne(cascade =CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="invoice_id",referencedColumnName ="id")
	private Invoice invoice;
	
	
}
