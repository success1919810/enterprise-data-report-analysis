package com.yocyl.dcp.stratetyfactoryddddemo.domain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: success
 * @date: 2025/10/19 14:37
 * @version: v1.0.0
 * @description: 支付结果
 **/
@Data
@AllArgsConstructor
@Builder
public class PaymentResult {

    private boolean success;

    private String transactionId;

    private String channel;

    private LocalDateTime payTime;

    private String errorCode;

    private String errorMsg;

    public static PaymentResult success(String transactionId,String channel) {
        return PaymentResult.builder().success(true)
                .transactionId(transactionId).channel(channel).payTime(LocalDateTime.now()).build();
    }

    public static PaymentResult fail(String errorCode,String errorMsg) {
        return PaymentResult.builder().success(false)
                .errorCode(errorCode).errorMsg(errorMsg).payTime(LocalDateTime.now()).build();
    }
}
