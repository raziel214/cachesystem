package com.jikkosoft.cachesystem.aplicaction.service;

import java.util.List;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.DeleteCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.in.FetchAllCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.in.FindByIdCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.in.SaveCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.in.UpdateCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.in.FetchAllCacheEntryUseCase;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CacheEntryService implements SaveCacheEntryUseCase, FetchAllCacheEntryUseCase,FindByIdCacheEntryUseCase,UpdateCacheEntryUseCase, DeleteCacheEntryUseCase {

	private final SaveCacheEntryUseCase saveCacheEntryUseCase;
	private final FetchAllCacheEntryUseCase fetchAllCacheEntryUseCase;
	private final FindByIdCacheEntryUseCase findByIdCacheEntryUseCase;
	private final UpdateCacheEntryUseCase updateCacheEntryUseCase;
	private final DeleteCacheEntryUseCase deleteCacheEntryUseCase;

	

	@Override
	public void delete(String key) {
		deleteCacheEntryUseCase.delete(key);
	}

	@Override
	public void updateCacheEntry(CacheEntry entry) {
		updateCacheEntryUseCase.updateCacheEntry(entry);		
	}

	@Override
	public CacheEntry findById(String key) {
		return findByIdCacheEntryUseCase.findById(key);
	}		
	

	@Override
	public void save(CacheEntry cacheEntry) {
		saveCacheEntryUseCase.save(cacheEntry);  
		
	}

	@Override
	public List<CacheEntry> fetchAllCacheEntry() {
		// TODO Auto-generated method stub
		return fetchAllCacheEntryUseCase.fetchAllCacheEntry();
	}
}
