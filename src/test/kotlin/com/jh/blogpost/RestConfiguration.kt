package com.jh.blogpost

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter

import org.springframework.hateoas.mediatype.hal.Jackson2HalModule

import com.fasterxml.jackson.databind.DeserializationFeature

import com.fasterxml.jackson.databind.ObjectMapper

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.hateoas.MediaTypes

import org.springframework.web.client.RestTemplate


@Configuration
class RestConfiguration {
    @Bean
//    @LoadBalanced
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        val mapper = ObjectMapper()
        mapper.configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
            false
        )
        mapper.registerModule(Jackson2HalModule())
        val converter = MappingJackson2HttpMessageConverter()
        converter.supportedMediaTypes = listOf(MediaTypes.HAL_JSON)
        converter.objectMapper = mapper
        return builder.messageConverters(converter).build()
    }
}
