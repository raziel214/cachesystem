package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCacheEntryByKeyServiceTest {

    @Mock
    private CacheRepositoryPort cacheRepository;

    @InjectMocks
    private FindCacheEntryByKeyService service;

    @Test
    void returnsEntryWhenPresent() {
        CacheEntry entry = CacheEntry.of("k", "v", null);
        when(cacheRepository.findByKey("k")).thenReturn(Optional.of(entry));
        assertThat(service.findByKey("k")).contains(entry);
    }

    @Test
    void returnsEmptyWhenMissing() {
        when(cacheRepository.findByKey("ghost")).thenReturn(Optional.empty());
        assertThat(service.findByKey("ghost")).isEmpty();
    }
}
