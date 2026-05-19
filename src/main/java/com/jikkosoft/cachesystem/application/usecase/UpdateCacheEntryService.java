package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.exception.CacheEntryNotFoundException;
import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.UpdateCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCacheEntryService implements UpdateCacheEntryUseCase {

    private final CacheRepositoryPort cacheRepository;

    @Override
    public void update(CacheEntry cacheEntry) {
        if (!cacheRepository.exists(cacheEntry.key())) {
            throw new CacheEntryNotFoundException(cacheEntry.key());
        }
        log.debug("Updating cache entry with key={}", cacheEntry.key());
        cacheRepository.save(cacheEntry);
    }
}
