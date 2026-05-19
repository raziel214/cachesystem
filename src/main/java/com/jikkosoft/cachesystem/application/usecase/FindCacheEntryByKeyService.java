package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.FindCacheEntryByKeyUseCase;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindCacheEntryByKeyService implements FindCacheEntryByKeyUseCase {

    private final CacheRepositoryPort cacheRepository;

    @Override
    public Optional<CacheEntry> findByKey(String key) {
        return cacheRepository.findByKey(key);
    }
}
