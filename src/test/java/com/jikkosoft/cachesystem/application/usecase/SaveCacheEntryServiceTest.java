package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveCacheEntryServiceTest {

    @Mock
    private CacheRepositoryPort cacheRepository;

    @InjectMocks
    private SaveCacheEntryService service;

    @Test
    void delegatesToRepository() {
        CacheEntry entry = CacheEntry.of("user:1", "alice", Duration.ofMinutes(5));
        service.save(entry);
        verify(cacheRepository).save(entry);
    }
}
