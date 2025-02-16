package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.OrderCancelDto;
import com.example.demo.Dto.OrderDto;
import com.example.demo.service.OrderServcie;
@RestController
@RequestMapping("/v1/api/users")
public class OrderController {

	private OrderServcie orderServcie;
	
	public OrderController(OrderServcie orderServcie) {
		this.orderServcie=orderServcie;
	}
	
	@PostMapping("/order")
	public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto){
		
		this.orderServcie.createOrder(orderDto);
		return new ResponseEntity<String>("order placed successfully",HttpStatus.CREATED);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteOrder(@RequestBody OrderCancelDto orderCancelDto){
		//added comment
		this.orderServcie.cancelOrder(orderCancelDto);

		// added newe             lien aat 34
		// added new line in line no 34

		return new ResponseEntity<String>("order deleted successfull",HttpStatus.OK);
	}
}
