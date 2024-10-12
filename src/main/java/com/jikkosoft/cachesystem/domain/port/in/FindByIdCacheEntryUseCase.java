package com.jikkosoft.cachesystem.domain.port.in;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;

public interface FindByIdCacheEntryUseCase {
	
	CacheEntry findById(String key);

}
