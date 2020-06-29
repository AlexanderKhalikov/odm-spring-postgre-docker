package com.odmWithKotlin.database.controller

import com.odmWithKotlin.database.model.EntityOfBadValues
import com.odmWithKotlin.database.repository.BadValuesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct

@RestController
class DataBaseController() {

    @Autowired
    private lateinit var badValuesRepository: BadValuesRepository

    @PostConstruct
    fun init() {
        badValuesRepository.saveAll(List(10) {EntityOfBadValues(getRandomString(10))})
    }

    @GetMapping("/badValues/all")
    fun getAllInns() = badValuesRepository.findAll()

    @GetMapping("/badValues/values")
    fun getAllInnValues() = badValuesRepository.allBadValues

    @PostMapping("/badValues/save")
    fun save(@RequestBody newEntity: EntityOfBadValues): EntityOfBadValues {
        badValuesRepository.save(newEntity)
        return newEntity
    }

    fun getRandomString(length: Int) : String {
        val allowedChars = ('A'..'Z') + ('a'..'z')
        return (1..length)
                .map { allowedChars.random() }
                .joinToString("")
    }
}