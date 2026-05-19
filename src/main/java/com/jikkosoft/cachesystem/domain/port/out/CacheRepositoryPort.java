package com.jikkosoft.cachesystem.domain.port.out;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;

import java.util.List;
import java.util.Optional;

public interface CacheRepositoryPort {

    void save(CacheEntry cacheEntry);

    Optional<CacheEntry> findByKey(String key);

    List<CacheEntry> findAll();

    void delete(String key);

    boolean exists(String key);
}
