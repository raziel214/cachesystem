package com.jikkosoft.cachesystem.aplicaction.usecase;

import org.springframework.stereotype.Service;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.FindByIdCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.out.CacheRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class FindByIdCacheEntryUseCaseImpl implements FindByIdCacheEntryUseCase{
	

	private final CacheRepositoryPort cacheRepositoryPort;

	@Override
	public CacheEntry findById(String key) {
		// TODO Auto-generated method stub
		return cacheRepositoryPort.findByKey(key);
	}

	
	
}
