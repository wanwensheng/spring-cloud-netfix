package com.wan.springcloudgatewayserver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Author: WanWenSheng
 * @Description:
 * @Dete: Created in 14:50 2020/12/25
 * @Modified By:
 */
@Component
public class IpAddressKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        System.out.println("请求被执行。。。。。。");
        System.out.println(new Date());
        return  Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
