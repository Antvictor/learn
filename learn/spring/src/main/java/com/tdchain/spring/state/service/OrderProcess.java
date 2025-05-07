package com.tdchain.spring.state.service;

import com.tdchain.spring.state.constant.OrderEvent;
import com.tdchain.spring.state.constant.OrderState;
import com.tdchain.spring.state.domain.Order;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Antvictor
 * @date 2024/3/2
 **/
@Component
@Slf4j
public class OrderProcess {

    @Resource
    private StateMachine<OrderState, OrderEvent> orderStateMachine;
    @Resource
    private StateMachinePersister<OrderState, OrderEvent, Order> persister;

    public void process(Order order, OrderEvent orderEvent) {
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader("order", order)
                .build();
        System.out.println(sendEvent(message));
    }

    @SneakyThrows
    private Object sendEvent(Message<OrderEvent> message){
        Order order = (Order) message.getHeaders().get("order");
        persister.restore(orderStateMachine, order);
        return orderStateMachine.sendEvent(message);
    }
}
