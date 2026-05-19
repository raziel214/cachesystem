package com.jikkosoft.cachesystem.domain.port.in;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;

import java.util.List;

public interface FetchAllCacheEntriesUseCase {
    List<CacheEntry> fetchAll();
}
