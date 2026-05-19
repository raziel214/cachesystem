package com.jikkosoft.cachesystem.domain.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public record CacheEntry(String key, Object value, Instant createdAt, Duration ttl) {

    public CacheEntry {
        Objects.requireNonNull(key, "key must not be null");
        if (key.isBlank()) {
            throw new IllegalArgumentException("key must not be blank");
        }
        if (createdAt == null) {
            createdAt = Instant.now();
        }
    }

    public static CacheEntry of(String key, Object value, Duration ttl) {
        return new CacheEntry(key, value, Instant.now(), ttl);
    }

    public boolean hasTtl() {
        return ttl != null && !ttl.isZero() && !ttl.isNegative();
    }

    public Instant expiresAt() {
        return hasTtl() ? createdAt.plus(ttl) : null;
    }
}
