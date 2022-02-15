package com.example.airqualityservice.utils;

import com.example.airqualityservice.controller.dto.AirQualityResDto;
import com.example.airqualityservice.service.City;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

//    @Bean
//    public org.ehcache.CacheManager ehCacheManager() {
//        org.ehcache.CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .withCache("cacheStore", CacheConfigurationBuilder
//                        .newCacheConfigurationBuilder(City.class, AirQualityResDto.class, ResourcePoolsBuilder.heap(10))
//                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(5))))
//                .build();
//
//        cacheManager.init();
//
//        return cacheManager;
//    }
}
