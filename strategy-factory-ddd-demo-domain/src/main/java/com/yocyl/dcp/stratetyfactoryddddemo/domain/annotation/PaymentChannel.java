package com.yocyl.dcp.stratetyfactoryddddemo.domain.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: success
 * @date: 2025/10/19 15:55
 * @version: v1.0.0
 * @description: TODO
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PaymentChannel {

    String value();

}
