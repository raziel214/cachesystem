package com.jikkosoft.cachesystem.domain.port.in;

import java.util.List;
import com.jikkosoft.cachesystem.domain.model.CacheEntry;

public interface FetchAllCacheEntryUseCase {	
	List<CacheEntry> fetchAllCacheEntry();
}
