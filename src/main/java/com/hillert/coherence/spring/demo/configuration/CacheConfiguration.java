package com.hillert.coherence.spring.demo.configuration;

import javax.cache.spi.CachingProvider;

import com.tangosol.coherence.jcache.CoherenceBasedCachingProvider;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration {

	@Bean
	public CachingProvider createCachingProvider() {
		CoherenceBasedCachingProvider provider = new CoherenceBasedCachingProvider();
		return provider;
	}

	@Bean
	public javax.cache.CacheManager createCacheManager(CachingProvider cachingProvider) {
		javax.cache.CacheManager cm = cachingProvider.getCacheManager();
		cm.createCache("greetingCache", new javax.cache.configuration.MutableConfiguration<>());
		return cachingProvider.getCacheManager();
	}

	@Bean
	public org.springframework.cache.CacheManager cacheManager(javax.cache.CacheManager jCacheCacheManager) {
		return new JCacheCacheManager(jCacheCacheManager);
	}

}
