package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.SaveCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SaveCacheEntryService implements SaveCacheEntryUseCase {

    private final CacheRepositoryPort cacheRepository;

    @Override
    public void save(CacheEntry cacheEntry) {
        log.debug("Saving cache entry with key={} ttl={}", cacheEntry.key(), cacheEntry.ttl());
        cacheRepository.save(cacheEntry);
    }
}
