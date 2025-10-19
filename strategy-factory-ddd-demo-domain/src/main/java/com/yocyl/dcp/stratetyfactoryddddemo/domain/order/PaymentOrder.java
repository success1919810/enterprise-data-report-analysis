package com.yocyl.dcp.stratetyfactoryddddemo.domain.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yocyl.dcp.stratetyfactoryddddemo.domain.constant.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author: success
 * @date: 2025/10/19 14:08
 * @version: v1.0.0
 * @description: 支付订单充血模型
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrder {

    private String orderId;

    private String userId;

    private BigDecimal amount;

    private String channel;

    private String productDesc;

    private OrderStatus status;

    private LocalDateTime creatTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    /**
     * 标记订单完成
     */
    public void markSuccess() {
        if(this.status!=OrderStatus.PENDING){
            throw new IllegalStateException("只有待支付订单才能标记成功，当前状态：" + this.status);
        }
        this.status = OrderStatus.SUCCESS;
        this.payTime = LocalDateTime.now();
    }
    /**
     * 标记订单失败
     */
    public void markFailed() {
        if(this.status!=OrderStatus.PENDING){
            throw new IllegalStateException("只有待支付订单才能标记失败，当前状态：" + this.status);
        }
        this.status = OrderStatus.FAILED;
        this.payTime = LocalDateTime.now();
    }

}
