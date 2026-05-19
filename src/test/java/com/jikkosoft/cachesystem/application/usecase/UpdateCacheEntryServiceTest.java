package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.exception.CacheEntryNotFoundException;
import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateCacheEntryServiceTest {

    @Mock
    private CacheRepositoryPort cacheRepository;

    @InjectMocks
    private UpdateCacheEntryService service;

    @Test
    void savesWhenEntryExists() {
        CacheEntry entry = CacheEntry.of("user:1", "alice", Duration.ofMinutes(5));
        when(cacheRepository.exists("user:1")).thenReturn(true);
        service.update(entry);
        verify(cacheRepository).save(entry);
    }

    @Test
    void throwsWhenEntryMissing() {
        CacheEntry entry = CacheEntry.of("ghost", "x", null);
        when(cacheRepository.exists("ghost")).thenReturn(false);

        assertThatThrownBy(() -> service.update(entry))
                .isInstanceOf(CacheEntryNotFoundException.class);

        verify(cacheRepository, never()).save(entry);
    }
}
