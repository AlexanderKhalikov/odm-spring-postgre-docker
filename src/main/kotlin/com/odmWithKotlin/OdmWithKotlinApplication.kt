package com.odmWithKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OdmWithKotlinApplication

fun main(args: Array<String>) {
	runApplication<OdmWithKotlinApplication>(*args)
}
