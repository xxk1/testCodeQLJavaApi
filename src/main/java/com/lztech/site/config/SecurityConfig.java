package com.lztech.site.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private WhitelistProperties whitelistProperties;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(new IpAddressRequestMatcher(whitelistProperties.getAddresses())).permitAll()
                                .antMatchers(whitelistProperties.getUrls().toArray(new String[0])).permitAll() // Permit all whitelist endpoints
                                .antMatchers("/**").authenticated() // Non-whitelisted endpoints need authentication
                )

                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                        ).authenticationEntryPoint(customAuthenticationEntryPoint())
                );
        return http.build();
    }

    // 自定义RequestMatcher，用于检查请求的IP地址
    private static class IpAddressRequestMatcher implements RequestMatcher {
        private List<String> trustedIps;

        public IpAddressRequestMatcher(List<String> trustedIps) {
            this.trustedIps = trustedIps;
        }

        @Override
        public boolean matches(HttpServletRequest request) {
            String clientIp = getClientIP(request); // 获取客户端IP的方法，根据实际情况实现
            return trustedIps.stream().anyMatch(clientIp::matches);
        }

        private String getClientIP(HttpServletRequest request) {
            String xfHeader = request.getHeader("X-Forwarded-For");
            if (xfHeader != null && !xfHeader.isEmpty()) {
                return xfHeader.split(",")[0]; // 取代理链中的第一个IP作为客户端IP，根据实际情况调整
            }
            return request.getRemoteAddr();
        }
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        RealmRoleConverter realmRoleConverter = new RealmRoleConverter();

        return new CustomJwtAuthenticationConverter(realmRoleConverter);
    }

    @Bean
    public AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }


    // 配置 HttpFirewall，用于处理请求头中的特殊字符。(南医大请求头中有中文)
    @Bean
    public StrictHttpFirewall httpFirewall() {
        // 创建一个 StrictHttpFirewall 实例，用于定义 HTTP 防火墙规则。
        StrictHttpFirewall firewall = new StrictHttpFirewall();

        // 允许所有的请求头名称，不对请求头名称进行限制。
        firewall.setAllowedHeaderNames((header) -> true);

        // 允许所有的请求头值，不对请求头的值进行限制。
        firewall.setAllowedHeaderValues((header) -> true);

        // 允许所有的请求参数名称，不对参数名称进行限制。
        firewall.setAllowedParameterNames((parameter) -> true);

        // 返回配置完成的防火墙实例。
        return firewall;
    }


}
