package com.yocyl.dcp.stratetyfactoryddddemo.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: success
 * @date: 2025/10/19 14:22
 * @version: v1.0.0
 * @description: 订单状态描述
 **/
@Getter
@AllArgsConstructor
public enum OrderStatus {

    PENDING(1,"待支付"),

    SUCCESS(2,"支付成功"),

    FAILED(3,"支付失败");

    private final Integer code;

    private final String desc;

}