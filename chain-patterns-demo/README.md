# 责任链模式演示 - 订单校验

## 业务场景

用户提交订单时需要经过多重校验，每个校验独立且有固定顺序：
1. **库存校验** (优先级10)：商品是否有货
2. **风控校验** (优先级20)：用户是否有风险、金额是否超限
3. **优惠券校验** (优先级30)：优惠券是否有效、是否满足使用条件

任一校验失败，订单提交失败（短路机制）；全部通过则订单创建成功。

---

## 设计模式亮点

### 1. 责任链 + 动态排序
- 每个Filter有 `code` 字段定义执行顺序
- `OrderFilterHandler` 自动按code排序并串联
- 比硬编码 `setNext()` 更灵活

### 2. 短路机制
- Filter返回 `Result`，失败时立即中断
- 避免执行无意义的后续校验

### 3. 单一职责
- 每个Filter只负责一种校验
- 易于单独测试、修改、扩展

---

## 核心代码

### 抽象Filter
```java
public abstract class OrderFilter {
    public abstract Integer getCode();        // 执行顺序
    public abstract Result doFilter(Order order); // 校验逻辑
}
```

### 责任链编排器
```java
public class OrderFilterHandler {
    public Result execute(Order order) {
        // 1. 按code排序
        orderFilterList.sort(Comparator.comparingInt(OrderFilter::getCode));
        
        // 2. 顺序执行，失败则短路
        for (OrderFilter filter : orderFilterList) {
            Result result = filter.doFilter(order);
            if (!result.isSuccess()) {
                return result;
            }
        }
        
        return Result.success("所有校验通过");
    }
}
```

---

## 运行方式

### 直接运行Main方法
```bash
cd chain-patterns-demo
mvn clean compile
mvn exec:java -Dexec.mainClass="com.yocyl.dcp.chainpatternsdemo.ChainPatternsDemoApplication"
```

或者在IDE中直接运行 `ChainPatternsDemoApplication.main()`

### 预期输出
```
========================================
责任链模式 - 订单校验演示
========================================

===== 测试用例1：正常订单 =====
执行库存校验...
✓ 库存校验通过
执行风控校验...
✓ 风控校验通过
✓ 无优惠券，跳过校验
结果：所有校验通过
成功：true

===== 测试用例2：库存不足 =====
执行库存校验...
结果：库存不足：商品R1库存0，需要10
成功：false

===== 测试用例3：风控拦截 =====
执行库存校验...
✓ 库存校验通过
执行风控校验...
结果：风控拦截：用户USER_RISK_001存在风险，请联系客服
成功：false

===== 测试用例4：优惠券无效 =====
执行库存校验...
✓ 库存校验通过
执行风控校验...
✓ 风控校验通过
执行优惠券校验...
结果：优惠券已使用或已过期：COUPON_200
成功：false
```

---

## 对应实际项目

该设计模式在实际数据上报系统中的应用：

**责任链编排**：
- `DataReportStatusHandler` (优先级1) → 状态校验
- `DataReportInstanceSaveHandler` (优先级2) → 保存总批次
- `DataReportAggregationHandler` (优先级3) → 数据归集

**关键差异**：
- 实际项目用 `setNext()` 硬编码顺序（配置类组装）
- 本Demo用 `code` 字段动态排序（更灵活）

**共同点**：
- 短路机制：任一步骤失败立即返回
- 单一职责：每个Handler独立
- 职责清晰：校验、处理、归集分离

---

## 技术栈

- Java 8+
- Maven
- Lombok

---

## 扩展建议

### 新增Filter
1. 继承 `OrderFilter`
2. 定义 `code`（决定执行顺序）
3. 实现 `doFilter()` 校验逻辑
4. 添加到 `OrderFilterHandler` 的Filter列表

### 改为Spring管理
```java
@Configuration
public class FilterChainConfig {
    @Bean
    public OrderFilterHandler orderFilterHandler(
            StockFilter stockFilter,
            RiskFilter riskFilter,
            CouponFilter couponFilter) {
        return new OrderFilterHandler(Arrays.asList(
            stockFilter, riskFilter, couponFilter
        ));
    }
}
```

---

## 学习价值

- 理解责任链模式的核心机制
- 掌握短路优化技巧
- 学会用code字段动态控制执行顺序
- 为实际项目的复杂校验链路打基础


