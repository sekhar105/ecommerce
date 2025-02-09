package com.example.demo;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
import com.example.demo.service.OrderServcie;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OrderManagementRepository orderManagementRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServcie orderServcie;

    private OrderDto orderDto;
    private OrderCancelDto orderCancelDto;

    @Before
    public void setUp() {
        orderDto = new OrderDto();
        orderDto.setProductId(1L);
        orderDto.setQuantity(2);

        orderCancelDto = new OrderCancelDto();
        orderCancelDto.setOrderid(1L);
    }

    @Test
    @Transactional
    public void testCreateOrder() {
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());
        when(orderManagementRepository.save(any(OrderManagement.class))).thenReturn(new OrderManagement());
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(new Invoice());

        String result = orderServcie.createOrder(orderDto);

        assertNull(result);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderManagementRepository, times(1)).save(any(OrderManagement.class));
        verify(invoiceRepository, times(1)).save(any(Invoice.class));
    }

    @Test(expected = OutOfStockException.class)
    @Transactional
    public void testCreateOrder_OutOfStock() {
        when(orderRepository.save(any(Order.class))).thenThrow(new OutOfStockException("Out of stock"));

        orderServcie.createOrder(orderDto);
    }

    @Test(expected = BadRequestException.class)
    @Transactional
    public void testCreateOrder_BadRequest() {
        orderServcie.createOrder(null);
    }

    @Test
    @Transactional
    public void testCancelOrder() {
        doNothing().when(orderRepository).deleteById(anyLong());

        String result = orderServcie.cancelOrder(orderCancelDto);

        assertNull(result);
        verify(orderRepository, times(1)).deleteById(anyLong());
    }

    @Test(expected = BadRequestException.class)
    @Transactional
    public void testCancelOrder_BadRequest() {
        orderServcie.cancelOrder(null);
    }
}
