package com.jikkosoft.cachesystem.infrastructure.persistence;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RedisCacheRepositoryAdapter implements CacheRepositoryPort {

    private static final String KEY_PREFIX = "cache:";
    private static final long SCAN_COUNT = 200L;

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisCacheRepositoryAdapter(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(CacheEntry cacheEntry) {
        String redisKey = toRedisKey(cacheEntry.key());
        if (cacheEntry.hasTtl()) {
            redisTemplate.opsForValue().set(redisKey, cacheEntry, cacheEntry.ttl());
        } else {
            redisTemplate.opsForValue().set(redisKey, cacheEntry);
        }
    }

    @Override
    public Optional<CacheEntry> findByKey(String key) {
        Object raw = redisTemplate.opsForValue().get(toRedisKey(key));
        return Optional.ofNullable(asCacheEntry(raw));
    }

    @Override
    public List<CacheEntry> findAll() {
        List<CacheEntry> result = new ArrayList<>();
        ScanOptions options = ScanOptions.scanOptions().match(KEY_PREFIX + "*").count(SCAN_COUNT).build();
        try (Cursor<String> cursor = redisTemplate.scan(options)) {
            while (cursor.hasNext()) {
                Object raw = redisTemplate.opsForValue().get(cursor.next());
                CacheEntry entry = asCacheEntry(raw);
                if (entry != null) {
                    result.add(entry);
                }
            }
        }
        return result;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(toRedisKey(key));
    }

    @Override
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(toRedisKey(key)));
    }

    private static String toRedisKey(String key) {
        return KEY_PREFIX + key;
    }

    @Nullable
    private static CacheEntry asCacheEntry(@Nullable Object raw) {
        return (raw instanceof CacheEntry entry) ? entry : null;
    }
}
