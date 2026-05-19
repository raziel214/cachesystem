package com.jikkosoft.cachesystem.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Duration;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CacheEntryResponse(
        @Schema(example = "user:42") String key,
        Object value,
        Instant createdAt,
        @Schema(description = "TTL in ISO-8601 format", example = "PT15M") Duration ttl,
        @Schema(description = "Absolute expiration moment if a TTL is set") Instant expiresAt
) {
    public static CacheEntryResponse from(CacheEntry entry) {
        return new CacheEntryResponse(
                entry.key(),
                entry.value(),
                entry.createdAt(),
                entry.ttl(),
                entry.expiresAt()
        );
    }
}
