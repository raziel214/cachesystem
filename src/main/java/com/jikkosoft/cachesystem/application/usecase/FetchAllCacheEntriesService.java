package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.FetchAllCacheEntriesUseCase;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FetchAllCacheEntriesService implements FetchAllCacheEntriesUseCase {

    private final CacheRepositoryPort cacheRepository;

    @Override
    public List<CacheEntry> fetchAll() {
        return cacheRepository.findAll();
    }
}
