package com.example.demo.Dto;

import lombok.Data;

@Data
public class OrderDto {

	private Long userId;
	private Long productId;
	private int quantity;

}
