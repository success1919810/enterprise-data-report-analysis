package com.yocyl.dcp.stratetyfactoryddddemo.domain.factory;

import com.yocyl.dcp.stratetyfactoryddddemo.domain.service.PaymentDomainService;

/**
 * @author: success
 * @date: 2025/10/19 16:06
 * @version: v1.0.0
 * @description: TODO
 **/
public interface PaymentStrategyFactory {

    PaymentDomainService getStrategy(String channel);

}
