package com.yocyl.dcp.chainpatternsdemo;

import com.yocyl.dcp.chainpatternsdemo.application.chain.*;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Order;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Result;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * 责任链模式演示
 */
public class ChainPatternsDemoApplication {

    public static void main(String[] args) {
        // 构建责任链
        OrderFilterHandler handler = buildFilterChain();
        
        System.out.println("========================================");
        System.out.println("责任链模式 - 订单校验演示");
        System.out.println("========================================\n");
        
        // 测试用例1：正常订单（全部通过）
        testCase1_NormalOrder(handler);
        
        // 测试用例2：库存不足（第一个Filter就失败）
        testCase2_StockInsufficient(handler);
        
        // 测试用例3：风控拦截（库存过了，风控失败）
        testCase3_RiskControl(handler);
        
        // 测试用例4：优惠券无效（前两个过了，优惠券失败）
        testCase4_InvalidCoupon(handler);
    }
    
    private static OrderFilterHandler buildFilterChain() {
        // 按code自动排序：STOCK(10) -> RISK(20) -> COUPON(30)
        return new OrderFilterHandler(Arrays.asList(
            new StockFilter(),
            new RiskFilter(),
            new CouponFilter()
        ));
    }
    
    private static void testCase1_NormalOrder(OrderFilterHandler handler) {
        System.out.println("===== 测试用例1：正常订单 =====");
        Order order = new Order.Builder()
                .orderId("ORDER_001")
                .userId("USER_001")
                .productId("R100")
                .quantity(2)
                .amount(new BigDecimal("500"))
                .Build();
        
        Result result = handler.execute(order);
        System.out.println("结果：" + result.getMsg());
        System.out.println("成功：" + result.isSuccess() + "\n");
    }
    
    private static void testCase2_StockInsufficient(OrderFilterHandler handler) {
        System.out.println("===== 测试用例2：库存不足 =====");
        Order order = new Order.Builder()
                .orderId("ORDER_002")
                .userId("USER_002")
                .productId("R1")  // 库存为0
                .quantity(10)
                .amount(new BigDecimal("300"))
                .Build();
        
        Result result = handler.execute(order);
        System.out.println("结果：" + result.getMsg());
        System.out.println("成功：" + result.isSuccess() + "\n");
    }
    
    private static void testCase3_RiskControl(OrderFilterHandler handler) {
        System.out.println("===== 测试用例3：风控拦截 =====");
        Order order = new Order.Builder()
                .orderId("ORDER_003")
                .userId("USER_RISK_001")  // 风险用户
                .productId("R100")
                .quantity(1)
                .amount(new BigDecimal("200"))
                .Build();
        
        Result result = handler.execute(order);
        System.out.println("结果：" + result.getMsg());
        System.out.println("成功：" + result.isSuccess() + "\n");
    }
    
    private static void testCase4_InvalidCoupon(OrderFilterHandler handler) {
        System.out.println("===== 测试用例4：优惠券无效 =====");
        Order order = new Order.Builder()
                .orderId("ORDER_004")
                .userId("USER_003")
                .productId("R100")
                .quantity(1)
                .couponCode("COUPON_200")  // 已使用的优惠券
                .amount(new BigDecimal("300"))
                .Build();
        
        Result result = handler.execute(order);
        System.out.println("结果：" + result.getMsg());
        System.out.println("成功：" + result.isSuccess() + "\n");
    }
}
