package com.odmWithKotlin.service

import org.springframework.http.HttpEntity

interface GetRequest {
    fun getRequest(url: String): HttpEntity<String>
}