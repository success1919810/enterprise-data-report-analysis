package com.yocyl.dcp.chainpatternsdemo.application.chain;

import com.yocyl.dcp.chainpatternsdemo.domain.constant.FilterCode;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Order;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Result;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: success
 * @date: 2025/10/18 13:45
 * @version: v1.0.0
 * @description: 优惠券校验过滤器
 **/
public class CouponFilter extends OrderFilter{
    
    // 模拟优惠券数据
    private Map<String, Coupon> couponMap = new HashMap<>();
    
    public CouponFilter() {
        couponMap.put("COUPON_100", new Coupon(new BigDecimal("100"), true));
        couponMap.put("COUPON_200", new Coupon(new BigDecimal("200"), false)); // 已使用
        couponMap.put("COUPON_500", new Coupon(new BigDecimal("500"), true));
    }
    
    @Override
    public Integer getCode() {
        return FilterCode.COUPON.getCode();
    }

    @Override
    public Result doFilter(Order order) {
        // 无优惠券直接通过
        if (order.getCouponCode() == null || order.getCouponCode().isEmpty()) {
            System.out.println("✓ 无优惠券，跳过校验");
            return Result.success("无优惠券");
        }
        
        System.out.println("执行优惠券校验...");
        
        Coupon coupon = couponMap.get(order.getCouponCode());
        if (coupon == null) {
            return Result.fail("优惠券不存在：" + order.getCouponCode());
        }
        
        if (!coupon.isValid()) {
            return Result.fail("优惠券已使用或已过期：" + order.getCouponCode());
        }
        
        if (order.getAmount().compareTo(coupon.getMinAmount()) < 0) {
            return Result.fail("优惠券使用条件不满足：需满" + coupon.getMinAmount() + "元");
        }
        
        System.out.println("✓ 优惠券校验通过");
        return Result.success("优惠券有效");
    }
    
    // 内部类：优惠券模型
    static class Coupon {
        private BigDecimal minAmount;
        private boolean valid;
        
        public Coupon(BigDecimal minAmount, boolean valid) {
            this.minAmount = minAmount;
            this.valid = valid;
        }
        
        public BigDecimal getMinAmount() { return minAmount; }
        public boolean isValid() { return valid; }
    }
}
