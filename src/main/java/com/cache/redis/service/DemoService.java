package com.cache.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
	@Autowired
	protected RedisTemplate<String, String> redisTemplate;

	public void setString(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}

	public String getString(String key) {
		String value = null;
		value=redisTemplate.opsForValue().get(key);
		return value;
	}
}
