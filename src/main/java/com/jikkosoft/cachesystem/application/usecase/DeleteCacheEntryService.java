package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.port.in.DeleteCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteCacheEntryService implements DeleteCacheEntryUseCase {

    private final CacheRepositoryPort cacheRepository;

    @Override
    public void delete(String key) {
        log.debug("Deleting cache entry with key={}", key);
        cacheRepository.delete(key);
    }
}
