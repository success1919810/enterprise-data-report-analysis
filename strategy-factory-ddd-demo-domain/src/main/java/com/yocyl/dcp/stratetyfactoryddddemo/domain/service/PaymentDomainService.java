package com.yocyl.dcp.stratetyfactoryddddemo.domain.service;

import com.yocyl.dcp.stratetyfactoryddddemo.domain.order.PaymentOrder;
import com.yocyl.dcp.stratetyfactoryddddemo.domain.order.PaymentResult;

/**
 * @author: success
 * @date: 2025/10/19 15:47
 * @version: v1.0.0
 * @description: TODO
 **/
public interface PaymentDomainService {

    public PaymentResult pay(PaymentOrder paymentOrder);

    public String getCode();
}
