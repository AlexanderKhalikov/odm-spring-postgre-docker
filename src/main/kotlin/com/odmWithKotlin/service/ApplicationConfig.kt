package com.odmWithKotlin.service

//import com.odmWithKotlin.database.controller.DataBaseController
import org.apache.cxf.Bus
import org.apache.cxf.bus.spring.SpringBus
import org.apache.cxf.ext.logging.LoggingFeature
import org.apache.cxf.jaxws.EndpointImpl
import org.apache.cxf.transport.servlet.CXFServlet
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.util.ArrayList
import javax.xml.ws.Endpoint


@Configuration
class ApplicationConfig {
    @Bean
    fun cxfServlet() = ServletRegistrationBean(CXFServlet(), "/DecisionService/*")
            .also<ServletRegistrationBean<CXFServlet>> {
                it.setName("cxfServlet")
            }

    @Bean
    @Primary
    fun dispatcherServletPathProvider() = DispatcherServletPath { "" }

    @Bean(name = [Bus.DEFAULT_BUS_ID])
    fun springBus(): SpringBus = SpringBus().apply {
        setFeatures(ArrayList(listOf(loggingFeature())))
    }

    @Bean
    fun loggingFeature(): LoggingFeature = LoggingFeature().apply {
        setPrettyLogging(true)
    }

    @Bean
    fun endpointServiceOppDecisionServiceNew(bus: Bus?, accountServiceEndpoint: ServiceDeployImpl?): Endpoint {
        val endpoint = EndpointImpl(bus, accountServiceEndpoint)
        endpoint.publish("/ws/serviceDeployNew/serviceOpp/v75")
        return endpoint
    }

//    @Bean
//    fun DataBaseController?.endpointEntity() = EndpointImpl(this)
}