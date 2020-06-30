package com.odmWithKotlin.database.cacheConfig

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import javax.management.timer.Timer

@Configuration
@EnableCaching
@EnableScheduling
class CachingConfig {
    @Bean
    fun cacheManager() = SimpleCacheManager().apply {
        setCaches(listOf(ConcurrentMapCache("bad_value")))
    }

    //    Каждый час очищаем кэш базы данных
    @Scheduled(fixedRate = Timer.ONE_HOUR)
    @CacheEvict(value = ["bad_value"], allEntries = true)
    fun clearCache() {
    }
}