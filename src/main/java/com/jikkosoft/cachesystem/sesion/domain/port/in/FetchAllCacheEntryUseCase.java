package com.jikkosoft.cachesystem.sesion.domain.port.in;

import java.util.List;

import com.jikkosoft.cachesystem.sesion.domain.model.CacheEntry;


public interface FetchAllCacheEntryUseCase {	
	List<CacheEntry> fetchAllCacheEntry();
}
