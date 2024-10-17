package com.jikkosoft.cachesystem.sesion.aplicaction.usecase;

import org.springframework.stereotype.Service;

import com.jikkosoft.cachesystem.sesion.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.sesion.domain.port.in.SaveCacheEntryUseCase;
import com.jikkosoft.cachesystem.sesion.domain.port.out.CacheRepositoryPort;

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
