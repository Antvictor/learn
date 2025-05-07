package com.tdchain.spring.state.service;

import com.tdchain.spring.state.annotation.StatesOnTransition;
import com.tdchain.spring.state.constant.OrderEvent;
import com.tdchain.spring.state.constant.OrderState;
import com.tdchain.spring.state.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * @author Antvictor
 * @date 2024/3/2
 **/
@Component
@Slf4j
@WithStateMachine(name = "orderStateMachine")
public class OrderStateService {

    @StatesOnTransition(source = OrderState.INIT, target = OrderState.PAYED)
    public Object pay(Message<OrderEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        order.setOrderState(OrderState.PAYED);
        System.out.println("init to payed");
        return "payed";
    }
}
