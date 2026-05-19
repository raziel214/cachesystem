package com.jikkosoft.cachesystem.domain.exception;

public class CacheEntryNotFoundException extends RuntimeException {

    public CacheEntryNotFoundException(String key) {
        super("Cache entry not found for key: " + key);
    }
}
