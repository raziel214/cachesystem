package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FetchAllCacheEntriesServiceTest {

    @Mock
    private CacheRepositoryPort cacheRepository;

    @InjectMocks
    private FetchAllCacheEntriesService service;

    @Test
    void returnsAllEntries() {
        List<CacheEntry> entries = List.of(
                CacheEntry.of("a", 1, null),
                CacheEntry.of("b", 2, null));
        when(cacheRepository.findAll()).thenReturn(entries);

        assertThat(service.fetchAll()).containsExactlyElementsOf(entries);
    }
}
