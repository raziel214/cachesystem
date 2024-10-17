package com.jikkosoft.cachesystem.sesion.domain.port.out;

import java.util.List;

import com.jikkosoft.cachesystem.sesion.domain.model.CacheEntry;



public interface CacheRepositoryPort {
	
	void save(CacheEntry cacheEntry);
	CacheEntry findByKey(String key);
	void update(CacheEntry entry);
	void delete(String key);
	List<CacheEntry> findAll();
}
