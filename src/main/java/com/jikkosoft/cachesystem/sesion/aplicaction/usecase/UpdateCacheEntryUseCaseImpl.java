package com.jikkosoft.cachesystem.aplicaction.usecase;

import org.springframework.stereotype.Service;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.UpdateCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Service
public class UpdateCacheEntryUseCaseImpl implements UpdateCacheEntryUseCase {
	
	private final CacheRepositoryPort cacheRepositoryPort;

	@Override
	public void updateCacheEntry(CacheEntry entry) {
		cacheRepositoryPort.update(entry);		
	}	

}
