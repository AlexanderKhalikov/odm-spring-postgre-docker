package com.odmWithKotlin.service

import ServiceDeployNew.*
import com.odmWithKotlin.database.repository.BadValuesRepository
import lombok.SneakyThrows
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.StringWriter
import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBException

@Service
class ServiceDeployImpl : ServiceDeployOld.ServiceOppDecisionService {

    @Autowired
    private lateinit var serviceOppDecisionServiceNew: ServiceOppDecisionService

    @Autowired
    private lateinit var modelMapper: ModelMapper

    @Value("\${webservice.client.urlServiceDeployNew}")
    private lateinit var clientUrlServiceDeployNew: String

    @Autowired
    private lateinit var badValuesRepository: BadValuesRepository

    private val logger = LoggerFactory.getLogger(ServiceOppDecisionService::class.java)

    @SneakyThrows
    override fun serviceOpp(
            serviceOppRequest: ServiceDeployOld.ServiceOppRequest
    ): ServiceDeployOld.ServiceOppResponse {

        /** mapping old to new */
        val serviceOppRequestNew = modelMapper.map(
                serviceOppRequest, ServiceDeployNew.ServiceOppRequest::class.java, ""
        )
        logger.debug("Mapping successful")
        serviceOppRequestNew.request.request.isAddtitionalValue = true

        try {
            val jaxbContext = JAXBContext.newInstance(ServiceDeployNew.ServiceOppRequest::class.java)
            val jaxbMarshaller = jaxbContext.createMarshaller()
            val sw = StringWriter()
            jaxbMarshaller.marshal(serviceOppRequestNew, sw)
            logger.debug("Got filled request for ServiceDeployNew")
            logger.debug("Request to ServiceDeployNew:")
            logger.debug(sw.toString())
        } catch (e: JAXBException) {
            logger.debug(e.printStackTrace().toString())
        }

        /** Calling ODM */
        logger.info("Calling ODM with URL: '$clientUrlServiceDeployNew'")
        val serviceOppResponseNew = serviceOppDecisionServiceNew
                .serviceOpp(
                        serviceOppRequestNew
                )

        logger.info("Call to Postgres DB")
        serviceOppResponseNew
                .response
                .response
                .isAnswer = badValuesRepository.allBadValues.contains(
                serviceOppRequestNew.request.request.name
        )

        logger.info("SOAP service ODM ServiceOppRequestNew responded successfully!")
        return modelMapper.map(
                serviceOppResponseNew, ServiceDeployOld.ServiceOppResponse::class.java
        )
    }
}