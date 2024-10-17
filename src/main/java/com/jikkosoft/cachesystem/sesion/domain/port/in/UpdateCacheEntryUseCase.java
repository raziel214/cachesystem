package com.jikkosoft.cachesystem.sesion.domain.port.in;

import com.jikkosoft.cachesystem.sesion.domain.model.CacheEntry;

public interface UpdateCacheEntryUseCase {	
	 void updateCacheEntry(CacheEntry entry);
}
