package com.jikkosoft.cachesystem.infrastructure.persistence;

import com.jikkosoft.cachesystem.AbstractRedisIntegrationTest;
import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisCacheRepositoryAdapterIT extends AbstractRedisIntegrationTest {

    @Autowired
    private RedisCacheRepositoryAdapter adapter;

    @Test
    void persistsAndRetrievesEntry() {
        CacheEntry entry = CacheEntry.of("user:1", "alice", Duration.ofMinutes(5));
        adapter.save(entry);

        Optional<CacheEntry> found = adapter.findByKey("user:1");
        assertThat(found).isPresent();
        assertThat(found.get().value()).isEqualTo("alice");
    }

    @Test
    void existsReflectsState() {
        adapter.save(CacheEntry.of("exists-check", "x", null));
        assertThat(adapter.exists("exists-check")).isTrue();
        adapter.delete("exists-check");
        assertThat(adapter.exists("exists-check")).isFalse();
    }

    @Test
    void findAllReturnsAllStoredEntries() {
        adapter.save(CacheEntry.of("k1", "v1", null));
        adapter.save(CacheEntry.of("k2", "v2", null));

        List<CacheEntry> all = adapter.findAll();
        assertThat(all).extracting(CacheEntry::key).contains("k1", "k2");
    }

    @Test
    void deleteRemovesEntry() {
        adapter.save(CacheEntry.of("ephemeral", "v", null));
        adapter.delete("ephemeral");
        assertThat(adapter.findByKey("ephemeral")).isEmpty();
    }
}
