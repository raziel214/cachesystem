package com.jikkosoft.cachesystem.domain.port.in;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;

import java.util.Optional;

public interface FindCacheEntryByKeyUseCase {
    Optional<CacheEntry> findByKey(String key);
}
