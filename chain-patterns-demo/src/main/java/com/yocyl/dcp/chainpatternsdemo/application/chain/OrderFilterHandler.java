package com.yocyl.dcp.chainpatternsdemo.application.chain;

import com.yocyl.dcp.chainpatternsdemo.domain.model.Order;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Result;

import java.util.Comparator;
import java.util.List;

/**
 * @author: success
 * @date: 2025/10/18 13:24
 * @version: v1.0.0
 * @description: 订单过滤器处理器（责任链编排）
 **/
public class OrderFilterHandler {
    private List<OrderFilter> orderFilterList;

    public OrderFilterHandler(List<OrderFilter> orderFilterList) {
        this.orderFilterList = orderFilterList;
    }

    public Result execute(Order order) {
        // 按code排序
        orderFilterList.sort(Comparator.comparingInt(OrderFilter::getCode));
        
        // 依次执行，任一失败则短路返回
        for (OrderFilter filter : orderFilterList) {
            Result result = filter.doFilter(order);
            if (!result.isSuccess()) {
                return result; // 短路
            }
        }
        
        return Result.success("所有校验通过");
    }
}
