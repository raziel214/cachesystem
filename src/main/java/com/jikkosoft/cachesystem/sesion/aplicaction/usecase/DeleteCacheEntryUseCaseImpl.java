package com.jikkosoft.cachesystem.sesion.aplicaction.usecase;

import org.springframework.stereotype.Service;

import com.jikkosoft.cachesystem.sesion.domain.port.in.DeleteCacheEntryUseCase;
import com.jikkosoft.cachesystem.sesion.domain.port.out.CacheRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class DeleteCacheEntryUseCaseImpl implements DeleteCacheEntryUseCase {
	
	private final CacheRepositoryPort cacheRepositoryPort;

	@Override
	public void delete(String key) {
		cacheRepositoryPort.delete(key);
		
	}

	

}
