package com.odmWithKotlin.client

import ServiceDeployNew.ServiceOppDecisionService
import org.apache.cxf.ext.logging.LoggingInInterceptor
import org.apache.cxf.ext.logging.LoggingOutInterceptor
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.web.client.RestTemplate

@Configuration
class ClientConfig {
    @Value("\${webservice.client.urlServiceDeployNew}")
    private lateinit var clientUrlServiceDeployNew: String

    @Value("\${webservice.client.cloudUsername}")
    private lateinit var userName: String

    @Value("\${webservice.client.cloudPassword}")
    private lateinit var passWord: String

    @Bean
    fun serviceOppDecisionServiceNew() = JaxWsProxyFactoryBean().apply {
        serviceClass = ServiceOppDecisionService::class.java
        address = clientUrlServiceDeployNew
        username = userName
        password = passWord
        inInterceptors.add(loggingOutInterceptor())
        outInterceptors.add(loggingInInterceptor())
    }.create() as ServiceOppDecisionService

    @Bean
    fun loggingInInterceptor() = LoggingInInterceptor().apply {
        setPrettyLogging(true)
    }

    @Bean
    fun loggingOutInterceptor() = LoggingOutInterceptor().apply {
        setPrettyLogging(true)
    }

    @Bean
    fun restTemplate() = RestTemplate()

    @Bean
    fun modelMapper() = ModelMapper().apply {
        configuration.matchingStrategy = MatchingStrategies.STANDARD
        configuration.fieldAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE
        configuration.methodAccessLevel = org.modelmapper.config.Configuration.AccessLevel.PRIVATE
        configuration.isFieldMatchingEnabled = true
        configuration.isAmbiguityIgnored = true
        typeMap(ServiceDeployOld.ServiceOppRequest::class.java, ServiceDeployNew.ServiceOppRequest::class.java)
    }

    companion object {
        @Bean
        fun propertySourcesPlaceholderConfigurer() = PropertySourcesPlaceholderConfigurer()
    }
}