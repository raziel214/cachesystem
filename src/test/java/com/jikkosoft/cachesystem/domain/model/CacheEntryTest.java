package com.jikkosoft.cachesystem.domain.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CacheEntryTest {

    @Test
    void rejectsNullKey() {
        assertThatThrownBy(() -> new CacheEntry(null, "v", Instant.now(), Duration.ofMinutes(1)))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void rejectsBlankKey() {
        assertThatThrownBy(() -> new CacheEntry("   ", "v", Instant.now(), Duration.ofMinutes(1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void defaultsCreatedAtWhenNull() {
        Instant before = Instant.now();
        CacheEntry entry = new CacheEntry("k", "v", null, Duration.ofSeconds(30));
        assertThat(entry.createdAt()).isAfterOrEqualTo(before);
    }

    @Test
    void hasTtlReturnsFalseForNullZeroOrNegative() {
        assertThat(CacheEntry.of("k", "v", null).hasTtl()).isFalse();
        assertThat(CacheEntry.of("k", "v", Duration.ZERO).hasTtl()).isFalse();
        assertThat(CacheEntry.of("k", "v", Duration.ofSeconds(-1)).hasTtl()).isFalse();
    }

    @Test
    void hasTtlReturnsTrueForPositiveDuration() {
        assertThat(CacheEntry.of("k", "v", Duration.ofMinutes(5)).hasTtl()).isTrue();
    }

    @Test
    void expiresAtIsCreatedAtPlusTtl() {
        Instant now = Instant.parse("2026-01-01T00:00:00Z");
        CacheEntry entry = new CacheEntry("k", "v", now, Duration.ofMinutes(10));
        assertThat(entry.expiresAt()).isEqualTo(now.plus(Duration.ofMinutes(10)));
    }

    @Test
    void expiresAtIsNullWithoutTtl() {
        assertThat(CacheEntry.of("k", "v", null).expiresAt()).isNull();
    }
}
