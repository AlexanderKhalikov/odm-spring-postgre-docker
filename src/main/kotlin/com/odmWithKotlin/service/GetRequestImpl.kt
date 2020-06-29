package com.mediationLayer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GetRequestImpl : GetRequest {
    @Autowired
    private lateinit var restTemplate: RestTemplate

    override fun getRequest(url: String): HttpEntity<String> {
        val headers = HttpHeaders()
        headers["Accept"] = "application/xml"
        headers.contentType = MediaType.APPLICATION_XML
        val entity: HttpEntity<*> = HttpEntity<Any>(headers)
        return restTemplate.exchange(
                url, HttpMethod.GET, entity, String::class.java)
    }
}