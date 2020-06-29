package com.odmWithKotlin.database.repository

import com.odmWithKotlin.database.model.EntityOfBadValues
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BadValuesRepository : JpaRepository<EntityOfBadValues?, Int?> {
    @get:Cacheable("bad_value")
    @get:Query(value = "select bad_value from entity_of_bad_values", nativeQuery = true)
    val allBadValues: List<String>
}
