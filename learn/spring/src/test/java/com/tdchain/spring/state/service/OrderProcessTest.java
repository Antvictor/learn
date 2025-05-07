package com.tdchain.spring.state.service;

import com.tdchain.spring.state.constant.OrderEvent;
import com.tdchain.spring.state.constant.OrderState;
import com.tdchain.spring.state.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Antvictor
 * @date 2024/3/2
 **/
@SpringBootTest
@Slf4j
public class OrderProcessTest {

    @Resource
    private OrderProcess orderProcess;

    @Test
    public void pay() {
        Order order = new Order();
        order.setOrderState(OrderState.INIT);
        System.out.println(order.getOrderState());
        orderProcess.process(order, OrderEvent.PAY);
        System.out.println(order.getOrderState());
    }
}