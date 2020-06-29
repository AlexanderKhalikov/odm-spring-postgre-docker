package com.odmWithKotlin.service

import ServiceDeployNew.*
import lombok.SneakyThrows
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ServiceDeployImpl : ServiceOppDecisionService {

    @Autowired
    private lateinit var serviceOppDecisionServiceNew: ServiceOppDecisionService

    @Value("\${webservice.client.urlServiceDeployNew}")
    private lateinit var clientUrlServiceDeployNew: String

    private val logger = LoggerFactory.getLogger(ServiceOppDecisionService::class.java)

    @SneakyThrows
    override fun serviceOpp(
            serviceOppRequest: ServiceOppRequest
    ): ServiceOppResponse {

        logger.info("Calling SOAP service with URL: '$clientUrlServiceDeployNew'")
        val response = ServiceOppResponse().apply {
            response = Response().apply {
                response = Response2()
            }
        }

        logger.info("Successfully called SOAP serviceOppDecisionServiceNew service!")

        response.response.response.isAnswer = true

        println("Всё ок")
        println(response)
        return response
    }
}