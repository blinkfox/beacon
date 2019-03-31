package com.blinkfox.beacon.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 错误页面配置处理类.
 *
 * @author blinkfox on 2019-03-31.
 */
@Configuration
public class ErrorPageConfig {

    /**
     * 配置错误页面处理.
     *
     * @return WebServerFactoryCustomizer实例
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.html"));
            factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html"));
        });
    }

}
