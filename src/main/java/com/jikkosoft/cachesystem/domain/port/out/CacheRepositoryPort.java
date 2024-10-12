package com.jikkosoft.cachesystem.domain.port.out;

import java.util.List;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;

public interface CacheRepositoryPort {
	
	void save(CacheEntry cacheEntry);
	CacheEntry findByKey(String key);
	void update(CacheEntry entry);
	void delete(String key);
	List<CacheEntry> findAll();
}
