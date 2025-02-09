package com.example.demo.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.OrderCancelDto;
import com.example.demo.Dto.OrderDto;
import com.example.demo.entites.Invoice;
import com.example.demo.entites.Order;
import com.example.demo.entites.OrderManagement;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.OutOfStockException;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.OrderManagementRepository;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderServcie {

	private InvoiceRepository invoiceRepository;

	private OrderManagementRepository orderManagementRepository;

	private OrderRepository orderRepository;

	@Autowired
	public OrderServcie(InvoiceRepository invoiceRepository, OrderManagementRepository orderManagementRepository,
			OrderRepository orderRepository) {
		this.invoiceRepository = invoiceRepository;
		this.orderManagementRepository = orderManagementRepository;
		this.orderRepository = orderRepository;
	}
	@Transactional
	public String createOrder(OrderDto orderDto) {

		if (orderDto != null) {
			// inventory status check
			if (true) {
				Order order = new Order();
				order.setOrderDate(LocalDate.now());
				
				OrderManagement orderManagement = new OrderManagement();
				orderManagement.setOrder(order);
				orderManagement.setCount(orderDto.getQuantity());
				orderManagement.setProductId(orderDto.getProductId());
				
				order.setOrderManagement(orderManagement);
				order.setUserid((long) 23);
				Invoice invoice = new Invoice();
				invoice.setBilldate(LocalDate.now());
				invoice.setOrderManagement(orderManagement);
				invoice.setPrice(0);
				
				orderManagement.setInvoice(invoice);

				orderRepository.save(order);
				invoiceRepository.save(invoice);
				orderManagementRepository.save(orderManagement);
			} else {
				throw new OutOfStockException("Out of stock");
			}
		} else {
			throw new BadRequestException("bad Request");
		}

		return null;
	}
@Transactional
	public String cancelOrder(OrderCancelDto orderCancelDto) {
		
		if(orderCancelDto!=null) {
			orderRepository.deleteById(orderCancelDto.getOrderid());
		}else {
			throw new BadRequestException("bad cancel order request");
		}
		
		return null;
	}
}
