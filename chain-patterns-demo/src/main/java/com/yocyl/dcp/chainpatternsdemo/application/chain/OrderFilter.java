package com.yocyl.dcp.chainpatternsdemo.application.chain;

import com.yocyl.dcp.chainpatternsdemo.domain.model.Order;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Result;

/**
 * @author: success
 * @date: 2025/10/18 13:23
 * @version: v1.0.0
 * @description: TODO
 **/
public abstract class OrderFilter {

    public abstract Integer getCode();

    public abstract Result doFilter(Order order);
}
