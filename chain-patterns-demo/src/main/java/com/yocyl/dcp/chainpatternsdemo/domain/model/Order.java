package com.yocyl.dcp.chainpatternsdemo.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: success
 * @date: 2025/10/18 12:31
 * @version: v1.0.0
 * @description: 建造器模式构建Order
 **/
@Data
public class Order {

    private String orderId;

    private String userId;

    private String productId;

    private Integer quantity;

    private String couponCode;

    private BigDecimal amount;

    public Order(Builder builder){
        this.amount = builder.amount;
        this.userId = builder.userId;
        this.orderId = builder.orderId;
        this.productId = builder.productId;
        this.quantity = builder.quantity;
        this.couponCode = builder.couponCode;
    }

    public static class Builder {
        private String orderId;

        private String userId;

        private String productId;

        private Integer quantity;

        private String couponCode;

        private BigDecimal amount;

        public Builder(){}

        public Builder orderId(String orderId){
            this.orderId = orderId;
            return this;
        }
        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder productId(String productId){
            this.productId = productId;
            return this;
        }
        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder couponCode(String ocouponCode){
            this.couponCode = ocouponCode;
            return this;
        }
        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Order Build() {
            return new Order(this);
        }
    }
}
