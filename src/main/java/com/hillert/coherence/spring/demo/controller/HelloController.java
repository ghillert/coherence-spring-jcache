package com.hillert.coherence.spring.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	private static final Logger LOGGER = LogManager.getLogger(HelloController.class);

	private final CacheManager cacheManager;

	public HelloController(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@RequestMapping("/")
	public String index() {
		final Cache cache = this.cacheManager.getCache("greetingCache");

		Integer counter = cache.get("counter", Integer.class);

		if (counter == null) {
			counter = 0;
		}
		else {
			counter++;
		}

		cache.put("counter", counter);

		LOGGER.info("Counter: {}.", counter);
		return "Greetings from Coherence! " + counter;
	}
}
