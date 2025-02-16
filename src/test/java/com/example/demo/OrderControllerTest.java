package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.Dto.OrderDto;
import com.example.demo.controller.OrderController;
import com.example.demo.service.OrderServcie;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {


    private MockMvc mockMvc;

    @Mock
    private OrderServcie orderServcie;

    @InjectMocks
    private OrderController orderController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testCreateOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setProductId(1L);
        orderDto.setQuantity(2);

//        when(orderServcie.createOrder(any(OrderDto.class))).thenReturn("order placed successfully");

        mockMvc.perform(post("/v1/api/users/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("order placed successfully"));

        verify(orderServcie, times(1)).createOrder(any(OrderDto.class));
    }

//    @Test
//    public void testDeleteOrder() throws Exception {
//        OrderCancelDto orderCancelDto = new OrderCancelDto();
//        orderCancelDto.setOrderid(1L);
//
//        when(orderServcie.cancelOrder(any(OrderCancelDto.class))).thenReturn("order deleted successfull");
//
//        mockMvc.perform(delete("/v1/api/users/delete")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(orderCancelDto)))
//                .andExpect(status().isOk())
//                .andExpect(content().string("order deleted successfull"));
//
//        verify(orderServcie, times(1)).cancelOrder(any(OrderCancelDto.class));
//    }
}
