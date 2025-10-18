package com.yocyl.dcp.chainpatternsdemo.application.chain;

import com.yocyl.dcp.chainpatternsdemo.domain.constant.FilterCode;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Order;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: success
 * @date: 2025/10/18 13:45
 * @version: v1.0.0
 * @description: 库存校验过滤器
 **/
public class StockFilter extends OrderFilter{
    //mock数据
    public Map<String,Integer> stockMap = new HashMap<>();

    public StockFilter() {
        this.stockMap.put("R100",100);
        this.stockMap.put("R1",0);
    }
    @Override
    public Integer getCode() {
        return FilterCode.STOCK.getCode();
    }

    @Override
    public Result doFilter(Order order) {
        System.out.println("执行库存校验...");
        
        Integer stock = stockMap.getOrDefault(order.getProductId(), 0);
        
        if (stock < order.getQuantity()) {
            return Result.fail("库存不足：商品" + order.getProductId() + 
                "库存" + stock + "，需要" + order.getQuantity());
        }
        
        System.out.println("✓ 库存校验通过");
        return Result.success("库存充足");
    }
}
