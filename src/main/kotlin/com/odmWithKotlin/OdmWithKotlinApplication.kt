package com.odmWithKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class OdmWithKotlinApplication

fun main(args: Array<String>) {
	runApplication<OdmWithKotlinApplication>(*args)
}
