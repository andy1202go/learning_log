## Controller标准
https://mp.weixin.qq.com/s/QhfIztBmQ_xC3oai8y8HQg
本篇主要要介绍的就是 controller 层的处理，一个完整的后端请求由 4 部分组成：

- 接口地址（也就是 URL 地址）

- 请求方式（一般就是 get、set，当然还有 put、delete）

- 请求数据（request，有 head 跟 body）

- 响应数据（response）



本篇将解决以下 3 个问题：

- 当接收到请求时，如何优雅的校验参数
- 返回响应数据该如何统一的进行处理
- 接收到请求，处理业务逻辑时抛出了异常又该如何处理
### Controller 层参数接收

### 统一状态码
统一使用com.example.demo.general下的ResultVo封装返回，状态码是ResultCode

### 统一校验
spring-validation使用中，实体入参统一加上@Validated，重要参数加注解校验

### 统一响应
ResultVo或单纯String;
AOP 拦截所有 Controller，再 @After 的时候统一封装实体；
如果是String，需要加单独注解NotControllerResponseAdvice

### 统一异常
@RestControllerAdvice 来增强所有 @RestController，然后使用 @ExceptionHandler 注解，就可以拦截到对应的异常