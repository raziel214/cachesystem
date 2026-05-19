package com.jikkosoft.cachesystem.infrastructure.rest.dto;

import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;

public record CacheEntryRequest(
        @Schema(description = "Cache key", example = "user:42")
        @NotBlank
        String key,

        @Schema(description = "Cache value (any JSON-serializable object)")
        @NotNull
        Object value,

        @Schema(description = "Optional TTL in ISO-8601 duration format. Omit for no expiration.", example = "PT15M")
        Duration ttl
) {
    public CacheEntry toDomain() {
        return CacheEntry.of(key, value, ttl);
    }
}
