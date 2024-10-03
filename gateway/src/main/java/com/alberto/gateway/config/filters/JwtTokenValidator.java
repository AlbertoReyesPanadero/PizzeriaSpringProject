package com.alberto.gateway.config.filters;

import com.alberto.gateway.util.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class JwtTokenValidator extends AbstractGatewayFilterFactory<JwtTokenValidator.Config> {

    private final JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        super(Config.class);
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (token == null || !token.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            try {
                token = token.substring(7);

                DecodedJWT decodedToken = jwtUtils.validateToken(token);
                String username = jwtUtils.getUsername(decodedToken);
                String authorities = decodedToken.getClaim("authorities").asString();
                // Añadir los valores al header de la solicitud
                exchange = addHeaders(exchange, username, authorities);

            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }

    private ServerWebExchange addHeaders(ServerWebExchange exchange, String username, String authorities) {
        return exchange.mutate()
                .request(r -> r.headers(headers -> {
                    headers.add("X-Username", username);
                    headers.add("X-Authorities", authorities);
                }))
                .build();
    }


    public static class Config {

    }
}
