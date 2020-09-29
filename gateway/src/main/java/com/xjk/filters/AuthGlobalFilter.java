package com.xjk.filters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * 自定义全局过滤器需要实现GlobalFilter和Ordered接口
 * @author xujunkai
 */
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    //完成判断逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain
            chain) {
        URI uri = exchange.getRequest().getURI();
        String path = uri.getPath();
        if(path.contains("/mnt/")){
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (StringUtils.isBlank(token)) {
                System.out.println("鉴权失败");
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

//调用chain.filter继续向下游执行
        return chain.filter(exchange);
    }
    //顺序,数值越小,优先级越高
    @Override
    public int getOrder() {

        return 0;
    }
}
