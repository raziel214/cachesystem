package com.jikkosoft.cachesystem.sesion.aplicaction.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jikkosoft.cachesystem.sesion.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.sesion.domain.port.in.FetchAllCacheEntryUseCase;
import com.jikkosoft.cachesystem.sesion.domain.port.out.CacheRepositoryPort;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Service
public class FetchAllCacheEntryUseCaseImpl implements FetchAllCacheEntryUseCase {
	
	private final CacheRepositoryPort cacheRepositoryPort;

	@Override
	public List<CacheEntry> fetchAllCacheEntry() {
		// TODO Auto-generated method stub
		return cacheRepositoryPort.findAll();
	}	

	
}
