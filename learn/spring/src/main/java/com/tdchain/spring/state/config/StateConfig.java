package com.tdchain.spring.state.config;

import com.tdchain.spring.state.constant.OrderEvent;
import com.tdchain.spring.state.constant.OrderState;
import com.tdchain.spring.state.domain.Order;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.state.StateListener;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

/**
 * @author Antvictor
 * @date 2024/3/2
 **/
@Configuration
@EnableStateMachine(name = "orderStateMachine")
public class StateConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {
    /**
     * 配置监听
     * @param config
     * @throws Exception
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    /**
     * 初始化状态，并配置对应可变化的状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderState.INIT)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions.withExternal()
                // 支付
                .source(OrderState.INIT).target(OrderState.PAYED).event(OrderEvent.PAY)
                .and()
                // 发货
                .withExternal().source(OrderState.PAYED).target(OrderState.DELIVERED).event(OrderEvent.DELIVER)
                .and()
                // 收货
                .withExternal().source(OrderState.DELIVERED).target(OrderState.FINISHED).event(OrderEvent.RECEIVE)
                .and()
                // 取消
                .withExternal().source(OrderState.INIT).target(OrderState.CANCELED).event(OrderEvent.CANCEL)
                .and()
                // 退款
                .withExternal().source(OrderState.PAYED).target(OrderState.REFUNDED).event(OrderEvent.REFUND)
                .and()
                // 关闭
                .withExternal().source(OrderState.INIT).target(OrderState.CLOSED).event(OrderEvent.CLOSE);

    }

    @Bean
    public StateMachineListener<OrderState, OrderEvent> listener() {
        return new StateMachineListenerAdapter<OrderState, OrderEvent>() {
            @Override
            public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
                OrderState state = null != from ? from.getId() : null;
                System.out.println("State " + state + " change to " + to.getId());
            }
        };
    }

    /**
     * 持久化
     * @return
     */
    @Bean
    public DefaultStateMachinePersister persister() {
        return new DefaultStateMachinePersister(new StateMachinePersist<OrderState, OrderEvent, Order>() {

            @Override
            public void write(StateMachineContext<OrderState, OrderEvent> stateMachineContext, Order order) throws Exception {
                // 持久化
            }

            @Override
            public StateMachineContext read(Order o) throws Exception {
                // 没有持久化，直接返回了
                return new DefaultStateMachineContext(o.getOrderState(), null, null, null);
            }
        });
    }
}
