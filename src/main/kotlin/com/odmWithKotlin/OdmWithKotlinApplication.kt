package com.odmWithKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class OdmWithKotlinApplication

fun main(args: Array<String>) {
	runApplication<OdmWithKotlinApplication>(*args)
}
