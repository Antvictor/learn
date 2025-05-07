package com.tdchain.spring.state.annotation;

import com.tdchain.spring.state.constant.OrderState;
import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Antvictor
 * @date 2024/3/2
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface StatesOnTransition {

    OrderState[] source() default {};

    OrderState[] target() default {};
}
