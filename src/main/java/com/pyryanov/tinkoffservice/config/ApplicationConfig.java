package com.pyryanov.tinkoffservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final ApiProperties apiConfig;

    @Bean
    public OpenApi api() {
        //добавление токена с помощью Environment variables:
//        String ssoToken = System.getenv("ssoToken");
//        return new OkHttpOpenApi(ssoToken, apiConfig.getIsSandBoxMode());
        return new OkHttpOpenApi(apiConfig.getToken(), apiConfig.getIsSandBoxMode());
    }
}
