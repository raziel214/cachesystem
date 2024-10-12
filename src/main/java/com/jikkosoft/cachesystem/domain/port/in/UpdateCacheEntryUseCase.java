package com.jikkosoft.cachesystem.domain.port.in;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;

public interface UpdateCacheEntryUseCase {	
	 void updateCacheEntry(CacheEntry entry);
}
