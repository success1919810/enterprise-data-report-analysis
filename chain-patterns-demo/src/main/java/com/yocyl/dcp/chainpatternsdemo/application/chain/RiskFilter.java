package com.yocyl.dcp.chainpatternsdemo.application.chain;

import com.yocyl.dcp.chainpatternsdemo.domain.constant.FilterCode;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Order;
import com.yocyl.dcp.chainpatternsdemo.domain.model.Result;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: success
 * @date: 2025/10/18 13:45
 * @version: v1.0.0
 * @description: 风控校验过滤器
 **/
public class RiskFilter extends OrderFilter{
    
    // 模拟风险用户名单
    private Set<String> riskUsers = new HashSet<>();
    
    public RiskFilter() {
        riskUsers.add("USER_RISK_001");
        riskUsers.add("USER_RISK_002");
    }

    @Override
    public Integer getCode() {
        return FilterCode.RISK.getCode();
    }

    @Override
    public Result doFilter(Order order) {
        System.out.println("执行风控校验...");
        
        // 校验1：用户是否在风险名单
        if (riskUsers.contains(order.getUserId())) {
            return Result.fail("风控拦截：用户" + order.getUserId() + "存在风险，请联系客服");
        }
        
        // 校验2：单笔金额是否超限
        if (order.getAmount() != null && 
            order.getAmount().compareTo(new BigDecimal("10000")) > 0) {
            return Result.fail("风控拦截：单笔订单金额" + order.getAmount() + "元，超过限额10000元");
        }
        
        System.out.println("✓ 风控校验通过");
        return Result.success("风控检查正常");
    }
}
