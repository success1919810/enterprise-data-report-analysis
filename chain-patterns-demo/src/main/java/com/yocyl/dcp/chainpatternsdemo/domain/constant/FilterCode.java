package com.yocyl.dcp.chainpatternsdemo.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author: success
 * @date: 2025/10/18 12:47
 * @version: v1.0.0
 * @description: TODO
 **/
@Getter
@AllArgsConstructor
public enum FilterCode {

    STOCK(10,"库存"),

    RISK(20,"风险"),

    COUPON(30,"优惠券");

    private final Integer code;

    private final String desc;

}
