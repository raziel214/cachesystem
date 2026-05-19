package com.jikkosoft.cachesystem.infrastructure.rest;

import com.jikkosoft.cachesystem.domain.exception.CacheEntryNotFoundException;
import com.jikkosoft.cachesystem.domain.model.CacheEntry;
import com.jikkosoft.cachesystem.domain.port.in.DeleteCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.in.FetchAllCacheEntriesUseCase;
import com.jikkosoft.cachesystem.domain.port.in.FindCacheEntryByKeyUseCase;
import com.jikkosoft.cachesystem.domain.port.in.SaveCacheEntryUseCase;
import com.jikkosoft.cachesystem.domain.port.in.UpdateCacheEntryUseCase;
import com.jikkosoft.cachesystem.infrastructure.rest.dto.CacheEntryRequest;
import com.jikkosoft.cachesystem.infrastructure.rest.dto.CacheEntryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cache")
@RequiredArgsConstructor
@Tag(name = "Cache", description = "Distributed cache management")
public class CacheController {

    private final SaveCacheEntryUseCase saveCacheEntryUseCase;
    private final FindCacheEntryByKeyUseCase findCacheEntryByKeyUseCase;
    private final FetchAllCacheEntriesUseCase fetchAllCacheEntriesUseCase;
    private final UpdateCacheEntryUseCase updateCacheEntryUseCase;
    private final DeleteCacheEntryUseCase deleteCacheEntryUseCase;

    @Operation(summary = "Create a cache entry")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Entry created"),
            @ApiResponse(responseCode = "400", description = "Invalid payload")
    })
    @PostMapping
    public ResponseEntity<CacheEntryResponse> create(@Valid @RequestBody CacheEntryRequest request) {
        CacheEntry entry = request.toDomain();
        saveCacheEntryUseCase.save(entry);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{key}")
                .buildAndExpand(entry.key())
                .toUri();
        return ResponseEntity.created(location).body(CacheEntryResponse.from(entry));
    }

    @Operation(summary = "Find a cache entry by key")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entry found"),
            @ApiResponse(responseCode = "404", description = "Entry not found")
    })
    @GetMapping("/{key}")
    public ResponseEntity<CacheEntryResponse> findByKey(@PathVariable String key) {
        return findCacheEntryByKeyUseCase.findByKey(key)
                .map(CacheEntryResponse::from)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CacheEntryNotFoundException(key));
    }

    @Operation(summary = "Fetch all cache entries")
    @GetMapping
    public List<CacheEntryResponse> findAll() {
        return fetchAllCacheEntriesUseCase.fetchAll().stream()
                .map(CacheEntryResponse::from)
                .toList();
    }

    @Operation(summary = "Update an existing cache entry")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Entry updated"),
            @ApiResponse(responseCode = "404", description = "Entry not found")
    })
    @PutMapping("/{key}")
    public ResponseEntity<CacheEntryResponse> update(
            @PathVariable String key,
            @Valid @RequestBody CacheEntryRequest request) {
        CacheEntry entry = CacheEntry.of(key, request.value(), request.ttl());
        updateCacheEntryUseCase.update(entry);
        return ResponseEntity.ok(CacheEntryResponse.from(entry));
    }

    @Operation(summary = "Delete a cache entry")
    @DeleteMapping("/{key}")
    @ApiResponse(responseCode = "204", description = "Entry deleted (or never existed)")
    public ResponseEntity<Void> delete(@PathVariable String key) {
        deleteCacheEntryUseCase.delete(key);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
