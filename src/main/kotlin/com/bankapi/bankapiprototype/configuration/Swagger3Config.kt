package com.bankapi.bankapiprototype.configuration

import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger3Config {
    @Bean
    fun publicApi(): GroupedOpenApi?{
        return GroupedOpenApi.builder().group("bankapiprototype")
            .pathsToMatch("/customers/**", "/credits/**")
            .build()
    }
}