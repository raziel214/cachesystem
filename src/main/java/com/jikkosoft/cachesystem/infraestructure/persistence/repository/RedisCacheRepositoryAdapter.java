package com.jikkosoft.cachesystem.infraestructure.persistence.repository;

import java.util.List;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;

@Component
public class RedisCacheRepositoryAdapter implements CacheRepositoryPort {
	

    private final RedisTemplate<String, CacheEntry> redisTemplate;

    public RedisCacheRepositoryAdapter(RedisTemplate<String, CacheEntry> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

	@Override
	public void save(CacheEntry cacheEntry) {
		redisTemplate.opsForValue().set(cacheEntry.getKey(), cacheEntry);
		
	}

	@Override
	public CacheEntry findByKey(String key) {
		
		return redisTemplate.opsForValue().get(key);
	}

	@Override
	public void update(CacheEntry entry) {
		save(entry);		
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);		
	}

	@Override
	public List<CacheEntry> findAll() {
		
		return redisTemplate.opsForValue().multiGet(redisTemplate.keys("*"));
	}

}
