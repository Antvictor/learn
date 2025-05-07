package com.tdchain.spring.state.domain;

import com.tdchain.spring.state.constant.OrderState;
import lombok.Data;

/**
 * @author Antvictor
 * @date 2024/3/2
 **/
@Data
public class Order {
    private OrderState orderState;
}
