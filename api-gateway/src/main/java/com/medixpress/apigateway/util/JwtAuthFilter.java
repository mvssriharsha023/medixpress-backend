package com.medixpress.apigateway.util;

import com.medixpress.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Long userId = Long.valueOf(jwtUtil.extractId(token));
                // You can't directly set attributes like in servlet request
                // But you can mutate the request and pass info via headers
                exchange = exchange.mutate()
                        .request(r -> r.headers(headers -> headers.add("id", String.valueOf(userId))))
                        .build();
            } catch (Exception e) {
                System.out.println("Invalid token: " + e.getMessage());
                // Optionally reject the request
            }
        }

        return chain.filter(exchange);
    }
}
