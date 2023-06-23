
package com.letsgo.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.net.HttpHeaders;
import com.letsgo.apigateway.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	@Autowired
	private RouteValidator validator;
	@Autowired
	private RestTemplate template;
	@Autowired
	private JwtUtil jwtUtil;

	public AuthenticationFilter() {
		super(Config.class);
	}

	public static class Config {

	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			String role = "ROLE_CUSTOMER";
			if (validator.isSecured.test(exchange.getRequest())) {
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authentication header");
				}
				// System.out.println(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0));
				String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeaders != null && authHeaders.startsWith("Bearer")) {
					authHeaders = authHeaders.substring(7);
				}
				try {
					jwtUtil.validateToken(authHeaders);
				} catch (Exception e) {
					throw new RuntimeException("Invalid Access..");
				}
				role = jwtUtil.getRoleFromToken(authHeaders);
			}
			ServerHttpRequest request = exchange.getRequest().mutate().header("X-Role", role).build();
			return chain.filter(exchange.mutate().request(request).build());
		});
	}
}
