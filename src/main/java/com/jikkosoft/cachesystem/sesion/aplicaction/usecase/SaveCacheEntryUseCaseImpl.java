package com.jikkosoft.cachesystem.aplicaction.usecase;

import org.springframework.stereotype.Service;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.SaveCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class SaveCacheEntryUseCaseImpl implements SaveCacheEntryUseCase {
	
	private final CacheRepositoryPort cacheRepositoryPort;

	@Override
	public void save(CacheEntry cacheEntry) {
		// TODO Auto-generated method stub
		cacheRepositoryPort.save(cacheEntry);
		
	}
	
}
