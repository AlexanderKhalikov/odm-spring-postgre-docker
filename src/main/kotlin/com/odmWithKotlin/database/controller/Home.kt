package com.odmWithKotlin.database.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Home {
    @RequestMapping("/")
    fun home() = "Hello Spring with Docker and Postgres"
}