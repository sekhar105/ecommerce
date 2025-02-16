package com.example.demo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.Dto.OrderCancelDto;
import com.example.demo.Dto.OrderDto;
import com.example.demo.entites.Invoice;
import com.example.demo.entites.Order;
import com.example.demo.entites.OrderManagement;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.OrderManagementRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderServcie;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

	@InjectMocks
	private OrderServcie orderServcie;
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private InvoiceRepository invoiceRepository;
	@Mock
	private OrderManagementRepository orderManagementRepository;
@Test 
	public void testCancelOrderValidate() {
		OrderCancelDto orderCancelDto = new OrderCancelDto();
		orderCancelDto.setOrderid(1l);
	 boolean cancelOrder = orderServcie.cancelOrder(orderCancelDto);
	 verify(orderRepository,times(1)).deleteById(1l);
		assertEquals(true, cancelOrder);
	}
@Test
public void testCancelOrderValidate_badRequestException() {
	OrderCancelDto orderCancelDto = new OrderCancelDto();
	orderCancelDto.setOrderid(-1l);
	BadRequestException badRequestException = assertThrows(BadRequestException.class, ()->{
		orderServcie.cancelOrder(null);
	});
	assertEquals("bad cancel order request", badRequestException.getMessage());
	verify(orderRepository, never()).deleteById(anyLong());
}

@Test
 void testCreateOrder() {
	OrderDto orderDto = new OrderDto();
	orderDto.setProductId(1l);
	orderDto.setQuantity(2);
	orderDto.setUserId(2l);
	
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
	when(orderRepository.save(any(Order.class))).thenReturn(order);
	when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
	when(orderManagementRepository.save(any(OrderManagement.class))).thenReturn(orderManagement);
	
	boolean createOrder = orderServcie.createOrder(orderDto);
	assertTrue(createOrder);
	
	
}
@Test	
public void testCreateOrderBadRequestException() {
	OrderDto orderDto=null;
	
	BadRequestException badRequestException= assertThrows(BadRequestException.class, ()->{
		orderServcie.createOrder(orderDto);
	});
	
	assertEquals("bad Request",badRequestException.getMessage());
}
	
	
}
