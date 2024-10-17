package com.jikkosoft.cachesystem.sesion.domain.port.in;

import com.jikkosoft.cachesystem.sesion.domain.model.CacheEntry;

public interface FindByIdCacheEntryUseCase {
	
	CacheEntry findById(String key);

}
