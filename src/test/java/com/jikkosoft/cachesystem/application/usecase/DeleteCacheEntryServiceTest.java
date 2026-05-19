package com.jikkosoft.cachesystem.application.usecase;

import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteCacheEntryServiceTest {

    @Mock
    private CacheRepositoryPort cacheRepository;

    @InjectMocks
    private DeleteCacheEntryService service;

    @Test
    void delegatesToRepository() {
        service.delete("user:1");
        verify(cacheRepository).delete("user:1");
    }
}
